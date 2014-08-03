package com.Dimcon;

import com.badlogic.gdx.Gdx;
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
    /** top,bottom,left,right */
     private float
                t,b,l,r;
    public float a = 1f;
    private Boolean RelativeToBottom = true,
                    RelativeToLeft = true,
                    Changed = true;
    static boolean Debugg = false;
    public static int RectsDrawn = 0;
    public static void setDebug(Boolean Value) {
        Debugg = Value;
    }
    public static Rect rScreen = new Rect(0, Gdx.graphics.getHeight(),Gdx.graphics.getWidth(),0);

    public Rect(float leftP, float topP, float rightP, float bottomP) {
        /** Define rectangle using a single procedure.  */
        l = leftP;
        r = rightP;
        t = topP;
        b = bottomP;
        Changed = true;

    }
    public Rect(Rect rDisplay,float leftP, float topP, float rightP, float bottomP) {
        /** Define rectangle using a single procedure in relation to its parent rDisplay.  */
        l = rDisplay.l() + leftP;
        r = rDisplay.l() +rightP;
        t = rDisplay.b() +topP;
        b = rDisplay.b() +bottomP;
        Changed = true;

    }
    public void Update(Rect rDisplay,float leftP, float topP, float rightP, float bottomP) {
        /** Define rectangle using a single procedure in relation to its parent rDisplay.  */
        l = rDisplay.l() + leftP;
        r = rDisplay.l() +rightP;
        t = rDisplay.b() +topP;
        b = rDisplay.b() +bottomP;
        Changed = true;

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
    public void setl(Rect rDisplay,float left) {
        l = rDisplay.l() + left;
        Changed = true;
    }
    public void setr(Rect rDisplay,float right) {
        r = rDisplay.l() + right;
        Changed = true;
    }
    public void setb(Rect rDisplay,float bottom) {
        b = rDisplay.b() + bottom;
        Changed = true;
    }
    public void sett(Rect rDisplay,float top) {
        t = rDisplay.b() + top;
        Changed = true;
    }
    public void setAlpha(float Alpha) {
        a = Alpha;
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
    /* Get height of Rect */
        if (RelativeToBottom) {
            return t - b;
        } else {
            return b - t;
        }
    }
    public void OffScreen() {
    /* Move all points to negative. */
        Changed = true;

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
            if (!(rTest.l < r && rTest.r > l) && bResult) {
                bResult = false;
            }
        } else {
            if (!(rTest.l > r && rTest.r < l) && bResult) {
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
            if (!(rTest.l < r && rTest.r > l && bResult)) {
                bResult = false;
            }
        } else {
            if (!(rTest.l > r && rTest.r < l && bResult)) {
                bResult = false;
            }
        }
        return bResult;
    }

    public void MoveLeft(float fAmount) {
        Changed = true;

        if (RelativeToLeft) {
            r = r - fAmount;
            l = l - fAmount;
        } else {
            r = r + fAmount;
            l = l + fAmount;
        }
    }
    public void MoveDown(float fAmount) {
        Changed = true;

        if (RelativeToBottom) {
            t = t - fAmount;
            b = b - fAmount;
        } else {
            t = t + fAmount;
            b = b + fAmount;
        }
    }
    public void MoveRight(float fAmount) {
        Changed = true;

        if (RelativeToLeft) {
            r = r + fAmount;
            l = l + fAmount;
        } else {
            r = r - fAmount;
            l = l - fAmount;
        }
    }
    public void Moveup(float fAmount) {
        Changed = true;

        if (RelativeToBottom) {
            t = t + fAmount;
            b = b + fAmount;
        } else {
            t = t - fAmount;
            b = b - fAmount;
        }
    }
    public void RectCopy(Rect rP) {
        /** Copy numerical values of given rect.
         *  Saying Rect1 = Rect2 seems to make Rect1 point to Rect2 instead of
         *  just copying it.    */
        l = rP.l;
        r = rP.r;
        t = rP.t;
        b = rP.b;
        Changed = true;
    }
    public void CopySquare(Rect rP,float rPadding) {
        Changed = true;
        /** Turn into square as large as possible within rectangle
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
    public void Draw(Texture tx, SpriteBatch sBtch,float fAlphaP) {
        DrawWithAlpha(tx,sBtch,fAlphaP);
    }
    public void DrawWithAlpha(Texture tx,SpriteBatch sBtch, float fAlpha) {
    /** Draw using LIBGDX Spritebatch. LibGDX uses the bottom left of
     * the screen as the reference  */
        if (IsInside(rScreen)) {
            if (Debugg) {
                Color newcol = sBtch.getColor();
                sBtch.setColor(1, 1, 1, 1);
                DrawOutLine(tx, sBtch);
                sBtch.setColor(newcol);
            } else {
                Color newcol = sBtch.getColor();
                sBtch.setColor(1, 1, 1, fAlpha);
                sBtch.draw(tx, l, b, width(), height());
                sBtch.setColor(newcol);
            }
            Rect.RectsDrawn++;
        }

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
        /** Amount to increase AnimTime by on each cycle, assuming Animate will be
         * called 60fps. Target Angle (90 degrees, 0.5 radians) divided by
         *  (Target time in milliseconds / Milliseconds each cycle) */
        AnimRate = (float)(0.5f * Math.PI) / (Timemillis/TimePerCycle);
        AnimTime = 0;   /* Start the clock at 0 */
        animTranslate = true;
        /** Store the requested ending values to ensure the animation ends where
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
        /** Refer to StartAnimT ^^  */
        AlphaAnimRate = (float)(0.5f * Math.PI) / (Timemillis/TimePerCycle);
        AlphaAnimTime = 0;
        animAlpha = true;
        distA = TargetAlpha - a;
    }

    public void Animate() {
        /** The distance that the rect needs to be moved is kept as a whole. AnimTime
         *  is 90 degrees of a circle. AnimTime is increased at a constant rate so that
         *  it reaches 90 degrees in the time asked for. Using Sin and Cos on the 'angle'
         *  returns a value that either increases (Cos) or decreases (Sin) with acceleration
         *  */
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
            if (fMult >= 0.99f) {
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
            if (fMult2 >= 0.99f) {
                animAlpha = false;
                AlphaAnimTime = 0;
                a = preA + distA;
            }

        }
    }

    private void SetPostAnim() {
        /** Sets the values of the rect before it is animated. */
        pt = t;
        pb = b;
        pl = l;
        pr = r;
    }

    /** Touch interface for Rect. Relies on TouchHandler to record touches.
     *  isTouched will be true if the current rect has been touched and is now
     *  under the users pointer. isTouched will be true if there is currently a
     *  pointer in this rect, regardless of where the pointer touched first.
     * */

    public int pointer;
    public Boolean IsTouched() {
        /** AHHOC (Annoyingly Hard, I was high on coffee when writing this) -Naos
         *  If touchHandler reports a new touch occurring in this rect and there
         *  is no currently assigned pointer, then assign that pointer to this rect.
         *  If this rects assigned pointer isn't in touchHandlers system, it's safe to assume
         *  this rect has been abandoned and should report as alone (-1).
         * */

        if (TouchHandler.TouchDownAtRect(this) > -1 && pointer == -1) {
            pointer = TouchHandler.TouchDownAtRect(this);
        }
        if (pointer > -1 && !TouchHandler.PointerIsHere(pointer)) {
            pointer = -1;
        }
        return pointer != -1;
    }
    public float TouchedX() {
        /** Reports the X coord of the pointer assigned to the current Rect. Returns
         * -1 if this rect is lonely (no assigned pointers)*/
        if (IsTouched()) {
            return TouchHandler.TouchMap.get(pointer).TouchPosX;
        }
        return -1;
    }
    public float TouchedY() {
        /** Reports the Y coord of the pointer assigned to the current Rect. Returns
         * -1 if this rect is lonely (no assigned pointers)*/
        if (IsTouched()) {
            return TouchHandler.TouchMap.get(pointer).TouchPosY;
        }
        return -1;
    }
    public float TouchedHandlex() {
        /** Reports the horizontal size of the space between the rect and the pointer. */
        if (IsTouched()) {
            return TouchHandler.TouchMap.get(pointer).TouchPosX - l;
        }
        return -1;
    }
    public float TouchedHandleY() {
        /** Reports the vertical size of the space between the rect and the pointer. */
        if (IsTouched()) {
            return TouchHandler.TouchMap.get(pointer).TouchPosY - b;
        }
        return -1;
    }
    public Boolean IsTouching() {
        /** This makes the rect desperate. It will report true if there is ANY
         *  touch within the rect. */
        return TouchHandler.isTouchingRect(this) != -1;
    }
    public float TouchingX() {
        /** Reports the X coord of a pointer here. Any pointer in the vicinity of this
         *  Rect will count as a touch, whether TouchHandler reports the touchdown
         *  here or not. */
            int Lpointer = TouchHandler.isTouchingRect(this);
            if (TouchHandler.TouchMap.get(Lpointer) == null)
                return -1;
            else return TouchHandler.TouchMap.get(Lpointer).TouchPosX;
    }
    public float TouchingY() {
        /** Reports the Y coord of a pointer here. Any pointer in the vicinity of this
         *  Rect will count as a touch, whether TouchHandler reports the touchdown
         *  here or not. */
        int Lpointer = TouchHandler.isTouchingRect(this);
        if (TouchHandler.TouchMap.get(Lpointer) == null)
            return -1;
        else return TouchHandler.TouchMap.get(Lpointer).TouchPosY;
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
            if (!(rTest.l() < r && rTest.r() > l && bResult)) {
                bResult = false;
            }
        } else {
            if (!(rTest.l() > r && rTest.r() < l && bResult)) {
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
            if (!(rTest.l < r && rTest.r > l && bResult)) {
                bResult = false;
            }
        } else {
            if (!(rTest.l > r && rTest.r < l && bResult)) {
                bResult = false;
            }
        }
        return bResult;
    }

    public void Draw(Texture tx, SpriteBatch sBtch) {
        /** Draw using LIBGDX Spritebatch. LibGDX uses the bottom left of
         * the screen as the reference  */
        sBtch.draw(tx, l, b, width(), height());
        Rect.RectsDrawn++;
    }
}

