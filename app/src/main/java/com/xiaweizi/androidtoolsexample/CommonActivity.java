package com.xiaweizi.androidtoolsexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CommonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
    }

    public void onClick(View view) {
        Toast.makeText(this, ((Button) view).getText(), Toast.LENGTH_SHORT).show();
    }
}
