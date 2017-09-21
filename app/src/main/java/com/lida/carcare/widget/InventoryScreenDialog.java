package com.lida.carcare.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;

/**
 * Created by Administrator on 2017/6/28.
 */

public class InventoryScreenDialog extends Dialog {

    public InventoryScreenDialog(@NonNull Context context) {
        super(context);
    }

    public InventoryScreenDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected InventoryScreenDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
