package com.kidsplace.kidsplace.commons;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDTO {
    
    private int page;               // 현재 페이지 번호 - 페이지 정보 계산에 사용
    private int recordSize;         // 페이지당 출력할 데이터 개수 - page와 함께 페이지 정보 계산에 사용
    private int pageSize;           // 화면 하단에 출력할 페이지의 크기 - 5로 지정하면 1~5까지, 10으로 지정하면 1~10까지의 페이지가 보이게 됨
    private String keyword;         // 검색 키워드 - MyBatis의 동적(Dynamic) SQL 처리에 사용
    private String searchType;      // 검색 유형 - keyword와 함께 검색처리에 사용
    // private Pagination pagination;  // 페이지네이션 정보

    public SearchDTO(){
        this.page = 1;
        this.recordSize = 10;
        this.pageSize = 10;
    }

    public int getOffset(){
        return (page - 1) * recordSize;
    }

}
