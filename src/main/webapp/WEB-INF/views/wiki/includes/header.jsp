<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="vector-header-container">
  <header class="vector-header mw-header">
    <div class="vector-header-start">
      <nav class="vector-main-menu-landmark" aria-label="사이트" role="navigation">

        <div id="vector-main-menu-dropdown"
             class="vector-dropdown vector-main-menu-dropdown vector-button-flush-left vector-button-flush-right">
          <input type="checkbox"
                 id="vector-main-menu-dropdown-checkbox"
                 role="button"
                 aria-haspopup="true"
                 data-event-name="ui.dropdown-vector-main-menu-dropdown"
                 class="vector-dropdown-checkbox "

                 aria-label="주 메뉴"

          />
          <label
              id="vector-main-menu-dropdown-label"
              for="vector-main-menu-dropdown-checkbox"
              class="vector-dropdown-label cdx-button cdx-button--fake-button cdx-button--fake-button--enabled cdx-button--weight-quiet cdx-button--icon-only "
              aria-hidden="true"

          >
            <span class="vector-icon mw-ui-icon-menu mw-ui-icon-wikimedia-menu"></span>


            <span class="vector-dropdown-label-text">주 메뉴</span>
          </label>
          <div class="vector-dropdown-content">


            <div id="vector-main-menu-unpinned-container" class="vector-unpinned-container">

              <div id="vector-main-menu" class="vector-main-menu vector-pinnable-element">
                <div
                    class="vector-pinnable-header vector-main-menu-pinnable-header vector-pinnable-header-unpinned"
                    data-feature-name="main-menu-pinned"
                    data-pinnable-element-id="vector-main-menu"
                    data-pinned-container-id="vector-main-menu-pinned-container"
                    data-unpinned-container-id="vector-main-menu-unpinned-container"
                >
                  <div class="vector-pinnable-header-label">주 메뉴</div>
                  <button class="vector-pinnable-header-toggle-button vector-pinnable-header-pin-button"
                          data-event-name="pinnable-header.vector-main-menu.pin">사이드바로 이동
                  </button>
                  <button class="vector-pinnable-header-toggle-button vector-pinnable-header-unpin-button"
                          data-event-name="pinnable-header.vector-main-menu.unpin">숨기기
                  </button>
                </div>


                <div id="p-navigation" class="vector-menu mw-portlet mw-portlet-navigation">
                  <div class="vector-menu-heading">
                    둘러보기
                  </div>
                  <div class="vector-menu-content">

                    <ul class="vector-menu-content-list">
                      <li id="n-mainpage-description" class="mw-list-item"><a
                          href="%ec%9c%84%ed%82%a4%eb%b0%b1%ea%b3%bc_%eb%8c%80%eb%ac%b8.html"
                          title="대문으로 가기 [z]" accesskey="z"><span>대문</span></a></li>
                      <li id="n-recentchanges" class="mw-list-item"><a
                          href="%ed%8a%b9%ec%88%98_%ec%b5%9c%ea%b7%bc%eb%b0%94%eb%80%9c.html"
                          title="위키의 최근 바뀐 목록 [r]" accesskey="r"><span>최근 바뀜</span></a></li>
                      <li id="n-currentevents" class="mw-list-item"><a
                          href="%ed%8f%ac%ed%84%b8_%ec%9a%94%ec%a6%98_%ed%99%94%ec%a0%9c.html"
                          title="최근의 소식 알아 보기"><span>요즘 화제</span></a></li>
                      <li id="n-randompage" class="mw-list-item"><a
                          href="%ea%b0%95%ea%b7%9c%ec%9b%90.html" title="무작위로 선택된 문서 불러오기 [x]"
                          accesskey="x"><span>임의의 문서로</span></a></li>
                      <li id="n-sitesupport" class="mw-list-item"><a
                          href="http://donate.wikimedia.org/wiki/Special:FundraiserRedirector?utm_source=donate&amp;utm_medium=sidebar&amp;utm_campaign=C13_ko.wikipedia.org&amp;uselang=ko"
                          title="지원을 기다립니다"><span>기부</span></a></li>
                    </ul>

                  </div>
                </div>


                <div id="p-사용자_모임" class="vector-menu mw-portlet mw-portlet-사용자_모임">
                  <div class="vector-menu-heading">
                    사용자 모임
                  </div>
                  <div class="vector-menu-content">

                    <ul class="vector-menu-content-list">
                      <li id="n-projectchat" class="mw-list-item"><a
                          href="%ec%9c%84%ed%82%a4%eb%b0%b1%ea%b3%bc_%ec%82%ac%eb%9e%91%eb%b0%a9.html"><span>사랑방</span></a>
                      </li>
                      <li id="n-portal" class="mw-list-item"><a
                          href="%ec%9c%84%ed%82%a4%eb%b0%b1%ea%b3%bc_%ec%82%ac%ec%9a%a9%ec%9e%90_%eb%aa%a8%ec%9e%84.html"
                          title="위키백과 참여자를 위한 토론/대화 공간입니다."><span>사용자 모임</span></a></li>
                      <li id="n-request" class="mw-list-item"><a
                          href="%ec%9c%84%ed%82%a4%eb%b0%b1%ea%b3%bc_%ec%9a%94%ec%b2%ad.html"><span>관리 요청</span></a>
                      </li>
                    </ul>

                  </div>
                </div>

                <div id="p-편집_안내" class="vector-menu mw-portlet mw-portlet-편집_안내">
                  <div class="vector-menu-heading">
                    편집 안내
                  </div>
                  <div class="vector-menu-content">

                    <ul class="vector-menu-content-list">
                      <li id="n-help" class="mw-list-item"><a
                          href="%ec%9c%84%ed%82%a4%eb%b0%b1%ea%b3%bc_%eb%8f%84%ec%9b%80%eb%a7%90.html"
                          title="도움말"><span>도움말</span></a></li>
                      <li id="n-policy" class="mw-list-item"><a
                          href="%ec%9c%84%ed%82%a4%eb%b0%b1%ea%b3%bc_%ec%a0%95%ec%b1%85%ea%b3%bc_%ec%a7%80%ec%b9%a8.html"><span>정책과 지침</span></a>
                      </li>
                      <li id="n-qna" class="mw-list-item"><a
                          href="%ec%9c%84%ed%82%a4%eb%b0%b1%ea%b3%bc_%ec%a7%88%eb%ac%b8%eb%b0%a9.html"><span>질문방</span></a>
                      </li>
                    </ul>

                  </div>
                </div>


              </div>

            </div>

          </div>
        </div>
      </nav>

      <a href="%ec%9c%84%ed%82%a4%eb%b0%b1%ea%b3%bc_%eb%8c%80%eb%ac%b8.html" class="mw-logo">
        <img class="mw-logo-icon" src="https://ko.wikipedia.org/static/images/icons/wikipedia.png" alt=""
             aria-hidden="true" height="50" width="50">
        <span class="mw-logo-container">
		<img class="mw-logo-wordmark" alt="위키백과"
             src="https://ko.wikipedia.org/static/images/mobile/copyright/wikipedia-wordmark-ko.svg"
             style="width: 7.5em; height: 1.75em;">
		<img class="mw-logo-tagline"
             alt=""
             src="https://ko.wikipedia.org/static/images/mobile/copyright/wikipedia-tagline-ko.svg" width="120"
             height="13" style="width: 7.5em; height: 0.8125em;">
	</span>
      </a>

    </div>
    <div class="vector-header-end">

      <div id="p-search" role="search"
           class="vector-search-box-vue  vector-search-box-collapses vector-search-box-show-thumbnail vector-search-box-auto-expand-width vector-search-box">
        <a href="%ed%8a%b9%ec%88%98_%ea%b2%80%ec%83%89.html"

           id=""
           class="cdx-button cdx-button--fake-button cdx-button--fake-button--enabled cdx-button--weight-quiet cdx-button--icon-only search-toggle"
           title="위키백과 검색 [f]" accesskey="f"><span
            class="vector-icon mw-ui-icon-search mw-ui-icon-wikimedia-search"></span>

          <span>검색</span>
        </a>

        <div class="vector-typeahead-search-container">
          <div class="cdx-typeahead-search cdx-typeahead-search--show-thumbnail cdx-typeahead-search--auto-expand-width">
            <form action="https://ko.wikipedia.org/w/index.php" id="searchform"
                  class="cdx-search-input cdx-search-input--has-end-button">
              <div id="simpleSearch" class="cdx-search-input__input-wrapper"
                   data-search-loc="header-moved">
                <div class="cdx-text-input cdx-text-input--has-start-icon">
                  <input
                      class="cdx-text-input__input"
                      type="search" name="search" placeholder="위키백과 검색" aria-label="위키백과 검색"
                      autocapitalize="sentences" title="위키백과 검색 [f]" accesskey="f"
                      id="searchInput"

                  >
                  <span class="cdx-text-input__icon cdx-text-input__start-icon"></span>
                </div>
                <input type="hidden" name="title" value="특수:검색">
              </div>
              <button class="cdx-button cdx-search-input__end-button">검색</button>
            </form>
          </div>
        </div>
      </div>

      <nav class="vector-user-links" aria-label="개인 도구" role="navigation">

        <div id="p-vector-user-menu-overflow"
             class="vector-menu mw-portlet mw-portlet-vector-user-menu-overflow">
          <div class="vector-menu-content">

            <ul class="vector-menu-content-list">
              <li id="pt-createaccount-2" class="user-links-collapsible-item mw-list-item"><a
                  href="https://ko.wikipedia.org/w/index.php?title=%ED%8A%B9%EC%88%98:%EA%B3%84%EC%A0%95%EB%A7%8C%EB%93%A4%EA%B8%B0&amp;returnto=%EC%9C%84%ED%82%A4%EB%B0%B1%EA%B3%BC%ED%86%A0%EB%A1%A0%3A%EB%8C%80%EB%AC%B8"
                  title="계정을 만들고 로그인하는 것이 좋습니다. 하지만 필수는 아닙니다"><span>계정 만들기</span></a></li>
              <li id="pt-login-2" class="user-links-collapsible-item mw-list-item"><a
                  href="https://ko.wikipedia.org/w/index.php?title=%ED%8A%B9%EC%88%98:%EB%A1%9C%EA%B7%B8%EC%9D%B8&amp;returnto=%EC%9C%84%ED%82%A4%EB%B0%B1%EA%B3%BC%ED%86%A0%EB%A1%A0%3A%EB%8C%80%EB%AC%B8"
                  title="위키백과에 로그인하면 여러가지 편리한 기능을 사용할 수 있습니다. [o]" accesskey="o"><span>로그인</span></a>
              </li>
            </ul>

          </div>
        </div>


        <div id="vector-user-links-dropdown"
             class="vector-dropdown vector-user-menu vector-button-flush-right vector-user-menu-logged-out"
             title="더 많은 옵션">
          <input type="checkbox"
                 id="vector-user-links-dropdown-checkbox"
                 role="button"
                 aria-haspopup="true"
                 data-event-name="ui.dropdown-vector-user-links-dropdown"
                 class="vector-dropdown-checkbox "

                 aria-label="개인 도구"

          />
          <label
              id="vector-user-links-dropdown-label"
              for="vector-user-links-dropdown-checkbox"
              class="vector-dropdown-label cdx-button cdx-button--fake-button cdx-button--fake-button--enabled cdx-button--weight-quiet cdx-button--icon-only "
              aria-hidden="true"

          >
            <span class="vector-icon mw-ui-icon-ellipsis mw-ui-icon-wikimedia-ellipsis"></span>


            <span class="vector-dropdown-label-text">개인 도구</span>
          </label>
          <div class="vector-dropdown-content">


            <div id="p-personal"
                 class="vector-menu mw-portlet mw-portlet-personal user-links-collapsible-item"
                 title="사용자 메뉴">
              <div class="vector-menu-content">

                <ul class="vector-menu-content-list">
                  <li id="pt-createaccount" class="user-links-collapsible-item mw-list-item"><a
                      href="https://ko.wikipedia.org/w/index.php?title=%ED%8A%B9%EC%88%98:%EA%B3%84%EC%A0%95%EB%A7%8C%EB%93%A4%EA%B8%B0&amp;returnto=%EC%9C%84%ED%82%A4%EB%B0%B1%EA%B3%BC%ED%86%A0%EB%A1%A0%3A%EB%8C%80%EB%AC%B8"
                      title="계정을 만들고 로그인하는 것이 좋습니다. 하지만 필수는 아닙니다"><span
                      class="vector-icon mw-ui-icon-userAdd mw-ui-icon-wikimedia-userAdd"></span>
                    <span>계정 만들기</span></a></li>
                  <li id="pt-login" class="user-links-collapsible-item mw-list-item"><a
                      href="https://ko.wikipedia.org/w/index.php?title=%ED%8A%B9%EC%88%98:%EB%A1%9C%EA%B7%B8%EC%9D%B8&amp;returnto=%EC%9C%84%ED%82%A4%EB%B0%B1%EA%B3%BC%ED%86%A0%EB%A1%A0%3A%EB%8C%80%EB%AC%B8"
                      title="위키백과에 로그인하면 여러가지 편리한 기능을 사용할 수 있습니다. [o]" accesskey="o"><span
                      class="vector-icon mw-ui-icon-logIn mw-ui-icon-wikimedia-logIn"></span>
                    <span>로그인</span></a></li>
                </ul>

              </div>
            </div>

            <div id="p-user-menu-anon-editor"
                 class="vector-menu mw-portlet mw-portlet-user-menu-anon-editor">
              <div class="vector-menu-heading">
                로그아웃한 편집자를 위한 문서 <a href="%eb%8f%84%ec%9b%80%eb%a7%90_%ec%86%8c%ea%b0%9c.html"
                                    aria-label="편집에 관해 더 알아보기"><span>더 알아보기</span></a>
              </div>
              <div class="vector-menu-content">

                <ul class="vector-menu-content-list">
                  <li id="pt-anoncontribs" class="mw-list-item"><a
                      href="%ed%8a%b9%ec%88%98_%eb%82%b4%ea%b8%b0%ec%97%ac.html"
                      title="이 IP 주소의 편집 목록 [y]" accesskey="y"><span>기여</span></a></li>
                  <li id="pt-anontalk" class="mw-list-item"><a
                      href="%ed%8a%b9%ec%88%98_%eb%82%b4%ec%82%ac%ec%9a%a9%ec%9e%90%ed%86%a0%eb%a1%a0.html"
                      title="현재 사용하는 IP 주소에 대한 토론 문서 [n]" accesskey="n"><span>토론</span></a></li>
                </ul>

              </div>
            </div>


          </div>
        </div>
      </nav>

    </div>
  </header>
</div>