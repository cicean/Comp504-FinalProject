package provided.extvisitor.test;

/**
 * Test host 
 * @author Stephen
 *
 */
public class HostStr2 extends AHostStr {

	/**
	 * for serialization
	 */
	private static final long serialVersionUID = -3707352846923938536L;

	/**
	 * construct with ID="HostStr2"
	 */
	public HostStr2() {
		super("HostStr2");

	}

	/**
	 * @return  2222 always
	 */
	public int getY() {
		return 2222;
	}

}
