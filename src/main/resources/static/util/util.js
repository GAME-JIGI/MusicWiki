//비동기로 요청을 날리고, 요청이 성공하면 리다이렉트 합니다.
function fetchRedirect(url, method, redirectUrl) {
  fetch(url, {method:method})
  .then(function (response) {
    if (response.ok) {
      location.href = redirectUrl
    } else {
      alert('네트워크 오류');
    }});
}

//비동기로 post 요청을 날리고, 요청이 성공하면 리다이렉트 합니다.
function postRedirect(url, formId, redirectUrl) {
  let form = document.getElementById(formId)
  let requestBody = new FormData(form)

  fetch(url, {method: "post", body: requestBody})
  .then(function (response) {
    if (response.ok) {
      location.href = redirectUrl
    } else {
      alert('네트워크 오류');
    }});
}

//특정 페이지로 이동하기
function movePage(url, page, param) {
  location.href = `${url}?page=${page}${param}`;
}