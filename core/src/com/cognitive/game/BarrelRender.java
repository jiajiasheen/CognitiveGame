package com.cognitive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.scenes.scene2d.Actor;

/*
 * Created by Draco on 2016-11-20.
 * This method is used to find the object position in the tiled map
 */

public class BarrelRender extends Actor{

    private Texture barrel; //the texture for the barrel
    private TextureRegion curBarrel;
    private int FRAME_COLS = 14;
    private int FRAME_ROWS = 30;
    private int[][] barrelRegion;
    private MapLayer objLayer;

    //if the box is opened
    private boolean[] opened;

    public BarrelRender(Map map, boolean[] box_opened){
        this.barrel = new Texture(Gdx.files.internal("items2.png"));
        this.objLayer = map.getLayers().get("objects");
        this.barrelRegion = new int[objLayer.getObjects().getCount()][2];
        this.opened = box_opened;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion[][] tmp = TextureRegion.split(barrel, barrel.getWidth() / FRAME_COLS, barrel.getHeight() / FRAME_ROWS);
        int index = 0;
        for(MapObject obj : objLayer.getObjects()){
            if(!opened[index])
                curBarrel = tmp[FRAME_ROWS - 1][7];
            else
                curBarrel = tmp[FRAME_ROWS - 1][8];

            TiledMapTileMapObject tobj = (TiledMapTileMapObject) obj;
            int x = tobj.getProperties().get("x", Float.class).intValue();
            int y = tobj.getProperties().get("y", Float.class).intValue();

            batch.draw(curBarrel, x, y);
            index++;
        }
    }

    public int[][] getBarrelRegion(){
        int index = 0;
        for(MapObject obj : objLayer.getObjects()){
            TiledMapTileMapObject tobj = (TiledMapTileMapObject) obj;
            barrelRegion[index][0] = tobj.getProperties().get("x", Float.class).intValue();
            //Gdx.app.log("X Axis: ", barrelRegion[index][0] + "");
            barrelRegion[index][1] = tobj.getProperties().get("y", Float.class).intValue();
            //Gdx.app.log("Y Axis: ", barrelRegion[index][1] + "");
            index++;
        }
        return barrelRegion;
    }
}
