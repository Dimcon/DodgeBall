package com.Dimcon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Dodgeball extends ApplicationAdapter {
	SpriteBatch batch;
    static ScreenManager Scrnman;
    static SplashScreen splash;
    static MainScreen main;

	@Override
	public void create () {
		batch = new SpriteBatch();
        Gdx.graphics.setContinuousRendering(true);
        splash = new SplashScreen();
        main = new MainScreen();
        Scrnman = new ScreenManager();
        Scrnman.AddScreen(splash);
        Scrnman.AddScreen(main);
        Scrnman.ScreenStore.get("Splash").stage = Stage.Create;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        //rTest.Draw(img,batch);
        Scrnman.Update(batch);
		batch.end();
	}
}
