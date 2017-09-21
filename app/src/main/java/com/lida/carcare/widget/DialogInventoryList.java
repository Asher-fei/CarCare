package com.lida.carcare.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;

/**
 * 盘点清单
 * Created by Administrator on 2017/7/5.
 */

public class DialogInventoryList extends Dialog {

    public DialogInventoryList(@NonNull Context context) {
        super(context);
    }

    public DialogInventoryList(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }
}
