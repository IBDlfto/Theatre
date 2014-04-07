<%-- 
    Document   : places
    Created on : 7 avr. 2014, 16:55:31
    Author     : loic
--%>
<div class="places">
    <c:forEach var="i" begin="1" end="10" step="1" >
        <h3>Rang ${i} </h3>
        <c:forEach var="place" items="${places}">
            <c:if test="${place.noRang == i}">
                <span class="placeLibre" data-place="${place.noPlace}" 
                      data-rang="${place.noRang}" title="Ajouter cette place à mon panier">
                    <c:out value="${place.noPlace}" />
                </span>
            </c:if>
        </c:forEach>
        <br />
    </c:forEach>
</div>