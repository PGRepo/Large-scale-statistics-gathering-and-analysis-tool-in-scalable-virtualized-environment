package com.data.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.data.dao.impl.CPUStatsDaoImpl;
import com.data.main.dao.CPUStatsDao;
import com.sun.jersey.api.view.Viewable;

@Path("/VMCPUStats") 

public class VMCPUStats {

	
	/*realtime*/
	@GET
	@Path("/viewStatsVMready")
	
	public String viewStatsVMready( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		System.out.println(cpu);
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String ready="Yes";
		String time="real";
		CPUStatsDao stats=new CPUStatsDao();
		System.out.println("Service");
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getStats(stats,ready,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	
	@GET
	@Path("/viewStatsVM")
	
	public String viewStatsVM( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String ready="No";
		String time="real";
		CPUStatsDao stats=new CPUStatsDao();
		System.out.println("Service");
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getStats(stats,ready,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	
	@GET
	@Path("/viewStatsHostReady")
	
	public String viewStatsHready( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();
		String ready="Yes";
		String time="real";
		CPUStatsDao hstats=new CPUStatsDao();
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getHStats(hstats,ready,time);
		response.setContentType("text/javascript");
	   
	    return flag;
	}
	
	@GET
	@Path("/viewStatsHost")
	
	public String viewStatsHost( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String ready="No";
		String time="real";
		CPUStatsDao stats=new CPUStatsDao();
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getHStats(stats,ready,time);
		response.setContentType("text/javascript");
		return flag;
	}
	
	/*realtime*/
	
	/*day*/
	@GET
	@Path("/viewStatsVMreadyDay")
	
	public String viewStatsVMreadyDay( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();	
		String ready="Yes";
		String time="day";
		CPUStatsDao stats=new CPUStatsDao();
		System.out.println("Service");
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getStats(stats,ready,time);
		response.setContentType("text/javascript");
		
	    return flag;
	}
	
	@GET
	@Path("/viewStatsVMDay")
	
	public String viewStatsVMDay( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();
		String ready="No";
		String time="day";
		CPUStatsDao stats=new CPUStatsDao();
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getStats(stats,ready,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	
	@GET
	@Path("/viewStatsHostReadyDay")
	
	public String viewStatsHreadyDay( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();
		String ready="Yes";
		String time="day";
		CPUStatsDao hstats=new CPUStatsDao();
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getHStats(hstats,ready,time);
		response.setContentType("text/javascript");
	   
	    return flag;
	}
	
	@GET
	@Path("/viewStatsHostDay")
	
	public String viewStatsHostDay( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();
		String ready="No";
		String time="day";
		CPUStatsDao stats=new CPUStatsDao();
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getHStats(stats,ready,time);
		response.setContentType("text/javascript");
		return flag;
	}
	
	
	/*day*/
	
	/*week*/
	@GET
	@Path("/viewStatsVMreadyWeek")
	
	public String viewStatsVMreadyWeek( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();	
		String ready="Yes";
		String time="week";
		CPUStatsDao stats=new CPUStatsDao();
		System.out.println("Service");
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getStats(stats,ready,time);
		response.setContentType("text/javascript");
		
	    return flag;
	}
	
	@GET
	@Path("/viewStatsVMWeek")
	
	public String viewStatsVMWeek( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();
		String ready="No";
		String time="week";
		CPUStatsDao stats=new CPUStatsDao();
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getStats(stats,ready,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	
	@GET
	@Path("/viewStatsHostReadyWeek")
	
	public String viewStatsHreadyDayWeek( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();
		String ready="Yes";
		String time="week";
		CPUStatsDao hstats=new CPUStatsDao();
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getHStats(hstats,ready,time);
		response.setContentType("text/javascript");
	   
	    return flag;
	}
	
	@GET
	@Path("/viewStatsHostWeek")
	
	public String viewStatsHostDayWeek( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();
		String ready="No";
		String time="week";
		CPUStatsDao stats=new CPUStatsDao();
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getHStats(stats,ready,time);
		response.setContentType("text/javascript");
		return flag;
	}
	
	
	/*week*/
	
	/*month*/
	@GET
	@Path("/viewStatsVMreadyMonth")
	
	public String viewStatsVMreadyMonth( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();	
		String ready="Yes";
		String time="month";
		CPUStatsDao stats=new CPUStatsDao();
	
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getStats(stats,ready,time);
		response.setContentType("text/javascript");
		
	    return flag;
	}
	
	@GET
	@Path("/viewStatsVMMonth")
	
	public String viewStatsVMMonth( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();
		String ready="No";
		String time="month";
		CPUStatsDao stats=new CPUStatsDao();
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getStats(stats,ready,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	
	@GET
	@Path("/viewStatsHostReadyMonth")
	
	public String viewStatsHreadyDayMonth( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();
		String ready="Yes";
		String time="month";
		CPUStatsDao hstats=new CPUStatsDao();
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getHStats(hstats,ready,time);
		response.setContentType("text/javascript");
	   
	    return flag;
	}
	
	@GET
	@Path("/viewStatsHostMonth")
	
	public String viewStatsHostDayMonth( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		CPUStatsDaoImpl cpu= new CPUStatsDaoImpl();
		cpu.connect();
		String ready="No";
		String time="month";
		CPUStatsDao hstats=new CPUStatsDao();
		CPUStatsDaoImpl dao= new CPUStatsDaoImpl();	
		String flag = dao.getHStats(hstats,ready,time);
		response.setContentType("text/javascript");
		return flag;
	}
	
	/*month*/
	
	
}
