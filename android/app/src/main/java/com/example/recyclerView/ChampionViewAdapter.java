package com.example.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitController.UrlService;
import com.example.anyDTO.ChampionDTO;
import com.example.nagneo_gg.R;

import java.util.ArrayList;
import java.util.List;

public class ChampionViewAdapter extends RecyclerView.Adapter<ChampionViewAdapter.ViewHolder> implements Filterable {
    private Context context;
    private OnChampionClickListener listener = null;

    private List<ChampionDTO> list_search;
    private List<ChampionDTO> list;


    public ChampionViewAdapter(List<ChampionDTO> list) {
        this.list = list;
        this.list_search = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.view_champion, parent, false);
        ChampionViewAdapter.ViewHolder viewHolder = new ChampionViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pos = position * 5;
        String name = null;
        if (pos < list.size()) {
            for (int i = 0; i < holder.image.length; i++) {
                if (pos < list.size()) {
                    name = list.get(pos).getKorid();
                    if (name.length() > 4) name = name.substring(0, 3) + "…";
                    holder.text[i].setText(name);
                    Glide.with(context).load(UrlService.champion_url + list.get(pos++).getEngid() + ".png").into(holder.image[i]);
                } else {
                    holder.text[i].setText("");
                    holder.image[i].setImageDrawable(null);
                }
            }
        } else {
            for (int i = 0; i < holder.image.length; i++) {
                holder.text[i].setText("");
                holder.image[i].setImageDrawable(null);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size() / 5 + 3;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    list = list_search;
                } else {
                    ArrayList<ChampionDTO> list_save = new ArrayList<>();
                    String string = null;
                    for (ChampionDTO i : list_search) {
                        if (constraint.charAt(0) >= 0xAC00) string = i.getKorid();
                        else string = getKoreaChar(i.getKorid());

                        if (string.contains(charString)) list_save.add(i);
                    }
                    list = list_save;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<ChampionDTO>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public String getKoreaChar(String charString) {
        String string = "";
        String strings[] = {
                "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ",
                "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ",
                "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ",
                "ㅋ", "ㅌ", "ㅍ", "ㅎ"
        };

        if (charString.length() > 0) {
            for (int i = 0; i < charString.length(); i++) {
                char charAt = charString.charAt(i);
                if (charAt >= 0xAC00) {
                    int uniVal = charAt - 0xAC00;
                    int index = ((uniVal - (uniVal % 28)) / 28) / 21;

                    string += strings[index];
                }
            }
        }

        return string;
    }

    public void setOnItemClickListener(OnChampionClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_champion1, image_champion2, image_champion3, image_champion4, image_champion5;
        private TextView text_champion1, text_champion2, text_champion3, text_champion4, text_champion5;
        private ImageView[] image = new ImageView[5];
        private TextView[] text = new TextView[5];

        ViewHolder(View itemView) {
            super(itemView);
            image[0] = image_champion1 = itemView.findViewById(R.id.image_champion1);
            image[1] = image_champion2 = itemView.findViewById(R.id.image_champion2);
            image[2] = image_champion3 = itemView.findViewById(R.id.image_champion3);
            image[3] = image_champion4 = itemView.findViewById(R.id.image_champion4);
            image[4] = image_champion5 = itemView.findViewById(R.id.image_champion5);

            text[0] = text_champion1 = itemView.findViewById(R.id.text_champion1);
            text[1] = text_champion2 = itemView.findViewById(R.id.text_champion2);
            text[2] = text_champion3 = itemView.findViewById(R.id.text_champion3);
            text[3] = text_champion4 = itemView.findViewById(R.id.text_champion4);
            text[4] = text_champion5 = itemView.findViewById(R.id.text_champion5);

            for (int i = 0; i < image.length; i++) {
                int finalI = i;
                image[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            if (listener != null && pos * 5 + finalI < list_search.size())
                                listener.onChampionClick(v, pos, list.get(pos * 5 + finalI));
                        }
                    }
                });
            }
        }
    }

    public interface OnChampionClickListener {
        void onChampionClick(View v, int position, ChampionDTO champion);
    }
}
