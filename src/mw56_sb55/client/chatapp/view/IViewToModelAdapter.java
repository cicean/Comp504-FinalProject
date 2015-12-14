package mw56_sb55.client.chatapp.view;

/**
 * The chat app view to model adatper
 * @author mw56
 * @param <TUser>
 * @param <TChatroom>
 */
public interface IViewToModelAdapter {

	/**
	 * ConnectTo function is used to connect to another machine
	 * using that machine's IP address.
	 * @param text The IP address
	 * @return A string indicating if we have connected successfully.
	 */
	public String connectTo(String text);

	/**
	 * quit the application.
	 */
	public void quit();

	/** Null adapter */
	public static final IViewToModelAdapter NULL_OBJECT = new IViewToModelAdapter() {

		@Override
		public String connectTo(String text) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void quit() {
			// TODO Auto-generated method stub
			
		}

		

	};


	

	

	

}
