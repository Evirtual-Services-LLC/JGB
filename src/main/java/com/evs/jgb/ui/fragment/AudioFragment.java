package com.evs.jgb.ui.fragment;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.evs.jgb.R;
import com.evs.jgb.adapter.ArticleListAdapter;
import com.evs.jgb.adapter.AudioAdapter;
import com.evs.jgb.adapter.CustomViewPagerAdapter;
import com.evs.jgb.model.parentModel.ArticleModel;
import com.evs.jgb.model.parentModel.AudioModel;
import com.evs.jgb.retrofit.ApiInterface;
import com.evs.jgb.retrofit.ApiInterfaceService;
import com.evs.jgb.retrofit.ListResponse;
import com.evs.jgb.utils.Global;
import com.evs.jgb.utils.Utills;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.evs.jgb.ui.fragment.ArticleNewFragment.BUNDLE_LIST_TEMP;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_DIVISION;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_MODULE;

public class AudioFragment extends Fragment {
    @BindView(R.id.text_toolbar)
    TextView text_toolbar;
    @BindView(R.id.etxt_search)
    EditText etxt_search;
    @BindView(R.id.iv_player)
    ImageView iv_player;
    @BindView(R.id.rv_audio_list)
    RecyclerView rv_audio_list;
    ArrayList<ArticleModel> list;
    AudioAdapter adapter;
    String module_id, devision_id;
    ACProgressFlower progressDialog;
    @BindView(R.id.not_found)
    TextView not_found;



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
        adapter = new AudioAdapter(getActivity());
        Bundle bundle = getArguments();
        if (bundle != null) {
            devision_id = bundle.getString(BUNDLE_ID_DIVISION);
            module_id = bundle.getString(BUNDLE_ID_MODULE);
            list = bundle.getParcelableArrayList(BUNDLE_LIST_TEMP);
            adapter.update(list);
        }

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rv_audio_list.setLayoutManager(manager);
        rv_audio_list.setAdapter(adapter);

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

    @Override
    public void onResume() {
        super.onResume();
        text_toolbar.setText("AUDIO");
        iv_player.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        getActivity().onBackPressed();
    }
}