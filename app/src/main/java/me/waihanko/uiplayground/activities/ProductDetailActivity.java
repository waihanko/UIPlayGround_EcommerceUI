package me.waihanko.uiplayground.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import me.waihanko.uiplayground.vo.Image;
import me.waihanko.uiplayground.R;
import me.waihanko.uiplayground.util.Tools;
import me.waihanko.uiplayground.adapters.AdapterImageSlider;

public class ProductDetailActivity extends AppCompatActivity {
    private int[] alphaColorList;
    private List<Image> items = new ArrayList<>();

    public static Intent getInstance(Context context) {
        Intent i = new Intent(context, ProductDetailActivity.class);
        return i;
    }

    private ViewPager viewPager;
    private LinearLayout layout_dots;
    private LinearLayout rlImageContent;
    private AdapterImageSlider adapterImageSlider;

    private static int[] array_image_product = {
            R.drawable.mango,
            R.drawable.kiwi,
            R.drawable.orange,
            R.drawable.strawberry,
            R.drawable.papaya,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeFullScreenUI();
        setContentView(R.layout.activity_product_detail);
        initComponent();
    }

    private void initComponent() {
        layout_dots = findViewById(R.id.layout_dots);
        viewPager = findViewById(R.id.pager);
        rlImageContent = findViewById(R.id.rl_imageContent);
        alphaColorList = new int[array_image_product.length];
        for (int i : array_image_product) {
            Image obj = new Image();
            obj.image = i;
            obj.imageDrw = getResources().getDrawable(obj.image);
            items.add(obj);
        }
        getDynamicAlphaColorList();
        adapterImageSlider = new AdapterImageSlider(this, new ArrayList<Image>(), alphaColorList);
        adapterImageSlider.setItems(items);
        viewPager.setAdapter(adapterImageSlider);

        // displaying selected image first
        viewPager.setCurrentItem(0);
        addBottomDots(layout_dots, adapterImageSlider.getCount(), 0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {

                if(pos < (adapterImageSlider.getCount() -1) && pos < (alphaColorList.length - 1)) {
                    ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();
                    rlImageContent.setBackgroundColor((Integer) mArgbEvaluator.evaluate(positionOffset, alphaColorList[pos], alphaColorList[pos + 1]));

                } else {
                    rlImageContent.setBackgroundColor(alphaColorList[alphaColorList.length - 1]);
                }

            }

            @Override
            public void onPageSelected(int pos) {
                addBottomDots(layout_dots, adapterImageSlider.getCount(), pos);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }

    private void getDynamicAlphaColorList() {
        for (int i = 0; i < items.size(); i++) {
            int itemNormalColor = Tools.getProductItemColor(this, items.get(i).image);
            int itemAlphaColor = Tools.getColorWithAlpha(itemNormalColor, 0.7f); //Reducing Color
            alphaColorList[i] = itemAlphaColor;
        }
    }

    private void addBottomDots(LinearLayout layout_dots, int size, int current) {
        ImageView[] dots = new ImageView[size];

        layout_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(ContextCompat.getColor(this, R.color.overlay_dark_10), PorterDuff.Mode.SRC_ATOP);
            layout_dots.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current].setColorFilter(ContextCompat.getColor(this, R.color.colorPrimaryLight), PorterDuff.Mode.SRC_ATOP);
        }
    }

    private void makeFullScreenUI() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }



}
