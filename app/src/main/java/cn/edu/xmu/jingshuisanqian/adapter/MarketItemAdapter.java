package cn.edu.xmu.jingshuisanqian.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.edu.xmu.jingshuisanqian.R;
import cn.edu.xmu.jingshuisanqian.entity.Item;
import cn.edu.xmu.jingshuisanqian.ui.activity.MarketDetailActivity;

/**
 * Created by hd_chen on 2016/9/1.
 */
public class MarketItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Item> itemList;
    Context context;

    public MarketItemAdapter() {

    }

    public MarketItemAdapter(List<Item> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        return new MyViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.market_item, parent, false),
                new MyItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        int id = view.getId();
                        switch (id) {
                            case R.id.for_detail:
                                Intent intent = new Intent(context, MarketDetailActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("item", itemList.get(position));
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                        }
                    }
                });
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Item item = itemList.get(position);
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.item_description.setText(item.getDescription());
        myViewHolder.price_before.setText(item.getPriceBefore());
        myViewHolder.price_now.setText(item.getPriceNow());
        myViewHolder.price_before.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.item_image)
        ImageView item_image;
        @Bind(R.id.item_description)
        TextView item_description;
        @Bind(R.id.price_now)
        TextView price_now;
        @Bind(R.id.price_before)
        TextView price_before;
        @Bind(R.id.for_detail)
        BootstrapButton for_detail;

        private MyItemClickListener mListener;

        public MyViewHolder(View itemView, MyItemClickListener mListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mListener = mListener;
            itemView.setOnClickListener(this);
            for_detail.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onItemClick(view, getPosition());
        }
    }

    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

}

