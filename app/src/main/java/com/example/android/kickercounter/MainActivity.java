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
    boolean isTimerOn = false;
    long delta = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        chronometer = findViewById(R.id.chronometer);
        hidePause();
    }

    public void displayForTeamA(int score) {
        TextView scoreView = findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamB(int score) {
        TextView scoreView = findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void addPointTeamA(View view) {
        scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);
    }

    public void addPointTeamB(View view) {
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

    public void timerClick(View view) {
        if (isTimerOn) {
            chronometer.stop();
            isTimerOn = false;
            showPause();
            delta = SystemClock.elapsedRealtime() - chronometer.getBase();
        } else {
            if (delta == 0) {
                initTimer();
            }
            chronometer.setBase(SystemClock.elapsedRealtime() - delta);
            chronometer.start();
            isTimerOn = true;
            hidePause();
        }
    }

    public void reset(View view) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        chronometer.stop();
        initTimer();
    }

    private void showToast(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void showPause() {
        TextView pause = findViewById(R.id.pause);
        pause.setVisibility(View.VISIBLE);
    }

    private void hidePause() {
        TextView pause = findViewById(R.id.pause);
        pause.setVisibility(View.INVISIBLE);
    }

    private void initTimer() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        chronometer.setBase(elapsedRealtime);
        delta = 0;
        hidePause();
    }
}

