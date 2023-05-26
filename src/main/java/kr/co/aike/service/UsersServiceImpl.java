package kr.co.aike.service;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import kr.co.aike.dao.UsersDao;
import kr.co.aike.domain.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {
	private final UsersDao dao;
	
	public ModelAndView addMessages(int code, String imgText, String msg1Text, String msg2Text, String link1Text, String link1Href , String link2Text, String link2Href) {
		ModelAndView mav=new ModelAndView();
		String msg1="<h1 class=\"mb-4\">"+msg1Text+"</h1>";
		String img=imgText;
		String msg2="<h1 class=\"mb-4\">"+msg2Text+"</h1>";
		String link1="<input class=\"btn btn-dark rounded-pill py-3 px-5\" type='button' value='"+link1Text+"' onclick='"+link1Href+"'>&nbsp;&nbsp;";
		String link2="<input class=\"btn btn-outline-dark rounded-pill py-3 px-5\" type='button' value='"+link2Text+"' onclick='"+link2Href+"'>";
		if(code==0) {
			mav.addObject("msg2", msg2);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else if(code==1) {
			mav.addObject("msg2", msg2);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
		} else if(code==2) {
			mav.addObject("msg1", msg1);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		}
		return mav;
	}
	
	@Override
	public int idCheck(String checkedid) throws Exception {
		int idExistCnt=dao.idExist(checkedid);
		return idExistCnt;
	}
	
	@Override
	public ModelAndView addUser(Users users) throws Exception {
		ModelAndView mav=new ModelAndView();
		log.info(users.toString());
		int cnt=dao.insertUsers(users);
		
		if(cnt==0){
			mav=addMessages(0,"<div class=\"mb-3\"><i class=\"bi bi-exclamation-triangle display-1 text-primary\"></i></div>","","!! 회원 등록 실패 !!","다시시도","javascript:history.back()","메인으로","location.href=\"../home\"");
		}else {
			mav=addMessages(1,"<div class=\"mb-3\"><i class=\"bi bi-stars text-warning\" style=\"font-size: 80px;\"></i></div>","","* ~ 회원 등록 성공 ~ *","로그인하기","location.href=\"./login\"","","");
		}//if end
		mav.setViewName("users/msgView");
		return mav;
	}
	
	//세션에 guest 할당 or 회원이면 존재하는 쿠키확인해서 아이디 기억 여부 파라미터에 추가
	public ModelAndView rememberId(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
				
		//세션 role&쿠키 확인해서 파라미터에 추가 
	    String sRoles="";
	    Users users = new Users();
	    if(session.getAttribute("authInfo")==null){
			//세션에 값이 없으면 guest로 값주기
			users.setRoles("guest");
			session.setAttribute("authInfo", users);
		}else {
			//세션에 값이 guest이면 쿠키 확인해서 아이디 기억 여부 파라미터에 추가
			users = (Users)session.getAttribute("authInfo");
			sRoles = users.getRoles();
			if(sRoles.equals("guest")) {
				Cookie[] cookies = request.getCookies();
				String c_id = "";
				if(cookies!=null) {
					for(int i=0;i<cookies.length;i++) {
						Cookie cookie = cookies[i];
						if(cookie.getName().equals("c_id")==true) {
							c_id=cookie.getValue();
							mav.addObject("c_id",c_id);
							mav.addObject("checked","checked");
						}
					}
				}
			}
		}
	    
		return mav;
	}
	
	//로그인할 때 쿠키에 아이디 기억 여부 저장
	public void rememberIdCookie(Users users, HttpServletRequest request, HttpServletResponse response) {
		String temp=request.getParameter("c_id");
		Cookie cookie = null;
		if(temp!=null) {
			if(temp.equals("SAVE")) {
				cookie = new Cookie("c_id",users.getUserId());
				cookie.setMaxAge(60*60*24*30);	
			}			
		}else {
			//아이디 기억 여부에 체크를 하지 않았을 경우 쿠키 제거
			cookie = new Cookie("c_id","");
			cookie.setMaxAge(0);
		}
		response.addCookie(cookie);
	}
	
	@Override
	public ModelAndView preLoginUser(HttpSession session, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();		
		mav = rememberId(session, request);
	    
	    //세션에 이전 페이지 경로 저장
		String temp=request.getHeader("Referer");
		int indexNum = temp.indexOf("aike");
		temp = temp.substring(indexNum+4);
		System.out.println(temp);
			//회원가입 후에 로그인하기 버튼을 눌러 로그인할 시 로그인 후 메인 페이지로 이동
		if(temp.equals("/users/register")) {
			temp="./../home";
		}
		session.setAttribute("place", temp);
		
		mav.setViewName("users/login");
		return mav;
	}
	
	@Override
	public ModelAndView loginUser(Users users, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav=new ModelAndView();
		Users findUsers = new Users();
		findUsers=dao.loginUser(users);
		if(findUsers==null) {
			mav = rememberId(session, request);
			rememberIdCookie(users, request, response);
			
			System.out.println("it's null");
			mav.addObject("msg", "아이디 혹은 비밀번호가 일치하지 않습니다. 입력한 내용을 다시 확인해 주세요.");
		}else {
			//쿠키에 아이디 기억 여부 저장
			rememberIdCookie(users, request, response);
			
			//세션에 회원 인가 내용 저장
			session.setAttribute("authInfo", findUsers);
			
			//세션에 저장했던 이전 페이지 경로로 이동
			mav.setViewName("redirect:"+session.getAttribute("place"));
		}
		return mav;
	}
	
	@Override
	public ModelAndView logoutUser(HttpSession session, HttpServletRequest request) throws Exception {
		session.removeAttribute("authInfo");
		ModelAndView mav = new ModelAndView();
		mav = rememberId(session, request);
		
		//이전 페이지 경로로 view 저장
		String temp=request.getHeader("Referer");
		int indexNum = temp.indexOf("aike");
		temp = temp.substring(indexNum+4);
		System.out.println(temp);
		mav.setViewName("redirect:"+ temp);
		return mav;
	}
	
	@Override
	public ModelAndView findId(Users users) throws Exception {
		ModelAndView mav=new ModelAndView();
		Users findUsers = new Users();
		findUsers=dao.selectUser(users);
		if(findUsers==null) {
			System.out.println("it's null");
			mav.addObject("msg", "입력한 이름과 이메일에 해당하는 회원이 존재하지 않습니다. 입력한 내용을 다시 확인해 주세요.");
		}else {
			mav=addMessages(2,"","아이디는 "+findUsers.getUserId()+" 입니다","","로그인하기","location.href=\"./login\"","비밀번호찾기","location.href=\"./findpw\"");
			mav.setViewName("users/msgView");
		}
		return mav;
	}
	
	@Autowired
    private JavaMailSender emailSender;
	
	@Override
	public void sendMail(String[] recipientList, String subject, String body){
	    MimeMessage message = emailSender.createMimeMessage();

	    try{
	        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	        
	        //메일 수신자 설정
	        messageHelper.setTo(recipientList);
	        //메일 제목 설정
	        messageHelper.setSubject("test_subject");
	        //메일 내용 설정(HTML 적용)
	        messageHelper.setText(body, true);
	        //메일 전송
	        emailSender.send(message);
	    } catch(Exception e){
	        log.info(e.toString());
	    }
	}
	
	@Override
	public ModelAndView findPw(Users users) throws Exception {
		ModelAndView mav=new ModelAndView();
		Users findUsers = new Users();
		findUsers=dao.selectUserForPw(users);
		if(findUsers==null) {
			System.out.println("it's null");
			mav.addObject("msg", "입력한 아이디와 이메일에 해당하는 회원이 존재하지 않습니다. 입력한 내용을 다시 확인해 주세요.");
		}else {
			//임시 비밀번호 만들기
				//대문자, 소문자, 숫자를 이용해서 랜덤하게 10글자를 만들기
			String[] ch= {
					"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
					"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
					"0","1","2","3","4","5","6","7","8","9"
			}; //ch[0]~ch[61]
			
				//ch배열에서 랜덤하게 10글자 뽑아서 가져오기
			StringBuilder tempPw=new StringBuilder();
			for(int i=0; i<10; i++) {
				int num=(int)(Math.random()*ch.length);
				tempPw.append(ch[num]);
			}//for end
			System.out.println(tempPw);
			
			//회원의 기존 비밀번호를 임시 비밀번호로 바꾸기
			int cnt = dao.updateUserPw(findUsers, tempPw);
			//바뀐 임시 비밀번호가 적용된 회원 정보 다시 불러오기
			findUsers=dao.selectUserForPw(users);
			
			if(cnt==1) {
				//임시 비밀번호로 테이블 수정 되었다면, 아이디와 비밀번호를 이메일 전송하기
				String[] recipientList = {findUsers.getUserEmail()};
				String subject = "Sprots shopping mall 임시 비밀번호 발급 안내";
				String body = "* 임시 비밀번호로 로그인 한 후, 회원 정보 수정에서 비밀번호를 변경하시기 바랍니다.";
				body+="<hr>";
				body+="<table style='border-collapse: collapse; width: 300px;'>";
				body+=" <tr>";
				body+="  <th style='background-color: #f2f2f2; font-weight: bold; padding: 8px; text-align: left; border-bottom: 1px solid #ddd;'>아이디</th>";
				body+="  <td style='padding: 8px; text-align: left; border-bottom: 1px solid #ddd;'>"+findUsers.getUserId()+"</td>";
				body+=" </tr>";
				body+="  <tr>";
				body+="    <th style='background-color: #f2f2f2; font-weight: bold; padding: 8px; text-align: left; border-bottom: 1px solid #ddd;'>임시비밀번호</th>";
				body+="    <td style='padding: 8px; text-align: left; border-bottom: 1px solid #ddd;'>"+findUsers.getUserPw()+"</td>";
				body+="  </tr>";
				body+="</table>";
				sendMail(recipientList, subject, body);
			}
			mav=addMessages(2,"","임시 비밀번호가 이메일로 전송되었습니다.","","로그인하기","location.href=\"./login\"","메인으로","location.href=\"../home\"");
			mav.setViewName("users/msgView");
		}
		return mav;
	}
	
	@Override
	public ModelAndView preUnregister() throws Exception {
		ModelAndView mav=new ModelAndView();
		mav=addMessages(2,"","회원 탈퇴하시겠습니까?","","회원탈퇴하기","location.href=\"./unregister\"","메인으로","location.href=\"../home\"");
		mav.setViewName("users/msgView");
		return mav;
	}
	
	@Override
	public ModelAndView unregister(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav=new ModelAndView();
		Users users = new Users();
		//세션에서 회원 데이터 가져오기
		users = (Users)session.getAttribute("authInfo");
		//db의 회원 데이터 삭제하기
		int cnt=dao.unregisterUser(users);

		if(cnt==0){
			mav=addMessages(0,"<div class=\"mb-3\"><i class=\"bi bi-exclamation-triangle display-1 text-primary\"></i></div>","","!! 회원 탈퇴 실패 !!","다시시도","javascript:history.back()","메인으로","location.href=\"../home\"");
		}else if(cnt==1){
			//쿠키에 아이디 기억 여부 삭제
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies) {
			    if(cookie.getName().equals("c_id")) {
			        cookie.setMaxAge(0);
			        response.addCookie(cookie);
			    }
			}
			//세션에서 회원 인가 내용 삭제
			session.removeAttribute("authInfo");
			mav=addMessages(1,"<div class=\"mb-3\"><i class=\"bi bi-stars text-warning\" style=\"font-size: 80px;\"></i></div>","","* ~ 회원 탈퇴 성공 ~ *","메인으로","location.href=\"../home\"","","");
		}//if end
		
		mav.setViewName("users/msgView");
		return mav;
	}
	
	@Override
	public ModelAndView modifyUser(Users users, HttpSession session) throws Exception {
		ModelAndView mav=new ModelAndView();
		log.info(users.toString());
		int cnt=dao.updateUser(users);
		if(cnt==0){
			mav=addMessages(0,"<div class=\"mb-3\"><i class=\"bi bi-exclamation-triangle display-1 text-primary\"></i></div>","","!! 회원 정보 수정 실패 !!","다시시도","javascript:history.back()","메인으로","location.href=\"../home\"");
		}else {
			//세션에 기존 회원 인가 내용 삭제
			session.removeAttribute("authInfo");
			//세션에 회원 인가 내용 저장
			session.setAttribute("authInfo", users);
			mav=addMessages(1,"<div class=\"mb-3\"><i class=\"bi bi-stars text-warning\" style=\"font-size: 80px;\"></i></div>","","* ~ 회원 정보 수정 성공 ~ *","메인으로","location.href=\"../home\"","","");
		}//if end
		mav.setViewName("users/msgView");
		return mav;		
	}
}
