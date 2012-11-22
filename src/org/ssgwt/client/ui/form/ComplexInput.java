package org.ssgwt.client.ui.form;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;


import org.ssgwt.client.ui.form.event.ComplexInputFormRemoveEvent;
import org.ssgwt.client.ui.form.event.ComplexInputFormAddEvent;
import org.ssgwt.client.ui.form.event.ComplexInputFormEditEvent;

public abstract class ComplexInput<T> 
    extends Composite 
    implements HasValue<T>, 
    InputField<T, T>, 
    ComplexInputFormRemoveEvent.ComplexInputFormRemoveHasHandlers, 
    ComplexInputFormAddEvent.ComplexInputFormAddHasHandlers,
    ComplexInputFormEditEvent.ComplexInputFormEditHasHandlers {
    
    protected FlowPanel mainPanel = new FlowPanel();
    
    protected FlowPanel dynamicFormPanel = new FlowPanel();
    
    protected FlowPanel viewPanel = new FlowPanel();
    
    protected FlowPanel dataPanel = new FlowPanel();
    
    private FlowPanel actionPanel = new FlowPanel();
    
    protected FlowPanel viewButtons = new FlowPanel();
    
    protected FlowPanel editButtons = new FlowPanel();
    
    protected Button saveButton = new Button("Save");
    
    protected Button undoButton = new Button("Undo");
    
    protected Button addButton = new Button("Add");
    
    protected Label editLabel = new Label("Edit /");
    
    protected Label removeLabel = new Label(" Remove");
    
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
        
        editLabel.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                setEditDtate();
            }
        });
        
        removeLabel.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                removeField();
            }
        });
        
        addButton.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                addField();
            }
        });
        
        saveButton.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
            	saveField();
            }
        });
        
        undoButton.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                setViewState();
            }
        });
    }
    
    public abstract Widget getUiBinder( );
    
    public abstract DynamicForm<T> getDynamicForm( );
    
    
    public abstract void setViewState();
    
    public abstract void setEditDtate();
    
    public abstract void setAddState();
    
    
    public abstract void saveField();
    
    public abstract void addField();
    
    public abstract void removeField();
    

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
        return null;
    }
    
    protected void setActionPanel(Widget widget) {
        actionPanel.clear();
        actionPanel.add(widget);
    }
    
    protected void addToActionPanel(Widget widget) {
        actionPanel.add(widget);
    }
    
    protected void addFromActionPanel(Widget widget) {
        actionPanel.remove(widget);
    }
    
    protected void clearActionPanel(Widget widget) {
        actionPanel.clear();
    }
    
    
    
    
    protected FlowPanel getViewPanel() {
        return this.viewPanel;
    }
    
    protected FlowPanel getDynamicFormPanel() {
        return this.dynamicFormPanel;
    }
    
    protected void setAddButton() {
        setActionPanel(addButton);
    }
    
    protected void setViewButtons() {
        setActionPanel(viewButtons);
    }
    
    protected void setEditButtons() {
        setActionPanel(editButtons);
    }

    /**
     * 
     */
    @Override
    public Class<T> getReturnType() {
        return null;
    }

    @Override
    public boolean isRequired() {
        return false;
    }

    @Override
    public void setRequired(boolean required) {
    }

    @Override
    public Widget getInputFieldWidget() {
        return this.getWidget(); 
    }

    @Override
    public void setReadOnly(boolean readOnly) {
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public void setValue(T value, boolean fireEvents) {
    }

    @Override
    public T getValue(T object) {
        return null;
    }

    @Override
    public void setValue(T object, T value) {
    }
    
}
