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
 * Servlet implementation class RegistServlet
 */
public class RegisterServlet extends HttpServlet {
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
		
		// DB関連の初期設定
		Connection conn = null; //DBとの接続、切断
		PreparedStatement pstmt = null; //SQL実行準備
		//ResultSet rset = null; //SQL実行結果を格納
		
		// 文字コードの初期設定
		request.setCharacterEncoding("UTF-8");
		
		// 入力情報の取得
		String name = request.getParameter("r_name");
		String email_address = request.getParameter("r_email_address"); //メールアドレス
		String pass = request.getParameter("r_pass"); //パスワード
		
		try {
			// データソースからConnectionを取得
			conn = ds.getConnection();

			// sql文作成の準備
			StringBuffer sql = new StringBuffer();

			// sqlの作成(email_addressからselect)
			sql.append("insert into user_table (id, name, pass, email_address, role) values (");
			sql.append("0, "); //auto_incrementによる自動割り当て
			sql.append("'" + name + "', ");
			sql.append("'" + pass + "', ");
			sql.append("'" + email_address + "', ");
			sql.append("'guest');"); //初期役職はguest


			// sql文表示
			// System.out.println(sql);

			// sql文実行準備
			pstmt = conn.prepareStatement(new String(sql));

			// sql文実行
			pstmt.execute();
		
			// memu.jspへ遷移
			request.getRequestDispatcher("/menu.jsp").forward(request, response);

			// 使用したオブジェクトを終了させる
			//rset.close();
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
