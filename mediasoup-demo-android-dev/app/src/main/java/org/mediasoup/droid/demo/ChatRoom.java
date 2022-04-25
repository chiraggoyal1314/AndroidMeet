package org.mediasoup.droid.demo;

import static org.mediasoup.droid.demo.Application.body;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.mediasoup.droid.ViewModels.ChatRoomViewModel;
import org.mediasoup.droid.ViewModels.ChatRoomViewModelFactory;
import org.mediasoup.droid.demo.databinding.ChatRoomBinding;
import org.mediasoup.droid.model.meetingModels.ChatModel;
import org.mediasoup.droid.model.meetingModels.SingleChatModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ChatRoom extends AppCompatActivity
{
    private final List<SingleChatModel> recyclerDataArrayList = new ArrayList<>();
    private ChatAdapter chatAdapter;
    public static ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        org.mediasoup.droid.demo.databinding.ChatRoomBinding chatRoomBinding = ChatRoomBinding.inflate(getLayoutInflater());
        setContentView(chatRoomBinding.getRoot());
//        closeKeyboard();
//        progressBar = findViewById(R.id.idPBLoading);
        ChatRoomViewModel chatRoomViewModel = new ViewModelProvider(this, new ChatRoomViewModelFactory(getApplicationContext())).get(ChatRoomViewModel.class);
        chatRoomBinding.chatList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        chatRoomBinding.chatList.setHasFixedSize(true);


        chatAdapter = new ChatAdapter(recyclerDataArrayList, getApplicationContext());
        chatRoomBinding.chatList.setAdapter(chatAdapter);
        chatRoomViewModel.getAllChats(body.getMeetingId());
        chatRoomViewModel.getChatModelMLD().observe(this, new Observer<ChatModel>() {
            @Override
            public void onChanged(ChatModel chatModel) {
                if(chatModel!=null && chatModel.getChatList()!=null && chatModel.getChatList().size()>0){
                    chatModel.getChatList().removeIf(item -> item.getChatMessage().isEmpty());
                    chatAdapter.updateChat(chatModel.getChatList());
                }

            }
        });
    }

//    private void closeKeyboard()
//    {
//        // this will give us the view
//        // which is currently focus
//        // in this layout
//        View view = this.getCurrentFocus();
//
//        // if nothing is currently
//        // focus then this will protect
//        // the app from crash
//        if (view != null) {
//
//            // now assign the system
//            // service to InputMethodManager
//            InputMethodManager manager
//                    = (InputMethodManager)
//                    getSystemService(
//                            Context.INPUT_METHOD_SERVICE);
//            manager
//                    .hideSoftInputFromWindow(
//                            view.getWindowToken(), 0);
//        }
//    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),RoomActivity.class));
    }

    public static class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.RecyclerViewHolder> {
        // creating a variable for our array list and context.
        private List<SingleChatModel> chatModels;

        // creating a constructor class.
        public ChatAdapter(List<SingleChatModel> chatModels, Context mcontext) {
            this.chatModels = chatModels;
        }

        @SuppressLint("NotifyDataSetChanged")
        public void updateChat(List<SingleChatModel> chatModels ){
            this.chatModels = chatModels;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Inflate Layout
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_receive, parent, false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            SingleChatModel modal = chatModels.get(position);
//            holder.participantId.setText(modal.getParticipantId());
                holder.participantName.setText(modal.getParticipantName());
                holder.chatMessage.setText(modal.getChatMessage());
                holder.createTime.setText(modal.getCreateTime());
        }
        @Override
        public int getItemCount() {
            return chatModels.size();
        }

        public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
//            private final TextView participantId;
            private final TextView participantName;
            private final TextView chatMessage;
            private final TextView createTime;
            public RecyclerViewHolder(@NonNull View itemView) {
                super(itemView);
//                participantId = itemView.findViewById(R.id.participantId);
                participantName = itemView.findViewById(R.id.participantName);
                chatMessage = itemView.findViewById(R.id.chatMessage);
                createTime = itemView.findViewById(R.id.createTimeMessage);
            }
        }
    }

}