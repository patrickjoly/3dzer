/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/test/java/domain/ModelTest.e.vm.java
 */
package com.dddzer.domain;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;
import org.junit.Test;

import com.dddzer.util.*;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import com.dddzer.domain.FaqQuestion;
import com.dddzer.domain.Part;
import com.dddzer.domain.Role;

/**
 * Basic tests for Account
 */
@SuppressWarnings("unused")
public class AccountTest {

    // test unique primary key
    @Test
    public void newInstanceHasNoPrimaryKey() {
        Account model = new Account();
        assertFalse(model.isIdSet());
    }

    @Test
    public void isIdSetReturnsTrue() {
        Account model = new Account();
        model.setId(ValueGenerator.getUniqueInteger());
        assertNotNull(model.getId());
        assertTrue(model.isIdSet());
    }

    // test columns methods

    //-------------------------------------------------------------
    // One to Many: SimpleOneToMany account.id ==> part.account_id
    //-------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // part.part <-- account.accounts
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @Test
    public void oneToMany_addPart() {
        Account one = new Account();
        Part many = new Part();

        // init
        one.addPart(many);

        // make sure it is propagated
        assertTrue(one.getParts().contains(many));
        assertTrue(one.equals(many.getAccount()));

        // now set it to null
        one.removePart(many);

        // make sure null is propagated
        assertFalse(one.getParts().contains(many));
        assertNull(many.getAccount());
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // faqQuestion.faqQuestion <-- account.accounts
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @Test
    public void oneToMany_addFaqQuestion() {
        Account one = new Account();
        FaqQuestion many = new FaqQuestion();

        // init
        one.addFaqQuestion(many);

        // make sure it is propagated
        assertTrue(one.getFaqQuestions().contains(many));
        assertTrue(one.equals(many.getAccount()));

        // now set it to null
        one.removeFaqQuestion(many);

        // make sure null is propagated
        assertFalse(one.getFaqQuestions().contains(many));
        assertNull(many.getAccount());
    }

    //-------------------------------------------------------------
    // Pure Many to Many
    //-------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @Test
    public void manyToMany_addRole() {
        Account many1 = new Account();
        Role many2 = new Role();

        // add it
        many1.addRole(many2);

        // check it is propagated
        assertTrue(many1.getRoles().contains(many2));
        // now let's remove it
        many1.removeRole(many2);

        // check it is propagated
        assertFalse(many1.getRoles().contains(many2));
    }

    @Test
    public void toStringNotNull() {
        Account model = new Account();
        assertNotNull(model.toString());
    }

    @Test
    public void equalsUsingBusinessKey() {
        Account model1 = new Account();
        Account model2 = new Account();
        String login = ValueGenerator.getUniqueString(100);
        model1.setLogin(login);
        model2.setLogin(login);
        assertTrue(model1.equals(model2));
        assertTrue(model2.equals(model1));
        assertTrue(model1.hashCode() == model2.hashCode());
    }
}