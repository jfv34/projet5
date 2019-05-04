package com.vincler.jf.projet5.models;

import java.util.List;

public interface ArticlesResponse<T extends Listable> {

    public List<T> getResults();
}


