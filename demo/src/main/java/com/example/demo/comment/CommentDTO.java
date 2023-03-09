package com.example.demo.comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CommentDTO {
	private int commentNum;				//댓글넘버
	private int boardNum;				//게시글넘버
	private String boardTitle;			//게시물 제목
	private String commentTxt; 			//댓글 내용
	private String userId;				//댓글 작성자
	private String delYn;				//삭제 여부
	private LocalDateTime commentReg;	//등록일
	private LocalDateTime commentUdt;	//수정일
	private String commentRegFormatted;
	private String commentUdtFormatted;

	public void setBoardDate(LocalDateTime commentReg) {
        this.commentReg = commentReg;
        this.commentRegFormatted = getComentDateFormatted(commentReg); // format date
    }

    public void setBoardUdt(LocalDateTime commentUdt) {
        this.commentUdt = commentUdt;
        this.commentUdtFormatted = getComentDateFormatted(commentUdt); // format date
    }

    private String getComentDateFormatted(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm:ss");
        return date.format(formatter);
    }
	
}
