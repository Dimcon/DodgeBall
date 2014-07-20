package com.Dimcon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.HashMap;

/**
 * Created by daimonsewell on 7/10/14.
 */
public class ScreenManager {
    static HashMap<String,Screen> ScreenStore = new HashMap<String, Screen>();
    long Now = 000000000;
    final long
                FramesPerSecond = 60,
                UpdateRate = 100000000/FramesPerSecond;
    static DeltaBatch batch = new DeltaBatch();
    static ResMan ResMan = new ResMan();
    static SpriteBatch Sbatch = new SpriteBatch();

    ScreenManager(Screen First) {
        Gdx.input.setInputProcessor(batch.toucher);
        First.stage = CycleStage.Create;
        AddScreen(First);
        //Gdx.input.setInputProcessor(batch.DrawStage);
        batch.batch = Sbatch;
    }

    public void AddScreen(Screen NewScreen) {
        ScreenStore.put(NewScreen.Name,NewScreen);
    }
    public void RemoveScreen(String sName) {
        ScreenStore.remove(sName);
    }

    public void StartScreen(String sScreen) {
        ScreenStore.get(sScreen).stage = CycleStage .Create;
    }
    public void EndScreen(String sScreen) {
        ScreenStore.get(sScreen).stage = CycleStage .AnimateOut;
    }
    public void KillScreen(String sScreen) {
        ScreenStore.get(sScreen).stage = CycleStage .Destroy;
    }

    public void Update() {
        batch.batch.begin();
        batch.Delta = UpdateRate/(System.nanoTime() - Now);
        Now = System.nanoTime();
        //////// Iterate through each screen.
        for (String key : ScreenStore.keySet()) {
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
        }//////////////////////////////////

        batch.batch.end();
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
