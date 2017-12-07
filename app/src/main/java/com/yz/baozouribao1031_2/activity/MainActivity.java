package com.yz.baozouribao1031_2.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.yz.baozouribao1031_2.R;
import com.yz.baozouribao1031_2.fragment.ChannelFragmentBackup;
import com.yz.baozouribao1031_2.fragment.HomeFragment;
import com.yz.baozouribao1031_2.fragment.RankFragment;
import com.yz.baozouribao1031_2.fragment.SearchFragment;
import com.yz.baozouribao1031_2.helper.FragmentHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView
        .OnNavigationItemSelectedListener {

    private Toolbar toolbar_main;
    private NavigationView navigationView_main;
    private DrawerLayout drawerLayout_main;
    private ImageView imageViewWelcome;

    private Context mContext = this;
    private String[] mArrTabTitlesHome = null;
    private List<Fragment> mList = new ArrayList<>();
    private SearchView searchViewSearch;
    private long mPressedTime = 0;//记录最近一次按返回键的时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        closeWelcome();

        initView();

        initToolbarAndDrawlayout();

        initData();
    }

    /**
     * 隐藏欢迎页面
     */
    private void closeWelcome() {
        imageViewWelcome = (ImageView) findViewById(R.id.imageView_welcome);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageViewWelcome.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    private void initView() {
        toolbar_main = (Toolbar) findViewById(R.id.toolbar_main);
        drawerLayout_main = (DrawerLayout) findViewById(R.id.drawerLayout_main);
        navigationView_main = (NavigationView) findViewById(R.id.navigationView_main);
        searchViewSearch = (SearchView) findViewById(R.id.searchView_search);
    }


    private void initData() {
        //加载4个大Fragment
        HomeFragment homeFragment = new HomeFragment();
        mList.add(homeFragment);
        RankFragment rankFragment = new RankFragment();
        mList.add(rankFragment);
        ChannelFragmentBackup channelFragment = new ChannelFragmentBackup();
        mList.add(channelFragment);
        SearchFragment searchFragment = new SearchFragment();
        mList.add(searchFragment);
        //默认显示第一个Fragment
        FragmentHelper.switchFragment(getSupportFragmentManager(), mList, 0, R.id
                .frameLayout_Fragment, 0, 0);
    }


    /**
     * 抽屉初始化
     */
    private void initToolbarAndDrawlayout() {
        setSupportActionBar(toolbar_main);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout_main,
                toolbar_main, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout_main.setDrawerListener(toggle);
        toggle.syncState();

        navigationView_main.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        //重写返回键，先收起抽屉
        if (drawerLayout_main.isDrawerOpen(GravityCompat.START)) {
            drawerLayout_main.closeDrawer(GravityCompat.START);
        } else {
            //双击返回键退出程序
//            long currentTime = System.currentTimeMillis();//获取第一次按键时间
//            if ((currentTime - mPressedTime) > 2000) {//比较两次按键时间差
//                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
//                mPressedTime = currentTime;
//            } else {
//                //退出程序
//                this.finish();
//                System.exit(0);
//            }
            //双击返回键退出程序
            long currentTime = System.currentTimeMillis();
            if((currentTime - mPressedTime) > 2000){
                Toast.makeText(mContext, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mPressedTime = currentTime;
            }else {
                //退出程序
                finish();
                System.exit(0);
            }

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_home:
                //首页
                searchViewSearch.setVisibility(View.GONE);
                toolbar_main.setTitle("暴走日报");
                FragmentHelper.switchFragment(getSupportFragmentManager(), mList, 0, R.id
                        .frameLayout_Fragment, 0, 0);
                break;
            case R.id.nav_rank:
                //排行榜
                searchViewSearch.setVisibility(View.GONE);
                toolbar_main.setTitle("排行榜");
                FragmentHelper.switchFragment(getSupportFragmentManager(), mList, 1, R.id
                        .frameLayout_Fragment, 0, 0);
                break;
            case R.id.nav_column:
                //频道
                searchViewSearch.setVisibility(View.GONE);
                toolbar_main.setTitle("频道");
                FragmentHelper.switchFragment(getSupportFragmentManager(), mList, 2, R.id
                        .frameLayout_Fragment, 0, 0);
                break;
            case R.id.nav_search:
                //搜索
                searchViewSearch.setVisibility(View.VISIBLE);
                searchViewSearch.setQueryHint("请输入关键字");
                searchViewSearch.setIconified(false);
                toolbar_main.setTitle("");
                FragmentHelper.switchFragment(getSupportFragmentManager(), mList, 3, R.id
                        .frameLayout_Fragment, 0, 0);
                break;
        }
        //关闭抽屉
        drawerLayout_main.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ButterKnife.unbind(this);
    }
}
