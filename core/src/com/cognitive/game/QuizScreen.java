package com.cognitive.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/*
 * Created by Draco on 2016-11-20.
 */

public class QuizScreen extends ApplicationAdapter implements Screen {


    private CognitiveGame cg; //for set screen purpose
    private Stage stage;
    private OrthographicCamera camera;

    public QuizScreen(CognitiveGame cg){
        this.cg = cg;
    }

    @Override
    public void show() {
        stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        camera = (OrthographicCamera) stage.getCamera();
        camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

        Texture ez_box = new Texture(Gdx.files.internal("easy_treasure.png"));
        Texture hd_box = new Texture(Gdx.files.internal("hard_treasure.png"));
        Texture ez_box_clicked = new Texture(Gdx.files.internal("easy_treasure_clicked.png"));
        Texture hd_box_clicked = new Texture(Gdx.files.internal("hard_treasure_clicked.png"));

        TextureRegion ez_rg = new TextureRegion(ez_box);
        TextureRegion hd_rg = new TextureRegion(hd_box);
        TextureRegion ez_rg_clicked = new TextureRegion(ez_box_clicked);
        TextureRegion hd_rg_clicked = new TextureRegion(hd_box_clicked);

        TextureRegionDrawable ez_d = new TextureRegionDrawable(ez_rg);
        TextureRegionDrawable hd_d = new TextureRegionDrawable(hd_rg);
        TextureRegionDrawable ez_clicked_d = new TextureRegionDrawable(ez_rg_clicked);
        TextureRegionDrawable hd_clicked_d = new TextureRegionDrawable(hd_rg_clicked);

        final ImageButton hard_level = new ImageButton(hd_d, hd_clicked_d);
        ImageButton easy_level = new ImageButton(ez_d, ez_clicked_d);

        hard_level.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                //hard and 6th box, dsst
                if(MainScreen.boxCounter == 6)
                    cg.myGameCallBack.onStartDSSTActivity(cg.player_pos, cg.box_opened, cg.logged);
                else if(MainScreen.boxCounter == 5 || MainScreen.boxCounter == 1)
                    cg.myGameCallBack.onStartQuizActivity(3, cg.player_pos, cg.box_opened, cg.logged);
                else if(MainScreen.boxCounter == 4)
                    cg.myGameCallBack.onStartVisualActivity(5, cg.player_pos, cg.box_opened, cg.logged);
                else
                    cg.myGameCallBack.onStartQuizActivity(3, cg.player_pos, cg.box_opened, cg.logged);
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        easy_level.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.print("**********************");
                System.out.println(MainScreen.boxCounter);

                if(MainScreen.boxCounter == 6)
                    cg.myGameCallBack.onStartDSSTActivity(cg.player_pos, cg.box_opened, cg.logged);
                else if(MainScreen.boxCounter == 5 || MainScreen.boxCounter == 1)
                    cg.myGameCallBack.onStartQuizActivity(2, cg.player_pos, cg.box_opened, cg.logged);
                else if(MainScreen.boxCounter == 4)
                    cg.myGameCallBack.onStartVisualActivity(4, cg.player_pos, cg.box_opened, cg.logged);
                else
                    cg.myGameCallBack.onStartQuizActivity(2, cg.player_pos, cg.box_opened, cg.logged);

                super.touchUp(event, x, y, pointer, button);
            }
        });

        hard_level.setPosition(stage.getWidth() / 2 - hd_box.getWidth(), stage.getHeight() / 2);
        easy_level.setPosition(stage.getWidth() / 2 + ez_box.getWidth(), stage.getHeight() / 2);

        stage.addActor(hard_level);
        stage.addActor(easy_level);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        stage.act();
        stage.draw();

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
