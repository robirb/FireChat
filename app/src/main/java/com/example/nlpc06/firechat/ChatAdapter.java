package com.example.nlpc06.firechat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NL PC 06 on 1/15/2018.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {

    private Context context;
    private List<Message> messageList;
    private LayoutInflater inflater;

    public ChatAdapter(Context context) {
        this.context = context;
        messageList = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.chat,parent,false);
        ChatHolder holder = new ChatHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ChatHolder holder, int position) {
        Message message = messageList.get(position);
        holder.tvName.setText(message.getName());
        holder.tvText.setText(message.getText());

    }

    public void addMessage(Message message){
        messageList.add(message);
        int posi = messageList.indexOf(message);
        notifyItemInserted(posi);
    }

    public void removeMessage(Message message){

        int posi = getposition(message);

        if(posi!=-1){
            messageList.remove(posi);
            notifyItemRemoved(posi);
        }

    }

    private int getposition(Message message) {

        int postion = -1;

        for(Message x: messageList){
            if(message.getName().equals(x.getName()) && message.getText().equals(x.getText())){
                postion = messageList.indexOf(x);
                break;
            }
        }
        return postion;

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }



    public class ChatHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvText;

        public ChatHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvText = itemView.findViewById(R.id.text);
        }
    }
}
