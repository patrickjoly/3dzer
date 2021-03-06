/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-spring-conversation:src/main/java/converter/JsfConverter.e.vm.java
 */
package com.dddzer.web.converter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import com.dddzer.domain.Product;
import com.dddzer.repository.ProductRepository;
import com.dddzer.web.converter.GenericJsfConverter;

/**
 * JSF Converter for {@link Product}.
 */
@Named
@Singleton
public class ProductJsfConverter extends GenericJsfConverter<Product, Integer> {
    @Inject
    public ProductJsfConverter(ProductRepository productRepository) {
        super(productRepository, Product.class, Integer.class);
    }
}