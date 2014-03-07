<%-- 
    Document   : header
    Created on : 4 mars 2014, 12:05:40
    Author     : TOURE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="top">
    <div class="gridContainer">
        <div class="gridLogo">
            <a href="../public/index">Petit Théatre de l'Informatique</a>
        </div>

        <div class="gridUser">
            <nav>
                <ul>
                    <li><a href="connexion">Connexion</a></li>
                    <li><a href="inscription">Inscription</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>
<header>
    <div class="gridContainer">
        <div class="grid_3">
            <hgroup>
                <h1 id="site_logo" ><a href="index" title=""><img src="../images/theatre.jpg" /></a></h1>
                <h2 id="site_description">Petit Théatre de l'Informatique</h2>
            </hgroup>
        </div>
        <div class="grid_9">
            <div class="top_header">
                <div class="welcome">
                    Cette saison sera donc très ambitieuse et très prestigieuse dans sa programmation.
                </div>
                <form class="search" action="search">
                    <input type="text" name="q" class="search_form" placeholder="Trouver une une presentation ...">
                    <input type="submit" class="search_button" value="">
                </form>
            </div>
            <nav class="primary">
                <ul>
                    <li class="curent"><a href="index">Accueil</a></li>
                    <li><a href="#">A l'affiche</a></li>
                    <li><a href="#">Réserver</a></li>
                    <li class="parent">
                        <a href="#">Accès Rapide</a>
                        <ul class="sub">
                            <li><a href="#">Accueil</a></li>
                            <li><a href="#">Connexion</a></li>
                            <li><a href="#">Inscription</a></li>
                            <li><a href="#">Administrateur</a></li>
                        </ul>
                    </li>
                </ul>
            </nav><!-- .primary -->
        </div>
    </div>
</header>
<section id="main" class="home">
    <div class="container_12">
