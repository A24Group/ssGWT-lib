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
package org.ssgwt.client.ui.form;

import org.ssgwt.client.ui.radioBox.RadioBoxComponent;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

/**
 * A RadioBoxField input field for the DynamicForm
 * 
 * @author Ruan Naude <ruan.naude@a24group.com>
 * @since 03 Dec 2012
 */
public abstract class RadioBoxField<T, F> extends RadioBoxComponent<T, F> implements InputField<T, F>, HasValue<F> {

    /**
     * The flag that indicates whether the input field is required or not
     */
    private boolean required = false;
    
    /**
     * Whether the radio box field is read only
     */
    private boolean isReadOnly;
    
    /**
     * Class Constructor
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    public RadioBoxField() {
        super();
    }
    
    /**
     * Class Constructor
     * 
     * @param required - The flag that indicates whether the input field is required or not
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
     * @param resources - The resources to be used for the RadioBoxComponent
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
     * @param resources - The resources to be used for the RadioBoxComponent
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
     * Retrieve the value from the object that should the displayed on the input field
     * 
     * @param object - The object the value should be retrieved from
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     * 
     * @return The value that should be displayed on the field
     */
    @Override
    public abstract F getValue(T object);

    /**
     * Sets the value from the input field on the object
     * 
     * @param object - The object the value was retrieved from
     * @param value - The value that should be displayed on the input field
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    @Override
    public abstract void setValue(T object, F value);

    /**
     * Retrieve the flag that indicates whether the input field is required or not
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
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
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    @Override
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * Retrieve the input field as a widget
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     * 
     * @return The input field as a widget
     */
    @Override
    public Widget getInputFieldWidget() {
        return this;
    }
    
    /**
     * Sets the RadioBoxField to read only
     * 
     * @param readOnly - The flag that indicates whether the input field is read only or not
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    @Override
    public void setReadOnly(boolean readOnly) {
        this.isReadOnly = readOnly;
        for (RadioButton radioButton : radioButtonOptions.keySet()) {
            radioButton.setEnabled(!readOnly);
            radioButtonOptions.get(radioButton).setReadOnly(readOnly);
        }
    }

    /**
     * Determine whether the input field is read only or not
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     * 
     * @return The flag that indicates whether the input field is read only or not
     */
    @Override
    public boolean isReadOnly() {
        return this.isReadOnly;
    }
}
