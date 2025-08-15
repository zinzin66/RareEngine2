package RareEngine2.GameUtils;
import java.util.HashMap;
import java.util.ArrayList;

public class StaticStorage {
    private static HashMap<String,Object> storedMap = new HashMap<String,Object>();
	public static void createNewStorageWithName(String name){
		HashMap<String,Object> nh = new HashMap<String,Object>();
		storedMap.put(name,nh);
	}
	public static HashMap<String,Object> getStorageWithName(String name){
		return (HashMap<String,Object>)storedMap.get(name);
	}
	public static boolean storeValueInStorageWithName(String name,String valueName,Object value){
		if(storedMap.containsKey(name)){
			((HashMap<String,Object>)storedMap.get(name)).put(valueName,value);
			return true;
		}else{
			return false;// here false means not sucessfully stored
		}
	}
	public static Object getValueStoredWithName(String storageName,String valueName){
		if(storedMap.containsKey(storageName)&&((HashMap<String,Object>)storedMap.get(storageName)).containsKey(valueName)){
			return ((HashMap<String,Object>)storedMap.get(storageName)).get(valueName);
		}
		return null;
	}
	public static boolean removeValue(String storageName, String valueName) {
		if(storedMap.containsKey(storageName)&&((HashMap<String,Object>)storedMap.get(storageName)).containsKey(valueName)) {
			return ((HashMap<String,Object>)storedMap.get(storageName)).remove(valueName) != null;
		}
		return false;
	}
	public static boolean removeStorage(String storageName) {
		if(storedMap.containsKey(storageName)) {
			return storedMap.remove(storageName) != null;
		}
		return false;
	}
	public static void clearAll() {
		storedMap.clear();
	}
	public static boolean hasStorage(String name) {
		return storedMap.containsKey(name);
	}
	public static boolean doesStorageWithNameContainValue(String storageName,String valueName){
		return storedMap.containsKey(storageName)&&((HashMap<String,Object>)storedMap.get(storageName)).containsKey(valueName);
	}
	public static ArrayList<String> listAllStorageNames(){
		ArrayList<String> set = new ArrayList<>();
		for(String key:storedMap.keySet()){
			set.add(key);
		}
		return set;
	}
	public static ArrayList<String> listAllValueNamesInStorage(String StorageName){
		ArrayList<String> set = new ArrayList<>();
		if(storedMap.containsKey(StorageName)){
			for(String key:((HashMap<String,Object>)storedMap.get(StorageName)).keySet()){
				set.add(key);
			}
		}
		return set;
	}
	public static HashMap<String,Object> getEntireDataStorage(){
		return storedMap;
	}
	public static HashMap<String,Object> getEntireDataStorageCopy(){
		return new HashMap<String,Object>(storedMap);
	}
}
