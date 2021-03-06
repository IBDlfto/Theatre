<%-- 
    Document   : representations
    Created on : 1 avr. 2014, 19:25:12
    Author     : toure
--%>

<div class="representations" data-num="${numS}" data-nom='<c:out value="${nom}" />'>
    <h2 class="title">
        Répresentations du spectacle <c:out value="${nom}"/>
    </h2>
    <c:choose>
        <c:when test="${representations.size() != 0}">
            <c:forEach items="${representations}" var="representation">
                <div class="representation" data-date="<c:out value='${representation.dateF}'/>" data-nums="<c:out value='${representation.numS}'/>">
                    <c:out value="${representation.date}"/>
                    <a href="#" class="placesDispo">Places dispo</a>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            Aucune répesentation pour ce spectacle
        </c:otherwise>
    </c:choose>
</div>