The Menu package contains four classes

## LeftMenuBar
This class displays a left menu that is populated by a list of MenuItems sent in to it. This class will create a LeftMenuItem of each MenuItem that is contained in the list.

## LeftMenuItem
This class contains the styling and action application of the MenuItems for the front end

## MenuItem
This class stores the values used for both the LeftMenuBar and the TopMenuBar buttons

## TopMenuBar
This class will display the the top menu based on buttons made from a list on MenuItems sent in to it

## Current issue we are still working on
 * Internet Explorer 8 seems to apply its own styling to the button top menu bar buttons when it is clicked. This causes the text in the button to move which it should not do unless the style specifically sais so.