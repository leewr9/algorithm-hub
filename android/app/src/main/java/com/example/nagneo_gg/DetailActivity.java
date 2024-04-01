package com.example.nagneo_gg;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.apiDTO.MatchDTO;
import com.example.fragmentMatch.MatchFragmentAdapter;
import com.google.android.material.tabs.TabLayout;

public class DetailActivity extends AppCompatActivity {
    private MatchDTO match;
    private String win;
    private int team;

    private MatchFragmentAdapter matchFragmentAdapter;

    private TabLayout tap_match;
    private ViewPager view_matches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        match = (MatchDTO) intent.getSerializableExtra("match");
        win = intent.getStringExtra("win");
        team = intent.getIntExtra("team", 0);

        matchDetailSetiing(match, win, team);
    }

    public void matchDetailSetiing(MatchDTO match, String win, int team) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        tap_match = findViewById(R.id.tap_match);
        view_matches = findViewById(R.id.view_matches);

        matchFragmentAdapter = new MatchFragmentAdapter(getSupportFragmentManager(), tap_match.getTabCount());
        matchFragmentAdapter.viewMatchSetting(match, win, team);
        view_matches.setAdapter(matchFragmentAdapter);

        view_matches.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tap_match));

        tap_match.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                view_matches.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}