$(document).ready(function(){



var answers = new Array();


var totalQuestions = $('.question').size();
var currentQuestion = 0;
$questions = $('.question');
$questions.hide();
$($questions.get(currentQuestion)).fadeIn();
$('#next').click(function(){


    $($questions.get(currentQuestion)).fadeOut(function(){
        currentQuestion = currentQuestion + 1;
		if(currentQuestion == (totalQuestions-1))
		{
		document.getElementById("next").value="Submit";
		}
        if(currentQuestion == totalQuestions){
        	var params = {answers:[]};
    		$.each($('#quiz-form').serializeArray(), function(i, field) {
    			params.answers.push(field.value);
    		});
    		$.ajax({
    			type: 'POST',
    			data: params,
    			success: function(data) {
    				$('#quiz-form').hide();
    				$('#quiz-form-placeholder').
    					html(data).
    					fadeIn('slow');
    			},
    			async: false
    		});
        }else{
        $($questions.get(currentQuestion)).fadeIn();
        }
    });

});


$('#back').click(function(){



if(currentQuestion!=0){

    $($questions.get(currentQuestion)).fadeOut(function(){
        currentQuestion = currentQuestion - 1;
        $($questions.get(currentQuestion)).fadeIn();
        document.getElementById("next").value="Next";

    });
    
}               

            });

        });

