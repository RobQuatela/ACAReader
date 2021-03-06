package com.accountomation.acareader.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.accountomation.acareader.dao.ACAService;

/**
 * Servlet implementation class UploadController
 */
@MultipartConfig
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("upload.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Part> fileParts = request.getParts().stream().filter(parts -> "uploadFile".equals(parts.getName())).collect(Collectors.toList());
		int[] processed = null;
		
		for(Part part : fileParts) {
			InputStream file = part.getInputStream();
			processed = ACAService.createACA(file);
		}
		
		request.setAttribute("processed", processed);
		doGet(request, response);
	}

}
