$(function() {
    $(".spectacles .fold img").each(function() {
        var img = $(this);
        var article = img.parent();
        img.clone().appendTo(article).wrap("<div class='bot' />");
        img.wrap("<div class='top' />");
        article.find("div").append("<div class='shadow' />");
    });
    
    $(".addCart").on("click", function() {
        var $over = $("<div id='over' />");
        $("body").prepend($over);
        alert('ok');
    });
});