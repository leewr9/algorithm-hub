package com.example.fragmentMatch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.apiDTO.MatchDTO;
import com.example.nagneo_gg.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ComparisonFragment extends Fragment {
    private MatchDTO match;
    private int team;
    private Map<String, int[]> total;

    private PieChart comparison_object, comparison_kill, comparison_gold, comparison_damage, comparison_heart, comparison_minion;

    ComparisonFragment(MatchDTO match, int team) {
        this.match = match;
        this.team = team;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comparison, container, false);

        comparison_object = view.findViewById(R.id.comparison_object);
        comparison_kill = view.findViewById(R.id.comparison_kill);
        comparison_damage = view.findViewById(R.id.comparison_damage);
        comparison_heart = view.findViewById(R.id.comparison_heart);
        comparison_gold = view.findViewById(R.id.comparison_gold);
        comparison_minion = view.findViewById(R.id.comparison_minion);

        matchTotalSetting();
        comparisonDetailSetting(comparison_heart, "받은 피해량");
        comparisonDetailSetting(comparison_damage, "입힌 피해량");
        comparisonDetailSetting(comparison_object, "오브젝트");
        comparisonDetailSetting(comparison_kill, "킬스코어");
        comparisonDetailSetting(comparison_minion, "미니언");
        comparisonDetailSetting(comparison_gold, "골드");

        return view;
    }

    public void matchTotalSetting() {
        total = new HashMap<>();
        total.put("오브젝트", new int[]{0, 0});
        total.put("킬스코어", new int[]{0, 0});
        total.put("받은 피해량", new int[]{0, 0});
        total.put("입힌 피해량", new int[]{0, 0});
        total.put("미니언", new int[]{0, 0});
        total.put("골드", new int[]{0, 0});

        total.get("오브젝트")[0] = match.getTeams().get(0).getTowerKills() + match.getTeams().get(0).getDragonKills() + match.getTeams().get(0).getBaronKills();
        total.get("오브젝트")[1] = match.getTeams().get(1).getTowerKills() + match.getTeams().get(1).getDragonKills() + match.getTeams().get(1).getBaronKills();

        for (int i = 0; i < match.getParticipants().size(); i++) {
            if (i < 5) {
                total.get("골드")[0] += match.getParticipants().get(i).getStats().getGoldEarned();
                total.get("킬스코어")[0] += match.getParticipants().get(i).getStats().getKills();
                total.get("받은 피해량")[0] += match.getParticipants().get(i).getStats().getTotalDamageTaken();
                total.get("입힌 피해량")[0] += match.getParticipants().get(i).getStats().getTotalDamageDealtToChampions();
                total.get("미니언")[0] += match.getParticipants().get(i).getStats().getTotalMinionsKilled() + match.getParticipants().get(i).getStats().getNeutralMinionsKilled();
            } else {
                total.get("골드")[1] += match.getParticipants().get(i).getStats().getGoldEarned();
                total.get("킬스코어")[1] += match.getParticipants().get(i).getStats().getKills();
                total.get("받은 피해량")[1] += match.getParticipants().get(i).getStats().getTotalDamageTaken();
                total.get("입힌 피해량")[1] += match.getParticipants().get(i).getStats().getTotalDamageDealtToChampions();
                total.get("미니언")[1] += match.getParticipants().get(i).getStats().getTotalMinionsKilled() + match.getParticipants().get(i).getStats().getNeutralMinionsKilled();
            }
        }
    }

    public void comparisonDetailSetting(PieChart comparison, String name) {
        int color[] = {getResources().getColor(R.color.blue), getResources().getColor(R.color.red)};
        ArrayList<PieEntry> setting = new ArrayList<>();
        String blue_label = String.valueOf(total.get(name)[0]);
        String red_label = String.valueOf(total.get(name)[1]);

        if (blue_label.length() > 3)
            blue_label = blue_label.substring(0, blue_label.length() - 3) + "," + blue_label.substring(blue_label.length() - 3);
        if (red_label.length() > 3)
            red_label = red_label.substring(0, red_label.length() - 3) + "," + red_label.substring(red_label.length() - 3);

        setting.add(new PieEntry(total.get(name)[0], "블루 " + blue_label));
        setting.add(new PieEntry(total.get(name)[1], "레드 " + red_label));

        PieDataSet dataSetting = new PieDataSet(setting, "");
        dataSetting.setColors(color);

        Legend i = comparison.getLegend();
        i.setTextSize(12);

        PieData data = new PieData(dataSetting);
        comparison.setTouchEnabled(false);

        comparison.setEntryLabelColor(getResources().getColor(R.color.empty));
        data.setValueTextColor(getResources().getColor(R.color.empty));

        comparison.setCenterText(name);
        comparison.setCenterTextSize(15);
        comparison.setCenterTextColor(getResources().getColor(R.color.firsnal));

        comparison.setHoleRadius(75);
        comparison.setData(data);
        comparison.setDescription(null);
        comparison.invalidate();
    }
}