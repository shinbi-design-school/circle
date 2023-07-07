// タイトル画面

window.onpageshow = () => {	
	$(".title-mv, .start-button").removeClass("mv-hidden");
	$(".intro-mv").addClass("mv-hidden");
	
	$(".start-button").on('click', () => {
		$(".title-mv, .intro-mv, .start-button").toggleClass("mv-hidden");
		window.setTimeout(() => {
			window.location.href = "./start";
		}, 6000)
	});
}