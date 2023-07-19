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
    private List<Domain> columnList;
    private int pageCount;
    private long rowsCount;

    //아래는 setPageCount 메소드가 자동으로 채워줌
    private List<Integer> pageList = new ArrayList<>();
    private boolean nextButton;
    private int nextPage;
    private boolean prevButton;
    private int prevPage;

    @Builder
    public PaginationResponse(PaginationRequest request, int pageCount, long rowsCount, List<Domain> columnList) {
        this.request = request;
        setPageCount(pageCount);
        this.rowsCount = rowsCount;
        this.columnList = columnList;
    }

    public void setPageCount(int pageCount) {
        int start = Math.max(0, request.getPage() - request.getMaxPage());
        int end = Math.min(pageCount - 1, request.getPage() + request.getMaxPage());
        for (int i = start; i <= end; i++) {
            pageList.add(i);
        }
        prevPage = Math.max(0, start-1);
        prevButton = start-1 >= 0;
        nextPage = Math.min(pageCount-1, end+1);
        nextButton = end+1 <= pageCount-1;
    }

}
