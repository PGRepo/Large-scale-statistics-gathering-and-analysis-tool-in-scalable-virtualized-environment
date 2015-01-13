package com.dpm.implementation;

import java.util.List;




import com.drs.host.Host;
import com.drs.manager.Manager;
import com.drs.vm.Vm;
import com.vmware.vim25.mo.ServiceInstance;

public class DPM extends  Manager implements Runnable{

	//private static final Logger log = Logger.getLogger(DPM.class);

	public DPM(ServiceInstance si) throws Exception {
		super(si);
		System.out.println("DPM started....");

	}
	public void start() throws Exception  {
		while (true) {
			// find the vhost that cpu load is lower than the threshold
			Host underloadHost = getUnderloadVhost();
			if (underloadHost != null) {
				Host targetVHost = null;
				// find the target vhost that can consolidate the vms on this vhost
				targetVHost = getTargetVHost(underloadHost, getAdjustment(underloadHost));
				if (targetVHost != null) {
					// migrate all the vms on this vhost to the target vhost
					List<Vm> vms = underloadHost.getVMs();
					try {
						for (Vm vm : vms)
							vm.migrateToHost(targetVHost);
					} catch (Exception e) {
						e.printStackTrace();
						//log.warn("migration failed, please go to check your vCenter.");
					}
					// shut down this vhost
					powerOff(underloadHost);
				}
			}
			
			try {
				Thread.sleep(1000 * 3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private boolean powerOff(Host vhost) throws Exception{
		return vhost.powerOffHost();
	}

	private Host getTargetVHost(Host underloadVHostint, int adjustment) throws Exception {
		List<Host> vHosts = getAllHosts();
		for (int i = 0; i < vHosts.size(); ++i) {
			if(!underloadVHostint.getHostName().equals(vHosts.get(i).getHostName())){
				if (!isOverloadedAfterMigrate(vHosts.get(i), adjustment)) {
					return vHosts.get(i);
				}
			}		
		}
		return null;
	}

	private int getAdjustment(Host host) throws Exception {
		int adm = 0;
		List<Vm> list_vm = host.getListOfAllVmUnderHost() ;
		for (Vm vm : list_vm) {
			adm += getCpuUsageVm(vm);
		}
		return adm;
	}

	private Host getUnderloadVhost() throws Exception {
		List<Host> vHosts = getAllHosts();
		for (int i = 0; i < vHosts.size(); ++i) {
			if (this.isUnderloaded((vHosts.get(i)))) {
				return vHosts.get(i);
			}
		}
		return null;
	}
	@Override
	public void run() {
		try {
			start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
