package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.ColorScheme;
import com.liferay.portal.kernel.model.Plugin;
import com.liferay.portal.kernel.model.PluginSetting;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.LayoutTemplateLocalServiceUtil;
import com.liferay.portal.kernel.service.PluginSettingLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ThemeLocalServiceUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.comparator.PortletTitleComparator;
import com.liferay.portal.kernel.util.comparator.RoleRoleIdComparator;
import com.liferay.roles.admin.kernel.util.RolesAdminUtil;
import java.util.ArrayList;
import java.util.List;
import javax.portlet.PortletURL;

public final class view_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(5);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
    _jspx_dependants.add("/themes.jspf");
    _jspx_dependants.add("/layout_templates.jspf");
    _jspx_dependants.add("/portlets.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_navigation$1bar_navigationItems_inverted_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_method_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_a_href;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_markupView_direction;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_button_value_onClick_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_href_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_navigation$1bar_navigationItems_inverted_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_method_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_a_href = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_markupView_direction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_button_value_onClick_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_href_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL.release();
    _jspx_tagPool_clay_navigation$1bar_navigationItems_inverted_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_url_method_message_nobody.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_aui_a_href.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.release();
    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_markupView_direction.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_aui_button_value_onClick_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_href_cssClass.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.release();
    _jspx_tagPool_c_when_test.release();
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
      out.write('\n');
      out.write('\n');

String tabs2 = ParamUtil.getString(request, "tabs2", "portlets");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/plugins_admin/view");
portletURL.setParameter("tabs2", tabs2);

PortletURL marketplaceURL = null;

boolean showEditPluginHREF = true;

      out.write('\n');
      out.write('\n');
      //  clay:navigation-bar
      com.liferay.frontend.taglib.clay.servlet.taglib.soy.NavigationBarTag _jspx_th_clay_navigation$1bar_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.NavigationBarTag) _jspx_tagPool_clay_navigation$1bar_navigationItems_inverted_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.NavigationBarTag.class);
      _jspx_th_clay_navigation$1bar_0.setPageContext(_jspx_page_context);
      _jspx_th_clay_navigation$1bar_0.setParent(null);
      _jspx_th_clay_navigation$1bar_0.setInverted( true );
      _jspx_th_clay_navigation$1bar_0.setNavigationItems(
		new JSPNavigationItemList(pageContext) {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(tabs2.equals("portlets"));
						navigationItem.setHref(renderResponse.createRenderURL(), "tabs2", "portlets");
						navigationItem.setLabel(LanguageUtil.get(request, "portlets"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs2.equals("themes"));
						navigationItem.setHref(renderResponse.createRenderURL(), "tabs2", "themes");
						navigationItem.setLabel(LanguageUtil.get(request, "themes"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs2.equals("layout-templates"));
						navigationItem.setHref(renderResponse.createRenderURL(), "tabs2", "layout-templates");
						navigationItem.setLabel(LanguageUtil.get(request, "layout-templates"));
					});
			}
		}
	);
      int _jspx_eval_clay_navigation$1bar_0 = _jspx_th_clay_navigation$1bar_0.doStartTag();
      if (_jspx_th_clay_navigation$1bar_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_clay_navigation$1bar_navigationItems_inverted_nobody.reuse(_jspx_th_clay_navigation$1bar_0);
        return;
      }
      _jspx_tagPool_clay_navigation$1bar_navigationItems_inverted_nobody.reuse(_jspx_th_clay_navigation$1bar_0);
      out.write("\n\n<div class=\"container-fluid-1280\">\n\t");
      //  c:choose
      com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
      _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
      _jspx_th_c_choose_0.setParent(null);
      int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
      if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write("\n\t\t");
        //  c:when
        com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
        _jspx_th_c_when_0.setPageContext(_jspx_page_context);
        _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
        _jspx_th_c_when_0.setTest( tabs2.equals("themes") );
        int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
        if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t");
          out.write('\n');
          out.write('\n');
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_0.setPageContext(_jspx_page_context);
          _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_c_if_0.setTest( marketplaceURL != null );
          int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
          if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\n\t");

	String taglibOnClick = "submitForm(document." + renderResponse.getNamespace() + "fm , '" + marketplaceURL.toString() +"');";
	
            out.write("\n\n\t");
            //  aui:button
            com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_0 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_onClick_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
            _jspx_th_aui_button_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_button_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
            _jspx_th_aui_button_0.setOnClick( taglibOnClick );
            _jspx_th_aui_button_0.setValue("install-more-themes");
            int _jspx_eval_aui_button_0 = _jspx_th_aui_button_0.doStartTag();
            if (_jspx_th_aui_button_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_button_value_onClick_nobody.reuse(_jspx_th_aui_button_0);
              return;
            }
            _jspx_tagPool_aui_button_value_onClick_nobody.reuse(_jspx_th_aui_button_0);
            out.write("\n\n\t<br /><br />\n");
          }
          if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
          out.write('\n');
          out.write('\n');

List themes = ThemeLocalServiceUtil.getThemes(company.getCompanyId());

          out.write("\n\n<div class=\"container-fluid container-max-xl container-view\">\n\t");
          //  liferay-ui:search-container
          com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_0 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL.get(com.liferay.taglib.ui.SearchContainerTag.class);
          _jspx_th_liferay$1ui_search$1container_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1container_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_liferay$1ui_search$1container_0.setIteratorURL( portletURL );
          _jspx_th_liferay$1ui_search$1container_0.setTotal( themes.size() );
          int _jspx_eval_liferay$1ui_search$1container_0 = _jspx_th_liferay$1ui_search$1container_0.doStartTag();
          if (_jspx_eval_liferay$1ui_search$1container_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            java.lang.Integer total = null;
            com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
            total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
            searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
            out.write("\n\t\t");
            //  liferay-ui:search-container-results
            java.util.List results = null;
            java.lang.Integer deprecatedTotal = null;
            com.liferay.taglib.ui.SearchContainerResultsTag _jspx_th_liferay$1ui_search$1container$1results_0 = (com.liferay.taglib.ui.SearchContainerResultsTag) _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.get(com.liferay.taglib.ui.SearchContainerResultsTag.class);
            _jspx_th_liferay$1ui_search$1container$1results_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container$1results_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
            _jspx_th_liferay$1ui_search$1container$1results_0.setResults( ListUtil.subList(themes, searchContainer.getStart(), searchContainer.getEnd()) );
            int _jspx_eval_liferay$1ui_search$1container$1results_0 = _jspx_th_liferay$1ui_search$1container$1results_0.doStartTag();
            results = (java.util.List) _jspx_page_context.findAttribute("results");
            deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
            if (_jspx_th_liferay$1ui_search$1container$1results_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1results_0);
              return;
            }
            results = (java.util.List) _jspx_page_context.findAttribute("results");
            deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
            _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1results_0);
            out.write("\n\n\t\t");
            //  liferay-ui:search-container-row
            com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_0 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
            _jspx_th_liferay$1ui_search$1container$1row_0.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
            _jspx_th_liferay$1ui_search$1container$1row_0.setClassName("com.liferay.portal.kernel.model.Theme");
            _jspx_th_liferay$1ui_search$1container$1row_0.setModelVar("curTheme");
            int _jspx_eval_liferay$1ui_search$1container$1row_0 = _jspx_th_liferay$1ui_search$1container$1row_0.doStartTag();
            if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              java.lang.Integer index = null;
              com.liferay.portal.kernel.model.Theme curTheme = null;
              com.liferay.portal.kernel.dao.search.ResultRow row = null;
              if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_liferay$1ui_search$1container$1row_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_liferay$1ui_search$1container$1row_0.doInitBody();
              }
              index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
              curTheme = (com.liferay.portal.kernel.model.Theme) _jspx_page_context.findAttribute("curTheme");
              row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
              do {
                out.write("\n\n\t\t\t");

			PluginPackage pluginPackage = curTheme.getPluginPackage();
			PluginSetting pluginSetting = PluginSettingLocalServiceUtil.getPluginSetting(company.getCompanyId(), curTheme.getThemeId(), Plugin.TYPE_THEME);

			PortletURL rowURL = renderResponse.createRenderURL();

			rowURL.setParameter("mvcRenderCommandName", "/plugins_admin/edit_plugin");
			rowURL.setParameter("redirect", currentURL);

			if (pluginPackage != null) {
				rowURL.setParameter("moduleId", pluginPackage.getModuleId());
			}

			rowURL.setParameter("pluginId", curTheme.getThemeId());
			rowURL.setParameter("pluginType", Plugin.TYPE_THEME);
			rowURL.setParameter("title", curTheme.getName());
			
                out.write("\n\n\t\t\t");
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_0 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
                _jspx_th_liferay$1ui_search$1container$1column$1text_0.setCssClass("table-cell-expand table-cell-minw-200 table-title");
                _jspx_th_liferay$1ui_search$1container$1column$1text_0.setName("themes");
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_0 = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doStartTag();
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_search$1container$1column$1text_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_0.doInitBody();
                  }
                  do {
                    out.write("\n\t\t\t\t");
                    //  aui:a
                    com.liferay.taglib.aui.ATag _jspx_th_aui_a_0 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_href.get(com.liferay.taglib.aui.ATag.class);
                    _jspx_th_aui_a_0.setPageContext(_jspx_page_context);
                    _jspx_th_aui_a_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_0);
                    _jspx_th_aui_a_0.setHref( showEditPluginHREF ? rowURL.toString() : null );
                    int _jspx_eval_aui_a_0 = _jspx_th_aui_a_0.doStartTag();
                    if (_jspx_eval_aui_a_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t<img alt=\"");
                      //  liferay-ui:message
                      com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                      _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_a_0);
                      _jspx_th_liferay$1ui_message_0.setEscapeAttribute( true );
                      _jspx_th_liferay$1ui_message_0.setKey("thumbnail");
                      int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
                      if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody.reuse(_jspx_th_liferay$1ui_message_0);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody.reuse(_jspx_th_liferay$1ui_message_0);
                      out.write("\" class=\"plugin-thumbnail\" src=\"");
                      out.print( curTheme.getStaticResourcePath() + HtmlUtil.escapeAttribute(curTheme.getImagesPath()) );
                      out.write("/thumbnail.png\" />\n\n\t\t\t\t\t<strong>");
                      out.print( curTheme.getName() );
                      out.write("</strong>\n\t\t\t\t");
                    }
                    if (_jspx_th_aui_a_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_0);
                      return;
                    }
                    _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_0);
                    out.write("\n\n\t\t\t\t");

				List colorSchemes = curTheme.getColorSchemes();
				
                    out.write("\n\n\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_0);
                    _jspx_th_c_if_1.setTest( !colorSchemes.isEmpty() );
                    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
                    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t<br />\n\n\t\t\t\t\t");
                      if (_jspx_meth_liferay$1ui_message_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_if_1, _jspx_page_context))
                        return;
                      out.write(":\n\n\t\t\t\t\t");
                      out.print( ListUtil.toString(colorSchemes, ColorScheme.NAME_ACCESSOR, StringPool.COMMA_AND_SPACE) );
                      out.write("\n\t\t\t\t");
                    }
                    if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                    out.write("\n\t\t\t");
                    int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
                out.write("\n\n\t\t\t");
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_1 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_1.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
                _jspx_th_liferay$1ui_search$1container$1column$1text_1.setCssClass("table-cell-expand-small table-cell-minw-75");
                _jspx_th_liferay$1ui_search$1container$1column$1text_1.setName("active");
                _jspx_th_liferay$1ui_search$1container$1column$1text_1.setValue( LanguageUtil.get(request, (pluginSetting.isActive() ? "yes" : "no")) );
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_1 = _jspx_th_liferay$1ui_search$1container$1column$1text_1.doStartTag();
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
                out.write("\n\n\t\t\t");
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_2 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_2.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
                _jspx_th_liferay$1ui_search$1container$1column$1text_2.setCssClass("table-cell-expand table-cell-minw-200");
                _jspx_th_liferay$1ui_search$1container$1column$1text_2.setName("roles");
                _jspx_th_liferay$1ui_search$1container$1column$1text_2.setValue( HtmlUtil.escape(StringUtil.merge(pluginSetting.getRolesArray(), StringPool.COMMA_AND_SPACE)) );
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_2 = _jspx_th_liferay$1ui_search$1container$1column$1text_2.doStartTag();
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
                out.write("\n\n\t\t\t");
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_3 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_3.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
                _jspx_th_liferay$1ui_search$1container$1column$1text_3.setName("");
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_3 = _jspx_th_liferay$1ui_search$1container$1column$1text_3.doStartTag();
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_search$1container$1column$1text_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_3.doInitBody();
                  }
                  do {
                    out.write("\n\t\t\t\t");
                    //  liferay-ui:icon-menu
                    com.liferay.taglib.ui.IconMenuTag _jspx_th_liferay$1ui_icon$1menu_0 = (com.liferay.taglib.ui.IconMenuTag) _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_markupView_direction.get(com.liferay.taglib.ui.IconMenuTag.class);
                    _jspx_th_liferay$1ui_icon$1menu_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_icon$1menu_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_3);
                    _jspx_th_liferay$1ui_icon$1menu_0.setDirection("down");
                    _jspx_th_liferay$1ui_icon$1menu_0.setMarkupView("lexicon");
                    _jspx_th_liferay$1ui_icon$1menu_0.setShowWhenSingleIcon( true );
                    int _jspx_eval_liferay$1ui_icon$1menu_0 = _jspx_th_liferay$1ui_icon$1menu_0.doStartTag();
                    if (_jspx_eval_liferay$1ui_icon$1menu_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_liferay$1ui_icon$1menu_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_liferay$1ui_icon$1menu_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_liferay$1ui_icon$1menu_0.doInitBody();
                      }
                      do {
                        out.write("\n\t\t\t\t\t");
                        //  liferay-ui:icon
                        com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_0 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_method_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
                        _jspx_th_liferay$1ui_icon_0.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
                        _jspx_th_liferay$1ui_icon_0.setMessage("edit");
                        _jspx_th_liferay$1ui_icon_0.setMethod("post");
                        _jspx_th_liferay$1ui_icon_0.setUrl( rowURL.toString() );
                        int _jspx_eval_liferay$1ui_icon_0 = _jspx_th_liferay$1ui_icon_0.doStartTag();
                        if (_jspx_th_liferay$1ui_icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_icon_url_method_message_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_icon_url_method_message_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
                        out.write("\n\t\t\t\t");
                        int evalDoAfterBody = _jspx_th_liferay$1ui_icon$1menu_0.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_liferay$1ui_icon$1menu_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                    }
                    if (_jspx_th_liferay$1ui_icon$1menu_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_markupView_direction.reuse(_jspx_th_liferay$1ui_icon$1menu_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_markupView_direction.reuse(_jspx_th_liferay$1ui_icon$1menu_0);
                    out.write("\n\t\t\t");
                    int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_3.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                out.write("\n\t\t");
                int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_0.doAfterBody();
                index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
                curTheme = (com.liferay.portal.kernel.model.Theme) _jspx_page_context.findAttribute("curTheme");
                row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_liferay$1ui_search$1container$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
              return;
            }
            _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
            out.write("\n\n\t\t");
            if (_jspx_meth_liferay$1ui_search$1iterator_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container_0, _jspx_page_context))
              return;
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_liferay$1ui_search$1container_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL.reuse(_jspx_th_liferay$1ui_search$1container_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL.reuse(_jspx_th_liferay$1ui_search$1container_0);
          out.write("\n</div>");
          out.write("\n\t\t");
        }
        if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
          return;
        }
        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
        out.write("\n\t\t");
        //  c:when
        com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
        _jspx_th_c_when_1.setPageContext(_jspx_page_context);
        _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
        _jspx_th_c_when_1.setTest( tabs2.equals("layout-templates") );
        int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
        if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t");
          out.write('\n');
          out.write('\n');
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_2.setPageContext(_jspx_page_context);
          _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
          _jspx_th_c_if_2.setTest( marketplaceURL != null );
          int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
          if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\n\t");

	String taglibOnClick = "submitForm(document." + renderResponse.getNamespace() + "fm , '" + marketplaceURL.toString() +"');";
	
            out.write("\n\n\t");
            //  aui:button
            com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_1 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_onClick_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
            _jspx_th_aui_button_1.setPageContext(_jspx_page_context);
            _jspx_th_aui_button_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
            _jspx_th_aui_button_1.setOnClick( taglibOnClick );
            _jspx_th_aui_button_1.setValue("install-more-layout-templates");
            int _jspx_eval_aui_button_1 = _jspx_th_aui_button_1.doStartTag();
            if (_jspx_th_aui_button_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_button_value_onClick_nobody.reuse(_jspx_th_aui_button_1);
              return;
            }
            _jspx_tagPool_aui_button_value_onClick_nobody.reuse(_jspx_th_aui_button_1);
            out.write("\n\n\t<br /><br />\n");
          }
          if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
          out.write('\n');
          out.write('\n');

List layoutTemplates = LayoutTemplateLocalServiceUtil.getLayoutTemplates();

          out.write("\n\n<div class=\"container-fluid container-fluid-max-xl container-view\">\n\t");
          //  liferay-ui:search-container
          com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_1 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL.get(com.liferay.taglib.ui.SearchContainerTag.class);
          _jspx_th_liferay$1ui_search$1container_1.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1container_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
          _jspx_th_liferay$1ui_search$1container_1.setIteratorURL( portletURL );
          _jspx_th_liferay$1ui_search$1container_1.setTotal( layoutTemplates.size() );
          int _jspx_eval_liferay$1ui_search$1container_1 = _jspx_th_liferay$1ui_search$1container_1.doStartTag();
          if (_jspx_eval_liferay$1ui_search$1container_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            java.lang.Integer total = null;
            com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
            total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
            searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
            out.write("\n\t\t");
            //  liferay-ui:search-container-results
            java.util.List results = null;
            java.lang.Integer deprecatedTotal = null;
            com.liferay.taglib.ui.SearchContainerResultsTag _jspx_th_liferay$1ui_search$1container$1results_1 = (com.liferay.taglib.ui.SearchContainerResultsTag) _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.get(com.liferay.taglib.ui.SearchContainerResultsTag.class);
            _jspx_th_liferay$1ui_search$1container$1results_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container$1results_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_1);
            _jspx_th_liferay$1ui_search$1container$1results_1.setResults( ListUtil.subList(layoutTemplates, searchContainer.getStart(), searchContainer.getEnd()) );
            int _jspx_eval_liferay$1ui_search$1container$1results_1 = _jspx_th_liferay$1ui_search$1container$1results_1.doStartTag();
            results = (java.util.List) _jspx_page_context.findAttribute("results");
            deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
            if (_jspx_th_liferay$1ui_search$1container$1results_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1results_1);
              return;
            }
            results = (java.util.List) _jspx_page_context.findAttribute("results");
            deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
            _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1results_1);
            out.write("\n\n\t\t");
            //  liferay-ui:search-container-row
            com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_1 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
            _jspx_th_liferay$1ui_search$1container$1row_1.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container$1row_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_1);
            _jspx_th_liferay$1ui_search$1container$1row_1.setClassName("com.liferay.portal.kernel.model.LayoutTemplate");
            _jspx_th_liferay$1ui_search$1container$1row_1.setModelVar("layoutTemplate");
            int _jspx_eval_liferay$1ui_search$1container$1row_1 = _jspx_th_liferay$1ui_search$1container$1row_1.doStartTag();
            if (_jspx_eval_liferay$1ui_search$1container$1row_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              java.lang.Integer index = null;
              com.liferay.portal.kernel.model.LayoutTemplate layoutTemplate = null;
              com.liferay.portal.kernel.dao.search.ResultRow row = null;
              if (_jspx_eval_liferay$1ui_search$1container$1row_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_liferay$1ui_search$1container$1row_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_liferay$1ui_search$1container$1row_1.doInitBody();
              }
              index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
              layoutTemplate = (com.liferay.portal.kernel.model.LayoutTemplate) _jspx_page_context.findAttribute("layoutTemplate");
              row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
              do {
                out.write("\n\n\t\t\t");

			PluginPackage pluginPackage = layoutTemplate.getPluginPackage();
			PluginSetting pluginSetting = PluginSettingLocalServiceUtil.getPluginSetting(company.getCompanyId(), layoutTemplate.getLayoutTemplateId(), Plugin.TYPE_LAYOUT_TEMPLATE);

			PortletURL rowURL = renderResponse.createRenderURL();

			rowURL.setParameter("mvcRenderCommandName", "/plugins_admin/edit_plugin");
			rowURL.setParameter("redirect", currentURL);

			if (pluginPackage != null) {
				rowURL.setParameter("moduleId", pluginPackage.getModuleId());
			}

			rowURL.setParameter("pluginId", layoutTemplate.getLayoutTemplateId());
			rowURL.setParameter("pluginType", Plugin.TYPE_LAYOUT_TEMPLATE);
			rowURL.setParameter("title", layoutTemplate.getName());
			
                out.write("\n\n\t\t\t");
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_4 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_4.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_1);
                _jspx_th_liferay$1ui_search$1container$1column$1text_4.setCssClass("table-cell-expand table-cell-minw-200 table-title");
                _jspx_th_liferay$1ui_search$1container$1column$1text_4.setName("layout-template");
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_4 = _jspx_th_liferay$1ui_search$1container$1column$1text_4.doStartTag();
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_search$1container$1column$1text_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_4.doInitBody();
                  }
                  do {
                    out.write("\n\t\t\t\t");
                    //  aui:a
                    com.liferay.taglib.aui.ATag _jspx_th_aui_a_1 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_href.get(com.liferay.taglib.aui.ATag.class);
                    _jspx_th_aui_a_1.setPageContext(_jspx_page_context);
                    _jspx_th_aui_a_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_4);
                    _jspx_th_aui_a_1.setHref( showEditPluginHREF ? rowURL.toString() : null );
                    int _jspx_eval_aui_a_1 = _jspx_th_aui_a_1.doStartTag();
                    if (_jspx_eval_aui_a_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t<img alt=\"");
                      //  liferay-ui:message
                      com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                      _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_a_1);
                      _jspx_th_liferay$1ui_message_2.setEscapeAttribute( true );
                      _jspx_th_liferay$1ui_message_2.setKey("thumbnail");
                      int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
                      if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody.reuse(_jspx_th_liferay$1ui_message_2);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_message_key_escapeAttribute_nobody.reuse(_jspx_th_liferay$1ui_message_2);
                      out.write("\" class=\"plugin-thumbnail\" src=\"");
                      out.print( layoutTemplate.getStaticResourcePath() + HtmlUtil.escapeAttribute(layoutTemplate.getThumbnailPath()) );
                      out.write("\" />\n\n\t\t\t\t\t<strong>");
                      out.print( HtmlUtil.escape(layoutTemplate.getName(locale)) );
                      out.write("</strong>\n\t\t\t\t");
                    }
                    if (_jspx_th_aui_a_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_1);
                      return;
                    }
                    _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_1);
                    out.write("\n\t\t\t");
                    int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_4.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
                out.write("\n\n\t\t\t");
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_5 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_5.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_1);
                _jspx_th_liferay$1ui_search$1container$1column$1text_5.setCssClass("table-cell-expand-small table-cell-minw-75");
                _jspx_th_liferay$1ui_search$1container$1column$1text_5.setName("active");
                _jspx_th_liferay$1ui_search$1container$1column$1text_5.setValue( LanguageUtil.get(request, (pluginSetting.isActive() ? "yes" : "no")) );
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_5 = _jspx_th_liferay$1ui_search$1container$1column$1text_5.doStartTag();
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_5);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_5);
                out.write("\n\n\t\t\t");
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_6 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_6.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_1);
                _jspx_th_liferay$1ui_search$1container$1column$1text_6.setCssClass("table-cell-expand table-cell-minw-200");
                _jspx_th_liferay$1ui_search$1container$1column$1text_6.setName("roles");
                _jspx_th_liferay$1ui_search$1container$1column$1text_6.setValue( HtmlUtil.escape(StringUtil.merge(pluginSetting.getRolesArray(), StringPool.COMMA_AND_SPACE)) );
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_6 = _jspx_th_liferay$1ui_search$1container$1column$1text_6.doStartTag();
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_6);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_6);
                out.write("\n\n\t\t\t");
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_7 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_7.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_1);
                _jspx_th_liferay$1ui_search$1container$1column$1text_7.setName("");
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_7 = _jspx_th_liferay$1ui_search$1container$1column$1text_7.doStartTag();
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_search$1container$1column$1text_7.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_7.doInitBody();
                  }
                  do {
                    out.write("\n\t\t\t\t");
                    //  liferay-ui:icon-menu
                    com.liferay.taglib.ui.IconMenuTag _jspx_th_liferay$1ui_icon$1menu_1 = (com.liferay.taglib.ui.IconMenuTag) _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_markupView_direction.get(com.liferay.taglib.ui.IconMenuTag.class);
                    _jspx_th_liferay$1ui_icon$1menu_1.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_icon$1menu_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_7);
                    _jspx_th_liferay$1ui_icon$1menu_1.setDirection("down");
                    _jspx_th_liferay$1ui_icon$1menu_1.setMarkupView("lexicon");
                    _jspx_th_liferay$1ui_icon$1menu_1.setShowWhenSingleIcon( true );
                    int _jspx_eval_liferay$1ui_icon$1menu_1 = _jspx_th_liferay$1ui_icon$1menu_1.doStartTag();
                    if (_jspx_eval_liferay$1ui_icon$1menu_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_liferay$1ui_icon$1menu_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_liferay$1ui_icon$1menu_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_liferay$1ui_icon$1menu_1.doInitBody();
                      }
                      do {
                        out.write("\n\t\t\t\t\t");
                        //  liferay-ui:icon
                        com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_1 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_method_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
                        _jspx_th_liferay$1ui_icon_1.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_icon_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_1);
                        _jspx_th_liferay$1ui_icon_1.setMessage("edit");
                        _jspx_th_liferay$1ui_icon_1.setMethod("post");
                        _jspx_th_liferay$1ui_icon_1.setUrl( rowURL.toString() );
                        int _jspx_eval_liferay$1ui_icon_1 = _jspx_th_liferay$1ui_icon_1.doStartTag();
                        if (_jspx_th_liferay$1ui_icon_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_icon_url_method_message_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_icon_url_method_message_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
                        out.write("\n\t\t\t\t");
                        int evalDoAfterBody = _jspx_th_liferay$1ui_icon$1menu_1.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_liferay$1ui_icon$1menu_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                    }
                    if (_jspx_th_liferay$1ui_icon$1menu_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_markupView_direction.reuse(_jspx_th_liferay$1ui_icon$1menu_1);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_markupView_direction.reuse(_jspx_th_liferay$1ui_icon$1menu_1);
                    out.write("\n\t\t\t");
                    int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_7.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_7 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_7);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_7);
                out.write("\n\t\t");
                int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_1.doAfterBody();
                index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
                layoutTemplate = (com.liferay.portal.kernel.model.LayoutTemplate) _jspx_page_context.findAttribute("layoutTemplate");
                row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_liferay$1ui_search$1container$1row_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_liferay$1ui_search$1container$1row_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_1);
              return;
            }
            _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_1);
            out.write("\n\n\t\t");
            if (_jspx_meth_liferay$1ui_search$1iterator_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container_1, _jspx_page_context))
              return;
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_liferay$1ui_search$1container_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL.reuse(_jspx_th_liferay$1ui_search$1container_1);
            return;
          }
          _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL.reuse(_jspx_th_liferay$1ui_search$1container_1);
          out.write("\n</div>");
          out.write("\n\t\t");
        }
        if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
          return;
        }
        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
        out.write("\n\t\t");
        //  c:when
        com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
        _jspx_th_c_when_2.setPageContext(_jspx_page_context);
        _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
        _jspx_th_c_when_2.setTest( tabs2.equals("hook-plugins") );
        int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
        if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
        }
        if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
          return;
        }
        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
        out.write("\n\t\t");
        //  c:when
        com.liferay.taglib.core.WhenTag _jspx_th_c_when_3 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
        _jspx_th_c_when_3.setPageContext(_jspx_page_context);
        _jspx_th_c_when_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
        _jspx_th_c_when_3.setTest( tabs2.equals("web-plugins") );
        int _jspx_eval_c_when_3 = _jspx_th_c_when_3.doStartTag();
        if (_jspx_eval_c_when_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
        }
        if (_jspx_th_c_when_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
          return;
        }
        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
        out.write("\n\t\t");
        //  c:otherwise
        com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
        _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
        _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
        int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
        if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t");
          out.write('\n');
          out.write('\n');
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_3.setPageContext(_jspx_page_context);
          _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
          _jspx_th_c_if_3.setTest( marketplaceURL != null );
          int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
          if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\n\t");

	String taglibOnClick = "submitForm(document." + renderResponse.getNamespace() + "fm , '" + marketplaceURL.toString() +"');";
	
            out.write("\n\n\t");
            //  aui:button
            com.liferay.taglib.aui.ButtonTag _jspx_th_aui_button_2 = (com.liferay.taglib.aui.ButtonTag) _jspx_tagPool_aui_button_value_onClick_nobody.get(com.liferay.taglib.aui.ButtonTag.class);
            _jspx_th_aui_button_2.setPageContext(_jspx_page_context);
            _jspx_th_aui_button_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
            _jspx_th_aui_button_2.setOnClick( taglibOnClick );
            _jspx_th_aui_button_2.setValue("install-more-portlets");
            int _jspx_eval_aui_button_2 = _jspx_th_aui_button_2.doStartTag();
            if (_jspx_th_aui_button_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_button_value_onClick_nobody.reuse(_jspx_th_aui_button_2);
              return;
            }
            _jspx_tagPool_aui_button_value_onClick_nobody.reuse(_jspx_th_aui_button_2);
            out.write("\n\n\t<br /><br />\n");
          }
          if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
          out.write('\n');
          out.write('\n');

List<Portlet> portlets = PortletLocalServiceUtil.getPortlets(company.getCompanyId(), false, false);

portlets = ListUtil.sort(portlets, new PortletTitleComparator(application, locale));

          out.write("\n\n<div class=\"container-fluid container-fluid-max-xl container-view\">\n\t");
          //  liferay-ui:search-container
          com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_2 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL.get(com.liferay.taglib.ui.SearchContainerTag.class);
          _jspx_th_liferay$1ui_search$1container_2.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_search$1container_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
          _jspx_th_liferay$1ui_search$1container_2.setIteratorURL( portletURL );
          _jspx_th_liferay$1ui_search$1container_2.setTotal( portlets.size() );
          int _jspx_eval_liferay$1ui_search$1container_2 = _jspx_th_liferay$1ui_search$1container_2.doStartTag();
          if (_jspx_eval_liferay$1ui_search$1container_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            java.lang.Integer total = null;
            com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
            total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
            searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
            out.write("\n\t\t");
            //  liferay-ui:search-container-results
            java.util.List results = null;
            java.lang.Integer deprecatedTotal = null;
            com.liferay.taglib.ui.SearchContainerResultsTag _jspx_th_liferay$1ui_search$1container$1results_2 = (com.liferay.taglib.ui.SearchContainerResultsTag) _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.get(com.liferay.taglib.ui.SearchContainerResultsTag.class);
            _jspx_th_liferay$1ui_search$1container$1results_2.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container$1results_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_2);
            _jspx_th_liferay$1ui_search$1container$1results_2.setResults( ListUtil.subList(portlets, searchContainer.getStart(), searchContainer.getEnd()) );
            int _jspx_eval_liferay$1ui_search$1container$1results_2 = _jspx_th_liferay$1ui_search$1container$1results_2.doStartTag();
            results = (java.util.List) _jspx_page_context.findAttribute("results");
            deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
            if (_jspx_th_liferay$1ui_search$1container$1results_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1results_2);
              return;
            }
            results = (java.util.List) _jspx_page_context.findAttribute("results");
            deprecatedTotal = (java.lang.Integer) _jspx_page_context.findAttribute("deprecatedTotal");
            _jspx_tagPool_liferay$1ui_search$1container$1results_results_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1results_2);
            out.write("\n\n\t\t");
            //  liferay-ui:search-container-row
            com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_2 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
            _jspx_th_liferay$1ui_search$1container$1row_2.setPageContext(_jspx_page_context);
            _jspx_th_liferay$1ui_search$1container$1row_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_2);
            _jspx_th_liferay$1ui_search$1container$1row_2.setClassName("com.liferay.portal.kernel.model.Portlet");
            _jspx_th_liferay$1ui_search$1container$1row_2.setModelVar("portlet");
            int _jspx_eval_liferay$1ui_search$1container$1row_2 = _jspx_th_liferay$1ui_search$1container$1row_2.doStartTag();
            if (_jspx_eval_liferay$1ui_search$1container$1row_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              java.lang.Integer index = null;
              com.liferay.portal.kernel.model.Portlet portlet = null;
              com.liferay.portal.kernel.dao.search.ResultRow row = null;
              if (_jspx_eval_liferay$1ui_search$1container$1row_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                out = _jspx_page_context.pushBody();
                _jspx_th_liferay$1ui_search$1container$1row_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                _jspx_th_liferay$1ui_search$1container$1row_2.doInitBody();
              }
              index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
              portlet = (com.liferay.portal.kernel.model.Portlet) _jspx_page_context.findAttribute("portlet");
              row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
              do {
                out.write("\n\n\t\t\t");

			PluginPackage pluginPackage = portlet.getPluginPackage();

			PortletURL rowURL = renderResponse.createRenderURL();

			rowURL.setParameter("mvcRenderCommandName", "/plugins_admin/edit_plugin");
			rowURL.setParameter("redirect", currentURL);

			if (pluginPackage != null) {
				rowURL.setParameter("moduleId", pluginPackage.getModuleId());
			}

			rowURL.setParameter("pluginId", portlet.getPortletId());
			rowURL.setParameter("pluginType", Plugin.TYPE_PORTLET);

			String title = PortalUtil.getPortletTitle(portlet, application, locale);

			rowURL.setParameter("title", title);
			
                out.write("\n\n\t\t\t");
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_8 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_href_cssClass.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_8.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_2);
                _jspx_th_liferay$1ui_search$1container$1column$1text_8.setCssClass("table-cell-expand table-cell-minw-200 table-title");
                _jspx_th_liferay$1ui_search$1container$1column$1text_8.setHref( rowURL );
                _jspx_th_liferay$1ui_search$1container$1column$1text_8.setName("portlet");
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_8 = _jspx_th_liferay$1ui_search$1container$1column$1text_8.doStartTag();
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_search$1container$1column$1text_8.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_8.doInitBody();
                  }
                  do {
                    out.write("\n\t\t\t\t");
                    //  aui:a
                    com.liferay.taglib.aui.ATag _jspx_th_aui_a_2 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_href.get(com.liferay.taglib.aui.ATag.class);
                    _jspx_th_aui_a_2.setPageContext(_jspx_page_context);
                    _jspx_th_aui_a_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_8);
                    _jspx_th_aui_a_2.setHref( showEditPluginHREF ? rowURL.toString() : null );
                    int _jspx_eval_aui_a_2 = _jspx_th_aui_a_2.doStartTag();
                    if (_jspx_eval_aui_a_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t");
                      //  c:choose
                      com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                      _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
                      _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_a_2);
                      int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
                      if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t");
                        //  c:when
                        com.liferay.taglib.core.WhenTag _jspx_th_c_when_4 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                        _jspx_th_c_when_4.setPageContext(_jspx_page_context);
                        _jspx_th_c_when_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                        _jspx_th_c_when_4.setTest( Validator.isNotNull(title) );
                        int _jspx_eval_c_when_4 = _jspx_th_c_when_4.doStartTag();
                        if (_jspx_eval_c_when_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t");
                          out.print( title );
                          out.write("\n\t\t\t\t\t\t");
                        }
                        if (_jspx_th_c_when_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                          return;
                        }
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                        out.write("\n\t\t\t\t\t\t");
                        //  c:when
                        com.liferay.taglib.core.WhenTag _jspx_th_c_when_5 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                        _jspx_th_c_when_5.setPageContext(_jspx_page_context);
                        _jspx_th_c_when_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                        _jspx_th_c_when_5.setTest( Validator.isNotNull(portlet.getDisplayName()) );
                        int _jspx_eval_c_when_5 = _jspx_th_c_when_5.doStartTag();
                        if (_jspx_eval_c_when_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t");
                          out.print( portlet.getDisplayName() );
                          out.write("\n\t\t\t\t\t\t");
                        }
                        if (_jspx_th_c_when_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_5);
                          return;
                        }
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_5);
                        out.write("\n\t\t\t\t\t\t");
                        //  c:otherwise
                        com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                        _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
                        _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                        int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
                        if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t");
                          out.print( portlet.getPortletName() );
                          out.write("\n\t\t\t\t\t\t");
                        }
                        if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                          return;
                        }
                        _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
                        out.write("\n\t\t\t\t\t");
                      }
                      if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                        return;
                      }
                      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                      out.write("\n\t\t\t\t");
                    }
                    if (_jspx_th_aui_a_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_2);
                      return;
                    }
                    _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_2);
                    out.write("\n\t\t\t");
                    int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_8.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_8 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_href_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_8);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_href_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_8);
                out.write("\n\n\t\t\t");
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_9 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_9.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_2);
                _jspx_th_liferay$1ui_search$1container$1column$1text_9.setCssClass("table-cell-expand table-cell-ws-nowrap");
                _jspx_th_liferay$1ui_search$1container$1column$1text_9.setName("active");
                _jspx_th_liferay$1ui_search$1container$1column$1text_9.setValue( LanguageUtil.get(request, (portlet.isActive() ? "yes" : "no")) );
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_9 = _jspx_th_liferay$1ui_search$1container$1column$1text_9.doStartTag();
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_9);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_9);
                out.write("\n\n\t\t\t");
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_10 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_10.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_2);
                _jspx_th_liferay$1ui_search$1container$1column$1text_10.setName("");
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_10 = _jspx_th_liferay$1ui_search$1container$1column$1text_10.doStartTag();
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_search$1container$1column$1text_10.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_10.doInitBody();
                  }
                  do {
                    out.write("\n\t\t\t\t");
                    //  liferay-ui:icon-menu
                    com.liferay.taglib.ui.IconMenuTag _jspx_th_liferay$1ui_icon$1menu_2 = (com.liferay.taglib.ui.IconMenuTag) _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_markupView_direction.get(com.liferay.taglib.ui.IconMenuTag.class);
                    _jspx_th_liferay$1ui_icon$1menu_2.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_icon$1menu_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_10);
                    _jspx_th_liferay$1ui_icon$1menu_2.setDirection("down");
                    _jspx_th_liferay$1ui_icon$1menu_2.setMarkupView("lexicon");
                    _jspx_th_liferay$1ui_icon$1menu_2.setShowWhenSingleIcon( true );
                    int _jspx_eval_liferay$1ui_icon$1menu_2 = _jspx_th_liferay$1ui_icon$1menu_2.doStartTag();
                    if (_jspx_eval_liferay$1ui_icon$1menu_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      if (_jspx_eval_liferay$1ui_icon$1menu_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                        out = _jspx_page_context.pushBody();
                        _jspx_th_liferay$1ui_icon$1menu_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                        _jspx_th_liferay$1ui_icon$1menu_2.doInitBody();
                      }
                      do {
                        out.write("\n\t\t\t\t\t");
                        //  liferay-ui:icon
                        com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_2 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_method_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
                        _jspx_th_liferay$1ui_icon_2.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_icon_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_2);
                        _jspx_th_liferay$1ui_icon_2.setMessage("edit");
                        _jspx_th_liferay$1ui_icon_2.setMethod("post");
                        _jspx_th_liferay$1ui_icon_2.setUrl( rowURL.toString() );
                        int _jspx_eval_liferay$1ui_icon_2 = _jspx_th_liferay$1ui_icon_2.doStartTag();
                        if (_jspx_th_liferay$1ui_icon_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_icon_url_method_message_nobody.reuse(_jspx_th_liferay$1ui_icon_2);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_icon_url_method_message_nobody.reuse(_jspx_th_liferay$1ui_icon_2);
                        out.write("\n\t\t\t\t");
                        int evalDoAfterBody = _jspx_th_liferay$1ui_icon$1menu_2.doAfterBody();
                        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                          break;
                      } while (true);
                      if (_jspx_eval_liferay$1ui_icon$1menu_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                        out = _jspx_page_context.popBody();
                    }
                    if (_jspx_th_liferay$1ui_icon$1menu_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_markupView_direction.reuse(_jspx_th_liferay$1ui_icon$1menu_2);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_markupView_direction.reuse(_jspx_th_liferay$1ui_icon$1menu_2);
                    out.write("\n\t\t\t");
                    int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_10.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_10 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_10);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_10);
                out.write("\n\t\t");
                int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_2.doAfterBody();
                index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
                portlet = (com.liferay.portal.kernel.model.Portlet) _jspx_page_context.findAttribute("portlet");
                row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
              if (_jspx_eval_liferay$1ui_search$1container$1row_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                out = _jspx_page_context.popBody();
            }
            if (_jspx_th_liferay$1ui_search$1container$1row_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_2);
              return;
            }
            _jspx_tagPool_liferay$1ui_search$1container$1row_modelVar_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_2);
            out.write("\n\n\t\t");
            if (_jspx_meth_liferay$1ui_search$1iterator_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1ui_search$1container_2, _jspx_page_context))
              return;
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_liferay$1ui_search$1container_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL.reuse(_jspx_th_liferay$1ui_search$1container_2);
            return;
          }
          _jspx_tagPool_liferay$1ui_search$1container_total_iteratorURL.reuse(_jspx_th_liferay$1ui_search$1container_2);
          out.write("\n</div>");
          out.write("\n\t\t");
        }
        if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
          return;
        }
        _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
        out.write('\n');
        out.write('	');
      }
      if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
        return;
      }
      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
      out.write("\n</div>");
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

  private boolean _jspx_meth_liferay$1ui_message_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_if_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
    _jspx_th_liferay$1ui_message_1.setKey("color-schemes");
    int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
    if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1iterator_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-iterator
    com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_0 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
    _jspx_th_liferay$1ui_search$1iterator_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1iterator_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
    _jspx_th_liferay$1ui_search$1iterator_0.setMarkupView("lexicon");
    int _jspx_eval_liferay$1ui_search$1iterator_0 = _jspx_th_liferay$1ui_search$1iterator_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1iterator_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1iterator_1(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-iterator
    com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_1 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
    _jspx_th_liferay$1ui_search$1iterator_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1iterator_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_1);
    _jspx_th_liferay$1ui_search$1iterator_1.setMarkupView("lexicon");
    int _jspx_eval_liferay$1ui_search$1iterator_1 = _jspx_th_liferay$1ui_search$1iterator_1.doStartTag();
    if (_jspx_th_liferay$1ui_search$1iterator_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1iterator_2(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1ui_search$1container_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-iterator
    com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_2 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
    _jspx_th_liferay$1ui_search$1iterator_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1iterator_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_2);
    _jspx_th_liferay$1ui_search$1iterator_2.setMarkupView("lexicon");
    int _jspx_eval_liferay$1ui_search$1iterator_2 = _jspx_th_liferay$1ui_search$1iterator_2.doStartTag();
    if (_jspx_th_liferay$1ui_search$1iterator_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_2);
    return false;
  }
}
