package com.evs.jgb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.evs.jgb.R;
import com.evs.jgb.model.parentModel.ArticleModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleDetailAdapter extends RecyclerView.Adapter<ArticleDetailAdapter.MyViewHolder>  {
    private Context context;
    ArrayList<ArticleModel> list, originalData;
    private OnClickListener listener;
    private final int limit = 10;

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;

    public ArticleDetailAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
        this.originalData = list;
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_product_name.setText(list.get(position).getTitle());
        holder.setOnClickListener(position1 -> {
            if (listener != null) {
                listener.onClick(this, position1);
            }
        });

    }

    public void update(List<ArticleModel> category) {
        this.list.clear();
        this.list.addAll(category);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(list.size() >= limit){
            return limit;
        }
        else
        {
            return list.size();
        }
    }



    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void add(List<ArticleModel> bootData) {
        this.list.addAll(bootData);
    }

    public interface OnClickListener {
        void onClick(ArticleDetailAdapter adapter, int position);

    }

    public ArticleModel get(int position) {
        return list.get(position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_data)
        TextView tv_product_name;
        private OnClickListener listener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onClick(getAdapterPosition());
            }

        }

        public void setOnClickListener(OnClickListener listener) {
            this.listener = listener;
        }

        public interface OnClickListener {
            void onClick(int position);
        }
    }

    public void filterList(ArrayList<ArticleModel> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }



}
