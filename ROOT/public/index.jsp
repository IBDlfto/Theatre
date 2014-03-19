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
                <article class="grid_3 fold" id="">
                    <img src="../images/test1.jpg" />
                    <span class="menu">
                        <span class="info icone"></span>
                        <span class="addCart icone"></span>
                    </span>
                    <span class="titre">
                        <h2>Titre de la representation</h2>
                    </span>
                </article>
                <div class="clear"></div>
                <article class="grid_3 fold" id="">
                    <img src="../images/test2.jpg" />
                </article>
                <div class="clear"></div>
                <article class="grid_3 fold" id="">
                    <img src="../images/theatre-1.jpg" />
                </article>
                <div class="clear"></div>
                <article class="grid_3 fold" id="">
                    <img src="../images/test2.jpg" />
                </article>
                <div class="clear"></div>
                <article class="grid_3 fold" id="">
                    <img src="../images/test2.jpg" />
                </article>
                <div class="clear"></div>
                <article class="grid_3 fold" id="">
                    <img src="../images/test2.jpg" />
                </article>
            </div><!-- .products -->
            <div class="clear"></div>
        </div><!-- #content -->

        <div class="clear"></div>
    </div><!-- .container_12 -->
    <c:import url="../common/footer.jsp" />
</body>
<script src="../js/jquery.min.js"></script>
<script src="../js/common.js"></script>
</html>
