package com.vincler.jf.projet5;

import com.vincler.jf.projet5.models.ArticleListType;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;

public class ArticleListTypeTest {


    @Test
    public void ArticleListType_must_not_be_empty() {
        ArticleListType[] articleListTypes = ArticleListType.values();
        assertNotNull(articleListTypes);
        assertNotEquals("", articleListTypes);
    }



    //CharSequence title_1 = PageAdapter.getPageTitle(1);


}
