import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;


public class DialogBoxFunctions
{
	GenerateKeys GK=new GenerateKeys();
	Cryptography crypt=new Cryptography();
	BluetoothDeviceDiscovery BDD=new BluetoothDeviceDiscovery();
	ContinuousDiscovery CD=new ContinuousDiscovery();
	public int Encrypt(String Path,String Name) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException
	{
		Frame frame=null;
		String value="";
		String address="";
		String add;
		if(Path==null)
		{
			JOptionPane.showMessageDialog(frame, "File not selected.", "Message", JOptionPane.PLAIN_MESSAGE);
		}
		else if(Name==null)
		{
			JOptionPane.showMessageDialog(frame, "Enter valid password.", "Message", JOptionPane.PLAIN_MESSAGE);
		}
		else 
		{
			
			value = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER,"Software\\File Security\\"+Path,"name");
			if(value==null)
			{
				System.out.println("Enter");
				WinRegistry.createKey(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\File Security\\"+Path);
				WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER,"SOFTWARE\\File Security\\"+Path,"Name",Path);
				BluetoothPairing BP=new BluetoothPairing();
				BP.pair(Path,Name);
				address = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER,"Software\\File Security\\"+Path,"Key1");
				GK.key2(Path, address);
				GK.key3(Path, address);				     
				crypt.Encrypt(Path);
				JOptionPane.showMessageDialog(frame, "File is encrypted", "Message", JOptionPane.PLAIN_MESSAGE);
				return 1;
			}
			else
			{
				add=BDD.address(Name);
				address = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER,"Software\\File Security\\"+Path,"Key1");
				if(add.equalsIgnoreCase(address))
				{
					File file=new File(value+".enc");
					if(!file.exists())
					{
						crypt.Encrypt(Path);
						JOptionPane.showMessageDialog(frame, "File is encrypted.", "Message", JOptionPane.PLAIN_MESSAGE);
						return 1;
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "File already in encryption mode.", "Message", JOptionPane.PLAIN_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Incorrect password.", "Message", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
		return 0;
	}
	public void Decrypt(String Path,String Name) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException
	{
		Frame frame=null;
		String value="";
		String address="";
		String add;
		if(Path==null)
		{
			JOptionPane.showMessageDialog(frame, "File not selected.", "Message", JOptionPane.PLAIN_MESSAGE);
		}
		else if(Name==null)
		{
			JOptionPane.showMessageDialog(frame, "Enter valid password.", "Message", JOptionPane.PLAIN_MESSAGE);
		}
		else 
		{
			String Path1=Path.substring(0, Path.length()-4);
			value = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER,"Software\\File Security\\"+Path1,"Name");
			System.out.println("value "+value);
			if(value==null)
			{
				JOptionPane.showMessageDialog(frame, "File is not in encyption mode.", "Message", JOptionPane.PLAIN_MESSAGE);
			}
			else
			{
				add=BDD.address(Name);
			System.out.println("add"+add);
			Path1=Path.substring(0,Path.length()-4);
				address = WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER,"Software\\File Security\\"+Path1,"Key1");
				if(add.equalsIgnoreCase(address))
				{
					System.out.println(Path);
					JOptionPane.showMessageDialog(frame, "File is in decryption mode.", "Message", JOptionPane.PLAIN_MESSAGE);
					CD.logtoCSV(Path,Name);
					
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Incorrect password.", "Message", JOptionPane.PLAIN_MESSAGE);
				}
			}
			
		}
	
	}
}

