/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/dao/support/JpaUniqueUtil.p.vm.java
 */
package com.dddzer.dao.support;

import static com.google.common.collect.Iterables.concat;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static java.util.Collections.emptyList;
import static org.apache.commons.lang.StringUtils.equalsIgnoreCase;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.hibernate.proxy.HibernateProxyHelper.getClassWithoutInitializingProxy;
import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;
import static org.springframework.util.ReflectionUtils.invokeMethod;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.UniqueConstraint;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import com.dddzer.domain.Identifiable;

@Named
@Singleton
public class JpaUniqueUtil {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Return the error code if the given property is already present in the database, returns null otherwise.
     */
    public String validateSimpleUnique(Identifiable<?> entity, String property, Object value) {
        Map<String, Object> values = newHashMap();
        values.put(property, value);
        return existsInDatabaseOnAllObjects(entity, values) ? simpleUniqueConstraintError(entity, property) : null;
    }

    /**
     * Return a list of error codes for all composite unique and simple unique constraints violations.
     */
    public List<String> validateUniques(Identifiable<?> entity) {
        return newArrayList(concat( //
                validateCompositeUniqueConstraints(entity), //
                validateSimpleUniqueConstraints(entity) //
        ));
    }

    private List<String> validateSimpleUniqueConstraints(Identifiable<?> entity) {
        return newArrayList(concat( //
                validateSimpleUniqueConstraintsDefinedOnMethods(entity), //
                validateSimpleUniqueConstraintsDefinedOnFields(entity)));
    }

    private List<String> validateSimpleUniqueConstraintsDefinedOnFields(Identifiable<?> entity) {
        Class<?> entityClass = getClassWithoutInitializingProxy(entity);
        List<String> errors = newArrayList();
        for (Field field : entityClass.getFields()) {
            Column column = field.getAnnotation(Column.class);
            if (column != null && column.unique()) {
                Map<String, Object> values = newHashMap();
                values.put(field.getName(), getValueFromField(field, entity));
                if (existsInDatabaseOnAllObjects(entity, values)) {
                    errors.add(simpleUniqueConstraintError(entity, field.getName()));
                }
            }
        }
        return errors;
    }

    private List<String> validateSimpleUniqueConstraintsDefinedOnMethods(Identifiable<?> entity) {
        Class<?> entityClass = getClassWithoutInitializingProxy(entity);
        List<String> errors = newArrayList();
        for (Method method : entityClass.getMethods()) {
            Column column = findAnnotation(entityClass, Column.class);
            if (column != null && column.unique()) {
                Map<String, Object> values = newHashMap();
                String property = methodToProperty(method);
                values.put(property, invokeMethod(method, entity));
                if (existsInDatabaseOnAllObjects(entity, values)) {
                    errors.add(simpleUniqueConstraintError(entity, property));
                }
            }
        }
        return errors;
    }

    private String simpleUniqueConstraintError(Identifiable<?> entity, String property) {
        return getEntityName(entity).toLowerCase() + "_" + property + "_already_exists";
    }

    private List<String> validateCompositeUniqueConstraints(Identifiable<?> entity) {
        Class<?> entityClass = getClassWithoutInitializingProxy(entity);
        Table table = findAnnotation(entityClass, Table.class);
        if (table == null) {
            return emptyList();
        }
        List<String> errors = newArrayList();
        for (UniqueConstraint uniqueConstraint : table.uniqueConstraints()) {
            if (!checkCompositeUniqueConstraint(entity, entityClass, uniqueConstraint)) {
                errors.add(compositeUniqueConstraintErrorCode(entity, uniqueConstraint));
            }
        }
        return errors;
    }

    private String compositeUniqueConstraintErrorCode(Identifiable<?> entity, UniqueConstraint uniqueConstraint) {
        return getEntityName(entity).toLowerCase() + "_"
                + (uniqueConstraint.name() == null ? "composite_unique_constraint_error" : uniqueConstraint.name().toLowerCase());
    }

    private boolean checkCompositeUniqueConstraint(Identifiable<?> entity, Class<?> entityClass, UniqueConstraint u) {
        Map<String, Object> values = newHashMap();
        values.putAll(getPropertyConstraints(entity, entityClass, u, ""));
        return !existsInDatabaseOnAllObjects(entity, values);
    }

    private Map<String, Object> getPropertyConstraints(Object entity, Class<?> entityClass, UniqueConstraint u, String prefix) {
        Map<String, Object> values = newHashMap();
        for (String column : u.columnNames()) {
            Method method = columnNameToMethod(entityClass, column);
            if (method != null) {
                values.put(prefix + methodToProperty(method), invokeMethod(method, entity));
            } else {
                Field field = columnNameToField(entityClass, column);
                if (field != null) {
                    values.put(prefix + field.getName(), getValueFromField(field, entity));
                }
            }
        }
        return values;
    }

    private Method columnNameToMethod(Class<?> clazz, String columnName) {
        for (Method method : clazz.getMethods()) {
            Column column = method.getAnnotation(Column.class);
            if (column != null && equalsIgnoreCase(columnName, column.name())) {
                return method;
            }
        }
        return null;
    }

    private Field columnNameToField(Class<?> clazz, String columnName) {
        for (Field field : clazz.getFields()) {
            Column column = field.getAnnotation(Column.class);
            if (equalsIgnoreCase(columnName, column.name())) {
                return field;
            }
        }
        return null;
    }

    private boolean existsInDatabaseOnAllObjects(Identifiable<?> entity, Map<String, Object> values) {
        if (entity == null || values == null || values.isEmpty()) {
            return false;
        }
        String entityName = getEntityName(entity);
        String sqlQuery = "select count(c) from " + entityName + " c where";
        boolean first = true;
        for (String propertyName : values.keySet()) {
            sqlQuery += (!first ? " and " : " ") + propertyName + "=:" + propertyName;
            first = false;
        }
        if (entity.isIdSet()) {
            if (!first) {
                sqlQuery += " and";
            }
            sqlQuery += " id<>:id";
        }
        TypedQuery<Long> query = entityManager.createQuery(sqlQuery, Long.class);
        for (String propertyName : values.keySet()) {
            query.setParameter(propertyName, values.get(propertyName));
        }
        if (entity.isIdSet()) {
            query.setParameter("id", entity.getId());
        }
        return query.getSingleResult() > 0;
    }

    private String getEntityName(Identifiable<?> entity) {
        Entity entityAnnotation = findAnnotation(entity.getClass(), Entity.class);
        if (isBlank(entityAnnotation.name())) {
            return getClassWithoutInitializingProxy(entity).getSimpleName();
        }
        return entityAnnotation.name();
    }

    private String methodToProperty(Method m) {
        return BeanUtils.findPropertyForMethod(m).getName();
    }

    private Object getValueFromField(Field field, Object object) {
        boolean accessible = field.isAccessible();
        try {
            return ReflectionUtils.getField(field, object);
        } finally {
            field.setAccessible(accessible);
        }
    }
}