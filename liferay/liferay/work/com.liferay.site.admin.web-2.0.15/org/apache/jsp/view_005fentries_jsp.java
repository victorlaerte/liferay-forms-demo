package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.map.constants.MapProviderWebKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.DuplicateGroupException;
import com.liferay.portal.kernel.exception.GroupFriendlyURLException;
import com.liferay.portal.kernel.exception.GroupInheritContentException;
import com.liferay.portal.kernel.exception.GroupKeyException;
import com.liferay.portal.kernel.exception.GroupParentException;
import com.liferay.portal.kernel.exception.LayoutSetVirtualHostException;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.NoSuchGroupException;
import com.liferay.portal.kernel.exception.NoSuchLayoutException;
import com.liferay.portal.kernel.exception.NoSuchLayoutSetException;
import com.liferay.portal.kernel.exception.NoSuchRoleException;
import com.liferay.portal.kernel.exception.PendingBackgroundTaskException;
import com.liferay.portal.kernel.exception.RequiredGroupException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.model.MembershipRequest;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.SiteConstants;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetPrototypeServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.TeamLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.GroupPermissionUtil;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorConstants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.KeyValuePairComparator;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropertiesParamUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.UnicodeFormatter;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.liveusers.LiveUsers;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.sitesadmin.search.SiteChecker;
import com.liferay.ratings.kernel.RatingsType;
import com.liferay.ratings.kernel.display.context.CompanyPortletRatingsDefinitionDisplayContext;
import com.liferay.ratings.kernel.display.context.GroupPortletRatingsDefinitionDisplayContext;
import com.liferay.ratings.kernel.transformer.RatingsDataTransformerUtil;
import com.liferay.site.admin.web.internal.constants.SiteAdminConstants;
import com.liferay.site.admin.web.internal.constants.SiteAdminPortletKeys;
import com.liferay.site.admin.web.internal.constants.SiteAdminWebKeys;
import com.liferay.site.admin.web.internal.display.context.SiteAdminDisplayContext;
import com.liferay.site.admin.web.internal.display.context.SiteInitializerItemDisplayContext;
import com.liferay.site.constants.SiteWebKeys;
import com.liferay.site.util.GroupURLProvider;
import com.liferay.sites.kernel.util.Sites;
import com.liferay.sites.kernel.util.SitesUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

public final class view_005fentries_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(4);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
    _jspx_dependants.add("/site_vertical_card.jspf");
    _jspx_dependants.add("/site_columns.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_a_href;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1help_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1image_src_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1row_rowVar_rowIdProperty_modelVar_keyProperty_escapedModel_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_url_title_rowChecker_resultRow_icon_actionJspServletContext_actionJsp;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_icon_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container_searchContainer_rowChecker_id;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_unicode_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_a_localizeLabel_label_href_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_a_href_cssClass;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_rowChecker_resultRow_imageUrl_actionJspServletContext_actionJsp;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_vertical$1card$1footer;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_a_href = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1help_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1image_src_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1row_rowVar_rowIdProperty_modelVar_keyProperty_escapedModel_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_url_title_rowChecker_resultRow_icon_actionJspServletContext_actionJsp = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_icon_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container_searchContainer_rowChecker_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_unicode_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_a_localizeLabel_label_href_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_a_href_cssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_rowChecker_resultRow_imageUrl_actionJspServletContext_actionJsp = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_vertical$1card$1footer = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.release();
    _jspx_tagPool_aui_a_href.release();
    _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1image_src_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1row_rowVar_rowIdProperty_modelVar_keyProperty_escapedModel_className.release();
    _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_url_title_rowChecker_resultRow_icon_actionJspServletContext_actionJsp.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_cssClass.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_icon_nobody.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container_searchContainer_rowChecker_id.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_message_unicode_key_nobody.release();
    _jspx_tagPool_aui_a_localizeLabel_label_href_nobody.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text.release();
    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.release();
    _jspx_tagPool_aui_a_href_cssClass.release();
    _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_rowChecker_resultRow_imageUrl_actionJspServletContext_actionJsp.release();
    _jspx_tagPool_liferay$1frontend_vertical$1card$1footer.release();
    _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody.release();
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

SiteAdminDisplayContext siteAdminDisplayContext = new SiteAdminDisplayContext(request, liferayPortletRequest, liferayPortletResponse);

portletDisplay.setShowStagingIcon(false);

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

String searchContainerId = ParamUtil.getString(request, "searchContainerId");

String displayStyle = GetterUtil.getString((String)request.getAttribute("view.jsp-displayStyle"));
SearchContainer groupSearch = (SearchContainer)request.getAttribute("view.jsp-groupSearchContainer");

SiteChecker siteChecker = new SiteChecker(liferayPortletResponse);

siteChecker.setRememberCheckBoxStateURLRegex("^(?!.*" + liferayPortletResponse.getNamespace() + "redirect).*(groupId=" + siteAdminDisplayContext.getGroupId() + ")");

      out.write('\n');
      out.write('\n');
      //  liferay-ui:search-container
      com.liferay.taglib.ui.SearchContainerTag _jspx_th_liferay$1ui_search$1container_0 = (com.liferay.taglib.ui.SearchContainerTag) _jspx_tagPool_liferay$1ui_search$1container_searchContainer_rowChecker_id.get(com.liferay.taglib.ui.SearchContainerTag.class);
      _jspx_th_liferay$1ui_search$1container_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1ui_search$1container_0.setParent(null);
      _jspx_th_liferay$1ui_search$1container_0.setId( searchContainerId );
      _jspx_th_liferay$1ui_search$1container_0.setRowChecker( siteChecker );
      _jspx_th_liferay$1ui_search$1container_0.setSearchContainer( groupSearch );
      int _jspx_eval_liferay$1ui_search$1container_0 = _jspx_th_liferay$1ui_search$1container_0.doStartTag();
      if (_jspx_eval_liferay$1ui_search$1container_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        java.lang.Integer total = null;
        com.liferay.portal.kernel.dao.search.SearchContainer searchContainer = null;
        total = (java.lang.Integer) _jspx_page_context.findAttribute("total");
        searchContainer = (com.liferay.portal.kernel.dao.search.SearchContainer) _jspx_page_context.findAttribute("searchContainer");
        out.write('\n');
        out.write('	');
        //  liferay-ui:search-container-row
        com.liferay.taglib.ui.SearchContainerRowTag _jspx_th_liferay$1ui_search$1container$1row_0 = (com.liferay.taglib.ui.SearchContainerRowTag) _jspx_tagPool_liferay$1ui_search$1container$1row_rowVar_rowIdProperty_modelVar_keyProperty_escapedModel_className.get(com.liferay.taglib.ui.SearchContainerRowTag.class);
        _jspx_th_liferay$1ui_search$1container$1row_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_search$1container$1row_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
        _jspx_th_liferay$1ui_search$1container$1row_0.setClassName("com.liferay.portal.kernel.model.Group");
        _jspx_th_liferay$1ui_search$1container$1row_0.setEscapedModel( true );
        _jspx_th_liferay$1ui_search$1container$1row_0.setKeyProperty("groupId");
        _jspx_th_liferay$1ui_search$1container$1row_0.setModelVar("curGroup");
        _jspx_th_liferay$1ui_search$1container$1row_0.setRowIdProperty("friendlyURL");
        _jspx_th_liferay$1ui_search$1container$1row_0.setRowVar("row");
        int _jspx_eval_liferay$1ui_search$1container$1row_0 = _jspx_th_liferay$1ui_search$1container$1row_0.doStartTag();
        if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          java.lang.Integer index = null;
          com.liferay.portal.kernel.model.Group curGroup = null;
          com.liferay.portal.kernel.dao.search.ResultRow row = null;
          if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
            out = _jspx_page_context.pushBody();
            _jspx_th_liferay$1ui_search$1container$1row_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
            _jspx_th_liferay$1ui_search$1container$1row_0.doInitBody();
          }
          index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
          curGroup = (com.liferay.portal.kernel.model.Group) _jspx_page_context.findAttribute("curGroup");
          row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
          do {
            out.write("\n\n\t\t");

		List<Group> childSites = curGroup.getChildren(true);

		boolean hasAddChildSitePermisison = siteAdminDisplayContext.hasAddChildSitePermission(curGroup);

		String siteImageURL = curGroup.getLogoURL(themeDisplay, false);

		PortletURL viewSubsitesURL = null;

		if (hasAddChildSitePermisison && (row != null)) {
			viewSubsitesURL = renderResponse.createRenderURL();

			viewSubsitesURL.setParameter("mvcPath", "/view.jsp");
			viewSubsitesURL.setParameter("groupId", String.valueOf(curGroup.getGroupId()));
		}

		GroupURLProvider groupURLProvider = (GroupURLProvider)request.getAttribute(SiteWebKeys.GROUP_URL_PROVIDER);

		String viewSiteURL = groupURLProvider.getGroupURL(curGroup, liferayPortletRequest);
		
            out.write("\n\n\t\t");
            //  c:choose
            com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
            _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
            _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1row_0);
            int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
            if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  c:when
              com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
              _jspx_th_c_when_0.setPageContext(_jspx_page_context);
              _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
              _jspx_th_c_when_0.setTest( displayStyle.equals("descriptive") );
              int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
              if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t");
                //  c:choose
                com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
                _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
                int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
                if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t\t");
                  //  c:when
                  com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                  _jspx_th_c_when_1.setPageContext(_jspx_page_context);
                  _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
                  _jspx_th_c_when_1.setTest( Validator.isNotNull(siteImageURL) );
                  int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
                  if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\t\t\t\t\t\t");
                    //  liferay-ui:search-container-column-image
                    com.liferay.taglib.ui.SearchContainerColumnImageTag _jspx_th_liferay$1ui_search$1container$1column$1image_0 = (com.liferay.taglib.ui.SearchContainerColumnImageTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1image_src_nobody.get(com.liferay.taglib.ui.SearchContainerColumnImageTag.class);
                    _jspx_th_liferay$1ui_search$1container$1column$1image_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_search$1container$1column$1image_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                    _jspx_th_liferay$1ui_search$1container$1column$1image_0.setSrc( siteImageURL );
                    int _jspx_eval_liferay$1ui_search$1container$1column$1image_0 = _jspx_th_liferay$1ui_search$1container$1column$1image_0.doStartTag();
                    if (_jspx_th_liferay$1ui_search$1container$1column$1image_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_search$1container$1column$1image_src_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1image_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1image_src_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1image_0);
                    out.write("\n\t\t\t\t\t");
                  }
                  if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                    return;
                  }
                  _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                  out.write("\n\t\t\t\t\t");
                  if (_jspx_meth_c_otherwise_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_choose_1, _jspx_page_context))
                    return;
                  out.write("\n\t\t\t\t");
                }
                if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                  return;
                }
                _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
                out.write("\n\n\t\t\t\t");
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_0 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_0.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
                _jspx_th_liferay$1ui_search$1container$1column$1text_0.setColspan( 2 );
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_0 = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doStartTag();
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_search$1container$1column$1text_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_0.doInitBody();
                  }
                  do {
                    out.write("\n\t\t\t\t\t<h5>\n\t\t\t\t\t\t");
                    //  aui:a
                    com.liferay.taglib.aui.ATag _jspx_th_aui_a_0 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_localizeLabel_label_href_nobody.get(com.liferay.taglib.aui.ATag.class);
                    _jspx_th_aui_a_0.setPageContext(_jspx_page_context);
                    _jspx_th_aui_a_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_0);
                    _jspx_th_aui_a_0.setHref( viewSiteURL );
                    _jspx_th_aui_a_0.setLabel( HtmlUtil.escape(curGroup.getDescriptiveName(locale)) );
                    _jspx_th_aui_a_0.setLocalizeLabel( false );
                    int _jspx_eval_aui_a_0 = _jspx_th_aui_a_0.doStartTag();
                    if (_jspx_th_aui_a_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_a_localizeLabel_label_href_nobody.reuse(_jspx_th_aui_a_0);
                      return;
                    }
                    _jspx_tagPool_aui_a_localizeLabel_label_href_nobody.reuse(_jspx_th_aui_a_0);
                    out.write("\n\t\t\t\t\t</h5>\n\n\t\t\t\t\t<ul class=\"list-inline\">\n\t\t\t\t\t\t<li class=\"h6 text-default\">\n\t\t\t\t\t\t\t");
                    //  c:choose
                    com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_2 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                    _jspx_th_c_choose_2.setPageContext(_jspx_page_context);
                    _jspx_th_c_choose_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_0);
                    int _jspx_eval_c_choose_2 = _jspx_th_c_choose_2.doStartTag();
                    if (_jspx_eval_c_choose_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_2.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
                      _jspx_th_c_when_2.setTest( curGroup.isActive() );
                      int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
                      if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t");
                        if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_2, _jspx_page_context))
                          return;
                        out.write("\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                      out.write("\n\t\t\t\t\t\t\t\t");
                      if (_jspx_meth_c_otherwise_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_choose_2, _jspx_page_context))
                        return;
                      out.write("\n\t\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_choose_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                      return;
                    }
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_2);
                    out.write("\n\t\t\t\t\t\t</li>\n\n\t\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_0);
                    _jspx_th_c_if_0.setTest( hasAddChildSitePermisison && GroupPermissionUtil.contains(permissionChecker, curGroup, ActionKeys.VIEW) );
                    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
                    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t<li class=\"h6\">\n\t\t\t\t\t\t\t\t");
                      //  aui:a
                      com.liferay.taglib.aui.ATag _jspx_th_aui_a_1 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_href.get(com.liferay.taglib.aui.ATag.class);
                      _jspx_th_aui_a_1.setPageContext(_jspx_page_context);
                      _jspx_th_aui_a_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
                      _jspx_th_aui_a_1.setHref( (viewSubsitesURL != null) ? viewSubsitesURL.toString() : StringPool.BLANK );
                      int _jspx_eval_aui_a_1 = _jspx_th_aui_a_1.doStartTag();
                      if (_jspx_eval_aui_a_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t<span class=\"text-primary\">");
                        //  liferay-ui:message
                        com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                        _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_a_1);
                        _jspx_th_liferay$1ui_message_2.setArguments( String.valueOf(childSites.size()) );
                        _jspx_th_liferay$1ui_message_2.setKey("x-child-sites");
                        int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
                        if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_2);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_2);
                        out.write("</span>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_aui_a_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_1);
                        return;
                      }
                      _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_1);
                      out.write("\n\t\t\t\t\t\t\t</li>\n\t\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
                    out.write("\n\t\t\t\t\t</ul>\n\t\t\t\t");
                    int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_0.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_colspan.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_0);
                out.write("\n\n\t\t\t\t");
                if (_jspx_meth_liferay$1ui_search$1container$1column$1jsp_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_0, _jspx_page_context))
                  return;
                out.write("\n\t\t\t");
              }
              if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
                return;
              }
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
              out.write("\n\t\t\t");
              //  c:when
              com.liferay.taglib.core.WhenTag _jspx_th_c_when_3 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
              _jspx_th_c_when_3.setPageContext(_jspx_page_context);
              _jspx_th_c_when_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
              _jspx_th_c_when_3.setTest( displayStyle.equals("icon") );
              int _jspx_eval_c_when_3 = _jspx_th_c_when_3.doStartTag();
              if (_jspx_eval_c_when_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\n\t\t\t\t");

				row.setCssClass("entry-card lfr-asset-item " + row.getCssClass());
				
                out.write("\n\n\t\t\t\t");
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_1 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_1.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_3);
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_1 = _jspx_th_liferay$1ui_search$1container$1column$1text_1.doStartTag();
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_search$1container$1column$1text_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_1.doInitBody();
                  }
                  do {
                    out.write("\n\t\t\t\t\t");
                    //  c:choose
                    com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_3 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                    _jspx_th_c_choose_3.setPageContext(_jspx_page_context);
                    _jspx_th_c_choose_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_1);
                    int _jspx_eval_c_choose_3 = _jspx_th_c_choose_3.doStartTag();
                    if (_jspx_eval_c_choose_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_4 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_4.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                      _jspx_th_c_when_4.setTest( Validator.isNotNull(siteImageURL) );
                      int _jspx_eval_c_when_4 = _jspx_th_c_when_4.doStartTag();
                      if (_jspx_eval_c_when_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t");
                        //  liferay-frontend:vertical-card
                        com.liferay.frontend.taglib.servlet.taglib.VerticalCardTag _jspx_th_liferay$1frontend_vertical$1card_0 = (com.liferay.frontend.taglib.servlet.taglib.VerticalCardTag) _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_rowChecker_resultRow_imageUrl_actionJspServletContext_actionJsp.get(com.liferay.frontend.taglib.servlet.taglib.VerticalCardTag.class);
                        _jspx_th_liferay$1frontend_vertical$1card_0.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1frontend_vertical$1card_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_4);
                        _jspx_th_liferay$1frontend_vertical$1card_0.setActionJsp("/site_action.jsp");
                        _jspx_th_liferay$1frontend_vertical$1card_0.setActionJspServletContext( application );
                        _jspx_th_liferay$1frontend_vertical$1card_0.setImageUrl( siteImageURL );
                        _jspx_th_liferay$1frontend_vertical$1card_0.setResultRow( row );
                        _jspx_th_liferay$1frontend_vertical$1card_0.setRowChecker( searchContainer.getRowChecker() );
                        _jspx_th_liferay$1frontend_vertical$1card_0.setTitle( curGroup.getDescriptiveName(locale) );
                        _jspx_th_liferay$1frontend_vertical$1card_0.setUrl( viewSiteURL );
                        int _jspx_eval_liferay$1frontend_vertical$1card_0 = _jspx_th_liferay$1frontend_vertical$1card_0.doStartTag();
                        if (_jspx_eval_liferay$1frontend_vertical$1card_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t");
                          out.write('\n');
                          out.write('\n');
                          //  liferay-frontend:vertical-card-footer
                          com.liferay.frontend.taglib.servlet.taglib.VerticalCardFooterTag _jspx_th_liferay$1frontend_vertical$1card$1footer_0 = (com.liferay.frontend.taglib.servlet.taglib.VerticalCardFooterTag) _jspx_tagPool_liferay$1frontend_vertical$1card$1footer.get(com.liferay.frontend.taglib.servlet.taglib.VerticalCardFooterTag.class);
                          _jspx_th_liferay$1frontend_vertical$1card$1footer_0.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1frontend_vertical$1card$1footer_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_vertical$1card_0);
                          int _jspx_eval_liferay$1frontend_vertical$1card$1footer_0 = _jspx_th_liferay$1frontend_vertical$1card$1footer_0.doStartTag();
                          if (_jspx_eval_liferay$1frontend_vertical$1card$1footer_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            if (_jspx_eval_liferay$1frontend_vertical$1card$1footer_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                              out = _jspx_page_context.pushBody();
                              _jspx_th_liferay$1frontend_vertical$1card$1footer_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                              _jspx_th_liferay$1frontend_vertical$1card$1footer_0.doInitBody();
                            }
                            do {
                              out.write('\n');
                              out.write('	');
                              //  aui:a
                              com.liferay.taglib.aui.ATag _jspx_th_aui_a_2 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_href.get(com.liferay.taglib.aui.ATag.class);
                              _jspx_th_aui_a_2.setPageContext(_jspx_page_context);
                              _jspx_th_aui_a_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_vertical$1card$1footer_0);
                              _jspx_th_aui_a_2.setHref( (viewSubsitesURL != null) ? viewSubsitesURL.toString() : StringPool.BLANK );
                              int _jspx_eval_aui_a_2 = _jspx_th_aui_a_2.doStartTag();
                              if (_jspx_eval_aui_a_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t<span class=\"text-primary\">");
                              //  liferay-ui:message
                              com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                              _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_a_2);
                              _jspx_th_liferay$1ui_message_3.setArguments( String.valueOf(childSites.size()) );
                              _jspx_th_liferay$1ui_message_3.setKey("x-child-sites");
                              int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
                              if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_3);
                              return;
                              }
                              _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_3);
                              out.write("</span>\n\t");
                              }
                              if (_jspx_th_aui_a_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_2);
                              return;
                              }
                              _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_2);
                              out.write('\n');
                              int evalDoAfterBody = _jspx_th_liferay$1frontend_vertical$1card$1footer_0.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                            } while (true);
                            if (_jspx_eval_liferay$1frontend_vertical$1card$1footer_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                              out = _jspx_page_context.popBody();
                          }
                          if (_jspx_th_liferay$1frontend_vertical$1card$1footer_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1frontend_vertical$1card$1footer.reuse(_jspx_th_liferay$1frontend_vertical$1card$1footer_0);
                            return;
                          }
                          _jspx_tagPool_liferay$1frontend_vertical$1card$1footer.reuse(_jspx_th_liferay$1frontend_vertical$1card$1footer_0);
                          out.write("\n\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_liferay$1frontend_vertical$1card_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_rowChecker_resultRow_imageUrl_actionJspServletContext_actionJsp.reuse(_jspx_th_liferay$1frontend_vertical$1card_0);
                          return;
                        }
                        _jspx_tagPool_liferay$1frontend_vertical$1card_url_title_rowChecker_resultRow_imageUrl_actionJspServletContext_actionJsp.reuse(_jspx_th_liferay$1frontend_vertical$1card_0);
                        out.write("\n\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_when_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_4);
                      out.write("\n\t\t\t\t\t\t");
                      //  c:otherwise
                      com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_2 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                      _jspx_th_c_otherwise_2.setPageContext(_jspx_page_context);
                      _jspx_th_c_otherwise_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_3);
                      int _jspx_eval_c_otherwise_2 = _jspx_th_c_otherwise_2.doStartTag();
                      if (_jspx_eval_c_otherwise_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t");
                        //  liferay-frontend:icon-vertical-card
                        com.liferay.frontend.taglib.servlet.taglib.IconVerticalCardTag _jspx_th_liferay$1frontend_icon$1vertical$1card_0 = (com.liferay.frontend.taglib.servlet.taglib.IconVerticalCardTag) _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_url_title_rowChecker_resultRow_icon_actionJspServletContext_actionJsp.get(com.liferay.frontend.taglib.servlet.taglib.IconVerticalCardTag.class);
                        _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_2);
                        _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setActionJsp("/site_action.jsp");
                        _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setActionJspServletContext( application );
                        _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setIcon("sites");
                        _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setResultRow( row );
                        _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setRowChecker( searchContainer.getRowChecker() );
                        _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setTitle( curGroup.getDescriptiveName(locale) );
                        _jspx_th_liferay$1frontend_icon$1vertical$1card_0.setUrl( viewSiteURL );
                        int _jspx_eval_liferay$1frontend_icon$1vertical$1card_0 = _jspx_th_liferay$1frontend_icon$1vertical$1card_0.doStartTag();
                        if (_jspx_eval_liferay$1frontend_icon$1vertical$1card_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t\t\t\t\t");
                          out.write('\n');
                          out.write('\n');
                          //  liferay-frontend:vertical-card-footer
                          com.liferay.frontend.taglib.servlet.taglib.VerticalCardFooterTag _jspx_th_liferay$1frontend_vertical$1card$1footer_1 = (com.liferay.frontend.taglib.servlet.taglib.VerticalCardFooterTag) _jspx_tagPool_liferay$1frontend_vertical$1card$1footer.get(com.liferay.frontend.taglib.servlet.taglib.VerticalCardFooterTag.class);
                          _jspx_th_liferay$1frontend_vertical$1card$1footer_1.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1frontend_vertical$1card$1footer_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_icon$1vertical$1card_0);
                          int _jspx_eval_liferay$1frontend_vertical$1card$1footer_1 = _jspx_th_liferay$1frontend_vertical$1card$1footer_1.doStartTag();
                          if (_jspx_eval_liferay$1frontend_vertical$1card$1footer_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                            if (_jspx_eval_liferay$1frontend_vertical$1card$1footer_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                              out = _jspx_page_context.pushBody();
                              _jspx_th_liferay$1frontend_vertical$1card$1footer_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                              _jspx_th_liferay$1frontend_vertical$1card$1footer_1.doInitBody();
                            }
                            do {
                              out.write('\n');
                              out.write('	');
                              //  aui:a
                              com.liferay.taglib.aui.ATag _jspx_th_aui_a_3 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_href.get(com.liferay.taglib.aui.ATag.class);
                              _jspx_th_aui_a_3.setPageContext(_jspx_page_context);
                              _jspx_th_aui_a_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1frontend_vertical$1card$1footer_1);
                              _jspx_th_aui_a_3.setHref( (viewSubsitesURL != null) ? viewSubsitesURL.toString() : StringPool.BLANK );
                              int _jspx_eval_aui_a_3 = _jspx_th_aui_a_3.doStartTag();
                              if (_jspx_eval_aui_a_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                              out.write("\n\t\t<span class=\"text-primary\">");
                              //  liferay-ui:message
                              com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_4 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                              _jspx_th_liferay$1ui_message_4.setPageContext(_jspx_page_context);
                              _jspx_th_liferay$1ui_message_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_a_3);
                              _jspx_th_liferay$1ui_message_4.setArguments( String.valueOf(childSites.size()) );
                              _jspx_th_liferay$1ui_message_4.setKey("x-child-sites");
                              int _jspx_eval_liferay$1ui_message_4 = _jspx_th_liferay$1ui_message_4.doStartTag();
                              if (_jspx_th_liferay$1ui_message_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_4);
                              return;
                              }
                              _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_4);
                              out.write("</span>\n\t");
                              }
                              if (_jspx_th_aui_a_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                              _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_3);
                              return;
                              }
                              _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_3);
                              out.write('\n');
                              int evalDoAfterBody = _jspx_th_liferay$1frontend_vertical$1card$1footer_1.doAfterBody();
                              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                              break;
                            } while (true);
                            if (_jspx_eval_liferay$1frontend_vertical$1card$1footer_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                              out = _jspx_page_context.popBody();
                          }
                          if (_jspx_th_liferay$1frontend_vertical$1card$1footer_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1frontend_vertical$1card$1footer.reuse(_jspx_th_liferay$1frontend_vertical$1card$1footer_1);
                            return;
                          }
                          _jspx_tagPool_liferay$1frontend_vertical$1card$1footer.reuse(_jspx_th_liferay$1frontend_vertical$1card$1footer_1);
                          out.write("\n\t\t\t\t\t\t\t");
                        }
                        if (_jspx_th_liferay$1frontend_icon$1vertical$1card_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_url_title_rowChecker_resultRow_icon_actionJspServletContext_actionJsp.reuse(_jspx_th_liferay$1frontend_icon$1vertical$1card_0);
                          return;
                        }
                        _jspx_tagPool_liferay$1frontend_icon$1vertical$1card_url_title_rowChecker_resultRow_icon_actionJspServletContext_actionJsp.reuse(_jspx_th_liferay$1frontend_icon$1vertical$1card_0);
                        out.write("\n\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_otherwise_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                        return;
                      }
                      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_2);
                      out.write("\n\t\t\t\t\t");
                    }
                    if (_jspx_th_c_choose_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
                      return;
                    }
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_3);
                    out.write("\n\t\t\t\t");
                    int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_1.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_1);
                out.write("\n\t\t\t");
              }
              if (_jspx_th_c_when_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
                return;
              }
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_3);
              out.write("\n\t\t\t");
              //  c:otherwise
              com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_3 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
              _jspx_th_c_otherwise_3.setPageContext(_jspx_page_context);
              _jspx_th_c_otherwise_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
              int _jspx_eval_c_otherwise_3 = _jspx_th_c_otherwise_3.doStartTag();
              if (_jspx_eval_c_otherwise_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t");
                out.write('\n');
                out.write('\n');
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_2 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_cssClass.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_2.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_3);
                _jspx_th_liferay$1ui_search$1container$1column$1text_2.setCssClass("table-cell-expand table-cell-minw-200 table-title");
                _jspx_th_liferay$1ui_search$1container$1column$1text_2.setName("name");
                _jspx_th_liferay$1ui_search$1container$1column$1text_2.setOrderable( true );
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_2 = _jspx_th_liferay$1ui_search$1container$1column$1text_2.doStartTag();
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_search$1container$1column$1text_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_2.doInitBody();
                  }
                  do {
                    out.write('\n');
                    out.write('	');
                    //  aui:a
                    com.liferay.taglib.aui.ATag _jspx_th_aui_a_4 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_localizeLabel_label_href_nobody.get(com.liferay.taglib.aui.ATag.class);
                    _jspx_th_aui_a_4.setPageContext(_jspx_page_context);
                    _jspx_th_aui_a_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_2);
                    _jspx_th_aui_a_4.setHref( viewSiteURL );
                    _jspx_th_aui_a_4.setLabel( HtmlUtil.escape(curGroup.getDescriptiveName(locale)) );
                    _jspx_th_aui_a_4.setLocalizeLabel( false );
                    int _jspx_eval_aui_a_4 = _jspx_th_aui_a_4.doStartTag();
                    if (_jspx_th_aui_a_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_a_localizeLabel_label_href_nobody.reuse(_jspx_th_aui_a_4);
                      return;
                    }
                    _jspx_tagPool_aui_a_localizeLabel_label_href_nobody.reuse(_jspx_th_aui_a_4);
                    out.write("\n\n\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_2);
                    _jspx_th_c_if_1.setTest( curGroup.isOrganization() );
                    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
                    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\n\t\t");

		Organization organization = OrganizationLocalServiceUtil.getOrganization(curGroup.getOrganizationId());
		
                      out.write("\n\n\t\t<span class=\"organization-type\">(");
                      //  liferay-ui:message
                      com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_5 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                      _jspx_th_liferay$1ui_message_5.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_message_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_1);
                      _jspx_th_liferay$1ui_message_5.setKey( organization.getType() );
                      int _jspx_eval_liferay$1ui_message_5 = _jspx_th_liferay$1ui_message_5.doStartTag();
                      if (_jspx_th_liferay$1ui_message_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_5);
                      out.write(")</span>\n\t");
                    }
                    if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                    out.write("\n\n\t");

	List<String> names = new ArrayList<String>();

	List<String> organizationNames = SitesUtil.getOrganizationNames(curGroup, user);

	names.addAll(organizationNames);

	boolean organizationUser = !organizationNames.isEmpty();

	row.setParameter("organizationUser", organizationUser);

	List<String> userGroupNames = SitesUtil.getUserGroupNames(curGroup, user);

	names.addAll(userGroupNames);

	boolean userGroupUser = !userGroupNames.isEmpty();

	row.setParameter("userGroupUser", userGroupUser);

	if (organizationUser || userGroupUser) {
		String message = StringPool.BLANK;

		if (names.size() == 1) {
			message = LanguageUtil.format(request, "you-are-a-member-of-x-because-you-belong-to-x", new Object[] {HtmlUtil.escape(curGroup.getDescriptiveName(locale)), HtmlUtil.escape(names.get(0))}, false);
		}
		else {
			message = LanguageUtil.format(request, "you-are-a-member-of-x-because-you-belong-to-x-and-x", new Object[] {HtmlUtil.escape(curGroup.getDescriptiveName(locale)), HtmlUtil.escape(StringUtil.merge(names.subList(0, names.size() - 1).toArray(new String[names.size() - 1]), ", ")), HtmlUtil.escape(names.get(names.size() - 1))}, false);
		}
	
                    out.write("\n\n\t\t");
                    //  liferay-ui:icon-help
                    com.liferay.taglib.ui.IconHelpTag _jspx_th_liferay$1ui_icon$1help_0 = (com.liferay.taglib.ui.IconHelpTag) _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.get(com.liferay.taglib.ui.IconHelpTag.class);
                    _jspx_th_liferay$1ui_icon$1help_0.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_icon$1help_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_2);
                    _jspx_th_liferay$1ui_icon$1help_0.setMessage( message );
                    int _jspx_eval_liferay$1ui_icon$1help_0 = _jspx_th_liferay$1ui_icon$1help_0.doStartTag();
                    if (_jspx_th_liferay$1ui_icon$1help_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.reuse(_jspx_th_liferay$1ui_icon$1help_0);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_icon$1help_message_nobody.reuse(_jspx_th_liferay$1ui_icon$1help_0);
                    out.write("\n\n\t");

	}
	
                    out.write('\n');
                    out.write('\n');
                    int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_2.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_orderable_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_2);
                out.write('\n');
                out.write('\n');
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_3 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_3.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_3);
                _jspx_th_liferay$1ui_search$1container$1column$1text_3.setCssClass("table-cell-expand-smaller table-cell-ws-nowrap");
                _jspx_th_liferay$1ui_search$1container$1column$1text_3.setName("child-sites");
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_3 = _jspx_th_liferay$1ui_search$1container$1column$1text_3.doStartTag();
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_search$1container$1column$1text_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_3.doInitBody();
                  }
                  do {
                    out.write('\n');
                    out.write('	');
                    //  c:choose
                    com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_4 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
                    _jspx_th_c_choose_4.setPageContext(_jspx_page_context);
                    _jspx_th_c_choose_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_3);
                    int _jspx_eval_c_choose_4 = _jspx_th_c_choose_4.doStartTag();
                    if (_jspx_eval_c_choose_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t");
                      //  c:when
                      com.liferay.taglib.core.WhenTag _jspx_th_c_when_5 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
                      _jspx_th_c_when_5.setPageContext(_jspx_page_context);
                      _jspx_th_c_when_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_4);
                      _jspx_th_c_when_5.setTest( hasAddChildSitePermisison && GroupPermissionUtil.contains(permissionChecker, curGroup, ActionKeys.VIEW) );
                      int _jspx_eval_c_when_5 = _jspx_th_c_when_5.doStartTag();
                      if (_jspx_eval_c_when_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t");
                        //  aui:a
                        com.liferay.taglib.aui.ATag _jspx_th_aui_a_5 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_href_cssClass.get(com.liferay.taglib.aui.ATag.class);
                        _jspx_th_aui_a_5.setPageContext(_jspx_page_context);
                        _jspx_th_aui_a_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_5);
                        _jspx_th_aui_a_5.setCssClass("table-link");
                        _jspx_th_aui_a_5.setHref( (viewSubsitesURL != null) ? viewSubsitesURL.toString() : StringPool.BLANK );
                        int _jspx_eval_aui_a_5 = _jspx_th_aui_a_5.doStartTag();
                        if (_jspx_eval_aui_a_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.write("\n\t\t\t\t<span class=\"child-sites-text\">");
                          //  liferay-ui:message
                          com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_6 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                          _jspx_th_liferay$1ui_message_6.setPageContext(_jspx_page_context);
                          _jspx_th_liferay$1ui_message_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_a_5);
                          _jspx_th_liferay$1ui_message_6.setArguments( String.valueOf(childSites.size()) );
                          _jspx_th_liferay$1ui_message_6.setKey("x-child-sites");
                          int _jspx_eval_liferay$1ui_message_6 = _jspx_th_liferay$1ui_message_6.doStartTag();
                          if (_jspx_th_liferay$1ui_message_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                            _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_6);
                            return;
                          }
                          _jspx_tagPool_liferay$1ui_message_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_6);
                          out.write("</span>\n\t\t\t");
                        }
                        if (_jspx_th_aui_a_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_a_href_cssClass.reuse(_jspx_th_aui_a_5);
                          return;
                        }
                        _jspx_tagPool_aui_a_href_cssClass.reuse(_jspx_th_aui_a_5);
                        out.write("\n\t\t");
                      }
                      if (_jspx_th_c_when_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_5);
                        return;
                      }
                      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_5);
                      out.write("\n\t\t");
                      //  c:otherwise
                      com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_4 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
                      _jspx_th_c_otherwise_4.setPageContext(_jspx_page_context);
                      _jspx_th_c_otherwise_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_4);
                      int _jspx_eval_c_otherwise_4 = _jspx_th_c_otherwise_4.doStartTag();
                      if (_jspx_eval_c_otherwise_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t");
                        out.print( StringPool.DASH );
                        out.write("\n\t\t");
                      }
                      if (_jspx_th_c_otherwise_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_4);
                        return;
                      }
                      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_4);
                      out.write('\n');
                      out.write('	');
                    }
                    if (_jspx_th_c_choose_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_4);
                      return;
                    }
                    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_4);
                    out.write('\n');
                    int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_3.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_3);
                out.write('\n');
                out.write('\n');
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_4 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_4.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_3);
                _jspx_th_liferay$1ui_search$1container$1column$1text_4.setCssClass("table-cell-expand-smallest table-cell-ws-nowrap");
                _jspx_th_liferay$1ui_search$1container$1column$1text_4.setName("membership-type");
                _jspx_th_liferay$1ui_search$1container$1column$1text_4.setValue( LanguageUtil.get(request, curGroup.getTypeLabel()) );
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_4 = _jspx_th_liferay$1ui_search$1container$1column$1text_4.doStartTag();
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_4);
                out.write('\n');
                out.write('\n');
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_5 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_5.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_3);
                _jspx_th_liferay$1ui_search$1container$1column$1text_5.setCssClass("table-cell-expand-smallest table-cell-minw-150");
                _jspx_th_liferay$1ui_search$1container$1column$1text_5.setName("members");
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_5 = _jspx_th_liferay$1ui_search$1container$1column$1text_5.doStartTag();
                if (_jspx_eval_liferay$1ui_search$1container$1column$1text_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
                    out = _jspx_page_context.pushBody();
                    _jspx_th_liferay$1ui_search$1container$1column$1text_5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
                    _jspx_th_liferay$1ui_search$1container$1column$1text_5.doInitBody();
                  }
                  do {
                    out.write("\n\t<span onmouseover=\"Liferay.Portal.ToolTip.show(this, '");
                    //  liferay-ui:message
                    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_7 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_unicode_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                    _jspx_th_liferay$1ui_message_7.setPageContext(_jspx_page_context);
                    _jspx_th_liferay$1ui_message_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_5);
                    _jspx_th_liferay$1ui_message_7.setKey("inherited-memberships-are-not-included-in-members-count");
                    _jspx_th_liferay$1ui_message_7.setUnicode( true );
                    int _jspx_eval_liferay$1ui_message_7 = _jspx_th_liferay$1ui_message_7.doStartTag();
                    if (_jspx_th_liferay$1ui_message_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_liferay$1ui_message_unicode_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
                      return;
                    }
                    _jspx_tagPool_liferay$1ui_message_unicode_key_nobody.reuse(_jspx_th_liferay$1ui_message_7);
                    out.write("');\">\n\n\t\t");

		int usersCount = UserLocalServiceUtil.getGroupUsersCount(curGroup.getGroupId());
		
                    out.write("\n\n\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_2.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_5);
                    _jspx_th_c_if_2.setTest( usersCount > 0 );
                    int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
                    if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t<div class=\"user-count\">\n\t\t\t\t");
                      out.print( LanguageUtil.format(request, usersCount > 1 ? "x-users" : "x-user", usersCount, false) );
                      out.write("\n\t\t\t</div>\n\t\t");
                    }
                    if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                    out.write("\n\n\t\t");

		int organizationsCount = OrganizationLocalServiceUtil.getGroupOrganizationsCount(curGroup.getGroupId());
		
                    out.write("\n\n\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_3.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_5);
                    _jspx_th_c_if_3.setTest( organizationsCount > 0 );
                    int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
                    if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t<div class=\"organization-count\">\n\t\t\t\t");
                      out.print( LanguageUtil.format(request, organizationsCount > 1 ? "x-organizations" : "x-organization", organizationsCount, false) );
                      out.write("\n\t\t\t</div>\n\t\t");
                    }
                    if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                    out.write("\n\n\t\t");

		int userGroupsCount = UserGroupLocalServiceUtil.getGroupUserGroupsCount(curGroup.getGroupId());
		
                    out.write("\n\n\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_4.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_5);
                    _jspx_th_c_if_4.setTest( userGroupsCount > 0 );
                    int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
                    if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t<div class=\"user-group-count\">\n\t\t\t\t");
                      out.print( LanguageUtil.format(request, userGroupsCount > 1 ? "x-user-groups" : "x-user-group", userGroupsCount, false) );
                      out.write("\n\t\t\t</div>\n\t\t");
                    }
                    if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                    out.write("\n\n\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_5.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container$1column$1text_5);
                    _jspx_th_c_if_5.setTest( (usersCount + organizationsCount + userGroupsCount) <= 0 );
                    int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
                    if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t0\n\t\t");
                    }
                    if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                    out.write("\n\t</span>\n");
                    int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1column$1text_5.doAfterBody();
                    if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                      break;
                  } while (true);
                  if (_jspx_eval_liferay$1ui_search$1container$1column$1text_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
                    out = _jspx_page_context.popBody();
                }
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_5);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_name_cssClass.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_5);
                out.write('\n');
                out.write('\n');
                //  c:if
                com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                _jspx_th_c_if_6.setPageContext(_jspx_page_context);
                _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_3);
                _jspx_th_c_if_6.setTest( PropsValues.LIVE_USERS_ENABLED );
                int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
                if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write('\n');
                  out.write('	');
                  //  liferay-ui:search-container-column-text
                  com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_6 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_6.setPageContext(_jspx_page_context);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_6);
                  _jspx_th_liferay$1ui_search$1container$1column$1text_6.setName("online-now");
                  _jspx_th_liferay$1ui_search$1container$1column$1text_6.setValue( String.valueOf(LiveUsers.getGroupUsersCount(company.getCompanyId(), curGroup.getGroupId())) );
                  int _jspx_eval_liferay$1ui_search$1container$1column$1text_6 = _jspx_th_liferay$1ui_search$1container$1column$1text_6.doStartTag();
                  if (_jspx_th_liferay$1ui_search$1container$1column$1text_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_6);
                    return;
                  }
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_6);
                  out.write('\n');
                }
                if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                  return;
                }
                _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                out.write('\n');
                out.write('\n');
                //  liferay-ui:search-container-column-text
                com.liferay.taglib.ui.SearchContainerColumnTextTag _jspx_th_liferay$1ui_search$1container$1column$1text_7 = (com.liferay.taglib.ui.SearchContainerColumnTextTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.get(com.liferay.taglib.ui.SearchContainerColumnTextTag.class);
                _jspx_th_liferay$1ui_search$1container$1column$1text_7.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_search$1container$1column$1text_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_3);
                _jspx_th_liferay$1ui_search$1container$1column$1text_7.setCssClass("table-cell-smallest table-cell-ws-nowrap table-column-text-center");
                _jspx_th_liferay$1ui_search$1container$1column$1text_7.setName("active");
                _jspx_th_liferay$1ui_search$1container$1column$1text_7.setValue( LanguageUtil.get(request, (curGroup.isActive() ? "yes" : "no")) );
                int _jspx_eval_liferay$1ui_search$1container$1column$1text_7 = _jspx_th_liferay$1ui_search$1container$1column$1text_7.doStartTag();
                if (_jspx_th_liferay$1ui_search$1container$1column$1text_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_7);
                  return;
                }
                _jspx_tagPool_liferay$1ui_search$1container$1column$1text_value_name_cssClass_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1text_7);
                out.write('\n');
                out.write('\n');
                if (_jspx_meth_liferay$1ui_search$1container$1column$1jsp_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_3, _jspx_page_context))
                  return;
                out.write("\n\t\t\t");
              }
              if (_jspx_th_c_otherwise_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_3);
                return;
              }
              _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_3);
              out.write("\n\t\t");
            }
            if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
              return;
            }
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
            out.write('\n');
            out.write('	');
            int evalDoAfterBody = _jspx_th_liferay$1ui_search$1container$1row_0.doAfterBody();
            index = (java.lang.Integer) _jspx_page_context.findAttribute("index");
            curGroup = (com.liferay.portal.kernel.model.Group) _jspx_page_context.findAttribute("curGroup");
            row = (com.liferay.portal.kernel.dao.search.ResultRow) _jspx_page_context.findAttribute("row");
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
          if (_jspx_eval_liferay$1ui_search$1container$1row_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
            out = _jspx_page_context.popBody();
        }
        if (_jspx_th_liferay$1ui_search$1container$1row_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_search$1container$1row_rowVar_rowIdProperty_modelVar_keyProperty_escapedModel_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_search$1container$1row_rowVar_rowIdProperty_modelVar_keyProperty_escapedModel_className.reuse(_jspx_th_liferay$1ui_search$1container$1row_0);
        out.write("\n\n\t");
        //  liferay-ui:search-iterator
        com.liferay.taglib.ui.SearchIteratorTag _jspx_th_liferay$1ui_search$1iterator_0 = (com.liferay.taglib.ui.SearchIteratorTag) _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody.get(com.liferay.taglib.ui.SearchIteratorTag.class);
        _jspx_th_liferay$1ui_search$1iterator_0.setPageContext(_jspx_page_context);
        _jspx_th_liferay$1ui_search$1iterator_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_search$1container_0);
        _jspx_th_liferay$1ui_search$1iterator_0.setDisplayStyle( displayStyle );
        _jspx_th_liferay$1ui_search$1iterator_0.setMarkupView("lexicon");
        int _jspx_eval_liferay$1ui_search$1iterator_0 = _jspx_th_liferay$1ui_search$1iterator_0.doStartTag();
        if (_jspx_th_liferay$1ui_search$1iterator_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
          return;
        }
        _jspx_tagPool_liferay$1ui_search$1iterator_markupView_displayStyle_nobody.reuse(_jspx_th_liferay$1ui_search$1iterator_0);
        out.write('\n');
      }
      if (_jspx_th_liferay$1ui_search$1container_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1ui_search$1container_searchContainer_rowChecker_id.reuse(_jspx_th_liferay$1ui_search$1container_0);
        return;
      }
      _jspx_tagPool_liferay$1ui_search$1container_searchContainer_rowChecker_id.reuse(_jspx_th_liferay$1ui_search$1container_0);
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

  private boolean _jspx_meth_c_otherwise_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_choose_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
    _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
    _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
    int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
    if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t\t\t\t\t");
      if (_jspx_meth_liferay$1ui_search$1container$1column$1icon_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_0, _jspx_page_context))
        return true;
      out.write("\n\t\t\t\t\t");
    }
    if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
      return true;
    }
    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1icon_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-icon
    com.liferay.taglib.ui.SearchContainerColumnIconTag _jspx_th_liferay$1ui_search$1container$1column$1icon_0 = (com.liferay.taglib.ui.SearchContainerColumnIconTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_icon_nobody.get(com.liferay.taglib.ui.SearchContainerColumnIconTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1icon_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
    _jspx_th_liferay$1ui_search$1container$1column$1icon_0.setIcon("sites");
    int _jspx_eval_liferay$1ui_search$1container$1column$1icon_0 = _jspx_th_liferay$1ui_search$1container$1column$1icon_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_icon_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1icon_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1icon_icon_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1icon_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
    _jspx_th_liferay$1ui_message_0.setKey("active");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }

  private boolean _jspx_meth_c_otherwise_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_choose_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_1 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
    _jspx_th_c_otherwise_1.setPageContext(_jspx_page_context);
    _jspx_th_c_otherwise_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_2);
    int _jspx_eval_c_otherwise_1 = _jspx_th_c_otherwise_1.doStartTag();
    if (_jspx_eval_c_otherwise_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      out.write("\n\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_liferay$1ui_message_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_otherwise_1, _jspx_page_context))
        return true;
      out.write("\n\t\t\t\t\t\t\t\t");
    }
    if (_jspx_th_c_otherwise_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
      return true;
    }
    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_1);
    _jspx_th_liferay$1ui_message_1.setKey("not-active");
    int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
    if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1jsp_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-jsp
    com.liferay.taglib.ui.SearchContainerColumnJSPTag _jspx_th_liferay$1ui_search$1container$1column$1jsp_0 = (com.liferay.taglib.ui.SearchContainerColumnJSPTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.get(com.liferay.taglib.ui.SearchContainerColumnJSPTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.setPath("/site_action.jsp");
    int _jspx_eval_liferay$1ui_search$1container$1column$1jsp_0 = _jspx_th_liferay$1ui_search$1container$1column$1jsp_0.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1jsp_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_search$1container$1column$1jsp_1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:search-container-column-jsp
    com.liferay.taglib.ui.SearchContainerColumnJSPTag _jspx_th_liferay$1ui_search$1container$1column$1jsp_1 = (com.liferay.taglib.ui.SearchContainerColumnJSPTag) _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.get(com.liferay.taglib.ui.SearchContainerColumnJSPTag.class);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_3);
    _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.setPath("/site_action.jsp");
    int _jspx_eval_liferay$1ui_search$1container$1column$1jsp_1 = _jspx_th_liferay$1ui_search$1container$1column$1jsp_1.doStartTag();
    if (_jspx_th_liferay$1ui_search$1container$1column$1jsp_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_search$1container$1column$1jsp_path_nobody.reuse(_jspx_th_liferay$1ui_search$1container$1column$1jsp_1);
    return false;
  }
}
