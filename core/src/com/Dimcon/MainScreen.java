package com.Dimcon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 * Created by daimonsewell on 7/11/14.
 */
public class MainScreen extends  Screen {
    public MainScreen() {
        Name = "Main";
    }


    @Override
    public void BeforeAll(DeltaBatch batch) {
        super.BeforeAll(batch);
    }

    @Override
    public void  AfterAll(DeltaBatch batch) {
        super.AfterAll(batch);

    }

    boolean touchingHandle = false,
            StartingGame = false,
            ChangeHandles = true;
    int StartingLevel = 0;
    float HandleX = 0,HandleY = 0,TouchLeft,TouchTop;
    Rect rReturn = new Rect();
    Rect HorPath = new Rect();
    Rect rSlowDown = new Rect();
    Rect rSpeedUP = new Rect();
    MenuOptions MenOp;

    @Override
    public Boolean Create(DeltaBatch batch) {
        rDisplay.MoveLeft(ScreenX);
        rDisplay.StartAnimT(rFullscreen, Interpolator.Decelerate, 1000);
        rDisplay.setAlpha(0f);
        rDisplay.StartAnimA(1f, Interpolator.Constant, 1000);
        ResMan.AddImage("Screen", "Screenshot.png");
        rReturn.RectCopy(Dodgeball.overlay.rHandle);
        HorPath = new Rect(rDisplay,10*fXunit,35*fYunit,90*fXunit,25*fYunit);
        rSlowDown = new Rect(HorPath.l(),HorPath.t() + (5*fYunit),HorPath.r(),HorPath.t());
        rSpeedUP = new Rect(rSlowDown.l(),rSlowDown.t() + (5*fYunit),rSlowDown.r(),rSlowDown.t());
        MenOp = new MenuOptions(fXunit,fYunit,40*fXunit,rDisplay);
        ClipToRDisplay = true;
        SetDebug(true);
        ResMan.AddRect("StartGame",new Rect());
        return super.Create(batch);
    }

    @Override
    public Boolean AnimIn(DeltaBatch batch) {
        rDisplay.DrawWithAlpha(ResMan.Get("Screen"), batch.batch, rDisplay.a());
        HorPath.Draw(ResMan.Get("Handle"), batch.batch);
        rSlowDown = new Rect(HorPath.l(),HorPath.b() + (10*fYunit),HorPath.r(),HorPath.b());
        rSpeedUP = new Rect(rSlowDown.l(),rSlowDown.t() + (10*fYunit),rSlowDown.r(),rSlowDown.t());
        RestrictHandle(batch.batch);
        MenOp.Update(HorPath,ResMan.GetRect("Handle"),ResMan.GetRect("Handle").CenterX(),rDisplay,batch.batch);
        return super.AnimIn(batch);
    }



    @Override
    public Boolean Draw(DeltaBatch batch) {
        HorPath.Draw(ResMan.Get("Handle"),batch.batch);
        rSlowDown = new Rect(HorPath.l(),HorPath.b() + (10*fYunit),HorPath.r(),HorPath.b());
        rSpeedUP = new Rect(rSlowDown.l(),rSlowDown.t() + (10*fYunit),rSlowDown.r(),rSlowDown.t());
        rDisplay.DrawWithAlpha((StartingGame?ResMan.Get("Screen") : ResMan.Get("Handle")), batch.batch, rDisplay.a());
        if (!StartingGame) MenOp.Update(HorPath,ResMan.GetRect("Handle"),ResMan.GetRect("Handle").CenterX(),rDisplay,batch.batch);
        RestrictHandle(batch.batch);
        return super.Draw(batch);
    }

    @Override
    public Boolean AnimOut(DeltaBatch batch) {
        rDisplay.StartAnimT(rFullscreen, Interpolator.Decelerate, 1000);
        rDisplay.StartAnimA(0f, Interpolator.Constant, 1000);
        return rDisplay.a() == 0f;
    }

    @Override
    public Boolean Destroy(DeltaBatch batch) {
        return super.Destroy(batch);
    }

    private int pos = 0;
    private void RestrictHandle(SpriteBatch batch) {
        HorPath = new Rect(rDisplay, 10 * fXunit, 30 * fYunit + (10 * fXunit), 90 * fXunit, 30 * fYunit);
        if (!ResMan.GetRect("Handle").IsTouched()) {
            if (touchingHandle) {
                ResMan.GetRect("Handle").StartAnimT(rReturn, Interpolator.Decelerate, 500);
            }
            touchingHandle = false;
            ChangeHandles = true;
        } else if (!touchingHandle) {
            touchingHandle = true;
            HandleX = ResMan.GetRect("Handle").TouchedX() - ResMan.GetRect("Handle").l();
            HandleY = ResMan.GetRect("Handle").TouchedY() - ResMan.GetRect("Handle").b();
        }
        if (touchingHandle && !StartingGame) {
            ResMan.GetRect("Handle").setb(rDisplay, ResMan.GetRect("Handle").TouchedY() - HandleY);
            ResMan.GetRect("Handle").sett(rDisplay, ResMan.GetRect("Handle").b() + 10 * fXunit);
            if (ResMan.GetRect("Handle").CenterY() > HorPath.b() && ResMan.GetRect("Handle").b() < HorPath.b()) {
                TouchLeft = ResMan.GetRect("Handle").TouchedX();
                ResMan.GetRect("Handle").setl(rDisplay, rReturn.l() + ((TouchLeft - HandleX - rReturn.l()) * (1 - ((HorPath.b() - ResMan.GetRect("Handle").b()) / (5 * fXunit)))));
                ResMan.GetRect("Handle").setr(rDisplay, ResMan.GetRect("Handle").l() + 10 * fXunit);
            }
            if (ResMan.GetRect("Handle").CenterY() < HorPath.b()) {
                ResMan.GetRect("Handle").setl(rDisplay, rReturn.l());
                ResMan.GetRect("Handle").setr(rDisplay, ResMan.GetRect("Handle").l() + 10 * fXunit);
            }
            if (ResMan.GetRect("Handle").b() < rReturn.b()) {
                ResMan.GetRect("Handle").setb(rReturn.b());
                ResMan.GetRect("Handle").sett(ResMan.GetRect("Handle").b() + 10 * fXunit);
            }
            if (ResMan.GetRect("Handle").b() > HorPath.b()) {
                TouchLeft = ResMan.GetRect("Handle").TouchedX() - HandleX;
                ResMan.GetRect("Handle").setl(rDisplay, (TouchLeft));
                ResMan.GetRect("Handle").setr(rDisplay, ResMan.GetRect("Handle").l() + 10 * fXunit);
                if (ResMan.GetRect("Handle").b() < rSlowDown.t() && ResMan.GetRect("Handle").b() > rSlowDown.b()) {
                    float fTop = ResMan.GetRect("Handle").TouchedY() - HandleY;
                    ResMan.GetRect("Handle").setb(rSlowDown.b());
                    ResMan.GetRect("Handle").sett(ResMan.GetRect("Handle").b() + 10 * fXunit);
                } else if (ResMan.GetRect("Handle").b() < rSpeedUP.t() && ResMan.GetRect("Handle").b() > rSlowDown.b()) {
                    float fTop = ResMan.GetRect("Handle").TouchedY() - HandleY;
                    ResMan.GetRect("Handle").setb(fTop - (rSpeedUP.height() * (float) (Math.cos(((fTop - rSpeedUP.b()) / rSpeedUP.height()) * (0.5 * Math.PI)))));

                    ResMan.GetRect("Handle").sett(ResMan.GetRect("Handle").b() + 10 * fXunit);
                }

            }
            if (ResMan.GetRect("Handle").b() > rSpeedUP.t()) {
                TouchLeft = ResMan.GetRect("Handle").TouchedX();
                if (ResMan.GetRect("Handle").b() - rSpeedUP.t() < 1 * fXunit) {
                    pos = MenOp.DeterminePos(HorPath, ResMan.GetRect("Handle").CenterX(), TouchLeft);
                    StartingLevel = pos;
                }
                ResMan.GetRect("Handle").setl(MenOp.SetLeft(HorPath, (((rSpeedUP.t() + (5 * fYunit)) > ResMan.GetRect("Handle").b())) ? 1 - (((rSpeedUP.t() + (5 * fYunit)) - ResMan.GetRect("Handle").b()) / (5 * fYunit)) : 1, HandleX, ResMan.GetRect("Handle").CenterX(), TouchLeft, pos));
                ResMan.GetRect("Handle").setr(rDisplay, ResMan.GetRect("Handle").l() + 10 * fXunit);
            }
            StartingGame = ResMan.GetRect("Handle").t() > ScreenY;
        } else if (touchingHandle) {
            float left = MenOp.SetLeft(HorPath, (((rSpeedUP.t() + (5 * fYunit)) > ResMan.GetRect("Handle").b())) ? 1 - (((rSpeedUP.t() + (5 * fYunit)) - ResMan.GetRect("Handle").b()) / (5 * fYunit)) : 1, HandleX, ResMan.GetRect("Handle").CenterX(), TouchLeft, pos);
            ResMan.GetRect("StartGame").Update(rDisplay,left,ScreenY- (30*fXunit),left + (10*fXunit),ScreenY - (40*fXunit));
            ResMan.GetRect("StartGame").DrawWithAlpha(ResMan.Get("Screen"), batch,1);
            if (ResMan.GetRect("StartGame").IsInside(ResMan.GetRect("Handle"))) {
                Switch("Game");
            }
            TouchLeft = ResMan.GetRect("Handle").TouchedX() - HandleX;
            ResMan.GetRect("Handle").setl(rDisplay, TouchLeft);
            ResMan.GetRect("Handle").setr(rDisplay, ResMan.GetRect("Handle").l() + 10 * fXunit);
            TouchTop = ResMan.GetRect("Handle").TouchedY() - HandleY;
            ResMan.GetRect("Handle").setb(rDisplay, (TouchTop));
            ResMan.GetRect("Handle").sett(rDisplay, ResMan.GetRect("Handle").b() + 10 * fXunit);

        } else {
            StartingGame = false;
        }
        if (StartingGame && ChangeHandles) {
            ChangeHandles = false;
            HandleX = ResMan.GetRect("Handle").TouchedX() - ResMan.GetRect("Handle").l();
            HandleY = ResMan.GetRect("Handle").TouchedY() - ResMan.GetRect("Handle").b();
        }
        ResMan.GetRect("Handle").Animate();
    }

}


