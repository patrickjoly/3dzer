/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-spring-conversation:src/main/java/domain/Controller.e.vm.java
 */
package com.dddzer.web.domain;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import com.dddzer.dao.support.SearchParameters;
import com.dddzer.domain.Part;
import com.dddzer.repository.PartRepository;
import com.dddzer.web.conversation.Conversation;
import com.dddzer.web.conversation.ConversationContext;
import com.dddzer.web.conversation.ConversationFactory;

/**
 * Stateless controller for Part conversation start. Provides also auto-complete support. 
 */
@Named
@Singleton
public class PartController implements ConversationFactory {
    public final static String editUri = "/domain/partEdit.faces";
    public final static String selectUri = "/domain/partSelect.faces";
    private PartRepository partRepository;

    @Inject
    public void setPartRepository(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    // --------------------------------
    // ConversationFactoryImpl
    // --------------------------------

    @Override
    public boolean canCreateConversation(HttpServletRequest request) {
        return selectUri.equals(request.getServletPath());
    }

    @Override
    public Conversation createConversation(HttpServletRequest request) {
        String uri = request.getServletPath();
        if (selectUri.equals(uri)) {
            Conversation conversation = Conversation.newInstance(request);
            ConversationContext<Part> ctx = newSearchContext();
            ctx.setLabelWithKey("part");
            conversation.setNextContext(ctx);
            return conversation;
        }

        throw new IllegalStateException("Unexpected conversation creation demand");
    }

    // --------------------------------
    // Autocomplete support
    // --------------------------------

    /**
     * This method is used from primefaces autocomplete components.
     */
    public List<Part> complete(String value) {
        SearchParameters sp = new SearchParameters().anywhere().searchPattern(value);
        return partRepository.find(sp);
    }

    // --------------------------------
    // Helper 
    // --------------------------------    

    /**
     * Helper to construct a new ConversationContext to edit an Part.
     * @param part the entity to edit.
     */
    public static ConversationContext<Part> newEditContext(final Part part) {
        ConversationContext<Part> ctx = new ConversationContext<Part>();
        ctx.setEntity(part); // used by GenericEditForm.init()
        ctx.setViewUri(editUri);
        ctx.addSourceIgnoringUseConversationEntityManager("form:category");
        ctx.addSourceIgnoringUseConversationEntityManager("form:account");
        return ctx;
    }

    /**
     * Helper to construct a new ConversationContext to edit an Part.
     * @param id the id of the entity to edit.
     */
    public static ConversationContext<Part> newEditContext(final Integer id) {
        ConversationContext<Part> ctx = new ConversationContext<Part>();
        ctx.setEntityId(id); // used by GenericEditForm.init()
        ctx.setViewUri(editUri);
        ctx.addSourceIgnoringUseConversationEntityManager("form:category");
        ctx.addSourceIgnoringUseConversationEntityManager("form:account");
        return ctx;
    }

    /**
     * Helper to construct a new ConversationContext for search/selection.
     */
    public static ConversationContext<Part> newSearchContext() {
        ConversationContext<Part> ctx = new ConversationContext<Part>();
        ctx.setUseConversationEntityManager(false);
        ctx.setViewUri(selectUri);
        return ctx;
    }
}