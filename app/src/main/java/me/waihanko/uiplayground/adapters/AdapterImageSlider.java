package me.waihanko.uiplayground.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import me.waihanko.uiplayground.vo.Image;
import me.waihanko.uiplayground.R;
import me.waihanko.uiplayground.util.Tools;

public class AdapterImageSlider extends PagerAdapter {

    private Activity act;
    private List<Image> items;
    RelativeLayout mProductImageLayout;
    ImageView productImage;
    int[] alphaColorList;
    private OnItemClickListener onItemClickListener;

    private interface OnItemClickListener {
        void onItemClick(View view, Image obj);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    // constructor
    public AdapterImageSlider(Activity activity, List<Image> items, int[] alphaColorList) {
        this.act = activity;
        this.items = items;
        this.alphaColorList = alphaColorList;
    }


    @Override
    public int getCount() {
        return this.items.size();
    }

    public Image getItem(int pos) {
        return items.get(pos);
    }

    public void setItems(List<Image> items) {
        this.items = items;
        notifyDataSetChanged();

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Image imageList = items.get(position);
        LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_slider_image, container, false);
        productImage = v.findViewById(R.id.ivProductImage);
        mProductImageLayout = v.findViewById(R.id.rvProductImageLayout);

        //Set Image
        Tools.displayImageOriginal(act, productImage, imageList.image);
        container.addView(v);
        return v;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }

}