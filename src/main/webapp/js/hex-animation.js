class Particle {
  constructor(x, y) {
    this.x = x;
    this.y = y;
    this.color = 'rgba(255, 255, 255, 1)';
    this.gradientColor = 'rgba(0, 0, 255, 0)'
    let rand = Math.random()
    if (rand < (1 / 3)){
      this.angle = Math.PI * 2 / 3;
    } else if(rand < (2 / 3)){
      this.angle = Math.PI * 2 * 2 / 3;
    } else {
      this.angle = Math.PI * 2;
    }
    this.speed = 8;
    this.bones = [];
    this.boneLimit = 6
    this.distTotal = 0;
    this.boneDist = 60;
    this.curvePro = 0.5

    this.lifespan = 1500;
    this.alpha = 1;
    this.alive = true;
  }

  setPos(x, y) {
    this.x = x;
    this.y = y;
    this.endX = this.x;
    this.endY = this.y;
  }

  len() {
    const lastX = this.bones.slice(-1)[0][0];
    const lastY = this.bones.slice(-1)[0][1];
    return Math.sqrt((this.x - lastX)**2  + (this.y - lastY)**2)
  }

  addBone() {
    if (this.distTotal == 0 || this.distTotal > this.boneDist) {
      this.distTotal = 0
      this.bones.unshift([this.x, this.y]);
      if (Math.random() > this.curvePro) {
        this.angle += Math.PI / 3;
        this.curvePro += 0.2;
      } else {
        this.angle -= Math.PI / 3;
        this.curvePro -= 0.2;
      }
    }

    if (this.bones.length > this.boneLimit) {
      this.bones.pop()
    }
  }

  death() {
    if (this.lifespan < 0){
      this.alpha -= 0.01;
      this.color = `rgba(255, 255, 255, ${this.alpha})`
    }
    
    if(this.alpha < 0 ){
      this.alive = false;
      this.alpha = 0;
    }
  }

  render(ctx) {
    this.addBone();
    let gradient = ctx.createRadialGradient(this.x,this.y,0,this.x,this.y,this.len());
    gradient.addColorStop(0, this.color);
    gradient.addColorStop(1, this.gradientColor);
    
    ctx.shadowColor = 'rgba(80, 80, 255, 1)';
    ctx.shadowBlur = 50;

    ctx.beginPath();
    ctx.strokeStyle = gradient;
    ctx.lineWidth = 2;
    ctx.lineCap = 'round';
    ctx.lineJoin = 'round';
    ctx.moveTo(this.x, this.y);
    for (let i of this.bones){

      ctx.lineTo(i[0], i[1]);

    }
    ctx.stroke();

    let marginX = Math.sin(this.angle) * this.speed
    let marginY = Math.cos(this.angle) * this.speed

    this.x += marginX ;
    this.y += marginY ;

    let dist = Math.sqrt(marginX**2  + marginY**2)
    this.distTotal += dist;
    this.lifespan -= dist;

    this.death();

  }
}

class Flash {
  constructor(x, y, r, span) {
    this.x = x;
    this.y = y;
    this.r = r;
    this.span = span;
    this.flashLen = 10;
    this.timer = 0;
    this.color = 'rgba(255, 255, 255, 0.8)';
    this.gradientColor = 'rgba(0, 0, 255, 0)';
    this.power = 0;
  }

  flash() {
    this.timer = this.flashLen;
  }

  rotation() {
    if (this.timer < 0) {
      return
    }

    if (this.timer > this.flashLen * 0.9){
      this.power = Math.sin(Math.PI / 2 * ((this.flashLen - this.timer) / (this.flashLen * 0.1))) ** 2 ;
    } else {
      this.power = Math.sin(Math.PI / 2 * (this.timer / this.flashLen * 0.9)) ** 2;
    }

    this.timer -= 1;
  }

  render(ctx){
    this.rotation();

    ctx.beginPath();
    ctx.moveTo(this.x, this.y);

    ctx.shadowBlur = 0;
    let gradient  = ctx.createRadialGradient(this.x, this.y, this.r / 15 * this.power, this.x, this.y, this.r * this.power);
    gradient.addColorStop(0, this.color);
    gradient.addColorStop(1, this.gradientColor);
    ctx.fillStyle = gradient;
    ctx.arc(this.x, this.y, this.r, 0, Math.PI * 2);
    ctx.fill();
  }
}

document.addEventListener('DOMContentLoaded', () => {
	
	const cvs = document.getElementsByClassName('background')[0];
	cvs.width = cvs.clientWidth;
	cvs.height = cvs.clientHeight;
	const ctx = cvs.getContext('2d');
	
	let width = cvs.width;
	let height = cvs.height;
	
	ctx.fillStyle = 'rgb(0,0,0)';
	ctx.fillRect(0,0,width,height);
	ctx.fill();
	
	let particles = []
	particles.push(new Particle(width / 2, height / 2));
	
	let flash = new Flash(width / 2, height / 2, 80, 250);
	
	let timer = 0;
	const loop = () => {
	  if (timer % 250 == 0){
		  flash.flash();
	  }
		
	  if (timer % 2 == 0 && timer % 250 <= 50){
	    particles.push(new Particle(width / 2, height / 2));
	  }

	  timer = timer <= 250 ? timer + 1 : 0;
	
	
	  cvs.width = cvs.clientWidth;
	  cvs.height = cvs.clientHeight;
	
	  ctx.fillStyle = 'rgb(0,0,0)';
	  ctx.fillRect(0,0,cvs.width,cvs.height);
	
	  // ctx.clearRect(0,0,width,height);
	  let i = 0
	  while(i < particles.length){
	    particles[i].render(ctx);
	    if(!particles[i].alive){
	      particles.splice(i, 1);
	      i--
	    } 
	    i++
	  }
	  
	  flash.render(ctx);
	  
	  requestAnimationFrame(loop);
	}
	loop();
});
