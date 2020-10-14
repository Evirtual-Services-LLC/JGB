package com.evs.jgb.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.evs.jgb.R;

public class TermsOfUse extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.terms_of_use, container,false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("TERMS OF USE");
    }
}
