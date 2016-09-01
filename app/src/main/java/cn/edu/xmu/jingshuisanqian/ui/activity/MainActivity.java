package cn.edu.xmu.jingshuisanqian.ui.activity;

import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.edu.xmu.jingshuisanqian.R;
import cn.edu.xmu.jingshuisanqian.ui.fragment.HomeFragment;
import cn.edu.xmu.jingshuisanqian.ui.fragment.MarketCategoryFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    HomeFragment productFragment;
    HomeFragment artFragment;
    MarketCategoryFragment marketCategoryFragment;
    FragmentTransaction ft;
    boolean shouldFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);
        shouldFinish = false;
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.shadow_bottom, "茶品").setActiveColor(R.color.primary))
                .addItem(new BottomNavigationItem(R.drawable.shadow_bottom, "茶艺").setActiveColor(R.color.primary))
                .addItem(new BottomNavigationItem(R.drawable.shadow_bottom, "商城").setActiveColor(R.color.primary))
                .addItem(new BottomNavigationItem(R.drawable.shadow_bottom, "个人").setActiveColor(R.color.primary))
                .setFirstSelectedPosition(0)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);
        productFragment = HomeFragment.newInstance(HomeFragment.PRODUCT);
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_fragment, productFragment).commit();
//        ft.add(R.id.content_fragment,productFragment);
//        ft.show(productFragment).commit();
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                changeTab0();
                break;
            case 1:
                changeTab1();
                break;
            case 2:
                changeTab2();
                break;
        }
    }

    public void changeTab0(HomeFragment... homeFragment) {
        ft = getSupportFragmentManager().beginTransaction();
        if (homeFragment.length != 0) {
            ft.remove(productFragment);
            productFragment = homeFragment[0];
            ft.add(R.id.content_fragment, productFragment);
        }
        if (productFragment == null) {
            productFragment = HomeFragment.newInstance(HomeFragment.PRODUCT);
            ft.add(R.id.content_fragment, productFragment);
        }
        if (artFragment != null) ft.hide(artFragment);
        if (marketCategoryFragment != null) ft.hide(marketCategoryFragment);
        ft.show(productFragment).commit();
    }

    public void changeTab1(HomeFragment... homeFragment) {
        ft = getSupportFragmentManager().beginTransaction();
        if (homeFragment.length != 0) {
            ft.remove(artFragment);
            artFragment = homeFragment[0];
            ft.add(R.id.content_fragment, artFragment);
        }
        if (artFragment == null) {
            artFragment = HomeFragment.newInstance(HomeFragment.ART);
            ft.add(R.id.content_fragment, artFragment);
        }
        if (productFragment != null) ft.hide(productFragment);
        if (marketCategoryFragment != null) ft.hide(marketCategoryFragment);
        ft.show(artFragment).commit();
    }

    public void changeTab2(MarketCategoryFragment... marketCategoryFragments) {
        ft = getSupportFragmentManager().beginTransaction();
        if (marketCategoryFragments.length != 0) {
            ft.remove(productFragment);
            marketCategoryFragment = marketCategoryFragments[0];
            ft.add(R.id.content_fragment, marketCategoryFragment);
        }
        if (marketCategoryFragment == null) {
            marketCategoryFragment = new MarketCategoryFragment();
            ft.add(R.id.content_fragment, marketCategoryFragment);
        }
        if (artFragment != null) ft.hide(artFragment);
        if (productFragment != null) ft.hide(productFragment);
        ft.show(marketCategoryFragment).commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.share_button) {
            Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(shouldFinish){
            finish();
        }else {
            shouldFinish = true;
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    shouldFinish = false;
                }
            }, 2000);
        }
    }
}
