package com.cognitive.game;

import com.badlogic.gdx.Game;

public class CognitiveGame extends Game {

    public  interface MyGameCallBack{
        public void onStartQuizActivity(int n);
        public void onStartVisualActivity();
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
