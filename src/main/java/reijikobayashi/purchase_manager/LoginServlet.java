package reijikobayashi.purchase_manager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//データソースの作成
	DataSource ds;

	// 初期化処理
	public void init() throws ServletException {
		try {
			// 初期コンテキストを取得
			InitialContext ic = new InitialContext();
			// ルックアップしてデータソースを取得
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/reijikobayashi");
		} catch (Exception e) {
		}
	}  
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//変数の初期設定
		String name = null; // ログイン名
		String status = "ログイン失敗"; // ステータス
		String role = null; // 役職
		
		// DB関連の初期設定
		Connection conn = null; //DBとの接続、切断
		PreparedStatement pstmt = null; //SQL実行準備
		ResultSet rset = null; //SQL実行結果を格納
		
		// 文字コードの初期設定
		request.setCharacterEncoding("UTF-8");
		
		// メールアドレスの取得
		String email_address = request.getParameter("email_address");

		// パスワードの取得
		String pass = request.getParameter("pass");
		
		try {
			// JDBC Driver の登録
//			Class.forName("com.mysql.cj.jdbc.Driver");		
//			// Connectionの作成
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company_db?serverTimezone=UTC&useSSL=false",
//					"suser", "suser_pass");
			
			// データソースからConnectionを取得
			conn = ds.getConnection();

			// sql文作成の準備
			StringBuffer sql = new StringBuffer();

			// sqlの作成(email_addressからselect)
			sql.append("select name, pass, role from user_table where ");
			sql.append("email_address = '" + email_address + "'");

			// sql文表示
			// System.out.println(sql);

			// sql文実行準備
			pstmt = conn.prepareStatement(new String(sql));

			// sql文実行
			pstmt.execute();

			// 実行結果をResultSetクラスに格納
			rset = pstmt.executeQuery();
			
			// 遷移ページへ値を引き渡し(Attributeで追加する)
			// request.setAttribute("login_info", rset);

			// IDとパスワードのチェック
			if (rset.next() != false) {
				// System.out.println(rset.getString("pass"));
				if (pass.equals(rset.getString("pass"))) {
					status = "ログイン成功";
					name = rset.getString("name");
					role = rset.getString("role");
				}else {
					
				}
			}else {
			}
			
			// HttpSessionにデータを追加
			HttpSession session = request.getSession( true );
			session.setAttribute("login", status);
			session.setAttribute("name", name);
			session.setAttribute("role", role);
			
			// memu.jspへ遷移
			request.getRequestDispatcher("/menu.jsp").forward(request, response);

			// 使用したオブジェクトを終了させる
			rset.close();
			pstmt.close();
			conn.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 念のためfinallyでDBとの接続を切断しておく
				conn.close();
			} catch (Exception e) {
			}
		}	
	}
}
