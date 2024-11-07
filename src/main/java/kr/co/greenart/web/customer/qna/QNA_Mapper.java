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
import org.apache.ibatis.jdbc.SQL;

import kr.co.greenart.web.util.MyOrder;

// 241023 수업

@Mapper
public interface QNA_Mapper {
	// 글 작성
	// @Options 는 h2db의 last_insert_id
	@Insert({
		"insert into customerqna (title, content, username, password)"
		, "values (#{title}, #{content}, #{username}, #{password})"
	})
	@Options(useGeneratedKeys = true, keyProperty = "articleId")
	int save(QNA qna);
	
	// 게시글 목록 (글 번호 기준 내림차순(최신순)으로 정렬)
	@Select({
        "select article_id, title, content, username, views, comments, is_secure, created_at from customerqna",
        "where is_deleted = false",
        "order by article_id desc",
        "limit #{pageSize} offset #{offset}"
    })
	@Results(id = "qnaList",
	    value = {
	        @Result(column = "article_id", property = "articleId"),
	        @Result(column = "title", property = "title"),
	        @Result(column = "content", property = "content"),
	        @Result(column = "username", property = "username"),
	        @Result(column = "views", property = "views"),
	        @Result(column = "comments", property = "comments"),
	        @Result(column = "is_secure", property = "secure"),
	        @Result(column = "created_at", property = "createdAt")
	    }
	)
	// int pageSize(limit) : 몇 개의 데이터를 가지고 올 지
	// int offset : 시작할 위치 설정 (0부터 시작)
	// (이 경우 페이지당 5개 게시글 표시)
	List<QNA> findAll(int pageSize, int offset);
	
	// 총 게시글 수
	@Select("SELECT COUNT(*) FROM customerqna WHERE is_deleted = false")
	int getTotalCount();
	
	// 게시글 목록 (작성일자 기준 내림차순 정렬)
	@Select({
		"select article_id, title, content, username, views, comments, is_secure from customerqna"
		, "order by created_at desc"
		, "limit #{pageSize} offset #{offset}"
	})
	@Results(id = "qnaByCreatedAtList"
		, value = {
				@Result(column = "article_id", property = "articleId")
				, @Result(column = "title", property = "title")
				, @Result(column = "content", property = "content")
				, @Result(column = "username", property = "username")
				, @Result(column = "views", property = "views")
				, @Result(column = "comments", property = "comments")
				, @Result(column = "is_secure", property = "secure")
		}
	)
	List<QNA> sortByCreatedAt(int pageSize, int offset);
	
	// 게시글 목록 (조회수 기준 내림차순 정렬)
	@Select({
		"select article_id, title, content, username, views, comments, is_secure from customerqna"
		, "order by views desc"
		, "limit #{pageSize} offset #{offset}"
	})
	@Results(id = "qnaByViewsList"
		, value = {
				@Result(column = "article_id", property = "articleId")
				, @Result(column = "title", property = "title")
				, @Result(column = "content", property = "content")
				, @Result(column = "username", property = "username")
				, @Result(column = "views", property = "views")
				, @Result(column = "comments", property = "comments")
				, @Result(column = "is_secure", property = "secure")
		}
	)
	List<QNA> sortByViews(int pageSize, int offset);
	
	// 게시글 조회 시, is_secure 값이 false(bit값의 0)인 행만 조회
	@Select({
		"select article_id, title, content, username, views, is_secure from customerqna"
		, "where is_secure = false"
		, "order by article_id desc"
		, "limit #{pageSize} offset #{offset}"
	})
	@ResultMap("qnaList") // 위의 @Results를 그대로 사용할거니까 @ResultMap 이용해서 @Results의 id 작성
	List<QNA> findBySecureIsFalse(int pageSize, int offset);
	
	// 게시글 조회(article_id(pk)로 검색, title, content, username)
	// 해당 게시글 상세 보기에 활용
	@Select("SELECT * FROM customerqna WHERE article_id = #{articleId}")
	@Results(
			id = "qnaMapping"
			, value = {
					@Result(column = "article_id", property = "articleId", id = true)
					, @Result(column = "title", property = "title")
					, @Result(column = "content", property = "content")
					, @Result(column = "username", property = "username")
					, @Result(column = "password", property = "password")
					, @Result(column = "views", property = "views")
					, @Result(column = "comments", property = "comments")
					, @Result(column = "created_at", property = "createdAt")
					, @Result(column = "updated_at", property = "updatedAt")
					, @Result(column = "is_secure", property = "secure")
					, @Result(column = "is_deleted", property = "deleted")
			}
	)
	QNA findById(Integer articleId);
	
	// 제목으로 게시글 조회(검색)
	@Select({
        "select article_id, title, content, username, views, comments, is_secure, created_at from customerqna",
        "where is_deleted = false and lower(title) like lower(concat('%', #{title}, '%'))",
        "order by article_id desc",
        "limit #{pageSize} offset #{offset}"
    })
	@ResultMap("qnaList") // 위의 @Results를 그대로 사용할거니까 @ResultMap 이용해서 @Results의 id 작성
	List<QNA> findByTitle(String title, int pageSize, int offset);
	
	// 제목으로 검색한 게시글 수
	@Select({"SELECT COUNT(*) FROM customerqna WHERE is_deleted = false ",
			 "and lower(title) like lower(concat('%', #{title}, '%'))"
	})
	int getCountByTitle(String title);
	
	// 작성자로 게시글 조회(검색)
	@Select({
        "select article_id, title, content, username, views, comments, is_secure, created_at from customerqna",
        "where is_deleted = false and lower(username) like lower(concat('%', #{username}, '%'))",
        "order by article_id desc",
        "limit #{pageSize} offset #{offset}"
    })
	@ResultMap("qnaList") // 위의 @Results를 그대로 사용할거니까 @ResultMap 이용해서 @Results의 id 작성
	List<QNA> findByUsername(String username, int pageSize, int offset);
	
	// 작성자로 검색한 게시글 수
	@Select({"SELECT COUNT(*) FROM customerqna WHERE is_deleted = false ",
			 "and lower(username) like lower(concat('%', #{username}, '%'))"
	})
	int getCountByUsername(String username);
	
	// views count 수정(article_id)(1 증가)
	@Update("UPDATE customerqna SET views = views + 1 WHERE article_id = #{articleId}")
	int updateCount(Integer articleId);
	
	// 게시글 수정
	@Update("update customerqna set title = #{title}, username = #{username}, content = #{content}"
			+ "where article_id = #{articleId} and password = #{password}")
	int update(QNA qna);
	
	// 글 논리 삭제 (pk 및 password 일치) => is_deleted 수정
	@Update("update customerqna set is_deleted = true where article_id = #{articleId} and password = #{password}")
	int updateDelete(QNA qna);
	
	// SQLProvider : MyBatis를 통해 동적으로 SQL 쿼리를 생성
	// 정렬과 페이징 처리가 필요한 상황에 유용하게 쓰임
	// 수업 예제로 써놓은거니까 게시판 구현에 응용해보기
	class SQLProvider {
		public String selectOrderBy(MyOrder order) {
			return new SQL()
					.SELECT("columns")
					.FROM("tablename")
					.ORDER_BY(order.get정렬방식())
					.LIMIT("리밋개수")
					.OFFSET("오프셋")
					.toString();
		}
	}
}