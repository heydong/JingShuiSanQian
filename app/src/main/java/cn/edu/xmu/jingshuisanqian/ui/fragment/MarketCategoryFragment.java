package cn.edu.xmu.jingshuisanqian.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.edu.xmu.jingshuisanqian.R;
import cn.edu.xmu.jingshuisanqian.ui.activity.MarketDetailActivity;
import cn.edu.xmu.jingshuisanqian.ui.activity.MarketShowActivity;

/**
 * Created by hd_chen on 2016/9/1.
 */
public class MarketCategoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.market_category, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.tianjian)
    public void tianjian() {
        Intent intent = new Intent(getActivity(), MarketShowActivity.class);
        intent.putExtra("title","净水三千商城");
        getActivity().startActivity(intent);
    }

    @OnClick(R.id.youxuan)
    public void youxuan() {
        Intent intent = new Intent(getActivity(), MarketShowActivity.class);
        intent.putExtra("title","净水三千商城");
        getActivity().startActivity(intent);
    }

    @OnClick(R.id.muximuxi)
    public void muximuxi() {
        Intent intent = new Intent(getActivity(), MarketShowActivity.class);
        intent.putExtra("title","净水三千商城");
        getActivity().startActivity(intent);
    }

}
