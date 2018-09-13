package cn.com.bwing.paper.login.model;

public interface LoginModel {


    void getLoginStatus(String username, String password, LoginStatusListener loginStatusListener);

    interface LoginStatusListener {
        void onSuccess();
        void onFailure(String msg);
    }
}
