package com.geelaro.wechatex;

import android.widget.Toast;

/**
 * Created by LEE on 2017/12/5.
 */

public class ShowToast {

    public static void Short(CharSequence charSequence){
        Toast.makeText(WeChatApp.getContext(),charSequence,Toast.LENGTH_SHORT).show();
    }

    public static void Long(CharSequence charSequence){
        Toast.makeText(WeChatApp.getContext(),charSequence,Toast.LENGTH_LONG).show();
    }
}
