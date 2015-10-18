package org.k.dao;

import org.k.domain.Comment;

import java.util.List;

public interface CommentDAO {
    public int addComment(Comment comment);
    public void updateComment(Comment comment);
    public void deleteComment(Comment comment);
    public List<Comment> getAllComments();
    public Comment getCommentById(int id);
}
