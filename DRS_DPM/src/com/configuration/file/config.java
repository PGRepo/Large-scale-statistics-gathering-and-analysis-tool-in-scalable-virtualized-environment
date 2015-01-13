package com.configuration.file;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import com.vmware.vim25.mo.ServiceInstance;

public class config {
	
	public final static String UserName = "administrator";
	public final static String Password = "12!@qwQW";
	public final static String VcenterUrl = "https://130.65.132.190/sdk";

	public final static String[] PerfCounters = { 
		"cpu.usage.average", 
		//"cpu.usagemhz.average",
		"mem.usage.average", 
		//"disk.usage.average", 
		"disk.read.average", "disk.write.average",
		//"datastore.datastoreReadBytes.latest",
		"virtualDisk.readOIO.latest", "virtualDisk.writeOIO.latest" };
	
	public ServiceInstance setUpConnection()
	{
	
	try {
		URL url_vcenter_for_vm = new URL(VcenterUrl);
		ServiceInstance si = new ServiceInstance(url_vcenter_for_vm, UserName, Password, true);
		System.out.println("Connection to Vcenter established.....");
		return si;
		
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	
	}

}
