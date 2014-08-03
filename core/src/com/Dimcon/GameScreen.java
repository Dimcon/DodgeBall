package com.Dimcon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by daimonsewell on 7/18/14.
 */
public class GameScreen extends Screen {
    public GameScreen() {
        Name = "Game";
    }
    public HashMap<Integer,Rect> EnemyStore = new HashMap<Integer, Rect>();
    public float SpawnHeight = 50*fYunit, SpawnWidth = 40*fXunit,MaxWidth = 5*fXunit,MinWidth = 1*fXunit;
    public boolean ClearedStaging = false;

    @Override
    public void BeforeAll(DeltaBatch batch) {
        super.BeforeAll(batch);
    }

    @Override
    public void AfterAll(DeltaBatch batch) {
        super.AfterAll(batch);
        //rDisplay.Draw(ResMan.Get("Screen"),batch.batch);
    }

    @Override
    public Boolean Create(DeltaBatch batch) {
        HandleX = ResMan.GetRect("Handle").TouchedX() - ResMan.GetRect("Handle").l();
        HandleY = ResMan.GetRect("Handle").TouchedY() - ResMan.GetRect("Handle").b();
        rDisplay.Moveup(100*fYunit);
        rDisplay.StartAnimT(rFullscreen,Interpolator.Accelerate,250);
        rDisplay.a = 0;
        OverlayScreen.TrackFinger = true;
        CreateAgain(true);
        SetDebug(true);
        return super.Create(batch);
    }

    @Override
    public Boolean AnimIn(DeltaBatch batch) {
        rDisplay.a = Math.min(rDisplay.a + 0.05f,1f);
        return rDisplay.a() == 1f;
    }

    boolean TouchingHandle = true;
    float HandleX = 0,TouchLeft,TouchBottom;
    float HandleY = 0;
    @Override
    public Boolean Draw(DeltaBatch batch) {
        return super.Draw(batch);
    }

    @Override
    public Boolean AnimOut(DeltaBatch batch) {
        return super.AnimOut(batch);
    }

    @Override
    public Boolean Destroy(DeltaBatch batch) {
        return super.Destroy(batch);
    }

    public void SpawnRow() {
        ClearedStaging = false;
        float Pos = 0;

    }

}

class EnemRect {
    Rect Position;
    Type tp;
     final int
            iShield = new Random().nextInt(100),
            iBomb = new Random().nextInt(100),
            iLife = new Random().nextInt(100);

    EnemRect(Rect position,int type) {
        Position = position;

    }

    public void Draw(SpriteBatch batch) {
        tp.Draw(Position,batch);
    }
}

enum Type {
    Normal,Shield,Bomb,Life;
    public void SetUpType() {
        ResMan.AddImage(Normal.toString(),"Screen","Type");
        ResMan.AddImage(Shield.toString(),"Screen","Type");
        ResMan.AddImage(Bomb.toString(),"Screen","Type");
        ResMan.AddImage(Life.toString(),"Screen","Type");
    }
    public void Draw(Rect r,SpriteBatch batch) {
        r.Draw(ResMan.Get(this.toString()),batch);
    }
}