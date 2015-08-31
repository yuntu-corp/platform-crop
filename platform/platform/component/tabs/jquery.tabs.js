
(function($){
	
	// get the left position of the tab element
	function getTabLeftPosition(container, tab) {
		var w = 0;
		var b = true;
		$('>div.tabs-header ul.tabs li', container).each(function(){
			if (this == tab) {
				b = false;
			}
			if (b == true) {
				w += $(this).outerWidth(true);
			}
		});
		return w;
	}
	
	// get the max tabs scroll width(scope)
	function getMaxScrollWidth(container) {
		var header = $('>div.tabs-header', container);
		var tabsWidth = 0;	// all tabs width
		$('ul.tabs li', header).each(function(){
			tabsWidth += $(this).outerWidth(true);
		});
		var wrapWidth = $('.tabs-wrap', header).width();
		var padding = parseInt($('.tabs', header).css('padding-left'));
		
		return tabsWidth - wrapWidth + padding;
	}
	
	// set the tabs scrollers to show or not,
	// dependent on the tabs count and width
	function setScrollers(container) {
		var header = $('>div.tabs-header', container);
		var tabsWidth = 0;
		$('ul.tabs li', header).each(function(){
			tabsWidth += $(this).outerWidth(true);
		});
		
		if (tabsWidth > header.width()) {
			$('.tabs-scroller-left', header).css('display', 'block');
			$('.tabs-scroller-right', header).css('display', 'block');
			$('.tabs-wrap', header).addClass('tabs-scrolling');
			
			if ($.boxModel == true) {
				$('.tabs-wrap', header).css('left',2);
			} else {
				$('.tabs-wrap', header).css('left',0);
			}
			var width = header.width()
				- $('.tabs-scroller-left', header).outerWidth()
				- $('.tabs-scroller-right', header).outerWidth();
			$('.tabs-wrap', header).width(width + 3);
			
		} else {
			$('.tabs-scroller-left', header).css('display', 'none');
			$('.tabs-scroller-right', header).css('display', 'none');
			$('.tabs-wrap', header).removeClass('tabs-scrolling').scrollLeft(0);
			$('.tabs-wrap', header).width(header.width());
			$('.tabs-wrap', header).css('left',0);
			
		}
	}
	
	// set size of the tabs container
	function setSize(container) {
		//去掉header的width
//		var header = $('>div.tabs-header', container);
//		if ($.boxModel == true) {
//			var delta = header.outerWidth(true) - header.width();
//			header.width($(container).width() - delta);
//		} else {
//			header.width($(container).width());
//		}
		
		setScrollers(container);
		
		var tabHeight = $('>div.tabs-header ul.tabs', container).outerHeight(true);
		var panels = $('>div.tabs-panels', container);
		var height = parseInt($(container).css('height'));
		if (!isNaN(height)) {
			if ($.boxModel == true) {
				var delta = panels.outerHeight(true) - panels.height();
				panels.css('height', height - tabHeight - delta || 'auto');
			} else {
				panels.css('height', height - tabHeight);
			}
		}
		
		//去掉tabs-panels的宽
//		var width = $(container).width();
//		if ($.boxModel == true) {
//			var delta = panels.outerWidth(true) - panels.width();
//			panels.css('width', width - delta);
//		} else {
//			panels.width(width);
//		}
		
		//添加自定义滚动条样式
		//$('>div.tabs-panels>div', container).jScrollPane({showArrows: true});

		// resize the children tabs container
		$('div.tabs-container', container).tabs('resize');	
		
	}
	
	// wrap the tabs header and body
	function wrapTabs(container) {
		$(container).wrapInner('<div class="tabs-panels"/>');
		$('<div class="tabs-header">'
				+ '<div class="tabs-scroller-left"></div>'
				+ '<div class="tabs-scroller-right"></div>'
				+ '<div class="tabs-wrap">'
				+ '<ul class="tabs"></ul>'
				+ '</div>'
				+ '</div>').prependTo(container);
		
		var header = $('>div.tabs-header', container);
		
		$('>div.tabs-panels>div', container).each(function(){
			if (!$(this).attr('id')) {
				$(this).attr('id', 'gen-tabs-panel' + $.fn.tabs.defaults.idSeed++);
			}
			
			var options = {
				id: $(this).attr('id'),
				uid: $(this).attr('uid'),
				title: $(this).attr('title'),
				content: null,
				href: $(this).attr('href'),
				closable: $(this).attr('closable') == 'true',
				icon: $(this).attr('icon'),
				selected: $(this).attr('selected') == 'true',
				cache: $(this).attr('cache') == 'false' ? false : true
			};
			
			createTab(container, options);
		});
		
		$('.tabs-scroller-left, .tabs-scroller-right', header).hover(
			function(){$(this).addClass('tabs-scroller-over');},
			function(){$(this).removeClass('tabs-scroller-over');}
		);
	}
	
	function createTab(container, options) {
		var header = $('>div.tabs-header', container);
		var tabs = $('ul.tabs', header);
		
		var tab = $('<li container="' + ($(container).attr('id') || '') + '" uid="' + ((options && options.uid) ? options.uid : '') + '"></li>');
		//var tab = $('<li></li>');
		//修改title中的特殊字符
		var tit = options.title;
		tit = String(tit).replace(/&/g,"&amp;").replace(/\"/g, "&quot;").replace(/</g, "&lt;").replace(/>/g, "&gt;");
		var tab_span = $('<span></span>').html(tit);
		var tab_a = $('<a class="tabs-inner"></a>').attr('href', 'javascript:void(0)').append(tab_span);
		tab.append(tab_a).appendTo(tabs);
		
		if (options.closable) {
			tab_span.addClass('tabs-closable');
			tab_a.after('<a href="javascript:void(0)" class="tabs-close"></a>');
		}
		if (options.icon) {
			tab_span.addClass('tabs-with-icon');
			tab_span.after($('<span/>').addClass('tabs-icon').addClass(options.icon));
		}
		if (options.selected) {
			tab.addClass('tabs-selected');
		}
		if (options.content) {
			$('#' + options.id).html(options.content);
		}
		$.data(tab[0], 'tabs.tab', {
			id: options.id,
			uid: options.uid,
			title: options.title,
			href: options.href,
			loaded: false,
			cache: options.cache
		});
	}
	
	function addTab(container, options) {
		options = $.extend({
			id: null,
			uid: null,
			title: '',
			content: '',
			href: null,
			cache: true,
			icon: null,
			closable: false,
			selected: true
		}, options || {});
		
		if (options.selected) {
			$('.tabs-header .tabs-wrap .tabs li', container).removeClass('tabs-selected');
		}
		options.id = 'gen-tabs-panel' + $.fn.tabs.defaults.idSeed++;
		$('<div></div>').attr('id', options.id).attr('uid', options.uid).attr('title', options.title).appendTo($('>div.tabs-panels', container));
		createTab(container, options);
	}
	
	function updateTab(container, options) {
		if(!options.uid){
			return;
		}
		var elem = $('>div.tabs-header li[uid=' + options.uid + ']', container)[0];
		if (!elem) return;
		
		if(options.title){
			$("span:eq(0)",elem).text(options.title);//tab页中的tab标题
			var panel = $('>div.tabs-panels div[uid=' + options.uid + ']', container);
			$(panel).attr("title",options.title);//tab中的panel
		}
		if(options.icon){
			$(".tabs-icon",elem).attr("class","").addClass("tabs-icon " + options.icon);
		}
	}
	
	// close a tab with special uid
	function closeTab(container, uid, needCallback) {
		var opts = $.data(container, 'tabs').options;
		//var elem = $('>div.tabs-header li:has(a span:contains("' + title + '"))', container)[0];
		var elem = $('>div.tabs-header li[uid=' + uid + ']', container)[0];
		if (!elem) return;
		
		var tabAttr = $.data(elem, 'tabs.tab');
		var panel = $('#' + tabAttr.id);
		
		if(needCallback){
			if (opts.onClose.call(panel, uid) == false) return;
		}
		
		var selected = $(elem).hasClass('tabs-selected');
		$.removeData(elem, 'tabs.tab');
		$(elem).remove();
		panel.remove();
		
		setSize(container);
		if (selected) {
			selectTab(container);
		} else {
			var wrap = $('>div.tabs-header .tabs-wrap', container);
			var pos = Math.min(
					wrap.scrollLeft(),
					getMaxScrollWidth(container)
			);
			wrap.animate({scrollLeft:pos}, opts.scrollDuration);
		}
	}
	
	// active the selected tab item, if no selected item then active the first item
	function selectTab(container, title){
		if (title) {
			var elem = $('>div.tabs-header li:has(a span:contains("' + title + '"))', container)[0];
			if (elem) {
				$(elem).trigger('click');
			}
		} else {
			var tabs = $('>div.tabs-header ul.tabs', container);
			if ($('.tabs-selected', tabs).length == 0) {
				$('li:first', tabs).trigger('click');
			} else {
				$('.tabs-selected', tabs).trigger('click');
			}
		}
	}
	
	$.fn.tabs = function(options, param){
		if (typeof options == 'string') {
			switch(options) {
				case 'resize':
					return this.each(function(){
						setSize(this);
					});
				case 'add':
					return this.each(function(){
						addTab(this, param);
						$(this).tabs();
					});
				case 'close':
					return this.each(function(){
						closeTab(this, param, false);
					});
				case 'select':
					return this.each(function(){
						selectTab(this, param);
					});
				case 'update':
					return this.each(function(){
						updateTab(this, param);
					});
			}
		}
		
		options = options || {};
		var rs = this.each(function(){
			var state = $.data(this, 'tabs');
			var opts;
			if (state) {
				opts = $.extend(state.options, options);
				state.options = opts;
			} else {
				wrapTabs(this);
				opts = $.extend({}, $.fn.tabs.defaults, options);
				$.data(this, 'tabs', {
					options: opts
				});
			}
			
			var container = this;
			var header = $('>div.tabs-header', container);
			var tabs = $('ul.tabs', header);
			
			if (opts.plain == true) {
				header.addClass('tabs-header-plain');
			} else {
				header.removeClass('tabs-header-plain');
			}
			
			if (state) {
				$('li', tabs).unbind('.tabs');
				$('a.tabs-close', tabs).unbind('.tabs');
				$('.tabs-scroller-left', header).unbind('.tabs');
				$('.tabs-scroller-right', header).unbind('.tabs');
			}
			
			$('li', tabs).bind('click.tabs', onClickTab);
			$('a.tabs-close', tabs).bind('click.tabs', onCloseTab);
			$('.tabs-scroller-left', header).bind('click.tabs', onClickScrollLeft);
			$('.tabs-scroller-right', header).bind('click.tabs', onClickScrollRight);

			setSize(container);
			selectTab(container);
			
			function onCloseTab(event) {
				var elem = $(this).parent()[0];
				var tabAttr = $.data(elem, 'tabs.tab');
				closeTab(container, tabAttr.uid, true);
				//阻止冒泡，触发li的点击事件
				event.stopPropagation();
			}
			
			function onClickTab() {
				$('.tabs-selected', tabs).removeClass('tabs-selected');
				$(this).addClass('tabs-selected');
				
				$('>div.tabs-panels>div', container).css('display', 'none');
				
				var wrap = $('.tabs-wrap', header);
				var leftPos = getTabLeftPosition(container, this);
				var left = leftPos - wrap.scrollLeft();
				var right = left + $(this).outerWidth();
				if (left < 0 || right > wrap.innerWidth()) {
					var pos = Math.min(
							leftPos - (wrap.width()-$(this).width()) / 2,
							getMaxScrollWidth(container)
					);
					wrap.animate({scrollLeft:pos}, opts.scrollDuration);
				}
				
				var tabAttr = $.data(this, 'tabs.tab');
				var panel = $('#' + tabAttr.id);
				panel.css('display', 'block').focus();
				$('div.tabs-container', panel).tabs('resize');
				
				//添加自定义滚动条样式
				//panel.jScrollPane({showArrows: true});
				
				if (tabAttr.href && (!tabAttr.loaded || !tabAttr.cache)) {
					panel.load(tabAttr.href, null, function(){
						opts.onLoad.apply(this, arguments);
						tabAttr.loaded = true;
					});
				}
				
				opts.onSelect.call(panel, tabAttr.title);
			}
			
			function onClickScrollLeft() {
				var wrap = $('.tabs-wrap', header);
				var pos = wrap.scrollLeft() - opts.scrollIncrement;
				wrap.animate({scrollLeft:pos}, opts.scrollDuration);
			}
			
			function onClickScrollRight() {
				var wrap = $('.tabs-wrap', header);
				var pos = Math.min(
						wrap.scrollLeft() + opts.scrollIncrement,
						getMaxScrollWidth(container)
				);
				wrap.animate({scrollLeft:pos}, opts.scrollDuration);
			}
		});
		return rs;
	};
	
	$.fn.tabs.defaults = {
		idSeed: 0,
		plain: false,
		scrollIncrement: 100,
		scrollDuration: 400,
		onLoad: function(){},
		onSelect: function(){},
		onClose: function(){}
	};
	
})(jQuery);