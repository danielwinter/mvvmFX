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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.util.StringConverter;

/**
 * Element that you can use in a View Model to transform any list to a string
 * representation which can be bound to UI Elements like {@link ChoiceBox} or
 * {@link ListView}. <b>You should only expose the {@link #stringListProperty()}
 * and/or the {@link #selectedIndexProperty()} to the view, otherwise you create
 * a visibility of the view to the model. If you want to expose it more
 * convenient, use the {@link ISelectableStringList} interface to hide all
 * dependencies to the model. Create something like this in your View Model:
 * 
 * <code>
 * public SelectableStringList stringListProperty(){
 * 		return selectableItemListInstance;
 * }
 * </code>
 * 
 * </b> You have to provide a {@link StringConverter} to define how to convert a
 * string. In addition you have properties which represents the actual selection
 * state of a list. You can set either the {@link #selectedIndexProperty()} or
 * the {@link #selectedItemProperty()} and the other will change automatically.
 * 
 * @author sialcasa
 * 
 * @param <ListType>
 *            type of the list elements which should be transformed to a string
 *            list
 */
public class SelectableItemList<ListType> extends ItemList<ListType> implements
		ISelectableStringList {

	// Indeces
	private IntegerProperty selectedIndex = new SimpleIntegerProperty();
	private ObjectProperty<ListType> selectedItem = new SimpleObjectProperty<>();

	/**
	 * Creates a {@link SelectableItemList} by a given list of items and a
	 * string converter.
	 * 
	 * @param itemList
	 *            which should be transformed for the UI
	 * @param stringConverter
	 *            which is used for transformation
	 */
	public SelectableItemList(ObservableList<ListType> itemList,
			final StringConverter<ListType> stringConverter) {
		super(itemList, stringConverter);
		// Order of processing is important!
		selectedItem.set(itemList.get(selectedIndex.get()));
		createIndexEvents();
	}

	// When the index property changed we have to change the selected item too
	// When the selected item changed we want to set the index property too
	private void createIndexEvents() {
		selectedIndex.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> bean,
					Number oldVal, Number newVal) {
				try {
					int index = newVal.intValue();
					ListType item = itemListProperty().get(index);
					selectedItem.set(item);
				} catch (IndexOutOfBoundsException e) {
					// If it was an invalid index, reset to the old value
					selectedIndex.set(oldVal.intValue());
				}
			}
		});

		selectedItem.addListener(new ChangeListener<ListType>() {
			@Override
			public void changed(ObservableValue<? extends ListType> arg0,
					ListType oldVal, ListType newVal) {

				int index = itemListProperty().get().indexOf(newVal);
				if (index != -1) {
					selectedIndex.set(index);
				} else {
					// If item not found - Rollback
					selectedItem.set(oldVal);
				}

			}
		});
	}

	/**
	 * Represents an {@link Integer} which is the current selection index. If
	 * you set another value to the property {@link #selectedItemProperty()}
	 * will change automatically. If the value was an invalid index, the change
	 * will be rollbacked.
	 * 
	 * @return the index property
	 */
	@Override
	public IntegerProperty selectedIndexProperty() {
		return this.selectedIndex;
	}

	/**
	 * Represents the current selected item. If you set another value to the
	 * property {@link #selectedIndexProperty()} will change automatically. If
	 * the value was an invalid item which is not in the itemlist, the change
	 * will be rollbacked.
	 * 
	 * @return the item property
	 */
	public ObjectProperty<ListType> selectedItemProperty() {
		return this.selectedItem;
	}
}
