package com.yc.es.controller;

import com.yc.es.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 功能描述: 控制层
 *
 * @Author: xieyc
 * @Date: 2020-12-27
 */
@Controller
public class IndexController {

    @Autowired
    private ContentService service;

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    /**
     * 爬取京东数据
     *
     * @param keyWord
     * @return
     */
    @GetMapping("/parse/{keyWord}")
    @ResponseBody
    public Boolean parse(@PathVariable("keyWord") String keyWord) {
        try {
            return service.parseContent(keyWord);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 搜索
     *
     * @param keyWord
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/search/{keyWord}/{pageNo}/{pageSize}")
    @ResponseBody
    public List<Map<String, Object>> search(@PathVariable("keyWord") String keyWord,
                                            @PathVariable("pageNo") Integer pageNo,
                                            @PathVariable("pageSize") Integer pageSize) {
        try {
            return service.search(keyWord, pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 高亮显示
     *
     * @param keyWord
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/searchHighLight/{keyWord}/{pageNo}/{pageSize}")
    @ResponseBody
    public List<Map<String, Object>> searchHighLight(@PathVariable("keyWord") String keyWord,
                                                     @PathVariable("pageNo") Integer pageNo,
                                                     @PathVariable("pageSize") Integer pageSize) {
        try {
            return service.searchHighLight(keyWord, pageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
