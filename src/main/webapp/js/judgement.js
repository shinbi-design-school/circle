function EncodeHTMLForm( data )
{
    var params = [];

    for( var name in data )
    {
        var value = data[ name ];
        var param = encodeURIComponent( name ) + '=' + encodeURIComponent( value );

        params.push( param );
    }

    return params.join( '&' ).replace( /%20/g, '+' );
}

document.addEventListener('DOMContentLoaded', () => {
		
	const sentenceContainer = document.getElementsByClassName("quiz-question")[0];
	const choiceContainer01 = document.getElementsByClassName("quiz-text01")[0];	
	const choiceContainer02 = document.getElementsByClassName("quiz-text02")[0];	
	const choiceContainer03 = document.getElementsByClassName("quiz-text03")[0];	
	const choiceContainer04 = document.getElementsByClassName("quiz-text04")[0];
	choiceContainer01.setAttribute('num', '0');
	choiceContainer02.setAttribute('num', '1');
	choiceContainer03.setAttribute('num', '2');
	choiceContainer04.setAttribute('num', '3');
	let token;
	const viewCorrect = document.getElementsByClassName("correct-image")[0];
	const viewIncorrect = document.getElementsByClassName("incorrect-image")[0];

	const get = () => {		
		fetch("advance", {
		  method: "GET",
		}).then(response => {
			if (response.ok){
				return response.text();
			} else {
				throw new Error();
			}
		})
		.then(text => {
			let lines = text.split(/\r?\n/)
			sentenceContainer.innerHTML = lines[0];
			choiceContainer01.value = lines[1];
			choiceContainer02.value = lines[2];
			choiceContainer03.value = lines[3];
			choiceContainer04.value = lines[4];
			token = lines[5];
		})
		.catch(error => console.log(error));
		
		
	}
	
	//連打したときにおかしくなる？
	const post = (e) => {
		
		const data = {"userChoice" : e.target.getAttribute('num'), "token" : token};
		
		fetch("advance", {
		  method: "POST",
		  headers: { 'Content-Type': 'application/x-www-form-urlencoded'},
		  body: EncodeHTMLForm( data ),
		}).then(response => {
			if (response.ok){
				return response.text();
			} else {
				throw new Error();
			}
		})
		.then(text => {
			let lines = text.split(/\r?\n/);
			
			if ( lines[0] === 'correct' ){
				viewCorrect.style.visibility = "visible";
				viewIncorrect.style.visibility = "hidden";
			} else {
				viewCorrect.style.visibility = "hidden";
				viewIncorrect.style.visibility = "visible";
			}
			
			if (lines.length > 1 && lines[1] === "finish"){
				window.location.href = "./play";
			} else {
				get();
			}
				
		})
		.catch(error => console.log(error));
		


	}
	
	get();

	choiceContainer01.onclick = "";
	choiceContainer01.addEventListener("click", post, false);
	choiceContainer02.onclick = "";
	choiceContainer02.addEventListener("click", post, false);
	choiceContainer03.onclick = "";
	choiceContainer03.addEventListener("click", post, false);
	choiceContainer04.onclick = "";
	choiceContainer04.addEventListener("click", post, false);

}, false);


