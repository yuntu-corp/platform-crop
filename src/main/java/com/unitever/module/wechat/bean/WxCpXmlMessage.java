package com.unitever.module.wechat.bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.cp.api.WxCpConfigStorage;
import me.chanjar.weixin.cp.util.crypto.WxCpCryptUtil;
import me.chanjar.weixin.cp.util.xml.XStreamTransformer;

import org.apache.commons.io.IOUtils;

/**
 * <pre>
 * 微信推送过来的消息，也是同步回复给用户的消息，xml格式
 * 相关字段的解释看微信开发者文档：
 * http://mp.weixin.qq.com/wiki/index.php?title=接收普通消息
 * http://mp.weixin.qq.com/wiki/index.php?title=接收事件推送
 * http://mp.weixin.qq.com/wiki/index.php?title=接收语音识别结果
 * </pre>
 *
 * @author Daniel Qian
 */
@XmlRootElement(name="xml")
public class WxCpXmlMessage implements Serializable {

  ///////////////////////
  // 以下都是微信推送过来的消息的xml的element所对应的属性
  ///////////////////////


  private Integer agentId;


  private String toUserName;


  private String fromUserName;


  private Long createTime;


  private String msgType;

  private String content;

  private Long msgId;

  private String picUrl;

  private String mediaId;

  private String format;

  private String thumbMediaId;

  private Double locationX;

  private Double locationY;

  private Double scale;

  private String label;

  private String title;

  private String description;

  private String url;

  private String event;

  private String eventKey;

  private String ticket;

  private Double latitude;

  private Double longitude;

  private Double precision;

  private String recognition;

  ///////////////////////////////////////
  // 群发消息返回的结果
  ///////////////////////////////////////
  /**
   * 群发的结果
   */
  private String status;
  /**
   * group_id下粉丝数；或者openid_list中的粉丝数
   */
  private Integer totalCount;
  /**
   * 过滤（过滤是指特定地区、性别的过滤、用户设置拒收的过滤，用户接收已超4条的过滤）后，准备发送的粉丝数，原则上，filterCount = sentCount + errorCount
   */
  private Integer filterCount;
  /**
   * 发送成功的粉丝数
   */
  private Integer sentCount;
  /**
   * 发送失败的粉丝数
   */
  private Integer errorCount;


  @XmlElement(name="AgentID")
  public Integer getAgentId() {
    return agentId;
  }

  public void setAgentId(Integer agentId) {
    this.agentId = agentId;
  }

  @XmlElement(name="ToUserName")
  public String getToUserName() {
    return toUserName;
  }

  public void setToUserName(String toUserName) {
    this.toUserName = toUserName;
  }

  @XmlElement(name="CreateTime")
  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  /**
   * <pre>
   * 当接受用户消息时，可能会获得以下值：
   * {@link me.chanjar.weixin.common.api.WxConsts#XML_MSG_TEXT}
   * {@link me.chanjar.weixin.common.api.WxConsts#XML_MSG_IMAGE}
   * {@link me.chanjar.weixin.common.api.WxConsts#XML_MSG_VOICE}
   * {@link me.chanjar.weixin.common.api.WxConsts#XML_MSG_VIDEO}
   * {@link me.chanjar.weixin.common.api.WxConsts#XML_MSG_LOCATION}
   * {@link me.chanjar.weixin.common.api.WxConsts#XML_MSG_LINK}
   * {@link me.chanjar.weixin.common.api.WxConsts#XML_MSG_EVENT}
   * </pre>
   *
   * @return
   */
  @XmlElement(name="MsgType")
  public String getMsgType() {
    return msgType;
  }

  /**
   * <pre>
   * 当发送消息的时候使用：
   * {@link me.chanjar.weixin.common.api.WxConsts#XML_MSG_TEXT}
   * {@link me.chanjar.weixin.common.api.WxConsts#XML_MSG_IMAGE}
   * {@link me.chanjar.weixin.common.api.WxConsts#XML_MSG_VOICE}
   * {@link me.chanjar.weixin.common.api.WxConsts#XML_MSG_VIDEO}
   * {@link me.chanjar.weixin.common.api.WxConsts#XML_MSG_NEWS}
   * </pre>
   *
   * @param msgType
   */
  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  @XmlElement(name="Content")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @XmlElement(name="MsgId")
  public Long getMsgId() {
    return msgId;
  }

  public void setMsgId(Long msgId) {
    this.msgId = msgId;
  }

  @XmlElement(name="PicUrl")
  public String getPicUrl() {
    return picUrl;
  }

  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }

  @XmlElement(name="MediaId")
  public String getMediaId() {
    return mediaId;
  }

  public void setMediaId(String mediaId) {
    this.mediaId = mediaId;
  }

  @XmlElement(name="Format")
  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  @XmlElement(name="ThumbMediaId")
  public String getThumbMediaId() {
    return thumbMediaId;
  }

  public void setThumbMediaId(String thumbMediaId) {
    this.thumbMediaId = thumbMediaId;
  }

  @XmlElement(name="Location_X")
  public Double getLocationX() {
    return locationX;
  }

  public void setLocationX(Double locationX) {
    this.locationX = locationX;
  }

  @XmlElement(name="Location_Y")
  public Double getLocationY() {
    return locationY;
  }

  public void setLocationY(Double locationY) {
    this.locationY = locationY;
  }

  @XmlElement(name="Scale")
  public Double getScale() {
    return scale;
  }

  public void setScale(Double scale) {
    this.scale = scale;
  }

  @XmlElement(name="Label")
  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  @XmlElement(name="Title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @XmlElement(name="Description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @XmlElement(name="Url")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @XmlElement(name="Event")
  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  @XmlElement(name="EventKey")
  public String getEventKey() {
    return eventKey;
  }

  public void setEventKey(String eventKey) {
    this.eventKey = eventKey;
  }

  @XmlElement(name="Ticket")
  public String getTicket() {
    return ticket;
  }

  public void setTicket(String ticket) {
    this.ticket = ticket;
  }

  @XmlElement(name="Latitude")
  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  @XmlElement(name="Longitude")
  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  @XmlElement(name="Precision")
  public Double getPrecision() {
    return precision;
  }

  public void setPrecision(Double precision) {
    this.precision = precision;
  }

  @XmlElement(name="Recognition")
  public String getRecognition() {
    return recognition;
  }

  public void setRecognition(String recognition) {
    this.recognition = recognition;
  }

  @XmlElement(name="FromUserName")
  public String getFromUserName() {
    return fromUserName;
  }

  public void setFromUserName(String fromUserName) {
    this.fromUserName = fromUserName;
  }

  protected static WxCpXmlMessage fromXml(String xml) {
    return XStreamTransformer.fromXml(WxCpXmlMessage.class, xml);
  }

  protected static WxCpXmlMessage fromXml(InputStream is) {
    return XStreamTransformer.fromXml(WxCpXmlMessage.class, is);
  }

  /**
   * 从加密字符串转换
   *
   * @param encryptedXml
   * @param wxCpConfigStorage
   * @param timestamp
   * @param nonce
   * @param msgSignature
   * @return
   */
  public static WxCpXmlMessage fromEncryptedXml(
      String encryptedXml,
      WxCpConfigStorage wxCpConfigStorage,
      String timestamp, String nonce, String msgSignature) {
    WxCpCryptUtil cryptUtil = new WxCpCryptUtil(wxCpConfigStorage);
    String plainText = cryptUtil.decrypt(msgSignature, timestamp, nonce, encryptedXml);
    return fromXml(plainText);
  }

  public static WxCpXmlMessage fromEncryptedXml(
      InputStream is,
      WxCpConfigStorage wxCpConfigStorage,
      String timestamp, String nonce, String msgSignature) {
    try {
      return fromEncryptedXml(IOUtils.toString(is, "UTF-8"), wxCpConfigStorage, timestamp, nonce, msgSignature);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @XmlElement(name="Status")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @XmlElement(name="TotalCount")
  public Integer getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }

  @XmlElement(name="FilterCount")
  public Integer getFilterCount() {
    return filterCount;
  }

  public void setFilterCount(Integer filterCount) {
    this.filterCount = filterCount;
  }

  @XmlElement(name="SentCount")
  public Integer getSentCount() {
    return sentCount;
  }

  public void setSentCount(Integer sentCount) {
    this.sentCount = sentCount;
  }

  @XmlElement(name="ErrorCount")
  public Integer getErrorCount() {
    return errorCount;
  }

  public void setErrorCount(Integer errorCount) {
    this.errorCount = errorCount;
  }



  @Override
  public String toString() {
    return "WxCpXmlMessage{" +
        "agentId=" + agentId +
        ", toUserName='" + toUserName + '\'' +
        ", fromUserName='" + fromUserName + '\'' +
        ", createTime=" + createTime +
        ", msgType='" + msgType + '\'' +
        ", content='" + content + '\'' +
        ", msgId=" + msgId +
        ", picUrl='" + picUrl + '\'' +
        ", mediaId='" + mediaId + '\'' +
        ", format='" + format + '\'' +
        ", thumbMediaId='" + thumbMediaId + '\'' +
        ", locationX=" + locationX +
        ", locationY=" + locationY +
        ", scale=" + scale +
        ", label='" + label + '\'' +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", url='" + url + '\'' +
        ", event='" + event + '\'' +
        ", eventKey='" + eventKey + '\'' +
        ", ticket='" + ticket + '\'' +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        ", precision=" + precision +
        ", recognition='" + recognition + '\'' +
        ", status='" + status + '\'' +
        ", totalCount=" + totalCount +
        ", filterCount=" + filterCount +
        ", sentCount=" + sentCount +
        ", errorCount=" + errorCount +
        '}';
  }

}
