package quynh.java.webapp.imagestore.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import quynh.java.webapp.imagestore.dao.ImageFileDao;
import quynh.java.webapp.imagestore.dao.ImageGroupDao;
import quynh.java.webapp.imagestore.dao.impl.ImageFileDaoImpl;
import quynh.java.webapp.imagestore.dao.impl.ImageGroupDaoImpl;
import quynh.java.webapp.imagestore.model.ImageGroup;
import quynh.java.webapp.imagestore.model.ImageFile;
import quynh.java.webapp.imagestore.service.ImageFileService;

public class ImageFileServiceImpl implements ImageFileService {
	private ImageGroupDao imageGroupDao = new ImageGroupDaoImpl();
	private ImageFileDao imageFileDao = new ImageFileDaoImpl();
	@Override
	public List<ImageFile> getAll(ImageGroup selectedGroup) {
		return imageFileDao.getAll(selectedGroup);
	}
	@Override
	public ImageFile delete(int imageId) {
		ImageFile imageFile = imageFileDao.getById(imageId);
		if (imageFile != null)
			imageFileDao.delete(imageFile);
		return imageFile;
	}
	@Override
	public ImageFile addImageFile(String imageName, String formatType, byte[] imageOriginData, byte[] thumbnailData, int groupId) {
		ImageGroup imageGroup = imageGroupDao.getById(groupId);
		ImageFile imageFile = new ImageFile();
		imageFile.setUploadedName(imageName);
		imageFile.setFormatType(formatType);
		imageFile.setData(imageOriginData);
		imageFile.setThumbnail(thumbnailData);
		imageFile.setCreatedTime(LocalDateTime.now());
		imageFile.setImageGroup(imageGroup);
		imageFileDao.add(imageFile);
		return imageFile;
	}
	@Override
	public ImageFile getImageById(int imageId) {
		return imageFileDao.getById(imageId);
	}
	@Override
	public ImageFile updateName(int imageId, String newName) {
		ImageFile imageFile = imageFileDao.getById(imageId);
		if (imageFile != null) {
			imageFile.setUploadedName(newName);
			imageFileDao.update(imageFile);
		}
		return imageFile;
	}

}
