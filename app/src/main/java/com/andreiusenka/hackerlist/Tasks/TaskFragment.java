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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.andreiusenka.hackerlist.R;
import com.andreiusenka.hackerlist.data.Task;
import com.andreiusenka.hackerlist.login.LoginActivity;
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
    private TaskListener taskListener = new TaskListener() {
        @Override
        public void onTaskItemClick(Task task) {
            mTaskPresenter.taskClicked(task);
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
        listAdapter = new TaskAdapter(new ArrayList<Task>(0));

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // Sign user out
                    Toasts.toastMessage(getContext(), "Logging out...");
                    logUserOut();
                }
            }
        };

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



    private static class TaskAdapter extends BaseAdapter {
        private List<Task> tasks;

        public TaskAdapter(List<Task> tasks) {
            this.tasks = tasks;
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

            Task task = getItem(i);

            CheckBox checkboxView = (CheckBox) listView.findViewById(R.id.checkbox_task);

            TextView textViewTitle = (TextView) listView.findViewById(R.id.textview_tasktitle);
            textViewTitle.setText(task.getTitleForListView());

            TextView textViewTime = (TextView) listView.findViewById(R.id.textview_tasktime);
            textViewTime.setText(task.getTimeForListView());

            ImageButton imageButton = (ImageButton) listView.findViewById(R.id.imagebutton_taskplay);
            if (task.isActive()) {
                imageButton.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
            } else {
                imageButton.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
            }



            return listView;
        }
    }

    private interface TaskListener {
        void onTaskItemClick(Task task);
    }


    // Log out handler.

    private void logUserOut() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
