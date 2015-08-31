(function($){
    $.fn.validationEngineLanguage = function(){
    };
    $.validationEngineLanguage = {
        newLang: function(){
            $.validationEngineLanguage.allRules = {
            	    required: {
            	        regex: 'none',
            	        alertText: $.i18n.prop("validationEngine_required_alertText"),
            	        alertTextCheckboxMultiple: $.i18n.prop("validationEngine_required_alertTextCheckboxMultiple"),
            	        alertTextCheckboxe: $.i18n.prop("validationEngine_required_alertTextCheckboxe"),
            	        alertTextDateRange: $.i18n.prop("validationEngine_required_alertTextDateRange")
            	    },
            	    invalidCharacter: {
            	        regex: 'none',
            	        alertText: $.i18n.prop("validationEngine_invalidCharacter_alertText")
            	    },
            	    dateRange: {
            	        regex: 'none',
            	        alertText: $.i18n.prop("validationEngine_dateRange_alertText"),
            	        alertText2: $.i18n.prop("validationEngine_dateRange_alertText2")
            	    },
            	    dateTimeRange: {
            	        regex: 'none',
            	        alertText: $.i18n.prop("validationEngine_dateTimeRange_alertText"),
            	        alertText2: $.i18n.prop("validationEngine_dateTimeRange_alertText2")
            	    },
            	    minSize: {
            	        regex: 'none',
            	        alertText: $.i18n.prop("validationEngine_minSize_alertText"),
            	        alertText2: $.i18n.prop("validationEngine_minSize_alertText2")
            	    },
            	    maxSize: {
            	        regex: 'none',
            	        alertText: $.i18n.prop("validationEngine_maxSize_alertText"),
            	        alertText2: $.i18n.prop("validationEngine_maxSize_alertText2")
            	    },
            	    groupRequired: {
            	        regex: 'none',
            	        alertText: $.i18n.prop("validationEngine_groupRequired_alertText")
            	    },
            	    min: {
            	        regex: 'none',
            	        alertText: $.i18n.prop("validationEngine_min_alertText")
            	    },
            	    max: {
            	        regex: 'none',
            	        alertText: $.i18n.prop("validationEngine_max_alertText")
            	    },
            	    past: {
            	        regex: 'none',
            	        alertText: $.i18n.prop("validationEngine_past_alertText")
            	    },
            	    future: {
            	        regex: 'none',
            	        alertText: $.i18n.prop("validationEngine_future_alertText")
            	    },
            	    maxCheckbox: {
            	        regex: 'none',
            	        alertText: $.i18n.prop("validationEngine_maxCheckbox_alertText"),
            	        alertText2: $.i18n.prop("validationEngine_maxCheckbox_alertText2")
            	    },
            	    minCheckbox: {
            	        regex: 'none',
            	        alertText: $.i18n.prop("validationEngine_minCheckbox_alertText"),
            	        alertText2: $.i18n.prop("validationEngine_minCheckbox_alertText2")
            	    },
            	    equals: {
            	        regex: 'none',
            	        alertText: $.i18n.prop("validationEngine_equals_alertText")
            	    },
            	    phone: {
            	        regex: /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/,
            	        alertText: $.i18n.prop("validationEngine_phone_alertText")
            	    },
            	    mobile: {
            	        regex: /^[1][358]\d{9}$/,
            	        alertText: $.i18n.prop("validationEngine_mobile_alertText")
            	    },
            	    phoneOrMobile: {
            	        regex: /^[1][358]\d{9}|(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/,
            	        alertText: $.i18n.prop("validationEngine_phoneOrMobile_alertText")
            	    },
            	    email: {
            	        regex: /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,
            	        alertText: $.i18n.prop("validationEngine_email_alertText")
            	    },
            	    positiveInteger: {
            	        regex: /^[1-9]\d*$/,
            	        alertText: $.i18n.prop("validationEngine_positiveInteger_alertText")
            	    },
            	    simpleByteCharacter: {
            	        regex: /^[\x00-\xff]+$/,
            	        alertText: $.i18n.prop("validationEngine_simpleByteCharacter_alertText")
            	    },
            	    doubleByteCharacter: {
            	        regex: /^[^\x00-\xff]+$/,
            	        alertText: $.i18n.prop("validationEngine_doubleByteCharacter_alertText")
            	    },
            	    integer: {
            	        regex: /^[\-\+]?\d+$/,
            	        alertText: $.i18n.prop("validationEngine_integer_alertText")
            	    },
            	    number: {
            	        regex: /^[\-\+]?(([0-9]+)([\.,]([0-9]+))?|([\.,]([0-9]+))?)$/,
            	        alertText: $.i18n.prop("validationEngine_number_alertText")
            	    },
            	    date: {
            	        regex: /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/,
            	        alertText: $.i18n.prop("validationEngine_date_alertText")
            	    },
            	    ipv4: {
            	        regex: /^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/,
            	        alertText: $.i18n.prop("validationEngine_ipv4_alertText")
            	    },
            	    url: {
            	        regex: /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i,
            	        alertText: $.i18n.prop("validationEngine_url_alertText")
            	    },
            	    onlyNumberSp: {
            	        regex: /^[0-9\ ]+$/,
            	        alertText: $.i18n.prop("validationEngine_onlyNumberSp_alertText")
            	    },
            	    onlyLetterSp: {
            	        regex: /^[a-zA-Z\ "]+$/,
            	        alertText: $.i18n.prop("validationEngine_onlyLetterSp_alertText")
            	    },
            	    onlyLetterNumber: {
            	        regex: /^[0-9a-zA-Z]+$/,
            	        alertText: $.i18n.prop("validationEngine_onlyLetterNumber_alertText")
            	    },
            	    onlyOrdinaryAndChineseCharacters: {
            	        regex: /^[A-Za-z0-9\u4e00-\u9fa5]+$/,
            	        alertText: $.i18n.prop("validationEngine_onlyOrdinaryAndChineseCharacters_alertText")
            	    },
            	    onlyAlphanumericUnderscores: {
            	        regex: /^\w+$/,
            	        alertText: $.i18n.prop("validationEngine_onlyAlphanumericUnderscores_alertText")
            	    },
            	    ajaxUserCall: {
            	        url: 'ajaxValidateFieldUser',
            	        extraData: 'name=eric',
            	        alertText: $.i18n.prop("validationEngine_ajaxUserCall_alertText"),
            	        alertTextLoad: $.i18n.prop("validationEngine_ajaxUserCall_alertTextLoad")
            	    },
            	    ajaxUserCallPhp: {
            	        url: 'phpajax/ajaxValidateFieldUser.php',
            	        extraData: 'name=eric',
            	        alertTextOk: $.i18n.prop("validationEngine_ajaxUserCallPhp_alertTextOk"),
            	        alertText: $.i18n.prop("validationEngine_ajaxUserCallPhp_alertText"),
            	        alertTextLoad: $.i18n.prop("validationEngine_ajaxUserCallPhp_alertTextLoad")
            	    },
            	    ajaxNameCall: {
            	        url: 'ajaxValidateFieldName',
            	        alertText: $.i18n.prop("validationEngine_ajaxNameCall_alertText"),
            	        alertTextOk: $.i18n.prop("validationEngine_ajaxNameCall_alertTextOk"),
            	        alertTextLoad: $.i18n.prop("validationEngine_ajaxNameCall_alertTextLoad")
            	    },
            	    ajaxNameCallPhp: {
            	        url: 'phpajax/ajaxValidateFieldName.php',
            	        alertText: $.i18n.prop("validationEngine_ajaxNameCallPhp_alertText"),
            	        alertTextLoad: $.i18n.prop("validationEngine_ajaxNameCallPhp_alertTextLoad")
            	    },
            	    ajaxItemRepeatCall: {
            	        alertTextOk: $.i18n.prop("validationEngine_ajaxItemRepeatCall_alertTextOk"),
            	        alertTextFail: $.i18n.prop("validationEngine_ajaxItemRepeatCall_alertTextFail"),
            	        alertTextLoad: $.i18n.prop("validationEngine_ajaxItemRepeatCall_alertTextLoad")
            	    },
            	    validate2fields: {
            	        alertText: $.i18n.prop("validationEngine_validate2fields_alertText")
            	    },
            	    dateFormat: {
            	        regex: /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$|^(?:(?:(?:0?[13578]|1[02])(\/|-)31)|(?:(?:0?[1,3-9]|1[0-2])(\/|-)(?:29|30)))(\/|-)(?:[1-9]\d\d\d|\d[1-9]\d\d|\d\d[1-9]\d|\d\d\d[1-9])$|^(?:(?:0?[1-9]|1[0-2])(\/|-)(?:0?[1-9]|1\d|2[0-8]))(\/|-)(?:[1-9]\d\d\d|\d[1-9]\d\d|\d\d[1-9]\d|\d\d\d[1-9])$|^(0?2(\/|-)29)(\/|-)(?:(?:0[48]00|[13579][26]00|[2468][048]00)|(?:\d\d)?(?:0[48]|[2468][048]|[13579][26]))$/,
            	        alertText: $.i18n.prop("validationEngine_dateFormat_alertText")
            	    },
            	    dateTimeFormat: {
            	        regex: /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])\s+(1[012]|0?[1-9]){1}:(0?[1-5]|[0-6][0-9]){1}:(0?[0-6]|[0-6][0-9]){1}\s+(am|pm|AM|PM){1}$|^(?:(?:(?:0?[13578]|1[02])(\/|-)31)|(?:(?:0?[1,3-9]|1[0-2])(\/|-)(?:29|30)))(\/|-)(?:[1-9]\d\d\d|\d[1-9]\d\d|\d\d[1-9]\d|\d\d\d[1-9])$|^((1[012]|0?[1-9]){1}\/(0?[1-9]|[12][0-9]|3[01]){1}\/\d{2,4}\s+(1[012]|0?[1-9]){1}:(0?[1-5]|[0-6][0-9]){1}:(0?[0-6]|[0-6][0-9]){1}\s+(am|pm|AM|PM){1})$/,
            	        alertText: $.i18n.prop("validationEngine_dateTimeFormat_alertText"),
            	        alertText2: $.i18n.prop("validationEngine_dateTimeFormat_alertText2"),
            	        alertText3: $.i18n.prop("validationEngine_dateTimeFormat_alertText3"),
            	        alertText4: $.i18n.prop("validationEngine_dateTimeFormat_alertText4")
            	    },
            	    chinese: {
            	        regex: /^[\u0391-\uFFE5]+$/,
            	        alertText: $.i18n.prop("validationEngine_chinese_alertText")
            	    },
            	    notAllowChinese: {
            	        regex: /^[^\u4e00-\u9fa5]*$/,
            	        alertText: $.i18n.prop("validationEngine_notAllowChinese_alertText")
            	    },
            	    identity: {
            	        regex: /^(\d{14}|\d{17})(\d|[xX])$/,
            	        alertText: $.i18n.prop("validationEngine_identity_alertText")
            	    },
            	    float: {
            	        regex: /^[0-9]+(\.[0-9]+)?$/,
            	        alertText: $.i18n.prop("validationEngine_float_alertText")
            	    },
            	    float1: {
            	        regex: /^[0-9]+(\.[0-9]{1})?$/,
            	        alertText: $.i18n.prop("validationEngine_float1_alertText")
            	    },
            	    float2: {
            	        regex: /^[0-9]+(\.[0-9]{1,2})?$/,
            	        alertText: $.i18n.prop("validationEngine_float2_alertText")
            	    },
            	    signedFloat: {
            	        regex: /^[\-\+]?([0-9]+(\.[0-9]+)?)$/,
            	        alertText: $.i18n.prop("validationEngine_signedFloat_alertText")
            	    },
            	    signedFloat1: {
            	        regex: /^[\-\+]?([0-9]+(\.[0-9]{1})?)$/,
            	        alertText: $.i18n.prop("validationEngine_signedFloat1_alertText")
            	    },
            	    signedFloat2: {
            	        regex: /^[\-\+]?([0-9]+(\.[0-9]{1,2})?)$/,
            	        alertText: $.i18n.prop("validationEngine_signedFloat2_alertText")
            	    }
            	};
            
        }
    };
    $.validationEngineLanguage.newLang();
})(jQuery);
