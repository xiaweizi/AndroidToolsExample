package com.xiaweizi.androidtoolsexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void common(View view) {
        startActivity(new Intent(MainActivity.this, CommonActivity.class));
    }

    public void RecyclerView(View view) {
        startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
    }
}
