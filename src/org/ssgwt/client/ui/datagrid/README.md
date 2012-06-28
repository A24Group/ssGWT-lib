# SSDataGrid

This is a generic DataGrid component that supports filtering, paging, multi select and sorting 

## Usage with UI binder
```
    <g:FlowPanel width="100%" height="100%" >
        <d:SSDataGrid width="100%" height="100%" >
            <d:column>
                <d:SSDataGridColumn headerText="First Name" width="30%" dataField="sFirstName" />
            </d:column>
            <d:column>
                <d:SSDataGridColumn headerText="Last Name" width="30%" dataField="sLastName" />
            </d:column>
            <d:column>
                <d:SSDataGridColumn headerText="Email Address" width="30%" dataField="sEmail" />
            </d:column>
        </d:SSDataGrid>
    </g:FlowPanel>
```