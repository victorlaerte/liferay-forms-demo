package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.PortletDecorator;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.util.LayoutDescription;
import com.liferay.portlet.configuration.css.web.internal.constants.PortletConfigurationCSSConstants;
import com.liferay.portlet.configuration.css.web.internal.display.context.PortletConfigurationCSSPortletDisplayContext;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class border_005fstyles_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_row;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_wrapperCssClass_showEmptyOption_name_label_disabled_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_name_label_inlineField_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_option_value_selected_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_col_width_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_wrapperCssClass_showEmptyOption_name_label;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_option_selected_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_title_name_label_inlineField;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset_label;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_col_width_last_cssClass;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_aui_row = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_wrapperCssClass_showEmptyOption_name_label_disabled_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_name_label_inlineField_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_option_value_selected_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_col_width_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_wrapperCssClass_showEmptyOption_name_label = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_option_selected_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_title_name_label_inlineField = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset_label = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_col_width_last_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_aui_row.release();
    _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.release();
    _jspx_tagPool_aui_select_wrapperCssClass_showEmptyOption_name_label_disabled_cssClass.release();
    _jspx_tagPool_aui_input_value_name_label_inlineField_nobody.release();
    _jspx_tagPool_aui_option_value_selected_label_nobody.release();
    _jspx_tagPool_aui_col_width_cssClass.release();
    _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.release();
    _jspx_tagPool_aui_select_wrapperCssClass_showEmptyOption_name_label.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_aui_option_selected_label_nobody.release();
    _jspx_tagPool_aui_select_title_name_label_inlineField.release();
    _jspx_tagPool_aui_fieldset_label.release();
    _jspx_tagPool_aui_col_width_last_cssClass.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      out.write('\n');
      out.write("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
      //  liferay-theme:defineObjects
      com.liferay.taglib.theme.DefineObjectsTag _jspx_th_liferay$1theme_defineObjects_0 = (com.liferay.taglib.theme.DefineObjectsTag) _jspx_tagPool_liferay$1theme_defineObjects_nobody.get(com.liferay.taglib.theme.DefineObjectsTag.class);
      _jspx_th_liferay$1theme_defineObjects_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1theme_defineObjects_0.setParent(null);
      int _jspx_eval_liferay$1theme_defineObjects_0 = _jspx_th_liferay$1theme_defineObjects_0.doStartTag();
      if (_jspx_th_liferay$1theme_defineObjects_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1theme_defineObjects_nobody.reuse(_jspx_th_liferay$1theme_defineObjects_0);
        return;
      }
      _jspx_tagPool_liferay$1theme_defineObjects_nobody.reuse(_jspx_th_liferay$1theme_defineObjects_0);
      com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay = null;
      com.liferay.portal.kernel.model.Company company = null;
      com.liferay.portal.kernel.model.Account account = null;
      com.liferay.portal.kernel.model.User user = null;
      com.liferay.portal.kernel.model.User realUser = null;
      com.liferay.portal.kernel.model.Contact contact = null;
      com.liferay.portal.kernel.model.Layout layout = null;
      java.util.List layouts = null;
      java.lang.Long plid = null;
      com.liferay.portal.kernel.model.LayoutTypePortlet layoutTypePortlet = null;
      java.lang.Long scopeGroupId = null;
      com.liferay.portal.kernel.security.permission.PermissionChecker permissionChecker = null;
      java.util.Locale locale = null;
      java.util.TimeZone timeZone = null;
      com.liferay.portal.kernel.model.Theme theme = null;
      com.liferay.portal.kernel.model.ColorScheme colorScheme = null;
      com.liferay.portal.kernel.theme.PortletDisplay portletDisplay = null;
      java.lang.Long portletGroupId = null;
      themeDisplay = (com.liferay.portal.kernel.theme.ThemeDisplay) _jspx_page_context.findAttribute("themeDisplay");
      company = (com.liferay.portal.kernel.model.Company) _jspx_page_context.findAttribute("company");
      account = (com.liferay.portal.kernel.model.Account) _jspx_page_context.findAttribute("account");
      user = (com.liferay.portal.kernel.model.User) _jspx_page_context.findAttribute("user");
      realUser = (com.liferay.portal.kernel.model.User) _jspx_page_context.findAttribute("realUser");
      contact = (com.liferay.portal.kernel.model.Contact) _jspx_page_context.findAttribute("contact");
      layout = (com.liferay.portal.kernel.model.Layout) _jspx_page_context.findAttribute("layout");
      layouts = (java.util.List) _jspx_page_context.findAttribute("layouts");
      plid = (java.lang.Long) _jspx_page_context.findAttribute("plid");
      layoutTypePortlet = (com.liferay.portal.kernel.model.LayoutTypePortlet) _jspx_page_context.findAttribute("layoutTypePortlet");
      scopeGroupId = (java.lang.Long) _jspx_page_context.findAttribute("scopeGroupId");
      permissionChecker = (com.liferay.portal.kernel.security.permission.PermissionChecker) _jspx_page_context.findAttribute("permissionChecker");
      locale = (java.util.Locale) _jspx_page_context.findAttribute("locale");
      timeZone = (java.util.TimeZone) _jspx_page_context.findAttribute("timeZone");
      theme = (com.liferay.portal.kernel.model.Theme) _jspx_page_context.findAttribute("theme");
      colorScheme = (com.liferay.portal.kernel.model.ColorScheme) _jspx_page_context.findAttribute("colorScheme");
      portletDisplay = (com.liferay.portal.kernel.theme.PortletDisplay) _jspx_page_context.findAttribute("portletDisplay");
      portletGroupId = (java.lang.Long) _jspx_page_context.findAttribute("portletGroupId");
      out.write('\n');
      out.write('\n');
      //  portlet:defineObjects
      com.liferay.taglib.portlet.DefineObjectsTag _jspx_th_portlet_defineObjects_0 = (com.liferay.taglib.portlet.DefineObjectsTag) _jspx_tagPool_portlet_defineObjects_nobody.get(com.liferay.taglib.portlet.DefineObjectsTag.class);
      _jspx_th_portlet_defineObjects_0.setPageContext(_jspx_page_context);
      _jspx_th_portlet_defineObjects_0.setParent(null);
      int _jspx_eval_portlet_defineObjects_0 = _jspx_th_portlet_defineObjects_0.doStartTag();
      if (_jspx_th_portlet_defineObjects_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_portlet_defineObjects_nobody.reuse(_jspx_th_portlet_defineObjects_0);
        return;
      }
      _jspx_tagPool_portlet_defineObjects_nobody.reuse(_jspx_th_portlet_defineObjects_0);
      javax.portlet.ActionRequest actionRequest = null;
      javax.portlet.ActionResponse actionResponse = null;
      javax.portlet.EventRequest eventRequest = null;
      javax.portlet.EventResponse eventResponse = null;
      com.liferay.portal.kernel.portlet.LiferayPortletRequest liferayPortletRequest = null;
      com.liferay.portal.kernel.portlet.LiferayPortletResponse liferayPortletResponse = null;
      javax.portlet.PortletConfig portletConfig = null;
      java.lang.String portletName = null;
      javax.portlet.PortletPreferences portletPreferences = null;
      java.util.Map portletPreferencesValues = null;
      javax.portlet.PortletSession portletSession = null;
      java.util.Map portletSessionScope = null;
      javax.portlet.RenderRequest renderRequest = null;
      javax.portlet.RenderResponse renderResponse = null;
      javax.portlet.ResourceRequest resourceRequest = null;
      javax.portlet.ResourceResponse resourceResponse = null;
      actionRequest = (javax.portlet.ActionRequest) _jspx_page_context.findAttribute("actionRequest");
      actionResponse = (javax.portlet.ActionResponse) _jspx_page_context.findAttribute("actionResponse");
      eventRequest = (javax.portlet.EventRequest) _jspx_page_context.findAttribute("eventRequest");
      eventResponse = (javax.portlet.EventResponse) _jspx_page_context.findAttribute("eventResponse");
      liferayPortletRequest = (com.liferay.portal.kernel.portlet.LiferayPortletRequest) _jspx_page_context.findAttribute("liferayPortletRequest");
      liferayPortletResponse = (com.liferay.portal.kernel.portlet.LiferayPortletResponse) _jspx_page_context.findAttribute("liferayPortletResponse");
      portletConfig = (javax.portlet.PortletConfig) _jspx_page_context.findAttribute("portletConfig");
      portletName = (java.lang.String) _jspx_page_context.findAttribute("portletName");
      portletPreferences = (javax.portlet.PortletPreferences) _jspx_page_context.findAttribute("portletPreferences");
      portletPreferencesValues = (java.util.Map) _jspx_page_context.findAttribute("portletPreferencesValues");
      portletSession = (javax.portlet.PortletSession) _jspx_page_context.findAttribute("portletSession");
      portletSessionScope = (java.util.Map) _jspx_page_context.findAttribute("portletSessionScope");
      renderRequest = (javax.portlet.RenderRequest) _jspx_page_context.findAttribute("renderRequest");
      renderResponse = (javax.portlet.RenderResponse) _jspx_page_context.findAttribute("renderResponse");
      resourceRequest = (javax.portlet.ResourceRequest) _jspx_page_context.findAttribute("resourceRequest");
      resourceResponse = (javax.portlet.ResourceResponse) _jspx_page_context.findAttribute("resourceResponse");
      out.write('\n');
      out.write('\n');

String currentURL = PortalUtil.getCurrentURL(request);

PortletConfigurationCSSPortletDisplayContext portletConfigurationCSSPortletDisplayContext = new PortletConfigurationCSSPortletDisplayContext(renderRequest);

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      //  aui:row
      com.liferay.taglib.aui.RowTag _jspx_th_aui_row_0 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row.get(com.liferay.taglib.aui.RowTag.class);
      _jspx_th_aui_row_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_row_0.setParent(null);
      int _jspx_eval_aui_row_0 = _jspx_th_aui_row_0.doStartTag();
      if (_jspx_eval_aui_row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        //  aui:col
        com.liferay.taglib.aui.ColTag _jspx_th_aui_col_0 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_cssClass.get(com.liferay.taglib.aui.ColTag.class);
        _jspx_th_aui_col_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_col_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_0);
        _jspx_th_aui_col_0.setCssClass("lfr-border-width use-for-all-column");
        _jspx_th_aui_col_0.setWidth( 33 );
        int _jspx_eval_aui_col_0 = _jspx_th_aui_col_0.doStartTag();
        if (_jspx_eval_aui_col_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  aui:fieldset
          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_0 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label.get(com.liferay.taglib.aui.FieldsetTag.class);
          _jspx_th_aui_fieldset_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_fieldset_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_0);
          _jspx_th_aui_fieldset_0.setLabel("border-width");
          int _jspx_eval_aui_fieldset_0 = _jspx_th_aui_fieldset_0.doStartTag();
          if (_jspx_eval_aui_fieldset_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\n\t\t\t");

			Map<String, Object> contextUseForAllWidth = new HashMap<>();

			contextUseForAllWidth.put("checked", portletConfigurationCSSPortletDisplayContext.isBorderSameForAll("borderWidth"));
			contextUseForAllWidth.put("inputSelector", ".same-border-width");
			contextUseForAllWidth.put("label", LanguageUtil.get(request, "same-for-all"));
			contextUseForAllWidth.put("name", renderResponse.getNamespace() + "useForAllWidth");
			
            out.write("\n\n\t\t\t");
            //  soy:component-renderer
            com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag _jspx_th_soy_component$1renderer_0 = (com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag) _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.get(com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag.class);
            _jspx_th_soy_component$1renderer_0.setPageContext(_jspx_page_context);
            _jspx_th_soy_component$1renderer_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_soy_component$1renderer_0.setContext( contextUseForAllWidth );
            _jspx_th_soy_component$1renderer_0.setModule("portlet-configuration-css-web/js/ToggleDisableInputs.es");
            _jspx_th_soy_component$1renderer_0.setTemplateNamespace("com.liferay.portlet.configuration.css.web.ToggleDisableInputs.render");
            int _jspx_eval_soy_component$1renderer_0 = _jspx_th_soy_component$1renderer_0.doStartTag();
            if (_jspx_th_soy_component$1renderer_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.reuse(_jspx_th_soy_component$1renderer_0);
              return;
            }
            _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.reuse(_jspx_th_soy_component$1renderer_0);
            out.write("\n\n\t\t\t<span class=\"field-row\">\n\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_name_label_inlineField_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_aui_input_0.setInlineField( true );
            _jspx_th_aui_input_0.setLabel("top");
            _jspx_th_aui_input_0.setName("borderWidthTop");
            _jspx_th_aui_input_0.setValue( portletConfigurationCSSPortletDisplayContext.getBorderWidthProperty("top", "value") );
            int _jspx_eval_aui_input_0 = _jspx_th_aui_input_0.doStartTag();
            if (_jspx_th_aui_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_name_label_inlineField_nobody.reuse(_jspx_th_aui_input_0);
              return;
            }
            _jspx_tagPool_aui_input_value_name_label_inlineField_nobody.reuse(_jspx_th_aui_input_0);
            out.write("\n\n\t\t\t\t");
            //  aui:select
            com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_0 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_title_name_label_inlineField.get(com.liferay.taglib.aui.SelectTag.class);
            _jspx_th_aui_select_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_select_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_aui_select_0.setInlineField( true );
            _jspx_th_aui_select_0.setLabel("");
            _jspx_th_aui_select_0.setName("borderWidthTopUnit");
            _jspx_th_aui_select_0.setTitle("top-border-unit");
            int _jspx_eval_aui_select_0 = _jspx_th_aui_select_0.doStartTag();
            if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_aui_select_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_aui_select_0.doInitBody();
              }
              do {
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_0 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_0.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                _jspx_th_aui_option_0.setLabel(new String("%"));
                _jspx_th_aui_option_0.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderWidthProperty("top", "unit"), "%") );
                int _jspx_eval_aui_option_0 = _jspx_th_aui_option_0.doStartTag();
                if (_jspx_th_aui_option_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_0);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_0);
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_1 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_1.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                _jspx_th_aui_option_1.setLabel(new String("px"));
                _jspx_th_aui_option_1.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderWidthProperty("top", "unit"), "px") );
                int _jspx_eval_aui_option_1 = _jspx_th_aui_option_1.doStartTag();
                if (_jspx_th_aui_option_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_1);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_1);
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_2 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_2.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                _jspx_th_aui_option_2.setLabel(new String("em"));
                _jspx_th_aui_option_2.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderWidthProperty("top", "unit"), "em") );
                int _jspx_eval_aui_option_2 = _jspx_th_aui_option_2.doStartTag();
                if (_jspx_th_aui_option_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_2);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_2);
                out.write("\n\t\t\t\t");
                int evalDoAfterBody = _jspx_th_aui_select_0.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_aui_select_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_select_title_name_label_inlineField.reuse(_jspx_th_aui_select_0);
              return;
            }
            _jspx_tagPool_aui_select_title_name_label_inlineField.reuse(_jspx_th_aui_select_0);
            out.write("\n\t\t\t</span>\n\t\t\t<span class=\"field-row\">\n\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_1 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_1.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_aui_input_1.setCssClass("same-border-width");
            _jspx_th_aui_input_1.setDisabled( portletConfigurationCSSPortletDisplayContext.isBorderSameForAll("borderWidth") );
            _jspx_th_aui_input_1.setInlineField( true );
            _jspx_th_aui_input_1.setLabel("right");
            _jspx_th_aui_input_1.setName("borderWidthRight");
            _jspx_th_aui_input_1.setValue( portletConfigurationCSSPortletDisplayContext.getBorderWidthProperty("right", "value") );
            int _jspx_eval_aui_input_1 = _jspx_th_aui_input_1.doStartTag();
            if (_jspx_th_aui_input_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.reuse(_jspx_th_aui_input_1);
              return;
            }
            _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.reuse(_jspx_th_aui_input_1);
            out.write("\n\n\t\t\t\t");
            //  aui:select
            com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_1 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.get(com.liferay.taglib.aui.SelectTag.class);
            _jspx_th_aui_select_1.setPageContext(_jspx_page_context);
            _jspx_th_aui_select_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_aui_select_1.setCssClass("same-border-width");
            _jspx_th_aui_select_1.setDisabled( portletConfigurationCSSPortletDisplayContext.isBorderSameForAll("borderWidth") );
            _jspx_th_aui_select_1.setInlineField( true );
            _jspx_th_aui_select_1.setLabel("");
            _jspx_th_aui_select_1.setName("borderWidthRightUnit");
            _jspx_th_aui_select_1.setTitle("right-border-unit");
            int _jspx_eval_aui_select_1 = _jspx_th_aui_select_1.doStartTag();
            if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_aui_select_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_aui_select_1.doInitBody();
              }
              do {
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_3 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_3.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
                _jspx_th_aui_option_3.setLabel(new String("%"));
                _jspx_th_aui_option_3.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderWidthProperty("right", "unit"), "%") );
                int _jspx_eval_aui_option_3 = _jspx_th_aui_option_3.doStartTag();
                if (_jspx_th_aui_option_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_3);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_3);
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_4 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_4.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
                _jspx_th_aui_option_4.setLabel(new String("px"));
                _jspx_th_aui_option_4.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderWidthProperty("right", "unit"), "px") );
                int _jspx_eval_aui_option_4 = _jspx_th_aui_option_4.doStartTag();
                if (_jspx_th_aui_option_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_4);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_4);
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_5 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_5.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
                _jspx_th_aui_option_5.setLabel(new String("em"));
                _jspx_th_aui_option_5.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderWidthProperty("right", "unit"), "em") );
                int _jspx_eval_aui_option_5 = _jspx_th_aui_option_5.doStartTag();
                if (_jspx_th_aui_option_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_5);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_5);
                out.write("\n\t\t\t\t");
                int evalDoAfterBody = _jspx_th_aui_select_1.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_aui_select_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.reuse(_jspx_th_aui_select_1);
              return;
            }
            _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.reuse(_jspx_th_aui_select_1);
            out.write("\n\t\t\t</span>\n\t\t\t<span class=\"field-row\">\n\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_2 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_2.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_aui_input_2.setCssClass("same-border-width");
            _jspx_th_aui_input_2.setDisabled( portletConfigurationCSSPortletDisplayContext.isBorderSameForAll("borderWidth") );
            _jspx_th_aui_input_2.setInlineField( true );
            _jspx_th_aui_input_2.setLabel("bottom");
            _jspx_th_aui_input_2.setName("borderWidthBottom");
            _jspx_th_aui_input_2.setValue( portletConfigurationCSSPortletDisplayContext.getBorderWidthProperty("bottom", "value") );
            int _jspx_eval_aui_input_2 = _jspx_th_aui_input_2.doStartTag();
            if (_jspx_th_aui_input_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.reuse(_jspx_th_aui_input_2);
              return;
            }
            _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.reuse(_jspx_th_aui_input_2);
            out.write("\n\n\t\t\t\t");
            //  aui:select
            com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_2 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.get(com.liferay.taglib.aui.SelectTag.class);
            _jspx_th_aui_select_2.setPageContext(_jspx_page_context);
            _jspx_th_aui_select_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_aui_select_2.setCssClass("same-border-width");
            _jspx_th_aui_select_2.setDisabled( portletConfigurationCSSPortletDisplayContext.isBorderSameForAll("borderWidth") );
            _jspx_th_aui_select_2.setInlineField( true );
            _jspx_th_aui_select_2.setLabel("");
            _jspx_th_aui_select_2.setName("borderWidthBottomUnit");
            _jspx_th_aui_select_2.setTitle("bottom-border-unit");
            int _jspx_eval_aui_select_2 = _jspx_th_aui_select_2.doStartTag();
            if (_jspx_eval_aui_select_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_aui_select_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_aui_select_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_aui_select_2.doInitBody();
              }
              do {
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_6 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_6.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
                _jspx_th_aui_option_6.setLabel(new String("%"));
                _jspx_th_aui_option_6.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderWidthProperty("bottom", "unit"), "%") );
                int _jspx_eval_aui_option_6 = _jspx_th_aui_option_6.doStartTag();
                if (_jspx_th_aui_option_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_6);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_6);
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_7 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_7.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
                _jspx_th_aui_option_7.setLabel(new String("px"));
                _jspx_th_aui_option_7.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderWidthProperty("bottom", "unit"), "px") );
                int _jspx_eval_aui_option_7 = _jspx_th_aui_option_7.doStartTag();
                if (_jspx_th_aui_option_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_7);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_7);
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_8 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_8.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_2);
                _jspx_th_aui_option_8.setLabel(new String("em"));
                _jspx_th_aui_option_8.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderWidthProperty("bottom", "unit"), "em") );
                int _jspx_eval_aui_option_8 = _jspx_th_aui_option_8.doStartTag();
                if (_jspx_th_aui_option_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_8);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_8);
                out.write("\n\t\t\t\t");
                int evalDoAfterBody = _jspx_th_aui_select_2.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_aui_select_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_aui_select_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.reuse(_jspx_th_aui_select_2);
              return;
            }
            _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.reuse(_jspx_th_aui_select_2);
            out.write("\n\t\t\t</span>\n\t\t\t<span class=\"field-row\">\n\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_3 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_3.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_aui_input_3.setCssClass("same-border-width");
            _jspx_th_aui_input_3.setDisabled( portletConfigurationCSSPortletDisplayContext.isBorderSameForAll("borderWidth") );
            _jspx_th_aui_input_3.setInlineField( true );
            _jspx_th_aui_input_3.setLabel("left");
            _jspx_th_aui_input_3.setName("borderWidthLeft");
            _jspx_th_aui_input_3.setValue( portletConfigurationCSSPortletDisplayContext.getBorderWidthProperty("left", "value") );
            int _jspx_eval_aui_input_3 = _jspx_th_aui_input_3.doStartTag();
            if (_jspx_th_aui_input_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.reuse(_jspx_th_aui_input_3);
              return;
            }
            _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.reuse(_jspx_th_aui_input_3);
            out.write("\n\n\t\t\t\t");
            //  aui:select
            com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_3 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.get(com.liferay.taglib.aui.SelectTag.class);
            _jspx_th_aui_select_3.setPageContext(_jspx_page_context);
            _jspx_th_aui_select_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_aui_select_3.setCssClass("same-border-width");
            _jspx_th_aui_select_3.setDisabled( portletConfigurationCSSPortletDisplayContext.isBorderSameForAll("borderWidth") );
            _jspx_th_aui_select_3.setInlineField( true );
            _jspx_th_aui_select_3.setLabel("");
            _jspx_th_aui_select_3.setName("borderWidthLeftUnit");
            _jspx_th_aui_select_3.setTitle("left-border-unit");
            int _jspx_eval_aui_select_3 = _jspx_th_aui_select_3.doStartTag();
            if (_jspx_eval_aui_select_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_aui_select_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_aui_select_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_aui_select_3.doInitBody();
              }
              do {
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_9 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_9.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_3);
                _jspx_th_aui_option_9.setLabel(new String("%"));
                _jspx_th_aui_option_9.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderWidthProperty("left", "unit"), "%") );
                int _jspx_eval_aui_option_9 = _jspx_th_aui_option_9.doStartTag();
                if (_jspx_th_aui_option_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_9);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_9);
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_10 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_10.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_3);
                _jspx_th_aui_option_10.setLabel(new String("px"));
                _jspx_th_aui_option_10.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderWidthProperty("left", "unit"), "px") );
                int _jspx_eval_aui_option_10 = _jspx_th_aui_option_10.doStartTag();
                if (_jspx_th_aui_option_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_10);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_10);
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_11 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_11.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_3);
                _jspx_th_aui_option_11.setLabel(new String("em"));
                _jspx_th_aui_option_11.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderWidthProperty("left", "unit"), "em") );
                int _jspx_eval_aui_option_11 = _jspx_th_aui_option_11.doStartTag();
                if (_jspx_th_aui_option_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_11);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_11);
                out.write("\n\t\t\t\t");
                int evalDoAfterBody = _jspx_th_aui_select_3.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_aui_select_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_aui_select_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.reuse(_jspx_th_aui_select_3);
              return;
            }
            _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.reuse(_jspx_th_aui_select_3);
            out.write("\n\t\t\t</span>\n\t\t");
          }
          if (_jspx_th_aui_fieldset_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_fieldset_label.reuse(_jspx_th_aui_fieldset_0);
            return;
          }
          _jspx_tagPool_aui_fieldset_label.reuse(_jspx_th_aui_fieldset_0);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_aui_col_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_0);
          return;
        }
        _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_0);
        out.write("\n\n\t");
        //  aui:col
        com.liferay.taglib.aui.ColTag _jspx_th_aui_col_1 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_cssClass.get(com.liferay.taglib.aui.ColTag.class);
        _jspx_th_aui_col_1.setPageContext(_jspx_page_context);
        _jspx_th_aui_col_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_0);
        _jspx_th_aui_col_1.setCssClass("lfr-border-style");
        _jspx_th_aui_col_1.setWidth( 33 );
        int _jspx_eval_aui_col_1 = _jspx_th_aui_col_1.doStartTag();
        if (_jspx_eval_aui_col_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  aui:fieldset
          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_1 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label.get(com.liferay.taglib.aui.FieldsetTag.class);
          _jspx_th_aui_fieldset_1.setPageContext(_jspx_page_context);
          _jspx_th_aui_fieldset_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_1);
          _jspx_th_aui_fieldset_1.setLabel("border-style");
          int _jspx_eval_aui_fieldset_1 = _jspx_th_aui_fieldset_1.doStartTag();
          if (_jspx_eval_aui_fieldset_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\n\t\t\t");

			Map<String, Object> contextUseForAllStyle = new HashMap<>();

			contextUseForAllStyle.put("checked", portletConfigurationCSSPortletDisplayContext.isBorderSameForAll("borderStyle"));
			contextUseForAllStyle.put("inputSelector", ".same-border-style");
			contextUseForAllStyle.put("label", LanguageUtil.get(request, "same-for-all"));
			contextUseForAllStyle.put("name", renderResponse.getNamespace() + "useForAllStyle");
			
            out.write("\n\n\t\t\t");
            //  soy:component-renderer
            com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag _jspx_th_soy_component$1renderer_1 = (com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag) _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.get(com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag.class);
            _jspx_th_soy_component$1renderer_1.setPageContext(_jspx_page_context);
            _jspx_th_soy_component$1renderer_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_soy_component$1renderer_1.setContext( contextUseForAllStyle );
            _jspx_th_soy_component$1renderer_1.setModule("portlet-configuration-css-web/js/ToggleDisableInputs.es");
            _jspx_th_soy_component$1renderer_1.setTemplateNamespace("com.liferay.portlet.configuration.css.web.ToggleDisableInputs.render");
            int _jspx_eval_soy_component$1renderer_1 = _jspx_th_soy_component$1renderer_1.doStartTag();
            if (_jspx_th_soy_component$1renderer_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.reuse(_jspx_th_soy_component$1renderer_1);
              return;
            }
            _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.reuse(_jspx_th_soy_component$1renderer_1);
            out.write("\n\n\t\t\t");
            //  aui:select
            com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_4 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_wrapperCssClass_showEmptyOption_name_label.get(com.liferay.taglib.aui.SelectTag.class);
            _jspx_th_aui_select_4.setPageContext(_jspx_page_context);
            _jspx_th_aui_select_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_aui_select_4.setLabel("top");
            _jspx_th_aui_select_4.setName("borderStyleTop");
            _jspx_th_aui_select_4.setShowEmptyOption( true );
            _jspx_th_aui_select_4.setWrapperCssClass("field-row");
            int _jspx_eval_aui_select_4 = _jspx_th_aui_select_4.doStartTag();
            if (_jspx_eval_aui_select_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_aui_select_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_aui_select_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_aui_select_4.doInitBody();
              }
              do {
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_12 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_12.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                _jspx_th_aui_option_12.setLabel(new String("dashed"));
                _jspx_th_aui_option_12.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("top", "borderStyle"), "dashed") );
                int _jspx_eval_aui_option_12 = _jspx_th_aui_option_12.doStartTag();
                if (_jspx_th_aui_option_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_12);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_12);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_13 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_13.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                _jspx_th_aui_option_13.setLabel(new String("double"));
                _jspx_th_aui_option_13.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("top", "borderStyle"), "double") );
                int _jspx_eval_aui_option_13 = _jspx_th_aui_option_13.doStartTag();
                if (_jspx_th_aui_option_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_13);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_13);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_14 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_14.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                _jspx_th_aui_option_14.setLabel(new String("dotted"));
                _jspx_th_aui_option_14.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("top", "borderStyle"), "dotted") );
                int _jspx_eval_aui_option_14 = _jspx_th_aui_option_14.doStartTag();
                if (_jspx_th_aui_option_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_14);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_14);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_15 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_15.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                _jspx_th_aui_option_15.setLabel(new String("groove"));
                _jspx_th_aui_option_15.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("top", "borderStyle"), "groove") );
                int _jspx_eval_aui_option_15 = _jspx_th_aui_option_15.doStartTag();
                if (_jspx_th_aui_option_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_15);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_15);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_16 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_16.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                _jspx_th_aui_option_16.setLabel(new String("hidden[css]"));
                _jspx_th_aui_option_16.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("top", "borderStyle"), "hidden") );
                _jspx_th_aui_option_16.setValue(new String("hidden"));
                int _jspx_eval_aui_option_16 = _jspx_th_aui_option_16.doStartTag();
                if (_jspx_th_aui_option_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_16);
                  return;
                }
                _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_16);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_17 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_17.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                _jspx_th_aui_option_17.setLabel(new String("inset"));
                _jspx_th_aui_option_17.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("top", "borderStyle"), "inset") );
                int _jspx_eval_aui_option_17 = _jspx_th_aui_option_17.doStartTag();
                if (_jspx_th_aui_option_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_17);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_17);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_18 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_18.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                _jspx_th_aui_option_18.setLabel(new String("outset"));
                _jspx_th_aui_option_18.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("top", "borderStyle"), "outset") );
                int _jspx_eval_aui_option_18 = _jspx_th_aui_option_18.doStartTag();
                if (_jspx_th_aui_option_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_18);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_18);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_19 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_19.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                _jspx_th_aui_option_19.setLabel(new String("ridge"));
                _jspx_th_aui_option_19.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("top", "borderStyle"), "ridge") );
                int _jspx_eval_aui_option_19 = _jspx_th_aui_option_19.doStartTag();
                if (_jspx_th_aui_option_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_19);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_19);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_20 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_20.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                _jspx_th_aui_option_20.setLabel(new String("solid"));
                _jspx_th_aui_option_20.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("top", "borderStyle"), "solid") );
                int _jspx_eval_aui_option_20 = _jspx_th_aui_option_20.doStartTag();
                if (_jspx_th_aui_option_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_20);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_20);
                out.write("\n\t\t\t");
                int evalDoAfterBody = _jspx_th_aui_select_4.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_aui_select_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_aui_select_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_select_wrapperCssClass_showEmptyOption_name_label.reuse(_jspx_th_aui_select_4);
              return;
            }
            _jspx_tagPool_aui_select_wrapperCssClass_showEmptyOption_name_label.reuse(_jspx_th_aui_select_4);
            out.write("\n\n\t\t\t");
            //  aui:select
            com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_5 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_wrapperCssClass_showEmptyOption_name_label_disabled_cssClass.get(com.liferay.taglib.aui.SelectTag.class);
            _jspx_th_aui_select_5.setPageContext(_jspx_page_context);
            _jspx_th_aui_select_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_aui_select_5.setCssClass("same-border-style");
            _jspx_th_aui_select_5.setDisabled( portletConfigurationCSSPortletDisplayContext.isBorderSameForAll("borderStyle") );
            _jspx_th_aui_select_5.setLabel("right");
            _jspx_th_aui_select_5.setName("borderStyleRight");
            _jspx_th_aui_select_5.setShowEmptyOption( true );
            _jspx_th_aui_select_5.setWrapperCssClass("field-row");
            int _jspx_eval_aui_select_5 = _jspx_th_aui_select_5.doStartTag();
            if (_jspx_eval_aui_select_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_aui_select_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_aui_select_5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_aui_select_5.doInitBody();
              }
              do {
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_21 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_21.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_5);
                _jspx_th_aui_option_21.setLabel(new String("dashed"));
                _jspx_th_aui_option_21.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("right", "borderStyle"), "dashed") );
                int _jspx_eval_aui_option_21 = _jspx_th_aui_option_21.doStartTag();
                if (_jspx_th_aui_option_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_21);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_21);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_22 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_22.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_5);
                _jspx_th_aui_option_22.setLabel(new String("double"));
                _jspx_th_aui_option_22.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("right", "borderStyle"), "double") );
                int _jspx_eval_aui_option_22 = _jspx_th_aui_option_22.doStartTag();
                if (_jspx_th_aui_option_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_22);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_22);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_23 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_23.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_5);
                _jspx_th_aui_option_23.setLabel(new String("dotted"));
                _jspx_th_aui_option_23.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("right", "borderStyle"), "dotted") );
                int _jspx_eval_aui_option_23 = _jspx_th_aui_option_23.doStartTag();
                if (_jspx_th_aui_option_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_23);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_23);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_24 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_24.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_24.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_5);
                _jspx_th_aui_option_24.setLabel(new String("groove"));
                _jspx_th_aui_option_24.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("right", "borderStyle"), "groove") );
                int _jspx_eval_aui_option_24 = _jspx_th_aui_option_24.doStartTag();
                if (_jspx_th_aui_option_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_24);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_24);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_25 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_25.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_25.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_5);
                _jspx_th_aui_option_25.setLabel(new String("hidden[css]"));
                _jspx_th_aui_option_25.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("right", "borderStyle"), "hidden") );
                _jspx_th_aui_option_25.setValue(new String("hidden"));
                int _jspx_eval_aui_option_25 = _jspx_th_aui_option_25.doStartTag();
                if (_jspx_th_aui_option_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_25);
                  return;
                }
                _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_25);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_26 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_26.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_26.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_5);
                _jspx_th_aui_option_26.setLabel(new String("inset"));
                _jspx_th_aui_option_26.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("right", "borderStyle"), "inset") );
                int _jspx_eval_aui_option_26 = _jspx_th_aui_option_26.doStartTag();
                if (_jspx_th_aui_option_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_26);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_26);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_27 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_27.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_27.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_5);
                _jspx_th_aui_option_27.setLabel(new String("outset"));
                _jspx_th_aui_option_27.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("right", "borderStyle"), "outset") );
                int _jspx_eval_aui_option_27 = _jspx_th_aui_option_27.doStartTag();
                if (_jspx_th_aui_option_27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_27);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_27);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_28 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_28.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_28.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_5);
                _jspx_th_aui_option_28.setLabel(new String("ridge"));
                _jspx_th_aui_option_28.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("right", "borderStyle"), "ridge") );
                int _jspx_eval_aui_option_28 = _jspx_th_aui_option_28.doStartTag();
                if (_jspx_th_aui_option_28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_28);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_28);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_29 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_29.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_29.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_5);
                _jspx_th_aui_option_29.setLabel(new String("solid"));
                _jspx_th_aui_option_29.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("right", "borderStyle"), "solid") );
                int _jspx_eval_aui_option_29 = _jspx_th_aui_option_29.doStartTag();
                if (_jspx_th_aui_option_29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_29);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_29);
                out.write("\n\t\t\t");
                int evalDoAfterBody = _jspx_th_aui_select_5.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_aui_select_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_aui_select_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_select_wrapperCssClass_showEmptyOption_name_label_disabled_cssClass.reuse(_jspx_th_aui_select_5);
              return;
            }
            _jspx_tagPool_aui_select_wrapperCssClass_showEmptyOption_name_label_disabled_cssClass.reuse(_jspx_th_aui_select_5);
            out.write("\n\n\t\t\t");
            //  aui:select
            com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_6 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_wrapperCssClass_showEmptyOption_name_label_disabled_cssClass.get(com.liferay.taglib.aui.SelectTag.class);
            _jspx_th_aui_select_6.setPageContext(_jspx_page_context);
            _jspx_th_aui_select_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_aui_select_6.setCssClass("same-border-style");
            _jspx_th_aui_select_6.setDisabled( portletConfigurationCSSPortletDisplayContext.isBorderSameForAll("borderStyle") );
            _jspx_th_aui_select_6.setLabel("bottom");
            _jspx_th_aui_select_6.setName("borderStyleBottom");
            _jspx_th_aui_select_6.setShowEmptyOption( true );
            _jspx_th_aui_select_6.setWrapperCssClass("field-row");
            int _jspx_eval_aui_select_6 = _jspx_th_aui_select_6.doStartTag();
            if (_jspx_eval_aui_select_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_aui_select_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_aui_select_6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_aui_select_6.doInitBody();
              }
              do {
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_30 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_30.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_30.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_6);
                _jspx_th_aui_option_30.setLabel(new String("dashed"));
                _jspx_th_aui_option_30.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("bottom", "borderStyle"), "dashed") );
                int _jspx_eval_aui_option_30 = _jspx_th_aui_option_30.doStartTag();
                if (_jspx_th_aui_option_30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_30);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_30);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_31 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_31.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_31.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_6);
                _jspx_th_aui_option_31.setLabel(new String("double"));
                _jspx_th_aui_option_31.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("bottom", "borderStyle"), "double") );
                int _jspx_eval_aui_option_31 = _jspx_th_aui_option_31.doStartTag();
                if (_jspx_th_aui_option_31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_31);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_31);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_32 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_32.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_32.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_6);
                _jspx_th_aui_option_32.setLabel(new String("dotted"));
                _jspx_th_aui_option_32.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("bottom", "borderStyle"), "dotted") );
                int _jspx_eval_aui_option_32 = _jspx_th_aui_option_32.doStartTag();
                if (_jspx_th_aui_option_32.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_32);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_32);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_33 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_33.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_33.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_6);
                _jspx_th_aui_option_33.setLabel(new String("groove"));
                _jspx_th_aui_option_33.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("bottom", "borderStyle"), "groove") );
                int _jspx_eval_aui_option_33 = _jspx_th_aui_option_33.doStartTag();
                if (_jspx_th_aui_option_33.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_33);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_33);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_34 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_34.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_34.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_6);
                _jspx_th_aui_option_34.setLabel(new String("hidden[css]"));
                _jspx_th_aui_option_34.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("bottom", "borderStyle"), "hidden") );
                _jspx_th_aui_option_34.setValue(new String("hidden"));
                int _jspx_eval_aui_option_34 = _jspx_th_aui_option_34.doStartTag();
                if (_jspx_th_aui_option_34.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_34);
                  return;
                }
                _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_34);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_35 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_35.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_35.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_6);
                _jspx_th_aui_option_35.setLabel(new String("inset"));
                _jspx_th_aui_option_35.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("bottom", "borderStyle"), "inset") );
                int _jspx_eval_aui_option_35 = _jspx_th_aui_option_35.doStartTag();
                if (_jspx_th_aui_option_35.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_35);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_35);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_36 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_36.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_36.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_6);
                _jspx_th_aui_option_36.setLabel(new String("outset"));
                _jspx_th_aui_option_36.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("bottom", "borderStyle"), "outset") );
                int _jspx_eval_aui_option_36 = _jspx_th_aui_option_36.doStartTag();
                if (_jspx_th_aui_option_36.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_36);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_36);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_37 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_37.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_37.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_6);
                _jspx_th_aui_option_37.setLabel(new String("ridge"));
                _jspx_th_aui_option_37.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("bottom", "borderStyle"), "ridge") );
                int _jspx_eval_aui_option_37 = _jspx_th_aui_option_37.doStartTag();
                if (_jspx_th_aui_option_37.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_37);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_37);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_38 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_38.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_38.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_6);
                _jspx_th_aui_option_38.setLabel(new String("solid"));
                _jspx_th_aui_option_38.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("bottom", "borderStyle"), "solid") );
                int _jspx_eval_aui_option_38 = _jspx_th_aui_option_38.doStartTag();
                if (_jspx_th_aui_option_38.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_38);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_38);
                out.write("\n\t\t\t");
                int evalDoAfterBody = _jspx_th_aui_select_6.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_aui_select_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_aui_select_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_select_wrapperCssClass_showEmptyOption_name_label_disabled_cssClass.reuse(_jspx_th_aui_select_6);
              return;
            }
            _jspx_tagPool_aui_select_wrapperCssClass_showEmptyOption_name_label_disabled_cssClass.reuse(_jspx_th_aui_select_6);
            out.write("\n\n\t\t\t");
            //  aui:select
            com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_7 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_wrapperCssClass_showEmptyOption_name_label_disabled_cssClass.get(com.liferay.taglib.aui.SelectTag.class);
            _jspx_th_aui_select_7.setPageContext(_jspx_page_context);
            _jspx_th_aui_select_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_aui_select_7.setCssClass("same-border-style");
            _jspx_th_aui_select_7.setDisabled( portletConfigurationCSSPortletDisplayContext.isBorderSameForAll("borderStyle") );
            _jspx_th_aui_select_7.setLabel("left");
            _jspx_th_aui_select_7.setName("borderStyleLeft");
            _jspx_th_aui_select_7.setShowEmptyOption( true );
            _jspx_th_aui_select_7.setWrapperCssClass("field-row");
            int _jspx_eval_aui_select_7 = _jspx_th_aui_select_7.doStartTag();
            if (_jspx_eval_aui_select_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_aui_select_7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_aui_select_7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_aui_select_7.doInitBody();
              }
              do {
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_39 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_39.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_39.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_7);
                _jspx_th_aui_option_39.setLabel(new String("dashed"));
                _jspx_th_aui_option_39.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("left", "borderStyle"), "dashed") );
                int _jspx_eval_aui_option_39 = _jspx_th_aui_option_39.doStartTag();
                if (_jspx_th_aui_option_39.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_39);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_39);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_40 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_40.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_40.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_7);
                _jspx_th_aui_option_40.setLabel(new String("double"));
                _jspx_th_aui_option_40.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("left", "borderStyle"), "double") );
                int _jspx_eval_aui_option_40 = _jspx_th_aui_option_40.doStartTag();
                if (_jspx_th_aui_option_40.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_40);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_40);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_41 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_41.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_41.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_7);
                _jspx_th_aui_option_41.setLabel(new String("dotted"));
                _jspx_th_aui_option_41.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("left", "borderStyle"), "dotted") );
                int _jspx_eval_aui_option_41 = _jspx_th_aui_option_41.doStartTag();
                if (_jspx_th_aui_option_41.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_41);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_41);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_42 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_42.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_42.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_7);
                _jspx_th_aui_option_42.setLabel(new String("groove"));
                _jspx_th_aui_option_42.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("left", "borderStyle"), "groove") );
                int _jspx_eval_aui_option_42 = _jspx_th_aui_option_42.doStartTag();
                if (_jspx_th_aui_option_42.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_42);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_42);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_43 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_value_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_43.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_43.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_7);
                _jspx_th_aui_option_43.setLabel(new String("hidden[css]"));
                _jspx_th_aui_option_43.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("left", "borderStyle"), "hidden") );
                _jspx_th_aui_option_43.setValue(new String("hidden"));
                int _jspx_eval_aui_option_43 = _jspx_th_aui_option_43.doStartTag();
                if (_jspx_th_aui_option_43.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_43);
                  return;
                }
                _jspx_tagPool_aui_option_value_selected_label_nobody.reuse(_jspx_th_aui_option_43);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_44 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_44.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_44.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_7);
                _jspx_th_aui_option_44.setLabel(new String("inset"));
                _jspx_th_aui_option_44.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("left", "borderStyle"), "inset") );
                int _jspx_eval_aui_option_44 = _jspx_th_aui_option_44.doStartTag();
                if (_jspx_th_aui_option_44.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_44);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_44);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_45 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_45.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_45.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_7);
                _jspx_th_aui_option_45.setLabel(new String("outset"));
                _jspx_th_aui_option_45.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("left", "borderStyle"), "outset") );
                int _jspx_eval_aui_option_45 = _jspx_th_aui_option_45.doStartTag();
                if (_jspx_th_aui_option_45.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_45);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_45);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_46 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_46.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_46.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_7);
                _jspx_th_aui_option_46.setLabel(new String("ridge"));
                _jspx_th_aui_option_46.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("left", "borderStyle"), "ridge") );
                int _jspx_eval_aui_option_46 = _jspx_th_aui_option_46.doStartTag();
                if (_jspx_th_aui_option_46.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_46);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_46);
                out.write("\n\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_47 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_47.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_47.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_7);
                _jspx_th_aui_option_47.setLabel(new String("solid"));
                _jspx_th_aui_option_47.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getBorderProperty("left", "borderStyle"), "solid") );
                int _jspx_eval_aui_option_47 = _jspx_th_aui_option_47.doStartTag();
                if (_jspx_th_aui_option_47.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_47);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_47);
                out.write("\n\t\t\t");
                int evalDoAfterBody = _jspx_th_aui_select_7.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_aui_select_7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_aui_select_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_select_wrapperCssClass_showEmptyOption_name_label_disabled_cssClass.reuse(_jspx_th_aui_select_7);
              return;
            }
            _jspx_tagPool_aui_select_wrapperCssClass_showEmptyOption_name_label_disabled_cssClass.reuse(_jspx_th_aui_select_7);
            out.write("\n\t\t");
          }
          if (_jspx_th_aui_fieldset_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_fieldset_label.reuse(_jspx_th_aui_fieldset_1);
            return;
          }
          _jspx_tagPool_aui_fieldset_label.reuse(_jspx_th_aui_fieldset_1);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_aui_col_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_1);
          return;
        }
        _jspx_tagPool_aui_col_width_cssClass.reuse(_jspx_th_aui_col_1);
        out.write("\n\n\t");
        //  aui:col
        com.liferay.taglib.aui.ColTag _jspx_th_aui_col_2 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_last_cssClass.get(com.liferay.taglib.aui.ColTag.class);
        _jspx_th_aui_col_2.setPageContext(_jspx_page_context);
        _jspx_th_aui_col_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_0);
        _jspx_th_aui_col_2.setCssClass("lfr-border-color");
        _jspx_th_aui_col_2.setDynamicAttribute(null, "last",  true );
        _jspx_th_aui_col_2.setWidth( 33 );
        int _jspx_eval_aui_col_2 = _jspx_th_aui_col_2.doStartTag();
        if (_jspx_eval_aui_col_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  aui:fieldset
          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_2 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label.get(com.liferay.taglib.aui.FieldsetTag.class);
          _jspx_th_aui_fieldset_2.setPageContext(_jspx_page_context);
          _jspx_th_aui_fieldset_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_2);
          _jspx_th_aui_fieldset_2.setLabel("border-color");
          int _jspx_eval_aui_fieldset_2 = _jspx_th_aui_fieldset_2.doStartTag();
          if (_jspx_eval_aui_fieldset_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\n\t\t\t");

			Map<String, Object> contextUseForAllColor = new HashMap<>();

			contextUseForAllColor.put("checked", portletConfigurationCSSPortletDisplayContext.isBorderSameForAll("borderColor"));
			contextUseForAllColor.put("inputSelector", ".same-border-color");
			contextUseForAllColor.put("label", LanguageUtil.get(request, "same-for-all"));
			contextUseForAllColor.put("name", renderResponse.getNamespace() + "useForAllColor");
			
            out.write("\n\n\t\t\t");
            //  soy:component-renderer
            com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag _jspx_th_soy_component$1renderer_2 = (com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag) _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.get(com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag.class);
            _jspx_th_soy_component$1renderer_2.setPageContext(_jspx_page_context);
            _jspx_th_soy_component$1renderer_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
            _jspx_th_soy_component$1renderer_2.setContext( contextUseForAllColor );
            _jspx_th_soy_component$1renderer_2.setModule("portlet-configuration-css-web/js/ToggleDisableInputs.es");
            _jspx_th_soy_component$1renderer_2.setTemplateNamespace("com.liferay.portlet.configuration.css.web.ToggleDisableInputs.render");
            int _jspx_eval_soy_component$1renderer_2 = _jspx_th_soy_component$1renderer_2.doStartTag();
            if (_jspx_th_soy_component$1renderer_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.reuse(_jspx_th_soy_component$1renderer_2);
              return;
            }
            _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.reuse(_jspx_th_soy_component$1renderer_2);
            out.write("\n\n\t\t\t");

			Map<String, Object> contextBorderTop = new HashMap<>();

			contextBorderTop.put("color", portletConfigurationCSSPortletDisplayContext.getBorderProperty("top", "borderColor"));
			contextBorderTop.put("elementClasses", "field-row");
			contextBorderTop.put("id", renderResponse.getNamespace() + "borderColorTop");
			contextBorderTop.put("label", LanguageUtil.get(request, "top"));
			contextBorderTop.put("name", renderResponse.getNamespace() + "borderColorTop");
			
            out.write("\n\n\t\t\t");
            //  soy:component-renderer
            com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag _jspx_th_soy_component$1renderer_3 = (com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag) _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.get(com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag.class);
            _jspx_th_soy_component$1renderer_3.setPageContext(_jspx_page_context);
            _jspx_th_soy_component$1renderer_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
            _jspx_th_soy_component$1renderer_3.setContext( contextBorderTop );
            _jspx_th_soy_component$1renderer_3.setModule("portlet-configuration-css-web/js/ColorPickerInput.es");
            _jspx_th_soy_component$1renderer_3.setTemplateNamespace("com.liferay.portlet.configuration.css.web.ColorPickerInput.render");
            int _jspx_eval_soy_component$1renderer_3 = _jspx_th_soy_component$1renderer_3.doStartTag();
            if (_jspx_th_soy_component$1renderer_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.reuse(_jspx_th_soy_component$1renderer_3);
              return;
            }
            _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.reuse(_jspx_th_soy_component$1renderer_3);
            out.write("\n\n\t\t\t");

			Map<String, Object> contextBorderRight = new HashMap<>();

			contextBorderRight.put("color", portletConfigurationCSSPortletDisplayContext.getBorderProperty("right", "borderColor"));
			contextBorderRight.put("disabled", portletConfigurationCSSPortletDisplayContext.isBorderSameForAll("borderColor"));
			contextBorderRight.put("elementClasses", "field-row");
			contextBorderRight.put("id", renderResponse.getNamespace() + "borderColorRight");
			contextBorderRight.put("inputClasses", "same-border-color");
			contextBorderRight.put("label", LanguageUtil.get(request, "right"));
			contextBorderRight.put("name", renderResponse.getNamespace() + "borderColorRight");
			
            out.write("\n\n\t\t\t");
            //  soy:component-renderer
            com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag _jspx_th_soy_component$1renderer_4 = (com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag) _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.get(com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag.class);
            _jspx_th_soy_component$1renderer_4.setPageContext(_jspx_page_context);
            _jspx_th_soy_component$1renderer_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
            _jspx_th_soy_component$1renderer_4.setContext( contextBorderRight );
            _jspx_th_soy_component$1renderer_4.setModule("portlet-configuration-css-web/js/ColorPickerInput.es");
            _jspx_th_soy_component$1renderer_4.setTemplateNamespace("com.liferay.portlet.configuration.css.web.ColorPickerInput.render");
            int _jspx_eval_soy_component$1renderer_4 = _jspx_th_soy_component$1renderer_4.doStartTag();
            if (_jspx_th_soy_component$1renderer_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.reuse(_jspx_th_soy_component$1renderer_4);
              return;
            }
            _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.reuse(_jspx_th_soy_component$1renderer_4);
            out.write("\n\n\t\t\t");

			Map<String, Object> contextBorderBottom = new HashMap<>();

			contextBorderBottom.put("color", portletConfigurationCSSPortletDisplayContext.getBorderProperty("bottom", "borderColor"));
			contextBorderBottom.put("disabled", portletConfigurationCSSPortletDisplayContext.isBorderSameForAll("borderColor"));
			contextBorderBottom.put("elementClasses", "field-row");
			contextBorderBottom.put("id", renderResponse.getNamespace() + "borderColorBottom");
			contextBorderBottom.put("inputClasses", "same-border-color");
			contextBorderBottom.put("label", LanguageUtil.get(request, "bottom"));
			contextBorderBottom.put("name", renderResponse.getNamespace() + "borderColorBottom");
			
            out.write("\n\n\t\t\t");
            //  soy:component-renderer
            com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag _jspx_th_soy_component$1renderer_5 = (com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag) _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.get(com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag.class);
            _jspx_th_soy_component$1renderer_5.setPageContext(_jspx_page_context);
            _jspx_th_soy_component$1renderer_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
            _jspx_th_soy_component$1renderer_5.setContext( contextBorderBottom );
            _jspx_th_soy_component$1renderer_5.setModule("portlet-configuration-css-web/js/ColorPickerInput.es");
            _jspx_th_soy_component$1renderer_5.setTemplateNamespace("com.liferay.portlet.configuration.css.web.ColorPickerInput.render");
            int _jspx_eval_soy_component$1renderer_5 = _jspx_th_soy_component$1renderer_5.doStartTag();
            if (_jspx_th_soy_component$1renderer_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.reuse(_jspx_th_soy_component$1renderer_5);
              return;
            }
            _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.reuse(_jspx_th_soy_component$1renderer_5);
            out.write("\n\n\t\t\t");

			Map<String, Object> contextBorderLeft = new HashMap<>();

			contextBorderLeft.put("color", portletConfigurationCSSPortletDisplayContext.getBorderProperty("left", "borderColor"));
			contextBorderLeft.put("disabled", portletConfigurationCSSPortletDisplayContext.isBorderSameForAll("borderColor"));
			contextBorderLeft.put("elementClasses", "field-row");
			contextBorderLeft.put("id", renderResponse.getNamespace() + "borderColorLeft");
			contextBorderLeft.put("inputClasses", "same-border-color");
			contextBorderLeft.put("label", LanguageUtil.get(request, "left"));
			contextBorderLeft.put("name", renderResponse.getNamespace() + "borderColorLeft");
			
            out.write("\n\n\t\t\t");
            //  soy:component-renderer
            com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag _jspx_th_soy_component$1renderer_6 = (com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag) _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.get(com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag.class);
            _jspx_th_soy_component$1renderer_6.setPageContext(_jspx_page_context);
            _jspx_th_soy_component$1renderer_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_2);
            _jspx_th_soy_component$1renderer_6.setContext( contextBorderLeft );
            _jspx_th_soy_component$1renderer_6.setModule("portlet-configuration-css-web/js/ColorPickerInput.es");
            _jspx_th_soy_component$1renderer_6.setTemplateNamespace("com.liferay.portlet.configuration.css.web.ColorPickerInput.render");
            int _jspx_eval_soy_component$1renderer_6 = _jspx_th_soy_component$1renderer_6.doStartTag();
            if (_jspx_th_soy_component$1renderer_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.reuse(_jspx_th_soy_component$1renderer_6);
              return;
            }
            _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.reuse(_jspx_th_soy_component$1renderer_6);
            out.write("\n\t\t");
          }
          if (_jspx_th_aui_fieldset_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_fieldset_label.reuse(_jspx_th_aui_fieldset_2);
            return;
          }
          _jspx_tagPool_aui_fieldset_label.reuse(_jspx_th_aui_fieldset_2);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_aui_col_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_col_width_last_cssClass.reuse(_jspx_th_aui_col_2);
          return;
        }
        _jspx_tagPool_aui_col_width_last_cssClass.reuse(_jspx_th_aui_col_2);
        out.write('\n');
      }
      if (_jspx_th_aui_row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_0);
        return;
      }
      _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_0);
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
