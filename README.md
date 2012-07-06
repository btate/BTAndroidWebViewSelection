<p> 
	I've Seen a lot of people trying to get user selections with a context menu working in Android web views.
The problem lies in Android's use of an intermediate text view that it places between the user
and the web view on selection.  So the javascript in the page doesn't know what's selected. 
</p>

<p> This solution uses a javascript interface to pass touches to the page and effectively cut Android's native 
selection out of the equation.  This has been tested from 2.2 to 4.0.3. </p>

<p> The example uses an html page in the assets folder with the javascript methods included.  The BTWebView class implements 
the interface methods necessary to draw the selection layer with the handles and show the context menu. Any content you wish to use 
this with will need the following javascript imports.

<pre>
    <script src='jquery.js'></script>
    <script src='rangy-core.js'></script>
    <script src='rangy-serializer.js'></script>
    <script src='android.selection.js'></script>
</pre>
</p>

<p>
	This solution employs a few libraries that deserve recognition.
	
	<ol>
		<li>
			The <a href="http://code.google.com/p/rangy/">rangy</a> javascript library by Tim Down.
		</li>
		<li>
			A wonderful <a href="http://blahti.wordpress.com/2011/01/17/moving-views-part-2/">drag and drop</a> library by Bill Lahti.
			<br /> <em>*Note: The version of this library included in this project has been slightly modified.</em>
		</li>
		<li>
			The <a href="http://www.londatiga.net/it/how-to-create-quickaction-dialog-in-android/">Quick Action</a> library by Lorensius Londa.
			<br /> <em>*Note: The version of this library included in this project has been slightly modified.</em>
		</li>
	</ol>
	
</p>

<p> This project is freely available for use. </p>