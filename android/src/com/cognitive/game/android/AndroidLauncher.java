package com.cognitive.game.android;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.cognitive.game.CognitiveGame;

public class AndroidLauncher extends AndroidApplication implements CognitiveGame.MyGameCallBack {

	private float[] player_pos = new float[2];
	private boolean[] box_opened = new boolean[7];

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_interface);

		//get pos from last question
		if(getIntent().getFloatArrayExtra("Player") != null)
			player_pos = getIntent().getFloatArrayExtra("Player");
		if(getIntent().getBooleanArrayExtra("Box") != null)
			box_opened = getIntent().getBooleanArrayExtra("Box");

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		CognitiveGame cognitiveGame = new CognitiveGame(player_pos, box_opened);
		cognitiveGame.setMyGameCallBack(this);
		initialize(cognitiveGame, config);
	}

	@Override
	public void onStartQuizActivity(int n, float[] player_pos, boolean[] box_opened) {
		Intent intent = new Intent (this, QuizActivity.class).putExtra("Level", n);
		intent.putExtra("Player", player_pos);
		intent.putExtra("Box", box_opened);
		startActivity(intent);
	}

	@Override
	public void onStartVisualActivity() {
		Intent intent = new Intent (this, VisualActivity.class);
		startActivity(intent);
	}
}
