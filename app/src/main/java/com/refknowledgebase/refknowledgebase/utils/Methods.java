package com.refknowledgebase.refknowledgebase.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.refknowledgebase.refknowledgebase.R;

import org.w3c.dom.Text;

import me.tankery.lib.circularseekbar.CircularSeekBar;

public class Methods {

    private static ProgressDialog mProgressDialog;

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else
//            showLongToast(context.getString(R.string.error_network_check), context);
            Toast.makeText(context, context.getString(R.string.error_network_check), Toast.LENGTH_SHORT).show();
        return false;
    }

    public static void showProgress(Context context) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(context.getString(R.string.loading));
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    public static void showProgress_data(Context context) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(context.getString(R.string.loading_data));
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    public static void closeProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    public static void showAlertDialog(String msg, Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(msg);
        dialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.create().show();
    }

//    private static Dialog dialog;
//    private static int pStatus = 0;
//    static final Handler handler = new Handler();
//
//    public static void showProgress (Context context){
//        if (dialog != null && dialog.isShowing()){
//            dialog.dismiss();
//            dialog = null;
//        }
//        dialog = new Dialog(context);
//        dialog.setContentView(R.layout.activity_loading);
//
//
//        final CircularSeekBar circularSeekBar = dialog.findViewById(R.id.circle_bar);
//        final TextView tv_percent = dialog.findViewById(R.id.tv_percent);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                pStatus = 0;
//                while (pStatus <= 100) {
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            circularSeekBar.setProgress(pStatus);
//                            tv_percent.setText(pStatus + " %");
//                        }
//                    });
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    pStatus++;
//                }
////                Intent intent_google = new Intent(Activity_Loading.this, DashboardActivity.class);
////                startActivity(intent_google);
//            }
//        }).start();
//        dialog.setCancelable(true);
//        dialog.show();
//    }
//
//    public static void closeProgress() {
//        if (dialog != null) {
//            dialog.dismiss();
//            dialog = null;
//        }
//    }
}
