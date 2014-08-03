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
    static SpriteBatch Sbatch = new SpriteBatch();
    static int RectsPerRound = 0,SmallImagesCalled,LargeImagesCalled;

    public static void FirstScreen(Screen First) {
        Gdx.input.setInputProcessor(batch.toucher);
        First.stage = CycleStage.Create;
        AddScreen(First);
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
        Rect.RectsDrawn = 0;
        batch.Delta = UpdateRate/(System.nanoTime() - Now);
        Now = System.nanoTime();
        com.Dimcon.ResMan.ResetUsed();
        //////// Iterate through each screen.

        for (String key : ScreenStore.keySet()) {
            /** Allow Clipping all drawing to rDisplay */
            batch.batch.begin();
            Screen LocalScreen = ScreenStore.get(key);
            if (LocalScreen.ClipToRDisplay) {
                LocalScreen.BeginClip(batch);
                LocalScreen.ClipRect(ScreenStore.get(key).rDisplay);
            }
            batch.batch.setColor(batch.batch.getColor().r,batch.batch.getColor().g,batch.batch.getColor().b,LocalScreen.rDisplay.a);
            /** NB NB NB NB NB
             * Multiple batch sessions in a single frame may decrease performance dramatically. */
            switch (LocalScreen.stage) {
                case Deactivated: default:
                    break;
                case Create:
                    if (!LocalScreen.Created || LocalScreen.CreateAgain) {
                        if (LocalScreen.Create(batch)) {
                            LocalScreen.stage = CycleStage.AnimateIn;
                        }
                        LocalScreen.Created = true;
                    }
                    if (LocalScreen.Debugger) {
                        LocalScreen.CreateDebug();
                    }
                    break;
                case AnimateIn:
                    LocalScreen.BeforeAll(batch);
                    if (LocalScreen.AnimIn(batch))
                    {LocalScreen.stage = CycleStage .Draw;} else
                        LocalScreen.AfterAll(batch);
                    break;
                case Draw:
                    LocalScreen.BeforeAll(batch);
                    if (LocalScreen.Draw(batch))
                    {LocalScreen.stage = CycleStage .AnimateOut;}else
                        LocalScreen.AfterAll(batch);
                    break;
                case AnimateOut:
                    LocalScreen.BeforeAll(batch);
                    if (LocalScreen.AnimOut(batch))
                    {LocalScreen.stage = CycleStage .Destroy;} else
                        LocalScreen.AfterAll(batch);
                    break;
                case Destroy:
                    if (LocalScreen.Destroy(batch))
                    {LocalScreen.stage = CycleStage .Deactivated;}
                    break;
            }
            LocalScreen.ResetUnits();
            for (String key2 : LocalScreen.ButtonStore.keySet()) {
                LocalScreen.ButtonStore.get(key2).UpdatePos(LocalScreen.rDisplay);
            }
            if (LocalScreen.Debugger && LocalScreen.stage != CycleStage.Deactivated) {
                LocalScreen.DrawDebug(batch.batch);
            }

            batch.batch.end();
            if (LocalScreen.ClipToRDisplay) {
                LocalScreen.DisableClip(batch);
            }
        }//////////////////////////////////

        LargeImagesCalled = ResMan.getUsedLarge();
        SmallImagesCalled = ResMan.getUsedSmall();
        RectsPerRound = Rect.RectsDrawn;
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
