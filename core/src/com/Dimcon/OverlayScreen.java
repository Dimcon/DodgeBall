package com.Dimcon;

/**
 * Created by daimonsewell on 7/18/14.
 */
public class OverlayScreen extends Screen {
    public OverlayScreen() {
        Name = "Overlay";
    }

    public Rect rPlayer, rHandle;
    @Override
    public void BeforeAll(DeltaBatch batch) {
        super.BeforeAll(batch);
    }

    @Override
    public void AfterAll(DeltaBatch batch) {
        super.AfterAll(batch);
    }

    @Override
    public Boolean Create(DeltaBatch batch) {
        rPlayer = new Rect(rDisplay, 45*fXunit,(20*fYunit) + (10*fXunit),55*fXunit,(20*fYunit));
        rHandle = new Rect();
        rHandle.RectCopy(rPlayer);
        ResMan.AddRect("Player",rPlayer);
        ResMan.AddRect("Handle",rHandle);
        ResMan.AddImage("Player","badlogic.jpg");
        ResMan.AddImage("Handle","badlogic.jpg");
        return super.Create(batch);
    }

    @Override
    public Boolean AnimIn(DeltaBatch batch) {
        ResMan.GetRect("Player").Update(rDisplay, 45*fXunit,(5*fYunit) + (10*fXunit),55*fXunit,(5*fYunit));
        ResMan.GetRect("Handle").RectCopy(rPlayer);
        ResMan.GetRect("Player").Draw(ResMan.Get("Player"),batch.batch,fAlpha);
        ResMan.GetRect("Handle").Draw(ResMan.Get("Handle"),batch.batch,fAlpha);
        return super.AnimIn(batch);
    }

    @Override
    public Boolean Draw(DeltaBatch batch) {
        ResMan.GetRect("Player").Update(rDisplay, 45*fXunit,(5*fYunit) + (10*fXunit),55*fXunit,(5*fYunit));
        ResMan.GetRect("Player").Draw(ResMan.Get("Player"),batch.batch,fAlpha);
        BeginClip(batch);
        ResMan.GetRect("Handle").Draw(ResMan.Get("Handle"),batch.batch,fAlpha);
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
}
