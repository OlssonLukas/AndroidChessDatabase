package com.example.chessmatch;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertData extends AppCompatActivity {
    EditText t1;
    int t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (EditText) findViewById(R.id.playerMove);
        t2 = 1;
    }

    public void addRecord(View view) {
        DBManager db = new DBManager(this);

        String res = db.addRecord(t1.getText().toString(), t2);

        Toast.makeText(this, res, Toast.LENGTH_LONG).show();
    }
}

