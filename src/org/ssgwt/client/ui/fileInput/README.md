
## SSFileSelector

### Description of features

Component that allow a developer to upload a file and style the button. The main issue was not uploading the file.  With the existing component that was easy, styling the component was in the other was hand not possible.

So this component allows you to create a dummy button to use and and style.

Build in validation is also build in on the component that only allow certain file types.  To do validation call the isFileValid() function

### Example of how to use

```java
// First you need a form to do the service call.  Note that i did not call the setters and other form functions direct

			// Set the allowed Extensions in a list
            ArrayList<String> listExtensions = new ArrayList<String>();
            listExtensions.add("jpg");
            listExtensions.add("gif");
            listExtensions.add("png");
            imageUploadPopupPresenter.setAllowedFiles(listExtensions);
            
            // Set the method to use
            // form.setMethod(method) the method beeing called on the form
            imageUploadPopupPresenter.setRestMethod(FormPanel.METHOD_POST);
            
            // Set the encoding type
            // form.setEncoding("applcation/json"); the method beeing called on the form
            imageUploadPopupPresenter.setEncoding("applcation/json");
            
            // The path
            // form.setAction(url) the method beeing called on the form
            imageUploadPopupPresenter.setServicePath("http://YourDomain/rest/images");

```

##Shows how to add to a form
```java
        FormPanel form = new FormPanel();
        SSFileSelector upload = new SSFileSelector("Text to display on the button");
        
        // Note the styling applied on this component is very important to the success to the component
        upload.setStyleName(standardPopupButtonStyle); // The styleing applyed here is the styling applied to the button that you want to display
        
        // Its very important that the styling applied here add the same margin and size as the button above.
        // Its important that you make the position absolute and opacity 0
        // What's happening here is that the unstyle able component is displayed over the button created above but is completely see through
        // Additional hover and active styles can be applied on the mouse over, mouse out and mouse down events.
        upload.setFileUploadStyle(fileUploadForStandardPopupButtonStyle);
        
        form.add(upload);
        
        form.submit();
```
