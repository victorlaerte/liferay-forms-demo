package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.marketplace.app.manager.web.internal.constants.BundleConstants;
import com.liferay.marketplace.app.manager.web.internal.constants.BundleStateConstants;
import com.liferay.marketplace.app.manager.web.internal.dao.search.MarketplaceAppManagerResultRowSplitter;
import com.liferay.marketplace.app.manager.web.internal.display.context.AppManagerDisplayContext;
import com.liferay.marketplace.app.manager.web.internal.display.context.AppManagerSearchResultsManagementToolbarDisplayContext;
import com.liferay.marketplace.app.manager.web.internal.display.context.ViewAppsManagerManagementToolbarDisplayContext;
import com.liferay.marketplace.app.manager.web.internal.display.context.ViewModuleManagementToolbarDisplayContext;
import com.liferay.marketplace.app.manager.web.internal.display.context.ViewModulesManagementToolbarDisplayContext;
import com.liferay.marketplace.app.manager.web.internal.util.AppDisplay;
import com.liferay.marketplace.app.manager.web.internal.util.MarketplaceAppManagerUtil;
import com.liferay.marketplace.exception.FileExtensionException;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.upload.UploadException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.search.ResultRow;
import java.util.Dictionary;
import java.util.List;
import javax.portlet.PortletURL;
import org.osgi.framework.Bundle;

public final class bundle_005faction_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/init.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_actionURL_var_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1delete_url_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_param_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1frontend_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_url_message_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_portlet_actionURL_var_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1delete_url_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_param_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_url_message_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_portlet_actionURL_var_name.release();
    _jspx_tagPool_liferay$1ui_icon$1delete_url_message_nobody.release();
    _jspx_tagPool_portlet_param_value_name_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1frontend_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ui_icon_url_message_nobody.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
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
      out.write("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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

AppManagerDisplayContext appManagerDisplayContext = new AppManagerDisplayContext(request, renderResponse);

      out.write('\n');
      out.write('\n');

String app = ParamUtil.getString(request, "app");
String category = ParamUtil.getString(request, "category");
String state = ParamUtil.getString(request, "state");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Bundle bundle = (Bundle)row.getObject();

      out.write('\n');
      out.write('\n');
      //  liferay-ui:icon-menu
      com.liferay.taglib.ui.IconMenuTag _jspx_th_liferay$1ui_icon$1menu_0 = (com.liferay.taglib.ui.IconMenuTag) _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.get(com.liferay.taglib.ui.IconMenuTag.class);
      _jspx_th_liferay$1ui_icon$1menu_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1ui_icon$1menu_0.setParent(null);
      _jspx_th_liferay$1ui_icon$1menu_0.setDirection("left-side");
      _jspx_th_liferay$1ui_icon$1menu_0.setIcon( StringPool.BLANK );
      _jspx_th_liferay$1ui_icon$1menu_0.setMarkupView("lexicon");
      _jspx_th_liferay$1ui_icon$1menu_0.setMessage( StringPool.BLANK );
      _jspx_th_liferay$1ui_icon$1menu_0.setShowWhenSingleIcon( true );
      int _jspx_eval_liferay$1ui_icon$1menu_0 = _jspx_th_liferay$1ui_icon$1menu_0.doStartTag();
      if (_jspx_eval_liferay$1ui_icon$1menu_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_liferay$1ui_icon$1menu_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_liferay$1ui_icon$1menu_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_liferay$1ui_icon$1menu_0.doInitBody();
        }
        do {
          out.write('\n');
          out.write('	');
          //  c:choose
          com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
          _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
          _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
          int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
          if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  c:when
            com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
            _jspx_th_c_when_0.setPageContext(_jspx_page_context);
            _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
            _jspx_th_c_when_0.setTest( bundle.getState() == BundleStateConstants.ACTIVE );
            int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
            if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  portlet:actionURL
              com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_0 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
              _jspx_th_portlet_actionURL_0.setPageContext(_jspx_page_context);
              _jspx_th_portlet_actionURL_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
              _jspx_th_portlet_actionURL_0.setName("deactivateBundles");
              _jspx_th_portlet_actionURL_0.setVar("deactivateBundlesURL");
              int _jspx_eval_portlet_actionURL_0 = _jspx_th_portlet_actionURL_0.doStartTag();
              if (_jspx_eval_portlet_actionURL_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_0 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_0.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_0);
                _jspx_th_portlet_param_0.setName("redirect");
                _jspx_th_portlet_param_0.setValue( currentURL );
                int _jspx_eval_portlet_param_0 = _jspx_th_portlet_param_0.doStartTag();
                if (_jspx_th_portlet_param_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_0);
                out.write("\n\t\t\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_1 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_1.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_0);
                _jspx_th_portlet_param_1.setName("bundleIds");
                _jspx_th_portlet_param_1.setValue( String.valueOf(bundle.getBundleId()) );
                int _jspx_eval_portlet_param_1 = _jspx_th_portlet_param_1.doStartTag();
                if (_jspx_th_portlet_param_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_1);
                out.write("\n\t\t\t");
              }
              if (_jspx_th_portlet_actionURL_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
                return;
              }
              _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_0);
              java.lang.String deactivateBundlesURL = null;
              deactivateBundlesURL = (java.lang.String) _jspx_page_context.findAttribute("deactivateBundlesURL");
              out.write("\n\n\t\t\t");

			String taglibDeactivateBundlesURL = "javascript:if (confirm(\'" + UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-deactivate-this") + "\')) {submitForm(document.hrefFm, \'" + HtmlUtil.unescape(deactivateBundlesURL.toString()) + "\');};";
			
              out.write("\n\n\t\t\t");
              //  liferay-ui:icon
              com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_0 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
              _jspx_th_liferay$1ui_icon_0.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
              _jspx_th_liferay$1ui_icon_0.setMessage("deactivate");
              _jspx_th_liferay$1ui_icon_0.setUrl( taglibDeactivateBundlesURL );
              int _jspx_eval_liferay$1ui_icon_0 = _jspx_th_liferay$1ui_icon_0.doStartTag();
              if (_jspx_th_liferay$1ui_icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
                return;
              }
              _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
              out.write("\n\t\t");
            }
            if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
              return;
            }
            _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
            out.write("\n\t\t");
            //  c:otherwise
            com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
            _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
            _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
            int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
            if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write("\n\t\t\t");
              //  portlet:actionURL
              com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_1 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
              _jspx_th_portlet_actionURL_1.setPageContext(_jspx_page_context);
              _jspx_th_portlet_actionURL_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
              _jspx_th_portlet_actionURL_1.setName("activateBundles");
              _jspx_th_portlet_actionURL_1.setVar("activateBundlesURL");
              int _jspx_eval_portlet_actionURL_1 = _jspx_th_portlet_actionURL_1.doStartTag();
              if (_jspx_eval_portlet_actionURL_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_2 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_2.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_1);
                _jspx_th_portlet_param_2.setName("redirect");
                _jspx_th_portlet_param_2.setValue( currentURL );
                int _jspx_eval_portlet_param_2 = _jspx_th_portlet_param_2.doStartTag();
                if (_jspx_th_portlet_param_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_2);
                out.write("\n\t\t\t\t");
                //  portlet:param
                com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_3 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
                _jspx_th_portlet_param_3.setPageContext(_jspx_page_context);
                _jspx_th_portlet_param_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_1);
                _jspx_th_portlet_param_3.setName("bundleIds");
                _jspx_th_portlet_param_3.setValue( String.valueOf(bundle.getBundleId()) );
                int _jspx_eval_portlet_param_3 = _jspx_th_portlet_param_3.doStartTag();
                if (_jspx_th_portlet_param_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                  return;
                }
                _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_3);
                out.write("\n\t\t\t");
              }
              if (_jspx_th_portlet_actionURL_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_1);
                return;
              }
              _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_1);
              java.lang.String activateBundlesURL = null;
              activateBundlesURL = (java.lang.String) _jspx_page_context.findAttribute("activateBundlesURL");
              out.write("\n\n\t\t\t");
              //  liferay-ui:icon
              com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_1 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_url_message_nobody.get(com.liferay.taglib.ui.IconTag.class);
              _jspx_th_liferay$1ui_icon_1.setPageContext(_jspx_page_context);
              _jspx_th_liferay$1ui_icon_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
              _jspx_th_liferay$1ui_icon_1.setMessage("activate");
              _jspx_th_liferay$1ui_icon_1.setUrl( activateBundlesURL );
              int _jspx_eval_liferay$1ui_icon_1 = _jspx_th_liferay$1ui_icon_1.doStartTag();
              if (_jspx_th_liferay$1ui_icon_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
                return;
              }
              _jspx_tagPool_liferay$1ui_icon_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
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
          out.write("\n\n\t");
          //  portlet:actionURL
          com.liferay.taglib.portlet.ActionURLTag _jspx_th_portlet_actionURL_2 = (com.liferay.taglib.portlet.ActionURLTag) _jspx_tagPool_portlet_actionURL_var_name.get(com.liferay.taglib.portlet.ActionURLTag.class);
          _jspx_th_portlet_actionURL_2.setPageContext(_jspx_page_context);
          _jspx_th_portlet_actionURL_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
          _jspx_th_portlet_actionURL_2.setName("uninstallBundles");
          _jspx_th_portlet_actionURL_2.setVar("uninstallBundlesURL");
          int _jspx_eval_portlet_actionURL_2 = _jspx_th_portlet_actionURL_2.doStartTag();
          if (_jspx_eval_portlet_actionURL_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_4 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_4.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_2);
            _jspx_th_portlet_param_4.setName("redirect");
            _jspx_th_portlet_param_4.setValue( currentURL );
            int _jspx_eval_portlet_param_4 = _jspx_th_portlet_param_4.doStartTag();
            if (_jspx_th_portlet_param_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_4);
            out.write("\n\t\t");
            //  portlet:param
            com.liferay.taglib.util.ParamTag _jspx_th_portlet_param_5 = (com.liferay.taglib.util.ParamTag) _jspx_tagPool_portlet_param_value_name_nobody.get(com.liferay.taglib.util.ParamTag.class);
            _jspx_th_portlet_param_5.setPageContext(_jspx_page_context);
            _jspx_th_portlet_param_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_portlet_actionURL_2);
            _jspx_th_portlet_param_5.setName("bundleIds");
            _jspx_th_portlet_param_5.setValue( String.valueOf(bundle.getBundleId()) );
            int _jspx_eval_portlet_param_5 = _jspx_th_portlet_param_5.doStartTag();
            if (_jspx_th_portlet_param_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
              return;
            }
            _jspx_tagPool_portlet_param_value_name_nobody.reuse(_jspx_th_portlet_param_5);
            out.write('\n');
            out.write('	');
          }
          if (_jspx_th_portlet_actionURL_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_2);
            return;
          }
          _jspx_tagPool_portlet_actionURL_var_name.reuse(_jspx_th_portlet_actionURL_2);
          java.lang.String uninstallBundlesURL = null;
          uninstallBundlesURL = (java.lang.String) _jspx_page_context.findAttribute("uninstallBundlesURL");
          out.write("\n\n\t");
          //  liferay-ui:icon-delete
          com.liferay.taglib.ui.IconDeleteTag _jspx_th_liferay$1ui_icon$1delete_0 = (com.liferay.taglib.ui.IconDeleteTag) _jspx_tagPool_liferay$1ui_icon$1delete_url_message_nobody.get(com.liferay.taglib.ui.IconDeleteTag.class);
          _jspx_th_liferay$1ui_icon$1delete_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ui_icon$1delete_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ui_icon$1menu_0);
          _jspx_th_liferay$1ui_icon$1delete_0.setMessage("uninstall");
          _jspx_th_liferay$1ui_icon$1delete_0.setUrl( uninstallBundlesURL );
          int _jspx_eval_liferay$1ui_icon$1delete_0 = _jspx_th_liferay$1ui_icon$1delete_0.doStartTag();
          if (_jspx_th_liferay$1ui_icon$1delete_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ui_icon$1delete_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon$1delete_0);
            return;
          }
          _jspx_tagPool_liferay$1ui_icon$1delete_url_message_nobody.reuse(_jspx_th_liferay$1ui_icon$1delete_0);
          out.write('\n');
          int evalDoAfterBody = _jspx_th_liferay$1ui_icon$1menu_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_liferay$1ui_icon$1menu_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_liferay$1ui_icon$1menu_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.reuse(_jspx_th_liferay$1ui_icon$1menu_0);
        return;
      }
      _jspx_tagPool_liferay$1ui_icon$1menu_showWhenSingleIcon_message_markupView_icon_direction.reuse(_jspx_th_liferay$1ui_icon$1menu_0);
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
