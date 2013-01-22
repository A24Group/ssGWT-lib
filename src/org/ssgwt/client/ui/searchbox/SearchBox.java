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
package org.ssgwt.client.ui.searchbox;

import java.util.List;

import org.ssgwt.client.ui.ImageButton;
import org.ssgwt.client.ui.searchbox.recorddisplays.SearchBoxRecordWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * A search box that will support live search and the user to pick an object
 * 
 * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
 * @since  22 January 2013
 *
 * @param <T> The type of the VO that will hold the data displayed by the search widget
 */
public abstract class SearchBox<T> extends Composite implements KeyUpHandler, ClickHandler {
    
    /**
     * The minimum characters required for search service call to be made
     */
    private int minCharCount = 3;
    
    /**
     * The text box the user will type his search in
     */
    @UiField
    TextBox textBox;
    
    /**
     * The button that will be used to submit the query
     */
    @UiField(provided=true)
    ImageButton submitButton;
    
    /**
     * The object that was selected by the user
     */
    T selectedObject = null;
    
    /**
     * Instance of the UiBinder
     */
    private static Binder uiBinder = GWT.create(Binder.class);
    
    /**
     * Holds an instance of the default resources
     */
    private static SearchBoxResources DEFAULT_RESOURCES;
    
    /**
     * Holds an instance of resources
     */
    private SearchBoxResources resources;

    /**
     * The selected display item
     */
    private SearchBoxRecordWidget<T> selectedDisplayItem;
    
    /**
     * The request id
     */
    private int requestId;
    
    /**
     * The string the last delay time was created for
     */
    private String previousSearchString = null;
    
    /**
     * A delay time that allows us to prevent service calls if the user types to fast
     */
    private Timer delayedRequest = null;
    
    /**
     * The number of milliseconds a call will be delayed after a key up event in order to prevent extra service calls
     */
    private int requestDelayTime = 100;
    
    /**
     * UiBinder interface for the composite
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since 22 January 2013
     */
    interface Binder extends UiBinder<Widget, SearchBox> {
    }
    /**
     * Class constructor
     * 
     * @param submitButtonLabel - The label that will be displayed on the button
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
     */
    public SearchBox(String submitButtonLabel) {
        this(submitButtonLabel, getDefaultResources());
    }
    
    /**
     * Class constructor
     * 
     * @param submitButtonLabel - The label that will be displayed on the button
     * @param resources - The resources the search box will use
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
     */
    public SearchBox(String submitButtonLabel, SearchBoxResources resources) {
        this.resources = resources;
        this.resources.searchBoxStyle().ensureInjected();
        submitButton = new ImageButton(submitButtonLabel);
        this.initWidget(uiBinder.createAndBindUi(this));
        submitButton.addClickHandler(this);
        textBox.addKeyUpHandler(this);
    } 
    
    /**
     * The resources interface for the text filter
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
     */
    public interface SearchBoxResources extends ClientBundle {
        
        /**
         * Retrieves an implementation of the Style interface generated using the specified css file
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  22 January 2013
         * 
         * @return An implementation of the Style interface
         */
        @Source("SearchBox.css")
        Style searchBoxStyle();
    }
    
    /**
     * The css resource for the text filter
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
     */
    public interface Style extends CssResource {
        
        /**
         * The style for the panel that contains all the elements on the text filter
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  22 January 2013
         * 
         * @return The name of the compiled style
         */
        String submitButton();
        
        /**
         * The style for the panel that contains all the elements on the text filter
         * 
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since  22 January 2013
         * 
         * @return The name of the compiled style
         */
        String textBox();
    }
    
    /**
     * The function that will contain action of the submit
     * 
     * @param selectedObject - The object the user selected
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
     */
    public abstract void onSubmit(T selectedObject);
    
    /**
     * Create an instance on the default resources object if it the
     * DEFAULT_RESOURCES variable is null if not it just return the object in
     * the DEFAULT_RESOURCES variable
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
     * 
     * @return the default resource object
     */
    private static SearchBoxResources getDefaultResources() {
        if (DEFAULT_RESOURCES == null) {
            DEFAULT_RESOURCES = GWT.create(SearchBoxResources.class);
        }
        return DEFAULT_RESOURCES;
    }
    
    /**
     * Getter for the resources instance
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
     * 
     * @return The resources
     */
    public SearchBoxResources getResources() {
        return this.resources;
    }
    
    /**
     * Set the select display item
     * 
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since  22 January 2013
     * 
     * @param selectedDisplayItem - The display item the user clicked on in the drop down
     */
    public void setSelectedDisplayItem(SearchBoxRecordWidget<T> selectedDisplayItem) {
        this.selectedDisplayItem = selectedDisplayItem;
        this.selectedObject = this.selectedDisplayItem.getItemVO();
    }
    
    /**
     * Sets minimum characters required for search service call to be made
     * 
     * @param minCharCount The minimum characters required for search service call to be made
     */
    public void setMinCharRequiredForSearch(int minCharCount) {
        this.minCharCount = minCharCount;
    }
    
    /**
     * The key up event handler
     * 
     * @param event - The key up event that is being handled
     */
    @Override
    public void onKeyUp(KeyUpEvent event){
        if (textBox.getText().length() >= 3 && !textBox.getText().equals(previousSearchString)) {
            previousSearchString = textBox.getText();
            System.out.println("Search string " + textBox.getText());
            if (delayedRequest != null) {
                delayedRequest.cancel();
                delayedRequest = null;
            }
            delayedRequest = new Timer() {
                
                @Override
                public void run() {
                    requestId++;
                    System.out.println("Making request: " + textBox.getText() + " :: " +requestId);
                    retrieveResult(textBox.getText(), requestId);
                    delayedRequest = null;
                }
            };
            delayedRequest.schedule(requestDelayTime);
        } else if (textBox.getText().length() < 3) {
            //TODO: Close the drop down popup and clear data
        }
    }
    
    /**
     * The function that is called to start a service call to return search data
     * 
     * @param searchString - The search string the user entered
     * @param requestId - The id of the request
     */
    public abstract void retrieveResult(String searchString, final int requestId);
    
    /**
     * Set the data on the search box that was returned by the service call
     * 
     * @param searchResults - The search results
     * @param requestId - The id of the request
     */
    public void setData(List<T> searchResults, int requestId) {
        System.out.println("Set data check: " + requestId + " == " + this.requestId);
        if (requestId == this.requestId) {
            System.out.println("Set data for request: " + requestId);
        }
    }
    
    /**
     * Handles the click events dispatched by the submit button
     * 
     * @param event - The click event that is being handled
     */
    @Override
    public void onClick(ClickEvent event) {
        onSubmit(selectedObject);
    }
}
