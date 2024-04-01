package com.example.fragmentMatch;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiDTO.MatchDTO;
import com.example.fragmentMain.FragmentAdapter;
import com.example.nagneo_gg.R;
import com.example.recyclerView.DamageViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DamageFragment extends Fragment {
    private MatchDTO match;
    private int team;
    private long max;

    private List<Long> team_red;
    private List<Long> team_blue;

    //Etc
    private DamageViewAdapter adapter_this, adapter_another;

    //Widget
    private View view;

    private RecyclerView view_this, view_another;
    private TextView damage_this, damage_another;

    DamageFragment(MatchDTO match, int team) {
        this.team_blue = new ArrayList<>();
        this.team_red = new ArrayList<>();
        this.match = match;
        this.team = team;

        for (int i = 0; i < match.getParticipants().size(); i++) {
            if (match.getParticipants().get(i).getTeamId() == 100)
                team_blue.add(match.getParticipants().get(i).getStats().getTotalDamageDealtToChampions());
            else
                team_red.add(match.getParticipants().get(i).getStats().getTotalDamageDealtToChampions());
        }

        Collections.sort(team_blue);
        Collections.sort(team_red);
        Collections.reverse(team_blue);
        Collections.reverse(team_red);

        if (team_blue.get(0) > team_red.get(0)) max = team_blue.get(0);
        else max = team_red.get(0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_damage, container, false);
        damage_this = view.findViewById(R.id.damage_this);
        damage_another = view.findViewById(R.id.damage_another);

        view_this = view.findViewById(R.id.view_this);
        view_another = view.findViewById(R.id.view_another);
        view_this.setLayoutManager(new LinearLayoutManager(view.getContext()));
        view_another.setLayoutManager(new LinearLayoutManager(view.getContext()));

        List<Long> this_damage = team_blue;
        List<Long> another_damage = team_red;

        String this_team =damage_this.getText().toString();
        String another_team= damage_another.getText().toString();
        int this_start = 0;
        int another_start = 5;

        damage_this.setText(Html.fromHtml(this_team + "<font color='#00409F'>블루팀</font>]"));
        damage_another.setText(Html.fromHtml(another_team + "<font color='#9F0000'>레드팀</font>]"));

        if (team == 200) {
            this_damage = team_red;
            another_damage = team_blue;
            this_start = 5;
            another_start = 0;

            damage_this.setText(Html.fromHtml(this_team + "<font color='#9F0000'>레드팀</font>]"));
            damage_another.setText(Html.fromHtml(another_team + "<font color='#00409F'>블루팀</font>]"));
        }

        adapter_this = new DamageViewAdapter(match.getParticipantIdentities(), match.getParticipants(), this_damage, max, this_start);
        adapter_this.adapterSetting();

        adapter_another = new DamageViewAdapter(match.getParticipantIdentities(), match.getParticipants(), another_damage, max, another_start);
        adapter_another.adapterSetting();

        adapter_this.setOnItemClickListener(new DamageViewAdapter.OnDamageClickListener() {
            @Override
            public void onSummonerClick(View v, int position, String summoner) throws Exception {
                FragmentAdapter.getSummonerFragment().searchSummoner(v, summoner);
            }

            @Override
            public void onChampionClick(View v, int position, String champion) {
                Toast.makeText(v.getContext(), champion, Toast.LENGTH_LONG).show();
            }

        });

        adapter_another.setOnItemClickListener(new DamageViewAdapter.OnDamageClickListener() {
            @Override
            public void onSummonerClick(View v, int position, String summoner) throws Exception {
                FragmentAdapter.getSummonerFragment().searchSummoner(v, summoner);
            }

            @Override
            public void onChampionClick(View v, int position, String champion) {
                Toast.makeText(v.getContext(), champion, Toast.LENGTH_LONG).show();
            }

        });

        view_this.setAdapter(adapter_this);
        view_another.setAdapter(adapter_another);

        return view;
    }
}