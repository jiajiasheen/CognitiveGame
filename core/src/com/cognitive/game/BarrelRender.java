package com.cognitive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
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

    private Texture barrel;
    private int[][] barrelRegion;
    private MapLayer objLayer;

    public BarrelRender(Map map){
        this.barrel = new Texture(Gdx.files.internal("mario.png"));
        this.objLayer = map.getLayers().get("objects");
        this.barrelRegion = new int[objLayer.getObjects().getCount()][2];
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for(MapObject obj : objLayer.getObjects()){
            TiledMapTileMapObject tobj = (TiledMapTileMapObject) obj;
            int x = tobj.getProperties().get("x", Float.class).intValue();
            int y = tobj.getProperties().get("y", Float.class).intValue();
            batch.draw(barrel, x, y);
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
