package proove.facts;

import proove.symbols.Symbol;

/**
 * While the proof steps are progressing, a set can be used to store facts<p>
 * Facts are pieces of statements such as something equals to something, or mirrors
 * @author Hai
 *
 */
public abstract class NewFact<T extends Symbol> {
	public String getType(){return type;}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(NewFact<T> other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}

		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		return true;
	}
	public abstract String explain();
	public abstract String state();
	public NewFact(String type, String symbol){
		this.type = type;
		this.symbol= symbol;
	}
	protected String type;
	protected String symbol;
}
