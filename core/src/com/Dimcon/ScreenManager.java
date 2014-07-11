package com.Dimcon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;

/**
 * Created by daimonsewell on 7/10/14.
 */
public class ScreenManager {
    public HashMap<String,Screen> ScreenStore = new HashMap<String, Screen>();
    private long Now = 000000000;
    private final long
                FramesPerSecond = 60,
                UpdateRate = 100000000/FramesPerSecond;
    public static DeltaBatch batch = new DeltaBatch();

    public void AddScreen(Screen NewScreen) {
        ScreenStore.put(NewScreen.Name,NewScreen);
    }
    public void RemoveScreen(String sName) {
        ScreenStore.remove(sName);
    }
    public void Update(SpriteBatch batchP) {
        batch.Delta = UpdateRate/(System.nanoTime() - Now);
        Now = System.nanoTime();
        batch.batch = batchP;
        //////// Iterate through each screen.
        for (String key : ScreenStore.keySet()) {
            switch (ScreenStore.get(key).stage) {
                case Deactivated: default:
                    break;
                case Create:
                    if (ScreenStore.get(key).Create())
                    {ScreenStore.get(key).stage = Stage.AnimateIn;};
                    break;
                case AnimateIn:
                    if (ScreenStore.get(key).AnimIn(batch))
                    {ScreenStore.get(key).stage = Stage.Draw;}
                    break;
                case Draw:
                    if (ScreenStore.get(key).Draw(batch))
                    {ScreenStore.get(key).stage = Stage.AnimateOut;}
                    break;
                case AnimateOut:
                    if (ScreenStore.get(key).AnimOut(batch))
                    {ScreenStore.get(key).stage = Stage.Destroy;}
                    break;
                case Destroy:
                    if (ScreenStore.get(key).Destroy())
                    {ScreenStore.get(key).stage = Stage.Deactivated;}
                    break;
            }
            ScreenStore.get(key).ResetUnits();
        }//////////////////////////////////
    }

}

class DeltaBatch {
    SpriteBatch batch;
    float Delta;
}
