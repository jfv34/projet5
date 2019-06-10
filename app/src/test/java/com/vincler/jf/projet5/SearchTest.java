package com.vincler.jf.projet5;

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
    public void When_dateBegin_is_empty_dateBegin_should_be_null(){
        SearchActivityPresenter presenter = new SearchActivityPresenter();
        presenter.setDateBeginFormatAPI("");
        Assert.assertNull(presenter.getDateBeginFormatAPI());
    }

}
