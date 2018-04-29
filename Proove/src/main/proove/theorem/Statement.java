package proove.theorem;

public class Statement {
	public void setTruth(boolean truth){
		isVerified = true;
		isTrue = truth;
	}
	public boolean getVerified(){return isVerified;}
	public boolean getTruth(){return isTrue;}
	private boolean isVerified = false;
	private boolean isTrue = false;
}
