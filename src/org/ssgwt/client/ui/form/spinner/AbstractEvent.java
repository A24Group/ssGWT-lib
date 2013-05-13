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

/**
 * Abstract event type.
 *
 * @param <FiresEvent> source of the event
 * @param <HandlerType> type of handler expected for this event
 */
public abstract class AbstractEvent<FiresEvent, HandlerType extends EventHandler> {
  private final FiresEvent source;

  protected AbstractEvent(FiresEvent source) {
    this.source = source;
  }

  /**
   * This is the class the handlers will be looking for.
   *
   * @return the event class
   */
  protected Class getEventClass() {
    return this.getClass();
  }

  /**
   * Gets the source of the event.
   */
  public FiresEvent getSource() {
    return source;
  }

  /**
   * Delegates firing the event to the given handler type.
   *
   * @param e event
   */
  protected abstract void fire(HandlerType e);

  /**
   * Try to fire the given event handler.
   *
   * @throws ClassCastException if the wrong event handler is used
   */
  protected void tryFire(EventHandler handler) {
    try {
      fire((HandlerType) handler);
    } catch (ClassCastException cast) {
      throw new IllegalStateException(this + " cannot be handled by " + handler
          + " as the handler does not support type " + EventHandler.class);
    }
  }
}