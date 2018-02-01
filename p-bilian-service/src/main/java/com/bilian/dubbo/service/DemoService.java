package com.bilian.dubbo.service;

import com.bilian.dubbo.api.IWeService;
import org.springframework.stereotype.Service;


/**
 * <p/>
 * <p><b>introduction:</b>
 * </p>
 * <p><b>packageName:</b>com.bilian.dubbo.service</p>
 * <p><b>projectName:</b>p-bilian-parent</p>
 * <p><b>User:</b> xuxiaoming <a href="mailto:xu.xiaoming535@chinaredstar.com">xu.xiaoming535@chinaredstar.com</a></p>
 * <p><b>Date:</b>2018/2/1</p>
 *
 * @author xuxiaoming
 */
@Service
class WeService implements IWeService {

    @Override
    public String demoServiceMethod() {
        return "hello service";

    }
}
