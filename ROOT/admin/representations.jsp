<%-- 
    Document   : representations
    Created on : 1 avr. 2014, 19:25:12
    Author     : toure
--%>

<div class="representations">
    <c:forEach items="${representations}" var="representation">
        <div class="representation" data-date="<c:out value='${representation.date}'/>">
            <c:out value="${representation.date}"/>
        </div>
    </c:forEach>
    <span class="addRep icone button sButton bOlive">Nouvelle répresentation</span>
</div>