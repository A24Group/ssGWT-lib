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
