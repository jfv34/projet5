package com.vincler.jf.projet5;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


public class NotificationsActivity extends SearchActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    NotificationsActivityPresenter presenter = new NotificationsActivityPresenter();
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String KEY_PREF_NOTIFICATIONS = "key_pref_notifications";
        sharedPreferences = getApplicationContext().getSharedPreferences(KEY_PREF_NOTIFICATIONS, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        setContentView(R.layout.activity_notifications);
        configureToolbar("Notifications");

        final CheckBox arts_check = findViewById(R.id.activity_search_checkbox_1);
        final CheckBox business_check = findViewById(R.id.activity_search_checkbox_2);
        final CheckBox entrepreneurs_check = findViewById(R.id.activity_search_checkbox_3);
        final CheckBox politics_check = findViewById(R.id.activity_search_checkbox_4);
        final CheckBox sports_check = findViewById(R.id.activity_search_checkbox_5);
        final CheckBox travels_check = findViewById(R.id.activity_search_checkbox_6);

        String loadQuery = sharedPreferences.getString("query", "");
        boolean loadCheckedArts = sharedPreferences.getBoolean("checked1", false);
        boolean loadCheckedBusiness = sharedPreferences.getBoolean("checked2", false);
        boolean loadCheckedEntrepreneurs = sharedPreferences.getBoolean("checked3", false);
        boolean loadCheckedPolitics = sharedPreferences.getBoolean("checked4", false);
        boolean loadCheckedSports = sharedPreferences.getBoolean("checked5", false);
        boolean loadCheckedTravels = sharedPreferences.getBoolean("checked6", false);

        EditText editText = findViewById(R.id.activity_search_query);

        editText.setText(loadQuery);
        arts_check.setChecked(loadCheckedArts);
        business_check.setChecked(loadCheckedBusiness);
        entrepreneurs_check.setChecked(loadCheckedEntrepreneurs);
        politics_check.setChecked(loadCheckedPolitics);
        sports_check.setChecked(loadCheckedSports);
        travels_check.setChecked(loadCheckedTravels);

        final SwitchCompat okNotifications = findViewById(R.id.activity_notifications_switch);
        if (loadQuery != null && !loadQuery.isEmpty()) {
            okNotifications.setChecked(true);
        }
        okNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (okNotifications.isChecked()) {


                    query = gettingQuery();

                    presenter.okNotificationsChecked(query, arts_check.isChecked(),
                            business_check.isChecked(), entrepreneurs_check.isChecked(), politics_check.isChecked(),
                            sports_check.isChecked(), travels_check.isChecked());
                    presenter.configureDates();

                    byte error = presenter.getError();

                    if (presenter.getError() != 0) {
                        okNotifications.setChecked(false);
                        toast(error);
                    } else {
                        saveSearch(arts_check.isChecked(),
                                business_check.isChecked(), entrepreneurs_check.isChecked(), politics_check.isChecked(),
                                sports_check.isChecked(), travels_check.isChecked());
                        presenter.sendPeriodicsNotifications();
                        toast(6);
                    }
                } else {
                    presenter.stopNotifications();

                    sharedPreferences.edit().clear().apply();
                    toast(5);

                }
            }
        });
    }


    private void saveSearch(boolean checked1, boolean checked2, boolean checked3,
                            boolean checked4, boolean checked5, boolean checked6) {
        editor.putString("query", query);
        editor.putBoolean("checked1", checked1);
        editor.putBoolean("checked2", checked2);
        editor.putBoolean("checked3", checked3);
        editor.putBoolean("checked4", checked4);
        editor.putBoolean("checked5", checked5);
        editor.putBoolean("checked6", checked6);
        editor.commit();
    }


}

