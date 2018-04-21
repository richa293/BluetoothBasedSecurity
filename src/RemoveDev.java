import java.io.IOException;
import java.util.Vector;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import com.intel.bluetooth.RemoteDeviceHelper;
/**
* Class that discovers all bluetooth devices in the neighbourhood
* and obtains their name and bluetooth address.
*/
public class RemoveDev implements DiscoveryListener
{
	    //object used for waiting
	    private static Object lock=new Object();

	    
	    public int random() throws BluetoothStateException
	    {
	    	RemoveDev remove = new RemoveDev();
	    	System.out.println("In my code");
	    	 LocalDevice localDevice = LocalDevice.getLocalDevice();
	    	 DiscoveryAgent agent = localDevice.getDiscoveryAgent();
		   
		        System.out.println("Starting device inquiry...");
		        agent.startInquiry(DiscoveryAgent.GIAC, remove);
				return 0;
		
	    }
		@Override
		public void deviceDiscovered(RemoteDevice arg0, DeviceClass arg1) {
			// TODO Auto-generated method stub
			try {
				RemoteDeviceHelper.removeAuthentication(arg0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		@Override
		public void inquiryCompleted(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void serviceSearchCompleted(int arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void servicesDiscovered(int arg0, ServiceRecord[] arg1) {
			// TODO Auto-generated method stub
			
		}
	   
}