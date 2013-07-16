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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.ssgwt.client.i18n.SSDate;
import org.ssgwt.client.ui.form.DateInputField;
import org.ssgwt.client.ui.form.InputField;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
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
public class RadioBoxComponent<T, F> extends Composite implements HasValue<F>, ValueChangeHandler<Boolean> {

    /**
     * Instance of the UiBinder
     */
    private static Binder uiBinder = GWT.create(Binder.class);

    /**
     * The default group radio buttons will belong to
     */
    private static String DEFAULT_GROUP = "default";

    /**
     * The current number of radio button groups on the form
     */
    private static int currentNumberOfGroups = 0;

    /**
     * The current radio button group number
     */
    private int thisGroupId;

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
    protected HashMap<RadioButton, InputField<T,F>> radioButtonOptions;

    /**
     * Indicator for when an option is the first
     */
    private boolean firstOption = true;

    /**
     * The container for the whole radio box field item
     */
    @UiField
    FlowPanel radioBoxComponent;

    /**
     * Track the index of each radio button added
     */
    protected ArrayList<RadioButton> indexedOptions = new ArrayList<RadioButton>();

    /**
     * UiBinder interface for the composite
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    @SuppressWarnings("rawtypes")
    interface Binder extends UiBinder<Widget, RadioBoxComponent> {
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

        /**
         * The style for a radio button option
         */
        String radioButtonOption();

        /**
         * The style for a radio margin
         */
        String radioButtonMargin();
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
        this.thisGroupId = currentNumberOfGroups;
        this.resources = resources;
        this.resources.radioBoxComponentStyle().ensureInjected();
        this.initWidget(uiBinder.createAndBindUi(this));
        currentNumberOfGroups++;
        radioBoxComponent.addStyleName(resources.radioBoxComponentStyle().radioBoxComponent());
        radioButtonOptions = new HashMap<RadioButton, InputField<T,F>>();
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
        RadioButton radioButton = new RadioButton(DEFAULT_GROUP + this.thisGroupId);
        ((HasValue)option).addValueChangeHandler(this);
        radioButton.addValueChangeHandler(this);
        radioButton.addStyleName(resources.radioBoxComponentStyle().radioButtonMargin());
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
        radioButtonLayout.setHeight("28px");
        radioButtonLayout.setWidth("50%");
        radioButtonLayout.addStyleName(resources.radioBoxComponentStyle().radioButtonOption());

        if (firstOption) {
            firstOption = false;
            radioButton.setValue(true);
        }

        //add the radio button and its value to the radioButtonOptions array to retrieve selected value later
        radioButtonOptions.put(radioButton, option);

        indexedOptions.add(radioButton);

        //add layout to the main radio box flow panel
        radioBoxComponent.add(radioButtonLayout);
    }

    /**
     * Allows you to get an input field so that you can set a value directly on the object
     *
     * @author Neil Nienaber <neil.nienaber@a24group.com>
     * @since  05 December 2012
     *
     * @param field The input field which has been added to the redio group
     *
     * @return
     */
    protected HasValue<F> getFieldAsHasValue(InputField<T, F> field) {
        return (HasValue<F>)field;
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
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<F> handler) {
        return this.addHandler(handler, ValueChangeEvent.getType());
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
                return ((HasValue<F>)radioButtonOptions.get(radioButton)).getValue();
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
        if (value != null) {
            if (value instanceof SSDate) {
                Boolean found = false;
                Map.Entry<RadioButton, InputField<T,F>> dateInputField = null;
                for (Map.Entry<RadioButton, InputField<T,F>> entry : radioButtonOptions.entrySet()) {
                    if (value.equals(((HasValue<F>)entry.getValue()).getValue())) {
                        entry.getKey().setValue(true);
                        found = true;
                    }
                    if (entry.getValue() instanceof DateInputField) {
                        dateInputField = entry;
                    }
                }
                if (!found) {
                    ((HasValue<F>)dateInputField.getValue()).setValue(value);
                    dateInputField.getKey().setValue(true);
                }
            } else {
                for (Map.Entry<RadioButton, InputField<T,F>> entry : radioButtonOptions.entrySet()) {
                    if (((HasValue<F>)entry.getValue()).getValue() == value) {
                        entry.getKey().setValue(true);
                    }
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
        setValue(value);
        if (fireEvents) {
            ValueChangeEvent.fire(this, getValue());
        }
    }

    /**
     * Handles what happens when the selected radio button changes
     *
     * @param event The value change event
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    @Override
    public void onValueChange(ValueChangeEvent event) {
        ValueChangeEvent.fire(this, getValue());
    }
}