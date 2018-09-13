package cn.com.bwing.paper.login.model;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginModelImp implements LoginModel {

    public LoginTask mLoginTask;
    private LoginStatusListener mLoginStatusListener;

    @Override
    public void getLoginStatus(String username, String password, LoginStatusListener loginListener) {
        mLoginStatusListener = loginListener;

        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            mLoginStatusListener.onFailure("请填写用户名或密码");
            return;
        }

        mLoginTask = new LoginTask();
        mLoginTask.execute(username, password);
    }

    private class LoginTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            try {
                String loginUrl = "http://qv4.bwing.com.cn/api/login";

                OkHttpClient httpClient = new OkHttpClient();

                FormBody formBody = new FormBody.Builder()
                        .add("username", strings[0])
                        .add("password", strings[1])
                        .add("domain", "q.bwing.club")
                        .build();

                Request request = new Request.Builder()
                        .url(loginUrl)
                        .post(formBody)
                        .build();

                Response response = httpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    String body = response.body().string();
                    JSONObject bodyObject = new JSONObject(body);
                    int code = bodyObject.getInt("code");
                    String msg = bodyObject.getString("msg");
                    if (code == 1) {
                        mLoginStatusListener.onSuccess();
                    } else {
                        mLoginStatusListener.onFailure(msg);

                    }

                } else {
                    mLoginStatusListener.onFailure("请联系技术人员");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

    }
}
