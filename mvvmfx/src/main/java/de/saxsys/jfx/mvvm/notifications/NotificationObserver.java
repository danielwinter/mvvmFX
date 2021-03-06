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
package de.saxsys.jfx.mvvm.notifications;

/**
 * Observer for getting notifications.
 * 
 * @author sialcasa
 * 
 */
public interface NotificationObserver {
	/**
	 * Handle the Notification which is passed by the @MVVMNotificationCenter.
	 * An @Object[] could be shipped.
	 * 
	 * @param key
	 *            notification name
	 * @param objects
	 *            which are passed
	 */
	public void receivedNotification(String key, Object... objects);
}
