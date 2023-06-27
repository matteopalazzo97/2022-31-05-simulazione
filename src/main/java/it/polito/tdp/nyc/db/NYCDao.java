package it.polito.tdp.nyc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.nyc.model.Arco;
import it.polito.tdp.nyc.model.Hotspot;

public class NYCDao {
	
	public List<Hotspot> getAllHotspot(){
		String sql = "SELECT * FROM nyc_wifi_hotspot_locations";
		List<Hotspot> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Hotspot(res.getInt("OBJECTID"), res.getString("Borough"),
						res.getString("Type"), res.getString("Provider"), res.getString("Name"),
						res.getString("Location"),res.getDouble("Latitude"),res.getDouble("Longitude"),
						res.getString("Location_T"),res.getString("City"),res.getString("SSID"),
						res.getString("SourceID"),res.getInt("BoroCode"),res.getString("BoroName"),
						res.getString("NTACode"), res.getString("NTAName"), res.getInt("Postcode")));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<String> getProvider(){
		String sql = "SELECT DISTINCT n.`Provider` "
				+ "FROM `nyc_wifi_hotspot_locations` n";
		List<String> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("Provider"));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<String> getVertici(String provider){
		String sql = "SELECT DISTINCT n.`City` "
				+ "FROM `nyc_wifi_hotspot_locations` n "
				+ "WHERE n.`Provider`=?";
		List<String> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, provider);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("City"));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
	public List<Arco> getArchi(String provider){
		String sql = "SELECT DISTINCT n1.`City` AS c1, AVG(n1.`Latitude`) AS lat1, AVG(n1.`Longitude`) AS lng1, "
				+ "					  n2.`City`AS c2, AVG(n2.`Latitude`) AS lat2, AVG(n2.`Longitude`) AS lng2 "
				+ "FROM `nyc_wifi_hotspot_locations` n1, `nyc_wifi_hotspot_locations` n2 "
				+ "WHERE n1.`Provider`=n2.`Provider` "
				+ "AND n1.`City`<n2.`City` "
				+ "AND n1.`Provider`=? "
				+ "AND n2.`Provider`=? "
				+ "GROUP BY n1.`City`, n2.`City`";
		List<Arco> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, provider);
			st.setString(2, provider);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Arco(res.getString("c1"), res.getInt("lat1"), res.getInt("lng1"), 
						res.getString("c2"), res.getInt("lat2"), res.getInt("lng2")));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return result;
	}
	
}
