<!---->
<div id="content">
    <div style="width: 100%; float: left; position: relative;">
        <form method="post" action="Breed">
            <input hidden="hidden" name="remoteServerID" value="${remoteServerID}"/>
            <input hidden="hidden" name="remoteMonsterID" value="${remoteMonsterID}"/>
    <!--	<input hidden="hidden" name="localMonsterID" value="${localMonsterID}"/>-->
            <div class="oneThird">

                <c:set var="monster" value="${remoteMonster}"/>
                <%@ include file="monster_detail.jspf" %>
            </div>
    </div>


    <h2>Your monsters</h2>
    <c:forEach items="${localMonsters}" var="monster">
        <div class="oneThird">
            <img align="left" src="images/${monster.isMale ? 'male' : 'female'}.png" />
            <%@ include file="monster_detail.jspf" %>
            <button class="button2" name="localMonsterID" type="submit" value="${monster.monsterID}">Breed</button>
        </div>
    </c:forEach>

</form>

</div>
