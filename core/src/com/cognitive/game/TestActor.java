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

    //initialize actor and buttons
    Texture texture = new Texture(Gdx.files.internal("walk_arisu.png"));
    Texture buttonL = new Texture(Gdx.files.internal("left.png"));
    Texture buttonR = new Texture(Gdx.files.internal("right.png"));
    Texture buttonD = new Texture(Gdx.files.internal("down.png"));
    Texture buttonU = new Texture(Gdx.files.internal("up.png"));

    //define image buttons
    //TODO: try to use touchPad instead of imageButton
    ImageButton imgButtonL;
    ImageButton imgButtonR;
    ImageButton imgButtonD;
    ImageButton imgButtonU;

    //actor original spot
    float actorX = 600, actorY = 600;

    //walking state
    private enum STATE{
        Left, Right, Up, Down, Idel
    }

    //initial state
    private STATE state = STATE.Idel;

    //get objects region from main screen
    private int[][] barelRegion;

    //actor frames
    private int FRAME_COLS = 3;
    private int FRAME_ROWS = 4;
    TextureRegion[] walkFrames;
    //Animation walkAnimation;
    //float stateTime;
    TextureRegion currentFrame;
    private static int step = 0; //store the current step in the animation frame
    private static int face_dir = 0; //0 is down, 1 is up, 2 is left, 3 is right

    public TestActor(Stage stage, int[][] barelRegion){

        this.stage = stage;
        this.barelRegion = barelRegion;

        //split actor texture
        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() / FRAME_COLS, texture.getHeight() / FRAME_ROWS);
        walkFrames = new TextureRegion[FRAME_ROWS * FRAME_COLS];

        //store each action pic into an array
        int index = 0;
        for(int i = 0;i < FRAME_ROWS;i++){
            for(int k = 0;k < FRAME_COLS;k++)
                walkFrames[index++] = tmp[i][k];
        }

        //walkAnimation = new Animation(0.025f, walkFrames);
        //stateTime = 0f;

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

        imgButtonL.setPosition(100, 470);
        imgButtonR.setPosition(250, 470);
        imgButtonD.setPosition(175, 400);
        imgButtonU.setPosition(175, 540);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        currentFrame = walkFrames[face_dir * 3 + step];
        batch.draw(currentFrame, actorX, actorY);
    }

    @Override
    public void act(float delta) {
        if(state == STATE.Right && actorX < Gdx.graphics.getWidth() - currentFrame.getRegionWidth()) {
            animationSet(3, face_dir);
            actorX += 5;
            setButton(5, 0);
        }else if(state == STATE.Left && actorX > 20) {
            animationSet(2, face_dir);
            actorX -= 5;
            setButton(-5, 0);
        }
        else if(state == STATE.Up && actorY < 3840 - currentFrame.getRegionHeight()) {
            animationSet(1, face_dir);
            actorY += 5;
            setButton(0, 5);
        }
        else if(state == STATE.Down && actorY > 20) {
            animationSet(0, face_dir);
            actorY -= 5;
            setButton(0, -5);
        }

    }

    private void setButton(float x, float y){
        imgButtonL.setPosition(imgButtonL.getX() + x, imgButtonL.getY() + y);
        imgButtonR.setPosition(imgButtonR.getX() + x, imgButtonR.getY() + y);
        imgButtonD.setPosition(imgButtonD.getX() + x, imgButtonD.getY() + y);
        imgButtonU.setPosition(imgButtonU.getX() + x, imgButtonU.getY() + y);
    }

    public boolean ifHitPoint(){
        for(int i = 0;i < barelRegion.length;i++){
            int x = barelRegion[i][0];
            int y = barelRegion[i][1];
            if((actorX > x - 34) && (actorX < x + 5) && (actorY > y - 5) && (actorY < y + 34))
                return true;
        }
        return false;
    }

    private void animationSet(int faceState, int face){
        if(faceState != face) {
            face_dir = faceState;
            step = 0;
        }else{
            if(step < 2)
                step++;
            else
                step = 0;
        }
    }

}
