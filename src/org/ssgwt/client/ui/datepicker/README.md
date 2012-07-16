### DateBox SSDatePicker
To use the SSDatePicker with the normal DateBox you have to use the org.ssgwt.client.ui.datepicker.DateBox
```
    SSDatePicker datePicker = new SSDatePicker();
    datePicker.setStyleName("dtPickerSize");
    DateBox dateBox = new DateBox<Contact>(datePicker, null, SSDateBox.DEFAULT_FORMAT);
```

### SSDateBox with SSDatePicker
The SSDateBox allows for an icon to the right of the input field this is the main difference between the SSDateBox and the DateBox
```
    SSDatePicker datePicker = new SSDatePicker();
    datePicker.setStyleName("dtPickerSize");
    FocusImage dateFieldIcon = new FocusImage();
    dateFieldIcon.setStyleName("styleForTheIcon");
    SSDateBox dateBox = new SSDateBox<Contact>(datePicker, null, SSDateBox.DEFAULT_FORMAT, dateFieldIcon);
```