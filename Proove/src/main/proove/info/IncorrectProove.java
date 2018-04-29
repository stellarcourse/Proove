package proove.info;

public class IncorrectProove extends Error{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9151905996938113640L;
	public IncorrectProove(String reason){
		super("Incorrect Proove");
		this.reason = reason;
	}
	public String getReason(){return reason;}
	protected String reason;
}
