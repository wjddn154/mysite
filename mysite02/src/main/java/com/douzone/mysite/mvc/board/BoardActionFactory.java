package com.douzone.mysite.mvc.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if("add".equals(actionName)) {
			action = new AddAction();
		} else if("addform".equals(actionName)) {
			action = new AddFormAction();
		} else if("addreply".equals(actionName)) {
			action = new AddReplyAction();
		} else if("addreplyform".equals(actionName)) {
			action = new AddReplyFormAction();
		} else if("view".equals(actionName)) {
			action = new ViewAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if("modify".equals(actionName)) {
			action = new ModifyAction();
		} else if("modifyform".equals(actionName)) {
			action = new ModifyFormAction();
		} else {
			action = new ListAction();
		}
		
		
		
		
		
		
		return action;
	}

	
}
