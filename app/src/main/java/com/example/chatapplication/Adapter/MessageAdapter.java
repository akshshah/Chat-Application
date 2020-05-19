package com.example.chatapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatapplication.MessageActivity;
import com.example.chatapplication.Model.Chat;
import com.example.chatapplication.Model.User;
import com.example.chatapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private Context context;
    private ArrayList<Chat> chats;
    private String imageURL;

    FirebaseUser firebaseUser;

    public MessageAdapter(Context context, ArrayList<Chat> chats, String imageURL) {
        this.context = context;
        this.chats = chats;
        this.imageURL = imageURL;
    }

    public MessageAdapter() {
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_RIGHT){
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_right, parent,false);
            ViewHolder holder = new MessageAdapter.ViewHolder(view);
            return holder;
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_left, parent,false);
            ViewHolder holder = new MessageAdapter.ViewHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {

        Chat chat = chats.get(position);

        holder.showMessage.setText(chat.getMessage());

        if(imageURL.equals("default")){
            holder.profilePic.setImageResource(R.mipmap.ic_launcher);
        }else {
            Glide.with(context)
                    .load(imageURL)
                    .into(holder.profilePic);
        }

        if(position == chats.size()-1){
            if(chat.isIsseen()){
                holder.txt_seen.setVisibility(View.VISIBLE);
                holder.txt_delivered.setVisibility(View.GONE);
            }else{
                holder.txt_seen.setVisibility(View.GONE);
                holder.txt_delivered.setVisibility(View.VISIBLE);
            }
        }else {
            holder.txt_delivered.setVisibility(View.GONE);
            holder.txt_seen.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView showMessage;
        private ImageView profilePic;
        private TextView txt_seen;
        private TextView txt_delivered;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            showMessage = itemView.findViewById(R.id.showMessage);
            profilePic = itemView.findViewById(R.id.profilePic);
            txt_seen = itemView.findViewById(R.id.txt_seen);
            txt_delivered = itemView.findViewById(R.id.txt_delivered);
        }
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser  = FirebaseAuth.getInstance().getCurrentUser();
        if(chats.get(position).getSender().equals(firebaseUser.getUid())){
            return MSG_TYPE_RIGHT;
        }else {
            return MSG_TYPE_LEFT;
        }
    }
}

