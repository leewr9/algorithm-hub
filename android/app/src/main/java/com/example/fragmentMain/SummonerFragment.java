package com.example.fragmentMain;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiDTO.MatchDTO;
import com.example.nagneo_gg.MainActivity;
import com.example.nagneo_gg.MatchActivity;
import com.example.nagneo_gg.R;
import com.example.recyclerView.LikeViewAdapter;
import com.example.recyclerView.MatchViewAdapter;
import com.example.recyclerView.SearchViewAdapter;
import com.example.retrofitController.IngamesInfo;
import com.example.retrofitController.MatchesInfo;

import java.util.ArrayList;
import java.util.List;

public class SummonerFragment extends Fragment implements MainActivity.OnBackPressedListener {
    private MatchesInfo matchesInfo = MatchesInfo.getInstance();
    private IngamesInfo ingamesInfo = IngamesInfo.getInstance();

    public static boolean isPageOpen = false;
    private String summonerName;

    //Etc
    private MainActivity mainActivity;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Intent intent;
    private Handler handler;
    private Activity activity;

    private MatchViewAdapter adapter;
    private LikeViewAdapter like;
    private SearchViewAdapter searches;

    private Animation search_start, start_end;

    //Widget
    private LinearLayout recent_searches;
    private EditText search_summoner;

    private View view;

    private ProgressDialog search_progress;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    private RecyclerView view_like, view_search;
    private ImageButton search, button_searches;
    private ImageView mainlogo, riot_web, lol_web;
    private TextView riot_webs, lol_webs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_summoner, container, false);
        matchesInfo.setTotalMap(ingamesInfo.getSummonerMap());
        matchesInfo.setRuneMap(ingamesInfo.getRuneMap());
        matchesInfo.setSpellMap(ingamesInfo.getSpellMap());

        sharedPreferences = getContext().getSharedPreferences("Like", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        search_summoner = view.findViewById(R.id.search_summoner);
        search = view.findViewById(R.id.search);
        button_searches = view.findViewById(R.id.button_searches);

        recent_searches = view.findViewById(R.id.recent_searches);
        view_like = view.findViewById(R.id.view_like);
        view_search = view.findViewById(R.id.view_search);

        mainlogo = view.findViewById(R.id.mainlogo);
        riot_web = view.findViewById(R.id.riot_web);
        lol_web = view.findViewById(R.id.lol_web);

        riot_webs = view.findViewById(R.id.riot_webs);
        lol_webs = view.findViewById(R.id.lol_webs);

        search_start = AnimationUtils.loadAnimation(view.getContext(), R.anim.search_start);
        start_end = AnimationUtils.loadAnimation(view.getContext(), R.anim.search_end);

        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        search_start.setAnimationListener(animListener);
        start_end.setAnimationListener(animListener);

        List<MatchDTO> list = new ArrayList<>();
        adapter = new MatchViewAdapter(list);
        matchesInfo.setAdapter(adapter);

        searches = new SearchViewAdapter(ingamesInfo.getSummonerMap());
        view_search.setLayoutManager(new LinearLayoutManager(getContext()));
        view_search.setAdapter(searches);

        mainlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAttach(getContext());
                if (isPageOpen) {
                    recent_searches.startAnimation(start_end);
                } else {
                    like = new LikeViewAdapter(getContext());
                    view_like.setLayoutManager(new LinearLayoutManager(getContext()));
                    view_like.setAdapter(like);

                    like.setOnItemClickListener(new LikeViewAdapter.OnSummonerClickListener() {
                        @Override
                        public void onSummonerClick(View v, int position, String summoner) throws Exception {
                            searchSummoner(v, summoner);
                        }

                        @Override
                        public void onSummonerRemove(View v, int position, String summoner) throws Exception {
                            like.remove(position);
                            editor.remove(summoner);
                            editor.commit();
                        }
                    });

                    mainActivity.setOnBackPressedListener(FragmentAdapter.listener_summoner);
                    recent_searches.setVisibility(View.VISIBLE);
                    recent_searches.startAnimation(search_start);
                }
            }
        });

        riot_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.riotgames.com/en")));
            }
        });

        lol_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://kr.leagueoflegends.com/ko-kr/")));
            }
        });

        riot_webs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.riotgames.com/en")));
            }
        });

        lol_webs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://kr.leagueoflegends.com/ko-kr/")));
            }
        });

        searches.setOnItemClickListener(new SearchViewAdapter.OnSummonerClickListener() {
            @Override
            public void onSummonerClick(View v, int position, String summoner) throws Exception {
                searchSummoner(v, summoner);
            }
        });

        button_searches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                activity.setOnBackPressedListener(null);
                if (isPageOpen) {
                    recent_searches.startAnimation(start_end);
                } else {
                    recent_searches.setVisibility(View.VISIBLE);
                    recent_searches.startAnimation(search_start);
                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    searchSummoner(v, " ");
                } catch (Exception e) {
                    Toast.makeText(mainActivity, "잠시 후, 다시 시도하십시오.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        search_summoner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searches.searchSettingMap();
            }
        });

        search_summoner.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        try {
                            searchSummoner(v, " ");
                        } catch (Exception e) {
                            Toast.makeText(mainActivity, "잠시 후, 다시 시도하십시오.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        search_summoner.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    view_search.setVisibility(View.VISIBLE);
                    searches.getFilter().filter(s);
                } else {
                    view_search.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return view;
    }

    @Override
    public void onBackPressed() {
        if (isPageOpen) {
            recent_searches.startAnimation(start_end);
            mainActivity.setOnBackPressedListener(null);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity)
            activity = (Activity) context;
    }

    public void searchSummoner(View v, String summoner) throws Exception {
        if (!summoner.equals(" ")) search_summoner.setText(summoner);
        intent = new Intent(v.getContext(), MatchActivity.class);
        handler = new Handler();

        builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle(Html.fromHtml("<font color='#9F0000'>NAGNEO.GG</fotn>")).setMessage(Html.fromHtml("<font color='#FFFFFF'>존재하지 않는 소환사입니다.</fotn>"));
        alertDialog = builder.create();

        search_progress = new ProgressDialog(v.getContext());
        search_progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        search_progress.setMessage("소환사 정보를 검색 중입니다.");

        summonerName = search_summoner.getText().toString().replace(" ", "").toLowerCase();
        search_summoner.setText("");
        matchesInfo.setAdapter(null);

        editor = sharedPreferences.edit();
        if (matchesInfo.chkSummoner(summonerName)) {
            adapter = new MatchViewAdapter(matchesInfo.getTotalMap().get(summonerName).getMatches());
            adapter.setSummonerName(summonerName);
            matchesInfo.setAdapter(adapter);

            if (matchesInfo.getTotalMap().get(summonerName).getLeagueEntry() == null)
                matchesInfo.leagueSearchApi(summonerName);
            else if (matchesInfo.getTotalMap().get(summonerName).getSummoner().getMatches() == null)
                matchesInfo.matchesSearchApi(summonerName);
        } else {
            matchesInfo.summonerSearchApi(summonerName);
            Thread.sleep(500);
            if (matchesInfo.getTotalMap().containsKey(summonerName))
                ingamesInfo.summonerInsertMysql(matchesInfo.getTotalMap().get(summonerName).getSummoner());
            else alertDialog.show();
        }

        if (!alertDialog.isShowing()) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    search_progress.show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            intent.putExtra("summoner", summonerName);
                            startActivity(intent);
                            if (search_progress.isShowing()) search_progress.dismiss();
                        }
                    }, 500);
                }
            });
        }
    }

    public class SlidingPageAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            if (isPageOpen) {
                recent_searches.setVisibility(View.INVISIBLE);
                isPageOpen = false;
            } else {
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }


}