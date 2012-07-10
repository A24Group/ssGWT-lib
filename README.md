This is an open source project under the Apache2 licence,
make sure to include the following on all files created within
this project:

```
/**
 * Copyright 2012 A24Group
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
```
Easiest is to include this in your auto generated code stub

# The following sections are included in the ssGWT project

## Validation
This pacakge contains reusable validation components. These components include validators that can be called by a ValidationChain or a FormValidator
Each validator implements the ValidatorInterface to ensure that each validator has the `isValid()` method.

### Validation Chain
A ValidationChain allows you to chain together multiple validators to validate a single value.
Some validators may take in additional configuration, this configuration should be passed in throught the `setConfiguration()`. If you create a validator that needs to take in additional configuration, please ensure that your validator extends the AbstractValidator

### Form Validator
The FormValidator is used to send in and store multiple fields to be validated through its addField function
These fields can then be validated using the doValidation function
The FormValidator will do this by creating the correct validators instance and then validating the fields based on the configuration set up

## Menu

#TODO:Add Menu description

## Datagrid

Using the constructor to set the data grid to multi select. An extra first column will be added to the data grid that contains a checkbox. 
The data grid will still use the default style that was designed. The checkbox column is always added as the first column

__NOTE:__ Currently all VO the used with the SSDataGrid should extend AbstractMultiSelectObject like in the example below:
This will add an extra field selected to the object.

Setting and retrieving data from the SSDataGrid is done using the setData and getData functions
The default style for the SSDataGrid will not align the header and content correctly if you don't use a FilterSortHeader. 
All columns will always be set as sortable.

## Current issue we are still working on
 * Internet Explorer 8 seems to move the data in the column to the side when you click on it. Other browsers don't seem to have this problem.
 * Internet Explorer 8 seems to apply its own styling to the button top menu bar buttons when it is clicked. This causes the text in the button to move which it should not do unless the style spesifically sais so.
