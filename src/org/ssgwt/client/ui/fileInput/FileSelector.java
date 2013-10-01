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

package org.ssgwt.client.ui.fileInput;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.Widget;

/**
 * FileSelector Component
 * 
 * This class is used to attach a file selection window to a specified widget.
 * It can also be used to re-attach that same handler to a different widget.
 * 
 * @author Michael Barnard <michael.barnard@a24group.com>
 * @since  27 September 2013
 */
public class FileSelector extends FileUpload {
    
    /**
     * Stores the part of the identifier for the file selector
     */
    private static final String FILE_SELECTOR_ID = "FileSelectorItem";
    
    /**
     * Stores the unique identifier for the file selector
     */
    private static int uniqueId = 1;
    
    /**
     * The widget that the file browser should be bound to
     */
    private Widget browseWidget;
    
    /**
     * Whether validation should be done on the file uploaded (extensions are specified)
     */
    boolean isValidation = false;
    
    /**
     * The list of allowed file extensions
     */
    ArrayList<String> allowedFiles;
    
    /**
     * Stores the on click action text
     */
    private static final String ON_CLICK = "onclick";
    
    /**
     * Class constructor
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  27 September 2013
     * 
     * @param browseWidget - The widget the file selector needs to be attached to
     */
    public FileSelector(Widget browseWidget) {
        this.isValidation = false;
        this.allowedFiles = null;
        connectWidgetToFileSelector(browseWidget);
        uniqueId++;
    }
    
    /**
     * This method handles the original connection of the widget and the file selector.
     * Any re-attachments will not be done by this method
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  27 September 2013
     * 
     * @param browseWidget - The widget the file selector needs to be attached to
     */
    private void connectWidgetToFileSelector(Widget browseWidget) {
        this.browseWidget = browseWidget;
        String refName = FILE_SELECTOR_ID + uniqueId;
        this.getElement().setId(refName);
        this.browseWidget.getElement().setAttribute(
            ON_CLICK, "document.getElementById('" + refName + "').click()"
        );
        this.setVisible(false);
    }
    
    /**
     * This method handles the re-connection of the different widget and an exsisting file selector.
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since  27 September 2013
     * 
     * @param browseWidget - The widget the file selector needs to be attached to
     */
    public void reConnectWidgetToFileSelector(Widget browseWidget) {
        this.browseWidget.getElement().removeAttribute(ON_CLICK);
        this.browseWidget = browseWidget;
        this.browseWidget.getElement().setAttribute(
            ON_CLICK, "document.getElementById('" + this.getElement().getId() + "').click()"
        );
        this.setVisible(false);
    }
    
    /**
     * Do empty check on a file extention
     * 
     * @author Micahel Barnard <michael.barnard@a24group.com>
     * @since  27 September 2013
     * 
     * @return If the image is of the correct type
     */
    public boolean isFileNone() {
        String fileName = this.getFilename();
        return fileName == null || fileName.length() == 0;
    }
    
    /**
     * Do validation on a file extension
     * 
     * @author Micahel Barnard <michael.barnard@a24group.com>
     * @since  27 September 2013
     * 
     * @return If the image is of the correct type
     */
    public boolean isFileValid() {
        String fileName = this.getFilename();
        String fileExtension = fileName.substring(this.getFilename().lastIndexOf(".") + 1);
        if (isValidation && !allowedFiles.contains(fileExtension)) {
            return false;
        }
        return true;
    }
    
    /**
     * This adds validation on the file extensions that's allowed
     * 
     * @author Micahel Barnard <michael.barnard@a24group.com>
     * @since  27 September 2013
     * 
     * @param allowedFiles ArrayList containing the extensions allowed
     */
    public void setAllowedFileTypes(ArrayList<String> allowedFiles) {
        if (allowedFiles != null && allowedFiles.size() > 0) {
            this.isValidation = true;
            this.allowedFiles = allowedFiles;
        }
    }
}
