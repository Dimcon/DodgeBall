package com.Dimcon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Dodgeball extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    float iUnit;
	Rect rTest;
	@Override
	public void create () {
        iUnit = Gdx.graphics.getHeight() / 100;
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        rTest = new Rect();
        rTest.equals(0,20*iUnit,20*iUnit,0);
        rTest.StartAnimT(20*iUnit,1,2000);
        Gdx.graphics.setContinuousRendering(true);
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
