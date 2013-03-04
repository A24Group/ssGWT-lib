package org.ssgwt.client.ui.form.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * Event dispatched to indicate that a field have been added on the ComplexInputForm
 * 
 * 
 * @author Alec Erasmus <alec.erasmus@a24group.com>
 * @since  1 March 2013
 */
public class ComplexInputFormFieldAddEvent extends GwtEvent<ComplexInputFormFieldAddEvent.ComplexInputFormFieldAddHandler> {

    /**
     * Type of the event.
     */
    public static Type<ComplexInputFormFieldAddHandler> TYPE = new Type<ComplexInputFormFieldAddHandler>();

    /**
     * Handler interface that should be implemented by components that wish to
     * handle the event when it is dispatched.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  1 March 2013
     */
    public interface ComplexInputFormFieldAddHandler extends EventHandler {

        /**
         * Method to be called when a {@link ComplexInputFormFieldAddEvent} is
         * being handled.
         * 
         * @author Alec Erasmus <alec.erasmus@a24group.com>
         * @since  1 March 2013
         * 
         * @param event The event being handled.
         */
        void onComplexInputFormFieldAdd(ComplexInputFormFieldAddEvent event);
    }

    /**
     * Function to add the handler to the class
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  1 March 2013
     * 
     * @param handler - The event be handled
     */
    public interface ComplexInputFormFieldAddHasHandlers extends HasHandlers {
        HandlerRegistration addComplexInputFormFieldAddHandler(ComplexInputFormFieldAddHandler handler);
    }

    /**
     * Class constructor.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  1 March 2013
     */
    public ComplexInputFormFieldAddEvent() {
    }

    /**
     * Dispatches the event to the given handler.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  1 March 2013
     * 
     * @param handler - The component that should handle the event.
     */
    @Override
    protected void dispatch(ComplexInputFormFieldAddHandler handler) {
        handler.onComplexInputFormFieldAdd(this);
    }

    /**
     * Returns the associated type of the event.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  1 March 2013
     * 
     * @return The associated type.
     */
    @Override
    public Type<ComplexInputFormFieldAddHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Returns the type of the event.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  1 March 2013
     * 
     * @return the Type of the event
     */
    public static Type<ComplexInputFormFieldAddHandler> getType() {
        return TYPE;
    }

    /**
     * Convenience method used to easily dispatch events of this type.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  1 March 2013
     * 
     * @param source The component that dispatches the event.
     */
    public static void fire(HasHandlers source) {
        source.fireEvent(new ComplexInputFormFieldAddEvent());
    }
}
