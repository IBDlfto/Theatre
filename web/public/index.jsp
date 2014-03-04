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

            <div class="products">
                    <article class="grid_3 article" id="">
                        <img class="sale" src="../images/new.png" alt="New">
                        <div class="prev">
                            <a href="details?id="><img src="../uploads/" alt="Product 1" title=""></a>
                        </div><!-- .prev -->

                        <h3 class="title"></h3>
                        <div class="cart">
                            <div class="price">
                                <div class="vert">
                                    
                                </div>
                            </div>
                        </div>
                    </article>
                <div class="clear"></div>
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
