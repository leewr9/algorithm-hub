package com.example.fragmentMain;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.nagneo_gg.MainActivity;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private static SummonerFragment summoner;
    private ChampionFragment champion;
    private ItemFragment item;
    public static MainActivity.OnBackPressedListener listener_summoner;
    public static MainActivity.OnBackPressedListener listener_champion;
    public static MainActivity.OnBackPressedListener listener_item;

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        if (summoner == null) {
            summoner = new SummonerFragment();
            listener_summoner = summoner;
        }
        if (champion == null) {
            champion = new ChampionFragment();
            listener_champion = champion;
        }
        if (item == null) {
            item = new ItemFragment();
            listener_item = item;
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return summoner;
        } else if (position == 1) {
            return champion;
        } else if (position == 2) {
            return item;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    public static SummonerFragment getSummonerFragment() {
        return summoner;
    }
}