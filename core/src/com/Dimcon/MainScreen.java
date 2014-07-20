package com.Dimcon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

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
    public void AfterAll(DeltaBatch batch) {
        super.AfterAll(batch);

    }

    boolean touchingHandle = false;
    float HandleX = 0,HandleY = 0,TouchLeft,TouchTop;
    Rect rReturn = new Rect();
    Rect HorPath = new Rect();

    @Override
    public Boolean Create(DeltaBatch batch) {
        rDisplay.MoveLeft(ScreenX);
        rDisplay.StartAnimT(rFullscreen, Interpolator.Decelerate, 1000);
        rDisplay.setAlpha(0f);
        rDisplay.StartAnimA(1f, Interpolator.Constant, 1000);
        ResMan.AddImage("Screen","badlogic.jpg");
        rReturn.RectCopy(Dodgeball.overlay.rHandle);
        HorPath = new Rect(rDisplay,10*fXunit,45*fYunit,90*fXunit,35*fYunit);
        return super.Create(batch);
    }

    @Override
    public Boolean AnimIn(DeltaBatch batch) {
        rDisplay.DrawWithAlpha(ResMan.Get("Screen"), batch.batch, rDisplay.a());
        HorPath = new Rect(rDisplay,10*fXunit,55*fYunit,90*fXunit,44*fYunit);
        HorPath.Draw(ResMan.Get("Handle"),batch.batch);
        if (!Dodgeball.overlay.rHandle.IsTouched()) {
            touchingHandle = false;
        } else if (!touchingHandle) {
            HandleX = ResMan.GetRect("Handle").TouchedX() - ResMan.GetRect("Player").l();
            HandleY = ResMan.GetRect("Handle").TouchedY() - ResMan.GetRect("Player").b();
        }
        if (Dodgeball.overlay.rHandle.b() > 20*fYunit) {
            ResMan.GetRect("Handle").setb(rDisplay, ResMan.GetRect("Handle").TouchedY() - HandleY);
            ResMan.GetRect("Handle").sett(rDisplay, ResMan.GetRect("Handle").b() + 10 * fXunit);
        }
        return super.AnimIn(batch);
    }

    @Override
    public Boolean Draw(DeltaBatch batch) {
        HorPath = new Rect(rDisplay,10*fXunit,55*fYunit,90*fXunit,44*fYunit);
        HorPath.Draw(ResMan.Get("Handle"),batch.batch);
        if (!ResMan.GetRect("Handle").IsTouched()) {
            if (touchingHandle) {
                ResMan.GetRect("Handle").StartAnimT(rReturn, Interpolator.Decelerate, 500);
            }
            touchingHandle = false;
        } else if (!touchingHandle) {
            touchingHandle = true;
            HandleX = ResMan.GetRect("Handle").TouchedX() - ResMan.GetRect("Handle").l();
            HandleY = ResMan.GetRect("Handle").TouchedY() - ResMan.GetRect("Handle").b();
        }
        if (ResMan.GetRect("Handle").IsTouched()) {
            ResMan.GetRect("Handle").setb(rDisplay, ResMan.GetRect("Handle").TouchedY() - HandleY);
            ResMan.GetRect("Handle").sett(rDisplay, ResMan.GetRect("Handle").b() + 10 * fXunit);
        }
        if (ResMan.GetRect("Handle").b() < rReturn.b() && touchingHandle) {
            ResMan.GetRect("Handle").setb(rReturn.b());
            ResMan.GetRect("Handle").sett(ResMan.GetRect("Handle").b() + 10 * fXunit);
        }
        if (ResMan.GetRect("Handle").t() > HorPath.b() && ResMan.GetRect("Handle").IsTouched()
                && ResMan.GetRect("Handle").b() < HorPath.b() && ResMan.GetRect("Handle").IsTouched()) {
            TouchLeft = ResMan.GetRect("Handle").TouchedX();
            ResMan.GetRect("Handle").setl(rDisplay, rReturn.l()  + ((TouchLeft - HandleX - rReturn.l()) * (1 - ((HorPath.b() -  ResMan.GetRect("Handle").b())/(10*fXunit)))));
            ResMan.GetRect("Handle").setr(rDisplay, ResMan.GetRect("Handle").l() + 10 * fXunit);
        }
        if (ResMan.GetRect("Handle").b() > HorPath.b()) {
            TouchLeft = ResMan.GetRect("Handle").TouchedX() - HandleX;
            ResMan.GetRect("Handle").setl(rDisplay, (TouchLeft));
            ResMan.GetRect("Handle").setr(rDisplay, ResMan.GetRect("Handle").l() + 10 * fXunit);
        }
        if (ResMan.GetRect("Handle").t() < HorPath.b()) {
            ResMan.GetRect("Handle").setl(rDisplay, rReturn.l());
            ResMan.GetRect("Handle").setr(rDisplay, ResMan.GetRect("Handle").l() + 10 * fXunit);
        }
        rDisplay.DrawWithAlpha(ResMan.Get("Screen"), batch.batch, rDisplay.a());
        ResMan.GetRect("Handle").Animate();
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
}
