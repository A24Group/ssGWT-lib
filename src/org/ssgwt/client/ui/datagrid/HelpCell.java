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
package org.ssgwt.client.ui.datagrid;

import org.ssgwt.client.ui.datagrid.event.IHelpEventHandler;
import org.ssgwt.client.ui.datagrid.event.HelpEvent;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Image;

/**
 * The cell that is used to display a Header for help
 * 
 * @author Ruan Naude
 * @since 4 July 2012
 */
public class HelpCell extends AbstractCell<String> implements HasHandlers {

    /**
     * Instance of the template
     */
    private static Template template;

    /**
     * Holds an instance of the default resources
     */
    private static Resources DEFAULT_RESOURCES;

    /**
     * Holds an instance of resources
     */
    private Resources resources;

    /**
     * Holds the help image that is displayed in the header
     */
    private Image helpImage;
    
    /**
     * The handler manager used to handle events
     */
    private HandlerManager handlerManager;

    /**
     * Create an instance on the default resources object if it the
     * DEFAULT_RESOURCES variable is null if not it just return the object in
     * the DEFAULT_RESOURCES variable
     * 
     * @return the default resource object
     */
    private static Resources getDefaultResources() {
        if (DEFAULT_RESOURCES == null) {
            DEFAULT_RESOURCES = GWT.create(Resources.class);
        }
        return DEFAULT_RESOURCES;
    }

    /**
     * A ClientBundle that provides images for this widget.
     * 
     * @author Ruan Naude
     * @since 04 July 2012
     */
    public interface Resources extends ClientBundle {

        @Source("images/Help_button_up.png")
        ImageResource helpIconOut();

        @Source("images/Help_button_up.png")
        ImageResource helpIconUp();

        @Source("images/Help_button_over.png")
        ImageResource helpIconOver();

        @Source("images/Help_button_down.png")
        ImageResource helpIconDown();
    }

    /**
     * Template providing SafeHTML templates to build the widget
     * 
     * @author Ruan Naude
     * @since 04 July 2012
     */
    interface Template extends SafeHtmlTemplates {
        @Template("<div style=\"\">")
        SafeHtml openContainerTag();

        @Template("<img src=\"\" />")
        SafeHtml imageTag();

        @Template("</div>")
        SafeHtml closeContainerTag();
    }

    /**
     * Default class constructor
     */
    public HelpCell() {
        this(getDefaultResources());
    }

    /**
     * Class constructor that allows the user to use a custom resource
     * 
     * @param resources - The resources object that should be used
     */
    public HelpCell(Resources resources) {
        super("mousedown", "mouseover", "mouseout", "mouseup");
        this.resources = resources;
        if (template == null) {
            template = GWT.create(Template.class);
        }
        handlerManager = new HandlerManager(this);
    }

    /**
     * The function that renders the content of the Cell
     */
    @Override
    public void render(Context context, String value, SafeHtmlBuilder sb) {
        helpImage = new Image(resources.helpIconUp());
        
        sb.append(template.openContainerTag());
        sb.appendHtmlConstant(helpImage.toString());
        sb.append(template.closeContainerTag());
    }

    /**
     * Handle a browser event that took place within the cell. The default
     * implementation returns null.
     * 
     * @param context - the {@link Context} of the cell
     * @param parent - the parent Element
     * @param value - the value associated with the cell
     * @param event - the native browser event
     * @param valueUpdater - a {@link ValueUpdater}, or null if not specified
     */
    @Override
    public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater) {
        super.onBrowserEvent(context, parent, value, event, valueUpdater);

        Element helpImageElement = getImageElement(parent);
        Element helpImageParentElement = helpImageElement.getParentElement();

        if (event.getEventTarget().equals(helpImageElement)) {
            if ("mousedown".equals(event.getType())) {
                replaceImageElement(resources.helpIconDown(), helpImageElement, helpImageParentElement);
            } else if ("mouseover".equals(event.getType())) {
                replaceImageElement(resources.helpIconOver(), helpImageElement, helpImageParentElement);
            } else if ("mouseout".equals(event.getType())) {
                replaceImageElement(resources.helpIconOut(), helpImageElement, helpImageParentElement);
            } else if ("mouseup".equals(event.getType())) {
                replaceImageElement(resources.helpIconUp(), helpImageElement, helpImageParentElement);
                fireEvent(new HelpEvent());
            }
        }
    }

    /**
     * This replaces the image that is displayed in the Cell
     * 
     * @param newImage - The new image the should be displayed
     * @param elementToReplace - The image element that should be replaced
     * @param parentOfElementToReplace - The parent of the element that should be replaced
     */
    public void replaceImageElement(ImageResource newImage, Element elementToReplace, Element parentOfElementToReplace) {
        helpImage.setResource(newImage);
        elementToReplace.removeFromParent();
        parentOfElementToReplace.appendChild(helpImage.getElement());
    }

    /**
     * Retrieves the element of the image that is being displayed in the Cell
     * 
     * @param parent - The top level container of the Cell
     * 
     * @return The element object that represents the image in the Cell
     */
    protected Element getImageElement(Element parent) {
        return parent.getFirstChildElement().getElementsByTagName("img").getItem(0);
    }

    /**
     * This is used to fire an event
     * 
     * @param event - The event that needs to be fired
     */
    @Override
    public void fireEvent(GwtEvent<?> event) {
        handlerManager.fireEvent(event);
    }

    /**
     * Adds a handler to the handler manager
     * 
     * @param handler - The handler to be added to the handle manager
     * @return The handle registration 
     */
    public HandlerRegistration addEventHandler(
            IHelpEventHandler handler) {
        return handlerManager.addHandler(HelpEvent.TYPE, handler);
    }
}
