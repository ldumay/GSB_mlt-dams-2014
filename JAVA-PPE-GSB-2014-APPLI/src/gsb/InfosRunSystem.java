package gsb;

import java.io.File;

public class InfosRunSystem {
	public static String[] InfosRunSystem() {
		String Space = "# - - - - - - - - - - - - - - - - - - - - - - - - - - - #";
		String SystemArch = System.getProperty("os.arch");
		String SystemName = System.getProperty("os.name");
		String SystemVersion = System.getProperty("os.version");
		String SystemJavaVersion = System.getProperty("java.version");
		
		String TotalInfosSystem[] = {Space, SystemArch, SystemName, SystemVersion, SystemJavaVersion};
		return TotalInfosSystem;
		
		/* 
		// Commande en console
		 
		System.out.println("Systeme : " + SystemName + " " + SystemArch);
		System.out.println("Version : " + SystemVersion);
		System.out.println("Java Version : " + SystemJavaVersion);
	    System.out.println("CPU cores : " +  Runtime.getRuntime().availableProcessors());
	    
	    // Commande en plus
	    
	    // Total amount of free memory available to the JVM
	    System.out.println("Free memory (bytes): " + 
	        Runtime.getRuntime().freeMemory());
	    // This will return Long.MAX_VALUE if there is no preset limit
	    long maxMemory = Runtime.getRuntime().maxMemory();
	    // Maximum amount of memory the JVM will attempt to use
	    System.out.println("Maximum memory (bytes): " + 
	        (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));
	    // Total memory currently in use by the JVM
	    System.out.println("Total memory (bytes): " + 
	        Runtime.getRuntime().totalMemory());
	    // Get a list of all filesystem roots on this system
	    File[] roots = File.listRoots();
	    // For each filesystem root, print some info
	    for (File root : roots) {
	      System.out.println("File system root: " + root.getAbsolutePath());
	      System.out.println("Total space (bytes): " + root.getTotalSpace());
	      System.out.println("Free space (bytes): " + root.getFreeSpace());
	      System.out.println("Usable space (bytes): " + root.getUsableSpace());
	    }
	    */
	  }
}
