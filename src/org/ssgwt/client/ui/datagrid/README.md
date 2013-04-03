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

This is a boolean image column. The first param is the image that display if the boolean is true and the second parameter is the image that 
will be displayed if the boolean value is false.

Note: If you would like n imageses to be displayed pass in a null.
```
    String tImageUrl = "imageUrl/for/true.jpg";
    String fImageUrl = "imageUrl/for/false.jpg";

    SSBooleanImageColumn<Contact> nameColumn = new SSBooleanImageColumn<Contact>(tImageUrl, fImageUrl) {
        @Override
        public String getValue(Contact object) {
            return object.name;
        }
    };
    
    table.addColumn(nameColumn, "Name");
```

Add a column with a FilterSortHeader can be done in 2 different ways.
Filter will need to be class variables because they are required in the filter change event handler. This is because all
filter will not have the same functions available to retrieve filter criteria as it is not the work of the data 
grid to handle this. Check the Filter change event section.
```
    TextFilter nameColumnFilter = new TextFilter();
    
    TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
        @Override
        public String getValue(Contact object) {
            return object.name;
        }
    };
    
    // When the one of the filters have been completed the example will be updated to create the FilterSortHeader using a filter object and not null
    FilterSortHeader headerNameTest = new FilterSortHeader("Name", nameColumnFilter); 
    table.addColumn(nameColumn, headerNameTest);
```
__OR__
```
    TextFilter nameColumnFilter = new TextFilter();
    
    TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
        @Override
        public String getValue(Contact object) {
            return object.name;
        }
    };
    
    // When the one of the filters have been completed the example will be updated to create the FilterSortHeader using a filter object and not null
    table.addFilterColumn(nameColumn, "Name", nameColumnFilter);
```

### Adding widgets to the action bar
```
    addContactButton = new Button("Add Contact");
    addContactButton.addClickHandler( new ClickHandler() {
        
        /**
         * Event handler for when the "Add Contact" button is clicked.
         * 
         * @param event - The event of the ClickEvent
         */
        @Override
        public void onClick(ClickEvent event) {
            // Navigate to your Add Contact page
        }
    });

    table.setActionBarWidget(addContactButton);
```

### How to handle the event of the SSDataGrid
Sort events
```
    table.addDataGridSortEvent(new IDataGridEventHandler() {
    
        /**
         * The event that fired on sorting.
         */
        @Override
        public void onDataEvent(DataGridSortEvent event) {
            if (event.getColumn().equals(nameColumn)) {
                if (event.isAscending()) {
                    //Sort ascending
                } else {
                    //Sort descending
                }
            } else {
                // Do stuff for other columns
            }getView().getOrganisationDataGrid()
            
        }
    });
```

Range change events / Paging events
```
    table.addRangeChangeHandler(new RangeChangeEvent.Handler() {
        
       /**
        * The event that fire once the range change on the datagrid.
        */
        @Override
        public void onRangeChange(RangeChangeEvent event) {
            // Retrieve data for the current range and set the data using the setRowData function
        }
    });
```

Filter change events
```
    table.addFilterChangeHandler(new FilterChangeEvent.FilterChangeHandler() {
        
        /**
         * The event that fired on filter change.
         */
        @Override
        public void onFilterChange(FilterChangeEvent event) {
            if (getView().getNameFieldFilter().isFilterActive()) {
                // Filter your data
            } else {
                // Remove Filter from your data
            }
        }
    });
```

Selection change events
```
    table.addDataGridRowSelectionChangedHandler(
            new DataGridRowSelectionChangedEvent.DataGridRowSelectionChangedHandler(){

                @Override
                public void onDataGridRowSelectionChanged(DataGridRowSelectionChangedEvent event) {
                	// Get the data that was selected or unselected
                    event.getChangedRows();
                }
                
            }
    );
```

For the SelectBoxFilter, you will need to call one additional method.
```
    SelectBoxFilter nameColumnFilter = new SelectBoxFilter();
    
    TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
        @Override
        public String getValue(Contact object) {
            return object.name;
        }
    };

    String[] listItems = new String[]{"optionOne", "optionTwo", "optionThree"};
    nameColumnFilter.setListBoxData(listItems);
    
    // When the one of the filters have been completed the example will be updated to create the FilterSortHeader using a filter object and not null
    table.addFilterColumn(nameColumn, "Name", nameColumnFilter);

```
The listItems variable is a String array of items to include in the dropdown list.
If you do not want an empty entry you can turn it off by using
```
    public void setIncludeEmptyToggle(boolean includeEmptyValue);
```
on the filter component

### TODO These still require some work before we can write a help section for them
 * Update the column add section once one of the filters are done

### Current issue we are still working on
 * Internet Explorer 8 seems to move the data in the column to the side when you click on it. Other browsers don't seem to have this problem
