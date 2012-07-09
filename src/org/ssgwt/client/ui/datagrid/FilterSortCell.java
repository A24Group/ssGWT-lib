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

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * The cell that is used to display a Header that allows filtering
 * 
 * @author Johannes Gryffenberg
 * @since 29 June 2012
 */
public class FilterSortCell extends AbstractCell<HeaderDetails> {

    /**
     * Instance of the template
     */
    private static Template template;

    /**
     * Holds an instance of the default resources
     */
    private static Resources DEFAULT_RESOURCES;

    /**
     * Flag indicating if the filter connected to the header is active
     */
    private boolean filterActive = false;

    /**
     * Holds an instance of resources
     */
    private Resources resources;

    /**
     * Holds the filter image that is displayed in the header
     */
    private Image filterImage;
    
    /**
     * The parent element of the Cell
     */
    private Element parent;
    
    /**
     * Holds the header details
     */
    private HeaderDetails headerDetails;
    
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
     * @author Johannes Gryffenberg
     * @since 02 July 2012
     */
    public interface Resources extends ClientBundle {

        @Source("images/Header-Filter-Button_active.png")
        ImageResource filterIconActive();

        @Source("images/Header-Filter-Button_up.png")
        ImageResource filterIconInactive();

        @Source("images/Header-Filter-Button_over.png")
        ImageResource filterIconOver();

        @Source("images/Header-Filter-Button_down.png")
        ImageResource filterIconDown();
    }

    /**
     * Template providing SafeHTML templates to build the widget
     * 
     * @author Johannes Gryffenberg
     * @since 02 July 2012
     */
    interface Template extends SafeHtmlTemplates {
        @Template("<div style=\"display: inline-block\">")
        SafeHtml openContainerTag();
        
        @Template("<div style=\"display: inline-block; padding-top: 2px;\">")
        SafeHtml openContainerTagWithPadding();

        @Template("<div style=\"display: inline; padding-right: 3px;\">")
        SafeHtml openInlineContainerTag();

        @Template("<img src=\"\" />")
        SafeHtml imageTag();

        @Template("<div style=\"display: inline; vertical-align: top;\">{0}</div>")
        SafeHtml header(String columnName);

        @Template("</div>")
        SafeHtml closeContainerTag();
    }

    /**
     * Default class constructor
     */
    public FilterSortCell() {
        this(getDefaultResources());
    }

    /**
     * Class constructor that allows the user to use a custom resource
     * 
     * @param resources - The resources object that should be used
     */
    public FilterSortCell(Resources resources) {
        super("mousedown", "mouseover", "mouseout", "mouseup");
        this.resources = resources;
        if (template == null) {
            template = GWT.create(Template.class);
        }
    }

    /**
     * The function that renders the content of the Cell
     */
    @Override
    public void render(Context context, HeaderDetails value, SafeHtmlBuilder sb) {
        this.headerDetails = value;
        if (filterActive) {
            filterImage = new Image(resources.filterIconActive());
        } else {
            filterImage = new Image(resources.filterIconInactive());
        }
        if (value.filterWidget != null) {
            value.filterWidget.setParentHeader(this);
        }
        filterImage.getElement().setAttribute("name", "filterIcon");
        sb.append(template.openContainerTag());
        sb.append(template.openContainerTagWithPadding());
        sb.append(template.openInlineContainerTag());
        sb.appendHtmlConstant(filterImage.toString());
        sb.append(template.closeContainerTag());
        sb.append(template.header(value.label));
        sb.append(template.closeContainerTag());
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
    public void onBrowserEvent(Context context, Element parent, HeaderDetails value, NativeEvent event, ValueUpdater<HeaderDetails> valueUpdater) {
        super.onBrowserEvent(context, parent, value, event, valueUpdater);
        this.parent = parent;
        Element filterImageElement = getImageElement(parent);
        Element filterImageParentElement = filterImageElement.getParentElement();

        if (event.getEventTarget().equals(filterImageElement)) {
            if ("mousedown".equals(event.getType())) {
                replaceImageElement(resources.filterIconDown(), filterImageElement, filterImageParentElement);
            } else if ("mouseover".equals(event.getType())) {
                replaceImageElement(resources.filterIconOver(), filterImageElement, filterImageParentElement);
            } else if ("mouseout".equals(event.getType())) {
                if (filterActive) {
                    replaceImageElement(resources.filterIconActive(), filterImageElement, filterImageParentElement);
                } else {
                    replaceImageElement(resources.filterIconInactive(), filterImageElement, filterImageParentElement);
                }
            } else if ("mouseup".equals(event.getType())) {
                replaceImageElement(resources.filterIconOver(), filterImageElement, filterImageParentElement);
                displayHeader();
            }
        }
    }
    
    /**
     * Displays the filter popup for the header column
     */
    private void displayHeader() {
        if (this.parent.getOffsetWidth() > 300) {
            this.headerDetails.filterWidget.setWidth("300px");
        } else if (this.parent.getOffsetWidth() > 200) {
            this.headerDetails.filterWidget.setWidth("" + this.parent.getOffsetWidth() + "px");
        } else {
            this.headerDetails.filterWidget.setWidth("200px");
        }
        this.headerDetails.filterWidget.center();
        this.headerDetails.filterWidget.setPopupPosition(this.parent.getAbsoluteLeft(), (this.parent.getAbsoluteTop() + this.parent.getOffsetHeight() + 10));
    }

    /**
     * This replaces the image that is displayed in the Cell
     * 
     * @param newImage - The new image the should be displayed
     * @param elementToReplace - The image element that should be replaced
     * @param parentOfElementToReplace - The parent of the element that should be replaced
     */
    public void replaceImageElement(ImageResource newImage, Element elementToReplace, Element parentOfElementToReplace) {
        filterImage.setResource(newImage);
        elementToReplace.removeFromParent();
        parentOfElementToReplace.appendChild(filterImage.getElement());
    }

    /**
     * Setter that updates the state of the filter
     * 
     * @param filterActive - Flag indicating if the filter is active
     */
    public void setFilterActive(boolean filterActive) {
        this.filterActive = filterActive;
        
        Element filterImageElement = getImageElement(parent);
        Element filterImageParentElement = filterImageElement.getParentElement();
        if (filterActive) {
            replaceImageElement(resources.filterIconActive(), filterImageElement, filterImageParentElement);
        } else {
            replaceImageElement(resources.filterIconInactive(), filterImageElement, filterImageParentElement);
        }
    }

    /**
     * Retrieves the element of the image that is being displayed in the Cell
     * 
     * @param parent - The top level container of the Cell
     * 
     * @return The element object that represents the image in the Cell
     */
    protected Element getImageElement(Element parent) {
        
        if (parent.getFirstChildElement().getElementsByTagName("img").getItem(0).getAttribute("name").equals("filterIcon")) {
            return parent.getFirstChildElement().getElementsByTagName("img").getItem(0);
        }
        return parent.getFirstChildElement().getElementsByTagName("img").getItem(1);
    }

}
