package com.example.fragmentMatch;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anyDTO.ItemDTO;
import com.example.apiDTO.MatchDTO;
import com.example.apiDTO.TeamStatsDTO;
import com.example.fragmentMain.FragmentAdapter;
import com.example.nagneo_gg.R;
import com.example.recyclerView.DetailViewAdapter;

public class TotalFragment extends Fragment {
    private MatchDTO match;
    private String win;
    private int team;

    //Etc
    private DetailViewAdapter detail_this, detail_another;

    //Widget
    private View view;
    private LinearLayout match_this, match_another;

    private TextView team_this, team_another, tower_this, tower_another, dragon_this, dragon_another, baron_this, baron_another;
    private RecyclerView view_detailthis, view_detailanother;

    TotalFragment(MatchDTO match, String win, int team) {
        this.match = match;
        this.win = win;
        this.team = team;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_total, container, false);

        view_detailthis = view.findViewById(R.id.view_detailthis);
        view_detailanother = view.findViewById(R.id.view_detailanother);

        view_detailthis.setLayoutManager(new LinearLayoutManager(view.getContext()));
        view_detailanother.setLayoutManager(new LinearLayoutManager(view.getContext()));

        team_this = view.findViewById(R.id.team_this);
        team_another = view.findViewById(R.id.team_another);

        tower_this = view.findViewById(R.id.tower_this);
        tower_another = view.findViewById(R.id.tower_another);
        dragon_this = view.findViewById(R.id.dragon_this);
        dragon_another = view.findViewById(R.id.dragon_another);
        baron_this = view.findViewById(R.id.baron_this);
        baron_another = view.findViewById(R.id.baron_another);

        match_this = view.findViewById(R.id.match_this);
        match_another = view.findViewById(R.id.match_another);

        totalMatchSetting();
        return view;
    }

    public void totalMatchSetting() {
        String win_lose[] = {"승리", "패배", "다시하기"};
        int colors[] = {R.color.blue, R.color.red, R.color.gray};

        String teams_this = "블루팀", teams_another = "레드팀";

        int result_this, result_another, start_this = 0, start_another = 5;

        if (win.equals("Win")) {
            result_this = 0;
            result_another = 1;
        } else if (win.equals("Fail")) {
            result_this = 1;
            result_another = 0;
        } else {
            result_this = 2;
            result_another = 2;
        }

        if (team == 200) {
            start_this = 5;
            start_another = 0;
            teams_this = "레드팀";
            teams_another = "블루팀";
        }

        for (TeamStatsDTO i : match.getTeams()) {
            if (i.getTeamId() == team) {
                tower_this.setText(String.valueOf(i.getTowerKills()));
                dragon_this.setText(String.valueOf(i.getDragonKills()));
                baron_this.setText(String.valueOf(i.getBaronKills()));
            } else {
                tower_another.setText(String.valueOf(i.getTowerKills()));
                dragon_another.setText(String.valueOf(i.getDragonKills()));
                baron_another.setText(String.valueOf(i.getBaronKills()));
            }
        }

        team_this.setText(teams_this + " [" + win_lose[result_this] + "]");
        team_another.setText(teams_another + " [" + win_lose[result_another] + "]");

        match_this.setBackgroundResource(colors[result_this]);
        match_another.setBackgroundResource(colors[result_another]);

        detail_this = new DetailViewAdapter(match.getParticipantIdentities(), match.getParticipants(), start_this, match.getGameDuration());
        detail_another = new DetailViewAdapter(match.getParticipantIdentities(), match.getParticipants(), start_another, match.getGameDuration());

        detail_this.adapterSetting();
        view_detailthis.setAdapter(detail_this);

        detail_another.adapterSetting();
        view_detailanother.setAdapter(detail_another);

        detail_this.setOnItemClickListener(new DetailViewAdapter.OnDetailClickListener() {
            @Override
            public void onSummonerClick(View v, int position, String summoner) throws Exception {
                FragmentAdapter.getSummonerFragment().searchSummoner(v, summoner);
            }

            @Override
            public void onChampionClick(View v, int position, String champion) {
                Toast.makeText(v.getContext(), champion, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemClick(View v, int position, ItemDTO item) {
                if (item != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    String title = "<font color='#00949E'>" + item.getKorid() + "</font>";
                    Spanned spanned = Html.fromHtml(item.getDescription().replaceAll("<br>", "/n"));
                    String totals = String.valueOf(item.getTotal());

                    if (totals.length() > 3)
                        totals = totals.substring(0, 1) + "," + totals.substring(1);

                    String message = "<font color='#FFFFFF'>" + spanned + "</font><br><font color='#FFFFFF'>구매가격 </font><font color='#FFC061'>" + totals + "원</font>";
                    builder.setTitle(Html.fromHtml(title)).setMessage(Html.fromHtml(message.replaceAll("/n", "<br>")));

                    AlertDialog alertDialog = builder.create();
                    alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    alertDialog.getWindow().setTitleColor(getResources().getColor(R.color.white));
                    alertDialog.show();
                }
            }
        });

        detail_another.setOnItemClickListener(new DetailViewAdapter.OnDetailClickListener() {
            @Override
            public void onSummonerClick(View v, int position, String summoner) throws Exception {
                FragmentAdapter.getSummonerFragment().searchSummoner(v, summoner);
            }

            @Override
            public void onChampionClick(View v, int position, String champion) {
                Toast.makeText(v.getContext(), champion, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemClick(View v, int position, ItemDTO item) {
                if (item != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    String title = "<font color='#00949E'>" + item.getKorid() + "</font>";
                    Spanned spanned = Html.fromHtml(item.getDescription().replaceAll("<br>", "/n"));
                    String totals = String.valueOf(item.getTotal());

                    if (totals.length() > 3)
                        totals = totals.substring(0, 1) + "," + totals.substring(1);

                    String message = "<font color='#FFFFFF'>" + spanned + "</font><br><font color='#FFFFFF'>구매가격 </font><font color='#FFC061'>" + totals + "원</font>";
                    builder.setTitle(Html.fromHtml(title)).setMessage(Html.fromHtml(message.replaceAll("/n", "<br>")));

                    AlertDialog alertDialog = builder.create();
                    alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    alertDialog.getWindow().setTitleColor(getResources().getColor(R.color.white));
                    alertDialog.show();
                }
            }
        });
    }
}