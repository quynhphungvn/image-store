package quynh.java.webapp.imagestore.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quynh.java.webapp.imagestore.model.ImageGroup;
import quynh.java.webapp.imagestore.model.ImageFile;
import quynh.java.webapp.imagestore.service.ImageGroupService;
import quynh.java.webapp.imagestore.service.ImageFileService;
import quynh.java.webapp.imagestore.service.impl.ImageGroupServiceImpl;
import quynh.java.webapp.imagestore.service.impl.ImageFileServiceImpl;
import quynh.java.webapp.imagestore.support.util.RequestUtil;

/**
 * Servlet implementation class HomeController
 */
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ImageGroupService imageGroupService = new ImageGroupServiceImpl(); 
    private ImageFileService imageInfoService = new ImageFileServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int groupId = RequestUtil.getParameterInt(request, "group-id");
		List<ImageGroup> imageGroups = null;
		ImageGroup selectedGroup = null;
		List<ImageFile> imageInfos = null;
		imageGroups = imageGroupService.getAll();
		if (groupId != 0) {
			selectedGroup = imageGroupService.getById(groupId);
			imageInfos = imageInfoService.getAll(selectedGroup);
		}
		request.setAttribute("imageGroups", imageGroups);
		request.setAttribute("selectedGroup", selectedGroup);
		request.setAttribute("imageFiles", imageInfos);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/home/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
