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
import com.dddzer.domain.Category;
import com.dddzer.repository.CategoryRepository;
import com.dddzer.web.converter.GenericJsfConverter;

/**
 * JSF Converter for {@link Category}.
 */
@Named
@Singleton
public class CategoryJsfConverter extends GenericJsfConverter<Category, Integer> {
    @Inject
    public CategoryJsfConverter(CategoryRepository categoryRepository) {
        super(categoryRepository, Category.class, Integer.class);
    }
}