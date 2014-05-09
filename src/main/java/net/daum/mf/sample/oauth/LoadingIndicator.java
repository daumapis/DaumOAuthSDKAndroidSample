package net.daum.mf.sample.oauth;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface.OnCancelListener;
import android.text.TextUtils;
import android.view.Window;

public final class LoadingIndicator {

    private static final LoadingIndicator _instance = new LoadingIndicator();

    public static LoadingIndicator getInstance() {
        return _instance;
    }

    private ProgressDialog _dialog;

    public void startLoadingIndicator(
            Activity activity,
            String title,
            String message,
            boolean cancelable,
            OnCancelListener listener) {

        _dialog = new ProgressDialog(activity);
        Window theWindow = _dialog.getWindow();
        theWindow.requestFeature(Window.FEATURE_NO_TITLE);
        _dialog.setCancelable(cancelable);
        _dialog.setOnCancelListener(listener);
        if (!TextUtils.isEmpty(title)) {
            _dialog.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            _dialog.setMessage(message);
        }
        _dialog.show();

    }

    public void stopLoadingIndicator(final Activity activity) {
        if (_dialog != null && _dialog.isShowing()) {
            _dialog.dismiss();
            _dialog = null;
        }
    }

    public void startLoadingIndicatorOnUiThread(
            final Activity activity,
            final String title,
            final String message,
            final boolean cancelable,
            final OnCancelListener listener) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            public void run() {
                startLoadingIndicator(activity, title, message, cancelable, listener);
            }
        });
    }

    public void stopLoadingOnUiThread(final Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            public void run() {
                stopLoadingIndicator(activity);
            }
        });
    }


}


