/**
 <b>Wysiwyg</b>. A wrapper for Bootstrap wyswiwyg plugin.
 It's just a wrapper so you still need to include Bootstrap wysiwyg script first.
*/
(function($ , undefined) {
	$.fn.ace_wysiwyg = function($options , undefined) {
		var options = $.extend( {
			speech_button:true,
			wysiwyg:{}
        }, $options);

		var color_values = [
			'#ac725e','#d06b64','#f83a22','#fa573c','#ff7537','#ffad46',
			'#42d692','#16a765','#7bd148','#b3dc6c','#fbe983','#fad165',
			'#92e1c0','#9fe1e7','#9fc6e7','#4986e7','#9a9cff','#b99aff',
			'#c2c2c2','#cabdbf','#cca6ac','#f691b2','#cd74e6','#a47ae2',
			'#444444'
		]

		var button_defaults =
		{
			'font' : {
				values:['Arial', 'Courier', 'Comic Sans MS', 'Helvetica', 'Open Sans', 'Tahoma', 'Verdana'],
				icon:'fa fa-font',
				title:'Font'
			},
			'fontSize' : {
				values:{5:'Huge', 3:'Normal', 1:'Small'},
				icon:'fa fa-text-height',
				title:'Font Size'
			},
			'bold' : {
				icon : 'fa fa-bold',
				title : 'Bold (Ctrl/Cmd+B)'
			},
			'italic' : {
				icon : 'fa fa-italic',
				title : 'Italic (Ctrl/Cmd+I)'
			},
			'strikethrough' : {
				icon : 'fa fa-strikethrough',
				title : 'Strikethrough'
			},
			'underline' : {
				icon : 'fa fa-underline',
				title : 'Underline'
			},
			'insertunorderedlist' : {
				icon : 'fa fa-list-ul',
				title : 'Bullet list'
			},
			'insertorderedlist' : {
				icon : 'fa fa-list-ol',
				title : 'Number list'
			},
			'outdent' : {
				icon : 'fa fa-outdent',
				title : 'Reduce indent (Shift+Tab)'
			},
			'indent' : {
				icon : 'fa fa-indent',
				title : 'Indent (Tab)'
			},
			'justifyleft' : {
				icon : 'fa fa-align-left',
				title : 'Align Left (Ctrl/Cmd+L)'
			},
			'justifycenter' : {
				icon : 'fa fa-align-center',
				title : 'Center (Ctrl/Cmd+E)'
			},
			'justifyright' : {
				icon : 'fa fa-align-right',
				title : 'Align Right (Ctrl/Cmd+R)'
			},
			'justifyfull' : {
				icon : 'fa fa-align-justify',
				title : 'Justify (Ctrl/Cmd+J)'
			},
			'createLink' : {
				icon : 'fa fa-link',
				title : 'Hyperlink',
				button_text : 'Add',
				placeholder : 'URL',
				button_class : 'btn-primary'
			},
			'unlink' : {
				icon : 'fa fa-chain-broken',
				title : 'Remove Hyperlink'
			},
			'insertImage' : {
				icon : 'fa fa-picture-o',
				title : 'Insert picture',
				button_text : '<i class="'+ ace.vars['icon'] + 'fa fa-file"></i> Choose Image &hellip;',
				placeholder : 'Image URL',
				button_insert : 'Insert',
				button_class : 'btn-success',
				button_insert_class : 'btn-primary',
				choose_file: true //show the choose file button?
			},
			'foreColor' : {
				values : color_values,
				title : 'Change Color'
			},
			'backColor' : {
				values : color_values,
				title : 'Change Background Color'
			},
			'undo' : {
				icon : 'fa fa-undo',
				title : 'Undo (Ctrl/Cmd+Z)'
			},
			'redo' : {
				icon : 'fa fa-repeat',
				title : 'Redo (Ctrl/Cmd+Y)'
			},
			'viewSource' : {
				icon : 'fa fa-code',
				title : 'View Source'
			}
		}
		
		var toolbar_buttons =
		options.toolbar ||
		[
			'font',
			null,
			'fontSize',
			null,
			'bold',
			'italic',
			'strikethrough',
			'underline',
			null,
			'insertunorderedlist',
			'insertorderedlist',
			'outdent',
			'indent',
			null,
			'justifyleft',
			'justifycenter',
			'justifyright',
			'justifyfull',
			null,
			'createLink',
			'unlink',
			null,
			'insertImage',
			null,
			'foreColor',
			null,
			'undo',
			'redo',
			null,
			'viewSource'
		]


		this.each(function() {
			var toolbar = ' <div class="wysiwyg-toolbar btn-toolbar center"> <div class="btn-group"> ';

			for(var tb in toolbar_buttons) if(toolbar_buttons.hasOwnProperty(tb)) {
				var button = toolbar_buttons[tb];
				if(button === null){
					toolbar += ' </div> <div class="btn-group"> ';
					continue;
				}
				
				if(typeof button == "string" && button in button_defaults) {
					button = button_defaults[button];
					button.name = toolbar_buttons[tb];
				} else if(typeof button == "object" && button.name in button_defaults) {
					button = $.extend(button_defaults[button.name] , button);
				}
				else continue;
				
				var className = "className" in button ? button.className : 'btn-default';
				switch(button.name) {
					case 'font':
						toolbar += ' <a class="btn btn-sm '+className+' dropdown-toggle" data-toggle="dropdown" title="'+button.title+'"><i class="'+ ace.vars['icon'] + button.icon+'"></i><i class="' + ace.vars['icon'] + 'fa fa-angle-down icon-on-right"></i></a> ';
						toolbar += ' <ul class="dropdown-menu dropdown-light dropdown-caret">';
						for(var font in button.values)
							if(button.values.hasOwnProperty(font))
								toolbar += ' <li><a data-edit="fontName ' + button.values[font] +'" style="font-family:\''+ button.values[font]  +'\'">'+button.values[font]  + '</a></li> '
						toolbar += ' </ul>';
					break;

					case 'fontSize':
						toolbar += ' <a class="btn btn-sm '+className+' dropdown-toggle" data-toggle="dropdown" title="'+button.title+'"><i class="'+ ace.vars['icon'] + button.icon+'"></i>&nbsp;<i class="'+ ace.vars['icon'] + 'fa fa-angle-down icon-on-right"></i></a> ';
						toolbar += ' <ul class="dropdown-menu dropdown-light dropdown-caret"> ';
						for(var size in button.values)
							if(button.values.hasOwnProperty(size))
								toolbar += ' <li><a data-edit="fontSize '+size+'"><font size="'+size+'">'+ button.values[size] +'</font></a></li> '
						toolbar += ' </ul> ';
					break;

					case 'createLink':
						toolbar += ' <div class="btn-group"> <a class="btn btn-sm '+className+' dropdown-toggle" data-toggle="dropdown" title="'+button.title+'"><i class="'+ ace.vars['icon'] + button.icon+'"></i></a> ';
						toolbar += ' <div class="dropdown-menu dropdown-caret dropdown-menu-right">\
							 <div class="input-group">\
								<input class="form-control" placeholder="'+button.placeholder+'" type="text" data-edit="'+button.name+'" />\
								<span class="input-group-btn">\
									<button class="btn btn-sm '+button.button_class+'" type="button">'+button.button_text+'</button>\
								</span>\
							 </div>\
						</div> </div>';
					break;

					case 'insertImage':
						toolbar += ' <div class="btn-group"> <a class="btn btn-sm '+className+' dropdown-toggle" data-toggle="dropdown" title="'+button.title+'"><i class="'+ ace.vars['icon'] + button.icon+'"></i></a> ';
						toolbar += ' <div class="dropdown-menu dropdown-caret dropdown-menu-right">\
							 <div class="input-group">\
								<input class="form-control" placeholder="'+button.placeholder+'" type="text" data-edit="'+button.name+'" />\
								<span class="input-group-btn">\
									<button class="btn btn-sm '+button.button_insert_class+'" type="button">'+button.button_insert+'</button>\
								</span>\
							 </div>';
							if( button.choose_file && 'FileReader' in window ) toolbar +=
							 '<div class="space-2"></div>\
							 <label class="center block no-margin-bottom">\
								<button class="btn btn-sm '+button.button_class+' wysiwyg-choose-file" type="button">'+button.button_text+'</button>\
								<input type="file" data-edit="'+button.name+'" />\
							  </label>'
						toolbar += ' </div> </div>';
					break;

					case 'foreColor':
					case 'backColor':
						toolbar += ' <select class="hide wysiwyg_colorpicker" title="'+button.title+'"> ';
						for(var color in button.values)
							toolbar += ' <option value="'+button.values[color]+'">'+button.values[color]+'</option> ';
						toolbar += ' </select> ';
						toolbar += ' <input style="display:none;" disabled class="hide" type="text" data-edit="'+button.name+'" /> ';
					break;

					case 'viewSource':
						toolbar += ' <a class="btn btn-sm '+className+'" data-view="source" title="'+button.title+'"><i class="'+ ace.vars['icon'] + button.icon+'"></i></a> ';
					break;
					default:
						toolbar += ' <a class="btn btn-sm '+className+'" data-edit="'+button.name+'" title="'+button.title+'"><i class="'+ ace.vars['icon'] + button.icon+'"></i></a> ';
					break;
				}
			}
			toolbar += ' </div> ';
			////////////
			var speech_input;
			if (options.speech_button && 'onwebkitspeechchange' in (speech_input = document.createElement('input'))) {
				toolbar += ' <input class="wysiwyg-speech-input" type="text" data-edit="inserttext" x-webkit-speech />';
			}
			speech_input = null;
			////////////
			toolbar += ' </div> ';


			//if we have a function to decide where to put the toolbar, then call that
			if(options.toolbar_place) toolbar = options.toolbar_place.call(this, toolbar);
			//otherwise put it just before our DIV
			else toolbar = $(this).before(toolbar).prev();

			toolbar.find('a[title]').tooltip({animation:false, container:'body'});
			toolbar.find('.dropdown-menu input[type=text]').on('click', function() {return false})
		    .on('change', function() {$(this).closest('.dropdown-menu').siblings('.dropdown-toggle').dropdown('toggle')})
			.on('keydown', function (e) {
				if(e.which == 27) {
					this.value = '';
					$(this).change();
				}
				else if(e.which == 13) {
					e.preventDefault();
					e.stopPropagation();
					$(this).change();
				}
			});
			
			toolbar.find('input[type=file]').prev().on(ace.click_event, function (e) { 
				$(this).next().click();
			});
			toolbar.find('.wysiwyg_colorpicker').each(function() {
				$(this).ace_colorpicker({pull_right:true}).change(function(){
					$(this).nextAll('input').eq(0).val(this.value).change();
				}).next().find('.btn-colorpicker').tooltip({title: this.title, animation:false, container:'body'})
			});
			
			
			var self = $(this);
			//view source
			var view_source = false;
			toolbar.find('a[data-view=source]').on('click', function(e){
				e.preventDefault();
				
				if(!view_source) {
					$('<textarea />')
					.css({'width':self.outerWidth(), 'height':self.outerHeight()})
					.val(self.html())
					.insertAfter(self)
					self.hide();
					
					$(this).addClass('active');
				}
				else {
					var textarea = self.next();
					self.html(textarea.val()).show();
					textarea.remove();
					
					$(this).removeClass('active');
				}
				
				view_source = !view_source;
			});


			var $options = $.extend({}, { activeToolbarClass: 'active' , toolbarSelector : toolbar }, options.wysiwyg || {})
			$(this).wysiwyg( $options );
		});

		return this;
	}


})(window.jQuery);



/**
 <b>Treeview</b>. A wrapper for FuelUX treeview element.
 It's just a wrapper so you still need to include FuelUX treeview script first.
*/
(function($ , undefined) {
	var $options = {
		'open-icon' : ace.vars['icon'] + 'fa fa-folder-open',
		'close-icon' : ace.vars['icon'] + 'fa fa-folder',
		'selectable' : true,
		'selected-icon' : ace.vars['icon'] + 'fa fa-check',
		'unselected-icon' : ace.vars['icon'] + 'fa fa-times'
	}

	$.fn.ace_tree = function(options) {
		$options = $.extend({}, $options, options)
		this.each(function() {
			var $this = $(this);
			$this.html('<div class="tree-folder" style="display:none;">\
				<div class="tree-folder-header">\
					<i class="'+ ace.vars['icon'] + $options['close-icon']+'"></i>\
					<div class="tree-folder-name"></div>\
				</div>\
				<div class="tree-folder-content"></div>\
				<div class="tree-loader" style="display:none"></div>\
			</div>\
			<div class="tree-item" style="display:none;">\
				'+($options['unselected-icon'] == null ? '' : '<i class="'+ ace.vars['icon'] + $options['unselected-icon']+'"></i>')+'\
				<div class="tree-item-name"></div>\
			</div>');
			$this.addClass($options['selectable'] == true ? 'tree-selectable' : 'tree-unselectable');
			
			$this.tree($options);
		});

		return this;
	}

})(window.jQuery);


/**
 <b>Wizard</b>. A wrapper for FuelUX wizard element.
 It's just a wrapper so you still need to include FuelUX wizard script first.
*/
(function($ , undefined) {
	$.fn.ace_wizard = function(options) {

		this.each(function() {
			var $this = $(this);
			$this.wizard();

			var buttons = $this.siblings('.wizard-actions').eq(0);
			var $wizard = $this.data('wizard');
			$wizard.$prevBtn.remove();
			$wizard.$nextBtn.remove();
			
			$wizard.$prevBtn = buttons.find('.btn-prev').eq(0).on(ace.click_event,  function(){
				$wizard.previous();
			}).attr('disabled', 'disabled');
			$wizard.$nextBtn = buttons.find('.btn-next').eq(0).on(ace.click_event,  function(){
				$wizard.next();
			}).removeAttr('disabled');
			$wizard.nextText = $wizard.$nextBtn.text();
			
			var step = options && ((options.selectedItem && options.selectedItem.step) || options.step);
			if(step) {
				$wizard.currentStep = step;
				$wizard.setState();
			}
		});

		return this;
	}

})(window.jQuery);


;/**
 Required. Ace's Basic File to Initiliaze Different Parts & Some Variables.
*/
if( !('ace' in window) ) window['ace'] = {}
if( !('helper' in window['ace']) ) window['ace'].helper = {}
if( !('vars' in window['ace']) ) {
  window['ace'].vars = {
	 'icon'	: ' ace-icon ',
	'.icon'	: '.ace-icon'
  }
}
ace.vars['touch']	= 'ontouchstart' in document.documentElement;


jQuery(function($) {
	//sometimes we try to use 'tap' event instead of 'click' if jquery mobile plugin is available
	ace.click_event = ace.vars['touch'] && $.fn.tap ? 'tap' : 'click';

	//sometimes the only good way to work around browser's pecularities is to detect them using user-agents
	//though it's not accurate
	var agent = navigator.userAgent
	ace.vars['webkit'] = !!agent.match(/AppleWebKit/i)
	ace.vars['safari'] = !!agent.match(/Safari/i) && !agent.match(/Chrome/i);
	ace.vars['android'] = ace.vars['safari'] && !!agent.match(/Android/i)
	ace.vars['ios_safari'] = !!agent.match(/OS ([4-8])(_\d)+ like Mac OS X/i) && !agent.match(/CriOS/i)
	
	ace.vars['non_auto_fixed'] = ace.vars['android'] || ace.vars['ios_safari'];
	// for android and ios we don't use "top:auto" when breadcrumbs is fixed
	if(ace.vars['non_auto_fixed']) {
		$('body').addClass('mob-safari');
	}

	ace.vars['transition'] = 'transition' in document.body.style || 'WebkitTransition' in document.body.style || 'MozTransition' in document.body.style || 'OTransition' in document.body.style

	/////////////////////////////

	//a list of available functions with their arguments
	// >>> null means enable
	// >>> false means disable
	// >>> array means function arguments
	var available_functions = {
		'general_vars' : null,//general_vars should come first

		'handle_side_menu' : null,
		'add_touch_drag' : null,
				
		'sidebar_scrollable' : [
							 //true, //enable only if sidebar is fixed , for 2nd approach only
							 true //scroll to selected item? (one time only on page load)
							,true //true = include shortcut buttons in the scrollbars
							,false || ace.vars['safari'] || ace.vars['ios_safari'] //true = include toggle button in the scrollbars
							,200 //> 0 means smooth_scroll, time in ms, used in first approach only, better to be almost the same amount as submenu transition time
							,false//true && ace.vars['touch'] //used in first approach only, true means the scrollbars should be outside of the sidebar
							],

		'sidebar_hoverable' : null,//automatically move up a submenu, if some part of it goes out of window
		
		'general_things' : null,
		
		'widget_boxes' : null,
		'widget_reload_handler' : null,
								   
		'settings_box' : null,//settings box
		'settings_rtl' : null,
		'settings_skin' : null,
		
		'enable_searchbox_autocomplete' : null,
		
		'auto_hide_sidebar' : null,//disable?
		'auto_padding' : null,//disable
		'auto_container' : null//disable
	};
	
	//enable these functions with related params
	for(var func_name in available_functions) {
		if(!(func_name in ace)) continue;

		var args = available_functions[func_name];
		if(args === false) continue;//don't run this function

		else if(args == null) args = [jQuery];
		  else if(args instanceof String) args = [jQuery, args];
		    else if(args instanceof Array) args.unshift(jQuery);//prepend jQuery

		ace[func_name].apply(null, args);
	}

})



ace.general_vars = function($) {
	var minimized_menu_class  = 'menu-min';
	var responsive_min_class  = 'responsive-min';
	var horizontal_menu_class = 'h-sidebar';
	
	var sidebar = $('#sidebar').eq(0);
	//differnet mobile menu styles
	ace.vars['mobile_style'] = 1;//default responsive mode with toggle button inside navbar
	if(sidebar.hasClass('responsive') && !$('#menu-toggler').hasClass('navbar-toggle')) ace.vars['mobile_style'] = 2;//toggle button behind sidebar
	 else if(sidebar.hasClass(responsive_min_class)) ace.vars['mobile_style'] = 3;//minimized menu
	  else if(sidebar.hasClass('navbar-collapse')) ace.vars['mobile_style'] = 4;//collapsible (bootstrap style)

	//update some basic variables
	$(window).on('resize.ace.vars' , function(){
		ace.vars['window'] = {width: parseInt($(this).width()), height: parseInt($(this).height())}
		ace.vars['mobile_view'] = ace.vars['mobile_style'] < 4 && ace.helper.mobile_view();
		ace.vars['collapsible'] = !ace.vars['mobile_view'] && ace.helper.collapsible();
		ace.vars['nav_collapse'] = (ace.vars['collapsible'] || ace.vars['mobile_view']) && $('#navbar').hasClass('navbar-collapse');

		var sidebar = $(document.getElementById('sidebar'));
		ace.vars['minimized'] = 
		(!ace.vars['collapsible'] && sidebar.hasClass(minimized_menu_class))
		 ||
		(ace.vars['mobile_style'] == 3 && ace.vars['mobile_view'] && sidebar.hasClass(responsive_min_class))

		ace.vars['horizontal'] = !(ace.vars['mobile_view'] || ace.vars['collapsible']) && sidebar.hasClass(horizontal_menu_class)
	}).triggerHandler('resize.ace.vars');
}

//
ace.general_things = function($) {
	//add scrollbars for user dropdowns
	var has_scroll = !!$.fn.ace_scroll;
	if(has_scroll) $('.dropdown-content').ace_scroll({reset: false, mouseWheelLock: true})
	/**
	//add scrollbars to body
	 if(has_scroll) $('body').ace_scroll({size: ace.helper.winHeight()})
	 $('body').css('position', 'static')
	*/

	//reset scrolls bars on window resize
	$(window).on('resize.reset_scroll', function() {
		/**
		 //reset body scrollbars
		 if(has_scroll) $('body').ace_scroll('update', {size : ace.helper.winHeight()})
		*/
		if(has_scroll) $('.ace-scroll').ace_scroll('reset');
	});
	$(document).on('settings.ace.reset_scroll', function(e, name) {
		if(name == 'sidebar_collapsed' && has_scroll) $('.ace-scroll').ace_scroll('reset');
	});


	//change a dropdown to "dropup" depending on its position
	$(document).on('click.dropdown.pos', '.dropdown-toggle[data-position="auto"]', function() {
		var offset = $(this).offset();
		var parent = $(this.parentNode);

		if ( parseInt(offset.top + $(this).height()) + 50 
				>
			(ace.helper.scrollTop() + ace.helper.winHeight() - parent.find('.dropdown-menu').eq(0).height()) 
			) parent.addClass('dropup');
		else parent.removeClass('dropup');
	});
	
	
	//prevent dropdowns from hiding when a tab is selected
	$(document).on('click', '.dropdown-navbar .nav-tabs', function(e){
		e.stopPropagation();
		var $this , href
		var that = e.target
		if( ($this = $(e.target).closest('[data-toggle=tab]')) && $this.length > 0) {
			$this.tab('show');
			e.preventDefault();
		}
	});
	
	//prevent dropdowns from hiding when a from is clicked
	/**$(document).on('click', '.dropdown-navbar form', function(e){
		e.stopPropagation();		
	});*/


	//disable navbar icon animation upon click
	$('.ace-nav [class*="icon-animated-"]').closest('a').one('click', function(){
		var icon = $(this).find('[class*="icon-animated-"]').eq(0);
		var $match = icon.attr('class').match(/icon\-animated\-([\d\w]+)/);
		icon.removeClass($match[0]);
	});


	//tooltip in sidebar items
	$('.sidebar .nav-list .badge[title],.sidebar .nav-list .badge[title]').each(function() {
		var tooltip_class = $(this).attr('class').match(/tooltip\-(?:\w+)/);
		tooltip_class = tooltip_class ? tooltip_class[0] : 'tooltip-error';
		$(this).tooltip({
			'placement': function (context, source) {
				var offset = $(source).offset();

				if( parseInt(offset.left) < parseInt(document.body.scrollWidth / 2) ) return 'right';
				return 'left';
			},
			container: 'body',
			template: '<div class="tooltip '+tooltip_class+'"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'
		});
	});
	
	//or something like this if items are dynamically inserted
	/**$('.sidebar').tooltip({
		'placement': function (context, source) {
			var offset = $(source).offset();

			if( parseInt(offset.left) < parseInt(document.body.scrollWidth / 2) ) return 'right';
			return 'left';
		},
		selector: '.nav-list .badge[title],.nav-list .label[title]',
		container: 'body',
		template: '<div class="tooltip tooltip-error"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'
	});*/
	
	


	//the scroll to top button
	var scroll_btn = $('.btn-scroll-up');
	if(scroll_btn.length > 0) {
		var is_visible = false;
		$(window).on('scroll.scroll_btn', function() {
			if(ace.helper.scrollTop() > parseInt(ace.helper.winHeight() / 4)) {
				if(!is_visible) {
					scroll_btn.addClass('display');
					is_visible = true;
				}
			} else {
				if(is_visible) {
					scroll_btn.removeClass('display');
					is_visible = false;
				}
			}
		}).triggerHandler('scroll.scroll_btn');

		scroll_btn.on(ace.click_event, function(){
			var duration = Math.min(500, Math.max(100, parseInt(ace.helper.scrollTop() / 3)));
			$('html,body').animate({scrollTop: 0}, duration);
			return false;
		});
	}


	//chrome and webkit have a problem here when resizing from 460px to more
	//we should force them redraw the navbar!
	if( ace.vars['webkit'] ) {
		var ace_nav = $('.ace-nav').get(0);
		if( ace_nav ) $(window).on('resize.webkit' , function(){
			ace.helper.redraw(ace_nav);
		});
	}

}




//some functions
ace.helper.collapsible = function() {
	var toggle
	return (document.querySelector('#sidebar.navbar-collapse') != null)
	&& ((toggle = document.querySelector('.navbar-toggle[data-target*=".sidebar"]')) != null)
	&&  toggle.scrollHeight > 0
	//sidebar is collapsible and collapse button is visible?
}
ace.helper.mobile_view = function() {
	var toggle
	return ((toggle = document.getElementById('menu-toggler')) != null	&& toggle.scrollHeight > 0)
}

ace.helper.redraw = function(elem) {
	var saved_val = elem.style['display'];
	elem.style.display = 'none';
	elem.offsetHeight;
	elem.style.display = saved_val;
}

ace.helper.scrollTop = function() {
	return document.scrollTop || document.documentElement.scrollTop || document.body.scrollTop
	//return $(window).scrollTop();
}
ace.helper.winHeight = function() {
	return window.innerHeight || document.documentElement.clientHeight;
	//return $(window).innerHeight();
}
ace.helper.camelCase = function(str) {
	return str.replace(/-([\da-z])/gi, function(match, chr) {
	  return chr ? chr.toUpperCase() : '';
	});
}
ace.helper.removeStyle = 
  'removeProperty' in document.body.style
  ?
  function(elem, prop) { elem.style.removeProperty(prop) }
  :
  function(elem, prop) { elem.style[ace.helper.camelCase(prop)] = '' }


ace.helper.hasClass = 
  'classList' in document.documentElement
  ?
  function(elem, className) { return elem.classList.contains(className); }
  :
  function(elem, className) { return elem.className.indexOf(className) > -1; };

