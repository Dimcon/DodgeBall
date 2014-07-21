package com.Dimcon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

import java.util.HashMap;

/**
 * Created by daimonsewell on 7/10/14.
 */
public class ScreenManager {
    static HashMap<String,Screen> ScreenStore = new HashMap<String, Screen>();
    static long Now = 000000000;
    static final long
                FramesPerSecond = 60,
                UpdateRate = 100000000/FramesPerSecond;
    static DeltaBatch batch = new DeltaBatch();
    static ResMan ResMan = new ResMan();
    static SpriteBatch Sbatch = new SpriteBatch();

    public static void FirstScreen(Screen First) {
        Gdx.input.setInputProcessor(batch.toucher);
        First.stage = CycleStage.Create;
        AddScreen(First);
        //Gdx.input.setInputProcessor(batch.DrawStage);
        batch.batch = Sbatch;
    }

    public static void AddScreen(Screen NewScreen) {
        ScreenStore.put(NewScreen.Name,NewScreen);
    }
    public  static void RemoveScreen(String sName) {
        ScreenStore.remove(sName);
    }

    public  static void StartScreen(String sScreen) {
        ScreenStore.get(sScreen).stage = CycleStage .Create;
    }
    public  static void EndScreen(String sScreen) {
        ScreenStore.get(sScreen).stage = CycleStage .AnimateOut;
    }
    public  static void KillScreen(String sScreen) {
        ScreenStore.get(sScreen).stage = CycleStage .Destroy;
    }

    public static void Update() {

        batch.Delta = UpdateRate/(System.nanoTime() - Now);
        Now = System.nanoTime();
        //////// Iterate through each screen.

        for (String key : ScreenStore.keySet()) {
            /** Allow Clipping all drawing to rDisplay */
            batch.batch.begin();
            if (ScreenStore.get(key).ClipToRDisplay) {
                ScreenStore.get(key).BeginClip(batch);
                ScreenStore.get(key).ClipRect(ScreenStore.get(key).rDisplay);
            }
            /** NB NB NB NB NB
             * Multiple batch sessions in a single frame may decrease performance dramatically. */
            switch (ScreenStore.get(key).stage) {
                case Deactivated: default:
                    break;
                case Create:
                    if (ScreenStore.get(key).Create(batch))
                    {ScreenStore.get(key).stage = CycleStage .AnimateIn;}
                    break;
                case AnimateIn:
                    ScreenStore.get(key).BeforeAll(batch);
                    if (ScreenStore.get(key).AnimIn(batch))
                    {ScreenStore.get(key).stage = CycleStage .Draw;} else
                    ScreenStore.get(key).AfterAll(batch);
                    break;
                case Draw:
                    ScreenStore.get(key).BeforeAll(batch);
                    if (ScreenStore.get(key).Draw(batch))
                    {ScreenStore.get(key).stage = CycleStage .AnimateOut;}else
                    ScreenStore.get(key).AfterAll(batch);
                    break;
                case AnimateOut:
                    ScreenStore.get(key).BeforeAll(batch);
                    if (ScreenStore.get(key).AnimOut(batch))
                    {ScreenStore.get(key).stage = CycleStage .Destroy;} else
                    ScreenStore.get(key).AfterAll(batch);
                    break;
                case Destroy:
                    if (ScreenStore.get(key).Destroy(batch))
                    {ScreenStore.get(key).stage = CycleStage .Deactivated;}
                    break;
            }
            ScreenStore.get(key).ResetUnits();
            for (String key2 : ScreenStore.get(key).ButtonStore.keySet()) {
                ScreenStore.get(key).ButtonStore.get(key2).UpdatePos(ScreenStore.get(key).rDisplay);
            }
            batch.batch.end();
            if (ScreenStore.get(key).ClipToRDisplay) {
                ScreenStore.get(key).DisableClip(batch);
            }
        }//////////////////////////////////


        batch.DrawStage.act();
        batch.DrawStage.draw();

    }
}

class DeltaBatch {
    SpriteBatch batch;
    float Delta;
    com.badlogic.gdx.scenes.scene2d.Stage DrawStage = new Stage();
    TouchHandler toucher = new TouchHandler();
}
