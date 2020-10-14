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
import com.evs.jgb.adapter.ParentingNewAdapter;
import com.evs.jgb.model.parentModel.ParentingNewModel;
import com.evs.jgb.ui.activity.MainActivity;
import com.evs.jgb.utils.RecyclerViewMargin;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParentingNew extends Fragment {
    @BindView(R.id.text_toolbar)
    TextView text_toolbar;
    @BindView(R.id.rv_praneting_list)
    RecyclerView rv_praneting_list;
    ParentingNewAdapter adapter;
    private ArrayList<ParentingNewModel> list;

    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_parenting_new, container, false);
        ButterKnife.bind(this, view);
        list = new ArrayList<>();
        list.add(new ParentingNewModel("1", "Articles", getString(R.drawable.note1)));
        list.add(new ParentingNewModel("2", "Audio", getString(R.drawable.music)));
        list.add(new ParentingNewModel("3", "eLearning", getString(R.drawable.study)));
        list.add(new ParentingNewModel("4", "Legal Forms", getString(R.drawable.note2)));
        list.add(new ParentingNewModel("5", "Online Seminars", getString(R.drawable.study2)));
        list.add(new ParentingNewModel("6", "Resources", getString(R.drawable.care)));

        adapter = new ParentingNewAdapter(getActivity(), list);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 2);
        RecyclerViewMargin decoration = new RecyclerViewMargin(12, 2);
        rv_praneting_list.addItemDecoration(decoration);
        rv_praneting_list.setLayoutManager(manager);
        rv_praneting_list.setAdapter(adapter);
        adapter.setOnClickListener((adapter, position) -> {
            ParentingNewModel model = adapter.get(position);
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
        text_toolbar.setText("PARENTING");
    }
    @OnClick(R.id.iv_back)
    public  void onClick(){
        getActivity().onBackPressed();
    }
}
