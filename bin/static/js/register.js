
//
////// 성별 선택
 $(function() {
    $('#sex').dropdown();
    $('#age').dropdown();

    $('#avatar').each(function() {
      $(this).css('height', $(this).width() + 'px');
    });


    $("#changeAvatar").on('click', function(e){
          e.preventDefault();
          $("#upload:hidden").trigger('click');
      });
  });

    function handleFiles(files){
        for(var i = 0; i < files.length; i++){
            var file = files[i];
            var imageType = /image.*/;

            if(!file.type.match(imageType)){
                continue;
            }

            var reader = new FileReader();

            reader.onload = function(e) {
                    jQuery('#avatar').attr('src', e.target.result);
            };
            reader.readAsDataURL(file);
            console.log(jQuery('#avatar').src);
        }
    }



