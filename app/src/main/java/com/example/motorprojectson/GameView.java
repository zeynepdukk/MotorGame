package com.example.motorprojectson;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

public class GameView extends View implements SensorEventListener {

    SensorManager sensorManager;
    private Sensor rotationSensor;
    private Paint myPaint;
    private int speed = 1;
    private int time = 0;
    private int score = 0;
    private int motorPosition = 0;
    int passedCars = 0;
    private ArrayList<HashMap<String, Object>> otherCars = new ArrayList<>();
    private int viewWidth = 0;
    private int viewHeight = 0;
    private DifficultyLevel difficultyLevel = DifficultyLevel.NOVICE;


    public enum DifficultyLevel {
        NOVICE,
        INTERMEDIATE,
        ADVANCED
    }

    public GameView(Context c, GameTask gameTask) {
        super(c);
        myPaint = new Paint();
        motorPosition = 1;


        sensorManager = (SensorManager) c.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (rotationSensor != null) {
                sensorManager.registerListener(this, rotationSensor, SensorManager.SENSOR_DELAY_GAME);
            }
        }

    }
    public void setDifficultyLevel(DifficultyLevel level) {
        this.difficultyLevel = level;
        switch (level) {
            case NOVICE:
                speed = 1;
                break;
            case INTERMEDIATE:
                speed = 2;
                break;
            case ADVANCED:
                speed = 3;
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int currentCarCount = 0;
        super.onDraw(canvas);
        viewWidth = this.getMeasuredWidth();
        viewHeight = this.getMeasuredHeight();

        if (currentCarCount < 2 && time % 1000 < 10 + speed) {
            for (int i = 0; i < 2; i++) {
                int randomLane = (int) (Math.random() * 3);

                boolean isRed = Math.random() < 0.33;

                HashMap<String, Object> map = new HashMap<>();
                map.put("lane", randomLane);
                map.put("StartTime", time);
                map.put("isRed", isRed);
                otherCars.add(map);
            }
        }

        time = time + 10 + speed;
        int motorWidth = viewWidth / 5;
        int motorHeight = motorWidth + 10;
        Drawable d = getResources().getDrawable(R.drawable.motor, null);

        d.setBounds(
                motorPosition * viewWidth / 3 + viewWidth / 15 + 25,
                viewHeight - 2 - motorHeight,
                motorPosition * viewWidth / 3 + viewWidth / 15 + motorWidth - 25,
                viewHeight - 2
        );

        d.draw(canvas);


        for (int i = 0; i < otherCars.size(); i++) {
            try {

                int carLane = (int) otherCars.get(i).get("lane");
                int carX = carLane * viewWidth / 3 + viewWidth / 15;
                int carY = time - (int) otherCars.get(i).get("StartTime");

                if (carLane == motorPosition &&
                        carX < motorPosition * viewWidth / 3 + viewWidth / 15 + motorWidth &&
                        carX + motorWidth > motorPosition * viewWidth / 3 + viewWidth / 15 &&
                        carY < viewHeight - 2 &&
                        carY > viewHeight - 2 - motorHeight) {
                    gameOver(score);
                }

                Drawable carDrawable;

                if ((boolean) otherCars.get(i).get("isRed")) {
                    carDrawable = getResources().getDrawable(R.drawable.redcar, null);
                } else {
                    carDrawable = getResources().getDrawable(R.drawable.caryellow, null);
                }

                carDrawable.setBounds(
                        carX + 25,
                        carY - motorHeight,
                        carX + motorWidth - 25,
                        carY
                );

                carDrawable.draw(canvas);
                if (carY < viewHeight + motorHeight*2) {
                    currentCarCount++;
                }
                if (carY < viewHeight + motorHeight * 2 && carY > viewHeight - 2) {
                    passedCars++;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        score = passedCars/45;
        myPaint.setColor(Color.WHITE);
        myPaint.setTextSize(40f);
        canvas.drawText("SCORE:" + score, 80f, 80f, myPaint);
        canvas.drawText("SPEED:" + speed, 380f, 80f, myPaint);
        invalidate();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float xAccel = event.values[0];
            if (1.0<xAccel&& xAccel < 7.0) {
                motorPosition=0;
            }
            else if (-1.0>xAccel&& xAccel > -7.0) {
                motorPosition=2;
            }
            else {
                motorPosition=1;
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    @Override
    protected void onDetachedFromWindow() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
        super.onDetachedFromWindow();
    }
    private void gameOver(int score) {
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }

        if (getContext() instanceof GameTask) {
            ((GameTask) getContext()).closeGame(score);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                accelerateMotorbike();
                break;
            case MotionEvent.ACTION_UP:
                decelerateMotorbike();
                break;
        }
        return true;
    }

    private void accelerateMotorbike() {
        speed += 1;
    }

    private void decelerateMotorbike() {
        speed = Math.max(1, speed - 1);
    }


}