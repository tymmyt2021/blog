package www.wzb.dao;


import tk.mybatis.mapper.common.Mapper;
import www.wzb.entity.UserContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserContentMapper  {

    List<UserContent> select(UserContent userContent);

    List<UserContent> findAllByUpvote();

    void updateById(UserContent userContent);

    void deleteById(Long cid);

    List<UserContent> findCategoryByUid(@Param("uid")long uid);


    int inserContent(UserContent userContent);


    List<UserContent> findByJoin(UserContent userContent);

    List<UserContent> findByUpvote(UserContent userContent);

    UserContent findById(Long id);
}