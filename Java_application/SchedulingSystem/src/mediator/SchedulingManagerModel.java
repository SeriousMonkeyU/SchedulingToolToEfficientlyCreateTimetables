package mediator;
import model.*;

/**
 * An interface with methods that allow user to perform actions on <code>model</code> classes within GUI
 *
 * @author Group 5
 * @version v1 December 2021
 */

public interface SchedulingManagerModel
{
   /**
    * Getter for all students in system
    * @return list of all students
    */
   StudentList getAllStudents();
   /**
    * Getter for all teachers in system
    * @return list of all teachers
    */
   TeacherList getAllTeachers();
   /**
    * Getter for all sessions in system
    * @return list of all sessions
    */
   SessionList getAllSessions();
   /**
    * Getter for all courses in system
    * @return list of all courses
    */
   CourseList getAllCourses();
   /**
    * Getter for all rooms in system
    * @return list of all rooms
    */
   RoomList getAllRooms();

   /**
    * Getter for student located at specific index
    * @param index number representing index
    * @return student that is located at index passed as parameter
    */

   Student getStudent(int index);

   /**
    * Getter for teacher located at specific index
    * @param index number representing index
    * @return teacher that is located at index passed as parameter
    */
   Teacher getTeacher(int index);

   /**
    * Getter for session located at specific index
    * @param index number representing index
    * @return session that is located at index passed as parameter
    */
   Session getSession(int index);

   /**
    * Getter for course located at specific index
    * @param index number representing index
    * @return course that is located at index passed as parameter
    */
   Course getCourse(int index);

   /**
    * Getter for room located at specific index
    * @param index number representing index
    * @return room that is located at index passed as parameter
    */

   Room getRoom(int index);

   /**
    * Getter for student from a system equal to student passed as parameter
    * @param student student that we want to find in the system
    * @return student equal to parameter or null if such student does not exist
    */
   Student getStudent(Student student);
   /**
    * Getter for teacher from a system equal to teacher passed as parameter
    * @param teacher teacher that we want to find in the system
    * @return teacher equal to parameter or null if such teacher does not exist
    */
   Teacher getTeacher(Teacher teacher);
   /**
    * Getter for session from a system equal to session passed as parameter
    * @param session session that we want to find in the system
    * @return session equal to parameter or null if such session does not exist
    */
   Session getSession(Session session);
   /**
    * Getter for course from a system equal to course passed as parameter
    * @param course course that we want to find in the system
    * @return course equal to parameter or null if such course does not exist
    */
   Course getCourse(Course course);
   /**
    * Getter for room from a system equal to room passed as parameter
    * @param room room that we want to find in the system
    * @return room equal to parameter or null if such room does not exist
    */
   Room getRoom(Room room);

   /**
    * Method adding student to list of students in the system
    * @param student student that will be added
    */
   void addStudent(Student student);
   /**
    * Method adding teacher to list of teachers in the system
    * @param teacher teacher that will be added
    */
   void addTeacher(Teacher teacher);

   /**
    * Method adding session to list of sessions in the system. The method also creates the reservation of the room at session's date and time slot
    * @param session session that will be added
    */
   void addSession(Session session);

   /**
    * Method removing student with certain id from a system
    * @param id id of student you want to be removed
    */
   void removeStudent(String id);
   /**
    * Method removing  from the system teacher equal to teacher passed as parameter
    * @param teacher teacher that you want to be removed
    */
   void removeTeacher(Teacher teacher);
   /**
    * Method removing teacher with certain id from a system
    * @param id id of teacher you want to be removed
    */
   void removeTeacher(String id);
   /**
    * Method removing from the system course equal to course passed as parameter
    * @param course course that you want to be removed
    */
   void removeCourse(Course course);

   /**
    * Method removing from the system session equal to session passed as parameter
    * @param session session that you want to be removed
    */
   void removeSession(Session session);

   /**
    * Method that conducts a filter on list of student and return filtered list, criteria of filtering are passed as parameters and can be
    * in any combination (e.g. filtering by semester and name at the same time). Method recognizes which parameters it should use in filtering based on their values.
    * @param name name that filtered students should have
    * @param semester semester on which filtered students should be
    * @param classLetters class letters (e.g. DK) of class that filtered students should be part of
    * @return <code>StudentList</code> containing students that match criteria of the filtering
    */
   StudentList getStudents(String name, int semester, String classLetters);

   /**
    * Getter for teacher with the id as the one passed as parameter
    * @param id id of the teacher
    * @return teacher with the id equal to <code>id</code> or null if such teacher does not exist
    */

   Teacher getTeacherById(String id);

   /**
    * Getter for course with specific name
    * @param name String representing name of the course
    * @return course with the same name as String passed as parameter or null if such a course does not exist
    */

   Course getCourseByName(String name);

   /**
    * Getter for course with specific name and teacher
    * @param name String representing name of the course
    * @param teacher instance of Teacher class
    * @return course with the name and teacher same as values passed in parameters or null if such a course does not exist
    */

   Course getCourseByNameAndTeacher(String name, Teacher teacher);

   /**
    * Method filtering the list of teacher based on any combination of 2 factors - their id and semester of coursed that they are teaching. Method returns filtered list of teachers
    * @param id id of the teacher
    * @param semester semester of course that the teacher should be teaching
    * @return <code>TeacherList</code> containing only teachers that match the filtering criteria
    */
   TeacherList getTeachers(String id, int semester);

   /**
    * Method returning CourseList with courses that have same semester and name as values passed as parameters
    * @param name String representing name of course
    * @param semester int representing the number of semester (1,2,3,4,5,6 or 7)
    * @return course list with courses fulfilling requirements
    */

   CourseList getCourses(String name, int semester);

   /**
    * Method conducting a search based on passed parameters (search can be done based on 1 parameter or combinations of 2 parameters or all 3 parameters).
    * Method recognizes which based on which parameters it should conduct search and returns result of search. Purpose of this method is to filter data about session.
    * @param date date on which sessions should happen
    * @param studentClass student class that should be participating in sessions
    * @param nameOfTheRoom name of the room in which session should take place
    * @return <code>SessionList</code> containing sessions that match passed parameters
    */

   SessionList getSessions(Date date, StudentClass studentClass, String nameOfTheRoom);

   /**
    * Getters for session happening at certain date
    * @param date date on which sessions should happen
    * @return <code>SessionList</code> containing sessions happening at passed date
    */

   SessionList getSessionsOnDate(Date date);

   /**
    * Getter for a room with specific name
    * @param name name of the room
    * @return room with name passed as parameter or null if such name does not exist
    */

   Room getRoomByName(String name);

   /**
    * Method searching for rooms that are not reserved at given date and timeslot, the search is conducted with use of loops and with use of the property
    * of the system that indexes of dates and timeslots that represent one reservation are the same
    * @param date date at which rooms should be not reserved
    * @param timeSlot timeslot at which rooms should not be reserved
    * @return <code>RoomList</code> containing only rooms that do not have reservation at given date and timeslot
    */

   RoomList getAllAvailableRoomsOnDateAndTimeSlot(Date date, TimeSlot timeSlot);

   /**
    * Method assigning teacher to certain course
    * @param teacher teacher that will have the course assigned
    * @param course course that we want to assign to a teacher
    */
   void assignTeacherToCourse(Teacher teacher, Course course);
   /**
    * Method assigning student to certain course
    * @param student student that will have the course assigned
    * @param course course that we want to assign to a student
    */
   void assignStudentToCourse(Student student, Course course);

   /**
    * Method unassigning teacher from teaching certain course
    * @param teacher teacher that will be unassigned from course
    * @param course course that we want to assign to a teacher
    */

   void removeTeacherFromCourse(Teacher teacher, Course course);

   /**
    * Method removing student from certain course
    * @param student student that will be removed from course
    * @param course course that we want to remove student from
    */
   void removeStudentFromCourse(Student student, Course course);

   /**
    * Method loading data from text file into the system
    * @param filename name of the file or path to the file if it is not it same directory as system
    * @param typeOfData type of data that we want to load into system (studentFile, roomFile or courseFile)
    */

   void importTextFile(String filename, String typeOfData);

   /**
    * Method writing list of sessions (schedule) to xml file to ensure system's persistence and also prepare data to be presented on website in form of timetable
    */
   void writeScheduleToXmlFile();

   /**
    * Method writing to the text file data kept in the system to ensure system's persistence
    * @param filename name of the file or path to the file if it is not it same directory as system
    * @param typeOfData type of data that we want to preserve (studentFile, roomFile or courseFile)
    */
   void writeToTextFile(String filename, String typeOfData);
}
