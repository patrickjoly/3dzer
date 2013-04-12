/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-spring-conversation:src/main/java/conversation/ConversationListener.p.vm.java
 */
package com.dddzer.web.conversation;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface to be implemented if you wish to listen to the lifecycle of {@link Conversation conversations}.
 */
public interface ConversationListener {

    /**
     * Called after a {@link Conversation} has been created but before it starts.
     * @param conversation the conversation that was created.
     */
    void conversationCreated(Conversation conversation);

    /**
     * Called to indicate the conversation is being resumed.
     */
    void conversationResuming(Conversation conversation, HttpServletRequest request);

    /**
     * Called to indicate the passed Conversation is being paused.
     */
    void conversationPausing(Conversation conversation);

    /**
     * Called to indicate the conversation is ending.
     */
    void conversationEnding(Conversation conversation);
}