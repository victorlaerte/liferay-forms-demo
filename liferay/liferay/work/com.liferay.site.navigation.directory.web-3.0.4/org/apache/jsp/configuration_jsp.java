package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.site.navigation.directory.web.internal.display.context.SitesDirectoryDisplayContext;
import com.liferay.taglib.ui.SitesDirectoryTag;

public final class configuration_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_namespace_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_edit$1form_name_method_fluid_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_renderURL_var_portletConfiguration_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_row;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_input_value_type_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_fieldset_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_option_label_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_sandbox;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_edit$1form$1body;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_select_value_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_type_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_col_width;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_edit$1form$1footer;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_fieldset$1group;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_preview_showBorders_queryString_portletName_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_namespace_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_edit$1form_name_method_fluid_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_renderURL_var_portletConfiguration_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_row = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_input_value_type_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_fieldset_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_option_label_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_sandbox = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_edit$1form$1body = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_select_value_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_type_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_col_width = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_edit$1form$1footer = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_fieldset$1group = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_preview_showBorders_queryString_portletName_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_namespace_nobody.release();
    _jspx_tagPool_liferay$1frontend_edit$1form_name_method_fluid_action.release();
    _jspx_tagPool_liferay$1portlet_renderURL_var_portletConfiguration_nobody.release();
    _jspx_tagPool_aui_row.release();
    _jspx_tagPool_aui_input_value_type_name_nobody.release();
    _jspx_tagPool_liferay$1frontend_fieldset_cssClass.release();
    _jspx_tagPool_aui_option_label_nobody.release();
    _jspx_tagPool_aui_script_sandbox.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_edit$1form$1body.release();
    _jspx_tagPool_aui_select_value_name.release();
    _jspx_tagPool_aui_button_type_nobody.release();
    _jspx_tagPool_aui_col_width.release();
    _jspx_tagPool_liferay$1frontend_edit$1form$1footer.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1frontend_fieldset$1group.release();
    _jspx_tagPool_liferay$1portlet_preview_showBorders_queryString_portletName_nobody.release();
    _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration_nobody.release();
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
      out.write("\n\n\n\n\n\n\n\n");
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

String portletResource = ParamUtil.getString(request, "portletResource");

SitesDirectoryDisplayContext sitesDirectoryDisplayContext = new SitesDirectoryDisplayContext(request);

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      //  liferay-portlet:actionURL
      com.liferay.taglib.portlet.ActionURLTag _jspx_th_liferay$1portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration_nobody.get(com.liferay.taglib.portlet.ActionURLTag.class);
      _jspx_th_liferay$1portlet_actionURL_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1portlet_actionURL_0.setParent(null);
      _jspx_th_liferay$1portlet_actionURL_0.setPortletConfiguration( true );
      _jspx_th_liferay$1portlet_actionURL_0.setVar("configurationActionURL");
      int _jspx_eval_liferay$1portlet_actionURL_0 = _jspx_th_liferay$1portlet_actionURL_0.doStartTag();
      if (_jspx_th_liferay$1portlet_actionURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration_nobody.reuse(_jspx_th_liferay$1portlet_actionURL_0);
        return;
      }
      _jspx_tagPool_liferay$1portlet_actionURL_var_portletConfiguration_nobody.reuse(_jspx_th_liferay$1portlet_actionURL_0);
      java.lang.String configurationActionURL = null;
      configurationActionURL = (java.lang.String) _jspx_page_context.findAttribute("configurationActionURL");
      out.write('\n');
      out.write('\n');
      //  liferay-portlet:renderURL
      com.liferay.taglib.portlet.RenderURLTag _jspx_th_liferay$1portlet_renderURL_0 = (com.liferay.taglib.portlet.RenderURLTag) _jspx_tagPool_liferay$1portlet_renderURL_var_portletConfiguration_nobody.get(com.liferay.taglib.portlet.RenderURLTag.class);
      _jspx_th_liferay$1portlet_renderURL_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1portlet_renderURL_0.setParent(null);
      _jspx_th_liferay$1portlet_renderURL_0.setPortletConfiguration( true );
      _jspx_th_liferay$1portlet_renderURL_0.setVar("configurationRenderURL");
      int _jspx_eval_liferay$1portlet_renderURL_0 = _jspx_th_liferay$1portlet_renderURL_0.doStartTag();
      if (_jspx_th_liferay$1portlet_renderURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1portlet_renderURL_var_portletConfiguration_nobody.reuse(_jspx_th_liferay$1portlet_renderURL_0);
        return;
      }
      _jspx_tagPool_liferay$1portlet_renderURL_var_portletConfiguration_nobody.reuse(_jspx_th_liferay$1portlet_renderURL_0);
      java.lang.String configurationRenderURL = null;
      configurationRenderURL = (java.lang.String) _jspx_page_context.findAttribute("configurationRenderURL");
      out.write('\n');
      out.write('\n');
      //  liferay-frontend:edit-form
      com.liferay.frontend.taglib.servlet.taglib.EditFormTag _jspx_th_liferay$1frontend_edit$1form_0 = (com.liferay.frontend.taglib.servlet.taglib.EditFormTag) _jspx_tagPool_liferay$1frontend_edit$1form_name_method_fluid_action.get(com.liferay.frontend.taglib.servlet.taglib.EditFormTag.class);
      _jspx_th_liferay$1frontend_edit$1form_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1frontend_edit$1form_0.setParent(null);
      _jspx_th_liferay$1frontend_edit$1form_0.setAction( configurationActionURL );
      _jspx_th_liferay$1frontend_edit$1form_0.setFluid( true );
      _jspx_th_liferay$1frontend_edit$1form_0.setMethod("post");
      _jspx_th_liferay$1frontend_edit$1form_0.setName("fm");
      int _jspx_eval_liferay$1frontend_edit$1form_0 = _jspx_th_liferay$1frontend_edit$1form_0.doStartTag();
      if (_jspx_eval_liferay$1frontend_edit$1form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_0 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_0.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form_0);
        _jspx_th_aui_input_0.setName( Constants.CMD );
        _jspx_th_aui_input_0.setType("hidden");
        _jspx_th_aui_input_0.setValue( Constants.UPDATE );
        int _jspx_eval_aui_input_0 = _jspx_th_aui_input_0.doStartTag();
        if (_jspx_th_aui_input_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_0);
        out.write('\n');
        out.write('	');
        //  aui:input
        com.liferay.taglib.aui.InputTag _jspx_th_aui_input_1 = (com.liferay.taglib.aui.InputTag) _jspx_tagPool_aui_input_value_type_name_nobody.get(com.liferay.taglib.aui.InputTag.class);
        _jspx_th_aui_input_1.setPageContext(_jspx_page_context);
        _jspx_th_aui_input_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form_0);
        _jspx_th_aui_input_1.setName("redirect");
        _jspx_th_aui_input_1.setType("hidden");
        _jspx_th_aui_input_1.setValue( configurationRenderURL );
        int _jspx_eval_aui_input_1 = _jspx_th_aui_input_1.doStartTag();
        if (_jspx_th_aui_input_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
          return;
        }
        _jspx_tagPool_aui_input_value_type_name_nobody.reuse(_jspx_th_aui_input_1);
        out.write("\n\n\t");
        //  liferay-frontend:edit-form-body
        com.liferay.frontend.taglib.servlet.taglib.EditFormBodyTag _jspx_th_liferay$1frontend_edit$1form$1body_0 = (com.liferay.frontend.taglib.servlet.taglib.EditFormBodyTag) _jspx_tagPool_liferay$1frontend_edit$1form$1body.get(com.liferay.frontend.taglib.servlet.taglib.EditFormBodyTag.class);
        _jspx_th_liferay$1frontend_edit$1form$1body_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1frontend_edit$1form$1body_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form_0);
        int _jspx_eval_liferay$1frontend_edit$1form$1body_0 = _jspx_th_liferay$1frontend_edit$1form$1body_0.doStartTag();
        if (_jspx_eval_liferay$1frontend_edit$1form$1body_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  aui:row
          com.liferay.taglib.aui.RowTag _jspx_th_aui_row_0 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row.get(com.liferay.taglib.aui.RowTag.class);
          _jspx_th_aui_row_0.setPageContext(_jspx_page_context);
          _jspx_th_aui_row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form$1body_0);
          int _jspx_eval_aui_row_0 = _jspx_th_aui_row_0.doStartTag();
          if (_jspx_eval_aui_row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t");
            //  aui:col
            com.liferay.taglib.aui.ColTag _jspx_th_aui_col_0 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width.get(com.liferay.taglib.aui.ColTag.class);
            _jspx_th_aui_col_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_col_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_0);
            _jspx_th_aui_col_0.setWidth( 50 );
            int _jspx_eval_aui_col_0 = _jspx_th_aui_col_0.doStartTag();
            if (_jspx_eval_aui_col_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              //  liferay-frontend:fieldset-group
              com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag _jspx_th_liferay$1frontend_fieldset$1group_0 = (com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag) _jspx_tagPool_liferay$1frontend_fieldset$1group.get(com.liferay.frontend.taglib.servlet.taglib.FieldsetGroupTag.class);
              _jspx_th_liferay$1frontend_fieldset$1group_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1frontend_fieldset$1group_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_0);
              int _jspx_eval_liferay$1frontend_fieldset$1group_0 = _jspx_th_liferay$1frontend_fieldset$1group_0.doStartTag();
              if (_jspx_eval_liferay$1frontend_fieldset$1group_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t\t");
                //  liferay-frontend:fieldset
                com.liferay.frontend.taglib.servlet.taglib.FieldsetTag _jspx_th_liferay$1frontend_fieldset_0 = (com.liferay.frontend.taglib.servlet.taglib.FieldsetTag) _jspx_tagPool_liferay$1frontend_fieldset_cssClass.get(com.liferay.frontend.taglib.servlet.taglib.FieldsetTag.class);
                _jspx_th_liferay$1frontend_fieldset_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1frontend_fieldset_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset$1group_0);
                _jspx_th_liferay$1frontend_fieldset_0.setCssClass("ml-3");
                int _jspx_eval_liferay$1frontend_fieldset_0 = _jspx_th_liferay$1frontend_fieldset_0.doStartTag();
                if (_jspx_eval_liferay$1frontend_fieldset_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t\t");
                  //  aui:row
                  com.liferay.taglib.aui.RowTag _jspx_th_aui_row_1 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row.get(com.liferay.taglib.aui.RowTag.class);
                  _jspx_th_aui_row_1.setPageContext(_jspx_page_context);
                  _jspx_th_aui_row_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
                  int _jspx_eval_aui_row_1 = _jspx_th_aui_row_1.doStartTag();
                  if (_jspx_eval_aui_row_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t");
                    //  aui:select
                    com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_0 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_value_name.get(com.liferay.taglib.aui.SelectTag.class);
                    _jspx_th_aui_select_0.setPageContext(_jspx_page_context);
                    _jspx_th_aui_select_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_1);
                    _jspx_th_aui_select_0.setName("preferences--sites--");
                    _jspx_th_aui_select_0.setValue( sitesDirectoryDisplayContext.getSites() );
                    int _jspx_eval_aui_select_0 = _jspx_th_aui_select_0.doStartTag();
                    if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_aui_select_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_aui_select_0.doInitBody();
                      }
                      do {
                        out.write("\n\t\t\t\t\t\t\t\t");
                        //  aui:option
                        com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_0 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                        _jspx_th_aui_option_0.setPageContext(_jspx_page_context);
                        _jspx_th_aui_option_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                        _jspx_th_aui_option_0.setLabel( SitesDirectoryTag.SITES_TOP_LEVEL );
                        int _jspx_eval_aui_option_0 = _jspx_th_aui_option_0.doStartTag();
                        if (_jspx_th_aui_option_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_0);
                          return;
                        }
                        _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_0);
                        out.write("\n\t\t\t\t\t\t\t\t");
                        //  aui:option
                        com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_1 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                        _jspx_th_aui_option_1.setPageContext(_jspx_page_context);
                        _jspx_th_aui_option_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                        _jspx_th_aui_option_1.setLabel( SitesDirectoryTag.SITES_PARENT_LEVEL );
                        int _jspx_eval_aui_option_1 = _jspx_th_aui_option_1.doStartTag();
                        if (_jspx_th_aui_option_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_1);
                          return;
                        }
                        _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_1);
                        out.write("\n\t\t\t\t\t\t\t\t");
                        //  aui:option
                        com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_2 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                        _jspx_th_aui_option_2.setPageContext(_jspx_page_context);
                        _jspx_th_aui_option_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                        _jspx_th_aui_option_2.setLabel( SitesDirectoryTag.SITES_SIBLINGS );
                        int _jspx_eval_aui_option_2 = _jspx_th_aui_option_2.doStartTag();
                        if (_jspx_th_aui_option_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_2);
                          return;
                        }
                        _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_2);
                        out.write("\n\t\t\t\t\t\t\t\t");
                        //  aui:option
                        com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_3 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
                        _jspx_th_aui_option_3.setPageContext(_jspx_page_context);
                        _jspx_th_aui_option_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_0);
                        _jspx_th_aui_option_3.setLabel( SitesDirectoryTag.SITES_CHILDREN );
                        int _jspx_eval_aui_option_3 = _jspx_th_aui_option_3.doStartTag();
                        if (_jspx_th_aui_option_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_3);
                          return;
                        }
                        _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_3);
                        out.write("\n\t\t\t\t\t\t\t");
                        int evalDoAfterBody = _jspx_th_aui_select_0.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_aui_select_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                    }
                    if (_jspx_th_aui_select_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_select_value_name.reuse(_jspx_th_aui_select_0);
                      return;
                    }
                    _jspx_tagPool_aui_select_value_name.reuse(_jspx_th_aui_select_0);
                    out.write("\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_aui_row_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_1);
                    return;
                  }
                  _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_1);
                  out.write("\n\n\t\t\t\t\t\t");
                  //  aui:row
                  com.liferay.taglib.aui.RowTag _jspx_th_aui_row_2 = (com.liferay.taglib.aui.RowTag) _jspx_tagPool_aui_row.get(com.liferay.taglib.aui.RowTag.class);
                  _jspx_th_aui_row_2.setPageContext(_jspx_page_context);
                  _jspx_th_aui_row_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_fieldset_0);
                  int _jspx_eval_aui_row_2 = _jspx_th_aui_row_2.doStartTag();
                  if (_jspx_eval_aui_row_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t\t");
                    //  aui:select
                    com.liferay.taglib.aui.SelectTag _jspx_th_aui_select_1 = (com.liferay.taglib.aui.SelectTag) _jspx_tagPool_aui_select_value_name.get(com.liferay.taglib.aui.SelectTag.class);
                    _jspx_th_aui_select_1.setPageContext(_jspx_page_context);
                    _jspx_th_aui_select_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_2);
                    _jspx_th_aui_select_1.setName("preferences--displayStyle--");
                    _jspx_th_aui_select_1.setValue( sitesDirectoryDisplayContext.getDisplayStyle() );
                    int _jspx_eval_aui_select_1 = _jspx_th_aui_select_1.doStartTag();
                    if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_aui_select_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_aui_select_1.doInitBody();
                      }
                      do {
                        out.write("\n\t\t\t\t\t\t\t\t");
                        if (_jspx_meth_aui_option_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_1, _jspx_page_context))
                          return;
                        out.write("\n\t\t\t\t\t\t\t\t");
                        if (_jspx_meth_aui_option_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_1, _jspx_page_context))
                          return;
                        out.write("\n\t\t\t\t\t\t\t\t");
                        if (_jspx_meth_aui_option_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_1, _jspx_page_context))
                          return;
                        out.write("\n\t\t\t\t\t\t\t\t");
                        if (_jspx_meth_aui_option_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_select_1, _jspx_page_context))
                          return;
                        out.write("\n\t\t\t\t\t\t\t");
                        int evalDoAfterBody = _jspx_th_aui_select_1.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_aui_select_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                    }
                    if (_jspx_th_aui_select_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_select_value_name.reuse(_jspx_th_aui_select_1);
                      return;
                    }
                    _jspx_tagPool_aui_select_value_name.reuse(_jspx_th_aui_select_1);
                    out.write("\n\t\t\t\t\t\t");
                  }
                  if (_jspx_th_aui_row_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_2);
                    return;
                  }
                  _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_2);
                  out.write("\n\t\t\t\t\t");
                }
                if (_jspx_th_liferay$1frontend_fieldset_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1frontend_fieldset_cssClass.reuse(_jspx_th_liferay$1frontend_fieldset_0);
                  return;
                }
                _jspx_tagPool_liferay$1frontend_fieldset_cssClass.reuse(_jspx_th_liferay$1frontend_fieldset_0);
                out.write("\n\t\t\t\t");
              }
              if (_jspx_th_liferay$1frontend_fieldset$1group_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_0);
                return;
              }
              _jspx_tagPool_liferay$1frontend_fieldset$1group.reuse(_jspx_th_liferay$1frontend_fieldset$1group_0);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_aui_col_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_col_width.reuse(_jspx_th_aui_col_0);
              return;
            }
            _jspx_tagPool_aui_col_width.reuse(_jspx_th_aui_col_0);
            out.write("\n\n\t\t\t");
            //  aui:col
            com.liferay.taglib.aui.ColTag _jspx_th_aui_col_1 = (com.liferay.taglib.aui.ColTag) _jspx_tagPool_aui_col_width.get(com.liferay.taglib.aui.ColTag.class);
            _jspx_th_aui_col_1.setPageContext(_jspx_page_context);
            _jspx_th_aui_col_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_row_0);
            _jspx_th_aui_col_1.setWidth( 50 );
            int _jspx_eval_aui_col_1 = _jspx_th_aui_col_1.doStartTag();
            if (_jspx_eval_aui_col_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t\t");
              //  liferay-portlet:preview
              com.liferay.taglib.portletext.PreviewTag _jspx_th_liferay$1portlet_preview_0 = (com.liferay.taglib.portletext.PreviewTag) _jspx_tagPool_liferay$1portlet_preview_showBorders_queryString_portletName_nobody.get(com.liferay.taglib.portletext.PreviewTag.class);
              _jspx_th_liferay$1portlet_preview_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1portlet_preview_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_col_1);
              _jspx_th_liferay$1portlet_preview_0.setPortletName( portletResource );
              _jspx_th_liferay$1portlet_preview_0.setQueryString("struts_action=/sites_directory/view");
              _jspx_th_liferay$1portlet_preview_0.setShowBorders( true );
              int _jspx_eval_liferay$1portlet_preview_0 = _jspx_th_liferay$1portlet_preview_0.doStartTag();
              if (_jspx_th_liferay$1portlet_preview_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1portlet_preview_showBorders_queryString_portletName_nobody.reuse(_jspx_th_liferay$1portlet_preview_0);
                return;
              }
              _jspx_tagPool_liferay$1portlet_preview_showBorders_queryString_portletName_nobody.reuse(_jspx_th_liferay$1portlet_preview_0);
              out.write("\n\t\t\t");
            }
            if (_jspx_th_aui_col_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_col_width.reuse(_jspx_th_aui_col_1);
              return;
            }
            _jspx_tagPool_aui_col_width.reuse(_jspx_th_aui_col_1);
            out.write("\n\t\t");
          }
          if (_jspx_th_aui_row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_0);
            return;
          }
          _jspx_tagPool_aui_row.reuse(_jspx_th_aui_row_0);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_liferay$1frontend_edit$1form$1body_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1frontend_edit$1form$1body.reuse(_jspx_th_liferay$1frontend_edit$1form$1body_0);
          return;
        }
        _jspx_tagPool_liferay$1frontend_edit$1form$1body.reuse(_jspx_th_liferay$1frontend_edit$1form$1body_0);
        out.write("\n\n\t");
        if (_jspx_meth_liferay$1frontend_edit$1form$1footer_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_edit$1form_0, _jspx_page_context))
          return;
        out.write('\n');
      }
      if (_jspx_th_liferay$1frontend_edit$1form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1frontend_edit$1form_name_method_fluid_action.reuse(_jspx_th_liferay$1frontend_edit$1form_0);
        return;
      }
      _jspx_tagPool_liferay$1frontend_edit$1form_name_method_fluid_action.reuse(_jspx_th_liferay$1frontend_edit$1form_0);
      out.write('\n');
      out.write('\n');
      //  aui:script
      com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_sandbox.get(com.liferay.taglib.aui.ScriptTag.class);
      _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
      _jspx_th_aui_script_0.setParent(null);
      _jspx_th_aui_script_0.setSandbox( true );
      int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
      if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_aui_script_0.doInitBody();
        }
        do {
          out.write("\n\tvar form = $('#");
          if (_jspx_meth_portlet_namespace_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_script_0, _jspx_page_context))
            return;
          out.write("fm');\n\n\tvar selectDisplayStyle = form.fm('displayStyle');\n\tvar selectSites = form.fm('sites');\n\n\tvar curPortletBoundaryId = '#p_p_id_");
          out.print( HtmlUtil.escapeJS(portletResource) );
          out.write("_';\n\n\tform.on(\n\t\t'change',\n\t\t'select',\n\t\tfunction() {\n\t\t\tvar data = Liferay.Util.ns(\n\t\t\t\t'_");
          out.print( HtmlUtil.escapeJS(portletResource) );
          out.write("_',\n\t\t\t\t{\n\t\t\t\t\tdisplayStyle: selectDisplayStyle.val(),\n\t\t\t\t\tsites: selectSites.val()\n\t\t\t\t}\n\t\t\t);\n\n\t\t\tLiferay.Portlet.refresh(curPortletBoundaryId, data);\n\t\t}\n\t);\n");
          int evalDoAfterBody = _jspx_th_aui_script_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_aui_script_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_script_sandbox.reuse(_jspx_th_aui_script_0);
        return;
      }
      _jspx_tagPool_aui_script_sandbox.reuse(_jspx_th_aui_script_0);
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

  private boolean _jspx_meth_aui_option_4(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_4 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_4.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
    _jspx_th_aui_option_4.setLabel(new String("icon"));
    int _jspx_eval_aui_option_4 = _jspx_th_aui_option_4.doStartTag();
    if (_jspx_th_aui_option_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_4);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_4);
    return false;
  }

  private boolean _jspx_meth_aui_option_5(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_5 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_5.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
    _jspx_th_aui_option_5.setLabel(new String("descriptive"));
    int _jspx_eval_aui_option_5 = _jspx_th_aui_option_5.doStartTag();
    if (_jspx_th_aui_option_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_5);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_5);
    return false;
  }

  private boolean _jspx_meth_aui_option_6(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_6 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_6.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
    _jspx_th_aui_option_6.setLabel(new String("list"));
    int _jspx_eval_aui_option_6 = _jspx_th_aui_option_6.doStartTag();
    if (_jspx_th_aui_option_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_6);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_6);
    return false;
  }

  private boolean _jspx_meth_aui_option_7(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_select_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:option
    com.liferay.taglib.aui.OptionTag _jspx_th_aui_option_7 = (com.liferay.taglib.aui.OptionTag) _jspx_tagPool_aui_option_label_nobody.get(com.liferay.taglib.aui.OptionTag.class);
    _jspx_th_aui_option_7.setPageContext(_jspx_page_context);
    _jspx_th_aui_option_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_select_1);
    _jspx_th_aui_option_7.setLabel(new String("list-hierarchy"));
    int _jspx_eval_aui_option_7 = _jspx_th_aui_option_7.doStartTag();
    if (_jspx_th_aui_option_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_7);
      return true;
    }
    _jspx_tagPool_aui_option_label_nobody.reuse(_jspx_th_aui_option_7);
    return false;
  }

  private boolean _jspx_meth_liferay$1frontend_edit$1form$1footer_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_edit$1form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-frontend:edit-form-footer
    com.liferay.frontend.taglib.servlet.taglib.EditFormFooterTag _jspx_th_liferay$1frontend_edit$1form$1footer_0 = (com.liferay.frontend.taglib.servlet.taglib.EditFormFooterTag) _jspx_tagPool_liferay$1frontend_edit$1form$1footer.get(com.liferay.frontend.taglib.servlet.taglib.EditFormFooterTag.class);
    _jspx_th_liferay$1frontend_edit$1form$1footer_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1frontend_edit$1form$1footer_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form_0);
    int _jspx_eval_liferay$1frontend_edit$1form$1footer_0 = _jspx_th_liferay$1frontend_edit$1form$1footer_0.doStartTag();
    if (_jspx_eval_liferay$1frontend_edit$1form$1footer_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t");
      if (_jspx_meth_aui_button_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_edit$1form$1footer_0, _jspx_page_context))
        return true;
      out.write("\n\n\t\t");
      if (_jspx_meth_aui_button_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1frontend_edit$1form$1footer_0, _jspx_page_context))
        return true;
      out.write('\n');
      out.write('	');
    }
    if (_jspx_th_liferay$1frontend_edit$1form$1footer_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1frontend_edit$1form$1footer.reuse(_jspx_th_liferay$1frontend_edit$1form$1footer_0);
      return true;
    }
    _jspx_tagPool_liferay$1frontend_edit$1form$1footer.reuse(_jspx_th_liferay$1frontend_edit$1form$1footer_0);
    return false;
  }

  private boolean _jspx_meth_aui_button_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_edit$1form$1footer_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_0 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_type_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form$1footer_0);
    _jspx_th_aui_button_0.setType("submit");
    int _jspx_eval_aui_button_0 = _jspx_th_aui_button_0.doStartTag();
    if (_jspx_th_aui_button_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_0);
      return true;
    }
    _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_0);
    return false;
  }

  private boolean _jspx_meth_aui_button_1(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1frontend_edit$1form$1footer_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_1 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_type_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_1.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_edit$1form$1footer_0);
    _jspx_th_aui_button_1.setType("cancel");
    int _jspx_eval_aui_button_1 = _jspx_th_aui_button_1.doStartTag();
    if (_jspx_th_aui_button_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_1);
      return true;
    }
    _jspx_tagPool_aui_button_type_nobody.reuse(_jspx_th_aui_button_1);
    return false;
  }

  private boolean _jspx_meth_portlet_namespace_0(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_script_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:namespace
    com.liferay.taglib.portlet.NamespaceTag _jspx_th_portlet_namespace_0 = (com.liferay.taglib.portlet.NamespaceTag) _jspx_tagPool_portlet_namespace_nobody.get(com.liferay.taglib.portlet.NamespaceTag.class);
    _jspx_th_portlet_namespace_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_namespace_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_script_0);
    int _jspx_eval_portlet_namespace_0 = _jspx_th_portlet_namespace_0.doStartTag();
    if (_jspx_th_portlet_namespace_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
      return true;
    }
    _jspx_tagPool_portlet_namespace_nobody.reuse(_jspx_th_portlet_namespace_0);
    return false;
  }
}