package com.Dimcon;

/**
 * Created by daimonsewell on 7/18/14.
 */
public class GameScreen extends Screen {
    public GameScreen() {
        Name = "Game";
    }

    @Override
    public void BeforeAll(DeltaBatch batch) {
        super.BeforeAll(batch);
    }

    @Override
    public void AfterAll(DeltaBatch batch) {
        super.AfterAll(batch);
        rDisplay.Draw(ResMan.Get("Screen"),batch.batch);
    }

    @Override
    public Boolean Create(DeltaBatch batch) {
        HandleX = ResMan.GetRect("Handle").TouchedX() - ResMan.GetRect("Handle").l();
        HandleY = ResMan.GetRect("Handle").TouchedY() - ResMan.GetRect("Handle").b();
        rDisplay.Moveup(100*fYunit);
        rDisplay.StartAnimT(rFullscreen,Interpolator.Accelerate,250);
        rDisplay.a = 0;
        OverlayScreen.TrackFinger = true;
        CreateAgain(true);
        return super.Create(batch);
    }

    @Override
    public Boolean AnimIn(DeltaBatch batch) {
        rDisplay.a = Math.min(rDisplay.a + 0.025f,1f);
        return rDisplay.a() == 1f;
    }

    boolean TouchingHandle = true;
    float HandleX = 0,TouchLeft,TouchBottom;
    float HandleY = 0;
    @Override
    public Boolean Draw(DeltaBatch batch) {
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
