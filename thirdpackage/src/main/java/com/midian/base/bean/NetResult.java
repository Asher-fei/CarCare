package com.midian.base.bean;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;

/**
 * 结果
 * 
 * @author XuYang
 * 
 */
public class NetResult extends NetBase {
	public String status = "";
	public String msg = "";
	public static Gson gson = new Gson();

	public boolean isOK() {
		return "1".equals(status);
	}

	public boolean noData() {
		return "no data".equals(status);
	}

	public static NetResult parse(String json) throws AppException {
		NetResult res = new NetResult();
		try {
			res = gson.fromJson(json, NetResult.class);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw AppException.json(e);
		}
		return res;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String staus) {
        this.status = staus;
    }
}
