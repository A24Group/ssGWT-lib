package org.ssgwt.client.ui.form;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * A Check box input field for the DynamicForm
 *
 * @author Alec Erasmus<alec.erasmus@a24group.com>
 * @since  10 May 2013
 *
 * @param <T> The object type the Dynamic form uses to get values from updates the value of the fields on
 */
public abstract class CheckBoxInputField<T> extends CheckBox implements InputField<T, Boolean> {

    /**
     * Flag used to indicate if the field is required
     */
    private boolean required = false;

    /**
     * Class Constructor
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  10 May 2013
     *
     * @param text - The text that's displayed with the check box
     */
    public CheckBoxInputField(String text) {
        this(text, false);
    }

    /**
     * Class Constructor
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  10 May 2013
     */
    public CheckBoxInputField() {
        super();
    }

    /**
     * Class Constructor
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  10 May 2013
     *
     * @param text - The text that's displayed with the check box
     * @param required - If the field is required or not
     */
    public CheckBoxInputField(String text, boolean required) {
        super(text);
        setRequired(required);
    }

    /**
     * Retrieve the value from the object that should be displayed on the input field
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  10 May 2013
     *
     * @param object - The object the value should be retrieved from
     *
     * @return The value that should be displayed on the field
     */
    @Override
    public abstract Boolean getValue(T object);

    /**
     * Sets the value from the input field on the object
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  10 May 2013
     *
     * @param object - The object the value was retrieved from
     * @param value - The value that is currently being displayed on the input field
     */
    @Override
    public abstract void setValue(T object, Boolean value);

    /**
     * Retrieve the flag that indicates whether the input field is required or not
     * Cannot be applied to this field
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  10 May 2013
     *
     * @return The flag that indicates whether the input field is required or not
     */
    @Override
    public boolean isRequired() {
        return this.required;
    }

    /**
     * Sets the flag that indicates whether the input field is required or not
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  10 May 2013
     *
     * @param required - The flag that indicates whether the input field is required or not
     */
    @Override
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * Retrieve the input field as a widget
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  10 May 2013
     *
     * @return The input field as a widget
     */
    @Override
    public Widget getInputFieldWidget() {
        return this;
    }

    /**
     * Set the field as readOnly
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  10 May 2013
     *
     * @param readOnly - Flag to indicate whether the field should be read only
     */
    @Override
    public void setReadOnly(boolean readOnly) {
        // Check box don't have a read only.
        super.setEnabled(!readOnly);
    }

    /**
     * Retrieve the flag that indicates whether the field is read only/enabled.
     * Read only and enabled is the same for a check box
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  10 May 2013
     *
     * @return The flag that indicates whether the field is read only/enabeld
     */
    @Override
    public boolean isReadOnly() {
        return super.isEnabled();
    }

    /**
     * Retrieve the class type the input field returns
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  10 May 2013
     *
     * @return The class type the input field returns
     */
    @Override
    public Class<Boolean> getReturnType() {
        return Boolean.class;
    }
}
