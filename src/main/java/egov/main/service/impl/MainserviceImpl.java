package egov.main.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import com.lib.model.UserVO;

import egov.main.dao.MainMapper;
import egov.main.service.MainService;
/* implements - 뒤에 기능적인 명세를 가지고 있는 interface를 적으면 됨.*/
@Service("MainService")
public class MainserviceImpl extends EgovAbstractServiceImpl implements MainService{
	 
	@Resource(name="MainMapper")
	MainMapper mainMapper;
	
	@Override
	public HashMap<String, Object> selectMain(HttpServletRequest request) throws Exception {
		HashMap<String,Object> paramMap = new HashMap();
		//request요청을(사용자가 입력한 값을) paramMap에 담아주기 - 이를 dataAccess Layer로 전달.
		
		/* mainDAO나 mainMapper*/
		return mainMapper.selectMain(paramMap);
	}
	@Override
	public HashMap<String, Object> selectLogin(HttpServletRequest request) throws Exception {
		/*여기서 유효성 체크를 해줘야함 - business layer에서는 요구사항에 명시된 핵심 유효성검사를 진행해야함. - 약속된 공통 규칙이 있다면 자바의 if문으로*/
		/* input에 적은 name을 impl(여기)에서 getparameter에 적으면 값을 얻을 수 있다*/
		String userid=request.getParameter("id");
		if(userid.length()>10) {
			throw new Exception("ValidError_userId");
		}
		
		HashMap<String,Object> paramMap = new HashMap();
		//request요청을(사용자가 입력한 값을) paramMap에 담아주기 - 이를 dataAccess Layer로 전달.
		
		//request요청을 paramMap에 담아주기
		//paramMap.put("inid", userid);
		paramMap.put("myid", userid);
		paramMap.put("out_state", -1);
		
		//조회가 되면 결과를 HashMap형태로 return해주고요.
		UserVO uservo = mainMapper.selectLogin(paramMap);
		
		//데이터를 사용자 측에 전달하기 전에, 요구사항에 명시된 핵심 유효성 검사할 수도 있음. if문
		//못찾았으면 예외를 반환해줌.
		if(uservo == null) {
			throw new Exception("resultError_idnotFound");
		}
		
		HashMap<String,Object> resultMap = new HashMap();
		resultMap.put("uservo", uservo);
		/* mainDAO나 mainMapper*/
		// 이렇게 HashMap을 Controller에 전달.
		return resultMap;
	}
}
