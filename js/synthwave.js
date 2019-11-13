$(function(){
    $(document).on('keydown keyup','#text',function(){
        var t = $(this).text();
        $('#text-shadow1, #text-shadow2').text(t);
    });
    setInterval(function(){
        var t = $('#text').text();
        $('#text-shadow1, #text-shadow2').text(t);
    },1);
});