package com.evs.jgb.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
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

import com.evs.jgb.R;
import com.evs.jgb.ui.activity.MainActivity;

public class International extends Fragment {

    RelativeLayout layoutParentOne, layoutParentTwo, layoutParentThree;
    TextView textHomeOne, textHomeTwo,textHomeThree;
    ImageView imageHomeOne,imageHomeTwo,imageHomeThree;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.parenting, container,false);

        layoutParentOne = view.findViewById(R.id.layoutParentOne);
        layoutParentTwo = view.findViewById(R.id.layoutParentTwo);
        layoutParentThree = view.findViewById(R.id.layoutParentThree);

        textHomeOne = view.findViewById(R.id.textHomeOne);
        textHomeTwo = view.findViewById(R.id.textHomeTwo);
        textHomeThree = view.findViewById(R.id.textHomeThree);

        imageHomeOne = view.findViewById(R.id.imageHomeOne);
        imageHomeTwo = view.findViewById(R.id.imageHomeTwo);
        imageHomeThree = view.findViewById(R.id.imageHomeThree);

        textHomeOne.setText("Immigration to the U.S.");
        textHomeTwo.setText("Relocating Abord");
        textHomeThree.setText("Emigration");

        imageHomeOne.setImageResource(R.drawable.international_one);
        imageHomeTwo.setImageResource(R.drawable.international_two);
        imageHomeThree.setImageResource(R.drawable.international_three);

        layoutParentOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment someFragment = new ParentingNew();
                Activity activity = getActivity();
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).loadMainFragment(someFragment, false);
                } else {
                    ((MainActivity) activity).replaceFragment(someFragment, someFragment.getClass().getSimpleName());
                }
            }
        });

        layoutParentTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Toast.makeText(getActivity(), "Working", Toast.LENGTH_SHORT).show();
                Fragment someFragment = new AdoptionNew();
                Activity activity = getActivity();
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).loadMainFragment(someFragment, false);
                } else {
                    ((MainActivity) activity).replaceFragment(someFragment, someFragment.getClass().getSimpleName());
                }
            }
        });

        layoutParentThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment someFragment = new ChildCare();
                Activity activity = getActivity();
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).loadMainFragment(someFragment, false);
                } else {
                    ((MainActivity) activity).replaceFragment(someFragment, someFragment.getClass().getSimpleName());
                }
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("INTERNATIONAL");
    }
}
