package com.cognitive.game.android;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class EndActivity extends Activity {
    public static int coins_final;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        coins_final = getIntent().getIntExtra("Coins", 0);
        //TextView goldenText = (TextView)findViewById(R.id.gold_text2);
        //System.out.println("^^^^^^^^^^^^^^^^" + coins_final);
        //goldenText.setText(" x " + String.valueOf(coins_final));

        Button end = (Button) findViewById(R.id.end_btn);
        ImageButton resultBtn = (ImageButton) findViewById(R.id.result_btn) ;

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        resultBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

}
