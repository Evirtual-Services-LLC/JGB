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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evs.jgb.R;
import com.evs.jgb.adapter.ArticleListAdapter;
import com.evs.jgb.model.SectionModel;
import com.evs.jgb.model.parentModel.ArticleModel;
import com.evs.jgb.retrofit.ApiInterface;
import com.evs.jgb.retrofit.ApiInterfaceService;
import com.evs.jgb.retrofit.ListResponse;
import com.evs.jgb.ui.activity.MainActivity;
import com.evs.jgb.utils.Global;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.evs.jgb.ui.fragment.ArticleDetailsFragment.BUNDLE_KEY;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_DIVISION;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_MODULE;

public class ArticlesFragment extends Fragment {
    public static final String BUNDLE_ID_CRED = "id_cred";
    public static final String BUNDLE_ID_MODULE_DESCRP = "id_module";
    public static final String BUNDLE_KEY_DETAILS = "productModel";
    @BindView(R.id.text_toolbar)
    TextView text_toolbar;
    @BindView(R.id.rv_article_list)
    RecyclerView rv_article_list;
    @BindView(R.id.not_found)
    TextView not_found;
    ArrayList<ArticleModel> list;
    ArticleListAdapter adapter;
    @BindView(R.id.etxt_search)
    EditText etxt_search;
    private SearchView searchView;
    ACProgressFlower progressDialog;
    String module_id, devision_id;

    // scrollview
    private int bootCounter = 0;
    private int maxRecords = 400;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_articles, container, false);
        ButterKnife.bind(this, view);
        intialize();
        return view;

        // Intent intent = new Intent(ArticlesActivity.this,DetailsActivity.class);
        // intent.putExtra("details","article");
        // startActivity(intent);
    }

    private void intialize() {

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        progressDialog = Global.getProgress(getActivity(), "Please wait...");
        adapter = new ArticleListAdapter(getActivity());
        Bundle bundle = getArguments();
        if (bundle != null) {
            devision_id = bundle.getString(BUNDLE_ID_DIVISION);
            module_id = bundle.getString(BUNDLE_ID_MODULE);
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

        rv_article_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private List<ArticleModel> bootData() {
        List<ArticleModel> persons = new ArrayList<>();
        for (int i = bootCounter; i < bootCounter + 20; i++) {
            ArticleModel person = new ArticleModel();
            persons.add(person);
        }
        bootCounter += 20;
        return persons;
    }


    void getSectionResponse() {
        progressDialog.show();
        ApiInterface networkService = ApiInterfaceService.getClient().create(ApiInterface.class);
        Call<ListResponse<ArticleModel>> arrayListCall = networkService.ArticleModulelist(devision_id, module_id, "", "001", "", "",
                "en-us", "id_cr,title,id_element,body_combine", "title", "asc", "", "", "",
                "", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZF9lYXAiOiIxNjUxIiwiaWRfY29tcGFueSI6MTY1MTI2NTY3fQ.FznzxAPBbFF9kI2Vd6G39P6kO431dztk8TN9VYir-jY",
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
                if (loginResponse != null) {
                    list = new ArrayList<>();
                    list = loginResponse.getList();
                    if (list.size() > 0 && list != null) {
                        adapter.update(list);
                    } else {
                        rv_article_list.setVisibility(View.GONE);
                        not_found.setVisibility(View.VISIBLE);
                    }
                } else {
                    Global.showToast(getActivity(), "No data Found");
                }

            }

            @Override
            public void onFailure(@NonNull Call<ListResponse<ArticleModel>> call, @NonNull Throwable t) {
                progressDialog.dismiss();
//                login_btn.setClickable(true);
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

    private void goTonextScreen(String id_cr, String id_module, ArticleModel model) {
        Fragment detailFrag = new ArticleDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY, "PARENTING DETAILS");
        bundle.putString(BUNDLE_ID_DIVISION, devision_id);
        bundle.putString(BUNDLE_ID_MODULE, module_id);
        bundle.putString(BUNDLE_ID_CRED, id_cr);
        bundle.putString(BUNDLE_ID_MODULE_DESCRP, id_module);
        bundle.putString(BUNDLE_KEY_DETAILS, new Gson().toJson(model));
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
    public void onResume() {
        super.onResume();
        text_toolbar.setText("ARTICLE");
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        getActivity().onBackPressed();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
        // Associate searchable configuration with the SearchView

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}