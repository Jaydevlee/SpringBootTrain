package com.example.demoMyBastis.mapper;

import com.example.demoMyBastis.model.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;
@Mapper
public interface ArticleMapper {
  @Results(id = "articleResult", value={
          @Result(property = "title", column = "title"),
          @Result(property = "description", column = "description"),
          @Result(property = "memberId", column = "member_id")
  })
  //모든 게시물 불러오기
  @Select("SELECT * FROM article")
  List<Article> selectAll();

  //전체 게시물 개수
  @Select("SELECT COUNT(*) FROM article")
  int selectAllCount();

  //게시판 번호로 게시물 불러오기
  @Select("Select * FROM article WHERE id=#{id}")
  Optional<Article> findById(@Param("id") Long id);

  //memberId로 게시물 불러오기
  @Select("Select * FROM article WHERE member_Id=#{memberId}")
  List<Article> findByMemberId(@Param("memberId") String memberId);

  //게시글 객체를DB에 입력
  @Insert("""
            INSERT INTO ARTICLE(ID, TITLE, DESCRIPTION, CREATED, UPDATED, MEMBER_ID) VALUES(article_seq.NEXTVAL, #{article.title}, #{article.description},
          CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, #{article.memberId})
          """)
  //@Options를 사용해 키로 사용된 칼럼에 자동으로 생성된 값을 다시 키 프로퍼티로 설정해 되돌려받을 수 있다.
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  int insert(@Param("article") Article article);

  //게시물 수정
  @Update("""
          UPDATE article SET title=#{title}, description=#{description}, updated=CURRENT_TIMESTAMP WHERE id=#{id}""")
  int update(@Param("id") Long id, @Param("title") String title,  @Param("description") String description);

  //게시물 삭제
  @Delete("DELETE FROM ARTICLE WHERE ID = #{id}")
  int deleteById(@Param("id") Long id);
  @Delete("DELETE FROM ARTICLE WHERE MEMBER_ID = #{memberId}")
  int deleteByMemberId(@Param("memberId") String memberId);
  @Delete("DELETE FROM ARTICLE")
  int deleteAll();
}
