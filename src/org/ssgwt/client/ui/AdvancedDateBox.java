/**
 * Copyright 2012 A24Group
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

/**
 * Package for all input control elements and components.
 */
package org.ssgwt.client.ui;

import java.util.Date;

import org.ssgwt.client.ui.datepicker.DateBox;
import org.ssgwt.client.ui.datepicker.SSDateBox;
import org.ssgwt.client.ui.datepicker.SSDatePicker;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.FocusEvent;

/**
 * AdvancedDatePicker
 * 
 * A date picker box that allows the developer to set a placeholder label inside it.
 * When in focus, the placeholder text is removed, when the focus fades, the
 * placeholder text will be replaced if no value was supplied.
 * 
 * @author Michael Barnard <michael.barnard@a24group.com>
 * @since 18 July 2012
 */
public class AdvancedDateBox extends DateBox implements AdvancedInputField<Date> {
    
    /**
     * The DateBoxHandler that replaces the origional handlers
     */
    protected DateBoxHandler handler = new DateBoxHandlerOveride();
    
    /**
     * DateBoxHandlerOveride
     * 
     * Internal class used to override the Focus and Blur handlers
     * that is put on the item
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 18 July 2012
     */
    public class DateBoxHandlerOveride extends DateBoxHandler {
        
        /**
         * Called when the method gains focus
         * 
         * @param event - The event causing the call
         */
        public void onFocus(FocusEvent event) {
            super.onFocus(event);
            hidePlaceholder();
        }

        /**
         * Called when the method loses focus
         * 
         * @param event - The event causing the call
         */
        public void onBlur(BlurEvent event) {
            super.onBlur(event);
            displayPlaceholder();
        }
    }
    
    /**
     * The text to be used as place holder inside the datepicker.
     */
    protected String placeholderText = "";

    /**
     * Style name to be applied to the datepicker when the placeholder is being
     * displayed. Defaults to 'placeholder'.
     */
    protected String placeholderStyleName = "placeholder";
    
    /**
     * Indicates whether the value for the datepicker is required to be filled in
     * when displayed on screen or not.
     */
    protected boolean required = false;
    
    /**
     * The style name to be added to the element if the element is marked as
     * required
     */
    protected String requiredStyleName = "required";
    
    /**
     * Class constructor
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 18 July 2012
     */
    public AdvancedDateBox() {
        super(new SSDatePicker(), null, SSDateBox.DEFAULT_FORMAT, false);
        addHandlers(handler);
    }
    
    /**
     * Sets the place holder text to be displayed when no value is given in the
     * text input.
     * 
     * @param placeholderText - The text to be used as the placeholder
     */
    public void setPlaceholder(String placeholderText) {
        this.placeholderText = placeholderText.trim();
        this.displayPlaceholder();
    }
    
    /**
     * Retrieves the place holder text to be displayed when no value is given in
     * the text input.
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 18 July 2012
     * 
     * @return The text to be used as the placeholder
     */
    public String getPlaceholderText() {
        return this.placeholderText;
    }

    /**
     * Changes the style name for the placeholder text in the datepicker.
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 18 July 2012
     */
    public void setPlaceholderStyleName(String placeholderStyleName) {
        this.placeholderStyleName = placeholderStyleName.trim();
    }
    
    /**
     * Returns the placeholder style name set on the datepicker.
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 18 July 2012
     * 
     * @return The placeholder styleName
     */
    public String getPlaceholderStyleName() {
        return this.placeholderStyleName;
    }

    /**
     * Returns whether the field is required or not
     * 
     * @return required or not
     */
    public boolean isRequired() {
        return required;
    }
    
    /**
     * Set the field to be either required or not. Will apply or remove the
     * required style name from the element dependant on the value provided.
     * 
     * @param required - Whether the field is required or not
     */
    public void setRequired(boolean required) {
        this.required = required;
        if (this.required) {
            this.addStyleName(this.getRequiredStyleName());
        } else {
            this.removeStyleName(this.getRequiredStyleName());
        }
    }
    
    /**
     * Retrieve the required style name
     * 
     * @return the requiredStyleName
     */
    public String getRequiredStyleName() {
        return requiredStyleName;
    }
    
    /**
     * Set the required style name
     * 
     * @param requiredStyleName - The required style name to set
     */
    public void setRequiredStyleName(String requiredStyleName) {
        this.removeStyleName(this.getRequiredStyleName());

        this.requiredStyleName = requiredStyleName;
        if (this.required) {
            this.addStyleName(this.getRequiredStyleName());
        }
    }
    
    /**
     * Displays the placeholder text only if no other value was provided. It
     * also adds the placeholder style name to the datepicker when adding the
     * placeholder text.
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 18 July 2012
     */
    public void displayPlaceholder() {
        if (super.getTextBox().getText().equals(getPlaceholderText()) || (super.getTextBox().getText().equals(""))) {
            super.getTextBox().setText(this.getPlaceholderText());
            super.getTextBox().addStyleName(this.getPlaceholderStyleName());
        }
    }
    
    /**
     * Hides the placeholder by removing the placeholder text from the datepicker
     * and also removing the placeholder style name from the datepicker.
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 18 July 2012
     */
    public void hidePlaceholder() {
        if (super.getTextBox().getText().equals(this.getPlaceholderText())) {
            super.getTextBox().setText("");
        }
        super.getTextBox().removeStyleName(this.getPlaceholderStyleName());
    }

    /**
     * Used to get the type of the input
     * 
     * @return The class type 
     */
    @Override
    public Class<Date> getReturnType() {
        return Date.class;
    }
} 
