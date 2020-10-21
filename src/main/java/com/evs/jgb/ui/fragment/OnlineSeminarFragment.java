package com.evs.jgb.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evs.jgb.R;
import com.evs.jgb.adapter.ArticleListAdapter;
import com.evs.jgb.adapter.AudioAdapter;
import com.evs.jgb.model.parentModel.ArticleModel;
import com.evs.jgb.model.parentModel.ArticleModel;
import com.evs.jgb.retrofit.ApiInterface;
import com.evs.jgb.retrofit.ApiInterfaceService;
import com.evs.jgb.retrofit.ListResponse;
import com.evs.jgb.ui.activity.MainActivity;
import com.evs.jgb.utils.Global;
import com.evs.jgb.utils.Utills;
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
import static com.evs.jgb.ui.fragment.ArticlesFragment.BUNDLE_KEY_DETAILS;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_DIVISION;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_MODULE;

public class OnlineSeminarFragment extends Fragment {
    @BindView(R.id.text_toolbar)
    TextView text_toolbar;
    @BindView(R.id.etxt_search)
    EditText etxt_search;
    @BindView(R.id.rv_audio_list)
    RecyclerView rv_audio_list;
    View includeLayout;
    TextView listDataText;
    ImageView listDataImg;
    ArrayList<ArticleModel> list;
    ArticleListAdapter adapter;
    @BindView(R.id.not_found)
    TextView not_found;
    ACProgressFlower progressDialog;
    String module_id, devision_id;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_audio, container, false);
        ButterKnife.bind(this, view);
        intialize();
        return view;

        // Intent intent = new Intent(ArticlesActivity.this,DetailsActivity.class);
        // intent.putExtra("details","article");
        // startActivity(intent);
    }

    private void intialize() {
        progressDialog = Global.getProgress(getActivity(), "Please wait...");
        adapter = new ArticleListAdapter(getActivity());
        Bundle bundle = getArguments();
        if (bundle != null) {
            devision_id = bundle.getString(BUNDLE_ID_DIVISION);
            module_id = bundle.getString(BUNDLE_ID_MODULE);
        }
        getSectionResponse();
//        list.add(new ArticleModel("1", "Being an upstander"));
//        list.add(new ArticleModel("2", "A Special online seminar"));
//        list.add(new ArticleModel("3", "Apps and guides for household"));
//        list.add(new ArticleModel("4", "Making a life your own day"));
//        list.add(new ArticleModel("5", "Maximing your day"));
//        list.add(new ArticleModel("6", "Being an upstander"));
//        list.add(new ArticleModel("7", "A Special online seminar"));
//        list.add(new ArticleModel("8", "Apps and guides for household"));
//        list.add(new ArticleModel("9", "Making a life your own day"));
//        list.add(new ArticleModel("10", "Maximing your day"));

      //  adapter = new ArticleListAdapter(getActivity(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rv_audio_list.setLayoutManager(manager);
        rv_audio_list.setAdapter(adapter);
        adapter.setOnClickListener((adapter1, position) -> {
            ArticleModel model=adapter.get(position);
            goTonextScreen(model);
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
    void getSectionResponse() {
        progressDialog.show();
        ApiInterface networkService = ApiInterfaceService.getClient().create(ApiInterface.class);
        Call<ListResponse<ArticleModel>> arrayListCall = networkService.ArticleModulelist(devision_id,module_id,"","030","","",
                "en-us","id_cr,title,id_element,body_combine","title","asc","","","",
                ""  , "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZF9lYXAiOiIxNjUxIiwiaWRfY29tcGFueSI6MTY1MTI2NTY3fQ.FznzxAPBbFF9kI2Vd6G39P6kO431dztk8TN9VYir-jY",
                "1651");
        arrayListCall.enqueue(new Callback<ListResponse<ArticleModel>>() {
            @Override
            public void onResponse(@NonNull Call<ListResponse<ArticleModel>> call, @NonNull Response<ListResponse<ArticleModel>> response) {
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

                    }
                }
                ListResponse<ArticleModel> loginResponse = response.body();
                if(loginResponse!=null) {
                    list = new ArrayList<>();
                    list = loginResponse.getList();
                    if (list.size() > 0 && list != null) {
                        adapter.update(list);
                    } else {
                        rv_audio_list.setVisibility(View.GONE);
                        not_found.setVisibility(View.VISIBLE);
                    }
                }else{
                    Global.showToast(getActivity(),"No data Found");
                }

            }

            @Override
            public void onFailure(@NonNull Call<ListResponse<ArticleModel>> call, @NonNull Throwable t) {
                progressDialog.dismiss();
//                login_btn.setClickable(true);
            }
        });

    }
    private void goTonextScreen(ArticleModel model) {
        Fragment detailFrag=new ArticleDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY, "ONLINE SEMINAR");
        bundle.putString(BUNDLE_KEY_DETAILS, new Gson().toJson(model));
        detailFrag.setArguments(bundle);
        Activity activity = getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).loadMainFragment(detailFrag, false);
        } else {
            replaceFragment(detailFrag,detailFrag.getClass().getSimpleName());
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
        text_toolbar.setText("ONLINE SEMINAR");
    }
    @OnClick(R.id.iv_back)
    public  void onClick(){
        getActivity().onBackPressed();
    }
}