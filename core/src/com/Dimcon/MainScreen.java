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
    public Boolean Create() {
        imgDraw = new Texture("Screenshot.png") ;
        BaseDrawable draw = new BaseDrawable();
        rDisplay.MoveLeft(ScreenX);
        rDisplay.StartAnimT(rFullscreen, Interpolator.Decelerate, 1000);
        rDisplay.setAlpha(0f);
        rDisplay.StartAnimA(1f, Interpolator.Constant, 1000);
        rButton = new Rect(rDisplay.l() + 10*fXunit,rDisplay.b() + 60*fYunit,rDisplay.l() + 90*fXunit,rDisplay.b() + 40*fYunit);
        font = new BitmapFont();
        skin = new Skin();
        skin.add("Draw",imgDraw);
        tbtn = new TextButton("Start",new TextButton.TextButtonStyle(skin.getDrawable("Draw"),
                skin.getDrawable("Draw"),
                skin.getDrawable("Draw"),font));
        stage = new Stage();
        stage.addActor(tbtn);
        Gdx.input.setInputProcessor(stage);
        tbtn.setPosition(rButton.l(),rButton.b());
        tbtn.setHeight(rButton.height());
        tbtn.setWidth(rButton.width());
        tbtn.addListener(new ClickListener() {
            @Override
            public void cancel() {
                super.cancel();
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                rButton.MoveLeft(5*fXunit);
            }

            @Override
            public boolean isOver(Actor actor, float x, float y) {
                return super.isOver(actor, x, y);
            }

            @Override
            public boolean isPressed() {
                return super.isPressed();
            }
        });
        DrawActors = true;
        return super.Create();
    }

    @Override
    public Boolean AnimIn(DeltaBatch batch) {
        rDisplay.DrawWithAlpha(imgDraw, batch.batch, rDisplay.a());
        rButton = new Rect(rDisplay.l() + 10*fXunit,rDisplay.b() + 60*fYunit,rDisplay.l() + 90*fXunit,rDisplay.b() + 40*fYunit);
        tbtn.setPosition(rButton.l(),rButton.b());
        tbtn.setHeight(rButton.height());
        tbtn.setWidth(rButton.width());
        return super.AnimIn(batch);
    }

    @Override
    public Boolean Draw(DeltaBatch batch) {
        rDisplay.DrawWithAlpha(imgDraw, batch.batch, rDisplay.a());
        /*if (Dodgeball.toucher.TouchMap.get(0) != null) {
            rDisplay = new Rect(Dodgeball.toucher.TouchMap.get(0).TouchPosX - (ScreenX / 2),
                    Dodgeball.toucher.TouchMap.get(0).TouchPosY + (ScreenY / 2),
                    Dodgeball.toucher.TouchMap.get(0).TouchPosX + (ScreenX / 2),
                    Dodgeball.toucher.TouchMap.get(0).TouchPosY - (ScreenY / 2));
        }*/
        //rButton = new Rect(rDisplay.l() + 10*fXunit,rDisplay.b() + 60*fYunit,rDisplay.l() + 90*fXunit,rDisplay.b() + 40*fYunit);
        tbtn.setPosition(rButton.l(),rButton.b());
        tbtn.setHeight(rButton.height());
        tbtn.setWidth(rButton.width());
        return super.Draw(batch);
    }

    @Override
    public Boolean AnimOut(DeltaBatch batch) {
        rDisplay.DrawWithAlpha(imgDraw, batch.batch, rDisplay.a());
        rButton = new Rect(rDisplay.l() + 10*fXunit,rDisplay.b() + 60*fYunit,rDisplay.l() + 90*fXunit,rDisplay.b() + 40*fYunit);
        rButton.DrawWithAlpha(imgDraw,batch.batch,(Dodgeball.toucher.isTouchingRect(rButton) )? 0f: 0.5f);
        return super.AnimOut(batch);
    }

    @Override
    public Boolean Destroy() {
        return super.Destroy();
    }
}
