package com.kidsplace.kidsplace.commons;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {                          // PageDTO는 생성자를 정의하고 Criteria와 전체 데이터 수(total)를 파라미터로 지정한다.
                                                
    private int startPage;
    private int endPage;
    private boolean prev, next;

    private int total;
    private Criteria cri;

    public PageDTO(Criteria cri, int total){    // Criteria 안에는 페이지에서 보여주는 데이터 수(amount)와 현재 페이지 번호(pageNum)를 가지고 있어 필요한 모든 내용을 계산할 수 있다.
        this.cri = cri;                 // cri, total 초기화
        this.total = total;

        this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;         // 페이지 끝 번호
        this.startPage = this.endPage - 9;                                      // 페이지 시작 번호
        int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));       // 전체 마지막 페이지 번호
        
        if(realEnd < this.endPage){                 // 페이지 끝 번호 유표성 검사
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;             // 다음 버튼 값 초기화
        this.next = this.endPage < realEnd;         // 이전 버튼 값 초기화
    }

}
