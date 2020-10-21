package com.evs.jgb.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.evs.jgb.R;
import com.evs.jgb.adapter.ParentListAdapter;
import com.evs.jgb.model.ParentReponse;
import com.evs.jgb.model.SectionModel;
import com.evs.jgb.model.parentModel.ParentModel;
import com.evs.jgb.retrofit.ApiInterface;
import com.evs.jgb.retrofit.ApiInterfaceService;
import com.evs.jgb.retrofit.ListResponse;
import com.evs.jgb.ui.activity.LoginActivity;
import com.evs.jgb.ui.activity.MainActivity;
import com.evs.jgb.utils.Global;
import com.evs.jgb.viewModels.AuthListener;
import com.evs.jgb.viewModels.ParentAuthListner;
import com.evs.jgb.viewModels.UserViewModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.evs.jgb.ui.fragment.ArticlesFragment.BUNDLE_KEY_DETAILS;

public class Parenting extends Fragment {
    public static final String BUNDLE_ID_DIVISION = "iddivision";
    public static final String BUNDLE_ID_MODULE = "idModule";
    @BindView(R.id.text_toolbar)
    TextView text_toolbar;
    @BindView(R.id.not_found)
    TextView not_found;
    @BindView(R.id.rv_parent_list)
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
        View view = inflater.inflate(R.layout.frag_parent, container, false);
        ButterKnife.bind(this, view);
        rv_parent_list = view.findViewById(R.id.rv_parent_list);
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
            goTonextScreen(position, devision_id, module_id, model);
        });
//        if (Global.isOnline(getActivity())) {
//            userViewModel.hitParentDivision(this);
//        }
//        userViewModel.getDivisionData().observe(getActivity(), new Observer<ArrayList<SectionModel>>() {
//            @Override
//            public void onChanged(ArrayList<SectionModel> addressModels) {
//                progressDialog.dismiss();
//                if (addressModels != null && addressModels.size() > 0) {
//                    adapter.update(addressModels);
//                } else {
//                    rv_parent_list.setVisibility(View.GONE);
//                    not_found.setVisibility(View.VISIBLE);
//                }
//            }
//        });

//        list = new ArrayList<>();
//        list.add(new ParentModel("1", "Parenting", R.drawable.parent_two));
//        list.add(new ParentModel("2", "Aging", R.drawable.aging_two));
//        list.add(new ParentModel("3", "Balancing", R.drawable.balance_two));
//        list.add(new ParentModel("4", "Thriving", R.drawable.thriving_two));
//        list.add(new ParentModel("5", "Working", R.drawable.working_two));
//        list.add(new ParentModel("6", "Living", R.drawable.living_two));
//        progressDialog = Global.getProgressDialog(getActivity(), "Please wait...");
//        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
//
//        adapter = new ParentListAdapter(getActivity(), list);


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
        text_toolbar.setText("PARENTING");

    }


    void getSectionResponse() {
        progressDialog.show();
        ApiInterface networkService = ApiInterfaceService.getClient().create(ApiInterface.class);
        Call<ListResponse<SectionModel>> arrayListCall = networkService.Modulelist(
                "en-US",
                "m002,m003,m004,m006,m005,m001",
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


    @OnClick(R.id.iv_back)
    public void onClick() {
        getActivity().onBackPressed();
    }
}
