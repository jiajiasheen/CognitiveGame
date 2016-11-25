package com.cognitive.game.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.cognitive.game.CognitiveGame;

public class AndroidLauncher extends AndroidApplication implements CognitiveGame.MyGameCallBack {

	private float[] player_pos = new float[]{600, 600};
	private boolean[] box_opened = new boolean[7];
    private boolean logged = false;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        //get log_in state
        if(getIntent().getBooleanExtra("Logged", false))
            logged = true;
        //get pos from last question
        if(getIntent().getFloatArrayExtra("Player") != null)
            player_pos = getIntent().getFloatArrayExtra("Player");
        if(getIntent().getBooleanArrayExtra("Box") != null)
            box_opened = getIntent().getBooleanArrayExtra("Box");

        if(!logged){
            Intent log_in = new Intent(this, LogInActivity.class);
            startActivity(log_in);
            finish();
        }

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		CognitiveGame cognitiveGame = new CognitiveGame(player_pos, box_opened, logged);
		cognitiveGame.setMyGameCallBack(this);
		initialize(cognitiveGame, config);

	}

	@Override
	public void onStartQuizActivity(int n, float[] player_pos, boolean[] box_opened, boolean logged) {
		Intent intent = new Intent (this, QuizActivity.class).putExtra("Level", n);
		intent.putExtra("Player", player_pos);
		intent.putExtra("Box", box_opened);
        intent.putExtra("Logged", logged);
		startActivity(intent);
        finish();
	}

	@Override
	public void onStartVisualActivity(int n, float[] player_pos, boolean[] box_opened, boolean logged) {
		Intent intent = new Intent (this, VisualActivity.class);
		intent.putExtra("ImageLevel",n);
		intent.putExtra("Player", player_pos);
		intent.putExtra("Box", box_opened);
        intent.putExtra("Logged", logged);
		startActivity(intent);
        finish();
	}

	@Override
	public void onStartDSSTActivity(float[] player_pos, boolean[] box_opened, boolean logged) {
		Intent intent = new Intent (this, DSSTActivity.class);
		intent.putExtra("Player", player_pos);
		intent.putExtra("Box", box_opened);
        intent.putExtra("Logged", logged);
		startActivity(intent);
        finish();
	}

    @Override
    public void onStartEndActivity() {
        Intent intent = new Intent (this, EndActivity.class);
        startActivity(intent);
        finish();
    }

}
