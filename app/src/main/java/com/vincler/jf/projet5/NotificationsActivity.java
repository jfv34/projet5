package com.vincler.jf.projet5;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.widget.CheckBox;
import android.widget.CompoundButton;


public class NotificationsActivity extends SearchActivity {

    NotificationsActivityPresenter presenter = new NotificationsActivityPresenter();
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        final SwitchCompat okNotifications = findViewById(R.id.activity_notifications_switch);
        okNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (okNotifications.isChecked()) {

                    final CheckBox arts_check = findViewById(R.id.activity_search_checkbox_1);
                    final CheckBox business_check = findViewById(R.id.activity_search_checkbox_2);
                    final CheckBox entrepreneurs_check = findViewById(R.id.activity_search_checkbox_3);
                    final CheckBox politics_check = findViewById(R.id.activity_search_checkbox_4);
                    final CheckBox sports_check = findViewById(R.id.activity_search_checkbox_5);
                    final CheckBox travels_check = findViewById(R.id.activity_search_checkbox_6);

                    query = gettingQuery();

                    presenter.okNotificationsChecked(query, arts_check.isChecked(),
                            business_check.isChecked(), entrepreneurs_check.isChecked(), politics_check.isChecked(),
                            sports_check.isChecked(), travels_check.isChecked());

                    byte error = presenter.getError();

                    if (presenter.getError() != 0) {
                        okNotifications.setChecked(false);
                        toast(error);
                    } else {
                        presenter.sendPeriodicsNotifications();
                        toast(6);
                    }
                } else {
                    presenter.stopNotifications();
                    toast(5);

                }
            }
        });
    }

}

