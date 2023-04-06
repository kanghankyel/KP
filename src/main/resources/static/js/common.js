// 파일경로 설정방법(host domain 변경시에도 적용될 수 있게)
let path = window.location.host;
let protocol = window.location.protocol+'//';
let urlparam = protocol+path;
// console.log(path);
// console.log(protocol);
// console.log(urlparam);