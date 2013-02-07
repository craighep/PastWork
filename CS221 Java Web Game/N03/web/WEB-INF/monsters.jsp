<!---->
<div id="content">

    <div class="oneThird">

        <form style="padding: 0 !important;" method="post" action="Monsters">
            <p><button  class="button1" name="generate" type="submit" value="generate">Generate new monster</button></p>
        </form>

	<form style="padding: 0 !important;" method="post" action="Fight">
	    <h2>Fight Requests</h2>
	    <c:forEach items="${receivedRequests}" var="fight">
		<div class="oneThirdInside">
		    <div style="width: 150px; position:relative; float:left;">
			<p><strong>${fight.remoteUserID}</strong> wants to fight monster <strong>${fight.localMonsterID}</strong></p>
		    </div>
		    <div style="width: 150px; position:relative; float:right;">
			<button class="button2" type="submit" name="id" value="${fight.id}">More info</button>
		    </div>
		</div>
	    </c:forEach>
	</form>

	<h2>Pending Fights</h2>
	<c:forEach items="${sentRequests}" var="fight"> 
	    <div class="oneThirdInside">
		<p>Monster ${fight.localMonsterID} against ${fight.remoteUserID}'s monster ${fight.remoteMonsterID}</p>  
	    </div>
	</c:forEach>

	<h2>Finished fights</h2>
	<c:forEach items="${completedRequests}" var="fight">
	    <div class="oneThirdInside">
		<p>Monster ${fight.localMonsterID} <strong>${fight.wasWon ? 'Won' : 'Lost'}</strong> against ${fight.remoteUserID}'s monster ${fight.remoteMonsterID}</p>  
	    </div>
	</c:forEach> 

    </div>




    <div class="twoThirds">
        <h2>Your monsters</h2>

	<c:forEach items="${monsters}" var="monster">
	    <div class="halfStyle">		
		<img align="left" src="images/${monster.isMale ? 'male' : 'female'}.png" />
		<%@ include file="monster_detail.jspf" %>
	    </div>
	    <div style="float: right; position: relative;">
		<form method="post" action="Monster">
                    <p style="padding-bottom: 5px;"><button class="button2" name="monsterID" type="submit" value="${monster.monsterID}">Fight</button></p>
                    <p style="padding-bottom: 5px;"><button class="button2" name="monsterID" type="submit" value="${monster.monsterID}">Edit</button></p>
		</form>
		<form method="post" action="Monsters">
		    <p style="padding-bottom: 5px;"><button class="button2" name="delete" type="submit" value="${monster.monsterID}">Delete</button></p>
		</form>
	    </div>
	</c:forEach>
    </div>

</div>
