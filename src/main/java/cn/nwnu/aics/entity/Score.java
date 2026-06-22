package cn.nwnu.aics.entity;

public class Score {
	private int id;
	private double daily;
	private double exam;
	private double count;
	private Student student;
	private Subject subject;
	private Cla2Sub cla2sub;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDaily() {
		return daily;
	}

	public void setDaily(double daily) {
		this.daily = daily;
	}

	public double getExam() {
		return exam;
	}

	public void setExam(double exam) {
		this.exam = exam;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Cla2Sub getCla2sub() {
		return cla2sub;
	}

	public void setCla2sub(Cla2Sub cla2sub) {
		this.cla2sub = cla2sub;
	}
}
