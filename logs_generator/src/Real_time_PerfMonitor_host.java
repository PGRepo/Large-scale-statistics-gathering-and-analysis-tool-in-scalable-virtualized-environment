
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.PerfCounterInfo;
import com.vmware.vim25.PerfEntityMetric;
import com.vmware.vim25.PerfEntityMetricBase;
import com.vmware.vim25.PerfMetricId;
import com.vmware.vim25.PerfMetricIntSeries;
import com.vmware.vim25.PerfMetricSeries;
import com.vmware.vim25.PerfMetricSeriesCSV;
import com.vmware.vim25.PerfProviderSummary;
import com.vmware.vim25.PerfQuerySpec;
import com.vmware.vim25.PerfSampleInfo;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.PerformanceManager;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;

public class Real_time_PerfMonitor_vm {

	static final String SERVER_NAME = "130.65.132.190";
	static final String USER_NAME = "Administrator";
	static final String PASSWORD = "12!@qwQW";
	//private static String VMNAME; 
	private static String HostName;
	//private static int SELECTED_COUNTER_ID[] = {2,68}; // Active (mem) in KB (absolute)

	public static void main(String[] args) 
	{
		String url = "https://" + SERVER_NAME + "/sdk/vimService";
		//String vmlogpath = "E:\\workspace\\DisasterRecovery\\logs\\myfilevmlogs.txt";
		//String vmlogpath = "/home/student/Desktop/logs/vmlogs/vmlogs.txt";
		String hostlogpath = "/home/student/Desktop/logs/hostlogs/hostlogs.txt";
		//int[] array_ids = new int[]{2,24,29,143,155,157,288,312,313,233,234};
		//int[] array_ids = new int[]{2,6,12,23,24,25,26,28,29,30,31,32,33,34,35,36,37,48,49,50,51,58,59,69,70,71,72,85,86,89,90,91,92,113,121,122,124,125,126,127,128,130,131,136,137,142,143,144,143,146,147,148,149,156,157,164,171,172,173,174,187,237,238,290,402,404,405,418,422,469,470,478};
		//int[] array_ids_vm = new int[]{2,12,33,37,70,90,85,86,6,24,173,174,171,172,143,146,147};
		int[] array_ids_host = new int[]{2,12,33,37,90,85,86,133,465,6,24,130,131,128,129,148,149,146,147};
		
		System.out.println("Enter Host name:");
		
		Scanner in = new Scanner(System.in);
	    //VMNAME = in.nextLine();
		HostName = in.nextLine();
	    
		
		/*
		 * 2 : Usage (cpu) in Percent (rate)
		 * 6 : Usage in MHz (cpu) in MHz (rate)
		 * 24 : Usage (mem) in Percent (absolute)
		 * 37: Shared (mem) in KB (absolute)
		 * 85 : Swap in rate (mem) in KBps (rate)
		 * 86 : Swap out rate (mem) in KBps (rate)
		 * 121 : Memory Reserved Capacity % (mem) in Percent (absolute)
		 * 122 : Memory Consumed by VMs (mem) in KB (absolute)
		 * 124 : Usage (disk) in KBps (rate)
		 * 125 : Usage (disk) in KBps (rate)
		 * 126 : Usage (disk) in KBps (rate)
		 * 127 : Usage (disk) in KBps (rate)
		 * 128 : Read requests (disk) in Number (delta)
		 * 129 : Write requests (disk) in Number (delta)
		 * 130 : Read rate (disk) in KBps (rate)
		 * 136 : Average read requests per second (disk) in Number (rate)
		 * 137 : Average write requests per second (disk) in Number (rate)
		 * 143 : Usage (net) in KBps (rate)
		 * 146 : Packets received (net) in Number (delta)
		 * 147 : Packets transmitted (net) in Number (delta)
		 * 148 : Data receive rate (net) in KBps (rate)
		 * 149 : Data transmit rate (net) in KBps (rate)
		 * 156 : Heartbeat (sys) in Number (delta)
		 * 157 : Usage (power) in Watt (rate)
		 * 164 : Read rate (storageAdapter) in KBps (rate)
		 * 171 : Average read requests per second (virtualDisk) in Number (rate)
		 * 172 : Average write requests per second (virtualDisk) in Number (rate)
		 * 173 : Read rate (virtualDisk) in KBps (rate)
		 * 174 : Write rate (virtualDisk) in KBps (rate)
		 * 187 : usage (datastore) in KBps (absolute)
		 * 237 : Total (cpu) in MHz (rate)
		 * 238 : Total (mem) in MB (absolute)
		 * 402 : Utilization (cpu) in Percent (rate)
		 * 403 : Utilization (cpu) in Percent (rate)
		 * 404 : Utilization (cpu) in Percent (rate)
		 * 405 : Utilization (cpu) in Percent (rate)
		 * 418 : Swap in (mem) in KB (absolute)
		 * 422 : Swap out (mem) in KB (absolute)
		 * 469 : Data receive rate (net) in KBps (rate)
		 * 470 : Data transmit rate (net) in KBps (rate)
		 * 
		 * 
		 * 
		 * 90 : Balloon (mem) in KB (absolute)
		 * 
		 * 
		 * */
		
		try {
			ServiceInstance si = new ServiceInstance(new URL(url), USER_NAME, PASSWORD, true);
			HostSystem host = (HostSystem) new InventoryNavigator(si.getRootFolder()).searchManagedEntity("HostSystem", HostName);
			//ManagedEntity mes = new InventoryNavigator(si.getRootFolder()).searchManagedEntity("VirtualMachine",VMNAME);
			//VirtualMachine vm = (VirtualMachine) mes;
			
			PerformanceManager perfMgr = si.getPerformanceManager();
			//System.out.println("Counter ID = " + SELECTED_COUNTER_ID);

			PerfProviderSummary summary = perfMgr.queryPerfProviderSummary(host);
			int perfInterval = summary.getRefreshRate();
			//System.out.println("Refresh rate = " + perfInterval);
			
			//PerfMetricId[] queryAvailablePerfMetric = perfMgr.queryAvailablePerfMetric(vm, null, null, perfInterval);
			//ArrayList<PerfMetricId> list = new ArrayList<PerfMetricId>();
		
			
			/*PerfCounterInfo[] perfCounters = perfMgr.getPerfCounter();
			for (int i = 0; i < perfCounters.length; i++) {
				PerfCounterInfo perfCounterInfo = perfCounters[i];
				String perfCounterString = perfCounterInfo.getNameInfo().getLabel() + " (" + perfCounterInfo.getGroupInfo().getKey() + ") in "
						+ perfCounterInfo.getUnitInfo().getLabel() + " (" + perfCounterInfo.getStatsType().toString() + ")";
				System.out.println(perfCounterInfo.getKey() + " : " + perfCounterString);
			}*/

	      /*  HashSet<Integer> hashedArray = new HashSet<Integer>( );
	        for( int entry : SELECTED_COUNTER_ID ) {
	            hashedArray.add( entry );
	        }
	        ArrayList<Integer> tracker = new ArrayList<Integer>();
 
	        for( PerfMetricId entry : queryAvailablePerfMetric ) {
	        	System.out.println("Counter Id: "+ entry.getCounterId());
	            if( hashedArray.contains( entry.getCounterId() ) ) 
	            {
	            	System.out.println("Counter Id: "+ entry.getCounterId());
	            	System.out.println("hasshed array"+ hashedArray);
	            	System.out.println("list "+ list);
	            		if(tracker.contains(entry.getCounterId()))
	            		{
	            			System.out.println("continue");
	            			continue;
	            		}
	            			
	            	list.add(entry);
	            	tracker.add(entry.getCounterId());
	            }
	        }*/
			/*
			for(PerfMetricId p: list)
			{
				System.out.println("counter id "+p.getCounterId());
				
			}*/
			//System.out.println("PerfInterval"+ perfInterval );
			PerfMetricId[] pmis = perfMgr.queryAvailablePerfMetric(
					host, null, null, perfInterval);
			
			
			//PerfMetricId[] pmis = list.toArray(new PerfMetricId[list.size()]);
			//System.out.println("size of pmis"+ pmis.length);
			int maxSample = 1;
			PerfQuerySpec qSpec = new PerfQuerySpec();
			qSpec.setEntity(host.getMOR());
			//qSpec.setFormat("csv");
			qSpec.setMetricId(pmis);
			qSpec.setMaxSample(new Integer(maxSample));
			qSpec.setIntervalId(perfInterval);
			
			while(true)
			{
			PerfEntityMetricBase[] pembs = perfMgr.queryPerf(new PerfQuerySpec[] { qSpec }); //1
			//System.out.println("lengeth of pembs array"+ pembs.length);
			StringBuilder log = new StringBuilder();
			
			List<Integer> visited = new ArrayList<Integer>();
			//String vmlogpath = "/home/student/Desktop/myfilevmlogs.txt";
			
			for (int i = 0; pembs != null && i < pembs.length; i++) 
			{
				
				String entityDesc = pembs[i].getEntity().getType()+ ":" + pembs[i].getEntity().getVal();
				//System.out.println(entityDesc);
				//log.append(pembs[i].getEntity().getVal()+" ");
				try
				{
					String filename= "MyFile.txt";
					FileWriter fw = new FileWriter(filename,true); //the true will append the new data
					fw.write(" "+pembs[i].getEntity().getVal());//appends the string to the file
					fw.close();
				}
				catch(IOException ioe)
				{
					System.err.println("IOException: " + ioe.getMessage());
				}
				//System.out.println("Entity:" + entityDesc);
				PerfEntityMetric pem = (PerfEntityMetric) pembs[i];
				PerfMetricSeries[] vals = pem.getValue();
				/*
				for(int k=0;k<vals.length;k++)
					System.out.println(vals[k].getId().counterId);*/
				
				
				//System.out.println("values length "+ vals.length);
				//System.out.println();
				/*for(PerfMetricSeries ps: vals)
				{
					System.out.println(ps.getId().counterId);
				}*/
				
				PerfSampleInfo[] infos = pem.getSampleInfo();
				
				
				int[] counterIds;
				log.setLength(0);
				log.append(host.getName()+" ");
				String timeStamp= (infos[i].getTimestamp().getTime()).toString();
				DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
				Date date = (Date)formatter.parse(timeStamp);
				//System.out.println(date);        

				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				String formatedDate = cal.get(Calendar.YEAR)+ "/"+ (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) +"-"+ cal.get(Calendar.HOUR_OF_DAY) +":"+cal.get(Calendar.MINUTE)+ ":"+ cal.get(Calendar.SECOND);
				//System.out.println("formatedDate : " + formatedDate);
				
				
				/* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				    Date date = sdf.parse(timeStamp);
				    System.out.println(timeStamp + " to " + date);
				String time_stamp=timeStamp.replaceAll("\\s","");*/
				//System.out.println(time_stamp);
				log.append(formatedDate+" ");
				
				for(int z=0;z<array_ids_host.length;z++)
				 {
					
					//System.out.println("########################3");
					
					/*PerfMetricIntSeries val1 = (PerfMetricIntSeries) vals[j];
					long[] longs = val1.getValue();
					for (int k = 0; k < longs.length; k++) {
						System.out.println(infos[k].getTimestamp().getTime() + " : " + entityDesc + " : " + longs[k]);
						
					}
					System.out.println();
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					
					
					
					for (int j = 0; vals != null && j < vals.length; ++j)
					{
						

					if(vals[j].getId().getCounterId()==array_ids_host[z])
					{
						
						if(visited.contains(vals[j].getId().getCounterId()))
						{
							break;
						}
						
						
						counterIds = new int[]{vals[j].getId().getCounterId()};
						//System.out.println(vals[j].getId().getCounterId());
						
						//printPerfCounters(perfMgr.queryPerfCounter(counterIds));
						//System.out.println("\n");

						if(vals[j] instanceof PerfMetricIntSeries)
						{
							PerfMetricIntSeries val = (PerfMetricIntSeries) vals[j];
							
							long[] longs = val.getValue();
							
							//System.out.println(vals[j].getId().getCounterId());
							//System.out.println("############################################"+ longs.length + "######################################3");
							
							for(int k=0; k<longs.length; k++) 
							{
								//System.out.println(k);
								//System.out.println("Sample time: "+infos[k].getTimestamp().getTime());
								//printPerfCounters(perfMgr.queryPerfCounter(counterIds));
								PerfCounterInfo[] pcis = perfMgr.queryPerfCounter(counterIds);
								//System.out.println("pcis length"+pcis.length);
								
								for(int l=0; pcis!=null && l<pcis.length; l++)
								{
									
									

									//System.out.println("\tKey:" + pcis[i].getKey());
									 //java.util.Date date= new java.util.Date();
									 //System.out.println(new Timestamp(date.getTime()));
									//String perfCounter = pcis[i].getGroupInfo().getKey() + "."+ pcis[i].getNameInfo().getKey() + "."+ pcis[i].getRollupType();
									//System.out.println("PerfCounter:" + perfCounter);
									
									//System.out.println(pcis[l].getNameInfo().getSummary() + " " + longs[k] + "," +pcis[l].getKey());
									//log.append(" "+pcis[l].getNameInfo().getSummary());
									log.append(longs[k] + " ");
									visited.add(vals[j].getId().getCounterId());
									//System.out.println("Level:" + pcis[i].getLevel());
									//System.out.println("StatsType:" + pcis[i].getStatsType());
									//System.out.println("UnitInfo:"+ pcis[i].getUnitInfo().getKey());

								}
								
								//System.out.print("  value  "+longs[k] + " ");
								//log.append(" "+ longs[k]);
								

								//log.setLength(0);
							}
							
							
							//System.out.println("Total:"+longs.length);

						}
						else if(vals[j] instanceof PerfMetricSeriesCSV)
						{ // it is not likely coming here...
							PerfMetricSeriesCSV val = (PerfMetricSeriesCSV) vals[j];
							System.out.println("CSV value:" + val.getValue());
						}
					}
					
			
					
				}
			}
				System.out.println(log);
				
				//System.out.println("###########################################################");

				try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(hostlogpath, true)))) {
				    out.println(log);
				}catch (IOException e) {
				    //exception handling left as an exercise for the reader
				}
			}
			//si.getServerConnection().logout();
			
			//System.out.println("sleeping ...");
			
			Thread.sleep(5000);
			
			
		}
		} catch (InvalidProperty e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RuntimeFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	static void printPerfCounters(PerfCounterInfo[] pcis)
	{
		System.out.println("awdwed");
		System.out.print(pcis[0].getNameInfo().getSummary());
		/*System.out.println("print Perf Counter");
		for(int i=0; pcis!=null && i<pcis.length; i++)
		{
			System.out.println(i);
			//System.out.println("\tKey:" + pcis[i].getKey());
			 //java.util.Date date= new java.util.Date();
			 //System.out.println(new Timestamp(date.getTime()));
			//String perfCounter = pcis[i].getGroupInfo().getKey() + "."+ pcis[i].getNameInfo().getKey() + "."+ pcis[i].getRollupType();
			//System.out.println("PerfCounter:" + perfCounter);
			//System.out.print("Description: "+pcis[i].getNameInfo().getSummary());
			System.out.print(pcis[i].getNameInfo().getSummary());
			//System.out.println("Level:" + pcis[i].getLevel());
			//System.out.println("StatsType:" + pcis[i].getStatsType());
			//System.out.println("UnitInfo:"+ pcis[i].getUnitInfo().getKey());

			
		}
*/	}
		
}
