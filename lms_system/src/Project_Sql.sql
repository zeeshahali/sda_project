Create DataBase lmsSystem
Go
Use lmsSystem

Create Table Teacher (
    Teacher_ID int Primary Key,
	Teacher_Name varchar(25),
	Teacher_Age int,
	Teacher_Email varchar(30) Unique,
	Teacher_Gender char Check (Teacher_Gender = 'M' OR Teacher_Gender = 'F')
);

Create Table Course (
    Course_ID int Primary Key,
	Course_Name varchar(25),
	Course_CH int Check (Course_CH >= 1 And Course_CH <=4)
);

Create Table Section (
    Section_ID int Primary Key,
	Section_Name varchar(15),
	Section_Total_Number_Of_Seats int,
    Section_Number_Of_Seats_Available int,
	Teacher_ID int Foreign Key References Teacher(Teacher_ID),
	Course_ID int Foreign Key References Course(Course_ID),
	Evaluation_ID int Foreign Key References Evaluation_criteria(Evaluation_ID)
);

Create Table Student (
    Student_ID int Primary Key,
	Student_Name varchar(25),
	Student_Gender char Check (Student_Gender = 'M' OR Student_Gender = 'F'),
	Student_Email varchar(30) Unique,
	Program int Foreign Key References Programs(Program_ID),
	SGPA int Default 0 Not Null,
	CGPA int Default 0 Not Null
);

Create Table Academic_Officer (
     Officer_ID int Primary Key,
	 Officer_Name varchar(25)
);

Create Table Users (
     Officer_ID int Foreign Key References Academic_Officer(Officer_ID),
     Teacher_ID int Foreign Key References Teacher(Teacher_ID),
     Student_ID int Foreign Key References Student(Student_ID),
	 Privilege char,
	 Primary Key (Officer_ID, Teacher_ID, Student_ID)
);

Create Table Course_Teachers (
    Teacher_ID int Foreign Key References Teacher(Teacher_ID),
	Course_ID int Foreign Key References Course(Course_ID),
	Section_ID int Foreign Key References Section(Section_ID),
	Primary Key (Teacher_ID, Course_ID, Section_ID)
);

Create Table Subscribe (
     Student_ID int Foreign Key References Student(Student_ID),
	 Section_ID int Foreign Key References Section(Section_ID),
	 Primary Key (Student_ID, Section_ID)
);

Create Table Transcript (
      Student_ID int Foreign Key References Student(Student_ID),
	  Course_ID int Foreign Key References Course(Course_ID),
	  Section_ID int Foreign Key References Section(Section_ID),
	  Grade int Default 0 Not Null,
	  Attendence int Default 0 Not Null,
	  Primary Key (Student_ID, Course_ID)
);


Create Table Evaluation_criteria (
      Evaluation_ID int Primary Key,
	  No_Of_Evaluations int,
	  Weightage int,
);

create table User_Types(
	[Type_ID] int Primary Key,
	[Type_Name] varchar(30)
)

create table User_Credentials(
	Email varchar(30) Foreign Key References Student(Student_Email) ,
	[password] varchar (30),
	[Type_ID] int Foreign Key References User_Types([Type_ID])
)