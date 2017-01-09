package cn.richinfo.customsharepreferences;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import cn.richinfo.customsharepreference.CustomSharePreferences;
import cn.richinfo.customsharepreference.SharePreferencesManager;
import cn.richinfo.customsharepreference.utils.GrantPermissionUtil;


public class MainActivity extends AppCompatActivity {
    private SharePreferencesManager sharePreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharePreferencesManager = SharePreferencesManager.getSharePreferencesManager();
        sharePreferencesManager.init(this, "AutoMailSDK");
        if (GrantPermissionUtil.checkPermissionStateAndRequest(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, 200)) {
            getValue();
        }
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                insertValue();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.e("info", "onRequestPermissionsResult()");
        switch (requestCode) {
            case 200: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getValue();
                } else {
                    Log.e("info","WRITE_EXTERNAL_STORAGE permission denied");
                }
            }
        }
    }

    private void getValue() {
        CustomSharePreferences sharePreferences = sharePreferencesManager.getSharedPreferences("automail", CustomSharePreferences.MODE_MULTI_PROCESS );
        Log.e("info", "key1 value is " + sharePreferences.getString("key1", "") + "\n" +
                "key2 value is " + sharePreferences.getInt("key2", 0) + "\n" +
                "key3 value is " + sharePreferences.getBoolean("key3", true) + "\n" +
                "key4 value is " + sharePreferences.getLong("key4", 0));
        Toast.makeText(this, "key1 value is " + sharePreferences.getString("key1", "") + "\n" +
                "key2 value is " + sharePreferences.getInt("key2", 0) + "\n" +
                "key3 value is " + sharePreferences.getBoolean("key3", true) + "\n" +
                "key4 value is " + sharePreferences.getLong("key4", 0), Toast.LENGTH_SHORT).show();
    }

    private void insertValue() {
        CustomSharePreferences settings = sharePreferencesManager.getSharedPreferences("automail", CustomSharePreferences.MODE_MULTI_PROCESS);
        CustomSharePreferences.Editor editor = settings.edit();
        editor.putString("key1", "value1");
        editor.putInt("key2", 1);
        editor.putBoolean("key3", false);
        editor.putLong("key4", 1);
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
