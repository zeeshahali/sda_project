use master
drop database lmsSystem
Create DataBase lmsSystem
Go
Use lmsSystem

Create Table Teacher (
    Teacher_ID int Primary Key Identity(1,1),
	Teacher_Name varchar(25),
	Teacher_username varchar(30) Unique,
	Teacher_Password varchar(30),
);

Create Table Academic_Officer (
	Officer_ID int Primary Key Identity(1,1),
	Officer_Name varchar(25),
	Officer_username varchar(30) unique,
	Officer_Password varchar(30)
);

Create Table Course (
    Course_Code varchar(10) Primary Key not null,
	Course_Name varchar(40),
	Offered_In_Semester int,
	Course_PreReq varchar(10) Foreign Key References Course(Course_Code) not null,
	Course_CH int Check (Course_CH >= 0 And Course_CH <=4)
);
drop table Section
Create Table Section (
    Section_Code varchar(20) Primary Key,
	Section_Name varchar(30),
	Section_Total_Number_Of_Seats int,
    Section_Number_Of_Seats_Available int,
	Teacher_username varchar(30) Foreign Key References Teacher(Teacher_username),
	Course_Code varchar(10) Foreign Key References Course(Course_Code),
);

drop table Student 
Create Table Student (
	Student_username varchar(30) Unique,
	Student_Password varchar(30),
	Student_Name varchar(25),
	Student_RollNo varchar(10) Primary Key,
	Student_Batch int,
	SGPA int Default 0 Not Null,
	CGPA int Default 0 Not Null
);

drop table RegisteredSection 
Create Table RegisteredSection (
	Section_Code varchar(20) Foreign Key References Section(Section_Code),
	Student_RollNo varchar(10) Foreign Key References Student(Student_RollNo),
	CreditEarned float,
	Semester int,
	TotalAttendance float,
	withdrawl varchar(15),
	Primary Key (Section_Code, Student_RollNo)
);
drop table Evaluations 
Create Table Evaluations (
      Evaluation_Name varchar(30),
	  Evaluation_Type varchar(15),
	  Weightage int, 
	  Marks_Obtained float,
	  Total_Marks int,
	  Registered_Student_RollNo varchar(10),
	  Registered_Section_Code varchar(20),
	  foreign key(Registered_Section_Code, Registered_Student_RollNo) References RegisteredSection(Section_Code, Student_RollNo),
	  primary key(Evaluation_Name, Registered_Section_Code, Registered_Student_RollNo)
);

drop table Attendance 
Create Table Attendance (
	[Date] varchar(15),
	present char check (present = 'p' or present = 'a'),
	Registered_Student_RollNo varchar(10),
	Registered_Section_Code varchar(20),
	foreign key(Registered_Section_Code, Registered_Student_RollNo) References RegisteredSection(Section_Code, Student_RollNo),
	Student_RollNo varchar(10) Foreign Key References Student(Student_RollNo),
	Primary Key ([Date], Registered_Section_Code, Student_RollNo)
);
drop table Subscribe 
Create Table Subscribe (
     Student_RollNo varchar(10) Foreign Key References Student(Student_RollNo),
	 Section_Code varchar(20) Foreign Key References Section(Section_Code),
	 Primary Key (Student_RollNo, Section_Code)
);
drop table Transcript 
Create Table Transcript (
      Student_RollNo varchar(10) Foreign Key References Student(Student_RollNo),
	  Section_Code varchar(20) Foreign Key References Section(Section_Code),
	  Course_Code varchar(10) Foreign Key References Course(Course_Code),
	  Grade int Default 0 Not Null,
	  Attendence int Default 0 Not Null,
	  Primary Key (Student_RollNo, Course_Code)
);

---- ----Teachers DATA ----------
Insert Into Teacher Values ('Ishaq Raza', 'Ishaq', '123456')
Insert Into Teacher Values ('Sarim Baig', 'Sarim', '123456')
Insert Into Teacher Values ('Kamran Lodhi', 'Kamran', '123456')
Insert Into Teacher Values ('Usman Awais', 'Usman', '123456')
Insert Into Teacher Values ('Aamir Raheem', 'Aamir', '123456')
Insert Into Teacher Values ('Taimoor Bakhshi', 'Taimoor', '123456')

------- Admin DATA ----------
Insert Into Academic_Officer Values ('Admin Sahb', 'Admin', 'admin')

--------- Courses DATA ---------
Insert Into Course Values ('None', 'None', 0, 'None', 0) --PreReq of those with no preReqs.
Insert Into Course Values ('CS220', 'Operating Systems', 3, 'None', 3)
Insert Into Course Values ('CS219', 'DataBase Systems', 3, 'None', 3)
Insert Into Course Values ('CS307', 'Computer Networks', 4, 'CS220', 3)
Insert Into Course Values ('CS118', 'Programming Fundamentals', 1, 'None', 4)
Insert Into Course Values ('CS217', 'OOP', 2, 'CS118', 3)
Insert Into Course Values ('CS303', 'Software Engineering', 5, 'CS307', 3)

--------- Sections DATA -----------
Insert Into Section Values ('CS219-A', 'Section A', 5, 3, 'Ishaq', 'CS219')
Insert Into Section Values ('CS220-B', 'Section B', 5, 3, 'Usman', 'CS220')
Insert Into Section Values ('CS307-A', 'Section A', 3, 2, 'Taimoor', 'CS307')
Insert Into Section Values ('CS220-D', 'Section D', 15, 15, 'Sarim', 'CS118')
Insert Into Section Values ('CS217-C', 'Section C', 10, 9, 'Sarim', 'CS217')
Insert Into Section Values ('CS303-F', 'Section F', 20, 19, 'Aamir', 'CS303')
Insert Into Section Values ('CS220-B', 'Section B', 7, 7, 'Ishaq', 'CS219')

----------- Students DATA ------------
Insert Into Student Values ('l18-5555', '123', 'Ronaldo', '18L-5555', 3, 0, 3.12)
Insert Into Student Values ('l18-0126', '123', 'Messi', '18L-0126', 3, 0, 2.99)
Insert Into Student Values ('l18-9797', '123', 'Lewandowski', '18L-9797', 4, 0, 3.67)
Insert Into Student Values ('l19-1299', '123', 'Halland', '19L-1299', 2, 0, 2.14)
Insert Into Student Values ('l17-4000', '123', 'Mbappe', '17L-4000', 5, 0, 2.62)

----------- Registered DATA ------------
Insert Into RegisteredSection Values('CS219-A', '18L-5555', 0, 3, 0, '2020-12-27')
Insert Into RegisteredSection Values('CS219-A', '18L-0126', 0, 3, 0, '2020-12-27')
Insert Into RegisteredSection Values('CS220-B', '18L-5555', 0, 3, 0, '2020-12-27')
Insert Into RegisteredSection Values('CS220-B', '18L-0126', 0, 3, 0, '2020-12-27')
Insert Into RegisteredSection Values('CS307-A', '18L-9797', 0, 4, 0, '2020-12-27')
Insert Into RegisteredSection Values('CS217-C', '19L-1299', 0, 2, 0, '2020-12-27')
Insert Into RegisteredSection Values('CS303-F', '17L-4000', 0, 5, 0, '2020-12-27')

---------- Evaluations DATA --------------
Insert Into Evaluations Values('Mid1', 'Midterm', 15, 21, 25, '18L-5555', 'CS219-A')
Insert Into Evaluations Values('Mid1', 'Midterm', 15, 19, 25, '18L-0126', 'CS219-A')
Insert Into Evaluations Values('Quiz2', 'Quiz', 5, 7, 10, '17L-4000', 'CS303-F')

------------ Attendance DATA -----------------
Insert Into Attendance Values('2020-10-22', 'p', '18L-5555', 'CS219-A', '18L-5555')
Insert Into Attendance Values('2020-10-22', 'a', '18L-0126', 'CS219-A', '18L-0126')
Insert Into Attendance Values('2020-10-24', 'a', '18L-5555', 'CS219-A', '18L-5555')
Insert Into Attendance Values('2020-10-24', 'a', '18L-0126', 'CS219-A', '18L-0126')
Insert Into Attendance Values('2020-11-12', 'p', '17L-4000', 'CS303-F', '17L-4000')
Insert Into Attendance Values('2020-11-14', 'a', '17L-4000', 'CS303-F', '17L-4000')
Insert Into Attendance Values('2020-11-19', 'p', '17L-4000', 'CS303-F', '17L-4000')

------------- Subscribe DATA --------------


------------- Transcript DATA --------------
Insert Into Transcript Values('18L-5555', 'CS219-A', 'CS219', 3.33, 89)
Insert Into Transcript Values('18L-5555', 'CS220-B', 'CS220', 2.67, 94)
