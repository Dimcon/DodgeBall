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

public class Dodgeball extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
    static ScreenManager Scrnman;
    static SplashScreen splash;
    static MainScreen main;
    public static TouchHandler toucher;

	@Override
	public void create () {
		batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);
        Gdx.graphics.setContinuousRendering(true);
        toucher = new TouchHandler();
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
        Scrnman.Update(batch);
		batch.end();
        if (main.DrawActors) {
            main.stage.act();
            main.stage.draw();
        }
	}

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        toucher.TouchDown(screenX,screenY,pointer);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        toucher.TouchUp(screenX,screenY,pointer);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        toucher.TouchMoved(screenX,screenY,pointer);
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
