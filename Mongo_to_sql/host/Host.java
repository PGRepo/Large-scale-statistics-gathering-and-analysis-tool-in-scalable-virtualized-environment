
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

public class Host 
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

	

	public static String getAggregateData() throws UnknownHostException, ParseException
	
	
	{
		String host_name=null;
		String time_stamp= null;
		String cpu_percentage_usage= null;
		String cpu_percantage_ready= null;
		String active_memory= null;
		String shared_memory= null;
	//	String swapped_memory=null;
		String balooned_memory= null;
		String memory_swap_in_rate= null;
		String memory_swap_out_rate= null;
		String disk_highest_latency= null;
		String disk_max_depth_queue= null;
		String cpu_usage_mhz= null;
		String memory_usage_percentage= null;
		String disk_read_rate_percentage= null;
		String disk_write_rate_percentage=null;
		String disk_read_request_number= null;
		String disk_write_request_number= null;
		String data_received_rate= null;
		String data_transmitted_rate= null;
		String packets_received= null;
		String packets_tranmitted= null;
		
		
			
		
		
		MongoClient client = new MongoClient("localhost",27017);
		db = client.getDB("283_project_data_stats");
		DBCollection coll = db.getCollection("host_logs");
	//	List<Student> students = new ArrayList<Student>();
	BasicDBObject query = new BasicDBObject("flag", new BasicDBObject("$exists",false));
		DBCursor cursor = coll.find(query);
		int i=0;
		try {
			
		   while(cursor.hasNext()) {
			  
			 //  System.out.println(cursor.next());
						 
				 
			   
			   DBObject dbo = cursor.next();
			   ObjectId _id = (ObjectId)dbo.get( "_id" );
			   
			  //_id= (String) dbo.get(_id);
			  
			 /* try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			   
			//  BasicDBList studentsList = (BasicDBList) dbo.get("students");
			   host_name = (String) dbo.get("host_name");
			   time_stamp = (String) dbo.get("time_stamp");
			   cpu_percentage_usage = (String) dbo.get("cpu_percentage_usage");
			   cpu_percantage_ready = (String) dbo.get("cpu_percentage_ready");
			   active_memory = (String) dbo.get("active_memory");
			   shared_memory = (String) dbo.get("shared_memory");
			  // swapped_memory = (String) dbo.get("swapped_memory");
			   balooned_memory = (String) dbo.get("balooned_memory");
			   memory_swap_in_rate = (String) dbo.get("memory_swap_in_rate");
			   memory_swap_out_rate = (String) dbo.get("memory_swap_out_rate");
			   disk_highest_latency = (String) dbo.get("disk_highest_latency");
			   disk_max_depth_queue = (String) dbo.get("disk_max_depth_queue");
			   cpu_usage_mhz = (String) dbo.get("cpu_usage_mhz");
			   memory_usage_percentage = (String) dbo.get("memory_usage_percentage");
			   disk_read_rate_percentage = (String) dbo.get("disk_read_rate_percentage");
			   disk_write_rate_percentage = (String) dbo.get("disk_write_rate_percentage");
			   disk_read_request_number = (String) dbo.get("disk_read_request_number");
			   disk_write_request_number = (String) dbo.get("disk_write_request_number");
			   data_received_rate = (String) dbo.get("data_received_rate");
			   data_transmitted_rate = (String) dbo.get("data_transmitted_rate");
			   packets_received = (String) dbo.get("packets_received");
			   packets_tranmitted = (String) dbo.get("packets_tranmitted");
			   
			   if(insertIntoSQL(   
					   host_name, 
					   time_stamp,
					   cpu_percentage_usage,
					   cpu_percantage_ready,
					   active_memory,
					   shared_memory,
					   balooned_memory,
					   memory_swap_in_rate,
					   memory_swap_out_rate,
					   disk_highest_latency,
					   disk_max_depth_queue,
					   cpu_usage_mhz,
					   memory_usage_percentage,
					   disk_read_rate_percentage,
					   disk_write_rate_percentage,
					   disk_read_request_number,
					   disk_write_request_number,
					   data_received_rate,
					   data_transmitted_rate,
					   packets_received,
					   packets_tranmitted)
					   )
			   {
			   
			   //System.out.println(""+i++);
			  
				    				
				   
				/*   BasicDBObject newDocument = new BasicDBObject();
					newDocument.append("$set", new BasicDBObject().append("flag", "1"));
					
					
				 
					BasicDBObject searchQuery = new BasicDBObject().append("_id", _id);
				    coll.update(searchQuery, newDocument);
				    
				    */
				    
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
			String host_name, 
			   String time_stamp,
			   String cpu_percentage_usage,
			   String cpu_percantage_ready,
			   String active_memory,
			   String shared_memory,
			   String balooned_memory,
			   String memory_swap_in_rate,
			   String memory_swap_out_rate,
			   String disk_highest_latency,
			   String disk_max_depth_queue,
			   String cpu_usage_mhz,
			   String memory_usage_percentage,
			   String disk_read_rate_percentage,
			   String disk_write_rate_percentage,
			   String disk_read_request_number,
			   String disk_write_request_number,
			   String data_received_rate,
			   String data_transmitted_rate,
			   String packets_received,
			   String packets_tranmitted
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
		//float Sswapped_memory=Float.parseFloat(swapped_memory);
		float Sbalooned_memory=Float.parseFloat(balooned_memory  == null ? "0" : balooned_memory);
		float Smemory_swap_in_rate=Float.parseFloat(memory_swap_in_rate  == null ? "0" :  memory_swap_in_rate);
		float Smemory_swap_out_rate=Float.parseFloat(memory_swap_out_rate  == null ? "0" : memory_swap_out_rate);
		float Sdisk_highest_latency=Float.parseFloat(disk_highest_latency  == null ? "0" : disk_highest_latency);
		float Sdisk_max_depth_queue=Float.parseFloat(disk_max_depth_queue  == null ? "0" : disk_max_depth_queue);
		float Scpu_usage_mhz=Float.parseFloat(cpu_usage_mhz  == null ? "0" : cpu_usage_mhz);
		float Smemory_usage_percentage=Float.parseFloat(memory_usage_percentage  == null ? "0" : memory_usage_percentage);
		float Sdisk_read_rate_percentage=Float.parseFloat(disk_read_rate_percentage  == null ? "0" : disk_read_rate_percentage);
		float Sdisk_write_rate_percentage=Float.parseFloat(disk_write_rate_percentage  == null ? "0" : disk_write_rate_percentage);
		float Sdisk_read_request_number=Float.parseFloat(disk_read_request_number  == null ? "0" : disk_read_request_number);
		float Sdisk_write_request_number=Float.parseFloat(disk_write_request_number  == null ? "0" : disk_write_request_number);
		float Sdata_received_rate=Float.parseFloat(data_received_rate  == null ? "0" : data_received_rate);
		float Sdata_transmitted_rate=Float.parseFloat(data_transmitted_rate  == null ? "0" : data_transmitted_rate);
		float Spackets_received=Float.parseFloat(packets_received  == null ? "0" : packets_received);
		float Spackets_tranmitted=Float.parseFloat(packets_tranmitted  == null ? "0" : packets_tranmitted);
				
		
		
		PreparedStatement st = (PreparedStatement) getMysqlConnection().prepareStatement("INSERT INTO hostlog (host_name, time_stamp,cpu_percentage_usage,cpu_percantage_ready,active_memory,shared_memory,balooned_memory,memory_swap_in_rate,memory_swap_out_rate,disk_highest_latency,disk_max_depth_queue,cpu_usage_mhz,memory_usage_percentage,disk_read_rate_percentage,disk_write_rate_percentage,disk_read_request_number,disk_write_request_number,data_received_rate,data_transmitted_rate,packets_received,packets_tranmitted)"
                + " VALUES"
                + "( '" + host_name + "','" + Stime_stamp + "'," + Scpu_percentage_usage + "," + Scpu_percantage_ready + "," + Sactive_memory + "," + Sshared_memory + "," + Sbalooned_memory + "," + Smemory_swap_in_rate + "," + Smemory_swap_out_rate + "," + Sdisk_highest_latency + "," + Sdisk_max_depth_queue + "," + Scpu_usage_mhz + "," + Smemory_usage_percentage + "," + Sdisk_read_rate_percentage + "," + Sdisk_write_rate_percentage + "," + Sdisk_read_request_number + "," + Sdisk_write_request_number + "," + Sdata_received_rate + "," + Sdata_transmitted_rate + "," + Spackets_received + "," + Spackets_tranmitted + ")");
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
