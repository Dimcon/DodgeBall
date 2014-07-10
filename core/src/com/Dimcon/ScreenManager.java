package com.Dimcon;

import java.util.HashMap;

/**
 * Created by daimonsewell on 7/10/14.
 */
public class ScreenManager {
    public HashMap<String,Screen> ScreenStore;

    public void AddScreen(Screen NewScreen) {
        ScreenStore.put(NewScreen.Name,NewScreen);
    }
}
