package com.travelmaker.reply.mapper;

import com.travelmaker.reply.domain.ReplyVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReplyMapper {
    @Insert("insert into rbbs(rbbs_id, bbs_id, management_id, bcontent, nickname) " +
            "values(rbbs_rbbs_id_seq.nextval, #{bbs_id}, #{management_id}, #{bcontent}, #{nickname})")
    public int insert(ReplyVO vo);

    @Select("select * from rbbs " +
            "where rbbs_id = #{rbbs_id}")
    public ReplyVO read(Long rbbs_id);
}
