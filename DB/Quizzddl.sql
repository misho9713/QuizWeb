DROP SCHEMA IF EXISTS QuizDB;
CREATE SCHEMA QuizDB;
USE QuizDB;
CREATE TABLE account
(
  user_id            INT PRIMARY KEY AUTO_INCREMENT,
  user_name          VARCHAR(100) UNIQUE,
  user_password_hash VARCHAR(40)                    NOT NULL,
  user_role          ENUM ('user', 'administrator') NOT NULL
);

CREATE TABLE quiz
(
  quiz_id                  INT PRIMARY KEY AUTO_INCREMENT,
  quiz_author              INT     NOT NULL,
  quiz_name                VARCHAR(150),
  is_random                BOOLEAN NOT NULL,
  is_single_page           BOOLEAN NOT NULL,
  has_immediate_correction BOOLEAN NOT NULL,
  can_practice             BOOLEAN NOT NULL,
  CONSTRAINT table_name_user_users_id_fk FOREIGN KEY (quiz_author) REFERENCES account (user_id)

);
CREATE TABLE question
(
  question_id     INT PRIMARY KEY                                           AUTO_INCREMENT,
  quiz_id         INT NOT NULL,
  question_text   TEXT,
  question_type   ENUM ('text', 'fill_blank', 'multiple_choice', 'picture') DEFAULT 'text',
  question_timer  LONG,
  CONSTRAINT questions_quizzes_quiz_id_fk FOREIGN KEY (quiz_id) REFERENCES quiz (quiz_id)
    ON DELETE CASCADE,
  answer_count    INT                                                       DEFAULT 1,
  question_weight INT                                                       DEFAULT 1
);
CREATE TABLE question_answer
(
  answer_id   INT PRIMARY KEY  AUTO_INCREMENT,
  question_id INT  NOT NULL,
  answer      TEXT NOT NULL,
  is_correct  BOOLEAN          DEFAULT TRUE,
  CONSTRAINT question_answers_questions_question_id_fk FOREIGN KEY (question_id) REFERENCES question (question_id)
    ON DELETE CASCADE
);

CREATE TABLE user_answer
(
  user_answer_id     INT PRIMARY KEY AUTO_INCREMENT,
  user_id            INT NOT NULL,
  question_id        INT NOT NULL,
  question_answer_id INT,
  user_answer_text   TEXT,
  user_time_taken    INT             DEFAULT 0,
  CONSTRAINT user_answer_user_user_id_fk FOREIGN KEY (user_id) REFERENCES account (user_id)
    ON DELETE CASCADE,
  CONSTRAINT user_answer_questions_question_id_fk FOREIGN KEY (question_id) REFERENCES question (question_id)
);
CREATE TABLE user_friendship
(
  friend_id        INT NOT NULL,
  second_friend_id INT NOT NULL,
  CONSTRAINT user_friendship_user_user_id_fk FOREIGN KEY (friend_id) REFERENCES account (user_id)
    ON DELETE CASCADE,
  CONSTRAINT user_friendship_user_user_id_fk_2 FOREIGN KEY (second_friend_id) REFERENCES account (user_id)
    ON DELETE CASCADE
);
CREATE TABLE message
(
  message_id   INT PRIMARY KEY AUTO_INCREMENT,
  sender_id    INT  NOT NULL,
  reciever_id  INT  NOT NULL,
  message_text TEXT NOT NULL,
  CONSTRAINT message_user_user_id_fk FOREIGN KEY (sender_id) REFERENCES account (user_id)
    ON DELETE CASCADE,
  CONSTRAINT message_user_user_id_fk_2 FOREIGN KEY (reciever_id) REFERENCES account (user_id)
    ON DELETE CASCADE
);
CREATE TABLE challenge
(
  challenge_id   INT PRIMARY KEY AUTO_INCREMENT,
  sender_id      INT NOT NULL,
  reciever_id    INT NOT NULL,
  quiz_id        INT NOT NULL,
  challenge_text TEXT,
  CONSTRAINT challenge_user_user_id_fk FOREIGN KEY (sender_id) REFERENCES account (user_id)
    ON DELETE CASCADE,
  CONSTRAINT challenge_user_user_id_fk_2 FOREIGN KEY (reciever_id) REFERENCES account (user_id)
    ON DELETE CASCADE,
  CONSTRAINT challenge_quizzes_quiz_id_fk FOREIGN KEY (quiz_id) REFERENCES quiz (quiz_id)
    ON DELETE CASCADE
);
CREATE TABLE friend_request
(
  request_id      INT PRIMARY KEY AUTO_INCREMENT,
  sender_id       INT NOT NULL,
  reciever_id     INT NOT NULL,
  request_message TEXT,
  CONSTRAINT friend_request_user_user_id_fk FOREIGN KEY (sender_id) REFERENCES account (user_id)
    ON DELETE CASCADE,
  CONSTRAINT friend_request_user_user_id_fk_2 FOREIGN KEY (reciever_id) REFERENCES account (user_id)
    ON DELETE CASCADE
);
CREATE TABLE quiz_taken
(
  quiz_taken_id INT PRIMARY KEY AUTO_INCREMENT,
  quiz_id       INT  NOT NULL,
  user_id       INT  NOT NULL,
  quiz_start    DATE NOT NULL,
  quiz_end      DATE NOT NULL,
  quiz_score    INT  NOT NULL,
  quiz_practice BOOLEAN         DEFAULT FALSE,
  CONSTRAINT quiz_taken_quizzes_quiz_id_fk FOREIGN KEY (quiz_id) REFERENCES quiz (quiz_id)
    ON DELETE CASCADE,
  CONSTRAINT quiz_taken_user_user_id_fk FOREIGN KEY (user_id) REFERENCES account (user_id)
    ON DELETE CASCADE
);
CREATE TABLE achievement
(
  achievement_id   INT PRIMARY KEY AUTO_INCREMENT,
  user_id          INT                                                                                                                            NOT NULL,
  quiz_id          INT             DEFAULT NULL,
  achievement_name ENUM ('Amateur Author', 'Prolific Author', 'Prodigious Author', 'Quiz Machine', 'I am the Greatest', 'Practice Makes Perfect') NOT NULL,
  CONSTRAINT achievemnts_user_user_id_fk FOREIGN KEY (user_id) REFERENCES account (user_id)
    ON DELETE CASCADE,
  CONSTRAINT achievemnts_quizzes_quiz_id_fk FOREIGN KEY (quiz_id) REFERENCES quiz (quiz_id)
    ON DELETE SET NULL
);