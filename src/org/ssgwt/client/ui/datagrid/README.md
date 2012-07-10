# A Guide to using the SSDataGrid

Creating an instance of the SSDataGrid

Using the default constructor the data grid will use the default style that was designed.
```
    SSDataGrid<Contact> table = new SSDataGrid<Contact>();
```

Using the constructor to set the data grid to multi select a extra first column will be added to the data grid that contains a checkbox. 
The data grid will still use the default style that was designed. The checkbox column is always added as the first column
```
    SSDataGrid<Contact> table = new SSDataGrid<Contact>( true ); // Does have the extra CheckBox column
    SSDataGrid<Contact> table = new SSDataGrid<Contact>( false ); // Doesn't have the extra column
```

The Contact VO in the example is the VO the object will be displaying.
__NOTE:__ Currently all VO the used with the SSDataGrid should extend AbstractMultiSelectObject like in the example below:
This will add an extra field selected to the object.
```
    private static class Contact extends AbstractMultiSelectObject {
        private String address;
        private Date birthday;
        private String name;
        
        public Contact(String name, Date birthday, String address) {
            this.name = name;
            this.birthday = birthday;
            this.address = address;
        }
    }
```

Setting and retrieving data from the SSDataGrid is done using the setData and getData functions
```
    table.setData(Arrays.asList(
        new Contact("John", new Date(80, 4, 12), "123 Fourth Avenue"),
        new Contact("Joe", new Date(85, 2, 22), "22 Lance Ln"),
        new Contact("George", new Date(46, 6, 6), "1600 Pennsylvania Avenue")));
    
    List<Contact> contacts = table.getData();
```

Adding columns to the DataGrid.
The default style for the SSDataGrid will not align the header and content correctly if you don't use a FilterSortHeader. 
All columns will always be set as sortable.

This will add a column to the data grid but will not use the FilterSortHeader and the above mentioned alignment issue will be seen.
```
    TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
        @Override
        public String getValue(Contact object) {
            return object.name;
        }
    };
    
    table.addColumn(nameColumn, "Name");
```

Add a column with a FilterSortHeader can be done in 2 different ways
```
    TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
        @Override
        public String getValue(Contact object) {
            return object.name;
        }
    };
    
    // When the one of the filters have been completed the example will be updated to create the FilterSortHeader using a filter object and not null
    FilterSortHeader headerNameTest = new FilterSortHeader( "Name", null ); 
    table.addColumn(nameColumn, headerNameTest);
```
__OR__
```
    TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
        @Override
        public String getValue(Contact object) {
            return object.name;
        }
    };
    
    // When the one of the filters have been completed the example will be updated to create the FilterSortHeader using a filter object and not null
    table.addFilterColumn(nameColumn, "Name", null);
```

### TODO These still require some work before we can write a help section for them
 * Explain how to listen to sort events and range change events
 * Update the column add section once one of the filters are done
 * Explain how to handle filter change events
 * Explain how to handle selection change events
 * Explain how to add a widget to the action bar

### Current issue we are still working on
 * Internet Explorer 8 seems to move the data in the column to the side when you click on it. Other browsers don't seem to have this problem