package com.namoo.social.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.namoo.social.domain.ImageFile;
import com.namoo.social.domain.Profile;
import com.namoo.social.domain.User;
import com.namoo.social.domain.UserStatus;
import com.namoo.social.dto.UserToUser;
import com.namoo.social.service.facade.MessageService;
import com.namoo.social.service.facade.UserService;
import com.namoo.social.web.press.Count;
import com.namoo.social.web.press.UserState;
import com.namoo.social.web.session.LoginRequired;
import com.namoo.social.web.session.SessionManager;

@Controller
public class UserController {

	@Value("#{profile['imageRootPath']}")
	private String imageRoot;
	
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	@ResponseBody
	public boolean login(HttpServletRequest req, User user) {
		//
		boolean login = userService.loginAsMember(user);
		
		if(login){
			//
			System.out.println("로긴성공");
			user = userService.findUser(user.getUserId());
			User loginUser = new User(user.getUserId(), user.getName(), user.getEmail());
			SessionManager.getInstance(req).setLogin(loginUser);	// 로그인 유저 정보를 세션에 담아서 보냄 설정
			return true;
		} else{
			//
			System.out.println("로긴실패");
			return false;
		}
	}
	
	@RequestMapping("/user/logout")
	public String logout(HttpServletRequest req) {
		//
		req.getSession().removeAttribute("login");
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/user/join")
	public String join(HttpServletRequest req, Model model, User user) {
		User login = SessionManager.getInstance(req).getLogin();
		if(login == null) {
			model.addAttribute(user);
			return "user/join";
		} else {
			return "redirect:/main";
		}
	}
	
	@RequestMapping(value = "/user/idConfirm", method = RequestMethod.GET)
	@ResponseBody
	public boolean join(@RequestParam("userId") String userId) {
		//
		User user = userService.findUser(userId);
		if(user == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(value = "/user/signup", method = RequestMethod.POST)
	public String join(User user) {
		//
		userService.joinAsMember(user);

		return "redirect:/";
	}
	
	@RequestMapping(value = "/user/search", method = RequestMethod.GET)
	@ResponseBody
	@LoginRequired
	public List<User> search(@RequestParam("keyword") String keyword) {
		//
		System.out.println(keyword);
		List<User> searchedUsers = userService.findUsersByKeyword(keyword);
		
		for (User user : searchedUsers) {
			System.out.println(user.getName());
		}
		
		return searchedUsers;
	}
	
	@RequestMapping(value = "/user/list/search", method = RequestMethod.GET)
	@LoginRequired
	public String searchingUserList(@RequestParam("keyword") String keyword, HttpServletRequest req, Model model) {
		//
		
		String loginId = SessionManager.getInstance(req).getLogin().getUserId();
		
		List<UserState> userList = new ArrayList<UserState>();
		
		List<User> allUserList = userService.findUsersByKeyword(keyword);
		List<User> followingList = userService.findAllFollowings(loginId);
		
		for (User user : allUserList) {
			UserState userState = new UserState(); 
			for(User following : followingList) {
				if(user.getUserId().equals(following.getUserId())){
					 userState.setUser(user);
					 userState.setFollowed(true);
					 break;
				}
			}
			if(userState.isFollowed() == false) {
				userState.setUser(user);
			}
//			if(!userState.getUser().getUserId().equals(loginId)){
				userList.add(userState);
//			}
		}
		
		model.addAttribute("userList", userList);
		
		return "user/search";
	}
	
	@RequestMapping("/user/list")
	@LoginRequired
	public String list(HttpServletRequest req, Model model) {
		//
		String loginId = SessionManager.getInstance(req).getLogin().getUserId();
		
		List<UserState> userList = new ArrayList<UserState>();
		
		List<User> allUserList = userService.findAllUsers();
		List<User> followingList = userService.findAllFollowings(loginId);
		
		for (User user : allUserList) {
			UserState userState = new UserState(); 
			for(User following : followingList) {
				if(user.getUserId().equals(following.getUserId())){
					 userState.setUser(user);
					 userState.setFollowed(true);
					 break;
				}
			}
			if(userState.isFollowed() == false) {
				userState.setUser(user);
			}
			if(!userState.getUser().getUserId().equals(loginId)){
				userList.add(userState);
			}
		}
		
		model.addAttribute("userList", userList);
		
		return "user/list";
	}
	
	@RequestMapping("/user/info")
	@LoginRequired
	public String info(HttpServletRequest req, Model model) {
		//
		String loginId = SessionManager.getInstance(req).getLogin().getUserId();
		
		User user = userService.findUser(loginId);
		
		model.addAttribute("user", user);
		
		return "user/info";
	}
	
	@RequestMapping(value = "/user/info/modify", method = RequestMethod.POST)
	@LoginRequired
	@ResponseBody
	public User infoModify(HttpServletRequest req, User user) {
		//
		userService.modifyUser(user);

		SessionManager.getInstance(req).setLogin(user);
		
		user = userService.findUser(user.getUserId());
		
		return user;
	}
	
	@RequestMapping("/user/follow/{memberId}")
	@LoginRequired
	@ResponseBody
	public boolean follow(@PathVariable("memberId") String memberId, 
			HttpServletRequest req) {
		//
		String loginId = SessionManager.getInstance(req).getLogin().getUserId();
		
		if (memberId.equals(loginId)) {
			//
			return false;
		} else {
			//
			UserToUser userToUser = new UserToUser(loginId, memberId);
			userService.addFollowing(userToUser);
			return true;
		}
	}
	
	@RequestMapping("/user/unfollow/{memberId}")
	@LoginRequired
	@ResponseBody
	public boolean unfollow(@PathVariable("memberId") String memberId, 
			HttpServletRequest req) {
		//
		String loginId = SessionManager.getInstance(req).getLogin().getUserId();
		
		if (memberId.equals(loginId)) {
			//
			return false;
		} else {
			//
			UserToUser userToUser = new UserToUser(loginId, memberId);
			userService.removeFollowing(userToUser);
			return true;
		}
	}
	
	@RequestMapping("/user/reco")
	@LoginRequired
	@ResponseBody
	public List<User> recommend(HttpServletRequest req) {
		//
		User login = SessionManager.getInstance(req).getLogin();
		
		List<User> recoMembers = userService
				.findRecommandableFollowings(login.getUserId());
		
		return recoMembers;
	}
	
	@RequestMapping("/user/friends")
	@LoginRequired
	@ResponseBody
	public List<User> getFrirendsList(HttpServletRequest req) {
		//
		User login = SessionManager.getInstance(req).getLogin();
		
		List<User> friends = new ArrayList<User>();
		
		List<User> friendsList = userService
				.findAllFriends(login.getUserId());
		
		for (User friend : friendsList) {
			if(friend.getUserStatus().isUserStatus()){
				long renewDate = friend.getUserStatus().getRenewDate().getTime()/1000;
				long now = new Date().getTime()/1000;
				if ((now - renewDate) > 5) {
					friend.setUserStatus(new UserStatus(false));
					userService.modifyUserStatus(friend);
				}
				System.out.println("지금 : " + now + "저장 : " + renewDate + "차이 : " + (now - renewDate));
			}
			
			friends.add(friend);
		}
		
		return friends;
	}
	
	@RequestMapping("/user/profile/{memberId}")
	@ResponseBody
	public User profile(HttpServletRequest req,
			@PathVariable("memberId") String memberId) {
		//
		User user = userService.findUser(memberId);
		Count count = new Count();
		count.setMsgCount(messageService.findAllMessagesByUserIdCount(memberId));
		count.setFollowingCount(userService.findAllFollowings(memberId).size());
		count.setFollowerCount(userService.findAllFollowers(memberId).size());
		Profile profile = null;
		if(user.getProfile() == null){
			profile = new Profile();
			profile.setIntroduction("");
			profile.setRegion("");
			profile.setHomepage("");
			profile.setImage(null);
		} else  {
			profile = user.getProfile();
		}
		profile.setCount(count);
		user.setProfile(profile);
		return user;
	}
	
	@RequestMapping(value = "/user/profile/modify", method = RequestMethod.POST)
	@LoginRequired
	@ResponseBody
	public User modifyProfile(User user, Profile profile, HttpServletRequest req
			,@RequestParam("imageFile") MultipartFile file
			) throws IOException {
		//
		String loginId = SessionManager.getInstance(req).getLogin().getUserId();
//		User user = userService.findUser(loginId);
		
		user.setProfile(profile);
		
		if (!file.isEmpty()) { 
			setupProfileImage(user, file);
		}
		
		userService.modifyUserProfile(user);;
		user = userService.findUser(loginId);
		
		return user;
	}
	
	@RequestMapping(value = "/user/profile/modify2", method = RequestMethod.POST)
	@LoginRequired
	@ResponseBody
	public User modifyProfile(User user, Profile profile, HttpServletRequest req
			) throws IOException {
		//
		String loginId = SessionManager.getInstance(req).getLogin().getUserId();
//		User user = userService.findUser(loginId);
		
		user.setProfile(profile);
		
		userService.modifyUserProfile(user);;
		user = userService.findUser(loginId);
		
		return user;
	}
	
	@RequestMapping(value = "/user/profile/{memberId}/image", method = RequestMethod.GET)
	public void getProfileImage(@PathVariable("memberId") String memberId,
			HttpServletResponse resp) throws IOException {
		//
		String contentType = null;
		InputStream in = null;
		
		User user = userService.findUser(memberId);
		
		Profile profile = user.getProfile();
		
		ImageFile imageFile = null;
		if (profile != null) {
			imageFile = profile.getImage();
		}
		
		if(imageFile != null) {
			contentType = imageFile.getContentType();
			in = new FileInputStream(new File(imageRoot + imageFile.getFileName()));
			System.out.println(imageFile.getFileName());
		} else {
			contentType = "image/png";
			in = this.getClass().getResourceAsStream("/default.png");
		}
		try {
			resp.setContentType(contentType);
			IOUtils.copy(in, resp.getOutputStream());
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	//--------------------------------------------------------------------------
	// private method
	
	private void setupProfileImage(User user, MultipartFile file) throws IOException {
		//
		StringBuffer sb = new StringBuffer();
		sb.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		sb.append(".");
		sb.append(FilenameUtils.getExtension(file.getOriginalFilename()));
		String saveFilename = sb.toString();
		File saveFile = new File(imageRoot + saveFilename);
		
		FileCopyUtils.copy(file.getBytes(), saveFile);
		
		String fileName = null;
		try {
			fileName = userService.findUser(user.getUserId()).getProfile().getImage().getFileName();
		} catch (NullPointerException e) {}
		
		if(fileName != null) {
			File savedFile = new File(imageRoot + fileName);
			savedFile.delete();
			System.out.println("saved image delete " + savedFile.getCanonicalPath());
		}
		
		ImageFile profileImage = new ImageFile(file.getContentType(), saveFilename);
		user.getProfile().setImage(profileImage);
		
		System.out.println("image saved in " + saveFile.getCanonicalPath());
	}
}