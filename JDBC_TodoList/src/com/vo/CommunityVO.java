package com.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommunityVO {

	int cSeq;
	String cTitle;
	String cContent;
	int cCnt;
	String cDate;
	String mId;

}//
