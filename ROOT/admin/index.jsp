<%-- 
    Document   : index
    Created on : 26 mars 2014, 23:45:05
    Author     : TOURE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrateur</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<c:url value="../css/admin.css" />" />
    </head>
    <body>
        <div id="contenu">
            <header>                
                <div class="gridLogo">
                    <a href="../public/index">Petit Théatre de l'Informatique</a>
                </div>
            </header>
            <section>
                <aside id="menuGauche">
                    <div id="navigation">
                        <ul id="menu">
                            <li><a href="#" class="titre menu icone">Menu</a></li>
                            <li><a href="#" class="load depots icone">Statistiques</a></li>
                            <li><a href="programmeAdmin" class="load depots icone">Spectacles de la saison</a></li>
                        </ul>
                    </div>
                    <div class="diviser">
                        <div><span></span></div>
                    </div> 
                </aside>
                <nav id="topBar">
                    <div id="liens">
                        <ul>
                            <li><a href="resume" id="resume" class="load home icone"></a></li>
                        </ul>
                    </div>
                    <div id="userOptions">
                        <ul>
                            <li><a href="nouveauSpectacle" class="load creation icone">Nouveau spectacle</a></li>
                            <li>
                                <a href="#" id="user" class="icone">Administrateur</a>
                                <ul>
                                    <li><a id="logout" class="icone" href="../public/" />Déconnexion</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>
                <article>
                    <div class="diviser">
                        <div><span></span></div>
                    </div>
                    <input type="hidden" id="numero" value="${sessionScope.sessionMembre.numero}" />
                    <div id="load">
                    </div>
                </article>
            </section>
        </div>
    </body>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/admin.js"></script>
</html>
