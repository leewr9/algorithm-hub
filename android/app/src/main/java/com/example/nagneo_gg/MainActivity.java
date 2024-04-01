package com.example.nagneo_gg;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.fragmentMain.ChampionFragment;
import com.example.fragmentMain.FragmentAdapter;
import com.example.fragmentMain.ItemFragment;
import com.example.fragmentMain.SummonerFragment;
import com.example.retrofitController.IngamesInfo;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private IngamesInfo ingamesInfo = IngamesInfo.getInstance();

    private long pressedTime = 0;

    //Etc
    private FragmentAdapter fragmentAdapter;
    private OnBackPressedListener listener;

    //Widget
    private TabLayout tap_all;
    private ViewPager view_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingIngameInfo();

        Intent intent = new Intent(this, LoadingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (android.os.Build.VERSION.SDK_INT > 9) {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);
                        }

                        tap_all = findViewById(R.id.tap_all);
                        view_pager = findViewById(R.id.view_pager);

                        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), tap_all.getTabCount());
                        view_pager.setAdapter(fragmentAdapter);

                        view_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tap_all));

                        tap_all.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                            @Override
                            public void onTabSelected(TabLayout.Tab tab) {
                                if (tab.getPosition() == 0) {
                                    if (SummonerFragment.isPageOpen) listener = FragmentAdapter.listener_summoner;
                                    else listener = null;
                                    ItemFragment.isPageOpen = false;
                                } else if (tab.getPosition() == 1) {
                                    if (ChampionFragment.isPageOpen) listener = FragmentAdapter.listener_champion;
                                    else listener = null;
                                } else if (tab.getPosition() == 2) {
                                    if (ItemFragment.isPageOpen) listener = FragmentAdapter.listener_item;
                                    else listener = null;
                                    SummonerFragment.isPageOpen = false;
                                }
                                view_pager.setCurrentItem(tab.getPosition());
                            }

                            @Override
                            public void onTabUnselected(TabLayout.Tab tab) {

                            }

                            @Override
                            public void onTabReselected(TabLayout.Tab tab) {

                            }
                        });
                    }
                }, 1000);
            }
        });



    }

    @Override
    public void onBackPressed() {
        if (listener != null) {
            listener.onBackPressed();
        } else {
            if (pressedTime == 0) {
                Toast.makeText(getApplicationContext(), "'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_LONG).show();
                pressedTime = System.currentTimeMillis();
            } else {
                int seconds = (int) (System.currentTimeMillis() - pressedTime);
                if (seconds > 2000) {
                    Toast.makeText(getApplicationContext(), "'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_LONG).show();
                    pressedTime = 0;
                } else {
                    super.onBackPressed();
                    finish();
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }
        }
    }

    public void settingIngameInfo() {
        ingamesInfo.summonerInfoMysql();
        ingamesInfo.championInfoMysql();
        ingamesInfo.itemInfoMysql();
    }

    public void setOnBackPressedListener(OnBackPressedListener listener) {
        this.listener = listener;
    }

    public interface OnBackPressedListener {
        public void onBackPressed();
    }
}