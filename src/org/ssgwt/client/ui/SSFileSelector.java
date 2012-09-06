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

/**
 * Package for all input control elements and components.
 */
package org.ssgwt.client.ui;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasName;

/**
 * SSFileSelector
 * 
 * The SSFileSelector is an object used as a FileUpload object.
 * Works the same way as what a FileUpload, upload an file but
 * is able to style the button. The File name is not displayed
 * but it is possible to get the file name.
 * 
 * //TODO This class is not done. 
 * 
 * @author Alec Erasmus <alec.erasmus@a24group.com>
 * @since 06 Sep 2012
 */
public class SSFileSelector extends Composite implements HasName, HasChangeHandlers {
    
    /**
     * The fileUpload object that does the actual file upload
     */
    FileUpload fileUpload;
    
    /**
     * The button that is displayed on the screen
     */
    Button button;
    
    /**
     * The form to submit on
     */
    FormPanel form;
    
    /**
     * Flag for when there is a from to submit on, if the button is clicked
     */
    boolean isForm = false;
    
    /**
     * Flag for when there is a from to submit on, if the button is clicked
     */
    boolean isValidation = false;
    
    /**
     * The allowed file extensions
     */
    ArrayList<String> allowedFiles;
    
    /**
     * The error string
     */
    String error = null;
    
    /**
     * Class constructor
     * 
     * @param label The label to display on the button
     */
    public SSFileSelector(String label) {
        FlowPanel mainContainer = new FlowPanel();
        fileUpload = new FileUpload();
        fileUpload.setWidth("0px");
        fileUpload.setHeight("0px");
        mainContainer.add(fileUpload);
        
        button = new Button(label);
        button.addClickHandler(new ClickHandler() {
            
            /**
             * The event that fires once you click on the button
             * 
             * @param event - The event that fires
             */
            @Override
            public void onClick(ClickEvent event) {
                SSFileSelector.clickOnInputFile(fileUpload.getElement());
                if (isForm) {
                    
                    /**
                     * Timer to delay
                     */
                    Timer timer = new Timer() {
                    	
                        /**
                         * This method will be called when a timer fires.
                         */
                        public void run() { 
                            if (!getFileSelectedComplete()) {
                                this.schedule(500);
                            } else {
                                if (isValidation) {
                                    error = null;
                                    int x = getFileName().lastIndexOf(".");
                                    if (x == -1) {
                                        setError();
                                    } else {
                                        String extension = getFileName().substring(x + 1);
                                        if (allowedFiles.contains(extension)) {
                                            form.submit();
                                        } else {
                                            setError();
                                        }
                                    }
                                } else {
                                    form.submit();
                                }
                            }
                        } 
                    }; 
                    timer.run();
                }
            }
        });
        mainContainer.add(button);
        initWidget(mainContainer);
    }
    
    /**
     * Call the click function on the element provided
     * 
     * @param element The element to click on
     */
    private static native void clickOnInputFile(Element element) /*-{
        element.click();
    }-*/;
    
    /**
     * Set the style on the component
     * 
     * @param style The style name
     */
    public void setStyleName(String style) {
        button.setStyleName(style);
    }
    
    /**
     * Adds a change handler on the component
     * 
     * @param handler The handler to add
     */
    public HandlerRegistration addChangeHandler(ChangeHandler handler) {
        return fileUpload.addChangeHandler(handler);
    }

    /**
     * Set the widget's name 
     * 
     * @param name The name
     */
    @Override
    public void setName(String name) {
        fileUpload.setName(name);
    }

    /**
     * Get the widget's name 
     */
    @Override
    public String getName() {
        return fileUpload.getName();
    }
    
    /**
     * Gets the filename selected by the user. This property has no mutator, as
     * browser security restrictions preclude setting it.
     * 
     * @return the widget's filename
     */
    public String getFileName() {
        return fileUpload.getFilename();
    }
    
    /**
     * If the form is set on the object the submit will be called on the selection of the file
     * 
     * @param form The form to call submit on
     */
    public void setFormToSubmit(FormPanel form) {
        this.isForm = true;
        this.form = form;
    }
    
    /**
     * This adds validation on the file extensions that's allowed
     * 
     * @param allowedFiles ArrayList containing the extensions allowed
     */
    public void setAlowdFiles(ArrayList<String> allowedFiles) {
        isValidation = true;
        this.allowedFiles = allowedFiles;
    }
    
    /**
     * Set the error message for validation error
     */
    public void setError() {
        // TODO getter for this function
        error = "The file does not have a valid extension";
    }
    
    /**
     * This function must check that the file upload is completed
     * Need to check the last uploaded file is not the same file
     */
    public boolean getFileSelectedComplete() {
        // TODO
        return false;
    }
}
