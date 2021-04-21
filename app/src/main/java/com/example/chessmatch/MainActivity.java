package com.example.chessmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button updateButton = findViewById(R.id.receiveButton);
        TextView updateText = findViewById(R.id.theirMove);
        Button sendButton = findViewById(R.id.sendButton);
        TextView sendText = findViewById(R.id.playerMove);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBManager dbManager = new DBManager(v.getContext());
                Cursor cursor = dbManager.getRecentMove();
                if(cursor.getCount() == 0) {
                    updateText.setText("This is a failure");
                }
                while(cursor.moveToNext()) {
                    updateText.setText(cursor.getString(0));
                }
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBManager dbManager = new DBManager(v.getContext());
                dbManager.addRecord(sendText.getText().toString(), 1);
                sendText.setText("");
            }
        });

    }
}
