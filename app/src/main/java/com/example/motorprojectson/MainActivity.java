package com.example.motorprojectson;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements GameTask {

    private LinearLayout rootLayout;
    private GameView mGameView;
    private TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button noviceButton = findViewById(R.id.noviceButton);
        Button intermediateButton = findViewById(R.id.intermediateButton);
        Button advancedButton = findViewById(R.id.advancedButton);
        rootLayout = findViewById(R.id.rootLayout);
        score = findViewById(R.id.score);
        mGameView = new GameView(this, this);


        noviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGameView.setDifficultyLevel(GameView.DifficultyLevel.NOVICE);
                mGameView.setBackgroundResource(R.drawable.road1);
                rootLayout.addView(mGameView);
                score.setVisibility(View.GONE);
                advancedButton.setVisibility(View.GONE);
                intermediateButton.setVisibility(View.GONE);
                noviceButton.setVisibility(View.GONE);
            }
        });
        intermediateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGameView.setDifficultyLevel(GameView.DifficultyLevel.INTERMEDIATE);
                mGameView.setBackgroundResource(R.drawable.road1);
                rootLayout.addView(mGameView);
                score.setVisibility(View.GONE);
                advancedButton.setVisibility(View.GONE);
                intermediateButton.setVisibility(View.GONE);
                noviceButton.setVisibility(View.GONE);
            }
        });
        advancedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGameView.setDifficultyLevel(GameView.DifficultyLevel.ADVANCED);
                mGameView.setBackgroundResource(R.drawable.road1);
                rootLayout.addView(mGameView);
                score.setVisibility(View.GONE);
                advancedButton.setVisibility(View.GONE);
                intermediateButton.setVisibility(View.GONE);
                noviceButton.setVisibility(View.GONE);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void closeGame(int mScore) {
        score.setText("Score : " + mScore);
        rootLayout.removeView(mGameView);
        score.setVisibility(View.VISIBLE);

    }
}
