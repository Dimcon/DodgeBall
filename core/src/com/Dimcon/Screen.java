package com.Dimcon;

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

     Rect    rDisplay = new Rect();

    Boolean Display = false,
            Paused = false,
            Active = false;

    String Name = " ";

    float   fXunit = 0,
            fYunit = 0,
            fAlpha = 0;

    public void ResetUnits() {
        fXunit = rDisplay.width()/100f;
        fYunit = rDisplay.height()/100f;
    }

    public Boolean Create() {
        Display = true;
        Paused = false;
        return true;
    }
    public Boolean AnimIn(SpriteBatch batch) {
        fAlpha = Math.min(fAlpha + 0.1f, 1f);
        return (fAlpha == 1f);
    }
    public Boolean Draw(SpriteBatch batch) {

        return false;
    }
    public Boolean AnimOut(SpriteBatch batch) {
        fAlpha = Math.max(fAlpha - 0.1f, 0f);
        return (fAlpha == 0f);
    }
    public Boolean Destroy() {
        Display = false;
        Paused = true;
        return true;
    }
}
