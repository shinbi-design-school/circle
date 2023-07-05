document.addEventListener('DOMContentLoaded', () => {
	const sentenceContainer = document.getElementsByClassName("quiz-question")[0];
	const choiceContainers01 = document.getElementsByClassName("quiz-text01")[0];	
	const choiceContainers02 = document.getElementsByClassName("quiz-text02")[0];	
	const choiceContainers03 = document.getElementsByClassName("quiz-text03")[0];	
	const choiceContainers04 = document.getElementsByClassName("quiz-text04")[0];
	const tokenContainer = document.getElementById("token");
	const viewCorrect = document.getElementsByClassName("correct-image")[0];
	const viewinCorrect = document.getElementsByClassName("incorrect-image")[0];

	const get = function(){
		const xhr = new XMLHttpRequest();
		xhr.setRequestHeader( 'Content-Type', 'application/x-www-form-urlencoded' );
		xhr.open("GET", "advance");
		xhr.send();
		
		xhr.onreadystatechange = () => {
			if( this.readyState == 4 && this.status == 200 ){
				//問題文と選択肢×4がはいったplain/textを処理する
				let lines = this.responseText.split(/\r?\n/);
				sentenceContainer.innerHTML = lines[0];
				choiceContainers01.innerHTML = lines[1];
				choiceContainers02.innerHTML = lines[2];
				choiceContainers03.innerHTML = lines[3];
				choiceContainers04.innerHTML = lines[4];
				tokenContainer.innerHTML = lines[5];
			}
		};
		
		
	}
	
	//連打したときにおかしくなるので正誤判定まで処理を待たせる処理を追加する必要がある。
	const post = function(userChoice, token){
		const xhr = new XMLHttpRequest();
		xhr.setRequestHeader( 'Content-Type', 'application/x-www-form-urlencoded' );
		const data = {"userChoice" : userChoice, "token" : token};

		xhr.open("POST", "advance");
		xhr.send( EncodeHTMLForm( data ) );	

		let response;
		xhr.onreadystatechange = () => {
			if( this.readyState == 4 && this.status == 200 ){
				if ( this.responseText === 'correct' ){
					return true;
				} else {
					return false;
				}
			}
		};		

	}
	
	choiceContainers01.onclick() = post("0", token.value);		
	choiceContainers02.onclick() = post("1", token.value);		
	choiceContainers03.onclick() = post("2", token.value);		
	choiceContainers04.onclick() = post("3", token.value);		

}, false);


