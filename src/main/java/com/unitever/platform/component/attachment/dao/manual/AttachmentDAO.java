package com.unitever.platform.component.attachment.dao.manual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.unitever.platform.component.attachment.constant.AttachmentConstant;
import com.unitever.platform.component.attachment.model.Attachment;
import com.unitever.platform.core.dao.BaseDAO;

@Repository
public class AttachmentDAO extends BaseDAO<Attachment, String>{

	public List<Attachment> getAttachmentsWithOwnerId(String ownerId, String status) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("ownerId", ownerId);
		params.put("status", status);
		return this.getList("getAttachmentsWithOwnerId", params);
	}

	public void updateNum(String ownerId, int fileNum) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("ownerId", ownerId);
		params.put("fileNum", fileNum);
		this.update("updateNum", params);
	}
	
	public void logicDelete(String id) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("status", AttachmentConstant.ATTACHMENT_STATUS_DELETE);
		this.update("logicDelete", params);
	}

}
