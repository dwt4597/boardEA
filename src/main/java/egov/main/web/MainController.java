package egov.main.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lib.model.UserVO;

import egov.main.service.MainService;

@Controller
public class MainController {
	
	@Resource(name="MainService")
	MainService mainService;
	
	@RequestMapping(value="/main.do")
	/* HttpServletRequest - 사용자가 입력한 값을 받을 수 있는 파라미터에 해당됨. */
	/* ModelMap - url을 요청한 사용자에게 데이터를 전달할 때 사용.  */
	/* 작성 후, ctrl + b키로 빌드.*/
	public String main(HttpServletRequest request, ModelMap model) 
	{
		
		
		return "main/main";
	}
	
	@RequestMapping(value="/main2.do")
	public String main2(HttpServletRequest request, ModelMap model) 
	{
		
		
		return "main/main2";
	}
	
	@RequestMapping(value="/main3.do")
	public String main3(@RequestParam("pw")String pw, HttpServletRequest request, ModelMap model) 
	{
		/*main2에서 name값*/
		String id = request.getParameter("id");
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		
		if(id.equals("asdqwe"))
		{
			model.addAttribute("userid", pw);
		}else {
			model.addAttribute("userid", pw);
		}
		
		userNo = userNo+5;
		model.addAttribute("userNo", userNo);
		return "main/main3";
	}
	
	@RequestMapping(value="/main4/{userNo}.do")
	public String main4(@PathVariable String userNo,HttpServletRequest request, ModelMap model) 
	{
		model.addAttribute("userNo", userNo);
		
		return "main/main3";
	}
	@RequestMapping(value="/main5.do")
	public String main5(HttpServletRequest request, ModelMap model) 
	{
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap = mainService.selectMain(request);
			/* map 타입이기에 키로 값을 얻어와야함 - userid이 키*/
			/* return page - main.jsp에 ${serverId} */
			model.addAttribute("serverId",resultMap.get("userid").toString());
		} catch (Exception e) {
			//로그기록, 상태코드 반환 또는 에러페이지 전달
			return "error/error";
		}
		
		return "main/main";
	}
	@RequestMapping(value="/login.do")
	public String login(HttpServletRequest request, ModelMap model) 
	{
		
		
		return "login/login";
	}
	@RequestMapping(value="/loginSubmission.do")
	public String loginSubmission(HttpServletRequest request, ModelMap model) 
	{
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap = mainService.selectLogin(request);
			
			/* 세션 로그인 아웃 - userid는 serviceImpl에서 select문에서 사용된 컬럼명을 적어주면 됨.*/
			/* toString()은 get함수가 object를 반환해주기에, 문자열  타입으로 변환해줌.*/
			request.getSession().setAttribute("uservo",resultMap.get("uservo"));
			
			/* map 타입이기에 키로 값을 얻어와야함 - userid이 키*/
			/* return page - main.jsp에 ${serverId} */
			model.addAttribute("serverId",((UserVO)resultMap.get("uservo")).getUserid());
		} catch (Exception e) {
			//로그기록, 상태코드 반환 또는 에러페이지 전달
			
			/* serviceImpl에서 던졌던 exception 여기서 처리.*/
			/* if(resultMap == null) { throw new Exception("resultError_idnotFound"); }	*/
			/* 위에 catch (Exception e) 의 e라는 매개변수에 담겨서 전달되기에, getMessage()를통해 얻고 앞서서 글자와 비교. */
			String error = e.getMessage();
			if(error.equals("resultError_idnotFound")) {
				return "redirect:/login.do";
			}else {
			//일반예외페이지
			return "error/error";
		}
		
		}
		/* redirect는 서버단에서 서버단으로 요청할 수 있음.*/
		return "main/main";
	}
	
	@RequestMapping(value="/logout.do")
	public String logout(HttpServletRequest request, ModelMap model) 
	{
		/* 위의 loginSubmission함수에서 session에 저장된 myid를 얻고 출력해주는 형태.*/
		System.out.println(request.getSession().getAttribute("myid").toString());
		/*invalidate함수를 호출하면, 키와 값으로 저장된 데이터를 삭제해줌.*/
		request.getSession().invalidate();
		System.out.println(",");
		return "main/logout";
	}
	
	
}
