/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/domain/Entity.e.vm.java
 */
package com.dddzer.domain;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.TemporalType.TIMESTAMP;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.validator.constraints.Email;
import com.dddzer.audit.AuditContextHolder;
import com.dddzer.domain.Account;
import com.dddzer.domain.IdentifiableHashBuilder;
import com.google.common.base.Objects;

@Entity
@Table(name = "`user`")
@FilterDef(name = "myUserFilter", defaultCondition = "account_id = :currentAccountId ", parameters = @ParamDef(name = "currentAccountId", type = "org.hibernate.type.IntegerType"))
@Filter(name = "myUserFilter")
public class User implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(User.class);

    // Raw attributes
    private Integer id; // pk
    private String email;
    private Integer enbled;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String country;
    private String zip;
    private Date creationDate;
    private String creationAuthor;
    private Date modificationDate;
    private String modificationAuthor;
    private Integer version;

    // Technical attributes for query by example
    private Integer accountId; // not null

    // Many to one
    private Account account; // not null (accountId)

    // ---------------------------
    // Constructors
    // ---------------------------

    public User() {
    }

    public User(Integer primaryKey) {
        setId(primaryKey);
    }

    // -------------------------------
    // Getter & Setter
    // -------------------------------

    // -- [id] ------------------------

    @Column(name = "id", precision = 10)
    @GeneratedValue
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Transient
    public boolean isIdSet() {
        return id != null;
    }

    // -- [email] ------------------------

    @Size(max = 100)
    @Email
    @Column(name = "email", length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // -- [enbled] ------------------------

    @Column(name = "enbled", precision = 3)
    public Integer getEnbled() {
        return enbled;
    }

    public void setEnbled(Integer enbled) {
        this.enbled = enbled;
    }

    // -- [firstName] ------------------------

    @Size(max = 100)
    @Column(name = "first_name", length = 100)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // -- [lastName] ------------------------

    @Size(max = 100)
    @Column(name = "last_name", length = 100)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // -- [street] ------------------------

    @Size(max = 100)
    @Column(name = "street", length = 100)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    // -- [city] ------------------------

    @Size(max = 100)
    @Column(name = "city", length = 100)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // -- [country] ------------------------

    @Size(max = 100)
    @Column(name = "country", length = 100)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // -- [zip] ------------------------

    @Size(max = 100)
    @Column(name = "zip", length = 100)
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    // -- [creationDate] ------------------------

    @Column(name = "creation_date", length = 19)
    @Temporal(TIMESTAMP)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    // -- [creationAuthor] ------------------------

    @Column(name = "creation_author", length = 100)
    public String getCreationAuthor() {
        return creationAuthor;
    }

    public void setCreationAuthor(String creationAuthor) {
        this.creationAuthor = creationAuthor;
    }

    // -- [modificationDate] ------------------------

    @Column(name = "modification_date", length = 19)
    @Temporal(TIMESTAMP)
    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    // -- [modificationAuthor] ------------------------

    @Size(max = 100)
    @Column(name = "modification_author", length = 100)
    public String getModificationAuthor() {
        return modificationAuthor;
    }

    public void setModificationAuthor(String modificationAuthor) {
        this.modificationAuthor = modificationAuthor;
    }

    // -- [version] ------------------------

    @Column(name = "version", precision = 10)
    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    // -- [accountId] ------------------------

    @Column(name = "account_id", nullable = false, precision = 10, insertable = false, updatable = false)
    public Integer getAccountId() {
        return accountId;
    }

    private void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    // --------------------------------------------------------------------
    // Many to One support
    // --------------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: User.accountId ==> Account.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @NotNull
    @JoinColumn(name = "account_id", nullable = false)
    @ManyToOne(cascade = { PERSIST, MERGE }, fetch = LAZY)
    public Account getAccount() {
        return account;
    }

    /**
     * Set the account without adding this User instance on the passed account
     * If you want to preserve referential integrity we recommend to use
     * instead the corresponding adder method provided by {@link Account}
     */
    public void setAccount(Account account) {
        this.account = account;

        // We set the foreign key property so it can be used by our JPA search by Example facility.
        if (account != null) {
            setAccountId(account.getId());
        } else {
            setAccountId(null);
        }
    }

    /**
     * Set the default values.
     */
    public void initDefaultValues() {
    }

    /**
     * equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof User && hashCode() == other.hashCode());
    }

    private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this);
    }

    /**
     * Construct a readable string representation for this User instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this) //
                .add("id", getId()) //
                .add("email", getEmail()) //
                .add("enbled", getEnbled()) //
                .add("firstName", getFirstName()) //
                .add("lastName", getLastName()) //
                .add("street", getStreet()) //
                .add("city", getCity()) //
                .add("country", getCountry()) //
                .add("zip", getZip()) //
                .add("creationDate", getCreationDate()) //
                .add("creationAuthor", getCreationAuthor()) //
                .add("modificationDate", getModificationDate()) //
                .add("modificationAuthor", getModificationAuthor()) //
                .add("version", getVersion()) //
                .add("accountId", getAccountId()) //
                .toString();
    }

    @PrePersist
    protected void prePersist() {
        if (AuditContextHolder.audit()) {
            setCreationAuthor(AuditContextHolder.username());
            setCreationDate(new Date());
        }
    }
}