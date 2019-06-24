package com.vincler.jf.projet5;

import android.widget.CheckBox;

import com.vincler.jf.projet5.models.ArrowClicked;

import org.junit.Assert;
import org.junit.Test;

public class SearchTest {

    @Test
    public void When_dateBegin_is_not_empty_dateBegin_is_valid() {
        SearchActivityPresenter presenter = new SearchActivityPresenter();
        presenter.setDateBeginFormatAPI("01012019");
        Assert.assertNotNull(presenter.getDateBeginFormatAPI());
    }

    @Test
    public void When_dateBegin_is_empty_dateBegin_should_be_null() {
        SearchActivityPresenter presenter = new SearchActivityPresenter();
        presenter.setDateBeginFormatAPI("");
        Assert.assertNull(presenter.getDateBeginFormatAPI());
    }

    @Test
    public void When_query_is_empty_search_return_error() {
        SearchActivityPresenter presenter = new SearchActivityPresenter();
        CheckBox checkBox;
        Assert.assertEquals(3, presenter.getError());
    }

    @Test
    public void When_date_of_DatePiker_is_not_empty_datedisplay_is_valid() {
        SearchActivityPresenter presenter = new SearchActivityPresenter();

        presenter.dates(2019, 8, 3, ArrowClicked.LEFT);
        Assert.assertEquals("20190903", presenter.getDateBeginFormatAPI());
    }

    @Test
    public void When_date_end_is_superior_at_date_end_error_code_is_1() {
        SearchActivityPresenter presenter = new SearchActivityPresenter();
        presenter.setDateBeginFormatAPI("20190101");
        presenter.setDateEndFormatAPI("20010101");
        presenter.verifyDates();
        Assert.assertEquals(1, presenter.verifyDates());
    }

    @Test
    public void When_date_begin_is_superior_at_date_today_error_code_is_2() {
        SearchActivityPresenter presenter = new SearchActivityPresenter();
        presenter.setDateBeginFormatAPI("20990101");
        presenter.verifyDates();
        Assert.assertEquals(2, presenter.verifyDates());
    }

    @Test
    public void When_date_end_is_superior_at_date_today_error_code_is_2() {
        SearchActivityPresenter presenter = new SearchActivityPresenter();
        presenter.setDateEndFormatAPI("20990101");
        presenter.verifyDates();
        Assert.assertEquals(2, presenter.verifyDates());
    }


}
