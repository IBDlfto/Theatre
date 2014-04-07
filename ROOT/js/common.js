$(function() {
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
        loading();
        $.post('representationS', {numS: id, public: 'public'}, function(data, status) {
            $content.html(data);
        });
    });
    $("body").on("click", ".placesDispo", function() {
        var numS = $(this).parent().data("nums");
        var dateRep = $(this).parent().data("date");
        console.log(numS + " " + dateRep);
        $(".close").trigger("click");
        loading();
        $.post('places', {numS: numS, dateRep: dateRep}, function(data) {
            $content.html(data);
        });
    });
    $("body").on("click", ".placeLibre", function() {
        var $this = $(this);
        $this.removeClass("placeLibre").addClass("auPanier");
        $.cookie("panier", $.cookie("panier") + $this.data("place") + "//" + $this.data("rang") + "---");
        refreshCart();
    });
    $("body").on("click", ".auPanier", function() {
        var $this = $(this);
        $this.removeClass("auPanier").addClass("placeLibre");
    });
    if ($.cookie("panier") === undefined) {
        $.cookie("panier", "---");
    } else {
        console.log("ok");
    }
    console.log($.cookie("panier"));
    refreshCart();

    function refreshCart() {
        $("#cart").text($.cookie("panier").split("---").length - 2);
    }

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