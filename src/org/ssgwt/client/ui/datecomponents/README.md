### Example of how to use the DateTimeComponent
The DateTimeComponent allows you to select a start date and end date and display you the diff between the two dates

```java

   // This is how you create an instance of the DateTimeComponent
   /**
     * Class constructor
     *
     * @param minDate - The minimum start date
     * @param maxDate - The maximum start date
     * @param selectedDate - The default selected date
     * @param minShiftTime - The minimum shift length in milliseconds
     * @param maxShiftTime - The maximum shift length in milliseconds
     */
   DateTimeComponent sateTimeComponent = new DateTimeComponent(
       new Date(SSDatePicker.DEFAULT_MINIMUM_YEAR - 1900, 0, 1), 
       new Date(SSDatePicker.DEFAULT_MAXIMUM_YEAR - 1900, 11, 31), 
       new Date(), 
       0, 
       86400000
   );

   // How to get the selected start date
   Date start = sateTimeComponent.getStartDate();
   // How to get the selected end date
   Date end = sateTimeComponent.getEndDate();
    
```
