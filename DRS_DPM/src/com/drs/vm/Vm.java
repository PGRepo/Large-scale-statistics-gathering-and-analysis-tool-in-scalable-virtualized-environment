package com.drs.vm;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import perfManager.performanceManager;

import com.drs.host.Host;
import com.vmware.vim25.ManagedObjectReference;
import com.vmware.vim25.TaskInfo;
import com.vmware.vim25.VirtualMachineCloneSpec;
import com.vmware.vim25.VirtualMachineMovePriority;
import com.vmware.vim25.VirtualMachineRelocateSpec;
import com.vmware.vim25.mo.ComputeResource;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.Task;
import com.vmware.vim25.mo.VirtualMachine;

public class Vm 
{
	
private VirtualMachine vm;
	
	public Vm(VirtualMachine vm) {
		this.vm = vm;
	}
	
	
	
	//get cpu usge of the vm
	
	public long getCpuUsageMhz(int mins, ServiceInstance si) throws Exception {
		System.out.print(vm.getName());
		return performanceManager.getCpuAvg(vm, mins);
	}
	
	public long getCpuAllocationLimit() {
		return vm.getConfig().getCpuAllocation().getLimit();
	}
	
	public long getCpuReservation() {
		return vm.getConfig().getCpuAllocation().getReservation();
	}
	
	
	public String getVMName() {
		return vm.getName();
	}
	
	public VirtualMachine getVM() {
		return vm;
	}
	
	public boolean migrateToHost(Host newhost) throws Exception {
		HostSystem newHost = newhost.getHost();
		ComputeResource cr = (ComputeResource) newHost.getParent();

		System.out.println("Migrating " + vm.getName() + "to host " + newHost.getName() );
		
		System.out.println("please wait....");

		Task task = vm.migrateVM_Task(cr.getResourcePool(), newHost,
				VirtualMachineMovePriority.highPriority, null);

		if (task.waitForTask() == Task.SUCCESS) {
			System.out.println(vm.getName() + " is migrated to host "
					+ newHost.getName());
			return true;
		} else {
			System.out.println(vm.getName() + " migration failed!");
			TaskInfo info = task.getTaskInfo();
			System.out.println(info.getError().getFault());
		}
		return false;
	}
	
	public boolean cloneVM(Host newhost) throws Exception {
		
		VirtualMachineCloneSpec cloneSpec = new VirtualMachineCloneSpec();
		VirtualMachineRelocateSpec locationSpec = new VirtualMachineRelocateSpec();
		locationSpec.setHost(newhost.getHost().getMOR());
		locationSpec.setPool((ManagedObjectReference)newhost.getHost().getParent().getPropertyByPath("resourcePool"));
		cloneSpec.setLocation(locationSpec);
		cloneSpec.setPowerOn(false);
		cloneSpec.setTemplate(false);

		Task task = vm.cloneVM_Task((Folder) vm.getParent(), vm.getName()
				+ "-Clone", cloneSpec);
		System.out.println("Launching the VM clone task. " + "Please wait ...");

		if (task.waitForTask() == Task.SUCCESS) {
			System.out.println(getVMName() + ": VM got cloned successfully.");
			return true;
		} else {
			System.out.println(getVMName() + ": VM cannot be cloned");
			TaskInfo info = task.getTaskInfo();
			System.out.println(info.getError().getFault());
			return false;
		}
	}
	
	
	
	

}
