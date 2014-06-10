package org.ssgwt.client.ui.form;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * A info Label input field for the DynamicForm
 * 
 * This is to add additional info about the field above this one.
 * 
 * @author Ruan Naude <ruan.naude@a24group.com>
 * @since 06 June 2014
 *
 * @param <T> The object type the Dynamic form uses to get values from updates the value of the fields on
 */
public class InfoInputField<T> extends FlowPanel implements InputField<T, FlowPanel> {
    
    /**
     * The label for the info text
     */
    private Label infoText;

    /**
     * Class Constructor
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 06 June 2014
     * 
     * @param text - The text to display in as info
     */
    public InfoInputField(String text) {
        this(text, null);
    }
    
    /**
     * Class Constructor
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 06 June 2014
     * 
     * @param text - The text to display in as info
     * @param imageUrl - The url to the info icon to display
     */
    public InfoInputField(String text, String imageUrl) {
        super();
        if (imageUrl != null) {
            Image icon = new Image(imageUrl);
            icon.getElement().setAttribute("style", "vertical-align: top; max-width: 10%; padding-top: 3px;");
            this.add(icon);
        }
        
        infoText = new Label(text);
        infoText.getElement().setAttribute(
            "style",
            "display: inline-block;" +
            "padding-left: 5px;" +
            "max-width: 90%;" +
            "box-sizing: border-box;" +
            "-webkit-box-sizing: border-box;" +
            "-moz-box-sizing: border-box;"
        );
        
        this.add(infoText);
    }
    
    /**
     * This function will set the text to display as info on the input field.
     * 
     * @param text - The text to display in as info
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 06 June 2014
     */
    public void setInfoMessage(String text) {
        infoText.setText(text);
    }
    
    /**
     * Retrieve the value from the object that should be displayed on the input field
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 06 June 2014
     * 
     * @param object - The object the value should be retrieved from
     * 
     * @return The value that should be displayed on the field
     */
    @Override
    public FlowPanel getValue(T object) {
        return null;
    }

    /**
     * Sets the value from the input field on the object
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 06 June 2014
     * 
     * @param object - The object the value was retrieved from
     * @param value - The value that is currently being displayed on the input field
     */
    @Override
    public void setValue(T object, FlowPanel value) {
        //this input is only for display purpose so we do not implement this function
    }

    /**
     * Retrieve the flag that indicates whether the input field is required or not
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 06 June 2014
     * 
     * @return The flag that indicates whether the input field is required or not
     */
    @Override
    public boolean isRequired() {
        return false;
    }

    /**
     * Sets the flag that indicates whether the input field is required or not
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 06 June 2014
     * 
     * @param required - The flag that indicates whether the input field is required or not
     */
    @Override
    public void setRequired(boolean required) {
        //this input is only for display purpose so we do not implement this function
    }

    /**
     * Retrieve the input field as a widget
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 06 June 2014
     * 
     * @return The input field as a widget
     */
    @Override
    public Widget getInputFieldWidget() {
        return this;
    }
    
    /**
     * Set all the field as readOnly
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 06 June 2014
     * 
     * @param readOnly - Flag to indicate whether the field should be read only
     */
    @Override
    public void setReadOnly(boolean readOnly) {
        //this input is only for display purpose so we do not implement this function
    }
    
    /**
     * Retrieve the flag that indicates whether the field is read only
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 06 June 2014
     * 
     * @return The flag that indicates whether the field is read only
     */
    @Override
    public boolean isReadOnly() {
        return true;
    }
    
    /**
     * Retrieve the class type the input field returns
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 06 June 2014
     * 
     * @return The class type the input field returns
     */
    @Override
    public Class<FlowPanel> getReturnType() {
        return FlowPanel.class;
    }
}
