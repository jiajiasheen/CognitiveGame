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
    private String text;
    private float posX;
    private float posY;

    public InstructionDisplay(String style, float scale, String text, float posX, float posY){
        font = new BitmapFont(Gdx.files.internal("fonts/" + style + ".fnt"));
        font.getData().setScale(scale);
        this.text = text;
        this.posX = posX;
        this.posY = posY;
        //Gdx.app.log("====BITMAPFONT: ", font.getXHeight() + "");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, text, posX, posY);
        //625 850
    }
}
