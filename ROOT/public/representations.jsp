<%-- 
    Document   : representations
    Created on : 1 avr. 2014, 19:25:12
    Author     : toure

<div class="representations">
    <h2>Répresentations du spectacle</h2>
    <c:choose>
        <c:when test="${representations} != '[]'">
            <c:forEach items="${representations}" var="representation">
                <div class="representation" data-date="<c:out value='${representation.date}'/>">
                    <c:out value="${representation.date}"/>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            Aucune répesentation pour ce spectacle
        </c:otherwise>
    </c:choose>
</div>
--%>