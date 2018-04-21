import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import com.intel.bluetooth.RemoteDeviceHelper;
	/**
	* Class that discovers all bluetooth devices in the neighbourhood
	* and pairs them,
	*/
public class BluetoothPairing implements DiscoveryListener
{	   
    //object used for waiting
    private static Object lock=new Object();

    //vector containing the devices discovered
	public static Vector vecDevices=new Vector();
	   
	//main method of the application
	public static void main(String[] args) throws IOException 
	{
	}
	//end of main
	
	public void pair(String FilePath,String name) throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
	       
		//create an instance of this class
		BluetoothPairing BluetoothPairing=new BluetoothPairing();
	       
		LocalDevice localDevice = LocalDevice.getLocalDevice();
	  
		//find devices
		DiscoveryAgent agent = localDevice.getDiscoveryAgent();
	   
		System.out.println("Starting device inquiry...");
		agent.startInquiry(DiscoveryAgent.GIAC, BluetoothPairing);
	       
		try
		{
			synchronized(lock)
			{
	                lock.wait();
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	       
		System.out.println("Device Inquiry Completed. ");
	       
		//print all devices in vecDevicdes
		int deviceCount=vecDevices.size();
	      
		if(deviceCount <= 0)
		{
			System.out.println("No Devices Found .");
		}
		else
		{
			System.out.println("Now");
	            //print bluetooth device addresses and names in the format [ No. address (name) ]
	            //System.out.println("Bluetooth Devices: ");
	            for (int i = 0; i <deviceCount; i++) 
	            {
	                RemoteDevice remoteDevice=(RemoteDevice)vecDevices.elementAt(i);
	                if(name.equalsIgnoreCase(remoteDevice.getFriendlyName(false)))
	                {
	                	RemoteDeviceHelper.authenticate(remoteDevice, "1234");
	    	            System.out.println("Device Authenticated");
	    	            WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER,"SOFTWARE\\File Security\\"+FilePath,"Key1",remoteDevice.getBluetoothAddress());
	    	            
	    	            
	                }
	            }
	        }
	       
	       
	    }//end of pair
	    //methods of DiscoveryListener
	   
	    /**
	     * This call back method will be called for each discovered bluetooth devices.
	     */
	    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod)
	    {
	        //add the device to the vector
	        if(!vecDevices.contains(btDevice)){
	            vecDevices.addElement(btDevice);
	        }
	    }
	 
	    /**
	     * This callback method will be called when the device discovery is
	     * completed.
	     */
	    public void inquiryCompleted(int discType) {
	        synchronized(lock){
	            lock.notify();
	        }
	       
	        switch (discType) {
	            case DiscoveryListener.INQUIRY_COMPLETED :
	                System.out.println("INQUIRY_COMPLETED");
	                break;
	               
	            case DiscoveryListener.INQUIRY_TERMINATED :
	                System.out.println("INQUIRY_TERMINATED");
	                break;
	               
	            case DiscoveryListener.INQUIRY_ERROR :
	                System.out.println("INQUIRY_ERROR");
	                break;
	 
	            default :
	                System.out.println("Unknown Response Code");
	                break;
	        }
	    }//end method

		@Override
		public void serviceSearchCompleted(int arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void servicesDiscovered(int arg0, ServiceRecord[] arg1) {
			// TODO Auto-generated method stub
			
		}
	}//end class
