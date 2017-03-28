package com.mac.th;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoViewAttacher;

public class RouteActivity extends AppCompatActivity {

    @BindView(R.id.back_Tv)
    TextView backTv;
    @BindView(R.id.floor_Iv)
    ImageView floorIv;
    @BindView(R.id.title_Tv)
    TextView titleTv;
    int floor = 1;
    PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        ButterKnife.bind(this);
        floor = getIntent().getExtras().getInt("floor");
        mAttacher = new PhotoViewAttacher(floorIv);
        switchFloor(floor);
    }

    @OnClick({R.id.back_Tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_Tv:
                finish();
                break;
        }
    }

    private void switchFloor(int floor) {
        int resouseId = -1;
        String title="一楼地图";
        switch (floor) {
            case 1:
                resouseId = R.drawable.f1;
                title="一楼地图";
                titleTv.setText(title);
                break;
            case 2:
                title="二楼地图";
                resouseId = R.drawable.f2;
                titleTv.setText(title);
                break;
            case 3:
                title="三楼地图";
                resouseId = R.drawable.f3;
                titleTv.setText(title);
                break;
            case 4:
                title="四楼地图";
                titleTv.setText(title);
                resouseId = R.drawable.f4;
                break;
        }
        Glide.with(this).load(resouseId).dontAnimate().dontTransform().into(floorIv);
    }
}
