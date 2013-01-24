## SearchBox

### Example
This is a small example of how to use the SearchBox

```Java
SearchBox<TestUserVo> x = new SearchBox<TestUserVo>("Assign") {
	
	/**
	 * The function that holds the logic for when the user presses the enter button or the submit button
	 * 
	 * @param selectedObject - The object the user selected
	 */
	@Override 
	public void onSubmit(TestUserVo selectedObject) {
		System.out.println("The user pressed the submit button " + selectedObject);
	}

	/**
	 * The function that will hold you service call when the service call is completed the setData function should be call to 
	 set the retrieved results. The timer here is just to simulate a real service call.
	 * 
	 * @param selectedObject - The object the user selected
	 */
	@Override
	public void retrieveResult(String searchString, final int requestId) {
		Timer x = new Timer() {
			
			@Override
			public void run() {
				ArrayList<TestUserVo> testData = new ArrayList<TestUserVo>();
				int x = (int)(Math.random() * 11);
				for (int i = 0; i < x; i++) {
					TestUserVo temp = new TestUserVo();
					temp.firstName = "Name" + i;
					temp.lastName = "Surname" + i;
					temp.username = "Username" + i;
					temp.avatarUrl = null;
					testData.add(temp);
				}
				setData(testData, requestId);
			}
		};
		x.schedule(500);
	}
	
	/**
	 * This function creates an the display objects that will be used to display each retrieved result
	 *
	 * @return A display object for a record
	 */
	@Override
	public SearchBoxRecordWidget<TestUserVo> createDisplayWidgetInstance() {
		return new SearchBoxUserRecord<TestUserVo>() {

			@Override
			public String getUsername(TestUserVo itemVO) {
				return itemVO.username;
			}

			@Override
			public String getFirstNameAndLastName(TestUserVo itemVO) {
				return itemVO.firstName + " & " + itemVO.lastName;
			}

			@Override
			public String getUserAvatarUrl(TestUserVo itemVO) {
				return itemVO.avatarUrl;
			}
		};
	}
};
```

### What the abstrct functions should do
At minimum you should implement the onSubmit, retrieveResult and createDisplayWidgetInstance functions to use this component.

The retrieve results is call if the user changed the text in the text box. In this function you should make your service call. 
When your service call(Retrieve your data) is completed the you should call the setData function.

The SearcdhBox is a parameterized type, the type you specify is the type of objects you get from your service call.

A custom display class can be used to display the result record. Each custom display item should extend the SearchBoxRecordWidget.
Currently we only have one of these display items SearchBoxUserRecord. The createDisplayWidgetInstance function should create a new 
instance of the items you want to use to display the records.

The onSubmit function is called each time the user clicks submit button or presses enter.