package com.example.androidacademyproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.androidacademyproject.R;

public class AuthorFragment extends Fragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        View layout = view.findViewById(R.id.aut_fragment);
        TextView textView = view.findViewById(R.id.aut_fragment_text);
        layout.setBackgroundColor(
                ContextCompat.getColor(getContext(), R.color.blue));

        textView.setText("I am a Fragment");

    }
}
