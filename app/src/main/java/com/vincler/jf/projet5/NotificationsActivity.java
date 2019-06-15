package com.vincler.jf.projet5;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;


public class NotificationsActivity extends SearchActivity {

    NotificationsPresenter presenter = new NotificationsPresenter();


    boolean notifications = false;
    String categories;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        final Switch okNotifications = findViewById(R.id.activity_notifications_switch);
        okNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (okNotifications.isChecked()) {

                    query = gettingQuery();

                    if (query.isEmpty()) {
                        toast(R.string.enterAtLeastOneKeyWord);
                        okNotifications.setChecked(false);
                        notifications = false;
                    } else {
                        //categories = selectCategories();
                        notifications = true;
                        final CheckBox arts_check = findViewById(R.id.activity_search_checkbox_1);
                        final CheckBox business_check = findViewById(R.id.activity_search_checkbox_2);
                        final CheckBox entrepreneurs_check = findViewById(R.id.activity_search_checkbox_3);
                        final CheckBox politics_check = findViewById(R.id.activity_search_checkbox_4);
                        final CheckBox sports_check = findViewById(R.id.activity_search_checkbox_5);
                        final CheckBox travels_check = findViewById(R.id.activity_search_checkbox_6);

                        presenter.selectCategories(arts_check, business_check, entrepreneurs_check,
                                politics_check, sports_check, travels_check);
                        presenter.data(query, categories);

                        WorkManager.getInstance().cancelAllWork();
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
