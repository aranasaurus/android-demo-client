android-demo-client
===================

The android app for my talk on Android WebViews and Java/Javascript Injection

See the [server repo](http://github.com/thatryana/android-demo-server/) for the Flask app server.

Usage
=====

Opening the app shows a WebView and a (Native) TableView. The WebView accesses the
[server](http://github.com/thatryana/android-demo-server/) which returns a webpage with
the first 4 results of a Google Image Search of a query (which can be changed by accessing
/set/<query> on the server from any browser). The user can tap any of these images and
the TableView will display json data passed in from Javascript on the page.

