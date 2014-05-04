package org.hl7.fhir.sentinel;

public class Sentinel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("FHIR Sentinel - parameters:");
			System.out.println("  -server [base] - base FHIR URL for server to monitor");
			System.out.println("  -username [name] - if server requires login");
			System.out.println("  -password [word] - if server requires login");
			System.out.println("  -reset - ignore current point and re-process everything");
		} else {
			SentinelWorker worker = new SentinelWorker();
			int i = 0;
			while (i < args.length) {
				String a = args[i];
				i++;
				if (a.equalsIgnoreCase("-server"))
					worker.setServer(args[i]);
				else if (a.equalsIgnoreCase("-username"))
					worker.setUsername(args[i]);
				else if (a.equalsIgnoreCase("-password"))
					worker.setPassword(args[i]);
				else {
					if (a.equalsIgnoreCase("-reset"))
						worker.setReset(true);
					else
						System.out.println("Unknown parameter: '"+a+"'");
					i--; // cause reset doesn't have a follower
				}
				i++;
			}
			try {
	      worker.execute();
      } catch (Exception e) {
	      e.printStackTrace();
      }
		}

	}

}
