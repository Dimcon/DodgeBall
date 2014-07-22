package com.Dimcon;

import com.badlogic.gdx.Gdx;
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
        rArray = new Rect[5];
        float fHolder = fLeft;
        for (int i = 0; i < rArray.length; i++) {
            rArray[i] = new Rect(rDisplayP, fHolder, 90 * fYUnit, fHolder + fWidth, 50 * fYUnit);
            fHolder += fWidth + fGap;
        }
        fTotWidth =  ((rArray.length - 1) * (fWidth + fGap)) + fWidth;
    }
    public void Update(Rect rHor,float fTouchX,Rect rDisplayP,SpriteBatch batch) {
        fLeft = (rHor.l()) -(((((fTouchX - rHor.l())/(rHor.width())))) * (fTotWidth - rHor.width()));
        float fHolder = fLeft;
        for (int i = 0; i < rArray.length; i++) {
            rArray[i] = new Rect(rDisplayP,fHolder,90*fYUnit,fHolder + fWidth,50*fYUnit);
            fHolder += fWidth + fGap;
            rArray[i].Draw(ResMan.Get("Screen"), batch);
        }
        if (rArray[rArray.length-1].r() <= 60*fXUnit) {
            fLeft =  (60*fXUnit) - ((rArray.length - 1) * (fWidth + fGap)) + fWidth;
        }
    }
    public float SetLeft(Rect rHor,float fMult,float fHandle,float fTouchX) {
        float Width = rHor.width();
        float fSingle = Width/(rArray.length-1);
        int Pos = (int)Math.floor((fTouchX-rHor.l()/(fSingle)));
        boolean FirstHalf = (Pos+0.49f) < (fTouchX-rHor.l())/(fSingle);
        if (FirstHalf) {
            double Angle = (double)(((fSingle*Pos) - (fTouchX - rHor.l()))/(fSingle/2))* (0.5 * Math.PI);
            return fTouchX - fHandle + (fMult*((fSingle/2) * (float)(1-Math.sin(Angle))));
            }
        double Angle =(double)(((fSingle*Pos) - (fTouchX - rHor.l()))/(fSingle/2))* (0.5 * Math.PI);
        return fTouchX - fHandle - (fMult*((fSingle/2) * (float)(Math.cos(Angle))));
    }
}
