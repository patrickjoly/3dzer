/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/test/java/service/ServiceImplTest.e.vm.java
 */
package com.dddzer.repository;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import javax.persistence.NonUniqueResultException;
import javax.persistence.NoResultException;

import org.junit.Before;
import org.junit.Test;

import com.dddzer.domain.FaqQuestion;
import com.dddzer.repository.FaqQuestionRepositoryImpl;
import com.dddzer.dao.FaqQuestionDao;
import com.dddzer.dao.support.SearchParameters;

/**
 * Unit test on FaqQuestionRepositoryImpl
 */
public class FaqQuestionRepositoryImplTest {

    private FaqQuestionRepositoryImpl faqQuestionRepositoryImpl;
    private FaqQuestionDao faqQuestionDao;

    @Before
    public void setUp() {
        // called before each test.
        faqQuestionRepositoryImpl = new FaqQuestionRepositoryImpl();
        faqQuestionDao = mock(FaqQuestionDao.class);
        faqQuestionRepositoryImpl.setFaqQuestionDao(faqQuestionDao);
    }

    @Test
    public void testFindUniqueOrNoneCaseNone() {
        FaqQuestion none = null;

        when(faqQuestionDao.findUniqueOrNone(any(FaqQuestion.class), any(SearchParameters.class))).thenReturn(none);

        FaqQuestion result = faqQuestionRepositoryImpl.findUniqueOrNone(new FaqQuestion());

        assertThat(result).isNull();
        verify(faqQuestionDao, times(1)).findUniqueOrNone(any(FaqQuestion.class), any(SearchParameters.class));
    }

    @Test
    public void testFindUniqueOrNoneCaseUnique() {
        FaqQuestion unique = new FaqQuestion();

        when(faqQuestionDao.findUniqueOrNone(any(FaqQuestion.class), any(SearchParameters.class))).thenReturn(unique);

        FaqQuestion result = faqQuestionRepositoryImpl.findUniqueOrNone(new FaqQuestion());

        assertThat(result).isNotNull();
        verify(faqQuestionDao, times(1)).findUniqueOrNone(any(FaqQuestion.class), any(SearchParameters.class));
    }

    @SuppressWarnings("unchecked")
    @Test(expected = NonUniqueResultException.class)
    public void testFindUniqueOrNoneCaseMultiple() {
        when(faqQuestionDao.findUniqueOrNone(any(FaqQuestion.class), any(SearchParameters.class))).thenThrow(NonUniqueResultException.class);

        faqQuestionRepositoryImpl.findUniqueOrNone(new FaqQuestion());
    }

    @SuppressWarnings("unchecked")
    @Test(expected = NoResultException.class)
    public void testFindUniqueCaseNone() {
        when(faqQuestionDao.findUnique(any(FaqQuestion.class), any(SearchParameters.class))).thenThrow(NoResultException.class);

        faqQuestionRepositoryImpl.findUnique(new FaqQuestion());
    }

    @Test
    public void testFindUniqueCaseUnique() {
        FaqQuestion unique = new FaqQuestion();

        when(faqQuestionDao.findUnique(any(FaqQuestion.class), any(SearchParameters.class))).thenReturn(unique);

        FaqQuestion result = faqQuestionRepositoryImpl.findUnique(new FaqQuestion());

        assertThat(result).isNotNull();
        verify(faqQuestionDao, times(1)).findUnique(any(FaqQuestion.class), any(SearchParameters.class));
    }

    @SuppressWarnings("unchecked")
    @Test(expected = NonUniqueResultException.class)
    public void testFindUniqueCaseMultiple() {
        when(faqQuestionDao.findUnique(any(FaqQuestion.class), any(SearchParameters.class))).thenThrow(NonUniqueResultException.class);

        faqQuestionRepositoryImpl.findUnique(new FaqQuestion());
    }
}