package com.andreiusenka.hackerlist.Tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.andreiusenka.hackerlist.R;
import com.andreiusenka.hackerlist.data.Task;
import com.andreiusenka.hackerlist.login.LoginActivity;
import com.andreiusenka.hackerlist.util.FirebaseUtil;
import com.andreiusenka.hackerlist.util.LogUser;
import com.andreiusenka.hackerlist.taskinfo.TaskInfoActivity;
import com.andreiusenka.hackerlist.util.Toasts;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


public class TaskFragment extends Fragment implements TaskContract.View  {

    private TaskContract.Presenter mTaskPresenter;

    private FirebaseAuth.AuthStateListener mAuthListener;

    private View noTasksView;
    private LinearLayout tasksView;
    private TaskAdapter listAdapter;

    private ListView taskList;
    private TaskInfoListener taskInfoListener = new TaskInfoListener() {
        @Override
        public void onTaskItemClick(Task task) {
            mTaskPresenter.taskClicked(task);
        }
    };

    private CheckboxListener checkboxListener = new CheckboxListener() {
        @Override
        public void onCheckbocClick(Task task) {
            mTaskPresenter.onCheckboxClicked(task);
        }
    };

    private PlayToggleListener playToggleListener = new PlayToggleListener() {
        @Override
        public void onPlayClick(Task task) {
            mTaskPresenter.onPlayButtonToggle(task);
        }
    };

    public static TaskFragment newInstance() {
        return new TaskFragment();
    }

    @Override
    public void setPresenter(TaskContract.Presenter presenter) {
        mTaskPresenter = presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listAdapter = new TaskAdapter(new ArrayList<Task>(0), taskInfoListener, checkboxListener, playToggleListener);

        mAuthListener = FirebaseUtil.addLogOutListener(getActivity());

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.task_fragment, container, false);


        //TODO: remove me
        ArrayList<Task> test = new ArrayList<>();
        test.add(new Task());
        listAdapter.setTasks(test);

        // Setup the task list
        taskList = (ListView) root.findViewById(R.id.task_activity_listview);
        taskList.setAdapter(listAdapter);
        return root;
    }


    public void updateData() {
        listAdapter.notifyDataSetChanged();
    }

    private static class TaskAdapter extends BaseAdapter {
        private List<Task> tasks;
        private TaskInfoListener taskInfoListener;
        private CheckboxListener checkboxListener;
        private PlayToggleListener playToggleListener;

        public TaskAdapter(List<Task> tasks, TaskInfoListener taskInfoListener, CheckboxListener checkboxListener, PlayToggleListener playToggleListener) {
            this.tasks = tasks;
            this.taskInfoListener = taskInfoListener;
            this.checkboxListener = checkboxListener;
            this.playToggleListener = playToggleListener;
        }

        public void setTasks(List<Task> tasks) {
            this.tasks = tasks;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return tasks.size();
        }

        @Override
        public Task getItem(int i) {
            return tasks.get(i);
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
                listView = inflater.inflate(R.layout.task_listitem, viewGroup, false);
            }

            final Task task = getItem(i);

            CheckBox checkboxView = (CheckBox) listView.findViewById(R.id.checkbox_task);
            checkboxView.setChecked(task.isComplete());

            TextView textViewTitle = (TextView) listView.findViewById(R.id.textview_tasktitle);
            textViewTitle.setText(task.getTitleForListView());

            TextView textViewTime = (TextView) listView.findViewById(R.id.textview_tasktime);
            textViewTime.setText(task.getTimeForListView());

            ImageButton imageButton = (ImageButton) listView.findViewById(R.id.imagebutton_taskplay);
            if (task.isActive()) {
                imageButton.setImageResource(R.drawable.ic_pause_circle_outline_black_48dp);
            } else {
                imageButton.setImageResource(R.drawable.ic_play_circle_outline_black_48dp);
            }

            listView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    taskInfoListener.onTaskItemClick(task);
                }
            });

            checkboxView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkboxListener.onCheckbocClick(task);
                }
            });

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    playToggleListener.onPlayClick(task);
                }
            });

            return listView;
        }
    }

    private interface CheckboxListener {
        void onCheckbocClick(Task task);
    }

    private interface PlayToggleListener {
        void onPlayClick(Task task);
    }

    private interface TaskInfoListener {
        void onTaskItemClick(Task task);
    }

    public void showTaskInfo(String taskID) {
        Intent intent = new Intent(getContext(), TaskInfoActivity.class);
        intent.putExtra(TaskInfoActivity.TASK_ID_EXTRA, taskID);
        startActivity(intent);
    }

    // Log out handler.

    private void logUserOut() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
