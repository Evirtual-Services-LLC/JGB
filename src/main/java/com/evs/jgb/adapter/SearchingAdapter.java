package com.evs.jgb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.evs.jgb.R;
import com.evs.jgb.model.parentModel.ArticleModel;
import com.evs.jgb.viewModels.FilterListeners;

import java.util.ArrayList;
import java.util.List;

public class SearchingAdapter extends ArrayAdapter<ArticleModel> {
    private  Context context;
    ArrayList<ArticleModel> list, originalData;
    int resource, textViewResourceId;
    private FilterListeners filterListeners;
    public SearchingAdapter(Context context,int resource, int textViewResourceId, ArrayList<ArticleModel> items) {
       super(context,resource,textViewResourceId,items);
        this.context = context;
        this.resource = resource;
        this.list=new ArrayList<>();
        this.textViewResourceId = textViewResourceId;
        this.list=items;
        this.originalData = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.article_list_item, parent, false);
        }
        ArticleModel names = list.get(position);
        if (names != null) {
            TextView lblName = (TextView) view.findViewById(R.id.tv_data);
            if (lblName != null)
                lblName.setText(names.getTitle());
        }
        return view;
    }
    public void setFilterListeners(FilterListeners filterFinishedListener)
    {
        filterListeners = filterFinishedListener;
    }
    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    /**
     * Custom Filter implementation for custom suggestions we provide.
     */
    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((ArticleModel) resultValue).getTitle();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();
            final List<ArticleModel> list = originalData;

            int count = list.size();
            final ArrayList<ArticleModel> nlist = new ArrayList<>(count);
            String filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = (list.get(i).getTitle());
                if (filterableString.toLowerCase().contains(filterString)) {
                    nlist.add(list.get(i));
                }
            }
            results.values = nlist;
            results.count = nlist.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<ArticleModel> filterList = (ArrayList<ArticleModel>) results.values;
            if (filterListeners != null && filterList != null)
                filterListeners.filteringFinished(filterList.size());
            if (results != null && results.count > 0) {
                clear();
                for (ArticleModel item : filterList) {
                    add(item);
                    notifyDataSetChanged();
                }
            }
        }

    };
}
