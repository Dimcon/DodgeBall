package com.Dimcon;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by daimonsewell on 7/11/14.
 */
public class MainScreen extends  Screen {
    private Texture imgDraw;

    public MainScreen() {
        Name = "Main";
    }
    @Override
    public Boolean Create() {
        imgDraw = new Texture("Screenshot.png");
        rDisplay.MoveLeft(ScreenX);
        rDisplay.StartAnimT(rFullscreen,Interpolator.Decelerate,5000);
        rDisplay.setAlpha(0f);
        rDisplay.StartAnimA(1f,Interpolator.Constant,1000);
        return super.Create();
    }

    @Override
    public Boolean AnimIn(DeltaBatch batch) {
        rDisplay.DrawWithAlpha(imgDraw,batch.batch,rDisplay.a());
        return super.AnimIn(batch);
    }

    @Override
    public Boolean Draw(DeltaBatch batch) {
        rDisplay.DrawWithAlpha(imgDraw,batch.batch,rDisplay.a());
        return super.Draw(batch);
    }

    @Override
    public Boolean AnimOut(DeltaBatch batch) {
        rDisplay.DrawWithAlpha(imgDraw,batch.batch,rDisplay.a());
        return super.AnimOut(batch);
    }

    @Override
    public Boolean Destroy() {
        return super.Destroy();
    }
}
