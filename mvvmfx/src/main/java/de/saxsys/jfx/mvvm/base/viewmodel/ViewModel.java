/*
 * Copyright 2013 Alexander Casall - Saxonia Systems AG
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 */
package de.saxsys.jfx.mvvm.base.viewmodel;

import de.saxsys.jfx.mvvm.base.view.View;
import de.saxsys.jfx.mvvm.notifications.NotificationCenter;

/**
 * Marker inferface for a View Model. Some additional hints to this layer:
 * 
 * Never create a dependency to the view in this layer - if you have to create a
 * new view after a business step, notify the view with the
 * {@link NotificationCenter} or a callback that the view can create the new
 * view. When you write a new {@link View} you should create the associated
 * {@link ViewModel} with tests before.
 * 
 * @author alexander.casall
 * 
 */
public interface ViewModel {

}
