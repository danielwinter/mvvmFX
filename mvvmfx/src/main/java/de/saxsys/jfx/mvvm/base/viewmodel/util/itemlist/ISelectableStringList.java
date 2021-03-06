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
package de.saxsys.jfx.mvvm.base.viewmodel.util.itemlist;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyListProperty;

/**
 * Interface to hide the visibility to an {@link SelectableItemList} in a view.
 * 
 * @author sialcasa
 * 
 */
public interface ISelectableStringList {

	/**
	 * String list which can be used by the UI to present the data.
	 * 
	 * @return string list
	 */
	public ReadOnlyListProperty<String> stringListProperty();

	/**
	 * Represents an {@link Integer} which is the current selection index.
	 * 
	 * @return the index property
	 */
	public IntegerProperty selectedIndexProperty();
}
