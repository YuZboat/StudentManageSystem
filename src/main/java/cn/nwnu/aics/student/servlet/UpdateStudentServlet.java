package cn.nwnu.aics.student.servlet;

import cn.nwnu.aics.impl.ClassesImpl;
import cn.nwnu.aics.impl.OperatorImpl;
import cn.nwnu.aics.impl.PictureImpl;
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
import jakarta.servlet.http.HttpSession;

import cn.nwnu.aics.entity.Classes;
import cn.nwnu.aics.entity.Operator;
import cn.nwnu.aics.entity.Score;
import cn.nwnu.aics.entity.Student;
import cn.nwnu.aics.entity.Subject;

@MultipartConfig
public class UpdateStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		OperatorImpl operatorImpl = new OperatorImpl();
		PictureImpl pictureImpl = new PictureImpl();
		StudentImpl studentImpl = new StudentImpl();
		ClassesImpl classesImpl = new ClassesImpl();
		SubjectImpl subjectImpl = new SubjectImpl();
		ScoreImpl scoreImpl = new ScoreImpl();
		List<Score> list_score;
		List<Subject> list_subject;
		HttpSession session = request.getSession();
		try {
			Student student_my = studentImpl.query("stu_id",
					request.getParameter("stu_id") + "").get(0);
			Operator operator_my = operatorImpl.query("ope_id",
					student_my.getOperator().getId() + "").get(0);

			student_my.setNo(request.getParameter("no"));
			student_my.setName(request.getParameter("name"));

			operator_my.setName(request.getParameter("log_name"));
			operator_my.setPwd(request.getParameter("log_pwd"));

			student_my.setSex(request.getParameter("sex").equals("male") ? "男" : "女");
			student_my.setBirth(sdf.parse(request.getParameter("birth")));
			Classes new_classes = classesImpl.query("cla_id",
					request.getParameter("cla_id")).get(0);
			if (new_classes.getId() != student_my.getClasses().getId()) {
				list_score = scoreImpl.query("stu_id", student_my.getId() + "");
				for (Score sco : list_score) {
					scoreImpl.delete(sco);
				}
				list_subject = subjectImpl.query("cla_id", new_classes.getId()
						+ "");
				for (Subject subject : list_subject) {
					Score score = new Score();
					score.setStudent(student_my);
					score.setSubject(subject);
					scoreImpl.add(score);
				}
			}
			student_my.setClasses(new_classes);
			student_my.setOperator(operator_my);

			pictureImpl.check(getServletConfig(), request, response, student_my);
			studentImpl.update(student_my);
			operatorImpl.update(operator_my);

			session.setAttribute("student_me", student_my);
			response.sendRedirect("pages/info_student.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
