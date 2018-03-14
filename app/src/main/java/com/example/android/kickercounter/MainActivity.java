package com.example.android.kickercounter;

import android.content.Context;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    int scoreTeamA = 0;
    int scoreTeamB = 0;
    Chronometer chronometer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        chronometer = findViewById(R.id.chronometer);

    }
    public void displayForTeamA(int score) {
        TextView scoreView = findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }
    public void displayForTeamB(int score) {
        TextView scoreView = findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void addPointTeamA (View view) {
        scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);
    }
    public void addPointTeamB (View view) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);
    }

    public void offPointTeamA(View view) {
        if (scoreTeamA == 0) {
            showToast("No possible, my friend");
            return;
        }
        scoreTeamA = scoreTeamA - 1;
        displayForTeamA(scoreTeamA);
    }
    public void offPointTeamB(View view) {
        if (scoreTeamB == 0) {
            showToast("No possible, my friend");
            return;
        }
        scoreTeamB = scoreTeamB - 1;
        displayForTeamB(scoreTeamB);
    }
    public void startTimer(View view) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        chronometer.setBase(elapsedRealtime);
        chronometer.start();
    }

    public void pauseTimer(View view) {
        chronometer.stop();
    }


    public void reset (View view) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        chronometer.setBase(SystemClock.elapsedRealtime());
    }
    private void showToast(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}

