package cn.nwnu.aics.cla2sub.servlet;

import cn.nwnu.aics.impl.Cla2SubImpl;
import cn.nwnu.aics.impl.ClassesImpl;
import cn.nwnu.aics.impl.ScoreImpl;
import cn.nwnu.aics.impl.StudentImpl;
import cn.nwnu.aics.impl.SubjectImpl;
import cn.nwnu.aics.impl.TeacherImpl;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cn.nwnu.aics.entity.Cla2Sub;
import cn.nwnu.aics.entity.Classes;
import cn.nwnu.aics.entity.Score;
import cn.nwnu.aics.entity.Student;
import cn.nwnu.aics.entity.Subject;
import cn.nwnu.aics.entity.Teacher;

public class AddCla2SubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 添加班级课程
		TeacherImpl teacherImpl = new TeacherImpl();
		ClassesImpl classesImpl = new ClassesImpl();
		SubjectImpl subjectImpl = new SubjectImpl();
		Cla2SubImpl cla2SubImpl = new Cla2SubImpl();
		StudentImpl studentImpl = new StudentImpl();
		ScoreImpl scoreImpl = new ScoreImpl();
		List<Student> list_student;
		Classes classes = classesImpl.query("cla_id",
				request.getParameter("cla_id")).get(0);
		Teacher teacher = teacherImpl.query("tec_id",
				request.getParameter("tec_id")).get(0);
		list_student = studentImpl.query("cla_id", classes.getId() + "");

		String[] sub_ids = request.getParameterValues("sub_ids");
		for (String id : sub_ids) {
			Subject subject = subjectImpl.query("sub_id", id).get(0);
			Cla2Sub cla2Sub = new Cla2Sub();
			cla2Sub.setClasses(classes);
			cla2Sub.setSubject(subject);
			cla2Sub.setTeacher(teacher);
			int i = cla2SubImpl.add(cla2Sub);
			// 为该班级的学生添加该门课的成绩
			for (Student stu : list_student) {
				Score score = new Score();
				score.setStudent(stu);
				score.setSubject(subject);
				score.setCla2sub(cla2SubImpl.findCla2sub(classes.getId(),
						teacher.getId(), subject.getId()));
				scoreImpl.add(score);
			}
			if (i == 1) {
				request.getSession().setAttribute("message", "为班级添加课程成功！");
			} else {
				request.getSession().setAttribute("message", "为班级添加课程失败！");
			}
		}
		response.sendRedirect("/Student/PlanAddCla2SubSevlet");
	}
}
