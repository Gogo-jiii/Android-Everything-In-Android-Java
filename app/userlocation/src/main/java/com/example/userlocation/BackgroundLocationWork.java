package com.example.userlocation;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.work.ForegroundInfo;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class BackgroundLocationWork extends Worker {

    private NotificationManager notificationManager;
    private Context context;
    String progress = "Starting work...";
    int NOTIFICATION_ID = 1;
    private LocationManager locationManager;
    private IntentFilter localBroadcastIntentFilter;

    public BackgroundLocationWork(@NonNull @org.jetbrains.annotations.NotNull Context context,
                                  @NonNull @org.jetbrains.annotations.NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
        notificationManager = (NotificationManager)
                context.getSystemService(NOTIFICATION_SERVICE);
        locationManager = LocationManager.getInstance(context);

        localBroadcastIntentFilter = new IntentFilter();
        localBroadcastIntentFilter.addAction("background_location");
        LocalBroadcastManager.getInstance(context).registerReceiver(backgroundLocationBroadCastReceiver,
                localBroadcastIntentFilter);
    }


    @NonNull @org.jetbrains.annotations.NotNull @Override public Result doWork() {
        setForegroundAsync(showNotification(progress));
        while (true) {
            locationManager.startLocationUpdates();

            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @NonNull
    private ForegroundInfo showNotification(String progress) {
        return new ForegroundInfo(NOTIFICATION_ID, createNotification(progress));
    }

    private Notification createNotification(String progress) {
        String CHANNEL_ID = "100";
        String title = "Foreground Work";
        String cancel = "Cancel";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(
                    new NotificationChannel(CHANNEL_ID, title,
                            NotificationManager.IMPORTANCE_HIGH));
        }

        return new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setTicker(title)
                .setContentText(progress)
                .setSmallIcon(R.drawable.ic_baseline_android_24)
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .build();
    }

    private void updateNotification(String progress) {
        Notification notification = createNotification(progress);
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    BroadcastReceiver backgroundLocationBroadCastReceiver = new BroadcastReceiver() {
        @Override public void onReceive(Context context, Intent intent) {
            Log.d("TAG","Broadcasted");
            progress = intent.getStringExtra("location");
            updateNotification(progress);
        }
    };

    @Override public void onStopped() {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(backgroundLocationBroadCastReceiver);
        super.onStopped();
    }
}