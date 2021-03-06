/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/test/java/dao/support/NamedQueryUtilIT.p.vm.java
 */
package com.dddzer.dao.support;

import java.util.*;

import javax.inject.Inject;
import org.apache.log4j.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dddzer.dao.support.*;
import com.dddzer.util.*;
import com.dddzer.domain.Account;
import com.dddzer.domain.Category;
import com.dddzer.domain.FaqAnswer;
import com.dddzer.domain.FaqQuestion;
import com.dddzer.domain.Part;
import com.dddzer.domain.Product;
import com.dddzer.domain.ProductType;
import com.dddzer.domain.Role;
import com.dddzer.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext-test.xml" })
@Transactional
public class NamedQueryUtilIT {

    private static final Logger log = Logger.getLogger(NamedQueryUtilIT.class);

    @Inject
    private NamedQueryUtil namedQueryUtil;

    @Test
    public void executeGetAllAccountsQueryName() {
        String sqlQuery = "Account.selectAll";
        List<Account> accounts = namedQueryUtil.findByNamedQuery(new SearchParameters().namedQuery(sqlQuery));

        if (accounts != null) {
            log.info(accounts.size());

            for (Account account : accounts) {
                log.info(account.toString());
            }
        }
    }

    @Test
    public void executeGetAllAccountsSqlQuery() {
        String sqlQuery = "Account.selectAll.native";
        List<Account> accounts = namedQueryUtil.findByNamedQuery(new SearchParameters().namedQuery(sqlQuery));

        if (accounts != null) {
            log.info(accounts.size());
            for (Account account : accounts) {
                log.info(account.toString());
            }
        }
    }

}
