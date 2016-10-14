package com.alsk.showcase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportFragmentManager().findFragmentById(R.id.main_content) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_content, ShowcaseFragment.newInstance())
                    .commit();
        }
    }
}
