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
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.evs.jgb.R;
import com.evs.jgb.adapter.ParentListAdapter;
import com.evs.jgb.model.ParentReponse;
import com.evs.jgb.model.parentModel.ParentModel;
import com.evs.jgb.ui.activity.MainActivity;
import com.evs.jgb.utils.Global;
import com.evs.jgb.viewModels.ParentAuthListner;
import com.evs.jgb.viewModels.UserViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Parenting extends Fragment implements ParentAuthListner {

    @BindView(R.id.text_toolbar)
    TextView text_toolbar;
    RelativeLayout layoutParentOne, layoutParentTwo, layoutParentThree;
    TextView textHomeOne, textHomeTwo, textHomeThree;
    ImageView imageHomeOne, imageHomeTwo, imageHomeThree;
    ProgressDialog progressDialog;
    SharedPreferences prefs;
    UserViewModel userViewModel;
    RequestQueue queue;
    ParentListAdapter adapter;
    RecyclerView rv_parent_list;
    private ArrayList<ParentModel> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_parent, container, false);
        ButterKnife.bind(this,view);
        rv_parent_list = view.findViewById(R.id.rv_parent_list);
        intialize();
        return view;
    }

    private void intialize() {
        list = new ArrayList<>();
        list.add(new ParentModel("1", "Parenting", ""));
        list.add(new ParentModel("2", "Adoption", ""));
        list.add(new ParentModel("3", "Child Care", ""));
        progressDialog = Global.getProgressDialog(getActivity(), "Please wait...");
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);

        adapter = new ParentListAdapter(getActivity(), list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        rv_parent_list.setLayoutManager(manager);
        rv_parent_list.setAdapter(adapter);
        adapter.setOnClickListener((adapter, position) -> {
            ParentModel model = adapter.get(position);
            goTonextScreen(position);
        });

        //  get_list();
        //  hitApi();
//        userViewModel.getParentData().observe(getActivity(), new Observer<ArrayList<ParentModel>>() {
//            @Override
//            public void onChanged(ArrayList<ParentModel> parentModels) {
//                progressDialog.dismiss();
//                adapter.update(parentModels);
//            }
//        });
    }

    private void goTonextScreen(int position) {
        if (position == 0) {
            Fragment fragment = new ParentingNew();
            Activity activity = getActivity();
            if (activity instanceof MainActivity) {
                ((MainActivity) activity).loadMainFragment(fragment, false);
            } else {
                replaceFragment(fragment, fragment.getClass().getSimpleName());
            }

        }else if(position==1){
            Global.showToast(getActivity(),"Feature Coming Soon.. Check back Later!");
//            Fragment fragment = new AgingFragment();
//            Activity activity = getActivity();
//            if (activity instanceof MainActivity) {
//                ((MainActivity) activity).loadMainFragment(fragment, false);
//            } else {
//                replaceFragment(fragment, fragment.getClass().getSimpleName());
//            }
        }else if(position==2) {
            Global.showToast(getActivity(),"Feature Coming Soon.. Check back Later!");
//            Fragment fragment = new BalancingFragment();
//            Activity activity = getActivity();
//            if (activity instanceof MainActivity) {
//                ((MainActivity) activity).loadMainFragment(fragment, false);
//            } else {
//                replaceFragment(fragment, fragment.getClass().getSimpleName());
//            }
        }

    }
    @SuppressLint("WrongConstant")
    public void replaceFragment(Fragment fragment, String backstack_name) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_container, fragment, backstack_name);
        fragmentTransaction.addToBackStack(backstack_name);
        fragmentTransaction.commit();
    }
    private void hitApi() {
        String id_language = "en-US";
        String id_module = "m002,m003,m004,m006,m005,m001";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZF9lYXAiOiIxNjUxIiwiaWRfY29tcGFueSI6MTY1MTI2NTY3fQ.FznzxAPBbFF9kI2Vd6G39P6kO431dztk8TN9VYir-jY";
        String api_id = "1651";
        if (Global.isOnline(getActivity())) {
            userViewModel.hitParentDetails(id_language, id_module, token, api_id, this);
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        text_toolbar.setText("PARENTING");
    }

    @Override
    public void onStarted() {
        progressDialog.show();
    }

    @Override
    public void onOrderSuccess(LiveData<ParentReponse> data) {
        progressDialog.dismiss();
        data.observe(getActivity(), new Observer<ParentReponse>() {
            @Override
            public void onChanged(ParentReponse parentReponse) {
                //         adapter.update(parentReponse.getList_parent());
            }
        });

    }

    @Override
    public void onFailure(String message) {
        progressDialog.dismiss();
        Global.msgDialog(getActivity(), message);

    }

    private void get_list() {
        String id_language = "en-US";
        String id_module = "m002,m003,m004,m006,m005,m001";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZF9lYXAiOiIxNjUxIiwiaWRfY29tcGFueSI6MTY1MTI2NTY3fQ.FznzxAPBbFF9kI2Vd6G39P6kO431dztk8TN9VYir-jY";
        String api_id = "1651";
        progressDialog = Global.getProgressDialog(getActivity(), "Please wait...");
        queue = Volley.newRequestQueue(getContext());
        final JSONObject json = new JSONObject();
        try {

            json.put("id_language", id_language);
            json.put("id_module", id_module);
            json.put("token", token);
            json.put("api_id", api_id);

            Log.e("eventList", String.valueOf(json));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, "https://www.powerflexweb.com/api_content/common/read_mod.php/", json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.e("login reponse", "" + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();
                Global.msgDialog(getActivity(), "Internet connection is slow");
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);
        queue.add(jsonObjectRequest);
    }
    @OnClick(R.id.iv_back)
    public  void onClick(){
        getActivity().onBackPressed();
    }
}
