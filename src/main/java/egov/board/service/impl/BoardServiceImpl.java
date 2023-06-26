package egov.board.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Service;

import com.lib.model.UserVO;
import com.lib.util.Validation_Form;

import egov.board.dao.BoardMapper;
import egov.board.service.BoardService;
/* implements - 뒤에 기능적인 명세를 가지고 있는 interface를 적으면 됨.*/
@Service("BoardService")
public class BoardServiceImpl extends EgovAbstractServiceImpl implements BoardService{
	 
	@Resource(name="BoardMapper")
	BoardMapper boardMapper;

	@Override
	public void checkUser(HttpServletRequest request) throws Exception {

		if(request.getSession().getAttribute("uservo")==null) 
		{
			throw new Exception("로그인안했음.");
		}
	}
	
	@Override
	public void saveBoard(HttpServletRequest request) throws Exception {

		if(request.getSession().getAttribute("uservo")==null) 
		{
			throw new Exception("로그인안했음.");
		}
		//사용자요청을 데이터베이스로 전달
		String title = request.getParameter("title");
		String content = request.getParameter("mytextarea");
		
		if(title.length()>25) 
		{
			//발생 시. controller에서 else if(error.equals("제목을 다시 확인해주세요.")) {}
			throw new Exception("제목을 다시 확인해주세요.");
		}
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("in_title", title);
		paramMap.put("in_content", content);
		paramMap.put("in_userid", ((UserVO)request.getSession().getAttribute("uservo")).getUserid());
		paramMap.put("out_state", 0);
		boardMapper.saveBoard(paramMap);
	}

	@Override
	public HashMap<String, Object> showUser(HttpServletRequest request) throws Exception {
		if(request.getSession().getAttribute("uservo")==null) 
		{
			throw new Exception("로그인안했음.");
		}
		//사용자요청을 데이터베이스로 전달
		//session에 저 장 돼 있 는 id 도 필 요 에 따 라 얻 을 수 있 음.
		//session의 반 환 타 입 이 object이 기 때 문 에 , 강 제 형 변 환.
		String id = ((UserVO)request.getSession().getAttribute("uservo")).getUserid();
		String brdid = request.getParameter("brdid");
		boolean validNumber = false;
		/*com.lib.util.Validation_Form.java - 값 을 boolean true false로 저 장 */
		validNumber = Validation_Form.validNum(brdid);
		
		if(validNumber ==false)
		{
			throw new Exception("유효성검사실패");
		}
		
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("in_brdid", brdid);
		paramMap.put("out_state", 0);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap= boardMapper.showBoard(paramMap);
		if(resultMap==null)
		{
			throw new Exception("페이지찾을수없음");
		}
		return resultMap;
	}

	@Override
	public ArrayList<HashMap<String, Object>> showUserList(HttpServletRequest request) throws Exception {
		
		if(request.getSession().getAttribute("uservo")==null) 
		{
			throw new Exception("로그인안했음.");
		}
		
		String pageNo=request.getParameter("pageNo");
		if(pageNo==null||pageNo.equals("")) 
		{
			pageNo="1";
		}
		else
		{
			pageNo=request.getParameter("pageNo");
		}
		
		/* paginationInfo를 컨트롤 마우스클릭 declared type 눌러봐 설명있음 맨위  */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(Integer.parseInt(pageNo));
		paginationInfo.setPageSize(10);//10이면 - 하단에 보여지는 페이지 숫자가 최대 10개
		paginationInfo.setRecordCountPerPage(10);
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pi_offset", (paginationInfo.getCurrentPageNo()-1)*paginationInfo.getRecordCountPerPage()); //(요청된페이지번호 -1) * (한 페이지에 보여질 게시글 수)
		paramMap.put("pi_RecordCountPerPage", paginationInfo.getRecordCountPerPage());
		paramMap.put("out_listcount",0);//전체 게시물 건수 - 게시판 아래 위치한 페이지를 몇 개 보여줄지 등등 계산하기 위함.
		paramMap.put("out_state", 0);
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		list= boardMapper.showBoardList(paramMap);
		int listCount = Integer.parseInt(paramMap.get("out_listcount").toString());
		paginationInfo.setTotalRecordCount(listCount);
		
		if(list==null)
		{
			throw new Exception("페이지찾을수없음");
		}
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("paginationInfo", paginationInfo);//paginationInfo객체와 listCount를 담아 controller에 보내줌.
		resultMap.put("listCount", listCount);//listCount
		list.add(resultMap);
		return list;
	}
}
