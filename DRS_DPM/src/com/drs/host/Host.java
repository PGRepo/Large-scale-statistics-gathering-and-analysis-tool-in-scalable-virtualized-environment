package com.drs.host;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import perfManager.performanceManager;

import com.drs.vm.Vm;
import com.vmware.vim25.HostCpuInfo;
import com.vmware.vim25.HostSystemPowerState;
import com.vmware.vim25.TaskInfo;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.Task;
import com.vmware.vim25.mo.VirtualMachine;

public class Host {
	
	
	private HostSystem host;
	private List<Vm> vms;

	public Host(HostSystem host) throws Exception {
		this.host = host;
		getListOfAllVmUnderHost();
	}	
	
	public long getCpuUsageMhz(int mins) throws Exception {
		System.out.print(host.getName());
		System.out.println();
		return performanceManager.getCpuAvg(host, mins);
	}
	
	
	
	public long getTotalCpuMhz() {
		HostCpuInfo cpuInfo = host.getHardware().getCpuInfo();
		return cpuInfo.getHz() * cpuInfo.getNumCpuCores() / 1024 / 1024;
	}
	
	
	
	public boolean powerOffHost() throws Exception {
		Task task = host.shutdownHost_Task(true);
		
		if (task.waitForTask() == Task.SUCCESS) {
			System.out.println(host.getName() + " is powered off.");
			return true;
		} else {
			System.out.println(host.getName() + " power off failed!");
			TaskInfo info = task.getTaskInfo();
			System.out.println(info.getError().getFault());
			return false;
		}
	}
	

	
	public String getHostName() {
		return host.getName();
	}	
	
	public List<Vm> getVMs() throws Exception {
		getListOfAllVmUnderHost();
		return vms;
	}
	
	public HostSystem getHost() {
		return host;
	}
	
	public List<Vm> getListOfAllVmUnderHost() throws Exception {
		vms = new ArrayList<Vm>();
		
		ManagedEntity[] mes = new InventoryNavigator(host)
				.searchManagedEntities("VirtualMachine");
		if (mes == null) return null;	
		
		for (int i = 0; i < mes.length; i++) {
			vms.add(new Vm((VirtualMachine) mes[i]));
		}
		return vms;
		
	}
	
	
	/*public List<HostSystem> getAllHost(ServiceInstance _service_instance)
	{
		ManagedEntity[] hostmanagedEntities;
		List< HostSystem> listOfHost = new ArrayList<HostSystem>();
		
		try {
			hostmanagedEntities = new InventoryNavigator(
					_service_instance.getRootFolder()).searchManagedEntities("HostSystem");
			HostSystem host = null;
			for (ManagedEntity managedEntity : hostmanagedEntities) 
			{
				System.out.println();
				host = (HostSystem)managedEntity;
				if(host !=null)
					{
						listOfHost.add(host);
						System.out.println("Host name: '" + host.getName() + "'");
					}
			}
			
						
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOfHost;
		
	}*/

}
