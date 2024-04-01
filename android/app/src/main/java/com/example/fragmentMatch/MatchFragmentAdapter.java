package com.example.fragmentMatch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.apiDTO.MatchDTO;

public class MatchFragmentAdapter extends FragmentStatePagerAdapter {
    private TotalFragment total;
    private DamageFragment damage;
    private ComparisonFragment comparison;

    public MatchFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return total;
        } else if (position == 1) {
            return damage;
        } else if (position == 2) {
            return comparison;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    public void viewMatchSetting(MatchDTO match, String win, int team) {
        if(total == null) total = new TotalFragment(match, win, team);
        if(damage == null) damage = new DamageFragment(match, team);
        if(comparison == null) comparison = new ComparisonFragment(match, team);
    }


}