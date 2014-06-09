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

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * This class will render a top menu based on a list 
 * of menu items that is passed in to it
 * 
 * @author Dmitri De Klerk <dmitri.deklerk@a24group.com>
 * @since  5 June 2014
 */
public class TipBar extends Composite {
    
    /**
     * Instance of the UiBinder
     */
    private static Binder uiBinder = GWT.create(Binder.class);
    
    /**
     * The default resources holder for the top menu
     */
    private static TipBarResources DEFAULT_RESOURCES;
    
    /**
     * The holder for the custom resources
     */
    private TipBarResources resources;
    
    /**
     * The flow panel used for the tip item list
     */
    @UiField
    FlowPanel tipBar;
    
    /**
     * The horizontal panel used to display the tip bar
     */
    @UiField
    HorizontalPanel tipBarPanel;
    
    /**
     * UiBinder interface for the composite
     * 
     * @author Dmitri De Klerk <dmitri.deklerk@a24group.com>
     * @since  5 June 2014
     */
    interface Binder extends UiBinder<Widget, TipBar> {
    }

    /**
     * A ClientBundle that provides resources for this widget.
     */
    public interface TipBarResources extends ClientBundle {
        
        /**
         * The styles used in this widget.
         */
        @Source("TipBar.css")
        Style tipBarStyle();
    }
    
    /**
     * The css resource standards that should be followed
     * 
     * @author Dmitri De Klerk <dmitri.deklerk@a24group.com>
     * @since  5 June 2014
     */
    public interface Style extends CssResource {
        
        /**
         * The style for a tip bar font item
         *  
         * @return The name of the font style
         */
        String fontStyle();

        /**
         * The style for a tip bar link item
         *  
         * @return The name of the link style
         */
        String linkStyle();
    }
    
    /**
     * The class constructor
     * 
     * @param tipBarItems - The list of tip bar items that needs to be added to the tip bar
     */
    public TipBar(List<TipItemInterface> tipBarItems) {
        this(tipBarItems, getDefaultResources());
    }

    /**
     * The class constructor
     * 
     * @param tipBarItems - The list of tip bar items that needs to be added to the tip bar
     * @param resources - The resources that needs to be used on the tip bar
     */
    public TipBar(List<TipItemInterface> tipBarItems, TipBarResources resources) {
        this.resources = resources;
        this.resources.tipBarStyle().ensureInjected();
        this.initWidget(uiBinder.createAndBindUi(this));
        this.setTipBar(tipBarItems);
    }
    
    /**
     * Setter for the tip bar Items
     * 
     * @param tipItems - The list of tip items that needs to be added to the tip bar
     */
    public void setTipBar(List<TipItemInterface> tipItems) {
        tipBar.clear();
        if (tipItems != null) {
            // if the tip bar items is empty, do not display the tip panel
            if (tipItems.size() == 0) {
                tipBarPanel.setVisible(false);
            } else {
                tipBarPanel.setVisible(true);
                for (TipItemInterface menuItem : tipItems) {

                    final FlowPanel flowPanel = new FlowPanel();
                    final TipItemInterface currentItem = menuItem;

                    final Label label = new Label();
                    label.setText("- " + currentItem.getLabel());
                    label.setStyleName(resources.tipBarStyle().fontStyle());

                    final Label actionLabel = new Label();
                    actionLabel.setText(currentItem.getActionLabel());
                    actionLabel.setStyleName(resources.tipBarStyle().linkStyle());
                    actionLabel.addClickHandler(new ClickHandler() {
                        
                        /**
                         * Will add the callback function for the action label click
                         * 
                         * author Dmitri De Klerk <dmitri.deklerk@a24group.com>
                         * @since 9 June 2014
                         *  
                         * @param event - The click event that fires the callback
                         */
                        public void onClick(ClickEvent event) {
                            currentItem.getCallBack().onClickAction();
                        }
                    });
                    flowPanel.add(label);
                    flowPanel.add(actionLabel);
                    tipBar.add(flowPanel);
                }
            }
        }
    }
    
    /**
     * This function will add a single tip item to the tip bar
     * 
     * @author Dmitri De Klerk <dmitri.deklerk@a24group.com>
     * @since  9 June 2014
     * 
     * @param tipItems - The tip items that needs to be added to the tip bar
     */
    public void addTipBarItem(TipItemInterface tipBarItem) {
        tipBarPanel.setVisible(true);

        FlowPanel flowPanel = new FlowPanel();
        final TipItemInterface currentItem = tipBarItem;

        Label label = new Label();
        label.setText("- " + currentItem.getLabel());
        label.setStyleName(resources.tipBarStyle().fontStyle());
        flowPanel.add(label);

        Label actionLabel = new Label();
        actionLabel.setText(currentItem.getActionLabel());
        actionLabel.setStyleName(resources.tipBarStyle().linkStyle());
        actionLabel.addClickHandler(new ClickHandler() {
            
            /**
             * Will add the callback function for the action label click
             * 
             * author Dmitri De Klerk <dmitri.deklerk@a24group.com>
             * @since 9 June 2014
             * 
             * @param event - The click event that fires the callback
             */
            public void onClick(ClickEvent event) {
                currentItem.getCallBack().onClickAction();
            }
        });
        flowPanel.add(actionLabel);
        
        tipBar.add(flowPanel);
    }
    
    /**
     * Clear the tip bar and hide the tip bar panel, which will hide the 
     * tip icon. 
     * 
     * @author Dmitri De Klerk <dmitri.deklerk@a24group.com>
     * @since  9 June 2014
     */
    public void clearTipBar() {
        tipBar.clear();
        tipBarPanel.setVisible(false);
    }
    
    /**
     * Gets the default tip bar resources
     * 
     * @return The default resources that should be used for the tip bar
     */
    private static TipBarResources getDefaultResources() {
        if (DEFAULT_RESOURCES == null) {
            DEFAULT_RESOURCES = GWT.create(TipBarResources.class);
        }
        return DEFAULT_RESOURCES;
    }
    
}
