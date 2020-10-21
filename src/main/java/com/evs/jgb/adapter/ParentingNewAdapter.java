package com.evs.jgb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.evs.jgb.R;
import com.evs.jgb.model.parentModel.ParentingNewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParentingNewAdapter extends RecyclerView.Adapter<ParentingNewAdapter.MyViewHolder> {
    private Context context;
    ArrayList<ParentingNewModel> parentModelList = new ArrayList<>();
    private OnClickListener listener;

    public ParentingNewAdapter(Context context, ArrayList<ParentingNewModel> items) {
        this.context = context;
        this.parentModelList = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frag_parenting_item_new, parent, false);
        return new ParentingNewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_product_name.setText(parentModelList.get(position).getName());

        Glide.with(context).load(parentModelList.get(position).getImage()).placeholder(R.drawable.logo).into(holder.iv_items);
        holder.setOnClickListener(position1 -> {
            if (listener != null) {
                listener.onClick(ParentingNewAdapter.this, position1);
            }
        });
    }

    public void update(List<ParentingNewModel> category) {
        this.parentModelList.clear();
        this.parentModelList.addAll(category);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return parentModelList.size();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void onClick(ParentingNewAdapter adapter, int position);
    }

    public ParentingNewModel get(int position) {
        return parentModelList.get(position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_items)
        ImageView iv_items;
        @BindView(R.id.tv_product_name)
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

}
