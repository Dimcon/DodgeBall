package com.Dimcon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by daimonsewell on 7/7/14.0
 */
public class Screen {
    /**
     * Screen class. The events will be called by ScreenHandler in the order:
     * Create, AnimIn, Draw, AnimOut and Destroy.
     * ScreenHandler will loop the current procedure until it returns True.
     * fXUnit and fYUnit are a percentage of the width and height of rDisplay
     * and should be used with reference to rDisplay in order to achieve
     * screen manipulation such as proper scaling and position.*/

    Rect    rDisplay = new Rect(0, Gdx.graphics.getHeight(),Gdx.graphics.getWidth(),0),
            rFullscreen = new Rect(0, Gdx.graphics.getHeight(),Gdx.graphics.getWidth(),0);
    Boolean Display = false,
            Paused = false,
            Active = false;
    String Name = null;
    float   fXunit = rDisplay.width()/100f,
            fYunit = rDisplay.height()/100f,
            fAlpha = 0,
            ScreenY = Gdx.graphics.getHeight(),
            ScreenX = Gdx.graphics.getWidth();
    public Stage stage = Stage.Deactivated;

    public void ResetUnits() {
        /* Run every frame regardless of whether the screen is drawn or not. */
        fXunit = rDisplay.width()/100f;
        fYunit = rDisplay.height()/100f;
        rDisplay.Animate();
    }
    public void Switch(String key,ScreenManager screenManager) {
        stage = Stage.AnimateOut;
        screenManager.ScreenStore.get(key).stage = Stage.Create;
    }
    public Boolean Create() {
        Display = true;
        Paused = false;
        return true;
    }
    public Boolean AnimIn(DeltaBatch batch) {
        fAlpha = Math.min(fAlpha + 0.01f, 1f);
        return (fAlpha == 1f);
    }
    public Boolean Draw(DeltaBatch batch) {
        return false;
    }
    public Boolean AnimOut(DeltaBatch batch) {
        fAlpha = Math.max(fAlpha - 0.01f, 0f);
        return (fAlpha == 0f);
    }
    public Boolean Destroy() {
        Display = false;
        Paused = true;
        return true;
    }
}
enum Stage {
    Deactivated, Create, AnimateIn, Draw, AnimateOut, Destroy;
}
