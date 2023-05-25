package com.example.trazoodialogfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class BottomFragment extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set style for the dialog fragment
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomDialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);

        // Initialize your fragment's views and set up any necessary listeners

        return view;
    }
}
