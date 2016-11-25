package com.cognitive.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class CognitiveGame extends Game {
    public float[] player_pos;
    public boolean[] box_opened;
    public boolean logged;
    private Music music_level1;

    public CognitiveGame(float[] player_pos, boolean[] box_opened, boolean logged){
        this.player_pos = player_pos;
        this.box_opened = box_opened;
        this.logged = logged;
    }

    public  interface MyGameCallBack{
        public void onStartQuizActivity(int n, float[] player_pos, boolean[] box_opened, boolean logged);
        public void onStartVisualActivity(int n, float[] player_pos, boolean[] box_opened, boolean logged);
        public void onStartDSSTActivity(float[] player_pos, boolean[] box_opened, boolean logged);
        public void onStartEndActivity();
    }

    public static MyGameCallBack myGameCallBack;

    public void setMyGameCallBack(MyGameCallBack callBack){
        myGameCallBack = callBack;
    }

    @Override
    public void create() {
        setMainScreen();
        /*
        music_level1 = Gdx.audio.newMusic(Gdx.files.internal("dragon_city.ogg"));
        music_level1.setLooping(true);
        music_level1.play();
        */
    }

    public void setMainScreen(){
        MainScreen mainScreen = new MainScreen(this);
        setScreen(mainScreen);
    }

    public void setQuizScreen(){
        QuizScreen quizScreen = new QuizScreen(this);
        setScreen(quizScreen);
    }
}
