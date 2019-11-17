var XHR = null;
function getXHR() {
	if (window.ActiveXObject) {
		return new ActiveXObject('Msxml2.XMLHTTP');
	} else if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	} else {
		return null;
	}
}
function sendRequest(url, param, method, callback) {
	XHR = getXHR();

	if (method) {
		newMethod = method;
	} else {
		newMethod = 'GET';
	}
	
	if (newMethod != 'GET' && newMethod != 'POST') {
		newMethod = 'GET';
	}
	
	var newParam = (param == null || param == '') ? null : param;
	newUrl = url;
	if (newMethod == 'GET' && newParam != null) {
		newUrl = newUrl + '?' + newParam;
	}
	XHR.onreadystatechange = callback;
	XHR.open(newMethod, newUrl, true);
	XHR.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	XHR.send(newMethod == 'POST' ? newParam : null);
}