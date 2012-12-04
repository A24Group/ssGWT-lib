### How to use the RadioBoxComponent
The RadioBoxComponent is used by the the org.ssgwt.client.ui.form.RadioBoxField class.

When creating an instance of the RadioBoxField class you are required to implement the following functions:

* getReturnType - This function will set the return type for the RadioBoxField. All option fields added to the RadioBoxField should return this type then.
* getValue - This function specify the value of the value object that will be returned
* setValue - This will specify to which value on the value object the RadioBoxField will write its selected radio button's value

This can be done as follow:

```java
    //The Date passed in here can be of any tipe that you want the radio button field to return
    RadioBoxField radioButtonField = new RadioBoxField<ValueObject, Date>() {

        @Override
        public Class<Date> getReturnType() {
            return Date.class;
        }

        @Override
        public Date getValue(ValueObject object) {
            return object.value;
        }

        @Override
        public void setValue(ValueObject object, Date value) {
            object.value = value;
        }
    };
```

### Adding options to the RadioBoxField
Any class that implements InputField and HasValue can be added to the RadioBoxField with the addOption function.

This can be done as follow:
```java
radioButtonField.addOption(InputField);
```

The RadioBoxField can then be added to the DynamicForm.
```java
dynamicForm.addField(radioButtonField, "Choose an option:");
```

### RadioBoxField options with different types

In some cases one might want to add options in the RadioBoxField with different types, for example:

#####Scenario:
```
A DateInputField where a date will be returned and another option that is a label that says "Current date" and should return the current date.
```
Since a label does not return a date a custom class needs to be created for this.

The custom class that needs to be created needs to do the following:
* Extend desired widget - For example in the scenario above this would be Label
* Implement InputField - With the desired return type (For example Date for the above scenario)
* Implement HasValue - With the desired return type (For example Date for the above scenario)

All functions that need to be implemented can be left as created by the Auto-generated method stub except for the getReturnType which should return the desired return type (For example Date for the above scenario).

The getValue function of this class can now be overridden to return the desired value when the class gets instantiated before being added to the RadioBoxField.

Example of DateLabel for the above scenario:
```java
    DateLabel dateLabel = new DateLabel() {
        
        @Override
        public Date getValue() {
            return new Date();
        }
    };
    
    dateLabel.setText("Current date");
    radioButtonField.addOption(dateLabel);
```

