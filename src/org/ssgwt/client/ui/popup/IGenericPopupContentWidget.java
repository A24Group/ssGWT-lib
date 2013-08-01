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
package org.ssgwt.client.ui.popup;

import com.google.gwt.user.client.ui.Widget;

/**
 * This is the interface to be implemented by all generic popup
 * content widgets
 * 
 * @author Ruan Naude <naudeuran777@gmail.com>
 * @since 15 July 2013
 */
public interface IGenericPopupContentWidget {
    
    /**
     * This will set the parent popup for the widget
     * 
     * @param parent - The parent popup
     * 
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     */
    void setParent(GenericPopup parent);
    
   /**
    * Sets the data required by the popup content widget
    * 
    * @param data - The required data
    * 
    * @author Ruan Naude <naudeuran777@gmail.com>
    * @since 15 July 2013
    */
    void setData(Object data);
    
    /**
     * Return the popup content widget as a widget
     * 
     * @author Ruan Naude <naudeuran777@gmail.com>
     * @since 15 July 2013
     * 
     * @return the component as a widget
     */
    Widget asWidget();
    
}
