function changeQuantity(nameIndex) {
		var quantity = document.getElementById("product" + nameIndex).value;
		var request = new XMLHttpRequest();
		var params = "?product=" + nameIndex + "&quantity=" + quantity;
		
		request.onreadystatechange = function() {
			//when response is received
			if (this.readyState == 4 && this.status == 200) {
			}
			else
			if (this.readyState == 4 && this.status == 401) {
				alert("Sorry, you must be admin to like this video!");
			}
				
		}
		request.open("post", "../basket/quantity" + params, true);
		request.send();
	}