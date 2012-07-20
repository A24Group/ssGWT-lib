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
package org.ssgwt.client.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * AdvancedPasswordBox
 * 
 * A password box that allows the developer to set a placeholder label inside it.
 * When in focus, the placeholder text is removed, when the focus fades, the
 * placeholder text will be replaced if no value was supplied.
 * 
 * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
 * @since 19 July 2012
 */
public class AdvancedPasswordBox extends Composite implements HasValue<String> {

    /**
     * The panel that holds the place holder and the password field
     */
    FlowPanel mainPanel = new FlowPanel();
    
    /**
     * The text box that displays the place holder text if no password is currently filled in
     */
    TextBox placeholder = new TextBox();
    
    /**
     * The box that will display if the user types a password
     */
    PasswordTextBox passwordBox = new PasswordTextBox();

    /**
     * Flag that indicates if the field is required
     */
    private boolean required;

    /**
     * The name of the style that is applied to the field if it is required
     */
    private String requiredStyleName;
    
    /**
     * Style name to be applied to the textbox when the placeholder is being
     * displayed. Defaults to 'placeholder'.
     */
    protected String placeholderStyleName = "placeholder";
    
    /**
     * Class constructor
     */
    public AdvancedPasswordBox() {
        mainPanel.add(placeholder);
        placeholder.setWidth("100%");
        mainPanel.add(passwordBox);
        passwordBox.setWidth("100%");
        passwordBox.setVisible(false);
        
        placeholder.addFocusHandler(new FocusHandler() {
            
            /**
             * Function called when the place holder gains focus
             * 
             * @param event - The event that should be handled
             */
            @Override
            public void onFocus(FocusEvent event) {
                placeholder.setVisible(false);
                Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand () {
                    public void execute () {
                        passwordBox.setFocus(true);
                    }
                });
                passwordBox.setVisible(true);
            }
        });
        
        passwordBox.addBlurHandler(new BlurHandler() {
            
            /**
             * Function called when the password box loses focus
             * 
             * @param event - The event that should be handled
             */
            @Override
            public void onBlur(BlurEvent event) {
                if (passwordBox.getText() == null || passwordBox.getText().equals("")) {
                    placeholder.setVisible(true);
                    passwordBox.setVisible(false);
                }
            }
        });
        
        initWidget(mainPanel);
    }

    /**
     * Adds a {@link ValueChangeEvent} handler.
     * 
     * @param handler the handler
     * @return the registration for the event
     */
    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        return passwordBox.addValueChangeHandler(handler);
    }

    /**
     * Gets this object's value.
     *
     * @return the object's value
     */
    @Override
    public String getValue() {
        return passwordBox.getValue();
    }

    /**
     * Sets this object's value without firing any events. This should be
     * identical to calling setValue(value, false).
     * <p>
     * It is acceptable to fail assertions or throw (documented) unchecked
     * exceptions in response to bad values.
     * <p>
     * Widgets must accept null as a valid value. By convention, setting a widget to 
     * null clears value, calling getValue() on a cleared widget returns null. Widgets
     * that can not be cleared (e.g. {@link CheckBox}) must find another valid meaning
     * for null input.
     *
     * @param value the object's new value
     */
    @Override
    public void setValue(String value) {
        passwordBox.setValue(value);
    }

    /**
     * Sets this object's value. Fires
     * {@link com.google.gwt.event.logical.shared.ValueChangeEvent} when
     * fireEvents is true and the new value does not equal the existing value.
     * <p>
     * It is acceptable to fail assertions or throw (documented) unchecked
     * exceptions in response to bad values.
     *
     * @param value the object's new value
     * @param fireEvents fire events if true and value is new
     */
    @Override
    public void setValue(String value, boolean fireEvents) {
        passwordBox.setValue(value, fireEvents);
    }
    
    /**
     * Sets the place holder text to be displayed when no value is given in the
     * text input.
     * 
     * @param placeholderText
     *            The text to be used as the placeholder
     */
    public void setPlaceholder(String placeholderText) {
        placeholder.setText(placeholderText.trim());
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
     * @param required
     *            Whether the field is required or not
     */
    public void setRequired(boolean required) {
        this.required = required;
        if (this.required) {
            placeholder.addStyleName(this.getRequiredStyleName());
            passwordBox.addStyleName(this.getRequiredStyleName());
        } else {
            placeholder.removeStyleName(this.getRequiredStyleName());
            passwordBox.removeStyleName(this.getRequiredStyleName());
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
     * @param requiredStyleName
     *            the required style name to set
     */
    public void setRequiredStyleName(String requiredStyleName) {
        this.removeStyleName(this.getRequiredStyleName());

        this.requiredStyleName = requiredStyleName;
        if (this.required) {
            placeholder.addStyleName(this.getRequiredStyleName());
            passwordBox.addStyleName(this.getRequiredStyleName());
        }
    }
    
    /**
     * Adds or removes a style name. This method is typically used to remove
     * secondary style names, but it can be used to remove primary stylenames as
     * well. That use is not recommended.
     * 
     * @param style the style name to be added or removed
     * @param add <code>true</code> to add the given style, <code>false</code> to
     *          remove it
     * @see #addStyleName(String)
     * @see #removeStyleName(String)
     */
    public void setStyleName(String style, boolean add) {
        placeholder.setStyleName(style, add);
        passwordBox.setStyleName(style, add);
    }
    
    /**
     * Clears all of the object's style names and sets it to the given style. You
     * should normally use {@link #setStylePrimaryName(String)} unless you wish to
     * explicitly remove all existing styles.
     * 
     * @param style the new style name
     * @see #setStylePrimaryName(String)
     */
    public void setStyleName(String style) {
        placeholder.setStyleName(style);
        placeholder.addStyleName(placeholderStyleName);
        passwordBox.setStyleName(style);
    }
    
    /**
     * Changes the style name for the placeholder text in the textbox.
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 05 June 2012
     */
    public void setPlaceholderStyleName(String placeholderStyleName) {
        this.placeholderStyleName = placeholderStyleName.trim();
    }

    /**
     * Returns the placeholder style name set on the textbox.
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 05 June 2012
     * 
     * @return The placeholder styleName
     */
    public String getPlaceholderStyleName() {
        return this.placeholderStyleName;
    }
    
    /**
     * Sets the container style name
     * 
     * @param containerStyleName The style to apply
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since 20 July 2012
     */
    public void setContainerStyle(String containerStyleName) {
        super.setStyleName(containerStyleName);
    }
    
    /**
     * Returns the container stylename
     * 
     * @author Lodewyk Duminy <lodewyk.duminy@a24group.com>
     * @since 20 July 2012
     * 
     * @return The container styleName
     */
    public String getContainerStyleName() {
        return super.getStyleName();
    }
}
