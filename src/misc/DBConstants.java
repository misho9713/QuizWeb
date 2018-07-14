package misc;

public final class DBConstants {
    public static final String MYSQL_DATABASE_NAME = "quizdb";
    /* Quiz table constants */
    public static final String DB_TABLE_QUIZ = "quiz";
    public static final String DB_COLUMN_QUIZ_ID = "quiz_id";
    public static final String DB_COLUMN_QUIZ_NAME = "quiz_name";
    public static final String DB_COLUMN_QUIZ_RANDOM = "is_random";
    public static final String DB_COLUMN_QUIZ_SINGLE = "is_single_page";
    public static final String DB_COLUMN_QUIZ_IMMEDIATE = "has_immediate_correction";
    public static final String DB_COLUMN_QUIZ_PRACTICE = "can_practice";
    public static final String DB_COLUMN_QUIZ_AUTHOR = "quiz_author";

    /* User table constants*/
    public static final String DB_TABLE_USER = "account";
    public static final String DB_COLUMN_USER_ID = "user_id";
    public static final String DB_COLUMN_USER_NAME = "user_name";
    public static final String DB_COLUMN_USER_PASSWORD = "user_password_hash";
    public static final String DB_COLUMN_USER_ROLE = "user_role";

    /* Question table constants */
    public static final String DB_TABLE_QUESTION = "question";
    public static final String DB_COLUMN_QUESTION_ID = "question_id";
    public static final String DB_COLUMN_QUESTION_QUIZ_ID = "quiz_id";
    public static final String DB_COLUMN_QUESTION_TEXT = "question_text";
    public static final String DB_COLUMN_QUESTION_TYPE = "question_type";
    public static final String DB_COLUMN_QUESTION_TIMER = "question_timer";
    public static final String DB_COLUMN_QUESTION_ANSWER_COUNT = "answer_count";
    public static final String DB_COLUMN_QUESTION_WEIGHT = "question_weight";

    /* Question answer table constants */
    public static final String DB_TABLE_QUESTION_ANSWER = "question_answer";
    public static final String DB_COLUMN_QUESTION_ANSWER_ID = "answer_id";
    public static final String DB_COLUMN_QUESTION_ANSWER_QUESTIN_ID = "question_id";
    public static final String DB_COLUMN_QUESTION_ANSWER_TEXT = "answer";
    public static final String DB_COLUMN_QUESTION_ANSWER_CORRECT = "is_correct";

    /* User answer table constants */
    public static final String DB_TABLE_USER_ANSWER = "user_answer";
    public static final String DB_COLUMN_USER_ANSWER_ID = "user_answer_id";
    public static final String DB_COLUMN_USER_ANSWER_USER_ID = "user_id";
    public static final String DB_COLUMN_USER_ANSWER_QUESTION_ID = "question_id";
    public static final String DB_COLUMN_USER_ANSWER_QUESTION_ANSWER_ID = "question_answer_id";
    public static final String DB_COLUMN_USER_ANSWER_TEXT = "user_answer_text";
    public static final String DB_COLUMN_USER_ANSWER_TIME_TAKEN = "user_time_taken";

    /* User friendship table constants*/
    public static final String DB_TABLE_USER_FRIENDSHIP = "user_friendship";
    public static final String DB_COLUMN_USER_FRIENDSHIP_FRIEND_ID = "friend_id";
    public static final String DB_COLUMN_USER_FRIENDSHIP_SECOND_ID = "second_friend_id";

    /* Message table constants */
    public static final String DB_TABLE_MESSAGE = "message";
    public static final String DB_COLUMN_MESSAGE_ID = "message_id";
    public static final String DB_COLUMN_MESSAGE_SENDER = "sender_id";
    public static final String DB_COLUMN_MESSAGE_RECIEVER = "reciever_id";
    public static final String DB_COLUMN_MESSAGE_TEXT = "message_text";

    /* Challenge table constants */
    public static final String DB_TABLE_CHALLENGE = "challenge";
    public static final String DB_COLUMN_CHALLENGE_ID = "challenge_id";
    public static final String DB_COLUMN_CHALLENGE_SENDER = "sender_id";
    public static final String DB_COLUMN_CHALLENGE_RECIEVER = "reciever_id";
    public static final String DB_COLUMN_CHALLENGE_TEXT = "challenge_text";

    /* Friend request table constants */
    public static final String DB_TABLE_FRIEND_REQUEST = "request";
    public static final String DB_COLUMN_FRIEND_REQUEST_ID = "request_id";
    public static final String DB_COLUMN_FRIEND_REQUEST_SENDER = "sender_id";
    public static final String DB_COLUMN_FRIEND_REQUEST_RECIEVER = "reciever_id";
    public static final String DB_COLUMN_FRIEND_REQUEST_TEXT = "request_message";

    /* Taken quiz table constants*/
    public static final String DB_TABLE_TAKEN_QUIZ = "quiz_taken";
    public static final String DB_COLUMN_TAKEN_QUIZ_ID = "quiz_taken_id";
    public static final String DB_COLUMN_TAKEN_QUIZ_QUIZ = "quiz_id";
    public static final String DB_COLUMN_TAKEN_QUIZ_USER = "user_id";
    public static final String DB_COLUMN_TAKEN_QUIZ_START = "quiz_start";
    public static final String DB_COLUMN_TAKEN_QUIZ_END = "quiz_end";
    public static final String DB_COLUMN_TAKEN_QUIZ_SCORE = "quiz_score";

    /* Achievement table constants */
    public static final String DB_TABLE_ACHIEVEMENTS = "achievement";
    public static final String DB_COLUMN_ACHIEVEMENT_ID = "achievement_id";
    public static final String DB_COLUMN_ACHIEVEMENT_USER = "user_id";
    public static final String DB_COLUMN_ACHIEVEMENT_QUIZ = "quiz_id";
    public static final String DB_COLUMN_ACHIEVEMENT_NAME = "achievement_name";

    private DBConstants() {

    }
}
