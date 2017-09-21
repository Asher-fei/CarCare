package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 员工详情
 * Created by WeiQingFeng on 2017/5/17.
 */

public class MemberDetailBean extends NetResult
{
    /**
     * data : {"userId":"513a048c534011e78e92f44d30a3e396","status":0,"username":"军","auditStatus":"1","shopCode":null,"tsDeparts":[{"dname":"维修"},{"dname":"前台接待"},{"dname":"美容"}],"tsUser":{"userId":"513a048c534011e78e92f44d30a3e396","status":null,"username":null,"auditStatus":null,"shopCode":null,"tsDeparts":null,"tsUser":null,"tsRole":null,"inviteCode":null,"headImg":null,"phone":"13467444771"},"tsRole":{"rolename":"员工"},"inviteCode":3,"headImg":null}
     */

    private DataBean data;

    public static MemberDetailBean parse(String json) throws AppException
    {
        MemberDetailBean res = new MemberDetailBean();
        try
        {
            res = gson.fromJson(json, MemberDetailBean.class);
        } catch (JsonSyntaxException e)
        {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    public DataBean getData()
    {
        return data;
    }

    public void setData(DataBean data)
    {
        this.data = data;
    }

    public static class DataBean
    {
        /**
         * userId : 513a048c534011e78e92f44d30a3e396
         * status : 0
         * username : 军
         * auditStatus : 1
         * shopCode : null
         * tsDeparts : [{"dname":"维修"},{"dname":"前台接待"},{"dname":"美容"}]
         * tsUser : {"userId":"513a048c534011e78e92f44d30a3e396","status":null,"username":null,"auditStatus":null,"shopCode":null,"tsDeparts":null,"tsUser":null,"tsRole":null,"inviteCode":null,"headImg":null,"phone":"13467444771"}
         * tsRole : {"rolename":"员工"}
         * inviteCode : 3
         * headImg : null
         */

        private String id;
        private String status;
        private String username;
        private String auditStatus;
        private String shopCode;
        private TsUserBean tsUser;
        private TsRoleBean tsRole;
        private String inviteCode;
        private String headImg;
        private List<TsDepartsBean> tsDeparts;

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getStatus()
        {
            return status;
        }

        public void setStatus(String status)
        {
            this.status = status;
        }

        public String getUsername()
        {
            return username;
        }

        public void setUsername(String username)
        {
            this.username = username;
        }

        public String getAuditStatus()
        {
            return auditStatus;
        }

        public void setAuditStatus(String auditStatus)
        {
            this.auditStatus = auditStatus;
        }

        public String getShopCode()
        {
            return shopCode;
        }

        public void setShopCode(String shopCode)
        {
            this.shopCode = shopCode;
        }

        public TsUserBean getTsUser()
        {
            return tsUser;
        }

        public void setTsUser(TsUserBean tsUser)
        {
            this.tsUser = tsUser;
        }

        public TsRoleBean getTsRole()
        {
            return tsRole;
        }

        public void setTsRole(TsRoleBean tsRole)
        {
            this.tsRole = tsRole;
        }

        public String getInviteCode()
        {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode)
        {
            this.inviteCode = inviteCode;
        }

        public String getHeadImg()
        {
            return headImg;
        }

        public void setHeadImg(String headImg)
        {
            this.headImg = headImg;
        }

        public List<TsDepartsBean> getTsDeparts()
        {
            return tsDeparts;
        }

        public void setTsDeparts(List<TsDepartsBean> tsDeparts)
        {
            this.tsDeparts = tsDeparts;
        }

        public static class TsUserBean
        {
            /**
             * userId : 513a048c534011e78e92f44d30a3e396
             * status : null
             * username : null
             * auditStatus : null
             * shopCode : null
             * tsDeparts : null
             * tsUser : null
             * tsRole : null
             * inviteCode : null
             * headImg : null
             * phone : 13467444771
             */

            private String id;
            @SerializedName("status")
            private String statusX;
            private String username;
            private String auditStatus;
            private String shopCode;
            private String tsDeparts;
            private String tsUser;
            private String tsRole;
            private String inviteCode;
            private String headImg;
            private String phone;

            public String getId()
            {
                return id;
            }

            public void setId(String id)
            {
                this.id = id;
            }

            public String getStatusX()
            {
                return statusX;
            }

            public void setStatusX(String statusX)
            {
                this.statusX = statusX;
            }

            public String getUsername()
            {
                return username;
            }

            public void setUsername(String username)
            {
                this.username = username;
            }

            public String getAuditStatus()
            {
                return auditStatus;
            }

            public void setAuditStatus(String auditStatus)
            {
                this.auditStatus = auditStatus;
            }

            public String getShopCode()
            {
                return shopCode;
            }

            public void setShopCode(String shopCode)
            {
                this.shopCode = shopCode;
            }

            public String getTsDeparts()
            {
                return tsDeparts;
            }

            public void setTsDeparts(String tsDeparts)
            {
                this.tsDeparts = tsDeparts;
            }

            public String getTsUser()
            {
                return tsUser;
            }

            public void setTsUser(String tsUser)
            {
                this.tsUser = tsUser;
            }

            public String getTsRole()
            {
                return tsRole;
            }

            public void setTsRole(String tsRole)
            {
                this.tsRole = tsRole;
            }

            public String getInviteCode()
            {
                return inviteCode;
            }

            public void setInviteCode(String inviteCode)
            {
                this.inviteCode = inviteCode;
            }

            public String getHeadImg()
            {
                return headImg;
            }

            public void setHeadImg(String headImg)
            {
                this.headImg = headImg;
            }

            public String getPhone()
            {
                return phone;
            }

            public void setPhone(String phone)
            {
                this.phone = phone;
            }
        }

        public static class TsRoleBean
        {
            /**
             * rolename : 员工
             */

            private String fid;
            private String rolename;

            public String getfId()
            {
                return fid;
            }

            public void setfId(String fid)
            {
                this.fid = fid;
            }

            public String getRolename()
            {
                return rolename;
            }

            public void setRolename(String rolename)
            {
                this.rolename = rolename;
            }
        }

        public static class TsDepartsBean
        {
            /**
             * dname : 维修
             */

            private String dname;

            public String getDname()
            {
                return dname;
            }

            public void setDname(String dname)
            {
                this.dname = dname;
            }
        }
    }
}
