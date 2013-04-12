/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-spring-conversation:src/main/java/util/MessageBundle.p.vm.java
 */
package com.dddzer.web.util;

import java.util.Enumeration;
import java.util.ResourceBundle;

import com.dddzer.util.ResourcesUtil;

/**
 * This ResourceBundle is set in faces-config.xml under 'msg' var.
 * <p>
 * Implementation uses Spring {@link MessageSource}.
 * <p>
 * From your JSF2 pages, you may use <code>#{msg.property_key}</code>.
 * <p>
 * _HACK_ as it is a tricky JSF/Spring integration point.
 */
public class MessageBundle extends ResourceBundle {

    private ResourcesUtil resourcesUtil;

    @Override
    public Enumeration<String> getKeys() {
        return null;
    }

    @Override
    protected Object handleGetObject(String key) {
        if (resourcesUtil == null) {
            resourcesUtil = ResourcesUtil.getInstance();
        }
        return resourcesUtil.getProperty(key);
    }
}