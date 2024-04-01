package com.example.fragmentMain;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.retrofitController.IngamesInfo;
import com.example.retrofitController.UrlService;
import com.example.anyDTO.ChampionDTO;
import com.example.recyclerView.ChampionViewAdapter;
import com.example.nagneo_gg.MainActivity;
import com.example.nagneo_gg.R;

import java.util.List;
import java.util.Map;

public class ChampionFragment extends Fragment implements MainActivity.OnBackPressedListener {
    private IngamesInfo ingamesInfo = IngamesInfo.getInstance();

    public static boolean isPageOpen = false;
    private String[] qwer = {"Q", "W", "E", "R"};

    private List<ChampionDTO> list;
    private Map<Integer, Integer> lists_champion;

    //Etc
    private Animation info_start, info_end;
    private MainActivity mainActivity;

    private SkinSlideAdapter skinSlideAdapter;
    private ChampionViewAdapter adapter;

    //Widger
    private LinearLayout recent_champion;
    private EditText search_champion;

    private View view;
    private RecyclerView view_champion;

    private ViewPager slide_champion;
    private Button button_before, button_after;
    private TextView text_champion, text_title, text_skill1, text_skill2, text_skin;

    private ImageButton button_champion;
    private ImageView image_champion, image_skill1, image_skill2, image_skill3, image_skill4, image_skill5;
    private ImageView[] image_skill = new ImageView[5];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_champion, container, false);
        search_champion = view.findViewById(R.id.search_champion);

        view_champion = view.findViewById(R.id.view_champion);
        view_champion.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = ingamesInfo.getChampionAdapter();
        view_champion.setAdapter(adapter);

        list = ingamesInfo.getChampionList();
        lists_champion = ingamesInfo.getChampionInteger();

        recent_champion = view.findViewById(R.id.recent_champion);
        text_champion = view.findViewById(R.id.text_champion);
        text_title = view.findViewById(R.id.text_title);
        button_champion = view.findViewById(R.id.button_champion);
        slide_champion = view.findViewById(R.id.slide_champion);

        image_skill[0] = image_skill1 = view.findViewById(R.id.image_skill1);
        image_skill[1] = image_skill2 = view.findViewById(R.id.image_skill2);
        image_skill[2] = image_skill3 = view.findViewById(R.id.image_skill3);
        image_skill[3] = image_skill4 = view.findViewById(R.id.image_skill4);
        image_skill[4] = image_skill5 = view.findViewById(R.id.image_skill5);

        text_skill1 = view.findViewById(R.id.text_skill1);
        text_skill2 = view.findViewById(R.id.text_skill2);

        button_after = view.findViewById(R.id.button_after);
        button_before = view.findViewById(R.id.button_before);

        info_start = AnimationUtils.loadAnimation(view.getContext(), R.anim.info_start);
        info_end = AnimationUtils.loadAnimation(view.getContext(), R.anim.info_end);

        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        info_start.setAnimationListener(animListener);
        info_end.setAnimationListener(animListener);

        search_champion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        button_champion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPageOpen) {
                    recent_champion.startAnimation(info_end);
                }
            }
        });

        adapter.setOnItemClickListener(new ChampionViewAdapter.OnChampionClickListener() {
            @Override
            public void onChampionClick(View v, int position, ChampionDTO champion) {
                if (!isPageOpen) {
                    champion_detail(v, position, champion);
                    mainActivity.setOnBackPressedListener(FragmentAdapter.listener_champion);
                    recent_champion.setVisibility(View.VISIBLE);
                    recent_champion.startAnimation(info_start);
                }
            }
        });

        return view;
    }

    @Override
    public void onBackPressed() {
        if (isPageOpen) {
            recent_champion.startAnimation(info_end);
            mainActivity.setOnBackPressedListener(null);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void champion_detail(View v, int position, ChampionDTO champion) {
        onAttach(v.getContext());
        skinSlideAdapter = new SkinSlideAdapter(v.getContext(), champion.getSkin(), champion.getEngid());
        slide_champion.setAdapter(skinSlideAdapter);

        text_champion.setText(champion.getKorid());
        text_title.setText(champion.getTitle());
        text_skill1.setText(champion.getSkill().get(4).getKorid() + " [패시브]");
        text_skill2.setText(Html.fromHtml(champion.getSkill().get(4).getDescription()));

        Glide.with(recent_champion.getContext()).load(UrlService.skill_url + "passive/" + champion.getSkill().get(4).getEngid()).into(image_skill[0]);

        image_skill[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_skill1.setText(champion.getSkill().get(4).getKorid() + " [패시브]");
                text_skill2.setText(Html.fromHtml(champion.getSkill().get(4).getDescription()));
            }
        });

        for (int i = 0; i < champion.getSkill().size() - 1; i++) {
            Glide.with(recent_champion.getContext()).load(UrlService.skill_url + "spell/" + champion.getSkill().get(i).getEngid() + ".png").into(image_skill[i + 1]);

            int finalI = i;
            image_skill[i + 1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    text_skill1.setText(champion.getSkill().get(finalI).getKorid() + " [" + qwer[finalI] + "스킬]");
                    text_skill2.setText(Html.fromHtml(champion.getSkill().get(finalI).getDescription()));
                }
            });
        }
        if (lists_champion.get(champion.getNumid()) == lists_champion.size() - 1) {
            button_after.setText("마지막 챔피언입니다.");
        } else {
            button_after.setText("다음 챔피언→");
            button_after.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    champion_detail(v, position, list.get(lists_champion.get(champion.getNumid()) + 1));
                }
            });
        }
        if (lists_champion.get(champion.getNumid()) == 0) {
            button_before.setText("처음 챔피언입니다.");
        } else {
            button_before.setText("←이전 챔피언");
            button_before.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    champion_detail(v, position, list.get(lists_champion.get(champion.getNumid()) - 1));
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
                recent_champion.setVisibility(View.GONE);
                isPageOpen = false;
            } else {
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }

    public class SkinSlideAdapter extends PagerAdapter {

        private LayoutInflater inflater;
        private Context context;
        private List<ChampionDTO.SkinDTO> skin;
        private String champion;

        public SkinSlideAdapter(Context context, List<ChampionDTO.SkinDTO> skin, String champion) {
            this.context = context;
            this.skin = skin;
            this.champion = champion;
        }

        @Override
        public int getCount() {
            return skin.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.slide_champion, container, false);
            image_champion = v.findViewById(R.id.image_champion);
            text_skin = v.findViewById(R.id.text_skin);
            Glide.with(v.getContext()).load(UrlService.skin_url + champion + "_" + skin.get(position).getNum() + ".jpg").into(image_champion);
            image_champion.setBackgroundResource(R.drawable.image_border);

            text_skin.setText(skin.get(position).getKorid());
            if (position == 0) text_skin.setText("기본 스킨");

            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.invalidate();
        }
    }
}