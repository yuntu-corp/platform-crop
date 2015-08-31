package com.unitever.platform.component.attachment.aspect;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.unitever.platform.component.attachment.annotation.AttachmentAnnotation;
import com.unitever.platform.component.attachment.annotation.AttachmentField;
import com.unitever.platform.component.attachment.model.Attachment;
import com.unitever.platform.component.attachment.service.AttachmentService;
import com.unitever.platform.component.attachment.util.AttachmentUtil;
import com.unitever.platform.util.ReflectUtil;

//@Aspect
//@Component
public class AttachmentAspect {

	/**
	 * 拦截需添加update merge等方法，需测试
	 * 
	 * @param joinPoint
	 * @throws IOException
	 */
	@AfterReturning("execution(* com.unitever.platform.core.dao.BaseDAO.save(..)) || execution(* com.unitever.platform.core.dao.BaseDAO.update(..)) || execution(* com.unitever.platform.core.dao.BaseDAO.saveOrUpdate(..))")
	public void bindAttachment(JoinPoint joinPoint) throws IOException {
		Object objArgsObject = joinPoint.getArgs()[0];// 取得第一个参数对象
		AttachmentAnnotation attachmentAnnotation = objArgsObject.getClass().getAnnotation(AttachmentAnnotation.class);
		if (attachmentAnnotation != null) {
			AttachmentUtil.autoBindAttachment(objArgsObject);
		}
	}

	@Before("execution(* com.unitever.platform.core.dao.BaseDAO.delete(..)) ")
	public void deleteAttachment(JoinPoint joinPoint) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Object objArgsObject = null;
		if (joinPoint.getArgs()[0] instanceof String[]) {
			Method getMethod = joinPoint.getTarget().getClass().getMethod("get", Serializable.class);
			for (String id : (String[]) joinPoint.getArgs()[0]) {
				Object obj = getMethod.invoke(joinPoint.getTarget(), id);
				delete(obj);
			}
		} else if (joinPoint.getArgs()[0] instanceof String) {
			Method getMethod = joinPoint.getTarget().getClass().getMethod("get", Serializable.class);
			Object obj = getMethod.invoke(joinPoint.getTarget(), joinPoint.getArgs()[0]);
			delete(obj);
		} else {
			objArgsObject = joinPoint.getArgs()[0];// 取得第一个参数对象
			delete(objArgsObject);
		}
	}

	private void delete(Object objArgsObject) {
		if (objArgsObject == null) {
			return;
		}
		AttachmentAnnotation attachmentAnnotation = objArgsObject.getClass().getAnnotation(AttachmentAnnotation.class);
		if (attachmentAnnotation != null) {
			List<Attachment> attachments = attachmentService.getAttachmentsWithOwnerId(ReflectUtil.getFieldValue(objArgsObject, "id").toString());
			if(attachments==null){
				return;
			}
			for (Attachment att : attachments) {
				attachmentService.logicDelete(att.getId());
			}
			for (Field f : objArgsObject.getClass().getDeclaredFields()) {
				if (!f.getName().equals("id")) {
					AttachmentField attachmentField = f.getAnnotation(AttachmentField.class);
					if (attachmentField != null) {
						List<Attachment> attachmentOther = attachmentService.getAttachmentsWithOwnerId(ReflectUtil.getFieldValue(objArgsObject, f.getName()).toString());
						for (Attachment att : attachmentOther) {
							attachmentService.logicDelete(att.getId());
						}
					}
				}
			}
		}
	}

	@Autowired
	private AttachmentService attachmentService;

}
