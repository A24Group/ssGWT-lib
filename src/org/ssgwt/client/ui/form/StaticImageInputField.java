package org.ssgwt.client.ui.form;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * An image input field for the DynamicForm
 * 
 * Used for display purposes only.
 * 
 * @author Michael Barnard <michael.barnard@a24group.com>
 * @since  14 May 2013
 *
 * @param <T> The object type the Dynamic form uses to get values from updates the value of the fields on
 */
public class StaticImageInputField<T> extends FlowPanel implements InputField<T, FlowPanel> {
    
    private String imageUrl = null;
    
    /**
     * Class Constructor
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  14 May 2013
     * 
     * @param imageURL - The url of the image that you wish to display
     */
    public StaticImageInputField(String imageURL) {
        super();
        this.imageUrl = imageURL;
        Image img = new Image(this.imageUrl);
        this.add(img);
    }

    /**
     * Getter for the return type of the item
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  14 May 2013
     * 
     * @return The class type the input field returns
     */
    @Override
    public Class<FlowPanel> getReturnType() {
        return FlowPanel.class;
    }

    /**
     * Get the value stored in the object
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  14 May 2013
     * 
     * @param object - The object the value should be retrieved from
     * 
     * @return null - No value can be assigned to image
     */
    @Override
    public FlowPanel getValue(T object) {
        return null;
    }

    /**
     * The setter for the value
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  14 May 2013
     * 
     * @param object - The object the value was retrieved from
     * @param value - The value that is currently being displayed on the input field
     */
    @Override
    public void setValue(T object, FlowPanel value) {
        // This can not be applied to this field.
    }

    /**
     * Used to check if the field is Required
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  14 May 2013
     * 
     * @retrun false - Image field is never required
     */
    @Override
    public boolean isRequired() {
        return false;
    }

    /**
     * Setter for the required state of the object
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  14 May 2013
     * 
     * @param required - Whether the field is required
     */
    @Override
    public void setRequired(boolean required) {
        // This can not be applied to this field.
    }

    /**
     * Get the entire field as a widget
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  14 May 2013
     * 
     * @return The entire field as a widget
     */
    @Override
    public Widget getInputFieldWidget() {
        return this;
    }

    /**
     * Set whether the field is read only
     *
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  14 May 2013
     * 
     * @param whether the field is read only
     */
    @Override
    public void setReadOnly(boolean readOnly) {
        // This can not be applied to this field.
    }

    /**
     * Getter for read only state of the widget
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  14 May 2013
     * 
     * @return false - The image field can not be read only
     */
    @Override
    public boolean isReadOnly() {
        return false;
    }
    
    /**
     * Set the url for the image
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  14 May 2013
     * 
     * @param imageUrl - The url of the image that you wish to display
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    /**
     * Getter for the url of the image that is set
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  14 May 2013
     * 
     * @return The url of the image that is set
     */
    public String getImageUrl() {
        return this.imageUrl;
    }
}
