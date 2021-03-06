/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/test/java/service/ModelGenerator.e.vm.java
 */
package com.dddzer.repository;

import javax.inject.Named;
import javax.inject.Singleton;
import com.dddzer.domain.Category;
import com.dddzer.util.ValueGenerator;

/**
 * Helper class to create transient entities instance for testing purposes.
 * Simple properties are pre-filled with random values.
 */
@SuppressWarnings("unused")
@Named
@Singleton
public class CategoryGenerator {

    /**
     * Returns a new Category instance filled with random values.
     */
    public Category getCategory() {
        Category category = new Category();

        // simple attributes follows
        category.setDescriptionFr("a");
        category.setDescriptionEn("a");
        return category;
    }

}