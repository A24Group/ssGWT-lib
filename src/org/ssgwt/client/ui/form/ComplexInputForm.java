package org.ssgwt.client.ui.form;

import java.util.ArrayList;
import java.util.List;

import org.ssgwt.client.ui.form.event.ComplexInputFormActionEvent;
import org.ssgwt.client.ui.form.event.ComplexInputFormActionEvent.ComplexInputFormActionHandler;
import org.ssgwt.client.ui.form.event.ComplexInputFormAddEvent;
import org.ssgwt.client.ui.form.event.ComplexInputFormCancelEvent;
import org.ssgwt.client.ui.form.event.ComplexInputFormConfirmationEvent;
import org.ssgwt.client.ui.form.event.ComplexInputFormConfirmationEvent.ComplexInputFormConfirmationHandler;
import org.ssgwt.client.ui.form.event.ComplexInputFormFieldAddEvent;
import org.ssgwt.client.ui.form.event.ComplexInputFormFieldAddEvent.ComplexInputFormFieldAddHandler;
import org.ssgwt.client.ui.form.event.ComplexInputFormRemoveEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

/**
 * Complex input form that allows more complex fields to be added like an array of DynamicForm
 *
 * Constructor need the OutterVO, InnerVO, TheField and the class literal of the TheField class
 *
 * @author Alec Erasmus<alec.erasmus@a24group.com>
 * @since 22 November 2012
 *
 * @param <OutterVO> The object type of super form that the ComplexInputForm vo(InnerVO) is mapped to.
 * @param <InnerVO> The object type the inner form uses to get values from updates the value of the fields on
 * @param <TheField> The type of input field.
 */
public abstract class ComplexInputForm<OutterVO, InnerVO, TheField extends ComplexInput<InnerVO>, T>
    extends Composite
    implements
        HasValue<List<InnerVO>>,
        InputField<OutterVO, List>,
        ComplexInputFormAddEvent.ComplexInputFormAddHandler,
        ComplexInputFormRemoveEvent.ComplexInputFormRemoveHandler,
        ComplexInputFormConfirmationEvent.ComplexInputFormConfirmationHasHandlers,
        ComplexInputFormCancelEvent.ComplexInputFormCancelHandler,
        ComplexInputFormFieldAddEvent.ComplexInputFormFieldAddHasHandlers,
        ComplexInputFormFieldAddEvent.ComplexInputFormFieldAddHandler,
        ComplexInputFormActionEvent.ComplexInputFormActionHasHandlers {

    /**
     * Contain the list of the embedded Vos
     */
    private List<InnerVO> innerVOs = new ArrayList<InnerVO>();

    /**
     * Array list contains the fields
     */
    private final ArrayList<TheField> fields = new ArrayList<TheField>();

    /**
     * Used to apply the gray row style
     */
    private static String STYLE_GRAY_ROW = "ssGWT-displayGrayRow";

    /**
     * Used to apply the first row style
     */
    private static String STYLE_FIRST_ROW = "ssGWT-DynamicInputFirstRow";

    /**
     * Main panel
     */
    private final FlowPanel complexInputForm = new FlowPanel();

    /**
     * The class Literal of the type of field on the form
     */
    private Class<?> classLiteral;

    /**
     * Injectioned object
     */
    private T object;

    /**
     * Flag for if the field is required
     */
    private boolean isRequired = false;

    /**
     * Flag for if the field is ReadOnly
     */
    private boolean isReadOnly = false;

    /**
     * Creator used to create the InputFields
     */
    private InputFieldCreator inputFieldCreator;

    /**
     * ComplexInputFormConfirmationHandler that will be added to inner ComplexInputForm forms.
     */
    private ComplexInputFormConfirmationHandler complexInputFormConfirmationHandler;

    /**
     * ComplexInputFormActionHandler that will be added to the inner ComplexInputForms.
     */
    private ComplexInputFormActionHandler complexInputFormActionHandler;

    /**
     * Class constructor
     *
     * Build the form and fields based on the field class literal
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @param classLiteral - Class Literal of the type of field on the form
     */
    public ComplexInputForm(Class<?> classLiteral) {
        this((InputFieldCreator)GWT.create(InputFieldCreator.class), classLiteral);
    }

    /**
     * Class constructor
     *
     * Build the form and fields based on the field class literal
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @param inputFieldCreator - input Field Creator
     * @param classLiteral - Class Literal of the type of field on the form
     */
    public ComplexInputForm(InputFieldCreator inputFieldCreator, Class<?> classLiteral) {
        initWidget(complexInputForm);
        this.classLiteral = classLiteral;
        this.inputFieldCreator = inputFieldCreator;

    }

    /**
     * Set an embedded Object on the the fields created
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @param object - The object to set
     */
    public void setEmbeddedObject(T object) {
        this.object = object;
    }

    /**
     * Return the type of the return type by the input field
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @return the List class literal
     */
    @Override
    public Class<List> getReturnType() {
        return List.class;
    }

    /**
     * Return the field as Widget
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @return the form as a Widget
     */
    @Override
    public Widget getInputFieldWidget() {
        return this.asWidget();
    }

    /**
     * Get the values from the fields and add it to the Vo
     *
     * Populate the inner list of VOs with the data in the fields
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @return a list of the innerVO
     */
    @Override
    public List<InnerVO> getValue() {
        int counter = 0;
        if (innerVOs != null) {
            innerVOs.clear();
        }
        for (TheField field : fields) {
            if (counter == 0) {
                counter++;
                continue;
            }
            innerVOs.add(field.getValue());
        }
        return innerVOs;
    }

    /**
     * Adds the a new field.
     *
     * This is a function that is called from the addField event that adds a
     * new field on the form after the data from the field in index is saved
     * in the array of VOs.
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     */
    public void addField() {
        // Add the value in the current field at index 0 to the list of VO's
        // Index 0 is always the add field and the is the only field that can be in add state.
        if (innerVOs == null) {
            innerVOs = new ArrayList<InnerVO>();
        }
        innerVOs.add(fields.get(0).getValue());
        // After the values in the field is added to the array innerVO set the field to view state
        fields.get(0).setViewState();
        // Create a new add field
        TheField field = createField();
        // Add the required handlers
        addComplexInputFormHandlers(field);
        // Add the new add field at index 0.
        // The field at index 0 is always the add field
        fields.add(0, field);
        // The field needs to be re-added to the main panel because the added
        // field needs to be at the top and cannot be added on a panel at index 0.
        // The index of the field in the array can be re-added to the main panel
        // in the correct order but it first needs to be cleared from the main panel.
        complexInputForm.clear();
        for (TheField inputField : fields) {
            if (!inputField.dynamicFormPanel.isVisible()) {
                inputField.addStyleName(STYLE_GRAY_ROW);
            }
            inputField.removeStyleName(STYLE_FIRST_ROW);
            complexInputForm.add(inputField);
        }
        field.removeStyleName(STYLE_GRAY_ROW);
        field.addStyleName(STYLE_FIRST_ROW);
    }

    /**
     * Add the required handlers to the field so that once the add button or removed button is
     * clicked on the field the form can handle the event.
     *     - ComplexInputFormRemoveHandler
     *     - ComplexInputFormAddHandler
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @param field - The field to add the handlers to
     */
    public void addComplexInputFormHandlers(TheField field) {
        field.addComplexInputFormRemoveHandler(this);
        field.addComplexInputFormAddHandler(this);
        field.addComplexInputFormCancelHandler(this);
        field.addComplexInputFormFieldAddHandler(this);
        addComplexInputFormConfirmationHandlerOnFields(field, complexInputFormConfirmationHandler);
        addComplexInputFormActionHandlerOnFields(field, complexInputFormActionHandler);
    }

    /**
     * This function is called on the remove event the remove the field from the
     * form, field list and the field value form the list of inner VOs.
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @param removeVO - The Vo to remove from the inner VO list
     * @param removeField - The field to remove from the form
     */
    public void removeField(InnerVO removeVO, TheField removeField) {
        innerVOs.remove(removeVO);
        fields.remove(removeField);
        complexInputForm.remove(removeField);
    }

    /**
     * Set the values on the fields within the form.
     * The fields is auto generated for each VO in the list.
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @param value - The list of the inner VO to add to the form
     */
    @Override
    public void setValue(List<InnerVO> value) {
        this.innerVOs = value;
        // Clear the field list
        fields.clear();
        if (value != null) {
            for (InnerVO property : this.innerVOs) {
                generateField(property);
            }
        }
        this.render();
    }

    /**
     * Generate the field based on the VO passed
     * and set the data on the field.
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @param value - The innerVo that contains the data to set on the field
     */
    private void generateField(InnerVO value) {
        // Create the field based on the class literal
        TheField field = createField();
        // Set the value on the field
        field.setValue(value);
        // Add the handlers
        addComplexInputFormHandlers(field);
        // Set the field to view state
        field.setViewState();
        // Add the fields to fields list that will be used by the render function to add the fields on the form
        fields.add(field);
    }

    /**
     * Add the field on the form it self.
     * This is only called on create and on setValue
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     */
    private void render() {
        // Clear all the fields on the form
        complexInputForm.clear();
        // Create the add field and add it at index 0.
        TheField addField = createField();
        addComplexInputFormHandlers(addField);
        fields.add(0, addField);
        // Loop through the field list and add to the form
        for (TheField field : fields) {
            field.addStyleName(STYLE_GRAY_ROW);
            complexInputForm.add(field);
        }
        addField.removeStyleName(STYLE_GRAY_ROW);
        addField.addStyleName(STYLE_FIRST_ROW);
    }

    /**
     * Handler for the ComplexInputFormRemove that is catch from the the event fired on the remove button
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @param event - The event object that was fired
     */
    @Override
    public void onComplexInputFormRemove(final ComplexInputFormRemoveEvent event) {
        ComplexInputFormConfirmationEvent.fire(true, this, new AsyncCallback<T>() {

            /**
             * The onfailure method that will not do anything
             *
             * @author Ashwin Arendse <ashwin.arendse@a24group.com>
             * @since  03 December 2012
             *
             * @param caught - The exception that were caught
             */
            @Override
            public void onFailure(Throwable caught) {
            }

            /**
             * The on success if a user discards his changes
             *
             * @author Ashwin Arendse <ashwin.arendse@a24group.com>
             * @since  03 December 2012
             *
             * @param result - the result of tpe T
             */
            @Override
            public void onSuccess(T result) {
                // Remove a field
                removeField(
                    (InnerVO) event.getRemoveObjectVO(),
                    (TheField) event.getRemoveObjectField()
                );
            }
        });
    }

    /**
     * Function for when a ComplexInputFormConfirmationHandler needed to be added
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @param handler - The handler that can re added
     */
    @Override
    public HandlerRegistration addComplexInputFormConfirmationHandler(ComplexInputFormConfirmationHandler handler) {
        this.complexInputFormConfirmationHandler = handler;
        for (Object field : fields) {
            if (field instanceof ComplexInput) {
                addComplexInputFormConfirmationHandlerOnFields((ComplexInput)field, complexInputFormConfirmationHandler);
            }
        }
        return this.addHandler(handler, ComplexInputFormConfirmationEvent.TYPE);
    }

    /**
     * Add the ComplexInputFormConfirmationHandler to a complexIput inner complex input form
     *
     * @param field - The ComplexInput field to add the ComplexInputFormConfirmationHandler
     * @param handler - The ComplexInputFormConfirmationHandler to add to the ComplexInputForm
     */
    private void addComplexInputFormConfirmationHandlerOnFields(ComplexInput field, ComplexInputFormConfirmationHandler handler) {
        List<InputField> listInputFields = field.getInputFromInputList();
        for (InputField inputField : listInputFields) {
            if (inputField instanceof ComplexInputForm) {
                ((ComplexInputForm) inputField).addComplexInputFormConfirmationHandler(complexInputFormConfirmationHandler);
            }
        }
    }

    /**
     * Sets the message as a variable so that it can be passed on to other components also
     * Adds a ComplexInputFormMessageHandler that can be fired each time if a message needs to be displayed
     * out side the scope of the class
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  29 Aug 2013
     *
     * @param handler - The complex input form message handler
     */
    @Override
    public HandlerRegistration addComplexInputFormActionHandler(ComplexInputFormActionHandler handler) {
        this.complexInputFormActionHandler = handler;
        for (Object field : fields) {
            if (field instanceof ComplexInput) {
                addComplexInputFormActionHandlerOnFields((ComplexInput)field, complexInputFormActionHandler);
            }
        }
        return this.addHandler(handler, ComplexInputFormActionEvent.TYPE);
    }

    /**
     * Add the ComplexInputFormActionHandler to a complexIput inner complex input form
     *
     * @author Alec Erasmus <alec.erasmus@a24group.com>
     * @since  29 Aug 2013
     *
     * @param field - The ComplexInput field to add the ComplexInputFormMessageHandler
     * @param handler - The ComplexInputFormActionHandler to add to the ComplexInputForm
     */
    private void addComplexInputFormActionHandlerOnFields(ComplexInput field, ComplexInputFormActionHandler handler) {
        if (handler != null) {
            field.addComplexInputFormActionHandler(handler);
            List<InputField> listInputFields = field.getInputFromInputList();
            for (InputField inputField : listInputFields) {
                if (inputField instanceof ComplexInputForm) {
                    ((ComplexInputForm) inputField).addComplexInputFormActionHandler(handler);
                }
            }
        }
    }

    /**
     * Adds a ComplexInputFormFieldAddHandler that listen for the ComplexInputFormFieldAddEvent to
     * fire on each time a new field is added
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  1 March 2013
     *
     * @return {@link HandlerRegistration}
     */
    @Override
    public HandlerRegistration addComplexInputFormFieldAddHandler(ComplexInputFormFieldAddHandler handler) {
        return this.addHandler(handler, ComplexInputFormFieldAddEvent.TYPE);
    }

    /**
     * Handler for the ComplexInputFormRemove that is catch from the the event fired on the remove button
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @param event - The event object that was fired
     */
    @Override
    public void onComplexInputFormCancel(ComplexInputFormCancelEvent event) {
        ComplexInputFormConfirmationEvent.fire(false, this, event.getCallback());
    }

    /**
     * Function that listen for the ComplexInputFormFieldAddEvent each time a recored have been added
     * in one of the nesting complex input forms and re-fire the event with the place where the event
     * originated form as the source.
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  1 March 2013
     *
     * @param handler - The handler that can re added
     */
    @Override
    public void onComplexInputFormFieldAdd(ComplexInputFormFieldAddEvent event) {
        fireEvent(event);
    }

    /**
     * Handler for the ComplexInputFormAdd that is catch from the the event fired on the add button
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @param event - The event object that was fired
     */
    @Override
    public void onComplexInputFormAdd(ComplexInputFormAddEvent event) {
        // Add a new field
        addField();
        ComplexInputFormFieldAddEvent.fire(this);
    }

    /**
     * Function that create a field and inject the an object
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     */
    public TheField createField() {
        TheField addField = (TheField) inputFieldCreator.createItem(classLiteral);
        addField.setInjectedObject(object);
        addField.constructor();
        return addField;
    }

    /**
     * Set the values and could fire an event
     *
     * NOTE : This function is not fully implemented
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @param value - The values to set.
     * @param fireEvents - Flag for the firing of events
     */
    @Override
    public void setValue(List<InnerVO> value, boolean fireEvents) {
        setValue(value);
    }

    /**
     * Function for when a ValueChangeHandler needed to be added
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @param handler - The handler that can re added
     */
    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<List<InnerVO>> handler) {
        return null;
    }

    /**
     * This function is forced implemented but does not have a use in the form
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @return false
     */
    @Override
    public boolean isRequired() {
        return this.isRequired;
    }

    /**
     * Set the fields to Required or not
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @param required - If the field is required
     */
    @Override
    public void setRequired(boolean required) {
        this.isRequired = required;
        for (TheField field : fields) {
            field.setRequired(required);
        }
    }

    /**
     * To set the fields to read only or not
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @param readOnly - If the field is readOnly
     */
    @Override
    public void setReadOnly(boolean readOnly) {
        this.isReadOnly = readOnly;
        for (TheField field : fields) {
            field.setReadOnly(readOnly);
        }
    }

    /**
     * Getter for the fields added to the compexInputForm input form.
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  01 March 2013
     *
     * @return the lsit og fields
     */
    public List<TheField> getFields() {
        return this.fields;
    }

    /**
     * Retrun the boolean if the the field is ReadOnly or not
     *
     * @author Alec Erasmus<alec.erasmus@a24group.com>
     * @since  22 November 2012
     *
     * @return if the field is Read Only
     */
    @Override
    public boolean isReadOnly() {
        return this.isReadOnly;
    }

    /**
     * This function will determine whether there is unsaved data on a complex input
     *
     * @author Ruan Naude <ruan.naude@a24group.com>
     * @since  10 Dec 2012
     */
    public boolean hasUnsavedData() {
        for (TheField field : fields) {
            if (field.hasUnsavedData()) {
                return true;
            }
        }
        return false;
    }

    /**
     * This function will update the field labels on the form
     *
     * @author Ruan Naude <nauderuan777@gmail.com>
     * @since 02 July 2013
     */
    public void updateFieldLabels() {
        for (TheField field : fields) {
            field.updateFieldLabels();
        }
    }
}
