package com.Dimcon;

import com.badlogic.gdx.Gdx;

import java.awt.Point;
import java.util.HashMap;

/**
 * Created by daimonsewell on 7/11/14.
 */
public class TouchHandler {
    int ScreenX = Gdx.graphics.getWidth(),ScreenY= Gdx.graphics.getHeight();
    public static HashMap<Integer,TouchID> TouchMap = new HashMap<Integer,TouchID>();

    public boolean isTouchingRect(Rect rTest) {
        for (Integer key : TouchMap.keySet()) {
            if (rTest.IsInside(new Rect(TouchMap.get(key).TouchPosX,
                    TouchMap.get(key).TouchPosY,
                    TouchMap.get(key).TouchPosX,
                    TouchMap.get(key).TouchPosY))) {
                return true;
            }
        }
        return false;
    }
    public void TouchDown(float TouchX, float TouchY,int Pointer) {
        TouchMap.put(Pointer,new TouchID(TouchX,ScreenY-TouchY));
    }
    public void TouchMoved(float TouchX, float TouchY,int Pointer){
        TouchMap.get(Pointer).TouchPosX = TouchX;
        TouchMap.get(Pointer).TouchPosY = ScreenY - TouchY;
    }
    public void TouchUp(float TouchX, float TouchY,int Pointer) {
        TouchMap.remove(Pointer);
    }
}

class TouchID {
    float TouchPosX, TouchPosY;

    TouchID(float touchPosX, float touchPosY) {
        TouchPosX = touchPosX;
        TouchPosY = touchPosY;
    }
}