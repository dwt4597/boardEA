package egov.main.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public interface MainService {

	HashMap<String, Object> selectMain(HttpServletRequest request)throws Exception;

	HashMap<String, Object> selectLogin(HttpServletRequest request)throws Exception;

}
