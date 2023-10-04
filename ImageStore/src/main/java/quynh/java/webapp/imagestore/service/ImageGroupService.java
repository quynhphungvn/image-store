package quynh.java.webapp.imagestore.service;

import java.util.List;

import quynh.java.webapp.imagestore.model.ImageGroup;

public interface ImageGroupService {

	List<ImageGroup> getAll();

	ImageGroup getById(int parseInt);

	ImageGroup addGroup(String groupName);

	ImageGroup updateGroupName(int groupId, String newName);

	ImageGroup deleteGroup(int groupId);

}
