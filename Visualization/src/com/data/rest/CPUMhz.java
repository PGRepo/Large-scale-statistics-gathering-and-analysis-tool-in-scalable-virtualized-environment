package com.data.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.data.dao.impl.CPUMhzDaoImpl;
import com.data.dao.impl.CPUStatsDaoImpl;
import com.data.main.dao.CPUStatsDao;

@Path("/CPUStatsMhz") 
public class CPUMhz {
	
	/*realtime*/
	@GET
	@Path("/viewCPUMhz")
	
	public String viewCPUMhz( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUMhzDaoImpl cpu= new CPUMhzDaoImpl();
		System.out.println(cpu);
		cpu.connect();
		String cp="mhz";
		String time="real";
		CPUStatsDao stats=new CPUStatsDao();
		System.out.println("Service");
		CPUMhzDaoImpl dao= new CPUMhzDaoImpl();	
		String flag = dao.getStats(stats,cp,time);
		response.setContentType("text/javascript");
	    return flag;
	}
	
	@POST
	@Path("/viewCPUMhzMin")
	
	public String viewCPUMhzMin( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUMhzDaoImpl cpu= new CPUMhzDaoImpl();
		System.out.println(cpu);
		cpu.connect();
		String cp="mhz";
		String time="real";
		CPUStatsDao stats=new CPUStatsDao();
		System.out.println("Service");
		CPUMhzDaoImpl dao= new CPUMhzDaoImpl();	
		String flag = dao.getStats(stats,cp,time);
		response.setContentType("text/javascript");
	    return flag;
	}
	
	@GET
	@Path("/viewHostCPUMhz")
	
	public String viewHostCPUMhz( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUMhzDaoImpl cpu= new CPUMhzDaoImpl();
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String cp="mhz";
		String time="real";
		CPUStatsDao hstats=new CPUStatsDao();
		System.out.println("Service");
		CPUMhzDaoImpl dao= new CPUMhzDaoImpl();	
		String flag = dao.getHStats(hstats,cp,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	
	
	/*realtime*/
	
	/*day*/
	@GET
	@Path("/viewCPUMhzDay")
	
	public String viewCPUMhzDay( @Context HttpServletRequest request,@Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();	
		String cp="mhz";
		String time="day";
		CPUStatsDao stats=new CPUStatsDao();
		System.out.println("Service");
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getStats(stats,cp,time);
		response.setContentType("text/javascript");
	    return flag;
	}
	
	@GET
	@Path("/viewCPUMhzHostDay")
	
	public String viewCPUMhzHostDay( @Context HttpServletRequest request,@Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();	
		String cp="mhz";
		String time="day";
		CPUStatsDao hstats=new CPUStatsDao();
		System.out.println("Service");
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getHStats(hstats,cp,time);
		response.setContentType("text/javascript");
	    return flag;
	}
	
	
	
	/*day*/
	/*week*/
	
	@GET
	@Path("/viewCPUMhzWeek")
	
	public String viewCPUMhzWeek( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();
		String cp="mhz";
		String time="week";
		CPUStatsDao stats=new CPUStatsDao();
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getStats(stats,cp,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	
	@GET
	@Path("/viewCPUMhzHostWeek")
	
	public String viewCPUMhzHostWeek( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();
		String cp="mhz";
		String time="week";
		CPUStatsDao hstats=new CPUStatsDao();
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getHStats(hstats,cp,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	/*week*/
	/*month*/
	@GET
	@Path("/viewCPUMhzMonth")
	
	public String viewCPUMhzMonth( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();
		String cp="mhz";
		String time="month";
		CPUStatsDao stats=new CPUStatsDao();
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getStats(stats,cp,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	
	@GET
	@Path("/viewCPUMhzHostMonth")
	
	public String viewCPUMhzHostMonth( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();
		String cp="mhz";
		String time="month";
		CPUStatsDao hstats=new CPUStatsDao();
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getHStats(hstats,cp,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	/*month*/
	

}
