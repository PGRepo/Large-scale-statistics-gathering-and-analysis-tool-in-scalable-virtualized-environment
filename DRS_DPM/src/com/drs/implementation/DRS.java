package com.drs.implementation;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.security.Provider.Service;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.configuration.file.config;
import com.drs.host.Host;
import com.drs.manager.Manager;
import com.drs.vm.Vm;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;

public class DRS extends Manager {
	
	
	//Initialize variable to set up connection to vcenter
	
	
	public DRS (int typeOfDRS, ServiceInstance si) throws Exception
	{
		super(si);
		if(typeOfDRS == 1)
		{
			startInitialPlacement();
		}
		if(typeOfDRS == 2)
		{
			balanaceLoad();
		}
		
	}
	
	
	public void startInitialPlacement() throws Exception
	{
		
		System.out.println("staring Initial placement");
		System.out.println("Step1: create a vm .....");
		List<Vm> vm_list = getAllVM();
		Vm vm = getLowestUsageVm(vm_list);
		System.out.println("created a clone of vm" + vm.getVMName());
		System.out.println("Step2: Place it at appropriate host .... ");
		List<Host> host_list = getAllHosts();
		Host host = getLowestUsageHost(host_list);
		System.out.println("Host selected for placement is " + host.getHostName());

		if(createVM(vm,host))
		{
			System.out.println("Sucessfully created and placed vm... ");
			
		}
		else
		{
			System.out.println("could not create vm...Exting...");
			System.exit(0);
		}
		
		
		
	}
	
	
	public void balanaceLoad() throws Exception
	{
		System.out.println("starting rebalancing vm to reduce load on host....");
		
		System.out.println("");
		
		Host lowest_usage_host;
		Vm lowest_usage_vm;
		List<Host> host_list = getAllHosts();
		List<Vm> vm_list = getAllVM();
		
		
		for( Host host:host_list)
		{
								if(isOverloaded(host))
								{
									lowest_usage_host = getLowestUsageHost(host_list);
									lowest_usage_vm= getLowestUsageVm(vm_list);
				
													if(lowest_usage_host.getHostName().equalsIgnoreCase(host.getHostName()))
													{
														System.out.println("Host"+ host.getHostName()+ "is having least CPU usage....");
														continue;
													}
													else
													{
														
					
																if(!isOverloadedAfterMigrate(lowest_usage_host, getCpuUsageVm(lowest_usage_vm))) 
																		{			
																								
																		System.out.println("Virtual machine: " +			
																		lowest_usage_vm.getVMName() + " start migrate to new host: " + 
																		lowest_usage_host.getHostName());
																		lowest_usage_vm.migrateToHost(lowest_usage_host);
																		} 
																else if(isOverloadedAfterMigrate(lowest_usage_host,	getCpuUsageVm(lowest_usage_vm))	
																		&& isOverloadedAfterMigrate(lowest_usage_host, 					
																		(Math.abs(getCpuUsageVm(lowest_usage_vm)) * -1)))
																{ 	
																		System.out.println("Simulation migrate not pass");				 
																		System.out.println("Current distination host usage also high");	
																		System.out.println("Power on new host, migrate vm to new host");
																}
																
																else 
																{
																		 
																		System.out.println("Virtual machine : " + 						
																				lowest_usage_vm.getVMName() + " : no need to do migrate\n");
																		
																}

														}
										}
										else
										{
											System.out.println("Host " + host.getHostName() + "is stable so no operation:" );
										}
								
						}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private void getConfigDetails()
	{
		
		
	}
	
	
	
	
				
		
	}
	
	

	
	
	


