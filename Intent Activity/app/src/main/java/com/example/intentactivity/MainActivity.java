package com.example.intentactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etInputData;
    public static final String KEY_PESAN = "pesan_dari_main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInputData = findViewById(R.id.etInputData);
    }

    public void kirimData(View view) {
        String pesan = etInputData.getText().toString();
        Intent intent = new Intent(this, TujuanActivity.class);
        intent.putExtra(KEY_PESAN, pesan);
        startActivity(intent);
    }
}