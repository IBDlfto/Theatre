<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
        <link rel="stylesheet" href="<c:url value="../css/public.css" />" />
    </head>
    <body>
        <c:import url="../common/header.jsp" />
        <div id="slider">
            SLIDER A PREVOIR SI LE TEMPS LE PERMET
        </div>
    </div>
    <div class="container_12">
        <div id="content">
            <div class="grid_12">
                <h2 class="product-title">Spéctacles à venir</h2>
            </div><!-- .grid_12 -->
            <div class="clear"></div>
            <div class="spectacles">
                <c:forEach var="spectacle" items="${spectacles}">
                    <article class="grid_3 fold" id="<c:out value="${spectacle.id}"/>">
                        <img src="../uploads/1367274198351.jpg" />
                        <span class="menu">
                            <span class="info icone"></span>
                            <span class="addCart icone"></span>
                        </span>
                        <span class="titre">
                            <h2><c:out value="${spectacle.nom}"/></h2>
                        </span>
                    </article>
                    <div class="clear"></div>
                </c:forEach>                
            </div><!-- .products -->
            <div class="clear"></div>
        </div><!-- #content -->

        <div class="clear"></div>
    </div><!-- .container_12 -->
    <c:import url="../common/footer.jsp" />
</body>
<script src="../js/jquery.min.js"></script>
<script src="../js/jquery.cookie.js"></script>
<script src="../js/common.js"></script>
</html>
