package www.wzb.dao;

import www.wzb.entity.Upvote;

public interface UpvoteMapper {
    Upvote findByUidAndConId(Upvote upvote);

    Upvote getByUid(Upvote upvote);

    void update(Upvote upvote);

    void deleteByContentId(Long contentId);

    int add(Upvote upvote);
}
