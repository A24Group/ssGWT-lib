package org.ssgwt.client.ui.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.ssgwt.client.validation.FormValidator;

import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Dynamic form that allows fields extending the InputField interface to be added an creates the field with a label
 * 
 * @author Johannes Gryffenberg<johannes.gryffenberg@gmail.com>
 * @since 12 July 2012
 *
 * @param <T> The object type the Dynamic form uses to get values from updates the value of the fields on
 */
public class DynamicForm<T> extends Composite {
    
    /**
     * The field component that is used to display the field label, the field and the required star if the field is required
     * 
     * @author Johannes Gryffenberg<johannes.gryffenberg@gmail.com>
     * @since 12 July 2012
     */
    private class Field extends Composite {
        
        /**
         * The label of the field
         */
        private Label fieldLabel = new Label();
        
        /**
         * The the flow panel that holds the label and the inputFieldContainer
         */
        private FlowPanel container = new FlowPanel();
        
        /**
         * The input field
         */
        private InputField inputField;
        
        /**
         * The required start label
         */
        private Label requiredStar = new Label("*");
        
        /**
         * The flow panel that holds the input field and the required star
         */
        private FlowPanel inputFieldContainer = new FlowPanel();
        
        /**
         * Class constructor
         * 
         * @param inputField - The input field that should be displayed on the dynamic form
         * @param label - The label that should be displayed above the input field
         */
        public Field(InputField inputField, String label) {
            initWidget(container);
            this.container.setWidth("100%");
            this.inputField = inputField;
            this.fieldLabel.setText(label);
            this.container.add(this.fieldLabel);
            this.container.add(this.inputFieldContainer);
            this.inputFieldContainer.add(this.inputField.getInputFieldWidget());
            this.inputFieldContainer.add(this.requiredStar);
            this.requiredStar.setVisible(this.inputField.isRequired());
            this.fieldLabel.setStyleName(labelStyleName);
            this.inputField.getInputFieldWidget().setStyleName(inputFieldStyleName);
            this.requiredStar.setStyleName(requiredIndicatorStyle);
            this.inputField.setReadOnly(readOnly);
        }
    }
    
    /**
     * The flag that indicates whether the fields on the form is read only
     */
    private boolean readOnly;
    
    /**
     * The main container of the component
     */
    private FlowPanel mainConatiner = new FlowPanel();
    
    /**
     * HashMap that holds the Field object used for each input field
     */
    private HashMap<InputField, Field> fields = new HashMap<InputField, Field>();
    
    /**
     * The data object that holds the data t
     */
    private T dataObject;
    
    /**
     * The default style name for the labels of the dynamic form
     */
    public static final String DEFAULT_LABEL_STYLE = "ssGwt-Label";
    
    /**
     * The default style name for the input fields on the dynamic form
     */
    public static final String DEFAULT_INPUT_FIELD_STYLE = "ssGwt-Input";
    
    /**
     * The default style name for the required indicator on the dynamic form
     */
    public static final String DEFAULT_REQUIRED_INDICATOR_STYLE = "ssGwt-RequiredIndicator";
    
    /**
     * The default width of the input fields
     */
    public static final String DEFAULT_FIELD_WIDTH = "300px";
    
    /**
     * The style name for the labels of the dynamic form
     */
    private String labelStyleName;

    /**
     * The style name for the input fields on the dynamic form
     */
    private String inputFieldStyleName;
    
    /**
     * The style name for the required indicator on the dynamic form
     */
    private String requiredIndicatorStyle;
    
    /**
     * The validator used to validate the form
     */
    FormValidator formValidator = new FormValidator();
    
    /**
     * The width of the input fields
     */
    private String fieldWidth;
    
    /**
     * Class constructor
     */
    public DynamicForm() {
        this(DEFAULT_LABEL_STYLE, DEFAULT_INPUT_FIELD_STYLE, DEFAULT_REQUIRED_INDICATOR_STYLE, DEFAULT_FIELD_WIDTH);
    }
    
    /**
     * Class constructor
     * 
     * @param fieldWidth - The width of the input fields
     */
    public DynamicForm(String fieldWidth) {
        this(DEFAULT_LABEL_STYLE, DEFAULT_INPUT_FIELD_STYLE, DEFAULT_REQUIRED_INDICATOR_STYLE, fieldWidth);
    }
    
    /**
     * Class constructor
     * 
     * @param labelStyleName - The style name for the labels of the dynamic form
     * @param inputFieldStyleName - The style name for the input fields on the dynamic form
     * @param requiredIndicatorStyle - The style name for the required indicator on the dynamic form
     */
    public DynamicForm(String labelStyleName, String inputFieldStyleName, String requiredIndicatorStyle) {
        this(labelStyleName, inputFieldStyleName, requiredIndicatorStyle, DEFAULT_FIELD_WIDTH);
    }
    
    /**
     * Class constructor
     * 
     * @param labelStyleName - The style name for the labels of the dynamic form
     * @param inputFieldStyleName - The style name for the input fields on the dynamic form
     * @param requiredIndicatorStyle - The style name for the required indicator on the dynamic form
     * @param fieldWidth - The width of the input fields
     */
    public DynamicForm(String labelStyleName, String inputFieldStyleName, String requiredIndicatorStyle, String fieldWidth) {
        initWidget(mainConatiner);
        this.labelStyleName = labelStyleName;
        this.inputFieldStyleName = inputFieldStyleName;
        this.requiredIndicatorStyle = requiredIndicatorStyle;
        this.fieldWidth = fieldWidth;
    }
    
    /**
     * Sets that data object for the form
     * 
     * @param dataObject - The data object used 
     */
    public void setData(T dataObject) {
        this.dataObject = dataObject;
        updateFieldData();
    }
    
    /**
     * Retrieves the passed in object with the field data updated on the object
     * 
     * @return The passed in object with the field data updated on the object
     */
    public T getData() {
        updateDataObject();
        return dataObject;
    }
    
    /**
     * Updates the data object that was set using the setData function with the data in the fields
     */
    protected void updateDataObject() {
        for(InputField<T, ?> field : fields.keySet()) {
            if (String.class.equals(field.getReturnType())) {
                ((InputField<T, String>)field).setValue(dataObject, ((HasValue<String>)field).getValue());
            } else if (Date.class.equals(field.getReturnType())) {
                ((InputField<T, Date>)field).setValue(dataObject, ((HasValue<Date>)field).getValue());
            }
        }
    }
    
    /**
     * Updates the fields with the data that was set using the setData function
     */
    protected void updateFieldData() {
        for(InputField<T, ?> field : fields.keySet()) {
            if (String.class.equals(field.getReturnType())) {
                ((HasValue<String>)field).setValue(((InputField<T, String>)field).getValue(dataObject));
            } else if (Date.class.equals(field.getReturnType())) {
                ((HasValue<Date>)field).setValue(((InputField<T, Date>)field).getValue(dataObject));
            }
        }
    }
    
    /**
     * Adds a input field to the Dynamic form
     * 
     * @param inputField - The input field that should be added to the form
     * @param label - The label that should be display above the field
     */
    public void addField(InputField<T, ?> inputField, String label) {
        drawField(inputField, label);
    }
    
    /**
     * Removes a field from the Dynamic form
     * 
     * @param inputField - The input field that should be removed from the Dynamic form
     */
    public void removeField(InputField<T, ?> inputField) {
        mainConatiner.remove(fields.get(inputField).container);
        fields.remove(inputField);
    }
    
    /**
     * Validates the form and returns a string the first validation error if there is any errors
     * 
     * @return The validation error message
     */
    public String doValidation() {
        return formValidator.doValidation();
    }
    
    /**
     * Draws the field on the form
     * 
     * @param inputField - The input field that should be added to the form
     * @param label - The label that should be display above the field
     */
    private void drawField(InputField<T, ?> inputField, String label) {
        Field fieldInfo = new Field(inputField, label);
        inputField.getInputFieldWidget().setWidth(fieldWidth);
        mainConatiner.add(fieldInfo);
        fields.put(inputField, fieldInfo);
    }
    
    /**
     * Updates styles of the fields and hides the required star if the required state of a field changes
     */
    public void redraw() {
        for (Field field : fields.values()) {
            field.fieldLabel.setStyleName(labelStyleName);
            field.inputField.getInputFieldWidget().setStyleName(inputFieldStyleName);
            field.requiredStar.setStyleName(requiredIndicatorStyle);
            field.requiredStar.setVisible(field.inputField.isRequired());
            field.inputField.setReadOnly(this.readOnly);
        }
    }
    
    /**
     * Adds validation criteria to a input field
     * 
     * @param inputField - The input field that should be validated
     * @param validatorReferenceName - The reference name for the validation
     * @param config - Validation configuration settings
     */
    public void addFieldValidation(InputField<T, ?> inputField, String validatorReferenceName, HashMap<String, ?> config) {
        formValidator.addField(validatorReferenceName, inputField.getInputFieldWidget(), config);
    }
    
    /**
     * Adds validation criteria to a input field
     * 
     * @param inputField - The input field that should be validated
     * @param validatorReferenceName - The reference name for the validation
     * @param config - Validation configuration settings
     * @param errorMessage - The error message to be displayed
     */
    public void addFieldValidation(InputField<T, ?> inputField, String validatorReferenceName, HashMap<String, ?> config, String errorMessage) {
        formValidator.addField(validatorReferenceName, inputField.getInputFieldWidget(), config, errorMessage);
    }
    
    /**
     * Adds validation criteria to a input field
     * 
     * @param inputField - The input field that should be validated
     * @param validatorReferenceName - The reference name for the validation
     * @param config - Validation configuration settings
     * @param errorMessage - The error message to be displayed
     * @param errorStyleName - the error style type
     */
    public void addFieldValidation(InputField<T, ?> inputField, String validatorReferenceName, HashMap<String, ?> config, String errorMessage, String errorStyleName) {
        formValidator.addField(validatorReferenceName, inputField.getInputFieldWidget(), config, errorMessage, errorStyleName);
    }
    
    /**
     * Retrieves the style name for the labels of the dynamic form
     * 
     * @return The style name for the labels of the dynamic form
     */
    public String getLabelStyleName() {
        return labelStyleName;
    }
    
    /**
     * Sets the style name for the labels of the dynamic form
     * 
     * @param labelStyleName - The style name for the labels of the dynamic form
     */
    public void setLabelStyleName(String labelStyleName) {
        this.labelStyleName = labelStyleName;
        redraw();
    }
    
    /**
     * Retrieves the style name for the input fields on the dynamic form
     * 
     * @return The style name for the input fields on the dynamic form
     */
    public String getInputFieldStyleName() {
        return inputFieldStyleName;
    }
    
    /**
     * Sets the style name for the input fields on the dynamic form
     * 
     * @param inputFieldStyleName - The style name for the input fields on the dynamic form
     */
    public void setInputFieldStyleName(String inputFieldStyleName) {
        this.inputFieldStyleName = inputFieldStyleName;
        redraw();
    }
    
    /**
     * Retrieve the style name for the required indicator on the dynamic form
     * 
     * @return The style name for the required indicator on the dynamic form
     */
    public String getRequiredIndicatorStyle() {
        return requiredIndicatorStyle;
    }
    
    /**
     * Sets the style name for the required indicator on the dynamic form
     * 
     * @param requiredIndicatorStyle - The style name for the required indicator on the dynamic form
     */
    public void setRequiredIndicatorStyle(String requiredIndicatorStyle) {
        this.requiredIndicatorStyle = requiredIndicatorStyle;
        redraw();
    }
    
    /**
     * Set all the fields on the form as readOnly
     * 
     * @param readOnly - Flag to indicate whether the fields should be read only
     */
    public void setFieldsReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
        redraw();
    }
    
    /**
     * Retrieve the flag that indicates whether the fields on the form is read only
     * 
     * @return The flag that indicates whether the fields on the form is read only
     */
    public boolean isFieldsReadOnly() {
        return this.readOnly;
    }
    
    /**
     * Set a fields visibility
     * 
     * @param inputField - The input field
     * @param visible - True to disply the field|false to hide a field
     */
    public void setFieldVisible(InputField<T, ?> inputField, boolean visible) {
        if (fields.containsKey(inputField)) {
            fields.get(inputField).setVisible(visible);
        }
    }
    
    /**
     * Set global dynamic form keybord keypress handler on each field
     * 
     * @param handler - The handler tp apply to the fields 
     */
    public void setKeyPressFieldsHandler(KeyPressHandler handler) {
        for (InputField inputField : fields.keySet()) {
            inputField.getInputFieldWidget().addDomHandler(handler, KeyPressEvent.getType());
        }
        
    }
}