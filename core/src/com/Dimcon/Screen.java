package com.Dimcon;

/**
 * Created by daimonsewell on 7/7/14.
 */
public class Screen {

    Rect    rDisplay = new Rect();

    Boolean Display = false,
            Paused = false;

    float   fXunit = 0,
            fYunit = 0,
            fAlpha = 0;


    public void Create() {
        Display = true;
    }
    public void AnimIn() {

    }
    public void Draw() {

    }
    public void AnimOut() {

    }
    public void Destroy() {
        Display = false;
    }
    public void SwitchTo() {

    }
}
