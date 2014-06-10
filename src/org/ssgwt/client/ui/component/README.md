The Component package contains classes

## TipBar
This class displays a tip bar, and lists all tip items under one another, This class
also have an action link, with a callback method on clicking the action link.

### How to use
Below is an example of a tip item, with an action label.
```java
TipItem tItem = new TipItem();
tItem.setLabel("This vacancy can be featured.");
tItem.setActionLabel("Click here to feature", new TipBarCallback() {
    /**
     * This function will be called when the action label is clicked
     * 
     * @author Dmitri De Klerk
     * @since  9 June 2014
     */
    @Override
    public void onClickAction() {
        // navigate to make the vacancy featured
    }
});

addTipBarItem(tItem);
```

Below is an example of a tip item, without an action link. 
```java
TipItem tItem = new TipItem();
tItem.setLabel("You have worked 30hours this week.");

addTipBarItem(tItem);
```
