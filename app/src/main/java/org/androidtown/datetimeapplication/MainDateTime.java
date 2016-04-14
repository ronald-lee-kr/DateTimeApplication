package org.androidtown.datetimeapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class MainDateTime extends AppCompatActivity {
    Chronometer chrono;
    Button btnStart, btnEnd;
    RadioButton rdoCal, rdoTime;
    CalendarView calVew;
    TimePicker tPicker;
    String tvYear, tvMonth, tvDay, tvHour, tvMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_date_time);
        setTitle("시간 예약");

        btnStart = (Button)findViewById(R.id.btnStart);
        btnEnd = (Button)findViewById(R.id.btnEnd);
        chrono = (Chronometer)findViewById(R.id.chronometer1);
        rdoCal = (RadioButton)findViewById(R.id.rdoCal);
        rdoTime = (RadioButton)findViewById(R.id.rdoTime);
        tPicker = (TimePicker)findViewById(R.id.timePicker1);
        calVew = (CalendarView)findViewById(R.id.calendarView1);

        tPicker.setVisibility(View.INVISIBLE);
        calVew.setVisibility(View.INVISIBLE);

        rdoCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tPicker.setVisibility(View.INVISIBLE);
                calVew.setVisibility(View.VISIBLE);
            }
        });

        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tPicker.setVisibility(View.VISIBLE);
                calVew.setVisibility(View.INVISIBLE);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
                chrono.setTextColor(Color.RED);
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrono.stop();
                chrono.setTextColor(Color.BLUE);

                java.util.Calendar curDate = java.util.Calendar.getInstance();
                curDate.setTimeInMillis(calVew.getDate());
                tvYear = Integer.toString(curDate.get(Calendar.YEAR));
                tvMonth = Integer.toString(1+curDate.get(Calendar.MONTH));
                tvDay = Integer.toString(curDate.get(Calendar.DATE));

                tvHour = Integer.toString(tPicker.getHour());
                tvMinute = Integer.toString(tPicker.getMinute());

                new AlertDialog.Builder(MainDateTime.this)
                        .setTitle("예약 확인")
                        .setMessage(tvYear + "년" + tvMonth + "월" + tvDay + "일 "
                        + tvHour + "시" + tvMinute + "분에 예약됨")
                        .setNeutralButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });
    }
}
