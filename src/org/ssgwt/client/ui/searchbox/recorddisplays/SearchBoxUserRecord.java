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
package org.ssgwt.client.ui.searchbox.recorddisplays;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * This is the class for the user records to be displayed in the search box results
 *
 * @author Ruan Naude <nauderuan777@gmail.com>
 * @since 22 Jan 2013
 */
public abstract class SearchBoxUserRecord<T> extends SearchBoxRecordWidget<T> {

    /**
     * Instance of the UiBinder
     */
    private static Binder uiBinder = GWT.create(Binder.class);

    /**
     * The default resource to use for the SearchBoxUserRecord class
     */
    private static SearchBoxUserRecordResources DEFAULT_RESOURCES;

    /**
     * The resource to use for the LeftMenuItem class
     */
    private SearchBoxUserRecordResources resources;

    /**
     * The value object to be used on the search box component
     */
    private T itemVO;

    /**
     * The main container of the search box user record
     */
    @UiField
    FlowPanel searchBoxUserRecord;

    /**
     * The label for the name of the user
     */
    Label nameLabel;

    /**
     * The label for the username of the user
     */
    Label usernameLabel;

    /**
     * The image for the user avatar
     */
    Image avatarImage;

    /**
     * The remove image
     */
    Image removeImage;

    /**
     * UiBinder interface for the composite
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 22 Jan 2013
     */
    interface Binder extends UiBinder<Widget, SearchBoxUserRecord<?>> {
    }

    /**
     * A ClientBundle that provides style for this widget.
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 22 Jan 2013
     */
    public interface SearchBoxUserRecordResources extends ClientBundle {

        /**
         * The style source to be used in this widget
         */
        @Source("SearchBoxUserRecord.css")
        Style searchBoxUserRecordStyle();

        /**
         * Icon used as the default user avatar
         */
        @Source("images/Default_user_icon.png")
        @ImageOptions(flipRtl = true)
        ImageResource defaultUserAvatar();

        /**
         * Icon used as the default user avatar
         */
        @Source("images/deleteButton.png")
        @ImageOptions(flipRtl = true)
        ImageResource removeIcon();
    }

    /**
     * The Css style recourse items to use in this widget
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 22 Jan 2013
     */
    public interface Style extends CssResource {

        /**
         * The style for the panel that contains
         * the whole user record
         *
         * @author Ruan Naude <nauderuan777@gmail.com>
         * @since 22 Jan 2013
         *
         * @return The name of the compiled style
         */
        String searchBoxUserRecord();

        /**
         * The style for the name of the user
         *
         * @author Ruan Naude <nauderuan777@gmail.com>
         * @since 22 Jan 2013
         *
         * @return The name of the compiled style
         */
        String nameLabel();

        /**
         * The style for the username of the user
         *
         * @author Ruan Naude <nauderuan777@gmail.com>
         * @since 22 Jan 2013
         *
         * @return The name of the compiled style
         */
        String usernameLabel();

        /**
         * The style for the user avatar image
         *
         * @author Ruan Naude <nauderuan777@gmail.com>
         * @since 22 Jan 2013
         *
         * @return The name of the compiled style
         */
        String avatarImage();

        /**
         * The style for the remove image
         *
         * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
         * @since 26 Feb 2013
         *
         * @return The name of the compiled style
         */
        String removeImageStyle();

        /**
         * The style for the user record when it is selected
         *
         * @author Ruan Naude <nauderuan777@gmail.com>
         * @since 22 Jan 2013
         *
         * @return The name of the compiled style
         */
        String searchBoxUserRecordSelected();

        /**
         * The style for the username when the record is selected
         *
         * @author Ruan Naude <nauderuan777@gmail.com>
         * @since 22 Jan 2013
         *
         * @return The name of the compiled style
         */
        String usernameLabelSelected();

        /**
         * The style for the avatar when the record is selected
         *
         * @author Ruan Naude <nauderuan777@gmail.com>
         * @since 22 Jan 2013
         *
         * @return The name of the compiled style
         */
        String avatarImageSelected();
    }

    /**
     * This function will check of there is already a default resource to
     * use for the user record and if not create a default resource
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 22 Jan 2013
     *
     * @return The default resource
     */
    private static SearchBoxUserRecordResources getDefaultResources() {
        if (DEFAULT_RESOURCES == null) {
            DEFAULT_RESOURCES = GWT.create(SearchBoxUserRecordResources.class);
        }
        return DEFAULT_RESOURCES;
    }

    /**
     * Class constructor for user record
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 09 July 2012
     */
    public SearchBoxUserRecord() {
        this(getDefaultResources(), false);
    }

    /**
     * Class constructor for user record with custom has remove action flag
     *
     * @param hasRemoveAction Flag to set if the widget has a remove action
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    public SearchBoxUserRecord(boolean hasRemoveAction) {
        this(getDefaultResources(), hasRemoveAction);
    }

    /**
     * Class constructor for user record with custom recource
     *
     * @param resources The resource to be used for the SearchBoxUserRecord
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    public SearchBoxUserRecord(SearchBoxUserRecordResources resources, boolean hasRemoveAction) {
        super();
        this.resources = resources;
        this.resources.searchBoxUserRecordStyle().ensureInjected();
        this.add(uiBinder.createAndBindUi(this));
        //create the labels and image for the user record
        nameLabel = new Label();
        usernameLabel = new Label();
        avatarImage = new Image(resources.defaultUserAvatar());

        //add styls to the user record elements
        searchBoxUserRecord.setStyleName(resources.searchBoxUserRecordStyle().searchBoxUserRecord());
        avatarImage.setStyleName(resources.searchBoxUserRecordStyle().avatarImage());
        nameLabel.setStyleName(resources.searchBoxUserRecordStyle().nameLabel());
        usernameLabel.setStyleName(resources.searchBoxUserRecordStyle().usernameLabel());

        //add the labels and avatar image to the main container
        if (hasRemoveAction) {
            removeImage = new Image(resources.removeIcon());
            removeImage.setStyleName(resources.searchBoxUserRecordStyle().removeImageStyle());
            removeImage.setVisible(false);
            searchBoxUserRecord.add(removeImage);
            this.addMouseOverHandler(new MouseOverHandler() {

                /**
                 * Handler for the mouse over event
                 *
                 * @param event The mouse over event being handled
                 *
                 * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
                 * @since 04 March 2012
                 */
                @Override
                public void onMouseOver(MouseOverEvent event) {
                    removeImage.setVisible(true);
                    setSelectedState(true);
                }
            });

            this.addMouseOutHandler(new MouseOutHandler() {

                /**
                 * Handler for the mouse out event
                 *
                 * @param event The mouse out event being handled
                 *
                 * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
                 * @since 04 March 2012
                 */
                @Override
                public void onMouseOut(MouseOutEvent event) {
                    removeImage.setVisible(false);
                    setSelectedState(false);
                }
            });

            removeImage.addClickHandler(new ClickHandler() {

                /**
                 * Handler for the click event
                 *
                 * @param event The mouse click being handled
                 *
                 * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
                 * @since 04 March 2012
                 */
                @Override
                public void onClick(ClickEvent event) {
                    onRemoveAction();
                }
            });
        }
        searchBoxUserRecord.add(avatarImage);
        searchBoxUserRecord.add(nameLabel);
        searchBoxUserRecord.add(usernameLabel);
    }

    /**
     * Sets the VO for the user record and details to be displayed by the record
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    @Override
    public void setItemVO(T itemVO) {
        this.itemVO = itemVO;
        nameLabel.setText(getFirstNameAndLastName(itemVO));
        if (getUsername(itemVO) != null && !getUsername(itemVO).trim().equals("")) {
            usernameLabel.setText("- " + getUsername(itemVO));
        }
        if (getUserAvatarUrl(itemVO) != null && !getUserAvatarUrl(itemVO).trim().equals("")) {
            avatarImage.setUrl(getUserAvatarUrl(itemVO));
        }
    }

    /**
     * Gets the VO of the user record
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     *
     * @return The user record's VO
     */
    @Override
    public T getItemVO() {
        return this.itemVO;
    }

    /**
     * Returns the text to be returned when the record is selected
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     *
     * @return The record text to be displayed
     */
    @Override
    public String getItemSelectionText() {
        String itemString = getFirstNameAndLastName(itemVO);
        if (getUsername(itemVO) != null && !getUsername(itemVO).trim().equals("")) {
            itemString += " - " + getUsername(itemVO);
        }

        return itemString;
    }

    /**
     * This will set the user record to the selected state by updating style accordingly
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     */
    @Override
    public void setSelectedState(boolean selected) {
        if (selected) {
            searchBoxUserRecord.addStyleName(resources.searchBoxUserRecordStyle().searchBoxUserRecordSelected());
            usernameLabel.addStyleName(resources.searchBoxUserRecordStyle().usernameLabelSelected());
            avatarImage.addStyleName(resources.searchBoxUserRecordStyle().avatarImageSelected());
        } else {
            searchBoxUserRecord.removeStyleName(resources.searchBoxUserRecordStyle().searchBoxUserRecordSelected());
            usernameLabel.removeStyleName(resources.searchBoxUserRecordStyle().usernameLabelSelected());
            avatarImage.removeStyleName(resources.searchBoxUserRecordStyle().avatarImageSelected());
        }
    }

    /**
     * Gets the username of the user to be displayed
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     *
     * @return The username of the user to be displayed
     */
    public abstract String getUsername(T itemVO);

    /**
     * Gets the name of the user to be displayed
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     *
     * @return The name of the user to be displayed
     */
    public abstract String getFirstNameAndLastName(T itemVO);

    /**
     * Gets the url of the user avatar image to be displayed
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since 03 Dec 2012
     *
     * @return The user avatar image to be displayed
     */
    public abstract String getUserAvatarUrl(T itemVO);

    /**
     * The function that is called when the remove button is clicked
     *
     * @author Johannes Gryffenberg <johannes.gryffenberg@gmail.com>
     * @since 26 Feb 2012
     */
    public abstract void onRemoveAction();
}