/*
 * Inline Form Validation Engine 2.2, jQuery plugin
 *
 * Copyright(c) 2010, Cedric Dugas
 *
 * 2.0 Rewrite by Olivier Refalo
 *
 * Form validation engine allowing custom regex rules to be added.
 * Licensed under the MIT License
 */
(function($) {

	var methods = {

		/**
		 * Kind of the constructor, called before any action
		 * @param {Map} user options
		 */
		init : function(options) {
			var form = this;
			if (!form.data('jqv') || form.data('jqv') == null) {
				methods._saveOptions(form, options);
				$(document).on("click",".formError", function() {
					$(this).fadeOut(150, function() {
							$(this).remove();
					});
				});
			}
		},
		/**
		 * jQuery.validationEngine
		 * Attachs jQuery.validationEngine to form.submit and field.blur events
		 * Takes an optional params: a list of options
		 * ie. jQuery("#formID1").validationEngine('attach', {promptPosition : "centerRight"});
		 */
		attach : function(userOptions) {
			var form = this;
			var options;
			if (userOptions){
				options = methods._saveOptions(form, userOptions);
			}else{
				options = form.data('jqv');
			}

			var validateAttribute = (form.find("[data-validation-engine*=validate]")) ? "data-validation-engine" : "class";

			if (!options.binded) {

				// bind fields blur click 
				form.find("[class*=validate]").not("[type=checkbox]").not("[type=radio]").not(".datepicker").bind(options.validationEventTrigger,methods._onFieldEvent);
				form.find("[class*=validate][type=checkbox],[class*=validate][type=radio]").bind("click", methods._onFieldEvent);
				form.find("[class*=validate][class*=datepicker]").bind(options.validationEventTrigger, {"delay" : 300}, methods._onFieldEvent);
				//bind 不需要验证的，验证是否有特殊字符
				form.find("input[type=text],textarea").not("[class*=validate]").not(":hidden").not(":disabled").bind(options.validationEventTrigger,methods._onFieldEvent);

				// bind form.submit
				form.bind("submit", methods._onSubmitEvent);

				//没有id的控件添加id属性
				form.find("input[type=text],textarea").not("[id]").each(function(){
					$(this).attr("id",$.uuid());
				});
				
				options.binded = true;
			}
			return this;
		},
		/**
		 * Unregisters any bindings that may point to jQuery.validaitonEngine
		 */
		detach : function() {
			var form = this;
			var options = form.data('jqv');

			// unbind fields
			form.find("[class*=validate]").not("[type=checkbox]").unbind(options.validationEventTrigger, methods._onFieldEvent);
			form.find("[class*=validate][type=checkbox],[class*=validate][type=radio]").unbind("click", methods._onFieldEvent);
			form.find("input[type=text],textarea").not("[class*=validate]").not(":hidden").not(":disabled").unbind(options.validationEventTrigger,methods._onFieldEvent);
			
			// unbind form.submit
			form.unbind("submit", methods.onAjaxFormComplete);

			form.removeData('jqv');
		},
		/**
		 * Validates the form fields, shows prompts accordingly.
		 * Note: There is no ajax form validation with this method, only field ajax validation are evaluated
		 *
		 * @return true if the form validates, false if it fails
		 */
		validate : function() {
			var form = $(this);
			var options = form.data('jqv');
			if(!methods._checkAjaxStatus(options)){
				return false ;
			}
			return methods._validateFields(this,true);
		},
		/**
		 * Validates one field, shows prompt accordingly.
		 * Note: There is no ajax form validation with this method, only field ajax validation are evaluated
		 *
		 * @return true if the form validates, false if it fails
		 */
		validateField : function(el) {
			var options = $(this).data('jqv');
			return methods._validateField($(el), options);
		},
		/**
		 * Validates the form fields, shows prompts accordingly.
		 * Note: this methods performs fields and form ajax validations(if setup)
		 *
		 * @return true if the form validates, false if it fails, undefined if ajax is used for form validation
		 */
		validateform : function() {
			return methods._onSubmitEvent.call(this);
		},
		/**
		 *  Redraw prompts position, useful when you change the DOM state when validating
		 */
		updatePromptsPosition : function() {
			var form = this.closest('form');
			var options = form.data('jqv');
			// No option, take default one .not(':hidden')去掉了隐藏标签不验证
			form.find('[class*=validate]').not(":disabled").each(function() {
				var field = $(this);
				var prompt = methods._getPrompt(field);
				var promptText = $(prompt).find(".formErrorContent").html();
				if (prompt){
					methods._updatePrompt(field, $(prompt),promptText, undefined, false,options);
				}
			})
		},
		/**
		 * IDDisplays a prompt on a element.
		 * Note that the element needs an id!
		 *
		 * @param {String} promptText html text to display type
		 * @param {String} type the type of bubble: 'pass' (green), 'load' (black) anything else (red)
		 * @param {String} possible values topLeft, topRight, bottomLeft, centerRight, bottomRight
		 */
		showPrompt : function(promptText, type, promptPosition, showArrow) {
			var form = this.closest('form');
			var options = form.data('jqv');
			// No option, take default one
			if (!options)
				options = methods._saveOptions(this, options);
			if (promptPosition)
				options.promptPosition = promptPosition;
			options.showArrow = showArrow == true;

			methods._showPrompt(this, promptText, type, false, options);
		},
		/**
		 * Closes all error prompts on the page
		 */
		hidePrompt : function() {
			var promptClass = "." + methods._getClassName($(this).attr("id")) + "formError";
			$(promptClass).fadeTo("fast", 0.3, function() {
				$(this).remove();
			});
		},
		/**
		 * Closes form error prompts, CAN be invidual
		 */
		hide : function() {
			var closingtag;
			if ($(this).is("form")) {
				closingtag = "parentForm" + $(this).attr('id');
			} else {
				closingtag = $(this).attr('id') + "formError";
			}
			$('.' + closingtag).fadeTo("fast", 0.3, function() {
				$(this).remove();
			});
		},
		/**
		 * Closes all error prompts on the page
		 */
		hideAll : function() {
			$('.formError').fadeTo("fast", 0.3, function() {
				$(this).remove();
			});
		},
		/**
		 * Typically called when user exists a field using tab or a mouse click, triggers a field
		 * validation
		 */
		_onFieldEvent : function(event) {
			var field = $(this);
			var form = field.closest('form');
			var options = form.data('jqv');
			// validate the current field
			window.setTimeout(function() {
				methods._validateField(field, options);
			}, (event.data) ? event.data.delay : 0);

		},
		/**
		 * Called when the form is submited, shows prompts accordingly
		 *
		 * @param {jqObject}
		 *            form
		 * @return false if form submission needs to be cancelled
		 */
		_onSubmitEvent : function() {
			var form = $(this);
			var options = form.data('jqv');

			// validate each field (- skip field ajax validation, no necessary since we will perform an ajax form validation)
			var r = methods._validateFields(form, true), _r = (!options.customValidate || options.customValidate(options));

			if (r && _r && options.ajaxFormValidation) {
				methods._validateFormWithAjax(form, options);
				return false;
			}
			
			if (r && !methods._checkAjaxStatus(options)) {
                return false;
            }

			if (options.onValidationComplete) {
				options.onValidationComplete(form, r);
				return false;
			}
			return r && _r;
		},

		/**
		 * Return true if the ajax field validations passed so far
		 * @param {Object} options
		 * @return true, is all ajax validation passed so far (remember ajax is async)
		 */
		_checkAjaxStatus : function(options) {
			var status = true;
			$.each(options.ajaxValidCache, function(key, value) {
				if (!value) {
					status = false;
					// break the each
					return false;
				}
			});
			return status;
		},
		/**
		 * 验证表单域，从而显示提示Validates form fields, shows prompts accordingly
		 *
		 * @param {jqObject}
		 *            form
		 * @param {skipAjaxFieldValidation}
		 *            boolean - when set to true, ajax field validation is skipped, typically used when the submit button is clicked
		 *
		 * @return true if form is valid, false if not, undefined if ajax form validation is done
		 */
		_validateFields : function(form, skipAjaxValidation) {
			var options = form.data('jqv');

			var errorFound = false;

			// Trigger hook, start validation
			form.trigger("jqv.form.validating");
			// first, evaluate status of non ajax fields
			form.find("[class*=validate]").not(":disabled").each(function() {
				var field = $(this);
				errorFound |= methods._validateField(field,options, skipAjaxValidation);
			});
			//验证没有class*=validate的input[type=text]和textarea
			form.find("input[type=text],textarea").not("[class*=validate]").not(":hidden").not(":disabled").each(function() {
				var field = $(this);
				errorFound |= methods._validateField(field,options, skipAjaxValidation);
			});
			
			// second, check to see if all ajax calls completed ok
			// errorFound |= !methods._checkAjaxStatus(options);

			// thrird, check status and scroll the container accordingly
			form.trigger("jqv.form.result", [ errorFound ]);

			if (errorFound) {
				if (options.scroll) {
					// get the position of the first error, there should be at least one, no need to check this
					//var destination = form.find(".formError:not('.greenPopup'):first").offset().top;

					// look for the visually top prompt
					var destination = Number.MAX_VALUE;
					var fixleft = 0;
					var lst = $(".formError:not('.greenPopup')");

					for ( var i = 0; i < lst.length; i++) {
						var d = $(lst[i]).offset().top;
						if (d < destination) {
							destination = d;
							fixleft = $(lst[i]).offset().left;
						}
					}

					if (!options.isOverflown){
						$("html:not(:animated),body:not(:animated)").animate( {
							scrollTop : destination,
							scrollLeft : fixleft
						}, 1100);
					} else {
						var overflowDIV = $(options.overflownDIV);
						var scrollContainerScroll = overflowDIV.scrollTop();
						var scrollContainerPos = -parseInt(overflowDIV.offset().top);

						destination += scrollContainerScroll + scrollContainerPos - 5;
						var scrollContainer = $(options.overflownDIV + ":not(:animated)");

						scrollContainer.animate( {
							scrollTop : destination
						}, 1100);

						$("html:not(:animated),body:not(:animated)").animate( {
							scrollTop : overflowDIV.offset().top,
							scrollLeft : fixleft
						}, 1100);
					}
				}
				return false;
			}
			return true;
		},
		/**
		 * This method is called to perform an ajax form validation.
		 * During this process all the (field, value) pairs are sent to the server which returns a list of invalid fields or true
		 *
		 * @param {jqObject} form
		 * @param {Map} options
		 */
		_validateFormWithAjax : function(form, options) {

			var data = form.serialize();
			var url = (options.ajaxFormValidationURL) ? options.ajaxFormValidationURL : form.attr("action");
			$.ajax( {
						type : "GET",
						url : url,
						cache : false,
						dataType : "json",
						data : data,
						form : form,
						methods : methods,
						options : options,
						beforeSend : function() {
							return options.onBeforeAjaxFormValidation(form,
									options);
						},
						error : function(data, transport) {
							methods._ajaxError(data, transport);
						},
						success : function(json) {

							if (json !== true) {

								// getting to this case doesn't necessary means that the form is invalid
						// the server may return green or closing prompt actions
						// this flag helps figuring it out
						var errorInForm = false;
						for ( var i = 0; i < json.length; i++) {
							var value = json[i];

							var errorFieldId = value[0];
							var errorField = $($("#" + errorFieldId)[0]);

							// make sure we found the element
							if (errorField.length == 1) {

								// promptText or selector
								var msg = value[2];
								// if the field is valid
								if (value[1] == true) {

									if (msg == "" || !msg) {
										// if for some reason, status==true and error="", just close the prompt
										methods._closePrompt(errorField);
									} else {
										// the field is valid, but we are displaying a green prompt
										if (options.allrules[msg]) {
											var txt = options.allrules[msg].alertTextOk;
											if (txt)
												msg = txt;
										}
										methods._showPrompt(errorField, msg,
												"pass", false, options, true);
									}

								} else {
									// the field is invalid, show the red error prompt
									errorInForm |= true;
									if (options.allrules[msg]) {
										var txt = options.allrules[msg].alertText;
										if (txt)
											msg = txt;
									}
									methods._showPrompt(errorField, msg, "",
											false, options, true);
								}
							}
						}
						options.onAjaxFormComplete(!errorInForm, form, json,
								options);
					} else
						options.onAjaxFormComplete(true, form, "", options);
				}
					});

		},
		/**
		 * Validates field, shows prompts accordingly
		 *
		 * @param {jqObject}
		 *            field
		 * @param {Array[String]}
		 *            field's validation rules
		 * @param {Map}
		 *            user options
		 * @return true if field is valid
		 */
		_validateField : function(field, options, skipAjaxValidation) {
			if (!field.attr("id")){
				$.error("jQueryValidate: an ID attribute is required for this field: " + field.attr("name") + " class:" + field.attr("class"));
			}

			var rulesParsing = field.attr('class');
			var getRules = /validate\[(.*)\]/.exec(rulesParsing);
			var rules = [];
			if (getRules){//有验证规则
				rules = getRules[1].split(/\[|,|\]/);
			}
			// true if we ran the ajax validation, tells the logic to stop messing with prompts
			var isAjaxValidator = false;
			var fieldName = field.attr("name");
			var promptText = "";
			var required = false;
			options.isError = false;
			options.showArrow = true;

			if(!field.hasClass("ignoreInvalidCharacter")){//有ignoreInvalidCharacter的class的元素不验正
				rules.push("invalidCharacter");//加入无效字符规则
			}
			
			for ( var i = 0; i < rules.length; i++) {
				var errorMsg = undefined;
				switch (rules[i]) {
				case "required":
					required = true;
					errorMsg = methods._required(field, rules, i, options);
					break;
				case "custom":
					errorMsg = methods._customRegex(field, rules, i, options);
					break;
				case "groupRequired":
					var classGroup = "[class*=" + rules[i + 1] + "]";
					var firstOfGroup = field.closest("form").find(classGroup).eq(0);
					if (firstOfGroup[0] != field[0]) {
						methods._validateField(firstOfGroup, options,skipAjaxValidation);
						options.showArrow = true;
						continue;
					}
					errorMsg = methods._groupRequired(field, rules, i, options);
					if (errorMsg){
						required = true;
					}
					options.showArrow = false;
					break;
				case "ajax":
					// ajax has its own prompts handling technique
					if (!skipAjaxValidation) {
						methods._ajax(field, rules, i, options);
						isAjaxValidator = true;
					}
					break;
				case "minSize":
					errorMsg = methods._minSize(field, rules, i, options);
					break;
				case "maxSize":
					errorMsg = methods._maxSize(field, rules, i, options);
					break;
				case "min":
					errorMsg = methods._min(field, rules, i, options);
					break;
				case "max":
					errorMsg = methods._max(field, rules, i, options);
					break;
				case "past"://未使用
					errorMsg = methods._past(field, rules, i, options);
					break;
				case "future"://未使用
					errorMsg = methods._future(field, rules, i, options);
					break;
				case "dateRange"://未使用
					var classGroup = "[class*=" + rules[i + 1] + "]";
					var firstOfGroup = field.closest("form").find(classGroup).eq(0);
					var secondOfGroup = field.closest("form").find(classGroup).eq(1);
					if (firstOfGroup[0].value || secondOfGroup[0].value) {
						errorMsg = methods._dateRange(firstOfGroup,secondOfGroup, rules, i, options);
					}
					if (errorMsg){
						required = true;
					}
					options.showArrow = false;
					break;

				case "dateTimeRange"://未使用
					var classGroup = "[class*=" + rules[i + 1] + "]";
					var firstOfGroup = field.closest("form").find(classGroup).eq(0);
					var secondOfGroup = field.closest("form").find(classGroup).eq(1);
					if (firstOfGroup[0].value || secondOfGroup[0].value) {
						errorMsg = methods._dateTimeRange(firstOfGroup,secondOfGroup, rules, i, options);
					}
					if (errorMsg){
						required = true;
					}
					options.showArrow = false;
					break;
				case "maxCheckbox":
					errorMsg = methods._maxCheckbox(field, rules, i, options);
					field = $(methods._getInputWithName(fieldName));
					break;
				case "minCheckbox":
					errorMsg = methods._minCheckbox(field, rules, i, options);
					field = $(methods._getInputWithName(fieldName));
					break;
				case "equals":
					errorMsg = methods._equals(field, rules, i, options);
					break;
				case "funcCall":
					errorMsg = methods._funcCall(field, rules, i, options);//未使用
					break;
				case "invalidCharacter":
					errorMsg = methods._invalidCharacter(field, rules, i, options);
					break;
				default:
					//$.error("jQueryValidator rule not found"+rules[i]);
				}
				if (errorMsg !== undefined) {
					//promptText += errorMsg + "<br/>";//将提示信息连接起来，都显示，
					promptText=errorMsg;//不将提示信息连接来，显示第一条信息，验证不通过
					options.isError = true;
					break;//发现错误，不再继续验证，将错误信息提示，始终只提示发现的第一条
				}
			}
			// If the rules required is not added, an empty field is not validated
			if (!required) {
				if (field.val() == ""){
					options.isError = false;
				}
			}
			// Hack for radio/checkbox group button, the validation go into the
			// first radio/checkbox of the group
			
			var fieldType = field.attr("type");
			var inputFields = $(methods._getInputWithName(fieldName));
			if ((fieldType == "radio" || fieldType == "checkbox") && fieldName && inputFields.size() > 1) {
				field = inputFields.last();
				options.showArrow = false;
			}
			if (fieldType == "text" && fieldName && inputFields.size() > 1) {
				//去掉input提示时，只提示最后一个的bug
				//field =	$(field.parents("form").find("input[name='" + fieldName+"']").last());
				options.showArrow = false;
			}
			
			if (options.isError) {
				// 如果发生错误 执行这里， 构建提示信息，radiolist checkboxlist uc:select(多选) 
				//  field是提示信息定位的标签，如果field是隐藏的，将会错位，需要指定一个field（jquery对象 ，必须有id）
				methods._showPrompt(field, promptText, "", false, options);
			} else {
				if (!isAjaxValidator){
					methods._closePrompt(field);
				}
			}
			field.trigger("jqv.field.result", [ field, options.isError, promptText ]);
			return options.isError;
		},
		/**
		 * Required validation
		 *
		 * @param {jqObject} field
		 * @param {Array[String]} rules
		 * @param {int} i rules index
		 * @param {Map}
		 *            user options
		 * @return an error string if validation failed
		 */
		_required : function(field, rules, i, options) {
			switch (field.attr("type")) {
			case "text":
			case "password":
			case "textarea":
			case "file":
			default:
				if (!field.val() || !$.trim(field.val())){
					var msg = options.allrules[rules[i]].alertText;
					if(field.attr("requiredErrorMsg")){
						msg = field.attr("requiredErrorMsg");
					}
					return msg;
				}
				break;
			case "radio":
			case "checkbox":
				var name = field.attr("name");
				if ($(methods._getInputWithNameAndChecked(name)).size() == 0) {//没有选择
					var msg = "";
					if ($(methods._getInputWithName(name)).size() == 1){
						msg = options.allrules[rules[i]].alertTextCheckboxe;
					} else {
						msg = options.allrules[rules[i]].alertTextCheckboxMultiple;
					}
					$(methods._getInputWithName(name)).each(function(){
						if($(this).attr("requiredErrorMsg")){
							msg = $(this).attr("requiredErrorMsg");
						}
					});
					return msg;
				}
				break;
			}
		},
		/**
		 * Validate that 1 from the group field is required
		 *
		 * @param {jqObject} field
		 * @param {Array[String]} rules
		 * @param {int} i rules index
		 * @param {Map}
		 *            user options
		 * @return an error string if validation failed
		 */
		_groupRequired : function(field, rules, i, options) {
			var classGroup = "[class*=" + rules[i + 1] + "]";
			var isValid = false;
			// Check all fields from the group
			field.closest("form").find(classGroup).each(function() {
				if (!methods._required($(this), rules, i, options)) {
					isValid = true;
					return false;
				}
			})

			if (!isValid){
				var msg = options.allrules[rules[i]].alertText;
				if(field.attr("groupRequiredErrorMsg")){
					msg = field.attr("groupRequiredErrorMsg");
				}
				return msg;
			}
		},
		/**
		 * Validate Regex rules
		 *
		 * @param {jqObject} field
		 * @param {Array[String]} rules
		 * @param {int} i rules index
		 * @param {Map}
		 *            user options
		 * @return an error string if validation failed
		 */
		_customRegex : function(field, rules, i, options) {
			var customRule = rules[i + 1];
			var rule = options.allrules[customRule];
			if (!rule) {
				alert("jqv:custom rule not found " + customRule);
				return;
			}
			
			var ex = rule.regex;
			if (!ex) {
				alert("jqv:custom regex not found " + customRule);
				return;
			}
			var pattern = new RegExp(ex);
			
			if (!pattern.test(field.val())){
				var msg = options.allrules[customRule].alertText;
				if(field.attr(customRule + "ErrorMsg")){
					msg = field.attr(customRule + "ErrorMsg");
				}
				return msg;
			}
		},
		/**
		 * Validate custom function outside of the engine scope
		 *
		 * @param {jqObject} field
		 * @param {Array[String]} rules
		 * @param {int} i rules index
		 * @param {Map}
		 *            user options
		 * @return an error string if validation failed
		 */
		_funcCall : function(field, rules, i, options) {
			var functionName = rules[i + 1];
			var fn = window[functionName];
			if (typeof (fn) == 'function')
				return fn(field, rules, i, options);

		},
		/**
		 * 验证非法的字符,html标签<>
		 * @param field
		 * @param rules
		 * @param i
		 * @param options
		 * @returns
		 */
		_invalidCharacter : function(field, rules, i, options) {
			switch (field.attr("type")) {
			case "file":
			case "radio":
			case "checkbox":
				break;
			default:
				if ($.trim(field.val()) != "" && /<[^>]*>/.exec($.trim(field.val()))){
					var msg = options.allrules[rules[i]].alertText;
					if(field.attr("invalidCharacterErrorMsg")){
						msg = field.attr("invalidCharacterErrorMsg");
					}
					return msg;
				}
			}
		},
		/**
		 * Field match
		 *
		 * @param {jqObject} field
		 * @param {Array[String]} rules
		 * @param {int} i rules index
		 * @param {Map}
		 *            user options
		 * @return an error string if validation failed
		 */
		_equals : function(field, rules, i, options) {
			var equalsField = rules[i + 1];
			if (field.val() != $("#" + equalsField).val()){
				var msg = options.allrules.equals.alertText;
				if(field.attr("equalsErrorMsg")){
					msg = field.attr("equalsErrorMsg");
				}
				return msg;
			}
		},
		/**
		 * Check the maximum size (in characters)
		 *
		 * @param {jqObject} field
		 * @param {Array[String]} rules
		 * @param {int} i rules index
		 * @param {Map}
		 *            user options
		 * @return an error string if validation failed
		 */
		_maxSize : function(field, rules, i, options) {
			var max = rules[i + 1];
			var len = field.val().length;

			if (len > max) {
				var rule = options.allrules.maxSize;
				var msg = rule.alertText + max + rule.alertText2;
				if(field.attr("maxSizeErrorMsg")){
					msg = field.attr("maxSizeErrorMsg");
					msg = msg.replaceAll("{0}",max).replaceAll("{1}",len);
				}
				return msg;
			}
		},
		/**
		 * Check the minimum size (in characters)
		 *
		 * @param {jqObject} field
		 * @param {Array[String]} rules
		 * @param {int} i rules index
		 * @param {Map}
		 *            user options
		 * @return an error string if validation failed
		 */
		_minSize : function(field, rules, i, options) {
			var min = rules[i + 1];
			var len = field.val().length;

			if (len < min) {
				var rule = options.allrules.minSize;
				var msg = rule.alertText + min + rule.alertText2;
				if(field.attr("minSizeErrorMsg")){
					msg = field.attr("minSizeErrorMsg");
					msg = msg.replaceAll("{0}",min).replaceAll("{1}",len);
				}
				return msg;
			}
		},
		/**
		 * Check number minimum value
		 *
		 * @param {jqObject} field
		 * @param {Array[String]} rules
		 * @param {int} i rules index
		 * @param {Map}
		 *            user options
		 * @return an error string if validation failed
		 */
		_min : function(field, rules, i, options) {
			var min = parseFloat(rules[i + 1]);
			var len = parseFloat(field.val());

			if (len < min) {
				var rule = options.allrules.min;
				var msg = rule.alertText + min;
				if(field.attr("minErrorMsg")){
					msg = field.attr("minErrorMsg");
					msg = msg.replaceAll("{0}",min).replaceAll("{1}",field.val());
				}
				return msg;
			}
		},
		/**
		 * Check number maximum value
		 *
		 * @param {jqObject} field
		 * @param {Array[String]} rules
		 * @param {int} i rules index
		 * @param {Map}
		 *            user options
		 * @return an error string if validation failed
		 */
		_max : function(field, rules, i, options) {
			var max = parseFloat(rules[i + 1]);
			var len = parseFloat(field.val());

			if (len > max) {
				var rule = options.allrules.max;
				var msg = rule.alertText + max;
				if(field.attr("maxErrorMsg")){
					msg = field.attr("maxErrorMsg");
					msg = msg.replaceAll("{0}",max).replaceAll("{1}",field.val());
				}
				return msg;
			}
		},
		/**
		 * Checks date is in the past
		 *
		 * @param {jqObject} field
		 * @param {Array[String]} rules
		 * @param {int} i rules index
		 * @param {Map}
		 *            user options
		 * @return an error string if validation failed
		 */
		_past : function(field, rules, i, options) {
			var p = rules[i + 1];
			var pdate = (p.toLowerCase() == "now") ? new Date() : methods._parseDate(p);
			var vdate = methods._parseDate(field.val());

			if (vdate < pdate) {
				var rule = options.allrules.past;
				var msg = rule.alertText + methods._dateToString(pdate);
				if(field.attr("pastErrorMsg")){
					msg = field.attr("pastErrorMsg");
					msg = msg.replaceAll("{0}",methods._dateToString(pdate)).replaceAll("{0}",methods._dateToString(vdate));
				}
				return msg;
			}
		},
		/**
		 * Checks date is in the future
		 *
		 * @param {jqObject} field
		 * @param {Array[String]} rules
		 * @param {int} i rules index
		 * @param {Map}
		 *            user options
		 * @return an error string if validation failed
		 */
		_future : function(field, rules, i, options) {
			var p = rules[i + 1];
			var pdate = (p.toLowerCase() == "now") ? new Date() : methods._parseDate(p);
			var vdate = methods._parseDate(field.val());

			if (vdate > pdate) {
				var rule = options.allrules.future;
				var msg = rule.alertText + methods._dateToString(pdate);
				if(field.attr("futureErrorMsg")){
					msg = field.attr("futureErrorMsg");
					msg = msg.replaceAll("{0}",methods._dateToString(pdate)).replaceAll("{0}",methods._dateToString(vdate));
				}
				return msg;
			}
		},
		/**
		 * Checks if valid date
		 *
		 * @param {string} date string
		 * @return a bool based on determination of valid date
		 */
		_isDate : function(value) {
			var dateRegEx = new RegExp(
					/^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$|^(?:(?:(?:0?[13578]|1[02])(\/|-)31)|(?:(?:0?[1,3-9]|1[0-2])(\/|-)(?:29|30)))(\/|-)(?:[1-9]\d\d\d|\d[1-9]\d\d|\d\d[1-9]\d|\d\d\d[1-9])$|^(?:(?:0?[1-9]|1[0-2])(\/|-)(?:0?[1-9]|1\d|2[0-8]))(\/|-)(?:[1-9]\d\d\d|\d[1-9]\d\d|\d\d[1-9]\d|\d\d\d[1-9])$|^(0?2(\/|-)29)(\/|-)(?:(?:0[48]00|[13579][26]00|[2468][048]00)|(?:\d\d)?(?:0[48]|[2468][048]|[13579][26]))$/);
			if (dateRegEx.test(value)) {
				return true;
			}
			return false;
		},
		/**
		 * Checks if valid date time
		 *
		 * @param {string} date string
		 * @return a bool based on determination of valid date time
		 */
		_isDateTime : function(value) {
			var dateTimeRegEx = new RegExp(
					/^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])\s+(1[012]|0?[1-9]){1}:(0?[1-5]|[0-6][0-9]){1}:(0?[0-6]|[0-6][0-9]){1}\s+(am|pm|AM|PM){1}$|^(?:(?:(?:0?[13578]|1[02])(\/|-)31)|(?:(?:0?[1,3-9]|1[0-2])(\/|-)(?:29|30)))(\/|-)(?:[1-9]\d\d\d|\d[1-9]\d\d|\d\d[1-9]\d|\d\d\d[1-9])$|^((1[012]|0?[1-9]){1}\/(0?[1-9]|[12][0-9]|3[01]){1}\/\d{2,4}\s+(1[012]|0?[1-9]){1}:(0?[1-5]|[0-6][0-9]){1}:(0?[0-6]|[0-6][0-9]){1}\s+(am|pm|AM|PM){1})$/);
			if (dateTimeRegEx.test(value)) {
				return true;
			}
			return false;
		},
		
		//Checks if the start date is before the end date
		//returns true if end is later than start
		_dateCompare : function(start, end) {
			return (new Date(start.toString()) < new Date(end.toString()));
		},
		
		/**
		 * Checks date range
		 *
		 * @param {jqObject} first field name
		 * @param {jqObject} second field name
		 * @return an error string if validation failed
		 */
		_dateRange : function(first, second, rules, i, options) {
			//are not both populated
			if ((!first[0].value && second[0].value) || (first[0].value && !second[0].value)) {
				var msg = options.allrules[rules[i]].alertText + options.allrules[rules[i]].alertText2;
				return msg;
			}

			//are not both dates
			if (!methods._isDate(first[0].value) || !methods._isDate(second[0].value)) {
				return options.allrules[rules[i]].alertText + options.allrules[rules[i]].alertText2;
			}

			//are both dates but range is off
			if (!methods._dateCompare(first[0].value, second[0].value)) {
				return options.allrules[rules[i]].alertText + options.allrules[rules[i]].alertText2;
			}
		},

		/**
		 * Checks date time range
		 *
		 * @param {jqObject} first field name
		 * @param {jqObject} second field name
		 * @return an error string if validation failed
		 */
		_dateTimeRange : function(first, second, rules, i, options) {
			//are not both populated
			if ((!first[0].value && second[0].value)
					|| (first[0].value && !second[0].value)) {
				return options.allrules[rules[i]].alertText
						+ options.allrules[rules[i]].alertText2;
			}
			//are not both dates
			if (!methods._isDateTime(first[0].value)
					|| !methods._isDateTime(second[0].value)) {
				return options.allrules[rules[i]].alertText
						+ options.allrules[rules[i]].alertText2;
			}
			//are both dates but range is off
			if (!methods._dateCompare(first[0].value, second[0].value)) {
				return options.allrules[rules[i]].alertText
						+ options.allrules[rules[i]].alertText2;
			}
		},
		/**
		 * Max number of checkbox selected
		 *
		 * @param {jqObject} field
		 * @param {Array[String]} rules
		 * @param {int} i rules index
		 * @param {Map}
		 *            user options
		 * @return an error string if validation failed
		 */
		_maxCheckbox : function(field, rules, i, options) {
			var nbCheck = rules[i + 1];
			var groupname = field.attr("name");
			var groupSize = $(methods._getInputWithNameAndChecked(groupname)).size();
			if (groupSize > nbCheck) {
				options.showArrow = false;
				var msg = "";
				if (options.allrules.maxCheckbox.alertText2){
					msg = options.allrules.maxCheckbox.alertText + " " + nbCheck + " " + options.allrules.maxCheckbox.alertText2;
				}else{
					msg = options.allrules.maxCheckbox.alertText;
				}
				if(field.attr("maxCheckboxErrorMsg")){
					msg = field.attr("maxCheckboxErrorMsg");
					msg = msg.replaceAll("{0}",nbCheck).replaceAll("{1}",groupSize);
				}
				return msg;
			}
		},
		/**
		 * Min number of checkbox selected
		 *
		 * @param {jqObject} field
		 * @param {Array[String]} rules
		 * @param {int} i rules index
		 * @param {Map}
		 *            user options
		 * @return an error string if validation failed
		 */
		_minCheckbox : function(field, rules, i, options) {
			var nbCheck = rules[i + 1];
			var groupname = field.attr("name");
			var groupSize = $(methods._getInputWithNameAndChecked(groupname)).size();
			if (groupSize < nbCheck) {
				options.showArrow = false;
				var msg = options.allrules.minCheckbox.alertText + " " + nbCheck + " " + options.allrules.minCheckbox.alertText2;
				if(field.attr("minCheckboxErrorMsg")){
					msg = field.attr("minCheckboxErrorMsg");
					msg = msg.replaceAll("{0}",nbCheck).replaceAll("{1}",groupSize);
				}
				return msg;
			}
		},
		/**
		 * Ajax field validation
		 *
		 * @param {jqObject} field
		 * @param {Array[String]} rules
		 * @param {int} i rules index
		 * @param {Map}
		 *            user options
		 * @return nothing! the ajax validator handles the prompts itself
		 */
		_ajax : function(field, rules, i, options) {

			var errorSelector = rules[i + 1];
			var rule = options.allrules[errorSelector];
			var extraData = rule.extraData;
			var extraDataDynamic = rule.extraDataDynamic;

			if (!extraData)
				extraData = "";

			if (extraDataDynamic) {
				var tmpData = [];
				var domIds = String(extraDataDynamic).split(",");
				for ( var i = 0; i < domIds.length; i++) {
					var id = domIds[i];
					if ($(id).length) {
						var inputValue = field.closest("form").find(id).val();
						var keyValue = id.replace('#', '') + '='
								+ escape(inputValue);
						tmpData.push(keyValue);
					}
				}
				extraDataDynamic = tmpData.join("&");
			} else {
				extraDataDynamic = "";
			}

			if (!options.isError) {
				var validateUrl = encodeURI(encodeURI(field.attr("validateUrl")));
				var excludeValue = field.attr("excludeValue")?field.attr("excludeValue"):"";
				var params = [];
				params.push("fieldId=" + field.attr("id"));
				params.push("&fieldValue=" + encodeURI(field.val()));
				params.push("&extraData=" + extraData);
				params.push("&excludeValue=" + encodeURI(excludeValue));
				params.push(extraDataDynamic);
				$
						.ajax( {
							type : "POST",
							url : validateUrl,
							cache : false,
							dataType : "json",
							data : params.join(""),
							field : field,
							rule : rule,
							methods : methods,
							options : options,
							beforeSend : function() {
								// build the loading prompt
							var loadingText = rule.alertTextLoad;
							if (loadingText)
								methods._showPrompt(field, loadingText, "load",
										true, options);
						},
						error : function(data, transport) {
							methods._ajaxError(data, transport);
						},
						success : function(json) {

							// asynchronously called on success, data is the json answer from the server
							var errorFieldId = json[0];
							var errorField = $($("#" + errorFieldId)[0]);
							// make sure we found the element
							if (errorField.length == 1) {
								var status = json[1];
								// read the optional msg from the server
								var msg = json[2];
								if (!status) {
									// Houston we got a problem - display an red prompt
									options.ajaxValidCache[errorFieldId] = false;
									options.isError = true;

									// resolve the msg prompt
									if (msg) {
										if (options.allrules[msg]) {
											var txt = options.allrules[msg].alertText;
											if (txt)
												msg = txt;
										}
									} else
										msg = rule.alertTextFail;

									methods._showPrompt(errorField, msg, "",
											true, options);
								} else {
									if (options.ajaxValidCache[errorFieldId] !== undefined)
										options.ajaxValidCache[errorFieldId] = true;

									// resolves the msg prompt
									if (msg) {
										if (options.allrules[msg]) {
											var txt = options.allrules[msg].alertTextOk;
											if (txt)
												msg = txt;
										}
									} else
										msg = rule.alertTextOk;

									// see if we should display a green prompt
									if (msg)
										methods._showPrompt(errorField, msg,
												"pass", true, options);
									else
										methods._closePrompt(errorField);
								}
							}
						}
						});
			}
		},
		/**
		 * Common method to handle ajax errors
		 *
		 * @param {Object} data
		 * @param {Object} transport
		 */
		_ajaxError : function(data, transport) {
			if (data.status == 0 && transport == null)
				alert("The page is not served from a server! ajax call failed");
			else if (typeof console != "undefined")
				console.log("Ajax error: " + data.status + " " + transport);
		},
		/**
		 * date -> string
		 *
		 * @param {Object} date
		 */
		_dateToString : function(date) {
			return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
		},
		/**
		 * Parses an ISO date
		 * @param {String} d
		 */
		_parseDate : function(d) {
			var dateParts = d.split("-");
			if (dateParts == d)
				dateParts = d.split("/");
			return new Date(dateParts[0], (dateParts[1] - 1), dateParts[2]);
		},
		/**
		 * Builds or updates a prompt with the given information
		 *
		 * @param {jqObject} field
		 * @param {String} promptText html text to display type
		 * @param {String} type the type of bubble: 'pass' (green), 'load' (black) anything else (red)
		 * @param {boolean} ajaxed - use to mark fields than being validated with ajax
		 * @param {Map} options user options
		 */
		_showPrompt : function(field, promptText, type, ajaxed, options, ajaxform) {
			var prompt = methods._getPrompt(field);
			// The ajax submit errors are not see has an error in the form,
			// When the form errors are returned, the engine see 2 bubbles, but those are ebing closed by the engine at the same time
			// Because no error was found befor submitting
			if (ajaxform)
				prompt = false;
			if (prompt)
				methods._updatePrompt(field, prompt, promptText, type, ajaxed, options);
			else
				methods._buildPrompt(field, promptText, type, ajaxed, options);
		},
		/**
		 * Builds and shades a prompt for the given field.
		 *
		 * @param {jqObject} field
		 * @param {String} promptText html text to display type
		 * @param {String} type the type of bubble: 'pass' (green), 'load' (black) anything else (red)
		 * @param {boolean} ajaxed - use to mark fields than being validated with ajax
		 * @param {Map} options user options
		 */
		_buildPrompt : function(field, promptText, type, ajaxed, options) {

			// create the prompt
			var prompt = $('<div>');
			prompt.addClass(methods._getClassName(field.attr("id")) + "formError");
			// add a class name to identify the parent form of the prompt
			if (field.is(":input"))
				prompt.addClass("parentForm" + methods._getClassName(field.parents('form').attr("id")));
			prompt.addClass("formError");
			
			switch (type) {
			case "pass":
				prompt.addClass("greenPopup");
				break;
			case "load":
				prompt.addClass("blackPopup");
			}
			if (ajaxed)
				prompt.addClass("ajaxed");

			// create the prompt content
			$('<div>').addClass("formErrorContent").html(promptText).appendTo(prompt);
			//加入左侧小指示箭头		
			$('<div>').addClass("tooltipfang").appendTo(prompt);	
			
			// create the css arrow pointing at the field
			// note that there is no triangle on max-checkbox and radio
			if (options.showArrow) {
				var arrow = $('<div>').addClass("formErrorArrow");

				switch (options.promptPosition) {
				case "bottomLeft":
				case "bottomRight":
					prompt.find(".formErrorContent").before(arrow);
					arrow.addClass("formErrorArrowBottom").html('<div class="line1"><!-- --></div><div class="line2"><!-- --></div><div class="line3"><!-- --></div><div class="line4"><!-- --></div><div class="line5"><!-- --></div><div class="line6"><!-- --></div><div class="line7"><!-- --></div><div class="line8"><!-- --></div><div class="line9"><!-- --></div><div class="line10"><!-- --></div>');
					break;
				case "topLeft":
				case "topRight":
					arrow.html('<div class="line10"><!-- --></div><div class="line9"><!-- --></div><div class="line8"><!-- --></div><div class="line7"><!-- --></div><div class="line6"><!-- --></div><div class="line5"><!-- --></div><div class="line4"><!-- --></div><div class="line3"><!-- --></div><div class="line2"><!-- --></div><div class="line1"><!-- --></div>');
					prompt.append(arrow);
					break;
				}
			}
			//Cedric: Needed if a container is in position:relative
			// insert prompt in the form or in the overflown container?
			// 插入提示信息到组件中
//			
			var promptObj = field;
//			alert(field.attr('id'))
			if(field.closest(".prompt-mark").length==1){
				promptObj=field.closest(".prompt-mark");
			}
			
			if (options.isOverflown){
				promptObj.before(prompt);
			}
			else{
				promptObj.after(prompt);
			}
			var pos = methods._calculatePosition(promptObj, prompt, options);
			prompt.css( {
				"top" : pos.callerTopPosition,
				"left" : pos.callerleftPosition,
				"marginTop" : pos.marginTopSize,
				"opacity" : 0
			});

			return prompt.animate( {
				"opacity" : 0.87
			});

		},
		/**
		 * Updates the prompt text field - the field for which the prompt
		 * @param {jqObject} field
		 * @param {String} promptText html text to display type
		 * @param {String} type the type of bubble: 'pass' (green), 'load' (black) anything else (red)
		 * @param {boolean} ajaxed - use to mark fields than being validated with ajax
		 * @param {Map} options user options
		 */
		_updatePrompt : function(field, prompt, promptText, type, ajaxed,options) {
			if (prompt) {
				if (type == "pass")
					prompt.addClass("greenPopup");
				else
					prompt.removeClass("greenPopup");

				if (type == "load")
					prompt.addClass("blackPopup");
				else
					prompt.removeClass("blackPopup");

				if (ajaxed)
					prompt.addClass("ajaxed");
				else
					prompt.removeClass("ajaxed");

				prompt.find(".formErrorContent").html(promptText);
				var promptObj = field;
//				alert(field.attr('name'))
				if(field.closest(".prompt-mark").length==1){
					promptObj=field.closest(".prompt-mark");
				}
				var pos = methods._calculatePosition(promptObj, prompt, options);
				prompt.animate( {
					"top" : pos.callerTopPosition,
					"left" : pos.callerleftPosition,
					"marginTop" : pos.marginTopSize
				});
			}
		},
		/**
		 * Closes the prompt associated with the given field
		 *
		 * @param {jqObject}
		 *            field
		 */
		_closePrompt : function(field) {
			var prompt = methods._getPrompt(field);
			
			//同时删除错误样式
			field.removeClass("errorStyle");
			
			if (prompt)
				prompt.fadeTo("fast", 0, function() {
					prompt.remove();
				});
		},
		closePrompt : function(field) {
			return methods._closePrompt(field);
		},
		/**
		 * Returns the error prompt matching the field if any
		 *
		 * @param {jqObject}
		 *            field
		 * @return undefined or the error prompt (jqObject)
		 */
		_getPrompt : function(field) {
			var className = field.attr("id").replace(":", "_") + "formError";
			var match = $("." + methods._escapeExpression(className))[0];
			if (match)
				return $(match);
		},
		/**
		 * Returns the escapade classname
		 *
		 * @param {selector}
		 *            className
		 */
		_escapeExpression : function(selector) {
			return selector.replace(/([#;&,\.\+\*\~':"\!\^$\[\]\(\)=>\|])/g,"\\$1");
		},
		/**
		 * Calculates prompt position
		 *
		 * @param {jqObject}
		 *            field
		 * @param {jqObject}
		 *            the prompt
		 * @param {Map}
		 *            options
		 * @return positions
		 */
		_calculatePosition : function(field, promptElmt, options) {

			var promptTopPosition, promptleftPosition, marginTopSize;
//			var fieldWidth = field.width();
//			var promptHeight = promptElmt.height();
			//解决提示位置不统一问题
			var fieldWidth = field.outerWidth();
			var promptHeight = promptElmt.outerHeight();
			
			
			var overflow = options.isOverflown;
			var _offset = 0;
			var fieldType = field.attr("type"), fieldName = field.attr("name");
			if ((fieldType == "radio" || fieldType == "checkbox")
					&& $(methods._getInputWithName(fieldName)).size() > 1) {
				_offset = 50;
			}
			if (overflow) {
				// is the form contained in an overflown container?
				promptTopPosition = promptleftPosition = 0;
				// compensation for the arrow
				marginTopSize = -promptHeight;
			} else {
				var offset = field.position();
				promptTopPosition = offset.top;
				promptleftPosition = offset.left + _offset;
				marginTopSize = 0;
			}

			switch (options.promptPosition) {

			default:
			case "topRight":
				if (overflow)
					// Is the form contained in an overflown container?
					promptleftPosition += fieldWidth - 30;
				else {
					promptleftPosition += fieldWidth - 30;
					promptTopPosition += -promptHeight - 2;
				}
				break;
			case "topLeft":
				promptTopPosition += -promptHeight - 10;
				break;
			case "centerRight":
				promptleftPosition += fieldWidth + 13;
				break;
			case "bottomLeft":
				promptTopPosition = promptTopPosition + field.height() + 15;
				break;
			case "bottomRight":
				promptleftPosition += fieldWidth - 30;
				promptTopPosition += field.height() + 5;
			}

			return {
				"callerTopPosition" : promptTopPosition + "px",
				"callerleftPosition" : promptleftPosition + "px",
				"marginTopSize" : marginTopSize + "px"
			};
		},
		/**
		 * Saves the user options and variables in the form.data
		 *
		 * @param {jqObject}
		 *            form - the form where the user option should be saved
		 * @param {Map}
		 *            options - the user options
		 * @return the user options (extended from the defaults)
		 */
		_saveOptions : function(form, options) {

			// is there a language localisation ?
			if ($.validationEngineLanguage)
				var allRules = $.validationEngineLanguage.allRules;
			else
				$.error("jQuery.validationEngine rules are not loaded, plz add localization files to the page");
			// --- Internals DO NOT TOUCH or OVERLOAD ---
			// validation rules and i18
			$.validationEngine.defaults.allrules = allRules;
	
			//修改为深拷贝，防止多个form共用相同的ajaxValidCache属性
			var userOptions = $.extend(true, {}, $.validationEngine.defaults, options);
	
			form.data('jqv', userOptions);
			return userOptions;
		},

		/**
		 * Removes forbidden characters from class name
		 * @param {String} className
		 */
		_getClassName : function(className) {
			return className.replace(":", "_").replace(".", "_");
		},
	
		_getInputWithName: function(name){
			var inputArray = [];
			var inputTags = document.getElementsByName(name);
			for(var i=0 ; i<inputTags.length ; i++){
				if($(inputTags[i]).is("input")){
					inputArray.push(inputTags[i]);
				}
			}
			if(inputArray.length==1){
				return inputArray[0];
			}
			return inputArray;
		},
		
		_getInputWithNameAndChecked: function(name){
			var inputArray = [];
			var inputTags = document.getElementsByName(name);
			for(var i=0 ; i<inputTags.length ; i++){
				if($(inputTags[i]).is("input") && inputTags[i].checked){
					inputArray.push(inputTags[i]);
				}
			}
			
			if(inputArray.length==1){
				return inputArray[0];
			}
			return inputArray;
		}
	
	};
	/**
	 * 验证某一特定域，根据待验证标签的验证规则
	 * @memberOf {TypeName} 
	 */
	$.fn.validateTheField=function(){
		var field=$(this);
		var form=field.parents("form");
		$(form).validationEngine("validateField", field);
	};
	/**
	 * Plugin entry point.
	 * You may pass an action as a parameter or a list of options.
	 * if none, the init and attach methods are being called.
	 * Remember: if you pass options, the attached method is NOT called automatically
	 *
	 * @param {String}
	 *            method (optional) action
	 */
	$.fn.validationEngine = function(method) {
		var form = $(this);
		if (!form[0])
			return false; // stop here if the form does not exist
		
		//验证前将所有没有id的都添加上id
		form.find("input,textarea").each(function(){
			if(!$(this).attr("id")){
				$(this).attr("id","val_" + $.uuid());
			}
		});
		
		//如果调过一次validationEngine()，第二次调用先detach
		if (!method && form.data('jqv') && form.data('jqv') != null) {
			methods["detach"].apply(form);
		}
		
		if (typeof (method) == 'string' && method.charAt(0) != '_' && methods[method]) {
			// make sure init is called once
			if (method != "showPrompt" && method != "hidePrompt" && method != "hide" && method != "hideAll"){
				methods.init.apply(form);
			}
			return methods[method].apply(form, Array.prototype.slice.call(arguments, 1));
		} else if (typeof method == 'object' || !method) {
			// default constructor with or without arguments
			methods.init.apply(form, arguments);
			return methods.attach.apply(form);
		} else {
			$.error('Method ' + method + ' does not exist in jQuery.validationEngine');
		}
	};
	$.fn.reloadValidationEngine=function(method){
		var form = $(this);
		if(!form[0]){
			return false; 
		}
		if(method && typeof method =='object'){
			form.validationEngine("attach",method);
		}else{
			form.validationEngine("attach",{});	
		}
		
	};
	$.fn.mutliselectValidate = function() {
		var rulesParsing = $(this).attr('class');
		var getRules = /validate\[(.*)\]/.exec(rulesParsing);
		if (!getRules)
			return true;
		var str = getRules[1];
		var rules = str.split(/\[|,|\]/);
		var errorMsg = undefined;
		var form = $(this).closest('form');
		var options = form.data('jqv');
		for ( var i = 0; i < rules.length; i++) {
			switch (rules[i]) {
			case "required":
				errorMsg = methods._required($(this), rules, i, options);
				break;
			}
		}
		var alias_id = $(this).attr('id');
		if (errorMsg !== undefined) {
			methods._showPrompt($('#' + alias_id + '_alias'), errorMsg, '', false, options);
			return false;
		}else methods._closePrompt($('#' + alias_id + '_alias'));
		return true;
	};
	
	/**
	 * 验证域从而显示提示信息Validates field, shows prompts accordingly
	 *
	 * @param {jqObject}  field
	 * @return true if field is valid
	 */
	$.fn.editableSelectValidate = function(hiddenId) {
		var $text = $(this);
		var $hidden = $("#"+hiddenId);
		var $select = $("#"+$(this).attr("id")+"_select");
		var rulesParsing = $select.attr('class');
		var getRules = /validate\[(.*)\]/.exec(rulesParsing);
		if (!getRules)
			return true;
		var str = getRules[1];
		var rules = str.split(/\[|,|\]/);
		var options = $text.closest('form').data('jqv');
		var promptText = "";
		for ( var i = 0; i < rules.length; i++) {
			var errorMsg = undefined;
			switch (rules[i]) {
			case "required":
				if(($text.val()=='' || $text.val()==$select.find("option:first").text()) && $hidden.val()==''){
					errorMsg = options.allrules[rules[i]].alertText;
				}
				break;
			}
			if (errorMsg !== undefined) {
				promptText += errorMsg + "<br/>";
			}
		}
		if (errorMsg !== undefined) {
			methods._showPrompt($text, promptText, "", false, options);
			return false;
		} else {
			methods._closePrompt($text);
			return true;
		}
	};

	// LEAK GLOBAL OPTIONS
	$.validationEngine = {
		defaults : {

			// Name of the event triggering field validation
			validationEventTrigger : "keyup click change",
			// Automatically scroll viewport to the first error
			scroll : true,
			// Opening box position, possible locations are: topLeft,
			// topRight, bottomLeft, centerRight, bottomRight
			promptPosition : "centerRight",
			bindMethod : "bind",
			// internal, automatically set to true when it parse a _ajax rule
			inlineAjax : false,
			// if set to true, the form data is sent asynchronously via ajax to the form.action url (get)
			ajaxFormValidation : false,
			// Ajax form validation callback method: boolean onComplete(form, status, errors, options)
			// retuns false if the form.submit event needs to be canceled.
			ajaxFormValidationURL : false,
			// The url to send the submit ajax validation (default to action)
			onAjaxFormComplete : $.noop,
			// called right before the ajax call, may return false to cancel
			onBeforeAjaxFormValidation : $.noop,
			// Stops form from submitting and execute function assiciated with it
			onValidationComplete : false,

			// Used when the form is displayed within a scrolling DIV
			isOverflown : false,
			overflownDIV : "",

			// true when form and fields are binded
			binded : false,
			// set to true, when the prompt arrow needs to be displayed
			showArrow : true,
			// did one of the validation fail ? kept global to stop further ajax validations
			isError : false,
			// Caches field validation status, typically only bad status are created.
			// the array is used during ajax form validation to detect issues early and prevent an expensive submit
			ajaxValidCache : {}

		}
	}
})(jQuery);
