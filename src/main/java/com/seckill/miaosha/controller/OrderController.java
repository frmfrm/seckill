package com.seckill.miaosha.controller;

import com.seckill.miaosha.domain.MiaoshaUser;
import com.seckill.miaosha.domain.OrderInfo;
import com.seckill.miaosha.redis.RedisService;
import com.seckill.miaosha.result.CodeMsg;
import com.seckill.miaosha.result.Result;
import com.seckill.miaosha.service.GoodsService;
import com.seckill.miaosha.service.MiaoshaUserService;
import com.seckill.miaosha.service.OrderService;
import com.seckill.miaosha.vo.GoodsVo;
import com.seckill.miaosha.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/order")
@Controller
public class OrderController {
	@Autowired
	GoodsService goodsService;
	@Autowired
	RedisService redisService;
	@Autowired
	MiaoshaUserService miaoshaUserService;
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping("/detail") 
	@ResponseBody
	//@NeedLogin
	/**
	 * 		@NeedLogin使用一个拦截器，不用每次都去判断user是否为空，在拦截器里面user为空，直接返回某页面。
	 * @param model
	 * @param user
	 * @param orderId
	 * @return
	 */
	public Result<OrderDetailVo> info(Model model, MiaoshaUser user,
									  @RequestParam("orderId") long orderId) {
		if(user==null) {
			return Result.error(CodeMsg.SESSION_ERROR);
		}
		OrderInfo order=orderService.getOrderByOrderId(orderId);
		if(order==null) {
			return Result.error(CodeMsg.ORDER_NOT_EXIST);
		}
		//订单存在的情况
		long goodsId=order.getGoodsId();
		GoodsVo gVo=goodsService.getGoodsVoByGoodsId(goodsId);
		OrderDetailVo oVo=new OrderDetailVo();
		oVo.setGoodsVo(gVo);
		oVo.setOrder(order);
		return Result.success(oVo);//返回页面login
	}
	
	
}
