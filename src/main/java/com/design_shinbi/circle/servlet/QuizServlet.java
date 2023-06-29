package com.design_shinbi.circle.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.design_shinbi.circle.model.Const;
import com.design_shinbi.circle.model.Quiz;

@WebServlet("/play")
public class QuizServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String jsp = null;
		HttpSession session = req.getSession();
		Quiz quiz = (Quiz)session.getAttribute("quiz");
						
		if (quiz != null) {
			quiz.chkState();
			if (quiz.getState().equals("standby")) {
				jsp = "/WEB-INF/jsp/standby.jsp";
				
			} else if(quiz.getState().equals("playing")) {
				session.setAttribute("question", quiz.pickQuestion());
				playProcess(quiz, req);
				jsp = "/WEB-INF/jsp/play.jsp";
				
			} else if(quiz.getState().equals("finish")) {
				resultProcess();
				jsp = "/WEB-INF/jsp/result.jsp";
			}
		} else {
			jsp = "/WEB-INF/jsp/standby.jsp";
		}
				
		RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
		dispatcher.forward(req, resp);
	}
	
	/* プレイ中の処理 */
	private void playProcess(Quiz quiz, HttpServletRequest req) {
		String userChoice = req.getParameter("userChoice"); 
		if (userChoice != null) {
			if (quiz.pickQuestion().getUserAnswered() == -1) {
				quiz.pickQuestion().setUserAnswered(userChoice);
				quiz.setAnswered(quiz.getAnswered() + 1);
			}
		}
		
		if (quiz.getAnswered() >= Const.QUIZ_CHOICE_VALUE) {
			quiz.setState("finish");
		}
	}
	
	/* クイズゲーム終了時に、ランキングやユーザー情報を変更する処理 */
	private void resultProcess() {
		
	}
	
}
