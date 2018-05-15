package com.labs.android.laba1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.security.Key;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuestionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class QuestionsFragment extends Fragment {
    private static final String ARG_HAGHMAP = "hashMap";
    private HashMap<String, String> hashMap;

    public QuestionsFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment QuestionsFragment.
     */
    public static QuestionsFragment newInstance(HashMap<String, String> hm) {
        QuestionsFragment fragment = new QuestionsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_HAGHMAP, hm);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = this.getArguments();
        if(b.getSerializable(ARG_HAGHMAP) != null){
            Object tmp = b.getSerializable(ARG_HAGHMAP);
            hashMap = (HashMap<String, String> )tmp;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_questions, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getView();
        if(view != null) {
          InitializeTable(view);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    private void InitializeTable(View view) {
        TableLayout tableLayout = view.findViewById(R.id.tableLayout);
        //tableLayout.removeAllViewsInLayout();

        int count = tableLayout.getChildCount();
        for (int i = 1; i < count; i++) {
            View child = tableLayout.getChildAt(i);
            if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
        }

        for (String key: hashMap.keySet())
        {
            TableRow tableRow = new TableRow(getActivity());

            TextView question = new TextView(getActivity());
            question.setText(key);
            TextView answer = new TextView(getActivity());
            answer.setText(hashMap.get(key));
            tableRow.addView(question);
            tableRow.addView(answer);

            tableLayout.addView(tableRow);
        }
    }

}
