package com.xyzq.kid.console.book.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mysql.jdbc.StringUtils;
import com.xyzq.kid.logic.dateUnviable.entity.DateUnviableEntity;
import com.xyzq.kid.logic.dateUnviable.service.DateUnviableService;
import com.xyzq.simpson.maggie.access.spring.MaggieAction;
import com.xyzq.simpson.maggie.framework.Context;
import com.xyzq.simpson.maggie.framework.Visitor;
import com.xyzq.kid.console.admin.action.AdminAjaxAction;

@MaggieAction(path="kid/console/isViableDate")
public class IsVisableDate extends AdminAjaxAction {
	
	@Autowired
	DateUnviableService dateUnviableService;

	@Override
	public String doExecute(Visitor visitor, Context context) throws Exception {
		String unviableDate=(String)context.parameter("unviableDate");
		if(!StringUtils.isNullOrEmpty(unviableDate)){
			DateUnviableEntity po=dateUnviableService.findBy(unviableDate);
			if(po==null){
				context.set("code", "0");
			}
		}
		return "success.json";
	}
}
