package org.ssgwt.client.ui.searchbox;

import org.ssgwt.client.ui.ImageButton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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
public abstract class SearchBox<T> extends Composite {
    
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
     */
    public SearchBox(String submitButtonLabel) {
        this(submitButtonLabel, getDefaultResources());
    }
    
    /**
     * Class constructor
     * 
     * @param submitButtonLabel - The label that will be displayed on the button
     * @param resources - The resources the search box will use
     */
    public SearchBox(String submitButtonLabel, SearchBoxResources resources) {
        this.resources = resources;
        this.resources.searchBoxStyle().ensureInjected();
        submitButton = new ImageButton(submitButtonLabel);
        this.initWidget(uiBinder.createAndBindUi(this));
        submitButton.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent arg0) {
                onSubmit(selectedObject);
            }
        });
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
     */
    public abstract void onSubmit(T selectedObject);
    
    /**
     * Create an instance on the default resources object if it the
     * DEFAULT_RESOURCES variable is null if not it just return the object in
     * the DEFAULT_RESOURCES variable
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
     * @return The resources
     */
    public SearchBoxResources getResources() {
        return this.resources;
    }
}
