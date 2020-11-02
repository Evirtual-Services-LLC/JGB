package com.evs.jgb.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.evs.jgb.R;
import com.evs.jgb.adapter.TabsFragmentAdapter;
import com.evs.jgb.model.SectionModel;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


import static com.evs.jgb.ui.fragment.ArticleNewFragment.BUNDLE_LIST_FULL;
import static com.evs.jgb.ui.fragment.ArticleNewFragment.BUNDLE_LIST_TEMP;
import static com.evs.jgb.ui.fragment.ArticlesFragment.BUNDLE_KEY_DETAILS;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_DIVISION;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_MODULE;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_TEXT;
import static com.evs.jgb.utils.Constant.tabs.CATEGORY;
import static com.evs.jgb.utils.Constant.tabs.CENTER;

public class TabsFragment extends Fragment {
    @BindView(R.id.tabs_settlement)
    TabLayout tabLayout;
    @BindView(R.id.pah_my_contest)
    ViewPager viewPager;
    @BindView(R.id.text_toolbar)
    TextView text_toolbar;
    private View view;
    String module_id, devision_id;
    SectionModel sectionModel;
    private ArrayList<SectionModel> temp_list = new ArrayList<>();
    private ArrayList<SectionModel> list_full = new ArrayList<>();
    private String textName;
    @BindView(R.id.iv_module)
    ImageView iv_module;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_tabs_frag, container, false);
        ButterKnife.bind(this, view);
        intialize();
        return view;
    }
    private void intialize() {
        Bundle bundle=getArguments();
                if(bundle!=null){
                    module_id = bundle.getString(BUNDLE_ID_MODULE);
                    devision_id = bundle.getString(BUNDLE_ID_DIVISION);
                    String json = bundle.getString(BUNDLE_KEY_DETAILS);
                    sectionModel = new Gson().fromJson(json, SectionModel.class);
                    temp_list = bundle.getParcelableArrayList(BUNDLE_LIST_TEMP);
                    list_full = bundle.getParcelableArrayList(BUNDLE_LIST_FULL);
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
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_categories)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_center)));
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        rv_adds.setLayoutManager(linearLayoutManager);
        List<TabsContestFragment> fragments = new ArrayList<>();
        fragments.add(TabsContestFragment.newInstance(CATEGORY,module_id,devision_id,sectionModel,list_full,temp_list,textName));
        fragments.add(TabsContestFragment.newInstance(CENTER,module_id,devision_id,sectionModel,list_full,temp_list,textName));
        TabsFragmentAdapter adapter = new TabsFragmentAdapter(getActivity(), getChildFragmentManager());
        adapter.update(fragments);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


    }
    @OnClick(R.id.iv_back)
    public void onBackPressed(){
        getActivity().onBackPressed();
    }

    @Override
    public void onResume() {
        super.onResume();
        text_toolbar.setText(sectionModel.getNative_term());
    }
}
