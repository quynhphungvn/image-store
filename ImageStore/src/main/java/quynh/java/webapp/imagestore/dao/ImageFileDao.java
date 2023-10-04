package quynh.java.webapp.imagestore.dao;

import java.util.List;

import quynh.java.webapp.imagestore.model.ImageGroup;
import quynh.java.webapp.imagestore.model.ImageFile;

public interface ImageFileDao {
    
    public List<ImageFile> getAll(ImageGroup imageGroup);
    public ImageFile getById(int id);
    public void add(ImageFile imageInfo);
    public void delete(ImageFile imageInfo);
    public void update(ImageFile imageInfo);
}
