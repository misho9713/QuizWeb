package Domain;

import java.sql.Date;

import java.util.List;

public class Quiz {
	
	   private int _id;
	   
	   private String _quizzdescription;
	   
	   private boolean _randomquestion;
	   
	   private boolean _multiplepage;
	   
	   private boolean  _immediatecorrection;
	   
	   private boolean _practicemode;
	    	   
	   private Date _createdate;
       
	   private User _quizauthor;
	
	
	
   public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_quizzdescription() {
		return _quizzdescription;
	}

	public void set_quizzdescription(String _quizzdescription) {
		this._quizzdescription = _quizzdescription;
	}

	public boolean is_randomquestion() {
		return _randomquestion;
	}

	public void set_randomquestion(boolean _randomquestion) {
		this._randomquestion = _randomquestion;
	}

	public boolean is_immediatecorrection() {
		return _immediatecorrection;
	}

	public void set_immediatecorrection(boolean _immediatecorrection) {
		this._immediatecorrection = _immediatecorrection;
	}

	public boolean is_practicemode() {
		return _practicemode;
	}

	public void set_practicemode(boolean _practicemode) {
		this._practicemode = _practicemode;
	}
	
	public Date get_createdate() {
		return _createdate;
	}

	public void set_createdate(Date _createdate) {
		this._createdate = _createdate;
	}

	public boolean is_multiplepage() {
		return _multiplepage;
	}

	public void set_multiplepage(boolean _multiplepage) {
		this._multiplepage = _multiplepage;
	}

	public User get_quizauthor() {
		return _quizauthor;
	}

	public void set_quizauthor(User _quizauthor) {
		this._quizauthor = _quizauthor;
	}

      
}
