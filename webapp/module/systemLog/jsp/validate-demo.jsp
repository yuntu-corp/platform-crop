<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp"%>
<div class="box1 fl" boxTitle="表单验证">
	<div class="box1 fl" boxTitle="1.将校验规则写到控件中">
		<font color="green"><b>展示效果：</b></font>
		<div style="margin-top: 10px; margin-left: 10px; margin-right: 10px;">
			<uc:form id="theForm1">
				<fieldset>
					<legend>表单</legend>
					<p>
						<label>Name (required, at least 2 characters)</label>
						<input id="name" type="text" name="name" minlength="2"  class="required" />
					</p>
					<p>
						<label>E-Mail (required)</label>
						<input id="email" type="text" name="email" class="required email"/>
					</p>
					<p>
						<label>URL (optional)</label>
						<input id="url" type="text" name="url" class="url"/>
					</p>
					<p>
						<label>远程验证</label>
						<input id="remote" name="remote" remote="${path}/dm/component/validateRemote" class="required"/>
					</p>
					<p>
						<label>Password</label>
						<input id="password" name="password" type="password" minlength="5"  class="required" />
					</p>
					<p>
						<label>确认密码</label>
						<input id="confirm_password" type="password" name="confirm_password" equalTo='#password' />
					</p>
					<p>
						<button onclick="validate('theForm1');">验证</button>
						<!-- 					<input class="submit" type="submit" value="Submit"> -->
					</p>
				</fieldset>

			</uc:form>

			<!-- <a href="javascript:;" onclick="setReset();">重置</a> -->
		</div>
		<font class="green fb">html代码部分：</font>
		<pre class="syntax brush-html">
			<form id="theForm1">
				<input id="name" type="text" name="name" minlength="2"  class="required" />
				<input id="email" type="text" name="email" class="required email" />
				<input id="url" type="text" name="url" class="url" />
				<input id="remote" name="remote" remote="${path}/dm/component/validateRemote"  class="required" />
				<input id="password" name="password" type="password" minlength="5"  class="required" />
				<input id="confirm_password" name="confirm_password" type="password" equalTo='#password' />
			</form>
		</pre>

		<font class="green fb">js代码部分：</font>
		<pre class="syntax brush-javascript">
			$.hz.validate.init("theForm1",{debug: true});
		</pre>
	</div>

	<div class="box1 fl" boxTitle="2.将校验规则写到JS中">
		<font color="green"><b>展示效果：</b></font>
		<div style="margin-top: 10px; margin-left: 10px; margin-right: 10px;">
			<uc:form id="theForm2">
				<fieldset>
					<legend>表单</legend>
					<table>
						<tr>
							<td>
								<label>Name (required, at least 2 characters)</label>
								<input id="name2" type="text" name="name"/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td>
								<label>E-Mail (required)</label>
								<input id="email2" type="text" name="email"/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td>
								<label>URL (optional)</label>
								<input id="url2" type="text" name="url"/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td>
								<label>远程验证</label>
								<input id="remote2" name="remote"/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td>
								<label>Password</label>
								<input id="password2" type="password" name="password"/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td>
								<label>确认密码</label>
								<input id="confirm_password2" type="password" name="confirm_password" />
							</td>
							<td></td>
						</tr>
					</table>
					<p>
						<button onclick="validate('theForm2');">验证</button>
					</p>
				</fieldset>

			</uc:form>

			<!-- <a href="javascript:;" onclick="setReset();">重置</a> -->
		</div>
		<font class="green fb">html代码部分：</font>
		<pre class="syntax brush-html">
			<form id="theForm2">
				<table>
					<tr>
						<td>
							<label>Name (required, at least 2 characters)</label>
							<input id="name2" type="text" name="name"/>
						</td>
						<td></td>
					</tr>
				</table>
				……
			</form>
		</pre>

		<font class="green fb">js代码部分：</font>
		<pre class="syntax brush-javascript">
			var validateOptions = {
				debug: true,
				errorPlacement: function(error, element){
					error.appendTo(element.parent("td").next("td"));
				},
				rules : {
					name : {
						required : true,
						minlength : 2
					},
					email : {
						required : true,
						email : true
					},
					url : {
						url : true
					},
					remote : {
						required : true,
						remote : "${path}/dm/component/validateRemote"
					},
					password : {
						required : true,
						minlength : 5
					},
					confirm_password : {
						required : true,
						equalTo : "#password2"
					}
				},
				messages : {
					confirm_password : {
						required : "请输入确认密码",
						equalTo : "两次密码输入不一致！"
					}
				}
			}
			$.hz.validate.init("theForm2",validateOptions);
		</pre>
	</div>

	<div class="box1 fl" boxTitle="3.radio、checkbox、select的验证">
		<font color="green"><b>展示效果：</b></font>
		<div style="margin-top: 10px; margin-left: 10px; margin-right: 10px;">
			<uc:form id="theForm3">
				<fieldset>
					<legend>表单</legend>
					<p>
						<label>Radio</label>
						<input  type="radio" id="gender_male" value="m" name="gender" required />
						<input  type="radio" id="gender_female" value="f" name="gender"/>
					</p>
					<p>
						<label>CheckBox (至少选择两个)</label>
						<input type="checkbox" class="checkbox" id="spam_email" value="email" name="spam[]" title="至少选择两个!" required minlength="2"/>
						<input type="checkbox" class="checkbox" id="spam_phone" value="phone" name="spam[]" />
						<input type="checkbox" class="checkbox" id="spam_mail" value="mail" name="spam[]" />
					</p>
					<p>
						<label>CheckBox (选择两到三个)</label>
						<input type="checkbox" class="checkbox" id="spam_email_" value="email" name="spam_[]" title="选择两到三个!" required rangelength="[2,3]"/>
						<input type="checkbox" class="checkbox" id="spam_phone_" value="phone" name="spam_[]" />
						<input type="checkbox" class="checkbox" id="spam_mail_" value="mail" name="spam_[]" />
						<input type="checkbox" class="checkbox" id="spam_other_" value="other" name="spam_[]" />
						<input type="checkbox" class="checkbox" id="spam_other_2" value="other" name="spam_[]" />
					</p>
					<p>
						<label>Select (required)</label>
						<select id="jungle" name="jungle" title="请选择一个!" required>
						    <option value="">请选择</option>
						    <option value="1">Buga</option>
						    <option value="2">Baga</option>
						    <option value="3">Oi</option>
						</select>
					</p>
					<p>
						<label>select(至少选择两个)</label>
						<select id="fruit" name="fruit" title="至少选择两个!" multiple="multiple" required  minlength="2">
						    <option value="b">Banana</option>
						    <option value="a">Apple</option>
						    <option value="p">Peach</option>
						    <option value="t">Turtle</option>
						</select>
					</p>
					<p>
						<button onclick="validate('theForm3');">验证</button>
						<!-- 					<input class="submit" type="submit" value="Submit"> -->
					</p>
				</fieldset>

			</uc:form>

			<!-- <a href="javascript:;" onclick="setReset();">重置</a> -->
		</div>
		<font class="green fb">html代码部分：</font>
		<pre class="syntax brush-html">
			<input  type="radio" id="gender_male" value="m" name="gender" required />
			<input  type="radio" id="gender_female" value="f" name="gender"/>
			
			<input type="checkbox" class="checkbox" id="spam_email" value="email" name="spam[]"  title="至少选择两个!" required minlength="2"/>
			<input type="checkbox" class="checkbox" id="spam_phone" value="phone" name="spam[]" />
			<input type="checkbox" class="checkbox" id="spam_mail" value="mail" name="spam[]" />
			
			<select id="jungle" name="jungle" title="请选择一个!" required>
			    <option value="">请选择</option>
			    <option value="1">Buga</option>
			    <option value="2">Baga</option>
			    <option value="3">Oi</option>
			</select>
			
			<select id="fruit" name="fruit" title="至少选择两个!" multiple="multiple" required  minlength="2">
			    <option value="b">Banana</option>
			    <option value="a">Apple</option>
			    <option value="p">Peach</option>
			    <option value="t">Turtle</option>
			</select>
		</pre>

		<font class="green fb">js代码部分：</font>
		<pre class="syntax brush-javascript">
			$.hz.validate.init("theForm3",{displayType:"float",debug: true});
		</pre>
	</div>
	
	<font class="green fb">常用API：</font>
	<table class="cssTableBody">
		<tr>
			<th colspan="4" align="center">参数列表</th>
		</tr>
		<tr>
		   <th width="300">名称</th>
		   <th width="200">类型</th>
		   <th width="200">默认值</th>
		   <th>描述</th>
		</tr>
		<tr>
		   <td>debug</td>
		   <td>Boolean</td>
		   <td>false</td>
		   <td>进行调试模式（表单不提交）</td>
		</tr>
		<tr>
		   <td>displayType</td>
		   <td>String</td>
		   <td>none</td>
		   <td>
		 	 	 错误提示的显示分类<br/>
		 	 	none：显示在表单控件后面<br/>
		 	 	float:浮动定位到表单控件后面，避免与errorPlacement参数一起使用
		   </td>
		</tr>
		<tr>
		   <td>errorPlacement</td>
		   <td>Function</td>
		   <td></td>
		   <td>
		  		自定义错误放到哪里。例如：<br/>
				$.hz.validate.init("theForm",{
					errorPlacement:function(error,element){
						error.appendTo(element.parent("td").next("td"));
					}
				});
		   </td>
		</tr>
		<tr>
		   <td>submitHandler</td>
		   <td>Function</td>
		   <td></td>
		   <td>
		  		 通过验证后运行的函数,里面要加上表单提交的函数,否则表单不会提交。例如：<br/>
				$.hz.validate.init("theForm",{
					submitHandler:function(form){
						$(form).ajaxSubmit();
					}
				});
		   </td>
		</tr>
		<tr>
		   <td>ignore</td>
		   <td>String</td>
		   <td></td>
		   <td>
		   		对某些元素不进行验证。例如：<br/>
				$.hz.validate.init("theForm",{ignore:".ignore"});
		   </td>
		</tr>
		<tr>
		   <td>ignoreTitle</td>
		   <td>Boolean</td>
		   <td>false</td>
		   <td>
		   		是否忽略验证提示采用Title属性
		   </td>
		</tr>
		<tr>
		   <td>rules</td>
		   <td>JSON</td>
		   <td></td>
		   <td>自定义规则,key:value的形式,key是要验证的元素,value可以是字符串或对象</td>
		</tr>
		<tr>
		   <td>messages</td>
		   <td>JSON</td>
		   <td></td>
		   <td>自定义的提示信息key:value的形式key是要验证的元素,值是字符串或函数</td>
		</tr>
		<tr>
		   <td>onfocusout</td>
		   <td>Boolean</td>
		   <td>true</td>
		   <td>是否在获取焦点时验证</td>
		</tr>
		<tr>
		   <td>onkeyup</td>
		   <td>Boolean</td>
		   <td>true</td>
		   <td>是否在敲击键盘时验证</td>
		</tr>
		<tr>
		   <td>onclick</td>
		   <td>Boolean</td>
		   <td>true</td>
		   <td>是否在鼠标点击时验证（一般验证checkbox,radiobox）</td>
		</tr>
	</table>
	<br/>
	<table class="cssTableBody">
		<tr>
			<th colspan="2" align="center">方法列表</th>
		</tr>
		<tr>
		   <th width="300">名称</th>
		   <th>描述</th>
		</tr>
		<tr>
		   <td>validate(options)</td>
		   <td>注册验证所选Form<br/>示例：$.hz.validate.init("theForm",{debug: true});</td>
		</tr>
		<tr>
		   <td>valid()</td>
		   <td>检查Form是否验证通过<br/>示例：$.hz.validate.valid("theForm");</td>
		</tr>
		<tr>
		   <td>addRules(rules)</td>
		   <td>增加验证规则<br/>示例：$.hz.validate.addRules("theForm",rules);</td>
		</tr>
		<tr>
		   <td>removeRules(rules)</td>
		   <td>删除验证规则<br/>示例：$.hz.validate.removeRules("theForm",rules);</td>
		</tr>
	</table>
</div>

<script>
	function validate(formId) {
		$.hz.validate.valid(formId);
	}

	function initValidateForm1() {
		$.hz.validate.init("theForm1",{debug: true});
	}

	function initValidateForm2() {
		var validateOptions = {
			debug: true,
			errorPlacement: function(error, element){
				error.appendTo(element.parent("td").next("td"));
			},
			rules : {
				name : {
					required : true,
					minlength : 2
				},
				email : {
					required : true,
					email : true
				},
				url : {
					url : true
				},
				remote : {
					required : true,
					remote : "${path}/dm/component/validateRemote"
				},
				password : {
					required : true,
					minlength : 5
				},
				confirm_password : {
					required : true,
					equalTo : "#password2"
				}
			},
			messages : {
				confirm_password : {
					required : "请输入确认密码",
					equalTo : "两次密码输入不一致！"
				}
			}
		}
		$.hz.validate.init("theForm2",validateOptions);
	}

	function initValidateForm3() {
		$.hz.validate.init("theForm3",{displayType:"float",debug: true});
	}
	
	function init() {
		initValidateForm1();
		initValidateForm2();
		initValidateForm3();
	}

	$(document).ready(function() {
		init()
		$(".box1").box1();
	});
</script>