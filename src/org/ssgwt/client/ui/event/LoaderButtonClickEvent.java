package org.ssgwt.client.ui.event;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * Event dispatched by the LoaderImageButton if it is clicked
 * 
 * @author Johannes Gryffenberg<johannes.gryffenberg@gmail.com>
 * @since  14 December 2012
 */
public class LoaderButtonClickEvent extends GwtEvent<LoaderButtonClickEvent.LoaderButtonClickHandler> {

    /**
     * Holds the event type
     */
    public static Type<LoaderButtonClickHandler> TYPE = new Type<LoaderButtonClickHandler>();
    
    /**
     * The original click event that was caught
     */
    private ClickEvent clickEvent;
    
    /**
     * The event handler interface for the event
     * 
     * @author Johannes Gryffenberg
     * @since 14 December 2012
     */
    public interface LoaderButtonClickHandler extends EventHandler {
        
        /**
         * Handles the LoaderButtonClickEvent
         * 
         * @param event - The event that should be handled
         */
        void onLoaderButtonClick(LoaderButtonClickEvent event);
    }

    /**
     * The event constructor
     * 
     * @param clickEvent - the original click event that was handled
     */
    public LoaderButtonClickEvent(ClickEvent clickEvent) {
        this.clickEvent = clickEvent;
    }

    /**
     * Retrieve the original click event that was handled
     * 
     * @return the original click event that was handled
     */
    public ClickEvent getClickEvent() {
        return clickEvent;
    }

    /**
     * Dispatches the event
     * 
     * @param handler - The function that will handle the event
     */
    @Override
    protected void dispatch(LoaderButtonClickHandler handler) {
        handler.onLoaderButtonClick(this);
    }

    /**
     * Getter to retrieve the event handler type
     * 
     * @return The event handler
     */
    @Override
    public Type<LoaderButtonClickHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Getter to retrieve the event handler type
     * 
     * @return The event handler
     */
    public static Type<LoaderButtonClickHandler> getType() {
        return TYPE;
    }

    /**
     * Helper function to create and dispatch an event
     * 
     * @param source - The event handler
     * @param clickEvent - The original click event that was handled
     */
    public static void fire(HasHandlers source, ClickEvent clickEvent) {
        source.fireEvent(new LoaderButtonClickEvent(clickEvent));
    }
}
