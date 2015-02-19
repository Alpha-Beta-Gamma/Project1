<html>

<head>
    <title>CS480 Demo Web Service</title>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
</head>

<body>    
    <div style="color:#0000FF" align="center">
        <h1>Welcome to the user debug area</h1>
        <h1>The key tools and techniques used include: </h1>
        <ul>
            <li>HTML - Obviously</li>
            <li><a href="http://freemarker.org/">FreeMarker</a></li>
            <li><a href="http://jquery.com/">JQuery</a></li>
            <li><a href="http://api.jquery.com/jquery.ajax/">JQuery - AJAX</a></li>
        </ul>
    </div>

    <hr>

    <div>
        <div>
            <label>User List</label>
            <table border="1">            
                <tr>
                    <td>ID</td>
                    <td>Name</td> 
                    <td>Major</td>
                    <td>Email</td> 
                    <td>Creation Time</td>
                    <td>Password</td> 
                    <td>Delete</td>
                </tr>
                <#list users as user>
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.major}</td>
                            <td>${user.creationTime}</td>
                            <td>${user.creationTime}</td>
                            <td>${user.creationTime}</td>
                            <td><button onclick="deleteUser('${user.id}')">Delete</button></td>
                        </tr>
                </#list>
            </table>
        </div>
        
        <hr>
        
        <div>
            <label>Add User - DO NOT ADD A USER WITHOUT ALL FIELDS FILLED OUT!</label>
            <table border="1">
                <tr>
                    <td>ID</td>
                    <td>Name</td> 
                    <td>Major</td>
                    <td>Email</td> 
                    <td>Password</td>              
                    <td>Add</td>
                </tr>                
                <tr>
                    <td><input type="text" id="input_id"></td>
                    <td><input type="text" id="input_name"></td>
                    <td><input type="text" id="input_major"></td>
                    <td><input type="text" id="input_email"></td>
                    <td><input type="text" id="input_password"></td>         
                    <td><button onclick="registermanual()">Add</button></td>
                </tr>
            </table>
        </div>

        <hr>

        <div>
            <label>Query User</label>
            <input type="text" id="query_id"><button onclick="getUser()">Get</button>
            <table border="1">
                <tr>
                    <td>ID</td>
                    <td>Name</td>
                    <td>Major</td>
                </tr>
                <tr>
                    <td><label id="result_id"></td>
                    <td><label id="result_name"></td>
                    <td><label id="result_major"></td>
                </tr>
            </table>
        </div>
    </div>
    
    
</body>

</html>