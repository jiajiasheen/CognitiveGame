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

import java.io.Console;

/*
 * Created by Draco on 2016-11-18.
 */

public class MainScreen extends ScreenAdapter {

    private Stage stage;
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    TestActor myActor;
    CognitiveGame cg;
    //MapLayer objectLayer;
    BarrelRender barrelRender;
    int[][] barelRegion;

    public MainScreen(CognitiveGame cg){
        this.cg = cg;
    }

    @Override
    public void show() {
        stage = new Stage(new ScalingViewport(Scaling.stretch, 2392, 1440, new OrthographicCamera()));
        camera = (OrthographicCamera) stage.getCamera();
        camera.setToOrtho(false, 1196, 720);
        Gdx.input.setInputProcessor(stage);

        //camera = new OrthographicCamera();
        //camera.setToOrtho(false, myActor.texture.getWidth(), myActor.texture.getHeight());
        //camera.update();
        tiledMap = new TmxMapLoader().load("map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        barrelRender = new BarrelRender(tiledMap);
        barelRegion = barrelRender.getBarrelRegion();

        myActor = new TestActor(stage, barelRegion);
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

        if(myActor.ifHitPoint())
            cg.myGameCallBack.onStartVisualActivity();
            //cg.myGameCallBack.onStartQuizActivity();
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
