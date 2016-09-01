package cn.edu.xmu.jingshuisanqian.ui.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.edu.xmu.jingshuisanqian.R;
import cn.edu.xmu.jingshuisanqian.adapter.MarketItemAdapter;
import cn.edu.xmu.jingshuisanqian.entity.Item;

public class MarketShowActivity extends AppCompatActivity {

    @Bind(R.id.market_list)
    RecyclerView recyclerView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_show);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        setTitle(getIntent().getStringExtra("title"));
        new GetMarketItemsTask().execute();
    }

    private class GetMarketItemsTask extends AsyncTask<Void, Void, Boolean> {

        List<Item> itemList;

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                MarketItemAdapter productItemAdapter = new MarketItemAdapter(itemList, MarketShowActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(MarketShowActivity.this));
                recyclerView.setAdapter(productItemAdapter);
            }
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            itemList = new ArrayList<>();
            Item item = new Item("1", "茶道日式方形大号实木茶盘竹制配陶瓷托盘功夫茶具排水茶台茶海"
                    , "茶道日式方形大号实木茶盘竹制配陶瓷托盘功夫茶具排水茶台茶海"
                    , "￥666", "￥800");
            for (int i = 0; i < 15; i++) {
                itemList.add(item);
            }
            return true;
        }
    }
}
