package com.dleal.linkfinder.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Daniel Leal on 09/03/16.
 */
public class ProgressDialogManager {

    private static ProgressDialog sProgressDialog;

    public static ProgressDialog showProgressDialog(Context context, String message) {
        if (sProgressDialog != null && sProgressDialog.isShowing())
            hideProgressDialog();
        sProgressDialog = ProgressDialog.show(context, null, message, true, false);
        return sProgressDialog;
    }

    public static ProgressDialog showProgressDialog(Context context, int messageId) {
        String message = context.getString(messageId);
        if (message != null)
            return showProgressDialog(context, message);
        else
            return null;

    }

    public static void hideProgressDialog() {
        if (sProgressDialog != null)
            sProgressDialog.dismiss();
    }

}
