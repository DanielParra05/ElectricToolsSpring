function searchCustomer() {
	let searchField = document.getElementById('searchField').value;
	if (!searchField || searchField.trim() == '') {
		document.getElementById('warningNoText').style.display = "block";
	} else {
		window.location.href = '/tools-app/customer-management/customers?searchField=' + searchField;
		document.getElementById('warningNoText').style.display = "none";
	}
}

function clearSearchBar(redirectUrl) {
	document.getElementById('searchField').value = '';
	window.location.href = redirectUrl;
}

function searchOrder() {
	let searchField = document.getElementById('searchField').value;
	if (!searchField || searchField.trim() == '' || !isNumber(searchField)) {
		document.getElementById('errorSearchOrder').style.display = "block";
	} else {
		window.location.href = '/tools-app/order-management/orders?searchField=' + searchField;
		document.getElementById('errorSearchOrder').style.display = "none";
	}
}

function isNumber(stringToEvaluate) {
	return Boolean(/^\d+$/.test(stringToEvaluate));
}