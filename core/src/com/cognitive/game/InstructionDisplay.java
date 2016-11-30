package com.cognitive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Draco on 2016-11-29.
 */

public class InstructionDisplay extends Actor {

    private BitmapFont font;

    public InstructionDisplay(){
        font = new BitmapFont(Gdx.files.internal("fonts/text.fnt"));
        font.getData().setScale(0.5f);
        Gdx.app.log("====BITMAPFONT: ", font.getXHeight() + "");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, "CLICK ME!", 625, 850);
    }
}
