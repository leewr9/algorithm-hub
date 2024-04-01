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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import com.example.anyDTO.ItemDTO;
import com.example.recyclerView.ItemViewAdapter;
import com.example.nagneo_gg.MainActivity;
import com.example.nagneo_gg.R;

import java.util.List;
import java.util.Map;

public class ItemFragment extends Fragment implements MainActivity.OnBackPressedListener {
    private IngamesInfo ingamesInfo = IngamesInfo.getInstance();

    public static boolean isPageOpen = false;
    private Map<String, ItemDTO> list_item;

    //Etc
    private MainActivity mainActivity;
    private Animation info_start, info_end;
    private ArrayAdapter arrayAdapter;
    private mainSlideAdapter mainSlideAdapter;
    private subSlideAdapter subSlideAdapter;
    private ItemViewAdapter adapter;

    //Widget
    private EditText search_item;
    private LinearLayout recent_item;
    private Spinner spinner_item;

    private View view;

    private RecyclerView view_item;
    private ViewPager slide_sub, slide_main;

    private ImageView image_items;
    private ImageButton button_item;
    private TextView total_items, plaintaxt_items, description_items, text_items, text_tags, text_mains, text_subs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item, container, false);
        view_item = view.findViewById(R.id.view_item);
        list_item = ingamesInfo.getItemMap();

        isPageOpen = false;
        search_item = view.findViewById(R.id.search_item);
        search_item.setText("");
        spinner_item = view.findViewById(R.id.spinner_item);

        arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.등급, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_item.setAdapter(arrayAdapter);

        view_item.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = ingamesInfo.getItemAdapter();
        view_item.setAdapter(adapter);

        search_item.addTextChangedListener(new TextWatcher() {
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
        recent_item = view.findViewById(R.id.recent_item);

        info_start = AnimationUtils.loadAnimation(view.getContext(), R.anim.info_start);
        info_end = AnimationUtils.loadAnimation(view.getContext(), R.anim.info_end);

        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        info_start.setAnimationListener(animListener);
        info_end.setAnimationListener(animListener);

        image_items = view.findViewById(R.id.image_items);
        total_items = view.findViewById(R.id.total_items);
        plaintaxt_items = view.findViewById(R.id.plaintext_items);
        description_items = view.findViewById(R.id.description_items);

        button_item = view.findViewById(R.id.button_item);
        text_items = view.findViewById(R.id.text_items);
        text_tags = view.findViewById(R.id.text_tags);
        text_mains = view.findViewById(R.id.text_main);
        text_subs = view.findViewById(R.id.text_sub);

        slide_main = view.findViewById(R.id.slide_main);
        slide_sub = view.findViewById(R.id.slide_sub);

        button_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPageOpen) {
                    recent_item.startAnimation(info_end);
                }
            }
        });

        adapter.setOnItemClickListener(new ItemViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position, ItemDTO item) {
                if (!isPageOpen) {
                    mainActivity.setOnBackPressedListener(FragmentAdapter.listener_item);
                    recent_item.setVisibility(View.VISIBLE);
                    recent_item.startAnimation(info_start);
                    item_detail(item, v.getContext());
                }
            }
        });

        spinner_item.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        adapter.getFilter().filter("모든 아이템");
                        break;
                    case 1:
                        adapter.getFilter().filter("시작 아이템");
                        break;
                    case 2:
                        adapter.getFilter().filter("소모 아이템");
                        break;
                    case 3:
                        adapter.getFilter().filter("이동 아이템");
                        break;
                    case 4:
                        adapter.getFilter().filter("기본 아이템");
                        break;
                    case 5:
                        adapter.getFilter().filter("서사 아이템");
                        break;
                    case 6:
                        adapter.getFilter().filter("전설 아이템");
                        break;
                    case 7:
                        adapter.getFilter().filter("신화 아이템");
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    @Override
    public void onBackPressed() {
        if (isPageOpen) {
            recent_item.startAnimation(info_end);
            mainActivity.setOnBackPressedListener(null);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void item_detail(ItemDTO item, Context context) {
        onAttach(context);
        String total = String.valueOf(item.getTotal());
        String sell = String.valueOf(item.getSell());
        String base = String.valueOf(item.getBase());

        if (total.length() > 3)
            total = total.substring(0, total.length() - 3) + "," + total.substring(total.length() - 3);
        if (sell.length() > 3)
            sell = sell.substring(0, sell.length() - 3) + "," + sell.substring(sell.length() - 3);
        if (base.length() > 3)
            base = base.substring(0, base.length() - 3) + "," + base.substring(base.length() - 3);

        if (item.getMain().size() == 0) text_mains.setText("");
        else text_mains.setText("상위아이템");
        if (item.getSub().size() == 0) text_subs.setText("");
        else text_subs.setText("하위아이템 [조합가격 " + base + "원]");

        mainSlideAdapter = new mainSlideAdapter(context, item.getMain());
        subSlideAdapter = new subSlideAdapter(context, item.getSub());

        if (item.getMain().size() == 0 && item.getSub().size() != 0) {
            text_mains.setText("하위아이템 [조합가격 " + base + "원]");
            text_subs.setText("");
            slide_main.setAdapter(subSlideAdapter);
            slide_sub.setAdapter(mainSlideAdapter);
        } else {
            slide_main.setAdapter(mainSlideAdapter);
            slide_sub.setAdapter(subSlideAdapter);
        }

        Glide.with(context).load("http://ddragon.leagueoflegends.com/cdn/10.25.1/img/item/" + item.getNumid() + ".png").into(image_items);
        total_items.setText("구매가격 " + total + "원" + " | " + "판매가격 " + sell + "원");
        plaintaxt_items.setText(Html.fromHtml(item.getPlaintext()));
        if (item.getPlaintext().length() == 0)
            plaintaxt_items.setText(item.getKorid());
        description_items.setText(Html.fromHtml(item.getDescription()));

        text_items.setText(item.getKorid());
        text_tags.setText(item.getTag());
    }

    public class SlidingPageAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            if (isPageOpen) {
                recent_item.setVisibility(View.GONE);
                isPageOpen = false;
            } else {
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }

    public class subSlideAdapter extends PagerAdapter {

        private LayoutInflater inflater;
        private Context context;
        private List<ItemDTO.SubDTO> sub;
        private ImageView image_sub1, image_sub2, image_sub3, image_sub4, image_sub5;
        private ImageView[] image_sub = new ImageView[5];
        private TextView text_sub1, text_sub2, text_sub3, text_sub4, text_sub5;
        private TextView[] text_sub = new TextView[5];

        public subSlideAdapter(Context context, List<ItemDTO.SubDTO> sub) {
            this.context = context;
            this.sub = sub;
        }

        @Override
        public int getCount() {
            if (sub.size() % 5 != 0)
                return sub.size() / 5 + 1;
            else
                return sub.size() / 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.slide_sub, container, false);
            image_sub[0] = image_sub1 = v.findViewById(R.id.image_sub1);
            image_sub[1] = image_sub2 = v.findViewById(R.id.image_sub2);
            image_sub[2] = image_sub3 = v.findViewById(R.id.image_sub3);
            image_sub[3] = image_sub4 = v.findViewById(R.id.image_sub4);
            image_sub[4] = image_sub5 = v.findViewById(R.id.image_sub5);

            text_sub[0] = text_sub1 = v.findViewById(R.id.text_sub1);
            text_sub[1] = text_sub2 = v.findViewById(R.id.text_sub2);
            text_sub[2] = text_sub3 = v.findViewById(R.id.text_sub3);
            text_sub[3] = text_sub4 = v.findViewById(R.id.text_sub4);
            text_sub[4] = text_sub5 = v.findViewById(R.id.text_sub5);

            for (int i = 0; i < image_sub.length; i++) {
                if (position * 5 + i < sub.size()) {
                    String total = String.valueOf(list_item.get(sub.get(position * 5 + i).getSub()).getTotal());

                    if (total.length() > 3)
                        total = total.substring(0, total.length() - 3) + "," + total.substring(total.length() - 3);

                    Glide.with(context).load(UrlService.item_url + sub.get(position * 5 + i).getSub() + ".png").into(image_sub[i]);
                    text_sub[i].setText(list_item.get(sub.get(position * 5 + i).getSub()).getKorid() + "\n" + total + "원");
                    if (list_item.get(sub.get(position * 5 + i).getSub()).getKorid().length() > 8)
                        text_sub[i].setText(list_item.get(sub.get(position * 5 + i).getSub()).getKorid().substring(0, 7) + "…\n" + total + "원");

                    int finalI = i;
                    image_sub[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ItemDTO item = list_item.get(sub.get(position * 5 + finalI).getSub());
                            item_detail(item, v.getContext());
                        }
                    });


                } else {
                    Glide.with(context).load("").into(image_sub[i]);
                    image_sub[i].setBackground(null);
                    text_sub[i].setText("");
                }
            }

            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.invalidate();
        }
    }

    public class mainSlideAdapter extends PagerAdapter {

        private LayoutInflater inflater;
        private Context context;
        private List<ItemDTO.MainDTO> main;
        private ImageView image_main1, image_main2, image_main3, image_main4, image_main5;
        private ImageView[] image_main = new ImageView[5];
        private TextView text_main1, text_main2, text_main3, text_main4, text_main5;
        private TextView[] text_main = new TextView[5];

        public mainSlideAdapter(Context context, List<ItemDTO.MainDTO> main) {
            this.context = context;
            this.main = main;
        }

        @Override
        public int getCount() {
            if (main.size() % 5 != 0)
                return main.size() / 5 + 1;
            else
                return main.size() / 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.slide_main, container, false);

            image_main[0] = image_main1 = v.findViewById(R.id.image_main1);
            image_main[1] = image_main2 = v.findViewById(R.id.image_main2);
            image_main[2] = image_main3 = v.findViewById(R.id.image_main3);
            image_main[3] = image_main4 = v.findViewById(R.id.image_main4);
            image_main[4] = image_main5 = v.findViewById(R.id.image_main5);

            text_main[0] = text_main1 = v.findViewById(R.id.text_main1);
            text_main[1] = text_main2 = v.findViewById(R.id.text_main2);
            text_main[2] = text_main3 = v.findViewById(R.id.text_main3);
            text_main[3] = text_main4 = v.findViewById(R.id.text_main4);
            text_main[4] = text_main5 = v.findViewById(R.id.text_main5);

            for (int i = 0; i < image_main.length; i++) {
                if (position * 5 + i < main.size()) {
                    Glide.with(context).load(UrlService.item_url + main.get(position * 5 + i).getMain() + ".png").into(image_main[i]);
                    text_main[i].setText(list_item.get(main.get(position * 5 + i).getMain()).getKorid());
                    if (list_item.get(main.get(position * 5 + i).getMain()).getKorid().length() > 8)
                        text_main[i].setText(list_item.get(main.get(position * 5 + i).getMain()).getKorid().substring(0, 7) + "…");

                    int finalI = i;
                    image_main[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ItemDTO item = list_item.get(main.get(position * 5 + finalI).getMain());
                            item_detail(item, v.getContext());
                        }
                    });
                } else {
                    Glide.with(context).load("").into(image_main[i]);
                    image_main[i].setBackground(null);
                    text_main[i].setText("");
                }

            }

            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.invalidate();
        }
    }
}