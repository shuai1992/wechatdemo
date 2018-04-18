package com.qscn.wechatdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;

/**
 * Created by ${zhangshuai} on 2018/4/17.
 * 2751157603@qq.com
 */
public class MyFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper {
    @Override
    protected void setUpView() {
        super.setUpView();
        titleBar.setTitle("看看");
    }

    @Override
    public void onSetMessageAttributes(EMMessage message) {

    }

    @Override
    public void onEnterToChatDetails() {

    }

    @Override
    public void onAvatarClick(String username) {

    }

    @Override
    public void onAvatarLongClick(String username) {

    }

    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        return false;
    }

    @Override
    public void onMessageBubbleLongClick(EMMessage message) {

    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        return false;
    }

    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return null;
    }
}
