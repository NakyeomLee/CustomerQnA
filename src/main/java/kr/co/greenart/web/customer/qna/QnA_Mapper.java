package kr.co.greenart.web.customer.qna;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

// 241023 수업

@Mapper
public interface QnA_Mapper {
	// 글 작성
	@Insert({
		"insert into customerqna (title, content, username, password)"
		, "values (#{title}, #{content}, #{username}, #{password})"
	})
	@Options(useGeneratedKeys = true, keyProperty = "articleId")
	int save(QNA qna);
	
	// 게시글 목록 (글 번호 기준 내림차순으로 정렬)
	@Select({
		"select article_id, title, content, username, views, is_secure from customerqna"
		, "order by article_id desc"
		, "limit #{pageSize} offset #{offset}"
	})
	@Results(id = "qnaList"
		, value = {
				@Result(column = "article_id", property = "articleId")
				, @Result(column = "title", property = "title")
				, @Result(column = "content", property = "content")
				, @Result(column = "username", property = "username")
				, @Result(column = "views", property = "views")
				, @Result(column = "is_secure", property = "secure")
		}
	)
	List<QNA> findAll(int pageSize, int offset);
	
	// 게시글 조회 시, is_secure 값이 false인 행만 조회
	@Select({
		"select article_id, title, content, username, views, is_secure from customerqna"
		, "where is_secure = 0"
		, "order by article_id desc"
		, "limit #{pageSize} offset #{offset}"
	})
	@ResultMap("qnaList") // 위의 @Results를 그대로 사용할거니까 @ResultMap 이용해서 @Results의 id 작성
	List<QNA> findBySecureIsFalse(int pageSize, int offset);
	// TODO SQL 명령문 구현
	
	// 게시글 조회(article_id(pk)로 검색, title, content, username)
	@Select("select article_id, title, content, username from customerqna "
			+ "where article_id = #{articleId}")
//	Customerqna findByPk(int article_id);
	void findByPk();
	
	// 게시글(article_id)의 비밀 여부 조회
	@Select("select is_secure from customerqna where article_id = #{articleId}")
//	int isSecureByPk(int article_id);
	void findSecureByPk();

	// views count 수정(article_id)(1 증가)
	@Update("update customerqna set views = views + 1 where article_id = #{articleId}")
//	int updateViews(int views, int article_id);
	void updateViewsCount();
	
	// 글 논리 삭제 (pk 및 password 일치) => is_deleted 수정
	@Update("update customerqna set is_deleted = #{deleted} "
			+ "where article_id = #{articleId} and password = #{password}")
//	int updateIsDeleted(int is_deleted, int article_id, String password);
	void updateDelete();
}