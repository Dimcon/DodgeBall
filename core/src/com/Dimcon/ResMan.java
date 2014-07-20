package com.Dimcon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * Created by daimonsewell on 7/15/14.
 */
public class ResMan {
    private static HashMap<String, Resource> ImageStore = new HashMap<String, Resource>(100);
    private static HashMap<String, Resource> ImageStoreLarge = new HashMap<String, Resource>(10);
    private static HashMap<String,Rect> RectStore = new HashMap<String, Rect>(100);

    public static void AddRect(String sName,Rect rAdd) {
        RectStore.put(sName,rAdd);
    }
    public static Rect GetRect(String sName) {
        if (RectStore.containsKey(sName))
            return RectStore.get(sName);
        return null;
    }
    public ResMan(Texture DefaultTexture,Integer SmallResLimit,Integer LargeResLimit) {
        ImageStore = new HashMap<String, Resource>(100);
        ImageStoreLarge = new HashMap<String, Resource>();
    }
    public ResMan() {
    }

    public static void AddImage(String sName,String sPath) {
        Resource res = new Resource(sName,sPath,"Main");
        if (res.Size < (Gdx.graphics.getWidth()/2) * (Gdx.graphics.getHeight()/2)) {
            ImageStore.put(res.sName,res);
        } else {
            ImageStoreLarge.put(res.sName,res);
        }
    }
    public static void AddImage(String sName,String sPath,String Group) {
        Resource res = new Resource(sName,sPath,Group);
        if (res.Size < (Gdx.graphics.getWidth()/2) * (Gdx.graphics.getHeight()/2)) {
            ImageStore.put(res.sName,res);
        } else {
            ImageStoreLarge.put(res.sName,res);
        }
    }
    public static Texture Get(String sName) {
        if (ImageStore.containsKey(sName)) {
            if (ImageStore.get(sName).isNull) {
                ImageStore.get(sName).GetBack();
            }
            return ImageStore.get(sName).imgResource;
        } else if (ImageStoreLarge.containsKey(sName)) {
            if (ImageStoreLarge.get(sName).isNull) {
                ImageStoreLarge.get(sName).GetBack();
            }
            return ImageStoreLarge.get(sName).imgResource;
        }
        return null;
    }

    public static void DisposeGroup(String sGroup) {
        for (String key: ImageStore.keySet()) {
            if (ImageStore.get(key).Group.equals(sGroup))
                ImageStore.get(key).dispose();
        }
        for (String key: ImageStoreLarge.keySet()) {
            if (ImageStoreLarge.get(key).Group.equals(sGroup))
                ImageStoreLarge.get(key).dispose();
        }
    }
    public static void DisposeImage(String sName) {
        if (ImageStore.get(sName) != null) {
            ImageStore.get(sName).dispose();
            return;
        }
        if (ImageStoreLarge.get(sName) != null) {
            ImageStoreLarge.get(sName).dispose();
        }
    }
    public void DisposeAll() {
        for (String key: ImageStore.keySet()) {
            ImageStore.get(key).dispose();
        }
        for (String key: ImageStoreLarge.keySet()) {
            ImageStoreLarge.get(key).dispose();
        }
    }
}

class Resource {
    String sPath,sName,Group;
    Texture imgResource;
    float Size;
    boolean isNull = true;

    Resource(String sNameP,String sPathP,String sGroupP) {
        Group = sGroupP;
        sPath = sPathP;
        sName = sNameP;
        GetBack();
        Size = imgResource.getHeight() * imgResource.getWidth();
    }

    public void GetBack() {
        imgResource = new Texture(sPath);
        isNull = false;
    }
    public void dispose() {
        imgResource.dispose();
        isNull = true;
    }
}
