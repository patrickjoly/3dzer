/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-spring-conversation:src/main/java/domain/LazyDataModel.e.vm.java
 */
package com.dddzer.web.domain;

import static com.dddzer.web.conversation.ConversationHolder.getCurrentConversation;
import java.util.Arrays;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import com.dddzer.domain.ProductType;
import com.dddzer.repository.ProductTypeRepository;
import com.dddzer.repository.support.Repository;
import com.dddzer.web.conversation.ConversationContext;
import com.dddzer.web.converter.ProductTypeJsfConverter;
import com.dddzer.web.domain.support.GenericLazyDataModel;
import com.dddzer.web.domain.support.GenericSearchForm;

/**
 * Provides server-side pagination for search.
 * TODO: dependencies wiring after deserialization (get inspiration from http://jira.springframework.org/browse/SWF-1224 )
 */
@Named
@Scope("conversation")
public class ProductTypeLazyDataModel extends GenericLazyDataModel<ProductType, Integer, ProductTypeSearchForm> {
    private static final long serialVersionUID = 1L;

    @Inject
    transient private ProductTypeRepository productTypeRepository;
    @Inject
    transient private ProductTypeJsfConverter productTypeConverter;
    @Inject
    transient private ProductTypeSearchForm productTypeSearchForm;

    private ProductType[] selectedRows;

    @Override
    protected Repository<ProductType, Integer> getRepository() {
        return productTypeRepository;
    }

    @Override
    protected Converter getConverter() {
        return productTypeConverter;
    }

    @Override
    protected GenericSearchForm<ProductType, ProductTypeSearchForm> getSearchForm() {
        return productTypeSearchForm;
    }

    @Override
    protected ConversationContext<ProductType> getSelectedContext(ProductType selected) {
        if (selected.isIdSet()) {
            // just the id matters as we want to reload it in the conversation entity manager.
            return ProductTypeController.newEditContext(selected.getId());
        } else {
            return ProductTypeController.newEditContext(selected); // fresh entity (creation)
        }
    }

    // -----------------------------------
    // Multi selection support
    // -----------------------------------

    public void setSelectedRows(ProductType[] selectedRows) {
        this.selectedRows = selectedRows;
    }

    public ProductType[] getSelectedRows() {
        return selectedRows;
    }

    public String multiSelect() {
        return getCurrentConversation() //
                .<ConversationContext<ProductType>> getCurrentContext() //
                .getCallBack() //
                .selected(Arrays.asList(selectedRows));
    }
}