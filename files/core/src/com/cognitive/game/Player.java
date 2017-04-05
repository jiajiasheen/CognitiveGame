package com.cognitive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

public class Player extends Actor {

    Stage stage;

    //initialize actor and buttons
    Texture texture = new Texture(Gdx.files.internal("walk_arisu.png"));
    Texture buttonL = new Texture(Gdx.files.internal("left.png"));
    Texture buttonR = new Texture(Gdx.files.internal("right.png"));
    Texture buttonD = new Texture(Gdx.files.internal("down.png"));
    Texture buttonU = new Texture(Gdx.files.internal("up.png"));
    Texture di_img = new Texture(Gdx.files.internal("diamond.png"));

    //define image buttons
    //TODO: try to use touchPad instead of imageButton
    ImageButton imgButtonL;
    ImageButton imgButtonR;
    ImageButton imgButtonD;
    ImageButton imgButtonU;

    //boolean values for direction moving
    private boolean right = true;
    private boolean left = true;
    private boolean down = true;
    private boolean up = true;

    //actor original spot
    float actorX, actorY;

    //walking state
    private enum STATE{
        Left, Right, Up, Down, Idel
    }

    //initial state
    private STATE state = STATE.Idel;
    private STATE lastState = STATE.Idel;

    //get objects region from main screen
    private int[][] barelRegion;

    //actor frames
    private int FRAME_COLS = 3;
    private int FRAME_ROWS = 4;
    private int ITEM_FRAME_COLS = 14;
    private int ITEM_FRAME_ROWS = 30;

    private TextureRegion diamond;
    private TextureRegion gold;


    public int diamondN;

    private TextureRegion[] walkFrames;
    //Animation walkAnimation;
    //float stateTime;
    private TextureRegion currentFrame;
    private static int step = 0; //store the current step in the animation frame
    private static int face_dir = 0; //0 is down, 1 is up, 2 is left, 3 is right

    //locate which box is hitted
    public int which_box;
    private boolean[] box_opened;
    private boolean[][] barriers;

    private BitmapFont font;

    private int coins; //store the score

    public Player(Stage stage, int[][] barelRegion, float[] player_pos, boolean[] box_opened, boolean[][] barriers, int coins){

        this.stage = stage;
        this.barelRegion = barelRegion;
        this.actorX = player_pos[0];
        this.actorY = player_pos[1];
        this.box_opened = box_opened;
        this.barriers = barriers; //get obstacles on the map
        this.coins = coins;
        diamondN = 0;

        for(int i = 0;i < box_opened.length;i++){
            if(box_opened[i])
                diamondN++;
        }

        //Diamond
        diamond = TextureRegion.split(di_img, di_img.getWidth() / ITEM_FRAME_COLS, di_img.getHeight() / ITEM_FRAME_ROWS)[15][1];

        //Gold
        gold = TextureRegion.split(di_img, di_img.getWidth() / ITEM_FRAME_COLS, di_img.getHeight() / ITEM_FRAME_ROWS)[15][7];

        font = new BitmapFont(Gdx.files.internal("fonts/text.fnt"));
        font.getData().setScale(0.5f);

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
                lastState = state;
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
                lastState = state;
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
                lastState = state;
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
                lastState = state;
                state = STATE.Idel;
                super.touchUp(event, x, y, pointer, button);
            }
        });

        imgButtonL.setPosition(actorX - 500, actorY - 130);
        imgButtonR.setPosition(actorX - 350, actorY - 130);
        imgButtonD.setPosition(actorX - 425, actorY - 200);
        imgButtonU.setPosition(actorX - 425, actorY - 60);


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        currentFrame = walkFrames[face_dir * 3 + step];
        batch.draw(currentFrame, actorX, actorY);
        drawDiamond(batch);
        drawGold(batch);
    }

    @Override
    public void act(float delta) {
        checkMovable(actorX, actorY);

        //Gdx.app.log("=====Moving States:", "last state: " + lastState + " " + "current state: " + state);

        if(state == STATE.Right && actorX < Gdx.graphics.getWidth() - currentFrame.getRegionWidth() && right) {
            lastState = STATE.Right;
            animationSet(3, face_dir);
            actorX += 5;
            setButton(5, 0);
        }else if(state == STATE.Left && actorX > 20 && left) {
            lastState = STATE.Left;
            animationSet(2, face_dir);
            actorX -= 5;
            setButton(-5, 0);
        }
        else if(state == STATE.Up && actorY < 3840 - currentFrame.getRegionHeight() && up) {
            lastState = STATE.Up;
            animationSet(1, face_dir);
            actorY += 5;
            setButton(0, 5);
        }
        else if(state == STATE.Down && actorY > 20 && down) {
            lastState = STATE.Down;
            animationSet(0, face_dir);
            actorY -= 5;
            setButton(0, -5);
        }
        stateReset();
        //Gdx.app.log("===Actor position: ", actorX + " " + actorY);
    }

    private void setButton(float x, float y){
        imgButtonL.setPosition(imgButtonL.getX() + x, imgButtonL.getY() + y);
        imgButtonR.setPosition(imgButtonR.getX() + x, imgButtonR.getY() + y);
        imgButtonD.setPosition(imgButtonD.getX() + x, imgButtonD.getY() + y);
        imgButtonU.setPosition(imgButtonU.getX() + x, imgButtonU.getY() + y);
    }

    public int ifHitNPC(){
        if(actorX > 660 && actorX < 680 && actorY < 750 && actorY > 720)
            return 1;
        else if(actorX > 1130 && actorX < 1360 && actorY < 1660 && actorY > 1620)
            return 2;
        return 0;
    }

    public boolean ifHitPoint(){
        for(int i = 0;i < barelRegion.length;i++){
            if(box_opened[i]) continue;
            int x = barelRegion[i][0];
            int y = barelRegion[i][1];
            if((actorX > x - 34) && (actorX < x + 5) && (actorY > y - 5) && (actorY < y + 34)) {
                which_box = i;
                return true;
            }
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

    private void drawDiamond(Batch batch){
        for(int i = 0;i < diamondN;i++){
            batch.draw(diamond, actorX + 550 - i * 45 , actorY - 200);
        }
    }

    private void drawGold(Batch batch){
        batch.draw(gold, actorX - 460, actorY + 390);
        font.draw(batch, "X " + coins, actorX - 400, actorY + 430);
    }

    private void checkMovable(float x, float y){
        if(barriers[(int) x / 48][(int) y / 48]){
            if(state == STATE.Right && lastState == STATE.Right)
                right = false;
            else if(state == STATE.Left && lastState == STATE.Left)
                left = false;
            else if(state == STATE.Up && lastState == STATE.Up)
                up = false;
            else if(state == STATE.Down && lastState == STATE.Down)
                down = false;
        }
    }

    private void stateReset(){
        right = true;
        left = true;
        up = true;
        down = true;
    }
}
