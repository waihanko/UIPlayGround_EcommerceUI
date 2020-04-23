package me.waihanko.uiplayground.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import me.waihanko.uiplayground.R;
import me.waihanko.uiplayground.util.Tools;
import me.waihanko.uiplayground.adapters.SectionsPagerAdapter;
import me.waihanko.uiplayground.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {
    private ViewPager view_pager;
    private TabLayout tab_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
    }

    private void initComponent() {
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(view_pager);

        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        tab_layout.setupWithViewPager(view_pager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(MainFragment.newInstance(), "Fruits");
        adapter.addFragment(MainFragment.newInstance(), "Vegetables");
        adapter.addFragment(MainFragment.newInstance(), "Nuts & Seeds");
        adapter.addFragment(MainFragment.newInstance(), "Flowers");
        adapter.addFragment(MainFragment.newInstance(), "Milks");
        viewPager.setAdapter(adapter);
    }


}
