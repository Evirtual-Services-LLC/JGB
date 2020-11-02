package com.evs.jgb.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.evs.jgb.R;
import com.evs.jgb.model.SectionModel;
import com.evs.jgb.ui.activity.MainActivity;
import com.evs.jgb.ui.fragment.AgingFragment;
import com.evs.jgb.ui.fragment.BalancingFragment;
import com.evs.jgb.ui.fragment.International;
import com.evs.jgb.ui.fragment.LivingFragment;
import com.evs.jgb.ui.fragment.Parenting;
import com.evs.jgb.ui.fragment.ThrivingFragment;
import com.evs.jgb.ui.fragment.WorkingFragment;

import java.util.ArrayList;

import static com.evs.jgb.ui.fragment.ArticleNewFragment.BUNDLE_LIST_FULL;

public class CustomViewPagerAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater inflater;
    ProgressDialog progress;
    private ArrayList<SectionModel> model = new ArrayList<>();
    private ArrayList<SectionModel> list_full = new ArrayList<>();
    private String textname;

    public CustomViewPagerAdapter(Context context, ArrayList<SectionModel> list, ArrayList<SectionModel> list_full, String textName) {
        this.mContext = context;
        this.model = list;
        this.list_full = list_full;
        this.textname = textName;
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        RelativeLayout main_laout;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.horizontal_praent_item, container, false);

        main_laout = (RelativeLayout) itemView.findViewById(R.id.main_laout);
        TextView tv_product_name = (TextView) itemView.findViewById(R.id.tv_product_name);
        tv_product_name.setText(model.get(position).getNative_term());
        ((ViewPager) container).addView(itemView);

        main_laout.setOnClickListener(v -> {
            Fragment fragment = null;
            Bundle bundle = new Bundle();
            if (textname.equalsIgnoreCase("PARENTING")) {
                fragment = new Parenting();
                bundle.putParcelableArrayList(BUNDLE_LIST_FULL, list_full);
                fragment.setArguments(bundle);
                Activity activity = (AppCompatActivity) v.getContext();
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).loadMainFragment(fragment, true);
                } else {
                    replaceFragment(fragment, fragment.getClass().getSimpleName());
                }

            } else if (textname.equalsIgnoreCase("AGING")) {
                fragment = new AgingFragment();
                bundle.putParcelableArrayList(BUNDLE_LIST_FULL, list_full);
                fragment.setArguments(bundle);
                Activity activity = (AppCompatActivity) v.getContext();
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).loadMainFragment(fragment, true);
                } else {
                    replaceFragment(fragment, fragment.getClass().getSimpleName());
                }

            } else if (textname.equalsIgnoreCase("BALANCING")) {
                fragment = new BalancingFragment();
                bundle.putParcelableArrayList(BUNDLE_LIST_FULL, list_full);
                fragment.setArguments(bundle);
                Activity activity = (AppCompatActivity) v.getContext();
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).loadMainFragment(fragment, true);
                } else {
                    replaceFragment(fragment, fragment.getClass().getSimpleName());
                }

            } else if (textname.equalsIgnoreCase("THRIVING")) {
                fragment = new ThrivingFragment();
                bundle.putParcelableArrayList(BUNDLE_LIST_FULL, list_full);
                fragment.setArguments(bundle);
                Activity activity = (AppCompatActivity) v.getContext();
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).loadMainFragment(fragment, true);
                } else {
                    replaceFragment(fragment, fragment.getClass().getSimpleName());
                }

            } else if (textname.equalsIgnoreCase("WORKING")) {
                fragment = new WorkingFragment();
                bundle.putParcelableArrayList(BUNDLE_LIST_FULL, list_full);
                fragment.setArguments(bundle);
                Activity activity = (AppCompatActivity) v.getContext();
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).loadMainFragment(fragment, true);
                } else {
                    replaceFragment(fragment, fragment.getClass().getSimpleName());
                }

            } else if (textname.equalsIgnoreCase("LIVING")) {
                fragment = new LivingFragment();
                bundle.putParcelableArrayList(BUNDLE_LIST_FULL, list_full);
                fragment.setArguments(bundle);
                Activity activity = (AppCompatActivity)v.getContext();
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).loadMainFragment(fragment, true);
                } else {
                    replaceFragment(fragment, fragment.getClass().getSimpleName());
                }

            } else if (textname.equalsIgnoreCase("INTERNATIONAL")) {
                fragment = new International();
                bundle.putParcelableArrayList(BUNDLE_LIST_FULL, list_full);
                fragment.setArguments(bundle);
                Activity activity = (AppCompatActivity) v.getContext();
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).loadMainFragment(fragment, true);
                } else {
                    replaceFragment(fragment, fragment.getClass().getSimpleName());
                }

            }


        });
        return itemView;
    }


    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);
    }


    @SuppressLint("WrongConstant")
    public void replaceFragment(Fragment fragment, String backstack_name) {
        FragmentTransaction fragmentTransaction = ((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_container, fragment, backstack_name);
        fragmentTransaction.addToBackStack(backstack_name);
        fragmentTransaction.commit();
    }


}
