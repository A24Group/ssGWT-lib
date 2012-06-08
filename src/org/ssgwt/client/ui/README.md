# Reusable ui components for gwt
Package `org.ssgwt.client.ui`

This package contains reusable ui items such as custom textboxes, list items etc. Below is a list of each element and an example of usage in the ui binder.

## AdvancedTextbox

Extends the built in GWT Textbox to allow a developer to set a *placeholder* for the input box. It allows styling of this placeholder item by applyibng a style name, defaults to placeholder, to the input box while the placeholder is being displayed.

The AdvancedTextbox also overrides the `getText()` to always trim the result before returning it to the calling code.

The Advancedtextbox supports the following additional properties:
 * placeholder via `setPlaceholder()` or the `placeholder` attribure in the ui-binder
 * placeholderStyleName via `setPlaceholderStyleName` or the `placeholderStyleName` attribute in the ui-binder.
