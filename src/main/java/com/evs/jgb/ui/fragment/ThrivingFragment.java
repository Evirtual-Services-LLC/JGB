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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import static com.evs.jgb.ui.fragment.ArticlesFragment.BUNDLE_KEY_DETAILS;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_DIVISION;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_MODULE;

public class ThrivingFragment extends Fragment {
    @BindView(R.id.not_found)
    TextView not_found;
    @BindView(R.id.text_toolbar)
    TextView text_toolbar;
    @BindView(R.id.rv_thriving_list)
    RecyclerView rv_thriving_list;
    AgingAdapter adapter;
    private ArrayList<AgingModel> list;
    ACProgressFlower progressDialog;

    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_thriving, container, false);
        ButterKnife.bind(this, view);
        progressDialog = Global.getProgress(getActivity(), "Please wait...");
        getSectionResponse();
//        list = new ArrayList<>();
//        list.add(new AgingModel("1", " Health Tools", getString(R.drawable.note1)));
//        list.add(new AgingModel("2", " Live Healthy", getString(R.drawable.music)));
//        list.add(new AgingModel("3", "Healthy Eating", getString(R.drawable.study)));
//        list.add(new AgingModel("4", "Healthy Recipes", getString(R.drawable.note2)));
//        list.add(new AgingModel("5", "Medical Care", getString(R.drawable.study2)));
//        list.add(new AgingModel("6", "Infants' and Toddlers' Health", getString(R.drawable.care)));
//        list.add(new AgingModel("7", "Children's Health", getString(R.drawable.care)));
//        list.add(new AgingModel("8", "Adolescents' Health", getString(R.drawable.care)));
//        list.add(new AgingModel("9", "Women's Health", getString(R.drawable.care)));
//        list.add(new AgingModel("10", "Men's Health", getString(R.drawable.care)));
//        list.add(new AgingModel("11", "Seniors' Health", getString(R.drawable.care)));
//        list.add(new AgingModel("12", "Health Challenges", getString(R.drawable.care)));

        adapter = new AgingAdapter(getActivity());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        rv_thriving_list.setLayoutManager(manager);
        rv_thriving_list.setAdapter(adapter);
        adapter.setOnClickListener((adapter, position) -> {
            SectionModel model = adapter.get(position);
            String devision_id = adapter.get(position).getId_division();
            String module_id = adapter.get(position).getId_module();
            goTonextScreen(position, devision_id, module_id,model);
        });
        return view;
    }
    void getSectionResponse() {
        progressDialog.show();
        ApiInterface networkService = ApiInterfaceService.getClient().create(ApiInterface.class);
        Call<ListResponse<SectionModel>> arrayListCall = networkService.Modulelist(
                "en-US",
                "m029,m028,m033,m025,m027,m024,m026,m031,m032,m030",
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
                if (loginResponse.getList().size() > 0 && loginResponse.getList() != null) {
                    adapter.update(loginResponse.getList());
                } else {
                    rv_thriving_list.setVisibility(View.GONE);
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
        Bundle bundle = new Bundle();
        Fragment fragment = new ParentingNew();
        bundle.putString(BUNDLE_ID_DIVISION, devision_id);
        bundle.putString(BUNDLE_ID_MODULE, module_id);
        bundle.putString(BUNDLE_KEY_DETAILS, new Gson().toJson(model));
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
        text_toolbar.setText("THRIVING");
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        getActivity().onBackPressed();
    }
}
