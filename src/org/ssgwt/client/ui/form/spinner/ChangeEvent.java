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
 * Change event.
 *
 * @param <Value> value type
 */
public class ChangeEvent<Value> extends
    AbstractEvent<FiresChangeEvents, ChangeHandler<Value>> {
  private final Value oldValue;
  private final Value newValue;

  /**
   * Constructor.
   *
   * @param source source
   * @param oldValue old value
   * @param newValue new value
   */
  public ChangeEvent(FiresChangeEvents source, Value oldValue, Value newValue) {
    super(source);
    this.oldValue = oldValue;
    this.newValue = newValue;
  }

  @Override
  public void fire(ChangeHandler e) {
    e.onChange(this);
  }

  /**
   * Gets the new value that will be set.
   *
   * @return the new value
   */
  public Value getNewValue() {
    return newValue;
  }

  /**
   * Gets the old value.
   *
   * @return the old value.
   */
  public Value getOldValue() {
    return oldValue;
  }
}