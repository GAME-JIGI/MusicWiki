package com.gamejigi.wiki.util;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaginationResponse<Domain> {

    //서비스가 컨트롤러에 넘기기 전에 값을 채워야 하는 부분
    private PaginationRequest request;
    private int pageCount;
    private List<Domain> columnList;

    //아래는 setPageCount 메소드가 자동으로 채워줌
    private List<Integer> pageList = new ArrayList<>();
    private int nextPage;
    private int prevPage;

    @Builder
    public PaginationResponse(PaginationRequest request, int pageCount, List<Domain> columnList) {
        this.request = request;
        setPageCount(pageCount);
        this.columnList = columnList;
    }

    public void setPageCount(int pageCount) {
        int start = Math.max(0, request.getPage() - request.getMaxPage());
        int end = Math.min(pageCount, request.getPage() + request.getMaxPage());
        for (int i = start; i <= end; i++) {
            pageList.add(i);
        }
        prevPage = Math.max(0, start-1);
        nextPage = Math.min(pageCount, end+1);
    }

}
