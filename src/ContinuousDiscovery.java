import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import javax.bluetooth.UUID;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.RemoteDevice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

import com.intel.bluetooth.RemoteDeviceHelper;
public class ContinuousDiscovery 
{
	private final static ScheduledExecutorService scheduler =  Executors.newScheduledThreadPool(1);

	public static void main(String args[])
	{
		//logtoCSV("D:\\abc.txt.enc","Xperia miro");
	}
    public static void logtoCSV(final String FilePath,final String key) 
    {
        final Runnable logger= new Runnable()
        {
        	//Instance of Rinjdael class
        	Rinjdael rin=new Rinjdael();
        	Cryptography crypt=new Cryptography();
			@Override
			public void run() 
			{
				//	Instance of BluetoothDeviceDiscovery class				
				BluetoothDeviceDiscovery BDD=new BluetoothDeviceDiscovery();
			
				try 
				{
					int f=BDD.discovery(key,0);
                    rd.random();				
					System.out.println("f"+f);
					if(f==0)
					{
						int l=FilePath.length();
						String sub=FilePath.substring(l-4, l);
						System.out.println(sub);
						String cmp=".enc";
						if(sub!=cmp)
						{
							String FilePath1=FilePath.substring(0, FilePath.length()-4);
							System.out.println("nw");
							File file=new File(FilePath);
							if(!file.exists())
							{
							crypt.Encrypt(FilePath1);
							}
						}
					}
					else if(f==1)
					{
						File file=new File(FilePath.substring(0, FilePath.length()-4));
						if(!file.exists())
						{
							System.out.println("pp");
							crypt.Decrypt(FilePath);
							
						}
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
        	
        };
        final ScheduledFuture<?> loggerHandle =
        	scheduler.scheduleAtFixedRate(logger, 0, 20, SECONDS );
        //Incase you want to kill this after some time like 24 hours
        	scheduler.schedule(new Runnable()
        	{
        			public void run() { loggerHandle.cancel(true); }
        	}, 24, HOURS );
    }
    
 }