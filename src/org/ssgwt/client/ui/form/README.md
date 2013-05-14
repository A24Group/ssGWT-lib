### Example of how to use the DynamicForm
```java
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
```css

/* The container that holds all your input fields */
.form {
    
}

/* The style that will be applied to your input field if validation fails on the field */
.form .error {
    border: 1px solid #FF0000 !Important;
}

/* Style for the non-embedded container */
.form .ssGwt-DefaultContainer {
    margin-left: 30px;
}

/* Style for the embedded container */
.form .ssGwt-EmbeddedContainer {
    margin-left: 0px;
}

/* Additional Style for the non-embedded container */
.form .ssGwt-InputExtraNormal {
    -moz-border-radius: 3px;
    border-radius: 3px;
    border: 1px solid #CCCCCC;
}

/* Additional Style for the embedded container */
.form .ssGwt-InputExtraEmbedded {
    border:none;    
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

/* Used to apply horizontal styling */
.form .ssGwt-HorizontalDisplay {
    display: inline-block !Important;
}

```

### Available Input Fields that work with the DynamicForm

#### TextInputField
```java
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
    
    form.addField(nameInputField, "Name:");
```

#### DateInputField
```java
    SSDatePicker datePicker = new SSDatePicker();
    datePicker.setStyleName( "dtPickerSize" );
    DateInputField<Contact> testDateField = new DateInputField<Contact>(datePicker, null, SSDateBox.DEFAULT_FORMAT, true) {

        @Override
        public Date getValue(Contact object) {
            return object.testDate;
        }

        @Override
        public void setValue(Contact object, Date value) {
            object.testDate = value;
        }
    };
    
    form.addField(testDateField, "Test Date Field:");
```

#### RadioBoxField
```java
    //The Date passed in here can be of any tipe that you want the radio button field to return
    RadioBoxField radioButtonField = new RadioBoxField<OrganisationVO, Date>() {

        @Override
        public Class<Date> getReturnType() {
            return Date.class;
        }

        @Override
        public Date getValue(OrganisationVO object) {
            return object.value;
        }

        @Override
        public void setValue(OrganisationVO object, Date value) {
            object.value = value;
        }
    };
    
    //Create any InputField to add to the RadioBoxField or some of the special
    //InputField's for the RadioBoxField like the one below.
    //This is a label that implements InputField and will return a date.
    //The use of this is for n radio button component with different option
    //fiels but to have then all return the same tipe. Thus the DateLabel
    //can be used with a DateInputField and both will return a date.
    DateLabel dateLabel = new DateLabel() {
            
        @Override
        public Date getValue() {
            return new Date();
        }
    };
    dateLabel.setText("Label that will return the current date");
    
    //add dateLabel option to the RadioBoxField
    radio.addOption(dateLabel);
    
    form.addField(radioButtonField, "Test Radio Button Field:");
```

#### MonthDateInputField
The montDateInputField can typically be used where the actual day of the month is not important for example when
giving your education details. The day you started/ended is not really relevant.
```java
    dateStartedField = new MonthDateInputField<OrganisationVO>() {

        @Override
        public Date getValue(OrganisationVO object) {
            return TriageDate.restFormatStringToDate(object.dStartDate);
        }

        @Override
        public void setValue(OrganisationVO object, Date value) {
            object.dStartDate = TriageDate.dateToRestFormat(value);
        }
    };
    dateStartedField.setDateFormat("MMMM yyyy"); //Default to "MMMM yyyy"
    dateStartedField.setMaxDate(maxDate); //Default to current year
    dateStartedField.setMinDate(minDate); //Default to 1930 
    
    form.addField(testDateField, "Test Month Date Field:");
```

#### TimePickerInputField
The TimePickerInputField can typically be used where you want to select the time of the date by using a spinner. The date passed in from the constructor is the value used to scrole from
```java
    TimePicker customTimePicker = new TimePicker(
	new Date(),
	null, 
	DateTimeFormat.getFormat("HH"), 
	DateTimeFormat.getFormat("mm"), 
	null
    ) {

       @Override
       public Date getValue(OrganisationVO object) {
           // Do date logic
       }

       @Override
       public void setValue(OrganisationVO object, Date value) {
	   // Do date logic
       }
    };
    
    form.addField(customTimePicker, "Test Month Date Field:");
```
