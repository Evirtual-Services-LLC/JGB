package com.evs.jgb.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.evs.jgb.R;
import com.evs.jgb.adapter.ParentingNewAdapter;
import com.evs.jgb.model.SectionModel;
import com.evs.jgb.model.parentModel.ParentingNewModel;
import com.evs.jgb.ui.activity.MainActivity;
import com.evs.jgb.utils.RecyclerViewMargin;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.evs.jgb.ui.fragment.ArticleNewFragment.BUNDLE_LIST_FULL;
import static com.evs.jgb.ui.fragment.ArticleNewFragment.BUNDLE_LIST_TEMP;
import static com.evs.jgb.ui.fragment.ArticleNewFragment.BUNDLE_LIST_TEMP;
import static com.evs.jgb.ui.fragment.ArticlesFragment.BUNDLE_KEY_DETAILS;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_CATEGORY;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_DIVISION;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_MODULE;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_SELECT_TEXT;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_TEXT;

public class ParentingNew extends Fragment {
    @BindView(R.id.text_toolbar)
    TextView text_toolbar;
    @BindView(R.id.rv_praneting_list)
    RecyclerView rv_praneting_list;
    ParentingNewAdapter adapter;
    private ArrayList<ParentingNewModel> list;
    String module_id, devision_id,id_cr;
    SectionModel model;
    private String textName;
    @BindView(R.id.iv_module)
    ImageView iv_module;
    private ArrayList<SectionModel> list_full=new ArrayList<>();
    private ArrayList<SectionModel> temp_list=new ArrayList<>();
    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_parenting_new, container, false);
        ButterKnife.bind(this, view);
        intialize();
        list = new ArrayList<>();
        list.add(new ParentingNewModel("001", "Articles", R.drawable.note1));
        list.add(new ParentingNewModel("2", "Check Lists", R.drawable.music));
        list.add(new ParentingNewModel("3", "eLearning", R.drawable.study));
        list.add(new ParentingNewModel("4", "Legal Forms", R.drawable.note2));
        list.add(new ParentingNewModel("5", "Online Seminars", R.drawable.study2));
        list.add(new ParentingNewModel("6", "Resources", R.drawable.care));

        adapter = new ParentingNewAdapter(getActivity(), list);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 2);
        RecyclerViewMargin decoration = new RecyclerViewMargin(12, 2);
        rv_praneting_list.addItemDecoration(decoration);
        rv_praneting_list.setLayoutManager(manager);
        rv_praneting_list.setAdapter(adapter);
        adapter.setOnClickListener((adapter, position) -> {
            ParentingNewModel model = adapter.get(position);
            goTonextScreen(position, model,list);
        });


        return view;
    }

    private void intialize() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            devision_id = bundle.getString(BUNDLE_ID_DIVISION);
            module_id = bundle.getString(BUNDLE_ID_MODULE);
            id_cr=bundle.getString(BUNDLE_ID_CATEGORY);
            String json = bundle.getString(BUNDLE_KEY_DETAILS);
            model = new Gson().fromJson(json, SectionModel.class);
            list_full=bundle.getParcelableArrayList(BUNDLE_LIST_FULL);
            temp_list=bundle.getParcelableArrayList(BUNDLE_LIST_TEMP);
            textName=bundle.getString(BUNDLE_TEXT);
            iv_module.setVisibility(View.VISIBLE);
            if(textName.equalsIgnoreCase("PARENTING")){
                Glide.with(getActivity()).load(R.drawable.parenting).into(iv_module);
            }else if(textName.equalsIgnoreCase("AGING")){
                Glide.with(getActivity()).load(R.drawable.aging).into(iv_module);
            }else if(textName.equalsIgnoreCase("BALANCING")){
                Glide.with(getActivity()).load(R.drawable.balancing).into(iv_module);
            }else if(textName.equalsIgnoreCase("THRIVING")){
                Glide.with(getActivity()).load(R.drawable.thriving).into(iv_module);
            }else if(textName.equalsIgnoreCase("WORKING")){
                Glide.with(getActivity()).load(R.drawable.working).into(iv_module);
            }else if(textName.equalsIgnoreCase("LIVING")){
                Glide.with(getActivity()).load(R.drawable.living).into(iv_module);
            }else if(textName.equalsIgnoreCase("INTERNATIONAL")){
                Glide.with(getActivity()).load(R.drawable.ic_internet).into(iv_module);
            }

        }
    }

    private void goTonextScreen(int position, ParentingNewModel model, ArrayList<ParentingNewModel> list) {
        if (position == 0) {
            Bundle bundle = new Bundle();
            Fragment fragment = new ArticleNewFragment();
            bundle.putString(BUNDLE_ID_DIVISION, devision_id);
            bundle.putString(BUNDLE_ID_MODULE, module_id);
            bundle.putString(BUNDLE_ID_CATEGORY, id_cr);
            bundle.putString(BUNDLE_SELECT_TEXT, "ARTICLE");
            bundle.putString(BUNDLE_TEXT, textName);
            bundle.putString(BUNDLE_KEY_DETAILS, new Gson().toJson(model));
            bundle.putParcelableArrayList(BUNDLE_LIST_TEMP, temp_list);
            bundle.putParcelableArrayList(BUNDLE_LIST_FULL, list_full);
            fragment.setArguments(bundle);
            Activity activity = getActivity();
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).loadMainFragment(fragment, false);
            } else {
                replaceFragment(fragment, fragment.getClass().getSimpleName());
            }
        } else if (position == 1) {
            Fragment fragment = new AudioNewFragment();
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_ID_DIVISION, devision_id);
            bundle.putString(BUNDLE_ID_MODULE, module_id);
            bundle.putString(BUNDLE_ID_CATEGORY, id_cr);
            bundle.putString(BUNDLE_TEXT, textName);
            bundle.putString(BUNDLE_KEY_DETAILS, new Gson().toJson(model));
            bundle.putParcelableArrayList(BUNDLE_LIST_TEMP, temp_list);
            bundle.putParcelableArrayList(BUNDLE_LIST_FULL, list_full);
            fragment.setArguments(bundle);
            Activity activity = getActivity();
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).loadMainFragment(fragment, false);
            } else {
                replaceFragment(fragment, fragment.getClass().getSimpleName());
            }
        } else if (position == 2) {
            Fragment fragment = new ArticleNewFragment();
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_ID_DIVISION, devision_id);
            bundle.putString(BUNDLE_ID_MODULE, module_id);
            bundle.putString(BUNDLE_ID_CATEGORY, id_cr);
            bundle.putString(BUNDLE_TEXT, textName);
            bundle.putString(BUNDLE_KEY_DETAILS, new Gson().toJson(model));
            bundle.putParcelableArrayList(BUNDLE_LIST_TEMP, temp_list);
            bundle.putParcelableArrayList(BUNDLE_LIST_FULL, list_full);
            bundle.putString(BUNDLE_TEXT,"E-LEARNING");
            fragment.setArguments(bundle);
            Activity activity = getActivity();
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).loadMainFragment(fragment, false);
            } else {
                replaceFragment(fragment, fragment.getClass().getSimpleName());
            }
        } else if (position == 3) {
            Fragment fragment = new ArticleNewFragment();
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_ID_DIVISION, devision_id);
            bundle.putString(BUNDLE_ID_MODULE, module_id);
            bundle.putString(BUNDLE_ID_CATEGORY, id_cr);
            bundle.putString(BUNDLE_TEXT, textName);
            bundle.putString(BUNDLE_KEY_DETAILS, new Gson().toJson(model));
            bundle.putParcelableArrayList(BUNDLE_LIST_TEMP, temp_list);
            bundle.putParcelableArrayList(BUNDLE_LIST_FULL, list_full);
            bundle.putString(BUNDLE_TEXT,"LEGAL FORMS");
            fragment.setArguments(bundle);
            Activity activity = getActivity();
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).loadMainFragment(fragment, false);
            } else {
                replaceFragment(fragment, fragment.getClass().getSimpleName());
            }
        } else if (position == 4) {
            Fragment fragment = new ArticleNewFragment();
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_ID_DIVISION, devision_id);
            bundle.putString(BUNDLE_ID_MODULE, module_id);
            bundle.putString(BUNDLE_ID_CATEGORY, id_cr);
            bundle.putString(BUNDLE_TEXT, textName);
            bundle.putString(BUNDLE_KEY_DETAILS, new Gson().toJson(model));
            bundle.putParcelableArrayList(BUNDLE_LIST_TEMP, temp_list);
            bundle.putParcelableArrayList(BUNDLE_LIST_FULL, list_full);
            bundle.putString(BUNDLE_TEXT,"ONLINE SEMINAR");
            fragment.setArguments(bundle);
            Activity activity = getActivity();
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).loadMainFragment(fragment, false);
            } else {
                replaceFragment(fragment, fragment.getClass().getSimpleName());
            }
        } else if (position == 5) {
            Fragment fragment = new ArticleNewFragment();
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_ID_DIVISION, devision_id);
            bundle.putString(BUNDLE_ID_MODULE, module_id);
            bundle.putString(BUNDLE_ID_CATEGORY, id_cr);
            bundle.putString(BUNDLE_TEXT, textName);
            bundle.putString(BUNDLE_KEY_DETAILS, new Gson().toJson(model));
            bundle.putParcelableArrayList(BUNDLE_LIST_TEMP, temp_list);
            bundle.putParcelableArrayList(BUNDLE_LIST_FULL, list_full);
            bundle.putString(BUNDLE_TEXT,"RESOURCES");
            fragment.setArguments(bundle);
            Activity activity = getActivity();
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).loadMainFragment(fragment, false);
            } else {
                replaceFragment(fragment, fragment.getClass().getSimpleName());
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
        text_toolbar.setText(model.getNative_term());
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        getActivity().onBackPressed();
    }
}
