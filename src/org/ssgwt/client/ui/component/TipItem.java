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
package org.ssgwt.client.ui.component;

import org.ssgwt.client.ui.component.TipItemInterface;

/**
 * This is the class for the tip item.
 * 
 * @author Dmitri De Klerk <dmitri.deklerk@a24group.com>
 * @since  9 June 2014 
 */
public class TipItem implements TipItemInterface {
    
    /**
     * The text to display on the action label
     */
    public String actionLabelValue;
    
    /**
     * The text to display on the tip item
     */
    public String labelValue;
    
    /**
     * The tip bar callback
     */
    public TipBarCallback callback;
    
    /**
     * Getter for the callback
     * 
     * @author Dmitri De Klerk <dmitri.deklerk@a24group.com>
     * @since  9 June 2014
     */
    public TipBarCallback getCallBack() {
        return this.callback;
    }
    
    /**
     * Getter for the tip item label
     * 
     * @author Dmitri De Klerk <dmitri.deklerk@a24group.com>
     * @since  9 June 2014
     * 
     * @return The label of the tip item
     */
    public String getLabel() {
        return this.labelValue;
    }
    
    /**
     * Getter for the action label
     * 
     * @author Dmitri De Klerk <dmitri.deklerk@a24group.com>
     * @since  9 June 2014
     * 
     * @return The action label
     */
    public String getActionLabel() {
        return this.actionLabelValue;
    }
    
    /**
     * Setter for the label value
     * 
     * @param labelValue - The label value
     */
    public void setLabel(String labelValue) {
        this.labelValue = labelValue;
    }

    /**
     * Setter for the action label value
     * 
     * @param actionLabelValue - The action label value
     * @param callback - The tip bar callback 
     */
    public void setActionLabel(String actionLabelValue, TipBarCallback callback) {
        this.actionLabelValue = actionLabelValue;
        this.callback = callback;
    }
}