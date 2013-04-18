package org.ssgwt.client.ui.form;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * A Dropdown input field for the DynamicForm
 *
 * @author Alec Erasmus<alec.erasmus@a24group.com>
 * @since 17 Aug 2012
 *
 * @param <T> The object type the Dynamic form uses to get values from updates the value of the fields on
 * @param <ListItemType> The object type that display in the list
 */
public abstract class DropDownInputField<T, ListItemType> extends ListBox implements HasValue<String>, InputField<T, String> {

    /**
     * The value from the object that should displayed on the input field
     */
    private boolean required = false;

    /**
     * The data displaying in the list
     */
    private List<ListItemType> data;

    /**
     * This is a hash map containing the ListItemType mapped to a id
     */
    private final HashMap<String ,ListItemType> dataMap = new HashMap<String ,ListItemType>();

    /**
     * The value displayed if no value is selected
     */
    public String prompt = "--please select an item--";

    /**
     * Class Constructor
     */
    public DropDownInputField() {
        super();
        setPrompt();
    }

    /**
     * Class Constructor
     *
     * @param required - The value from the object that should the displayed on the input field
     */
    public DropDownInputField(boolean required) {
        super();
        setRequired(required);
        setPrompt();
    }

    /**
     * Retrieve the value from the object that should the displayed on the input field
     *
     * @param object - The object the value should be retrieved from
     *
     * @return The value that should be displayed ob the field
     */
    @Override
    public abstract String getValue(T object);

    /**
     * Sets the value from the input field on the object
     *
     * @param object - The object the value was retrieved from
     * @param value - The value that is currently being displayed on the input field
     */
    @Override
    public abstract void setValue(T object, String value);

    /**
     * Get the value displayed on the list
     *
     * @param object - The object in the list
     *
     * @return The list item displayed item
     */
    public abstract String getListLabel(ListItemType object);

    /**
     * Get the item selected in the list's id
     *
     * @param object - The object in the list
     *
     * @return The items id
     */
    public abstract String getListId(ListItemType object);

    /**
     * Set the prompt of the box
     */
    public abstract void setPrompt();

    /**
     * Set the data in the list
     *
     * @param data - The data to display on the list
     */
    public void setListBoxItems(List<ListItemType> data) {
         this.data = data;
         this.clear();
         this.addItem(prompt, "");
         for (ListItemType listItem : this.data) {
            this.dataMap.put(getListId(listItem), listItem);
            this.addItem(getListLabel(listItem), getListId(listItem));
         }
    }

    /**
     * Get the object set on the drop down by the getListId function id
     *
     * @param objectId - This is the id of the object returned by the getListId function
     *
     * @return the object for the key
     */
    public ListItemType getListItemObject(String objectId) {
        return this.dataMap.get(objectId);
    }

    /**
     * Retrieve the flag that indicates whether the input field is required or not
     *
     * @return The flag that indicates whether the input field is required or not
     */
    @Override
    public boolean isRequired() {
        return required;
    }

    /**
     * Set as readOnly
     *
     * @param readOnly - Flag to indicate whether the field should be read only
     */
    @Override
    public void setReadOnly(boolean readOnly) {
        super.setEnabled(!readOnly);
    }

    /**
     * Retrieve the flag that indicates whether the field is read only
     *
     * @return The flag that indicates whether the field is read only
     */
    @Override
    public boolean isReadOnly() {
        return !super.isEnabled();
    }

    /**
     * Sets the flag that indicates whether the input field is required or not
     *
     * @param required - The flag that indicates whether the input field is required or not
     */
    @Override
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * Retrieve the input field as a widget
     *
     * @return The input field as a widget
     */
    @Override
    public Widget getInputFieldWidget() {
        return this;
    }

    /**
     * Retrieve the class type the input field returns
     *
     * @return The class type the input field returns
     */
    @Override
    public Class<String> getReturnType() {
        return String.class;
    }

    /**
     * Gets this object's value.
     *
     * @return the object's value
     */
    @Override
    public String getValue() {
        if (getSelectedIndex() != -1) {
            return getValue(getSelectedIndex());
        } else {
            return null;
        }
    }

    /**
     * Sets this object's value without firing any events. This should be
     * identical to calling setValue(value, false).
     * <p>
     * It is acceptable to fail assertions or throw (documented) unchecked
     * exceptions in response to bad values.
     * <p>
     * Widgets must accept null as a valid value. By convention, setting a widget to
     * null clears value, calling getValue() on a cleared widget returns null. Widgets
     * that can not be cleared (e.g. {@link CheckBox}) must find another valid meaning
     * for null input.
     *
     * @param value the object's new value
     */
    @Override
    public void setValue(String value) {
        if (value == null) {
            setSelectedIndex(0);
        } else {
            for (int i = 0; i < getItemCount(); i++) {
                if (value.equals(getValue(i))) {
                    setSelectedIndex(i);
                }
            }
        }
    }

    /**
     * Sets this object's value. Fires
     * {@link com.google.gwt.event.logical.shared.ValueChangeEvent} when
     * fireEvents is true and the new value does not equal the existing value.
     * <p>
     * It is acceptable to fail assertions or throw (documented) unchecked
     * exceptions in response to bad values.
     *
     * @param value the object's new value
     * @param fireEvents fire events if true and value is new
     */
    @Override
    public void setValue(String value, boolean fireEvents) {
        setValue(value);
    }

    /**
     * Add a handerler on the chanch of the data selected
     *
     * @param handler - The value change handler
     *
     * @return null
     */
    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        return null;
    }
}
