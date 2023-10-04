package quynh.java.webapp.imagestore.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quynh.java.webapp.imagestore.model.ImageFile;
import quynh.java.webapp.imagestore.service.ImageFileService;
import quynh.java.webapp.imagestore.service.impl.ImageFileServiceImpl;
import quynh.java.webapp.imagestore.support.util.RequestUtil;

/**
 * Servlet implementation class ImageController
 */
public class ImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ImageFileService imageFileService = new ImageFileServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uriPath = request.getRequestURI();
		if (uriPath.contains("/image/thumbnail"))
		{
			int imageId = RequestUtil.getParameterInt(request, "id");
			if (imageId != 0) {
				ImageFile imageFile = imageFileService.getImageById(imageId);
				response.setContentType("image/" + imageFile.getFormatType());
				response.getOutputStream().write(imageFile.getThumbnail());
			}
		} else if (uriPath.contains("/image/origin"))
		{
			int imageId = RequestUtil.getParameterInt(request, "id");
			if (imageId != 0) {
				ImageFile imageFile = imageFileService.getImageById(imageId);
				response.setContentType("image/" + imageFile.getFormatType());
				response.getOutputStream().write(imageFile.getData());
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
