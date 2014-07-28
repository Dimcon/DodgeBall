package com.Dimcon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Dodgeball extends ApplicationAdapter {
    public static SplashScreen splash;
    public static GameScreen gamescreen;
    public static OverlayScreen overlay;
    public static MainScreen main;

	@Override
	public void create () {
        Gdx.graphics.setContinuousRendering(true);
        splash = new SplashScreen();
        main = new MainScreen();
        gamescreen = new GameScreen();
        overlay = new OverlayScreen();
        ScreenManager.FirstScreen(splash);
        ScreenManager.AddScreen(main);
        ScreenManager.AddScreen(gamescreen);
        ScreenManager.AddScreen(overlay);
        Rect.setDebug(true);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenManager.Update();
	}
}
