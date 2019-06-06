package com.vincler.jf.projet5;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;


public class NotificationsActivity extends SearchActivity {


    boolean notifications = false;
    String categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        final Switch okNotifications = findViewById(R.id.activity_notifications_switch);
        okNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (okNotifications.isChecked()) {

                    String query = gettingQuery();

                    if (query.isEmpty()) {
                        toast(R.string.enterAtLeastOneKeyWord);
                        okNotifications.setChecked(false);
                        notifications = false;
                    } else {
                        categories = selectCategories();
                        notifications = true;
                        WorkManager.getInstance().enqueue(periodicWorkRequest);


                    }

                } else {
                    notifications = false;
                }
            }
        });
    }

    final PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(NotificationsWorker.class, 16, TimeUnit.MINUTES)
            .addTag("periodic_work")
            .build();
}
