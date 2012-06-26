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

package org.ssgwt.client.validation;

import java.util.HashMap;
import com.google.gwt.user.client.ui.Widget;

/**
 * Stores the form field values to be used 
 * to do validation on required fields
 * 
 * @author Michael Barnard
 * @since 22 June 2012
 */
public class FormField {
    
    /**
     * The reference name for the validation
     */
    public String validatorReferenceName;
    
    /**
     * The ui field the validation is done on 
     */
    public Widget uiField;
    
    /**
     * Validation configuration settings
     */
    public HashMap<String, Integer> config;
    
    /**
     * The error message to be displayed
     */
    public String errorMessage;
    
    /**
     * The error style type
     */
    public String errorStyleName;
}