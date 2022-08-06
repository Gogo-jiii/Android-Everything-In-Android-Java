package com.example.notification;

import static android.app.PendingIntent.FLAG_MUTABLE;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.notification.databinding.FragmentNotificationBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotificationFragment extends Fragment {

    FragmentNotificationBinding binding;
    private NavController navController;
    private ToolbarManager toolbarManager;
    private NotificationCompat.Builder builder = null;
    private final String CHANNEL_NAME = "CHANNEL_NAME";
    private final String CHANNEL_DESCRIPTION = "CHANNEL_DESCRIPTION";
    private final String CHANNEL_ID = "CHANNEL_ID";
    private NotificationManager notificationManager;
    private ExecutorService executorService;
    private Bitmap bitmap;

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotificationBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        executorService = Executors.newSingleThreadExecutor();

        createNotificationChannel();

        binding.btnDefaultNotification.setOnClickListener(view -> showDefaultNotification());

        binding.btnBigTextNotification.setOnClickListener(view -> showBigTextNotification());

        binding.btnBigPictureNotification.setOnClickListener(view -> showBigPictureNotification());

        binding.btnLargeIconNotification.setOnClickListener(view -> showLargeIconNotification());

        binding.btnPendingIntentNotification.setOnClickListener(view -> showPendingIntentNotification());

        return binding.getRoot();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                    android.app.NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESCRIPTION);
            notificationManager = (NotificationManager) requireContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.toolbar,
                true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showDefaultNotification() {
        builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_android_24);
        builder.setContentTitle("Title");
        builder.setContentText("Text");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(requireContext());
        managerCompat.notify(1, builder.build());
    }

    private void showBigTextNotification() {
        builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_android_24);
        builder.setContentTitle("Title");
        builder.setContentText("View more...");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText("Lorem Ipsum is simply dummy text of the printing and " +
                "typesetting industry. Lorem Ipsum has been the industry's standard dummy" +
                " text ever since the 1500s, when an unknown printer took a galley of " +
                "type and scrambled it to make a type specimen book. It has survived not " +
                "only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with " +
                "the release of Letraset sheets containing Lorem Ipsum passages, and more" +
                " recently with desktop publishing software like Aldus PageMaker " +
                "including versions of Lorem Ipsum."));

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(requireContext());
        managerCompat.notify(2, builder.build());
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void showBigPictureNotification() {
        builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_android_24);
        builder.setContentTitle("Title");
        builder.setContentText("Text");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        executorService.execute(() -> {
            bitmap = drawableToBitmap(getResources().getDrawable(R.drawable.ic_baseline_android_24));
            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(requireContext());
            managerCompat.notify(3, builder.build());
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void showLargeIconNotification() {
        builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_android_24);
        builder.setContentTitle("Title");
        builder.setContentText("Text");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        executorService.execute(() -> {
            bitmap = drawableToBitmap(getResources().getDrawable(R.drawable.ic_baseline_android_24));
            builder.setLargeIcon(bitmap);
            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null));
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(requireContext());
            managerCompat.notify(4, builder.build());
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    private void showPendingIntentNotification() {
        builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_android_24);
        builder.setContentTitle("Title");
        builder.setContentText("Text");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(getContext(), NotificationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(requireActivity(), 0, intent,  FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(requireContext());
        managerCompat.notify(5, builder.build());
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

}