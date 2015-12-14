package provided.extvisitor.test;

/**
 * Test host
 * @author Stephen
 *
 */
public class HostStr3 extends AHostStr {

	/**
	 * For serialization
	 */
	private static final long serialVersionUID = -980479139007383453L;

	/**
	 * Construct with ID="HostStr3"
	 */
	public HostStr3() {
		super("HostStr3");

	}

	/**
	 * @return 3333 always
	 */
	public int getZ() {
		return 3333;
	}

}