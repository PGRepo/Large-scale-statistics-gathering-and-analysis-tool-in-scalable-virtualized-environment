
import java.net.InetAddress;


import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand.OutputType;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.mysql.jdbc.PreparedStatement;


public class test 
{
	private static DB db;
	

	private static Connection conn;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String AH_URL = "jdbc:mysql://localhost:3310/catalog";
	private static final String AH_USER = "root";
    private static final String AH_PASSWORD = "pratik";


	public static Connection getMysqlConnection() 
	{
		if (conn == null)
		{
			try {
				Class.forName(DRIVER);
				
				conn = DriverManager.getConnection(AH_URL,AH_USER,AH_PASSWORD);
		
				
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	

	public static String getAggregateData() throws UnknownHostException, ParseException {
		String vm_name=null;
		String time_stamp= null;
		String cpu_percentage_usage= null;
		String cpu_percantage_ready= null;
		String active_memory= null;
		String shared_memory= null;
		String swapped_memory=null;
		String balloon_memory= null;
		String memory_swap_in_rate= null;
		String memory_swap_out_rate= null;
		String cpu_usage_Mhz= null;
		String memory_percentage_usage= null;
		String rate_read_data_from_vd= null;
		String rate_write_data_to_vd=null;
		String avg_read_per_sec_vd= null;
		String avg_write_per_sec_vd= null;
		String network_utilization= null;
		String packets_received= null;
		String packets_transmitted= null;
			
		
		
		MongoClient client = new MongoClient("localhost",27017);
		db = client.getDB("283_project_data_stats");
		DBCollection coll = db.getCollection("vm_logs");
	//	List<Student> students = new ArrayList<Student>();
	BasicDBObject query = new BasicDBObject("flag", new BasicDBObject("$exists",false));
		DBCursor cursor = coll.find(query);
		try {
			
		   while(cursor.hasNext()) {
			  
			 //  System.out.println(cursor.next());
						 
				 
			  
			   DBObject dbo = cursor.next();
			   ObjectId _id = (ObjectId)dbo.get( "_id" );
			   System.out.println(_id);
			   
			   
			//  BasicDBList studentsList = (BasicDBList) dbo.get("students");
			   vm_name = (String) dbo.get("vm_name");
			   System.out.println("name"+vm_name);
			   time_stamp = (String) dbo.get("time_stamp");
			   System.out.println("time stamp"+ time_stamp);
			   cpu_percentage_usage = (String) dbo.get("cpu_usage_percentage");
			   System.out.println("########"+cpu_percentage_usage);
			   cpu_percantage_ready = (String) dbo.get("cpu_ready_percentage");
			   System.out.println(cpu_percantage_ready);
			   active_memory = (String) dbo.get("active_memory");
			   shared_memory = (String) dbo.get("shared_memory");
			   swapped_memory = (String) dbo.get("swapped_memory");
			   balloon_memory = (String) dbo.get("baloon_memory");
			   memory_swap_in_rate = (String) dbo.get("memory_swap_in_rate");
			   memory_swap_out_rate = (String) dbo.get("memory_swap_out_rate");
			   cpu_usage_Mhz = (String) dbo.get("cpu_usage_mhz");
			   memory_percentage_usage = (String) dbo.get("memory_usage_percentage");
			   rate_read_data_from_vd = (String) dbo.get("read_rate_data_from_vd");
			   rate_write_data_to_vd = (String) dbo.get("write_rate_data_to_vd");
			   avg_read_per_sec_vd = (String) dbo.get("avg_read_per_sec_vd");
			   avg_write_per_sec_vd = (String) dbo.get("avg_write_per_sec_vd");
			   network_utilization = (String) dbo.get("network_utilization");
			   packets_received = (String) dbo.get("packets_received");
			   packets_transmitted = (String) dbo.get("packets_transmitted");
			   
			   
			   
			   
			   if(insertIntoSQL(   
					   vm_name, 
					   time_stamp,
					   cpu_percentage_usage,
					   cpu_percantage_ready,
					   active_memory,
					   shared_memory,
					   swapped_memory,
					   balloon_memory,
					   memory_swap_in_rate,
					   memory_swap_out_rate,
					   cpu_usage_Mhz,
					   memory_percentage_usage,
					   rate_read_data_from_vd,
					   rate_write_data_to_vd,
					   avg_read_per_sec_vd,
					   avg_write_per_sec_vd,
					   network_utilization,
					   packets_received,
					   packets_transmitted
					   )
					   )
			   {
				   
				  /* BasicDBObject newDocument = new BasicDBObject();
					newDocument.append("$set", new BasicDBObject().append("flag", "1"));
				 
					BasicDBObject searchQuery = new BasicDBObject().append("vm_name", vm_name);
				    coll.update( searchQuery, newDocument);*/
				   
				   
				   System.out.println("Inserted Record with id"+ _id);
				    coll.update(new BasicDBObject("_id", _id),
                           new BasicDBObject("$set", new BasicDBObject("flag", "1")));
				   
				   
			   }
			   else
			   {
				   System.out.println("Error occured:");
			   }
			   
		   }
			   
		
		   
		} finally {
		   cursor.close();
		}
		
		
			
					
			
			
			
			
	
		
		
		
		
		// System.out.println(cpu);
		return null;
		
		
		

		
		
		
		
	}


	
	static Thread t1 = new Thread()
	{
		public void run(){
			while(true){
			try{
			getAggregateData();
			Thread.sleep(5000);
			}catch(UnknownHostException e){
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	};
	
	

	
	   
	   
	
	
	public static boolean insertIntoSQL(
			String vm_name, 
			   String time_stamp,
			   String cpu_percentage_usage,
			   String cpu_percantage_ready,
			   String active_memory,
			   String shared_memory,
			   String swapped_memory,
			   String balooned_memory,
			   String memory_swap_in_rate,
			   String memory_swap_out_rate,
			   String cpu_usage_Mhz,
			   String memory_percentage_usage,
			   String rate_read_data_from_vd,
			   String rate_write_data_to_vd,
			   String avg_read_per_sec_vd,
			   String avg_write_per_sec_vd,
			   String network_utilization,
			   String packets_received,
			   String packets_transmitted
			)
	{
		
	
	try {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss");
		java.util.Date parsedDate;
		
		
		
		
			parsedDate = dateFormat.parse(time_stamp);
		
		java.sql.Timestamp Stime_stamp = new java.sql.Timestamp(parsedDate.getTime());
		System.out.println("CPU"+cpu_percantage_ready);
		//Timestamp Stime_stamp=Timestamp.valueOf(time_stamp);
		
		float Scpu_percentage_usage=Float.parseFloat(cpu_percentage_usage == null ? "0" : cpu_percentage_usage);
		float Scpu_percantage_ready=Float.parseFloat(cpu_percantage_ready  == null ? "0" : cpu_percantage_ready);
		float Sactive_memory=Float.parseFloat(active_memory  == null ? "0" : active_memory);
		float Sshared_memory=Float.parseFloat(shared_memory  == null ? "0" : shared_memory);
		float Sswapped_memory=Float.parseFloat(swapped_memory  == null ? "0" : swapped_memory);
		//float Sswapped_memory=Float.parseFloat(swapped_memory);
		float Sballoon_memory=Float.parseFloat(balooned_memory  == null ? "0" : balooned_memory);
		float Smemory_swap_in_rate=Float.parseFloat(memory_swap_in_rate  == null ? "0" :  memory_swap_in_rate);
		float Smemory_swap_out_rate=Float.parseFloat(memory_swap_out_rate  == null ? "0" : memory_swap_out_rate);
		float Scpu_usage_Mhz=Float.parseFloat(cpu_usage_Mhz  == null ? "0" : cpu_usage_Mhz);
		float Smemory_percentage_usage=Float.parseFloat(memory_percentage_usage  == null ? "0" : memory_percentage_usage);
		float Srate_read_data_from_vd=Float.parseFloat(rate_read_data_from_vd  == null ? "0" : rate_read_data_from_vd);
		float Srate_write_data_to_vd=Float.parseFloat(rate_write_data_to_vd  == null ? "0" : rate_write_data_to_vd);
		float Savg_read_per_sec_vd=Float.parseFloat(avg_read_per_sec_vd  == null ? "0" : avg_read_per_sec_vd);
		float Savg_write_per_sec_vd=Float.parseFloat(avg_write_per_sec_vd  == null ? "0" : avg_write_per_sec_vd);
		float Snetwork_utilization=Float.parseFloat(network_utilization  == null ? "0" : network_utilization);
		float Spackets_received=Float.parseFloat(packets_received  == null ? "0" : packets_received);
		float Spackets_transmitted=Float.parseFloat(packets_transmitted  == null ? "0" : packets_transmitted);
		

		PreparedStatement st = (PreparedStatement) getMysqlConnection().prepareStatement("INSERT INTO vmlog (vm_name, time_stamp,cpu_percentage_usage,cpu_percantage_ready,active_memory,shared_memory,swapped_memory,balloon_memory,memory_swap_in_rate,memory_swap_out_rate,cpu_usage_Mhz,memory_percentage_usage,rate_read_data_from_vd,rate_write_data_to_vd,avg_read_per_sec_vd,avg_write_per_sec_vd,network_utilization,packets_received,packets_transmitted)"
                + " VALUES"
                + "( '" + vm_name + "','" + Stime_stamp + "'," + Scpu_percentage_usage + "," + Scpu_percantage_ready + "," + Sactive_memory + "," + Sshared_memory + "," + Sswapped_memory + "," + Sballoon_memory + "," + Smemory_swap_in_rate + "," + Smemory_swap_out_rate + "," + Scpu_usage_Mhz + "," + Smemory_percentage_usage + "," + Srate_read_data_from_vd + "," + Srate_write_data_to_vd + "," + Savg_read_per_sec_vd + "," + Savg_write_per_sec_vd + "," + Snetwork_utilization + "," + Spackets_received + "," + Spackets_transmitted + ")");
System.out.println("Inserted a row");
		st.executeUpdate();
				
	
		
		
	return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
	
	}
	

	public static void main(String[] args) throws UnknownHostException 
	{
		t1.start();
	}
}
