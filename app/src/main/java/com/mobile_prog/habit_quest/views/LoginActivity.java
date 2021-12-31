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
import com.mobile_prog.habit_quest.contexts.AuthContext;

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
            huaweiSignInBtn.setEnabled(false);
            Toast.makeText(this, "Processing your request, please wait...", Toast.LENGTH_SHORT).show();
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
            huaweiSignInBtn.setEnabled(true);
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
        AuthContext.set(account);

        Toast.makeText(this, "Welcome, " + AuthContext.getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}