package com.vincler.jf.projet5.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class Listable {
    public abstract String getCover();

    public abstract Date getDate();

    public String getDateString() {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        return dateFormat.format(getDate());
    }


    public abstract String getTitle();

    public abstract String getCategory();

    public abstract String getSubcategory();

    public abstract String getUrl();

}

