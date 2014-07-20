package com.Dimcon;

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
        rArray = new Rect[10];
        float fHolder = fLeft;
        for (int i = 0; i < rArray.length; i++) {
            rArray[i] = new Rect(rDisplayP, fHolder, 90 * fYUnit, fHolder + fWidth, 50 * fYUnit);
            fHolder += fWidth + fGap;
        }
        fTotWidth =  ((rArray.length - 1) * (fWidth + fGap)) + fWidth;
    }
    public void Update(float fTouchX,Rect rDisplayP,SpriteBatch batch) {
        fLeft = (50*fXUnit) -(((((fTouchX)/(rDisplayP.width())))) * (fTotWidth));
        float fHolder = fLeft;
        for (int i = 0; i < rArray.length; i++) {
            rArray[i] = new Rect(rDisplayP,fHolder,90*fYUnit,fHolder + fWidth,50*fYUnit);
            fHolder += fWidth + fGap;
            rArray[i].Draw(ResMan.Get("Screen"),batch);
        }
        if (rArray[rArray.length-1].r() <= 60*fXUnit) {
            fLeft =  (60*fXUnit) - ((rArray.length - 1) * (fWidth + fGap)) + fWidth;
        }
    }
}
