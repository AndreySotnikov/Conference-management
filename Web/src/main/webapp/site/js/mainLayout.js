function setEqualHeight(columns)
{
    var tallestcolumn = 0;
    columns.each(
        function()
        {
            currentHeight = $(this).height();
            if(currentHeight > tallestcolumn)
            {
                tallestcolumn = currentHeight;
            }
        }
    );
    columns.height(tallestcolumn);
}
$(document).ready(function() {
    setEqualHeight($(".mainspace > .column"));
});
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