/**
 * Created by nikita on 24.07.15.
 */
$(document).on('click','#menu-button',function(){
    $(".menuoverlay").show();
    $(".leftmenu").animate({width: 'toggle'});
    $(".shade").show();
});
var closeFunc = function() {
    $(".leftmenu").animate({width: 'toggle'}, 500, "swing", function () {
        $(".shade").hide();
        $(".menuoverlay").hide();
    });
}
$(document).on('click','.shade', closeFunc);
$(document).on('click','#close',closeFunc);
$('input[type=file]').bootstrapFileInput();
$(function(){
    $(".form_datetime").datetimepicker();
})