package com.example.quanlycsvc.ActivityInterface.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Chào mừng bạn đến trang quản lý");
    }

    public LiveData<String> getText() {
        return mText;
    }
}