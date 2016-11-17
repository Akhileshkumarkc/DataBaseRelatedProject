import java.sql.*;
import java.util.ArrayList;



import java.io.*;

class EnrollStudent {
	
	public EnrollStudent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public class reportStructure{
		public String Course_number;
		public String Section;
		public String Course_name;
		public String Instructor;
		
		public reportStructure(){};
		
		public void display(){
			System.out.println("");
			System.out.print(Course_number+" "+"Section"+Section+","+Course_name+","+"Instructor:"+Instructor);
			System.out.println("");
		}
	}
	
	//-------------------------------------
			//QUERYLIST
			public static final  String SelCourseNoFromSection = "SELECT course_number from SECTION "
								 			+ "Where SECTION_IDENTIFIER = ?";
			public static final  String SelAlreadyCouseNoFromStudent = "SELECT UNIQUE(COURSE_NUMBER) FROM GRADE_REPORT G,SECTION S "
												  + "WHERE G.SECTION_ID = S.SECTION_IDENTIFIER AND STUDENT_NUMBER =?";
			public static final  String SelAlreadyCouse08NoFromStudent = "SELECT COURSE_NUMBER,G.SECTION_ID FROM GRADE_REPORT G,SECTION S "
					  + "WHERE G.SECTION_ID = S.SECTION_IDENTIFIER AND STUDENT_NUMBER =? AND S.YEAR like '08' AND S.SEMESTER like 'Spring' "
					  + "AND G.SECTION_ID =? AND COURSE_NUMBER like ? ";
			public static final  String SelPreReqCourse ="SELECT PREREQUISITE_NUMBER from PREREQUISITE Where COURSE_NUMBER like ?";
			public static final  String SelYearSemFromCourse ="SELECT YEAR,SEMESTER FROM SECTION Where SECTION_IDENTIFIER = ?";
			public static final  String SelCountCourSpring08 = "SELECT COUNT(COURSE_NUMBER) FROM GRADE_REPORT G,SECTION S "
										    + "WHERE G.SECTION_ID = S.SECTION_IDENTIFIER "
										    + "AND STUDENT_NUMBER =? "
										    + "AND S.YEAR = 08 AND S.SEMESTER = 'SPRING'";
			public static final String INSClasses = "INSERT INTO GRADE_REPORT(STUDENT_NUMBER,SECTION_ID,GRADE) VALUES (?,?,null)";
			public static final String DropClasses = "DELETE FROM GRADE_REPORT WHERE STUDENT_NUMBER =? AND SECTION_ID = ? ";
			
			public static final String classScheduleQuery = "SELECT S.COURSE_NUMBER,G.SECTION_ID,C.COURSE_NAME,S.INSTRUCTOR FROM "
					+ "GRADE_REPORT G, SECTION S,COURSE C Where G.SECTION_ID = S.SECTION_IDENTIFIER AND S.COURSE_NUMBER = C.COURSE_NUMBER "
					+ "AND STUDENT_NUMBER = ? AND SEMESTER like 'Spring' AND YEAR like '08'";
			//-----------------------------------
			public static final int MAX_CLASSES_PER_SEM = 5;
	public static void main (String args [])
			throws SQLException, IOException {
		
		
		// Load Oracle’s JDBC Driver
		try {
			Class.forName
			("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println ("Could not load the driver");
		}
		//Connect to the database
		String user, pass;
//I		user = readEntry("userid : ");
//I		pass = readEntry("password: ");
		user="axk167131";
		pass="axk167131";
	
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@csoracle.utdallas.edu:1521:student",user,pass);
		if(conn!=null){
			System.out.println("The Database connected");
		}
		// user inputs
		int student_number,year,choice = 4;
		String sem;
		boolean repeat=true;
		do{
			repeat=true;
			student_number =  Integer.parseInt(readEntry("student no:"));
//			student_number=1;
			if(student_number > 0 && student_number <= 30){
				repeat = false;
			}
			else{
				System.out.println("student doesnt exist,tryagain");
			}
		}while(repeat);
		sem = readEntry("enter sem: ");
//		sem="Spring";
		year = Integer.parseInt(readEntry("enter year"));
//		year=2008;
		
		

		do{
			System.out.println("\nMain Menu");
			System.out.println("(1) Add Class");
			System.out.println("(2) Drop a Class");
			System.out.println("(3) See My Schedule");
			System.out.println("(4) Exit");
			choice =  Integer.parseInt(readEntry("Enter Your choice"));
		
			switch (choice){
			
			case 1: //Add a class
					try{					
//						AvailClassforEnroll(conn,student_number,sem,year);
						String course = 
								readEntry("Course Number:");
//						"MATH2001";
//						"MATH1001";
						String section = 
								readEntry("Section:");
//						"6";
//						"71";
//						"76";		
						if(ValSectionCourse(conn,course,section)
								&& valIfSecBelToSpring(conn,section) 
								&& ValIfCouReg(conn,course,student_number)  
								&& ValPreReqIsmet(conn,course,student_number)
								&& ValIfMaxRegExceed(conn,student_number))
						{
							Enrollclass(conn,student_number,course,section);
							System.out.println("The Student"+student_number+"was successfully enrolled to class "+course+" "+section);
						}
						else{
							System.out.println("Hence student cannot be Enrolled for classes");
						}
					}
					catch(SQLException E)
					{
						System.out.println("\n We are not able To Enroll classes, because of following Database exceptions");
						System.out.println(E);
					}
					break;
			case 2://drop a class
				
				try{					
//					classAvailToDrop(conn,student_number,sem,year);
					String course = 
								readEntry("Course Number:");
//					"MATH2001";
//					"MATH1001";
					String section = 
							readEntry("Section:");
//					"6";
//					"71";
//					"76";		
					if(ValSectionCourse(conn,course,section)
							&& valIfSecBelToSpring(conn,section) 
							&& ValIfCouReg08(conn,course,section,student_number)  
							)
					{
						dropClass(conn,student_number,course,section);
						System.out.println("The courses "+course+" "+section+" were dropped successfully for student "+student_number);
					}
					else{
						System.out.println("Hence classes cant be dropped");
					}
				}
				catch(SQLException E)
				{
					System.out.println("\n We are not able To drop classes, because of following Database exceptions");
					System.out.println(E);
				}
				break;
					
			case 3: //print schedule.
					try{
						printSchedule(conn,student_number);
					}
					catch(Exception e){
						System.out.println("Couldnot print the report because of following exception");
					}
				     
					 break;
			case 4:System.exit(-1); 
			default: System.out.println("Please enter a number between 0 to 4");
				break;	
			}
			
		}while(choice !=4);
	}
	
	 private static boolean ValyearSem(String year, String sem) {
		// TODO Auto-generated method stub
		 if(year.equalsIgnoreCase("2008") && sem.equalsIgnoreCase("Spring") && sem.equalsIgnoreCase("Fall"))
			 return true;
		 else 
			 	System.out.println("Only details of 2008 Spring are allowed to modify");
			 return false;
		 
	}

	private static void printSchedule(Connection conn, int student_number) throws SQLException {
		// TODO Auto-generated method stub
		 System.out.println("Your Current Schedule is");
		
		 
		
		 
		 
		 PreparedStatement p = conn.prepareStatement(classScheduleQuery);
		 p.clearParameters();
		 p.setInt(1, student_number);
		 ResultSet r = p.executeQuery();
		 
		 while(r.next()){
			 String Course_number = r.getString(1);
			 String Section = r.getString(2);
			 String Course_name = r.getString(3);
			 String Instructor = r.getString(4);
			 
			 System.out.print(Course_number+" "+"Section"+Section+", "+Course_name+", "+"Instructor:"+Instructor);
			 System.out.println("");
			 				
		 }
		
			
	}

	private static boolean dropClass(Connection conn, int student_number, String course, String section) throws SQLException {
		
		 PreparedStatement p = conn.prepareStatement(DropClasses);
		 p.clearParameters();
		 p.setInt(1, student_number);
		 p.setInt(2, Integer.parseInt(section));
	try{
		 p.executeUpdate();
	}
	 catch(SQLException e){
		 System.out.println("couldnot update the System");	
		 throw(e);
		 
	 }
	 return true;
	
}
		
	
	private static void classAvailToDrop(Connection conn, int student_number, String sem, int year) {
		// TODO Auto-generated method stub
		
	}
	 
	 /**
	  * Check if max Regcourses was done.
	  * @param conn
	  * @param student_number
	  * @return
	  * @throws SQLException
	  */
	private static boolean ValIfMaxRegExceed(Connection conn, int student_number) throws SQLException {
		 
		 
		 int noOfclasses =0;
		 PreparedStatement p = conn.prepareStatement(SelCountCourSpring08);
		 p.clearParameters();
		 p.setInt(1, student_number);
		 ResultSet r = p.executeQuery();
		 
		 if(r.next()){
			 noOfclasses = r.getInt(1);;
		 }
		 if(noOfclasses < MAX_CLASSES_PER_SEM)
		 {
			return true;
		 }
		 System.out.println("\nWARNING:Exceeded Maximum number of classes, retry again");
		 return false;
				 
		
	}
	/**
	 * Enroll for the class.
	 * @param conn
	 * @param student_number
	 * @param course
	 * @param section
	 * @return
	 * @throws SQLException
	 */
	private static boolean Enrollclass(Connection conn, int student_number, String course, String section) throws SQLException  {
		
		
		 
			 PreparedStatement p = conn.prepareStatement(INSClasses);
			 p.clearParameters();
			 p.setInt(1, student_number);
			 p.setInt(2, Integer.parseInt(section));
		try{
			 p.executeUpdate();
		}
		 catch(SQLException e){
			 System.out.println("couldnot update the System");	
			 throw(e);
			 
		 }
		 return true;
		
	}
	
	/**
	 * validates if Section belong to Spring 08.
	 * @param conn
	 * @param section
	 * @return
	 * @throws SQLException
	 */
	private static boolean valIfSecBelToSpring(Connection conn, String section) throws SQLException {
	
		 String year =null;
		 String sem =null;
		 
		 PreparedStatement p = conn.prepareStatement(SelYearSemFromCourse);
		 p.clearParameters();
		 p.setString(1, section);
		 ResultSet r = p.executeQuery();
		 
		 if(r.next()){
			 year = r.getString(1);
			 sem = r.getString(2);
		 }
		 if(year != null && sem!=null && year.equalsIgnoreCase("08")&&sem.equals("Spring"))
		 {
			return true;
		 }
		 System.out.println("\nWARNING:Section doesnt belong to the Spring 2008");
		return false;
		
	}
	/**
	 * Checks if the course was registered already.
	 * @param conn
	 * @param course
	 * @param student_number
	 * @return
	 * @throws SQLException
	 */
	private static boolean ValIfCouReg(Connection conn, String course, int student_number) throws SQLException {
		 String CourseNumber = null;
		 ArrayList<String> StudentCourseNumbers = new ArrayList<>();
		 ArrayList<String> PreCourseNumbers = new ArrayList<>();
		 PreparedStatement p = conn.prepareStatement(SelAlreadyCouseNoFromStudent);
		 p.clearParameters();
		 p.setInt(1, student_number);
		 ResultSet r = p.executeQuery();
		 
		 while(r.next()){
			 CourseNumber = r.getString(1);
			 StudentCourseNumbers.add(CourseNumber);
		 }
		 if(StudentCourseNumbers.contains(course)){
			 System.out.println("\nWARNING: The student " +student_number+" has been already Enrolled for this course "+course);
			 return false;
			 
		 }
		 else return true;
		
		
	}
	
	
	 /* Checks if the course was registered in spring08.
	 * @param conn
	 * @param course
	 * @param student_number
	 * @return
	 * @throws SQLException
	 */
	private static boolean ValIfCouReg08(Connection conn, String course, String section, int student_number) throws SQLException {
		 String CourseNumber = null;
		 int sectionnum=0;
		 int sectionInt = (int) Integer.parseInt(section);
		
		 PreparedStatement p = conn.prepareStatement(SelAlreadyCouse08NoFromStudent);
		 p.clearParameters();
		 p.setInt(1, student_number);
		 p.setInt(2,sectionInt );
		 p.setString(3,course);
		 ResultSet r = p.executeQuery();
		 
		 if(r.next()){
			 CourseNumber = r.getString(1);
			 sectionnum =r.getInt(2);
		 }
		 
		 if(course.equalsIgnoreCase(CourseNumber) && sectionInt==sectionnum){
			return true;
			 
		 }
		 
		 else{
			 System.out.println("\nWARNING: The student " +student_number+" has not been Enrolled for this course,section in the Spring 08 "+course+", "+section+" to drop");
			 return false;
			 }
		
		
		
	}
	
	
	//
	
	
	/**
	 * Checks if the prerequisite was met.
	 * @param conn
	 * @param course
	 * @param student_number
	 * @return
	 * @throws SQLException
	 */
	private static boolean ValPreReqIsmet(Connection conn, String course, int student_number) throws SQLException {
		

		 //Get all the CourseNumbers from student.
		 String CourseNumber = null;
		 ArrayList<String> StudentCourseNumbers = new ArrayList<>();
		 ArrayList<String> PreCourseNumbers = new ArrayList<>();
		 PreparedStatement p = conn.prepareStatement(SelAlreadyCouseNoFromStudent);
		 p.clearParameters();
		 p.setInt(1, student_number);
		 ResultSet r = p.executeQuery();
		 
		 while(r.next()){
			 CourseNumber = r.getString(1);
			 StudentCourseNumbers.add(CourseNumber);
		 }
		 //Get all the PreReqCourses from student.
		
		 p = conn.prepareStatement(SelPreReqCourse);
		 p.clearParameters();
		 p.setString(1, course);
		 r = p.executeQuery();
		 while(r.next()){
			 CourseNumber = r.getString(1);
			 PreCourseNumbers.add(CourseNumber);
		 }
		 
		 if(PreCourseNumbers.size()==0|| StudentCourseNumbers.containsAll(PreCourseNumbers)) return true;
		 else {
			 System.out.println("\nWARNING: PreRequisites are not met for the courses "+")PreCourseNumbers:("+PreCourseNumbers+")"+"StudentCourses:( "+StudentCourseNumbers);
			 return false;
		 }
	}
	/**
	 * validate if courses and section are matching...
	 * @param conn
	 * @param course
	 * @param section
	 * @return
	 * @throws SQLException
	 */
	private static boolean ValSectionCourse(Connection conn, String course, String section) throws SQLException {
		
		 String Db_course_name =null;
		 PreparedStatement p = conn.prepareStatement(SelCourseNoFromSection);
		 p.clearParameters();
		 p.setString(1, section);
		 ResultSet r = p.executeQuery();
		 
		 if(r.next()){
			 Db_course_name = r.getString(1);
		 }
		 if(Db_course_name != null && Db_course_name.equalsIgnoreCase(course))
		 {
			return true;
		 }
		 System.out.println("\nWARNING: The Course = "+course +"and section = "+section+ "are either not presenting or matching");
		return false;
		
	}
//	private static void AvailClassforEnroll(Connection conn, int student_number, String sem, int year) {
//		// TODO Auto-generated method stub
//		
//	}
	//readEntry function -- to read input string
	static String readEntry(String prompt) {
		try {
			StringBuffer buffer = new StringBuffer();
			System.out.print(prompt);
			System.out.flush();
			int c = System.in.read();
			while (c != '\n' && c != -1) {
				buffer.append((char) c);
				c = System.in.read();
			}
			return buffer.toString().trim();
		} catch (IOException e) {
			return "";
		}
	}
	
}