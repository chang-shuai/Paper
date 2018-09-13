package cn.com.bwing.paper.login.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import cn.com.bwing.paper.R;
import cn.com.bwing.paper.login.presenter.LoginPresenter;
import cn.com.bwing.paper.login.presenter.LoginPresenterImpl;

public class LoginFragment extends Fragment implements LoginView, View.OnClickListener {

    private Activity mActivity;
    private EditText mUsernameEdit;
    private EditText mPasswordEdit;
    private CheckBox mRecordPasswordCheckBox;
    private Button mLoginButton;
    private LoginPresenter mLoginPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        initView(view);
        bindEvent();
        return view;
    }

    private void initView(View view) {
        mActivity = getActivity();
        mUsernameEdit = view.findViewById(R.id.et_username);
        mPasswordEdit = view.findViewById(R.id.et_password);
        mRecordPasswordCheckBox = view.findViewById(R.id.checkbox_record_password);
        mLoginButton = view.findViewById(R.id.btn_login);
        mLoginPresenter = new LoginPresenterImpl(this);

    }

    private void bindEvent() {
        mLoginButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        mLoginPresenter.validateIdentity(mUsernameEdit.getText().toString(), mPasswordEdit.getText().toString());
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }
}
