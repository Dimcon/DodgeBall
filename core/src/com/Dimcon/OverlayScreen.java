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
        ResourceMan.AddImage("Player","badlogic.jpg");
        ResourceMan.AddImage("Handle","badlogic.jpg");
        return super.Create(batch);
    }

    @Override
    public Boolean AnimIn(DeltaBatch batch) {
        rPlayer.Update(rDisplay, 45*fXunit,(20*fYunit) + (10*fXunit),55*fXunit,(20*fYunit));
        rHandle.RectCopy(rPlayer);
        rPlayer.Draw(ResourceMan.Get("Player"),batch.batch,fAlpha);
        rHandle.Draw(ResourceMan.Get("Handle"),batch.batch,fAlpha);
        return super.AnimIn(batch);
    }

    @Override
    public Boolean Draw(DeltaBatch batch) {
        rPlayer.Update(rDisplay, 45*fXunit,(20*fYunit) + (10*fXunit),55*fXunit,(20*fYunit));
        rPlayer.Draw(ResourceMan.Get("Player"),batch.batch,fAlpha);
        rHandle.Draw(ResourceMan.Get("Handle"),batch.batch,fAlpha);
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
