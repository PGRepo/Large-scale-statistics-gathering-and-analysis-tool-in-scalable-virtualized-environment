package com.drs.manager;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;










import perfManager.performanceManager;

import com.drs.host.Host;
import com.drs.vm.Vm;
import com.vmware.vim25.HostSystemPowerState;
import com.vmware.vim25.VirtualMachinePowerState;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;

public class Manager
{
	
	protected ServiceInstance si;
	private int high_threshold = 70;
	private int low_threshold = 30;
	private int overloadLasts = 5;
	private int underloadLasts = 10;
	
	public Manager(ServiceInstance si) throws Exception {
		this.si = si;
	}
	
	
	/*public List<HostSystem> getAllHost()
	{
		ManagedEntity[] hostmanagedEntities;
		List< HostSystem> listOfHost = new ArrayList<HostSystem>();
		
		try {
			hostmanagedEntities = new InventoryNavigator(
					si.getRootFolder()).searchManagedEntities("HostSystem");
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
		
	}
	*/
	
	/*public synchronized List<VirtualMachine> getAllVM()
	{
		ServiceInstance serviceinstance = si;
		Folder rootFolder = serviceinstance.getRootFolder();
		ManagedEntity[] virtual_machine_me;
		List<VirtualMachine> listOfVM = new ArrayList<VirtualMachine>();
		try 
		{
			
			virtual_machine_me = new InventoryNavigator(rootFolder).searchManagedEntities("VirtualMachine");
		
			if(virtual_machine_me==null || virtual_machine_me.length ==0)
			{
				System.out.println("no vm found....");
				return listOfVM;
			}
			else
			{
				for(ManagedEntity me: virtual_machine_me)
				{
					VirtualMachine vm = (VirtualMachine) me;
					listOfVM.add(vm);
					//System.out.println(vm.getName());
					
				}
				
			}
		
		}
		catch ( RemoteException e) 
		{
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return listOfVM;
			
			
			
	}
	*/
	
	
	protected List<Host> getAllHosts() throws Exception {
		List<Host> vHosts = new ArrayList<Host>();
		Folder vCenterFolder = si.getRootFolder();
		ManagedEntity[] mes = new InventoryNavigator(vCenterFolder)
				.searchManagedEntities("HostSystem");
		if (mes.length != 0) {
			for (int i = 0; i < mes.length; i++) {
				HostSystem host = (HostSystem) mes[i];
				if (host.getRuntime().getPowerState() == HostSystemPowerState.poweredOn)
					vHosts.add(new Host(host));
			}
		} else {
			System.out.println("No host connected.");
		}
		return vHosts;
	}
	
	
	
	
	
	protected List<Vm> getAllVM() throws Exception {
		List<Vm> vm_list = new ArrayList<Vm>();
		Folder vCenterFolder = si.getRootFolder();
		ManagedEntity[] mes = new InventoryNavigator(vCenterFolder)
				.searchManagedEntities("VirtualMachine");
		if (mes.length != 0) {
			for (int i = 0; i < mes.length; i++) {
				VirtualMachine vm = (VirtualMachine) mes[i];
				if (vm.getRuntime().getPowerState() == VirtualMachinePowerState.poweredOn)
					{
					vm_list.add(new Vm(vm));
					
					}
			}
		} else {
			System.out.println("No host connected.");
		}
		
		return vm_list;
	}
	
	
	
	
	
	protected boolean isOverloaded (Host host) throws Exception {
		performanceManager.setUp(si);
		//long total = host.getTotalCpuMhz();
		long usage = host.getCpuUsageMhz(overloadLasts);
		return(usage/10.0 > high_threshold);
		//return (usage * 100.0 / total) > high_threshold;
	}
	
	
	
	protected boolean isUnderloaded (Host host) throws Exception {
		performanceManager.setUp(si);
		long total = host.getTotalCpuMhz();
		long usage = host.getCpuUsageMhz(underloadLasts);
		//return (usage * 0.0 / total) < low_threshold;
		return(usage/10.0 < low_threshold);
	}
	
	protected boolean isOverloadedAfterMigrate (Host host, long adjustment) throws Exception {
		performanceManager.setUp(si);
		//long total = host.getTotalCpuMhz();
		long usage = host.getCpuUsageMhz(overloadLasts) + adjustment;
		return(usage/100.0 > high_threshold);
		//return (usage * 100.0 / total) > high_threshold;
	}
	
/*	protected List<VHost> tempHost (VHost host) throws Exception {
		List<VHost> vHosts = getPoweredOnHosts();
		List<VHost> temp = new ArrayList<VHost>();
		for(int i=0; i<vHosts.size(); i++) {
			if(host!=vHosts.get(i)) {
				temp.add(vHosts.get(i));
			}
		}
		return temp;
	}*/
	
	protected Host getLowestUsageHost(List<Host> hosts) throws Exception {
		performanceManager.setUp(si);
		Host host = null;
		int i = 0;
		long min = Long.MAX_VALUE;
		while(i < hosts.size()) {
			if(hosts.get(i).getCpuUsageMhz(5) < min) {
				min = hosts.get(i).getCpuUsageMhz(5);
				host = hosts.get(i);
			}
			i++;
		}
		return host;
	}
	
	protected long getCpuUsageVm(Vm vm) throws Exception
	{
		return vm.getCpuUsageMhz(5, si);
	}
	
	protected Vm getLowestUsageVm(List<Vm> vms) throws Exception {
		Vm vm = null;
		int i = 0;
		long min = Long.MAX_VALUE;
		performanceManager.setUp(si);
		while(i < vms.size()) {
			if(vms.get(i).getCpuUsageMhz(5, si) < min) {
				min = vms.get(i).getCpuUsageMhz(5, si);
				vm = vms.get(i);
			}
			i++;
		}
		return vm;
	}
	
	
	protected boolean createVM(Vm vm , Host host)
	{
		
		try {
			return(vm.cloneVM(host));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		
		
	}
	/*public HostSystem getHostByName(String name) throws Exception {
		Folder rootFolder = si.getRootFolder();
		HostSystem host = (HostSystem) new InventoryNavigator(rootFolder).searchManagedEntity("HostSystem", name);
		if(host==null)
			return null;
		else
			return host;
	}*/
	
	
	
	
	

}
