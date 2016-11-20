package com.cognitive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/*
 * Created by Draco on 2016-11-18.
 */

public class TestActor extends Actor {

    Stage stage;
    CognitiveGame cg;

    Texture texture = new Texture(Gdx.files.internal("mario.png"));
    Texture buttonL = new Texture(Gdx.files.internal("left.png"));
    Texture buttonR = new Texture(Gdx.files.internal("right.png"));
    Texture buttonD = new Texture(Gdx.files.internal("down.png"));
    Texture buttonU = new Texture(Gdx.files.internal("up.png"));

    ImageButton imgButtonL;
    ImageButton imgButtonR;
    ImageButton imgButtonD;
    ImageButton imgButtonU;
    float actorX = 600, actorY = 600;

    enum STATE{
        Left, Right, Up, Down, Idel
    }

    STATE state = STATE.Idel;

    public TestActor(Stage stage, CognitiveGame cg){
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

        this.stage = stage;
        this.cg = cg;

        TextureRegion tr = new TextureRegion(buttonR);
        TextureRegionDrawable trd = new TextureRegionDrawable(tr);
        imgButtonR = new ImageButton(trd);

        TextureRegion trL = new TextureRegion(buttonL);
        TextureRegionDrawable trdL = new TextureRegionDrawable(trL);
        imgButtonL = new ImageButton(trdL);

        TextureRegion trD = new TextureRegion(buttonD);
        TextureRegionDrawable trdD = new TextureRegionDrawable(trD);
        imgButtonD = new ImageButton(trdD);

        TextureRegion trU = new TextureRegion(buttonU);
        TextureRegionDrawable trdU = new TextureRegionDrawable(trU);
        imgButtonU = new ImageButton(trdU);


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

        imgButtonD.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                state = STATE.Down;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                state = STATE.Idel;
                super.touchUp(event, x, y, pointer, button);
            }
        });

        imgButtonU.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                state = STATE.Up;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                state = STATE.Idel;
                super.touchUp(event, x, y, pointer, button);
            }
        });

        imgButtonL.setPosition(-20, 100);
        imgButtonR.setPosition(280, 100);
        imgButtonD.setPosition(130, -40);
        imgButtonU.setPosition(130, 240);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, actorX, actorY);
    }

    @Override
    public void act(float delta) {
        if(state == STATE.Right && actorX < Gdx.graphics.getWidth() - texture.getWidth()) {
            actorX += 5;
            setButton(5, 0);
        }else if(state == STATE.Left && actorX > 20) {
            actorX -= 5;
            setButton(-5, 0);
        }
        else if(state == STATE.Up) {
            actorY += 5;
            setButton(0, 5);
        }
        else if(state == STATE.Down && actorY > 20) {
            actorY -= 5;
            setButton(0, -5);
        }

        /*
        if(actorX == 650 && actorY == 600){
            stage.dispose();
            cg.setQuizScreen();
        }
        */

    }

    public void setButton(float x, float y){
        imgButtonL.setPosition(imgButtonL.getX() + x, imgButtonL.getY() + y);
        imgButtonR.setPosition(imgButtonR.getX() + x, imgButtonR.getY() + y);
        imgButtonD.setPosition(imgButtonD.getX() + x, imgButtonD.getY() + y);
        imgButtonU.setPosition(imgButtonU.getX() + x, imgButtonU.getY() + y);
    }

    public boolean ifHitPoint(){
        if(actorX > 650 && actorX < 680 && actorY > 600 && actorY < 630)
            return true;
        return false;
    }

}
