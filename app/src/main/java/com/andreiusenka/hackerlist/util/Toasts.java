package com.andreiusenka.hackerlist.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by drei on 2017-01-28.
 */

public class Toasts {
    static public void toastMessage(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
