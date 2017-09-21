package com.lida.carcare.retrofit;

import com.lida.carcare.bean.OCRBean;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public interface HttpService {

    @FormUrlEncoded
    @POST("recogliu.do")
    Observable<OCRBean> getResult(@FieldMap Map<String, String> options);

}
