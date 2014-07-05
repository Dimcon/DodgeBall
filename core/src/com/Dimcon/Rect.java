package com.Dimcon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by daimonsewell on 6/6/14.
 *
 * Rect Class
 *
 * Origin is bottom left (Booleans Interchangable)
 */
public class Rect {
    /** top,bottom,left,right  3 21
     * Post animation position
     * Distance to target animation position    */
     protected float
                t,b,l,r,
                pt, pb, pl, pr,
                dt,db,dl,dr;
    /** Basic Rectangle */
    private Boolean RelativeToBottom = true,
                    RelativeToLeft = true;
    /**
      * Feature to allow simple rectangle animations.
      * Positioning is handled by rect but updating must be done by parent class
      * in a consistent loop.
    */
    boolean animAlpha = false,
            animScale = false,
            animTranslate = false;
    Rect rAnimTarget;

    public float CenterX() {
    /** Get center of Rect (horizontal) */
            return (l + r)/2;
    }
    public float CenterY() {
    /** Get center of Rect (Vertical)   */
        return (t + b)/2;
    }
    public float width() {
    /** Get width of Rect   */
        if (RelativeToLeft) {
            return r - l;
        } else {
            return l - r;
        }
    }
    public float height() {
    /** Get height of Rect */
        if (RelativeToBottom) {
            return t - b;
        } else {
            return b - t;
        }
    }
    public void OffScreen() {
    /** Move all points to negative. */
        l = - 10;
        r = - 10;
        t = - 10;
        b = -10;
    }
    public void equals(float leftP, float topP, float rightP, float bottomP) {
        /** Define rectangle using a single procedure.  */
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
    public void RectCopy(Rect rP) {
        /** Copy numerical values of given rect.
         * Saying Rect1 = Rect2 seems to make Rect1 point to Rect2 instead of
         * just copying it.    */
        l = rP.l;
        r = rP.r;
        t = rP.t;
        b = rP.b;
    }
    public void CopySquare(Rect rP,float rPadding) {
        /** Turn into square as large as possible within rectangle(
         *  - - - - - - -_-_-_-_-_- - - - - - - -
         * | Rectangle  | New     |              |
         * |            | Square  |              |
         * |            |         |              |
         * |            |         |              |
         *  _ _ _ _ _ _ _-_-_-_-_-_ _ _ _ _ _ _ _
         */
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
    public void Draw(Texture tx, SpriteBatch sBtch) {
        /** Draw using LIBGDX Spritebatch. LibGDX uses the bottom left of
         * the screen as the reference  */
        sBtch.draw(tx, l, b, width(), height());
    }
    public void DrawWithAlpha(Texture tx,SpriteBatch sBtch, float fAlpha) {
        Color newcol = sBtch.getColor();
        sBtch.setColor(1,1,1,fAlpha);
        Draw(tx,sBtch);
        sBtch.setColor(newcol);
    }

}
