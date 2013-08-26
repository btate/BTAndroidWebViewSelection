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

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * This javascript interface allows the page to communicate that text has been selected by the user.
 * 
 * @author Brandon Tate
 *
 */
public class TextSelectionJavascriptInterface {

	/** The TAG for logging. */
	private static final String TAG = "TextSelectionJavascriptInterface";
	
	/** The javascript interface name for adding to web view. */
	private final String interfaceName = "TextSelection";
	
	/** The webview to work with. */
	private TextSelectionJavascriptInterfaceListener listener;
	
	/** The context. */
	Context mContext;
	
	
	/**
	 * Constructor accepting context.
	 * @param c
	 */
	public TextSelectionJavascriptInterface(Context c){
		this.mContext = c;
	}
	
	/**
	 * Constructor accepting context and listener.
	 * @param c
	 * @param listener
	 */
	public TextSelectionJavascriptInterface(Context c, TextSelectionJavascriptInterfaceListener listener){
		this.mContext = c;
		this.listener = listener;
	}
	
	/**
	 * Handles javascript errors.
	 * @param error
	 */
    @JavascriptInterface
	public void jsError(String error){
		if(this.listener != null){
			this.listener.tsjiJSError(error);
		}
	}
	
	/**
	 * Gets the interface name
	 * @return
	 */
    @JavascriptInterface
	public String getInterfaceName(){
		return this.interfaceName;
	}
	
	/**
	 * Put the app in "selection mode".
	 */
    @JavascriptInterface
	public void startSelectionMode(){
		
		if(this.listener != null)
			this.listener.tsjiStartSelectionMode();
		
	}
	
	/**
	 * Take the app out of "selection mode".
	 */
    @JavascriptInterface
	public void endSelectionMode(){
		
		if(this.listener != null)
			this.listener.tsjiEndSelectionMode();
		
	}
	
	/**
	 * Show the context menu
	 * @param range
	 * @param text
	 * @param handleBounds
	 * @param menuBounds
	 */
    @JavascriptInterface
	public void selectionChanged(String range, String text, String handleBounds, String menuBounds){
		if(this.listener != null)
			this.listener.tsjiSelectionChanged(range, text, handleBounds, menuBounds);
		
	}

    /**
     * Sets the content width of the page.
     * @param contentWidth
     */
    @JavascriptInterface
	public void setContentWidth(float contentWidth){
		if(this.listener != null)
			this.listener.tsjiSetContentWidth(contentWidth);
	}
	
}
