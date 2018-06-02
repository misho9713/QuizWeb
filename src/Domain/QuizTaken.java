package Domain;

import java.sql.Date;
import java.sql.Time;

public class QuizTaken {
	
	   private int _quiztakeId;
	   
	   private Date _startdate;
	   
	   private Date _enddate;
	   
	   private Time duration;
	   
	   private float _score;
	   
	   private User _user;
	   
	   private Quiz _quiz;
	   

   public int get_quiztakeId() {
		return _quiztakeId;
	}

	public void set_quiztakeId(int _quiztakeId) {
		this._quiztakeId = _quiztakeId;
	}

	public Date get_startdate() {
		return _startdate;
	}

	public void set_startdate(Date _startdate) {
		this._startdate = _startdate;
	}

	public Date get_enddate() {
		return _enddate;
	}

	public void set_enddate(Date _enddate) {
		this._enddate = _enddate;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public float get_score() {
		return _score;
	}

	public void set_score(float _score) {
		this._score = _score;
	}

	public User get_user() {
		return _user;
	}

	public void set_user(User _user) {
		this._user = _user;
	}

	public Quiz get_quiz() {
		return _quiz;
	}

	public void set_quiz(Quiz _quiz) {
		this._quiz = _quiz;
	}
   
}
