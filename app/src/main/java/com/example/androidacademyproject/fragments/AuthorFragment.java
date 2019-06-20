package com.example.androidacademyproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.androidacademyproject.R;

public class AuthorFragment extends Fragment {

    private static final String TEXT_KEY = "TEXT_KEY";

    private String text;

    public static AuthorFragment createInstance(String text){
        Bundle args = new Bundle();
        args.putString(TEXT_KEY, text);

        AuthorFragment fragment = new AuthorFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.author_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        text = args.getString(TEXT_KEY);

        View layout = view.findViewById(R.id.aut_fragment);
        TextView textView = view.findViewById(R.id.aut_fragment_text);
//        layout.setBackgroundColor(
//                ContextCompat.getColor(getContext(), R.color.blue));

        textView.setText(text);
    }

    public void setText(String text){
        this.text = text;
    }
}
