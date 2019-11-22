package com.rapidzpay.mygooglepush;

import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import androidx.annotation.NonNull;

public class EMFCMMSGService extends FirebaseMessagingService {
    private static final String TAG = "EMFCMMSGService";


    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.i(TAG, "onNewToken: 有新token？"+s);
    }

    @Override
    public void onMessageSent(@NonNull String s) {
        super.onMessageSent(s);
        Log.i(TAG, "onNewToken: 发送了个啥？"+s);
    }

    public EMFCMMSGService() {
        super();
        Log.i(TAG, "onNewToken: 服务启动了？");
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //设置消息免打扰
        Log.i(TAG, remoteMessage.getData().toString()+"---onMessageReceived: 我们收到了啥？"+remoteMessage.getTo());
        try {
                Map<String, String> data = remoteMessage.getData();
                String message = data.get("alert");
                Log.v(TAG, "onMessageReceived: " + message);
                Log.v(TAG, "onMessageReceived: " + remoteMessage.getData());
                if (message !=null && message.contains("您有一条新消息")) {
//                    EaseUI.getInstance().getNotifier().setLocale("en");
//                    String notifyText = null;
//                    try {
//                        notifyText = String.format(EaseUI.getInstance().getNotifier().msg,
//                                EaseUI.getInstance().getNotifier().fromUsers.size()+1,
//                                EaseUI.getInstance().getNotifier().notificationNum+1);
//                    } catch (Exception e) {
//                        notifyText = String.format(EaseUI.getInstance().getNotifier().msg.trim().replaceAll(" ",""),
//                                EaseUI.getInstance().getNotifier().fromUsers.size()+1,
//                                EaseUI.getInstance().getNotifier().notificationNum+1);
//                        e.printStackTrace();
//                    }
//                    EaseUI.getInstance().getNotifier().notify(notifyText);
                } else {
                    String type = remoteMessage.getData().get("type");
                    if (TextUtils.equals(type,"-1")) {
                       // EaseUI.getInstance().getNotifier().notifyNoSound("");
                    }else{
                        String value = remoteMessage.getData().get("body");
                        if (!TextUtils.isEmpty(value)) {
                            Log.i(TAG, "onMessageReceived: 我们得到的value是？"+value);
                        } else {
                          //  EaseUI.getInstance().getNotifier().notify(message);
                        }
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
