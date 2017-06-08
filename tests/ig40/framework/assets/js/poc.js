$(document).ready(function(){
    /*$("#introduction").click(function(){
        
		 alert(this.id);
		  $( "div[id*='Div']" ).hide();
		 $("#introDiv").show();
		
    });
	$("#background").click(function(){
       
		 alert(this.id);
		  $( "div[id*='Div']" ).hide();
		 $("#backgroundDiv").show();
		
    });*/
	$("a").click(function(){
        /*$("#conformance").hide();
		*/
		 $( "div[id*='Div']" ).hide();
		
		 $("#"+this.id+"Div").show();
		 // alert(this.id);
    });
   $( "div[id*='Div']" ).hide();
   $("#introductionDiv").show();
   
});
