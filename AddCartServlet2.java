package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddCartServlet2
 */
@WebServlet("/AddCartServlet2")
public class AddCartServlet2 extends HttpServlet {




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//ログイン済みかどうかをチェックする
		//すでに作成済みのセッション領域の取得
		HttpSession session = request.getSession(false);
		if(session == null) {//セッションがない場合
			out.println("<html><head><title>ShowCart</title></head><body>");
			out.println("<h1>ログインしてください</h1>");
			out.println("</body></html>");
			return;
		} else {//セッションは開始されているがログイン済みではない場合
			String isLogin = (String) session.getAttribute("isLogin");
			if(isLogin == null || !isLogin.equals("true")) {
				out.println("<html><head><title>ShowCart</title></head><body>");
				out.println("<h1>ログインしてください2</h1>");
				out.println("</body></html>");
				return;
			}
		}
		//リクエストパラメーターで読み込み
		String productNo = request.getParameter("product_no");
		int no = Integer.parseInt(productNo);
		String productName = null;
		//選択された商品の判定
		switch(no) {
		case 100:
			productName = "ホームパイ";
			break;
		case 101:
			productName = "ダース";
			break;
		case 102:
			productName = "プリッツ";
			break;
		case 103:
			productName = "おさつ";
			break;
		default:
			productName = "???";
		}

		@SuppressWarnings("unchecked")
		ArrayList<String> cart = (ArrayList<String>) session.getAttribute("products");
		if(cart == null) {//初めての時はカートを作成
			cart = new ArrayList<String>();
			session.setAttribute("products", cart);

		}
		//商品の追加
		cart.add(productName);

		//クライアントごとのカート情報の表示
		out.println("<html><head><title>ShowCart</title></head><body>");
		out.println("現在のカートの中身は下記の通りです<br><br>");
		for(int i = 0; i < cart.size(); i++) {
			out.println(i + 1);
			out.println(":" + cart.get(i) + "<br>");
		}

		//商品リストへのリンク
		out.println("<hr><a href='/jmaster2/selectProduct4.html'>商品リスト</a>");
		out.println("</body></html>");

	}

}
