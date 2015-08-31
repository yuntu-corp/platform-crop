package com.unitever.platform.component.attachment.dao.manual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.unitever.platform.component.attachment.model.AttachmentConfig;
import com.unitever.platform.core.dao.BaseDAO;

@Repository
public class AttachmentConfigDAO extends BaseDAO<AttachmentConfig, String>{

	public AttachmentConfig getAttachmentConfigWithCode(String code) {
		List<AttachmentConfig> attachmentConfigs = this.getList("getAttachmentConfigWithCode", code);
		return attachmentConfigs.size() > 0 ? attachmentConfigs.get(0) : null;
	}

	public List<AttachmentConfig> findAttachmentConfigWithCodeAndNotId(String code, String id) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("code", code);
		params.put("id", id);
		return this.getList("findAttachmentConfigWithCodeAndNotId", params);
	}

}
