package pkgPPT;

public class Response {

	private int iResponseNbr;
	private String strResponse;
	private boolean bCorrect = false;
	public Response(int iResponseNbr, String strResponse, boolean bCorrect) {
		super();
		this.iResponseNbr = iResponseNbr;
		this.strResponse = strResponse;
		this.bCorrect = bCorrect;
	}
	public int getiResponseNbr() {
		return iResponseNbr;
	}
	public String getStrResponse() {
		return strResponse;
	}
	public boolean isbCorrect() {
		return bCorrect;
	}
	

	
}
