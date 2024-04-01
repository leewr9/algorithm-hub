package com.example.recyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.retrofitController.UrlService;
import com.example.nagneo_gg.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LikeViewAdapter extends RecyclerView.Adapter<LikeViewAdapter.ViewHolder> {
    private Context context;
    private OnSummonerClickListener listener = null;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Map<String, ?> list;
    private List<String[]> list_name;

    public LikeViewAdapter(Context context) {
        this.sharedPreferences = context.getSharedPreferences("Like", Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();

        this.list = sharedPreferences.getAll();
        this.list_name = new ArrayList<>();

        for (String i : list.keySet()) {
            String strings[] = new String[2];
            strings[0] = i;
            strings[1] = list.get(i).toString().split(",")[1];
            list_name.add(strings);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.view_like, parent, false);
        LikeViewAdapter.ViewHolder viewHolder = new LikeViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list_name.get(position).length > 1) {
            Glide.with(context).load(UrlService.icon_url + list.get(list_name.get(position)[0]).toString().split(",")[0] + ".png").apply(RequestOptions.bitmapTransform(new RoundedCorners(100))).into(holder.image_icon);
            holder.text_like.setText(list_name.get(position)[0]);
            holder.text_likelevel.setText("Level " + list_name.get(position)[1]);
        }
    }

    @Override
    public int getItemCount() {
        return list_name.size();
    }

    public void remove(int position) {
        list.remove(list_name.get(position));
        editor.remove(list_name.get(position)[0]);
        editor.commit();

        list_name.remove(position);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnSummonerClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_icon;
        private TextView text_like, text_likelevel;
        private ImageButton image_like;

        ViewHolder(View itemView) {
            super(itemView);
            image_icon = itemView.findViewById(R.id.image_icon);
            text_like = itemView.findViewById(R.id.text_like);
            text_likelevel = itemView.findViewById(R.id.text_likelevel);
            image_like = itemView.findViewById(R.id.image_like);

            image_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        try {
                            listener.onSummonerClick(v, pos, list_name.get(pos)[0].replace(" ", "").toLowerCase());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            text_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        try {
                            listener.onSummonerClick(v, pos, list_name.get(pos)[0].replace(" ", "").toLowerCase());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            image_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        try {
                            listener.onSummonerRemove(v, pos, list_name.get(pos)[0].replace(" ", "").toLowerCase());
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

        void onSummonerRemove(View v, int position, String summoner) throws Exception;
    }
}
