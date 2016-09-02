package cn.edu.xmu.jingshuisanqian.ui.activity;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.image.SmartImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.edu.xmu.jingshuisanqian.R;
import cn.edu.xmu.jingshuisanqian.widget.ImageCycleView;
import cn.edu.xmu.jingshuisanqian.widget.ItemPopWindow;

public class MarketDetailActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.itemPhotos)
    ImageCycleView imageCycleView;
    @Bind(R.id.price_now)
    TextView price_now;
    @Bind(R.id.price_before)
    TextView price_before;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setTitle("商品详情");
        initPhotos();
        price_before.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        toolbar.getBackground().setAlpha(0);//toolbar透明度初始化为0
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.share_button:
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
                break;
//            case android.R.id.home:
//                onBackPressed();
//                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initPhotos() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int screenHeight = wm.getDefaultDisplay().getHeight();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, screenHeight / 4);
        imageCycleView.setLayoutParams(layoutParams);
        List<ImageCycleView.ImageInfo> imageUrls = new ArrayList<>();
        imageUrls.add(new ImageCycleView.ImageInfo(R.drawable.tea_image, "ad", ""));
        imageUrls.add(new ImageCycleView.ImageInfo(R.drawable.tea_image, "ad", ""));
        imageUrls.add(new ImageCycleView.ImageInfo(R.drawable.tea_image, "ad", ""));
        imageCycleView.setCycleDelayed(8000);
        imageCycleView.setTextAvailable(false);
        imageCycleView.loadData(imageUrls, new ImageCycleView.LoadImageCallBack() {
            @Override
            public ImageView loadAndDisplay(ImageCycleView.ImageInfo imageInfo) {
                SmartImageView smartImageView = new SmartImageView(MarketDetailActivity.this);
                Picasso.with(MarketDetailActivity.this).load(Integer.parseInt(imageInfo.image.toString())).into(smartImageView);
                return smartImageView;
            }
        });
    }

    @OnClick(R.id.chooseStandard)
    public void showPopFormBottom() {
        ItemPopWindow itemPopWindow = new ItemPopWindow(this, standardClickListener);
        //showAtLocation(View parent, int gravity, int x, int y)
        itemPopWindow.showAtLocation(findViewById(R.id.item_content), Gravity.CENTER, 0, 0);
    }

    @OnClick(R.id.cart)
    public void cart() {
        ItemPopWindow itemPopWindow = new ItemPopWindow(this, standardClickListener);
        //showAtLocation(View parent, int gravity, int x, int y)
        itemPopWindow.showAtLocation(findViewById(R.id.item_content), Gravity.CENTER, 0, 0);
    }

    private View.OnClickListener standardClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

            }
        }
    };

}
