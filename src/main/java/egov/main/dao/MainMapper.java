package egov.main.dao;

import java.util.HashMap;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.lib.model.UserVO;

@Mapper("MainMapper")
public interface MainMapper {

	HashMap<String, Object> selectMain(HashMap<String, Object> paramMap)throws Exception;

	UserVO selectLogin(HashMap<String, Object> paramMap)throws Exception;
	
}
