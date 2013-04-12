/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-spring-conversation:src/main/java/domain/SearchForm.e.vm.java
 */
package com.dddzer.web.domain;

import static com.dddzer.dao.support.EntitySelector.newEntitySelector;
import static com.dddzer.domain.Part_.account;
import static com.dddzer.domain.Part_.accountId;
import static com.dddzer.domain.Part_.categoryId;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import com.dddzer.dao.support.EntitySelector;
import com.dddzer.dao.support.SearchParameters;
import com.dddzer.domain.Account;
import com.dddzer.domain.Category;
import com.dddzer.domain.Part;
import com.dddzer.web.domain.support.GenericSearchForm;

/**
 * View Helper to find/select {@link Part}.
 * It exposes a {@link Part} instance so it can be used in search by Example query.
 */
@Named
@Scope("conversation")
public class PartSearchForm extends GenericSearchForm<Part, PartSearchForm> {
    private static final long serialVersionUID = 1L;

    private Part part = new Part();
    private EntitySelector<Part, Category, Integer> categorySelector = newEntitySelector(categoryId);
    private EntitySelector<Part, Account, Integer> accountSelector = newEntitySelector(accountId);

    public Part getPart() {
        return part;
    }

    @Override
    protected Part getEntity() {
        return part;
    }

    // Selectors for x-to-one associations
    public EntitySelector<Part, Category, Integer> getCategorySelector() {
        return categorySelector;
    }

    public EntitySelector<Part, Account, Integer> getAccountSelector() {
        return accountSelector;
    }

    public SearchParameters toSearchParameters() {
        return new SearchParameters() //
                .anywhere() //
                .leftJoin(account) //
                .entitySelector(categorySelector) //
                .entitySelector(accountSelector) //
        ;
    }

    @Override
    public PartSearchForm newInstance() {
        return new PartSearchForm();
    }

    @Override
    public void resetWithOther(PartSearchForm other) {
        this.part = other.getPart();
        this.categorySelector = other.getCategorySelector();
        this.accountSelector = other.getAccountSelector();
    }
}