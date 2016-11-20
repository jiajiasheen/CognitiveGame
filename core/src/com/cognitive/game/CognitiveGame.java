package com.cognitive.game;

import com.badlogic.gdx.Game;

public class CognitiveGame extends Game {
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
