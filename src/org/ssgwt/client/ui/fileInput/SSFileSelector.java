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
package org.ssgwt.client.ui.fileInput;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasName;

/**
 * SSFileSelector
 * 
 * Custom file uploader used to upload file.
 * The SSFileSelector needs to be added to a form to be used.
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
     * If validation should be done on the file uploaded
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
     * Class constructor.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  04 Feb 2013
     * 
     * @param label The label to display on the button
     */
    public SSFileSelector(String label) {
        FlowPanel mainContainer = new FlowPanel();
        fileUpload = new FileUpload();
        button = new Button(label);
        mainContainer.add(fileUpload);
        mainContainer.add(button);
        initWidget(mainContainer);
    }
    
    /**
     * Set the style of the fileUploader.
     * This style will contain the opacety 0 and the set width 
     * and height of the button you want to display.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  04 Feb 2013
     * 
     * @param styleName - Style to apply to the file uploader
     */
    public void setFileUploadStyle(String styleName) {
        fileUpload.setStyleName(styleName);
    }

    /**
     * Set the style on the component.
     * This style the button displayed
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  04 Feb 2013
     * 
     * @param style The style name
     */
    public void setStyleName(String style) {
        button.setStyleName(style);
    }
    
    /**
     * Adds a change handler on the component.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  04 Feb 2013
     * 
     * @param handler - Change Handler
     * 
     * @return Handler Registration
     */
    public HandlerRegistration addChangeHandler(ChangeHandler handler) {
        return fileUpload.addChangeHandler(handler);
    }

    /**
     * Set the widget's name
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  04 Feb 2013
     * 
     * @param name The name
     */
    @Override
    public void setName(String name) {
        fileUpload.setName(name);
    }

    /**
     * Get the widget's name.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  04 Feb 2013
     * 
     * @return the name of the widget
     */
    @Override
    public String getName() {
        return fileUpload.getName();
    }
    
    /**
     * Gets the filename selected. 
     * This property has no mutator, as
     * browser security restrictions preclude setting it.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  04 Feb 2013
     * 
     * @return the widget's filename
     */
    public String getFileName() {
        return fileUpload.getFilename();
    }
    
    /**
     * This adds validation on the file extensions that's allowed
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  04 Feb 2013
     * 
     * @param allowedFiles ArrayList containing the extensions allowed
     */
    public void setAllowedFiles(ArrayList<String> allowedFiles) {
        isValidation = true;
        this.allowedFiles = allowedFiles;
    }
    
    /**
     * Getter for the file upload
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  04 Feb 2013
     * 
     * @return the file File Upload
     */
    public FileUpload getFileUpload() {
        return this.fileUpload;
    }
    
    /**
     * Do validation on a file exstenstion.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  04 Feb 2013
     * 
     * @return if the form is valid or not.
     */
    public boolean isFileValid() {
        String fileExtension = fileUpload.getFilename().substring(fileUpload.getFilename().lastIndexOf(".") + 1);
        if (isValidation && !allowedFiles.contains(fileExtension)) {
            return false;
        }
        return true;
    }
    
    /**
     * Sets whether this browser button is visible.
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  12 Feb 2013
     * 
     * @param visible - True to show button false to hide it
     */
    public void displayBrowserButton(boolean visible) {
    	button.setVisible(visible);
    }
    
    /**
     * Add the Mouse Over Handler on the file uploader
     * 
     * @param handler - Mouse Over Handler
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  04 Feb 2013
     * 
     * @return Handler Registration
     */
    public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
        return fileUpload.addDomHandler(handler, MouseOverEvent.getType());
    }

    /**
     * Add the Mouse Down Handler on the file uploader
     * 
     * @param handler - Mouse Down Handler
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  04 Feb 2013
     * 
     * @return Handler Registration
     */
    public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
        return fileUpload.addDomHandler(handler, MouseDownEvent.getType());
    }

    /**
     * Add the Mouse Out Handler on the file uploader
     * 
     * @param handler - Mouse Out Handler
     * 
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  04 Feb 2013
     * 
     * @return Handler Registration
     */
    public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
        return fileUpload.addDomHandler(handler, MouseOutEvent.getType());
    }

}
