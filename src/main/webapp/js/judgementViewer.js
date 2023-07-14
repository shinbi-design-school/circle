import * as THREE from './three/three.module.js';
import * as BufferGeometryUtils from './three/BufferGeometryUtils.js';

class Correct extends THREE.Mesh{
  constructor() {
    const geometry = new THREE.TorusGeometry( 12, 2.5, 8, 32 );
    const material = new THREE.MeshToonMaterial({color: 0xff0000});
    super(geometry, material);
  }
}

class Incorrect extends THREE.Mesh{
  constructor() {
    const boxes = [];
    const size = 30;
    const geometry0 = new THREE.BoxGeometry( size,size/5,size/5 );
    boxes.push(geometry0.translate(0,0,0));
    const geometry1 = new THREE.BoxGeometry( size/5,size/2 - size / 10,size/5 );
    boxes.push(geometry1.translate(0,size*0.3,0));
    const geometry2 = new THREE.BoxGeometry( size/5,size/2 - size / 10,size/5  );
    boxes.push(geometry2.translate(0,-(size*0.3),0));
    const geometry = BufferGeometryUtils.mergeGeometries(boxes);
    geometry.rotateZ(Math.PI / 4);

    const material = new THREE.MeshToonMaterial({color: 0x0000cf});
    super(geometry, material);
  }
}


document.addEventListener('DOMContentLoaded', () => {
  const renderTarget = document.getElementsByClassName('judgement-canvas')[0];

  let width = innerWidth;
  let height = innerHeight;

  const renderer = new THREE.WebGLRenderer({
    canvas: renderTarget,
     alpha: true,
  });
  const scene = new THREE.Scene();
  
  const camera = new THREE.PerspectiveCamera(45, width / height, 0.1, 300);
  camera.position.set(0, 0, +50);

  const directionalLight = new THREE.DirectionalLight(0xFFFFFF, 0.5);
  directionalLight.position.set(0, 1, 1);
  scene.add(directionalLight);

  const pointLight = new THREE.PointLight(0xFFFFFF, 0.5, 100, 5.0);
  pointLight.position.set(0, 1, 1)
  scene.add(pointLight);

  const hemisphereLight = new THREE.HemisphereLight(0x888888, 0x0000FF, 0.6);
  hemisphereLight.position.set(0, -1, -1);
  scene.add(hemisphereLight);
  
  camera.lookAt(new THREE.Vector3(0, 0, 0));

  const incorrectMesh = new Incorrect();
  const correctMesh = new Correct();
  scene.add(incorrectMesh, correctMesh);

  let time = 0;
  const loop = () => {
    let angle = (1 - time) ** 2;
    time += 0.01;
    correctMesh.rotation.y += angle;
    incorrectMesh.rotation.y += angle;
//    correctMesh.rotateY( angle );
//    incorrectMesh.rotateY( angle );
    renderer.setSize(innerWidth, innerHeight);
    renderer.setPixelRatio('window'.devicePixelRatio);
    camera.aspect = innerWidth / innerHeight;
    camera.updateProjectionMatrix();
    renderer.render(scene, camera);
    if (angle <= 0.001) {
		ob.observe(renderTarget, obconfig);
		return;
    }
 	requestAnimationFrame(loop);
  }

  const ob = new MutationObserver((mutations) => {
	 mutations.forEach((mutation) => {		 
		 if (mutation.target.className === 'judgement-canvas canvas-incorrect'){
			 ob.disconnect();
			 time = 0;
			 correctMesh.position.set(0,-9000,0);
			 incorrectMesh.position.set(0,0,0);
			 incorrectMesh.rotation.y = 0.722519189487739;
			 loop();
		 }

		 if (mutation.target.className === 'judgement-canvas canvas-correct'){
			 ob.disconnect();
			 time = 0;
			 correctMesh.position.set(0,0,0);
			 incorrectMesh.position.set(0,-9000,0);
			 correctMesh.rotation.y = 0.722519189487739;
			 loop();
		 }
		 
	 });
  });
  const obconfig = { attributes : true };
  ob.observe(renderTarget, obconfig);

});