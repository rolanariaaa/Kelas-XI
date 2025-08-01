package com.example.datepicker;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText etTanggal;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTanggal = findViewById(R.id.etTanggal);
        calendar = Calendar.getInstance();
        loadCurrentDate();
    }

    private void loadCurrentDate() {
        // Mengatur format tanggal
        String dateFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
        etTanggal.setText(sdf.format(calendar.getTime()));
    }

    public void etTanggal(View view) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Perbarui Calendar dengan tanggal yang dipilih
                        calendar.set(year, monthOfYear, dayOfMonth);
                        // Perbarui EditText dengan tanggal yang dipilih
                        String dateFormat = "dd-MM-yyyy";
                        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
                        etTanggal.setText(sdf.format(calendar.getTime()));
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }
}