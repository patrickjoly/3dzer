/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/test/java/service/ModelGenerator.e.vm.java
 */
package com.dddzer.repository;

import java.util.Date;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import com.dddzer.domain.Account;
import com.dddzer.domain.User;
import com.dddzer.repository.AccountGenerator;
import com.dddzer.repository.AccountRepository;
import com.dddzer.util.ValueGenerator;

/**
 * Helper class to create transient entities instance for testing purposes.
 * Simple properties are pre-filled with random values.
 */
@SuppressWarnings("unused")
@Named
@Singleton
public class UserGenerator {

    /**
     * Returns a new User instance filled with random values.
     */
    public User getUser() {
        User user = new User();

        // simple attributes follows
        user.setEmail("dummy@dummy.com");
        user.setEnbled(1);
        user.setFirstName("a");
        user.setLastName("a");
        user.setStreet("a");
        user.setCity("a");
        user.setCountry("a");
        user.setZip("a");
        user.setCreationDate(new Date());
        user.setCreationAuthor("a");
        user.setModificationDate(new Date());
        user.setModificationAuthor("a");
        // mandatory relation
        Account account = accountGenerator.getAccount();
        accountRepository.save(account);
        user.setAccount(account);
        return user;
    }

    @Inject
    private AccountRepository accountRepository;
    @Inject
    private AccountGenerator accountGenerator;
}