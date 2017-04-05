package com.cognitive.game.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class LogInActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button loginBtn = (Button) findViewById(R.id.launcher_button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main_page = new Intent(v.getContext(), AndroidLauncher.class);
                main_page.putExtra("Logged", true);
                startActivity(main_page);
                finish();
            }
        });
    }

}
