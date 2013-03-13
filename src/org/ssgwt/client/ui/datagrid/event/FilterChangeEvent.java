package org.ssgwt.client.ui.datagrid.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * Event that is dispatched when a user changes a filter
 * 
 * @author Johannes Gryffenberg<johannes.gryffenberg@gmail.com>
 * @since 9 July 2012
 */
public class FilterChangeEvent extends GwtEvent<FilterChangeEvent.FilterChangeHandler> {
    
    /**
     * The handler type for the event
     */
    public static Type<FilterChangeHandler> TYPE = new Type<FilterChangeHandler>();

    /**
     * The event handler interface for the event
     * 
     * @author Johannes Gryffenberg
     * @since 9 July 2012
     */
    public interface FilterChangeHandler extends EventHandler {
        
        /**
         * Handles the FilterChangeEvent
         * 
         * @param event - The event that should be handled
         */
        void onFilterChange(FilterChangeEvent event);
    }

    /**
     * The event constructor
     */
    public FilterChangeEvent() {
    }

    /**
     * Dispatches the event
     * 
     * @param handler - The function that will handle the event
     */
    @Override
    protected void dispatch(FilterChangeHandler handler) {
        handler.onFilterChange(this);
    }

    /**
     * Getter to retrieve the event handler type
     * 
     * @return The event handler
     */
    @Override
    public Type<FilterChangeHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Getter to retrieve the event handler type
     * 
     * @return The event handler
     */
    public static Type<FilterChangeHandler> getType() {
        return TYPE;
    }

    /**
     * Helper function to make it easier to dispatch an FilterChangeEvent
     * 
     * @param source - The object that is dispatching the event
     */
    public static void fire(HasHandlers source) {
        source.fireEvent(new FilterChangeEvent());
    }
}
