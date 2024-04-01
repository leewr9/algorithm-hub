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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.retrofitController.UrlService;
import com.example.apiDTO.TotalDTO;
import com.example.nagneo_gg.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewAdapter.ViewHolder> implements Filterable {
    private Context context;
    private OnSummonerClickListener listener = null;

    private Map<String, TotalDTO> map;
    private List<TotalDTO> list;
    private List<TotalDTO> list_search;


    public SearchViewAdapter(Map<String, TotalDTO> map) {
        this.map = map;
        searchSettingMap();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.view_search, parent, false);
        SearchViewAdapter.ViewHolder viewHolder = new SearchViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(UrlService.icon_url + list.get(position).getSummoner().getProfileIconId() + ".png").apply(RequestOptions.bitmapTransform(new RoundedCorners(100))).into(holder.image_icons);
        holder.text_names.setText(list.get(position).getSummoner().getName());
        holder.text_levels.setText("Level " + list.get(position).getSummoner().getSummonerLevel());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString().replace(" ", "").toLowerCase();
                if (charString.isEmpty()) {
                    list = list_search;
                } else {
                    ArrayList<TotalDTO> list_save = new ArrayList<>();
                    String string = null;
                    for (TotalDTO i : list_search) {
                        string = i.getSummoner().getName().replace(" ", "").toLowerCase();

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
                list = (ArrayList<TotalDTO>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void searchSettingMap() {
        this.list = new ArrayList<>();
        this.list_search = new ArrayList<>();

        for (String i : this.map.keySet()) {
            list.add(this.map.get(i));
            list_search.add(this.map.get(i));
        }
    }

    public void setOnItemClickListener(OnSummonerClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_icons;
        private TextView text_names, text_levels;

        ViewHolder(View itemView) {
            super(itemView);
            image_icons = itemView.findViewById(R.id.image_icons);
            text_names = itemView.findViewById(R.id.text_names);
            text_levels = itemView.findViewById(R.id.text_levels);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        try {
                            listener.onSummonerClick(v, pos, list.get(pos).getSummoner().getName().replace(" ", "").toLowerCase());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    public interface OnSummonerClickListener {
        void onSummonerClick(View v, int position, String summoner) throws Exception;
    }
}
