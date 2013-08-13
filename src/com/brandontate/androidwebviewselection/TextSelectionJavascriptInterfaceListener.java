/*
 * Copyright (C) 2012 Brandon Tate
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.brandontate.androidwebviewselection;

/**
 * Text Selection Listener Interface
 *
 * @author Brandon Tate
 */
public interface TextSelectionJavascriptInterfaceListener {

	/**
	 * Informs the listener that there was a javascript error.
	 * @param error
	 */
	public abstract void tsjiJSError(String error);
	
	
	/**
	 * The user has started dragging the selection handles.
	 */
	public abstract void tsjiStartSelectionMode();
	
	/**
	 * The user has stopped dragging the selection handles.
	 */
	public abstract void tsjiEndSelectionMode();
	
	/**
	 * Tells the listener to show the context menu for the given range and selected text.
	 * The bounds parameter contains a json string representing the selection bounds in the form 
	 * { 'left': leftPoint, 'top': topPoint, 'right': rightPoint, 'bottom': bottomPoint }
	 * @param range
	 * @param text
	 * @param handleBounds
	 * @param menuBounds
	 */
	public abstract void tsjiSelectionChanged(String range, String text, String handleBounds, String menuBounds);
	
	/**
	 * Sends the content width to the listener.  
	 * Necessary because Android web views don't allow you to get the content width.
	 * @param contentWidth
	 */
	public abstract void tsjiSetContentWidth(float contentWidth);
}
