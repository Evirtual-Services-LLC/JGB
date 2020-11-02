package com.evs.jgb.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.evs.jgb.R;
import com.evs.jgb.adapter.ArticleDetailAdapter;
import com.evs.jgb.adapter.CustomViewPagerAdapter;
import com.evs.jgb.model.SectionModel;
import com.evs.jgb.model.parentModel.ArticleModel;
import com.evs.jgb.retrofit.ApiInterface;
import com.evs.jgb.retrofit.ApiInterfaceService;
import com.evs.jgb.retrofit.ListResponse;
import com.evs.jgb.ui.activity.MainActivity;
import com.evs.jgb.utils.Global;
import com.evs.jgb.viewModels.UserViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.evs.jgb.ui.fragment.ArticleDetailsFragment.BUNDLE_KEY;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_CATEGORY;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_DIVISION;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_MODULE;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_SELECT_TEXT;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_TEXT;

public class ArticleNewFragment extends Fragment {
    public static final String BUNDLE_ID_MODULE_DESCRP = "id_module";
    public static final String BUNDLE_KEY_DETAILS = "productModel";
    public static final String BUNDLE_LIST_TEMP = "articlelist";
    public static final String BUNDLE_LIST_FULL = "productlist";
    @BindView(R.id.text_toolbar)
    TextView text_toolbar;
    @BindView(R.id.rv_article_list)
    RecyclerView rv_article_list;
    @BindView(R.id.not_found)
    TextView not_found;
    ArrayList<ArticleModel> list = new ArrayList<>();
    ArticleDetailAdapter adapter;
    @BindView(R.id.etxt_search)
    EditText etxt_search;
    private SearchView searchView;
    ACProgressFlower progressDialog;
    String module_id, devision_id, id_category;
    UserViewModel userViewModel;
    private String selecttextName,textName;
    @BindView(R.id.btn_previous)
    ImageButton leftNav;
    @BindView(R.id.btn_next)
    ImageButton rightNav;
    int count = 0;

    //scroll
    int Total_data = 0;
    @BindView(R.id.images_pager)
    ViewPager mViewPager;
    CustomViewPagerAdapter customViewPagerAdapter;

    private ArrayList<SectionModel> temp_list = new ArrayList<>();
    private ArrayList<SectionModel> list_full = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_article_new, container, false);
        ButterKnife.bind(this, view);
        intialize();
        return view;
    }

    private void intialize() {
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        progressDialog = Global.getProgress(getActivity(), "Please wait...");
        adapter = new ArticleDetailAdapter(getActivity());

        Bundle bundle = getArguments();
        if (bundle != null) {
            devision_id = bundle.getString(BUNDLE_ID_DIVISION);
            module_id = bundle.getString(BUNDLE_ID_MODULE);
            temp_list = bundle.getParcelableArrayList(BUNDLE_LIST_TEMP);
          list_full = bundle.getParcelableArrayList(BUNDLE_LIST_FULL);
            selecttextName = bundle.getString(BUNDLE_SELECT_TEXT);
            textName=bundle.getString(BUNDLE_TEXT);
            id_category = bundle.getString(BUNDLE_ID_CATEGORY);
        }
        getSectionResponse();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        rv_article_list.setLayoutManager(manager);
        rv_article_list.setAdapter(adapter);
        adapter.setOnClickListener((adapter1, position) -> {
            ArticleModel model = adapter.get(position);
            String id_cr = adapter.get(position).getId_cr();
            String id_module = adapter.get(position).getId_element();
            goTonextScreen(id_cr, id_module, model);
        });

        etxt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        setData(temp_list);


    }

    private void setData(ArrayList<SectionModel> temp_list) {
        if (temp_list != null) {
            customViewPagerAdapter = new CustomViewPagerAdapter(getActivity(),list_full, temp_list,textName);
            mViewPager.setAdapter(customViewPagerAdapter);
        }
        leftNav.setOnClickListener(v -> {
            int tab = mViewPager.getCurrentItem();
            if (tab > 0) {
                tab--;
                mViewPager.setCurrentItem(tab);
            } else if (tab == 0) {
                mViewPager.setCurrentItem(tab);
            }

        });

        rightNav.setOnClickListener(v -> {
            int tab = mViewPager.getCurrentItem();
            tab++;
            mViewPager.setCurrentItem(tab);
            if (tab == (temp_list.size() - 1)) {

            }

        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("state: ", String.valueOf(position));
                if (mViewPager.getCurrentItem() == temp_list.size() - 1) {
                    rightNav.setVisibility(View.INVISIBLE);
                } else {
                    rightNav.setVisibility(View.VISIBLE);
                }
                if (mViewPager.getCurrentItem() == 0) {
                    leftNav.setVisibility(View.INVISIBLE);
                } else {
                    leftNav.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void filter(String toString) {
        ArrayList<ArticleModel> filteredList = new ArrayList<>();
        for (ArticleModel item : list) {
            if (item.getTitle().toLowerCase().contains(toString.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    @SuppressLint("WrongConstant")
    public void replaceFragment(Fragment fragment, String backstack_name) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_container, fragment, backstack_name);
        fragmentTransaction.addToBackStack(backstack_name);
        fragmentTransaction.commit();
    }

    private void goTonextScreen(String id_cr, String id_module, ArticleModel model) {

        Fragment detailFrag = new ArticleDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_ID_DIVISION, devision_id);
        bundle.putString(BUNDLE_ID_MODULE, module_id);
        bundle.putString(BUNDLE_ID_CATEGORY, id_cr);
        bundle.putString(BUNDLE_ID_MODULE_DESCRP, id_module);
        bundle.putString(BUNDLE_KEY_DETAILS, new Gson().toJson(model));
        if (selecttextName.equalsIgnoreCase("ARTICLE")) {
            bundle.putString(BUNDLE_KEY, "ARTICLE DETAILS");
        } else if (selecttextName.equalsIgnoreCase("E-LEARNING")) {
            bundle.putString(BUNDLE_KEY, "E-LEARNING DETAILS");
        }
        if (selecttextName.equalsIgnoreCase("LEGAL FORMS")) {
            bundle.putString(BUNDLE_KEY, "LEGAL FORMS DETAILS");
        }
        if (selecttextName.equalsIgnoreCase("ONLINE SEMINAR")) {
            bundle.putString(BUNDLE_KEY, "ONLINE SEMINAR DETAILS");
        }
        if (selecttextName.equalsIgnoreCase("RESOURCES")) {
            bundle.putString(BUNDLE_KEY, "RESOURCES DETAILS");
        }
        detailFrag.setArguments(bundle);
        Activity activity = getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).loadMainFragment(detailFrag, false);
        } else {
            replaceFragment(detailFrag, detailFrag.getClass().getSimpleName());
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        text_toolbar.setText(selecttextName);
    }

    @OnClick({R.id.btn_resources, R.id.iv_back})
    void OnClick(View view) {
        if (view.getId() == R.id.btn_resources) {
            Bundle bundle = new Bundle();
            Fragment detailFrag = new ArticlesFragment();
            bundle.putParcelableArrayList(BUNDLE_LIST_FULL, list);
            bundle.putString(BUNDLE_SELECT_TEXT, selecttextName);
            detailFrag.setArguments(bundle);
            Activity activity = getActivity();
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).loadMainFragment(detailFrag, false);
            } else {
                replaceFragment(detailFrag, detailFrag.getClass().getSimpleName());
            }

        } else if (view.getId() == R.id.iv_back) {
            getActivity().onBackPressed();
        }

    }

    void getSectionResponse() {
        progressDialog.show();
        ApiInterface networkService = ApiInterfaceService.getClient().create(ApiInterface.class);
        Call<ListResponse<ArticleModel>> arrayListCall = networkService.ArticleModulelist(devision_id, module_id, id_category, "001", "", "",
                "en-us", "id_cr,title,id_element,body_combine", "title", "asc", "", "", "",
                "", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZF9lYXAiOiIxNjUxIiwiaWRfY29tcGFueSI6MTY1MTI2NTY3fQ.FznzxAPBbFF9kI2Vd6G39P6kO431dztk8TN9VYir-jY",
                "1651");
        arrayListCall.enqueue(new Callback<ListResponse<ArticleModel>>() {
            @Override
            public void onResponse(Call<ListResponse<ArticleModel>> call, Response<ListResponse<ArticleModel>> response) {
                progressDialog.dismiss();
                if (!response.isSuccessful()) {
                    switch (response.code()) {
                        case 404:
                            Global.showToast(getActivity(), "not found");
                            break;
                        case 500:
                            Global.showToast(getActivity(), "server broken");
                            break;
                        default:
                            Global.showToast(getActivity(), "unknown error");
                            break;
                    }
                }
                ListResponse<ArticleModel> loginResponse = response.body();
                list = new ArrayList<>();
                list = loginResponse.getList();
                if (list != null) {
                    adapter.update(list);
                } else {
                    rv_article_list.setVisibility(View.GONE);
                    not_found.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<ListResponse<ArticleModel>> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("res onFailure(): ", "network failure :( inform the user and possibly retry");

            }
        });

    }


}
