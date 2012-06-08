# Reusable ui components for gwt
Package `org.ssgwt.client.ui`

This package contains reusable ui items such as custom textboxes, list items etc. Below is a list of each element and an example of usage in the ui binder.

## AdvancedTextbox

### Description of features

Extends the built in GWT Textbox to allow a developer to set a *placeholder* for the input box. It allows styling of this placeholder item by applyibng a style name, defaults to placeholder, to the input box while the placeholder is being displayed.

The AdvancedTextbox also overrides the `getText()` to always trim the result before returning it to the calling code.

### Description of additional attributes

The Advancedtextbox supports the following additional properties:
 * placeholder via `setPlaceholder()` or the `placeholder` attribure in the ui-binder
 * placeholderStyleName via `setPlaceholderStyleName` or the `placeholderStyleName` attribute in the ui-binder.

### Example of usage in UI-Binder

```xml
<!DOCTYPE ui:UiBinder SYSTEM "http://dl.google.com/gwt/DTD/xhtml.ent">

<ui:UiBinder xmlns:ui='urn:ui:com.google.gwt.uibinder'
	xmlns:g='urn:import:com.google.gwt.user.client.ui'
	xmlns:s='urn:import:org.ssgwt.client.ui'>

	<g:FlowPanel styleName="loginWidget" width="100%" height="100%">
		<s:AdvancedTextbox  placeholder="Username" placeholderStyleName="placeholder" />
	</g:FlowPanel>
</ui:UiBinder>

```
