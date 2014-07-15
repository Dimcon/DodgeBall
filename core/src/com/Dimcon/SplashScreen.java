package com.Dimcon;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by daimonsewell on 7/10/14.
 */
public class SplashScreen extends Screen {
    private Rect rTest = new Rect();
    private Texture img;

    public SplashScreen() {
        Name = "Splash";
    }

    @Override
    public Boolean Create(DeltaBatch batch) {
        super.Create(batch);
        img = new Texture("badlogic.jpg");
        rDisplay = new Rect(ScreenX/2,ScreenY/2,ScreenX/2,ScreenY/2);
        rDisplay.StartAnimT(rFullscreen,Interpolator.Constant,1000);
        rTest.RectCopy(rDisplay);
        rDisplay.setAlpha(0f);
        rDisplay.StartAnimA(1f,Interpolator.Constant,500);
        return true;
    }

    @Override
    public Boolean AnimIn(DeltaBatch batch) {
        rTest.DrawWithAlpha(img, batch.batch, rDisplay.a());
        rTest.RectCopy(rDisplay);
        return rDisplay.l() == 0;
    }

    @Override
    public Boolean Draw(DeltaBatch batch) {
        rTest.DrawWithAlpha(img, batch.batch, rDisplay.a());
        rTest.RectCopy(rDisplay);
        Switch("Main",Dodgeball.Scrnman);
        rDisplay.StartAnimT(new Rect(ScreenX,ScreenY,ScreenX * 2,0),Interpolator.Decelerate,1000);
        return false;
    }

    @Override
    public Boolean AnimOut(DeltaBatch batch) {
        fAlpha = Math.max(fAlpha - 0.01f,0f);
        rTest.DrawWithAlpha(img, batch.batch, rDisplay.a());
        rTest.RectCopy(rDisplay);
        return (rDisplay.a() == 0f);
    }

    @Override
    public Boolean Destroy(DeltaBatch batch) {
        return super.Destroy(batch);
    }
}
