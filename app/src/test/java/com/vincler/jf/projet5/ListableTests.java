package com.vincler.jf.projet5;

import com.vincler.jf.projet5.models.Article;
import com.vincler.jf.projet5.models.ArticleMedia;
import com.vincler.jf.projet5.models.Listable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ListableTests {

    @Test
    public void dateString_must_be_in_validformat() {
        Listable listable = new Article(new ArrayList<ArticleMedia>(), "title", new Date(), "subCategory", "category", "url");
        assertEquals(10, listable.getDateString().length());
    }


}