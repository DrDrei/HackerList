package com.andreiusenka.hackerlist.taskinfo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.NumberPicker;

import com.andreiusenka.hackerlist.R;

/**
 * Created by satyen on 29/01/17.
 */

public class NumberPickerDialog implements NumberPicker.OnValueChangeListener {

    private Context context;

    @Override
    public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {

    }

    public void show() {
        final Dialog dialog = new Dialog(context, R.style.AppTheme);
        dialog.setContentView(R.layout.numberpickerdialog);
    }
}
