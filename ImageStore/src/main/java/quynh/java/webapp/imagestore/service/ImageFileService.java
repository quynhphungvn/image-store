package quynh.java.webapp.imagestore.service;

import java.util.List;

import quynh.java.webapp.imagestore.model.ImageGroup;
import quynh.java.webapp.imagestore.model.ImageFile;

public interface ImageFileService {

	List<ImageFile> getAll(ImageGroup selectedGroup);

	ImageFile delete(int imageId);

	ImageFile addImageFile(String imageName, String formatType, byte[] imageOriginData, byte[] byteArray, int groupId);

	ImageFile getImageById(int imageId);

	ImageFile updateName(int imageId, String newName);

}
