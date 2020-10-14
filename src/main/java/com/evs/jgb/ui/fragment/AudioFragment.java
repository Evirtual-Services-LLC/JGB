package com.evs.jgb.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evs.jgb.R;
import com.evs.jgb.adapter.ArticleListAdapter;
import com.evs.jgb.adapter.AudioAdapter;
import com.evs.jgb.model.parentModel.ArticleModel;
import com.evs.jgb.model.parentModel.AudioModel;
import com.evs.jgb.utils.Utills;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioFragment extends Fragment {
    @BindView(R.id.text_toolbar)
    TextView text_toolbar;
    @BindView(R.id.rv_audio_list)
    RecyclerView rv_audio_list;
    View includeLayout;
    TextView listDataText;
    ImageView listDataImg;
    ArrayList<AudioModel> list;
    AudioAdapter adapter;
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
        list = new ArrayList<>();
        list.add(new AudioModel("1", "Decisio making tips"));
        list.add(new AudioModel("2", "Deflate the pressure"));
        list.add(new AudioModel("3", "listening Tips"));
        list.add(new AudioModel("4", "Pregnancy and weight"));
        list.add(new AudioModel("5", "adjsuting to single Parents"));
        list.add(new AudioModel("6", "Bringing your new home Baby"));
        list.add(new AudioModel("7", "Adjusting to Single Parenthood"));
        list.add(new AudioModel("8", "Decisio making tips"));
        list.add(new AudioModel("9", "Deflate the pressure"));
        list.add(new AudioModel("10", "listening Tips"));
        list.add(new AudioModel("11", "Pregnancy and weight"));
        list.add(new AudioModel("12", "adjsuting to single Parents"));
        list.add(new AudioModel("13", "Bringing your new home Baby"));
        list.add(new AudioModel("14", "Adjusting to Single Parenthood"));

        adapter = new AudioAdapter(getActivity(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rv_audio_list.setLayoutManager(manager);
        rv_audio_list.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        text_toolbar.setText("AUDIO");
    }
    @OnClick(R.id.iv_back)
    public  void onClick(){
        getActivity().onBackPressed();
    }
}