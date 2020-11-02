package com.evs.jgb.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.evs.jgb.R;
import com.evs.jgb.model.parentModel.ArticleModel;
import com.evs.jgb.retrofit.ApiInterface;
import com.evs.jgb.retrofit.ApiInterfaceService;
import com.evs.jgb.retrofit.ListResponse;
import com.evs.jgb.ui.activity.ArticleDetailsModel;
import com.evs.jgb.ui.activity.MainActivity;
import com.evs.jgb.utils.Global;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.evs.jgb.ui.fragment.ArticlesFragment.BUNDLE_ID_CRED;
import static com.evs.jgb.ui.fragment.ArticlesFragment.BUNDLE_ID_MODULE_DESCRP;
import static com.evs.jgb.ui.fragment.ArticlesFragment.BUNDLE_KEY_DETAILS;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_DIVISION;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_ID_MODULE;
import static com.evs.jgb.ui.fragment.Parenting.BUNDLE_TEXT;

public class ArticleDetailsFragment extends Fragment {
    public static final String BUNDLE_KEY = "key";
    @BindView(R.id.text_toolbar)
    TextView text_toolbar;
    @BindView(R.id.iv_pdf)
    ImageView iv_pdf;
    String details;
    @BindView(R.id.textHeading)
    TextView textHeading;
    @BindView(R.id.textDetail)
    TextView textDetail;
    String module_id, devision_id, id_cred, id_module_desc;
    ACProgressFlower progressDialog;
    ArrayList<ArticleDetailsModel> list;
    private ArticleModel articleModel;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_details, container, false);
        ButterKnife.bind(this, view);
        intialize();
        return view;

        // Intent intent = new Intent(ArticlesActivity.this,DetailsActivity.class);
        // intent.putExtra("details","article");
        // startActivity(intent);
    }

    private void intialize() {
        progressDialog = Global.getProgress(getActivity(), "Please wait...");
        Bundle bundle = getArguments();
        if (bundle != null) {
            details = bundle.getString(BUNDLE_KEY);
            devision_id = bundle.getString(BUNDLE_ID_DIVISION);
            module_id = bundle.getString(BUNDLE_ID_MODULE);
            id_cred = bundle.getString(BUNDLE_ID_CRED);
            id_module_desc = bundle.getString(BUNDLE_ID_MODULE_DESCRP);
            String json = bundle.getString(BUNDLE_KEY_DETAILS);
            articleModel = new Gson().fromJson(json, ArticleModel.class);
            String mHtmlString = articleModel.getBody_combine();
            String plainText = Html.fromHtml(mHtmlString).toString();
            textHeading.setText(articleModel.getTitle());
            textDetail.setText(plainText);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        text_toolbar.setText(details);
        iv_pdf.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        getActivity().onBackPressed();
    }

}