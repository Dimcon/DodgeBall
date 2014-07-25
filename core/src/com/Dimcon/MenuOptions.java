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
        rArray = new Rect[5];
        float fHolder = fLeft;
        for (int i = 0; i < rArray.length; i++) {
            rArray[i] = new Rect(rDisplayP, fHolder, 90 * fYUnit, fHolder + fWidth, 50 * fYUnit);
            fHolder += fWidth + fGap;
        }
        fTotWidth =  ((rArray.length - 1) * (fWidth + fGap)) + fWidth;
    }
    public void Update(Rect rHor,float fTouchX,Rect rDisplayP,SpriteBatch batch) {
        fLeft = (rHor.l()) - (((((fTouchX - rHor.l()) / (rHor.width())))) * (fTotWidth - rHor.width()));
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
    public float SetLeft(Rect rHor,float fMult,float fHandle,float fTouchX,SpriteBatch batch) {
        float mnHor = fTouchX - rHor.l();
        float fSingle = rHor.width() / (rArray.length-1);
        float fPos = ((mnHor - (mnHor % fSingle)) / fSingle);
        boolean bFirstHalf =  (mnHor % fSingle) / fSingle < 0.49f;
        if (bFirstHalf) {
            float fAngle = ((mnHor - (fSingle * fPos))/(fSingle/2));
            float fMulti = (float)(1-Math.cos(fAngle * (0.5f*Math.PI)));
            float fReturn = (fMult * (fMulti * (fSingle/2)));
            return (fSingle * fPos) + fReturn + fHandle;
        } else {
            float fAngle = (((fSingle * (fPos+1)) - mnHor)/(fSingle/2));
            float fMulti = (float)(Math.sin(fAngle * (0.5f*Math.PI)));
            float fReturn = (fMult * (fMulti*(fSingle/2)));
            return (fSingle * (fPos+1)) - fReturn + fHandle;
        }
    }
}
