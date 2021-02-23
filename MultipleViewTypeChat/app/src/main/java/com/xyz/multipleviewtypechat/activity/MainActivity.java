package com.xyz.multipleviewtypechat.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xyz.multipleviewtypechat.adapter.ChatAdapter;
import com.xyz.multipleviewtypechat.R;
import com.xyz.multipleviewtypechat.model.BaseModel;
import com.xyz.multipleviewtypechat.model.ReceiverChat;
import com.xyz.multipleviewtypechat.model.SenderChat;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This activity handles the message building part which is sent by the user
 */
public class MainActivity extends AppCompatActivity {

    private List<BaseModel> baseModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private EditText mEtChatMessage;
    private EditText mEtSenderOrReceiver;
    private Button mBtnSend;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setRecyclerAdapter();
        buildChatMessageList();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        mEtChatMessage = findViewById(R.id.etChatMessage);
        mEtSenderOrReceiver = findViewById(R.id.etSenderOrReceiver);
        mBtnSend = findViewById(R.id.btnSend);
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildChatMessageList();
            }
        });
    }

    private void setRecyclerAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        chatAdapter = new ChatAdapter(baseModelList);
        recyclerView.setAdapter(chatAdapter);
    }

    /**
     * The user needs to enter the message in the edittext and there user has
     * to provide a value i.e 1 or 2, if its 1 then we consider it as a sender
     * and populate the message in the right side and if its 2 we consider it as
     * a receiver and populate the messages in the left side of the screen
     */
    private void buildChatMessageList() {
        // Sender
        if (mEtSenderOrReceiver.getText().toString().equals("1")) {
            baseModelList.add(new SenderChat(mEtChatMessage.getText().toString()));
        } else if (mEtSenderOrReceiver.getText().toString().equals("2")) {
            baseModelList.add(new ReceiverChat(mEtChatMessage.getText().toString(), getFormattedDate()));
        }
        //Notify after every message so that the recycler view updates
        chatAdapter.notifyDataSetChanged();
    }

    /**
     * This method gives the formatted time in the format HH:mm ( Example : 10:29)
     *
     * @return formatted time
     */
    public String getFormattedDate() {
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        Format hourMinFormat = new SimpleDateFormat("HH.mm");
        return hourMinFormat.format(date);
    }
}