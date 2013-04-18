/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/dao/DAOImpl.e.vm.java
 */
package com.dddzer.dao;

import javax.inject.Named;
import javax.inject.Singleton;
import com.dddzer.dao.FaqQuestionDao;
import com.dddzer.dao.support.GenericDao;
import com.dddzer.domain.FaqQuestion;

/**
 * JPA 2 Data Access Object for {@link FaqQuestion}.
 * Note: do not use @Transactional in the DAO layer.
 */
@Named
@Singleton
public class FaqQuestionDao extends GenericDao<FaqQuestion, Integer> {
    public FaqQuestionDao() {
        super(FaqQuestion.class);
    }
}