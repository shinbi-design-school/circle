
import * as THREE from './three/three.module.js';
import { SimplexNoise } from './three/SimplexNoise.js';

document.addEventListener('DOMContentLoaded', () => {
  const renderTarget = document.getElementsByClassName('particles')[0];

  let width = innerWidth;
  let height = innerHeight;

  const renderer = new THREE.WebGLRenderer({
    canvas: renderTarget,
     alpha: true,
  });
  
  const camera = new THREE.PerspectiveCamera(45, width / height, 0.1, 3000);
  camera.position.set(-600, +200, +900);

  camera.lookAt(new THREE.Vector3(0, 0, 0));
  const scene = new THREE.Scene();
  scene.fog = new THREE.Fog(0x000000, 50, 2000);

  const geometry = new THREE.PlaneGeometry(2500, 2500, 50, 50);

  const material = new THREE.PointsMaterial({
    size: 5,
    color: 0x2fdfff,
  });

  const mesh = new THREE.Points(geometry, material);
  mesh.rotateX(Math.PI / 2);
  scene.add(mesh);

  const simplexNoise = new SimplexNoise();

  const loop = () => {
    const position = mesh.geometry.attributes.position;
    for (let i = 0; i < position.count; i++) {
      const x = position.getX(i);
      const y = position.getY(i);

      const time = Date.now() * 0.000025;

      const nextZ = simplexNoise.noise(x * 0.002 + time, y * 0.001 + time) * 150;

      position.setZ(i, nextZ);
    }

    position.needsUpdate = true;

    renderer.setSize(innerWidth, innerHeight);
    renderer.setPixelRatio('window'.devicePixelRatio);
    camera.aspect = innerWidth / innerHeight;
    camera.updateProjectionMatrix();
    renderer.render(scene, camera);
    requestAnimationFrame(loop);
  }
  loop();

});