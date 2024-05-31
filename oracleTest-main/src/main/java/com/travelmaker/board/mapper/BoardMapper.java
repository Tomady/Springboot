package com.travelmaker.board.mapper;

import com.travelmaker.board.domain.BoardVO;
import com.travelmaker.board.domain.Criteria;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

//    @Select("select * " +
//            "from(" +
//                "select /*+ index_desc(tbl_board pk_board)*/ rownum rn, bno, title, writer, content, regdate, updatedate " +
//                "from tbl_board " +
//                "where rownum <= #{pageNum} * #{amount} " +
//            ") " +
//            "where rn > (#{pageNum} - 1) * #{amount}")
    public List<BoardVO> getListWithPaging(Criteria cri);

    @Select("select * from tbl_board " +
            "where bno > 0")
    public List<BoardVO> getList();

    @Insert("insert into tbl_board(bno, title, writer, content) " +
            "values(seq_board.nextval, #{title}, #{writer}, #{content})")
    public void insert(BoardVO board);

    @SelectKey(statement = "select seq_board.nextval from dual", keyProperty = "bno", before = true, resultType = int.class)
    @Insert("insert into tbl_board(bno, title, writer, content) " +
            "values(#{bno}, #{title}, #{writer}, #{content})")
    public void insertSelectKey(BoardVO board);

    @Select("select * from tbl_board " +
            "where bno = #{bno}")
    public BoardVO read(int bno);

    @Delete("delete from tbl_board " +
            "where bno = #{bno}")
    public int delete(int bno);

    @Update("update tbl_board " +
            "set title = #{title}, " +
            "writer = #{writer}, " +
            "content = #{content}, " +
            "updatedate = sysdate " +
            "where bno = #{bno}")
    public int update(BoardVO board);

    public int getTotalCount(Criteria cri);
}
