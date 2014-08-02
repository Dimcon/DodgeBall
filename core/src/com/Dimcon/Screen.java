package com.Dimcon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.awt.FontMetrics;
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

    /** rDisplay Alpha is used to set the alpha of the SpriteBatch prior to calling
     * screen procedures. */
    Rect    rDisplay = new Rect(0, Gdx.graphics.getHeight(),Gdx.graphics.getWidth(),0);
    static Rect rFullscreen = new Rect(0, Gdx.graphics.getHeight(),Gdx.graphics.getWidth(),0);
    Boolean Display = false,
            Paused = false,
            Active = false,
            ClipToRDisplay = false,
            Created = false,
            CreateAgain = false,
            Debugger = false;
    public String Name = null;
    float   fXunit = rDisplay.width()/100f,
            fYunit = rDisplay.height()/100f,


            ScreenY = Gdx.graphics.getHeight(),
            ScreenX = Gdx.graphics.getWidth();
    public CycleStage  stage = CycleStage .Deactivated;

    public void ResetUnits() {
        /* Run every frame regardless of whether the screen is drawn or not. */
        fXunit = rDisplay.width()/100f;
        fYunit = rDisplay.height()/100f;
        rDisplay.Animate();

    }

    public void Switch(String key) {
        stage = CycleStage .AnimateOut;
        ScreenManager.ScreenStore.get(key).stage = CycleStage .Create;
    }
    public void CreateAgain(Boolean bYesNo) {
        CreateAgain = bYesNo;
    }

    public Boolean ScreenMethod(CycleStage stageP,String sScreen,DeltaBatch batch) {
        switch (ScreenManager.ScreenStore.get(sScreen).stage) {
            case Deactivated: default:
                break;
            case Create:
                return ScreenManager.ScreenStore.get(sScreen).Create(batch);
            case AnimateIn:
                return ScreenManager.ScreenStore.get(sScreen).AnimIn(batch);
            case Draw:
                return ScreenManager.ScreenStore.get(sScreen).Draw(batch);
            case AnimateOut:
                return ScreenManager.ScreenStore.get(sScreen).AnimOut(batch);
            case Destroy:
                return ScreenManager.ScreenStore.get(sScreen).Destroy(batch);
        }
        return true;
    }

    public void Start() {
        stage = CycleStage .Create;
    }
    public void End() {
        stage = CycleStage .AnimateOut;
    }
    public void Kill() {
        stage = CycleStage .Destroy;
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
        rDisplay.a = Math.min(rDisplay.a + 0.01f, 1f);
        return (rDisplay.a == 1f);
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
        rDisplay.a = Math.max(rDisplay.a - 0.01f, 0f);
        return (rDisplay.a == 0f);
    }
    public Boolean Destroy(DeltaBatch batch) {
        /** Called once AnimOut has completed and should only be called once. */
        Display = false;
        Paused = true;
        return true;
    }

    /** Hashmap of buttons to simplify adding buttons to the screen. */
    public HashMap<String,ButtonRect> ButtonStore = new HashMap<String, ButtonRect>();
    private BitmapFont dfont = new BitmapFont();

    public void AddButton(String sName,BitmapFont font,String sLabel,Rect rButton,String sResNorm,String sResHov,String sResDown,ClickListener clicker) {
        Skin skin = new Skin();
        skin.add("Norm", ResMan.Get(sResNorm));
        skin.add("Down", ResMan.Get(sResDown));
        skin.add("Hov", ResMan.Get(sResHov));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = (font != null) ? font : dfont;
        textButtonStyle.up = skin.getDrawable("Norm");
        textButtonStyle.down = skin.getDrawable("Down");
        textButtonStyle.checked = skin.getDrawable("Hov");
        Button tbtn = new TextButton("Button1", textButtonStyle);
        tbtn.setPosition(rButton.l(), rButton.b());
        tbtn.setHeight(rButton.height());
        tbtn.setWidth(rButton.width());
        tbtn.addListener(clicker);
        ButtonStore.put(sName, new ButtonRect(tbtn, rButton));
        ScreenManager.batch.DrawStage.addActor(ButtonStore.get(sName).button);
    }
    public void UpdateButton(String sName, float LfXunits, float TfYunits,float RfXUnits, float bfYunits) {
        ButtonStore.get(sName).rLayout = new Rect(LfXunits*fXunit,TfYunits*fYunit,RfXUnits*fXunit,bfYunits*fYunit);
    }
    public void RemoveButton(String sName) {
        ButtonStore.remove(sName);
    }

    /** Clip Interface
     *  Batch will be ended and restarted whenever a GL function is enabled/disabled.
     *  Be sure to batch multiple clips within the same batch.begin and batch.end for performance gain. */
    public void  EnableClip(DeltaBatch batch) {
        Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
    }
    public void BeginClip(DeltaBatch batch) {
        //batch.batch.end();
        batch.batch.flush();
        EnableClip(batch);
        //batch.batch.begin();
    }
    public void ClipRect(Rect rClip) {
        Gdx.gl.glScissor((int)rClip.l(),(int)rClip.b(),(int)rClip.width(),(int)rClip.height());
    }
    public void EndClip(DeltaBatch batch) {
        //batch.batch.end();
        batch.batch.flush();
        DisableClip(batch);
        //batch.batch.begin();
    }
    public void DisableClip(DeltaBatch batch) {
        Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
    }

    public void SetDebug(Boolean bYesNo) {
        Debugger = bYesNo;
    }
    /** Screen debugger.
     * NB 1 second = 1 000 000 000 nanoseconds */
    long fLastTime = 0;
    long fAverage = 0;
    int iLength = 100;
    String sDebugInfo;
    BitmapFont fontDebug = new BitmapFont();
    public void CreateDebug() {
        fontDebug.setScale(0.2f*fXunit);
        fontDebug.setColor(0,0,1,1);

    }
    public void DrawDebug(SpriteBatch batch) {
        sDebugInfo = "| " + Gdx.graphics.getFramesPerSecond() + "Fps | " +
                ScreenManager.RectsPerRound + " Rects | " +
                (ResMan.SmallImages - ResMan.SmallImagesPurged) + "/" + (ResMan.SmallImages)  + "S-L"
                + (ResMan.LargeImages - ResMan.LargeImagesPurged) + "/" + (ResMan.LargeImages) + " |";
        fontDebug.setColor(0,0,1,1);
        fontDebug.draw(batch, sDebugInfo,rDisplay.l() + fXunit,rDisplay.t() - fYunit);
        fontDebug.setColor(1,0,0,1);
        fontDebug.draw(batch, sDebugInfo,rDisplay.l() + fXunit,rDisplay.b() + 5*fYunit);
    }

}

enum CycleStage {
    Deactivated, Create, AnimateIn, Draw, AnimateOut, Destroy;
}
class ButtonRect {
    Button button;
    Rect rLayout;

    ButtonRect(Button button, Rect rLayout) {
        this.button = button;
        this.rLayout = rLayout;
    }
    public void UpdatePos(Rect rDisplayP) {
        button.setPosition(rDisplayP.l() + rLayout.l(),rDisplayP.b() + rLayout.b());
        button.setWidth(rLayout.r() - rLayout.l());
        button.setHeight(rLayout.t() - rLayout.b());
    }
}
