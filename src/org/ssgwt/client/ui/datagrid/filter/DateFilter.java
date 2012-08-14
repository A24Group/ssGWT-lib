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
package org.ssgwt.client.ui.datagrid.filter;

import org.ssgwt.client.ui.datagrid.filter.AbstractHeaderFilter.Criteria;
import org.ssgwt.client.ui.datagrid.filter.AbstractHeaderFilter.Resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DateFilter extends AbstractHeaderFilter {

    /**
     * Instance of the UiBinder
     */
    private static Binder uiBinder = GWT.create(Binder.class);
    
    /**
     * Holds an instance of the default resources
     */
    private static DateFilterResources DEFAULT_RESOURCES;
    
    /**
     * Holds an instance of resources
     */
    private DateFilterResources resources;
    
    /**
     * The title label
     */
    @UiField
    Label titleLabel;
    
    /**
     * The check box on the filter popup
     */
    @UiField
    CheckBox checkBox;
    
    /**
     * The icon that clears a the filter criteria
     */
    @UiField
    Image removeFilterIcon;
    
    /**
     * The apply button
     */
    @UiField
    Button applyButton;
    
    /**
     * UiBinder interface for the composite
     * 
     * @author Johannes Gryffenberg
     * @since 5 July 2012
     */
    interface Binder extends UiBinder<Widget, DateFilter> {
    }
    
    /**
     * The resources interface for the text filter
     * 
     * @author Johannes Gryffenberg
     * @since 5 July 2012
     */
    public interface DateFilterResources extends Resources {
        
        /**
         * Retrieves an implementation of the Style interface generated using the specified css file
         * 
         * @return An implementation of the Style interface
         */
        @Source("DateFilter.css")
        Style textFilterStyle();
    }
    
    /**
     * The css resource for the text filter
     * 
     * @author Johannes Gryffenberg
     * @since 5 July 2012
     */
    public interface Style extends CssResource {
        
        /**
         * The style for the panel that contains all the elements on the text filter
         * 
         * @return The name of the compiled style
         */
        String textFilterStyle();
        
        /**
         * The style for the speech bubble arrow border
         * 
         * @return The name of the compiled style
         */
        String arrowBorderStyle();
        
        /**
         * The style for the speech bubble arrow
         * 
         * @return The name of the compiled style
         */
        String arrowStyle();
        
        /**
         * The style for the title
         * 
         * @return The name of the compiled style
         */
        String titleStyle();
        
        /**
         * The style the sets the position of the remove filter icon
         * 
         * @return The name of the compiled style
         */
        String removeFilterIconStyle();
        
        /**
         * The style for the container that holds the checkbox
         * 
         * @return The name of the compiled style
         */
        String checkBoxContainerStyle();
        
        /**
         * The style for the checkbox
         * 
         * @return The name of the compiled style
         */
        String checkBoxStyle();
        
        /**
         * The style for the label of the checkbox
         * 
         * @return The name of the compiled style
         */
        String checkBoxLabelStyle();
        
        /**
         * The style for the text box label
         * 
         * @return The name of the compiled style
         */
        String textBoxLabelStyle();
        
        /**
         * The style for the container that holds the text box
         * 
         * @return The name of the compiled style
         */
        String textBoxStyleContainer();
        
        /**
         * The style for the text box
         * 
         * @return The name of the compiled style
         */
        String textBoxStyle();
        
        /**
         * The style for the container that holds the apply button
         * 
         * @return The name of the compiled style
         */
        String applyButtonContainer();
        
        /**
         * The style for the apply button
         * 
         * @return The name of the compiled style
         */
        String applyButton();
        
        /**
         * The style for the apply button when it is selected
         * 
         * @return The name of the compiled style
         */
        String applyButtonDown();
    }
    
    /**
     * The criteria object that will be used to represent the data enter by the user on the text filter
     * 
     * @author Johannes Gryffenberg
     * @since 5 July 2012
     */
    public static class DateFilterCriteria extends Criteria {
        
        /**
         * Flag to indicate if the user is looking for empty entries only
         */
        private boolean findEmptyEntriesOnly;
        
        /**
         * Retrieve the flag that indicates if the user is looking for empty entries only
         * 
         * @return The flag that indicates if the user is looking for empty entries only
         */
        public boolean isFindEmptyEntriesOnly() {
            return findEmptyEntriesOnly;
        }

        /**
         * Sets the flag that the user is looking for empty entries only or not
         * 
         * @param findEmptyEntriesOnly - The new value for the flag value
         */
        public void setFindEmptyEntriesOnly(boolean findEmptyEntriesOnly) {
            this.findEmptyEntriesOnly = findEmptyEntriesOnly;
        }
        
    }
    
    /**
     * The default class constructor
     */
    public DateFilter() {
        this(getDefaultResources());
    }
    
    /**
     * Class constructor that takes a custom resources class
     * 
     * @param resources - The resources the text filter should use
     */
    public DateFilter(DateFilterResources resources) {
        super(true);
        this.resources = resources;
        this.resources.textFilterStyle().ensureInjected();
        this.setStyleName("");
        this.setWidget(uiBinder.createAndBindUi(this));
        setCriteria(new DateFilterCriteria());
        addRemoveIconEventHandlers();
        addApplyButtonEventHandlers();
        addCheckBoxEventHandlers();
    }
    
    /**
     * Adds event handlers to the checkbox on the TextFilter
     */
    private void addCheckBoxEventHandlers() {
        this.checkBox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            
            /**
             * The function that will be called if the value on the check box changes
             * 
             * @param event The event that should be handled
             */
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                //TODO: Handle logic for the search empty fields check box
            }
        });
    }

    /**
     * Function that adds the required events handlers to the apply button
     */
    private void addApplyButtonEventHandlers() {
        this.applyButton.addMouseDownHandler(new MouseDownHandler() {
            
            /**
             * The event that is call if the apply button fires a mouse down event
             * 
             * @param event - The mouse down event that should be handled
             */
            @Override
            public void onMouseDown(MouseDownEvent event) {
                applyButton.addStyleName(resources.textFilterStyle().applyButtonDown());
            }
        });
        
        this.applyButton.addMouseUpHandler(new MouseUpHandler() {
            
            /**
             * The event that is call if the apply button fires a mouse up event
             * 
             * @param event - The mouse up event that should be handled
             */
            @Override
            public void onMouseUp(MouseUpEvent event) {
                applyButton.removeStyleName(resources.textFilterStyle().applyButtonDown());
                closeFilterPopup(false);
            }
        });
        
        this.applyButton.addMouseOutHandler(new MouseOutHandler() {
            
            /**
             * The event that is call if the apply button fires a mouse out event
             * 
             * @param event - The mouse out event that should be handled
             */
            @Override
            public void onMouseOut(MouseOutEvent event) {
                applyButton.removeStyleName(resources.textFilterStyle().applyButtonDown());
            }
        });
    }

    /**
     * Adds the required events handlers to the remove filter icon
     */
    private void addRemoveIconEventHandlers() {
        this.removeFilterIcon.addMouseOverHandler(new MouseOverHandler() {
            
            /**
             * The event that is call if the remove criteria icon fires a mouse over event
             * 
             * @param event - The mouse over event that should be handled
             */
            @Override
            public void onMouseOver(MouseOverEvent event) {
                removeFilterIcon.setResource(resources.removeFilterIconOver());
            }
        });
        
        this.removeFilterIcon.addMouseOutHandler(new MouseOutHandler() {
            
            /**
             * The event that is call if the remove criteria icon fires a mouse out event
             * 
             * @param event - The mouse out event that should be handled
             */
            @Override
            public void onMouseOut(MouseOutEvent event) {
                removeFilterIcon.setResource(resources.removeFilterIconUp());
            }
        });
        
        this.removeFilterIcon.addMouseDownHandler(new MouseDownHandler() {
            
            /**
             * The event that is call if the remove criteria icon fires a mouse down event
             * 
             * @param event - The mouse down event that should be handled
             */
            @Override
            public void onMouseDown(MouseDownEvent event) {
                removeFilterIcon.setResource(resources.removeFilterIconDown());
            }
        });
        
        this.removeFilterIcon.addMouseUpHandler(new MouseUpHandler() {
            
            /**
             * The event that is call if the remove criteria icon fires a mouse up event
             * 
             * @param event - The mouse up event that should be handled
             */
            @Override
            public void onMouseUp(MouseUpEvent event) {
                removeFilterIcon.setResource(resources.removeFilterIconOver());
                clearFields();
            }
        });
    }
    
    /**
     * Create an instance on the default resources object if it the
     * DEFAULT_RESOURCES variable is null if not it just return the object in
     * the DEFAULT_RESOURCES variable
     * 
     * @return the default resource object
     */
    private static DateFilterResources getDefaultResources() {
        if (DEFAULT_RESOURCES == null) {
            DEFAULT_RESOURCES = GWT.create(DateFilterResources.class);
        }
        return DEFAULT_RESOURCES;
    }
    
    @Override
    public void setTitle(String title) {
        this.titleLabel.setText(title);
    }

    /**
     * Getter for the resources instance
     * 
     * @return The resources
     */
    public DateFilterResources getResources() {
        return this.resources;
    }
    
    @Override
    public DateFilterCriteria getCriteria() {
        return (DateFilterCriteria)this.filterCirteria;
    }

    @Override
    protected boolean checkFilterActive() {
        if (getCriteria().isFindEmptyEntriesOnly()) {
            return true;
        }
        // TODO: Add Logic for from and to field
        return false;
    }

    @Override
    protected void updateCriteriaObject() {
        getCriteria().setFindEmptyEntriesOnly(checkBox.getValue());
        // TODO: Add Logic for from and to field
    }

    @Override
    protected void setCriteriaObjectEmpty() {
        getCriteria().setFindEmptyEntriesOnly(false);
        // TODO: Add Logic for from and to field
        
    }

    @Override
    protected void updateFieldData() {
        checkBox.setValue(getCriteria().isFindEmptyEntriesOnly());
        // TODO: Add Logic for from and to field
    }

    @Override
    protected void clearFields() {
        checkBox.setValue(false);
        // TODO: Add Logic for from and to field
    }

    /**
     * Retrieves the check box that is displayed on the TextFilter.
     * This function is protected as it is only used by test cases.
     * 
     * @return instance of the check box that is displayed on the TextFilter
     */
    protected CheckBox getCheckBox() {
        return checkBox;
    }
    
    /**
     * Retrieves the title label that is displayed on the TextFilter.
     * This function is protected as it is only used by test cases.
     * 
     * @return instance of the title label that is displayed on the TextFilter
     */
    protected Label getTitleLabel() {
        return titleLabel;
    }
}
