$(document).ready(function () {
  // Activate WOW.js
  new WOW().init();
});

$(window).ready(function() {
  // Splash Screen
  $("#splash").fadeOut();
});
$(function()
		{
		    $(document).on('click', '.recipe-ingredients .btn-add', function(e)
		    {
		        e.preventDefault();

		        var controlForm = $('.recipe-ingredients'),
		            currentEntry = $(this).parents('.ingredients-list:first'),
		            newEntry = $(currentEntry.clone()).appendTo(controlForm);

		        newEntry.find('input').val('');
		        
		        //get current indexid
		        var currentIndex = $(this).parents().eq(1).find('.index').val();
		        alert('index val'+currentIndex);
		        var updatedIndex = parseInt(currentIndex)+1;
		        var amountId = 'ingredients'+updatedIndex+'.amount';
		        var amountName = 'ingredients['+updatedIndex+'].amount';
		        
		        var uomId = 'ingredients'+updatedIndex+'.unitOfMeasure.id';
		        var uomName = 'ingredients['+updatedIndex+'].unitOfMeasure.id';
		        
		        var uomId = 'ingredients'+updatedIndex+'.unitOfMeasure.id';
		        var uomName = 'ingredients['+updatedIndex+'].unitOfMeasure.id';
		     
		        var uomDescId = 'ingredients'+updatedIndex+'.unitOfMeasure.description';
		        var uomDescName = 'ingredients['+updatedIndex+'].unitOfMeasure.description';
		    
		        var descId = 'ingredients'+updatedIndex+'.description';
		        var descName = 'ingredients['+updatedIndex+'].description';
		    
		        newEntry.find('input.amt').attr('name',amountName).attr('id',amountId);
		        newEntry.find('.uomSelect').attr('name',uomName).attr('id',uomId);
		        newEntry.find('.d-none').attr('name',uomDescName).attr('id',uomDescId);
		        newEntry.find('.desc').attr('name',descName).attr('id',descId);
		        
		        newEntry.find('.index').val(updatedIndex);
				
		       
		       
		       
		        controlForm.find('.ingredients-list:not(:last) .btn-add .fa')
		            .removeClass('fa-plus').addClass('fa-minus');
		        controlForm.find('.ingredients-list:not(:last) .btn-add').
		        removeClass('btn-success').addClass('btn-danger');
		        controlForm.find('.ingredients-list:not(:last) .btn-add').removeClass('btn-add').
		        addClass('btn-remove');
		    }).on('click', '.btn-remove', function(e)
		    {
				/*$(this).parents('.ingredients-list:first').remove();

				e.preventDefault();
				return false;*/
			});
		    
		    //for direction nodes
		    $(document).on('click', '.recipe-directions .btn-add', function(e)
				    {
				        e.preventDefault();
				        console.log('inside click method');
				        var controlForm = $('#ol-directions'),
				            currentEntry = $(this).parents('.li-directions:first'),
				            newEntry = $(currentEntry.clone()).appendTo(controlForm);
				        console.log('new entry'+newEntry.text());
				        newEntry.find('input').val('');
				        controlForm.find('.li-directions:not(:last) .btn-add .fa')
				            .removeClass('fa-plus').addClass('fa-minus');
				        controlForm.find('.li-directions:not(:last) .btn-add').
				        removeClass('btn-success').addClass('btn-danger');
				        controlForm.find('.li-directions:not(:last) .btn-add').removeClass('btn-add').
				        addClass('btn-remove');
				    }).on('click', '.btn-remove', function(e)
				    {
						$(this).parents('.li-directions:first').remove();

						e.preventDefault();
						return false;
					});
		    
		    $('#recipeForm').submit(function () {

		        // Get the Login Name value and trim it
		   //    var name = $.trim($('#log').val());
		        console.log('inside submit function');
		        $("#ol-directions input[name='directions']").each(function() {
		        	var tempVal = $(this).val();
		        	var newTemp = '<li>'+tempVal+'</li>'
		           $(this).val(newTemp);
		        });
		       
		    }); 
		
		    	$(".file-ico").click(function () {
		    	  console.log('uplod-file-div is clicked....');
		    	  $('#uploadfile').trigger('click');
		    	});

		    	$('#uploadfile').on('change',function(e){
		    		var val = $(this).val();
			      console.log('file change has happened and val ='+val);
			  
			     var selectedFile = e.target.files[0];
			     var reader = new FileReader();

			     var imgtag = document.getElementById("base_image_url");
			     imgtag.title = selectedFile.name;
			     $('.upload-file-div span').text(selectedFile.name);
			     reader.onload = function(event) {
			       imgtag.src = event.target.result;
			     };

			     reader.readAsDataURL(selectedFile);
		    	});
		    	$('#upload').on('click', function() {
		    		
		      	 $('#loaderIcon').removeClass("d-none");
		    	 setTimeout(uploadFile, 5000)
		    	// uploadFile();
		    	});
		    	
		    	function uploadFile() {
		    		 var dataimg = new FormData();
		    		     dataimg.append('uploadfile', $("#uploadfile")[0].files[0]);
		    		     dataimg.append('recipeId',$('#recipeForm #id').val());
		    		    
		    		
		    		  $.ajax({
		    		    url: "/recipe/ImageUpload",
		    		    enctype: 'multipart/form-data',
		    		    type: "POST",
		    		    data: dataimg,
		    		    processData: false,
		    		    contentType: false,
		    		    timeout: 600000,
		    		    cache: false,
		    		    success: function () {
		    		   // alert('image upload sucessfull');
		   		    	 $('#loaderIcon').addClass("d-none");
		   		 	 
		    		    },
		    		    error: function () {
		    		      alert('image upload error');
		    		    }
		    		  });
		    		} // f
		});

function updateUOMVal(caller){
	var temp =caller.find('option:selected').text();
	caller.next().val(temp);
	
}
