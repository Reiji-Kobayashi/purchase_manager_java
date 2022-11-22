package reijikobayashi.purchase_manager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import reijikobayashi.purchase_manager.UserBeans;

import java.io.IOException;

/**
 * Servlet implementation class ManageUserServlet
 */
public class ManageUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 文字コードの設定
		request.setCharacterEncoding("UTF-8");

		// modeの取得
		String mode = request.getParameter("mode");

		// 実行ステータスの宣言
		String status = "成功しました";

		// JavaBeansの初期化
		UserBeans user = new UserBeans(request);
		
		switch (mode) {

		case "add": // 登録
			break;
			
		case "delete": // 削除
			if (user.deleteData() == false) {
				status = "失敗しました";
			}
			break;
			
		case "change": // 変更
			request.setAttribute("user", user);
			request.getRequestDispatcher("/change_user_data.jsp").forward(request, response);
			return;

		case "changed": // 変更確定
			if (!(user.updateData())) {
				status = "失敗しました";
			}
			break;
		}

		// statusをセットしてresult.jspに転送
		// request.setAttribute("status", status);
		request.getRequestDispatcher("/admin.jsp").forward(request, response);

	}
	
	//　URLの後ろについたパラメータを取得
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

}
