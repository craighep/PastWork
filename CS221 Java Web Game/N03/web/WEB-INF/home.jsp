<div id="content">
    <div class="oneThird">
        <h2 style="text-align: center;">Hello ${user.name}!</h2>
        <img src="${user.avatarURL}" width="150" style="margin-left: auto; margin-right: auto; display: block;"/>
        <h3 style="text-align: center;">£${user.money}</h3>
        <p>Select an option from the top menu</p>
    </div>

    <div class="oneThird">
        <h2>Battle requests</h2>
	<form style="padding: 0;" method="post" action="Fight"> 
	    <c:forEach items="${fightRequests}" var="request">
		<div class="oneThirdInside">
		    <div style="width: 150px; position:relative; float:left;">
			<p><strong>${request.remoteUserID}</strong> requested battle</p>
		    </div>
		    <div style="width: 150px; position:relative; float:right;">
			<button class="button2" name="id" type="submit" value="${request.id}">More info</button>
		    </div>
		</div>
	    </c:forEach>
	</form>

        <h2>Friends requests</h2>
        <form style="padding: 0;" method="post" action="Friends"> 
            <c:forEach items="${friendRequests}" var="request">
                <div class="oneThirdInside">
                    <div style="width: 150px; position:relative; float:left;">
                        <p><strong>User:</strong> ${request.remoteUserID}<br />
                            <strong>Server:</strong> ${request.remoteServerNumber}</p>
                    </div>
                    <div style="width: 150px; position:relative; float:left;">
                        <button class="button2" name="accept" type="submit" value="${request.id}">Accept</button>
                        <button class="button2" name="reject" type="submit" value="${request.id}">Reject</button>
                    </div>
                </div>
            </c:forEach>
        </form>
    </div>

    <div class="oneThird">
	<h2>Friend's Monsters</h2>
	<c:forEach items="${remoteMonsters}" var="monster">
	    <%@ include file="monster_detail.jspf" %>
	    <c:if test="${monster.saleOffer gt 0}">
		<form method="post" action="Buy">
		    <input name="remoteServerID" value="${monster.server.id}" hidden="hidden"/>
		    <button name="remoteMonsterID" type="submit" value="${monster.monsterID}">Buy</button>
		</form>
	    </c:if>
	    <c:if test="${monster.breedOffer gt 0}">
		<form method="post" action="Breed">
		    <input name="remoteServerID" value="${monster.server.id}" hidden="hidden"/>
		    <button name="remoteMonsterID" type="submit" value="${monster.monsterID}">Breed</button>
		</form>
	    </c:if>
	</c:forEach>
    </div>
</div>
</div>
