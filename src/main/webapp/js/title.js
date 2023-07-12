// タイトル画面

window.onpageshow = () => {	
	$(".title-mv, .start-button, .mypage-link-container").removeClass("mv-hidden");
	$(".intro-mv").addClass("mv-hidden");
	
	$(".start-button").on('click', () => {
		$(".title-mv, .intro-mv, .start-button, .mypage-link-container").toggleClass("mv-hidden");
		window.setTimeout(() => {
			window.location.href = "./start";
		}, 6000)
	});
}