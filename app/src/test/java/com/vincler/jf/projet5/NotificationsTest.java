package com.vincler.jf.projet5;

import org.junit.Assert;
import org.junit.Test;

public class NotificationsTest {

    @Test
    public void When_no_categories_checked_error_code_is_4() {
        NotificationsActivityPresenter presenter = new NotificationsActivityPresenter();
        presenter.okNotificationsChecked("test",false, false, false, false, false,false);
        Assert.assertEquals(4, presenter.getError());
    }

    @Test
    public void When_categories_selected_are_arts_and_travels_String_categories_is_correct() {
        NotificationsActivityPresenter presenter = new NotificationsActivityPresenter();
        presenter.okNotificationsChecked("test", true, false, false, false, false, true);
        Assert.assertEquals("news_desk:(\"arts\"\"travels\")", presenter.getCategories());
    }

    @Test
    public void When_query_is_empty_error_code_is_3() {
        NotificationsActivityPresenter presenter = new NotificationsActivityPresenter();
        presenter.okNotificationsChecked("", true, false, true, false, true, false);
        Assert.assertEquals(3, presenter.getError());
    }



}
