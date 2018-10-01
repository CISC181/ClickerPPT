package pkgPPT;

import java.util.ArrayList;

public class Question {

	private int iQuestion;
	private String strQuestionTitle;
	private String strQuestion;
	private ArrayList<Response> Responses = new ArrayList<Response>();
	
	public Question(int iQuestion,String strQuestionTitle, String strQuestion) {
		super();
		this.strQuestionTitle = strQuestionTitle;
		this.iQuestion = iQuestion;
		this.strQuestion = strQuestion;
	}
	
	public Question(int iQuestion,String strQuestionTitle, String strQuestion, ArrayList<Response> responses) {
		super();
		this.strQuestionTitle = strQuestionTitle;
		this.iQuestion = iQuestion;
		this.strQuestion = strQuestion;
		Responses = responses;
	}

	public int getiQuestion() {
		return iQuestion;
	}

	public String getStrQuestionTitle() {
		return strQuestionTitle;
	}

	public void setStrQuestionTitle(String strQuestionTitle) {
		this.strQuestionTitle = strQuestionTitle;
	}

	public void setiQuestion(int iQuestion) {
		this.iQuestion = iQuestion;
	}

	public String getStrQuestion() {
		return strQuestion;
	}

	public void setStrQuestion(String strQuestion) {
		this.strQuestion = strQuestion;
	}

	public ArrayList<Response> getResponses() {
		return Responses;
	}

	public void setResponses(ArrayList<Response> responses) {
		Responses = responses;
	}
	
	public void ClearResponses()
	{
		Responses.clear();
	}
	
	public void AddResponse(Response r)
	{
		Responses.add(r);
	}
}
