package com.lida.carcare.bean;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/**
 * Created by WeiQingFeng on 2017/4/28.
 */

public class ComparatorCar implements Comparator
{

    @Override
    public int compare(Object o1, Object o2)
    {
        CarManageListBean.Data bean1 = (CarManageListBean.Data) o1;
        CarManageListBean.Data bean2 = (CarManageListBean.Data) o2;
        if (bean1.getCarNo() != null && bean2.getCarNo() != null)
        {
            return Collator.getInstance(Locale.CHINA).compare(bean1.getCarNo(), bean2.getCarNo());
        }
//        int flag=bean1.toString().compareTo(bean2.toString());
        return -1;
    }

}
