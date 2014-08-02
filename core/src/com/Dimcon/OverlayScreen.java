package com.Dimcon;

/**
 * Created by daimonsewell on 7/18/14.
 */
public class OverlayScreen extends Screen {
    public OverlayScreen() {
        Name = "Overlay";
    }
    public static boolean DrawPlayer = false;

    public Rect rPlayer, rHandle;
    @Override
    public void BeforeAll(DeltaBatch batch) {
        super.BeforeAll(batch);
    }

    @Override
    public void AfterAll(DeltaBatch batch) {
        super.AfterAll(batch);
        ResMan.GetRect("Player").Animate();
    }

    @Override
    public Boolean Create(DeltaBatch batch) {
        rPlayer = new Rect(rDisplay, 45*fXunit,(5*fYunit) + (10*fXunit),55*fXunit,(5*fYunit));
        rHandle = new Rect();
        rHandle.RectCopy(rPlayer);
        ResMan.AddRect("Player",rPlayer);
        ResMan.GetRect("Player").a = 0f;
        ResMan.AddRect("Handle",rHandle);
        ResMan.AddImage("Player","badlogic.jpg");
        ResMan.AddImage("Handle","badlogic.jpg");
        return super.Create(batch);
    }

    @Override
    public Boolean AnimIn(DeltaBatch batch) {
        ResMan.GetRect("Player").Update(rDisplay, 45*fXunit,(5*fYunit) + (10*fXunit),55*fXunit,(5*fYunit));
        ResMan.GetRect("Handle").RectCopy(rPlayer);
        ResMan.GetRect("Player").Draw(ResMan.Get("Player"),batch.batch,rDisplay.a);
        ResMan.GetRect("Handle").Draw(ResMan.Get("Handle"),batch.batch,rDisplay.a);
        return super.AnimIn(batch);
    }

    boolean TouchingHandle = true;
    public static boolean TrackFinger = false;
    float HandleX = 0,TouchLeft,TouchBottom;
    float HandleY = 0;
    @Override
    public Boolean Draw(DeltaBatch batch) {
        if (true) {
            ResMan.GetRect("Player").Update(rDisplay,
                    ResMan.GetRect("Handle").CenterX() - (2.5f * fXunit),
                    ResMan.GetRect("Handle").CenterY() + (22.5f * fXunit),
                    ResMan.GetRect("Handle").CenterX() + (2.5f * fXunit),
                    ResMan.GetRect("Handle").CenterY() + (17.5f * fXunit));
            ResMan.GetRect("Player").Draw(ResMan.Get("Player"), batch.batch);
        }
        ResMan.GetRect("Handle").Draw(ResMan.Get("Handle"),batch.batch,rDisplay.a);
        if (TrackFinger) {
            if (!ResMan.GetRect("Handle").IsTouched()) {
                Paused = true;
                if (TouchingHandle)  ResMan.GetRect("Player").StartAnimA(0,Interpolator.Constant,1000);
                TouchingHandle = false;
                ScreenManager.StartScreen("Main");
                ScreenManager.EndScreen("Game");
                TrackFinger = false;
            } else {
                if (!TouchingHandle) {
                    HandleX = ResMan.GetRect("Handle").TouchedX() - ResMan.GetRect("Handle").l();
                    HandleY = ResMan.GetRect("Handle").TouchedY() - ResMan.GetRect("Handle").b();
                }
                TouchingHandle = true;
                TouchBottom = ResMan.GetRect("Handle").TouchedY() - HandleY;
                TouchLeft = ResMan.GetRect("Handle").TouchedX() - HandleX;
                ResMan.GetRect("Handle").setl(rDisplay, TouchLeft);
                ResMan.GetRect("Handle").setb(rDisplay, TouchBottom);
                ResMan.GetRect("Handle").sett(rDisplay, ResMan.GetRect("Handle").b() + (10 * fXunit));
                ResMan.GetRect("Handle").setr(rDisplay, ResMan.GetRect("Handle").l() + (10 * fXunit));
            }
        }
        return super.Draw(batch);
    }

    @Override
    public Boolean AnimOut(DeltaBatch batch) {
        return super.AnimOut(batch);
    }

    @Override
    public Boolean Destroy(DeltaBatch batch) {
        return super.Destroy(batch);
    }
}
