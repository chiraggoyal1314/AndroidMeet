package org.mediasoup.droid.ViewModels;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ChatRoomViewModelFactory implements ViewModelProvider.Factory {
    private Context context;

    public ChatRoomViewModelFactory(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ChatRoomViewModel(context);
    }
}
