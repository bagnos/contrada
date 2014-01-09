function hideLoading() {
	document.getElementById('loading').style.display = "none";
	document.getElementById('pagine').style.display = "block";
}
function loadIFrame(page) {
	document.getElementById('loading').style.display = "block";
	document.getElementById('pagine').style.display = "none";
	document.getElementById('pagina').src=page; 
}
