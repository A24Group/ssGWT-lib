package org.ssgwt.client.ui.form;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * A line input field for the DynamicForm
 * 
 * Used for display purposes only.
 * 
 * @author Alec Erasmus<alec.erasmus@a24group.com>
 * @since 20 Feb 2013
 *
 * @param <T> The object type the Dynamic form uses to get values from updates the value of the fields on
 */
public class LineInputField<T> extends FlowPanel implements InputField<T, FlowPanel> {
    
    /**
     * Class Constructor
     * 
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 20 Feb 2013
     */
    public LineInputField() {
        super();
    }
    
    /**
     * Class Constructor
     * 
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 20 Feb 2013
     * 
     * @param required - The value from the object that should the displayed on the input field
     */
    public LineInputField(boolean required) {
        super();
        setRequired(required);
    }
    
    /**
     * Retrieve the value from the object that should the displayed on the input field
     * 
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 20 Feb 2013
     * 
     * @param object - The object the value should be retrieved from
     * 
     * @return The value that should be displayed ob the field
     */
    @Override
    public FlowPanel getValue(T object) {
        return null;
    }

    /**
     * Sets the value from the input field on the object
     * 
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 20 Feb 2013
     * 
     * @param object - The object the value was retrieved from
     * @param value - The value that is currently being displayed on the input field
     */
    @Override
    public void setValue(T object, FlowPanel value) {
    }

    /**
     * Retrieve the flag that indicates whether the input field is required or not
     * Cannot be applied to this field
     * 
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 20 Feb 2013
     * 
     * @return The flag that indicates whether the input field is required or not
     */
    @Override
    public boolean isRequired() {
        return false;
    }

    /**
     * Sets the flag that indicates whether the input field is required or not
     * Cannot be applied to this field
     * 
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 20 Feb 2013
     * 
     * @param required - The flag that indicates whether the input field is required or not
     */
    @Override
    public void setRequired(boolean required) {
    }

    /**
     * Retrieve the input field as a widget
     * 
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 20 Feb 2013
     * 
     * @return The input field as a widget
     */
    @Override
    public Widget getInputFieldWidget() {
        return this;
    }
    
    /**
     * Set all the field as readOnly
     * Cannot be applied to this field
     * 
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 20 Feb 2013
     * 
     * @param readOnly - Flag to indicate whether the field should be read only
     */
    @Override
    public void setReadOnly(boolean readOnly) {
        // This can not be applyed to this field.
    }
    
    /**
     * Retrieve the flag that indicates whether the field is read only
     * 
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 20 Feb 2013
     * 
     * @return The flag that indicates whether the field is read only
     */
    @Override
    public boolean isReadOnly() {
        // This can not be applyed to this field.
        return true;
    }
    
    /**
     * Retrieve the class type the input field returns
     * 
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since 20 Feb 2013
     * 
     * @return The class type the input field returns
     */
    @Override
    public Class<FlowPanel> getReturnType() {
        return FlowPanel.class;
    }
}
