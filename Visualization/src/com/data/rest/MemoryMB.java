package com.data.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.data.dao.impl.CPUStatsDaoImpl;
import com.data.dao.impl.MEmoryMBDaoImpl;
import com.data.main.dao.CPUStatsDao;


@Path("/MemoryMBStats") 
public class MemoryMB {

	/*realtime*/
	@GET
	@Path("/viewMemBalloon")
	
	public String viewMemBalloon( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		MEmoryMBDaoImpl cpu= new MEmoryMBDaoImpl();
		System.out.println(cpu);
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String mem="balloon";
		String time="real";
		CPUStatsDao stats=new CPUStatsDao();
		System.out.println("Service");
		MEmoryMBDaoImpl dao= new MEmoryMBDaoImpl();	
		String flag = dao.getStats(stats,mem,time);
		response.setContentType("text/javascript");
	    return flag;
	}
	
	@GET
	@Path("/viewMemActive")
	
	public String viewMemActive( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		MEmoryMBDaoImpl cpu= new MEmoryMBDaoImpl();
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String mem="active";
		String time="real";
		CPUStatsDao stats=new CPUStatsDao();
		System.out.println("Service");
		MEmoryMBDaoImpl dao= new MEmoryMBDaoImpl();	
		String flag = dao.getStats(stats,mem,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	
	@GET
	@Path("/viewMemShared")
	
	public String viewMemShared( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		MEmoryMBDaoImpl cpu= new MEmoryMBDaoImpl();
		cpu.connect();
		String mem="shared";
		String time="real";
		CPUStatsDao stats=new CPUStatsDao();
		MEmoryMBDaoImpl dao= new MEmoryMBDaoImpl();	
		String flag = dao.getStats(stats,mem,time);
		response.setContentType("text/javascript");
	   
	    return flag;
	}
	
	@GET
	@Path("/viewMemSwapped")
	
	public String viewMemSwapped( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		MEmoryMBDaoImpl cpu= new MEmoryMBDaoImpl();
		cpu.connect();
		System.out.println("After connect - cpustats service");
		String mem="swapped";
		String time="real";
		CPUStatsDao stats=new CPUStatsDao();
		MEmoryMBDaoImpl dao= new MEmoryMBDaoImpl();	
		String flag = dao.getStats(stats,mem,time);
		response.setContentType("text/javascript");
		return flag;
	}
	
	/*realtime*/
	
	/*day*/
	@GET
	@Path("/viewStatsMemBalloonDay")
	
	public String viewStatsMemBalloonDay( @Context HttpServletRequest request,@Context HttpServletResponse response)throws Exception{
		
		MEmoryMBDaoImpl cpu= new MEmoryMBDaoImpl();
		cpu.connect();	
		String mem="balloon";
		String time="day";
		CPUStatsDao stats=new CPUStatsDao();
		System.out.println("Service");
		MEmoryMBDaoImpl dao= new MEmoryMBDaoImpl();	
		String flag = dao.getStats(stats,mem,time);
		response.setContentType("text/javascript");
	    return flag;
	}
	/*day*/
	/*week*/
	
	@GET
	@Path("/viewStatsMemBalloonWeek")
	
	public String viewStatsMemBalloonWeek( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		MEmoryMBDaoImpl cpu= new MEmoryMBDaoImpl();
		cpu.connect();
		String mem="balloon";
		String time="week";
		CPUStatsDao stats=new CPUStatsDao();
		MEmoryMBDaoImpl dao= new MEmoryMBDaoImpl();	
		String flag = dao.getStats(stats,mem,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	/*week*/
	/*month*/
	@GET
	@Path("/viewStatsMemBalloonMonth")
	
	public String viewStatsMemBalloonMonth( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		MEmoryMBDaoImpl cpu= new MEmoryMBDaoImpl();
		cpu.connect();
		String mem="balloon";
		String time="month";
		CPUStatsDao stats=new CPUStatsDao();
		MEmoryMBDaoImpl dao= new MEmoryMBDaoImpl();	
		String flag = dao.getStats(stats,mem,time);
		response.setContentType("text/javascript");
	   
	    return flag;
	}
	/*month*/
	
	
	//////  HOST
	
	/*realtime*/
	@GET
	@Path("/viewStatsMemHostBalloon")
	
	public String viewStatsMemHostBalloon( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		MEmoryMBDaoImpl cpu= new MEmoryMBDaoImpl();
		cpu.connect();
		String mem="balloon";
		String time="real";
		CPUStatsDao hstats=new CPUStatsDao();
		MEmoryMBDaoImpl dao= new MEmoryMBDaoImpl();	
		String flag = dao.getHStats(hstats,mem,time);
		response.setContentType("text/javascript");
		return flag;
	}
	
	
	
	@GET
	@Path("/viewStatsMemHostActive")
	
	public String viewStatsMemHostActive( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		MEmoryMBDaoImpl cpu= new MEmoryMBDaoImpl();
		cpu.connect();	
		String mem="active";
		String time="real";
		CPUStatsDao stats=new CPUStatsDao();
		
		MEmoryMBDaoImpl dao= new MEmoryMBDaoImpl();	
		String flag = dao.getHStats(stats,mem,time);
		response.setContentType("text/javascript");
		
	    return flag;
	}
	
	@GET
	@Path("/viewStatsMemHostShared")
	
	public String viewStatsMemHostShared( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		MEmoryMBDaoImpl cpu= new MEmoryMBDaoImpl();
		cpu.connect();
		String mem="shared";
		String time="real";
		CPUStatsDao stats=new CPUStatsDao();
		MEmoryMBDaoImpl dao= new MEmoryMBDaoImpl();	
		String flag = dao.getHStats(stats,mem,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	
	@GET
	@Path("/viewStatsMemHostSwapped")
	
	public String viewStatsMemHostSwapped( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		MEmoryMBDaoImpl cpu= new MEmoryMBDaoImpl();
		cpu.connect();
		String mem="swapped";
		String time="real";
		CPUStatsDao hstats=new CPUStatsDao();
		MEmoryMBDaoImpl dao= new MEmoryMBDaoImpl();	
		String flag = dao.getHStats(hstats,mem,time);
		response.setContentType("text/javascript");
	   
	    return flag;
	}
	
	
/*day*/

	@GET
	@Path("/viewStatsMemHostBalloonDay")
	
	public String viewStatsMemHostBalloonDay( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		MEmoryMBDaoImpl cpu= new MEmoryMBDaoImpl();
		cpu.connect();
		String mem="balloon";
		String time="day";
		CPUStatsDao stats=new CPUStatsDao();
		MEmoryMBDaoImpl dao= new MEmoryMBDaoImpl();	
		String flag = dao.getHStats(stats,mem,time);
		response.setContentType("text/javascript");
		return flag;
	}
	
	
	/*week*/
	
	
	@GET
	@Path("/viewStatsMemHostBalloonWeek")
	
	public String viewStatsMemHostBalloonWeek( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		MEmoryMBDaoImpl cpu= new MEmoryMBDaoImpl();
		cpu.connect();	
		String mem="balloon";
		String time="week";
		CPUStatsDao stats=new CPUStatsDao();
	
		MEmoryMBDaoImpl dao= new MEmoryMBDaoImpl();	
		String flag = dao.getHStats(stats,mem,time);
		response.setContentType("text/javascript");
		
	    return flag;
	}
	
	/*month*/
	@GET
	@Path("/viewStatsMemHostBalloonMonth")
	
	public String viewStatsMemHostBalloonMonth( @Context HttpServletRequest request,
		      @Context HttpServletResponse response)throws Exception{
		
		MEmoryMBDaoImpl cpu= new MEmoryMBDaoImpl();
		cpu.connect();
		String mem="balloon";
		String time="month";
		CPUStatsDao stats=new CPUStatsDao();
		MEmoryMBDaoImpl dao= new MEmoryMBDaoImpl();	
		String flag = dao.getHStats(stats,mem,time);
		response.setContentType("text/javascript");
	    
	    return flag;
	}
	
	
	
	/*month*/
	
	
	
	
	
	
	
}
