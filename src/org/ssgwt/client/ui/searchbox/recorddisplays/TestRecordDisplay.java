package org.ssgwt.client.ui.searchbox.recorddisplays;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public abstract class TestRecordDisplay<T> extends SearchBoxRecordWidget<T> {
	
	private T object;
	
	Label y = new Label();
	
	public TestRecordDisplay() {
		super();
		FlowPanel x = new FlowPanel();
		x.add(y);
		this.add(x);
	}
	
	@Override
	public void setItemVO(T itemVO) {
		object = itemVO;
		y.setText(message(itemVO));
	}

	@Override
	public T getItemVO() {
		return object;
	}

	@Override
	public String getItemSelectionText() {
		return message(object);
	}

	@Override
	public void setSelectedState(boolean selected) {
		if (selected) {
			this.getElement().getStyle().setProperty("backgroundColor", "#CCC");
		} else {
			this.getElement().getStyle().setProperty("backgroundColor", "#FFF");
		}
	}
	
	public abstract String message(T itemVO);
	
}
