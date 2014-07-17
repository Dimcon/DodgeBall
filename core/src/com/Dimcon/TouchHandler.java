package com.Dimcon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import java.awt.Point;
import java.util.HashMap;

/**
 * Created by daimonsewell on 7/11/14.
 */
public class TouchHandler implements InputProcessor {
    int ScreenX = Gdx.graphics.getWidth(),ScreenY= Gdx.graphics.getHeight();
    public static HashMap<Integer,TouchID> TouchMap = new HashMap<Integer,TouchID>();
    public static HashMap<Integer,TouchID> TouchDown = new HashMap<Integer,TouchID>();

    public static int isTouchingRect(Rect rTest) {
        for (Integer key : TouchMap.keySet()) {
            if (rTest.IsInside(new Rect(TouchMap.get(key).TouchPosX,
                    TouchMap.get(key).TouchPosY,
                    TouchMap.get(key).TouchPosX,
                    TouchMap.get(key).TouchPosY))) {
                return key;
            }
        }
        return -1;
    }
    public static boolean PointerIsHere(int Pointer) {
        return (TouchMap.get(Pointer) != null);
    }
    public static boolean isTouchingRect(Rect rTest, int Pointer) {
        if (TouchMap.get(Pointer) != null)
            if (rTest.IsInside(new Rect(TouchMap.get(Pointer).TouchPosX,
                    TouchMap.get(Pointer).TouchPosY,
                    TouchMap.get(Pointer).TouchPosX,
                    TouchMap.get(Pointer).TouchPosY))) {
                return true;
            }
        return false;
    }
    public static int TouchDownAtRect(Rect rTest) {
        for (Integer key : TouchMap.keySet()) {
            if (rTest.IsInside(new Rect(TouchDown.get(key).TouchPosX,
                    TouchDown.get(key).TouchPosY,
                    TouchDown.get(key).TouchPosX,
                    TouchDown.get(key).TouchPosY))) {
                return key;
            }
        }
        return -1;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (ScreenManager.batch.DrawStage.touchDown(screenX,screenY,pointer,button)) {
            return true;
        }
        TouchMap.put(pointer,new TouchID(screenX,ScreenY-screenY));
        TouchDown.put(pointer,new TouchID(screenX,ScreenY-screenY));
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (ScreenManager.batch.DrawStage.touchUp(screenX,screenY,pointer,button)) {
            return true;
        }
        TouchMap.remove(pointer);
        TouchDown.remove(pointer);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (ScreenManager.batch.DrawStage.touchDragged(screenX,screenY,pointer)) {
            return true;
        }
        TouchMap.get(pointer).TouchPosX = screenX;
        TouchMap.get(pointer).TouchPosY = ScreenY - screenY;
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

class TouchID {
    float TouchPosX, TouchPosY;

    TouchID(float touchPosX, float touchPosY) {
        TouchPosX = touchPosX;
        TouchPosY = touchPosY;
    }
}