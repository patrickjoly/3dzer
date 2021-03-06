/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/test/java/dao/DaoImplIT.e.vm.java
 */
package com.dddzer.dao;

import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import static com.google.common.collect.Sets.newHashSet;
import static org.fest.assertions.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dddzer.domain.Category;
import com.dddzer.repository.CategoryGenerator;
import com.dddzer.dao.CategoryDao;

/**
 * Integration test on CategoryDaoImpl
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext-test.xml" })
@Transactional
public class CategoryDaoImplTest {
    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(CategoryDaoImplTest.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private CategoryDao categoryDao;

    @Inject
    private CategoryGenerator categoryGenerator;

    @Test
    @Rollback
    public void saveAndGet() {
        Category category = categoryGenerator.getCategory();

        // add it to a Set before saving (force equals/hashcode)
        Set<Category> set = newHashSet(category, category);
        assertThat(set).hasSize(1);

        categoryDao.save(category);
        entityManager.flush();

        // reload it from cache and check equality
        Category model = new Category();
        model.setId(category.getId());
        assertThat(category).isEqualTo(categoryDao.get(model));

        // clear cache to force reload from db
        entityManager.clear();

        // pk are equals...
        assertThat(category.getId()).isEqualTo(categoryDao.get(model).getId());

        // but since you do not use a business key, equality is lost.
        assertThat(category).isNotEqualTo(categoryDao.get(model));
    }
}