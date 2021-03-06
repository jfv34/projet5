package com.vincler.jf.projet5;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NotificationsWorker extends Worker {

    NotificationsActivityPresenter presenter = new NotificationsActivityPresenter();


    public NotificationsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        presenter.retrofit();
        int numberOfArticles = presenter.getNumberOfArticles();
        String message = "";

        if (numberOfArticles == 1) {
            message = String.valueOf((R.string.notificationOneArticle));
        }
        if (numberOfArticles > 1) {
            message = String.valueOf(R.string.notificationManyArticles1) + numberOfArticles +
                    (R.string.notificationsManyArticles2);
        }
        if (numberOfArticles > 0) {
            sendNotification("Votre recherche", message);
        }

        return Result.success();
    }


    public void sendNotification(String title, String message) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        //If on Oreo then notification required a notification channel.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default", "Default", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "default")
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher);

        notificationManager.notify(1, notification.build());
    }
}
