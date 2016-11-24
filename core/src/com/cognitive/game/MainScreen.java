package com.cognitive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

/*
 * Created by Draco on 2016-11-18.
 */

public class MainScreen extends ScreenAdapter {

    //TODO: collision detection
    //TODO: hide on bush

    private Stage stage;
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private Player myActor;
    private CognitiveGame cg;
    public static int boxCounter;

    public MainScreen(CognitiveGame cg){
        this.cg = cg;
    }

    @Override
    public void show() {
        stage = new Stage(new ScalingViewport(Scaling.stretch, 2392, 1440, new OrthographicCamera()));
        camera = (OrthographicCamera) stage.getCamera();
        camera.setToOrtho(false, 1087, 655);
        Gdx.input.setInputProcessor(stage);

        //camera = new OrthographicCamera();
        //camera.setToOrtho(false, myActor.texture.getWidth(), myActor.texture.getHeight());
        //camera.update();
        tiledMap = new TmxMapLoader().load("map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        BarrelRender barrelRender = new BarrelRender(tiledMap, cg.box_opened);
        int[][] barelRegion = barrelRender.getBarrelRegion();

        myActor = new Player(stage, barelRegion, cg.player_pos, cg.box_opened);
        stage.addActor(myActor);
        stage.addActor(myActor.imgButtonL);
        stage.addActor(myActor.imgButtonR);
        stage.addActor(myActor.imgButtonD);
        stage.addActor(myActor.imgButtonU);
        stage.addActor(barrelRender);

        //tiledMapRenderer.setView(camera);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        camera.position.set(myActor.actorX + myActor.texture.getWidth() / 2, myActor.actorY + myActor.texture.getHeight() / 2, 0);
        camera.update();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        if(myActor.ifHitPoint()) {
            cg.player_pos[0] = myActor.actorX;
            cg.player_pos[1] = myActor.actorY;
            cg.box_opened[myActor.which_box] = true;
            boxCounter = myActor.which_box;
            cg.setQuizScreen();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
