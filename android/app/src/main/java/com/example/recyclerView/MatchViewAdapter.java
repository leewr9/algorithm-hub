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
import com.example.apiDTO.MatchDTO;
import com.example.nagneo_gg.R;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class MatchViewAdapter extends RecyclerView.Adapter<MatchViewAdapter.ViewHolder> implements Serializable {
    private MatchesInfo matchesInfo = MatchesInfo.getInstance();
    private IngamesInfo ingamesInfo = IngamesInfo.getInstance();

    private Context context;
    private OnMatchClickListener listener = null;

    private String summonerName;

    private List<MatchDTO> list;
    private Map<Integer, ChampionDTO> champion_list;
    private Map<String, ItemDTO> item_list;

    public MatchViewAdapter(List<MatchDTO> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.view_matchlist, parent, false);
        MatchViewAdapter.ViewHolder viewHolder = new MatchViewAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MatchViewAdapter.ViewHolder holder, int position) {
        holder.tv_TypeDate.setText(list.get(position).getType() + "\n" + list.get(position).getCreation() + "\n\n\n" + "상세보기 ＞");

        for (int i = 0; i < list.get(position).getParticipantIdentities().size(); i++) {
            if (summonerName.equals(list.get(position).getParticipantIdentities().get(i).getPlayer().getSummonerName().replace(" ", "").toLowerCase())) {
                int items[] = list.get(position).getParticipants().get(i).getStats().getItem();
                String kda = String.valueOf(list.get(position).getParticipants().get(i).getStats().getKills()) + " / <font color='#9F0000'>" + String.valueOf(list.get(position).getParticipants().get(i).getStats().getDeaths()) + "</font> / " + String.valueOf(list.get(position).getParticipants().get(i).getStats().getAssists() + "<br>");
                String win = null;
                int team = 0;

                Glide.with(context).load(UrlService.champion_url + champion_list.get(list.get(position).getParticipants().get(i).getChampionId()).getEngid() + ".png").into(holder.iv_ChampionIcon);
                Glide.with(context).load(UrlService.spell_url + matchesInfo.getSpellName(list.get(position).getParticipants().get(i).getSpell1Id()) + ".png").into(holder.iv_Spell01);
                Glide.with(context).load(UrlService.spell_url + matchesInfo.getSpellName(list.get(position).getParticipants().get(i).getSpell2Id()) + ".png").into(holder.iv_Spell02);
                Glide.with(context).load(UrlService.rune_url + matchesInfo.getRuneName(list.get(position).getParticipants().get(i).getStats().getPerk0()) + ".png").into(holder.iv_Rune01);
                Glide.with(context).load(UrlService.rune_url + matchesInfo.getRuneName(list.get(position).getParticipants().get(i).getStats().getPerkSubStyle()) + ".png").into(holder.iv_Rune02);
                holder.tv_KDA1.setText(Html.fromHtml(kda));
                holder.tv_KDA.setText(String.format("%.2f", list.get(position).getParticipants().get(i).getStats().getKda()) + ":1 평점");

                if (list.get(position).getTeams().get(0).getTeamId() == list.get(position).getParticipants().get(i).getTeamId()) {
                    win = list.get(position).getTeams().get(0).getWin();
                    team = list.get(position).getTeams().get(0).getTeamId();
                } else {
                    win = list.get(position).getTeams().get(1).getWin();
                    team = list.get(position).getTeams().get(1).getTeamId();
                }

                if (Integer.parseInt(list.get(position).getGameTime().substring(0, 2)) < 5) {
                    holder.tv_Match_Duration.setBackgroundResource(R.color.gray);
                    holder.tv_Match_Duration.setText("다시하기\nㅡ\n" + list.get(position).getGameTime());
                    win = "Regame";
                } else if (win.equals("Win")) {
                    holder.tv_Match_Duration.setBackgroundResource(R.color.blue);
                    holder.tv_Match_Duration.setText("　승리　\nㅡ\n" + list.get(position).getGameTime());
                } else {
                    holder.tv_Match_Duration.setBackgroundResource(R.color.red);
                    holder.tv_Match_Duration.setText("　패배　\nㅡ\n" + list.get(position).getGameTime());
                }

                String finalWin = win;
                int finalTeam = team;

                int finalI = i;
                holder.iv_ChampionIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onChampionClick(v, position, champion_list.get(list.get(position).getParticipants().get(finalI).getChampionId()).getKorid());
                    }
                });

                holder.tv_TypeDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onMatchClick(v, position, list.get(position), finalWin, finalTeam);
                    }
                });
                for (int j = 0; j < holder.iv_item.length; j++) {
                    if (item_list.containsKey(String.valueOf(items[j]))) {
                        Glide.with(context).load(UrlService.item_url + items[j] + ".png").into(holder.iv_item[j]);
                        int finalJ = j;
                        holder.iv_item[j].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (listener != null && position != RecyclerView.NO_POSITION) {
                                    listener.onItemClick(v, position, item_list.get(String.valueOf(items[finalJ])));
                                }
                            }
                        });
                    } else {
                        holder.iv_item[j].setImageResource(R.drawable.empty_item);
                    }
                }
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setSummonerName(String name) {
        this.summonerName = name;
    }

    public void adapterSetting() {
        champion_list = ingamesInfo.getChampionMap();
        item_list = ingamesInfo.getItemMap();
    }

    public void setOnItemClickListener(OnMatchClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_Match_Duration, tv_KDA, tv_KDA1, tv_TypeDate;
        private ImageView iv_ChampionIcon, iv_Spell01, iv_Spell02, iv_Rune01, iv_Rune02, iv_item0, iv_item01, iv_item02, iv_item03, iv_item04, iv_item05, iv_item06;
        private ImageView iv_item[] = new ImageView[7];

        ViewHolder(View itemView) {
            super(itemView);

            tv_Match_Duration = itemView.findViewById(R.id.tv_Match_Duration);
            tv_KDA = itemView.findViewById(R.id.tv_KDA);
            tv_KDA1 = itemView.findViewById(R.id.tv_KDA1);
            tv_TypeDate = itemView.findViewById(R.id.tv_TypeDate);

            iv_ChampionIcon = itemView.findViewById(R.id.iv_ChampionIcon);

            iv_Spell01 = itemView.findViewById(R.id.iv_Spell01);
            iv_Spell02 = itemView.findViewById(R.id.iv_Spell02);

            iv_Rune01 = itemView.findViewById(R.id.iv_Rune01);
            iv_Rune02 = itemView.findViewById(R.id.iv_Rune02);

            iv_item[0] = iv_item0 = itemView.findViewById(R.id.iv_item00);
            iv_item[1] = iv_item01 = itemView.findViewById(R.id.iv_item01);
            iv_item[2] = iv_item02 = itemView.findViewById(R.id.iv_item02);
            iv_item[3] = iv_item03 = itemView.findViewById(R.id.iv_item03);
            iv_item[4] = iv_item04 = itemView.findViewById(R.id.iv_item04);
            iv_item[5] = iv_item05 = itemView.findViewById(R.id.iv_item05);
            iv_item[6] = iv_item06 = itemView.findViewById(R.id.iv_item06);
        }
    }

    public interface OnMatchClickListener {
        void onMatchClick(View v, int position, MatchDTO match, String win, int team);

        void onChampionClick(View v, int position, String champion);

        void onItemClick(View v, int position, ItemDTO item);
    }

}
