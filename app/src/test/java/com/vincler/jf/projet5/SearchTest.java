package com.vincler.jf.projet5;

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
    public void When_dateEnd_is_not_empty_dateBegin_is_valid() {
        SearchActivityPresenter presenter = new SearchActivityPresenter();
        presenter.setDateEndFormatAPI("01012019");
        Assert.assertNotNull(presenter.getDateEndFormatAPI());
    }

    @Test
    public void When_dateEnd_is_empty_dateBegin_should_be_null() {
        SearchActivityPresenter presenter = new SearchActivityPresenter();
        presenter.setDateEndFormatAPI("");
        Assert.assertNull(presenter.getDateEndFormatAPI());
    }

/*    @Test
    public void When_query_is_empty_error_code_is_3() {
        SearchActivityPresenter presenter = new SearchActivityPresenter();
        presenter.search("", true, false, true, false, true, false);
        Assert.assertEquals(3, presenter.getError());
    }*/

    @Test
    public void When_date_of_DatePiker_is_not_empty_date_API_is_valid() {
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

   /* @Test
    public void When_no_categories_checked_error_code_is_4() {
        SearchActivityPresenter presenter = new SearchActivityPresenter();
        presenter.search("test", false, false, false, false, false, false);
        Assert.assertEquals(4, presenter.getError());
    }

    @Test
    public void When_categories_selected_are_arts_and_travels_String_categories_is_correct() {
        SearchActivityPresenter presenter = new SearchActivityPresenter();
        presenter.search("test", true, false, false, false, false, true);
        Assert.assertEquals("news_desk:(\"arts\"\"travels\")", presenter.getCategories());
    }*/

    @Test
    public void When_date_of_DatePiker_is_empty_date_API_is_null() {
        SearchActivityPresenter presenter = new SearchActivityPresenter();
        presenter.dates(0, 0, 0, ArrowClicked.RIGHT);
        Assert.assertNull(presenter.getDateBeginFormatAPI());
    }

}