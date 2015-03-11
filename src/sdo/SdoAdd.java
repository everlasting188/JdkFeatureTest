package sdo;

import java.awt.geom.Point2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.spatial.geometry.JGeometry;
import oracle.sql.STRUCT;

public class SdoAdd {
	
	public static String driver = "oracle.jdbc.driver.OracleDriver";
	public static String url = "jdbc:oracle:thin:@192.168.4.170:1521:mega";
	public static String uid = "megagis";
	public static String psw = "megagis";
	
	public static void main(String[] args) {
		//testInsert();
		//testInsertCircle();
		testSelectList();
	}
	
	
	public static void testInsert()
	{
		
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uid, psw);

			JGeometry jGeometry = new JGeometry(41884696, 14377039, 0);
			//JGeometry.createLinearPolygon(java.lang.Object[] coords, int dim, int srid)
			
			STRUCT obj = jGeometry.store(jGeometry, conn);
			String sql = "insert into ZYQ_POI values(103,?,?)";
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ps.clearParameters();
			ps.setString(1, "我的家");
			
			ps.setObject(2, obj);
			// 插入点地物记录
			ps.executeUpdate();
			conn.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 下面的这个例子，首先用结果集的getObject()方法，把各行的几何体对象提取为STRUCT类型，然后使用JGeometry的静态方法load()把它转换成JGeometry对象。
		STRUCT dbObject = (STRUCT) rs.getObject(1);
		JGeometry geom = JGeometry.load(dbObject);
		要使用优化的反序列化工具(unpickler)，首先使用结果集的getBytes( )方法提取几何体到一个字节数组中。然后再次使用JGeometry的静态方法load()把它转换成JGeometry对象。
		byte[] image = rs. getBytes (1);
		JGeometry geom = JGeometry.load(image);
		
Select A.*
  FROM mingshengguji A  WHERE SDO_RELATE(A.GEOMETRY, mdsys.sdo_geometry(2003,4326,null,mdsys.sdo_elem_info_array(1, 1003, 3),mdsys.sdo_ordinate_array(10, 10,
                                                              1000,1000)),'mask=inside querytype=WINDOW') = 'TRUE';
	 */
	public static void testSelectList()
	{
		
		StringBuffer sqlSb = new StringBuffer();
		sqlSb.append(" Select A.GEOMETRY ")
		.append(" FROM mingshengguji A   WHERE SDO_RELATE(A.GEOMETRY, mdsys.sdo_geometry(2003,4326,null,mdsys.sdo_elem_info_array(1, 1003, 3),mdsys.sdo_ordinate_array(10, 10, ")
		.append("  1000,1000)),'mask=inside querytype=WINDOW') = 'TRUE'");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rsResultSet = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uid, psw);

			JGeometry jGeometry = JGeometry.createCircle(1000, 10, 10, 0);
			//JGeometry.createLinearPolygon(java.lang.Object[] coords, int dim, int srid)
			
			//String sql = "select GSHAPE from  ZYQ_POI ";
			ps = conn.prepareStatement(sqlSb.toString());
			
			rsResultSet = ps.executeQuery();
			while(rsResultSet.next())
			{
				STRUCT image =  (STRUCT)rsResultSet.getObject(1);
				JGeometry geom = JGeometry.load(image);
				
				if (!geom.isPoint()) {
					double xys[]  = geom.getOrdinatesArray();
					Point2D[] includePoint = geom.getJavaPoints();
					System.out.println(includePoint.length);
				}
				
				
				
				System.out.println(geom.getType()+":");
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rsResultSet !=null) {
				try {
					rsResultSet.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	//---获得对应的列表
	public static void testInsertCircle()
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, uid, psw);

			JGeometry jGeometry = JGeometry.createCircle(1000, 10, 10, 0);
			//JGeometry.createLinearPolygon(java.lang.Object[] coords, int dim, int srid)
			
			STRUCT obj = jGeometry.store(jGeometry, conn);
			String sql = "insert into ZYQ_POI values(102,?,?)";
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ps.clearParameters();
			ps.setString(1, "我的园");
			
			ps.setObject(2, obj);
			// 插入点地物记录
			ps.executeUpdate();
			conn.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	create table poi (
	id INTEGER,
	gname VARCHAR2(256),
	gshape MDSYS.SDO_GEOMETRY);
	 */
}
