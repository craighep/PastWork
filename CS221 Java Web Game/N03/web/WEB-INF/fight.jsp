<div id="content">
    
    <form method="post" action="Fight">
	<input name="id" value="${fight.id}" hidden="hidden"/>
    
    <div class="halfStyle">
	<h2>My Monster: </h2>
	<img style="padding-left: 20px" align="left" src="images/male.png" />
	<c:set var="monster" value="${localMonster}"/>
	<%@ include file="monster_detail.jspf" %>
    </div>

    <div class="halfStyle">
	<h2>${remoteMonster.userID}'s Monster: </h2>
	<img style="padding-left: 20px" align="left" src="images/female.png" />
	<c:set var="monster" value="${remoteMonster}"/>
	<%@ include file="monster_detail.jspf" %>
    </div>
    
    <center>
	<button class="button1" type="submit" name="action" value="fight">Fight</button>
	<button class="button1" type="submit" name="action" value="reject">Reject</button>
    </center>
    
    </form>

</div>
