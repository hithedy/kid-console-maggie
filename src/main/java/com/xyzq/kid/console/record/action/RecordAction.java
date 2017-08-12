package com.xyzq.kid.console.record.action;

import com.xyzq.kid.logic.record.service.RecordService;
import com.xyzq.simpson.base.json.JSONObject;
import com.xyzq.simpson.maggie.access.spring.MaggieAction;
import com.xyzq.simpson.maggie.framework.Context;
import com.xyzq.simpson.maggie.framework.Visitor;
import com.xyzq.kid.console.admin.action.AdminAjaxAction;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 查询飞行日志记录
 */
@MaggieAction(path = "kid/console/loadFlightDiary")
public class RecordAction extends AdminAjaxAction {
	/**
	 * Action中只支持Autowired注解引入SpringBean
	 */
	@Autowired
	private RecordService recordService;


	/**
	 * 动作执行
	 *
	 * @param visitor 访问者
	 * @param context 请求上下文
	 * @return 下一步动作，包括后缀名，null表示结束
	 */
	@Override
	public String doExecute(Visitor visitor, Context context) throws Exception {
		String data = JSONObject.convertFromObject(recordService.load(Integer.valueOf(String.valueOf(context.parameter("id"))))).toString();
		context.set("msg", "这个是前端需要展示的消息");
		context.set("data", data);
		return "success.json";
	}

}
