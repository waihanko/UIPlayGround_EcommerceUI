package me.waihanko.uiplayground.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.waihanko.uiplayground.ProductDetailActivity;
import me.waihanko.uiplayground.R;
import me.waihanko.uiplayground.Tools;
import me.waihanko.uiplayground.vo.ProductItems;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private List<ProductItems> itemList = new ArrayList<>();
    private TextView name, description, price;
    private ImageView mProductImage;
    private Button mAddToCard;
    private Context context;
    private CardView mProductItem;
    private ConstraintLayout mBgCard;
    private int[] bgColorList;

    public ProductAdapter(Context c, List<ProductItems> list, int[] alphaColorList) {
        context = c;
        itemList = list;
        bgColorList = alphaColorList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @SuppressLint("CutPasteId")
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_productName);
            description = itemView.findViewById(R.id.tv_productName);
            price = itemView.findViewById(R.id.tv_productName);
            mAddToCard = itemView.findViewById(R.id.btnAddToCard);
            mProductItem = itemView.findViewById(R.id.cv_productItem);
            mProductImage = itemView.findViewById(R.id.iv_ProductImage);
            mBgCard = itemView.findViewById(R.id.rvBgCard);
        }
    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder holder, int position) {
        ProductItems item = itemList.get(position);
        name.setText(item.getName());
        description.setText(item.getDescription());
        price.setText(item.getPrice());
        Tools.displayImageOriginal(context, mProductImage, item.getImage());
        mBgCard.setBackgroundColor(bgColorList[position]);
        mAddToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_addtocard);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(true);
                dialog.show();
            }
        });

        mProductItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = ProductDetailActivity.getInstance(context);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }



}
