package com.mobile_prog.habit_quest.contexts;

import android.net.Uri;

import com.huawei.hms.support.account.result.AuthAccount;

public class AuthContext {
    private static AuthAccount current = null;

    //bisa dapet:
//    Log.i(TAG, "display name:" + authAccount.getDisplayName());
//    Log.i(TAG, "photo uri string:" + authAccount.getAvatarUriString());
//    Log.i(TAG, "photo uri:" + authAccount.getAvatarUri());
//    Log.i(TAG, "email:" + authAccount.getEmail());
//    Log.i(TAG, "openid:" + authAccount.getOpenId());
//    Log.i(TAG, "unionid:" + authAccount.getUnionId());

    public static String getName() {
        if (current == null) return "";
        return current.getDisplayName();
    }

    public static String getAvatarUriString() {
        if (current == null) return "";
        return current.getAvatarUriString();
    }

    public static String getEmail() {
        if (current == null) return "";
        return current.getEmail();
    }

    public static String getId() {
        if (current == null) return "";
        return current.getUnionId();
    }

    public static void set(AuthAccount account) {
        current = account;
    }
}
