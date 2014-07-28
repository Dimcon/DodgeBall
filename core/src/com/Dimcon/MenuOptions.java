package com.Dimcon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by daimonsewell on 7/20/14.
 */
public class MenuOptions {
    float fXUnit,fYUnit;
    float fLeft,fMult,fTotWidth;
    float fWidth;
    float fGap;
    Rect[] rArray;

    public MenuOptions(float fXUnitP,float fYUnitP,float fStartAt,Rect rDisplayP) {
        fXUnit = fXUnitP;
        fYUnit = fYUnitP;
        fLeft = fStartAt;
        fWidth = 10*fXUnit;
        fGap = 25*fXUnit;
        rArray = new Rect[11];
        float fHolder = fLeft;
        for (int i = 0; i < rArray.length; i++) {
            rArray[i] = new Rect(rDisplayP, fHolder, 90 * fYUnit, fHolder + fWidth, 50 * fYUnit);
            fHolder += fWidth + fGap;
        }
        fTotWidth =  ((rArray.length - 1) * (fWidth + fGap)) + fWidth;
    }
    public void Update(Rect rHor,Rect rHandle, float fTouchX,Rect rDisplayP,SpriteBatch batch) {
        fLeft = (rHor.l() - (rHandle.width()/2)) - (((((fTouchX - rHor.l()) / (rHor.width())))) * (fTotWidth - (rHor.width() + (rHandle.width()))));
        float fHolder = fLeft;
        for (int i = 0; i < rArray.length; i++) {
            rArray[i] = new Rect(rDisplayP, fHolder, 90 * fYUnit, fHolder + fWidth, 50 * fYUnit);
            fHolder += fWidth + fGap;
            rArray[i].Draw(ResMan.Get("Screen"), batch);
        }
        if (rArray[rArray.length - 1].r() <= 60 * fXUnit) {
            fLeft = (60 * fXUnit) - ((rArray.length - 1) * (fWidth + fGap)) + fWidth;
        }
    }
    public int DeterminePos(Rect rHor,float CenterX,float fTouchX) {
        float TouchXmC = (fTouchX - rHor.l()) - (fTouchX - CenterX);
        int fPos = (int)((TouchXmC - (TouchXmC % (rHor.width() / (rArray.length-1)))) / (rHor.width() / (rArray.length-1)));
        if (!(((TouchXmC % (rHor.width() / (rArray.length-1))) / (rHor.width() / (rArray.length-1))) < 0.49f))
            fPos++;
        /** Get Current point*/
        return fPos;
    }
    public float SetLeft(Rect rHor,float fMult,float fHandle,float CenterX,float fTouchX,int Pos) {
        float TouchXmC = (fTouchX - rHor.l()) - (fTouchX - CenterX);
        float fSingle = rHor.width() / (rArray.length-1);
        int fPos = Pos;
        /** Limit to current point*/
        return fTouchX - fHandle + (fMult * ((fPos*fSingle) - TouchXmC));
        /*ACCELDECEL at points      if (bFirstHalf) {
            float fTimesBy = (1 - (float)Math.sin(((TouchXmC - (fPos * fSingle))/(fSingle/2)) * (0.5f*Math.PI)));
            fMod = fMult * fTimesBy * ((TouchXmC - (fPos * fSingle))*1f);
        } else {
            float fTimesBy = 1-(float)Math.sin(((((fPos+1) * fSingle) - TouchXmC)/(fSingle/2)) * (0.5f*Math.PI));
            fMod = fMult * fTimesBy * (-(((fPos+1) * fSingle) - TouchXmC)*1f);
        }
        return fTouchX - fMod - fHandle;*/
    }
}
