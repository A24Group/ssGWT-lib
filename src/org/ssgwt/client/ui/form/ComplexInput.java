package org.ssgwt.client.ui.form;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;


import org.ssgwt.client.ui.form.event.ComplexInputFormRemoveEvent;
import org.ssgwt.client.ui.form.event.ComplexInputFormAddEvent;

/**
 * Abstract Complex Input is an input field that contains a dynamic form and a 
 * view ui that is binded and used to display the view state of the field
 * 
 * @author Alec Erasmus <alec.erasmus@a24group.com>
 * @since  22 November 2012
 *
 * @param <T> is the type if data used like a VO
 */
public abstract class ComplexInput<T> extends Composite 
    implements 
        HasValue<T>, 
        InputField<T, T>, 
        ComplexInputFormRemoveEvent.ComplexInputFormRemoveHasHandlers, 
        ComplexInputFormAddEvent.ComplexInputFormAddHasHandlers 
    {
    
    /**
     * This is the main panel.
     */
    protected FlowPanel mainPanel = new FlowPanel();
    
    /**
     * The panel that holds the dynamicForm.
     */
    protected FlowPanel dynamicFormPanel = new FlowPanel();
    
    /**
     * The panel that holds the view.
     * 
     * The ui binder is added to it
     */
    protected FlowPanel viewPanel = new FlowPanel();
    
    /**
     * The panel data contains the data of the field.
     * 
     * The view and dynamicForm.
     */
    protected FlowPanel dataPanel = new FlowPanel();
    
    /**
     * Panel that holds the action buttons
     */
    private FlowPanel actionPanel = new FlowPanel();
    
    /**
     * The Panel that holds the view buttons
     */
    protected FlowPanel viewButtons = new FlowPanel();
    
    /**
     * The Panel that holds the edit buttons
     */
    protected FlowPanel editButtons = new FlowPanel();
    
    /**
     * The save buttons.
     */
    protected Button saveButton = new Button("Save");
    
    /**
     * The undo button
     */
    protected Button undoButton = new Button("Undo");
    
    /**
     * The add buttons
     */
    protected Button addButton = new Button("Add");
    
    /**
     * The edit label
     */
    protected Label editLabel = new Label("Edit /");
    
    /**
     * The remove label
     */
    protected Label removeLabel = new Label(" Remove");
    
    /**
     * Class constructor
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     */
    public ComplexInput() {
        initWidget(mainPanel);
        
        dynamicFormPanel.add(getDynamicForm());
        dynamicFormPanel.setStyleName("displayInlineMiddle");
        
        viewPanel.add(getUiBinder());
        viewPanel.setStyleName("displayInlineMiddle");
        viewPanel.setVisible(false);
        
        dataPanel.add(dynamicFormPanel);
        dataPanel.add(viewPanel);
        dataPanel.setStyleName("displayInlineMiddle");
        
        viewButtons.add(editLabel);
        editLabel.setStyleName("displayInlineMiddle");
        editLabel.setStyleName("languageInputClickLabels");
        viewButtons.add(removeLabel);
        removeLabel.setStyleName("displayInlineMiddle");
        removeLabel.setStyleName("languageInputClickLabels");
        viewButtons.setStyleName("displayInlineMiddle");
        
        editButtons.add(saveButton);
        saveButton.setStyleName("displayInlineMiddle");
        editButtons.add(undoButton);
        undoButton.setStyleName("displayInlineMiddle");
        editButtons.setStyleName("displayInlineMiddle");
        
        actionPanel.add(addButton);
        actionPanel.setStyleName("displayInlineMiddle");
        
        mainPanel.add(dataPanel);
        mainPanel.add(actionPanel);
        
        /**
         * Add click handler on editLabel
         */
        editLabel.addClickHandler(new ClickHandler() {
            
            /**
             * Event cached on click of the component.
             * 
             * @author Alec Erasmus <alec.erasmus@a24group.com>
             * @since  22 November 2012
             * 
             * @param event - The click event
             */
            @Override
            public void onClick(ClickEvent event) {
                setEditDtate();
            }
        });
        
        /**
         * Add click handler on removeLabel
         */
        removeLabel.addClickHandler(new ClickHandler() {
            
            /**
             * Event cached on click of the component.
             * 
             * @author Alec Erasmus <alec.erasmus@a24group.com>
             * @since  22 November 2012
             * 
             * @param event - The click event
             */
            @Override
            public void onClick(ClickEvent event) {
                removeField();
            }
        });
        
        /**
         * Add click handler on addButton
         */
        addButton.addClickHandler(new ClickHandler() {
            
            /**
             * Event cached on click of the component.
             * 
             * @author Alec Erasmus <alec.erasmus@a24group.com>
             * @since  22 November 2012
             * 
             * @param event - The click event
             */
            @Override
            public void onClick(ClickEvent event) {
                addField();
            }
        });
        
        /**
         * Add click handler on saveButton
         */
        saveButton.addClickHandler(new ClickHandler() {
            
            /**
             * Event cached on click of the component.
             * 
             * @author Alec Erasmus <alec.erasmus@a24group.com>
             * @since  22 November 2012
             * 
             * @param event - The click event
             */
            @Override
            public void onClick(ClickEvent event) {
                saveField();
            }
        });
        
        /**
         * Add click handler on undoButton
         */
        undoButton.addClickHandler(new ClickHandler() {
            
            /**
             * Event cached on click of the component.
             * 
             * @author Alec Erasmus <alec.erasmus@a24group.com>
             * @since  22 November 2012
             * 
             * @param event - The click event
             */
            @Override
            public void onClick(ClickEvent event) {
                setViewState();
            }
        });
    }
    
    /**
     * Abstract function for the get of the ui view
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @return the ui as a Widget
     */
    public abstract Widget getUiBinder( );
    
    /**
     * Abstract function for the get of the DynamicForm
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @return the DynamicForm
     */
    public abstract DynamicForm<T> getDynamicForm( );
    
    /**
     * Abstract function to set the field in a view state
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     */
    public abstract void setViewState();
    
    /**
     * Abstract function to set the field in a edit state
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     */
    public abstract void setEditDtate();
    
    /**
     * Abstract function to set the field in a add state
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     */
    public abstract void setAddState();
    
    /**
     * Abstract function that will be called on click of the save button is clicked
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     */
    public abstract void saveField();
    
    /**
     * Abstract function that will be called on click of the add button is clicked
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     */
    public abstract void addField();
    
    /**
     * Abstract function that will be called on click of the remove button is clicked
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     */
    public abstract void removeField();
    

    /**
     * If the need arise for the field to have ValueChangeHandler added to it
     */
    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
        return null;
    }
    
    /**
     * Function that will set a widget in the action container
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @param widget - The widget to set
     */
    protected void setActionPanel(Widget widget) {
        actionPanel.clear();
        actionPanel.add(widget);
    }
    
    /**
     * Function that will add a widget in the action container
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @param widget - The widget to add
     */
    protected void addToActionPanel(Widget widget) {
        actionPanel.add(widget);
    }
    
    /**
     * Function that will remove a widget in the action container
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @param widget - The widget to remove
     */
    protected void removeFromActionPanel(Widget widget) {
        actionPanel.remove(widget);
    }
    
    /**
     * Function that will clear the action container
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     */
    protected void clearActionPanel() {
        actionPanel.clear();
    }
    
    /**
     * Getter for the view panel
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @return the view panel
     */
    protected FlowPanel getViewPanel() {
        return this.viewPanel;
    }
    
    /**
     * Getter for the dynamic panel
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @return the dynamic panel
     */
    protected FlowPanel getDynamicFormPanel() {
        return this.dynamicFormPanel;
    }
    
    /**
     * Set the add button to the action container.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     */
    protected void setAddButton() {
        setActionPanel(addButton);
    }
    
    /**
     * Set the view buttons to the action container.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     */
    protected void setViewButtons() {
        setActionPanel(viewButtons);
    }
    
    /**
     * Set the edit button to the action container.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     */
    protected void setEditButtons() {
        setActionPanel(editButtons);
    }

    /**
     * Return the field as a widget
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @return the field as a widget
     */
    @Override
    public Widget getInputFieldWidget() {
        return this.getWidget(); 
    }
    
    /**
     * Unimplemented function
     */
    @Override
    public Class<T> getReturnType() {
        return null;
    }

    /**
     * Unimplemented function
     */
    @Override
    public boolean isRequired() {
        return false;
    }

    /**
     * Unimplemented function
     */
    @Override
    public void setRequired(boolean required) {
    }

    /**
     * Unimplemented function
     */
    @Override
    public void setReadOnly(boolean readOnly) {
    }

    /**
     * Unimplemented function
     */
    @Override
    public boolean isReadOnly() {
        return false;
    }

    /**
     * Unimplemented function
     */
    @Override
    public void setValue(T value, boolean fireEvents) {
    }

    /**
     * Unimplemented function
     */
    @Override
    public T getValue(T object) {
        return null;
    }

    /**
     * Unimplemented function
     */
    @Override
    public void setValue(T object, T value) {
    }
    
}
