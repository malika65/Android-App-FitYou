package com.example.fitnessapplication.ui.progress;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.fitnessapplication.R;

import java.util.ArrayList;
import java.util.Locale;

public class ProgressFragment extends Fragment {
      private static final int TIMER_LENGTH = 30;
      private static final long START_TIME_IN_MILLIS = 30000;
      private TextView mTextViewCountDown;
      private Button mButtonStartPause;
      private Button mButtonReset;
      private CountDownTimer mCountDownTimer;
      private boolean mTimerRunning;
      private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
      private TextView textView;
      private ProgressBar progressBar;
      SeekBar seekBar;

      public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_progress, container, false);
            mButtonStartPause = root.findViewById(R.id.button_start_pause);
            mButtonReset = root.findViewById(R.id.button_reset);
            mTextViewCountDown = root.findViewById(R.id.text_view_countdown);
            textView = (TextView) root.findViewById(R.id.textView);
            progressBar = (ProgressBar) root.findViewById(R.id.progressBar);

            seekBar = (SeekBar) root.findViewById(R.id.seekBar);
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                  @Override
                  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressBar.setProgress(progress);
                        mTimeLeftInMillis = progress * 1000;
                        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
                        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
                        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                        mTextViewCountDown.setText(timeLeftFormatted);
                        mTextViewCountDown.setText("" + progress);
                  }
                  @Override
                  public void onStartTrackingTouch(SeekBar seekBar) {
                  }
                  @Override
                  public void onStopTrackingTouch(SeekBar seekBar) {
                  }
            });


            mButtonStartPause.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        if (mTimerRunning) {
                              pauseTimer();

                        } else {
                              startTimer();

                        }
                  }
            });
            mButtonReset.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        resetTimer();

                  }
            });
            updateCountDownText();
            return root;
      }

      private void startTimer() {
            mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
                  @Override
                  public void onTick(long millisUntilFinished) {
                        mTimeLeftInMillis = millisUntilFinished;
                        updateCountDownText();

                  }
                  @Override
                  public void onFinish() {
                        mTimerRunning = false;
                        mButtonStartPause.setText("Start");
                        mButtonStartPause.setVisibility(View.INVISIBLE);
                        mButtonReset.setVisibility(View.VISIBLE);
                  }
            }.start();
            mTimerRunning = true;
            mButtonStartPause.setText("pause");
            mButtonReset.setVisibility(View.INVISIBLE);
      }
      private void pauseTimer() {
            mCountDownTimer.cancel();
            mTimerRunning = false;
            mButtonStartPause.setText("Start");
            mButtonReset.setVisibility(View.VISIBLE);
      }
      private void resetTimer() {
            seekBar.setProgress(0);
            mTimeLeftInMillis = START_TIME_IN_MILLIS;
            updateCountDownText();
            mButtonReset.setVisibility(View.INVISIBLE);
            mButtonStartPause.setVisibility(View.VISIBLE);
      }
      private void updateCountDownText() {
            int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
            int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
            mTextViewCountDown.setText(timeLeftFormatted);
      }


}