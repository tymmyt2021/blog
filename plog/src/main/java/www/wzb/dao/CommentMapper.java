package www.wzb.dao;

import www.wzb.entity.Comment;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface CommentMapper {
    List<Comment> select(Comment comment);

    void update(Comment comment);

    Comment findById(Long id);
    //根据文章id查询所有评论
    List<Comment> selectAll(@Param("cid")long cid);
    //根据文章id查询所有一级评论
    List<Comment> findAllFirstComment(@Param("cid")long cid);
    //根据文章id和二级评论ids查询出所有二级评论
    List<Comment> findAllChildrenComment(@Param("cid")long cid,@Param("children")String children);
    //插入评论并返回主键id 返回类型只是影响行数  id在Comment对象中
    int insertComment(Comment comment);

    void deleteById(Long id);

    void deleteByContentId(Long cid);

    void deleteChildrenComment(List<Object> list);

}
