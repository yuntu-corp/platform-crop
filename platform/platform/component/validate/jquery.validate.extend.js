/**
 * jquery.validate 验证框架封装
 */
(function($) {
	$.validator.setDefaults({
		onkeyup : false,
		errorClass: "validator_error",
		validClass: "validator_valid",
		errorElement: "label"
	});
	
	$.hz.validate = {
		init:function(formId,options){
			var defaultOptions = {
				displayType:"none",
				onkeyup : false
			};
			var theOptions = $.extend(defaultOptions, options);
			if(displayOptions[theOptions.displayType]){
				theOptions = $.extend(theOptions, displayOptions[theOptions.displayType]);
			}
			return $("#"+formId).validate(theOptions);
		},
		valid:function(formId){
			return $("#"+formId).valid();
		},
		addRules:function(formId, rules){
			return $("#"+formId).rules("add", rules);
		},
		removeRules:function(formId, rules){
			return $("#"+formId).rules("remove", rules);
		},
		showPrompt:function(element, message){
			displayOptions.float.showPrompt(element,message);
		},
		closePrompt:function(element){
			displayOptions.float.closePrompt(element);
		}
	};
	
	var displayOptions = {
		float:{
			errorPlacement: function(error, element) {
				var elem = $(element);
	            // Check we have a valid error message
				var options = {showArrow:true,promptPosition:"centerRight",isOverflown:false};
	            if (!error.is(':empty')) {
	            	methods._showPrompt(elem, $(error).html(), "", false, options);
	            } else {
	            	methods._closePrompt(elem);
	            }
	        },
	        showPrompt: function(element, message, type, ajaxed) {
				var elem = $(element);
	            // Check we have a valid error message
				var options = {showArrow:true,promptPosition:"centerRight",isOverflown:false};
	        	methods._showPrompt(elem, message, type, ajaxed, options);
	        },
	        closePrompt: function(element){
	        	methods._closePrompt(element);
	        },
	        success: function(label) {
//	        	label.text("ok!").addClass("success");
	        }
		},
		mark:{
			
		},
		none:{
			
		}
	};
    		
    var methods = {
		_showPrompt : function(field, promptText, type, ajaxed, options ,ajaxform) {
			var prompt = methods._getPrompt(field);
			// The ajax submit errors are not see has an error in the form,
			// When the form errors are returned, the engine see 2 bubbles, but those are ebing closed by the engine at the same time
			// Because no error was found befor submitting
			if (ajaxform)
				prompt = false;
			if (prompt){
				methods._updatePrompt(field, prompt, promptText, type, ajaxed, options);
			}
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
	//    			alert(field.attr('id'))
			var fieldType = field.attr("type"), fieldName = field.attr("name");
			var inputFields = $(methods._getInputWithName(fieldName));
			if(field.closest(".prompt-mark").length==1){
				promptObj=field.closest(".prompt-mark");
			}else if ((fieldType == "radio" || fieldType == "checkbox") && fieldName && inputFields.size() > 1) {
				promptObj = inputFields.last();
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
			
			prompt.on("click", function() {
				$(this).fadeOut(150, function() {
						$(this).remove();
				});
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
	//    				alert(field.attr('name'))
				var fieldType = field.attr("type"), fieldName = field.attr("name");
				var inputFields = $(methods._getInputWithName(fieldName));
				if(field.closest(".prompt-mark").length==1){
					promptObj=field.closest(".prompt-mark");
				}else if ((fieldType == "radio" || fieldType == "checkbox") && fieldName && inputFields.size() > 1) {
					promptObj = inputFields.last();
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
	//    			var fieldWidth = field.width();
	//    			var promptHeight = promptElmt.height();
			//解决提示位置不统一问题
			var fieldWidth = field.outerWidth();
			var promptHeight = promptElmt.outerHeight();
			
			
			var overflow = options.isOverflown;
			var _offset = 0;
			var fieldType = field.attr("type"), fieldName = field.attr("name");
			if ((fieldType == "radio" || fieldType == "checkbox")
					&& $(methods._getInputWithName(fieldName)).size() > 1) {
//				_offset = 50;
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
		/**
		 * Removes forbidden characters from class name
		 * @param {String} className
		 */
		_getClassName : function(className) {
			return className.replace(":", "_").replace(".", "_");
		}
    }
})(jQuery);