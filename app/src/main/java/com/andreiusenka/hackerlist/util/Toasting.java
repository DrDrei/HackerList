package com.andreiusenka.hackerlist.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by drei on 2017-01-28.
 */

public class Toasting {
    static public void displayToast(Context context, String string) {
        Toast toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
    }
}
