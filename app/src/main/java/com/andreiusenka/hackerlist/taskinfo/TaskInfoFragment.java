package com.andreiusenka.hackerlist.taskinfo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.andreiusenka.hackerlist.R;
import com.andreiusenka.hackerlist.entities.TaskSingleton;
import com.andreiusenka.hackerlist.entities.TimeSegment;
import com.andreiusenka.hackerlist.util.Toasts;

import java.util.ArrayList;
import java.util.List;

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

    private TimesAdapter listAdapter;
    private ListView timesList;


    private DatePickerDialog.OnDateSetListener mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            mTaskInfoPresenter.setDate(year, month, dayOfMonth);
        }
    };

    @Override
    public void setCompletionCheckbox(boolean value) {
        completionCheckbox.setChecked(value);
    }

    @Override
    public void setDurationText(String duration) {
        durationTextView.setText(duration);
    }

    @Override
    public void setDateText(String date) {
        dateTextView.setText(date);
    }

    @Override
    public void setTitleText(String title) {
        taskTitleEditText.setText(title);
    }

    @Override
    public void showDeleteAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Delete Task");
        alertDialog.setMessage("Are you sure you want to DELETE?");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DELETE",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        TaskSingleton.getInstance().getTask().removeTask();
                        TaskSingleton.getInstance().setTask(null);
                        Toasts.toastMessage(getContext(), "Task Deleted.");
                        returnToPrevious();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Keep",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void returnToPrevious() {
        getActivity().onBackPressed();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listAdapter = new TimesAdapter(new ArrayList<TimeSegment>(0));


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
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");

        timesList = (ListView) root.findViewById(R.id.taskinfo_times_listview);
        timesList.setAdapter(listAdapter);

        taskTitleEditText = (EditText) root.findViewById(R.id.taskinfo_tasktitle_edittext);
//        taskTitleEditText.setText(TaskSingleton.getInstance().getTask().getTitle());

        durationTextView = (TextView) root.findViewById(R.id.taskinfo_timetext);
        durationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNumberPicker();
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
    public void onResume() {
        super.onResume();
        mTaskInfoPresenter.start();
    }

    public void showNumberPicker() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setTitle("Duration (Hours : Minutes)");
        dialog.setContentView(R.layout.numberpickerdialog);
        Button saveButton = (Button) dialog.findViewById(R.id.timedialog_savebutton);
        Button cancelButton = (Button) dialog.findViewById(R.id.timedialog_cancelbutton);
        final NumberPicker npHours = (NumberPicker) dialog.findViewById(R.id.numberpicker_hours);
        final NumberPicker npMinutes = (NumberPicker) dialog.findViewById(R.id.numberpicker_minutes);

        npHours.setMaxValue(100);
        npHours.setMinValue(0);

        npMinutes.setMaxValue(60);
        npMinutes.setMinValue(0);

        npHours.setWrapSelectorWheel(false);
        npMinutes.setWrapSelectorWheel(false);

        saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                mTaskInfoPresenter.setTime(new Long(npHours.getValue()), new Long(npMinutes.getValue()));
                dialog.dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void updateData() {
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void setData(List<TimeSegment> times) {
        listAdapter.setTimes(times);
        updateData();
        if (times.isEmpty()) {
            timesList.setVisibility(View.GONE);
        } else {
            timesList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setPresenter(TaskInfoContract.Presenter presenter) {
        mTaskInfoPresenter = presenter;
    }

    @Override
    public boolean getCompletionCheckbox() {
        return completionCheckbox.isChecked();
    }

    @Override
    public String getDurationText() {
        return (String) durationTextView.getText();
    }

    @Override
    public String getDateText() {
        return (String) dateTextView.getText();
    }

    @Override
    public String getTitleText() {
        return taskTitleEditText.getText().toString();
    }

    @Override
    public void showDateDialogFragment() {
    }

    private static class TimesAdapter extends BaseAdapter {
        private List<TimeSegment> timeSegments;

        public TimesAdapter(List<TimeSegment> times) {
            this.timeSegments = times;
        }

        public void setTimes(List<TimeSegment> times) {
            this.timeSegments = times;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return timeSegments.size();
        }

        @Override
        public TimeSegment getItem(int i) {
            return timeSegments.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View listView = view;
            if (listView == null) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                listView = inflater.inflate(R.layout.startend_listitem, viewGroup, false);
            }

            final TimeSegment timeSegment = getItem(i);

            TextView textViewTitle = (TextView) listView.findViewById(R.id.textview_starttime);
            textViewTitle.setText(timeSegment.getStartTimeForListItem());

            TextView textViewTime = (TextView) listView.findViewById(R.id.textview_endtime);
            textViewTime.setText(timeSegment.getEndTimeForListItem());

            return listView;
        }
    }
}
