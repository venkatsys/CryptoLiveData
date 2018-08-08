package com.smart.cryptolivedata.recview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smart.cryptolivedata.R;


import java.util.ArrayList;
import java.util.List;

public class MyCryptoAdapter extends RecyclerView.Adapter<MyCryptoAdapter.CoinViewHolder> {
    List<CoinModel> mItems = new ArrayList<>();
    public final String STR_TEMPLATE_NAME = "%s\t\t\t\t\t\t%s";
    public final String STR_TEMPLATE_PRICE = "%s$\t\t\t\t\t\t24H Volume:\t\t\t%s$";

    @Override
    public CoinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Log.d(TAG, "onBindViewHolder() called with: holder = " + "onCreateViewHolder");
        return new CoinViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CoinViewHolder holder, int position) {
        final CoinModel model = mItems.get(position);
        //Log.d(TAG, "onBindViewHolder() called with: holder = " + model.name);
        holder.tvNameAndSymbol.setText(String.format(STR_TEMPLATE_NAME, model.name, model.symbol));
        holder.tvPriceAndVolume.setText(String.format(STR_TEMPLATE_PRICE, model.priceUsd, model.volume24H));
        //Glide.with(holder.ivIcon).load(model.imageUrl).into(holder.ivIcon);
    }

    @Override
    public int getItemCount() {
        //Log.d(TAG, "setItems()" + mItems.size());
        return mItems.size();
    }

    public void setItems(List<CoinModel> data) {
        //Log.d(TAG, "setItems()" + data.size());
        this.mItems.clear();
        this.mItems.addAll(data);
        notifyDataSetChanged();
    }

    class CoinViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameAndSymbol;
        TextView tvPriceAndVolume;
        ImageView ivIcon;

        public CoinViewHolder(View itemView) {
            super(itemView);
            tvNameAndSymbol = itemView.findViewById(R.id.tvNameAndSymbol);
            tvPriceAndVolume = itemView.findViewById(R.id.tvPriceAndVolume);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }
    }
}