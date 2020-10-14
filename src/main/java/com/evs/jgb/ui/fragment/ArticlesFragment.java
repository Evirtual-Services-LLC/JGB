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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evs.jgb.R;
import com.evs.jgb.adapter.ArticleListAdapter;
import com.evs.jgb.model.parentModel.ArticleModel;
import com.evs.jgb.ui.activity.MainActivity;
import com.evs.jgb.utils.Utills;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.evs.jgb.ui.fragment.ArticleDetailsFragment.BUNDLE_KEY;

public class ArticlesFragment extends Fragment {
    @BindView(R.id.text_toolbar)
    TextView text_toolbar;
    @BindView(R.id.rv_article_list)
    RecyclerView rv_article_list;
    ArrayList<ArticleModel> list;
    ArticleListAdapter adapter;


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
        list = new ArrayList<>();
        list.add(new ArticleModel("1", "Adjusting to Single Parenthood"));
        list.add(new ArticleModel("2", "Becoming Single Parenthood"));
        list.add(new ArticleModel("3", "bringing ypur single baby"));
        list.add(new ArticleModel("4", "Budegeting Single Parenthood"));
        list.add(new ArticleModel("5", "talking Parenthood"));
        list.add(new ArticleModel("6", "Adjusting to Single Parenthood"));
        list.add(new ArticleModel("7", "Bringing Single Parenthood"));
        list.add(new ArticleModel("8", "Adjusting to Single Parenthood"));
        list.add(new ArticleModel("9", "talking Parenthood"));
        list.add(new ArticleModel("10", "Bringing Single Parenthood"));
        list.add(new ArticleModel("11", "Budegeting Single Parenthood"));
        list.add(new ArticleModel("12", "Becoming Single Parenthood"));
        list.add(new ArticleModel("13", "talking Parenthood"));
        list.add(new ArticleModel("14", "Budegeting Single Parenthood"));
        list.add(new ArticleModel("15", "Adjusting to Single Parenthood"));
        list.add(new ArticleModel("16", "Budegeting Single Parenthood"));

        adapter = new ArticleListAdapter(getActivity(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rv_article_list.setLayoutManager(manager);
        rv_article_list.setAdapter(adapter);
        adapter.setOnClickListener((adapter1, position) -> {
            ArticleModel model=adapter.get(position);
            goTonextScreen();
        });

    }

    private void goTonextScreen() {
        Fragment detailFrag=new ArticleDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY, "PARENTING DETAILS");
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
        text_toolbar.setText("ARTICLE");
    }

    @OnClick(R.id.iv_back)
    public  void onClick(){
        getActivity().onBackPressed();
    }
}