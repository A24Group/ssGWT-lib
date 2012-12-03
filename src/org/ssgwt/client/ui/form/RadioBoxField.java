package org.ssgwt.client.ui.form;

import org.ssgwt.client.ui.radioBox.RadioBoxComponent;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

/**
 * A RadioBoxComponent input field for the DynamicForm
 * 
 * @author Ruan Naude <ruan.naude@a24group.com>
 * @since 03 Dec 2012
 */
public abstract class RadioBoxField<T, F> extends RadioBoxComponent<T, F> implements InputField<T, F>, HasValue<F> {

    /**
     * The value from the object that should the displayed on the input field
     */
    private boolean required = false;
    
    /**
     * Add an option to the list of radio button options
     * 
     * @param option - The option to add to the radio button list
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    public void addOption(InputField<T, F> option) {
        super.addOption(option);
    }
    
    /**
     * Class Constructor
     */
    public RadioBoxField() {
        super();
    }
    
    /**
     * Class Constructor
     * 
     * @param required - The value from the object that should the displayed on the input field
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    public RadioBoxField(boolean required) {
        super();
        setRequired(required);
    }
    
    /**
     * Class constructor for the radio box field with a recourse
     * 
     * @param resources The resources to be used for the RadioBoxComponent
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    public RadioBoxField(RadioBoxComponentResources resources) {
        super(resources);
    }
    
    /**
     * Class constructor for the radio box field with a recourse and requirement
     * 
     * @param resources The resources to be used for the RadioBoxComponent
     * @param required - The value from the object that should the displayed on the input field
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    public RadioBoxField(RadioBoxComponentResources resources, boolean required) {
        super(resources);
        setRequired(required);
    }
    
    /**
     * Retrieve the value from the object that should the displayed on the input field
     * 
     * @param object - The object the value should be retrieved from
     * 
     * @return The value that should be displayed ob the field
     */
    @Override
    public abstract F getValue(T object);

    /**
     * Sets the value from the input field on the object
     * 
     * @param object - The object the value was retrieved from
     * @param value - The value that is currently being displayed on the input field
     */
    @Override
    public abstract void setValue(T object, F value);

    /**
     * Retrieve the flag that indicates whether the input field is required or not
     * 
     * @return The flag that indicates whether the input field is required or not
     */
    @Override
    public boolean isRequired() {
        return required;
    }

    /**
     * Sets the flag that indicates whether the input field is required or not
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
     * @return The input field as a widget
     */
    @Override
    public Widget getInputFieldWidget() {
        return this;
    }
}
