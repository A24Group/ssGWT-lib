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
package org.ssgwt.client.ui.menu;

import java.util.List;
import com.google.gwt.user.client.Command;

/**
 * Menu Item Interface.
 * 
 * Represents a single menu item for the menu structure. All models that are to be used with the menu
 * components need to implement this interface.
 * 
 * This interface allows the developer using the component to decide on the implementation for retrieving
 * the data, whether it be from a restful web service using Resty or from a local file via Piriti.
 * 
 * @author Jaco Nel <jaco.nel@a24group.com>
 * @since 26 September 2012
 */
public interface MenuItemInterface {

	/**
     * Getter for the place name of the menu item
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 26 September 2012
     * 
     * @return the placename of the menu item
     */
    public String getPlaceName();
    
    /**
     * Getter for label of the menu item
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 26 September 2012
     * 
     * @return The label of the menu item
     */
    public String getLabel();
    
    /**
     * Getter for the order of the current menu item
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 26 September 2012
     * 
     * @return The order of the current menu item
     */
    public int getOrder();
    
    /**
     * Getter for the default selected state of the menu item
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 26 September 2012
     * 
     * @return Whether the menu item should be defaultly selected
     */
    public boolean isDefaultSelected();
   
    /**
     * Getter for the command action of the menu item
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 26 September 2012
     * 
     * @return The command action for the menu item
     */
    public Command getCommand();
    
    /**
     * Getter for the selected image of the menu item
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 26 September 2012
     * 
     * @return The image of the menu item when it is selected
     */
    public String getSelectedImage();

    /**
     * Getter for the unselected image of the menu item
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 26 September 2012
     * 
     * @return The unselected image of the menu item
     */
    public String getUnSelectedImage();
    
    /**
     * Getter for the sub menu items of this menu item
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 26 September 2012
     * 
     * @return The sub menu items of this menu item
     */
    public List<MenuItemInterface> getSubMenus();
    
    /**
     * Getter for the reference name for the menu item
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 26 September 2012
     * 
     * @return The reference name for the menu item
     */
    public String getReferenceName();
   
    /**
     * Getter for the notification count
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 26 September 2012
     * 
     * @return The notification count
     */
    public int getNotificationCount();
    
    /**
     * Retrieves the name places that will also cause this left menu item to be selected
     * 
     * @author Jaco Nel <jaco.nel@a24group.com>
     * @since 26 September 2012
     * 
     * @return the name places that will also cause this left menu item to be selected
     */
    public List<String> getReferencedNamePlaces();
    
    /**
     * Setter for the command action of the menu item
     * 
     * @param command - The command action of the menu item
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 9 July 2012
     */
    public void setCommand(Command command);
    
    /**
     * Setter for the default selected state of the menu item
     * 
     * @param defaultSelected - The default selected state of the menu item
     * 
     * @author Michael Barnard <michael.barnard@a24group.com>
     * @since 9 July 2012
     */
    public void setDefaultSelected(boolean defaultSelected);
}