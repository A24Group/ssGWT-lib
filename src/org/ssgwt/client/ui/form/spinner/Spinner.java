/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.ssgwt.client.ui.form.spinner;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ImageBundle;
import com.google.gwt.user.client.ui.Widget;

/**
 * The {@link Spinner} provide two arrows for in- and decreasing values. A
 * linked {@link SpinnerListener}
 */
public class Spinner {
    /**
     * Default resources for spinning arrows.
     */
    public interface SpinnerResources extends ImageBundle {
        @Resource("arrowDown.png")
        AbstractImagePrototype arrowDown();

        @Resource("arrowDownDisabled.png")
        AbstractImagePrototype arrowDownDisabled();

        @Resource("arrowDownHover.png")
        AbstractImagePrototype arrowDownHover();

        @Resource("arrowDownPressed.png")
        AbstractImagePrototype arrowDownPressed();

        @Resource("arrowUp.png")
        AbstractImagePrototype arrowUp();

        @Resource("arrowUpDisabled.png")
        AbstractImagePrototype arrowUpDisabled();

        @Resource("arrowUpHover.png")
        AbstractImagePrototype arrowUpHover();

        @Resource("arrowUpPressed.png")
        AbstractImagePrototype arrowUpPressed();
    }

    private static final int INITIAL_SPEED = 7;

    private final SpinnerResources images;
    private final Image decrementArrow = new Image();
    private final Image incrementArrow = new Image();

    private final List<SpinnerListener> spinnerListeners = new ArrayList<SpinnerListener>();
    private int step, minStep, maxStep, initialSpeed = 7;
    private long value, min, max;
    private boolean increment;
    private final boolean constrained;
    private boolean enabled = true;

    private final Timer timer = new Timer() {
        private int counter = 0;
        private int speed = 7;

        @Override
        public void cancel() {
            super.cancel();
            speed = initialSpeed;
            counter = 0;
        }

        @Override
        public void run() {
            counter++;
            if (speed <= 0 || counter % speed == 0) {
                speed--;
                counter = 0;
                if (increment) {
                    increase();
                } else {
                    decrease();
                }
            }
            if (speed < 0 && step < maxStep) {
                step += 1;
            }
        }
    };

    private final MouseDownHandler mouseDownHandler = new MouseDownHandler() {

        @Override
        public void onMouseDown(MouseDownEvent event) {
            if (enabled) {
                Image sender = (Image) event.getSource();
                if (sender == incrementArrow) {
                    images.arrowUpPressed().applyTo(sender);
                    increment = true;
                    increase();
                } else {
                    images.arrowDownPressed().applyTo(sender);
                    increment = false;
                    decrease();
                }
                timer.scheduleRepeating(30);
            }
        }
    };

    private final MouseOverHandler mouseOverHandler = new MouseOverHandler() {
        @Override
        public void onMouseOver(MouseOverEvent event) {
            if (enabled) {
                Image sender = (Image) event.getSource();
                if (sender == incrementArrow) {
                    images.arrowUpHover().applyTo(sender);
                } else {
                    images.arrowDownHover().applyTo(sender);
                }
            }
        }
    };

    private final MouseOutHandler mouseOutHandler = new MouseOutHandler() {
        @Override
        public void onMouseOut(MouseOutEvent event) {
            if (enabled) {
                cancelTimer((Widget) event.getSource());
            }
        }
    };

    private final MouseUpHandler mouseUpHandler = new MouseUpHandler() {
        @Override
        public void onMouseUp(MouseUpEvent event) {
            if (enabled) {
                cancelTimer((Widget) event.getSource());
            }
        }
    };

    /**
     * @param spinner
     *            the widget listening to the arrows
     * @param value
     *            initial value
     */
    public Spinner(SpinnerListener spinner, long value) {
        this(spinner, value, 0, 0, 1, 99, false);
    }

    /**
     * @param spinner
     *            the widget listening to the arrows
     * @param value
     *            initial value
     * @param min
     *            min value
     * @param max
     *            max value
     */
    public Spinner(SpinnerListener spinner, long value, long min, long max) {
        this(spinner, value, min, max, 1, 99, true);
    }

    /**
     * @param spinner
     *            the widget listening to the arrows
     * @param value
     *            initial value
     * @param min
     *            min value
     * @param max
     *            max value
     * @param minStep
     *            min value for stepping
     * @param maxStep
     *            max value for stepping
     */
    public Spinner(SpinnerListener spinner, long value, long min, long max,
            int minStep, int maxStep) {
        this(spinner, value, min, max, minStep, maxStep, true);
    }

    /**
     * @param spinner
     *            the widget listening to the arrows
     * @param value
     *            initial value
     * @param min
     *            min value
     * @param max
     *            max value
     * @param minStep
     *            min value for stepping
     * @param maxStep
     *            max value for stepping
     * @param constrained
     *            determines if min and max value will take effect
     */
    public Spinner(SpinnerListener spinner, long value, long min, long max,
            int minStep, int maxStep, boolean constrained) {
        this(spinner, value, min, max, minStep, maxStep, constrained,
                (SpinnerResources) GWT.create(SpinnerResources.class));
    }

    /**
     * @param spinner
     *            the widget listening to the arrows
     * @param value
     *            initial value
     * @param min
     *            min value
     * @param max
     *            max value
     * @param minStep
     *            min value for stepping
     * @param maxStep
     *            max value for stepping
     * @param constrained
     *            determines if min and max value will take effect
     * @param images
     *            the arrows images
     */
    public Spinner(SpinnerListener spinner, long value, long min, long max,
            int minStep, int maxStep, boolean constrained,
            SpinnerResources images) {
        super();
        spinnerListeners.add(spinner);
        this.images = images;
        this.value = value;
        this.constrained = constrained;
        this.step = minStep;
        this.minStep = minStep;
        this.maxStep = maxStep;
        this.min = min;
        this.max = max;
        this.initialSpeed = INITIAL_SPEED;
        incrementArrow.addMouseUpHandler(mouseUpHandler);
        incrementArrow.addMouseDownHandler(mouseDownHandler);
        incrementArrow.addMouseOverHandler(mouseOverHandler);
        incrementArrow.addMouseOutHandler(mouseOutHandler);
        incrementArrow.setStylePrimaryName("incrementArrow");
        images.arrowUp().applyTo(incrementArrow);
        decrementArrow.addMouseUpHandler(mouseUpHandler);
        decrementArrow.addMouseDownHandler(mouseDownHandler);
        decrementArrow.addMouseOverHandler(mouseOverHandler);
        decrementArrow.addMouseOutHandler(mouseOutHandler);
        decrementArrow.setStylePrimaryName("decrementArrow");
        images.arrowDown().applyTo(decrementArrow);
        fireOnValueChanged();
    }

    /**
     * @param listener
     *            the listener to add
     */
    public void addSpinnerListener(SpinnerListener listener) {
        spinnerListeners.add(listener);
    }

    /**
     * @return the image representing the decreating arrow
     */
    public Image getDecrementArrow() {
        return decrementArrow;
    }

    /**
     * @return the image representing the increasing arrow
     */
    public Image getIncrementArrow() {
        return incrementArrow;
    }

    /**
     * @return the maximum value
     */
    public long getMax() {
        return max;
    }

    /**
     * @return the maximum spinner step
     */
    public int getMaxStep() {
        return maxStep;
    }

    /**
     * @return the minimum value
     */
    public long getMin() {
        return min;
    }

    /**
     * @return the minimum spinner step
     */
    public int getMinStep() {
        return minStep;
    }

    /**
     * @return the current value
     */
    public long getValue() {
        return value;
    }

    /**
     * @return true is min and max values are active, false if not
     */
    public boolean isConstrained() {
        return constrained;
    }

    /**
     * @return Gets whether this widget is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param listener
     *            the listener to remove
     */
    public void removeSpinnerListener(SpinnerListener listener) {
        spinnerListeners.remove(listener);
    }

    /**
     * Sets whether this widget is enabled.
     *
     * @param enabled
     *            true to enable the widget, false to disable it
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            images.arrowUp().applyTo(incrementArrow);
            images.arrowDown().applyTo(decrementArrow);
        } else {
            images.arrowUpDisabled().applyTo(incrementArrow);
            images.arrowDownDisabled().applyTo(decrementArrow);
        }
        if (!enabled) {
            timer.cancel();
        }
    }

    /**
     * @param initialSpeed
     *            the initial speed of the spinner. Higher values mean lower
     *            speed, default value is 7
     */
    public void setInitialSpeed(int initialSpeed) {
        this.initialSpeed = initialSpeed;
    }

    /**
     * @param max
     *            the maximum value. Will not have any effect if constrained is
     *            set to false
     */
    public void setMax(long max) {
        this.max = max;
    }

    /**
     * @param maxStep
     *            the maximum step for this spinner
     */
    public void setMaxStep(int maxStep) {
        this.maxStep = maxStep;
    }

    /**
     * @param min
     *            the minimum value. Will not have any effect if constrained is
     *            set to false
     */
    public void setMin(long min) {
        this.min = min;
    }

    /**
     * @param minStep
     *            the minimum step for this spinner
     */
    public void setMinStep(int minStep) {
        this.minStep = minStep;
    }

    /**
     * @param value
     *            sets the current value of this spinner
     * @param fireEvent
     *            fires value changed event if set to true
     */
    public void setValue(long value, boolean fireEvent) {
        this.value = value;
        if (fireEvent) {
            fireOnValueChanged();
        }
    }

    /**
     * Decreases the current value of the spinner by subtracting current step
     */
    protected void decrease() {
        value -= step;
        if (constrained && value < min) {
            value = min;
            timer.cancel();
        }
        fireOnValueChanged();
    }

    /**
     * Increases the current value of the spinner by adding current step
     */
    protected void increase() {
        value += step;
        if (constrained && value > max) {
            value = max;
            timer.cancel();
        }
        fireOnValueChanged();
    }

    private void cancelTimer(Widget sender) {
        step = minStep;
        if (sender == incrementArrow) {
            images.arrowUp().applyTo((Image) sender);
        } else {
            images.arrowDown().applyTo((Image) sender);
        }
        timer.cancel();
    }

    protected void fireOnValueChanged() {
        for (SpinnerListener listener : spinnerListeners) {
            listener.onSpinning(value);
        }
    }
}