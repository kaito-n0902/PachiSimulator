package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.FindMainKeyLogic;
import model.FindPachiLogic;
import model.InsertLogic;
import model.Pachi;
import model.PachiLogicEva;
import model.PachiLogicRezero;
import model.PachiLogicToloveru;

public class PachiTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/selectPachi.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String model = request.getParameter("radiobtn");
		int money = Integer.parseInt(request.getParameter("money"));
		
		//取得したリクエストパラメータでJavaBeansにてインスタンス化
		FindMainKeyLogic findbo = new FindMainKeyLogic();
		int mainKey = findbo.findMainkey(model);
		Pachi pachi = new Pachi(mainKey, model, money);
		
		//ロジックの呼び出し
		if(model.equals("toloveru")) { //とらぶるを選んだ場合
			PachiLogicToloveru pachiLogicToloveru = new PachiLogicToloveru();
			pachiLogicToloveru.execute(pachi);
		}else if(model.equals("rezero")) { //リゼロを選んだ場合
			PachiLogicRezero pachiLogicRezero = new PachiLogicRezero();
			pachiLogicRezero.execute(pachi);
		}else if(model.equals("eva")) { //エヴァを選んだ場合
			PachiLogicEva pachiLogicEva = new PachiLogicEva();
			pachiLogicEva.execute(pachi);
		}
		
		//リクエストスコープにインスタンスを保存
		request.setAttribute("pachi", pachi);
		
		//DBにレコードを追加
		InsertLogic insertbo = new InsertLogic();
		String modelName = pachi.getModel();
		insertbo.execute(pachi, modelName);
		
		//DBから過去の結果を取得、セッションスコープに保存
		FindPachiLogic findpachibo = new FindPachiLogic();
		List<Pachi> pachiList = findpachibo.execute(modelName);
		
		HttpSession session = request.getSession();
		session.setAttribute("pachiList", pachiList);
		
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/pachiResult.jsp");
		dispatcher.forward(request, response);
	}
}
