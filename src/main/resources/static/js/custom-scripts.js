function searchCustomer() {
	let searchField = document.getElementById('searchField').value;
	if (!searchField || searchField.trim() == '') {
		document.getElementById('warningNoText').style.display = "block";
	} else {
		window.location.href = '/tools-app/customer-management/customers?searchField=' + searchField;
		document.getElementById('warningNoText').style.display = "none";
	}
}

function clearSearchBar() {
	document.getElementById('searchField').value = '';
	window.location.href = '/tools-app/customer-management/customers';
}