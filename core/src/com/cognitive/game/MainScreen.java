package com.cognitive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import java.util.List;

/*
 * Created by Draco on 2016-11-18.
 */

public class MainScreen extends ScreenAdapter {

    //TODO: FUTURE WORK hide on bush

    private Stage stage;
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private Player myActor;
    private CognitiveGame cg;
    public static int boxCounter;

    //for dialog and fonts
    public Window dialogWindow;
    public BitmapFont font;

    public MainScreen(CognitiveGame cg){
        this.cg = cg;
    }

    //barriers regions
    private boolean[][] barriers;

    @Override
    public void show() {
        stage = new Stage(new ScalingViewport(Scaling.stretch, 2392, 1440, new OrthographicCamera()));
        camera = (OrthographicCamera) stage.getCamera();
        camera.setToOrtho(false, 1087, 655);

        barriers = new boolean[50][80];

        InstructionDisplay instr = new InstructionDisplay();

        //setWindow();

        Gdx.input.setInputProcessor(stage);

        //camera = new OrthographicCamera();
        //camera.setToOrtho(false, myActor.texture.getWidth(), myActor.texture.getHeight());
        //camera.update();
        tiledMap = new TmxMapLoader().load("map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        BarrelRender barrelRender = new BarrelRender(tiledMap, cg.box_opened);
        int[][] barelRegion = barrelRender.getBarrelRegion();

        //set barriers
        setBarriers();

        //set guard button
        Texture guardPNG = new Texture(Gdx.files.internal("guard.png"));
        TextureRegion guardRegion = TextureRegion.split(guardPNG, guardPNG.getWidth() / 12, guardPNG.getHeight() / 8)[0][1];
        ImageButton guard = new ImageButton(new TextureRegionDrawable(guardRegion));
        guard.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                cg.myGameCallBack.onStartInstructionActivity(cg.player_pos, cg.box_opened, cg.logged);
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        guard.setPosition(670, 750);

        myActor = new Player(stage, barelRegion, cg.player_pos, cg.box_opened, barriers);
        stage.addActor(barrelRender);
        stage.addActor(myActor);
        stage.addActor(guard);
        stage.addActor(instr);
        stage.addActor(myActor.imgButtonL);
        stage.addActor(myActor.imgButtonR);
        stage.addActor(myActor.imgButtonD);
        stage.addActor(myActor.imgButtonU);
        //stage.addActor(dialogWindow);

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

        if(myActor.ifHitNPC() == 1){
            Gdx.app.log("==Hitted: ", "NPC hitted");
        }

        if(myActor.ifHitNPC() == 2) {
            if(myActor.diamondN == 4)
                cg.myGameCallBack.onStartEndActivity();
        }

        if(myActor.ifHitPoint()) {
            cg.player_pos[0] = myActor.actorX;
            cg.player_pos[1] = myActor.actorY;
            cg.box_opened[myActor.which_box] = true;
            boxCounter = myActor.which_box;
            Gdx.app.log("====Box Number: ", boxCounter + "");
            cg.setQuizScreen();
        }
    }

    // TODO: FUTURE WORK set pop up dialog
    private void setWindow(){
        TextureRegionDrawable WindowDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("badlogic.jpg"))));
        Window.WindowStyle style = new Window.WindowStyle(font, Color.WHITE, WindowDrawable);
        dialogWindow = new Window("Game", style);
        dialogWindow.setWidth(Gdx.graphics.getWidth()/5.0f);
        dialogWindow.setHeight(Gdx.graphics.getHeight()/5.0f);

        dialogWindow.setPosition(100,80);

        dialogWindow.setMovable(true);
    }

    //check all the cells if there are obstacles in them
    private void setBarriers(){
        TiledMapTileLayer obs1 = (TiledMapTileLayer) tiledMap.getLayers().get("obstacle");
        TiledMapTileLayer obs2 = (TiledMapTileLayer) tiledMap.getLayers().get("obstacle2");

        for(int i = 0;i < 50;i++){
            for(int j = 0;j < 80;j++){
                if(obs1.getCell(i, j) != null)
                    barriers[i][j] = true;
                if(obs2.getCell(i,j) != null)
                    barriers[i][j] = true;
            }
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
