package com.smart.cryptolivedata.screens;



import com.smart.cryptolivedata.recview.CoinModel;

import java.util.List;

public interface MainScreen {
    void updateData(List<CoinModel> data);
    void setError(String msg);
}
