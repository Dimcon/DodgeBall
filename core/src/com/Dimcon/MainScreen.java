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
    float HandleX = 0,HandleY = 0;
    Rect rReturn = new Rect();
    @Override
    public Boolean Create(DeltaBatch batch) {
        rDisplay.MoveLeft(ScreenX);
        rDisplay.StartAnimT(rFullscreen, Interpolator.Decelerate, 1000);
        rDisplay.setAlpha(0f);
        rDisplay.StartAnimA(1f, Interpolator.Constant, 1000);
        ResourceMan.AddImage("Screen","badlogic.jpg");
        rReturn.RectCopy(Dodgeball.overlay.rHandle);
        return super.Create(batch);
    }

    @Override
    public Boolean AnimIn(DeltaBatch batch) {
        rDisplay.DrawWithAlpha(ResourceMan.Get("Screen"), batch.batch, rDisplay.a());
        if (!Dodgeball.overlay.rHandle.IsTouched()) {
            touchingHandle = false;
        } else if (!touchingHandle) {
            HandleX = Dodgeball.overlay.rHandle.TouchedX() - Dodgeball.overlay.rPlayer.l();
            HandleY = Dodgeball.overlay.rHandle.TouchedY() - Dodgeball.overlay.rPlayer.b();
        }
        if (Dodgeball.overlay.rHandle.b() > 20*fYunit) {
            Dodgeball.overlay.rHandle.setb(rDisplay, Dodgeball.overlay.rHandle.TouchedY() - HandleY);
            Dodgeball.overlay.rHandle.sett(rDisplay, Dodgeball.overlay.rHandle.b() + 10 * fXunit);
        }
        return super.AnimIn(batch);
    }

    @Override
    public Boolean Draw(DeltaBatch batch) {
        if (!Dodgeball.overlay.rHandle.IsTouched()) {
            if (touchingHandle) {
                Dodgeball.overlay.rHandle.StartAnimT(rReturn,Interpolator.Decelerate,500);
            }
            touchingHandle = false;
        } else if (!touchingHandle) {
            touchingHandle = true;
            HandleX = Dodgeball.overlay.rHandle.TouchedX() - Dodgeball.overlay.rHandle.l();
            HandleY = Dodgeball.overlay.rHandle.TouchedY() - Dodgeball.overlay.rHandle.b();
        }
        if (Dodgeball.overlay.rHandle.IsTouched()) {
            Dodgeball.overlay.rHandle.setb(rDisplay, Dodgeball.overlay.rHandle.TouchedY() - HandleY);
            Dodgeball.overlay.rHandle.sett(rDisplay, Dodgeball.overlay.rHandle.b() + 10 * fXunit);
        }
        if (Dodgeball.overlay.rHandle.b() < rReturn.b() && touchingHandle) {
            Dodgeball.overlay.rHandle.setb(rReturn.b());
            Dodgeball.overlay.rHandle.sett(Dodgeball.overlay.rHandle.b() + 10 * fXunit);
        }
        rDisplay.DrawWithAlpha(ResourceMan.Get("Screen"), batch.batch, rDisplay.a());
        Dodgeball.overlay.rHandle.Animate();
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
