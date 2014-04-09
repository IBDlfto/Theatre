$(function() {

    var panierFinal = [];
    function refreshCart() {
        var panier = $.cookie("panier").split("---");
        panierFinal = [];
        for (var i = 1; i < panier.length - 1; i++) {
            panierFinal.push(panier[i].split("//"));
        }
        $("#cart").text(panierFinal.length);
        console.log(panierFinal);
    }

    $(".spectacles .fold img").each(function() {
        var img = $(this);
        var article = img.parent();
        img.clone().appendTo(article).wrap("<div class='bot' />");
        img.wrap("<div class='top' />");
        article.find("div").append("<div class='shadow' />");
    });

    var $over = $("<div id='over' />");
    var $content = $("<div />");
    $over.append($content);
    $(".addCart").on("click", function() {
        var id = $(this).parents("article:first").attr("id");
        var nom = $(this).parents("article:first").data("nom");
        loading();
        $.post('representationS', {numS: id, nom: nom}, function(data, status) {
            $content.html(data);
        });
    });
    $("body").on("click", ".placesDispo", function(e) {
        e.preventDefault();
        var numS = $(this).parent().data("nums");
        var dateRep = $(this).parent().data("date");
        var nomS = $(this).parents(".representations:first").data("nom");
        console.log(numS + " " + nomS + " " + dateRep);
        $(".close").trigger("click");
        loading();
        $.post('places', {numS: numS, dateRep: dateRep, nomS: nomS}, function(data) {
            $content.html(data);
            for (var i = 0; i < panierFinal.length; i++) {
                if ($(".places").data("num") == (panierFinal[i])[0]) {
                    var places = $(".placeLibre");
                    for (var j = 0; j < places.length; j++) {
                        if (places[j].dataset.place == (panierFinal[i])[3] &&
                                places[j].dataset.rang == (panierFinal[i])[4]) {
                            places[j].setAttribute("class", "auPanier");
                        }
                    }
                }
            }
        });
    });
    $("body").on("click", ".placeLibre", function() {
        var $this = $(this);
        var $parent = $(this).parents(".places:first");
        $this.removeClass("placeLibre").addClass("auPanier");
        $.cookie("panier", $.cookie("panier")
                + $parent.data("num") + "//"
                + $parent.data("nom") + "//"
                + $parent.data("date") + "//"
                + $this.data("place") + "//"
                + $this.data("rang") + "---");
        refreshCart();
    });
    $("body").on("click", ".auPanier", function() {
        var $this = $(this);
        var $parent = $(this).parents(".places:first");
        $this.removeClass("auPanier").addClass("placeLibre");
        $.cookie("panier", $.cookie("panier").replace(
                $parent.data("num") + "//"
                + $parent.data("nom") + "//"
                + $parent.data("date") + "//"
                + $this.data("place") + "//"
                + $this.data("rang") + "---",
                ""));
        refreshCart();
    });
    if ($.cookie("panier") === undefined) {
        $.cookie("panier", "---");
    } else {
    }
    refreshCart();
    var price = [0, 30, 30, 30, 30, 30, 30, 30, 25, 25, 10];
    $("#cart").on("click", function(e) {
        e.preventDefault();
        loading();
        var div = "<div class='representations'>";
        div += "<h2 class='title'>Contenu du panier</h2>";
        if (panierFinal.length == 0) {
            div += "<h2 class='title'>Le panier ne contient aucune place</h2>";
        }
        else {
            var prix = 0;
            for (var i = 0; i < panierFinal.length; i++) {
                prix += price[(panierFinal[i])[4]];
                div += "<div class='representation' data-num='" + (panierFinal[i])[0] + "' "
                        + "data-nom='" + (panierFinal[i])[1] + "' "
                        + "data-date='" + (panierFinal[i])[2] + "' "
                        + "data-place='" + (panierFinal[i])[3] + "' "
                        + "data-rang='" + (panierFinal[i])[4] + "'>";
                div += "<span class='nomspec'> Spectacle: " + (panierFinal[i])[1] + "</span>";
                div += "<span class='datespec'>" + (panierFinal[i])[2] + "</span>";
                div += "<span class='placespec'> place: " + (panierFinal[i])[3] + "</span>";
                div += "<span class='rangspec'> rang: " + (panierFinal[i])[4] + "</span>";
                div += "<span class='prixspec'> prix: " + price[(panierFinal[i])[4]] + " euros</span>";
                div += "<span class='remove icone' title='Supprimer du panier'></span>";
                div += "</div>";
            }
            div += "<span class='subTotal'>Total : <b>" + prix + "</b> euros</span>";
            div += "<button class='button sButton bOlive confirm'>Confirmer l'achat</button>";
        }
        div += "</div>";
        $content.html(div);
    });
    $("body").on("click", ".remove", function() {
        var $parent = $(this).parents(".representation:first");
        $.cookie("panier", $.cookie("panier").replace(
                $parent.data("num") + "//"
                + $parent.data("nom") + "//"
                + $parent.data("date") + "//"
                + $parent.data("place") + "//"
                + $parent.data("rang") + "---",
                ""));
        $parent.slideUp("slow", function() {
            refreshCart();
            $("#cart").trigger("click");
        });
    });
    
    $("body").on("click", ".confirm", function() {
        var montant = $.trim($(this).prev().find("b").text());
        loading();
        $.post("reserver", {cookie: panierFinal, length: panierFinal.length, montant: montant}, function(data) {
            data = $.parseJSON(data);
            var contenu = "<div class='representations'><h2>"+data.message+"</h2></div>";
            $content.html(contenu);
            if(!data.error) {
                $.cookie("panier", "---");
                refreshCart();
            }
        });
    });

    function loading() {
        $over.show();
        var $loader = $("<span class='loader'><img src='../images/preloader16.gif' /> Chargement en cours ...</span>");
        $("body").prepend($over);
        $content.html($loader);
        var $close = $("<span class='close'>x Fermer</span>").on("click", function() {
            $over.hide();
        });
        $over.prepend($close);
    }
});