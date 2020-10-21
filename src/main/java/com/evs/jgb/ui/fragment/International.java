package com.evs.jgb.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.evs.jgb.R;
import com.evs.jgb.adapter.ParentListAdapter;
import com.evs.jgb.model.SectionModel;
import com.evs.jgb.retrofit.ApiInterface;
import com.evs.jgb.retrofit.ApiInterfaceService;
import com.evs.jgb.retrofit.ListResponse;
import com.evs.jgb.ui.activity.MainActivity;
import com.evs.jgb.utils.Global;
import com.evs.jgb.viewModels.UserViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_DIVISION;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_MODULE;

public class International extends Fragment {
    @BindView(R.id.text_toolbar)
    TextView text_toolbar;
    @BindView(R.id.not_found)
    TextView not_found;
    @BindView(R.id.rv_international_list)
    RecyclerView rv_parent_list;
    RelativeLayout layoutParentOne, layoutParentTwo, layoutParentThree;
    TextView textHomeOne, textHomeTwo, textHomeThree;
    ImageView imageHomeOne, imageHomeTwo, imageHomeThree;
    ACProgressFlower progressDialog;
    SharedPreferences prefs;
    UserViewModel userViewModel;
    RequestQueue queue;
    ParentListAdapter adapter;
    private ArrayList<SectionModel> list;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_inerntational, container, false);
        ButterKnife.bind(this, view);
        intialize();
        return view;
    }

    private void intialize() {
        progressDialog = Global.getProgress(getActivity(), "Please wait...");
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        adapter = new ParentListAdapter(getActivity());
        getSectionResponse();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        rv_parent_list.setLayoutManager(manager);
        rv_parent_list.setAdapter(adapter);
        adapter.setOnClickListener((adapter, position) -> {
            SectionModel model = adapter.get(position);
            String devision_id = adapter.get(position).getId_division();
            String module_id = adapter.get(position).getId_module();
            goTonextScreen(position, devision_id, module_id);
        });
    }

    private void goTonextScreen(int position, String devision_id, String module_id) {

        Bundle bundle = new Bundle();
        Fragment fragment = new ParentingNew();
        bundle.putString(BUNDLE_ID_DIVISION, devision_id);
        bundle.putString(BUNDLE_ID_MODULE, module_id);
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


    void getSectionResponse() {
        progressDialog.show();
        ApiInterface networkService = ApiInterfaceService.getClient().create(ApiInterface.class);
        Call<ListResponse<SectionModel>> arrayListCall = networkService.Modulelist(
                "en-US",
                "m301,m305,m300,m303,m302,m306,m304",
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
                if (loginResponse != null) {
                    list = new ArrayList<>();
                    list = loginResponse.getList();
                    if (list.size() > 0 && list != null) {
                        adapter.update(list);
                    } else {
                        rv_parent_list.setVisibility(View.GONE);
                        not_found.setVisibility(View.VISIBLE);
                    }
                } else {
                    Global.showToast(getActivity(), "No data Found");
                }


            }

            @Override
            public void onFailure(@NonNull Call<ListResponse<SectionModel>> call, @NonNull Throwable t) {
                progressDialog.dismiss();
//                login_btn.setClickable(true);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        text_toolbar.setText("INTERNATIONAL");
    }
    @OnClick(R.id.iv_back)
    public void onClick() {
        getActivity().onBackPressed();
    }
}
