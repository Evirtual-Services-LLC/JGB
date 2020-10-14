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
import com.evs.jgb.model.parentModel.ParentModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParentListAdapter extends RecyclerView.Adapter<ParentListAdapter.MyViewHolder> {
    private Context context;
    ArrayList<ParentModel> parentModelList = new ArrayList<>();
    private OnClickListener listener;


    public ParentListAdapter(Context context, ArrayList<ParentModel> items) {
        this.context = context;
        this.parentModelList = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frag_parenting_item, parent, false);
        return new ParentListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textHomeOne.setText(parentModelList.get(position).getName());
        Glide.with(context).load(parentModelList.get(position).getImage()).placeholder(R.drawable.logo).into(holder.imageHomeOne);
        holder.setOnClickListener(position1 -> {
            if (listener != null) {
                listener.onClick(this, position1);
            }
        });

    }

//    public void update(List<ParentModel> category) {
//        this.parentModelList.clear();
//        this.parentModelList.addAll(category);
//        notifyDataSetChanged();
//    }

    @Override
    public int getItemCount() {
        return parentModelList.size();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void onClick(ParentListAdapter adapter, int position);
    }

    public ParentModel get(int position) {
        return parentModelList.get(position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_item)
        ImageView imageHomeOne;
        @BindView(R.id.tv_product_name)
        TextView textHomeOne;
        private OnClickListener listener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
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
