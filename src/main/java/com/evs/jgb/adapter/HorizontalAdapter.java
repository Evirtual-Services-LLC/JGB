package com.evs.jgb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.evs.jgb.R;
import com.evs.jgb.model.SectionModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {
    private Context context;
    ArrayList<SectionModel> parentModelList = new ArrayList<>();
    private OnClickListener listener;


    public HorizontalAdapter(Context context) {
        this.context = context;
        this.parentModelList = new ArrayList<>();
    }

    @NonNull
    @Override
    public HorizontalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_praent_item, parent, false);
        return new HorizontalAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalAdapter.MyViewHolder holder, int position) {
        holder.tv_product_name.setText(parentModelList.get(position).getNative_term());
        holder.setOnClickListener(position1 -> {
            if (listener != null) {
                listener.onClick(this, position1);
            }
        });

    }
    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
    public interface OnClickListener {
        void onClick(HorizontalAdapter adapter, int position);

    }

    public void update(List<SectionModel> category) {
        this.parentModelList.clear();
        this.parentModelList.addAll(category);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return parentModelList.size();
    }



    public SectionModel get(int position) {
        return parentModelList.get(position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener   {
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
