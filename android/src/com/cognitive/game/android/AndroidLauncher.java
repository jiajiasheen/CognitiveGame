package com.cognitive.game.android;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.cognitive.game.CognitiveGame;

public class AndroidLauncher extends AndroidApplication implements CognitiveGame.MyGameCallBack {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_interface);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		CognitiveGame cognitiveGame = new CognitiveGame();
		cognitiveGame.setMyGameCallBack(this);
		initialize(new CognitiveGame(), config);
	}

	@Override
	public void onStartQuizActivity() {
		Intent intent = new Intent (this, QuizActivity.class);
		startActivity(intent);
	}

	@Override
	public void onStartVisualActivity() {
		Intent intent = new Intent (this, VisualActivity.class);
		startActivity(intent);
	}
}
