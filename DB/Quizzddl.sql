drop schema if exists QuizDB;
create schema QuizDB;
use QuizDB;
create table Users(
   ID int primary key auto_increment,
   
   UserT_ID int references UserTypes(ID),
   
   login_name nvarchar(50) unique not null,
   
   user_password  text
);

create table Quizzes(
   ID int primary key auto_increment,
   
   quizz_description text not null,
   
   random_question boolean,
   
   multiple_page boolean,
   
   immediate_correction boolean,
   
   practice_mode boolean,
   
   User_ID  int not null references Users(ID),
   
   create_time datetime 
   
);
create table QuestionTypes(
  ID int auto_increment primary key,
  
  question_name  varchar(50)
);

create table Questions(
   ID int primary key auto_increment,
   
   QType_ID int not null references QuestionTypes(ID),
   
   question text 
);

create table QuizzesTaken(

    ID int auto_increment primary key,
    
    User_ID int not null references Users(ID),
    
    Quiz_ID int references Quizzes(ID),
    
    start_date datetime not null,
    
    end_date datetime not null,
    
	duration time not null,
    
    score decimal(5,2) not null
);

create table UserTypes(
     ID int auto_increment primary key,
     
     user_type_name varchar(50)
     
)