package com.travelmaker.reply.mapper;

import com.travelmaker.board.domain.Criteria;
import com.travelmaker.reply.domain.ReplyVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReplyMapper {
    @Insert("insert into tbl_reply(rno, bno, reply, replyer) " +
            "values(seq_reply.nextval, #{bno}, #{reply}, #{replyer})")
    public int insert(ReplyVO vo);

    @Select("select * from tbl_reply " +
            "where rno = #{rno}")
    public ReplyVO read(Long rno);

    @Delete("delete from tbl_reply " +
            "where rno = #{rno}")
    public int delete(Long rno);

    @Update("update tbl_reply " +
            "set reply = #{reply}, " +
            "updatedate = sysdate " +
            "where rno = #{rno}")
    public int update(ReplyVO reply);

    @Select("select rno, bno, reply, replyer, replyDate, updateDate " +
            "from tbl_reply " +
            "where bno = #{bno} " +
            "order by rno asc")
    public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);
}
