package com.example.demo;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

	
	@Autowired
	UserRepo repo;
	
	@Autowired
	ProductRepository repo1;
	
	@Autowired
	ProductDAO dao;
	
	@Autowired
	UserDAO dao1;
	
	
	
	Logger log=Logger.getAnonymousLogger();

	@RequestMapping("/")
	public ModelAndView loadpage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("login.jsp");
		log.info("login page is loaded");
		return mv;
	}
	
//	@ResponseBody
	@RequestMapping("/login")
	public ModelAndView checklogin(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		 String user= request.getParameter("user");
		 String password=request.getParameter("pwd");
		 
		 User User=repo.findByuserpwd(user, password);
		 log.info("the user details are:"+User);
//		 String user1=emp.getUsername();
		 if (User != null) {
		        if (user.equals("admin") && password.equals("admin")) {
//		        	RedirectView redirectView = new RedirectView("http://localhost:8089/get-all", true);
		            mv.setViewName("redirect:/getproducts");
		        }else
//		        	RedirectView redirectView = new RedirectView("http://localhost:8089/get-products", true);
		            mv.setViewName("redirect:/getall");
		        }
		 
		     else 
		     {
		        mv.setViewName("fail.jsp");
		    }

		    return mv;
		}

	
//	@ResponseBody
	@RequestMapping("/registerservice")
	public ModelAndView register(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();

//		String user= request.getParameter("user");
//		 String password=request.getParameter("pwd");
//		 String email= request.getParameter("email");
		 User user1=new User();
		 
		 user1.setUsername(request.getParameter("user"));
		 user1.setPassword(request.getParameter("pwd"));
		 user1.setEmail(request.getParameter("email"));
		 
		 User details=dao1.insert(user1);
		 if(details!=null) {
				mv.setViewName("Registration.jsp");
				log.info("going to success.jsp page");
			}
			else {
				mv.setViewName("fail.jsp");
				log.info("going to fail.jsp page");
			}
		 //rest api->RestTemplate
//		 RestTemplate rest=new RestTemplate();
//		 String url="http://localhost:8089/register-user/"+user+"/"+password+"/"+email;
//		 log.info(url);
//		 rest.getForObject(url,String.class);
//		 return "registration is successfully done";
		return mv;
	}
	
	private String FOLDER="C:\\Users\\RASHMI\\Desktop\\images\\"; 
	@RequestMapping("/insert")
	public ModelAndView insert(HttpServletRequest request,HttpServletResponse response,@RequestParam("pname") String pname,@RequestParam("pcost") String pcost,@RequestParam("file") MultipartFile file) {
		ModelAndView mv=new ModelAndView();
		log.info("object for model and view created ");
		Product product=new Product();
		product.setPname(pname);
		product.setCost(Integer.parseInt(pcost));
//		/G:/image/abc.png
		product.setFilePath(FOLDER+file.getOriginalFilename());
		log.info("setting of values done" +product.getPname()+"  "+product.getCost());
		Product order=dao.insert(product);
		if(order!=null) {
			mv.setViewName("success.jsp");
			log.info("going to success.jsp page");
		}
		else {
			mv.setViewName("fail.jsp");
			log.info("going to fail.jsp page");
		}
		return mv;
	}
	
	@RequestMapping("/getall")
	public ModelAndView displayall(HttpServletRequest request,HttpServletResponse response) {
	ModelAndView mv=new ModelAndView();
	log.info("object for model and view created ");
	Product product=new Product();
	List<Product> order=dao.getall();
	mv.addObject("list",order);
	mv.setViewName("display.jsp");
	return mv;
	
	}


	
	@RequestMapping("/getproducts")
	public ModelAndView displayproduct(HttpServletRequest request,HttpServletResponse response) {
	ModelAndView mv=new ModelAndView();
	log.info("object for model and view created ");
	Product product=new Product();
	List<Product> order=dao.getall();
	mv.addObject("list",order);
	mv.setViewName("products.jsp");
	return mv;
	
	}
	
	@RequestMapping("/updatedata")
	@ResponseBody
	public ModelAndView updatedata(Product product,@RequestParam("pid") int pid,@RequestParam("pname") String pname, @RequestParam ("cost") int cost) {
		ModelAndView mv=new ModelAndView();
		log.info("object for model and view created ");
	Product p=repo1.findById(product.getPid()).orElse(null);;
	p.setPname(pname);
	p.setCost(cost);
	mv.addObject(p);
	mv.setViewName("success.jsp");
	repo1.save(p);
	return mv;
	}
	
	@RequestMapping("/resetpassword")
	@ResponseBody
	public ModelAndView resetPassword(User user,
	        @RequestParam(name = "username", required = true) String username,
	        @RequestParam(name = "password", required = true) String password) {
	    ModelAndView modelAndView = new ModelAndView();
	    log.info("object for model and view created ");
	    User user1 = repo.findByUsername(username);     
	    log.info("The user is :"+ user1.getUsername());
	    user1.setPassword(password);
	    user1.setUsername(username);
	    repo.save(user1);
	    modelAndView.setViewName("pwdresetconfirm.jsp");
	    return modelAndView;
	}
   
   @RequestMapping(value = "/delete", method = RequestMethod.GET)
   public ModelAndView deletedata(@RequestParam ("pid") int pid )
   {
	   ModelAndView mv=new ModelAndView();
		log.info("object for model and view created ");
		 repo1.deleteById(pid);
		 mv.setViewName("redirect:/getproducts");
		return mv;
	
}

	@RequestMapping("/bankcontroller")
	public ModelAndView subAndView(HttpServletRequest request,HttpServletResponse response, @RequestParam("pcost") String pcost) {
		ModelAndView mv=new ModelAndView();
		log.info("object for model and view created ");
		Bank bank=new Bank();
		double amount=bank.getAmount();
		log.info("The amount is "+amount);
	    double balance = amount-Integer.parseInt(pcost);
	    mv.addObject("newBalance", balance);
	    log.info("The balance amount is "+ balance);
	    mv.setViewName("confirmationpage.jsp");
		return mv;
		
	}
	
	

}



