### Example of how to use the DynamicForm
```
public class DynamicFormExample implements EntryPoint {
    
    /**
     * A simple data type that represents a contact.
     */
    private static class Contact extends AbstractMultiSelectObject {
        private String address;
        private String random;
        private String name;

        public Contact(String name, String random, String address) {
            this.name = name;
            this.random = random;
            this.address = address;
        }
        
        public String toString() {
            return name + " " + random + " " + address;
        }
    }
    
    public void onModuleLoad() {
        // Creates a length validator
        HashMap<String, Integer> strValidatorConfig = new HashMap<String, Integer>();
        strValidatorConfig.put(StringValidator.OPTION_MIN_LENGTH, 1);
        
        // Setup the regex validator config
        HashMap<String, String> strRegexValidatorConfig = new HashMap<String, String>();
        strRegexValidatorConfig.put(StringRegexValidator.VALIDATOR_REGEX_NAME, StringRegexValidator.REGEX_NAME_PATTERN);
        
        // Creates a Contact object that will be used by the dynamic form
        Contact contact = new Contact("Sh33pman", "Random", "Somewhere");
        
        // Create the dynamic form
        final DynamicForm<Contact> form = new DynamicForm<DataGridColumn.Contact>();
        
        // Create a field for the name property on the Contact object
        TextInputField<Contact> nameInputField = new TextInputField<Contact>(true) {

            @Override
            public String getValue(Contact object) {
                return object.name;
            }

            @Override
            public void setValue(Contact object, String value) {
                object.name = value;
            }
        };
        // Add the name field to the dynamic form
        form.addField(nameInputField, "Name:");
        
        // Create a field for the random property on the Contact object
        TextInputField<Contact> randomInputField = new TextInputField<Contact>() {

            @Override
            public String getValue(Contact object) {
                return object.random;
            }

            @Override
            public void setValue(Contact object, String value) {
                object.random = value;
            }
        };
        // Add the random field to the dynamic form
        form.addField(randomInputField, "Random:");
        
        // Create a field for the address property on the Contact object
        TextInputField<Contact> addressInputField = new TextInputField<Contact>(true) {

            @Override
            public String getValue(Contact object) {
                return object.address;
            }

            @Override
            public void setValue(Contact object, String value) {
                object.address = value;
            }
        };
        // Add the address field to the dynamic form
        form.addField(addressInputField, "Address:");
        
        // Add the length validator to the name field
        form.addFieldValidation(nameInputField, FormFieldConstants.VALIDATE_STRING_REFERENCE, strValidatorConfig, "The Name field is required", "error");
        // Add the regex validator to the name field
        form.addFieldValidation(nameInputField, FormFieldConstants.VALIDATE_REGEX_REFERENCE, strRegexValidatorConfig, "The Name can not contain numeric characters", "error");
        
        // Add the length validator to the address field
        form.addFieldValidation(addressInputField, FormFieldConstants.VALIDATE_STRING_REFERENCE, strValidatorConfig, "The Name field is required", "error");
        
        // Sets the Contact object on the dynamic form that will be used to set the data on the fields
        form.setData(contact);
        
        // Add button that will have action
        Button clickMe = new Button("Click Me");
        
        // The label the will display the Contact object or validation error
        final Label x = new Label();
        
        // Button click handler
        clickMe.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                String validationMessages = form.doValidation()
                if (validationMessages != null) {
                    x.setText(validationMessages);
                    return;
                }
                
                x.setText(form.getData().toString());
            }
        });
        
        form.setStyleName("form");
        
        FlowPanel mainFlowPanel = new FlowPanel();
        mainFlowPanel.setSize("100%", "100%");
        form.setSize("400px", "100%");
        mainFlowPanel.add(form);
        mainFlowPanel.add(clickMe);
        mainFlowPanel.add(x);
        RootPanel.get().add(mainFlowPanel);
    }
}
```

### How to style your Dynamic Form using the default style names

Add the following to your style sheet
```

/* The container that holds all your input fields */
.form {
    
}

/* The style that will be applied to your input field if validation fails on the field */
.form .error {
    border: 1px solid #FF0000 !Important;
}

/* The style for the labels of the input fields */
.form .ssGwt-Label {
    font-family: sans-serif;
    font-size: 12px;
    font-weight: bold;
    padding-left: 1px;
    padding-top: 10px;
    padding-bottom: 4px;
    color: #666666;
}

/* The style for the input fields */
.form .ssGwt-Input {
    display: inline-block;
    -moz-border-radius: 3px;
    border-radius: 3px;
    border: 1px solid #CCCCCC;
    font-family: sans-serif;
    font-size: 12px;
    padding-top:4px;
    padding-bottom: 4px;
    padding-left: 4px;
    color: #485E87;
}

/* The style for the required star */
.form .ssGwt-RequiredIndicator {
    vertical-align: top;
    display: inline-block;
    font-family: sans-serif;
    font-size: 12px;
    font-weight: bold;
    padding-left: 4px;
    color: #DC8726;
}
```