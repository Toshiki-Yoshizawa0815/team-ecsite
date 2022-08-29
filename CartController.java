package jp.co.internous.sunflower.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.sunflower.model.domain.TblCart;
import jp.co.internous.sunflower.model.domain.dto.CartDto;
import jp.co.internous.sunflower.model.form.CartForm;
import jp.co.internous.sunflower.model.mapper.TblCartMapper;
import jp.co.internous.sunflower.model.session.LoginSession;


@Controller
@RequestMapping("/sunflower/cart")
public class CartController {
	
	@Autowired
	private TblCartMapper tblCartMapper;
	
	@Autowired
	private LoginSession loginSession;
	

	@RequestMapping("/")
	public String index(Model m) {		
		long userId = loginSession.getUserId();
		if(userId == 0) {
			userId = loginSession.getTempId();
		}

		List<CartDto> cartList = tblCartMapper.findByUserIdAndGetCart(userId);

		m.addAttribute("cartList", cartList);		
		m.addAttribute("loginSession", loginSession);

		return "cart";
	}

	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addCartPost(CartForm form, Model m) {
		
		long userId = loginSession.getUserId();
		if(userId == 0) {
			userId = loginSession.getTempId();
		}
		
		form.setUserId(userId);
		
		List<TblCart> carts = tblCartMapper.findByProductId(userId, form.getProductId());

		TblCart cart = new TblCart(form);
		if(carts != null && carts.size() > 0) {
			tblCartMapper.update(cart);
		} else {
			tblCartMapper.insert(cart);
		}

		return "redirect:/sunflower/cart/add";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addCartGet(Model m) {
		
		long userId = loginSession.getUserId();
		if(userId == 0) {
			userId = loginSession.getTempId();
		}

		List<CartDto> cartList = tblCartMapper.findByUserIdAndGetCart(userId);
		
		m.addAttribute("cartList", cartList);
		m.addAttribute("loginSession", loginSession);

		return "cart";
	}
	
	@ResponseBody
	@PostMapping("/delete")
	public String deleteCart(@RequestBody CartForm form) {
		List<Long> cartId = form.getId();
		try {
			cartId.forEach(id -> {
				tblCartMapper.delete(id);
			});
		} catch (IllegalArgumentException e) {
			return "-1";
		}
		
		return "1";
	}
	
}
