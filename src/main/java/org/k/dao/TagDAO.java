package org.k.dao;

import org.k.domain.Tag;

import java.util.List;

public interface TagDAO {
    public int addTag(Tag tag);
    public void updateTag(Tag tag);
    public void deleteTag(Tag tag);
    public List<Tag> getAllTags();
    public Tag getTagById(int id);
}
