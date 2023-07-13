package com.gamejigi.wiki.util;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class PaginationRequest {

    //컨트롤러가 서비스에 넘기기 전에 값을 채워야 하는 부분
    @Builder.Default
    private int maxPage = 5; //앞뒤로 최대 5페이지 까지 버튼이 보인다는 뜻 (1 2 3 4 5 현재 7 8 9 10 11)
    @Builder.Default
    private int maxSize = 10; //한 페이지에 최대 10줄이 나온다는 뜻
    @Builder.Default
    private String search = "";
    @Builder.Default
    private String searchType = ""; //d: 날짜순, a: 사전순
    @Builder.Default
    private int sort = 0; //0: 정렬안함, 1: 오름차순, 2: 내림차순
    @Builder.Default
    private String sortColumn = ""; //ex) createdDate: 생성된 순서로 정렬
    private int page; //페이지는 0번부터 시작하는 걸로. 프론트에서 알아서 바꿔서 보여주기

    public Sort getSort() {
        if (sort == 0) {
            return null;
        }
        var sortingMethod = Sort.by(sortColumn);
        return sort == 1 ? sortingMethod.ascending() : sortingMethod.descending();
    }

    public PageRequest getPageRequest() {
        if (sort != 0)
            return PageRequest.of(page, maxSize, getSort());
        return PageRequest.of(page, maxSize);
    }

    public Pageable getPageable() {
        return (Pageable) getPageRequest();
    }

}
