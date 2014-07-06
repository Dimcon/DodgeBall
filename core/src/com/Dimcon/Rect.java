package com.Dimcon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Timer;

/**
 * Created by daimonsewell on 6/6/14.
 *
 * Rect Class
 *
 * Origin is bottom left (Booleans Interchangable)
 */
public class Rect {
    /** Basic Rectangle */
    /* top,bottom,left,right */
     protected float
                t,b,l,r,a = 1f;
    private Boolean RelativeToBottom = true,
                    RelativeToLeft = true;
    public Rect(float leftP, float topP, float rightP, float bottomP) {
        /** Define rectangle using a single procedure.  */
        l = leftP;
        r = rightP;
        t = topP;
        b = bottomP;
    }
    public Rect() {
    }
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

        DrawWithAlpha(tx,sBtch,a);
    }
    public void DrawWithAlpha(Texture tx,SpriteBatch sBtch, float fAlpha) {
    /** Draw using LIBGDX Spritebatch. LibGDX uses the bottom left of
     * the screen as the reference  */
        Color newcol = sBtch.getColor();
        sBtch.setColor(1,1,1,fAlpha);
        sBtch.draw(tx, l, b, width(), height());
        sBtch.setColor(newcol);
    }

    /** Simple rectangle animations.
      * Positioning is handled by rect but updating must be done by parent class
      * in a consistent loop.
      */
    protected float pt, pb, pl, pr, /* Post animation position               */
                    dt,db,dl,dr,    /* Distance to target animation position */
                    preA, distA,     /* Post animation and distance till  Alpha */

                    AnimTime = 0,
                    AnimRate = 0,
                    AlphaAnimTime = 0,
                    AlphaAnimRate = 0,
                    TimePerCycle = 16;
    boolean animAlpha = false,
            animTranslate = false;

    public final int
            IAccel = 1,  /* Animation Interpolators  */
            IDecel = 2,
            IConst = 3;
    public int Interpolator, IAlphInterp = IConst;

    public void StartAnimT(Rect rDist,int InterpolatorP,float Timemillis) {
        SetPostAnim();
        Interpolator = InterpolatorP;
        AnimRate = (float)(0.5f * Math.PI) / (Timemillis/TimePerCycle);
        AnimTime = 0;
        animTranslate = true;
        dt = rDist.t;
        db = rDist.b;
        dl = rDist.l;
        dr = rDist.r;
    }
    public void StartAnimA(float TargetAlpha,int InterpolatorP,float Timemillis) {
        preA = a;
        IAlphInterp = InterpolatorP;
        AlphaAnimRate = (float)(0.5f * Math.PI) / (Timemillis/TimePerCycle);
        AlphaAnimTime = 0;
        animAlpha = true;
        distA = TargetAlpha - a;
    }

    public void Animate() {
            if (animTranslate) {
                float fMult = 0;
                switch (Interpolator) {
                    case 1:
                        fMult = (float) (1 - Math.cos(AnimTime));    /*  Accelerate  */
                        break;
                    case 2:
                        fMult = (float) Math.sin(AnimTime);          /*  Decelerate */
                        break;
                    case 3:
                        fMult = (float) (AnimTime / (0.5f * Math.PI));     /* Constant */
                        break;
                }
                AnimTime += AnimRate;
                if (AnimTime > 0.5f * Math.PI) {
                    animTranslate = false;
                    AnimTime = 0;
                }
                t = pt + (dt * fMult);
                b = pb + (db * fMult);
                l = pl + (dl * fMult);
                r = pr + (dr * fMult);
            }
            if (animAlpha) {
                float fMult2 = 0;
                switch (Interpolator) {
                    case 1:
                        fMult2 = (float) (1 - Math.cos(AlphaAnimTime));    /*  Accelerate  */
                        break;
                    case 2:
                        fMult2 = (float) Math.sin(AlphaAnimTime);          /*  Decelerate */
                        break;
                    case 3:
                        fMult2 = (float) (AlphaAnimTime / (0.5f * Math.PI));     /* Constant */
                        break;
                }
                AlphaAnimTime += AlphaAnimRate;
                if (AlphaAnimTime > 0.5f * Math.PI) {
                    animAlpha = false;
                    AlphaAnimTime = 0;
                }
                a = preA + (distA * fMult2);
            }
    }

    private void SetPostAnim() {
        pt = t;
        pb = b;
        pl = l;
        pr = r;
    }
}

