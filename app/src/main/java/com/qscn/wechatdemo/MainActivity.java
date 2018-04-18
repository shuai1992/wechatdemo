package com.qscn.wechatdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${zhangshuai} on 2018/4/16.
 * 2751157603@qq.com
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.regist)
    TextView regist;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.receive_mag)
    TextView receiveMag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    @OnClick({R.id.regist, R.id.login, R.id.send, R.id.open_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.regist:
                registMethod();
                break;
            case R.id.login:
                loginMechod();
                break;
            case R.id.send:
                sendMechod();
                break;
            case R.id.open_message:
            startActivity(new Intent(this,ChatActivity.class));
                break;
        }
    }

    private void sendMechod() {
        EMMessage message = EMMessage.createTxtSendMessage("你好", "a123");
        //发送消息
        EMClient.getInstance().chatManager().sendMessage(message);
    }

    private void registMethod() {
        try {
            EMClient.getInstance().createAccount("a123", "123");
        } catch (HyphenateException e) {
            e.printStackTrace();
            Log.d("%s", "注册失败" + e.getMessage());
        }
    }

    private void loginMechod() {
        EMClient.getInstance().login("a123", "123", new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.d("main", "登录聊天服务器成功！");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.d("main", "登录聊天服务器失败！");
            }
        });
    }

    EMMessageListener msgListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(final List<EMMessage> messages) {
            //收到消息
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    receiveMag.setText(messages.toString());
                    Log.d("%s", "收到消息" + messages);
                }
            });

        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //收到透传消息
        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
            //收到已读回执
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
            //收到已送达回执
        }

        @Override
        public void onMessageRecalled(List<EMMessage> messages) {
            //消息被撤回
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
            //消息状态变动
        }
    };
}
