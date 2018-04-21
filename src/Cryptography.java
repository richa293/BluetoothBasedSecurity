import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class Cryptography
{
	//create instance of class Rinjdael
	Rinjdael rin=new Rinjdael();
	GenerateKeys genKey=new GenerateKeys();
	public void Encrypt(String FilePath) throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		//Encrypt file  for the first time
		String key1=WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER,"Software\\File Security\\"+FilePath,"Key1");
		System.out.println("key1"+key1);
		rin.encryptFile(FilePath, key1);
		//Encrypt file  for the second time
		String key2=WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER,"Software\\File Security\\"+FilePath,"Key2");
		String FilePath1=FilePath+".enc";
		rin.encryptFile1(FilePath1, key2);
		
		//Encrypt file  for the third time
		String key3=WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER,"Software\\File Security\\"+FilePath,"Key3");
		rin.encryptFile1(FilePath1, key3); 
	}
	public void Decrypt(String FilePath) throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		//Decrypt file  for the first time
		String FilePath1=FilePath.substring(0, FilePath.length()-4);
		String key1= WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER,"Software\\File Security\\"+FilePath1,"Key1");
		System.out.println("key 1"+key1);
		String key3= WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER,"Software\\File Security\\"+FilePath1,"Key3");
		System.out.println(key3);
		rin.decryptFile(FilePath, key3);
		System.out.println(FilePath);
		//Decrypt file  for the second time
		String key2= WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER,"Software\\File Security\\"+FilePath1,"Key2");
		System.out.println(key2);
		rin.decryptFile1(FilePath1, key2);
		//Decrypt file  for the third time
		System.out.println("FilePath"+FilePath);
		rin.decryptFile1(FilePath1, key1); 
	}

}
