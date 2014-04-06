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
            console.log(status);
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