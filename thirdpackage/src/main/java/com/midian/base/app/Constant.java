package com.midian.base.app;

/**
 * Created by WeiQingFeng on 2017/5/10.
 */

public class Constant {
    public final static String OCRKey = "BBFpSJN7UPX8op4S1tG6w7";
    public final static String OCRSecret = "d883f7f6d0024cbaadebea60f5531a97";
    public final static String OCRURL = "http://netocr.com/api/recogliu.do";

    public final static String BASEIMG = "http://120.25.218.134:8090/CarCare/";
    public final static String BASE = "http://192.168.0.115:8091/";
    public final static String BASEURL = "http://192.168.0.115:8091/app/car/";
    public final static String BASEWXURL = "http://192.168.0.115:8091/app/pay/";
    /*public final static String BASE = "http://120.25.218.134:8080/redRainInterface/";
    public final static String BASEURL = "http://120.25.218.134:8080/redRainInterface/app/car/";
    public final static String BASEWXURL = "http://120.25.218.134:8080/redRainInterface/app/pay/";*/


    /**
     * 登录
     */
    public final static String LOGIN = BASE + "app/user/login";

    /**
     * 获取车类别
     */
    public final static String GETCARTYPE = BASEURL + "getBrand";

    /**
     * 条件查询获取客户列表
     */
    public final static String GETCUSTOMERLISTBYQUERY = BASEURL + "getCustomerListByNameOrPhone";

    /**
     * 获取客户级别
     */
    public final static String GETCUSTOMERLEVEL = BASEURL + "getCustomerLevel";

    /**
     * 获取客户来源
     */
    public final static String GETCUSTOMERSOURCE = BASEURL + "getCustomerSource";

    /**
     * 获取服务一级分类
     */
    public final static String GETCATEGORY = BASEURL + "getCategory";

    /**
     * 获取服务二级分类
     */
    public final static String GETCATEGORYBYCODE = BASEURL + "getCategoryByCode";

    /**
     * 获取服务二级分类下的商品
     */
    public final static String GETSERVICEBYCODE = BASEURL + "getServiceByCode";

    /**
     * 添加服务一二级分类
     */
    public final static String SAVESERVICECODE = BASEURL + "saveSeriviceCode";

    /**
     * 注册
     */
    public final static String REGISTER = BASEURL + "userRegister";

    /**
     * 输入里程数时获取上次的保养记录的服务（商品）
     */
    public final static String CARMAINTAINRECORD = BASEURL + "carMaintainRecord";

    /**
     * 输入公里弹出美容项目
     */
    public final static String GETCARMAINTAIN = BASEURL + "getCarMaintain";

    /**
     * 添加新车
     */
    public final static String SAVECAR = BASEURL + "saveCar";

    /**
     * 新增客户
     */
    public final static String SAVECUSTOMER = BASEURL + "saveCustomer";

    /**
     * 新增服务
     */
    public final static String SAVESERVICE = BASEURL + "saveSerive";

    /**
     * 删除服务分类
     */
    public final static String DELETESERVICE = BASEURL + "deleteTsCategory";

    /**
     * 修改服务分类
     */
    public final static String UPDATESERVICE = BASEURL + "updateTsCategory";

    /**
     * 获取商品一级分类
     */
    public final static String GETPRODUCTCATEGORY = BASEURL + "getProductCategory";

    /**
     * 获取商品二级分类
     */
    public final static String GETPRODUCTCATEGORYBYCODE = BASEURL + "getProductCategoryByCode";

    /**
     * 获取商品二级分类下的商品列表
     */
    public final static String GETPRODUCTBYCODE = BASEURL + "getProductByCode";

    /**
     * 修改商品
     */
    public final static String UPDATETPCATEGORY = BASEURL + "updateTpCategory";

    /**
     * 删除商品分类
     */
    public final static String DELETEPRODUCT = BASEURL + "deleteTpCategory";


    /**
     * 新增商品
     */
    public final static String SAVEPRODUCT = BASEURL + "saveProduct";

    /**
     * 添加商品一二级分类
     */
    public final static String SAVEPRODUCTPARENTCODE = BASEURL + "saveProductParentCode";

    /**
     * 改派选择技师一级分类
     */
    public final static String SELECTROLECOUNTLIST = BASEURL + "selectRoleCountList";

    /**
     * 改派选择技师二级分类
     */
    public final static String SELECTROLECANDUSERLIST = BASEURL + "selectRoleAndUserList";

    /**
     * 提交审核
     */
    public final static String USERAUBMITAUDIT = BASEURL + "userAubmitAudit";

    /**
     * 获取项目（美容，保养，维修，改装）
     */
    public final static String GETCARPROJECT = BASEURL + "getCarProject";

    /**
     * 获取施工单
     */
    public final static String GETDOCUMENTLIST = BASEURL + "getdocumentList";

    /**
     * 个人资料
     */
    public final static String SELECTBYIDQUERYUSERINFO = BASEURL + "selectByIdQueryUserInfo";

    /**
     * 根据项目获取价格
     */
    public final static String GETPRICE = BASEURL + "getPrice";

    /**
     * 员工管理（审核用户）分页列表
     */
    public final static String EMPMANAGER = BASEURL + "empManager";

    /**
     * 员工详情
     */
    public final static String EMPMANAGERDETAILS = BASEURL + "empManagerDetails";

    /**
     * 获取角色权限
     */
    public final static String SELECTEMPROLELISTDATA = BASEURL + "selectEmpRoleListData";

    /**
     * 老板审核
     */
    public final static String UPDATEEMPINFO = BASEURL + "updateEmpInfo";

    /**
     * 职位下的用户列表
     */
    public final static String FINDTSDEPARTANDTSBASEUSER = BASEURL + "findTsDepartAndTsBaseUser";

    /**
     * 职位
     */
    public final static String FINDTSDEPARTANDCOUNT = BASEURL + "findTsDepartAndCount";

    /**
     * 根据主键查询商品详情
     */
    public final static String FINDPRODUCT = BASEURL + "findProduct";

    /**
     * 根据主键查询服务详情
     */
    public final static String FINDSERVICE = BASEURL + "findService";

    /**
     * 根据商品名字模糊查询
     */
    public final static String FINDPRODUCTBYNAME = BASEURL + "findProductByName";

    /**
     * 根据服务模糊查询
     */
    public final static String FINDSERVICEBYNAME = BASEURL + "findServiceByName";

    /**
     * 派工的时候，根据服务选择相对应部门的员工
     */
    public final static String GETUSERLIST = BASEURL + "getUserList";

    /**
     * 派工和改派
     */
    public final static String SAVEORUPDATECARDISPATCH = BASEURL + "saveOrUpdateCarDispatch";

    /**
     * 车店列表
     */
    public final static String SELECTSHOPLIST = BASEURL + "selectShopList";

    /**
     * 获取车辆信息
     */
    public final static String GETCARDETAIL = BASEURL + "getCarDetail";

    /**
     * 删除商品
     */
    public final static String DELETEGOODS = BASEURL + "deleteGoods";

    /**
     * 删除服务
     */
    public final static String DELETESER = BASEURL + "deleteService";

    /**
     * 修改商品
     */
    public final static String UPDATEPRODUCT = BASEURL + "updateProduct";

    /**
     * 修改服务详情
     */
    public final static String UPDATESERVICEDETAIL = BASEURL + "updateSerive";

    /**
     * 派工的时候，选择部门的员工
     */
    public final static String GETDEPUSERLIST = BASEURL + "getDepUserList";

    /**
     * 上传头像
     */
    public final static String UPLOADPERSONALAVATAR = BASEURL + "uploadPersonalAvatar";

    /**
     * 结算
     */
    public final static String CLOSEACCOUNT = BASEURL + "closeAccount";

    /**
     * 修改车辆信息和服务
     */
    public final static String UPDATECARDETAILANDSERVICE = BASEURL + "updateCarDetailAndService";

    /**
     * 修改员工
     */
    public final static String UPDATEEMPMANAGER = BASEURL + "updateempManager";

    /**
     * 获取公告
     */
    public final static String GETLINE = BASEURL + "getLine";

    /**
     * 根据公告id返回详情
     */
    public final static String GETLINEDETAIL = BASEURL + "getLineDetail";

    /**
     * 获取轮播
     */
    public final static String GETBANNER = BASEURL + "getBanner";

    /**
     * 根据轮播id返回详情
     */
    public final static String GETBANNERDETAIL = BASEURL + "getBannerDetail";

    /**
     * 车辆管理列表
     */
    public final static String SELECTBYSTR = BASEURL + "getCarList";

    /**
     * 修改手机号码
     */
    public final static String UPDATEUSERINFOPHONE = BASEURL + "updateUserInfoPhone";

    /**
     * 修改密码
     */
    public final static String UPDATEUSERINFOPASSWORD = BASEURL + "updateUserInfoPassword";

    /**
     * 取消在店车辆
     */
    public final static String CANCEL = BASEURL + "cancel";

    /**
     * 根据项目返回服务的员工
     */
    public final static String GETNAME = BASEURL + "getName";

    /**
     * 员工或者老板绩效
     */
    public final static String GETDECUMENTBYUID = BASEURL + "getDecumentByUid";

    /**
     * 员工绩效的具体项目
     */
    public final static String GETDECUMENTBUCARNO = BASEURL + "getDecumentByCarno";

    /**
     * 供应商列表查询
     */
    public final static String GETSUPPLIERLIST = BASEURL + "getSupplierList";

    /**
     *
     */
    public final static String GETSUPPLIERDETAIL = BASEURL + "getSupplierDetail";

    /**
     * 删除供应商
     */
    public final static String DELETESUPPLIER = BASEURL + "deleteSupplier";

    /**
     * 获取供应商列表带模糊查询
     */
    public final static String FINDSUPPLIERBYNAME = BASEURL + "findSupplierByName";


    /**
     * 添加供应商
     */
    public final static String SAVESUPPLIER = BASEURL + "saveSupplier";

    /**
     * 修改供应商
     */
    public final static String UPDATESUPPLIER = BASEURL + "updateSupplier";
    /**
     * 仓库列表查询
     */
    public final static String GETREPERTORYLIST = BASEURL + "getRepertoryList";

    /**
     * 删除仓库
     */
    public final static String DELETEREPERTORY = BASEURL + "deleteRepertory";

    /**
     * 添加仓库
     */
    public final static String SAVEREPERTORY = BASEURL + "saveRepertory";

    /**
     * 添加仓库
     */
    public final static String UPDATEREPERTORY = BASEURL + "updateRepertory";

    /**
     * 车辆信息
     */
    public final static String SELECTMODIFYVEHICLESERVICEDETAILS = BASEURL + "selectModifyVehicleServiceDetails";

    /**
     * 报价
     */
    public final static String SELECTINFOOFFER = BASEURL + "selectInfoOffer";

    /**
     * 车辆历史信息消费记录
     */
    public final static String SELECTRECORDSOFCONSUMPTION = BASEURL + "selectRecordsOfConsumption";

    /**
     * 检车记录
     */
    public final static String SELECTPICKUPTHECARRECORD = BASEURL + "selectPickUpTheCarRecord";

    /**
     * 查询店铺对应的商品列表
     */
    public final static String SELECTPRODUCTBYSHOPID = BASEURL + "selectProductByShopId";

    /**
     * 采购
     */
    public final static String SAVEPURCHASE = BASEURL + "savePurchase";

    /**
     * 采购记录
     */
    public final static String SELECTCARPURCHASE = BASEURL + "selectCarPurchase";

    /**
     * 智能提醒
     */
    public final static String SELECTCARREMINDLIST = BASEURL + "selectCarRemindlist";

    /**
     * 提车
     */
    public final static String PICKCAR = BASEURL + "pickCar";

    /**
     * 智能提醒详情
     */
    public final static String SELECTACAREMINDLISTINFO = BASEURL + "selectCarRemindlistInfo";

    /**
     * 创建提醒
     */
    public final static String SAVEINTELLIGENTREMINDER = BASEURL + "saveIntelligentReminder";

    /**
     * 查询捡车项目列表
     */
    public final static String SELECTCARINSPECTPROJECTDATA = BASEURL + "selectCarInspectProjectData";

    /**
     * 检车
     */
    public final static String SAVEINSPECTIONVEHICLE = BASEURL + "saveInspectionVehicle";

    /**
     * 点击历史进店后展示的车辆信息 和车辆管理的车辆列表详情
     */
    public final static String GETCARNEWDETAIL = BASEURL + "getCarnewDetail";

    /**
     * 车辆管理修改车辆信息
     */
    public final static String UPDATECAR = BASEURL + "updateCar";

    /**
     * 关闭智能提醒
     */
    public final static String UPDATEINTELLIGENTREMINDER = BASEURL + "updateIntelligentReminder";

    /**
     * 获取车型
     */
    public final static String GETBRAND = BASEURL + "getBrand";

    /**
     * 待入库列表
     */
    public final static String SELECTCARPURCHSEDETAILLIST = BASEURL + "selectCarPurchaseDetailList";

    /**
     * 待入库撤单
     */
    public final static String UPDATECARPURCHASESTATUS = BASEURL + "updateCarPurchaseStatus";

    /**
     * 采购详情
     */
    public final static String SELECTCARPURCHASEDETAIL = BASEURL + "selectCarPurchaseDetail";

    /**
     * 出入库记录
     */
    public final static String GETOUPINPHISTORY = BASEURL + "selectCarEntryRecordsList";

    /**
     * 领料人
     */
    public final static String SELECTCURRENTSTOREUSERDATA = BASEURL + "selectCurrentStoreUserData";

    /**
     * 领料出库
     */
    public final static String UPDATECARPURCHASERECORDBATCH = BASEURL + "updateCarPurchaseRecordBatch";

    /**
     * 领料出库商品列表
     */
    public final static String SELECTOUTBOUNDGOODSLIST = BASEURL + "selectOutboundGoodslist";

    /**
     * 入库
     */
    public final static String SAVEPURCHASESTATUS = BASEURL + "savePurchaseStatus";

    /**
     * 直接出入库
     */
    public final static String SAVEINSERTPURCHASE = BASEURL + "savePurchaseInsertPost";

    /**
     * 采购退货
     */
    public final static String UPDATEPURCHASEACOUNTREPERTORY = BASEURL + "updatePurchaseAcountRepertory";

    /**
     * 通过code获取库存
     */
    public final static String GETWAREHOUSEBYCODE = BASEURL + "getWarehouseByCode";
    /**
     * 出入库详情
     */
    public final static String SELECTCARENTRYRECORDSBYID = BASEURL + "selectCarEntryRecordsById";

    /**
     * 待入库-入库-列表
     */
    public final static String SELECTPURCHASEDETAILLIST = BASEURL + "selectPurchaseDetailList";

    /**
     * 会员管理-获取次卡类型
     */
    public final static String GETONCECARDTYPE = BASEURL + "getOnceCardType";

    /**
     * 新增消费卡
     */
    public final static String SAVECONSUMECARD = BASEURL + "saveConsumeCard";


    /**
     * 库存盘点列表
     */
    public final static String SELECTSHOUT = BASEURL + "selectShout";

    /**
     * 统计当天的总收入或者总支出
     */
    public final static String SELECTCARINCOMESPENDINGDAYTOTALPRICE = BASEURL + "selectCarIncomeSpendingDayTotalPrice";

    /**
     * 统计当月的总收入或者总支出
     */
    public final static String SELECTCARINCOMESPENDINGMONTHTOTALPRICE = BASEURL + "selectCarIncomeSpendingMonthTotalPrice";


    /**
     * 当日收入详情
     */
    public final static String SELECTCARINCOMEDAYBYTIME = BASEURL + "selectCarIncomeDayByTime";

    /**
     * 当日支出详情
     */
    public final static String SELECTCARSPENDINGDAYBYTIME = BASEURL + "selectCarSpendingDayByTime";

    /**
     * 当月收入详情
     */
    public final static String SELECTCARINCOMEMONTHBYTIME = BASEURL + "selectCarIncomeMonthByTime";

    /**
     * 当月支出详情
     */
    public final static String SELECTCARSPENDINGMONTHBYTIME = BASEURL + "selectCarSpendingMonthByTime";

    /**
     * 收入或支出录入
     */
    public final static String SAVACARINCOMESPENDING = BASEURL + "savaCarIncomeSpending";


    /**
     * 新增次卡
     */
    public final static String SAVEONCECARDTYPE = BASEURL + "saveOnceCardType";

    /**
     * 会员管理
     */
    public final static String SELECTMEMBERMANAGEMENTMEMBERBER = BASEURL + "selectMemberManagementMemberber";

    /**
     * 次卡详情
     */
    public final static String GETONCECARDTYPEDETAIL = BASEURL + "getOnceCardTypeDetail";

    /**
     * 开次卡
     */
    public final static String SAVEONCECARD = BASEURL + "saveOnceCard";

    /**
     * 获取消费卡
     */
    public final static String GETCONSUMECARD = BASEURL + "getConsumeCard";

    /**
     * 充值
     */
    public final static String RECHARGETHECARD = BASEURL + "rechargeTheCard";

    /**
     * 采购详情修改
     */
    public final static String UPDATECARPURCHASEDETAIL = BASEURL + "updateCarPurchaseDetail";

    /**
     * 余额不足列表
     */
    public final static String SELECTCONSUMECARDINSUFFICIENT = BASEURL + "selectConsumeCardInsufficient";

    /**
     * 余次不足列表
     */
    public final static String SELECTOVERTIMELESSTHAN = BASEURL + "selectOverTimeLessThan";

    /**
     * 即将过期列表
     */
    public final static String SELECTWILLEXPIRE = BASEURL + "selectWillExpire";

    /**
     * 已过期列表
     */
    public final static String SELECTEXPIRED = BASEURL + "selectExpired";

    /**
     * 在店车辆的车辆信息客户选择更新
     */
    public final static String UPDATEDOCUMENTCUSTOMER = BASEURL + "updateDocumentCustomer";

    /**
     * 次卡列表
     */
    public final static String SELECTTIMECARDLIST = BASEURL + "selectTimeCardlist";


    /**
     * 有限卡列表
     */
    public final static String SELECTLIMITEDOFCARDSLIST = BASEURL + "selectLimitedOfCardslist";


    /**
     * 无限卡列表
     */
    public final static String SELECTUNLIMITEDLIST = BASEURL + "selectUnlimitedlist";


    /**
     * 充值卡列表
     */
    public final static String SELECTOFPRELOADEDCARDSLIST = BASEURL + "selectOfPreloadedCardslist";


    /**
     * 关闭提醒列表
     */
    public final static String SELECTCARREMINDSHUTDOWNLIST = BASEURL + "selectCarRemindShutDownlist";


    /**
     * 新增盘点
     */
    public final static String SAVESHOUT = BASEURL + "saveShout";

    /**
     * 盘点详情
     */
    public final static String SELECTSHOUTDETAIL = BASEURL + "selectShoutDetail";

    /**
     * 当前用户拥有的次卡列表
     */
    public final static String SELECTONCECARBYCUSTOMERIDIDANDSHOPID = BASEURL + "selectOnceCarByCustomerIdIdAndShopId";

    /**
     * 其它出库
     */
    public final static String UPDATEPURCHASE = BASEURL + "updatePurchase";

    /**
     * 提醒设置查询
     */
    public final static String SELECTCARREMINDSET = BASEURL + "selectCarRemindSet";

    /**
     * 提醒设置
     */
    public final static String SAVECARREMINDSET = BASEURL + "savecarRemindSet";

    /**
     * 查询版本更新数据
     */
    public final static String UPGRADEDATA = BASEURL + "selectVisionUpdate";

    /**
     * 删除卡种
     */
    public final static String DELETEONCECARDTYPE = BASEURL + "deleteOnceCardType";

    /**
     * 查询客户详情
     */
    public final static String SELECTCUSTOMERBYIDINFONATION = BASEURL + "selectCustomerByIdInfonation";

    /**
     * 更新客户信息
     */
    public final static String UPDATECUSTOMER = BASEURL + "updateCustomer";
    /**
     * 删除车辆
     */
    public final static String DELETECARBYID = BASEURL + "deleteCarById";

    /**
     * 查询订货品类
     */
    public final static String SELECTORDERCLASSIFICATION = BASEURL+"SelectOrderClassification";

    /**
     * 查询订货商品推荐
     */
    public final static String SELECTORDERRECOMMENDED = BASEURL+"SelectOrderRecommended";

    /**
     * 根据订货品类查询商品
     */
    public final static String SELECTPRODUCTBYCLASSIFICATION = BASEURL+"SelectProductByClassification";

    /**
     * 订货提交
     */
    public final static String SAVEPLACEANORDER = BASEURL+"saveplaceAnOrder";

    /**
     * 订单列表
     */
    public final static String SELECTORDERPLACE = BASEURL+"SelectOrderPlace";

    /**
     * 订单详情
     */
    public final static String SELECTORDERPLACEBYID = BASEURL+"selectOrderPlaceById";

    /**
     * 根据id查询商品
     */
    public final static String SELECTPRODUCTBYID = BASEURL+"SelectProductById";

    /**
     * 订货广告图片
     */
    public final static String SELECTBYORDERADVERTISEMENT = BASEURL+"selectByOrderAdvertisement";


    /**
     * 删除我的订单
     */
    public final static String DELETEPLACEANORDER = BASEURL+"deleteplaceAnOrder";

    /**
     * 服务热门推荐
     */
    public final static String SELECTJFOMCATEGORY = BASEURL+"selectJfomCategory";

    /**
     * 微信支付
     */
    public final static String WXPAY = BASEWXURL+"WXPay";

    /**
     * 公众号列表
     */
    public final static String SELECTWEIXINAPPLIST = BASEURL+"selectWeiXinAppList";


    /**
     * 删除客户信息
     */
    public final static String DELETECUSTOMERBYID = BASEURL+"deleteCustomerById";

    /**
     * 根据车牌号查客户
     */
    public final static String SELECTCARANDDOCUMENTBYCARNO = BASEURL+"selectCarAndDocumentByCarNo";


    /**
     * 查询定位救援列表
     */
    public final static String SELECTPOSITIONINGTHERESCUE = BASEURL+"selectPositioningTheRescue";


    /**
     * 添加热门推荐
     */
    public final static String SAVEJFOMCATEGORY = BASEURL+"saveJfomCategory";

    /**
     * 删除热门服务
     */
    public final static String DELETEJFOMCATEGORY = BASEURL+"deleteJfomCategory";


    /**
     * 新增自定义服务
     */
    public final static String SAVESERIVECUSTOM = BASEURL+"saveSeriveCustom";


}











