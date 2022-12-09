package com.example.innovationfactorytask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private TabLayout bottomTabLayout;

    private TabsAccessorAdapter tabsAccessorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager2) findViewById(R.id.main_tabs_pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        tabsAccessorAdapter = new TabsAccessorAdapter(fragmentManager, getLifecycle());
        viewPager.setAdapter(tabsAccessorAdapter);

        tabLayout = (TabLayout) findViewById(R.id.main_tabs);
        bottomTabLayout=(TabLayout)findViewById(R.id.main_tabs_bottom);
        setTabsTitles();
        setBottomTaps();

    }
    private void setTabsTitles() {


        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.flight));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.home_icone));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.bed));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.car));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.location));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }


    private void setBottomTaps(){


        bottomTabLayout.addTab(bottomTabLayout.newTab().setIcon(R.drawable.bottom_home));
        bottomTabLayout.addTab(bottomTabLayout.newTab().setIcon(R.drawable.search));
        bottomTabLayout.addTab(bottomTabLayout.newTab().setIcon(R.drawable.heart));
        bottomTabLayout.addTab(bottomTabLayout.newTab().setIcon(R.drawable.menu));


        bottomTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

              //  viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==2)
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}