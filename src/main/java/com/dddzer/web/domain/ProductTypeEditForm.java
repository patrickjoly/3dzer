/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-spring-conversation:src/main/java/domain/EditForm.e.vm.java
 */
package com.dddzer.web.domain;

import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import com.dddzer.domain.ProductType;
import com.dddzer.repository.ProductTypeRepository;
import com.dddzer.web.domain.support.GenericEditForm;

/**
 * View Helper/Controller to edit {@link ProductType}.
 */
@Named
@Scope("conversation")
public class ProductTypeEditForm extends GenericEditForm<ProductType, Integer> {

    @Inject
    public void setProductTypeRepository(ProductTypeRepository productTypeRepository) {
        setRepository(productTypeRepository);
    }

    public ProductType getProductType() {
        return getEntity();
    }
}