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
	Texture img;
    float iUnit;
	Rect rTest;
    Button btnTouch;
    Drawable drwble;

	@Override
	public void create () {
        iUnit = Gdx.graphics.getHeight() / 100;
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        rTest = new Rect(0,20*iUnit,20*iUnit,0);
        rTest.StartAnimT(new Rect(0,100*iUnit,100*iUnit,0), 1, 1000);
        rTest.setAlpha(0);
        rTest.StartAnimA(1f, 1, 4000);
        Gdx.graphics.setContinuousRendering(true);
        /*btnTouch = new ImageTextButton(img);*/
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        rTest.Draw(img,batch);
        rTest.Animate();
		batch.end();
	}
}
