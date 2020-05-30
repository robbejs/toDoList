function myFunction(){
    var x = document.getElementById("mySelect").value;
    window.location.replace('?lang=' + x);
}

    /*$(document).ready(function() {
        $("#locale").change(function () {
            var selectedOption = $('#locale').val();
            if (selectedOption != ''){
                window.location.replace('?lang=' + selectedOption);
            }
        });
    });*/

