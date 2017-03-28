package com.mac.th;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.mac.th.cons.AppData;
import com.mac.th.tool.XmlParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends AppCompatActivity {
    private final String TAG = "GuideActivity";
    @BindView(R.id.enter_Iv)
    ImageView enterIv;
    @BindView(R.id.map_Iv)
    ImageView mapIv;
    private KProgressHUD mProgress;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG, "解析完毕:" + msg.what);
            if (mProgress.isShowing()) {
                mProgress.dismiss();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initAppData();
    }

    private void initAppData() {
        if (mProgress != null && mProgress.isShowing())
            return;
        if (mProgress == null) {
            mProgress = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Loading……")
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f);
            mProgress.setCancellable(true);
        }
        mProgress.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i(TAG, "Thread start");
                    InputStream is = getAssets().open("appdata.xml");
                    Log.i(TAG, "read file appdata.xml");
                    AppData.setData(XmlParser.parse2Item(is));
                    is.close();
                    handler.sendEmptyMessage(0);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @OnClick({R.id.enter_Iv, R.id.map_Iv})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.enter_Iv:
                intent.setClass(this, MainActivity.class);
                break;
            case R.id.map_Iv:
                intent.setClass(this, MapActivity.class);
                break;
        }
        startActivity(intent);
    }
}
