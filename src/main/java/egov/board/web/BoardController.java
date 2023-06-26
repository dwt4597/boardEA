package egov.board.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egov.board.service.BoardService;

@Controller
public class BoardController {
	
	@Resource(name="BoardService")BoardService boardService;
	
	@RequestMapping(value="/boardWrite.do")
	public String boardWrite(HttpServletRequest request, ModelMap model) 
	{
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			boardService.checkUser(request);
		} catch (Exception e) {
			//로그기록, 상태코드 반환 또는 에러페이지 전달
			String error = e.getMessage();
			if(error.equals("로그인안했음.")) 
			{
				return "redirect:/login.do";
			}
			else 
			{
			//일반예외페이지
			
			}
			return "error/error";
		}
		/* redirect는 서버단에서 서버단으로 요청할 수 있음.*/
		return "board/boardwrite";
	}
	
	@RequestMapping(value="/boardInsert.do")
	public String boardInsert(HttpServletRequest request, ModelMap model) 
	{
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			boardService.saveBoard(request);
		} catch (Exception e) {
			//로그기록, 상태코드 반환 또는 에러페이지 전달
			String error = e.getMessage();
			if(error.equals("로그인안했음.")) 
			{
				return "redirect:/login.do";
			}
			else if(error.equals("제목을 다시 확인해주세요.")) {
				return "redirect:/boardWrite.do";
			}
			else 
			{
			//일반예외페이지
			
			}
			return "error/error";
		}
		/* redirect는 서버단에서 서버단으로 요청할 수 있음.*/
		return "board/boardwrite";
	}
	@RequestMapping(value="/boardView.do")
	public String boardView(HttpServletRequest request, ModelMap model) 
	{
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap = boardService.showUser(request);
			
		} catch (Exception e) {
			//로그기록, 상태코드 반환 또는 에러페이지 전달
			String error = e.getMessage();
			if(error.equals("로그인안했음.")) 
			{
				return "redirect:/login.do";
			} else if(error.equals("유효성검사실패"))
			{
				//return "error/error";
			}else if(error.equals("페이지찾을수없음"))
			{
				//return "error/error";
			}
			else 
			{
			//일반예외페이지
			
			}
			return "error/error";
		}
		model.addAttribute("userid",resultMap.get("userid").toString());
		model.addAttribute("title",resultMap.get("title").toString());
		model.addAttribute("boardcontents",resultMap.get("boardcontents").toString());
		
		/* redirect는 서버단에서 서버단으로 요청할 수 있음.*/
		return "board/boardview";
	}
	@RequestMapping(value="/boardList.do")
	public String boardList(HttpServletRequest request, ModelMap model) 
	{
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			list = boardService.showUserList(request);
			
		} catch (Exception e) {
			//로그기록, 상태코드 반환 또는 에러페이지 전달
			String error = e.getMessage();
			if(error.equals("로그인안했음.")) 
			{
				return "redirect:/login.do";
			} else if(error.equals("유효성검사실패"))
			{
				//return "error/error";
			}else if(error.equals("페이지찾을수없음"))
			{
				//return "error/error";
			}
			else 
			{
			//일반예외페이지
			
			}
			
			return "error/error";
		}
		
		model.addAllAttributes(list.get(list.size()-1)); //list에 get함수를 통해 hashmap을꺼내옴. list.size함수와 -1을 통해 list에 맨 마지막에 넣었던 것을 가져오는 형태.
		//즉 마지막에 넣었던 게시글 숫자, 페이징객체가 꺼내오겠죠.
		list.remove(list.size()-1); //list에는 게시판 글 만 존재하도록, 게시글숫자와 페이징 객체는 제거해줌.
		model.addAttribute("boardlist",list);
		
		/* redirect는 서버단에서 서버단으로 요청할 수 있음.*/
		return "board/boardlist2";
	}
	
}
