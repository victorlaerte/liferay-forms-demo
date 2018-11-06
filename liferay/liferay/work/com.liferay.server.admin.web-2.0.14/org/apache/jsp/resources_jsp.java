package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.petra.log4j.Levels;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.convert.ConvertProcess;
import com.liferay.portal.convert.ConvertProcessUtil;
import com.liferay.portal.convert.documentlibrary.FileSystemStoreRootDirException;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.image.ImageMagickUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.model.impl.*;
import com.liferay.portal.kernel.scripting.ScriptingUtil;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OSDetector;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xuggler.XugglerInstallException;
import com.liferay.portal.kernel.xuggler.XugglerUtil;
import com.liferay.portal.model.impl.*;
import com.liferay.portal.service.*;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.ShutdownUtil;
import com.liferay.server.admin.web.internal.display.context.ServerDisplayContext;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public final class resources_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_resourceURL_var_id;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_cssClass_collapsible;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1help_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_panel$1container_persistState_id_extended;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_resourceURL_var_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_cssClass_collapsible = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1help_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_panel$1container_persistState_id_extended = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_portlet_resourceURL_var_id.release();
    _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_cssClass_collapsible.release();
    _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody.release();
    _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_panel$1container_persistState_id_extended.release();
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
      out.write("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
      //  liferay-frontend:defineObjects
      com.liferay.frontend.taglib.servlet.taglib.DefineObjectsTag _jspx_th_liferay$1frontend_defineObjects_0 = (com.liferay.frontend.taglib.servlet.taglib.DefineObjectsTag) _jspx_tagPool_liferay$1frontend_defineObjects_nobody.get(com.liferay.frontend.taglib.servlet.taglib.DefineObjectsTag.class);
      _jspx_th_liferay$1frontend_defineObjects_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1frontend_defineObjects_0.setParent(null);
      int _jspx_eval_liferay$1frontend_defineObjects_0 = _jspx_th_liferay$1frontend_defineObjects_0.doStartTag();
      if (_jspx_th_liferay$1frontend_defineObjects_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1frontend_defineObjects_nobody.reuse(_jspx_th_liferay$1frontend_defineObjects_0);
        return;
      }
      _jspx_tagPool_liferay$1frontend_defineObjects_nobody.reuse(_jspx_th_liferay$1frontend_defineObjects_0);
      java.lang.String currentURL = null;
      javax.portlet.PortletURL currentURLObj = null;
      java.util.ResourceBundle resourceBundle = null;
      javax.portlet.WindowState windowState = null;
      currentURL = (java.lang.String) _jspx_page_context.findAttribute("currentURL");
      currentURLObj = (javax.portlet.PortletURL) _jspx_page_context.findAttribute("currentURLObj");
      resourceBundle = (java.util.ResourceBundle) _jspx_page_context.findAttribute("resourceBundle");
      windowState = (javax.portlet.WindowState) _jspx_page_context.findAttribute("windowState");
      out.write('\n');
      out.write('\n');
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

PortletMode portletMode = liferayPortletRequest.getPortletMode();

      out.write('\n');
      out.write('\n');

String tabs1 = ParamUtil.getString(request, "tabs1", "resources");
String tabs2 = ParamUtil.getString(request, "tabs2");

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

long uptimeDiff = System.currentTimeMillis() - PortalUtil.getUptime().getTime();
long days = uptimeDiff / Time.DAY;
long hours = (uptimeDiff / Time.HOUR) % 24;
long minutes = (uptimeDiff / Time.MINUTE) % 60;
long seconds = (uptimeDiff / Time.SECOND) % 60;

Runtime runtime = Runtime.getRuntime();

long totalMemory = runtime.totalMemory();
long usedMemory = totalMemory - runtime.freeMemory();

      out.write('\n');
      out.write('\n');
      //  liferay-ui:panel-container
      com.liferay.taglib.ui.PanelContainerTag _jspx_th_liferay$1ui_panel$1container_0 = (com.liferay.taglib.ui.PanelContainerTag) _jspx_tagPool_liferay$1ui_panel$1container_persistState_id_extended.get(com.liferay.taglib.ui.PanelContainerTag.class);
      _jspx_th_liferay$1ui_panel$1container_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1ui_panel$1container_0.setParent(null);
      _jspx_th_liferay$1ui_panel$1container_0.setExtended( true );
      _jspx_th_liferay$1ui_panel$1container_0.setId("adminServerAdministrationActionsPanelContainer");
      _jspx_th_liferay$1ui_panel$1container_0.setPersistState( true );
      int _jspx_eval_liferay$1ui_panel$1container_0 = _jspx_th_liferay$1ui_panel$1container_0.doStartTag();
      if (_jspx_eval_liferay$1ui_panel$1container_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_liferay$1ui_panel$1container_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_liferay$1ui_panel$1container_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_liferay$1ui_panel$1container_0.doInitBody();
        }
        do {
          out.write("\n\t<div class=\"panel panel-default server-admin-tabs\" id=\"adminServerInformationPanel\">\n\t\t<div class=\"panel-body\">\n\t\t\t<div class=\"alert alert-info\">\n\t\t\t\t<strong>");
          if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel$1container_0, _jspx_page_context))
            return;
          out.write("</strong>: ");
          out.print( ReleaseInfo.getReleaseInfo() );
          out.write("\n\t\t\t\t<strong>");
          if (_jspx_meth_liferay$1ui_message_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel$1container_0, _jspx_page_context))
            return;
          out.write("</strong>:\n\n\t\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_0.setPageContext(_jspx_page_context);
          _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
          _jspx_th_c_if_0.setTest( days > 0 );
          int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
          if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t");
            out.print( days );
            out.write(' ');
            out.print( LanguageUtil.get(request, ((days > 1) ? "days" : "day")) );
            out.write(",\n\t\t\t\t");
          }
          if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
          out.write("\n\n\t\t\t\t");

				NumberFormat timeNumberFormat = NumberFormat.getInstance();

				timeNumberFormat.setMaximumIntegerDigits(2);
				timeNumberFormat.setMinimumIntegerDigits(2);
				
          out.write("\n\n\t\t\t\t");
          out.print( timeNumberFormat.format(hours) );
          out.write(':');
          out.print( timeNumberFormat.format(minutes) );
          out.write(':');
          out.print( timeNumberFormat.format(seconds) );
          out.write("\n\t\t\t</div>\n\n\t\t\t<div class=\"meter-wrapper text-center\">\n\t\t\t\t");
          //  portlet:resourceURL
          com.liferay.taglib.portlet.ResourceURLTag _jspx_th_portlet_resourceURL_0 = (com.liferay.taglib.portlet.ResourceURLTag) _jspx_tagPool_portlet_resourceURL_var_id.get(com.liferay.taglib.portlet.ResourceURLTag.class);
          _jspx_th_portlet_resourceURL_0.setPageContext(_jspx_page_context);
          _jspx_th_portlet_resourceURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
          _jspx_th_portlet_resourceURL_0.setId("/server_admin/view_chart");
          _jspx_th_portlet_resourceURL_0.setVar("totalMemoryChartURL");
          int _jspx_eval_portlet_resourceURL_0 = _jspx_th_portlet_resourceURL_0.doStartTag();
          if (_jspx_eval_portlet_resourceURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t");
            if (_jspx_meth_portlet_param_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_resourceURL_0, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_resourceURL_0);
            _jspx_th_portlet_param_1.setName("totalMemory");
            _jspx_th_portlet_param_1.setValue( String.valueOf(totalMemory) );
            int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
            if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
            out.write("\n\t\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_resourceURL_0);
            _jspx_th_portlet_param_2.setName("usedMemory");
            _jspx_th_portlet_param_2.setValue( String.valueOf(usedMemory) );
            int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
            if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
            out.write("\n\t\t\t\t");
          }
          if (_jspx_th_portlet_resourceURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_portlet_resourceURL_var_id.reuse(_jspx_th_portlet_resourceURL_0);
            return;
          }
          _jspx_tagPool_portlet_resourceURL_var_id.reuse(_jspx_th_portlet_resourceURL_0);
          java.lang.String totalMemoryChartURL = null;
          totalMemoryChartURL = (java.lang.String) _jspx_page_context.findAttribute("totalMemoryChartURL");
          out.write("\n\n\t\t\t\t<img alt=\"");
          //  liferay-ui:message
          com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody.get(com.liferay.taglib.ui.MessageTag.class);
          _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
          _jspx_th_liferay$1ui_message_2.setEscapeAttribute( true );
          _jspx_th_liferay$1ui_message_2.setKey("memory-used-vs-total-memory");
          int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
          if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody.reuse(_jspx_th_liferay$1ui_message_2);
            return;
          }
          _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody.reuse(_jspx_th_liferay$1ui_message_2);
          out.write("\" src=\"");
          out.print( totalMemoryChartURL );
          out.write("\" />\n\n\t\t\t\t");
          //  portlet:resourceURL
          com.liferay.taglib.portlet.ResourceURLTag _jspx_th_portlet_resourceURL_1 = (com.liferay.taglib.portlet.ResourceURLTag) _jspx_tagPool_portlet_resourceURL_var_id.get(com.liferay.taglib.portlet.ResourceURLTag.class);
          _jspx_th_portlet_resourceURL_1.setPageContext(_jspx_page_context);
          _jspx_th_portlet_resourceURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
          _jspx_th_portlet_resourceURL_1.setId("/server_admin/view_chart");
          _jspx_th_portlet_resourceURL_1.setVar("maxMemoryChartURL");
          int _jspx_eval_portlet_resourceURL_1 = _jspx_th_portlet_resourceURL_1.doStartTag();
          if (_jspx_eval_portlet_resourceURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t\t");
            if (_jspx_meth_portlet_param_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_portlet_resourceURL_1, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_4 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_4.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_resourceURL_1);
            _jspx_th_portlet_param_4.setName("maxMemory");
            _jspx_th_portlet_param_4.setValue( String.valueOf(runtime.maxMemory()) );
            int _jspx_eval_portlet_param_4 = _jspx_th_portlet_param_4.doStartTag();
            if (_jspx_th_portlet_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
            out.write("\n\t\t\t\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_5 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_5.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_resourceURL_1);
            _jspx_th_portlet_param_5.setName("usedMemory");
            _jspx_th_portlet_param_5.setValue( String.valueOf(usedMemory) );
            int _jspx_eval_portlet_param_5 = _jspx_th_portlet_param_5.doStartTag();
            if (_jspx_th_portlet_param_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
            out.write("\n\t\t\t\t");
          }
          if (_jspx_th_portlet_resourceURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_portlet_resourceURL_var_id.reuse(_jspx_th_portlet_resourceURL_1);
            return;
          }
          _jspx_tagPool_portlet_resourceURL_var_id.reuse(_jspx_th_portlet_resourceURL_1);
          java.lang.String maxMemoryChartURL = null;
          maxMemoryChartURL = (java.lang.String) _jspx_page_context.findAttribute("maxMemoryChartURL");
          out.write("\n\n\t\t\t\t<img alt=\"");
          //  liferay-ui:message
          com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody.get(com.liferay.taglib.ui.MessageTag.class);
          _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
          _jspx_th_liferay$1ui_message_3.setEscapeAttribute( true );
          _jspx_th_liferay$1ui_message_3.setKey("memory-used-vs-max-memory");
          int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
          if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody.reuse(_jspx_th_liferay$1ui_message_3);
            return;
          }
          _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody.reuse(_jspx_th_liferay$1ui_message_3);
          out.write("\" src=\"");
          out.print( maxMemoryChartURL );
          out.write("\" />\n\t\t\t</div>\n\n\t\t\t<br />\n\n\t\t\t");

			NumberFormat basicNumberFormat = NumberFormat.getInstance(locale);
			
          out.write("\n\n\t\t\t<table class=\"lfr-table memory-status-table\">\n\t\t\t\t<tr>\n\t\t\t\t\t<td>\n\t\t\t\t\t\t<h4 class=\"pull-right\">");
          if (_jspx_meth_liferay$1ui_message_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel$1container_0, _jspx_page_context))
            return;
          out.write("</h4>\n\t\t\t\t\t</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\t<span class=\"text-muted\">");
          out.print( basicNumberFormat.format(usedMemory) );
          out.write(' ');
          if (_jspx_meth_liferay$1ui_message_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel$1container_0, _jspx_page_context))
            return;
          out.write("</span>\n\t\t\t\t\t</td>\n\t\t\t\t</tr>\n\t\t\t\t<tr>\n\t\t\t\t\t<td>\n\t\t\t\t\t\t<h4 class=\"pull-right\">");
          if (_jspx_meth_liferay$1ui_message_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel$1container_0, _jspx_page_context))
            return;
          out.write("</h4>\n\t\t\t\t\t</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\t<span class=\"text-muted\">");
          out.print( basicNumberFormat.format(runtime.totalMemory()) );
          out.write(' ');
          if (_jspx_meth_liferay$1ui_message_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel$1container_0, _jspx_page_context))
            return;
          out.write("</span>\n\t\t\t\t\t</td>\n\t\t\t\t</tr>\n\t\t\t\t<tr>\n\t\t\t\t\t<td>\n\t\t\t\t\t\t<h4 class=\"pull-right\">");
          if (_jspx_meth_liferay$1ui_message_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel$1container_0, _jspx_page_context))
            return;
          out.write("</h4>\n\t\t\t\t\t</td>\n\t\t\t\t\t<td>\n\t\t\t\t\t\t<span class=\"text-muted\">");
          out.print( basicNumberFormat.format(runtime.maxMemory()) );
          out.write(' ');
          if (_jspx_meth_liferay$1ui_message_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel$1container_0, _jspx_page_context))
            return;
          out.write("</span>\n\t\t\t\t\t</td>\n\t\t\t\t</tr>\n\t\t\t</table>\n\n\t\t\t<br />\n\t\t</div>\n\t</div>\n\n\t");
          //  liferay-ui:panel
          com.liferay.taglib.ui.PanelTag _jspx_th_liferay$1ui_panel_0 = (com.liferay.taglib.ui.PanelTag) _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_cssClass_collapsible.get(com.liferay.taglib.ui.PanelTag.class);
          _jspx_th_liferay$1ui_panel_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_panel_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
          _jspx_th_liferay$1ui_panel_0.setCollapsible( true );
          _jspx_th_liferay$1ui_panel_0.setCssClass("server-admin-actions-panel");
          _jspx_th_liferay$1ui_panel_0.setExtended( true );
          _jspx_th_liferay$1ui_panel_0.setId("adminServerAdministrationSystemActionsPanel");
          _jspx_th_liferay$1ui_panel_0.setMarkupView("lexicon");
          _jspx_th_liferay$1ui_panel_0.setPersistState( true );
          _jspx_th_liferay$1ui_panel_0.setTitle("system-actions");
          int _jspx_eval_liferay$1ui_panel_0 = _jspx_th_liferay$1ui_panel_0.doStartTag();
          if (_jspx_eval_liferay$1ui_panel_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t<ul class=\"list-group system-action-group\">\n\t\t\t<li class=\"clearfix list-group-item\">\n\t\t\t\t<div class=\"pull-left\">\n\t\t\t\t\t<h5>");
            if (_jspx_meth_liferay$1ui_message_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_0, _jspx_page_context))
              return;
            out.write("</h5>\n\t\t\t\t</div>\n\n\t\t\t\t<div class=\"pull-right\">\n\t\t\t\t\t");
            if (_jspx_meth_aui_button_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_0, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t</div>\n\t\t\t</li>\n\t\t\t<li class=\"clearfix list-group-item\">\n\t\t\t\t<div class=\"pull-left\">\n\t\t\t\t\t<h5>");
            if (_jspx_meth_liferay$1ui_message_11((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_0, _jspx_page_context))
              return;
            out.write("</h5>\n\t\t\t\t</div>\n\n\t\t\t\t<div class=\"pull-right\">\n\t\t\t\t\t");
            if (_jspx_meth_aui_button_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_0, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t</div>\n\t\t\t</li>\n\t\t</ul>\n\t");
          }
          if (_jspx_th_liferay$1ui_panel_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_cssClass_collapsible.reuse(_jspx_th_liferay$1ui_panel_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_cssClass_collapsible.reuse(_jspx_th_liferay$1ui_panel_0);
          out.write("\n\n\t");
          //  liferay-ui:panel
          com.liferay.taglib.ui.PanelTag _jspx_th_liferay$1ui_panel_1 = (com.liferay.taglib.ui.PanelTag) _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_cssClass_collapsible.get(com.liferay.taglib.ui.PanelTag.class);
          _jspx_th_liferay$1ui_panel_1.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_panel_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
          _jspx_th_liferay$1ui_panel_1.setCollapsible( true );
          _jspx_th_liferay$1ui_panel_1.setCssClass("server-admin-actions-panel");
          _jspx_th_liferay$1ui_panel_1.setExtended( true );
          _jspx_th_liferay$1ui_panel_1.setId("adminServerAdministrationCacheActionsPanel");
          _jspx_th_liferay$1ui_panel_1.setMarkupView("lexicon");
          _jspx_th_liferay$1ui_panel_1.setPersistState( true );
          _jspx_th_liferay$1ui_panel_1.setTitle("cache-actions");
          int _jspx_eval_liferay$1ui_panel_1 = _jspx_th_liferay$1ui_panel_1.doStartTag();
          if (_jspx_eval_liferay$1ui_panel_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t<ul class=\"list-group system-action-group\">\n\t\t\t<li class=\"clearfix list-group-item\">\n\t\t\t\t<div class=\"pull-left\">\n\t\t\t\t\t<h5>");
            if (_jspx_meth_liferay$1ui_message_12((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_1, _jspx_page_context))
              return;
            out.write("</h5>\n\t\t\t\t</div>\n\n\t\t\t\t<div class=\"pull-right\">\n\t\t\t\t\t");
            if (_jspx_meth_aui_button_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_1, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t</div>\n\t\t\t</li>\n\t\t\t<li class=\"clearfix list-group-item\">\n\t\t\t\t<div class=\"pull-left\">\n\t\t\t\t\t<h5>");
            if (_jspx_meth_liferay$1ui_message_13((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_1, _jspx_page_context))
              return;
            out.write("</h5>\n\t\t\t\t</div>\n\n\t\t\t\t<div class=\"pull-right\">\n\t\t\t\t\t");
            if (_jspx_meth_aui_button_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_1, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t</div>\n\t\t\t</li>\n\t\t\t<li class=\"clearfix list-group-item\">\n\t\t\t\t<div class=\"pull-left\">\n\t\t\t\t\t<h5>");
            if (_jspx_meth_liferay$1ui_message_14((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_1, _jspx_page_context))
              return;
            out.write("</h5>\n\t\t\t\t</div>\n\n\t\t\t\t<div class=\"pull-right\">\n\t\t\t\t\t");
            if (_jspx_meth_aui_button_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_1, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t</div>\n\t\t\t</li>\n\t\t\t<li class=\"clearfix list-group-item\">\n\t\t\t\t<div class=\"pull-left\">\n\t\t\t\t\t<h5>");
            if (_jspx_meth_liferay$1ui_message_15((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_1, _jspx_page_context))
              return;
            out.write("</h5>\n\t\t\t\t</div>\n\n\t\t\t\t<div class=\"pull-right\">\n\t\t\t\t\t");
            if (_jspx_meth_aui_button_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_1, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t</div>\n\t\t\t</li>\n\t\t</ul>\n\t");
          }
          if (_jspx_th_liferay$1ui_panel_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_cssClass_collapsible.reuse(_jspx_th_liferay$1ui_panel_1);
            return;
          }
          _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_cssClass_collapsible.reuse(_jspx_th_liferay$1ui_panel_1);
          out.write("\n\n\t");
          //  liferay-ui:panel
          com.liferay.taglib.ui.PanelTag _jspx_th_liferay$1ui_panel_2 = (com.liferay.taglib.ui.PanelTag) _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_cssClass_collapsible.get(com.liferay.taglib.ui.PanelTag.class);
          _jspx_th_liferay$1ui_panel_2.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_panel_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
          _jspx_th_liferay$1ui_panel_2.setCollapsible( true );
          _jspx_th_liferay$1ui_panel_2.setCssClass("server-admin-actions-panel");
          _jspx_th_liferay$1ui_panel_2.setExtended( true );
          _jspx_th_liferay$1ui_panel_2.setId("adminServerAdministrationVerificationActionsPanel");
          _jspx_th_liferay$1ui_panel_2.setMarkupView("lexicon");
          _jspx_th_liferay$1ui_panel_2.setPersistState( true );
          _jspx_th_liferay$1ui_panel_2.setTitle("verification-actions");
          int _jspx_eval_liferay$1ui_panel_2 = _jspx_th_liferay$1ui_panel_2.doStartTag();
          if (_jspx_eval_liferay$1ui_panel_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t<ul class=\"list-group system-action-group\">\n\t\t\t<li class=\"clearfix list-group-item\">\n\t\t\t\t<div class=\"pull-left\">\n\t\t\t\t\t<h5>");
            if (_jspx_meth_liferay$1ui_message_16((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_2, _jspx_page_context))
              return;
            out.write("</h5>\n\t\t\t\t</div>\n\n\t\t\t\t<div class=\"pull-right\">\n\t\t\t\t\t");
            if (_jspx_meth_aui_button_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_2, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t</div>\n\t\t\t</li>\n\t\t\t<li class=\"clearfix list-group-item\">\n\t\t\t\t<div class=\"pull-left\">\n\t\t\t\t\t<h5>");
            if (_jspx_meth_liferay$1ui_message_17((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_2, _jspx_page_context))
              return;
            out.write("</h5>\n\t\t\t\t</div>\n\n\t\t\t\t<div class=\"pull-right\">\n\t\t\t\t\t");
            if (_jspx_meth_aui_button_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_2, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t</div>\n\t\t\t</li>\n\t\t</ul>\n\t");
          }
          if (_jspx_th_liferay$1ui_panel_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_cssClass_collapsible.reuse(_jspx_th_liferay$1ui_panel_2);
            return;
          }
          _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_cssClass_collapsible.reuse(_jspx_th_liferay$1ui_panel_2);
          out.write("\n\n\t");
          //  liferay-ui:panel
          com.liferay.taglib.ui.PanelTag _jspx_th_liferay$1ui_panel_3 = (com.liferay.taglib.ui.PanelTag) _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_cssClass_collapsible.get(com.liferay.taglib.ui.PanelTag.class);
          _jspx_th_liferay$1ui_panel_3.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_panel_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
          _jspx_th_liferay$1ui_panel_3.setCollapsible( true );
          _jspx_th_liferay$1ui_panel_3.setCssClass("server-admin-actions-panel");
          _jspx_th_liferay$1ui_panel_3.setExtended( true );
          _jspx_th_liferay$1ui_panel_3.setId("adminServerAdministrationCleanUpActionsPanel");
          _jspx_th_liferay$1ui_panel_3.setMarkupView("lexicon");
          _jspx_th_liferay$1ui_panel_3.setPersistState( true );
          _jspx_th_liferay$1ui_panel_3.setTitle("clean-up-actions");
          int _jspx_eval_liferay$1ui_panel_3 = _jspx_th_liferay$1ui_panel_3.doStartTag();
          if (_jspx_eval_liferay$1ui_panel_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t<ul class=\"list-group system-action-group\">\n\t\t\t<li class=\"clearfix list-group-item\">\n\t\t\t\t<div class=\"pull-left\">\n\t\t\t\t\t<h5>");
            if (_jspx_meth_liferay$1ui_message_18((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_3, _jspx_page_context))
              return;
            out.write("</h5>\n\t\t\t\t</div>\n\n\t\t\t\t<div class=\"pull-right\">\n\t\t\t\t\t");
            if (_jspx_meth_aui_button_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_3, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t</div>\n\t\t\t</li>\n\t\t\t<li class=\"clearfix list-group-item\">\n\t\t\t\t<div class=\"pull-left\">\n\t\t\t\t\t<h5>");
            if (_jspx_meth_liferay$1ui_message_19((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_3, _jspx_page_context))
              return;
            out.write(' ');
            if (_jspx_meth_liferay$1ui_icon$1help_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_3, _jspx_page_context))
              return;
            out.write("</h5>\n\t\t\t\t</div>\n\n\t\t\t\t<div class=\"pull-right\">\n\t\t\t\t\t");
            if (_jspx_meth_aui_button_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_3, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t</div>\n\t\t\t</li>\n\t\t\t<li class=\"clearfix list-group-item\">\n\t\t\t\t<div class=\"pull-left\">\n\t\t\t\t\t<h5>");
            if (_jspx_meth_liferay$1ui_message_20((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_3, _jspx_page_context))
              return;
            out.write(' ');
            if (_jspx_meth_liferay$1ui_icon$1help_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_3, _jspx_page_context))
              return;
            out.write("</h5>\n\t\t\t\t</div>\n\n\t\t\t\t<div class=\"pull-right\">\n\t\t\t\t\t");
            if (_jspx_meth_aui_button_10((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_panel_3, _jspx_page_context))
              return;
            out.write("\n\t\t\t\t</div>\n\t\t\t</li>\n\t\t</ul>\n\t");
          }
          if (_jspx_th_liferay$1ui_panel_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_cssClass_collapsible.reuse(_jspx_th_liferay$1ui_panel_3);
            return;
          }
          _jspx_tagPool_liferay$1ui_panel_title_persistState_markupView_id_extended_cssClass_collapsible.reuse(_jspx_th_liferay$1ui_panel_3);
          out.write('\n');
          int evalDoAfterBody = _jspx_th_liferay$1ui_panel$1container_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_liferay$1ui_panel$1container_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_liferay$1ui_panel$1container_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1ui_panel$1container_persistState_id_extended.reuse(_jspx_th_liferay$1ui_panel$1container_0);
        return;
      }
      _jspx_tagPool_liferay$1ui_panel$1container_persistState_id_extended.reuse(_jspx_th_liferay$1ui_panel$1container_0);
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

  private boolean _jspx_meth_liferay$1ui_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel$1container_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
    _jspx_th_liferay$1ui_message_0.setKey("info");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_1(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel$1container_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
    _jspx_th_liferay$1ui_message_1.setKey("uptime");
    int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
    if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
    return false;
  }

  private boolean _jspx_meth_portlet_param_0(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_resourceURL_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_resourceURL_0);
    _jspx_th_portlet_param_0.setName("type");
    _jspx_th_portlet_param_0.setValue("total");
    int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
    if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
    return false;
  }

  private boolean _jspx_meth_portlet_param_3(javax.servlet.jsp.tagext.JspTag _jspx_th_portlet_resourceURL_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  portlet:param
    com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
    _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
    _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_resourceURL_1);
    _jspx_th_portlet_param_3.setName("type");
    _jspx_th_portlet_param_3.setValue("max");
    int _jspx_eval_portlet_param_3 = _jspx_th_portlet_param_3.doStartTag();
    if (_jspx_th_portlet_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
      return true;
    }
    _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_4(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel$1container_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_4 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_4.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
    _jspx_th_liferay$1ui_message_4.setKey("used-memory");
    int _jspx_eval_liferay$1ui_message_4 = _jspx_th_liferay$1ui_message_4.doStartTag();
    if (_jspx_th_liferay$1ui_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_4);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_5(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel$1container_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_5 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_5.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
    _jspx_th_liferay$1ui_message_5.setKey("bytes");
    int _jspx_eval_liferay$1ui_message_5 = _jspx_th_liferay$1ui_message_5.doStartTag();
    if (_jspx_th_liferay$1ui_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_6(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel$1container_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_6 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_6.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
    _jspx_th_liferay$1ui_message_6.setKey("total-memory");
    int _jspx_eval_liferay$1ui_message_6 = _jspx_th_liferay$1ui_message_6.doStartTag();
    if (_jspx_th_liferay$1ui_message_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_6);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_7(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel$1container_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_7 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_7.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
    _jspx_th_liferay$1ui_message_7.setKey("bytes");
    int _jspx_eval_liferay$1ui_message_7 = _jspx_th_liferay$1ui_message_7.doStartTag();
    if (_jspx_th_liferay$1ui_message_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_8(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel$1container_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_8 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_8.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
    _jspx_th_liferay$1ui_message_8.setKey("maximum-memory");
    int _jspx_eval_liferay$1ui_message_8 = _jspx_th_liferay$1ui_message_8.doStartTag();
    if (_jspx_th_liferay$1ui_message_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_8);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_8);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_9(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel$1container_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_9 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_9.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel$1container_0);
    _jspx_th_liferay$1ui_message_9.setKey("bytes");
    int _jspx_eval_liferay$1ui_message_9 = _jspx_th_liferay$1ui_message_9.doStartTag();
    if (_jspx_th_liferay$1ui_message_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_9);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_9);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_10(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_10 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_10.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_0);
    _jspx_th_liferay$1ui_message_10.setKey("run-the-garbage-collector-to-free-up-memory");
    int _jspx_eval_liferay$1ui_message_10 = _jspx_th_liferay$1ui_message_10.doStartTag();
    if (_jspx_th_liferay$1ui_message_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_10);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_10);
    return false;
  }

  private boolean _jspx_meth_aui_button_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_0 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_0);
    _jspx_th_aui_button_0.setCssClass("save-server-button");
    _jspx_th_aui_button_0.setDynamicAttribute(null, "data-cmd", new String("gc"));
    _jspx_th_aui_button_0.setValue("execute");
    int _jspx_eval_aui_button_0 = _jspx_th_aui_button_0.doStartTag();
    if (_jspx_th_aui_button_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_0);
      return true;
    }
    _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_11(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_11 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_11.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_0);
    _jspx_th_liferay$1ui_message_11.setKey("generate-thread-dump");
    int _jspx_eval_liferay$1ui_message_11 = _jspx_th_liferay$1ui_message_11.doStartTag();
    if (_jspx_th_liferay$1ui_message_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_11);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_11);
    return false;
  }

  private boolean _jspx_meth_aui_button_1(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_1 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_1.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_0);
    _jspx_th_aui_button_1.setCssClass("save-server-button");
    _jspx_th_aui_button_1.setDynamicAttribute(null, "data-cmd", new String("threadDump"));
    _jspx_th_aui_button_1.setValue("execute");
    int _jspx_eval_aui_button_1 = _jspx_th_aui_button_1.doStartTag();
    if (_jspx_th_aui_button_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_1);
      return true;
    }
    _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_12(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_12 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_12.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_1);
    _jspx_th_liferay$1ui_message_12.setKey("clear-content-cached-by-this-vm");
    int _jspx_eval_liferay$1ui_message_12 = _jspx_th_liferay$1ui_message_12.doStartTag();
    if (_jspx_th_liferay$1ui_message_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_12);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_12);
    return false;
  }

  private boolean _jspx_meth_aui_button_2(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_2 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_2.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_1);
    _jspx_th_aui_button_2.setCssClass("save-server-button");
    _jspx_th_aui_button_2.setDynamicAttribute(null, "data-cmd", new String("cacheSingle"));
    _jspx_th_aui_button_2.setValue("execute");
    int _jspx_eval_aui_button_2 = _jspx_th_aui_button_2.doStartTag();
    if (_jspx_th_aui_button_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_2);
      return true;
    }
    _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_2);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_13(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_13 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_13.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_1);
    _jspx_th_liferay$1ui_message_13.setKey("clear-content-cached-across-the-cluster");
    int _jspx_eval_liferay$1ui_message_13 = _jspx_th_liferay$1ui_message_13.doStartTag();
    if (_jspx_th_liferay$1ui_message_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_13);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_13);
    return false;
  }

  private boolean _jspx_meth_aui_button_3(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_3 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_3.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_1);
    _jspx_th_aui_button_3.setCssClass("save-server-button");
    _jspx_th_aui_button_3.setDynamicAttribute(null, "data-cmd", new String("cacheMulti"));
    _jspx_th_aui_button_3.setValue("execute");
    int _jspx_eval_aui_button_3 = _jspx_th_aui_button_3.doStartTag();
    if (_jspx_th_aui_button_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_3);
      return true;
    }
    _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_3);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_14(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_14 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_14.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_1);
    _jspx_th_liferay$1ui_message_14.setKey("clear-the-database-cache");
    int _jspx_eval_liferay$1ui_message_14 = _jspx_th_liferay$1ui_message_14.doStartTag();
    if (_jspx_th_liferay$1ui_message_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_14);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_14);
    return false;
  }

  private boolean _jspx_meth_aui_button_4(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_4 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_4.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_1);
    _jspx_th_aui_button_4.setCssClass("save-server-button");
    _jspx_th_aui_button_4.setDynamicAttribute(null, "data-cmd", new String("cacheDb"));
    _jspx_th_aui_button_4.setValue("execute");
    int _jspx_eval_aui_button_4 = _jspx_th_aui_button_4.doStartTag();
    if (_jspx_th_aui_button_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_4);
      return true;
    }
    _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_4);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_15(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_15 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_15.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_1);
    _jspx_th_liferay$1ui_message_15.setKey("clear-the-direct-servlet-cache");
    int _jspx_eval_liferay$1ui_message_15 = _jspx_th_liferay$1ui_message_15.doStartTag();
    if (_jspx_th_liferay$1ui_message_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_15);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_15);
    return false;
  }

  private boolean _jspx_meth_aui_button_5(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_5 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_5.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_1);
    _jspx_th_aui_button_5.setCssClass("save-server-button");
    _jspx_th_aui_button_5.setDynamicAttribute(null, "data-cmd", new String("cacheServlet"));
    _jspx_th_aui_button_5.setValue("execute");
    int _jspx_eval_aui_button_5 = _jspx_th_aui_button_5.doStartTag();
    if (_jspx_th_aui_button_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_5);
      return true;
    }
    _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_5);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_16(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_16 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_16.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_2);
    _jspx_th_liferay$1ui_message_16.setKey("verify-database-tables-of-all-plugins");
    int _jspx_eval_liferay$1ui_message_16 = _jspx_th_liferay$1ui_message_16.doStartTag();
    if (_jspx_th_liferay$1ui_message_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_16);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_16);
    return false;
  }

  private boolean _jspx_meth_aui_button_6(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_6 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_6.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_2);
    _jspx_th_aui_button_6.setCssClass("save-server-button");
    _jspx_th_aui_button_6.setDynamicAttribute(null, "data-cmd", new String("verifyPluginTables"));
    _jspx_th_aui_button_6.setValue("execute");
    int _jspx_eval_aui_button_6 = _jspx_th_aui_button_6.doStartTag();
    if (_jspx_th_aui_button_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_6);
      return true;
    }
    _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_6);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_17(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_17 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_17.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_2);
    _jspx_th_liferay$1ui_message_17.setKey("verify-membership-policies");
    int _jspx_eval_liferay$1ui_message_17 = _jspx_th_liferay$1ui_message_17.doStartTag();
    if (_jspx_th_liferay$1ui_message_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_17);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_17);
    return false;
  }

  private boolean _jspx_meth_aui_button_7(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_7 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_7.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_2);
    _jspx_th_aui_button_7.setCssClass("save-server-button");
    _jspx_th_aui_button_7.setDynamicAttribute(null, "data-cmd", new String("verifyMembershipPolicies"));
    _jspx_th_aui_button_7.setValue("execute");
    int _jspx_eval_aui_button_7 = _jspx_th_aui_button_7.doStartTag();
    if (_jspx_th_aui_button_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_7);
      return true;
    }
    _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_7);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_18(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_18 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_18.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_18.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_3);
    _jspx_th_liferay$1ui_message_18.setKey("reset-preview-and-thumbnail-files-for-documents-and-media");
    int _jspx_eval_liferay$1ui_message_18 = _jspx_th_liferay$1ui_message_18.doStartTag();
    if (_jspx_th_liferay$1ui_message_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_18);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_18);
    return false;
  }

  private boolean _jspx_meth_aui_button_8(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_8 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_8.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_3);
    _jspx_th_aui_button_8.setCssClass("save-server-button");
    _jspx_th_aui_button_8.setDynamicAttribute(null, "data-cmd", new String("dlPreviews"));
    _jspx_th_aui_button_8.setValue("execute");
    int _jspx_eval_aui_button_8 = _jspx_th_aui_button_8.doStartTag();
    if (_jspx_th_aui_button_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_8);
      return true;
    }
    _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_8);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_19(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_19 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_19.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_19.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_3);
    _jspx_th_liferay$1ui_message_19.setKey("clean-up-permissions");
    int _jspx_eval_liferay$1ui_message_19 = _jspx_th_liferay$1ui_message_19.doStartTag();
    if (_jspx_th_liferay$1ui_message_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_19);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_19);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_icon$1help_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:icon-help
    com.liferay.taglib.ui.IconHelpTag _jspx_th_liferay$1ui_icon$1help_0 = (com.liferay.taglib.ui.IconHelpTag) _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.get(com.liferay.taglib.ui.IconHelpTag.class);
    _jspx_th_liferay$1ui_icon$1help_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_icon$1help_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_3);
    _jspx_th_liferay$1ui_icon$1help_0.setMessage("clean-up-permissions-help");
    int _jspx_eval_liferay$1ui_icon$1help_0 = _jspx_th_liferay$1ui_icon$1help_0.doStartTag();
    if (_jspx_th_liferay$1ui_icon$1help_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.reuse(_jspx_th_liferay$1ui_icon$1help_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.reuse(_jspx_th_liferay$1ui_icon$1help_0);
    return false;
  }

  private boolean _jspx_meth_aui_button_9(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_9 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_9.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_3);
    _jspx_th_aui_button_9.setCssClass("save-server-button");
    _jspx_th_aui_button_9.setDynamicAttribute(null, "data-cmd", new String("cleanUpPermissions"));
    _jspx_th_aui_button_9.setValue("execute");
    int _jspx_eval_aui_button_9 = _jspx_th_aui_button_9.doStartTag();
    if (_jspx_th_aui_button_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_9);
      return true;
    }
    _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_9);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_20(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_20 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_20.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_20.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_3);
    _jspx_th_liferay$1ui_message_20.setKey("clean-up-portlet-preferences");
    int _jspx_eval_liferay$1ui_message_20 = _jspx_th_liferay$1ui_message_20.doStartTag();
    if (_jspx_th_liferay$1ui_message_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_20);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_20);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_icon$1help_1(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:icon-help
    com.liferay.taglib.ui.IconHelpTag _jspx_th_liferay$1ui_icon$1help_1 = (com.liferay.taglib.ui.IconHelpTag) _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.get(com.liferay.taglib.ui.IconHelpTag.class);
    _jspx_th_liferay$1ui_icon$1help_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_icon$1help_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_3);
    _jspx_th_liferay$1ui_icon$1help_1.setMessage("clean-up-portlet-preferences-help");
    int _jspx_eval_liferay$1ui_icon$1help_1 = _jspx_th_liferay$1ui_icon$1help_1.doStartTag();
    if (_jspx_th_liferay$1ui_icon$1help_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.reuse(_jspx_th_liferay$1ui_icon$1help_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.reuse(_jspx_th_liferay$1ui_icon$1help_1);
    return false;
  }

  private boolean _jspx_meth_aui_button_10(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_panel_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:button
    com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_10 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
    _jspx_th_aui_button_10.setPageContext(_jspx_page_context);
    _jspx_th_aui_button_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_panel_3);
    _jspx_th_aui_button_10.setCssClass("save-server-button");
    _jspx_th_aui_button_10.setDynamicAttribute(null, "data-cmd", new String("cleanUpPortletPreferences"));
    _jspx_th_aui_button_10.setValue("execute");
    int _jspx_eval_aui_button_10 = _jspx_th_aui_button_10.doStartTag();
    if (_jspx_th_aui_button_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_10);
      return true;
    }
    _jspx_tagPool_aui_button_value_data$1cmd_cssClass_nobody.reuse(_jspx_th_aui_button_10);
    return false;
  }
}
