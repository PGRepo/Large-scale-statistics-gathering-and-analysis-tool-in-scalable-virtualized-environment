package com.data.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.data.dao.impl.CPUStatsDaoImpl;
import com.data.dao.impl.DiskNumDaoImpl;
import com.data.main.dao.CPUStatsDao;

@Path("/DiskNum") 
public class DiskNum {
/*realtime*/
	
	@GET
	@Path("/viewDiskNum")
	
	public String viewDiskNum( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskNumDaoImpl cpu= new DiskNumDaoImpl();
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String cp="ms";
		String time="real";
		CPUStatsDao hstats=new CPUStatsDao();
		System.out.println("Service");
		DiskNumDaoImpl dao= new DiskNumDaoImpl();	
		String flag = dao.getHStats(hstats,cp,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	
	
	/*realtime*/
	
	/*day*/
	
	@GET
	@Path("/viewDiskNumDay")
	
	public String viewDiskNumDay( @Context HttpServletRequest request,@Context HttpServletResponse response)throws Exception{
		
		DiskNumDaoImpl cpu= new DiskNumDaoImpl();
		cpu.connect();	
		String cp="ms";
		String time="day";
		CPUStatsDao hstats=new CPUStatsDao();
		System.out.println("Service");
		DiskNumDaoImpl dao= new DiskNumDaoImpl();	
		String flag = dao.getHStats(hstats,cp,time);
		response.setContentType("text/javascript");
	    return flag;
	}
	
	
	
	/*day*/
	/*week*/
	
	
	
	@GET
	@Path("/viewDiskNumWeek")
	
	public String viewDiskNumWeek( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskNumDaoImpl cpu= new DiskNumDaoImpl();
		cpu.connect();
		String cp="ms";
		String time="week";
		CPUStatsDao hstats=new CPUStatsDao();
		DiskNumDaoImpl dao= new DiskNumDaoImpl();	
		String flag = dao.getHStats(hstats,cp,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	/*week*/
	/*month*/
	
	
	@GET
	@Path("/viewDiskNumMonth")
	
	public String viewDiskNumMonth( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskNumDaoImpl cpu= new DiskNumDaoImpl();
		cpu.connect();
		String cp="ms";
		String time="month";
		CPUStatsDao hstats=new CPUStatsDao();
		DiskNumDaoImpl dao= new DiskNumDaoImpl();	
		String flag = dao.getHStats(hstats,cp,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	/*month*/
	

}
