<table style="width: 100%;">
    <tr>
	<td>
	    <!-- USERS TABLE -->
	    <form method="post" action="Admin">
		<h2>Users</h2>
		<div class="datatable">
		    <table>
			<tr><td>ID</td><td>Name</td><td>Money</td></tr>   
			<c:forEach items="${users}" var="u">
			    <tr>
				<td>${u.userID}</td><td>${u.name}</td><td>${u.money}</td>
				<td><button name="delete" type="submit" value="${u.id}">Delete</button></td>
				<td><button name="reset_password" type="submit" value="${u.id}">Reset Password</button></td>
				<td><button name="set_money" type="submit" value="${u.id}" disabled="disabled">Set Money</button></td>
			    </tr>
			</c:forEach>
		    </table>
		</div>
		<h3>New password</h3>
		<input type="password" name="password"/>
	    </form>
	</td>
    </tr>
</table>
