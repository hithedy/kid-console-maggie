package com.xyzq.kid.console.admin.action;

import com.xyzq.kid.logic.admin.entity.AdminEntity;
import com.xyzq.kid.logic.admin.service.AdminService;
import com.xyzq.simpson.base.json.JSONNumber;
import com.xyzq.simpson.base.json.JSONObject;
import com.xyzq.simpson.base.json.JSONString;
import com.xyzq.simpson.base.text.Text;
import com.xyzq.simpson.maggie.access.spring.MaggieAction;
import com.xyzq.simpson.maggie.framework.Context;
import com.xyzq.simpson.maggie.framework.Visitor;
import com.xyzq.simpson.maggie.framework.action.core.IAction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 管理员登录动作
 */
@MaggieAction(path = "kid/console/getLogin")
public class AdminLoginAction implements IAction {
    /**
     * 管理员服务
     */
    @Autowired
    protected AdminService adminService;


    /**
     * 动作执行
     *
     * @param visitor 访问者
     * @param context 请求上下文
     * @return 下一步动作，包括后缀名，null表示结束
     */
    @Override
    public String execute(Visitor visitor, Context context) throws Exception {
        String userName = (String) context.parameter("userName");
        String password = (String) context.parameter("password");
        if(Text.isBlank(userName) || Text.isBlank(password)) {
            context.put("msg", "请填写帐号密码");
            return "fail.json";
        }
        AdminEntity adminEntity = adminService.findByUserName(userName);
        if(null == adminEntity) {
            context.put("msg", "用户不存在");
            return "fail.json";
        }
        if(!password.equals(adminEntity.password)) {
            context.put("msg", "密码不正确");
            return "fail.json";
        }
        String aId = adminService.saveSession(adminEntity.id);
        visitor.setCookie("aid", aId);
        JSONObject data = new JSONObject();
        data.put("id", new JSONNumber(adminEntity.id));
        data.put("userName", new JSONString(adminEntity.userName));
        data.put("mobileNo", new JSONString(adminEntity.mobile));
        data.put("email", new JSONString(adminEntity.email));
        context.set("data", data);
        return "success.json";
    }
}
