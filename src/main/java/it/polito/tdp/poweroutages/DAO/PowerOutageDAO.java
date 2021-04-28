package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<PowerOutage> getPowerOutageList(int nercId) {

		String sql = "SELECT id, nerc_id,customers_affected,date_event_began,date_event_finished FROM poweroutages WHERE nerc_id=? ORDER BY date_event_began ASC";
		List<PowerOutage> powerOutageList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nercId);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				LocalDateTime db = res.getTimestamp("date_event_began").toLocalDateTime();
				LocalDateTime df = res.getTimestamp("date_event_finished").toLocalDateTime();
				PowerOutage po = new PowerOutage(res.getInt("Id"), res.getInt("nerc_id"), res.getInt("customers_affected"), db, df);
				powerOutageList.add(po);
//				PowerOutage p=new PowerOutage(res.getInt("id"), res.getInt("id_nerc"), res.getInt("customer_affected"), res.getTimestamp("date_event_began").toLocalDateTime(), res.getTimestamp("date_event_finished").toLocalDateTime());
//				powerOutageList.add(p);
//				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
//				nercList.add(n);	
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return powerOutageList;
	}
	

}
