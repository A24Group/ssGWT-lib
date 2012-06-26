# Validation

This pacakge contains reusable validation components. These components include validators that can be called by a ValidationChain or a FormValidator

Each validator implements the ValidatorInterface to ensure that each validator has the `isValid()` method.

## Validation Chain

A ValidationChain allows you to chain together multiple validators to validate a single value.

Some validators may take in additional configuration, this configuration should be passed in throught the `setConfiguration()`. If you create a validator that needs to take in additional configuration, please ensure that your validator extends the AbstractValidator

### Example Usage

```java

	// String validator takes in additional configuration, set up the configuration
        HashMap<String, Integer> strValidatorConfig = new HashMap<String, Integer>();
        strValidatorConfig.put(StringValidator.OPTION_MIN_LENGTH, 1);

        StringValidator strValidator = new StringValidator();
        strValidator.setConfiguration(strValidatorConfig);

	boolean valid = strValidator.isValid("String to be tested")
```

## Form Validator

The FormValidator is used to send in and store multiple fields to be validated through its addField function

These fields can then be validated using the doValidation function

The FormValidator will do this by creating the correct validators instance and then validating the fields based on the configuration set up

# Example Usage

```java
    
    AdvancedTextbox dateInput = new AdvancedTextbox();
    AdvancedTextbox emailInput = new AdvancedTextbox();
    AdvancedTextbox nameInput = new AdvancedTextbox();
    
    HashMap<String, Integer> strValidatorConfig = new HashMap<String, Integer>();
    strValidatorConfig.put(StringValidator.OPTION_MIN_LENGTH, 1);
    
    FormValidator strValidator = new StringValidator();
    
    FormValidator.addField(FormFieldConstants.VALIDATE_DATE_REFERENCE, dateInput, strValidatorConfig, "Custom date validation message", "customErroStyleName");
    FormValidator.addField(FormFieldConstants.VALIDATE_EMAIL_REFERENCE, emailInput, strValidatorConfig, "Custom email validation message"); //will use no error styling
    FormValidator.addField(FormFieldConstants.VALIDATE_STRING_REFERENCE, nameInput, strValidatorConfig); //will use default message and no error styling
    
    String validationMessage = FormValidator.doValidation(); //validates all the fields and breaks and send back validation error message when first validation error is found
	
```