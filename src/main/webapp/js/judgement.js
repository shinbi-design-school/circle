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
	let questionNumber = 1;
	const questionNumberContainer = document.getElementsByClassName("quiz-question-number")[0];
	const judgementCanvas = document.getElementsByClassName("judgement-canvas")[0];
	
	viewCorrect.style.visibility = "hidden";
	viewIncorrect.style.visibility = "hidden";
	
	const finish = () => {
		window.location.href = "./play";
	}

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
			viewCorrect.style.visibility = "hidden";
			viewIncorrect.style.visibility = "hidden";
			judgementCanvas.className = "judgement-canvas canvas-hidden";
			
			questionNumberContainer.textContent = `問題${questionNumber++}`;
			
			choiceContainer01.onclick = "";
			choiceContainer01.addEventListener("click", post, false);
			choiceContainer02.onclick = "";
			choiceContainer02.addEventListener("click", post, false);
			choiceContainer03.onclick = "";
			choiceContainer03.addEventListener("click", post, false);
			choiceContainer04.onclick = "";
			choiceContainer04.addEventListener("click", post, false);
			
		})
		.catch(error => console.log(error));
		
		
	}
	
	const post = (e) => {
		e.target.removeEventListener("click", post);
		
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
				judgementCanvas.className = "judgement-canvas canvas-correct";
			} else if ( lines[0] === 'incorrect' ) {
				viewCorrect.style.visibility = "hidden";
				viewIncorrect.style.visibility = "visible";
				judgementCanvas.className = "judgement-canvas canvas-incorrect";
			}
			
			if (lines.length > 1 && lines[1] === "finish"){
				setTimeout(finish, 2000);
			} else {
				setTimeout(get, 2000);
			}
				
		})
		.catch(error => console.log(error));
		
	}
	
	get();

}, false);


