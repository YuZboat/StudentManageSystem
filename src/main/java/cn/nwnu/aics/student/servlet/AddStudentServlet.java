package cn.nwnu.aics.student.servlet;

import cn.nwnu.aics.impl.Cla2SubImpl;
import cn.nwnu.aics.impl.ClassesImpl;
import cn.nwnu.aics.impl.OperatorImpl;
import cn.nwnu.aics.impl.PictureImpl;
import cn.nwnu.aics.impl.RoleImpl;
import cn.nwnu.aics.impl.ScoreImpl;
import cn.nwnu.aics.impl.StudentImpl;
import cn.nwnu.aics.impl.SubjectImpl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cn.nwnu.aics.entity.Cla2Sub;
import cn.nwnu.aics.entity.Classes;
import cn.nwnu.aics.entity.Operator;
import cn.nwnu.aics.entity.Role;
import cn.nwnu.aics.entity.Score;
import cn.nwnu.aics.entity.Student;
import cn.nwnu.aics.entity.Subject;

@MultipartConfig
public class AddStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		OperatorImpl operatorImpl = new OperatorImpl();
		PictureImpl pictureImpl = new PictureImpl();
		StudentImpl studentImpl = new StudentImpl();
		ClassesImpl classesImpl = new ClassesImpl();
		SubjectImpl subjectImpl = new SubjectImpl();
		ScoreImpl scoreImpl = new ScoreImpl();
		RoleImpl roleImpl = new RoleImpl();
		try {
			// 验证必填字段
			String opeName = request.getParameter("ope_name");
			String opePwd = request.getParameter("ope_pwd");
			String stuNo = request.getParameter("no");
			String stuName = request.getParameter("name");
			String birthStr = request.getParameter("birth");
			String claId = request.getParameter("cla_id");
			
			if (opeName == null || opeName.trim().isEmpty() ||
				opePwd == null || opePwd.trim().isEmpty() ||
				stuNo == null || stuNo.trim().isEmpty() ||
				stuName == null || stuName.trim().isEmpty() ||
				birthStr == null || birthStr.trim().isEmpty() ||
				claId == null || claId.trim().isEmpty()) {
				request.getSession().setAttribute("message", "请填写所有必填字段！");
				response.sendRedirect("pages/add_student.jsp");
				return;
			}
			
			// 检查账号是否已存在
			List<Operator> existingOperators = operatorImpl.query("ope_name", opeName);
			if (!existingOperators.isEmpty()) {
				request.getSession().setAttribute("message", "账号已存在，请使用其他账号名！");
				response.sendRedirect("pages/add_student.jsp");
				return;
			}
			
			// 检查学号是否已存在
			List<Student> existingStudents = studentImpl.query("stu_no", stuNo);
			if (!existingStudents.isEmpty()) {
				request.getSession().setAttribute("message", "学号已存在，请使用其他学号！");
				response.sendRedirect("pages/add_student.jsp");
				return;
			}
			
			// 检查班级是否存在
			List<Classes> classesList = classesImpl.query("cla_id", claId);
			if (classesList.isEmpty()) {
				request.getSession().setAttribute("message", "选择的班级不存在，请先添加班级！");
				response.sendRedirect("pages/add_student.jsp");
				return;
			}
			
			// 检查角色是否存在
			List<Role> roles = roleImpl.query("rol_id", "3");
			if (roles.isEmpty()) {
				request.getSession().setAttribute("message", "学生角色不存在，请检查数据库role表！");
				response.sendRedirect("pages/add_student.jsp");
				return;
			}
			
			Student student = new Student();
			Operator operator = new Operator();

			operator.setName(opeName);
			operator.setPwd(opePwd);
			operator.setRole(roles.get(0));
			operator = operatorImpl.add(operator);
			
			if (operator == null || operator.getId() == 0) {
				request.getSession().setAttribute("message", "创建账号失败！");
				response.sendRedirect("pages/add_student.jsp");
				return;
			}

			student.setOperator(operator);
			student.setNo(stuNo);
			student.setName(stuName);
			student.setSex(request.getParameter("sex").equals("male") ? "男" : "女");
			student.setBirth(sdf.parse(birthStr));
			student.setClasses(classesList.get(0));
			pictureImpl.check(getServletConfig(), request, response, student);
			int i = studentImpl.add(student);
			
			if (i == 1) {
				student = studentImpl.query("ope_id", operator.getId() + "").get(0);
				List<Subject> list_subject = subjectImpl.query("stu_id", student.getId() + "");
				List<Cla2Sub> list_cla2sub = new Cla2SubImpl().query("stu_id", student.getId() + "");
				for (int x = 0; x < list_subject.size(); x++) {
					Score score = new Score();
					score.setStudent(student);
					score.setSubject(list_subject.get(x));
					score.setCla2sub(list_cla2sub.get(x));
					scoreImpl.add(score);
				}
				request.getSession().setAttribute("message", "添加学生成功！");
			} else {
				request.getSession().setAttribute("message", "添加学生失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("message", "添加学生失败：" + e.getMessage());
		}
		response.sendRedirect("pages/add_student.jsp");
	}
}
