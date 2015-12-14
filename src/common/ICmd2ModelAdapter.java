package common;

import java.awt.Component;
import java.util.function.Supplier;

import javax.swing.JFrame;

import common.message.IChatMessage;
import provided.mixedData.MixedDataKey;

/**
 * Command to model adapter that enables command to talk to local model. 
 */
public interface ICmd2ModelAdapter {

	/**
	 * Was scrollable() in HW08. 
	 *  
	 * Give local view a component factory to append to its scrollable unit. 
	 * Typical implementation will make use of SwingUtilities.invokeLater(). 
	 * @param componentFac - factory that will be passed to append to the local view's scrollable unit. 
	 */
	public void addToScrollable(Supplier<Component> componentFac);

	/**
	 * Was updateable() in HW08. 
	 * 
	 * Give local view a component factory to update its updatable unit. 
	 * Typical implementation will make use of SwingUtilities.invokeLater(). 
	 * @param componentFac - factory that will be passed to update the local view's updatable unit.  
	 */
	public void updateUpdatable(Supplier<Component> componentFac);
	
	/**
	 * Give local view a component factory to create a new JFrame window.
	 * @param frameFac - factory that will be passed to view to make a new window.
	 */
	public void createNewWindow(Supplier<JFrame> frameFac);

	/**
	 * Returns entry from MixedDataDictionary by specified key. 
	 * @param <T> - type of key
	 * @param key - a MixedDataKey
	 * @return entry from MixedDataDictionary by specified key
	 */
	public <T> T getMixedDataDictEntry(MixedDataKey<T> key);

	/**
	 * Set entry to MixedDataDictionary by specified key. 
	 * @param <T> - type of key
	 * @param key - a MixedDataKey
	 * @param value - data associated with this key
	 */
	public <T> void setMixedDataDictEntry(MixedDataKey<T> key, T value);

	/**
	 * Sends the message to your current chatroom.
	 * @param message The message to be sent to the chatroom.
	 */
	public void sendToChatroom(IChatMessage message);
	
	/**
	 * Send a message to a particular user.
	 * @param msg the message to send
	 * @param chatUser the receipt of the message
	 */
	public void sendMsgTo(IChatMessage msg, IChatUser chatUser);

	/**
	 * Returns the user name of the peer.
	 * @return the user name of the peer.
	 */
	public String getUserName();

}
