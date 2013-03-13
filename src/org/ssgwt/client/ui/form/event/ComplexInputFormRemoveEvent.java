package org.ssgwt.client.ui.form.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * Event dispatched to indicate that a field need to be removed on the ComplexInputForm.
 * 
 * @author Alec Erasmus <alec.erasmus@a24group.com>
 * @since  22 November 2012
 */
public class ComplexInputFormRemoveEvent extends
        GwtEvent<ComplexInputFormRemoveEvent.ComplexInputFormRemoveHandler> {

    /**
     * Type of the event.
     */
    public static Type<ComplexInputFormRemoveHandler> TYPE = new Type<ComplexInputFormRemoveHandler>();
    
    /**
     * The VO to be removed.
     */
    private Object removeObjectVO;
    
    /**
     * The Field to be removed.
     */
    private Object removeObjectField;

    /**
     * Handler interface that should be implemented by components that wish to
     * handle the event when it is dispatched.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     */
    public interface ComplexInputFormRemoveHandler extends EventHandler {
        
        /**
         * Method to be called when a {@link ComplexInputFormAddEvent} is
         * being handled.
         * 
         * @author Alec Erasmus <alec.erasmus@a24group.com>
         * @since  22 November 2012
         * 
         * @param event The event being handled.
         */
        void onComplexInputFormRemove(ComplexInputFormRemoveEvent event);
    }

    /**
     * Function to add the handler to the class
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @param handler - The event be handled
     */
    public interface ComplexInputFormRemoveHasHandlers extends HasHandlers {
        HandlerRegistration addComplexInputFormRemoveHandler(ComplexInputFormRemoveHandler handler);
    }

    /**
     * Class constructor.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @param removeObjectVO - The VO to be removed.
     * @param removeObjectField - The Field to be removed.
     */
    public ComplexInputFormRemoveEvent(Object removeObjectVO, Object removeObjectField) {
        this.removeObjectVO = removeObjectVO;
        this.removeObjectField = removeObjectField;
    }

    /**
     * Getter for the removeObjectVO
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @return the VO to removed
     */
    public Object getRemoveObjectVO() {
        return removeObjectVO;
    }
    
    /**
     * Getter for the removeObjectField
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @return the field to removed
     */
    public Object getRemoveObjectField() {
        return removeObjectField;
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
    protected void dispatch(ComplexInputFormRemoveHandler handler) {
        handler.onComplexInputFormRemove(this);
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
    public Type<ComplexInputFormRemoveHandler> getAssociatedType() {
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
    public static Type<ComplexInputFormRemoveHandler> getType() {
        return TYPE;
    }
    
    /**
     * Convenience method used to easily dispatch events of this type.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @param source The component that dispatches the event.
     * @param removeObjectVO - The VO to be removed.
     * @param removeObjectField - The Field to be removed.
     */
    public static void fire(HasHandlers source, Object removeObjectVO, Object removeObjectField) {
        source.fireEvent(new ComplexInputFormRemoveEvent(removeObjectVO, removeObjectField));
    }
}
