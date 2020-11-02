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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.evs.jgb.R;
import com.evs.jgb.adapter.AgingAdapter;
import com.evs.jgb.model.AgingModel;
import com.evs.jgb.model.SectionModel;
import com.evs.jgb.retrofit.ApiInterface;
import com.evs.jgb.retrofit.ApiInterfaceService;
import com.evs.jgb.retrofit.ListResponse;
import com.evs.jgb.ui.activity.MainActivity;
import com.evs.jgb.utils.Global;
import com.evs.jgb.utils.RecyclerViewMargin;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.evs.jgb.ui.fragment.ArticleNewFragment.BUNDLE_LIST_FULL;
import static com.evs.jgb.ui.fragment.ArticleNewFragment.BUNDLE_LIST_TEMP;
import static com.evs.jgb.ui.fragment.ArticlesFragment.BUNDLE_KEY_DETAILS;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_DIVISION;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_MODULE;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_TEXT;

public class BalancingFragment extends Fragment {
    @BindView(R.id.not_found)
    TextView not_found;
    @BindView(R.id.text_toolbar)
    TextView text_toolbar;
    @BindView(R.id.rv_balancing_list)
    RecyclerView rv_balancing_list;
    AgingAdapter adapter;
    ACProgressFlower progressDialog;
    private ArrayList<SectionModel> list;
    private ArrayList<SectionModel> temp_list;
    private ArrayList<SectionModel> list_full=new ArrayList<>();
    //set image toolbar
    private String bundle_text;
    @BindView(R.id.iv_module)
    ImageView iv_module;


    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_balancing, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            bundle_text = bundle.getString(BUNDLE_TEXT);
            if (bundle_text.equalsIgnoreCase("BALANCING")) {
                iv_module.setVisibility(View.VISIBLE);
                Glide.with(getActivity()).load(R.drawable.balancing).into(iv_module);
            }
//            list_full=new ArrayList<>();
//            list_full = bundle.getParcelableArrayList(BUNDLE_LIST_FULL);
//
//        }if (list_full != null && list_full.size() > 0) {
//            adapter.update(list_full);
//        } else {
        }

            progressDialog = Global.getProgress(getActivity(), "Please wait...");
            getSectionResponse();

            adapter = new AgingAdapter(getActivity());
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
            rv_balancing_list.setLayoutManager(manager);
            rv_balancing_list.setAdapter(adapter);
            adapter.setOnClickListener((adapter, position) -> {
                SectionModel model = adapter.get(position);
                String devision_id = adapter.get(position).getId_division();
                String module_id = adapter.get(position).getId_module();
                goTonextScreen(position, devision_id, module_id, model);
            });


        return view;
    }
    void getSectionResponse() {
        progressDialog.show();
        ApiInterface networkService = ApiInterfaceService.getClient().create(ApiInterface.class);
        Call<ListResponse<SectionModel>> arrayListCall = networkService.Modulelist(
                "en-US",
                "m022,m017,m018,m020,m021,m016,m019",
//               R.string.apitoken,
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZF9lYXAiOiIxNjUxIiwiaWRfY29tcGFueSI6MTY1MTI2NTY3fQ.FznzxAPBbFF9kI2Vd6G39P6kO431dztk8TN9VYir-jY",
                "1651"
        );
        arrayListCall.enqueue(new Callback<ListResponse<SectionModel>>() {
            @Override
            public void onResponse(@NonNull Call<ListResponse<SectionModel>> call, @NonNull Response<ListResponse<SectionModel>> response) {
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
                ListResponse<SectionModel> loginResponse = response.body();
                list = new ArrayList<>();
                list = loginResponse.getList();
                if (list.size() > 0 && list != null) {
                    adapter.update(list);
                } else {
                    rv_balancing_list.setVisibility(View.GONE);
                    not_found.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onFailure(@NonNull Call<ListResponse<SectionModel>> call, @NonNull Throwable t) {
                progressDialog.dismiss();
//                login_btn.setClickable(true);
            }
        });

    }

    private void goTonextScreen(int position, String devision_id, String module_id, SectionModel model) {
        temp_list=new ArrayList<>();
        temp_list=list;
        temp_list.remove(position);
        Bundle bundle = new Bundle();
        Fragment fragment = new TabsFragment();
        bundle.putString(BUNDLE_ID_DIVISION, devision_id);
        bundle.putString(BUNDLE_TEXT, "BALANCING");
        bundle.putString(BUNDLE_ID_MODULE, module_id);
        bundle.putString(BUNDLE_KEY_DETAILS, new Gson().toJson(model));
        bundle.putParcelableArrayList(BUNDLE_LIST_TEMP, temp_list);
        bundle.putParcelableArrayList(BUNDLE_LIST_FULL, list);
        fragment.setArguments(bundle);
        Activity activity = getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).loadMainFragment(fragment, false);
        } else {
            replaceFragment(fragment, fragment.getClass().getSimpleName());
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
        text_toolbar.setText("BALANCING");
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        getActivity().onBackPressed();
    }
}
