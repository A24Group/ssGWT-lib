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
package org.ssgwt.client.ui.radioBox;

import org.ssgwt.client.ui.form.InputField;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * This class is to be used simply for the adding of a label to a 
 * radio button on the RadioBoxComponent to return a set boolean.
 * 
 * @author Alec Erasmus <Alec.Erasmus@a24group.com>
 * @since 04 Dec 2012
 */
public abstract class BooleanLabel<T> extends Label implements InputField<T, Boolean>, HasValue<Boolean> {
    
    /**
     * Constructor
     * 
     * @author Alec Erasmus <Alec.Erasmus@a24group.com>
     * @since 04 Dec 2012
     */
    public BooleanLabel() {
        super();
    }
    
    /**
     * Constructor
     * 
     * @author Alec Erasmus <Alec.Erasmus@a24group.com>
     * @since  04 Dec 2012
     * 
     * @param text - The text dsplayed on the label
     */
    public BooleanLabel(String text) {
        super(text);
    }
    
    /**
     * Adds change handlers to the BooleanLabel
     * 
     * @param handler - The change handlers to add
     * 
     * @author Alec Erasmus <Alec.Erasmus@a24group.com>
     * @since  04 Dec 2012
     */
    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Boolean> handler) {
        return null;
    }

    /**
     * Set the value on the BooleanLabel
     * 
     * @param value - The value to set
     * 
     * @author Alec Erasmus <Alec.Erasmus@a24group.com>
     * @since  04 Dec 2012
     */
    @Override
    public void setValue(Boolean value) {}

    /**
     * Set the value on the BooleanLabel
     * 
     * @param value - The value to set
     * @param fireEvents - Whether the value set has event to fire
     * 
     * @author Alec Erasmus <Alec.Erasmus@a24group.com>
     * @since  04 Dec 2012
     */
    @Override
    public void setValue(Boolean value, boolean fireEvents) {
    }

    /**
     * Getter for the return type of BooleanLabel
     * 
     * @author Alec Erasmus <Alec.Erasmus@a24group.com>
     * @since 04 Dec 2012
     * 
     * @return The return type of BooleanLabel
     */
    @Override
    public Class<Boolean> getReturnType() {
        return Boolean.class;
    }

    /**
     * Returns the value of BooleanLabel
     * 
     * @author Alec Erasmus <Alec.Erasmus@a24group.com>
     * @since 04 Dec 2012
     * 
     * @return The value of BooleanLabel
     */
    @Override
    public Boolean getValue(T object) {
        return null;
    }

    /**
     * Setter for the value of BooleanLabel
     * 
     * @param object - The object to set the value on
     * @param value - The value to be set
     * 
     * @author Alec Erasmus <Alec.Erasmus@a24group.com>
     * @since 04 Dec 2012
     */
    @Override
    public void setValue(T object, Boolean value) {
    }

    /**
     * Return whether the field is required
     * 
     * @author Alec Erasmus <Alec.Erasmus@a24group.com>
     * @since 04 Dec 2012
     * 
     * @return Whether the BooleanLabel is required
     */
    @Override
    public boolean isRequired() {
        return false;
    }

    /**
     * Setter for if the field is required
     * 
     * @param required - Whether the BooleanLabel is required
     * 
     * @author Alec Erasmus <Alec.Erasmus@a24group.com>
     * @since 04 Dec 2012
     */
    @Override
    public void setRequired(boolean required) {
    }

    /**
     * Getter for the input field
     * 
     * @author Alec Erasmus <Alec.Erasmus@a24group.com>
     * @since 04 Dec 2012
     * 
     * @return The widget on which the input will happen
     */
    @Override
    public Widget getInputFieldWidget() {
        return this;
    }

    /**
     * Sets component to read only
     * 
     * @param readOnly - Whether the BooleanLabel is read only
     * 
     * @author Alec Erasmus <Alec.Erasmus@a24group.com>
     * @since 04 Dec 2012
     */
    @Override
    public void setReadOnly(boolean readOnly) {
    }

    /**
     * Whether the label is read only
     * 
     * @param Whether the BooleanLabel is read only
     * 
     * @author Alec Erasmus <Alec.Erasmus@a24group.com>
     * @since 04 Dec 2012
     */
    @Override
    public boolean isReadOnly() {
        return true;
    }

}
