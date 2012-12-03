package org.ssgwt.client.ui.form.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import java.lang.Object;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Event dispatched to indicate that confirmation is needed on a ComplexInputForm.
 * 
 * @author Ashwin Arendse <ashwin.arendse@a24group.com>
 * @since  03 December 2012
 */
public class ComplexInputFormConfirmationEvent extends
        GwtEvent<ComplexInputFormConfirmationEvent.ComplexInputFormConfirmationHandler> {

    /**
     * Type of the event.
     */
    public static Type<ComplexInputFormConfirmationHandler> TYPE = new Type<ComplexInputFormConfirmationHandler>();
    
    /**
     * class variable for the Async Callback
     */
    private AsyncCallback callback;

    /**
     * Handler interface that should be implemented by components that wish to
     * handle the event when it is dispatched.
     * 
     * @author Ashwin Arendse <ashwin.arendse@a24group.com>
     * @since  03 December 2012
     */
    public interface ComplexInputFormConfirmationHandler extends EventHandler {
        
        /**
         * Method to be called when a {@link ComplexInputFormConfirmationEvent} is
         * being handled.
         * 
         * @author Ashwin Arendse <ashwin.arendse@a24group.com>
         * @since  03 December 2012
         * 
         * @param event The event being handled.
         */
        void onComplexInputFormConfirmation(ComplexInputFormConfirmationEvent event);
    }

    /**
     * Function to add the handler to the class
     * 
     * @author Ashwin Arendse <ashwin.arendse@a24group.com>
     * @since  03 December 2012
     * 
     * @param handler - The event be handled
     */
    public interface ComplexInputFormConfirmationHasHandlers extends HasHandlers {
        HandlerRegistration addComplexInputFormConfirmationHandler(ComplexInputFormConfirmationHandler handler);
    }

    /**
     * Class constructor.
     * 
     * @author Ashwin Arendse <ashwin.arendse@a24group.com>
     * @since  03 December 2012
     * 
     * @param removeObjectVO - The VO to be removed.
     * @param removeObjectField - The Field to be removed.
     */
    public ComplexInputFormConfirmationEvent(AsyncCallback callback) {
        this.callback = callback;
    }

    /**
     * Dispatches the event to the given handler.
     * 
     * @author Ashwin Arendse <ashwin.arendse@a24group.com>
     * @since  03 December 2012
     * 
     * @param handler - The component that should handle the event.
     */
    @Override
    protected void dispatch(ComplexInputFormConfirmationHandler handler) {
        handler.onComplexInputFormConfirmation(this);
    }

    /**
     * Returns the associated type of the event.
     * 
     * @author Ashwin Arendse <ashwin.arendse@a24group.com>
     * @since  03 December 2012
     * 
     * @return The associated type.
     */
    @Override
    public Type<ComplexInputFormConfirmationHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Returns the type of the event.
     * 
     * @author Ashwin Arendse <ashwin.arendse@a24group.com>
     * @since  03 December 2012
     * 
     * @return the Type of the event
     */
    public static Type<ComplexInputFormConfirmationHandler> getType() {
        return TYPE;
    }
    
    /**
     * Convenience method used to easily dispatch events of this type.
     * 
     * @author Ashwin Arendse <ashwin.arendse@a24group.com>
     * @since  03 December 2012
     * 
     * @param source The component that dispatches the event.
     * @param callback - An Async Callback
     */
    public static void fire(HasHandlers source, AsyncCallback callback) {
        source.fireEvent(new ComplexInputFormConfirmationEvent(callback));
    }

    /**
     * Getter for the Async Callback
     * 
     * @return the callback
     */
    public AsyncCallback getCallback() {
        return callback;
    }
}
