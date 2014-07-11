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
    public static HashMap<Integer,Rect> RectMap = new HashMap<Integer,Rect>();

    public void AddRect(Rect r) {
        Integer i;
        for (i = 0; RectMap.get(i) != null; i++) {}
        RectMap.put(i, r);
    }
    public void RemoveRect(Integer id) {
        RectMap.remove(id);
    }
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
        for (Integer key: RectMap.keySet()) {
            if (RectMap.get(key).IsInside(new Rect(TouchX,ScreenY-TouchY,TouchX,ScreenY-TouchY))) {
                RectMap.get(key).Touched(Pointer);
            }
        }
    }
    public void TouchMoved(float TouchX, float TouchY,int Pointer){
        TouchMap.get(Pointer).TouchPosX = TouchX;
        TouchMap.get(Pointer).TouchPosY = ScreenY - TouchY;
        for (Integer key: RectMap.keySet()) {
            if (RectMap.get(key).IsInside(new Rect(TouchX,ScreenY-TouchY,TouchX,ScreenY-TouchY))) {
                RectMap.get(key).Touching(Pointer);
            }
        }
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