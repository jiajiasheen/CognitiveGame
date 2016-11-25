package com.cognitive.game.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class EndActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Button end = (Button) findViewById(R.id.end_btn);

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
