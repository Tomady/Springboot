package com.travelmaker.reply.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class ReplyVO {
    private Long rbbs_id;
    private Long bbs_id;
    private Long management_id;
    private String nickname;
    private String bcontent;
    private Timestamp cdate;
    private Timestamp udate;
}
