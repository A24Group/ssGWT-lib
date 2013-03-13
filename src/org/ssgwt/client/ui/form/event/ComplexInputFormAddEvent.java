package org.ssgwt.client.ui.form.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * Event dispatched to indicate that a field need to be added on the ComplexInputForm 
 * and that the current data in the current add field need to be added to the list of VOs.S
 * 
 * @author Alec Erasmus <alec.erasmus@a24group.com>
 * @since  22 November 2012
 */
public class ComplexInputFormAddEvent extends GwtEvent<ComplexInputFormAddEvent.ComplexInputFormAddHandler> {

    /**
     * Type of the event.
     */
    public static Type<ComplexInputFormAddHandler> TYPE = new Type<ComplexInputFormAddHandler>();

    /**
     * Handler interface that should be implemented by components that wish to
     * handle the event when it is dispatched.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     */
    public interface ComplexInputFormAddHandler extends EventHandler {
    
        /**
         * Method to be called when a {@link ComplexInputFormAddEvent} is
         * being handled.
         * 
         * @author Alec Erasmus <alec.erasmus@a24group.com>
         * @since  22 November 2012
         * 
         * @param event The event being handled.
         */
        void onComplexInputFormAdd(ComplexInputFormAddEvent event);
    }

    /**
     * Function to add the handler to the class
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @param handler - The event be handled
     */
    public interface ComplexInputFormAddHasHandlers extends HasHandlers {
        HandlerRegistration addComplexInputFormAddHandler(ComplexInputFormAddHandler handler);
    }

    /**
     * Class constructor.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     */
    public ComplexInputFormAddEvent() {
    }

    /**
     * Dispatches the event to the given handler.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @param handler - The component that should handle the event.
     */
    @Override
    protected void dispatch(ComplexInputFormAddHandler handler) {
        handler.onComplexInputFormAdd(this);
    }

    /**
     * Returns the associated type of the event.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @return The associated type.
     */
    @Override
    public Type<ComplexInputFormAddHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Returns the type of the event.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @return the Type of the event
     */
    public static Type<ComplexInputFormAddHandler> getType() {
        return TYPE;
    }

    /**
     * Convenience method used to easily dispatch events of this type.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @param source The component that dispatches the event.
     */
    public static void fire(HasHandlers source) {
        source.fireEvent(new ComplexInputFormAddEvent());
    }
}
