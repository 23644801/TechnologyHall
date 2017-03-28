package com.mac.th;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapActivity extends Activity {

    @BindView(R.id.back_Tv)
    TextView backTv;
    @BindView(R.id.map_Iv)
    ImageView mapIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_Tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_Tv:
                finish();
                break;
        }
    }
}
