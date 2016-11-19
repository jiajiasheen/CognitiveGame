package com.cognitive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Draco on 2016-11-18.
 */

public class TestActor extends Actor {

    Texture texture = new Texture(Gdx.files.internal("mario.png"));
    Texture buttonL = new Texture(Gdx.files.internal("buttonL.png"));
    Texture buttonR = new Texture(Gdx.files.internal("buttonR.png"));
    ImageButton imgButtonL;
    ImageButton imgButtonR;
    float actorX = 600, actorY = 600;

    enum STATE{
        Left, Right, Idel
    }

    STATE state = STATE.Idel;

    public TestActor(){
        /*
        setBounds(actorX, actorY, texture.getWidth(), texture.getHeight());
        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ((TestActor)event.getTarget()).started = true;
                return true;
            }
        });
        */

        TextureRegion tr = new TextureRegion(buttonR);
        TextureRegionDrawable trd = new TextureRegionDrawable(tr);
        imgButtonR = new ImageButton(trd);

        TextureRegion trL = new TextureRegion(buttonL);
        TextureRegionDrawable trdL = new TextureRegionDrawable(trL);
        imgButtonL = new ImageButton(trdL);


        imgButtonR.addListener(new InputListener(){

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                state = STATE.Idel;
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                state = STATE.Right;
                return true;
            }
        });

        imgButtonL.addListener(new InputListener(){

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                state = STATE.Idel;
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                state = STATE.Left;
                return true;
            }
        });

        imgButtonL.setPosition(0, 0);
        imgButtonR.setPosition(300, 0);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, actorX, actorY);
        //batch.draw(buttonL, 300, 0);
        //batch.draw(buttonR, 0, 0);
    }

    @Override
    public void act(float delta) {
        if(state == STATE.Right) {
            actorX += 10;
        }else if(state == STATE.Left)
            actorX -= 10;
    }

}
