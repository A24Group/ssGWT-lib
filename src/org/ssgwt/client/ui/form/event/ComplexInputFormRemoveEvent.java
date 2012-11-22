package org.ssgwt.client.ui.form.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import java.lang.Object;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.event.shared.HandlerRegistration;

public class ComplexInputFormRemoveEvent extends
		GwtEvent<ComplexInputFormRemoveEvent.ComplexInputFormRemoveHandler> {

	public static Type<ComplexInputFormRemoveHandler> TYPE = new Type<ComplexInputFormRemoveHandler>();
	
	private Object removeObjectVO;
	
	private Object removeObjectField;

	public interface ComplexInputFormRemoveHandler extends EventHandler {
		void onComplexInputFormRemove(ComplexInputFormRemoveEvent event);
	}

	public interface ComplexInputFormRemoveHasHandlers extends HasHandlers {
		HandlerRegistration addComplexInputFormRemoveHandler(
				ComplexInputFormRemoveHandler handler);
	}

	public ComplexInputFormRemoveEvent(Object removeObjectVO, Object removeObjectField) {
		this.removeObjectVO = removeObjectVO;
		this.removeObjectField = removeObjectField;
	}

	public Object getRemoveObjectVO() {
		return removeObjectVO;
	}
	
	public Object getRemoveObjectField() {
		return removeObjectField;
	}

	@Override
	protected void dispatch(ComplexInputFormRemoveHandler handler) {
		handler.onComplexInputFormRemove(this);
	}

	@Override
	public Type<ComplexInputFormRemoveHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<ComplexInputFormRemoveHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Object removeObjectVO, Object removeObjectField) {
		source.fireEvent(new ComplexInputFormRemoveEvent(removeObjectVO, removeObjectField));
	}
}
