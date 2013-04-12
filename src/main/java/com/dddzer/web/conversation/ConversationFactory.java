/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-spring-conversation:src/main/java/conversation/ConversationFactory.p.vm.java
 */
package com.dddzer.web.conversation;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface to create Conversation given an incoming http request.
 * The requested URI determines which conversation should be created.
 */
public interface ConversationFactory {

    /**
     * Whether this factory can create a {@link Conversation} for the given request.
     */
    boolean canCreateConversation(HttpServletRequest request);

    /**
     * Create a new {@link Conversation} for the given request.
     */
    Conversation createConversation(HttpServletRequest request);
}