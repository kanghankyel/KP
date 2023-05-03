package com.kidsplace.kidsplace.commons;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Pagination {

    private int totalRecordCount;       // 전체 데이터(게시글) 수
    private int totalPageCount;         // 페이지 하단에 출력할 전체 페이지 수
    private int startPage;              // 현재 페이지의 첫 페이지 번호
    private int endPage;                // 현재 페이지의 끝 페이지 번호
    private int limitStart;             // LIMIT 시작 위치(offset과 동일한 기능의 변수)
    private boolean existPrevPage;      // 이전 페이지 존재 여부
    private boolean existNextPage;      // 다음 페이지 존재 여부

    private int page;
    private int recordSize = 10;
    private int pageSize = 10;

    public Pagination(int totalRecordCount, int page){
        this.page = page;
        if(totalRecordCount > 0){
            this.totalRecordCount = totalRecordCount;
            calculation();        // 게시글 데이터가 있는 경우에만 실행되는 로직
        }
    }

    private void calculation(){

        // 전체 페이지 수 계산
        totalPageCount = ((totalRecordCount - 1) / recordSize) + 1;

        if (page > totalPageCount) {
            endPage = totalPageCount;
        } else if (page < 1) {
            page = 1;
        } else {
            // 첫 페이지 번호 계산
            // 1. 0을 제외한 startPage는 5배수 +1의 값이다.
            // 2. 5 초과의 경우 page보다 작은 수 중 가장 큰 5의 배수 중 +1한 값이다.
            startPage = ((page / pageSize) * pageSize) + 1;

            // 끝 페이지 번호 계산
            endPage = startPage + pageSize - 1;

            if (endPage > totalPageCount) {
                endPage = totalPageCount;
            }

        }
        // LIMIT 시작 위치 계산
        limitStart = (page - 1) * pageSize + 1;

        // 이전 페이지 존재 여부 확인
        existPrevPage = startPage != 1;

        // 다음 페이지 존재 여부 확인
        existNextPage = endPage < totalPageCount;

    }

}
