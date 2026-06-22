package cn.nwnu.aics.impl;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import cn.nwnu.aics.dao.IPicture;
import cn.nwnu.aics.entity.Student;

public class PictureImpl implements IPicture {
	public void check(ServletConfig servletConfig, HttpServletRequest request,
			HttpServletResponse response, Student student) {
		try {
			Part filePart = request.getPart("pic");
			if (filePart != null && filePart.getSize() > 0
					&& filePart.getSubmittedFileName() != null
					&& !filePart.getSubmittedFileName().isEmpty()) {
				delete(servletConfig, request, response, student);
				upload(servletConfig, request, student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(ServletConfig servletConfig, HttpServletRequest request,
			HttpServletResponse response, Student student) {
		String picOld = student.getPic();
		if (picOld != null && !picOld.equals("")
				&& !picOld.equals("../images/person.png")) {
			String basePath = servletConfig.getServletContext().getRealPath("/");
			File oldFile = new File(basePath, picOld.replace("../", ""));
			if (oldFile.isFile()) {
				oldFile.delete();
			}
		}
	}

	public void upload(ServletConfig servletConfig, HttpServletRequest request,
			Student student) {
		try {
			Part filePart = request.getPart("pic");
			if (filePart == null || filePart.getSize() == 0) {
				return;
			}
			String fileName = filePart.getSubmittedFileName();
			String ext = "";
			int dot = fileName.lastIndexOf('.');
			if (dot >= 0) {
				ext = fileName.substring(dot + 1).toLowerCase();
			}
			String relativePath = "upload/" + student.getNo() + "." + ext;
			File uploadDir = new File(servletConfig.getServletContext().getRealPath("/upload"));
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			File target = new File(uploadDir, student.getNo() + "." + ext);
			try (InputStream in = filePart.getInputStream()) {
				Files.copy(in, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
			student.setPic("../" + relativePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
