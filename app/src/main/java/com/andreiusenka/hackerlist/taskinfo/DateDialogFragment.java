package com.andreiusenka.hackerlist.taskinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.andreiusenka.hackerlist.R;

/**
 * Created by satyen on 28/01/17.
 */

public class DateDialogFragment extends DialogFragment {

    Button saveButton;
    Button cancelButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.datedialog_fragment, container, false);

        saveButton = (Button) root.findViewById(R.id.datedialog_savebutton);
        cancelButton = (Button) root.findViewById(R.id.datedialog_cancelbutton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return root;
    }
}
