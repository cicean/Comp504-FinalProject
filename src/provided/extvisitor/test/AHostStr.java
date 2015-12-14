package provided.extvisitor.test;

import provided.extvisitor.*;

/**
 * Test host
 * @author Stephen
 *
 */
public abstract class AHostStr implements IHostStr<AHostStr> {

	/**
	 * for serialization
	 */
	private static final long serialVersionUID = -5951881622588273026L;

	/**
	 * Host id
	 */
	private String idx = "AHostStr";

	/**
	 * @param idx  host id
	 */
	public AHostStr(String idx) {
		this.idx = idx;
	}

	@Override
	public <R, P> R execute(IExtVisitor<R, String, P, ? extends AHostStr> algo,
			@SuppressWarnings("unchecked") P... params) {
		return algo.caseAt(idx, this, params);
	}

}
