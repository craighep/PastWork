<div id="content">
    <div class="oneThird">
        <h2>Add friends:</h2>

	<table>

	    <form name="newrequest" method="post" action="Friends">
		<tr>
		    <td>
			<select class="input" name="remoteServerID">
			    <c:forEach var="server" items="${servers}">
				<c:if test="${not empty server.serviceRootURL}">
				<option value="${server.serverNumber}"
					<c:if test="${selectedServerID eq server.serverID}">
					    selected="selected"
					</c:if>
					    >${server.serverNumber} (${server.serviceRootURL})</option>
				</c:if>
			    </c:forEach>
			</select>
			<input class="button1" type="submit" value="See Users">
		    </td>
		</tr>

		<c:if test="${not empty remoteUsers}">
		    <tr><td><h3>Remote Users</h3>
			    <table>
				<c:forEach var="remoteUser" items="${remoteUsers}">
				    <tr><td><strong>User ID</strong></td><td>${remoteUser.userID}</td></tr>
				    <tr><td><strong>Name</strong></td><td>${remoteUser.name}</td></tr>
				    <tr><td><strong>Money</strong></td><td>${remoteUser.money}</td></tr>
                                    <tr><td><button name="remoteUserID" type="submit" value="${remoteUser.userID}">Send</button></td></tr>
				</c:forEach>
			    </table>
			</td>
		    </tr>
		</c:if>

	    </form>
	</table>
    </div>

    <div class="oneThird">

        <form name="friends" method="post" action="Friends">
            <h2>My Friends</h2>

            <c:forEach items="${friends}" var="friend">
                <div style="width: 160px; position:relative; float:left;">
                    <p><strong>User:</strong> ${friend.remoteUserID}<br />
                        <strong>Server:</strong> ${friend.remoteServerNumber}</p>
                </div>
                <div style="width: 90px; position:relative; float:left;">
                    <button class="button2" name="delete" type="submit" value="${friend.id}">Unfriend</button>
                </div>										
            </c:forEach>

        </form>
    </div>
    <div class="oneThird">
        <form name="received" method="post" action="Friends">
            <h2>Friend Requests</h2> 
            <c:forEach items="${receivedRequests}" var="friend">
                <div class="oneThird">
                    <div style="width: 150px; position:relative; float:left;">
                        <p><strong>User:</strong> ${friend.remoteUserID}<br />
                            <strong>Server:</strong> ${friend.remoteServerNumber}</p>
                    </div>
                    <div style="width: 150px; position:relative; float:left;">
                        <button class="button2" name="accept" type="submit" value="${friend.id}">Accept</button>
                        <button class="button2" name="reject" type="submit" value="${friend.id}">Reject</button>
                    </div>
                </div>
            </c:forEach>
        </form>
        <form style="margin-top: 10px;" name="sent" method="post" action="Friends">
            <h2>Pending Requests</h2>
            <c:forEach items="${sentRequests}" var="friend">
                <div style="width: 160px; position:relative; float:left;">
                    <p><strong>User: </strong>${friend.remoteUserID}<br />
                        <strong>Server: </strong>${friend.remoteServerNumber}</p>
                </div>
                <div style="width: 100px; position:relative; float:left;">
                    <button class="button2" name="delete" type="submit" value="${friend.id}">Delete</button>
                </div>
            </c:forEach>
        </form>

        </td>
        </table>
    </div>
</div>
