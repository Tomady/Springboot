package com.travelmaker.board.mapper;

import com.travelmaker.board.domain.BoardVO;
import com.travelmaker.board.domain.Criteria;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("select * " +
            "from(" +
                "select /*+ index_desc(bbs bbs_pk)*/ rownum rn, bbs_id, management_id, title, code_id, nickname, bcontent, status, hit, good, bad, plan_id, cdate, udate " +
                "from bbs " +
                "where rownum <= #{pageNum} * #{amount} " +
            ") " +
            "where rn > (#{pageNum} - 1) * #{amount}")
    public List<BoardVO> getListWithPaging(Criteria cri);

    @Select("select * from bbs " +
            "where bbs_id > 0")
    public List<BoardVO> getList();

    @Insert("insert into bbs(bbs_id, management_id, title, code_id, nickname, bcontent, status) " +
            "values(bbs_bbs_id_seq.nextval, #{management_id}, #{title}, #{code_id}, #{nickname}, #{bcontent}, #{status})")
    public void insert(BoardVO board);

    @SelectKey(statement = "select bbs_bbs_id_seq.nextval from dual", keyProperty = "bbs_id", before = true, resultType = int.class)
    @Insert("insert into bbs(bbs_id, management_id, title, code_id, nickname, bcontent, status) " +
            "values(#{bbs_id}, #{management_id}, #{title}, #{code_id}, #{nickname}, #{bcontent}, #{status})")
    public void insertSelectKey(BoardVO board);

    @Select("select * from bbs " +
            "where bbs_id = #{bbs_id}")
    public BoardVO read(int bbs_id);

    @Delete("delete from bbs " +
            "where bbs_id = #{bbs_id}")
    public int delete(int bbs_id);

    @Update("update bbs " +
            "set management_id = #{management_id}, " +
            "title = #{title}, " +
            "code_id = #{code_id}, " +
            "nickname = #{nickname}, " +
            "bcontent = #{bcontent}, " +
            "status = #{status}, " +
            "plan_id = #{plan_id, jdbcType=INTEGER}, " +
            "udate = CURRENT_DATE " +
            "where bbs_id = #{bbs_id}")
    public int update(BoardVO board);
}
