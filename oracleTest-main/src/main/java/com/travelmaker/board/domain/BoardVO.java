package com.travelmaker.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
public class BoardVO {
    private int bbs_id;
    private int management_id;
    private String title;
    private String code_id;
    private String nickname;
    private String bcontent;
    private char status;
    private int hit;
    private int good;
    private int bad;
    private Integer plan_id;
    private Timestamp cdate;
    private Timestamp udate;
}
