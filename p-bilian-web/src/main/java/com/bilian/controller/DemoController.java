package com.bilian.controller;

import com.bilian.dubbo.api.IWeService;
import com.bilian.result.ResultResponse;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p/>
 * <p><b>introduction:</b>
 * </p>
 * <p><b>packageName:</b>com.bilian.controller</p>
 * <p><b>projectName:</b>p-bilian-parent</p>
 * <p><b>User:</b> xuxiaoming <a href="mailto:xu.xiaoming535@chinaredstar.com">xu.xiaoming535@chinaredstar.com</a></p>
 * <p><b>Date:</b>2018/1/12</p>
 *
 * @author xuxiaoming
 */
@Controller
@RequestMapping(value = "/v1/demo")
@Api(value = "/v1/demo", description = "controllerDemo")
public class DemoController {

    private static Logger logger = LoggerFactory.getLogger(DemoController.class);


    @Autowired
    private IWeService weService;



    /**
     * get请求Demo
     *
     * @return
     */
    @ApiResponses(value = {@ApiResponse(code = 200, message = "get请求Demo")})
    @ApiOperation(value = "getMethod", notes = "get请求Demo")
    @ResponseBody
    @RequestMapping(value = "getMethod", method = {RequestMethod.GET})
    public ResultResponse<Object> getMethod(@ApiParam(name = "id", value = "id", required = true)
                                                @RequestParam(value = "id") Long id){
       String result=  weService.demoServiceMethod();

        return  new ResultResponse<Object>().success(result);
    }



    /**
     * get请求Demo
     *
     * @return
     */
    @ApiResponses(value = {@ApiResponse(code = 200, message = "post请求Demo")})
    @ApiOperation(value = "postMethod", notes = "post请求Demo")
    @ResponseBody
    @RequestMapping(value = "postMethod", method = {RequestMethod.POST})
    public ResultResponse<Object> postMethod(@RequestBody String jsonStr){

        return  new ResultResponse<Object>().success("hello postMethod");
    }



}
