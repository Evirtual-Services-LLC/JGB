package com.evs.jgb.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.evs.jgb.R;
import com.evs.jgb.ui.fragment.ArticleDetailsFragment;
import com.evs.jgb.utils.Utills;

public class AdoptionListActivity extends AppCompatActivity {

    View articleView;
    TextView listDataText;
    ImageView listDataImg;
    String dataStr, num = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_list);

        Utills.StatusBarColour(AdoptionListActivity.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.text_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        dataStr = getIntent().getStringExtra("list");
        mTitle.setText("ARTICLES".toUpperCase());

        if(dataStr.equalsIgnoreCase("articles")){
            mTitle.setText("ARTICLE".toUpperCase());
            listDataText.setText("Foster Parents Considering Adoption");
            num = "1";
        }
        if(dataStr.equalsIgnoreCase("Glossary")){
            mTitle.setText("GLOSSARY".toUpperCase());
            listDataText.setText("Legal Glossary A - D");
            num = "2";
        }if(dataStr.equalsIgnoreCase("providerSearch")){
            mTitle.setText("PROVIDER SEARCH".toUpperCase());
            listDataText.setText("Adoption Provider Locator");
            num = "3";
        }if(dataStr.equalsIgnoreCase("resources")){
            mTitle.setText("RESOURCES".toUpperCase());
            listDataText.setText("Adopt America Network");
            num = "4";
        }if(dataStr.equalsIgnoreCase("checkList")){
            mTitle.setText("CHECK LIST".toUpperCase());
            listDataText.setText("Child Care Handbook");
            num = "5";
        }if(dataStr.equalsIgnoreCase("handBook")){
            mTitle.setText("HAND BOOK".toUpperCase());
            listDataText.setText("Authorization for Foreign Travel With a Minor");
            num = "6";
        }if(dataStr.equalsIgnoreCase("legalForm")){
            mTitle.setText("LEGAL FORM".toUpperCase());
            listDataText.setText("Camp Locator");
            num = "7";
        }if(dataStr.equalsIgnoreCase("regulations")){
            mTitle.setText("REGULATIONS".toUpperCase());
            listDataText.setText("Child Care Regulations");
            num = "8";
        }

        articleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdoptionListActivity.this, ArticleDetailsFragment.class);
                intent.putExtra("details",num);
                startActivity(intent);
            }
        });

    }
}