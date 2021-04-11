package www.wzb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.wzb.dao.UpvoteMapper;
import www.wzb.entity.Upvote;
import www.wzb.service.UpvoteService;
@Service
public class UpvoteServiceImpl implements UpvoteService{
    @Autowired
    private UpvoteMapper upvoteMapper;
    @Override
    public Upvote findByUidAndConId(Upvote upvote) {
        return upvoteMapper.findByUidAndConId(upvote);
    }

    @Override
    public int add(Upvote upvote) {
        return upvoteMapper.add(upvote);
    }

    @Override
    public Upvote getByUid(Upvote upvote) {
        return upvoteMapper.getByUid(upvote);
    }

    @Override
    public void update(Upvote upvote) {
        upvoteMapper.update(upvote);
    }

    @Override
    public void deleteByContentId(Long cid) {
        upvoteMapper.deleteByContentId(cid);
    }
}
