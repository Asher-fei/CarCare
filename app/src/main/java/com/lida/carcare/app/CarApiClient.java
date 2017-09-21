package com.lida.carcare.app;

import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.bean.AddGoodBean;
import com.lida.carcare.bean.BannerBean;
import com.lida.carcare.bean.BannerDetailBean;
import com.lida.carcare.bean.CarAndDocumentBean;
import com.lida.carcare.bean.CarDetailBean;
import com.lida.carcare.bean.CarInShopBean;
import com.lida.carcare.bean.CarInfoBean;
import com.lida.carcare.bean.CarInspectProjectBean;
import com.lida.carcare.bean.CarManageListBean;
import com.lida.carcare.bean.CarRecordBean;
import com.lida.carcare.bean.CarRemindSetBean;
import com.lida.carcare.bean.CarTypeBean;
import com.lida.carcare.bean.CardRestrictCarnoBean;
import com.lida.carcare.bean.ChooseOrderGoodBean;
import com.lida.carcare.bean.ConsumBean;
import com.lida.carcare.bean.ConsumeCardBean;
import com.lida.carcare.bean.CurrentStoreUserBean;
import com.lida.carcare.bean.CustomerDetailBean;
import com.lida.carcare.bean.CustomerMainBean;
import com.lida.carcare.bean.CustomerSourceBean;
import com.lida.carcare.bean.GetCarMainTainBean;
import com.lida.carcare.bean.GetCarnewDetailBean;
import com.lida.carcare.bean.GetDecumentByCarnoBean;
import com.lida.carcare.bean.GetNameBean;
import com.lida.carcare.bean.GetPriceBean;
import com.lida.carcare.bean.GetUserListBean;
import com.lida.carcare.bean.GetWarehouseByCodeBean;
import com.lida.carcare.bean.GoodBean;
import com.lida.carcare.bean.GoodClassABean;
import com.lida.carcare.bean.GoodDetailBean;
import com.lida.carcare.bean.GoodListBean;
import com.lida.carcare.bean.GoodSearchResultBean;
import com.lida.carcare.bean.GradeBean;
import com.lida.carcare.bean.IncomeDetailsBean;
import com.lida.carcare.bean.IncomeExpenditureDean;
import com.lida.carcare.bean.InventoryVerificationBean;
import com.lida.carcare.bean.InventoryVerificationDetailBean;
import com.lida.carcare.bean.LackOfBalanceBean;
import com.lida.carcare.bean.LimitedOfCardsListBean;
import com.lida.carcare.bean.LineBean;
import com.lida.carcare.bean.LineDetailBean;
import com.lida.carcare.bean.LocateTheRescueBean;
import com.lida.carcare.bean.LoginBean;
import com.lida.carcare.bean.MemberBean;
import com.lida.carcare.bean.MemberDetailBean;
import com.lida.carcare.bean.MemberManagementBean;
import com.lida.carcare.bean.OnceCarByCustomerBean;
import com.lida.carcare.bean.OpenTimeCardDetailBean;
import com.lida.carcare.bean.OrderAdvertisementBean;
import com.lida.carcare.bean.OrderClassificationBean;
import com.lida.carcare.bean.OrderPlaceBean;
import com.lida.carcare.bean.OrderPlaceDetailBean;
import com.lida.carcare.bean.OrderRecommendedBean;
import com.lida.carcare.bean.OutIntHistoryBean;
import com.lida.carcare.bean.OutIntHistoryDetailsBean;
import com.lida.carcare.bean.OverTimeLessThanBean;
import com.lida.carcare.bean.PerformanceBean;
import com.lida.carcare.bean.PersonalDataBean;
import com.lida.carcare.bean.PositionBean;
import com.lida.carcare.bean.PreloadedCardsListBean;
import com.lida.carcare.bean.ProductsInfo;
import com.lida.carcare.bean.PublicAppointmentBean;
import com.lida.carcare.bean.PurchaseDetailBean;
import com.lida.carcare.bean.PurchaseHistoryBean;
import com.lida.carcare.bean.QueryAllGoodsBean;
import com.lida.carcare.bean.RegisterBean;
import com.lida.carcare.bean.RemindBean;
import com.lida.carcare.bean.RemindDetailBean;
import com.lida.carcare.bean.RemindShutDownBean;
import com.lida.carcare.bean.RoleBean;
import com.lida.carcare.bean.SelectInfoOfferBean;
import com.lida.carcare.bean.SelectOutboundGoodslistBean;
import com.lida.carcare.bean.SelectPurchaseDetailListBean;
import com.lida.carcare.bean.SelectShopListBean;
import com.lida.carcare.bean.ServerClassABean;
import com.lida.carcare.bean.ServerClassBBean;
import com.lida.carcare.bean.ServerHotBean;
import com.lida.carcare.bean.ServiceBean;
import com.lida.carcare.bean.ServiceCustomResultBean;
import com.lida.carcare.bean.ServiceDetailBean;
import com.lida.carcare.bean.ServiceGoodBean;
import com.lida.carcare.bean.ServiceSearchResultBean;
import com.lida.carcare.bean.SpendingDetailBean;
import com.lida.carcare.bean.StorageListBean;
import com.lida.carcare.bean.SupplierBean;
import com.lida.carcare.bean.SupplierDetailsBean;
import com.lida.carcare.bean.TimeCardListBean;
import com.lida.carcare.bean.UnlimitedListBean;
import com.lida.carcare.bean.WaitWareHouseBean;
import com.lida.carcare.bean.WeiXinPayBean;
import com.lida.carcare.bean.WillExpireBean;
import com.lida.carcare.bean.WorkOrderBean;
import com.lida.carcare.bean.WorkerTreeBean;
import com.lida.carcare.updater.Version;
import com.midian.base.afinal.http.AjaxParams;
import com.midian.base.api.ApiCallback;
import com.midian.base.api.BaseApiClient;
import com.midian.base.app.AppContext;
import com.midian.base.app.Constant;
import com.midian.base.bean.NetResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by WeiQingFeng on 2017/5/10.
 */

public class CarApiClient extends BaseApiClient {
    public CarApiClient(AppContext ac) {
        super(ac);
    }

    static public void init(AppContext appcontext) {
        if (appcontext == null)
            return;
        appcontext.api.addApiClient(new CarApiClient(appcontext));
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     */
    public void login(String account, String password, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("account", account);
        params.put("password", password);
        post(callback, Constant.LOGIN, params, LoginBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取车类型
     *
     * @param callback
     */
    public void getCarType(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        get(callback, Constant.GETCARTYPE, params, CarTypeBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 选择客户列表
     *
     * @return
     * @throws Exception
     */
    public CustomerMainBean getCustomerList(String str, String shopId) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("str", str);
        params.put("shopId", shopId);
        return (CustomerMainBean) getSync(Constant.GETCUSTOMERLISTBYQUERY, params, CustomerMainBean.class);
    }

    /**
     * 获取用户级别
     *
     * @return
     * @throws Exception
     */
    public void getCustomerLevel(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        get(callback, Constant.GETCUSTOMERLEVEL, params, GradeBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取客户来源
     *
     * @return
     * @throws Exception
     */
    public void getCustomerSource(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        get(callback, Constant.GETCUSTOMERSOURCE, params, CustomerSourceBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 输入里程数时获取上次的保养记录的服务（商品）
     *
     * @param carNo
     * @param callback
     */
    public void carMaintainRecord(String carNo, String shopId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("carNo", carNo);
        params.put("shopId", shopId);
        get(callback, Constant.CARMAINTAINRECORD, params, GoodBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取服务一级分类
     *
     * @param callback
     */
    public void getCategory(String shopId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", shopId);
        get(callback, Constant.GETCATEGORY, params, ServiceBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取服务二级分类
     *
     * @param code
     * @param callback
     */
    public void getCategoryByCode(String code, String shopId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("code", code);
        params.put("shopId", shopId);
        get(callback, Constant.GETCATEGORYBYCODE, params, ServiceBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取服务二级分类下的商品
     *
     * @param code
     * @return
     * @throws Exception
     */
    public ServiceGoodBean getGoodsByCode(String shopId, String code) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", shopId);
        params.put("serviceType", code);
        return (ServiceGoodBean) getSync(Constant.GETSERVICEBYCODE, params, ServiceGoodBean.class);
    }

    /**
     * 获取服务二级分类下的商品
     *
     * @param shopId
     * @param code
     * @param callback
     */
    public void getGoodsByCode(String shopId, String code, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", shopId);
        params.put("serviceType", code);
        get(callback, Constant.GETSERVICEBYCODE, params, ServiceGoodBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 添加新车
     *
     * @param carNo           车牌号   String
     * @param carFrameNo      车架号  String
     * @param brandId         车型  String
     * @param customerName    客户  String
     * @param sex             性别 0男1女  String
     * @param mobile          电话  String
     * @param customerId      选择客户id (这个可选，客户，电话，性别则必填)  String
     * @param isWait          是否在店等 0否1是   String
     * @param mileage         公里数  String
     * @param oiltable        油量   String
     * @param deliveryTime    预计交车时间 String
     * @param registerTime    注册时间  String
     * @param remark          备注  String
     * @param commercialTime  商业险到期时间  String
     * @param userName        当前登录人
     * @param compulsoryTime  交强险到期日  String
     * @param goodsProject    美容项目  String 多个用,分割
     * @param maintainProject 保养项目  String 多个用,分割
     * @param repairProject   维修项目  String 多个用,分割
     * @param refitProject    改装项目  String 多个用,分割
     */
    public void saveCar(String carNo, String carFrameNo, String brandId, String customerName, String sex,
                        String mobile, String customerId, String isWait, String mileage, String oiltable,
                        String deliveryTime, String registerTime, String remark, String commercialTime,
                        String userName, String compulsoryTime, String goodsProject, String maintainProject,
                        String repairProject, String refitProject, String shopId, String amount,
                        String goodsNumber,String maintainNumber,String repairNumber,String refitNumber,String nickname,String goodPrice,
                        String maintainPrice,String repairPrice,String refitPrice,ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("carNo", carNo);
        params.put("carFrameNo", carFrameNo);
        params.put("brandId", brandId);
        params.put("customerName", customerName);
        params.put("sex", sex);
        params.put("mobile", mobile);
        params.put("customerId", customerId);
        params.put("isWait", isWait);
        params.put("mileage", mileage);
        params.put("oiltable", oiltable);
        params.put("deliveryTime", deliveryTime);
        params.put("registerTime", registerTime);
        params.put("remark", remark);
        params.put("commercialTime", commercialTime);
        params.put("userName", userName);
        params.put("compulsoryTime", compulsoryTime);
        params.put("goodsProject", goodsProject);
        params.put("maintainProject", maintainProject);
        params.put("repairProject", repairProject);
        params.put("refitProject", refitProject);
        params.put("shopId", shopId);
        params.put("amount", amount);
        params.put("goodsNumber", goodsNumber);
        params.put("maintainNumber", maintainNumber);
        params.put("repairNumber", repairNumber);
        params.put("refitNumber", refitNumber);
        params.put("nickname",nickname);
        params.put("goodPrice",goodPrice);
        params.put("maintainPrice",maintainPrice);
        params.put("refitPrice",refitPrice);
        params.put("repairPrice",repairPrice);
        get(callback, Constant.SAVECAR, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 注册
     *
     * @param phone
     * @param userName
     * @param shopName
     * @param password
     * @param callback
     */
    public void userRegister(String phone, String userName, String shopName, String password, String shopCode, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("phone", phone);
        params.put("userName", userName);
        params.put("shopName", shopName);
        params.put("password", password);
        params.put("shopCode", shopCode);
        Log.i("TAG", "password = " + password);
        get(callback, Constant.REGISTER, params, RegisterBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 新增客户
     *
     * @param customerName    客户名称
     * @param customerType    客户类型 0个人1单位
     * @param sex             0男1女
     * @param mobilePhone     手机号
     * @param customerLevelId 客户级别
     * @param customerSource  客户来源
     * @param birthDate       出生日期
     * @param customerCompany 所在单位
     * @param remark          备注
     * @param contactName     联系人名称  （多个联系人用,分割，可以不填，联系人字段填 的话，就全部必填）
     * @param contactSex      联系人性别
     * @param contactMobile   联系人手机号
     * @param contactRelation 与联系人关系
     */
    public void saveCustomer(String customerName, String customerType, String sex, String mobilePhone,
                             String customerLevelId, String customerSource, String birthDate, String customerCompany,
                             String remark, String contactName, String contactSex, String contactMobile,
                             String contactRelation, String shopId,String nickname, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("customerName", customerName);
        params.put("customerType", customerType);
        params.put("sex", sex);
        params.put("mobilePhone", mobilePhone);
        params.put("customerLevelId", customerLevelId);
        params.put("customerSource", customerSource);
        params.put("birthDate", birthDate);
        params.put("customerCompany", customerCompany);
        params.put("remark", remark);
        params.put("contactName", contactName);
        params.put("contactSex", contactSex);
        params.put("contactMobile", contactMobile);
        params.put("contactRelation", contactRelation);
        params.put("nickname", nickname);
        params.put("shopId", shopId);
        get(callback, Constant.SAVECUSTOMER, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取商品一级分类
     *
     * @param callback
     */
    public void getProductCategory(String shopId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", shopId);
        get(callback, Constant.GETPRODUCTCATEGORY, params, GoodClassABean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取商品二级分类
     *
     * @param callback
     */
    public void getProductCategoryByCode(String code, String shopId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("code", code);
        params.put("shopId", shopId);
        get(callback, Constant.GETPRODUCTCATEGORYBYCODE, params, GoodClassABean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取商品二级分类下的商品列表
     *
     * @param serviceType
     * @param shopId
     * @return
     * @throws Exception
     */
    public GoodListBean getProductByCode(String serviceType, String shopId) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("serviceType", serviceType);
        params.put("shopId", shopId);
        return (GoodListBean) getSync(Constant.GETPRODUCTBYCODE, params, GoodListBean.class);
    }


    /**
     * 删除商品
     *
     * @param id
     * @param callback
     */
    public void deleteProduct(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.DELETEPRODUCT, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 新增服务
     *
     * @param name         名称
     * @param serviceType  分类id
     * @param drawType     提成类型0是百分比1是金额
     * @param drawPrice    提成值
     * @param servicePrice 价格
     * @param remark       备注
     * @param serviceImg   图片
     */
    public void saveService(String name, String shopId, String serviceType, String drawType, String drawPrice,
                            String servicePrice, String remark, String serviceImg, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("name", name);
        params.put("serviceType", serviceType);
        params.put("shopId", shopId);
        params.put("drawType", drawType);
        params.put("drawPrice", drawPrice);
        params.put("servicePrice", servicePrice);
        params.put("remark", remark);
        params.setHasFile(true);
        LogUtils.e(serviceImg);
        if (serviceImg != null) {
            File file = new File(serviceImg);
            if (file.exists()) {
                try {
                    params.put("serviceImg", file);
                    LogUtils.e("file:" + file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        post(callback, Constant.SAVESERVICE, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 删除服务
     *
     * @param id
     * @param callback
     */
    public void deleteSerive(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.DELETESERVICE, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 修改服务
     *
     * @param id
     * @param name
     * @param callback
     */
    public void updateService(String id, String name, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        params.put("name", name);
        get(callback, Constant.UPDATESERVICE, params, AddGoodBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 添加服务一二级分类
     *
     * @param code
     * @param name
     * @param callback
     */
    public void saveServiceCode(String shopId, String code, String name, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", shopId);
        params.put("code", code);
        params.put("name", name);
        get(callback, Constant.SAVESERVICECODE, params, AddGoodBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 添加商品
     *
     * @param internationalCode 条码
     * @param name              名称
     * @param brand             品牌
     * @param priceStandardSell 售价
     * @param type              0常备商品 1库存商品
     * @param sizeName          规格名称
     * @param sizeParm          规格参数
     * @param drawType          百分比和金额类型 0   1
     * @param drawPrice         百分比和金额的值
     * @param remark            备注
     * @param productImg        图片
     */
    public void saveProduct(String internationalCode, String name, String productType, String brand, String priceStandardSell, String type,
                            String sizeName, String sizeParm, String drawType, String drawPrice, String remark,
                            String productImg, String shopId, String safetyInventory, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("internationalCode", internationalCode);
        params.put("name", name);
        params.put("productType", productType);
        params.put("brand", brand);
        params.put("priceStandardSell", priceStandardSell);
        params.put("type", type);
        params.put("sizeName", sizeName);
        params.put("sizeParm", sizeParm);
        params.put("drawType", drawType);
        params.put("drawPrice", drawPrice);
        params.put("remark", remark);
        params.put("safetyInventory", safetyInventory);
        params.setHasFile(true);
        File file = new File(productImg);
        if (file.exists()) {
            try {
                params.put("productImg", file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        params.put("shopId", shopId);
        post(callback, Constant.SAVEPRODUCT, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 修改商品分类
     *
     * @param name
     * @param id
     * @param callback
     */
    public void updateTpCategory(String name, String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("name", name);
        params.put("id", id);
        get(callback, Constant.UPDATETPCATEGORY, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 添加商品一二级分类
     *
     * @param code
     * @param name
     * @param callback
     */
    public void saveProductParentCode(String code, String name, String shopId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("code", code);
        params.put("name", name);
        params.put("shopId", shopId);
        get(callback, Constant.SAVEPRODUCTPARENTCODE, params, AddGoodBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取技师一级分类
     *
     * @param callback
     */
    public void getServerClassA(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        get(callback, Constant.SELECTROLECOUNTLIST, params, ServerClassABean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取技师二级分类
     *
     * @param roleid
     * @param callback
     */
    public void getServerClassB(String roleid, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("roleid", roleid);
        get(callback, Constant.SELECTROLECANDUSERLIST, params, ServerClassBBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 提交审核
     *
     * @param userName         店主姓名
     * @param shopName         店铺姓名（必填）
     * @param idCard           身份证
     * @param mobilephone      手机（必填）
     * @param address          店铺地址
     * @param cardFrontPath    身份证正面照
     * @param cardNegativePath 身份证反面照
     * @param userid           用户编号（必填隐藏字段）
     * @param password         用很好密码（必填隐藏字段）
     */
    public void userAubmitAudit(String userName, String shopName, String idCard, String mobilephone,
                                String address, String cardFrontPath, String cardNegativePath, List<String> pics,
                                String userid, String password, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("userName", userName);
        params.put("shopName", shopName);
        params.put("idCard", idCard);
        params.put("mobilephone", mobilephone);
        params.put("address", address);
        params.put("cardFrontPath", cardFrontPath);
        params.put("cardNegativePath", cardNegativePath);
        params.put("userid", userid);
        params.put("password", password);
        params.setHasFile(true);
        for (int i = 0; i < pics.size(); i++) {
            File file = new File(pics.get(i));
            if (file.exists()) {
                try {
                    params.put("shopPicPath" + i + 1, file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        post(callback, Constant.USERAUBMITAUDIT, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 输入公里弹出美容项目
     *
     * @param callback
     */
    public void getCarMaintain(String carMileage, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("carMileage", carMileage);
        get(callback, Constant.GETCARMAINTAIN, params, GetCarMainTainBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 在店车辆
     *
     * @param typeName
     * @return
     */
    public CarInShopBean getCarProject(String typeName, String shopId) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("typeName", typeName);
        params.put("shopId", shopId);
        return (CarInShopBean) getSync(Constant.GETCARPROJECT, params, CarInShopBean.class);
    }

    /**
     * 在店车辆
     *
     * @param typeName
     * @return
     */
    public void getCarProject1(String typeName, String shopId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("typeName", typeName);
        params.put("shopId", shopId);
        get(callback, Constant.GETCARPROJECT, params, CarInShopBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    public void getCarProject2(String typeName, String shopId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("typeName", typeName);
        params.put("shopId", shopId);
        get(callback, Constant.GETCARPROJECT, params, CarInShopBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    public void getCarProject3(String typeName, String shopId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("typeName", typeName);
        params.put("shopId", shopId);
        get(callback, Constant.GETCARPROJECT, params, CarInShopBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    public void getCarProject4(String typeName, String shopId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("typeName", typeName);
        params.put("shopId", shopId);
        get(callback, Constant.GETCARPROJECT, params, CarInShopBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取施工单
     *
     * @param carNo
     * @return
     * @throws Exception
     */
    public WorkOrderBean getdocumentList(String carNo, String shopId) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("carNo", carNo);
        params.put("shopId", shopId);
        return (WorkOrderBean) getSync(Constant.GETDOCUMENTLIST, params, WorkOrderBean.class);
    }

    /**
     * 个人资料
     *
     * @param id
     * @param callback
     */
    public void personalData(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.SELECTBYIDQUERYUSERINFO, params, PersonalDataBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取价格
     *
     * @param goodsProject
     * @param callback
     */
    public void getPrice(String goodsProject, String shopId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("goodsProject", goodsProject);
        params.put("shopId", shopId);
        get(callback, Constant.GETPRICE, params, GetPriceBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 员工管理
     *
     * @param auditStatus
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public MemberBean auditStatus(String auditStatus, String pageNo, String pageSize) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("auditStatus", auditStatus);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        params.put("shopId", ac.shopId);
        return (MemberBean) getSync(Constant.EMPMANAGER, params, MemberBean.class);
    }

    /**
     * 员工详情
     *
     * @param id
     * @param callback
     */
    public void empManagerDetails(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.EMPMANAGERDETAILS, params, MemberDetailBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取员工角色权限
     *
     * @return
     * @throws Exception
     */
    public RoleBean selectEmpRoleListData() throws Exception {
        AjaxParams params = new AjaxParams();
        return (RoleBean) getSync(Constant.SELECTEMPROLELISTDATA, params, RoleBean.class);
    }

    /**
     * 获取职位列表
     *
     * @return
     * @throws Exception
     */
    public PositionBean findTsDepartAndCount() throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        return (PositionBean) getSync(Constant.FINDTSDEPARTANDCOUNT, params, PositionBean.class);
    }

    public void findTsDepartAndCount(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        get(callback, Constant.FINDTSDEPARTANDCOUNT, params, PositionBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 老板审核通过
     *
     * @param departed
     * @param status
     * @param userid
     * @param auditStatus
     * @param callback
     */
    public void updateEmpInfo(String roleid, String departed, String status, String userid, String auditStatus, String scale, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("roleid", roleid);
        params.put("departed", departed);
        params.put("status", status);
        params.put("userid", userid);
        params.put("auditStatus", auditStatus);
        params.put("scale", scale);
        get(callback, Constant.UPDATEEMPINFO, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 商品详情
     *
     * @param id
     * @param callback
     */
    public void findProduct(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        params.put("shopId", ac.shopId);
        get(callback, Constant.FINDPRODUCT, params, GoodDetailBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 服务详情
     *
     * @param id
     * @param callback
     */
    public void findService(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        params.put("shopId", ac.shopId);
        get(callback, Constant.FINDSERVICE, params, ServiceDetailBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 商品搜索结果页
     *
     * @return
     * @throws Exception
     */
    public GoodSearchResultBean findProductByName(String name, String shopId) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("name", name);
        params.put("shopId", shopId);
        return (GoodSearchResultBean) getSync(Constant.FINDPRODUCTBYNAME, params, GoodSearchResultBean.class);
    }

    /**
     * 服务搜索结果页
     *
     * @return
     * @throws Exception
     */
    public ServiceSearchResultBean findServiceByName(String name, String shopId) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("name", name);
        params.put("shopId", shopId);
        return (ServiceSearchResultBean) getSync(Constant.FINDSERVICEBYNAME, params, ServiceSearchResultBean.class);
    }
    public void findServiceByName(String name,ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("name", name);
        params.put("shopId", ac.shopId);
        get(callback, Constant.FINDSERVICEBYNAME, params, ServiceSearchResultBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 派工的时候，根据服务选择相对应部门的员工
     *
     * @param projectName
     * @param shopId
     * @return
     * @throws Exception
     */
    public GetUserListBean getUserList(String projectName, String shopId) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("projectName", projectName);
        params.put("shopId", shopId);
        return (GetUserListBean) getSync(Constant.GETUSERLIST, params, GetUserListBean.class);
    }

    /**
     * 派工和改派
     *
     * @param id
     * @param goodsProject
     * @param carNo
     * @param shopId
     * @param callback
     */
    public void saveOrUpdateCarDispatch(String id, String goodsProject, String carNo, String shopId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        params.put("goodsProject", goodsProject);
        params.put("carNo", carNo);
        params.put("shopId", shopId);
        get(callback, Constant.SAVEORUPDATECARDISPATCH, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 店铺列表查询
     *
     * @return
     * @throws Exception
     */
    public SelectShopListBean selectShopList() throws Exception {
        AjaxParams params = new AjaxParams();
        return (SelectShopListBean) getSync(Constant.SELECTSHOPLIST, params, SelectShopListBean.class);
    }

    /**
     * 获取车辆信息
     *
     * @param id
     * @param shopId
     * @param callback
     */
    public void getCarDetail(String id, String shopId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        params.put("shopId", shopId);
        get(callback, Constant.GETCARDETAIL, params, CarDetailBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 删除商品
     *
     * @param id
     * @param callback
     */
    public void deleteGoods(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.DELETEGOODS, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 修改商品
     *
     * @param internationalCode 条码
     * @param name              名称
     * @param brand             品牌
     * @param priceStandardSell 售价
     * @param type              0常备商品 1库存商品
     * @param sizeName          规格名称
     * @param sizeParm          规格参数
     * @param drawType          百分比和金额类型
     * @param drawPrice         百分比和金额的值
     * @param remark            备注
     * @param productImg        图片
     * @param productType       分类
     * @param callback
     */
    public void updateProduct(String id, String internationalCode, String name, String brand, String priceStandardSell,
                              String type, String sizeName, String sizeParm, String drawType, String drawPrice, String remark,
                              String productImg, String productType, String num, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("id", id);
        params.put("internationalCode", internationalCode);
        params.put("name", name);
        params.put("brand", brand);
        params.put("priceStandardSell", priceStandardSell);
        params.put("type", type);
        params.put("sizeName", sizeName);
        params.put("sizeParm", sizeParm);
        params.put("drawType", drawType);
        params.put("drawPrice", drawPrice);
        params.put("remark", remark);
        params.put("productType", productType);
        params.put("num", num);
        params.setHasFile(true);
        if(productImg!=null) {
            File file = new File(productImg);
            if (file.exists()) {
                try {
                    params.put("productImg", file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        post(callback, Constant.UPDATEPRODUCT, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 删除服务
     *
     * @param id
     * @param callback
     */
    public void deleteService(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.DELETESER, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 修改服务
     *
     * @param id
     * @param callback
     */
    public void updateSerive(String id, String name, String serviceType, String drawType, String drawPrice,
                             String servicePrice, String remark, String serviceImg, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("id", id);
        params.put("name", name);
        params.put("serviceType", serviceType);
        params.put("drawType", drawType);
        params.put("drawPrice", drawPrice);
        params.put("servicePrice", servicePrice);
        params.put("remark", remark);

        params.setHasFile(true);
        if (serviceImg != null) {
            File file = new File(serviceImg);
            if (file.exists()) {
                try {
                    params.put("serviceImg", file);
                    LogUtils.e("file:" + file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        post(callback, Constant.UPDATESERVICEDETAIL, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 派工的时候，选择部门的员工
     *
     * @param callback
     */
    public void getDepUserList(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        post(callback, Constant.GETDEPUSERLIST, params, WorkerTreeBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 上传头像
     *
     * @param callback
     */
    public void uploadPersonalAvatar(String userid, String headPortrait, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("userid", userid);
        File file = new File(headPortrait);
        if (file.exists()) {
            try {
                params.put("headPortrait", file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        post(callback, Constant.UPLOADPERSONALAVATAR, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 结算
     *
     * @param documentId
     * @param callback
     */
    public void closeAccount(String documentId, String workStatus, String payMethod, String onceCardNo,String price, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("documentId", documentId);
        params.put("workStatus", workStatus);
        params.put("payMethod", payMethod);
        params.put("onceCardNo", onceCardNo == null ? "" : onceCardNo);
        params.put("price",price);
        post(callback, Constant.CLOSEACCOUNT, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 修改车辆信息和修改服务项目
     *
     * @param callback
     */
    public void updateCarDetailAndService(String carNo, String brandId, String carFrameNo, String carPrice, String createDate,
                                          String compulsoryDate, String endDate, String goodsProject, String amount,
                                          String shopId, String documentId,String projectNumber,String projectPrice,ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("carNo", carNo);
        params.put("brandId", brandId);
        params.put("carFrameNo", carFrameNo);
        params.put("carPrice", carPrice);
        params.put("createDate", createDate);
        params.put("compulsoryDate", compulsoryDate);
        params.put("endDate", endDate);
        params.put("goodsProject", goodsProject);
        params.put("amount", amount);
        params.put("shopId", shopId);
        params.put("documentId", documentId);
        params.put("projectNumber",projectNumber);
        params.put("projectPrice",projectPrice);
        post(callback, Constant.UPDATECARDETAILANDSERVICE, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 修改员工
     */
    public void updatetempManager(String id, String status, String departId, String roleId, String scale, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        params.put("status", status);
        params.put("departId", departId);
        params.put("roleId", roleId);
        params.put("scale", scale);
        post(callback, Constant.UPDATEEMPMANAGER, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取公告
     *
     * @param callback
     */
    public void getLine(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        get(callback, Constant.GETLINE, params, LineBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 根据公告id返回详情
     *
     * @param id
     * @param callback
     */
    public void getLineDetail(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.GETLINEDETAIL, params, LineDetailBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取轮播
     */
    public void getBanner(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        get(callback, Constant.GETBANNER, params, BannerBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 根据轮播id返回详情
     *
     * @param id
     * @param callback
     */
    public void getBannerDetail(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.GETBANNERDETAIL, params, BannerDetailBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 车辆管理列表
     *
     * @param shopId 店铺id 必填
     * @param str    手机号或者车牌或者姓名
     */
    public void getCarList(String shopId, String str, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", shopId);
        params.put("str", str);
        get(callback, Constant.SELECTBYSTR, params, CarManageListBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 取消在店车辆
     *
     * @param callback
     */
    public void cancel(String documentId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("documentId", documentId);
        get(callback, Constant.CANCEL, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 取消在店车辆
     */
    public GetNameBean getName(String projects, String documentId) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("projects", projects);
        params.put("documentId", documentId);
        return (GetNameBean) getSync(Constant.GETNAME, params, GetNameBean.class);
    }

    /**
     * 员工或者老板绩效
     *
     * @param monthDate
     * @param userId    null则老板
     * @return
     * @throws Exception
     */
    public PerformanceBean getPerformance(String yearDate, String monthDate, String userId) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("uid", userId);
        params.put("monthDate", monthDate);
        params.put("yearDate", yearDate);
        return (PerformanceBean) getSync(Constant.GETDECUMENTBYUID, params, PerformanceBean.class);
    }

    /**
     * 员工或者老板绩效
     *
     * @param monthDate
     * @return
     * @throws Exception
     */
    public GetDecumentByCarnoBean getDecumentByCarno(String yearDate, String monthDate, String carNo) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("uid", ac.userId);
        params.put("monthDate", monthDate);
        params.put("yearDate", yearDate);
        params.put("carNo", carNo);
        return (GetDecumentByCarnoBean) getSync(Constant.GETDECUMENTBUCARNO, params, GetDecumentByCarnoBean.class);
    }

    /**
     * 仓库列表查询
     *
     * @return
     * @throws Exception
     */
    public StorageListBean getRepertoryList() throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        return (StorageListBean) getSync(Constant.GETREPERTORYLIST, params, StorageListBean.class);
    }

    public void getRepertoryList(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        get(callback, Constant.GETREPERTORYLIST, params, StorageListBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 删除仓库
     *
     * @param id
     * @param callback
     */
    public void deleteRepertory(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.DELETEREPERTORY, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 增加仓库
     *
     * @param callback
     */
    public void saveRepertory(String repertoryName, String remark, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("repertoryName", repertoryName);
        params.put("remark", remark);
        get(callback, Constant.SAVEREPERTORY, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 编辑仓库
     *
     * @param callback
     */
    public void updateRepertory(String repertoryName, String remark, String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("repertoryName", repertoryName);
        params.put("remark", remark);
        params.put("id", id);
        get(callback, Constant.UPDATEREPERTORY, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 供应商列表查询
     *
     * @param callback
     */
    public void getSupplierList(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        get(callback, Constant.GETSUPPLIERLIST, params, SupplierBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 供应商详情查询
     *
     * @param id       查询当条详情，没有则列出所有
     * @param callback
     */
    public void getSupplierListDetails(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.GETSUPPLIERDETAIL, params, SupplierDetailsBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 删除供应商
     *
     * @param id
     * @param callback
     */
    public void deletesupplier(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.DELETESUPPLIER, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 获取供应商列表带模糊查询
     *
     * @param supplierCompany
     * @param callback
     */
    public void findsupplierbyname(String supplierCompany, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("supplierCompany", supplierCompany);
        get(callback, Constant.FINDSUPPLIERBYNAME, params, SupplierBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 添加供应商
     *
     * @param supplierCompany
     * @param supplierName
     * @param mobilephoneNo
     * @param faxNo
     * @param address
     * @param remark
     * @param callback
     */
    public void savesupplier(String supplierCompany, String supplierName, String mobilephoneNo, String faxNo,
                             String address, String remark, String accountNo, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("supplierCompany", supplierCompany);
        params.put("supplierName", supplierName);
        params.put("mobilephoneNo", mobilephoneNo);
        params.put("faxNo", faxNo);
        params.put("address", address);
        params.put("remark", remark);
        params.put("accountNo", accountNo);
        params.put("shopId", ac.shopId);
        get(callback, Constant.SAVESUPPLIER, params, SupplierDetailsBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 修改供应商
     *
     * @param id
     * @param supplierCompany
     * @param supplierName
     * @param mobilephoneNo
     * @param faxNo
     * @param address
     * @param remark
     * @param callback
     */
    public void updatesupplier(String id, String supplierCompany, String supplierName, String mobilephoneNo, String faxNo,
                               String address, String remark, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        params.put("supplierCompany", supplierCompany);
        params.put("supplierName", supplierName);
        params.put("mobilephoneNo", mobilephoneNo);
        params.put("faxNo", faxNo);
        params.put("Address", address);
        params.put("Remark", remark);
        get(callback, Constant.UPDATESUPPLIER, params, SupplierDetailsBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 报价
     *
     * @param id
     * @return
     * @throws Exception
     */
    public SelectInfoOfferBean selectInfoOffer(String id) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        return (SelectInfoOfferBean) getSync(Constant.SELECTINFOOFFER, params, SelectInfoOfferBean.class);
    }

    /**
     * 查询修改车辆服务
     */
    public void selectModifyVehicleServiceDetails(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.SELECTMODIFYVEHICLESERVICEDETAILS, params, CarInfoBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 车辆历史信息消费记录
     */
    public void selectRecordsOfConsumption(String carNo, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("carNo", carNo);
        get(callback, Constant.SELECTRECORDSOFCONSUMPTION, params, ConsumBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 车辆历史信息消费记录
     */
    public void selectPickUpTheCarRecord(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.SELECTPICKUPTHECARRECORD, params, CarRecordBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 查询店铺对应的商品列表
     */
    public QueryAllGoodsBean selectProductByShopId() throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        return (QueryAllGoodsBean) getSync(Constant.SELECTPRODUCTBYSHOPID, params, QueryAllGoodsBean.class);
    }

    public void selectProductByShopId(String name, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("name", name);
        get(callback, Constant.SELECTPRODUCTBYSHOPID, params, QueryAllGoodsBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 采购
     *
     * @param supplierId
     * @param logisticsCompany
     * @param logisticsCode
     * @param remark
     * @param stock
     * @param goodsId
     */
    public void savePurchase(String supplierId, String logisticsCompany, String logisticsCode, String remark,
                             String stock, String goodsId, String price, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("supplierId", supplierId);
        params.put("logisticsCompany", logisticsCompany);
        params.put("logisticsCode", logisticsCode);
        params.put("remark", remark);
        params.put("stock", stock);
        params.put("goodsId", goodsId);
        params.put("price", price);
        get(callback, Constant.SAVEPURCHASE, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 查询店铺对应的商品列表
     */
    public PurchaseHistoryBean selectCarPurchase(String purchaseStutas, String purchaseName) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("purchaseStutas", purchaseStutas);
        params.put("purchaseName", purchaseName);
        return (PurchaseHistoryBean) getSync(Constant.SELECTCARPURCHASE, params, PurchaseHistoryBean.class);
    }

    /**
     * 智能提醒列表
     */
    public RemindBean selectCarRemindlist() throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        return (RemindBean) getSync(Constant.SELECTCARREMINDLIST, params, RemindBean.class);
    }

    /**
     * 智能提醒详情
     *
     * @param id
     * @param callback
     */
    public void selectCarRemindlistInfo(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.SELECTACAREMINDLISTINFO, params, RemindDetailBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 提车
     *
     * @param documentId
     * @param callback
     */
    public void pickCar(String documentId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("documentId", documentId);
        params.put("workStatus", "3");
        get(callback, Constant.PICKCAR, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 创建提醒
     *
     * @param callback
     */
    public void saveIntelligentReminder(String carNo, String customerNo, String maturityDate, String reminderDetails, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("carNo", carNo);
        params.put("customerNo", customerNo);
        params.put("maturityDate", maturityDate);
        params.put("reminderDetails", reminderDetails);
        get(callback, Constant.SAVEINTELLIGENTREMINDER, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 查询捡车项目列表
     */
    public void selectCarInspectProjectData(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.SELECTCARINSPECTPROJECTDATA, params, CarInspectProjectBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 检车
     */
    public void saveInspectionVehicle(String id, String entryName, String checkRemarks, String detectionOpinion,
                                      String conclusion, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        params.put("entryName", entryName);
        params.put("checkRemarks", checkRemarks);
        params.put("detectionOpinion", detectionOpinion);
        params.put("conclusion", conclusion);
        get(callback, Constant.SAVEINSPECTIONVEHICLE, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 点击历史进店后展示的车辆信息 和车辆管理的车辆列表详情
     */
    public void getCarnewDetail(String carId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("carId", carId);
        params.put("shopId", ac.shopId);
        get(callback, Constant.GETCARNEWDETAIL, params, GetCarnewDetailBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 车辆管理修改车辆信息
     *
     * @param carNo
     * @param brandId
     * @param carFrameNo
     * @param carPrice
     * @param createDate
     * @param compulsoryDate
     * @param endDate
     * @param callback
     */
    public void updatecar(String carNo, String brandId, String carFrameNo, String carPrice, String createDate,
                          String compulsoryDate, String endDate, String engineNo, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("carNo", carNo);
        params.put("brandId", brandId);
        params.put("carFrameNo", carFrameNo);
        params.put("carPrice", carPrice);
        params.put("createDate", createDate);
        params.put("compulsoryDate", compulsoryDate);
        params.put("endDate", endDate);
        params.put("engineNo", engineNo);
        params.put("shopId", ac.shopId);
        get(callback, Constant.UPDATECAR, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 关闭提醒
     *
     * @param id
     * @param carState
     * @param callback
     */
    public void updateIntelligentReminder(String id, String carState, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        params.put("carState", carState);
        get(callback, Constant.UPDATEINTELLIGENTREMINDER, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 采购详情
     *
     * @param id
     * @param callback
     */
    public void selectCarPurchaseDetail(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        post(callback, Constant.SELECTCARPURCHASEDETAIL, params, PurchaseDetailBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 查询待入库列表
     */
    public WaitWareHouseBean selectCarPurchaseDetailList() throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        return (WaitWareHouseBean) getSync(Constant.SELECTCARPURCHSEDETAILLIST, params, WaitWareHouseBean.class);
    }

    /**
     * 待入库撤单
     *
     * @param id
     * @param remark
     * @param callback
     */
    public void updateCarPurchaseStatus(String id, String remark, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        params.put("remark", remark);
        get(callback, Constant.UPDATECARPURCHASESTATUS, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 领料人
     *
     * @param callback
     */
    public void selectCurrentStoreUserData(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        get(callback, Constant.SELECTCURRENTSTOREUSERDATA, params, CurrentStoreUserBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 领料出库
     *
     * @param callback
     */
    public void updateCarPurchaseRecordBatch(String userId, String id, String remark, String num,
                                             String supplierId, String payType, String returnPrice,ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("userId", userId);
        params.put("id", id);
        params.put("remark", remark);
        params.put("num", num);
        params.put("supplierId", supplierId);
        params.put("payType", payType);
        params.put("returnPrice",returnPrice==null?"":returnPrice);
        Log.i("TAG","returnPrice = "+returnPrice);
        get(callback, Constant.UPDATECARPURCHASERECORDBATCH, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 领料出库商品列表
     */
    public SelectOutboundGoodslistBean selectOutboundGoodslist(String name, String productType,
                                                               String repertoryId) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("name", name);
        params.put("productType", productType);
        params.put("repertoryId", repertoryId);
        return (SelectOutboundGoodslistBean) getSync(Constant.SELECTOUTBOUNDGOODSLIST, params, SelectOutboundGoodslistBean.class);
    }

    /**
     * 直接入库
     *
     * @param ids
     * @param payTpe
     * @param payPrice
     * @param priceType
     * @param remark
     * @param repertoryId
     * @param callback
     */
    public void saveInsertPurchase(String payTpe, String payPrice, String priceType,
                                   String remark, String repertoryId, String supplierId, String logisticsCompany, String logisticsCode, String stock, String goodsId, String price, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("payTpe", payTpe);
        params.put("payPrice", payPrice);
        params.put("priceType", priceType);
        params.put("remark", remark);
        params.put("repertoryId", repertoryId);
        params.put("supplierId", supplierId);
        params.put("logisticsCompany", logisticsCompany);
        params.put("logisticsCode", logisticsCode);
        params.put("stock", stock);
        params.put("goodsId", goodsId);
        params.put("price", price);
        get(callback, Constant.SAVEINSERTPURCHASE, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 采购退货
     *
     * @param ids
     * @param payTpe
     * @param payPrice
     * @param priceType
     * @param remark
     * @param repertoryId
     * @param callback
     */
    public void updatePurchaseAcountRepertory(String ids, String payTpe, String payPrice, String priceType,
                                              String remark, String repertoryId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("ids", ids);
        params.put("payTpe", payTpe);
        params.put("payPrice ", payPrice);
        params.put("priceType ", priceType);
        params.put("remark ", remark);
        params.put("repertoryId ", repertoryId);
        get(callback, Constant.UPDATEPURCHASEACOUNTREPERTORY, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 入库
     *
     * @param ids
     * @param payTpe
     * @param payPrice
     * @param priceType
     * @param remark
     * @param repertoryId
     * @param callback
     */
    public void savePurchaseStatus(String ids, String payTpe, String payPrice, String priceType, String remark, String repertoryId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("ids", ids);
        params.put("payTpe", payTpe);
        params.put("payPrice", payPrice + "");
        params.put("priceType", priceType);
        params.put("remark", remark);
        params.put("repertoryId", repertoryId);
        get(callback, Constant.SAVEPURCHASESTATUS, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 通过code获取库存
     *
     * @param product_code
     * @return
     * @throws Exception
     */
    public GetWarehouseByCodeBean getWarehouseBy(String product_code) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("product_code", product_code);
        return (GetWarehouseByCodeBean) getSync(Constant.GETWAREHOUSEBYCODE, params, GetWarehouseByCodeBean.class);
    }

    /**
     * 获取出入库记录列表
     *
     * @return
     * @throws Exception
     */
    public OutIntHistoryBean getOupInpHistory(String str, String state) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("str", str);
        params.put("state", state);
        return (OutIntHistoryBean) getSync(Constant.GETOUPINPHISTORY, params, OutIntHistoryBean.class);
    }

    /**
     * 出入库详情
     *
     * @param id
     * @param callback
     */
    public void getOupIntHistoryDetails(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.SELECTCARENTRYRECORDSBYID, params, OutIntHistoryDetailsBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 待入库-入库商品列表
     *
     * @param callback
     */
    public void selectPurchaseDetailList(String ids, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("ids", ids);
        get(callback, Constant.SELECTPURCHASEDETAILLIST, params, SelectPurchaseDetailListBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取次卡类型
     *
     * @return
     * @throws Exception
     */
    public CardRestrictCarnoBean getOnceCardType() throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        return (CardRestrictCarnoBean) getSync(Constant.GETONCECARDTYPE, params, CardRestrictCarnoBean.class);
    }

    public void getOnceCardType(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        get(callback, Constant.GETONCECARDTYPE, params, CardRestrictCarnoBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 新增消费卡
     *
     * @param consumeCardNo
     * @param consumeCardName
     * @param mobile
     * @param residualAmount
     * @param customerId
     * @param callback
     */
    public void saveConsumeCard(String consumeCardNo, String consumeCardName, String mobile, String residualAmount, String customerId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("consumeCardNo", consumeCardNo);
        params.put("consumeCardName", consumeCardName);
        params.put("mobile", mobile);
        params.put("residualAmount", residualAmount);
        params.put("customerId", customerId);
        get(callback, Constant.SAVECONSUMECARD, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 库存盘点列表
     *
     * @return
     * @throws Exception
     */
    public InventoryVerificationBean selectShout(String shoutId) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("shoutId", shoutId);
        return (InventoryVerificationBean) getSync(Constant.SELECTSHOUT, params, InventoryVerificationBean.class);
    }


    /**
     * 统计当天的总收入或者总支出
     *
     * @param dateTime
     * @param callback
     */
    public void selectCarIncomeSpendingDayTotalPrice(String dateTime, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("dateTime", dateTime);
        get(callback, Constant.SELECTCARINCOMESPENDINGDAYTOTALPRICE, params, IncomeExpenditureDean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 统计当月的总收入或者总支出
     *
     * @param dateTime
     * @param callback
     */
    public void selectCarIncomeSpendingMonthTotalPrice(String dateTime, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("dateTime", dateTime);
        get(callback, Constant.SELECTCARINCOMESPENDINGMONTHTOTALPRICE, params, IncomeExpenditureDean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 当日收入详情
     *
     * @return
     * @throws Exception
     */
    public void selectCarIncomeDayByTime(String dateTime, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("dateTime", dateTime);
        get(callback, Constant.SELECTCARINCOMEDAYBYTIME, params, IncomeDetailsBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 当日支出详情
     *
     * @return
     * @throws Exception
     */
    public void selectCarSpendingDayByTime(String dateTime, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("dateTime", dateTime);
        get(callback, Constant.SELECTCARSPENDINGDAYBYTIME, params, SpendingDetailBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 当月收入详情
     *
     * @return
     * @throws Exception
     */
    public void selectCarIncomeMonthByTime(String dateTime, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("dateTime", dateTime);
        get(callback, Constant.SELECTCARINCOMEMONTHBYTIME, params, IncomeDetailsBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 当月支出详情
     *
     * @return
     * @throws Exception
     */
    public void selectCarSpendingMonthByTime(String dateTime, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("dateTime", dateTime);
        get(callback, Constant.SELECTCARSPENDINGMONTHBYTIME, params, SpendingDetailBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 收入或支出录入
     *
     * @param name
     * @param type
     * @param price
     * @param state
     * @param remake
     * @param callback
     */
    public void savaCarIncomeSpending(String name, String type, String price, String state, String remake, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("name", name);
        params.put("type", type);
        params.put("price", price);
        params.put("state", state);
        params.put("remake", remake);
        get(callback, Constant.SAVACARINCOMESPENDING, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 新增次卡
     *
     * @param name
     * @param price
     * @param limit
     * @param serviceWithTimes
     * @param remark
     * @param callback
     */
    public void saveOnceCardType(String name, String price, String limit, String serviceWithTimes, String remark, ApiCallback callback) {

        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("name", name);
        params.put("limit", limit);
        params.put("price", price);
        params.put("serviceWithTimes", serviceWithTimes);
        params.put("remark", remark);
        get(callback, Constant.SAVEONCECARDTYPE, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 会员管理
     *
     * @param callback
     */
    public void selectMemberManagementMemberber(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        get(callback, Constant.SELECTMEMBERMANAGEMENTMEMBERBER, params, MemberManagementBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 次卡详情
     *
     * @param callback
     */
    public void getOnceCardTypeDetail(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.GETONCECARDTYPEDETAIL, params, OpenTimeCardDetailBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 开次卡
     *
     * @param onceCardNo
     * @param onceCardName
     * @param mobile
     * @param cardTypeId
     * @param customerId
     * @param carNo
     * @param callback
     */
    public void saveOnceCard(String onceCardNo, String onceCardName, String mobile, String cardTypeId, String customerId, String carNo,String expirationMonth, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("onceCardNo", onceCardNo);
        params.put("onceCardName", onceCardName);
        params.put("mobile", mobile);
        params.put("cardTypeId", cardTypeId);
        params.put("customerId", customerId);
        params.put("carNo", carNo);
        params.put("expirationMonth", expirationMonth==null?"":expirationMonth);
        get(callback, Constant.SAVEONCECARD, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取消费卡
     *
     * @param callback
     */
    public void getConsumeCard(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        get(callback, Constant.GETCONSUMECARD, params, ConsumeCardBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 充值
     *
     * @param callback
     */
    public void rechargeTheCard(String consumeCardNo, String residualAmount, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("consumeCardNo", consumeCardNo);
        params.put("residualAmount", residualAmount);
        get(callback, Constant.RECHARGETHECARD, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 采购详情修改
     *
     * @param callback
     */
    public void updateCarPurchaseDetail(String id, String logisticsCompany, String logisticsCode, String remark, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        params.put("logisticsCompany", logisticsCompany);
        params.put("logisticsCode", logisticsCode);
        params.put("remark", remark);
        get(callback, Constant.UPDATECARPURCHASEDETAIL, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 余额不足列表
     *
     * @return
     * @throws Exception
     */
    public LackOfBalanceBean selectConsumeCardInsufficient(String consumeCardNo) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("consumeCardNo", consumeCardNo);
        return (LackOfBalanceBean) getSync(Constant.SELECTCONSUMECARDINSUFFICIENT, params, LackOfBalanceBean.class);
    }

    /**
     * 余次不足列表
     *
     * @return
     * @throws Exception
     */
    public OverTimeLessThanBean selectOverTimeLessThan(String onceCardNo) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("onceCardNo", onceCardNo);
        return (OverTimeLessThanBean) getSync(Constant.SELECTOVERTIMELESSTHAN, params, OverTimeLessThanBean.class);
    }

    /**
     * 即将过期列表
     *
     * @return
     * @throws Exception
     */
    public WillExpireBean selectWillExpire(String cardNo) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("cardNo", cardNo);
        return (WillExpireBean) getSync(Constant.SELECTWILLEXPIRE, params, WillExpireBean.class);
    }

    /**
     * 已过期列表
     *
     * @return
     * @throws Exception
     */
    public WillExpireBean selectExpired(String cardNo) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("cardNo", cardNo);
        return (WillExpireBean) getSync(Constant.SELECTEXPIRED, params, WillExpireBean.class);
    }

    /**
     * 在店车辆的车辆信息客户选择更新
     *
     * @param id
     * @param customerId
     * @param callback
     */
    public void updateDocumentCustomer(String id, String customerId, String cid, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        params.put("customerId", customerId);
        params.put("cid", cid);
        get(callback, Constant.UPDATEDOCUMENTCUSTOMER, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 次卡列表
     *
     * @param carNo
     * @return
     * @throws Exception
     */
    public TimeCardListBean selectTimeCardlist(String carNo) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("carNo", carNo);
        return (TimeCardListBean) getSync(Constant.SELECTTIMECARDLIST, params, TimeCardListBean.class);
    }

    /**
     * 充值卡列表
     *
     * @param consumeCardNo
     * @return
     * @throws Exception
     */
    public PreloadedCardsListBean selectOfPreloadedCardslist(String consumeCardNo) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("consumeCardNo", consumeCardNo);
        return (PreloadedCardsListBean) getSync(Constant.SELECTOFPRELOADEDCARDSLIST, params, PreloadedCardsListBean.class);
    }

    /**
     * 有限卡列表
     *
     * @param carNo
     * @return
     * @throws Exception
     */
    public LimitedOfCardsListBean selectLimitedOfCardslist(String carNo) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("carNo", carNo);
        return (LimitedOfCardsListBean) getSync(Constant.SELECTLIMITEDOFCARDSLIST, params, LimitedOfCardsListBean.class);
    }

    /**
     * 无限卡列表
     *
     * @param carNo
     * @return
     * @throws Exception
     */
    public UnlimitedListBean selectUnlimitedlist(String carNo) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("carNo", carNo);
        return (UnlimitedListBean) getSync(Constant.SELECTUNLIMITEDLIST, params, UnlimitedListBean.class);
    }


    /**
     * 已关闭提醒列表
     *
     * @param stateName
     * @return
     * @throws Exception
     */
    public RemindShutDownBean selectCarRemindShutDownlist(String stateName) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("stateName", stateName);
        return (RemindShutDownBean) getSync(Constant.SELECTCARREMINDSHUTDOWNLIST, params, RemindShutDownBean.class);
    }

    /**
     * 新增盘点
     *
     * @param goodsid
     * @param repertoryId
     * @param shoutStock
     * @param stock
     * @param callback
     */
    public void saveShout(String goodsid, String repertoryId, String shoutStock, String stock, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("uid", ac.userId);
        params.put("goodsid", goodsid);
        params.put("repertoryId", repertoryId);
        params.put("shoutStock", shoutStock);
        params.put("stock", stock);
        get(callback, Constant.SAVESHOUT, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 盘点详情
     *
     * @param id
     * @param callback
     */
    public void selectShoutDetail(String id, String shoutOk, String shoutNo, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        params.put("shoutOk", shoutOk == null ? "" : shoutOk);
        params.put("shoutNo", shoutNo == null ? "" : shoutNo);
        get(callback, Constant.SELECTSHOUTDETAIL, params, InventoryVerificationDetailBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 当前用户拥有的次卡列表
     *
     * @param customerId
     * @return
     * @throws Exception
     */
    public void selectOnceCarByCustomerIdIdAndShopId(String customerId, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("customerId", customerId);
        get(callback, Constant.SELECTONCECARBYCUSTOMERIDIDANDSHOPID, params, OnceCarByCustomerBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 其他出库
     *
     * @param id
     * @param remark
     * @param num
     * @param supplierId
     * @param payType
     * @param callback
     */
    public void updatePurchase(String id, String remark, String num, String supplierId, String payType, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("id", id);
        params.put("remark", remark);
        params.put("num", num);
        params.put("supplierId", supplierId);
        params.put("payType", payType);
        get(callback, Constant.UPDATEPURCHASE, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 版本更新数据查询
     *
     * @param callback
     */
    public void upgradeData(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        get(callback, Constant.UPGRADEDATA, params, Version.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 提醒设置查询
     *
     * @param callback
     */
    public void selectCarRemindSet(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        get(callback, Constant.SELECTCARREMINDSET, params, CarRemindSetBean.class, getMethodName(Thread.currentThread().getStackTrace()));

    }

    /**
     * 提醒设置提交
     *
     * @param maintenanceState      保养状态为1开启，0关闭（int）
     * @param maintenanceDate       保养到期时间为月份（int）
     * @param maintenanceRemindDate 保养提醒时间天数（int）
     * @param insuranceState        保险状态为1开启，0关闭（int）
     * @param insuranceDate         保险到期时间为月份（int）
     * @param insuranceRemindDate   保险提醒时间天数（int）
     * @param annualState           年检状态为1开启，0关闭（int）
     * @param annualDate            年检到期时间为月份（int）
     * @param annualRemindDate      年检提醒时间天数（int）
     * @param inspectionState       车检状态为1开启，0关闭（int）
     * @param inspectionDate        车检到期时间为月份（int）
     * @param inspectionRemindDate  车检提醒时间天数（int）
     */
    public void savecarRemindSet(int maintenanceState, int maintenanceDate, int maintenanceRemindDate, int insuranceState, int insuranceDate, int insuranceRemindDate,
                                 int annualState, int annualDate, int annualRemindDate, int inspectionState, int inspectionDate, int inspectionRemindDate, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("maintenanceState", String.valueOf(maintenanceState));
        params.put("maintenanceDate", String.valueOf(maintenanceDate));
        params.put("maintenanceRemindDate", String.valueOf(maintenanceRemindDate));
        params.put("insuranceState", String.valueOf(insuranceState));
        params.put("insuranceDate", String.valueOf(insuranceDate));
        params.put("insuranceRemindDate", String.valueOf(insuranceRemindDate));
        params.put("annualState", String.valueOf(annualState));
        params.put("annualDate", String.valueOf(annualDate));
        params.put("annualRemindDate", String.valueOf(annualRemindDate));
        params.put("inspectionState", String.valueOf(inspectionState));
        params.put("inspectionDate", String.valueOf(inspectionDate));
        params.put("inspectionRemindDate", String.valueOf(inspectionRemindDate));
        get(callback, Constant.SAVECARREMINDSET, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 删除卡种
     *
     * @param id
     * @param callback
     */
    public void deleteOnceCardType(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.DELETEONCECARDTYPE, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 客户详情
     *
     * @param id
     * @param callback
     */
    public void selectCustomerByIdInfonation(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.SELECTCUSTOMERBYIDINFONATION, params, CustomerDetailBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 更新客户
     *
     * @param customerName
     * @param customerType
     * @param sex
     * @param mobilePhone
     * @param customerLevelId
     * @param customerSource
     * @param birthDate
     * @param customerCompany
     * @param remark
     * @param contactName
     * @param contactSex
     * @param contactMobile
     * @param contactRelation
     * @param callback
     */
    public void updateCustomer(String id, String customerName, String customerType, String sex, String mobilePhone,
                               String customerLevelId, String customerSource, String birthDate, String customerCompany,
                               String remark, String contactName, String contactSex, String contactMobile,
                               String contactRelation,String nickname, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        params.put("customerName", customerName);
        params.put("customerType", customerType);
        params.put("sex", sex);
        params.put("mobilePhone", mobilePhone);
        params.put("customerLevelId", customerLevelId);
        params.put("customerSource", customerSource);
        params.put("birthDate", birthDate);
        params.put("customerCompany", customerCompany);
        params.put("remark", remark);
        params.put("contactName", contactName);
        params.put("contactSex", contactSex);
        params.put("contactMobile", contactMobile);
        params.put("contactRelation", contactRelation);
        params.put("nickname", nickname);
        get(callback, Constant.UPDATECUSTOMER, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 删除车辆
     *
     * @param id
     * @param callback
     */
    public void deleteCarById(String id, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.DELETECARBYID, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 查询订货商品品类
     *
     * @param callback
     */
    public void SelectOrderClassification(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        get(callback, Constant.SELECTORDERCLASSIFICATION, params, OrderClassificationBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 查询订货商品推荐
     *
     * @param callback
     */
    public void SelectOrderRecommended(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        get(callback, Constant.SELECTORDERRECOMMENDED, params, OrderRecommendedBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 根据订货品类查询商品
     *
     * @param id
     * @param productName
     * @return
     */
    public ChooseOrderGoodBean SelectProductByClassification(String id, String goodId,String productName) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("id", id);
        params.put("productName", productName);
        params.put("goodId", goodId);
        return (ChooseOrderGoodBean) getSync(Constant.SELECTPRODUCTBYCLASSIFICATION, params, ChooseOrderGoodBean.class);
    }

    /**
     * 订货提交
     * @param userId
     * @param totalPrice
     * @param price
     * @param goodNum
     * @param goodId
     * @param callback
     */
    public void saveplaceAnOrder(String totalPrice, String price, String goodNum, String goodId,ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("userId", ac.userId);
        params.put("totalPrice", totalPrice);
        params.put("price", price);
        params.put("goodNum", goodNum);
        params.put("goodId", goodId);
        get(callback, Constant.SAVEPLACEANORDER, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 订单列表
     * @return
     * @throws Exception
     */
    public OrderPlaceBean SelectOrderPlace(String status) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("status",status==null?"":status);
        return (OrderPlaceBean) getSync(Constant.SELECTORDERPLACE, params, OrderPlaceBean.class);
    }

    /**
     * 订单详情
     * @param id
     * @param callback
     */
    public void selectOrderPlaceById(String id,ApiCallback callback){
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.SELECTORDERPLACEBYID, params, OrderPlaceDetailBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * id查询商品
     * @param id
     * @param callback
     */
    public void SelectProductById(String id,ApiCallback callback){
        AjaxParams params = new AjaxParams();
        params.put("id", id);
        get(callback, Constant.SELECTPRODUCTBYID, params, ProductsInfo.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 订货广告图片
     * @param callback
     */
    public void selectByOrderAdvertisement(ApiCallback callback){
        AjaxParams params = new AjaxParams();
        get(callback, Constant.SELECTBYORDERADVERTISEMENT, params, OrderAdvertisementBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 删除我的订单
     * @param id
     * @param callback
     */
    public void deleteplaceAnOrder(String id,ApiCallback callback){
        AjaxParams params = new AjaxParams();
        params.put("id",id);
        get(callback,Constant.DELETEPLACEANORDER,params,NetResult.class,getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 服务热门推荐
     * @param callback
     */
    public void selectJfomCategory(ApiCallback callback){
        AjaxParams params = new AjaxParams();
        params.put("shopId",ac.shopId);
        get(callback,Constant.SELECTJFOMCATEGORY,params,ServerHotBean.class,getMethodName(Thread.currentThread().getStackTrace()));
    }
    public ServerHotBean selectJfomCategory(String name) throws Exception{
        AjaxParams params = new AjaxParams();
        params.put("shopId",ac.shopId);
        params.put("name",name);
        return (ServerHotBean) getSync(Constant.SELECTJFOMCATEGORY, params, ServerHotBean.class);
    }

    /**
     * 微信支付
     * @param orderId
     * @param callback
     */
    public void WXPay(String orderId,ApiCallback callback){
        AjaxParams params = new AjaxParams();
        params.put("orderId",orderId);
        post(callback,Constant.WXPAY,params,WeiXinPayBean.class,getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 公众号列表
     * @return
     * @throws Exception
     */
    public PublicAppointmentBean selectWeiXinAppList()throws Exception{
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        return (PublicAppointmentBean) getSync(Constant.SELECTWEIXINAPPLIST, params, PublicAppointmentBean.class);
    }
    public void selectWeiXinAppList(ApiCallback callback){
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        get(callback,Constant.SELECTWEIXINAPPLIST,params,PublicAppointmentBean.class,getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 删除客户信息
     * @param id
     * @param callback
     */
    public void deleteCustomerById(String id,ApiCallback callback){
        AjaxParams params = new AjaxParams();
        params.put("id",id);
        get(callback,Constant.DELETECUSTOMERBYID,params,NetResult.class,getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 根据车牌号查客户
     * @param carNo
     * @param callback
     */
    public void selectCarAndDocumentByCarNo(String carNo,ApiCallback callback){
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("carNo", carNo);
        get(callback,Constant.SELECTCARANDDOCUMENTBYCARNO,params,CarAndDocumentBean.class,getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 查询定位救援列表
     * @return
     * @throws Exception
     */
    public LocateTheRescueBean selectPositioningTheRescue() throws Exception{
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        return (LocateTheRescueBean) getSync(Constant.SELECTPOSITIONINGTHERESCUE, params, LocateTheRescueBean.class);
    }
    public void selectPositioningTheRescue(ApiCallback callback){
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        get(callback,Constant.SELECTPOSITIONINGTHERESCUE,params,LocateTheRescueBean.class,getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 添加热门推荐
     * @param id
     * @param callback
     */
    public void saveJfomCategory(String id,ApiCallback callback){
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("id", id);
        get(callback,Constant.SAVEJFOMCATEGORY,params,NetResult.class,getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 删除热门服务
     * @param id
     */
    public void deleteJfomCategory(String id,ApiCallback callback){
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("id", id);
        get(callback,Constant.DELETEJFOMCATEGORY,params,NetResult.class,getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 新增自定义服务
     * @param name
     * @param serviceType
     * @param drawType
     * @param servicePrice
     * @param drawPrice
     * @param frequency
     * @param callback
     */
    public void saveSeriveCustom(String name,String serviceType,String drawType,String servicePrice,String drawPrice,String frequency,ApiCallback callback){
        AjaxParams params = new AjaxParams();
        params.put("shopId", ac.shopId);
        params.put("name", name);
        params.put("serviceType", serviceType);
        params.put("drawType", drawType);
        params.put("servicePrice", servicePrice);
        params.put("drawPrice", drawPrice);
        params.put("frequency", frequency);
        get(callback,Constant.SAVESERIVECUSTOM,params,ServiceCustomResultBean.class,getMethodName(Thread.currentThread().getStackTrace()));
    }

}

