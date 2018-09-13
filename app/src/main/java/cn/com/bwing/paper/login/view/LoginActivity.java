package cn.com.bwing.paper.login.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import cn.com.bwing.paper.SingleFragmentActivity;

public class LoginActivity extends SingleFragmentActivity {


    @Override
    protected Fragment crateFragment() {
        return new LoginFragment();
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, LoginActivity.class);

        return intent;
    }


}
