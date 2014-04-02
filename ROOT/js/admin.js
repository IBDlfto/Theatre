$(function() {

    $('body').on('click', 'a', function(e) {
        if ($(this).attr('href') === '#') {
            e.preventDefault();
            return false;
        }
    });

    $.fn.notif = function(options) {
        var options = $.extend({
            content: 'Chargement de la page en cours ...',
            timeout: false
        }, options);
        return this.each(function() {
            var $this = $(this);
            $this.find('.notifications').remove();
            var $notifs = $('<div class="notifications"/>');
            $notifs.append('<div class="notification">' + options.content + '</div>');
            $this.prepend($notifs);
            if (options.timeout) {
                setTimeout(function() {
                    $notifs.addClass('animated bounceOut').delay(1000).slideUp('slow', function() {
                        $(this).remove();
                    });
                }, options.timeout);
            }
        });
    };
    $.fn.info = function(options) {
        return this.each(function() {
            var $this = $(this);
            $this.find('notifications').remove();
            var cl = 'success';
            if (options.type)
                cl = 'error';
            var $infos = $('<div class="' + cl + '"/>');
            $infos.append('<p class="icone">' + options.content + '</div>');
            $this.prepend($infos);
            $infos.click(function(e) {
                e.preventDefault();
                $(this).addClass('animated bounceOut').delay(1000).slideUp('slow', function() {
                    $(this).remove();
                });
            });
        });
    };
    $('#contenu').notif({timeout: 1000});
    var ancre = (location.hash).split('#/')[1];
    if ($('a[href="' + ancre + '"]').length > 0) {
        var courant = ancre;
    } else {
        var courant = 'statistiques';
        var espace = location.href;
    }
    $('section').on('click', '.load', function(e) {
        e.preventDefault();
        var $this = $(this);
        var link = $(this).attr('href');
        if (link !== '#') {
            $('#navigation').find('a').removeClass('curent');
            $('#menu').find('a[href="' + link + '"]').addClass('curent');
            $('#contenu').notif();
            $('#load').load(link, function() {
                history.pushState({key: 'value'}, document.title, '#/' + link);
                document.title = 'Petit Théatre de l\'Informatique - ' + link.replace(/..\/common\//, '').toUpperCase();
                $(this).find('#expiration').val(dateChoisie(0, 1, 1900));
                closeNotif();
            });
        }
    });

    window.onpopstate = function(e) {
        if (e.state !== null) {
            var link = (location.hash).split('#/')[1];
            $('#load').load(link, function() {
                $(this).find('#expiration').val(dateChoisie(0, 1, 1900));
            });
        }
    };
    if ($('#menu').find('a[href="' + courant + '"]').length > 0) {
        $('#menu').find('a[href="' + courant + '"]').trigger('click');
    } else {
        $('a[href="' + courant + '"]').trigger('click');
    }

    $('#userOptions li').hover(
            function() {
                $(this).find('ul:first').removeClass('hinge');
                $(this).find('ul:first').addClass('animated swing');
            },
            function() {
                $(this).find('ul:first').css('display', 'block');
                $(this).find('ul:first').removeClass('swing');
                $(this).find('ul:first').addClass('animated hinge');
            }
    );

    $('section').on('click', '.delete', function() {
        var $this = $(this);
        var $oui = $('<span class="oui icone button sButton bOlive">Oui</span>');
        var $non = $('<span class="non icone button sButton bMuddy">Non</span>');
        $this.addClass('animated shake').delay(500).slideUp(function() {
            $this.siblings('.button, form').slideUp();
            $this.after($non);
            $this.after($oui);
        });
    });

    $('section').on('click', '.non', function() {
        console.log('ok');
        $(this).addClass('animated shake').delay(200).slideUp(function() {
            $(this).siblings('.button').removeClass('animated shake').slideDown();
            $(this).siblings('.oui').remove();
            $(this).remove();
        });
    });

    $('section').on('click', '.spectacle .oui', function() {
        var $article = $(this).parents('article:first');
        //$.post('encheris', {id: $article.attr('id')}, function() {
        $article.addClass('animated hinge').delay(1500).slideUp(function() {
            $article.remove();
        });
        //});
    });

    // Affichage des representations
    $('section').on('click', '.spectacle .show', function() {
        var $article = $(this).parents('article:first');
        $('#contenu').notif({content: 'Affichage des répresentations en cours ...'});

        $.post('representationS', {numS: $article.attr('id')}, function(data) {
            $article.find('.grid_6').
                    after($("<div class='representations'>" + data + "</div>").
                    slideDown("slow", function() {
                        closeNotif();
            }));
        });
    });

    /* ------------ Pagination -------------- */
    $('section').on('click', '.pagination a', function(e) {
        e.preventDefault();
        var url = $(this).attr('href');
        if (url !== '#') {
            $('#load').load(url);
        }
    });
    $('body').on('mouseover', function() {
        var height = $('body').css('height').split('px')[0] - 85 + 'px';
        $('section > article').css('height', height);
    });
    $('article').on('click', '.add', function() {
        $(this).before('<input type="text" class="nvlle_sc" autofocus /><br />');
    });
    $('article').on('click', '.modification', function() {
        val = $.trim($(this).next().text());
        $(this).hide().siblings().hide();
        $(this).after('<input type="text" class="edition_cat" value="' + val + '" autofocus />');
    });
    $('article').on('click', '.annuler_ajout', function() {
        var $sel = $(this).parent().addClass('animated fadeOutUpBig');
    });
    /* ----------------------- Date picker en français --------------------------- */
    $.datepicker.regional['fr'] = {
        closeText: 'Fermer',
        prevText: 'Précédent',
        nextText: 'Suivant',
        currentText: 'Aujourd\'hui',
        monthNames: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
        monthNamesShort: ['Janv.', 'Févr.', 'Mars', 'Avril', 'Mai', 'Juin', 'Juil.', 'Août', 'Sept.', 'Oct.', 'Nov.', 'Dec.'],
        dayNames: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'],
        dayNamesShort: ['Dim.', 'Lun.', 'Mar.', 'Mer.', 'Jeu.', 'Ven.', 'Sam.'],
        dayNamesMin: ['D', 'L', 'M', 'M', 'J', 'V', 'S'],
        weekHeader: 'Sem.',
        dateFormat: 'dd/mm/yy',
        minDate: 1,
        firstDay: 1,
        showMonthAfterYear: false,
        yearSuffix: ''
    };
    $.datepicker.setDefaults($.datepicker.regional['fr']);
    $('#load').on('mouseenter', '#expiration', function() {
        $(this).datepicker({
            minDate: 1
        });
    });

    function closeNotif() {
        $('.notifications').addClass('animated bounceOut').delay(1000).slideUp('slow', function() {
            $(this).remove();
        });
    }
});

function dateChoisie(j, m, a) {
    var date = new Date();
    date = new Date(date.setDate(date.getDate() + j));
    date = new Date(date.setMonth(date.getMonth() + m));
    date = new Date(date.setYear(date.getYear() + a));
    var jour = date.getDate();
    var mois = (date.getMonth() + 1);
    if (jour < 10)
        jour = '0' + jour;
    if (mois < 10)
        mois = '0' + mois;
    date = jour + '/' + mois + '/' + (date.getYear() + 1900);
    return date;
}