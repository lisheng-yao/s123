package com.shop.controller;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.shop.model.*;

@WebServlet("/DBPicReader")
public class DBPicReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpg");
		ServletOutputStream out = response.getOutputStream();
		
		Integer shopCarouselNo = Integer.valueOf(request.getParameter("shopCarouselNo"));
		ShopCarouselService ShopCarouselSvc = new ShopCarouselService();
		out.write(ShopCarouselSvc.getOneShopCarousel(shopCarouselNo).getShopCarouselPic());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
