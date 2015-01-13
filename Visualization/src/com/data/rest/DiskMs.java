package com.data.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.data.dao.impl.CPUStatsDaoImpl;
import com.data.dao.impl.DiskMsDaoImpl;
import com.data.main.dao.CPUStatsDao;

@Path("/DiskMs") 
public class DiskMs {
	/*realtime*/
	
	@GET
	@Path("/viewDiskMs")
	
	public String viewDiskMs( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskMsDaoImpl cpu= new DiskMsDaoImpl();
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String cp="ms";
		String time="real";
		CPUStatsDao hstats=new CPUStatsDao();
		System.out.println("Service");
		DiskMsDaoImpl dao= new DiskMsDaoImpl();	
		String flag = dao.getHStats(hstats,cp,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	
	
	/*realtime*/
	
	/*day*/
	
	@GET
	@Path("/viewDiskMsDay")
	
	public String viewDiskMsDay( @Context HttpServletRequest request,@Context HttpServletResponse response)throws Exception{
		
		DiskMsDaoImpl cpu= new DiskMsDaoImpl();
		cpu.connect();	
		String cp="ms";
		String time="day";
		CPUStatsDao hstats=new CPUStatsDao();
		System.out.println("Service");
		DiskMsDaoImpl dao= new DiskMsDaoImpl();	
		String flag = dao.getHStats(hstats,cp,time);
		response.setContentType("text/javascript");
	    return flag;
	}
	
	
	
	/*day*/
	/*week*/
	
	
	
	@GET
	@Path("/viewDiskMsWeek")
	
	public String viewDiskMsWeek( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskMsDaoImpl cpu= new DiskMsDaoImpl();
		cpu.connect();
		String cp="ms";
		String time="week";
		CPUStatsDao hstats=new CPUStatsDao();
		DiskMsDaoImpl dao= new DiskMsDaoImpl();	
		String flag = dao.getHStats(hstats,cp,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	/*week*/
	/*month*/
	
	
	@GET
	@Path("/viewDiskMsMonth")
	
	public String viewDiskMsMonth( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskMsDaoImpl cpu= new DiskMsDaoImpl();
		cpu.connect();
		String cp="ms";
		String time="month";
		CPUStatsDao hstats=new CPUStatsDao();
		DiskMsDaoImpl dao= new DiskMsDaoImpl();	
		String flag = dao.getHStats(hstats,cp,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	/*month*/
	

}
