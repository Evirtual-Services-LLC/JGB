package com.evs.jgb.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.evs.jgb.R;
import com.evs.jgb.ui.activity.AdoptionListActivity;

public class AdoptionNew extends Fragment {

    TextView textDashboardOne, textDashboardTwo, textDashboardThree,textDashboardFour,textDashboardFive,textDashboardSix;
    LinearLayout layoutHomeOne,layoutHomeTwo,layoutHomeThree,layoutHomeFour,layoutHomeFive,layoutHomeSix;
    ImageView dashboardImageOne, dashboardImageTwo,dashboardImageThree,dashboardImageFour,dashboardImageFive,dashboardImageSix;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard, container,false);
        textDashboardOne = view.findViewById(R.id.textDashboardOne);
        textDashboardTwo = view.findViewById(R.id.textDashboardTwo);
        textDashboardThree = view.findViewById(R.id.textDashboardThree);
        textDashboardFour = view.findViewById(R.id.textDashboardFour);
        textDashboardFive = view.findViewById(R.id.textDashboardFive);
        textDashboardSix = view.findViewById(R.id.textDashboardSix);

        layoutHomeOne = view.findViewById(R.id.layoutHomeOne);
        layoutHomeTwo = view.findViewById(R.id.layoutHomeTwo);
        layoutHomeThree = view.findViewById(R.id.layoutHomethree);
        layoutHomeFour = view.findViewById(R.id.layoutHomeFour);
        layoutHomeFive = view.findViewById(R.id.layoutHomeFive);
        layoutHomeSix = view.findViewById(R.id.layoutHomeSix);

        dashboardImageOne = view.findViewById(R.id.dashboardImageOne);
        dashboardImageTwo = view.findViewById(R.id.dashboardImageTwo);
        dashboardImageThree = view.findViewById(R.id.dashboardImageThree);
        dashboardImageFour = view.findViewById(R.id.dashboardImageFour);
        dashboardImageFive = view.findViewById(R.id.dashboardImageFive);
        dashboardImageSix = view.findViewById(R.id.dashboardImageSix);

        layoutHomeFive.setVisibility(View.GONE);
        layoutHomeSix.setVisibility(View.GONE);

        textDashboardOne.setText("Articles");
        textDashboardTwo.setText("Grossary");
        textDashboardThree.setText("Provider Search");
        textDashboardFour.setText("Resources");

        dashboardImageOne.setImageResource(R.drawable.note1);
        dashboardImageTwo.setImageResource(R.drawable.music);
        dashboardImageThree.setImageResource(R.drawable.study);
        dashboardImageFour.setImageResource(R.drawable.note2);

        layoutHomeOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdoptionListActivity.class);
                intent.putExtra("list","articles");
                startActivity(intent);
            }
        });

        layoutHomeTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdoptionListActivity.class);
                intent.putExtra("list","Glossary");
                startActivity(intent);
            }
        });

        layoutHomeThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdoptionListActivity.class);
                intent.putExtra("list","providerSearch");
                startActivity(intent);
            }
        });

        layoutHomeFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdoptionListActivity.class);
                intent.putExtra("list","resources");
                startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("ADOPTION");
    }
}
