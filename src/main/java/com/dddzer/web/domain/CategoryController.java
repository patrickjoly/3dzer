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
import com.dddzer.domain.Category;
import com.dddzer.repository.CategoryRepository;
import com.dddzer.web.conversation.Conversation;
import com.dddzer.web.conversation.ConversationContext;
import com.dddzer.web.conversation.ConversationFactory;

/**
 * Stateless controller for Category conversation start. Provides also auto-complete support. 
 */
@Named
@Singleton
public class CategoryController implements ConversationFactory {
    public final static String editUri = "/domain/categoryEdit.faces";
    public final static String selectUri = "/domain/categorySelect.faces";
    private CategoryRepository categoryRepository;

    @Inject
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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
            ConversationContext<Category> ctx = newSearchContext();
            ctx.setLabelWithKey("category");
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
    public List<Category> complete(String value) {
        SearchParameters sp = new SearchParameters().anywhere().searchPattern(value);
        return categoryRepository.find(sp);
    }

    // --------------------------------
    // Helper 
    // --------------------------------    

    /**
     * Helper to construct a new ConversationContext to edit an Category.
     * @param category the entity to edit.
     */
    public static ConversationContext<Category> newEditContext(final Category category) {
        ConversationContext<Category> ctx = new ConversationContext<Category>();
        ctx.setEntity(category); // used by GenericEditForm.init()
        ctx.setViewUri(editUri);
        return ctx;
    }

    /**
     * Helper to construct a new ConversationContext to edit an Category.
     * @param id the id of the entity to edit.
     */
    public static ConversationContext<Category> newEditContext(final Integer id) {
        ConversationContext<Category> ctx = new ConversationContext<Category>();
        ctx.setEntityId(id); // used by GenericEditForm.init()
        ctx.setViewUri(editUri);
        return ctx;
    }

    /**
     * Helper to construct a new ConversationContext for search/selection.
     */
    public static ConversationContext<Category> newSearchContext() {
        ConversationContext<Category> ctx = new ConversationContext<Category>();
        ctx.setUseConversationEntityManager(false);
        ctx.setViewUri(selectUri);
        return ctx;
    }
}