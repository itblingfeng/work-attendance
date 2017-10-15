package cn.blingfeng.workflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("workflow")
@Controller
public class WorkFlowController {

      @RequestMapping("/reAttend")
    public String reAttend(){

          return "attend";
      }
}
