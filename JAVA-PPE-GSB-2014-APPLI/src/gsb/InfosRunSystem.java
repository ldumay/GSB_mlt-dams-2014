package gsb;

public class InfosRunSystem {
  public static String[] InfosRunSystem() {
    String Space = "# - - - - - - - - - - - - - - - - - - - - - - - - - - - #";
    String SystemArch = System.getProperty("os.arch");
    String SystemName = System.getProperty("os.name");
    String SystemVersion = System.getProperty("os.version");
    String SystemJavaVersion = System.getProperty("java.version");
    String[] TotalInfosSystem = { Space, SystemArch, SystemName, SystemVersion, SystemJavaVersion };
    return TotalInfosSystem;
  }
}
