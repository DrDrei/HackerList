package com.andreiusenka.hackerlist.Tasks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.andreiusenka.hackerlist.R;
import com.andreiusenka.hackerlist.data.Task;

import java.util.ArrayList;


public class TaskFragment extends Fragment implements TaskContract.View  {

    private TaskContract.Presenter mTaskPresenter;

    private View noTasksView;
    private LinearLayout tasksView;
    private TaskAdapter listAdapter;

    private ListView taskList;

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
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.task_fragment, container, false);

        // Setup the task list
        taskList = (ListView) root.findViewById(R.id.task_activity_listview);
        return root;
    }

    private static class TaskAdapter extends BaseAdapter {
        private ArrayList<Task> tasks;

        @Override
        public int getCount() {
            return tasks.size();
        }

        @Override
        public Object getItem(int i) {
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

            return listView;


        }
    }
}
