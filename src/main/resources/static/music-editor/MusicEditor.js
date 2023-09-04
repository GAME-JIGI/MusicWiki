const content = `asdfasdfasdf
$$abc
X:1
T:예시1
K:Bb
Bcde|
$$
asdfasdfasdf
$$abc
K:Bb
Bcde|
$$
aasdfasdf`;

document.addEventListener("DOMContentLoaded", onLoad);

function onLoad() {

  const editor1 = new toastui.Editor({
    el: document.getElementById("editor1"),
    height: '500px',
    initialValue: content
  });

  const editor2 = new toastui.Editor({
    el: document.getElementById("editor2"),
    previewStyle: 'vertical',
    height: '500px',
    plugins: [abcPlugin1],
    initialValue: content
  });

  const editor3 = new toastui.Editor({
    el: document.getElementById("editor3"),
    previewStyle: 'vertical',
    height: '500px',
    plugins: [abcPlugin2],
    initialValue: content
  });
}

function abcPlugin1() { //악보 띄우기만 가능한 버전
  const toHTMLRenderers = {
    abc(node, content) {
      const abcString = node.literal;
      const randomValue = Math.floor(Math.random() * 10000);
      const id = `abc${randomValue}`;

      setTimeout(
        () => {
          ABCJS.renderAbc(id, abcString);
        },
        1000
      );

      return [
        { type: 'openTag', tagName: 'div', outerNewLine: true, attributes: { id } },
        { type: 'closeTag', tagName: 'div', outerNewLine: true }
      ];
    },
  }

  return { toHTMLRenderers }
}

function abcPlugin2() { //음표 클릭 이벤트 받는 버전
  const toHTMLRenderers = {
    abc(node, content) {
      const abcString = node.literal;
      const randomValue = Math.floor(Math.random() * 10000);
      const id = `abc${randomValue}`;

      setTimeout(
        () => {
          const dragging = true;
          const options = { dragging, clickListener }
          ABCJS.renderAbc(id, abcString, options);
        },
        1000
      );

      return [
        { type: 'openTag', tagName: 'div', outerNewLine: true, attributes: { id } },
        { type: 'closeTag', tagName: 'div', outerNewLine: true }
      ];
    },
  }

  return { toHTMLRenderers }
}

function clickListener(abcelem, tuneNumber, classes, analysis, drag, mouseEvent) {
  console.log({ abcelem, tuneNumber, classes, analysis, drag, mouseEvent });
}