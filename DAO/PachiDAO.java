package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Pachi;

public class PachiDAO {
	private final static String JDBC_URL = "jdbc:postgresql://192.168.182.128:5432/pachi";
	
	public void insertDB(Pachi pachi, String modelName) {
		//実行結果のログ記録時に使用
		
		//JDBCドライバを読み込む
		try{
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
			
		//postgreSQLへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL);
				PreparedStatement preStatement = conn.prepareStatement
				("INSERT INTO " + modelName + " (p_num, p_money, p_firstbonus, p_petitbonus, p_mediumbonus,"
				+ "p_bigbonus, p_maxbonus, p_result, p_usedmoney) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);"))
		{
		int key = pachi.getKey();
		int money = pachi.getMoney();
		int firstBonus = pachi.getFirstBonusCnt();
		int petitBonus = pachi.getPetitBonus();
		int mediumBonus = pachi.getMediumBonus();
		int bigBonus = pachi.getBigBonus();
		int maxBonus = pachi.getMaxBonus();
		int result = pachi.getBall();
		int usedMoney = pachi.getUsedMoney();
				
		//INSERT文を準備
		preStatement.setInt(1, key);
	    preStatement.setInt(2, money);
	    preStatement.setInt(3, firstBonus);
	    preStatement.setInt(4, petitBonus);
	    preStatement.setInt(5, mediumBonus);
	    preStatement.setInt(6, bigBonus);
	    preStatement.setInt(7, maxBonus);
	    preStatement.setInt(8, result);
	    preStatement.setInt(9, usedMoney);
	            
	    //INSERT文を実行
		preStatement.executeUpdate(); 
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int findMainkey(String model) {
		//主キー取得用
		//JDBCドライバを読み込む
		int mainKey = 1;
		try{
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
			
		//postgreSQLへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL);
				PreparedStatement preStatement = conn.prepareStatement("SELECT * FROM " + model + " ORDER BY p_num DESC LIMIT 1;"))
		{	
			//SELECT文を実行し、結果を取得
			ResultSet rs = preStatement.executeQuery();
			
			if(rs.next()) {
				//取得した主キーに+1する
				mainKey = rs.getInt("p_num") + 1;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return mainKey;
	}
	
	public List<Pachi> findPachi(String modelName){
		//過去の結果表示用
		
		List<Pachi> pachiList = new ArrayList<>();
		
		//JDBCドライバを読み込む
		try{
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//postgreSQLへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL);
			PreparedStatement preStatement = conn.prepareStatement("SELECT * FROM " + modelName + " ;"))
		{
			
			//レコード数が10を超えていたら古いものから削除
			int recordCnt = getRecordCount(modelName);
			if(recordCnt > 10) {
            	deleteOldRecord(modelName);
            }
			
		    //SELECT文を実行
			ResultSet rs = preStatement.executeQuery(); 
			
			//SELECT結果の受け取り
			while(rs.next()){
                int mainKey = rs.getInt(1);
                int money = rs.getInt(2);
                int firstBonus = rs.getInt(3);
                int petitBonus = rs.getInt(4);
                int mediumBonus = rs.getInt(5);
                int bigBonus = rs.getInt(6);
                int maxBonus = rs.getInt(7);
                int result = rs.getInt(8);
                int usedMoney = rs.getInt(9);
                Pachi pachi = new Pachi(mainKey, modelName, money, firstBonus, petitBonus, mediumBonus, bigBonus, maxBonus, result, usedMoney);
                pachiList.add(pachi);
            }	
		}catch(SQLException e) {
				e.printStackTrace();
		}
		return pachiList;
	}
	
	public static int getRecordCount(String modelName){
        //テーブル内レコード数確認用
		
        try (Connection conn = DriverManager.getConnection(JDBC_URL);
        	PreparedStatement preparedStatement = conn.prepareStatement("SELECT COUNT(*) FROM " + modelName + " ;"))
        {
        	ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // COUNT(*)の結果を取得
            }
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        return 0;
    }
	
	public static void deleteOldRecord(String modelName){
		//テーブル内の古いレコード削除用
		
        String deleteSQL = "DELETE FROM " + modelName + " WHERE ctid IN ("
                         + "SELECT ctid FROM " + modelName
                         + " ORDER BY p_num ASC "
                         + "LIMIT 1)";
        
        try (Connection conn = DriverManager.getConnection(JDBC_URL);
        	PreparedStatement preparedStatement = conn.prepareStatement(deleteSQL)) {
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
        	e.printStackTrace();
        }
    }
}
