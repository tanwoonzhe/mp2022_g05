package com.example.laibrary.ui.book;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BookViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BookViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is book fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
