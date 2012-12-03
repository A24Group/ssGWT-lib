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
import java.util.List;
import java.util.Map;

import org.ssgwt.client.ui.form.InputField;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

/**
 * This class if used for the creation of the radio box component
 * 
 * @author Ruan Naude <ruan.naude@a24group.com>
 * @since 03 Dec 2012
 */
public class RadioBoxComponent<T, F> extends Composite implements HasValue<F> {
    
    /**
     * Instance of the UiBinder
     */
    private static Binder uiBinder = GWT.create(Binder.class);
    
    /**
     * The default group radio buttons will belong to
     */
    private static String DEFAULT_GROUP;
    
    /**
     * The default resource to use for the RadioBoxComponent class
     */
    private static RadioBoxComponentResources DEFAULT_RESOURCES;
    
    /**
     * The resource to use for the RadioBoxComponent class
     */
    private RadioBoxComponentResources resources;
    
    /**
     * The list of radio button values
     */
    private HashMap<RadioButton, InputField<T,F>> radioButtonOptions = new HashMap<RadioButton, InputField<T,F>>();
    
    /**
     * The container for the whole radio box field item
     */
    @UiField
    FlowPanel radioBoxComponent;
    
    /**
     * UiBinder interface for the composite
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    @SuppressWarnings("rawtypes")
    interface Binder extends UiBinder<Widget, RadioBoxComponent> {
        // TODO make sure @SuppressWarnings("rawtypes") is best way to go about this
    }

    /**
     * A ClientBundle that provides style for this widget.
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    public interface RadioBoxComponentResources extends ClientBundle {
        
        /**
         * The style source to be used in this widget
         */
        @Source("RadioBoxComponent.css")
        Style radioBoxComponentStyle();
    }
    
    /**
     * The Css style recourse items to use in this widget
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    public interface Style extends CssResource {
        
        /**
         * The style for the main container of the radio box component
         */
        String radioBoxComponent();
    }
    
    /**
     * Class constructor for the radio box field
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    public RadioBoxComponent() {
        this(getDefaultResources());
    }
    
    /**
     * Class constructor for the radio box field with a recourse
     * 
     * @param resources The resources to be used for the RadioBoxComponent
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    public RadioBoxComponent(RadioBoxComponentResources resources) {
        this.resources = resources;
        this.resources.radioBoxComponentStyle().ensureInjected();
        this.initWidget(uiBinder.createAndBindUi(this));
        radioBoxComponent.addStyleName(resources.radioBoxComponentStyle().radioBoxComponent());
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
        //Create layout panel to hold radio button and value
        LayoutPanel radioButtonLayout = new LayoutPanel();
        RadioButton radioButton = new RadioButton(DEFAULT_GROUP);
        FlowPanel radioButtonValue = new FlowPanel();
        radioButtonValue.add(option.getInputFieldWidget());
        
        //add the radio button to the layout panel
        radioButtonLayout.add(radioButton);
        radioButtonLayout.setWidgetLeftWidth(radioButton, 0, Unit.PX, 20, Unit.PX);
        radioButtonLayout.setWidgetTopBottom(radioButton, 0, Unit.PX, 0, Unit.PX);
        
        //add the radio button's value to the layout panel
        radioButtonLayout.add(radioButtonValue);
        radioButtonLayout.setWidgetLeftRight(radioButtonValue, 20, Unit.PX, 0, Unit.PX);
        radioButtonLayout.setWidgetTopBottom(radioButtonValue, 0, Unit.PX, 0, Unit.PX);
        
        //set default style on radio option
        radioButtonLayout.setHeight("20px");
        radioButtonLayout.setWidth("50%");
        
        //add the radio button and its value to the radioButtonOptions array to retrieve selected value later
        radioButtonOptions.put(radioButton, option);
        
        //add layout to the main radio box flow panel
        radioBoxComponent.add(radioButtonLayout);
    }
    
    /**
     * This function will check of there is already a default resource to
     * use for the radio box component and if not create a default resource
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     * 
     * @return The default resource for the LeftMenuItem
     */
    private static RadioBoxComponentResources getDefaultResources() {
        if (DEFAULT_RESOURCES == null) {
            DEFAULT_RESOURCES = GWT.create(RadioBoxComponentResources.class);
        }
        return DEFAULT_RESOURCES;
    }
    
    /**
     * Adds change handlers to the radio box component
     * 
     * @param handler - The change handlers to add
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    @Override
    public HandlerRegistration addValueChangeHandler(
            ValueChangeHandler<F> handler) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * The value to be returned from the selected radio button on the 
     * radio button component.
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     * 
     * @return The value of the selected radio button
     */
    @Override
    public F getValue() {
        for (RadioButton radioButton : radioButtonOptions.keySet()) {
            if(radioButton.getValue()){
                if (radioButtonOptions.get(radioButton).getReturnType().equals(Date.class)) {
                    return (F)((HasValue<Date>)radioButtonOptions.get(radioButton)).getValue();
                } else if (radioButtonOptions.get(radioButton).getReturnType().equals(String.class)) {
                    return (F)((HasValue<String>)radioButtonOptions.get(radioButton)).getValue();
                } else if (radioButtonOptions.get(radioButton).getReturnType().equals(Boolean.class)) {
                    return (F)((HasValue<Boolean>)radioButtonOptions.get(radioButton)).getValue();
                } else if (radioButtonOptions.get(radioButton).getReturnType().equals(List.class)) {
                    return (F)((HasValue<List>)radioButtonOptions.get(radioButton)).getValue();
                }
            }
        }
        return null;
    }

    /**
     * Sets the selected radio button on the radio box component
     * 
     * @param value The selected value
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    @Override
    public void setValue(F value) {
        if (value.equals(Date.class)) {
            Boolean found = false;
            for (Map.Entry<RadioButton, InputField<T,F>> entry : radioButtonOptions.entrySet()) {
                if ((F)((HasValue<Date>)entry.getValue()).getValue() == value) {
                    entry.getKey().setValue(true);
                }
            }
            if (!found) {
                //TODO find the Datepicker and set date and radio selected.
            }
        } else if (value.equals(String.class)) {
            for (Map.Entry<RadioButton, InputField<T,F>> entry : radioButtonOptions.entrySet()) {
                if ((F)((HasValue<String>)entry.getValue()).getValue() == value) {
                    entry.getKey().setValue(true);
                }
            }
        } else if (value.equals(List.class)) {
            for (Map.Entry<RadioButton, InputField<T,F>> entry : radioButtonOptions.entrySet()) {
                if ((F)((HasValue<List>)entry.getValue()).getValue() == value) {
                    entry.getKey().setValue(true);
                }
            }
        } else if (value.equals(Boolean.class)) {
            for (Map.Entry<RadioButton, InputField<T,F>> entry : radioButtonOptions.entrySet()) {
                if ((F)((HasValue<Boolean>)entry.getValue()).getValue() == value) {
                    entry.getKey().setValue(true);
                }
            }
        }
    }

    /**
     * Sets the selected radio button on the radio box component
     * and whether it has n event to fire
     * 
     * @param value - The selected value
     * @param fireEvents - whether the selected item has n event to fire
     * 
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    @Override
    public void setValue(F value, boolean fireEvents) {
        // TODO Auto-generated method stub
    }
}