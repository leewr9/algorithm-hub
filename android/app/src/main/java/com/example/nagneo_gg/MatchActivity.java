package com.example.nagneo_gg;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.anyDTO.ItemDTO;
import com.example.apiDTO.LeagueEntryDTO;
import com.example.apiDTO.MatchDTO;
import com.example.apiDTO.TotalDTO;
import com.example.recyclerView.MatchViewAdapter;
import com.example.retrofitController.IngamesInfo;
import com.example.retrofitController.MatchesInfo;
import com.example.retrofitController.UrlService;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.HashMap;
import java.util.Map;

public class MatchActivity extends AppCompatActivity {
    private MatchesInfo matchesInfo = MatchesInfo.getInstance();
    private IngamesInfo ingamesInfo = IngamesInfo.getInstance();

    private boolean isLikeOn = false;

    private TotalDTO summoner;
    private Map<String, Integer> emblem;
    private Map<String, Integer> border;

    //Etc
    private Handler handler;
    private MatchViewAdapter adapter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    //Widget
    private RecyclerView view_match;
    private SwipyRefreshLayout swipe_match;
    private ProgressDialog search_progress;

    private TextView name_summoner, level_summoner, solo_tier, solo_point, solo_kda, team_tier, team_point, team_kda;
    private ImageView icon_summoner, border_summoner, solo_league, team_league;
    private ImageButton like_summoner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        drawbleMapSetting();

        sharedPreferences = getSharedPreferences("Like", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        name_summoner = findViewById(R.id.name_summoner);
        level_summoner = findViewById(R.id.level_summoner);
        solo_tier = findViewById(R.id.solo_tier);
        solo_point = findViewById(R.id.solo_point);
        solo_kda = findViewById(R.id.solo_kda);
        team_tier = findViewById(R.id.team_tier);
        team_point = findViewById(R.id.team_point);
        team_kda = findViewById(R.id.team_kda);

        icon_summoner = findViewById(R.id.icon_summoner);
        border_summoner = findViewById(R.id.border_summoner);
        solo_league = findViewById(R.id.solo_league);
        team_league = findViewById(R.id.team_league);

        view_match = findViewById(R.id.view_match);
        like_summoner = findViewById(R.id.like_summoner);

        view_match.setLayoutManager(new LinearLayoutManager(this));

        adapter = matchesInfo.getAdapter();
        adapter.adapterSetting();

        swipe_match = findViewById(R.id.swipe_match);
        swipe_match.setDirection(SwipyRefreshLayoutDirection.BOTTOM);
        view_match.setAdapter(adapter);

        Intent intent = getIntent();
        String summonerName = intent.getStringExtra("summoner");
        summoner = matchesInfo.getTotalMap().get(summonerName);
        matchInfoSetting();

        if (sharedPreferences.contains(summoner.getSummoner().getName())) {
            isLikeOn = true;
            like_summoner.setBackgroundResource(R.drawable.like_on);
        } else {
            isLikeOn = false;
            like_summoner.setBackgroundResource(R.drawable.like_off);
        }

        like_summoner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLikeOn) {
                    like_summoner.setBackgroundResource(R.drawable.like_on);
                    editor.putString(summoner.getSummoner().getName(), summoner.getSummoner().getProfileIconId() + "," + summoner.getSummoner().getSummonerLevel());
                    isLikeOn = true;
                } else {
                    like_summoner.setBackgroundResource(R.drawable.like_off);
                    editor.remove(summoner.getSummoner().getName());
                    isLikeOn = false;
                }
                editor.commit();
            }
        });

        swipe_match.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if (adapter.getItemCount() % 10 == 0) {
                    handler = new Handler();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    matchesInfo.matchesSearchApi(summonerName);
                                    swipe_match.setRefreshing(false);
                                }
                            }, 250);
                        }
                    });
                } else {
                    swipe_match.setRefreshing(false);
                }
            }
        });

        adapter.setOnItemClickListener(new MatchViewAdapter.OnMatchClickListener() {
             @Override
            public void onMatchClick(View v, int position, MatchDTO match, String win, int team) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("match", match);
                intent.putExtra("win", win);
                intent.putExtra("team", team);
                startActivity(intent);
            }

            @Override
            public void onChampionClick(View v, int position, String champion) {
                Toast.makeText(v.getContext(), champion, Toast.LENGTH_LONG).show();
            }

            @SuppressLint("ResourceAsColor")
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
                    alertDialog.getWindow().setTitleColor(R.color.white);
                    alertDialog.show();
                }
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        summoner = matchesInfo.getTotalMap().get(intent.getStringExtra("summoner"));
        matchInfoSetting();
    }

    public void drawbleMapSetting() {
        emblem = new HashMap<>();
        emblem.put("IRON", R.drawable.emblem_iron);
        emblem.put("BRONZE", R.drawable.emblem_bronze);
        emblem.put("SILVER", R.drawable.emblem_silver);
        emblem.put("GOLD", R.drawable.emblem_gold);
        emblem.put("PLATINUM", R.drawable.emblem_platinum);
        emblem.put("DIAMOND", R.drawable.emblem_diamond);
        emblem.put("MASTER", R.drawable.emblem_master);
        emblem.put("GRANDMASTER", R.drawable.emblem_grandmaster);
        emblem.put("CHALLENGER", R.drawable.emblem_challenger);

        border = new HashMap<>();
        border.put("IRON", R.drawable.border_iron);
        border.put("BRONZE", R.drawable.border_bronze);
        border.put("SILVER", R.drawable.border_silver);
        border.put("GOLD", R.drawable.border_gold);
        border.put("PLATINUM", R.drawable.border_platinum);
        border.put("DIAMOND", R.drawable.border_diamond);
        border.put("MASTER", R.drawable.border_master);
        border.put("GRANDMASTER", R.drawable.border_grandmaster);
        border.put("CHALLENGER", R.drawable.border_challenger);
    }

    public void refrashSummonerSearch(View v) throws Exception {
        if (summoner.getSummoner().getTimer() == 0) {
            String summonerName = summoner.getSummoner().getName().replace(" ", "").toLowerCase();
            handler = new Handler();
            search_progress = new ProgressDialog(v.getContext());
            search_progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            search_progress.setMessage("소환사 정보를 갱신 중입니다.");

            matchesInfo.summonerSearchApi(summonerName);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    search_progress.show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            summoner = matchesInfo.getTotalMap().get(summonerName);
                            summoner.getSummoner().setTimer();
                            editor.putString(summoner.getSummoner().getName(), summoner.getSummoner().getProfileIconId() + "," + summoner.getSummoner().getSummonerLevel());
                            editor.commit();

                            ingamesInfo.summonerUpdateMysql(summoner.getSummoner());

                            if (search_progress.isShowing()) {
                                search_progress.dismiss();
                                Intent intent = new Intent(MatchActivity.this, MatchActivity.class);
                                intent.putExtra("summoner", summonerName);
                                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent);
                            }
                        }
                    }, 500);
                }
            });
        } else {
            Toast.makeText(this, summoner.getSummoner().getTimer() + "초 후에 갱신 가능합니다.", Toast.LENGTH_LONG).show();
        }
    }

    public void matchInfoSetting() {
        if (summoner != null) {
            Glide.with(MatchActivity.this).load(UrlService.icon_url + summoner.getSummoner().getProfileIconId() + ".png").into(icon_summoner);
            name_summoner.setText(summoner.getSummoner().getName());
            level_summoner.setText(String.valueOf(summoner.getSummoner().getSummonerLevel()));
            if (summoner.getLeagueEntry() != null) {
                for (LeagueEntryDTO i : summoner.getLeagueEntry()) {
                    i.setPercent();
                    if (i.getQueueType().contains("SOLO")) {
                        border_summoner.setBackgroundResource(border.get(i.getTier()));
                        solo_league.setImageResource(emblem.get(i.getTier()));
                        solo_tier.setText(i.getTier() + " " + i.getRank());
                        solo_point.setText(String.valueOf(i.getLeaguePoints()) + "포인트");
                        solo_kda.setText(String.valueOf(i.getWins()) + "승 " + String.valueOf(i.getLosses()) + "패 승률 " + String.valueOf(i.getPercent()) + "%");
                    } else {
                        team_league.setImageResource(emblem.get(i.getTier()));
                        team_tier.setText(i.getTier() + " " + i.getRank());
                        team_point.setText(String.valueOf(i.getLeaguePoints()) + "포인트");
                        team_kda.setText(String.valueOf(i.getWins()) + "승 " + String.valueOf(i.getLosses()) + "패 승률 " + String.valueOf(i.getPercent()) + "%");
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }
    }
}
