package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentVO {

	int cmSeq;
	int cSeq;
	String cmContent;
	String cmDate;
	String mId;

}//
