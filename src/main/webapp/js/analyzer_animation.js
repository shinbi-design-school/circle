class Ring {
	constructer(){
		this.centerX = 0;
		this.centerY = 0;
		this.angle = 0;
		this.len = 0;
		this.thickness = 0;
		this.radius = 0;
		this.color = 'rgba(0,0,0,1)';
	}
	
	setCenter(x, y){
		this.centerX = x;
		this.centerY = y;
	}
	
	setAngle(rad) {
		this.angle = rad;
	}
	
	setLen(l) {
		this.len = l;
	}
	
	setThickness(t) {
		this.thickness = t;
	}
	
	setRadius(r) {
		this.radius = r;
	}
	
	setColor(rgba) {
		this.color = rgba;
	}
	
	render(ctx) {
		while (this.angle <= 0){
			this.angle += Math.PI * 2;
		}
		
		while (this.angle >= Math.PI){
			this.angle -= Math.PI * 2;
		}
		
		ctx.fillStyle = this.color;
		ctx.shadowColor = this.color;
		ctx.beginPath();
		ctx.arc(this.centerX, this.centerY, this.radius, this.angle-this.len, this.angle, false );
		ctx.lineTo(this.centerX + this.radius * Math.cos(this.angle), this.centerY + this.radius * Math.sin(this.angle));
		ctx.arc(this.centerX, this.centerY, this.radius-(this.radius*this.thickness), this.angle, this.angle-this.len, true );
		ctx.fill();
	}
}

document.addEventListener('DOMContentLoaded', () => {
	const image = document.getElementsByClassName('image')[0];
	const canvas = document.getElementsByClassName('analyzer')[0];
	let width = image.clientWidth;
	let height = image.clientHeight;
	canvas.width = width;
	canvas.height = height;
	
	const ctx = canvas.getContext('2d');
	ctx.shadowBlur = 40;
	
	const ringColor = 'rgba(50, 192, 255, 0.75)';
	
	const ring1 = new Ring(); 
	ring1.setAngle(0);
	ring1.setCenter(width / 2, height / 2 );
	ring1.setLen(Math.PI * 1.5);
	ring1.setThickness(0.05);
	ring1.setRadius(width/2 * 0.8);
	ring1.setColor(ringColor);
	
	const ring2 = new Ring();
	ring2.setAngle(0);
	ring2.setCenter(width / 2, height / 2 );
	ring2.setLen(0.9);
	ring2.setThickness(0.1);
	ring2.setRadius(ring1.radius * 0.8);
	ring2.setColor(ringColor);
	
	const ring3 = new Ring();
	ring3.setAngle(0);
	ring3.setCenter(width / 2, height / 2 );
	ring3.setLen(Math.PI * 1.7);
	ring3.setThickness(0.03);
	ring3.setRadius(ring1.radius * 0.2);
	ring3.setColor(ringColor);
	
	const ring4 = new Ring();
	ring4.setAngle(0);
	ring4.setCenter(width / 2, height / 2 );
	ring4.setLen(0.5);
	ring4.setThickness(0.5);
	ring4.setRadius(ring1.radius * 0.60);
	ring4.setColor(ringColor);
	
	const ring5 = new Ring();
	ring5.setAngle(0);
	ring5.setCenter(width / 2, height / 2 );
	ring5.setLen(Math.PI * 1.7);
	ring5.setThickness(0.03);
	ring5.setRadius(ring1.radius * 0.7);
	ring5.setColor(ringColor);
	
	const ring6 = new Ring(); 
	ring6.setAngle(0);
	ring6.setCenter(width / 2, height / 2 );
	ring6.setLen(Math.PI * 1.5);
	ring6.setThickness(0.02);
	ring6.setRadius(ring1.radius * 1.05);
	ring6.setColor(ringColor);
	
	const loop = () =>{
		ctx.clearRect(0, 0, width, height);
		
		ring1.setAngle(ring1.angle + 0.01);
		ring1.render(ctx);
		
		ring2.setAngle(ring2.angle + 0.03);
		ring2.render(ctx);
		
		ring3.setAngle(ring3.angle + 0.005);
		ring3.render(ctx);

		ring4.setAngle(ring4.angle - 0.05);
		ring4.render(ctx);
		
		ring5.setAngle(ring5.angle - 0.012);
		ring5.render(ctx);
		
		ring6.setAngle(ring6.angle - 0.15);
		ring6.render(ctx);

		window.requestAnimationFrame(loop);
	}
	
	loop();
}, false);
