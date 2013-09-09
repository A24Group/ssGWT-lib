package org.ssgwt.client.ui.form.event;

import java.util.HashMap;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * Event dispatched to indicate that an action was fired with in a commponent.
 * This is a generic event for a complext input
 *
 * @author Alec Erasmus <alec.erasmus@a24group.com>
 * @since  29 Aug 2013
 */
public class ComplexInputFormActionEvent extends GwtEvent<ComplexInputFormActionEvent.ComplexInputFormActionHandler> {

    /**
     * Type of the event.
     */
    public static Type<ComplexInputFormActionHandler> TYPE = new Type<ComplexInputFormActionHandler>();

    /**
     * The constant for the email Confirmation action
     */
    public final static String EMAIL_CONFIRMATION_ACTION = "emailConfirmationAction";

    /**
     * This is a HashMap that can be used as parameters
     */
    private final HashMap<String, String> params;

    /**
     * This is the action that was fired.
     */
    private final String action;

    /**
     * Handler interface that should be implemented by components that wish to
     * handle the event when it is dispatched.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  29 Aug 2013
     */
    public interface ComplexInputFormActionHandler extends EventHandler {

        /**
         * Method to be called when a {@link ComplexInputFormActionEvent} is
         * being handled.
         *
         * @author Alec Erasmus <alec.erasmus@a24group.com>
         * @since  29 Aug 2013
         *
         * @param event The event being handled.
         */
        void onComplexInputFormAction(ComplexInputFormActionEvent event);
    }

    /**
     * Function to add the handler to the class
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  29 Aug 2013
     *
     * @param handler - The event be handled
     */
    public interface ComplexInputFormActionHasHandlers extends HasHandlers {

        /**
         * The function that will add the handler to the class
         *
         * @param handler - The complex input form action handler being added
         *
         * @author Alec Erasmus <alec.erasmus@a24group.com>
         * @since  29 Aug 2013
         *
         * @return The handler HandlerRegistration
         */
        HandlerRegistration addComplexInputFormActionHandler(ComplexInputFormActionHandler handler);
    }

    /**
     * Class constructor.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  29 Aug 2013
     *
     * @param action - The action fired
     */
    public ComplexInputFormActionEvent(String action) {
        this(action, null);
    }

    /**
     * Class constructor.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  29 Aug 2013
     *
     * @param action - The action fired
     * @param params - Hashmap with the params for the action
     */
    public ComplexInputFormActionEvent(String action, HashMap<String, String> params) {
        this.action = action;
        this.params = params;
    }

    /**
     * Dispatches the event to the given handler.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  29 Aug 2013
     *
     * @param handler - The component that should handle the event.
     */
    @Override
    protected void dispatch(ComplexInputFormActionHandler handler) {
        handler.onComplexInputFormAction(this);
    }

    /**
     * Returns the associated type of the event.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  29 Aug 2013
     *
     * @return The associated type.
     */
    @Override
    public Type<ComplexInputFormActionHandler> getAssociatedType() {
        return TYPE;
    }

    /**
     * Returns the type of the event.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  29 Aug 2013
     *
     * @return the Type of the event
     */
    public static Type<ComplexInputFormActionHandler> getType() {
        return TYPE;
    }

    /**
     * Convenience method used to easily dispatch events of this type.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  29 Aug 2013
     *
     * @param source - The component that dispatches the event.
     * @param action - This is a generic action repersented with a string
     */
    public static void fire(HasHandlers source, String action) {
        source.fireEvent(new ComplexInputFormActionEvent(action));
    }

    /**
     * Convenience method used to easily dispatch events of this type.
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  29 Aug 2013
     *
     * @param source - The component that dispatches the event.
     * @param action - This is a generic action repersented with a string
     * @param params - This is parameters for the action
     */
    public static void fire(HasHandlers source, String action, HashMap<String, String> params) {
        source.fireEvent(new ComplexInputFormActionEvent(action, params));
    }

    /**
     * This is the getter for the action string that was fired
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  29 Aug 2013
     *
     * @return the action as a string
     */
    public String getAction() {
        return this.action;
    }

    /**
     * Getter for the params passed in by the constructor
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  02 September 2013
     *
     * @return Hashmap used for params
     */
    public HashMap<String, String> getParams() {
        return this.params;
    }
}
