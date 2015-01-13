package com.starter.code;

import java.util.Scanner;

import com.configuration.file.config;
import com.dpm.implementation.DPM;
import com.drs.implementation.DRS;
import com.vmware.vim25.mo.ServiceInstance;

public class Start {
	
	
	public static void main(String[] args)
	{
		
		System.out.println("select from below options: ");
		System.out.println("press 1 to start Initial placement ");
		System.out.println("press 2 to start rebalance load ");
		System.out.println("press 3 to implement DPM ");
		Scanner in = new Scanner(System.in);
	    int selection = in.nextInt();
	    
	    if (selection == 1)
	    {
	    	
			
			try {
				DRS drs = new DRS(1, establishConnection());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    else if(selection == 2)
	    {
	    	try {
				DRS drs = new DRS(2, establishConnection());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    else if(selection == 3)
	    {
	    	try {
				DPM dpm = new DPM(establishConnection());
				dpm.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    else 
	    {
	    	System.out.println("invalid input...exiting");
	    }
	   
	 
		
		
		
	}
	
	
	public static ServiceInstance establishConnection()
	{
		config conf= new config();
		ServiceInstance si = conf.setUpConnection();
		if(si == null)
		{
			System.out.println("could not establish connection to vCenter....exiting....");
			System.exit(0);
			
		}
		
		return si;
	}

}
