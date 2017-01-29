package com.andreiusenka.hackerlist.taskinfo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.andreiusenka.hackerlist.R;

/**
 * Created by satyen on 28/01/17.
 */

public class TaskInfoFragment extends Fragment implements TaskInfoContract.View {
    private TaskInfoContract.Presenter mTaskInfoPresenter;

    private Toolbar toolbar;
    private EditText taskTitleEditText;
    private TextView dateTextView;
    private TextView durationTextView;
    private CheckBox completionCheckbox;

    private TimePickerDialog.OnTimeSetListener mOnTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
            mTaskInfoPresenter.setTime(hourOfDay, minute);
        }
    };

    private DatePickerDialog.OnDateSetListener mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            mTaskInfoPresenter.setDate(year, month, dayOfMonth);
        }
    };


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
        toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");

        taskTitleEditText = (EditText) root.findViewById(R.id.taskinfo_tasktitle_edittext);
        durationTextView = (TextView) root.findViewById(R.id.taskinfo_timetext);
        durationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), mOnTimeSetListener, mTaskInfoPresenter.getHourOfDay(), mTaskInfoPresenter.getMinute(), false);
                timePickerDialog.show();
            }
        });

        completionCheckbox = (CheckBox) root.findViewById(R.id.taskinfo_checkbox);

        dateTextView = (TextView) root.findViewById(R.id.taskinfo_datetext);
        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), mOnDateSetListener, mTaskInfoPresenter.getYear(), mTaskInfoPresenter.getMonth(), mTaskInfoPresenter.getDayOfMonth());
                datePickerDialog.show();
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
