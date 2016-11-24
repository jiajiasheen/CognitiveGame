package com.cognitive.game;

import com.badlogic.gdx.Game;

public class CognitiveGame extends Game {
    public float[] player_pos;
    public boolean[] box_opened;
    public boolean logged;

    public CognitiveGame(float[] player_pos, boolean[] box_opened, boolean logged){
        this.player_pos = player_pos;
        this.box_opened = box_opened;
        this.logged = logged;
    }

    public  interface MyGameCallBack{
        public void onStartQuizActivity(int n, float[] player_pos, boolean[] box_opened, boolean logged);
        public void onStartVisualActivity(int n, float[] player_pos, boolean[] box_opened, boolean logged);
        public void onStartDSSTActivity(float[] player_pos, boolean[] box_opened, boolean logged);
    }

    public static MyGameCallBack myGameCallBack;

    public void setMyGameCallBack(MyGameCallBack callBack){
        myGameCallBack = callBack;
    }

    @Override
    public void create() {
        setMainScreen();
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
