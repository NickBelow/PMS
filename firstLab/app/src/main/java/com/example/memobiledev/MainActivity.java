package com.example.memobiledev;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

/**
 * class which represents MainWindow
 *app starts here
 */

public class MainActivity extends AppCompatActivity {

    /**
     * Method called when view is crated
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(
                new SampleFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this)
        );
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }
}