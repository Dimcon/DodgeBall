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
    private Texture imgDraw;
    private Rect rButton, rTRack;
    public MainScreen() {
        Name = "Main";
    }
    private TextButton tbtn;
    private BitmapFont font;
    public Skin skin;
    public com.badlogic.gdx.scenes.scene2d.Stage stage;
    boolean DrawActors = false;

    @Override
    public void BeforeAll(DeltaBatch batch) {
        super.BeforeAll(batch);
        //UpdateButton("sTest", rButton);
    }

    @Override
    public void AfterAll(DeltaBatch batch) {
        rButton.Draw(ReseourceMan.Get("Norm"),batch.batch);
        super.AfterAll(batch);

    }

    @Override
    public Boolean Create(DeltaBatch batch) {
        imgDraw = new Texture("Screenshot.png");
        rDisplay.MoveLeft(ScreenX);
        rDisplay.StartAnimT(rFullscreen, Interpolator.Decelerate, 1000);
        rDisplay.setAlpha(0f);
        rDisplay.StartAnimA(1f, Interpolator.Constant, 1000);
        rButton = new Rect(rDisplay,20*fXunit,40*fYunit,50*fXunit,20*fYunit);
        ReseourceMan.AddImage("Norm","Screenshot.png");
        /*AddButton("sTest", null, "Start", rButton, "Norm", "Norm", "Norm", new ClickListener() {

            *//*@Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rButton.setl(rDisplay, x - (20 * fXunit));
                rButton.setr(rDisplay, x + (20 * fXunit));
                rButton.sett(rDisplay, y + (20 * fXunit));
                rButton.setb(rDisplay, y - (20 * fXunit));
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                rButton.setl(rDisplay, x - (20 * fXunit));
                rButton.setr(rDisplay, x + (20 * fXunit));
                rButton.sett(rDisplay, y + (20 * fXunit));
                rButton.setb(rDisplay, y - (20 * fXunit));
                super.touchDragged(event, x, y, pointer);}*//*


            @Override
            public boolean isPressed() {
                return super.isPressed();
            }
        });*/
        return super.Create(batch);
    }

    @Override
    public Boolean AnimIn(DeltaBatch batch) {
        rDisplay.DrawWithAlpha(imgDraw, batch.batch, rDisplay.a());
        rButton = new Rect(rDisplay, 10*fXunit,60*fYunit,90*fXunit,40*fYunit);
        return super.AnimIn(batch);
    }

    @Override
    public Boolean Draw(DeltaBatch batch) {
        rDisplay.DrawWithAlpha(imgDraw, batch.batch, rDisplay.a());
        if (rButton.IsTouched()) {
            rButton.setl(rDisplay, rButton.TouchedX()- (20*fXunit));
            rButton.setr(rDisplay, rButton.TouchedX() + (20 * fXunit));
            rButton.sett(rDisplay, rButton.TouchedY() + (20 * fXunit));
            rButton.setb(rDisplay, rButton.TouchedY() - (20 * fXunit));
        }
        return super.Draw(batch);
    }

    @Override
    public Boolean AnimOut(DeltaBatch batch) {
        rDisplay.DrawWithAlpha(imgDraw, batch.batch, rDisplay.a());
        rButton = new Rect(rDisplay, 10*fXunit,60*fYunit,90*fXunit,40*fYunit);
        rButton.DrawWithAlpha(imgDraw,batch.batch,(batch.toucher.isTouchingRect(rButton) != -1)? 0f: 0.5f);
        return super.AnimOut(batch);
    }

    @Override
    public Boolean Destroy(DeltaBatch batch) {
        return super.Destroy(batch);
    }
}
