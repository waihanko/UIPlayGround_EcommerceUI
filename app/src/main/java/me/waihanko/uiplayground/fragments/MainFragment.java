package me.waihanko.uiplayground.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.waihanko.uiplayground.MainActivity;
import me.waihanko.uiplayground.R;
import me.waihanko.uiplayground.Tools;
import me.waihanko.uiplayground.adapters.ProductAdapter;
import me.waihanko.uiplayground.vo.ProductItems;

public class MainFragment extends Fragment {
    private RecyclerView mItemList;
    private ProductAdapter mProductAdapter;
    private List<ProductItems> itemList = new ArrayList<>();
    private int[] alphaColorList;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mProductAdapter = new ProductAdapter(this.getContext(), itemList,alphaColorList);
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mItemList = v.findViewById(R.id.rvProducts);
        mItemList.setLayoutManager(mLayoutManager);
        mItemList.setItemAnimator(new DefaultItemAnimator());
        mItemList.setAdapter(mProductAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        itemList.add(new ProductItems("AA", "DescAA", "100",R.drawable.papaya));
        itemList.add(new ProductItems("BBCDD", "WQAD", "300",R.drawable.kiwi));
        itemList.add(new ProductItems("TWGE", "SAWW", "800",R.drawable.mango));
        itemList.add(new ProductItems("AA", "DescAA", "100",R.drawable.orange));
        itemList.add(new ProductItems("BBCDD", "WQAD", "300",R.drawable.strawberry));
        itemList.add(new ProductItems("TWGE", "SAWW", "800",R.drawable.papaya));
        alphaColorList = new int[itemList.size()];
        getDynamicAlphaColorList();

        super.onCreate(savedInstanceState);
    }

    private void getDynamicAlphaColorList() {
        for (int i = 0; i < itemList.size(); i++) {
            int extractImageColor = Tools.setProductItemBackground(getActivity(), itemList.get(i).getImage());
            int extractImageColorWithAlpha = Tools.getColorWithAlpha(extractImageColor, 0.7f);
            alphaColorList[i] = extractImageColorWithAlpha;
        }
    }
}
