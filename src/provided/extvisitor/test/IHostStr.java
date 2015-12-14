package provided.extvisitor.test;

import provided.extvisitor.*;

/**
 * Different *sub-classes* of this interface call different methods on the visitor
 * @param <H> Tyhpe of host the this host's visitor will work with
 */
interface IHostStr<H extends IHostStr<? extends H>> extends
		IExtVisitorHost<String, H> {
}

/**
 * A lambda-based visitor to a HostStr
 */
class HostStrVis extends AExtVisitor<Integer, String, Integer, HostStr> {
	/**
	 * For serialization
	 */
	private static final long serialVersionUID = -9219792019089879282L;

	/**
	 * Construct with no-op result = -1
	 */
	public HostStrVis() {
		super(-1);
	}
}

/**
 * A visitor to an IHostStr
 */
class HostStrVis2 extends AExtVisitor<Integer, String, Integer, AHostStr> {
	/**
	 * For serialization
	 */
	private static final long serialVersionUID = -5583524977815228794L;

	/**
	 * Construct with no-op result = -99
	 */
	public HostStrVis2() {
		super(-99);
	}
}

/**
 * A host that uses an integer for its index
 */
class HostInt extends AExtVisitorHost<Integer, HostInt> {
	/**
	 * for serialization
	 */
	private static final long serialVersionUID = -6913783063875505325L;

	/**
	 * @param i  No-op result
	 */
	public HostInt(int i) {
		super(i);
	}
}

/**
 * A visitor to a host that uses an integer index
 */
class HostIntVis1 extends AExtVisitor<String, Integer, Integer, HostInt> {
	/**
	 * For serialization
	 */
	private static final long serialVersionUID = 926232454724347039L;

	/**
	 * Construct wtih no-op result = "No cmd found"
	 */
	public HostIntVis1() {
		super("No cmd found!");
	}
}
