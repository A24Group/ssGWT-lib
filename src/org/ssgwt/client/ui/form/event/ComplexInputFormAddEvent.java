package org.ssgwt.client.ui.form.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.event.shared.HandlerRegistration;

public class ComplexInputFormAddEvent extends
		GwtEvent<ComplexInputFormAddEvent.ComplexInputFormAddHandler> {

	public static Type<ComplexInputFormAddHandler> TYPE = new Type<ComplexInputFormAddHandler>();

	public interface ComplexInputFormAddHandler extends EventHandler {
		void onComplexInputFormAdd(ComplexInputFormAddEvent event);
	}

	public interface ComplexInputFormAddHasHandlers extends HasHandlers {
		HandlerRegistration addComplexInputFormAddHandler(
				ComplexInputFormAddHandler handler);
	}

	public ComplexInputFormAddEvent() {
	}

	@Override
	protected void dispatch(ComplexInputFormAddHandler handler) {
		handler.onComplexInputFormAdd(this);
	}

	@Override
	public Type<ComplexInputFormAddHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<ComplexInputFormAddHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source) {
		source.fireEvent(new ComplexInputFormAddEvent());
	}
}
