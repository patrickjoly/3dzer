/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/repository/RepositoryImpl.e.vm.java
 */
package com.dddzer.repository;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import javax.inject.Named;
import javax.inject.Singleton;
import org.springframework.transaction.annotation.Transactional;

import com.dddzer.dao.support.GenericDao;
import com.dddzer.repository.support.RepositoryImpl;
import com.dddzer.domain.FaqQuestion;
import com.dddzer.dao.FaqQuestionDao;

/**
 * Default implementation of the {@link FaqQuestionRepository} interface.
 * Note: you may use multiple DAO from this layer.
 * @see FaqQuestionRepository
 */
@Named("faqQuestionRepository")
@Singleton
public class FaqQuestionRepositoryImpl extends RepositoryImpl<FaqQuestion, Integer> implements FaqQuestionRepository {

    private static final Logger log = Logger.getLogger(FaqQuestionRepositoryImpl.class);

    protected FaqQuestionDao faqQuestionDao;

    @Inject
    public void setFaqQuestionDao(FaqQuestionDao faqQuestionDao) {
        this.faqQuestionDao = faqQuestionDao;
    }

    /**
     * Dao getter used by the {@link RepositoryImpl}.
     */
    @Override
    public GenericDao<FaqQuestion, Integer> getDao() {
        return faqQuestionDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FaqQuestion getNew() {
        return new FaqQuestion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FaqQuestion getNewWithDefaults() {
        FaqQuestion result = getNew();
        result.initDefaultValues();
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(FaqQuestion faqQuestion) {
        if (faqQuestion == null) {
            if (log.isDebugEnabled()) {
                log.debug("Skipping deletion for null model!");
            }

            return;
        }

        // remove the reference from the associated account
        if (faqQuestion.getAccount() != null) {
            faqQuestion.getAccount().removeFaqQuestion(faqQuestion);
        }

        super.delete(faqQuestion);
    }
}