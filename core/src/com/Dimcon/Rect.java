package com.Dimcon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.Timer;

/**
 * Created by daimonsewell on 6/6/14.
 *
 * Rect Class
 *
 * Origin is bottom left (Booleans Interchangable)
 */
public class Rect {
    /** Rectangle with basic animation support
     *  Has a higher memory footprint than RectS (Very basic Rect)*/
    /* top,bottom,left,right */
     private float
                t,b,l,r,a = 1f;
    private Boolean RelativeToBottom = true,
                    RelativeToLeft = true,
                    Changed = true,
                    Debugg = false;
    public Rect(float leftP, float topP, float rightP, float bottomP) {
        /* Define rectangle using a single procedure.  */
        l = leftP;
        r = rightP;
        t = topP;
        b = bottomP;
    }
    public Rect(TouchHandler th,float leftP, float topP, float rightP, float bottomP) {
        /* Define rectangle using a single procedure.  */
        l = leftP;
        r = rightP;
        t = topP;
        b = bottomP;
        th.AddRect(this);
        Touchable = true;
    }
    public Rect() {
    }

    public float l() {
        return l;
    }
    public float r() {
        return r;
    }
    public float t() {
        return t;
    }
    public float b() {
        return b;
    }
    public float a() {
        return a;
    }
    public void setl(float left) {
        l = left;
        Changed = true;
    }
    public void setr(float right) {
        r = right;
        Changed = true;
    }
    public void setb(float bottom) {
        b = bottom;
        Changed = true;
    }
    public void sett(float top) {
        t = top;
        Changed = true;
    }
    public void setAlpha(float Alpha) {
        a = Alpha;
    }

    public float CenterX() {
    /* Get center of Rect (horizontal) */
            return (l + r)/2;
    }
    public float CenterY() {
    /* Get center of Rect (Vertical)   */
        return (t + b)/2;
    }
    public float width() {
    /* Get width of Rect   */
        if (RelativeToLeft) {
            return r - l;
        } else {
            return l - r;
        }
    }
    public float height() {
    /* Get height of Rect */
        if (RelativeToBottom) {
            return t - b;
        } else {
            return b - t;
        }
    }
    public void OffScreen() {
    /* Move all points to negative. */
        l = - 10;
        r = - 10;
        t = - 10;
        b = -10;
    }

    public boolean IsInside(Rect rTest) {
        Boolean bResult = false;
        if (RelativeToBottom) {
            if (rTest.t > b && rTest.b < t ) {
                bResult = true;
            }
        } else {
            if (rTest.b > t && rTest.t < b ) {
                bResult = true;
            }
        }
        if (RelativeToLeft) {
            if (!(rTest.l < r && rTest.r > l && bResult == true)) {
                bResult = false;
            }
        } else {
            if (!(rTest.l > r && rTest.r < l && bResult == true)) {
                bResult = false;
            }
        }
        return bResult;
    }
    public boolean IsInside(RectS rTest) {
        Boolean bResult = false;
        if (RelativeToBottom) {
            if (rTest.t > b && rTest.b < t ) {
                bResult = true;
            }
        } else {
            if (rTest.b > t && rTest.t < b ) {
                bResult = true;
            }
        }
        if (RelativeToLeft) {
            if (!(rTest.l < r && rTest.r > l && bResult == true)) {
                bResult = false;
            }
        } else {
            if (!(rTest.l > r && rTest.r < l && bResult == true)) {
                bResult = false;
            }
        }
        return bResult;
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
        /* Copy numerical values of given rect.
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
        if (Debugg) {
            Color newcol = sBtch.getColor();
            sBtch.setColor(1,1,1,1);
            DrawOutLine(tx, sBtch);
            sBtch.setColor(newcol);
        } else DrawWithAlpha(tx,sBtch,a);
    }
    public void DrawWithAlpha(Texture tx,SpriteBatch sBtch, float fAlpha) {
    /* Draw using LIBGDX Spritebatch. LibGDX uses the bottom left of
     * the screen as the reference  */
        Color newcol = sBtch.getColor();
        sBtch.setColor(1,1,1,fAlpha);
        sBtch.draw(tx, l, b, width(), height());
        sBtch.setColor(newcol);
    }

    private RectS rsl = new RectS(),rst = new RectS(),rsr= new RectS(),rsb= new RectS();
    public void DrawOutLine(Texture tx, SpriteBatch batch) {
        if (Changed) {
            rsl.RectCopy(this);
            rsl.r = l + 5;
            rst.RectCopy(this);
            rst.b = t - 5;
            rsr.RectCopy(this);
            rsr.l = r - 5;
            rsb.RectCopy(this);
            rsb.t = b + 5;
            Changed = false;
        }
        rsl.Draw(tx,batch);
        rst.Draw(tx,batch);
        rsr.Draw(tx,batch);
        rsb.Draw(tx,batch);
    }

    /** Simple rectangle animations. Positioning is handled by rect but updating must be done
     * by parent class in a consistent loop.   */
    protected float pt, pb, pl, pr,     /* Post animation position.                 */
                    dt,db,dl,dr,        /* Distance to target animation position.   */
                    preA, distA,        /* Post animation and distance till  Alpha. */

                    AnimTime = 0,
                    AnimRate = 0,
                    AlphaAnimTime = 0,
                    AlphaAnimRate = 0,
                    TimePerCycle = 16;
    boolean animAlpha = false,
            animTranslate = false;
    RectS rsDest;

    public Interpolator Interp, AlphaInterp = com.Dimcon.Interpolator.Constant;

    public void StartAnimT(Rect rDest,Interpolator InterpolatorP,float Timemillis) {
        SetPostAnim();
        Interp = InterpolatorP;

        /* Amount to increase the AnimTime by on each cycle, assuming 60fps
        *  Target Angle (90 degrees, 0.5 radians) divided by
        *  (Target time in milliseconds / Milliseconds each cycle) */
        AnimRate = (float)(0.5f * Math.PI) / (Timemillis/TimePerCycle);
        AnimTime = 0;   /* Start the clock at 0 */
        animTranslate = true;
        /* Store requested ending values to ensure the animation ends where
        *  requested.   */
        rsDest = new RectS(rDest.l,rDest.t,rDest.r,rDest.b);
        dt = rDest.t - t;
        db = rDest.b - b;
        dl = rDest.l - l;
        dr = rDest.r - r;
    }
    public void StartAnimA(float TargetAlpha,Interpolator InterpolatorP,float Timemillis) {
        preA = a;
        AlphaInterp = InterpolatorP;
        /* Refer to StartAnimT ^^  */
        AlphaAnimRate = (float)(0.5f * Math.PI) / (Timemillis/TimePerCycle);
        AlphaAnimTime = 0;
        animAlpha = true;
        distA = TargetAlpha - a;
    }

    public void Animate() {
        if (animTranslate) {
            float fMult = 0; /* Value to multiply the final distance by*/
            switch (Interp) {
                case Accelerate:
                    fMult = (float) (1 - Math.cos(AnimTime));    /*  Accelerate  */
                    break;
                case Decelerate:
                    fMult = (float) Math.sin(AnimTime);          /*  Decelerate */
                    break;
                case Constant:
                    fMult = (float) (AnimTime / (0.50000f * Math.PI));     /* Constant */
                    break;
            }
            AnimTime += AnimRate;

            sett(pt + (dt * fMult));
            setb(pb + (db * fMult));
            setl(pl + (dl * fMult));
            setr(pr + (dr * fMult));
            if (fMult >= 0.999f) {
                animTranslate = false;
                AnimTime = 0;
                sett(rsDest.t);
                setb(rsDest.b);
                setl(rsDest.l);
                setr(rsDest.r);
            }
        }
        if (animAlpha) {
            float fMult2 = 0;
            switch (AlphaInterp) {
                case Accelerate:
                    fMult2 = (float) (1 - Math.cos(AlphaAnimTime));    /*  Accelerate  */
                    break;
                case Decelerate:
                    fMult2 = (float) Math.sin(AlphaAnimTime);          /*  Decelerate */
                    break;
                case Constant:
                    fMult2 = (float) (AlphaAnimTime / (0.5f * Math.PI));     /* Constant */
                    break;
            }
            AlphaAnimTime += AlphaAnimRate;
            a = preA + (distA * fMult2);
            if (fMult2 >= 0.999f) {
                animAlpha = false;
                AlphaAnimTime = 0;
                a = preA + distA;
            }

        }
    }

    private void SetPostAnim() {
        pt = t;
        pb = b;
        pl = l;
        pr = r;
    }

    /** Touch interface for Rect. Relies on a TouchHandler to record touches.
     * */
     boolean Touchable = true;
    HashMap<Integer,Integer> PointerTrack = new HashMap<Integer, Integer>();
    public void Touched(Integer PointerID) {

    }
    public void Touching(Integer PointerID) {

    }
}

enum Interpolator {
    Accelerate, Decelerate, Constant
}
class RectS {
    /** Basic Rectangle */
    /* top,bottom,left,right */
    protected float
            t,b,l,r;
    private Boolean RelativeToBottom = true,
            RelativeToLeft = true;
    public RectS(float leftP, float topP, float rightP, float bottomP) {
        /** Define rectangle using a single procedure.  */
        l = leftP;
        r = rightP;
        t = topP;
        b = bottomP;
    }
    public RectS() {
        OffScreen();
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
        l = rP.l();
        r = rP.r();
        t = rP.t();
        b = rP.b();
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

    public boolean IsInside(Rect rTest) {
        Boolean bResult = false;
        if (RelativeToBottom) {
            if (rTest.t() > b && rTest.b() < t ) {
                bResult = true;
            }
        } else {
            if (rTest.b() > t && rTest.t() < b ) {
                bResult = true;
            }
        }
        if (RelativeToLeft) {
            if (!(rTest.l() < r && rTest.r() > l && bResult == true)) {
                bResult = false;
            }
        } else {
            if (!(rTest.l() > r && rTest.r() < l && bResult == true)) {
                bResult = false;
            }
        }
        return bResult;
    }
    public boolean IsInside(RectS rTest) {
        Boolean bResult = false;
        if (RelativeToBottom) {
            if (rTest.t > b && rTest.b < t ) {
                bResult = true;
            }
        } else {
            if (rTest.b > t && rTest.t < b ) {
                bResult = true;
            }
        }
        if (RelativeToLeft) {
            if (!(rTest.l < r && rTest.r > l && bResult == true)) {
                bResult = false;
            }
        } else {
            if (!(rTest.l > r && rTest.r < l && bResult == true)) {
                bResult = false;
            }
        }
        return bResult;
    }

    public void Draw(Texture tx, SpriteBatch sBtch) {
        /** Draw using LIBGDX Spritebatch. LibGDX uses the bottom left of
         * the screen as the reference  */
        sBtch.draw(tx, l, b, width(), height());
    }
}

