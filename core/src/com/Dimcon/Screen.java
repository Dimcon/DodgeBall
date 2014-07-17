package com.Dimcon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.HashMap;

/**
 * Created by daimonsewell on 7/7/14.0
 */
public class Screen {
    /**
     * Screen class. The events will be called by ScreenHandler in the order:
     * Create, AnimIn, Draw, AnimOut and Destroy.
     * ScreenHandler will loop the current procedure until it returns True.
     * fXUnit and fYUnit are a percentage of the width and height of rDisplay
     * and should be used with reference to rDisplay in order to achieve
     * screen manipulation such as proper scaling and position. */


    Rect    rDisplay = new Rect(0, Gdx.graphics.getHeight(),Gdx.graphics.getWidth(),0);
    static Rect rFullscreen = new Rect(0, Gdx.graphics.getHeight(),Gdx.graphics.getWidth(),0);
    Boolean Display = false,
            Paused = false,
            Active = false;
    String Name = null;
    float   fXunit = rDisplay.width()/100f,
            fYunit = rDisplay.height()/100f,

    /** fAlpha is used to set the alpha of the SpriteBatch prior to calling
    * screen procedures. */
            fAlpha = 0,

            ScreenY = Gdx.graphics.getHeight(),
            ScreenX = Gdx.graphics.getWidth();
    public CycleStage  stage = CycleStage .Deactivated;

    public void ResetUnits() {
        /* Run every frame regardless of whether the screen is drawn or not. */
        fXunit = rDisplay.width()/100f;
        fYunit = rDisplay.height()/100f;
        rDisplay.Animate();

    }
    public void Switch(String key,ScreenManager screenManager) {
        stage = CycleStage .AnimateOut;
        screenManager.ScreenStore.get(key).stage = CycleStage .Create;
    }
    public void BeforeAll(DeltaBatch batch) {
        /** Called before each Frame once Create has been called. Is not called before Destroy. */
    }
    public void AfterAll(DeltaBatch batch) {
        /** Called after each Frame once Create has been called. Is not called after Destroy. */
    }

    public Boolean Create(DeltaBatch batch) {
        /** This is called straight after the screen has been switched to active and
        * only runs a single time. */
        Display = true;
        Paused = false;
        return true;
    }
    public Boolean AnimIn(DeltaBatch batch) {
        /** Called after Create. AnimIn is called continuously until it returns true.
        *  By default a simple fade in animation has been set up. Override to create
        *  a different animation. Return true to move on to Draw stage. */
        fAlpha = Math.min(fAlpha + 0.01f, 1f);
        return (fAlpha == 1f);
    }
    public Boolean Draw(DeltaBatch batch) {
        /** The main logic loop of a screen. Called continuously after AnimIn until
        *  True is returned. Will run continuously by default. */
        return false;
    }
    public Boolean AnimOut(DeltaBatch batch) {
        /** Called at the same time the previous screens AnimIn is called. Called
        * continuously once the current screen has been switched out until returns true.
        * This allows an animation when switching screens. */
        fAlpha = Math.max(fAlpha - 0.01f, 0f);
        return (fAlpha == 0f);
    }
    public Boolean Destroy(DeltaBatch batch) {
        /** Called once AnimOut has completed and should only be called once. */
        Display = false;
        Paused = true;
        return true;
    }

    /** Hashmap of buttons to simplify adding buttons to the screen. */
    private HashMap<String,Button> ButtonStore = new HashMap<String, Button>();
    private BitmapFont dfont = new BitmapFont();

    public void AddButton(String sName,BitmapFont font,String sLabel,Rect rButton,String sResNorm,String sResHov,String sResDown,ClickListener clicker) {
        Skin skin = new Skin();
        skin.add("Norm",ReseourceMan.Get(sResNorm));
        skin.add("Down",ReseourceMan.Get(sResDown));
        skin.add("Hov",ReseourceMan.Get(sResHov));
        Button tbtn = new TextButton(sLabel,new TextButton.TextButtonStyle(
                skin.getDrawable("Norm"),
                skin.getDrawable("Down"),
                skin.getDrawable("Hov"),(font == null)?dfont : font));
        skin.dispose();
        tbtn.setPosition(rButton.l(),rButton.b());
        tbtn.setHeight(rButton.height());
        tbtn.setWidth(rButton.width());
        tbtn.addListener(clicker);
        ButtonStore.put(sName,tbtn);
        ScreenManager.batch.DrawStage.addActor(ButtonStore.get(sName));
    }
    public void UpdateButton(String sName, Rect rUpdate) {
        ButtonStore.get(sName).setPosition(rUpdate.l(),rUpdate.b());
        ButtonStore.get(sName).setHeight(rUpdate.height());
        ButtonStore.get(sName).setWidth(rUpdate.width());
    }
    public void RemoveButton(String sName) {
        ButtonStore.remove(sName);
    }
}

enum CycleStage {
    Deactivated, Create, AnimateIn, Draw, AnimateOut, Destroy;
}
