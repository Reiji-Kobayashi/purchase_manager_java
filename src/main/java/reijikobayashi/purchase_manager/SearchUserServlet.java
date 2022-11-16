package reijikobayashi.purchase_manager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Servlet implementation class SearchUser
 */
public class SearchUserServlet extends HttpServlet {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// DB関連の初期設定
		Connection conn = null; //DBとの接続、切断
		PreparedStatement pstmt = null; //SQL実行準備
		ResultSet rset = null; //SQL実行結果を格納

		// 文字コードの設定
		request.setCharacterEncoding("UTF-8");

		// 検索条件の取得
		String id = request.getParameter("s_id"); //id
		String name = request.getParameter("s_name"); //名前
		String email_address = request.getParameter("s_email_address"); //メールアドレス
		String role = request.getParameter("s_role"); //メールアドレス
			
		try {

			// データソースからConnectionを取得
			conn = ds.getConnection();

			// sql文作成の準備
			StringBuffer sql = new StringBuffer();

			// sqlの作成(nameから)
			sql.append("select id, name, email_address, role from user_table where name like '%");
			sql.append(name + "%'");

			// idが選択されている場合は追加する
			if (id != "") {
				sql.append("and id ='" + id + "'");
			}

			// email_addressが選択されている場合は追加する
			if (email_address != "") {
				sql.append("and email_address ='" + email_address + "'");
			}

			// roleが選択されている場合、は追加する
			if (role != "") {
				sql.append("and role ='" + role + "'");
			}

			// sql文表示
			System.out.println(sql);

			// sql文実行準備
			pstmt = conn.prepareStatement(new String(sql));

			// sql文実行
			pstmt.execute();

			// 実行結果をResultSetクラスに格納
			rset = pstmt.executeQuery();

			// 遷移ページへ値を引き渡し(Attributeで追加する)
			request.setAttribute("user_list", rset);

			// search.jspへ遷移
			request.getRequestDispatcher("/manage_user.jsp").forward(request, response);

			// 使用したオブジェクトを終了させる
			rset.close();
			pstmt.close();
			conn.close();
		
		} catch (Exception e) {
			e.printStackTrace();
					
			//String status ="検索に失敗しました。管理者に連絡してください";					
			//request.setAttribute("status", status);
			//request.getRequestDispatcher("/result.jsp").forward(request, response);

		} finally {
			try {
				// 念のためfinallyでDBとの接続を切断しておく
				conn.close();
			} catch (Exception e) {
			}
		}
	}

}
