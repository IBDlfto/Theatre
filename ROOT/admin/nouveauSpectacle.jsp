<%-- 
    Document   : creation
    Created on : 16 avr. 2013, 12:36:13
    Author     : TOURE
--%>

<div class="fond">
    <div class="side">
        <form id="creationForm">
            <h1 class="page_title">Ajout d'un nouveau spectacle</h1>            
            <label for="libelle" class="formLabel">Titre : </label>
            <input type="text" id="libelle" name="libelle" required /><br />
            <label for="description" class="formLabel">Description : </label>
            <textarea name="description" id="description" required></textarea><br />
            <div id="droparea">
                <p>Glissez-Déposez votre image ici.</p>
                <span>ou</span><br />
                <input id="browse" type="file" title=""/>
            </div>
            <span class="formLabel"></span>
            <input type="submit" class="button sButton bSky" value="Publier le spectacle" />
        </form>
    </div>
    <div class="creationSide">
        <div id="filelist">
            <h1 class="page_title">Visualiser l'image</h1>
            <div id="previewImage">
                Aucune image sélectionnée.
            </div>
        </div>
    </div>
</div>
<script src="../js/upload.js"></script>