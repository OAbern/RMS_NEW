/**
 * jQuery-Plugin "clearField"
 * 
 * @version: 1.1, 04.12.2010
 * 
 * @author: Stijn Van Minnebruggen
 *          stijn@donotfold.be
 *          http://www.donotfold.be
 * 
 * @example: $('selector').clearField();
 * @example: $('selector').clearField({ blurClass: 'myBlurredClass', activeClass: 'myActiveClass' });
 * 
 */

(function($) {
	
	$.fn.clearField = function(settings) {
		
		/**
		 * Settings
		 * 
		 */
		
		settings = jQuery.extend({
			blurClass: 'clearFieldBlurred',
			activeClass: 'clearFieldActive',
			attribute: 'rel',
			value: ''
		}, settings);
		
		
		/**
		 * loop each element
		 * 
		 */
		
		return $(this).each(function() {
			
			/**
			 * Set element
			 * 
			 */
			
			var el = $(this);
			
			
			/**
			 * Get starting value
			 * 
			 */
			
			settings.value = el.val();
			
			
			/**
			 * Add or get attribute
			 * 
			 */
			
			if(el.attr(settings.attribute) == undefined) {
				el.attr(settings.attribute, el.val()).addClass(settings.blurClass);
			} else {
				settings.value = el.attr(settings.attribute);
			}
			
			
			/**
			 * Set focus action
			 * 
			 */
			
			el.focus(function() {
				
				if(el.val() == el.attr(settings.attribute)) {
					el.val('').removeClass(settings.blurClass).addClass(settings.activeClass);
				}
				
			});
			
			
			/**
			 * Set blur action
			 * 
			 */
			
			el.blur(function() {
				
				if(el.val() == '') {
					el.val(el.attr(settings.attribute)).removeClass(settings.activeClass).addClass(settings.blurClass);
				}
				
			});
			
			
		});
		
	};
	
})(jQuery);
