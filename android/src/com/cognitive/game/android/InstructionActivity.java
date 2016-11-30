package com.cognitive.game.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InstructionActivity extends Activity {
    public static float[] player_pos;
    public static boolean[] box_opened;
    public static boolean logged;
    public static int coins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        player_pos = getIntent().getFloatArrayExtra("Player");
        box_opened = getIntent().getBooleanArrayExtra("Box");
        logged = getIntent().getBooleanExtra("Logged", true);
        coins = getIntent().getIntExtra("Coins", 0);

        Button next_btn = (Button) findViewById(R.id.next_instr);
        final Button back_btn = (Button) findViewById(R.id.instr_btn);

        final TextView instructions = (TextView) findViewById(R.id.instruction_part);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instructions.setText(R.string.box_tips);
                back_btn.setVisibility(View.VISIBLE);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), AndroidLauncher.class);
                intent.putExtra("Player", player_pos);
                intent.putExtra("Box", box_opened);
                intent.putExtra("Logged", logged);
                intent.putExtra("Coins", coins);
                startActivity(intent);
                finish();
            }
        });

    }
}
