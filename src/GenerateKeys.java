import java.lang.reflect.InvocationTargetException;


public class GenerateKeys
{
	static GenerateKeys GK= new GenerateKeys();
	public static void main(String args[]) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
	GK.keys("Filename","123456789");
	}
	public void keys(String Filename,String address) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		key1(Filename,address);
		key2(Filename,address);
		key3(Filename,address);		
	}
	public void key1(String Filename,String address) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		String key1 = address;
		WinRegistry.createKey(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\File Security\\"+Filename);
		WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER,"SOFTWARE\\File Security\\"+Filename,"Key1",key1);
	}
	public void key2(String Filename,String address) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		String key2;
		int Min=10000;
		int Max=99999;
		int ran=Min + (int)(Math.random() * ((Max - Min) + 1));
		key2=address+ran;
		WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER,"SOFTWARE\\File Security\\"+Filename,"Key2",key2);
	}
	public void key3(String Filename,String address) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		String key3;
		int Min=10000;
		int Max=99999;
		int ran=Min + (int)(Math.random() * ((Max - Min) + 1));
		key3=ran+address;
		WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER,"SOFTWARE\\File Security\\"+Filename,"Key3",key3);
	}
}

