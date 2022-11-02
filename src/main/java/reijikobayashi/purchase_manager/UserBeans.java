package reijikobayashi.purchase_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.servlet.http.HttpServletRequest;

public class UserBeans {
	private String id;
	private String name;
	private String pass;
	private String email_address;
	private String role;
	
	// DB関連の初期設定
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private DataSource ds = null;
	
	// コンストラクタ
	public UserBeans(HttpServletRequest request) {
		setId(request.getParameter("id"));
		setName(request.getParameter("name"));
		setPass(request.getParameter("pass"));
		setEmailAddress(request.getParameter("email_address"));
		setRole(request.getParameter("role"));
	}	
	
	// データベースへのアクション
	private void doDataBase(String sql) throws Exception {	
		// コンテキストの取得
		InitialContext ic = new InitialContext();
		// ルックアップしてデータソースを取得
		ds = (DataSource) ic.lookup("java:comp/env/jdbc/reijikobayashi");
		conn = ds.getConnection();
				
		// sql文を表示
		System.out.println(sql);
		pstmt = conn.prepareStatement(sql);
		
		// sqlを実行
		pstmt.execute();

		// 使用したオブジェクトを終了
		pstmt.close();
		conn.close();
	}
	
	//
	
	
	
	// getter, setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmailAddress() {
		return email_address;
	}
	public void setEmailAddress(String email_address) {
		this.email_address = email_address;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
