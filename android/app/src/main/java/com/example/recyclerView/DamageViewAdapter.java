package com.example.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.anyDTO.ChampionDTO;
import com.example.apiDTO.ParticipantDTO;
import com.example.apiDTO.ParticipantIdentityDTO;
import com.example.nagneo_gg.R;
import com.example.retrofitController.IngamesInfo;
import com.example.retrofitController.UrlService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DamageViewAdapter extends RecyclerView.Adapter<DamageViewAdapter.ViewHolder> implements Serializable {
    private IngamesInfo ingamesInfo = IngamesInfo.getInstance();
    private OnDamageClickListener listener = null;

    private Context context;

    private long damage[] = new long[5];
    private long max;
    private int start;

    private List<ParticipantIdentityDTO> list;
    private List<ParticipantDTO> list_detail;
    private Map<Integer, ChampionDTO> champion_list;

    public DamageViewAdapter(List<ParticipantIdentityDTO> list, List<ParticipantDTO> list_detail, List<Long> damage, long max, int start) {
        this.list = list;
        this.list_detail = list_detail;
        this.max = max;
        this.start = start;

        for (int i = 0; i < this.damage.length; i++) this.damage[i] = damage.get(i);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.view_matchdamage, parent, false);
        DamageViewAdapter.ViewHolder viewHolder = new DamageViewAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DamageViewAdapter.ViewHolder holder, int position) {
        if (max == 1000) {
            int pos = position + start;
            holder.damage_name.setText(list.get(pos).getPlayer().getSummonerName());
            holder.damage_graph.setText(String.valueOf(list_detail.get(pos).getStats().getTotalDamageDealtToChampions()));

            holder.match_damage.setMax((int) max);
            holder.match_damage.setProgress((int) list_detail.get(pos).getStats().getTotalDamageDealtToChampions());

            Glide.with(context).load(UrlService.champion_url + champion_list.get(list_detail.get(pos).getChampionId()).getEngid() + ".png").apply(RequestOptions.bitmapTransform(new RoundedCorners(100))).into(holder.damage_icon);

            holder.damage_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pos != RecyclerView.NO_POSITION) {
                        try {
                            listener.onSummonerClick(v, position, list.get(pos).getPlayer().getSummonerName().replace(" ", "").toLowerCase());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            holder.damage_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pos != RecyclerView.NO_POSITION) {
                        try {
                            listener.onChampionClick(v, position, champion_list.get(list_detail.get(pos).getChampionId()).getKorid());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (damage[position] == list_detail.get(i).getStats().getTotalDamageDealtToChampions()) {
                    String damages = String.valueOf(list_detail.get(i).getStats().getTotalDamageDealtToChampions());
                    if (damages.length() > 3)
                        damages = damages.substring(0, damages.length() - 3) + "," + damages.substring(damages.length() - 3);

                    holder.damage_name.setText(list.get(i).getPlayer().getSummonerName());
                    holder.damage_graph.setText(damages);

                    holder.match_damage.setMax((int) max);
                    holder.match_damage.setProgress((int) list_detail.get(i).getStats().getTotalDamageDealtToChampions());

                    Glide.with(context).load(UrlService.champion_url + champion_list.get(list_detail.get(i).getChampionId()).getEngid() + ".png").apply(RequestOptions.bitmapTransform(new RoundedCorners(100))).into(holder.damage_icon);

                    int finalI = i;
                    holder.damage_name.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (position != RecyclerView.NO_POSITION) {
                                try {
                                    listener.onSummonerClick(v, position, list.get(finalI).getPlayer().getSummonerName().replace(" ", "").toLowerCase());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

                    holder.damage_icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (position != RecyclerView.NO_POSITION) {
                                try {
                                    listener.onChampionClick(v, position, champion_list.get(list_detail.get(finalI).getChampionId()).getKorid());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

                    break;
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return damage.length;
    }

    public void adapterSetting() {
        champion_list = ingamesInfo.getChampionMap();
    }

    public void setOnItemClickListener(OnDamageClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar match_damage;
        private TextView damage_name, damage_graph;
        private ImageView damage_icon;

        ViewHolder(View itemView) {
            super(itemView);
            match_damage = itemView.findViewById(R.id.match_damage);
            damage_name = itemView.findViewById(R.id.damage_name);
            damage_graph = itemView.findViewById(R.id.damage_graph);
            damage_icon = itemView.findViewById(R.id.damage_icon);
        }
    }

    public interface OnDamageClickListener {
        void onSummonerClick(View v, int position, String summoner) throws Exception;

        void onChampionClick(View v, int position, String champion);
    }
}
