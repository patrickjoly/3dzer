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
import com.dddzer.domain.FaqAnswer;
import com.dddzer.dao.FaqAnswerDao;

/**
 * Default implementation of the {@link FaqAnswerRepository} interface.
 * Note: you may use multiple DAO from this layer.
 * @see FaqAnswerRepository
 */
@Named("faqAnswerRepository")
@Singleton
public class FaqAnswerRepositoryImpl extends RepositoryImpl<FaqAnswer, Integer> implements FaqAnswerRepository {

    private static final Logger log = Logger.getLogger(FaqAnswerRepositoryImpl.class);

    protected FaqAnswerDao faqAnswerDao;

    @Inject
    public void setFaqAnswerDao(FaqAnswerDao faqAnswerDao) {
        this.faqAnswerDao = faqAnswerDao;
    }

    /**
     * Dao getter used by the {@link RepositoryImpl}.
     */
    @Override
    public GenericDao<FaqAnswer, Integer> getDao() {
        return faqAnswerDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FaqAnswer getNew() {
        return new FaqAnswer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FaqAnswer getNewWithDefaults() {
        FaqAnswer result = getNew();
        result.initDefaultValues();
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(FaqAnswer faqAnswer) {
        if (faqAnswer == null) {
            if (log.isDebugEnabled()) {
                log.debug("Skipping deletion for null model!");
            }

            return;
        }

        // remove the reference from the associated faqQuestion
        if (faqAnswer.getFaqQuestion() != null) {
            faqAnswer.getFaqQuestion().removeFaqAnswer(faqAnswer);
        }

        super.delete(faqAnswer);
    }
}