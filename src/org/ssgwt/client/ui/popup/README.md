The Generic Popup

Widgets that are displayed in the Generic popup
need to implement the IGenericPopupContentWidget

## IGenericPopupContentWidget
void setParent(GenericPopup parent);
void setData(Object data);
Widget asWidget();

## GenericPopup

The GenericPopup class can be used to create a popup to display
a widget.
This popup can handle things such as:
	Displaying the popup in the center of the screen
	Displaying the popup relative to a desired component with a 
	arrow pointing from the popup to the component
	Closing the popup when clicking outside the popup
	Closing the popup when hovering outside the popup
	Having a loading state while data is being prepared

The Generic popup's width and height will be determined by the
widget passed into the popup. With that said please note:

NOTE:The Generic popup does not automatically take into account 
items added or removed from the widget during loading, thus 
resizing caused by removing or adding items to the widget needs 
to be handled in the widget itself by calling the 
calculatePopupPosition manually to recalculate the new position 
of the popup

Also the loading state needs to be set in the setData function 
that the widget implements from the IGenericPopupContentWidget 
interface.

## GenericPopup Example

```Java

/**
 * Example, we extend Composite here but component can be any widget
 */
public static class TestWidget extends Composite implements IGenericPopupContentWidget {
    
    Label label1 = new Label("");
    Label label2 = new Label("test");
    public GenericPopup parent;
    private FlowPanel panel;
    
    /**
     * Class constructor
     */
    public TestWidget() {
        panel = new FlowPanel();
        panel.add(label2);
        initWidget(panel);
    }
    
    /**
     * Set the popup widget data
     */
    @Override
    public void setData(final Object data) {
        parent.setLoadingState(true);
        
        //set widget data here from the data passed in
        //since this can be a service call the popup is set in loading state
        //and a timer is used below to simulate a service call.
        Timer timer = new Timer() {
            
            /**
             * Timer used here to simulate a service call.
             */
            @Override
            public void run() {
                String text = "test1";
                label1.setText(text);
                panel.add(label1);
                
                //recalculate popup position since a label
                //got added after popup was already visible on screen
                parent.calculatePopupPosition();
                
                //take popup out of loading state
                parent.setLoadingState(false);
            }
        };
        timer.schedule(1000);

    }
    
    /**
     * Return component as widget
     */
    public Widget asWidget() {
        return this;
    }

    /**
     * Set the parent
     * The set of the parent will be handled by the 
     * popup to allow access in this class to the parent
     */
    @Override
    public void setParent(GenericPopup parent) {
        this.parent = parent;
    }
}
```

## GenericPopup center popup

```Java
    // popupContentWidget - The widget to display in the popup
    // closeOnMouseOut - (optional) Whether to close the popup on mouse out
    // useArrow - (optional) Whether to use a arrow on the popup
    // resource - (optional) The resource to be used for the GenericPopup styling
    GenericPopup popup = new GenericPopup(popupContentWidget, closeOnMouseOut, useArrow, resource);
    
    //to display popup in center of screen
    popup.displayPopup();

    //to hide
    popup.hide();
```

## GenericPopup pointer popup

```Java
    // popupContentWidget - The widget to display in the popup
    // closeOnMouseOut - (optional) Whether to close the popup on mouse out
    // useArrow - (optional) Whether to use a arrow on the popup
    // resource - (optional) The resource to be used for the GenericPopup styling
    GenericPopup popup = new GenericPopup(popupContentWidget, closeOnMouseOut, useArrow, resource);
    
    //To display popup relative to component with a arrow
    // attachToWidget - The widget the popup is attached to
    // closeOnMouseOut - (optional) Whether to close the popup on mouse out
    // useArrow - (optional) Whether to use a arrow on the popup
    popup.displayPopup(attachToWidget, closeOnMouseOut, useArrow);

    //to hide
    popup.hide();
```


NOTE: If all optional values are not used the popup will by default display
in the center of the screen without arrow and close on mouse out but will still
have close on click outside popup. 
