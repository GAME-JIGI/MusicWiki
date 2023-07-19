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

//비동기로 patch 요청을 날리고, 요청이 성공하면 리다이렉트 합니다.
function patchRedirect(url, formId, redirectUrl) {
  let form = document.getElementById(formId)
  let requestBody = new FormData(form)

  fetch(url, {method: "PATCH", body: requestBody})
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

//쿼리 파라미터랑 이동하기
function moveParam(url, param) {
  location.href = url + "?" + param;
}

//쿼리 파라미터를 수정하기 //value 가 빈 문자열이면 삭제함
function editParam(param, key, value) {
  param += "";
  key += "";
  value += "";

  const isValueEmpty = value === "";
  const pair = isValueEmpty ? "" : `${key}=${value}`;
  const ampersand = isValueEmpty ? "" : "&";

  const isEmpty = param.indexOf('=') === -1;
  if (isEmpty) {
    return pair;
  }

  const oldStartIndex = param.indexOf(key + '=');
  const isNew = oldStartIndex === -1;
  if (isNew) {
    return `${param}${ampersand}${pair}`;
  }

  let oldLastIndex = param.slice(oldStartIndex).indexOf('&');
  oldLastIndex = oldLastIndex === -1 ? param.length - 1 : oldLastIndex - 1;

  const oldLeftStr = param.slice(0, Math.max(0, oldStartIndex - 1));
  const oldRightStr = param.slice(oldLastIndex + 1);
  return `${oldLeftStr}${oldRightStr}${ampersand}${pair}`;
}

//목록 정렬 관련 쿼리 파라미터를 수정하기
function editParamSort(param, sort, col) {
  param = editParam(param, "sort", sort);
  param = editParam(param, "col", col);

  return param
}

//id를 받아서 해당 요소의 값를 가져옴
function getValue(elementId) {
  return document.getElementById(elementId).value
}