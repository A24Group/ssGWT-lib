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
        ComplexInputFormAddEvent.ComplexInputFormAddHasHandlers {
    
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
    protected Label removeLabel = new Label("Remove");
    
    /**
     * The panel that will be used to display either the info message
     * or the validation error
     */
    private FlowPanel messagePanel;

    /**
     * The table-panel that will be used to display either the info message
     * or the validation error
     */
    private FlowPanel messageTable;

    /**
     * The row-panel that will be used to display either the info message
     * or the validation error
     */
    private FlowPanel messageRow;
    
    /**
     * The cell-label that will hold either the info message or the
     * validation error
     */
    private Label messageCell;
    
    /**
     * Flowpanel to hold the message panel
     */
    private FlowPanel messageContainer;
    
    /**
     * Injected Object
     */
    protected Object injectedObject;
    
    /**
     * Class constructor
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     */
    public ComplexInput() {
        initWidget(mainPanel);
    }
    
    /**
     * Style to display elements inline
     */
    private String displayInline = "ssGWT-displayInlineBlockMiddel";
    
    /**
     * language Input Click Labels style
     */
    private String languageInputClickLabels = "ssGWT-languageInputClickLabels";
    
    /**
     * Save Button style
     */
    private String complexSaveButton = "ssGWT-complexSaveButton";
    
    /**
     * Label Button style
     */
    private String complexLabelButton = "ssGWT-complexLabelButton";
    
    /**
     * Undo Button style
     */
    private String complexUndoButton = "ssGWT-complexUndoButton";
    
    /**
     * Add Button style
     */
    private String complexAddButton = "ssGWT-complexAddButton";
    
    /**
     * Action Container style
     */
    private String complexActionContainer = "ssGWT-complexActionContainer";
    
    /**
     * Function to construct all the components and add it to the main panel
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     */
    public void constructor() {
        //create view components
        messagePanel = new FlowPanel();
        messageTable = new FlowPanel();
        messageRow = new FlowPanel();
        messageCell = new Label();
        messageContainer = new FlowPanel();
        
        messageContainer.add(messagePanel);
        messagePanel.setVisible(false);
        mainPanel.add(messagePanel);
    	
        dynamicFormPanel.add(getDynamicForm());
        dynamicFormPanel.setStyleName(displayInline);
        
        viewPanel.add(getUiBinder());
        viewPanel.setStyleName(displayInline);
        viewPanel.setVisible(false);
        
        dataPanel.add(dynamicFormPanel);
        dataPanel.add(viewPanel);
        dataPanel.setStyleName(displayInline);
        
        viewButtons.add(editLabel);
        editLabel.setStyleName(displayInline, true);
        editLabel.setStyleName(languageInputClickLabels, true);
        editLabel.setStyleName(complexLabelButton, true);
        viewButtons.add(removeLabel);
        removeLabel.setStyleName(displayInline, true);
        removeLabel.setStyleName(languageInputClickLabels, true);
        removeLabel.setStyleName(complexLabelButton, true);
        viewButtons.setStyleName(displayInline, true);
        
        editButtons.add(saveButton);
        saveButton.setStyleName(complexSaveButton);
        editButtons.add(undoButton);
        undoButton.setStyleName(displayInline);
        undoButton.setStyleName(complexUndoButton, true);
        editButtons.setStyleName(displayInline);
        
        addButton.setStyleName(complexAddButton);
        actionPanel.add(addButton);
        actionPanel.setStyleName(displayInline);
        actionPanel.setStyleName(complexActionContainer, true);
        
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
    public abstract Widget getUiBinder();
    
    /**
     * Abstract function for the get of the DynamicForm
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @return the DynamicForm
     */
    public abstract DynamicForm<T> getDynamicForm();
    
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
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @param handler - The Value Change Handler
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
     * A setter for an inject object
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  22 November 2012
     * 
     * @param object - The object to inject
     */
    public void setInjectedObject(Object object) {
        this.injectedObject = object;
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
     * Set the message panel to visible and sets an error message
     * on it. Also applies the error style.
     * 
     * @param message String to display as message
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  26 November 2012
     */
    public void displayValidationError(String message) {
        this.clearMessage();
        
        messageCell.setText(message);
        
        messageCell.setStyleName("messageErrorCell");
        messageRow.setStyleName("messageRow");
        messageTable.setStyleName("messageTable");
        messagePanel.setStyleName("ssGWT-complexMessagePanel");
        
        messageRow.add(messageCell);
        messageTable.add(messageRow);
        messagePanel.add(messageTable);
        
        messagePanel.setVisible(true);
    }
    
    /**
     * Set the message panel to visible and sets an info message
     * on it. Also applies the info style.
     * 
     * @param message String to display as message
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  26 November 2012
     */
    public void displayInfoMessage(String message) {
        this.clearMessage();
        
        messageCell.setText(message);
        
        messageCell.setStyleName("messageInfoCell");
        messageRow.setStyleName("messageRow");
        messageTable.setStyleName("messageTable");
        messagePanel.setStyleName("ssGWT-complexMessagePanel");
        
        messageRow.add(messageCell);
        messageTable.add(messageRow);
        messagePanel.add(messageTable);
        
        messagePanel.setVisible(true);
    }
    
    /**
     * Clear the message panel of messages and sets it's
     * visibility to false.
     * 
     * @author Ashwin Arendse <ashwin.arendse@a24group.com>
     * @since  12 November 2012
     */
    public void clearMessage() {
        messagePanel.setVisible(false);
        messageCell.setText("");
        messagePanel.clear();
    }
    
    /**
     * Function force implementation due to class inheritance 
     */
    @Override
    @Deprecated
    public Class<T> getReturnType() {
        return null;
    }

    /**
     * Function force implementation due to class inheritance 
     */
    @Deprecated
    @Override
    public boolean isRequired() {
        return false;
    }

    /**
     * Function force implementation due to class inheritance 
     */
    @Override
    @Deprecated
    public void setRequired(boolean required) {
    }

    /**
     * Function force implementation due to class inheritance 
     */
    @Override
    @Deprecated
    public void setReadOnly(boolean readOnly) {
    }

    /**
     * Function force implementation due to class inheritance 
     */
    @Override
    @Deprecated
    public boolean isReadOnly() {
        return false;
    }

    /**
     * Function force implementation due to class inheritance 
     */
    @Override
    @Deprecated
    public void setValue(T value, boolean fireEvents) {
    }

    /**
     * Function force implementation due to class inheritance 
     */
    @Override
    @Deprecated
    public void setValue(T object, T value) {
    }

    /**
     * Function force implementation due to class inheritance
     */
    @Override
    @Deprecated
    public T getValue(T object) {
        return null;
    }
}
