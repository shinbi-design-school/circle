document.addEventListener('DOMContentLoaded', () => {

	const button = document.getElementsByClassName('stuffed-roll')[0];
	const video = document.getElementsByClassName('stuffed-roll-mv')[0];

	button.addEventListener('click', zoomin, false);
	
	function zoomin() {
		button.className = 'stuffed-roll-play';
		button.removeEventListener('click',zoomin);
		button.addEventListener('click', zoomout, false);
		video.classList.remove('mv-hidden');
	}
	
	function zoomout() {
		button.className = 'stuffed-roll-stop';
		button.removeEventListener('click',zoomout);
		button.addEventListener('click', zoomin, false);
		video.classList.add('mv-hidden');
		setTimeout(() => {
			button.className = 'stuffed-roll';
			}, 800);
	}
}, false);
