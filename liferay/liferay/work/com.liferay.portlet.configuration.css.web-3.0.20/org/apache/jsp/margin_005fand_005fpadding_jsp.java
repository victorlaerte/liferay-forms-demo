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

public final class margin_005fand_005fpadding_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_name_label_inlineField_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_row;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_col_width_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_option_selected_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_title_name_label_inlineField;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset_label;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_col_width_last_cssClass;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_aui_input_value_name_label_inlineField_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_row = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_col_width_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_option_selected_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_title_name_label_inlineField = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset_label = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_col_width_last_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_aui_input_value_name_label_inlineField_nobody.release();
    _jspx_tagPool_aui_row.release();
    _jspx_tagPool_aui_col_width_cssClass.release();
    _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_aui_option_selected_label_nobody.release();
    _jspx_tagPool_aui_select_title_name_label_inlineField.release();
    _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_aui_fieldset_label.release();
    _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.release();
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
        _jspx_th_aui_col_0.setCssClass("lfr-padding use-for-all-column");
        _jspx_th_aui_col_0.setWidth( 50 );
        int _jspx_eval_aui_col_0 = _jspx_th_aui_col_0.doStartTag();
        if (_jspx_eval_aui_col_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  aui:fieldset
          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_0 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label.get(com.liferay.taglib.aui.FieldsetTag.class);
          _jspx_th_aui_fieldset_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_fieldset_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_0);
          _jspx_th_aui_fieldset_0.setLabel("padding");
          int _jspx_eval_aui_fieldset_0 = _jspx_th_aui_fieldset_0.doStartTag();
          if (_jspx_eval_aui_fieldset_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\n\t\t\t");

			Map<String, Object> contextUseForAllPadding = new HashMap<>();

			contextUseForAllPadding.put("checked", portletConfigurationCSSPortletDisplayContext.isSpacingSameForAll("padding"));
			contextUseForAllPadding.put("inputSelector", ".same-padding");
			contextUseForAllPadding.put("label", LanguageUtil.get(request, "same-for-all"));
			contextUseForAllPadding.put("name", renderResponse.getNamespace() + "useForAllPadding");
			
            out.write("\n\n\t\t\t");
            //  soy:component-renderer
            com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag _jspx_th_soy_component$1renderer_0 = (com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag) _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.get(com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag.class);
            _jspx_th_soy_component$1renderer_0.setPageContext(_jspx_page_context);
            _jspx_th_soy_component$1renderer_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
            _jspx_th_soy_component$1renderer_0.setContext( contextUseForAllPadding );
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
            _jspx_th_aui_input_0.setName("paddingTop");
            _jspx_th_aui_input_0.setValue( portletConfigurationCSSPortletDisplayContext.getPaddingProperty("top", "value") );
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
            _jspx_th_aui_select_0.setName("paddingTopUnit");
            _jspx_th_aui_select_0.setTitle("top-padding-unit");
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
                _jspx_th_aui_option_0.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getPaddingProperty("top", "unit"), "%") );
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
                _jspx_th_aui_option_1.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getPaddingProperty("top", "unit"), "px") );
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
                _jspx_th_aui_option_2.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getPaddingProperty("top", "unit"), "em") );
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
            _jspx_th_aui_input_1.setCssClass("same-padding");
            _jspx_th_aui_input_1.setDisabled( portletConfigurationCSSPortletDisplayContext.isSpacingSameForAll("padding") );
            _jspx_th_aui_input_1.setInlineField( true );
            _jspx_th_aui_input_1.setLabel("right");
            _jspx_th_aui_input_1.setName("paddingRight");
            _jspx_th_aui_input_1.setValue( portletConfigurationCSSPortletDisplayContext.getPaddingProperty("right", "value") );
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
            _jspx_th_aui_select_1.setCssClass("same-padding");
            _jspx_th_aui_select_1.setDisabled( portletConfigurationCSSPortletDisplayContext.isSpacingSameForAll("padding") );
            _jspx_th_aui_select_1.setInlineField( true );
            _jspx_th_aui_select_1.setLabel("");
            _jspx_th_aui_select_1.setName("paddingRightUnit");
            _jspx_th_aui_select_1.setTitle("right-padding-unit");
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
                _jspx_th_aui_option_3.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getPaddingProperty("right", "unit"), "%") );
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
                _jspx_th_aui_option_4.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getPaddingProperty("right", "unit"), "px") );
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
                _jspx_th_aui_option_5.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getPaddingProperty("right", "unit"), "em") );
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
            _jspx_th_aui_input_2.setCssClass("same-padding");
            _jspx_th_aui_input_2.setDisabled( portletConfigurationCSSPortletDisplayContext.isSpacingSameForAll("padding") );
            _jspx_th_aui_input_2.setInlineField( true );
            _jspx_th_aui_input_2.setLabel("bottom");
            _jspx_th_aui_input_2.setName("paddingBottom");
            _jspx_th_aui_input_2.setValue( portletConfigurationCSSPortletDisplayContext.getPaddingProperty("bottom", "value") );
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
            _jspx_th_aui_select_2.setCssClass("same-padding");
            _jspx_th_aui_select_2.setDisabled( portletConfigurationCSSPortletDisplayContext.isSpacingSameForAll("padding") );
            _jspx_th_aui_select_2.setInlineField( true );
            _jspx_th_aui_select_2.setLabel("");
            _jspx_th_aui_select_2.setName("paddingBottomUnit");
            _jspx_th_aui_select_2.setTitle("bottom-padding-unit");
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
                _jspx_th_aui_option_6.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getPaddingProperty("bottom", "unit"), "%") );
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
                _jspx_th_aui_option_7.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getPaddingProperty("bottom", "unit"), "px") );
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
                _jspx_th_aui_option_8.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getPaddingProperty("bottom", "unit"), "em") );
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
            _jspx_th_aui_input_3.setCssClass("same-padding");
            _jspx_th_aui_input_3.setDisabled( portletConfigurationCSSPortletDisplayContext.isSpacingSameForAll("padding") );
            _jspx_th_aui_input_3.setInlineField( true );
            _jspx_th_aui_input_3.setLabel("left");
            _jspx_th_aui_input_3.setName("paddingLeft");
            _jspx_th_aui_input_3.setValue( portletConfigurationCSSPortletDisplayContext.getPaddingProperty("left", "value") );
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
            _jspx_th_aui_select_3.setCssClass("same-padding");
            _jspx_th_aui_select_3.setDisabled( portletConfigurationCSSPortletDisplayContext.isSpacingSameForAll("padding") );
            _jspx_th_aui_select_3.setInlineField( true );
            _jspx_th_aui_select_3.setLabel("");
            _jspx_th_aui_select_3.setName("paddingLeftUnit");
            _jspx_th_aui_select_3.setTitle("left-padding-unit");
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
                _jspx_th_aui_option_9.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getPaddingProperty("left", "unit"), "%") );
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
                _jspx_th_aui_option_10.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getPaddingProperty("left", "unit"), "px") );
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
                _jspx_th_aui_option_11.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getPaddingProperty("left", "unit"), "em") );
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
        com.liferay.taglib.aui.ColTag _jspx_th_aui_col_1 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width_last_cssClass.get(com.liferay.taglib.aui.ColTag.class);
        _jspx_th_aui_col_1.setPageContext(_jspx_page_context);
        _jspx_th_aui_col_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_0);
        _jspx_th_aui_col_1.setCssClass("lfr-margin use-for-all-column");
        _jspx_th_aui_col_1.setDynamicAttribute(null, "last",  true );
        _jspx_th_aui_col_1.setWidth( 50 );
        int _jspx_eval_aui_col_1 = _jspx_th_aui_col_1.doStartTag();
        if (_jspx_eval_aui_col_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  aui:fieldset
          com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_1 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label.get(com.liferay.taglib.aui.FieldsetTag.class);
          _jspx_th_aui_fieldset_1.setPageContext(_jspx_page_context);
          _jspx_th_aui_fieldset_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_1);
          _jspx_th_aui_fieldset_1.setLabel("margin");
          int _jspx_eval_aui_fieldset_1 = _jspx_th_aui_fieldset_1.doStartTag();
          if (_jspx_eval_aui_fieldset_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\n\t\t\t");

			Map<String, Object> contextUseForAllMargin = new HashMap<>();

			contextUseForAllMargin.put("checked", portletConfigurationCSSPortletDisplayContext.isSpacingSameForAll("margin"));
			contextUseForAllMargin.put("inputSelector", ".same-margin");
			contextUseForAllMargin.put("label", LanguageUtil.get(request, "same-for-all"));
			contextUseForAllMargin.put("name", renderResponse.getNamespace() + "useForAllMargin");
			
            out.write("\n\n\t\t\t");
            //  soy:component-renderer
            com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag _jspx_th_soy_component$1renderer_1 = (com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag) _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.get(com.liferay.frontend.taglib.soy.servlet.taglib.ComponentRendererTag.class);
            _jspx_th_soy_component$1renderer_1.setPageContext(_jspx_page_context);
            _jspx_th_soy_component$1renderer_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_soy_component$1renderer_1.setContext( contextUseForAllMargin );
            _jspx_th_soy_component$1renderer_1.setModule("portlet-configuration-css-web/js/ToggleDisableInputs.es");
            _jspx_th_soy_component$1renderer_1.setTemplateNamespace("com.liferay.portlet.configuration.css.web.ToggleDisableInputs.render");
            int _jspx_eval_soy_component$1renderer_1 = _jspx_th_soy_component$1renderer_1.doStartTag();
            if (_jspx_th_soy_component$1renderer_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.reuse(_jspx_th_soy_component$1renderer_1);
              return;
            }
            _jspx_tagPool_soy_component$1renderer_templateNamespace_module_context_nobody.reuse(_jspx_th_soy_component$1renderer_1);
            out.write("\n\n\t\t\t<span class=\"field-row\">\n\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_4 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_name_label_inlineField_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_4.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_aui_input_4.setInlineField( true );
            _jspx_th_aui_input_4.setLabel("top");
            _jspx_th_aui_input_4.setName("marginTop");
            _jspx_th_aui_input_4.setValue( portletConfigurationCSSPortletDisplayContext.getMarginProperty("top", "value") );
            int _jspx_eval_aui_input_4 = _jspx_th_aui_input_4.doStartTag();
            if (_jspx_th_aui_input_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_name_label_inlineField_nobody.reuse(_jspx_th_aui_input_4);
              return;
            }
            _jspx_tagPool_aui_input_value_name_label_inlineField_nobody.reuse(_jspx_th_aui_input_4);
            out.write("\n\n\t\t\t\t");
            //  aui:select
            com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_4 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_title_name_label_inlineField.get(com.liferay.taglib.aui.SelectTag.class);
            _jspx_th_aui_select_4.setPageContext(_jspx_page_context);
            _jspx_th_aui_select_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_aui_select_4.setInlineField( true );
            _jspx_th_aui_select_4.setLabel("");
            _jspx_th_aui_select_4.setName("marginTopUnit");
            _jspx_th_aui_select_4.setTitle("top-margin-unit");
            int _jspx_eval_aui_select_4 = _jspx_th_aui_select_4.doStartTag();
            if (_jspx_eval_aui_select_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_aui_select_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_aui_select_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_aui_select_4.doInitBody();
              }
              do {
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_12 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_12.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                _jspx_th_aui_option_12.setLabel(new String("%"));
                _jspx_th_aui_option_12.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getMarginProperty("top", "unit"), "%") );
                int _jspx_eval_aui_option_12 = _jspx_th_aui_option_12.doStartTag();
                if (_jspx_th_aui_option_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_12);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_12);
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_13 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_13.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                _jspx_th_aui_option_13.setLabel(new String("px"));
                _jspx_th_aui_option_13.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getMarginProperty("top", "unit"), "px") );
                int _jspx_eval_aui_option_13 = _jspx_th_aui_option_13.doStartTag();
                if (_jspx_th_aui_option_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_13);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_13);
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_14 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_14.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_4);
                _jspx_th_aui_option_14.setLabel(new String("em"));
                _jspx_th_aui_option_14.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getMarginProperty("top", "unit"), "em") );
                int _jspx_eval_aui_option_14 = _jspx_th_aui_option_14.doStartTag();
                if (_jspx_th_aui_option_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_14);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_14);
                out.write("\n\t\t\t\t");
                int evalDoAfterBody = _jspx_th_aui_select_4.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_aui_select_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_aui_select_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_select_title_name_label_inlineField.reuse(_jspx_th_aui_select_4);
              return;
            }
            _jspx_tagPool_aui_select_title_name_label_inlineField.reuse(_jspx_th_aui_select_4);
            out.write("\n\t\t\t</span>\n\t\t\t<span class=\"field-row\">\n\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_5 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_5.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_aui_input_5.setCssClass("same-margin");
            _jspx_th_aui_input_5.setDisabled( portletConfigurationCSSPortletDisplayContext.isSpacingSameForAll("margin") );
            _jspx_th_aui_input_5.setInlineField( true );
            _jspx_th_aui_input_5.setLabel("right");
            _jspx_th_aui_input_5.setName("marginRight");
            _jspx_th_aui_input_5.setValue( portletConfigurationCSSPortletDisplayContext.getMarginProperty("right", "value") );
            int _jspx_eval_aui_input_5 = _jspx_th_aui_input_5.doStartTag();
            if (_jspx_th_aui_input_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.reuse(_jspx_th_aui_input_5);
              return;
            }
            _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.reuse(_jspx_th_aui_input_5);
            out.write("\n\n\t\t\t\t");
            //  aui:select
            com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_5 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.get(com.liferay.taglib.aui.SelectTag.class);
            _jspx_th_aui_select_5.setPageContext(_jspx_page_context);
            _jspx_th_aui_select_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_aui_select_5.setCssClass("same-margin");
            _jspx_th_aui_select_5.setDisabled( portletConfigurationCSSPortletDisplayContext.isSpacingSameForAll("margin") );
            _jspx_th_aui_select_5.setInlineField( true );
            _jspx_th_aui_select_5.setLabel("");
            _jspx_th_aui_select_5.setName("marginRightUnit");
            _jspx_th_aui_select_5.setTitle("top-margin-unit");
            int _jspx_eval_aui_select_5 = _jspx_th_aui_select_5.doStartTag();
            if (_jspx_eval_aui_select_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_aui_select_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_aui_select_5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_aui_select_5.doInitBody();
              }
              do {
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_15 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_15.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_5);
                _jspx_th_aui_option_15.setLabel(new String("%"));
                _jspx_th_aui_option_15.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getMarginProperty("right", "unit"), "%") );
                int _jspx_eval_aui_option_15 = _jspx_th_aui_option_15.doStartTag();
                if (_jspx_th_aui_option_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_15);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_15);
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_16 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_16.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_5);
                _jspx_th_aui_option_16.setLabel(new String("px"));
                _jspx_th_aui_option_16.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getMarginProperty("right", "unit"), "px") );
                int _jspx_eval_aui_option_16 = _jspx_th_aui_option_16.doStartTag();
                if (_jspx_th_aui_option_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_16);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_16);
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_17 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_17.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_5);
                _jspx_th_aui_option_17.setLabel(new String("em"));
                _jspx_th_aui_option_17.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getMarginProperty("right", "unit"), "em") );
                int _jspx_eval_aui_option_17 = _jspx_th_aui_option_17.doStartTag();
                if (_jspx_th_aui_option_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_17);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_17);
                out.write("\n\t\t\t\t");
                int evalDoAfterBody = _jspx_th_aui_select_5.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_aui_select_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_aui_select_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.reuse(_jspx_th_aui_select_5);
              return;
            }
            _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.reuse(_jspx_th_aui_select_5);
            out.write("\n\t\t\t</span>\n\t\t\t<span class=\"field-row\">\n\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_6 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_6.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_aui_input_6.setCssClass("same-margin");
            _jspx_th_aui_input_6.setDisabled( portletConfigurationCSSPortletDisplayContext.isSpacingSameForAll("margin") );
            _jspx_th_aui_input_6.setInlineField( true );
            _jspx_th_aui_input_6.setLabel("bottom");
            _jspx_th_aui_input_6.setName("marginBottom");
            _jspx_th_aui_input_6.setValue( portletConfigurationCSSPortletDisplayContext.getMarginProperty("bottom", "value") );
            int _jspx_eval_aui_input_6 = _jspx_th_aui_input_6.doStartTag();
            if (_jspx_th_aui_input_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.reuse(_jspx_th_aui_input_6);
              return;
            }
            _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.reuse(_jspx_th_aui_input_6);
            out.write("\n\n\t\t\t\t");
            //  aui:select
            com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_6 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.get(com.liferay.taglib.aui.SelectTag.class);
            _jspx_th_aui_select_6.setPageContext(_jspx_page_context);
            _jspx_th_aui_select_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_aui_select_6.setCssClass("same-margin");
            _jspx_th_aui_select_6.setDisabled( portletConfigurationCSSPortletDisplayContext.isSpacingSameForAll("margin") );
            _jspx_th_aui_select_6.setInlineField( true );
            _jspx_th_aui_select_6.setLabel("");
            _jspx_th_aui_select_6.setName("marginBottomUnit");
            _jspx_th_aui_select_6.setTitle("top-margin-unit");
            int _jspx_eval_aui_select_6 = _jspx_th_aui_select_6.doStartTag();
            if (_jspx_eval_aui_select_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_aui_select_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_aui_select_6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_aui_select_6.doInitBody();
              }
              do {
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_18 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_18.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_6);
                _jspx_th_aui_option_18.setLabel(new String("%"));
                _jspx_th_aui_option_18.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getMarginProperty("bottom", "unit"), "%") );
                int _jspx_eval_aui_option_18 = _jspx_th_aui_option_18.doStartTag();
                if (_jspx_th_aui_option_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_18);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_18);
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_19 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_19.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_6);
                _jspx_th_aui_option_19.setLabel(new String("px"));
                _jspx_th_aui_option_19.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getMarginProperty("bottom", "unit"), "px") );
                int _jspx_eval_aui_option_19 = _jspx_th_aui_option_19.doStartTag();
                if (_jspx_th_aui_option_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_19);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_19);
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_20 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_20.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_6);
                _jspx_th_aui_option_20.setLabel(new String("em"));
                _jspx_th_aui_option_20.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getMarginProperty("bottom", "unit"), "em") );
                int _jspx_eval_aui_option_20 = _jspx_th_aui_option_20.doStartTag();
                if (_jspx_th_aui_option_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_20);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_20);
                out.write("\n\t\t\t\t");
                int evalDoAfterBody = _jspx_th_aui_select_6.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_aui_select_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_aui_select_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.reuse(_jspx_th_aui_select_6);
              return;
            }
            _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.reuse(_jspx_th_aui_select_6);
            out.write("\n\t\t\t</span>\n\t\t\t<span class=\"field-row\">\n\t\t\t\t");
            //  aui:input
            com.liferay.taglib.aui.InputTag _jspx_th_aui_input_7 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.get(com.liferay.taglib.aui.InputTag.class);
            _jspx_th_aui_input_7.setPageContext(_jspx_page_context);
            _jspx_th_aui_input_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_aui_input_7.setCssClass("same-margin");
            _jspx_th_aui_input_7.setDisabled( portletConfigurationCSSPortletDisplayContext.isSpacingSameForAll("margin") );
            _jspx_th_aui_input_7.setInlineField( true );
            _jspx_th_aui_input_7.setLabel("left");
            _jspx_th_aui_input_7.setName("marginLeft");
            _jspx_th_aui_input_7.setValue( portletConfigurationCSSPortletDisplayContext.getMarginProperty("bottom", "value") );
            int _jspx_eval_aui_input_7 = _jspx_th_aui_input_7.doStartTag();
            if (_jspx_th_aui_input_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.reuse(_jspx_th_aui_input_7);
              return;
            }
            _jspx_tagPool_aui_input_value_name_label_inlineField_disabled_cssClass_nobody.reuse(_jspx_th_aui_input_7);
            out.write("\n\n\t\t\t\t");
            //  aui:select
            com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_7 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.get(com.liferay.taglib.aui.SelectTag.class);
            _jspx_th_aui_select_7.setPageContext(_jspx_page_context);
            _jspx_th_aui_select_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
            _jspx_th_aui_select_7.setCssClass("same-margin");
            _jspx_th_aui_select_7.setDisabled( portletConfigurationCSSPortletDisplayContext.isSpacingSameForAll("margin") );
            _jspx_th_aui_select_7.setInlineField( true );
            _jspx_th_aui_select_7.setLabel("");
            _jspx_th_aui_select_7.setName("marginLeftUnit");
            _jspx_th_aui_select_7.setTitle("top-margin-unit");
            int _jspx_eval_aui_select_7 = _jspx_th_aui_select_7.doStartTag();
            if (_jspx_eval_aui_select_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_eval_aui_select_7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_aui_select_7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_aui_select_7.doInitBody();
              }
              do {
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_21 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_21.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_21.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_7);
                _jspx_th_aui_option_21.setLabel(new String("%"));
                _jspx_th_aui_option_21.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getMarginProperty("bottom", "unit"), "%") );
                int _jspx_eval_aui_option_21 = _jspx_th_aui_option_21.doStartTag();
                if (_jspx_th_aui_option_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_21);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_21);
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_22 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_22.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_22.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_7);
                _jspx_th_aui_option_22.setLabel(new String("px"));
                _jspx_th_aui_option_22.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getMarginProperty("bottom", "unit"), "px") );
                int _jspx_eval_aui_option_22 = _jspx_th_aui_option_22.doStartTag();
                if (_jspx_th_aui_option_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_22);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_22);
                out.write("\n\t\t\t\t\t");
                //  aui:option
                com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_23 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_selected_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                _jspx_th_aui_option_23.setPageContext(_jspx_page_context);
                _jspx_th_aui_option_23.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_7);
                _jspx_th_aui_option_23.setLabel(new String("em"));
                _jspx_th_aui_option_23.setSelected( Objects.equals(portletConfigurationCSSPortletDisplayContext.getMarginProperty("bottom", "unit"), "em") );
                int _jspx_eval_aui_option_23 = _jspx_th_aui_option_23.doStartTag();
                if (_jspx_th_aui_option_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_23);
                  return;
                }
                _jspx_tagPool_aui_option_selected_label_nobody.reuse(_jspx_th_aui_option_23);
                out.write("\n\t\t\t\t");
                int evalDoAfterBody = _jspx_th_aui_select_7.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_aui_select_7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_aui_select_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.reuse(_jspx_th_aui_select_7);
              return;
            }
            _jspx_tagPool_aui_select_title_name_label_inlineField_disabled_cssClass.reuse(_jspx_th_aui_select_7);
            out.write("\n\t\t\t</span>\n\t\t");
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
          _jspx_tagPool_aui_col_width_last_cssClass.reuse(_jspx_th_aui_col_1);
          return;
        }
        _jspx_tagPool_aui_col_width_last_cssClass.reuse(_jspx_th_aui_col_1);
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
