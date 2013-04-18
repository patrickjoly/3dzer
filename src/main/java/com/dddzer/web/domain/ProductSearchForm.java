/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-spring-conversation:src/main/java/domain/SearchForm.e.vm.java
 */
package com.dddzer.web.domain;

import static com.dddzer.dao.support.EntitySelector.newEntitySelector;
import static com.dddzer.dao.support.Ranges.RangeInteger.newRangeInteger;
import static com.dddzer.domain.Product_.accoutId;
import static com.dddzer.domain.Product_.part;
import static com.dddzer.domain.Product_.partId;
import static com.dddzer.domain.Product_.price;
import static com.dddzer.domain.Product_.productTypeId;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import com.dddzer.dao.support.EntitySelector;
import com.dddzer.dao.support.Ranges.RangeInteger;
import com.dddzer.dao.support.SearchParameters;
import com.dddzer.domain.Account;
import com.dddzer.domain.Part;
import com.dddzer.domain.Product;
import com.dddzer.domain.ProductType;
import com.dddzer.web.domain.support.GenericSearchForm;

/**
 * View Helper to find/select {@link Product}.
 * It exposes a {@link Product} instance so it can be used in search by Example query.
 */
@Named
@Scope("conversation")
public class ProductSearchForm extends GenericSearchForm<Product, ProductSearchForm> {
    private static final long serialVersionUID = 1L;

    private Product product = new Product();
    private RangeInteger<Product> priceRange = newRangeInteger(price);
    private EntitySelector<Product, Part, Integer> partSelector = newEntitySelector(partId);
    private EntitySelector<Product, Account, Integer> accoutSelector = newEntitySelector(accoutId);
    private EntitySelector<Product, ProductType, Integer> productTypeSelector = newEntitySelector(productTypeId);

    public Product getProduct() {
        return product;
    }

    @Override
    protected Product getEntity() {
        return product;
    }

    // Ranges, used from the view.
    public RangeInteger<Product> getPriceRange() {
        return priceRange;
    }

    // Selectors for x-to-one associations
    public EntitySelector<Product, Part, Integer> getPartSelector() {
        return partSelector;
    }

    public EntitySelector<Product, Account, Integer> getAccoutSelector() {
        return accoutSelector;
    }

    public EntitySelector<Product, ProductType, Integer> getProductTypeSelector() {
        return productTypeSelector;
    }

    public SearchParameters toSearchParameters() {
        return new SearchParameters() //
                .anywhere() //
                .leftJoin(part) //
                .range(priceRange) //
                .entitySelector(partSelector) //
                .entitySelector(accoutSelector) //
                .entitySelector(productTypeSelector) //
        ;
    }

    @Override
    public ProductSearchForm newInstance() {
        return new ProductSearchForm();
    }

    @Override
    public void resetWithOther(ProductSearchForm other) {
        this.product = other.getProduct();
        this.priceRange = other.getPriceRange();
        this.partSelector = other.getPartSelector();
        this.accoutSelector = other.getAccoutSelector();
        this.productTypeSelector = other.getProductTypeSelector();
    }
}