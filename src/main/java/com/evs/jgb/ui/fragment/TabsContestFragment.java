package com.evs.jgb.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.evs.jgb.R;
import com.evs.jgb.adapter.TabCatergoryAdapter;
import com.evs.jgb.model.CategoryModel;
import com.evs.jgb.model.SectionModel;
import com.evs.jgb.model.parentModel.ArticleModel;
import com.evs.jgb.retrofit.ApiInterface;
import com.evs.jgb.retrofit.ApiInterfaceService;
import com.evs.jgb.retrofit.ListResponse;
import com.evs.jgb.ui.activity.MainActivity;
import com.evs.jgb.utils.Global;
import com.evs.jgb.viewModels.AuthListener;
import com.evs.jgb.viewModels.UserViewModel;
import com.google.gson.Gson;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.evs.jgb.ui.fragment.ArticleNewFragment.BUNDLE_LIST_FULL;
import static com.evs.jgb.ui.fragment.ArticleNewFragment.BUNDLE_LIST_TEMP;
import static com.evs.jgb.ui.fragment.ArticlesFragment.BUNDLE_KEY_DETAILS;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_CATEGORY;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_DIVISION;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_MODULE;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_TEXT;
import static com.evs.jgb.utils.Constant.tabs.CATEGORY;
import static com.evs.jgb.utils.Constant.tabs.CENTER;

public class TabsContestFragment extends Fragment implements AuthListener<ListResponse<CategoryModel>> {
    @BindView(R.id.rv_adds)
    RecyclerView rv_list;
    @BindView(R.id.tv_no_orders)
    TextView no_found;
    private View view;
    private static final String BUNDLE_KEY_CONTEST_TYPE = "selectedType";
    private String type = CATEGORY;
    private TabCatergoryAdapter adapter;
    ACProgressFlower progressDialog;
    UserViewModel userViewModel;
    int requestType = 1;
    String module_id, devision_id;
    SectionModel sectionModel;
    private ArrayList<SectionModel> temp_list = new ArrayList<>();
    private ArrayList<SectionModel> list_full = new ArrayList<>();
    private ArrayList<CategoryModel> list = new ArrayList<>();
    private String textName;

    public static TabsContestFragment newInstance(String type, String module_id, String devision_id, SectionModel model, ArrayList<SectionModel> list_full,ArrayList<SectionModel> temp_list, String textName) {
        TabsContestFragment fragment = new TabsContestFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_CONTEST_TYPE, type);
        bundle.putString(BUNDLE_ID_DIVISION, devision_id);
        bundle.putString(BUNDLE_ID_MODULE, module_id);
        bundle.putString(BUNDLE_KEY_DETAILS, new Gson().toJson(model));
        bundle.putString(BUNDLE_TEXT, textName);
        bundle.putParcelableArrayList(BUNDLE_LIST_FULL, list_full);
        bundle.putParcelableArrayList(BUNDLE_LIST_TEMP, temp_list);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_ads_list, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString(BUNDLE_KEY_CONTEST_TYPE);
            module_id = bundle.getString(BUNDLE_ID_MODULE);
            devision_id = bundle.getString(BUNDLE_ID_DIVISION);
            String json = bundle.getString(BUNDLE_KEY_DETAILS);
            textName = bundle.getString(BUNDLE_TEXT);
            sectionModel = new Gson().fromJson(json, SectionModel.class);
            temp_list = bundle.getParcelableArrayList(BUNDLE_LIST_TEMP);
            list_full = bundle.getParcelableArrayList(BUNDLE_LIST_FULL);
            intialize();
        }
    }

    private void intialize() {
        adapter = new TabCatergoryAdapter(getActivity());
        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        progressDialog = Global.getProgress(getActivity(), "Please wait...");
        if (Global.isOnline(getActivity())) {
            if (type.equalsIgnoreCase(CATEGORY)) {

            // userViewModel.hitCategoryDetails(module_id, this);
                getSectionResponse();
                requestType = 1;
            } else if (type.equalsIgnoreCase(CENTER)) {
                no_found.setVisibility(View.VISIBLE);
                no_found.setText("No Center Found");
                //  userViewModel.hitCategoryDetails(module_id, this);
                // requestType = 2;
            }
        }
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        rv_list.setLayoutManager(manager);
        rv_list.setAdapter(adapter);
        adapter.setOnClickListener((adapter1, position) -> {
            CategoryModel model = adapter.get(position);
            String id_cr = adapter.get(position).getId_category();

            goTonextScreen(id_cr, model);
        });

    }

    private void goTonextScreen(String id_cr, CategoryModel model) {
        Fragment detailFrag = new ParentingNew();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_ID_CATEGORY, id_cr);
        bundle.putString(BUNDLE_ID_DIVISION, devision_id);
        bundle.putString(BUNDLE_ID_MODULE, module_id);
        bundle.putString(BUNDLE_TEXT, textName);
        bundle.putString(BUNDLE_KEY_DETAILS, new Gson().toJson(model));
        bundle.putParcelableArrayList(BUNDLE_LIST_FULL, list_full);
        bundle.putParcelableArrayList(BUNDLE_LIST_TEMP, temp_list);

        detailFrag.setArguments(bundle);
        Activity activity = getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).loadMainFragment(detailFrag, false);
        } else {
            replaceFragment(detailFrag, detailFrag.getClass().getSimpleName());
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
    public void onStarted() {
        progressDialog.show();
    }

    @Override
    public void onSuccess(LiveData<ListResponse<CategoryModel>> data) {
        progressDialog.dismiss();
        data.observe(getActivity(), new Observer<ListResponse<CategoryModel>>() {
            @Override
            public void onChanged(ListResponse<CategoryModel> categoryModelListResponse) {
                if (requestType == 1) {
                    if (data.getValue().getList() != null && data.getValue().getList().size() > 0) {
                        adapter.update(data.getValue().getList());
                    } else {
                        rv_list.setVisibility(View.GONE);
                        no_found.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    @Override
    public void onFailure(String message) {
        progressDialog.dismiss();
        Global.showToast(getActivity(), message);
    }
    void getSectionResponse() {
        progressDialog.show();
        ApiInterface networkService = ApiInterfaceService.getClient().create(ApiInterface.class);
        Call<ListResponse<CategoryModel>> arrayListCall = networkService.categoryList(  "en-us",module_id, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZF9lYXAiOiIxNjUxIiwiaWRfY29tcGFueSI6MTY1MTI2NTY3fQ.FznzxAPBbFF9kI2Vd6G39P6kO431dztk8TN9VYir-jY",
                "1651");
        arrayListCall.enqueue(new Callback<ListResponse<CategoryModel>>() {
            @Override
            public void onResponse(Call<ListResponse<CategoryModel>> call, Response<ListResponse<CategoryModel>> response) {
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
                ListResponse<CategoryModel> loginResponse = response.body();
                list = new ArrayList<>();
                list = loginResponse.getList();
                if (list != null) {
                    adapter.update(list);
                } else {
                    rv_list.setVisibility(View.GONE);
                    no_found.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<ListResponse<CategoryModel>> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("res onFailure(): ", "network failure :( inform the user and possibly retry");

            }
        });

    }
}
