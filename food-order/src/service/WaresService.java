package service;

import dao.DishDao;
import dao.FavorDao;
import dao.IdleDao;
import dao.MenuDao;
import dao.OrderDao;
import dao.RestDao;
import dao.SalerDao;
import dao.VipDao;

import java.util.List;

import model.Dish;
import model.Idle;
import model.Menu;
import model.Order;
import model.Restaurant;
import model.Saler;

public class WaresService {

	private RestDao restDao;
	private SalerDao salerDao;
	private FavorDao favorDao;
	private VipDao vipDao;
	private IdleDao idleDao;
	private MenuDao menuDao;
	private OrderDao orderDao;
	private DishDao dishDao;
	
	
	public WaresService() {
		
	}
	
	public List getOrderByDate(Long rid, String date) {
		return orderDao.getOrderByDate(rid, date);
	}
	
	public boolean isVip(Long cid, Long rid) {
		return vipDao.isVip(cid, rid);
	}
	
	public boolean reqVip(Long cid, Long rid) {
		return vipDao.reqVip(cid, rid);
	}
	
	public boolean addVip(Long vid) {
		return vipDao.addVip(vid);
	}
	
	public boolean removeVip(Long vid) {
		return vipDao.removeVip(vid);
	}
	
	public int getCusRestIdlesSize(Long rid, Long cid) {
		return restDao.getCusRestIdlesSize(rid, cid);
	}
	public List getCusRestIdlesLimit(Long rid, Long cid, int start, int offset) {
		return restDao.getCusRestIdlesLimit(rid, cid, start, offset);
	}
	
	public List getOrderDish(Long oid) {
		return orderDao.getOrderDish(oid);
	}
	
	public Menu getMenuByRid(Long rid) {
		return menuDao.getMenuByRid(rid);
	}
	
	public Order getOrderByIid(Long iid) {
		return orderDao.getOrderByIid(iid);
	}
	
	public Restaurant getRestById(Long rid) {
		
		return restDao.getRestById(rid);
	}
	
	public List searchRest(String rname) {
		
		return restDao.searchRest(rname);
	}
	public List getMenuDish(Long rid) {
		return menuDao.getMenuDish(rid);
	}
	
	public List getCusRestIdles(Long rid, Long cid) {
		if (rid == null || cid == null) {
			return null;
		} else {
			return restDao.getCusRestIdles(rid, cid);
		}
	}
	public List getRestIdles(Long rid) {
		if (rid == null) {
			return null;
		} else {
			return restDao.getRestIdles(rid);
		}
	}
	
	public void updateIdle(Idle idle) {
		
		if (idle == null) {
			return;
		} else {
			restDao.updateIdle(idle);
		}
	}
	
	
	public void deleteIdle(Idle idle) {
		if (idle == null) {
			return;
		} else {
			restDao.deleteIdle(idle);
		}
	}
	
	public boolean addIdle(Idle idle) {
		if (idle == null) {
			return false;
		} else {
			return idleDao.addIdle(idle);
		}
	}
	public Idle getIdleById(Long iid) {
		return idleDao.getIdleById(iid);
	}
	
	public boolean addFavor(Long cid, Long rid) {
		if (cid == null || rid == null) {
			return false;
		} else if (favorDao.findFavor(cid, rid)) {
			return false;
		} else {
			return favorDao.addFavor(cid, rid);
		}
		
	}
	
	public List getFavorRest(Long cid) {
		if (cid == null) {
			return null;
		} else {
			return favorDao.getFavorRest(cid);
		}
	}
	public void updateRest(Restaurant rest) {
		if (rest == null) {
			return;
		} else {
			salerDao.updateRest(rest);
		}
	}
	
	public void addRest(Long sid, Restaurant rest) {
		if (sid == null || rest == null) {
			return;
		} else {
			salerDao.addRest(sid, rest);
		}
	}
	
	public void deleteRest(Long sid, Restaurant rest) {
		if (sid == null || rest == null) {
			return;
		} else {
			salerDao.deleteRest(sid, rest);
		}
	}
	
	public List getOwnRest(Long sid) {
		if (sid == null) {
			return null;
		}
		return salerDao.getOwnRest(sid);	
	}
	
	public List getAllRest() {
		
		List<Restaurant> rests = restDao.findAllRest();
		return rests;
	}
	public RestDao getRestDao() {
		return restDao;
	}
	public void setRestDao(RestDao restDao) {
		this.restDao = restDao;
	}
	public SalerDao getSalerDao() {
		return salerDao;
	}
	public void setSalerDao(SalerDao salerDao) {
		this.salerDao = salerDao;
	}
	public FavorDao getFavorDao() {
		return favorDao;
	}
	public void setFavorDao(FavorDao favorDao) {
		this.favorDao = favorDao;
	}
	public IdleDao getIdleDao() {
		return idleDao;
	}
	public void setIdleDao(IdleDao idleDao) {
		this.idleDao = idleDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	public MenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	
	public DishDao getDishDao() {
		return dishDao;
	}

	public void setDishDao(DishDao dishDao) {
		this.dishDao = dishDao;
	}
	
	public VipDao getVipDao() {
		return vipDao;
	}

	public void setVipDao(VipDao vipDao) {
		this.vipDao = vipDao;
	}

	public void addOrder(Long iid, String time) {
		orderDao.addOrder(iid, time);
		
	}
	
	public boolean addOrderDish(Long oid, Long did, Long num) {
		if (orderDao.addOrderDish(oid, did, num)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean removeFavor(Long cid, Long rid) {
		if (cid == null || rid == null) {
			return false;
		} else {
			return favorDao.removeFavor(cid, rid);
		}
	}

	public void updateDish(Dish dish) {
		dishDao.updateDish(dish);
		
	}

	public List<Dish> getDishes(Long rid) {
		return dishDao.getDishes(rid);
	}

	public void addDish(Dish dish) {
		dishDao.addDish(dish);
		
	}

	public Dish getDishById(long did) {
		return dishDao.getDishById(did);
	}

	public void deleteDish(Dish dish) {
		dishDao.deleteDish(dish);
		
	}

	public void addMenu(Long rid, Menu menu) {
		menuDao.addMenu(rid, menu);
		
	}

	public void acceptIdle(Long iid) {
		idleDao.acceptIdle(iid);
		
	}

	public void denyIdle(Long iid) {
		idleDao.denyIdle(iid);
		
	}

	public List getCusIdles(Long cid) {
		return idleDao.getCusIdles(cid);
	}

	public void finishIdle(Long iid) {
		idleDao.finishIdle(iid);
		
	}

	public List getRestVips(Long rid) {
		return restDao.getRestVips(rid);
	}
}
