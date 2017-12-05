package com.hawky.rxpal.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.LinearLayout;

import com.hawky.rxpal.R;

/**
 * 默认加载框
 *
 * @author [*昨日重现*] lhy_ycu@163.com
 * @since version 1.0
 */
public class LoadingDialog extends Dialog {

    public interface ProgressListener {
        void onCancelProgress();
    }

    /**
     * 构造加载框
     *
     * @param context    上下文
     * @param cancelable 是否可取消
     * @param listener   取消监听
     */
    public LoadingDialog(Context context, boolean cancelable, final ProgressListener listener) {
        super(context, R.style.theme_dialog_loading);
        View view = View.inflate(context, R.layout.dialog_general_loading, null);
        LinearLayout layout = view.findViewById(R.id.ll_loading_dialog);
        setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                if (listener != null) {
                    listener.onCancelProgress();
                }
            }
        });
        setCancelable(cancelable);
    }

}
