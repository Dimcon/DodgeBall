package com.Dimcon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by daimonsewell on 6/6/14.
 *
 * Rect Class
 *
 * Origin is bottom left
 */
public class Rect {
    protected float
                //top,bottom,left,right
                t,b,l,r;
    //Basic Rectangle
    //Simplifies a lot of code.

    //Get center of Rect (horizontal)
    public float CenterX() {
        return (l + r)/2;
    }
    //Get center of Rect (Vertical)
    public float CenterY() {
        return (t + b)/2;
    }
    //Get width of Rect
    public float width() {
        return r - l;
    }
    //Get height of Rect
    public float height() {
        return t - b;
    }
    //Move Rect to upper left, all points off screen.
    public void OffScreen() {
        l = - 10;
        r = - 10;
        t = - 10;
        b = -10;
    }
    //Turn into square as large as possible within rectangle(
    // ##############_________################
    // # Rectangle  | New     |              #
    // #            | Square  |              #
    // #            |         |              #
    // #            |         |              #
    // ##############---------################
    public void CopySquare(Rect rP,float rPadding) {
        if (rP.height() >= rP.width()) {
            l = rP.CenterX() - ((rP.width()/2) - rPadding);
            r = rP.CenterX() + ((rP.width()/2) - rPadding);
            t = rP.CenterY() + ((rP.width()/2) - rPadding);
            b = rP.CenterY() - ((rP.width()/2) - rPadding);
        } else {
            l = rP.CenterX() - ((rP.height()/2) - rPadding);
            r = rP.CenterX() + ((rP.height()/2) - rPadding);
            t = rP.CenterY() + ((rP.height()/2) - rPadding);
            b = rP.CenterY() - ((rP.height()/2) - rPadding);
        }
    }
    //Define rectangle in one line.
    public void equals(float leftP, float topP, float rightP, float bottomP) {
        l = leftP;
        r = rightP;
        t = topP;
        b = bottomP;
    }
    public void MoveLeft(float fAmount) {
        l = l - fAmount;
        r = r - fAmount;
    }
    public void MoveDown(float fAmount) {
        t = t - fAmount;
        b = b - fAmount;
    }
    //Copy numerical values of given rect
    // (Saying Rect1 = Rect2 seems to make Rect1 point to Rect2 instead of
    //  just copying it)
    public void RectCopy(Rect rP) {
        l = rP.l;
        r = rP.r;
        t = rP.t;
        b = rP.b;
    }

    public void Draw(Texture tx, SpriteBatch sBtch) {
        sBtch.draw(tx, l, b, width(), height());
    }
}
