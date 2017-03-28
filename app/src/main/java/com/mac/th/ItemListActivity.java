package com.mac.th;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
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

public class ItemListActivity extends AppCompatActivity {

    @BindView(R.id.back_Tv)
    TextView backTv;
    @BindView(R.id.gridView)
    GridView gridView;
    CommonAdapter<Item> commonAdapter;
    int floor = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);
        ButterKnife.bind(this);
        floor = getIntent().getExtras().getInt("floor");
        initData(floor);
    }

    List<Item> datas = new ArrayList<>();

    private void initData(final int floor) {
        datas.clear();
        List<Item> tem_Data = AppData.getItems(floor);
        if (tem_Data != null && tem_Data.size() > 0) {
            datas.addAll(tem_Data);
        }
        if (commonAdapter == null) {
            commonAdapter = new CommonAdapter<Item>(this, R.layout.exhibition_project_layout, datas) {
                @Override
                protected void convert(ViewHolder viewHolder, Item item, int position) {
                    String fileName = ItemImageResTool.getCoverImage(ItemListActivity.this, floor, item.getId());
                    viewHolder.setText(R.id.ep_name_Tv, item.getName());
                    if (fileName != null) {
                        Glide.with(ItemListActivity.this).load(fileName).dontAnimate().dontTransform().into((ImageView) viewHolder.getView(R.id.ep_Iv));
                    } else {
                        ((ImageView) viewHolder.getView(R.id.ep_Iv)).setImageBitmap(null);
                    }
                }
            };
            gridView.setAdapter(commonAdapter);
        }
        commonAdapter.notifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(ItemListActivity.this, DetailsActivity.class);
                Item item = AppData.getItem(floor, position);
                intent.putExtra("item", item);
                intent.putExtra("floor", floor);
                intent.putExtra("floor", floor);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.back_Tv)
    public void onClick() {
        finish();
    }
}
