package com.evs.jgb.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import androidx.fragment.app.FragmentTransaction;

import com.evs.jgb.R;
import com.evs.jgb.ui.activity.MainActivity;

public class DashBoard extends Fragment {

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

        textDashboardOne.setText("Parenting");
        textDashboardTwo.setText("Aging");
        textDashboardThree.setText("Balancing");
        textDashboardFour.setText("Thriving");
        textDashboardFive.setText("Working");
        textDashboardSix.setText("Living");

        dashboardImageOne.setImageResource(R.drawable.parenting);
        dashboardImageTwo.setImageResource(R.drawable.aging);
        dashboardImageThree.setImageResource(R.drawable.balancing);
        dashboardImageFour.setImageResource(R.drawable.thriving);
        dashboardImageFive.setImageResource(R.drawable.working);
        dashboardImageSix.setImageResource(R.drawable.living);


        layoutHomeOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Parenting();
                Activity activity = getActivity();
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).loadMainFragment(fragment, true);
                } else {
                    replaceFragment(fragment, fragment.getClass().getSimpleName());
                }
            }
        });

        layoutHomeTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AgingFragment();
                Activity activity = getActivity();
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).loadMainFragment(fragment, true);
                } else {
                    replaceFragment(fragment, fragment.getClass().getSimpleName());
                }
            }
        });

        layoutHomeThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new BalancingFragment();
                Activity activity = getActivity();
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).loadMainFragment(fragment, true);
                } else {
                    replaceFragment(fragment, fragment.getClass().getSimpleName());
                }
            }
        });

        layoutHomeFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ThrivingFragment();
                Activity activity = getActivity();
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).loadMainFragment(fragment, true);
                } else {
                    replaceFragment(fragment, fragment.getClass().getSimpleName());
                }
            }
        });

        layoutHomeFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new WorkingFragment();
                Activity activity = getActivity();
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).loadMainFragment(fragment, true);
                } else {
                    replaceFragment(fragment, fragment.getClass().getSimpleName());
                }
            }
        });

        layoutHomeSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new LivingFragment();
                Activity activity = getActivity();
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).loadMainFragment(fragment, true);
                } else {
                    replaceFragment(fragment, fragment.getClass().getSimpleName());
                }
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("DASHBOARD");
    }
    @SuppressLint("WrongConstant")
    public void replaceFragment(Fragment fragment, String backstack_name) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_container, fragment, backstack_name);
        fragmentTransaction.addToBackStack(backstack_name);
        fragmentTransaction.commit();
    }
}
