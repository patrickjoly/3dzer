/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/domain/EntityMeta_.e.vm.java
 */
package com.dddzer.domain;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import com.dddzer.domain.Account;

@StaticMetamodel(User.class)
public abstract class User_ {

    // Raw attributes
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, Integer> enbled;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> street;
    public static volatile SingularAttribute<User, String> city;
    public static volatile SingularAttribute<User, String> country;
    public static volatile SingularAttribute<User, String> zip;
    public static volatile SingularAttribute<User, Date> creationDate;
    public static volatile SingularAttribute<User, String> creationAuthor;
    public static volatile SingularAttribute<User, Date> modificationDate;
    public static volatile SingularAttribute<User, String> modificationAuthor;
    public static volatile SingularAttribute<User, Integer> version;

    // Technical attributes for query by example
    public static volatile SingularAttribute<User, Integer> accountId;

    // Many to one
    public static volatile SingularAttribute<User, Account> account;
}