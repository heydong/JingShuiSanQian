package cn.edu.xmu.jingshuisanqian.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.edu.xmu.jingshuisanqian.R;
import cn.edu.xmu.jingshuisanqian.entity.Product;
import cn.edu.xmu.jingshuisanqian.ui.ProductDetailActivity;
import cn.edu.xmu.jingshuisanqian.utils.OnMoveAndSwipedListener;

/**
 * Created by hd_chen on 2016/8/31.
 */
public class ProductItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnMoveAndSwipedListener {
    List<Product> productList;
    Context context;


    public ProductItemAdapter() {

    }

    public ProductItemAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false),
                new MyItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(context, ProductDetailActivity.class);
                        intent.putExtra("title", productList.get(position).getTitle());
                        context.startActivity(intent);
                    }
                });
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Product product = productList.get(position);
        ((MyViewHolder) holder).tag.setText(product.getTag());
        ((MyViewHolder) holder).time.setText(product.getTime());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //交换mItems数据的位置
        Collections.swap(productList, fromPosition, toPosition);
        //交换RecyclerView列表中item的位置
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        //删除mItems数据
        productList.remove(position);
        //删除RecyclerView列表对应item
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.product_image)
        ImageView product_image;
        @Bind(R.id.tag_img)
        ImageView tag_img;
        @Bind(R.id.tag)
        TextView tag;
        @Bind(R.id.time)
        TextView time;

        private MyItemClickListener mListener;

        public MyViewHolder(View itemView, MyItemClickListener mListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mListener = mListener;
            itemView.setOnClickListener(this);
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
