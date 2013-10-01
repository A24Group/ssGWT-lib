# THE FileSelector
### The FileSelector is a rewrite of the ssFileSelector, using a much better system on javascript injection on a specified GWT widget.

### Usage
Creating a new file upload widget
```java
FileSelector upload = new FileSelector(
    customWidget
);
upload.setName("file"); // This does not matter
upload.setAllowedFileTypes(arrayListOfFIleTypes);
```
**NOTE:** Make sure that no other handlers are attached to the widget

Moving the file browser to another component
```java
upload.reConnectWidgetToFileSelector(
    someOtherWidget
);
```

Listening for fileUploads
```java
upload.addChangeHandler(new ChangeHandler() {
    @Override
    public void onChange(ChangeEvent event) {
        // When a file is uploaded.
        // YOUR CODE HERE        
    }
});
```

Checking uploaded files
```java
boolean cancelClickedAfterFirstBrowse = upload.isFileNone();
boolean fileOfCorrectAllowedMimeType = upload.isFileValid();
String fileName = upload.getFileName();
```


# THE DEPRICATED SSFileSelector
## For future use, please refrain from using the ssFileSelector as it was only build as a temporary fix for a problem. Rather use the former file selector

### Description of features

Custom component that allow styling of the file uploader. 

So this component allows you to create a dummy button to use to display and and style.

Build in validation is also build in on the component that only allow certain file types.  To do validation call the isFileValid() function

### Example of how to use

##Shows how to add to a form
```java
        FormPanel form = new FormPanel();
        SSFileSelector upload = new SSFileSelector("Text to display on the button");
        
        // Note the styling applied on this component is very important to the success to the component
        upload.setStyleName(standardPopupButtonStyle); // The styleing applyed here is the styling applied to the button that you want to display
        
        // Its very important that the styling applied here adds the same margin and size as the button above.
        // Its important that you make the position absolute and opacity 0
        // What's happening here is that the not styleable component is displayed over the button created above but is completely see through
        // Additional hover and active styles can be applied on the mouse over, mouse out and mouse down events.
        upload.setFileUploadStyle(fileUploadForStandardPopupButtonStyle);
        
        form.add(upload);
        
        form.submit();
```

##Example of css to apply to the the SSFileSelector
```java
#This style will be used upload.setStyleName()#
.upload {
    width: 50px;
    height: 50px;
    position: absolute;
    opacity: 0;
}

#This style will be used upload.setFileUploadStyle()#
.buttonUpload {
    width: 50px;
    height: 50px;
}
```
