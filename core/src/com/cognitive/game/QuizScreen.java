package com.cognitive.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/*
 * Created by Draco on 2016-11-20.
 */

public class QuizScreen extends ApplicationAdapter implements Screen {

    CognitiveGame cg;

    public QuizScreen(CognitiveGame cg){
        this.cg = cg;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void hide() {

    }
}
