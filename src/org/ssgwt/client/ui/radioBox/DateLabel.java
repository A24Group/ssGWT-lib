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

import java.util.Date;
import org.ssgwt.client.ui.form.InputField;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * This class is to be used simply for the adding of a label to a
 * radio button on the RadioBoxComponent to return a set date.
 * 
 * @author Ruan Naude <ruan.naude@a24group.com>
 * @since 03 Dec 2012
 */
public abstract class DateLabel<T> extends Label implements InputField<T, Date>, HasValue<Date> {
    
    public DateLabel() {
        super();
    }
    
    public DateLabel(String text) {
        super(text);
    }
    
    /**
     * Adds change handlers to the DateLabel
     * 
     * @param handler - The change handlers to add
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Date> handler) {
        return null;
    }

    /**
     * Set the value on the DateLabel
     * 
     * @param value - The date to set
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    @Override
    public void setValue(Date value) {
    }

    /**
     * Set the value on the DateLabel and whether it has events to fire
     * 
     * @param value - The value to set
     * @param fireEvents - Whether the value set has event to fire
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    @Override
    public void setValue(Date value, boolean fireEvents) {
    }

    /**
     * Getter for the return type of DateLabel
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     * 
     * @return The return type of DateLabel
     */
    @Override
    public Class<Date> getReturnType() {
        return Date.class;
    }

    /**
     * Returns the value of DateLabel
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     * 
     * @return The value of DateLabel
     */
    @Override
    public Date getValue(T object) {
        return null;
    }

    /**
     * Setter for the value of DateLabel
     * 
     * @param object - The object to set the value on
     * @param value - The value to be set
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    @Override
    public void setValue(T object, Date value) {
    }

    /**
     * Return whether the field is required
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     * 
     * @return Whether the DateLabel is required
     */
    @Override
    public boolean isRequired() {
        return false;
    }

    /**
     * Setter for if the field is required
     * 
     * @param required - Whether the DateLabel is required
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    @Override
    public void setRequired(boolean required) {
    }

    /**
     * Getter for the input field widget
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
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
     * @param readOnly - Whether the DateLabel is read only
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    @Override
    public void setReadOnly(boolean readOnly) {
    }

    /**
     * Whether the label is read only
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     * 
     * @return Whether the DateLabel is read only
     */
    @Override
    public boolean isReadOnly() {
        return false;
    }
}
