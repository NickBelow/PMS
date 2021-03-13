package com.example.memobiledev;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * Class which represents fragment of a page
 */
public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;


    public PageFragment() {
        // Required empty public constructor
    }


    public static PageFragment newInstance(int mPage) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, mPage);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPage = getArguments().getInt(ARG_PAGE);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_fragment, container, false);

        return view;
    }
}