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
public class BluetoothDeviceDiscovery implements DiscoveryListener
{
	    //object used for waiting
	    private static Object lock=new Object();
	   
	    //vector containing the devices discovered
	    public static Vector vecDevices=new Vector();
	   
	    //main method of the application
	    public static void main(String[] args) throws IOException 
	    {
	        	       
	    }//end main
	    // matching device
	    public int discovery(String name,int flag) throws IOException
	    {
	    	int g;
	    	//create an instance of this class
	        BluetoothDeviceDiscovery bluetoothDeviceDiscovery=new BluetoothDeviceDiscovery();
	       
	        //display local device address and name
	        LocalDevice localDevice = LocalDevice.getLocalDevice();
	        System.out.println("Address: "+localDevice.getBluetoothAddress());
	        System.out.println("Name: "+localDevice.getFriendlyName());
	       
	        //find devices
	        DiscoveryAgent agent = localDevice.getDiscoveryAgent();
	        vecDevices.removeAllElements();
	        System.out.println("Starting device inquiry...");
	        agent.startInquiry(DiscoveryAgent.GIAC, bluetoothDeviceDiscovery);
	       
	      /*  RemoteDevice[] arsenal = LocalDevice.getLocalDevice().getDiscoveryAgent().retrieveDevices(DiscoveryAgent.PREKNOWN);
	        arsenal[0].getBluetoothAddress();
	        arsenal[0].getFriendlyName(true);
	        int deviceCount2=arsenal.length;
	        System.out.println("Bluetooth Devices in Arsenal: ");
	        System.out.println((1)+". "+arsenal[0].getBluetoothAddress()+" ("+arsenal[0].getFriendlyName(true)+")");
	        if(deviceCount2 <= 0){
	            System.out.println("No Paired Devices Found .");
	        }
	        else{
	            //print bluetooth device addresses and names in the format [ No. address (name) ]
	          
	           for (int i = 0; i <deviceCount2; i++) {
	                RemoteDevice remoteDevice2=arsenal[i];

	                

	                if (name.equalsIgnoreCase(remoteDevice2.getFriendlyName(true)))
	                {
	                	g=1;
	                	System.out.println("g "+g);
	                }
	         }
	        }
	        
	        
	        
	        */
	        
	        try {
	            synchronized(lock){
	                lock.wait();
	            }
	        }
	        catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	       
	       
	        System.out.println("Device Inquiry Completed. ");
	       
	        //print all devices in vecDevicdes
	        int deviceCount=vecDevices.size();
	       
	      //  if(deviceCount <= 0){
	        //    System.out.println("No Devices Found .");
	       // }
	        //else{
	            //print bluetooth device addresses and names in the format [ No. address (name) ]
	            System.out.println("Bluetooth Devices in Vec: ");
	            for (int i = 0; i <deviceCount; i++) {
	                RemoteDevice remoteDevice=(RemoteDevice)vecDevices.elementAt(i);

		              System.out.println("Original list: "+(i+1)+". "+remoteDevice.getBluetoothAddress()+" ("+remoteDevice.getFriendlyName(true)+")");

	                if (name.equalsIgnoreCase(remoteDevice.getFriendlyName(true)))
	                {
	                	flag=1;
	                	System.out.println("flag "+flag);
	                }
	              System.out.println("Original list: "+(i+1)+". "+remoteDevice.getBluetoothAddress()+" ("+remoteDevice.getFriendlyName(true)+")");
	            }
	        //}
	        
	        return flag;
	    }
	    public String address(String name) throws IOException
	    {
	    	
	    	//create an instance of this class
	        BluetoothDeviceDiscovery bluetoothDeviceDiscovery=new BluetoothDeviceDiscovery();
	       
	        //display local device address and name
	        LocalDevice localDevice = LocalDevice.getLocalDevice();
	        System.out.println("Address: "+localDevice.getBluetoothAddress());
	        System.out.println("Name: "+localDevice.getFriendlyName());
	       
	        //find devices
	        DiscoveryAgent agent = localDevice.getDiscoveryAgent();
	        vecDevices.removeAllElements();
	        System.out.println("Starting device inquiry...");
	        agent.startInquiry(DiscoveryAgent.GIAC, bluetoothDeviceDiscovery);
	       
	       try {
	           synchronized(lock){
	                lock.wait();
	            }
	        }
	        catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	       
	       
	        System.out.println("Device Inquiry Completed. ");
	       
	        //print all devices in vecDevicdes
	        int deviceCount=vecDevices.size();
	       
	        if(deviceCount <= 0){
	            System.out.println("No Devices Found .");
	        }
	        else{
	            //print bluetooth device addresses and names in the format [ No. address (name) ]
	          //  System.out.println("Bluetooth Devices: ");
	            for (int i = 0; i <deviceCount; i++) {
	                RemoteDevice remoteDevice=(RemoteDevice)vecDevices.elementAt(i);
	                if (name.equalsIgnoreCase(remoteDevice.getFriendlyName(true)))
	                {
	                	return (remoteDevice.getBluetoothAddress());
	                }
	        //        System.out.println((i+1)+". "+remoteDevice.getBluetoothAddress()+" ("+remoteDevice.getFriendlyName(true)+")");
	            }
	            return "null" ;
	        }
	        
	        return "null" ;
	    }
	   
	    //methods of DiscoveryListener
	   
	    /**
	     * This call back method will be called for each discovered bluetooth devices.
	     */
	    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
	    	
	        System.out.println("Device discovered: "+btDevice.getBluetoothAddress());
	    	try {
				if(btDevice.getFriendlyName(true) == "1234")
				{
      try {
				    RemoteDeviceHelper.authenticate(btDevice, "1234");
				    System.out.println("Device Authenticated");
				} catch (IOException CantAuthenticate){}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //add the device to the vector
	        
	       if(!vecDevices.contains(btDevice)){
	            vecDevices.addElement(btDevice);
	        }
	       try {
			RemoteDeviceHelper.removeAuthentication(btDevice);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	 
	            case DiscoveryListener.SERVICE_SEARCH_DEVICE_NOT_REACHABLE :
	            	System.out.println("DEVICE NOT CONNECTED");
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
