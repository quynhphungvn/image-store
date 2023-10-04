package quynh.java.webapp.imagestore.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import quynh.java.webapp.imagestore.model.ImageGroup;
import quynh.java.webapp.imagestore.service.ImageFileService;
import quynh.java.webapp.imagestore.service.ImageGroupService;
import quynh.java.webapp.imagestore.service.impl.ImageFileServiceImpl;
import quynh.java.webapp.imagestore.service.impl.ImageGroupServiceImpl;
import quynh.java.webapp.imagestore.support.util.ImageUtil;
import quynh.java.webapp.imagestore.support.util.RequestUtil;

/**
 * Servlet implementation class AdminController
 */
@MultipartConfig
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ImageGroupService imageGroupService = new ImageGroupServiceImpl();  
    private ImageFileService imageFileService = new ImageFileServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathUri = request.getRequestURI();
		if (pathUri.contains("/group/add"))
			processAddGroup(request,response);
		else if (pathUri.contains("/group/update"))
			processUpdateGroup(request, response);
		else if (pathUri.contains("/group/delete"))
			processDeleteGroup(request, response);
		else if (pathUri.contains("/image/add"))
			processAddImages(request, response);
		else if (pathUri.contains("/image/update"))
			processUpdateImage(request, response);
		else if (pathUri.contains("/image/delete"))
			processDeleteImage(request, response);
	}

	private void processDeleteImage(HttpServletRequest request, HttpServletResponse response) {
		int imageId = RequestUtil.getParameterInt(request, "image-id");
		if (imageId != 0) {
			imageFileService.delete(imageId);
		}
	}

	private void processUpdateImage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int imageId = RequestUtil.getParameterInt(request, "image-id");
		String sSelectedImageGroup = request.getParameter("selected-group-id");
		String newName = request.getParameter("new-name");
		if (imageId != 0 && newName != null && newName.isBlank() == false)
			imageFileService.updateName(imageId, newName);
		try {
			response.sendRedirect("/ImageStore/home?group-id=" + sSelectedImageGroup);
		} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processAddImages(HttpServletRequest request, HttpServletResponse response) {
		String sSelectedGroupId = null;
		String sGroupId = null;
		try {
			List<Part> parts = (List<Part>) request.getParts();
			sSelectedGroupId = RequestUtil.convertPartToString(parts.get(0));
			for (int i = 1; i < parts.size(); i++) {
				Part imagePart = parts.get(i);
				String imageName = imagePart.getSubmittedFileName();
				String formatType = imageName.split("\\.")[1].toLowerCase();
				byte[] imageOriginData = imagePart.getInputStream().readAllBytes();
				ByteArrayOutputStream imageThumbBytes = null;
				try {
					imageThumbBytes = ImageUtil.resizeImage(imagePart.getInputStream(), 165, 165, formatType);					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				imageFileService.addImageFile(imageName, formatType, imageOriginData, imageThumbBytes.toByteArray(), Integer.parseInt(sSelectedGroupId));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		try {
			response.sendRedirect("/ImageStore/home?group-id=" + sSelectedGroupId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	private void processDeleteGroup(HttpServletRequest request, HttpServletResponse response) {
		int groupId = RequestUtil.getParameterInt(request, "group-id");
		if (groupId != 0) 
			imageGroupService.deleteGroup(groupId);
		int selectedGroupId = RequestUtil.getParameterInt(request, "selected-group-id");
		try {
			if (selectedGroupId != groupId && selectedGroupId != 0)
				response.sendRedirect("/ImageStore/home?group-id=" + selectedGroupId);
			else 
				response.sendRedirect("/ImageStore/home");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processUpdateGroup(HttpServletRequest request, HttpServletResponse response) {
		String newName = request.getParameter("new-name");
		int groupId = RequestUtil.getParameterInt(request, "group-id");
		if (groupId != 0 && newName != null && newName.isBlank() == false) {
			ImageGroup imageGroup = imageGroupService.updateGroupName(groupId, newName);
		}
		String sSelectedGroupId = request.getParameter("selected-group-id");
		try {
			response.sendRedirect("/ImageStore/home?group-id=" + sSelectedGroupId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processAddGroup(HttpServletRequest request, HttpServletResponse response) {
		String groupName = request.getParameter("name");
		imageGroupService.addGroup(groupName);
		int selectedGroupId = RequestUtil.getParameterInt(request, "selected-group-id");
		try {
			response.sendRedirect("/ImageStore/home?group-id=" + selectedGroupId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
