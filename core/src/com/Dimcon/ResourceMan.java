package com.Dimcon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * Created by daimonsewell on 7/15/14.
 */
public class ResourceMan {
    private static HashMap<String, Resource> ImageStore = new HashMap<String, Resource>(100);
    private static HashMap<String, Resource> ImageStoreLarge = new HashMap<String, Resource>(10);

    public ResourceMan(Texture DefaultTexture,Integer SmallResLimit,Integer LargeResLimit) {
        ImageStore = new HashMap<String, Resource>(100);
        ImageStoreLarge = new HashMap<String, Resource>();
    }
    public ResourceMan() {
    }

    public static void AddImage(String sName,String sPath) {
        Resource res = new Resource(sName,sPath);
        if (res.Size < (Gdx.graphics.getWidth()/2) * (Gdx.graphics.getHeight()/2)) {
            ImageStore.put(res.sName,res);
        } else {
            ImageStoreLarge.put(res.sName,res);
        }
    }
    public static Texture Get(String sName) {
        if (ImageStore.containsKey(sName)) {
            return ImageStore.get(sName).imgResource;
        } else if (ImageStoreLarge.containsKey(sName)) {
            return ImageStoreLarge.get(sName).imgResource;
        }
        return null;
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
    String sPath,sName;
    Texture imgResource;
    float Size;

    Resource(String sNameP,String sPathP) {
        sPath = sPathP;
        sName = sNameP;
        GetBack();
        Size = imgResource.getHeight() * imgResource.getWidth();
    }

    public void GetBack() {
        imgResource = new Texture(sPath);
    }
    public void dispose() {
        imgResource.dispose();
    }
}
