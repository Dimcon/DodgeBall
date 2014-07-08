package com.Dimcon;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by daimonsewell on 6/6/14.
 * Uses LibGDX Textures
 */
public class ButtonCollection {
    public BitmapFont.TextBounds tb;
}

/*class Button {
    private Boolean hasIcon,enabled;
    private Rect rPosition = new Rect(),
                 rIcon = new Rect();
    private Texture imgBackground,imgIcon;
    private String sLabel;
    private void DefStartup(String imgBKGPath, String sLabelP,Rect rPos) {
        imgBackground = new Texture(imgBKGPath);
        rPosition.RectCopy(rPos);
        sLabel = sLabelP;
    }

    Button(String simgBKGPath, String sLabel,Rect rPos) {
        hasIcon = false;
        DefStartup(simgBKGPath,sLabel,rPos);
    }
    Button(String simgBKGPath,String simgIconPath, float fIconPadding, String sLabelP,Rect rPos) {
        hasIcon = true;
        rIcon.CopySquare(rPos,fIconPadding);
        rIcon.MoveLeft((rPosition.width()/2) - (rIcon.width()/2) - fIconPadding);
        imgIcon = new Texture(simgIconPath);
        DefStartup(simgBKGPath,sLabelP,rPos);
    }
    public void DrawBtn(SpriteBatch batch,BitmapFont fnt,float fTextSize,BitmapFont.TextBounds tb) {
        rPosition.Draw(imgBackground,batch);
        Color prevCol = batch.getColor();
        batch.setColor(1,1,1,1);
        if (hasIcon) {
            fnt.getBounds(sLabel,0,5,tb);
            fnt.draw(batch,sLabel,rPosition.l + (rIcon.width()/2) + (((rPosition.r - rPosition.l) - (tb.width))/2), rPosition.t - (((rPosition.t - rPosition.b) - (tb.height))/2));
            rIcon.Draw(imgIcon,batch);
        } else {
            fnt.getBounds(sLabel,0,5,tb);
            fnt.draw(batch,sLabel,rPosition.l + (((rPosition.r - rPosition.l) - (tb.width))/2), rPosition.t - (((rPosition.t - rPosition.b) - (tb.height))/2));
        }
        batch.setColor(prevCol);
    }
}*/
