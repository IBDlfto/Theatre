
$(function() {
    $('#droparea').on({
        dragover: function(e) {
            e.preventDefault();
            $(this).addClass('hover');
        },
        dragleave: function(e) {
            e.preventDefault();
            $(this).removeClass('hover');
        }
    });
    var types = ['png', 'jpg', 'jpeg', 'gif'];
    var file = null;
    document.querySelector('#droparea').addEventListener('drop', function(e) {
        e.preventDefault();
        var files = e.dataTransfer.files;
        console.log(files[0].size)
        traiter(files);
    }, false);
    $('section').on('change', '#browse', function(e) {
        e.preventDefault();
        var files = this.files
        traiter(files);
    });
    function traiter(files) {
        console.log(file);
        if (files.length === 1) {
            var imgType;
            for (var i = 0; i < files.length; i++) {
                imgType = files[i].name.split('.');
                imgType = imgType[imgType.length - 1].toLowerCase();
            }
            if (types.indexOf(imgType) !== -1) {
                $('#previewImage').html('');
                $('.progressBar').remove();
                $('#percent').remove();
                $('#droparea p').text('Glissez-deposez une nouvelle image pour remplacer la précedente');
                var prev = document.querySelector('#previewImage');
                file = files[0];
                var reader = new FileReader();
                reader.onload = function() {
                    var img = document.createElement('img');
                    img.src = this.result;
                    prev.appendChild(img);
                };
                reader.readAsDataURL(file);
                $('#previewImage').after('<div class="progressBar"><div class="progress"></div></div><span id="percent">100%</span>');
            } else {
                alert('Ce format n\'est pas supporté, merci de selectionner une image valide');
            }
        } else {
            alert('Le système multi-upload n\'est pas autorisé');
        }
    }

    $('section').on('submit', '#creationForm', function(e) {
        e.preventDefault();
        var $this = $(this);
        if (file !== null) {
            var progress = $(this).find('.progress');
            var xhr = new XMLHttpRequest();
            var form = new FormData(this);
            xhr.open('POST', 'nouveauSpectacle');
            form.append('image', file);
            xhr.send(form);
            xhr.upload.addEventListener('progress', function(e) {
                if (e.lengthComputable) {
                    console.log(e);
                    var perc = (Math.round(e.loaded / e.total) * 100) + '%';
                    progress.css({width: perc});
                    $('#percent').text(perc);
                }
            }, false);
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && (xhr.status === 200 || xhr.status === 0)) {
                    $('#contenu').notif({content: xhr.responseText, timeout: 5000});
                }
            };
        } else {
            $('#contenu').notif({content: 'Veuillez sélectionner une image', timeout: 5000});
        }
    });
    $('section').on('submit', '#editForm', function(e) {
        e.preventDefault();
        var $this = $(this);
        var $article = $this.parents('article:first');
        var $fond = $(this).parents('.fond:first');
        var image = $.trim($article.find('.prev a img').attr('src').split('/uploads/')[1]);
        var xhr = new XMLHttpRequest();
        var form = new FormData(this);
        form.append('id', $article.attr('id'));
        form.append('picture', image);
        form.append('image', file);
        xhr.open('POST', 'nouveauSpectacle');
        xhr.send(form);
        xhr.upload.addEventListener('progress', function(e) {
            if (e.lengthComputable) {
                var perc = (Math.round(e.loaded / e.total) * 100) + '%';
                $('.progress').css({width: perc});
                $('#percent').text(perc);
            }
        }, false);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && (xhr.status === 200 || xhr.status === 0)) {
                $fond.slideUp(1500, function() {
                    $article.children().remove();
                    $article.append(xhr.responseText);
                });
            }
        };
    });
});