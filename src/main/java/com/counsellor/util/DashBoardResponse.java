package com.counsellor.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashBoardResponse {
	
	private Integer totalEnqs;
	
	private Integer openEnqs;
	private Integer enrolledEnqs;
	private Integer lostEnqs;

}
