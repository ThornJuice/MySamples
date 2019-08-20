package com.hzy.nofity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity";
    NotificationManager notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String id = "my_channel_01";
        String name="我是渠道名字";
        Intent mIntent=new Intent(this, MyBroadCast.class);
        mIntent.setAction("test111111111111111111");
        PendingIntent mPendingIntent= PendingIntent.getBroadcast(this, 0, mIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            Log.i(TAG, mChannel.toString());
            notificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(this,id)
                    .setChannelId(id)
                    .setContentTitle("新消息来了0")
                    .setContentText("新消息新消息0")
                    .setSmallIcon(R.mipmap.ic_launcher)
                   // .setContentIntent(mPendingIntent)
                    .setDeleteIntent(mPendingIntent)
                    .build();

        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,id)
                    .setContentTitle("新消息来了1")
                    .setContentText("新消息新消息1")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setOngoing(true)
                    .setContentIntent(mPendingIntent);
            notification = notificationBuilder.build();
        }

        notificationManager.notify(200, notification);
        
    }
}
