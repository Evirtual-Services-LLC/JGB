package com.evs.jgb.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evs.jgb.R;
import com.evs.jgb.adapter.AgingAdapter;
import com.evs.jgb.model.AgingModel;
import com.evs.jgb.ui.activity.MainActivity;
import com.evs.jgb.utils.RecyclerViewMargin;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThrivingFragment extends Fragment {

    @BindView(R.id.text_toolbar)
    TextView text_toolbar;
    @BindView(R.id.rv_thriving_list)
    RecyclerView rv_thriving_list;
    AgingAdapter adapter;
    private ArrayList<AgingModel> list;

    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_thriving, container, false);
        ButterKnife.bind(this, view);
        list = new ArrayList<>();
        list.add(new AgingModel("1", " Health Tools", getString(R.drawable.note1)));
        list.add(new AgingModel("2", " Live Healthy", getString(R.drawable.music)));
        list.add(new AgingModel("3", "Healthy Eating", getString(R.drawable.study)));
        list.add(new AgingModel("4", "Healthy Recipes", getString(R.drawable.note2)));
        list.add(new AgingModel("5", "Medical Care", getString(R.drawable.study2)));
        list.add(new AgingModel("6", "Infants' and Toddlers' Health", getString(R.drawable.care)));
        list.add(new AgingModel("7", "Children's Health", getString(R.drawable.care)));
        list.add(new AgingModel("8", "Adolescents' Health", getString(R.drawable.care)));
        list.add(new AgingModel("9", "Women's Health", getString(R.drawable.care)));
        list.add(new AgingModel("10", "Men's Health", getString(R.drawable.care)));
        list.add(new AgingModel("11", "Seniors' Health", getString(R.drawable.care)));
        list.add(new AgingModel("12", "Health Challenges", getString(R.drawable.care)));

        adapter = new AgingAdapter(getActivity(), list);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 2);
        RecyclerViewMargin decoration = new RecyclerViewMargin(12, 2);
        rv_thriving_list.addItemDecoration(decoration);
        rv_thriving_list.setLayoutManager(manager);
        rv_thriving_list.setAdapter(adapter);
        adapter.setOnClickListener((adapter, position) -> {
            AgingModel model = adapter.get(position);
            goTonextScreen(position);
        });
        return view;
    }

    private void goTonextScreen(int position) {
        if (position == 0) {
            Fragment fragment = new ArticlesFragment();
            Activity activity = getActivity();
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).loadMainFragment(fragment, false);
            } else {
                replaceFragment(fragment,fragment.getClass().getSimpleName());
            }
        } else if (position == 1) {
            Fragment fragment = new AudioFragment();
            Activity activity = getActivity();
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).loadMainFragment(fragment, false);
            } else {
                replaceFragment(fragment,fragment.getClass().getSimpleName());
            }
        } else if (position == 2) {
            Fragment fragment = new ELearningFragment();
            Activity activity = getActivity();
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).loadMainFragment(fragment, false);
            } else {
                replaceFragment(fragment,fragment.getClass().getSimpleName());
            }
        } else if (position == 3) {
            Fragment fragment = new LegalFormsFragment();
            Activity activity = getActivity();
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).loadMainFragment(fragment, false);
            } else {
                replaceFragment(fragment,fragment.getClass().getSimpleName());
            }
        } else if (position == 4) {
            Fragment fragment = new OnlineSeminarFragment();
            Activity activity = getActivity();
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).loadMainFragment(fragment, false);
            } else {
                replaceFragment(fragment,fragment.getClass().getSimpleName());
            }
        } else if (position == 5) {
            Fragment fragment = new ResourcesFragment();
            Activity activity = getActivity();
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).loadMainFragment(fragment, false);
            } else {
                replaceFragment(fragment,fragment.getClass().getSimpleName());
            }
        }
    }

    @SuppressLint("WrongConstant")
    public void replaceFragment(Fragment fragment, String backstack_name) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_container, fragment, backstack_name);
        fragmentTransaction.addToBackStack(backstack_name);
        fragmentTransaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        text_toolbar.setText("THRIVING");
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        getActivity().onBackPressed();
    }
}
