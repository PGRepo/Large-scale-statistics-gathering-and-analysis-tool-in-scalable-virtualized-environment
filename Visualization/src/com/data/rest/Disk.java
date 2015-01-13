package com.data.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.data.dao.impl.CPUStatsDaoImpl;
import com.data.dao.impl.DiskDaoImpl;
import com.data.main.dao.CPUStatsDao;

@Path("/DiskIO") 
public class Disk {

	/*realtime*/
	@GET
	@Path("/viewDiskRateRead")
	
	public String viewDiskRateRead( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskDaoImpl cpu= new DiskDaoImpl();
		System.out.println(cpu);
		cpu.connect();
		String rate="read";
		String time="real";
		CPUStatsDao stats=new CPUStatsDao();
		System.out.println("Service");
		DiskDaoImpl dao= new DiskDaoImpl();	
		String flag = dao.getStats(stats,rate,time);
		response.setContentType("text/javascript");
	    return flag;
	}
	
	@GET
	@Path("/viewDiskRateHRead")
	
	public String viewDiskRateHRead( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskDaoImpl cpu= new DiskDaoImpl();
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String rate="read";
		String time="real";
		CPUStatsDao hstats=new CPUStatsDao();
		System.out.println("Service");
		DiskDaoImpl dao= new DiskDaoImpl();	
		String flag = dao.getHStats(hstats,rate,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	@GET
	@Path("/viewDiskRateHWrite")
	
	public String viewDiskRateHWrite( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskDaoImpl cpu= new DiskDaoImpl();
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String rate="write";
		String time="real";
		CPUStatsDao hstats=new CPUStatsDao();
		System.out.println("Service");
		DiskDaoImpl dao= new DiskDaoImpl();	
		String flag = dao.getHStats(hstats,rate,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	@GET
	@Path("/viewDiskRateWrite")
	
	public String viewDiskRateWrite( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskDaoImpl cpu= new DiskDaoImpl();
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String rate="write";
		String time="real";
		CPUStatsDao stats=new CPUStatsDao();
		System.out.println("Service");
		DiskDaoImpl dao= new DiskDaoImpl();	
		String flag = dao.getStats(stats,rate,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	
	
	/*realtime*/
	
	/*day*/
	@GET
	@Path("/viewDiskRateReadDay")
	
	public String viewDiskRateReadDay( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskDaoImpl cpu= new DiskDaoImpl();
		System.out.println(cpu);
		cpu.connect();
		String rate="read";
		String time="day";
		CPUStatsDao stats=new CPUStatsDao();
		System.out.println("Service");
		DiskDaoImpl dao= new DiskDaoImpl();	
		String flag = dao.getStats(stats,rate,time);
		response.setContentType("text/javascript");
	    return flag;
	}
	
	@GET
	@Path("/viewDiskRateHReadDay")
	
	public String viewDiskRateHReadDay( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskDaoImpl cpu= new DiskDaoImpl();
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String rate="read";
		String time="day";
		CPUStatsDao hstats=new CPUStatsDao();
		System.out.println("Service");
		DiskDaoImpl dao= new DiskDaoImpl();	
		String flag = dao.getHStats(hstats,rate,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	@GET
	@Path("/viewDiskRateHWriteDay")
	
	public String viewDiskRateHWriteDay( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskDaoImpl cpu= new DiskDaoImpl();
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String rate="write";
		String time="day";
		CPUStatsDao hstats=new CPUStatsDao();
		System.out.println("Service");
		DiskDaoImpl dao= new DiskDaoImpl();	
		String flag = dao.getHStats(hstats,rate,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	@GET
	@Path("/viewDiskRateWriteDay")
	
	public String viewDiskRateWriteDay( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskDaoImpl cpu= new DiskDaoImpl();
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String rate="write";
		String time="day";
		CPUStatsDao stats=new CPUStatsDao();
		System.out.println("Service");
		DiskDaoImpl dao= new DiskDaoImpl();	
		String flag = dao.getStats(stats,rate,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	
	
	/*day*/
	/*week*/
	@GET
	@Path("/viewDiskRateReadWeek")
	
	public String viewDiskRateReadWeek( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskDaoImpl cpu= new DiskDaoImpl();
		System.out.println(cpu);
		cpu.connect();
		String rate="read";
		String time="week";
		CPUStatsDao stats=new CPUStatsDao();
		System.out.println("Service");
		DiskDaoImpl dao= new DiskDaoImpl();	
		String flag = dao.getStats(stats,rate,time);
		response.setContentType("text/javascript");
	    return flag;
	}
	
	@GET
	@Path("/viewDiskRateHReadWeek")
	
	public String viewDiskRateHReadWeek( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskDaoImpl cpu= new DiskDaoImpl();
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String rate="read";
		String time="week";
		CPUStatsDao hstats=new CPUStatsDao();
		System.out.println("Service");
		DiskDaoImpl dao= new DiskDaoImpl();	
		String flag = dao.getHStats(hstats,rate,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	@GET
	@Path("/viewDiskRateHWriteWeek")
	
	public String viewDiskRateHWriteWeek( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskDaoImpl cpu= new DiskDaoImpl();
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String rate="write";
		String time="week";
		CPUStatsDao hstats=new CPUStatsDao();
		System.out.println("Service");
		DiskDaoImpl dao= new DiskDaoImpl();	
		String flag = dao.getHStats(hstats,rate,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	@GET
	@Path("/viewDiskRateWriteWeek")
	
	public String viewDiskRateWriteWeek( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskDaoImpl cpu= new DiskDaoImpl();
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String rate="write";
		String time="week";
		CPUStatsDao stats=new CPUStatsDao();
		System.out.println("Service");
		DiskDaoImpl dao= new DiskDaoImpl();	
		String flag = dao.getStats(stats,rate,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	
	/*week*/
	/*month*/
	@GET
	@Path("/viewDiskRateReadMonth")
	
	public String viewDiskRateReadMonth( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskDaoImpl cpu= new DiskDaoImpl();
		System.out.println(cpu);
		cpu.connect();
		String rate="read";
		String time="month";
		CPUStatsDao stats=new CPUStatsDao();
		System.out.println("Service");
		DiskDaoImpl dao= new DiskDaoImpl();	
		String flag = dao.getStats(stats,rate,time);
		response.setContentType("text/javascript");
	    return flag;
	}
	
	@GET
	@Path("/viewDiskRateHReadMonth")
	
	public String viewDiskRateHReadMonth( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskDaoImpl cpu= new DiskDaoImpl();
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String rate="read";
		String time="month";
		CPUStatsDao hstats=new CPUStatsDao();
		System.out.println("Service");
		DiskDaoImpl dao= new DiskDaoImpl();	
		String flag = dao.getHStats(hstats,rate,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	@GET
	@Path("/viewDiskRateHWriteMonth")
	
	public String viewDiskRateHWriteMonth( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskDaoImpl cpu= new DiskDaoImpl();
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String rate="write";
		String time="month";
		CPUStatsDao hstats=new CPUStatsDao();
		System.out.println("Service");
		DiskDaoImpl dao= new DiskDaoImpl();	
		String flag = dao.getHStats(hstats,rate,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	@GET
	@Path("/viewDiskRateWriteMonth")
	
	public String viewDiskRateWriteMonth( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		DiskDaoImpl cpu= new DiskDaoImpl();
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String rate="write";
		String time="month";
		CPUStatsDao stats=new CPUStatsDao();
		System.out.println("Service");
		DiskDaoImpl dao= new DiskDaoImpl();	
		String flag = dao.getStats(stats,rate,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	/*month*/
}
