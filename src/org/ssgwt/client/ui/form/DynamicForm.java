package org.ssgwt.client.ui.form;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.ssgwt.client.validation.FormValidator;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;

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
            this(inputField, label, false);
        }
        
        /**
         * Class constructor
         * 
         * @param inputField - The input field that should be displayed on the dynamic form
         * @param label - The label that should be displayed above the input field
         * @param customStyleName - The custom style to apply to the field
         */
        public Field(InputField inputField, String label, String customStyleName) {
            this(inputField, label, false, customStyleName);
        }
        
        /**
         * Class constructor
         * 
         * @param inputField - The input field that should be displayed on the dynamic form
         * @param label - The label that should be displayed above the input field
         * @param embeded - Whether the component is an embeded object or not
         */
        public Field(InputField inputField, String label, boolean embeded) {
            this(inputField, label, embeded, layout, "");
        }
        
        /**
         * Class constructor
         * 
         * @param inputField - The input field that should be displayed on the dynamic form
         * @param label - The label that should be displayed above the input field
         * @param embeded - Whether the component is an embeded object or not
         * @param customStyleName - The custom style to apply to the field
         */
        public Field(InputField inputField, String label, boolean embeded, String customStyleName) {
            this(inputField, label, embeded, layout, customStyleName);
        }
        
        /**
         * Class constructor
         * 
         * @param inputField - The input field that should be displayed on the dynamic form
         * @param label - The label that should be displayed above the input field
         * @param embeded - Whether the component is an embeded object or not
         * @param layout - The layout of the field
         */
        public Field(InputField inputField, String label, boolean embeded, int layout) {
            this(inputField, label, embeded, layout, "");
        }
        
        /**
         * Class constructor
         * 
         * @param inputField - The input field that should be displayed on the dynamic form
         * @param label - The label that should be displayed above the input field
         * @param embeded - Whether the component is an embeded object or not
         * @param layout - The layout of the field. If an incorrect value is passed, it will default to vertical
         * @param customStyleName - The custom style to apply to the field
         */
        public Field(InputField inputField, String label, boolean embeded, int layout, String customStyleName) {
            initWidget(container);

            this.inputField = inputField;
            this.fieldLabel.setText(label);
            if (!this.fieldLabel.getText().equals("")) {
                this.container.add(this.fieldLabel);
            }
            this.fieldLabel.addStyleName(labelStyleName);
            this.container.add(this.inputFieldContainer);
            this.inputFieldContainer.add(this.inputField.getInputFieldWidget());
            this.inputFieldContainer.add(this.requiredStar);
            this.requiredStar.setVisible(this.inputField.isRequired());
            this.inputField.getInputFieldWidget().addStyleName(inputFieldStyleName);
            if (!customStyleName.equals("")) {
                this.addStyleName(customStyleName);
            }
            if (embeded) {
                this.container.setStyleName(containerEmbeddedStyleName);
                this.inputField.getInputFieldWidget().addStyleName(inputFieldAdditionalEmbeddedStyleName);
                
            } else {
                this.inputField.getInputFieldWidget().addStyleName(inputFieldAdditionalNormalStyleName);
                this.container.addStyleName(containerDefaultStyleName);
                this.requiredStar.addStyleName(requiredIndicatorStyle);
                this.inputField.setReadOnly(readOnly);
            }
            switch (layout) {
                case DynamicForm.LAYOUT_HORIZONTAL:
                    //Add style to make components align horizontally
                    this.container.addStyleName(horizontalDefaultStyleName);
                    this.container.setWidth("");
                    break;
                case DynamicForm.LAYOUT_VERTICAL:
                default:
                    if (customStyleName.equals("")) {
                        this.inputField.getInputFieldWidget().setWidth(fieldWidth);
                    }
                    break;
            }
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
     * The default style name for the horizontal display style for the dynamic form
     */
    public static final String DEFAULT_HORIZONTAL_CONTAINER_STYLE = "ssGwt-HorizontalDisplay";
    
    /**
     * The default style name for the default container on the dynamic form
     */
    public static final String DEFAULT_CONTAINER_STYLE = "ssGwt-DefaultContainer";
    
    /**
     * The default style name for the Embedded container on the dynamic form
     */
    public static final String DEFAULT_CONTAINER_EMBEDDED_STYLE = "ssGwt-EmbeddedContainer";

    /**
     * The default style name for the normal input fields on the dynamic form
     */
    public static final String DEFAULT_ADDITIONAL_NORMAL_INPUT_FIELD_STYLE = "ssGwt-InputExtraNormal";

    /**
     * The default style name for the embedded input fields on the dynamic form
     */
    public static final String DEFAULT_ADDITIONAL_EMBEDDED_INPUT_FIELD_STYLE = "ssGwt-InputExtraEmbedded";

    /**
     * Another layout on the dynamic form
     */
    public static final int LAYOUT_HORIZONTAL = 0;
    
    /**
     * The default layout on the dynamic form
     */
    public static final int LAYOUT_VERTICAL = 1;
    
    /**
     * The default width of the input fields
     */
    public static final String DEFAULT_FIELD_WIDTH = "230px";
    
    /**
     * The style name for the labels of the dynamic form
     */
    private String labelStyleName;

    /**
     * The style name for the embedded container of the dynamic form
     */
    private String containerEmbeddedStyleName;

    /**
     * The style name for the default container of the dynamic form
     */
    private String containerDefaultStyleName;

    /**
     * The style name for the input fields on the dynamic form
     */
    private String inputFieldStyleName;
    
    /**
     * Additional style name for non-embedded field
     */
    private String inputFieldAdditionalNormalStyleName;

    /**
     * Additional style name for embedded field
     */
    private String inputFieldAdditionalEmbeddedStyleName;
    
    /**
     * Additional style name for horizontal fields
     */
    private String horizontalDefaultStyleName;
    
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
     * Used to store the value of the layout
     */
    private int layout;
    
    /**
     * Class constructor
     */
    public DynamicForm() {
        this(DEFAULT_LABEL_STYLE, DEFAULT_INPUT_FIELD_STYLE, DEFAULT_REQUIRED_INDICATOR_STYLE, DEFAULT_FIELD_WIDTH, LAYOUT_VERTICAL);
    }
    
    /**
     * Class constructor for just the layout
     * 
     * @param layout - The layout to use
     */
    public DynamicForm(int layout) {
        this(DEFAULT_LABEL_STYLE, DEFAULT_INPUT_FIELD_STYLE, DEFAULT_REQUIRED_INDICATOR_STYLE, DEFAULT_FIELD_WIDTH, layout);
    }
    
    /**
     * Class constructor
     * 
     * @param fieldWidth - The width of the input fields
     */
    public DynamicForm(String fieldWidth) {
        this(DEFAULT_LABEL_STYLE, DEFAULT_INPUT_FIELD_STYLE, DEFAULT_REQUIRED_INDICATOR_STYLE, fieldWidth, LAYOUT_VERTICAL);
    }
    
    /**
     * Class constructor
     * 
     * @param labelStyleName - The style name for the labels of the dynamic form
     * @param inputFieldStyleName - The style name for the input fields on the dynamic form
     * @param requiredIndicatorStyle - The style name for the required indicator on the dynamic form
     */
    public DynamicForm(String labelStyleName, String inputFieldStyleName, String requiredIndicatorStyle) {
        this(labelStyleName, inputFieldStyleName, requiredIndicatorStyle, DEFAULT_FIELD_WIDTH, LAYOUT_VERTICAL);
    }
    
    /**
     * Class constructor
     * 
     * @param labelStyleName - The style name for the labels of the dynamic form
     * @param inputFieldStyleName - The style name for the input fields on the dynamic form
     * @param requiredIndicatorStyle - The style name for the required indicator on the dynamic form
     * @param fieldWidth - The width of the input fields
     * @param layout - The layout of the field
     */
    public DynamicForm(String labelStyleName, String inputFieldStyleName, String requiredIndicatorStyle, String fieldWidth, int layout) {
        initWidget(mainConatiner);
        this.labelStyleName = labelStyleName;
        this.inputFieldStyleName = inputFieldStyleName;
        this.requiredIndicatorStyle = requiredIndicatorStyle;
        this.fieldWidth = fieldWidth;
        this.layout = layout;
        
        //set defaults for additional styles
        this.containerDefaultStyleName = DEFAULT_CONTAINER_STYLE;
        this.containerEmbeddedStyleName = DEFAULT_CONTAINER_EMBEDDED_STYLE;
        this.inputFieldAdditionalEmbeddedStyleName = DEFAULT_ADDITIONAL_EMBEDDED_INPUT_FIELD_STYLE;
        this.inputFieldAdditionalNormalStyleName = DEFAULT_ADDITIONAL_NORMAL_INPUT_FIELD_STYLE;
        this.horizontalDefaultStyleName = DEFAULT_HORIZONTAL_CONTAINER_STYLE;
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
            } else if (List.class.equals(field.getReturnType())) {
                ((InputField<T, List>)field).setValue(dataObject, ((HasValue<List>)field).getValue());
            } else if (Boolean.class.equals(field.getReturnType())) {
                ((InputField<T, Boolean>)field).setValue(dataObject, ((HasValue<Boolean>)field).getValue());
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
            } else if (List.class.equals(field.getReturnType())) {
                ((HasValue<List>)field).setValue(((InputField<T, List>)field).getValue(dataObject));
            } else if (Boolean.class.equals(field.getReturnType())) {
                ((InputField<T, Boolean>)field).setValue(dataObject, ((HasValue<Boolean>)field).getValue());
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
        addField(inputField, label, false, "");
    }
    
    /**
     * Adds a input field to the Dynamic form
     * 
     * @param inputField - The input field that should be added to the form
     * @param label - The label that should be display above the field
     * @param customStyleName - The custom style to apply to the field
     */
    public void addField(InputField<T, ?> inputField, String label, String customStyleName) {
        addField(inputField, label, false, customStyleName);
    }
    
    /**
     * Adds a input field to the Dynamic form
     * 
     * @param inputField - The input field that should be added to the form
     * @param label - The label that should be display above the field
     * @param embeded - Whether the component is an embeded object or not
     */
    public void addField(InputField<T, ?> inputField, String label, boolean embeded) {
        addField(inputField, label, embeded, "");
    }
    
    /**
     * Adds a input field to the Dynamic form
     * 
     * @param inputField - The input field that should be added to the form
     * @param label - The label that should be display above the field
     * @param embeded - Whether the component is an embeded object or not
     * @param customStyleName - The custom style to apply to the field
     */
    public void addField(InputField<T, ?> inputField, String label, boolean embeded, String customStyleName) {
        drawField(inputField, label, embeded, customStyleName);
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
        drawField(inputField, label, false, "");
    }
    
    /**
     * Draws the field on the form
     * 
     * @param inputField - The input field that should be added to the form
     * @param label - The label that should be display above the field
     * @param customStyleName - The custom style to apply to the field
     */
    private void drawField(InputField<T, ?> inputField, String label, String customStyleName) {
        drawField(inputField, label, false, customStyleName);
    }
    
    /**
     * Draws the field on the form
     * 
     * @param inputField - The input field that should be added to the form
     * @param label - The label that should be display above the field
     * @param embeded - Whether the component is an embeded object or not
     */
    private void drawField(InputField<T, ?> inputField, String label, boolean embeded) {
        drawField(inputField, label, embeded, "");
    }
    
    /**
     * Draws the field on the form
     * 
     * @param inputField - The input field that should be added to the form
     * @param label - The label that should be display above the field
     * @param embeded - Whether the component is an embeded object or not
     * @param customStyleName - The custom style to apply to the field
     */
    private void drawField(InputField<T, ?> inputField, String label, boolean embeded, String customStyleName) {
        Field fieldInfo = new Field(inputField, label, embeded, customStyleName);
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
     * Sets the default container style name.
     * This is non-emebed style for the container. that contains the fields
     * 
     * @param defaultContainerStyleName - the default container style name
     */
    public void setDefaultContainerStyleName(String defaultContainerStyleName) {
        this.containerDefaultStyleName = defaultContainerStyleName;
        redraw();
    }
    
    /**
     * Retrieves the default container style name
     * 
     * @return the default container style name
     */
    public String getDefaultContainerStyleName() {
        return containerDefaultStyleName;
    }
    
    /**
     * Sets the embedded container style name that contains the fields
     * 
     * @param embeddedContainerStyleName - the embedded container style name that contains the fields
     */
    public void setEmbeddedContainerStyleName(String embeddedContainerStyleName) {
        this.containerEmbeddedStyleName = embeddedContainerStyleName;
        redraw();
    }

    /**
     * Retrieves the embedded container style name
     * 
     * @return the embedded container style name
     */
    public String getEmbeddedContainerStyleName() {
        return containerEmbeddedStyleName;
    }
    
    /**
     * Sets the style name for the embedded input fields on the dynamic form
     * 
     * @param inputFieldAdditionalEmbeddedStyleName - the additional style name for the embedded input fields on the dynamic form
     */
    public void setInputFieldAdditionalEmbeddedStyleName(
            String inputFieldAdditionalEmbeddedStyleName) {
        this.inputFieldAdditionalEmbeddedStyleName = inputFieldAdditionalEmbeddedStyleName;
        redraw();
    }

    /**
     * Retrieves the style name for the input fields on the dynamic form
     * 
     * @return The style name for the input fields on the dynamic form
     */
    public String getInputFieldAdditionalEmbeddedStyleName() {
        return inputFieldAdditionalEmbeddedStyleName;
    }

    /**
     * Sets the style name for the input fields on the dynamic form
     * 
     * @param inputFieldAdditionalNormalStyleName - the additional style name for the input fields on the dynamic form
     */
    public void setInputFieldAdditionalNormalStyleName(
            String inputFieldAdditionalNormalStyleName) {
        this.inputFieldAdditionalNormalStyleName = inputFieldAdditionalNormalStyleName;
        redraw();
    }

    /**
     * Retrieves the style name for the input fields on the dynamic form
     * 
     * @return The style name for the input fields on the dynamic form
     */
    public String getInputFieldAdditionalNormalStyleName() {
        return inputFieldAdditionalNormalStyleName;
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
     * Set global dynamic form keyboard keydown handler on each field.
     * 
     * @param handler - The handler type apply to the fields 
     */
    public void setKeyDownFieldsHandler(KeyDownHandler handler) {
        for (InputField inputField : fields.keySet()) {
            inputField.getInputFieldWidget().addDomHandler(handler, KeyDownEvent.getType());
        }
        
    }
}