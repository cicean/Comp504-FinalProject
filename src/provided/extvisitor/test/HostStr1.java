package provided.extvisitor.test;

/**
 * Test host 
 * @author Stephen
 *
 */
public class HostStr1 extends AHostStr {

	/**
	 * for serialization
	 */
	private static final long serialVersionUID = 3535670799396363961L;

	/**
	 * construct with ID="HostStr1"
	 */
	public HostStr1() {
		super("HostStr1");

	}

	/**
	 * @return 1111 always
	 */
	public int getX() {
		return 1111;
	}

}
