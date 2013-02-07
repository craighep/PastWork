<div id="content">
    <div style="width: 960px; float: left; position: relative;">
        <form method="post" action="Monster">
            <input type="hidden" name="monsterID" value="${monster.monsterID}"/>


            <div style="padding: 0 !important; padding-left: 10px !important;" class="oneThird">
                <h2>Monster ${monster.monsterID}</h2>

                <img style="padding-left: 20px" align="left" src="images/${monster.isMale ? 'male' : 'female'}.png" />
                <%@ include file="monster_detail.jspf" %>

            </div>

            <div style="padding: 0 !important;" class="oneThird">
                <h2>Sale offer</h2>
                <input type="number" name="saleOffer" min="0" max="99999999" value="${monster.saleOffer}" />
                <h2>Breed offer</h2>
                <input type="number" name="breedOffer" min="0" max="99999999" value="${monster.breedOffer}" />

                <br/><button class="button1" type="submit">Save</button>
            </div>
            <div style="padding: 0 !important;" class="oneThird">
                <h2>Fight</h2>
                <select name="friendID" style="display:inline;">
                    <c:forEach var="friend" items="${friends}">
                        <option value="${friend.id}">${friend.remoteUserID} (${friend.remoteServerNumber})</option>
                    </c:forEach>
                </select>
                <button class="button1" type="submit">See Monsters</button>
                </h2>
            </div>
    </div>
    <c:forEach items="${remoteMonsters}" var="monster">
        <div style="padding-bottom: 15px !important; padding-top: 15px !important; width: 440px !important; padding-left: 20px; "class="halfStyle">
            <!--		<img style="padding-left: 20px" align="left" src="images/male.png" />-->
            <%@ include file="monster_detail.jspf" %>
            <button class="button1" type="submit" name="remoteMonsterID" value="${monster.monsterID}">Fight</button>
        </div>
    </c:forEach>
</div>

</form>

</div>
