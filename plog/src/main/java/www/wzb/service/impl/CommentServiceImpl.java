package www.wzb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.wzb.dao.CommentMapper;
import www.wzb.entity.Comment;
import www.wzb.service.CommentService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public int add(Comment comment) {
        return commentMapper.insertComment(comment);
    }

    @Override
    public void update(Comment comment) {
        commentMapper.update(comment);
    }

    @Override
    public List<Comment> findAll(Long content_id) {
        return commentMapper.selectAll(content_id);
    }

    @Override
    public Comment findById(Long id) {
        return commentMapper.findById(id);
    }

    @Override
    public List<Comment> findAllFirstComment(Long content_id) {
        return commentMapper.findAllFirstComment(content_id);
    }

    @Override
    public List<Comment> findAllChildrenComment(Long content_id, String children) {
        return commentMapper.findAllChildrenComment(content_id,children);
    }

    @Override
    public void deleteById(Long id) {
        commentMapper.deleteById(id);
    }

    @Override
    public void deleteChildrenComment(String children) {
        List<Object> list = new ArrayList<Object>(  );
        String[] split = children.split( "," );
        for(int i = 0;i<split.length;i++){
            list.add( split[i] );
        }
        commentMapper.deleteChildrenComment(list);
    }

    @Override
    public void deleteByContentId(Long cid) {
        commentMapper.deleteByContentId(cid);
    }
}
