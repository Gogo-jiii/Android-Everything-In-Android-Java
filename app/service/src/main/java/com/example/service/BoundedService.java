package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BoundedService extends Service {

    private final IBinder binder = new LocalBinder();
    public int data;

    public BoundedService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class LocalBinder extends Binder {
        BoundedService getService() {
            computeData();
            return BoundedService.this;
        }
    }

    private void computeData() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            //task
            for (int i = 0; i < 100; i++) {
                data = i;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}