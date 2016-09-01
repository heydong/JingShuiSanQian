package cn.edu.xmu.jingshuisanqian.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.edu.xmu.jingshuisanqian.R;
import cn.edu.xmu.jingshuisanqian.adapter.ProductItemAdapter;
import cn.edu.xmu.jingshuisanqian.entity.Product;
import cn.edu.xmu.jingshuisanqian.ui.activity.MainActivity;
import cn.edu.xmu.jingshuisanqian.utils.SimpleItemTouchHelperCallback;

/**
 * Created by hd_chen on 2016/8/31.
 */
public class HomeFragment extends Fragment {

    @Bind(R.id.product_list)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    PullRefreshLayout pullRefreshLayout;

    public static final int PRODUCT = 0;
    public static final int ART = 1;

    int type;

    public static HomeFragment newInstance(int type) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        ButterKnife.bind(this, view);
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                HomeFragment homeFragment = HomeFragment.newInstance(type);
                if (type == PRODUCT) {
                    ((MainActivity) getActivity()).changeTab0(homeFragment);
                } else if (type == ART) {
                    ((MainActivity) getActivity()).changeTab1(homeFragment);
                }
//                new GetProductTask().execute();
            }
        });
        pullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_RING);
        pullRefreshLayout.setColorSchemeColors(R.color.secondary_text, R.color.tagDark
                , R.color.primary, R.color.accent_translucent);
        new GetProductTask().execute();
        return view;
    }

    private class GetProductTask extends AsyncTask<Void, Void, Boolean> {

        List<Product> productList;

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                pullRefreshLayout.setRefreshing(false);
                ProductItemAdapter productItemAdapter = new ProductItemAdapter(productList, getActivity(), type);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(productItemAdapter);
                ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(productItemAdapter);
                ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
                mItemTouchHelper.attachToRecyclerView(recyclerView);
            }
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            productList = new ArrayList<>();
            Product product;
            if (type == PRODUCT) {
                product = new Product("茶品", "2016.8.31", "煮茶论道，快意人生");
            } else if (type == ART) {
                product = new Product("茶艺", "2016.8.31", "煮茶论道，快意人生");
            } else {
                product = new Product("茶品/茶艺", "2016.8.31", "煮茶论道，快意人生");
            }
            productList.clear();
            for (int i = 0; i < 7; i++) {
                productList.add(product);
            }
            return true;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
}
