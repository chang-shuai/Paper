package cn.com.bwing.paper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment_acitvity);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.single_fragment);
        if (fragment == null) {
            manager.beginTransaction()
                    .add(R.id.single_fragment, crateFragment())
                    .commit();
        }

    }

    protected abstract Fragment crateFragment();
}
