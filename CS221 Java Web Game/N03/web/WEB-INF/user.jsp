<div id="content">
    <form method="post" action="User">
        <table style="width: 600px; padding: 10px 10px 10px 10px;">
            <tr><td><strong>User ID: </strong></td><td>		<input class="input" type="text" disabled="disabled" value="${user.userID}"></td></tr>
            <tr><td><strong>Name: </strong></td><td>			<input class="input" type="text" name="name" value="${user.name}"></td></tr>
            <tr><td><strong>Avatar URL: </strong></td><td>			<input class="input" type="text" name="avatarURL" value="${user.avatarURL}"></td></tr>
            <tr><td><strong>New Password: </strong></td><td>		<input class="input" type="password" size="20" name="password"></td></tr>
            <tr><td><strong>Verify password: </strong></td><td>	<input class="input" type="password" size="20" name="verify"></td></tr>
            <tr><td/><td>				<input class="button1" type="submit" value="Save"></td></tr>
        </table>
    </form>
</div>
