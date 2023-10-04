package quynh.java.webapp.imagestore.service.impl;

import java.util.List;

import quynh.java.webapp.imagestore.dao.ImageGroupDao;
import quynh.java.webapp.imagestore.dao.impl.ImageGroupDaoImpl;
import quynh.java.webapp.imagestore.model.ImageGroup;
import quynh.java.webapp.imagestore.service.ImageGroupService;

public class ImageGroupServiceImpl implements ImageGroupService {
	private ImageGroupDao imageGroupDao = new ImageGroupDaoImpl();
	@Override
	public List<ImageGroup> getAll() {
		return imageGroupDao.getAll();
	}
	@Override
	public ImageGroup getById(int id) {
		return imageGroupDao.getById(id);
	}
	@Override
	public ImageGroup addGroup(String groupName) {
		ImageGroup imageGroup = new ImageGroup();
		imageGroup.setName(groupName);
		imageGroupDao.add(imageGroup);
		return imageGroup;
	}
	@Override
	public ImageGroup updateGroupName(int groupId, String newName) {
		ImageGroup imageGroup = imageGroupDao.getById(groupId);
		if (imageGroup != null && newName.isBlank() == false) {
			imageGroup.setName(newName);
			imageGroupDao.update(imageGroup);
		}
		return imageGroup;
	}
	@Override
	public ImageGroup deleteGroup(int groupId) {
		ImageGroup imageGroup = imageGroupDao.getById(groupId);
		if (imageGroup != null)
			imageGroupDao.delete(imageGroup);
		return imageGroup;
	}
	
}
