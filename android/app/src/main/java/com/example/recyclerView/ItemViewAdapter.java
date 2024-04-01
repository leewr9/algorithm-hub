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
import com.example.anyDTO.ItemDTO;
import com.example.nagneo_gg.R;
import com.example.retrofitController.UrlService;

import java.util.ArrayList;
import java.util.List;

public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.ViewHolder> implements Filterable {
    private Context context;
    private OnItemClickListener listener = null;

    private List<ItemDTO> list_search;
    private List<ItemDTO> list;

    public ItemViewAdapter(List<ItemDTO> list) {
        this.list = list;
        this.list_search = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.view_item, parent, false);
        ItemViewAdapter.ViewHolder viewHolder = new ItemViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position < list.size()) {
            Glide.with(context).load(UrlService.item_url + list.get(position).getNumid() + ".png").into(holder.image_item);
            holder.text_item.setText(list.get(position).getKorid());
            holder.text_tag.setBackgroundResource(R.color.firsnal);
            holder.text_tag.setText(list.get(position).getTag());
        } else {
            holder.text_item.setText("");
            holder.text_tag.setText("");
            holder.image_item.setImageDrawable(null);
            holder.text_tag.setBackground(null);
            holder.image_item.setBackground(null);
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 4;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty() || charString.equals("모든 아이템")) {
                    list = list_search;
                } else {
                    ArrayList<ItemDTO> list_save = new ArrayList<>();
                    String string = null;
                    if (charString.contains("시작")) {
                        for (ItemDTO i : list_search) {
                            if (i.getTag() != null && i.getTag().equals("시작")) {
                                list_save.add(i);
                            }
                        }
                    } else if (charString.contains("이동")) {
                        for (ItemDTO i : list_search) {
                            if (i.getTag().equals("이동")) {
                                list_save.add(i);
                            }
                        }
                    } else if (charString.contains("소모")) {
                        for (ItemDTO i : list_search) {
                            if (i.getTag().equals("소모") || i.getKorid().contains("물약")) {
                                list_save.add(i);
                            }
                        }
                    } else if (charString.contains("기본")) {
                        for (ItemDTO i : list_search) {
                            if (i.getTag() != null && i.getSub().size() == 0 && i.getTotal() <= 1300) {
                                if (i.getTag().equals("물리") || i.getTag().equals("마법") || i.getTag().equals("방어")) {
                                    list_save.add(i);
                                }
                            }
                        }
                    } else if (charString.contains("서사")) {
                        for (ItemDTO i : list_search) {
                            if (i.getTotal() > 500 && i.getTotal() < 1600 && i.getSub().size() != 0 && !i.getTag().equals("이동")) {
                                list_save.add(i);
                            }
                        }
                    } else if (charString.contains("전설")) {
                        for (ItemDTO i : list_search) {
                            if (i.getTotal() >= 1600 && !i.getDescription().contains("신화급")) {
                                list_save.add(i);
                            }
                        }
                    } else if (charString.contains("신화")) {
                        for (ItemDTO i : list_search) {
                            if (i.getDescription().contains("신화급")) {
                                list_save.add(i);
                            }
                        }
                    } else {
                        for (ItemDTO i : list_search) {
                            if (constraint.charAt(0) >= 0xAC00) string = i.getKorid();
                            else string = getKoreaChar(i.getKorid());

                            if (string.contains(charString)) list_save.add(i);
                        }
                    }
                    list = list_save;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<ItemDTO>) results.values;
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

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_item;
        private TextView text_item, text_tag;
        private View view;

        ViewHolder(View itemView) {
            super(itemView);
            image_item = itemView.findViewById(R.id.image_item);
            text_item = itemView.findViewById(R.id.text_item);
            text_tag = itemView.findViewById(R.id.text_tag);
            view = itemView;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        listener.onItemClick(v, pos, list.get(pos));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position, ItemDTO item);
    }
}
