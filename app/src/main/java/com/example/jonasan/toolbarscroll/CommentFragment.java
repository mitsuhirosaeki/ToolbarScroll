package com.example.jonasan.toolbarscroll;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by jinbocho on 2015/07/14.
 */
public class CommentFragment extends Fragment{

    @InjectView(R.id.list)
    ListView  listView;

    public static CommentFragment newInstance() {
        CommentFragment fragment = new CommentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CommentFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] members = { "mhidaka", "rongon_xp", "kacchi0516", "kobashinG",
                "seit", "kei_i_t", "furusin_oriver" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_expandable_list_item_1, members);

        listView.setAdapter(adapter);
    }
}
