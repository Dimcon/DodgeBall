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
    private Boolean RelativeToBottom = true,
                    RelativeToLeft = true;

    //Feature to allow simple rectangle animations.
    //Positioning is handled by rect but updating must be done by parent class
    // in a consistent loop.
    boolean animAlpha = false,
            animScale = false,
            animTranslate = false
                    ;
    Rect rAnimTarget;

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
        if (RelativeToLeft) {
            return r - l;
        } else {
            return l - r;
        }
    }
    //Get height of Rect
    public float height() {
        if (RelativeToBottom) {
            return t - b;
        } else {
            return b - t;
        }
    }
    //Move all points to negative.
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
        float w = ((rP.width()/2) - rPadding);
        float h = ((rP.height()/2) - rPadding);
        if (RelativeToBottom) {
            if (rP.height() >= rP.width()) {
                t = rP.CenterY() + w;
                b = rP.CenterY() - w;
            } else {
                t = rP.CenterY() + h;
                b = rP.CenterY() - h;
            }
        } else {
            if (rP.height() >= rP.width()) {
                t = rP.CenterY() - w;
                b = rP.CenterY() + w;
            } else {
                t = rP.CenterY() - h;
                b = rP.CenterY() + h;
            }
        }
        if (RelativeToLeft) {
            if (rP.height() >= rP.width()) {
                l = rP.CenterX() - w;
                r = rP.CenterX() + w;
            } else {
                l = rP.CenterX() - h;
                r = rP.CenterX() + h;
            }
        } else {
            if (rP.height() >= rP.width()) {
                l = rP.CenterX() + w;
                r = rP.CenterX() - w;
            } else {
                l = rP.CenterX() + h;
                r = rP.CenterX() - h;
            }
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
        if (RelativeToLeft) {
            r = r - fAmount;
            l = l - fAmount;
        } else {
            r = r + fAmount;
            l = l + fAmount;
        }
    }
    public void MoveDown(float fAmount) {
        if (RelativeToBottom) {
            t = t - fAmount;
            b = b - fAmount;
        } else {
            t = t + fAmount;
            b = b + fAmount;
        }
    }
    public void MoveRight(float fAmount) {
        if (RelativeToLeft) {
            r = r + fAmount;
            l = l + fAmount;
        } else {
            r = r - fAmount;
            l = l - fAmount;
        }
    }
    public void Moveup(float fAmount) {
        if (RelativeToBottom) {
            t = t - fAmount;
            b = b - fAmount;
        } else {
            t = t + fAmount;
            b = b + fAmount;
        }
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
        //Draw using LIBGDX Spritebatch. LibGDX uses the bottom left of
        //the screen as the reference.
        sBtch.draw(tx, l, b, width(), height());
    }
    public void DrawWithAlpha(Texture tx,SpriteBatch sBtch, float fAlpha) {
        sBtch.setColor(1,1,1,fAlpha);
        Draw(tx,sBtch);
    }

}
