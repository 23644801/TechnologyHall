package com.mac.th;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mac.th.entity.Item;
import com.mac.th.tool.ItemImageResTool;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity {

    Item item = null;
    @BindView(R.id.back_Tv)
    TextView backTv;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.description_zh_Tv)
    TextView descriptionZhTv;
    @BindView(R.id.operation_zh_Tv)
    TextView operationZhTv;
    @BindView(R.id.description_en_Tv)
    TextView descriptionEnTv;
    @BindView(R.id.operation_en_Tv)
    TextView operationEnTv;
    int floor = -1;
    @BindView(R.id.title_Tv)
    TextView titleTv;
    @BindView(R.id.myScrollView)
    ScrollView myScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        floor = getIntent().getExtras().getInt("floor");
        item = getIntent().getExtras().getParcelable("item");
        initView();
    }

    private void initView() {
        if (item != null) {
            String explanation_en = item.getExplanation_en();
            String explanation_zh = item.getExplanation_zh();
            String instruction_en = item.getInstruction_en();
            String instruction_zh = item.getInstruction_zh();
            descriptionZhTv.setText(explanation_zh);
            descriptionEnTv.setText(explanation_en);
            operationZhTv.setText(instruction_zh);
            operationEnTv.setText(instruction_en);
            titleTv.setText(item.getName());
            List<String> images = ItemImageResTool.getImages(this, floor, item.getId());
            initBannerData(images);
        }
    }


    void initBannerData(List<String> images) {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path.toString()).into(imageView);
            }
        });

        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(Arrays.asList(titles));
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @OnClick(R.id.back_Tv)
    public void onClick() {
        finish();
    }
}
