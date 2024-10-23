package kr.co.greenart.web.customer.qna;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface QnAMapper {
	// 글 작성
	@Insert("insert into customerqna (title, content, username, password) "
			+ "values (#{title}, #{content}, #{username}, #{password})")
	int write(Customerqna customerqna);
	
	// 게시글 목록
	@Select("select article_id, title, username, is_secure from customerqna")
	Customerqna findAll();
	
	// article_id(pk)로 게시글 조회
	@Select("select article_id, title, content, username from customerqna "
			+ "where article_id = #{article_id} and is_secure = 0")
	Customerqna findByPk(int article_id);
	
	// 게시글의 비밀 여부 조회
	@Select("select is_secure from customerqna where article_id = #{article_id}")
	int isSecureByPk(int article_id);
	
	// views 조회
	@Select("select views from customerqna where article_id = #{article_id}")
	int selectViews(int article_id);
	
	// views count 수정(1 증가)
	@Update("update customerqna set views = views + 1 where article_id = #{article_id}")
	int updateViews(int views, int article_id);
	
	// 글 논리 삭제 (pk 및 password 일치) => is_deleted 수정
	@Update("update customerqna set is_deleted = #{is_deleted} "
			+ "where article_id = #{article_id} and password = #{password}")
	int updateIsDeleted(int is_deleted, int article_id, String password);
}