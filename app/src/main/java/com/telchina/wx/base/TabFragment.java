package com.telchina.wx.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.telchina.wx.R;
import com.telchina.wx.chat.Msg;
import com.telchina.wx.chat.MsgAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * tab页显示的内容
 */
public class TabFragment extends Fragment implements View.OnClickListener {

    private String mTitle = "Default";
    private int mIndex = 0;

    public static final String TITLE = "title";
    public static final String INDEX = "index";

    private List<Msg> msgList = new ArrayList<>();
    private MsgAdapter msgAdapter;
    private ListView msgListView;
    private Button sendBtn;
    private EditText inputText;

    private String TAG = "TabFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //获取参数
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
            mIndex = getArguments().getInt(INDEX, 0);
        }

        if (mIndex == 0) {
            Log.d(TAG, "index 0");
            View view = inflater.inflate(R.layout.layout_chat, container, false);
            initMsgs();
            msgAdapter = new MsgAdapter(getActivity(), R.layout.chat_msg_item, msgList);
            msgListView = (ListView) view.findViewById(R.id.msg_list_view);
            msgListView.setAdapter(msgAdapter);
            sendBtn = (Button) view.findViewById(R.id.send_button);
            inputText = (EditText) view.findViewById(R.id.input_edit_text);
            sendBtn.setOnClickListener(this);

            return view;
        } else {
            //创建一个TextView
            TextView tv = new TextView(getActivity());
            tv.setTextSize(30);
            tv.setBackgroundColor(Color.LTGRAY);
            tv.setText(mTitle + "  序号:" + mIndex);
            tv.setGravity(Gravity.CENTER);
            tv.setHeight(60);

            return tv;
        }
    }

    private void initMsgs() {
        msgList.add(new Msg("Hello guy!!!", Msg.TYPE_RECEIVED));
        msgList.add(new Msg("你好！！", Msg.TYPE_SEND));
        msgList.add(new Msg("哈喽！", Msg.TYPE_RECEIVED));
    }

    @Override
    public void onClick(View v) {

        String content = inputText.getText().toString();
        if (!"".equals(content)) {
            Msg msg = new Msg(content, Msg.TYPE_SEND);
            msgList.add(msg);
            msgAdapter.notifyDataSetChanged();
            msgListView.setSelection(msgList.size());
            inputText.setText("");
        }

    }
}
