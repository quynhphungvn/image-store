package quynh.java.webapp.imagestore.dao;

import java.util.List;

import quynh.java.webapp.imagestore.model.ImageGroup;

public interface ImageGroupDao {
    
    public List<ImageGroup> getAll();
    public ImageGroup getById(int id);
    public void add(ImageGroup imageGroup);
    public void delete(ImageGroup imageGroup);
    public void update(ImageGroup imageGroup);
}
