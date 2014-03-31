<div class="products_list">     
    <c:choose>
        <c:when test="${spectacles == '[]'}">
            <article class="notFound">
                Aucun spectacle pour cette saison
            </article>
        </c:when>
        <c:otherwise>
            <c:forEach var="spectacle" items="${spectacles}">
                <article class="spectacle" id="${produit.id}">
                    <div class="grid_3">
                        <div class="prev">
                            <a href="#"><img src="<c:out value="../uploads/" />"></a>
                        </div><!-- .prev -->
                    </div>

                    <div class="grid_6">
                        <div class="entry_content">
                            <h3 class="title"><c:out value="${spectacle.nom}" /></h3>
                            <p class="desc">
                                <c:out value="${spectacle.nom}" />
                            </p>
                        </div><!-- .entry_content -->

                        <div class="price">    
                        </div>

                        <div class="views">
                            <div class="actions">
                                <span class="show icone button sButton bOlive">Répresentations</span>
                                <span class="edit icone button sButton bSky">Modifier</span>
                                <span class="delete icone button sButton bMuddy">Effacer</span>
                            </div>
                        </div>
                    </div><!-- .grid_6 -->
                    <div class="representations">
                        <div class="representation"></div>
                        <div class="representation"></div>
                        <div class="representation"></div>
                    </div>
                    <div class="clear"></div>
                </article>
            </c:forEach>
            <div class="pagination">
                <ul>
                    <c:forEach begin="1" end="10" var="i">
                        <c:choose>
                            <c:when test="${form.page == i}">
                                <li class="curent"><a href="#">${i}</a></li>
                                </c:when>
                                <c:otherwise>
                                <li><a href="encheris?page=${i}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                </ul>
            </div>
        </c:otherwise>
    </c:choose>
</div>