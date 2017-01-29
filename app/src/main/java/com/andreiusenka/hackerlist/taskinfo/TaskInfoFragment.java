package com.andreiusenka.hackerlist.taskinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.andreiusenka.hackerlist.R;

import java.util.Date;

/**
 * Created by satyen on 28/01/17.
 */

public class TaskInfoFragment extends Fragment implements TaskInfoContract.View {
    private TaskInfoContract.Presenter mTaskInfoPresenter;

    private ActionBar toolbar;
    private EditText taskTitleEditText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static TaskInfoFragment newInstance() {
        return new TaskInfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.taskinfo_fragment, container, false);


        // Initialize toolbar
        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        taskTitleEditText = (EditText) root.findViewById(R.id.taskinfo_tasktitle_edittext);

        TextView dateTextView = (TextView) root.findViewById(R.id.taskinfo_datetext);
        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DateDialogFragment dateDialogFragment = new DateDialogFragment();
                dateDialogFragment.show(((AppCompatActivity) getActivity()).getSupportFragmentManager(), "Date" );
//                mTaskInfoPresenter.dateTextClicked(); TODO: put in presenter? LUL
            }
        });

        return root;
    }

    @Override
    public void setPresenter(TaskInfoContract.Presenter presenter) {
        mTaskInfoPresenter = presenter;
    }

    @Override
    public void showDateDialogFragment() {
    }
}
