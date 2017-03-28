package com.mac.th;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mac.th.cons.AppData;
import com.mac.th.entity.Item;
import com.mac.th.tool.ItemImageResTool;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.back_Tv)
    TextView backTv;
    @BindView(R.id.floor_Iv)
    ImageView floorIv;
    @BindView(R.id.f4_ll)
    LinearLayout f4Ll;
    @BindView(R.id.f3_ll)
    LinearLayout f3Ll;
    @BindView(R.id.f2_ll)
    LinearLayout f2Ll;
    @BindView(R.id.f1_ll)
    LinearLayout f1Ll;
    @BindView(R.id.gridView)
    GridView gridView;
    int current_select_floor = 1;
    int last_select_floor = 1;
    @BindView(R.id.floor4_Iv)
    ImageView floor4Iv;
    @BindView(R.id.floor3_Iv)
    ImageView floor3Iv;
    @BindView(R.id.floor2_Iv)
    ImageView floor2Iv;
    @BindView(R.id.floor1_Iv)
    ImageView floor1Iv;
    @BindView(R.id.floor_ll)
    LinearLayout floorLl;
    CommonAdapter<Item> commonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.f1).dontAnimate().dontTransform().into(floorIv);
        initData(current_select_floor);
    }

    List<Item> datas = new ArrayList<>();

    private void initData(final int floor) {
        datas.clear();
        List<Item> tem_Data = AppData.getItems(floor, 7);
        Item more_Item = new Item();
        tem_Data.add(more_Item);
        if (tem_Data != null && tem_Data.size() > 0) {
            datas.addAll(tem_Data);
        }
        if (commonAdapter == null) {
            commonAdapter = new CommonAdapter<Item>(this, R.layout.exhibition_project_layout, datas) {
                @Override
                protected void convert(ViewHolder viewHolder, Item item, int position) {
                    if (position == 7) {
                        viewHolder.setText(R.id.ep_name_Tv, "查看更多");
                        Glide.with(MainActivity.this).load(R.drawable.more).dontAnimate().dontTransform().into((ImageView) viewHolder.getView(R.id.ep_Iv));
                    } else {
                        String fileName = ItemImageResTool.getCoverImage(MainActivity.this, floor, item.getId());
                        viewHolder.setText(R.id.ep_name_Tv, item.getName());
                        if (fileName != null) {
                            Glide.with(MainActivity.this).load(fileName).dontAnimate().dontTransform().into((ImageView) viewHolder.getView(R.id.ep_Iv));
                        } else {
                            ((ImageView) viewHolder.getView(R.id.ep_Iv)).setImageBitmap(null);
                        }
                    }
                }
            };
            gridView.setAdapter(commonAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent();
                    if (position == 7) {
                        intent.setClass(MainActivity.this, ItemListActivity.class);
                        intent.putExtra("floor", current_select_floor);
                    } else {
                        intent.setClass(MainActivity.this, DetailsActivity.class);
                        Item item = AppData.getItem(current_select_floor, position);
                        intent.putExtra("item", item);
                        intent.putExtra("floor", current_select_floor);
                    }
                    startActivity(intent);
                }
            });
        } else {
            commonAdapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.back_Tv, R.id.floor_Iv, R.id.f4_ll, R.id.f3_ll, R.id.f2_ll, R.id.f1_ll})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.back_Tv:
                finish();
                break;
            case R.id.floor_Iv:
                intent.setClass(MainActivity.this, RouteActivity.class);
                intent.putExtra("floor", current_select_floor);
                startActivity(intent);
                break;
            case R.id.f4_ll:
                switchFloor(4);
                break;
            case R.id.f3_ll:
                switchFloor(3);
                break;
            case R.id.f2_ll:
                switchFloor(2);
                break;
            case R.id.f1_ll:
                switchFloor(1);
                break;
        }
    }


    private void switchFloor(int floor) {
        if (current_select_floor == floor) {
            return;
        }
        int resouseId = -1;
        ImageView lastSelectFloorView = null;
        ImageView currentSelectFloorView = null;
        int lastSelectFloorResId = -1;
        int currentSelectFloorResId = -1;
        last_select_floor = current_select_floor;
        switch (last_select_floor) {
            case 1:
                lastSelectFloorView = floor1Iv;
                lastSelectFloorResId = R.drawable.f1_thumbnails;
                break;
            case 2:
                lastSelectFloorView = floor2Iv;
                lastSelectFloorResId = R.drawable.f2_thumbnails;
                break;
            case 3:
                lastSelectFloorView = floor3Iv;
                lastSelectFloorResId = R.drawable.f3_thumbnails;
                break;
            case 4:
                lastSelectFloorView = floor4Iv;
                lastSelectFloorResId = R.drawable.f4_thumbnails;
                break;
        }
        current_select_floor = floor;
        switch (floor) {
            case 1:
                resouseId = R.drawable.f1;
                currentSelectFloorView = floor1Iv;
                currentSelectFloorResId = R.drawable.f1_thumbnails_select;
                break;
            case 2:
                resouseId = R.drawable.f2;
                currentSelectFloorView = floor2Iv;
                currentSelectFloorResId = R.drawable.f2_thumbnails_select;
                break;
            case 3:
                resouseId = R.drawable.f3;
                currentSelectFloorView = floor3Iv;
                currentSelectFloorResId = R.drawable.f3_thumbnails_select;
                break;
            case 4:
                resouseId = R.drawable.f4;
                currentSelectFloorView = floor4Iv;
                currentSelectFloorResId = R.drawable.f4_thumbnails_select;
                break;
        }
        lastSelectFloorView.setImageResource(lastSelectFloorResId);
        currentSelectFloorView.setImageResource(currentSelectFloorResId);
//        Glide.with(this).load(lastSelectFloorResId).into(lastSelectFloorView);
//        Glide.with(this).load(currentSelectFloorResId).into(currentSelectFloorView);
        Glide.with(this).load(resouseId).fitCenter().dontAnimate().dontTransform().into(floorIv);
        initData(current_select_floor);
    }

}
