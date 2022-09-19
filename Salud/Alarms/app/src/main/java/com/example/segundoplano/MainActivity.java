package com.example.segundoplano;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.segundoplano.UI.DatePickerFragment;
import com.example.segundoplano.UI.TimePickerFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText etDate;
    EditText etTime;
    Button btnAlarma;
    private Context context;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = getApplicationContext();
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        etDate = findViewById(R.id.etDate);
        btnAlarma = findViewById(R.id.btnAlarma);
        etTime = findViewById(R.id.etTime);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });
        btnAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String date = etDate.getText().toString();
               String time = etTime.getText().toString();
               Calendar calendar = Calendar.getInstance();

               SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
                try {
                    calendar.setTime(sdf.parse(date + " " + time));
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);

                Toast.makeText(getApplicationContext(), "La alarma se estableció para el " + date + " a las " + time, Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void showDatePicker(){
        DatePickerFragment dateFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                etDate.setText(day + "/" + (month + 1) + "/" + year);

            }
        });

        dateFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    public void showTimePicker(){
        TimePickerFragment timePicker = TimePickerFragment.getInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minutes) {
                etTime.setText(hour + ":" + minutes);
            }
        });
        timePicker.show(getSupportFragmentManager(), "TimePicker");
    }
}