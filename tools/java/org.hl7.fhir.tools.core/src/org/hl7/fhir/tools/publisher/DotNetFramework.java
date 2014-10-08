package org.hl7.fhir.tools.publisher;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.Logger.LogMessageType;
import org.hl7.fhir.utilities.Utilities;

public class DotNetFramework {

  private static String getDotNetFrameworkPath() {
    try {
      String frameworkPath = WinRegistry.readString(WinRegistry.HKEY_LOCAL_MACHINE, "Software\\Microsoft\\.NetFramework", "InstallRoot");

      if (frameworkPath != null) {
        File fp = new File(frameworkPath);

        File[] frameworks = fp.listFiles();

        if (frameworks != null) {
          String newest = "";

          for (int i = 0; i < frameworks.length; i++)
            if (frameworks[i].getName().startsWith("v"))
              if (frameworks[i].getName().compareTo(newest) > 0)
                newest = frameworks[i].getName();

          if (!newest.equals(""))
            return Utilities.appendSlash(frameworkPath) + newest;
        }
      }
    } catch (Exception exc) {
      // Nothing to do, just return null
    }
    return null;
  }

  public static class DotNetCompileResult {
    public String message;
    public String errors;
    public int exitValue;
  }

  public static DotNetCompileResult compile(String makefile, Logger l)
  {
    if (!System.getProperty("os.name").startsWith("Windows")) {
      l.log("Not on a Windows system; will not attempt to compile the C# reference implementation.", LogMessageType.Warning);
      return null;
    }

    DotNetCompileResult result = new DotNetCompileResult();

    String fwPath = getDotNetFrameworkPath();

    if( fwPath == null )
    {
      l.log("Cannot find .NET framework path, will not attempt to compile the C# reference implementation.", LogMessageType.Warning);
      return null;
    }

    if( !fwPath.contains("v4.") )
    {
      l.log("Found .NET framework, but the publication tool requires a .NET v4.x framework", LogMessageType.Hint);
      return null;
    }
    
    l.log("Using .NET framework in " + fwPath, LogMessageType.Hint);
    
    String build = Utilities.appendSlash(fwPath) + "msbuild.exe" + " " + makefile;
    
    build += " /p:Configuration=Release /verbosity:m";
    
    try
    {
      Process cmdProc = Runtime.getRuntime().exec(build);

      BufferedReader stdoutReader = new BufferedReader(
          new InputStreamReader(cmdProc.getInputStream()));
      String line;
      while ((line = stdoutReader.readLine()) != null) {
        if(result.message == null)
          result.message = line;
        else
          result.message = result.message + Utilities.systemEol() + line;
      }

      BufferedReader stderrReader = new BufferedReader(
          new InputStreamReader(cmdProc.getErrorStream()));
      while ((line = stderrReader.readLine()) != null) {
        if(result.errors == null)
          result.errors = line;
        else
          result.errors = result.errors + Utilities.systemEol() + line;

      }

      result.exitValue = cmdProc.exitValue();
    }
    catch(Exception e)
    {
      // Some low-level compilation error
      l.log("Compilation failed due to dotnet tooling errors:" + Utilities.systemEol() + e.getMessage(), LogMessageType.Error);
      return null;
    }

    return result;
  }
}
