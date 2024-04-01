package com.example.recyclerView;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitController.IngamesInfo;
import com.example.retrofitController.MatchesInfo;
import com.example.retrofitController.UrlService;
import com.example.anyDTO.ChampionDTO;
import com.example.anyDTO.ItemDTO;
import com.example.apiDTO.ParticipantDTO;
import com.example.apiDTO.ParticipantIdentityDTO;
import com.example.nagneo_gg.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetailViewAdapter extends RecyclerView.Adapter<DetailViewAdapter.ViewHolder> implements Serializable {
    private MatchesInfo matchesInfo = MatchesInfo.getInstance();
    private IngamesInfo ingamesInfo = IngamesInfo.getInstance();

    private Context context;
    private OnDetailClickListener listener = null;

    private long time;

    private List<ParticipantIdentityDTO> list;
    private List<ParticipantDTO> list_detail;
    private Map<Integer, ChampionDTO> champion_list;
    private Map<String, ItemDTO> item_list;


    public DetailViewAdapter(List<ParticipantIdentityDTO> list, List<ParticipantDTO> list_detail, int start, long time) {
        this.list = new ArrayList<>();
        this.list_detail = new ArrayList<>();
        this.time = time;

        for (int i = start; i < start + 5; i++) {
            this.list.add(list.get(i));
            this.list_detail.add(list_detail.get(i));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.view_matchdetail, parent, false);
        DetailViewAdapter.ViewHolder viewHolder = new DetailViewAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DetailViewAdapter.ViewHolder holder, int position) {
        int item[] = list_detail.get(position).getStats().getItem();
        String kda = String.valueOf(list_detail.get(position).getStats().getKills()) + " / <font color='#9F0000'>" + String.valueOf(list_detail.get(position).getStats().getDeaths()) + "</font> / " + String.valueOf(list_detail.get(position).getStats().getAssists());
        list_detail.get(position).getStats().setMinuteMinionsKilled(time);

        Glide.with(context).load(UrlService.champion_url + champion_list.get(list_detail.get(position).getChampionId()).getEngid() + ".png").into(holder.match_icon);
        holder.match_name.setText(list.get(position).getPlayer().getSummonerName());
        holder.match_kda.setText(Html.fromHtml(kda + " 평점 " + String.format("%.2f", list_detail.get(position).getStats().getKda())));
        holder.match_level.setText(String.valueOf(list_detail.get(position).getStats().getChampLevel()));
        holder.match_cs.setText("CS " + String.valueOf(list_detail.get(position).getStats().getTotalMinionsKilled() + list_detail.get(position).getStats().getNeutralMinionsKilled()) + " [" + list_detail.get(position).getStats().getMinuteMinionsKilled() + "]");

        Glide.with(context).load(UrlService.spell_url + matchesInfo.getSpellName(list_detail.get(position).getSpell1Id()) + ".png").into(holder.match_spell01);
        Glide.with(context).load(UrlService.spell_url + matchesInfo.getSpellName(list_detail.get(position).getSpell2Id()) + ".png").into(holder.match_spell02);
        Glide.with(context).load(UrlService.rune_url + matchesInfo.getRuneName(list_detail.get(position).getStats().getPerk0()) + ".png").into(holder.match_rune01);
        Glide.with(context).load(UrlService.rune_url + matchesInfo.getRuneName(list_detail.get(position).getStats().getPerkSubStyle()) + ".png").into(holder.match_rune02);

        holder.match_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != RecyclerView.NO_POSITION) {
                    try {
                        listener.onChampionClick(v, position, champion_list.get(list_detail.get(position).getChampionId()).getKorid());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        for (int i = 0; i < holder.match_item.length; i++) {
            if (item_list.containsKey(String.valueOf(item[i]))) {
                Glide.with(context).load(UrlService.item_url + item[i] + ".png").into(holder.match_item[i]);
                int finalI = i;
                holder.match_item[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position != RecyclerView.NO_POSITION) {
                            try {
                                listener.onItemClick(v, position, item_list.get(String.valueOf(item[finalI])));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            } else {
                holder.match_item[i].setImageResource(R.drawable.empty_item);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void adapterSetting() {
        champion_list = ingamesInfo.getChampionMap();
        item_list = ingamesInfo.getItemMap();
    }

    public void setOnItemClickListener(OnDetailClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView match_name, match_kda, match_level, match_cs;
        ImageView match_icon, match_item00, match_item01, match_item02, match_item03, match_item04, match_item05, match_item06, match_rune01, match_rune02, match_spell01, match_spell02;

        ImageView match_item[] = new ImageView[7];

        ViewHolder(View itemView) {
            super(itemView);
            match_name = itemView.findViewById(R.id.match_name);
            match_kda = itemView.findViewById(R.id.match_kda);
            match_level = itemView.findViewById(R.id.match_level);
            match_cs = itemView.findViewById(R.id.match_cs);

            match_icon = itemView.findViewById(R.id.match_icon);
            match_item[0] = match_item00 = itemView.findViewById(R.id.match_item00);
            match_item[1] = match_item01 = itemView.findViewById(R.id.match_item01);
            match_item[2] = match_item02 = itemView.findViewById(R.id.match_item02);
            match_item[3] = match_item03 = itemView.findViewById(R.id.match_item03);
            match_item[4] = match_item04 = itemView.findViewById(R.id.match_item04);
            match_item[5] = match_item05 = itemView.findViewById(R.id.match_item05);
            match_item[6] = match_item06 = itemView.findViewById(R.id.match_item06);

            match_rune01 = itemView.findViewById(R.id.match_rune01);
            match_rune02 = itemView.findViewById(R.id.match_rune02);
            match_spell01 = itemView.findViewById(R.id.match_spell01);
            match_spell02 = itemView.findViewById(R.id.match_spell02);

            match_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        try {
                            listener.onSummonerClick(v, pos, list.get(pos).getPlayer().getSummonerName().replace(" ", "").toLowerCase());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    public interface OnDetailClickListener {
        void onSummonerClick(View v, int position, String summoner) throws Exception;

        void onChampionClick(View v, int position, String champion);

        void onItemClick(View v, int position, ItemDTO item);
    }

}
