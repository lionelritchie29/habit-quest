package com.mobile_prog.habit_quest.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;
import com.huawei.hms.support.hwid.ui.HuaweiIdAuthButton;
import com.mobile_prog.habit_quest.R;

public class LoginActivity extends AppCompatActivity {

    private HuaweiIdAuthButton huaweiSignInBtn;

    // for huawei single sign on
    private AccountAuthService mAuthService;
    private AccountAuthParams mAuthParams;
    private static final int REQUEST_CODE_SIGN_IN = 1000;
    private final String TAG = "HuaweiAuthLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        disableNightMode();
        initializeHuaweiSingleSignOn();
    }

    private void disableNightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    private void initializeHuaweiSingleSignOn() {
        huaweiSignInBtn = findViewById(R.id.huawei_sign_in_btn);
        huaweiSignInBtn.setOnClickListener(v -> {
            Log.i(TAG, "Logging in");
            silentSignInByHwId();
        });
    }

    private void silentSignInByHwId() {
        mAuthParams = new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                .setEmail()
                .createParams();
        mAuthService = AccountAuthManager.getService(this, mAuthParams);

        Task<AuthAccount> task = mAuthService.silentSignIn();
        task.addOnSuccessListener(authAccount -> {
            onSignInSuccess(authAccount);
        });

        task.addOnFailureListener(e -> {
           if (e instanceof ApiException) {
               ApiException apiException = (ApiException) e;
               Intent signInIntent = mAuthService.getSignInIntent();
               startActivityForResult(signInIntent, REQUEST_CODE_SIGN_IN);
           }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SIGN_IN) {
            Task<AuthAccount> authAccountTask = AccountAuthManager.parseAuthResultFromIntent(data);
            if (authAccountTask.isSuccessful()) {
                AuthAccount authAccount = authAccountTask.getResult();
                onSignInSuccess(authAccount);
                Log.i(TAG, "onActivitResult of sigInInIntent, request code: " + REQUEST_CODE_SIGN_IN);
            } else {
                Log.e(TAG, "sign in failed : " +((ApiException)authAccountTask.getException()).getStatusCode());
            }
        }
    }

    private void onSignInSuccess(AuthAccount account) {
        Log.i(TAG, "display name:" + account.getDisplayName());
        Log.i(TAG, "photo uri string:" + account.getAvatarUriString());
        Log.i(TAG, "photo uri:" + account.getAvatarUri());
        Log.i(TAG, "email:" + account.getEmail());
        Log.i(TAG, "openid:" + account.getOpenId());
        Log.i(TAG, "unionid:" + account.getUnionId());
        Toast.makeText(this, "Welcome, " + account.getDisplayName(), Toast.LENGTH_SHORT).show();
    }
}