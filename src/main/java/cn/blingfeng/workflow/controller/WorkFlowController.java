package cn.blingfeng.workflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author : blingfeng
 * @date : 2017/10/15
 * @description 工作流
 **/
@RequestMapping("workflow")
@Controller
public class WorkFlowController {

      @RequestMapping("/reAttend")
    public String reAttend(){

          return "attence";
      }
}
