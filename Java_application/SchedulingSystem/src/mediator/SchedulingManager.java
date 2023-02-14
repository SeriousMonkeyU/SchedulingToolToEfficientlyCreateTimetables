package mediator;
import model.*;

/**
 * A class implementing methods from interface <code>SchedulingManagerModel</code>
 *
 * @author Group 5
 * @version v1 December 2021
 */

public class SchedulingManager implements SchedulingManagerModel
{
  private StudentList studentList;
  private TeacherList teacherList;
  private CourseList courseList;
  private SessionList sessionList;
  private RoomList roomList;

  public SchedulingManager()
  {
     studentList = new StudentList();
     teacherList = new TeacherList();
     courseList = new CourseList();
     sessionList = new SessionList();
     roomList = new RoomList();
     importTextFile("Courses.txt", "courseFile");
    importTextFile("Rooms.txt", "roomFile");
    importTextFile("Students.txt", "studentFile");
    LoadSchedule loadSchedule = new LoadSchedule(this);
    loadSchedule.loadXmlFile();
  }

  public StudentList getAllStudents()
  {
    return studentList;
  }
  public TeacherList getAllTeachers()
  {
    return teacherList;
  }
  public CourseList getAllCourses()
  {
    return courseList;
  }
  public SessionList getAllSessions()
  {
    return sessionList;
  }

  @Override public RoomList getAllRooms()
  {
    return roomList;
  }

  public Teacher getTeacher(int index)
  {
    return teacherList.getTeacher(index);
  }
  public Student getStudent(int index)
  {
    return studentList.getStudent(index);
  }
  public Course getCourse(int index)
  {
    return courseList.getCourse(index);
  }
  public Session getSession(int index)
  {
    return sessionList.getSession(index);
  }
  public Room getRoom(int index)
  {
    return roomList.getRoom(index);
  }

  public Teacher getTeacher(Teacher teacher)
  {
    return teacherList.getTeacher(teacher);
  }
  public Student getStudent(Student student)
  {
    return studentList.getStudent(student);
  }
  public Course getCourse(Course course)
  {
    return courseList.getCourse(course);
  }
  public Session getSession(Session session)
  {
    return sessionList.getSession(session);
  }
  public Room getRoom(Room room)
  {
    return roomList.getRoom(room);
  }


  public void addTeacher(Teacher teacher)
  {
    if (teacher != null)
      teacherList.addTeacher(teacher);
  }
  public void addStudent(Student student)
  {
    if (student != null)
      studentList.addStudent(student);
  }
  public void addSession(Session session)
  {
    if (session != null)
    {
      sessionList.addSession(session);
      sessionList.getSession(session).getRoom().setReservation(session.getDate(),session.getTimeSlot());
    }

  }

  public void removeTeacher(Teacher teacher)
  {
    teacherList.removeTeacher(teacher);
  }

  public void removeTeacher(String id)
  {
    teacherList.removeTeacher(id);
  }
  public void removeStudent(String id)
  {
    studentList.removeStudent(id);
  }
  public void removeCourse(Course course)
  {
    courseList.removeCourse(course);
  }
  public void removeSession(Session session)
  {
    sessionList.getSession(session).getRoom().removeReservation(session.getDate(),session.getTimeSlot());
    sessionList.removeSession(session);
  }

  public StudentList getStudents(String name, int semester, String classLetters)
  {
    return studentList.getStudents(name, semester, classLetters);
  }
  public Teacher getTeacherById(String id)
  {
    return teacherList.getTeacherById(id);
  }

  public Course getCourseByName(String name)
  {
    return courseList.getCourseByName(name);
  }

  public Course getCourseByNameAndTeacher(String name, Teacher teacher)
  {
    return courseList.getCourseByNameAndTeacher(name, teacher);
  }

  public TeacherList getTeachers(String id, int semester)
  {
    return teacherList.getTeachers(id,semester);
  }

  public CourseList getCourses(String name, int semester)
  {
    return courseList.getCourses(name, semester);
  }
  public SessionList getSessions(Date date, StudentClass studentClass, String nameOfTheRoom)
  {
    return sessionList.getSessions(date, studentClass, nameOfTheRoom);
  }

   public SessionList getSessionsOnDate(Date date)
  {
    return sessionList.getSessionsOnDate(date);
  }

  public Room getRoomByName(String name)
  {
    return roomList.getRoomByName(name);
  }

  public RoomList getAllAvailableRoomsOnDateAndTimeSlot(Date date, TimeSlot timeslot)
  {
    return roomList.getAllAvailableRoomsOnDateAndTimeSlot(date, timeslot);
  }

  public void assignTeacherToCourse(Teacher teacher, Course course)
  {
    teacherList.getTeacher(teacher).assignToCourse(course);
  }
  public void assignStudentToCourse(Student student, Course course)
  {
    studentList.getStudent(student).assignToCourse(course);
  }

  public void removeTeacherFromCourse(Teacher teacher, Course course)
  {
    teacherList.getTeacher(teacher).removeFromCourse(course);
  }
  public void removeStudentFromCourse(Student student, Course course)
  {
    studentList.getStudent(student).removeFromCourse(course);
  }

  public void importTextFile(String filename, String typeOfData)
  {
    LoadFiles loader = new LoadFiles(filename, this);

    switch (typeOfData)
    {
      case "courseFile":
        loader.loadCourseFile();
        TeacherList teachers = new TeacherList();
          for (int i = 0; i < courseList.getNumberOfCourses(); i++)
          {
            if (courseList.getCourse(i).getTeacher() != null)
            {
              Teacher teacher = courseList.getCourse(i).getTeacher();
              if (!teachers.containsTeacher(teacher))
              {
                teachers.addTeacher(teacher);
              }
              teachers.getTeacherById(teacher.getId()).assignToCourse(courseList.getCourse(i));
            }
            }

          teacherList = teachers;
        break;
      case "roomFile":
        loader.loadRoomFile();
        break;
      case "studentFile":
        loader.loadStudentFile();
        for (int i = 0; i < studentList.getNumberOfStudents(); i++)
        {
          if (studentList.getStudent(i).getNumberOfCourses() == 0)
          {
            for(int j = 0; j < courseList.getNumberOfCourses(); j++)
            {
              if (studentList.getStudent(i).getSemester() == courseList.getCourse(j).getSemester()
                  && studentList.getStudent(i).getClassLetters().equals(courseList.getCourse(j).getClassLetters()))
              {
                studentList.getStudent(i).assignToCourse(courseList.getCourse(j));
              }
            }
          }

        }
        break;
    }
  }

  public void writeScheduleToXmlFile()
  {
    WriteToXML writer = new WriteToXML();
    writer.writeScheduleToXml(getAllSessions());
  }

   public void writeToTextFile(String filename, String typeOfData)
  {
    WriteToTextFile writer = new WriteToTextFile(filename,this);

    switch (typeOfData)
    {
      case "courseFile":
        writer.writeToCourseFile();
        break;
      case "roomFile":
        writer.writeToRoomFile();
        break;
      case "studentFile":
        writer.writeToStudentFile();
        break;
    }
  }
}
