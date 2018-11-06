package org.apache.jsp.bookmarks;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.KeyValuePairComparator;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.social.bookmarks.SocialBookmark;
import com.liferay.social.bookmarks.taglib.internal.util.SocialBookmarksRegistryUtil;
import com.liferay.social.bookmarks.taglib.internal.util.SocialBookmarksTagUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class page_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/bookmarks/init.jsp");
    _jspx_dependants.add("/init.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_html$1bottom_outputKey;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1util_html$1top_outputKey;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_label_icon_dropdownItems_componentId_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1social$1bookmarks_bookmark_url_type_title_target_displayStyle_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_script_sandbox;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_icon_dropdownItems_componentId_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_liferay$1util_html$1bottom_outputKey = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1util_html$1top_outputKey = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_label_icon_dropdownItems_componentId_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1social$1bookmarks_bookmark_url_type_title_target_displayStyle_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_script_sandbox = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_icon_dropdownItems_componentId_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_liferay$1util_html$1bottom_outputKey.release();
    _jspx_tagPool_liferay$1util_html$1top_outputKey.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_label_icon_dropdownItems_componentId_nobody.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_liferay$1social$1bookmarks_bookmark_url_type_title_target_displayStyle_nobody.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_aui_script.release();
    _jspx_tagPool_aui_script_sandbox.release();
    _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_icon_dropdownItems_componentId_nobody.release();
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
      out.write('\n');
      out.write('\n');
      out.write("\n\n\n\n\n\n\n\n\n\n\n\n");
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

String className = GetterUtil.getString((String)request.getAttribute("liferay-social-bookmarks:bookmarks:className"));
long classPK = GetterUtil.getLong((Long)request.getAttribute("liferay-social-bookmarks:bookmarks:classPK"));
String displayStyle = GetterUtil.getString((String)request.getAttribute("liferay-social-bookmarks:bookmarks:displayStyle"));
int maxInlineItems = GetterUtil.getInteger(request.getAttribute("liferay-social-bookmarks:bookmarks:maxInlineItems"));
String target = GetterUtil.getString((String)request.getAttribute("liferay-social-bookmarks:bookmarks:target"));
String title = GetterUtil.getString((String)request.getAttribute("liferay-social-bookmarks:bookmarks:title"));
String[] types = SocialBookmarksRegistryUtil.getValidTypes((String[])request.getAttribute("liferay-social-bookmarks:bookmarks:types"));
String url = GetterUtil.getString((String)request.getAttribute("liferay-social-bookmarks:bookmarks:url"));

      out.write('\n');
      out.write('\n');

String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_social_bookmarks_page") + StringPool.UNDERLINE;
String dropdownMenuComponentId = randomNamespace + "socialBookmarksDropdownMenu";

      out.write('\n');
      out.write('\n');
      //  liferay-util:html-top
      com.liferay.taglib.util.HtmlTopTag _jspx_th_liferay$1util_html$1top_0 = (com.liferay.taglib.util.HtmlTopTag) _jspx_tagPool_liferay$1util_html$1top_outputKey.get(com.liferay.taglib.util.HtmlTopTag.class);
      _jspx_th_liferay$1util_html$1top_0.setPageContext(_jspx_page_context);
      _jspx_th_liferay$1util_html$1top_0.setParent(null);
      _jspx_th_liferay$1util_html$1top_0.setOutputKey("social_bookmarks_css");
      int _jspx_eval_liferay$1util_html$1top_0 = _jspx_th_liferay$1util_html$1top_0.doStartTag();
      if (_jspx_eval_liferay$1util_html$1top_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_liferay$1util_html$1top_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_liferay$1util_html$1top_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_liferay$1util_html$1top_0.doInitBody();
        }
        do {
          out.write("\n\t<link href=\"");
          out.print( PortalUtil.getStaticResourceURL(request, application.getContextPath() + "/css/main.css") );
          out.write("\" rel=\"stylesheet\" type=\"text/css\" />\n");
          int evalDoAfterBody = _jspx_th_liferay$1util_html$1top_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_liferay$1util_html$1top_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_liferay$1util_html$1top_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_liferay$1util_html$1top_outputKey.reuse(_jspx_th_liferay$1util_html$1top_0);
        return;
      }
      _jspx_tagPool_liferay$1util_html$1top_outputKey.reuse(_jspx_th_liferay$1util_html$1top_0);
      out.write("\n\n<div class=\"taglib-social-bookmarks\" id=\"");
      out.print( randomNamespace );
      out.write("socialBookmarks\">\n\t");
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
        _jspx_th_c_when_0.setTest( displayStyle.equals("menu") );
        int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
        if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t\t");
          //  clay:dropdown-menu
          com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag _jspx_th_clay_dropdown$1menu_0 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag) _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_label_icon_dropdownItems_componentId_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag.class);
          _jspx_th_clay_dropdown$1menu_0.setPageContext(_jspx_page_context);
          _jspx_th_clay_dropdown$1menu_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_clay_dropdown$1menu_0.setComponentId( dropdownMenuComponentId );
          _jspx_th_clay_dropdown$1menu_0.setDropdownItems( SocialBookmarksTagUtil.getDropdownItems(request.getLocale(), types, className, classPK, title, url) );
          _jspx_th_clay_dropdown$1menu_0.setIcon("share");
          _jspx_th_clay_dropdown$1menu_0.setLabel( LanguageUtil.get(request, "share") );
          _jspx_th_clay_dropdown$1menu_0.setStyle("secondary");
          _jspx_th_clay_dropdown$1menu_0.setTriggerCssClasses("btn-outline-borderless btn-outline-secondary btn-sm");
          int _jspx_eval_clay_dropdown$1menu_0 = _jspx_th_clay_dropdown$1menu_0.doStartTag();
          if (_jspx_th_clay_dropdown$1menu_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_label_icon_dropdownItems_componentId_nobody.reuse(_jspx_th_clay_dropdown$1menu_0);
            return;
          }
          _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_label_icon_dropdownItems_componentId_nobody.reuse(_jspx_th_clay_dropdown$1menu_0);
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
          out.write("\n\t\t\t<ul class=\"list-unstyled ");
          out.print( displayStyle );
          out.write("\">\n\n\t\t\t\t");

				for (int i = 0; i < Math.min(types.length, maxInlineItems); i++) {
					SocialBookmark socialBookmark = SocialBookmarksRegistryUtil.getSocialBookmark(types[i]);
					String styleClass = "taglib-social-bookmark-" + types[i];
				
          out.write("\n\n\t\t\t\t\t<li class=\"taglib-social-bookmark ");
          out.print( styleClass );
          out.write("\" onClick=\"");
          out.print( "return " + SocialBookmarksTagUtil.getClickJSCall(className, classPK, types[i], socialBookmark.getPostURL(title, url), url) );
          out.write("\">\n\t\t\t\t\t\t");
          //  liferay-social-bookmarks:bookmark
          com.liferay.social.bookmarks.taglib.servlet.taglib.SocialBookmarkTag _jspx_th_liferay$1social$1bookmarks_bookmark_0 = (com.liferay.social.bookmarks.taglib.servlet.taglib.SocialBookmarkTag) _jspx_tagPool_liferay$1social$1bookmarks_bookmark_url_type_title_target_displayStyle_nobody.get(com.liferay.social.bookmarks.taglib.servlet.taglib.SocialBookmarkTag.class);
          _jspx_th_liferay$1social$1bookmarks_bookmark_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1social$1bookmarks_bookmark_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
          _jspx_th_liferay$1social$1bookmarks_bookmark_0.setDisplayStyle( displayStyle );
          _jspx_th_liferay$1social$1bookmarks_bookmark_0.setTarget( target );
          _jspx_th_liferay$1social$1bookmarks_bookmark_0.setTitle( title );
          _jspx_th_liferay$1social$1bookmarks_bookmark_0.setType( types[i] );
          _jspx_th_liferay$1social$1bookmarks_bookmark_0.setUrl( url );
          int _jspx_eval_liferay$1social$1bookmarks_bookmark_0 = _jspx_th_liferay$1social$1bookmarks_bookmark_0.doStartTag();
          if (_jspx_th_liferay$1social$1bookmarks_bookmark_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1social$1bookmarks_bookmark_url_type_title_target_displayStyle_nobody.reuse(_jspx_th_liferay$1social$1bookmarks_bookmark_0);
            return;
          }
          _jspx_tagPool_liferay$1social$1bookmarks_bookmark_url_type_title_target_displayStyle_nobody.reuse(_jspx_th_liferay$1social$1bookmarks_bookmark_0);
          out.write("\n\t\t\t\t\t</li>\n\n\t\t\t\t");

				}
				
          out.write("\n\n\t\t\t</ul>\n\n\t\t\t");

			if (types.length > maxInlineItems) {
			
          out.write("\n\n\t\t\t\t");

				String[] remainingTypes = ArrayUtil.subset(types, maxInlineItems, types.length);
				
          out.write("\n\n\t\t\t\t");
          //  clay:dropdown-menu
          com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag _jspx_th_clay_dropdown$1menu_1 = (com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag) _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_icon_dropdownItems_componentId_nobody.get(com.liferay.frontend.taglib.clay.servlet.taglib.soy.DropdownMenuTag.class);
          _jspx_th_clay_dropdown$1menu_1.setPageContext(_jspx_page_context);
          _jspx_th_clay_dropdown$1menu_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
          _jspx_th_clay_dropdown$1menu_1.setComponentId( dropdownMenuComponentId );
          _jspx_th_clay_dropdown$1menu_1.setDropdownItems( SocialBookmarksTagUtil.getDropdownItems(request.getLocale(), remainingTypes, className, classPK, title, url) );
          _jspx_th_clay_dropdown$1menu_1.setIcon("share");
          _jspx_th_clay_dropdown$1menu_1.setStyle("secondary");
          _jspx_th_clay_dropdown$1menu_1.setTriggerCssClasses("btn-monospaced btn-outline-borderless btn-outline-secondary btn-sm");
          int _jspx_eval_clay_dropdown$1menu_1 = _jspx_th_clay_dropdown$1menu_1.doStartTag();
          if (_jspx_th_clay_dropdown$1menu_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_icon_dropdownItems_componentId_nobody.reuse(_jspx_th_clay_dropdown$1menu_1);
            return;
          }
          _jspx_tagPool_clay_dropdown$1menu_triggerCssClasses_style_icon_dropdownItems_componentId_nobody.reuse(_jspx_th_clay_dropdown$1menu_1);
          out.write("\n\n\t\t\t");

			}
			
          out.write("\n\n\t\t");
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
      if (_jspx_meth_liferay$1util_html$1bottom_0(_jspx_page_context))
        return;
      out.write("\n\n\t");
      //  aui:script
      com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_1 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script_sandbox.get(com.liferay.taglib.aui.ScriptTag.class);
      _jspx_th_aui_script_1.setPageContext(_jspx_page_context);
      _jspx_th_aui_script_1.setParent(null);
      _jspx_th_aui_script_1.setSandbox( true );
      int _jspx_eval_aui_script_1 = _jspx_th_aui_script_1.doStartTag();
      if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_aui_script_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_aui_script_1.doInitBody();
        }
        do {
          out.write("\n\t\tLiferay.componentReady('");
          out.print( dropdownMenuComponentId );
          out.write("').then(\n\t\t\tfunction(dropdownMenu) {\n\t\t\t\tdropdownMenu.on(\n\t\t\t\t\t['itemClicked'],\n\t\t\t\t\tfunction(event) {\n\t\t\t\t\t\tevent.preventDefault();\n\n\t\t\t\t\t\tvar data = event.data.item.data;\n\n\t\t\t\t\t\tsocialBookmarks_handleItemClick(\n\t\t\t\t\t\t\tdata.className,\n\t\t\t\t\t\t\tparseInt(data.classPK),\n\t\t\t\t\t\t\tdata.type,\n\t\t\t\t\t\t\tdata.postURL,\n\t\t\t\t\t\t\tdata.url\n\t\t\t\t\t\t);\n\t\t\t\t\t}\n\t\t\t\t);\n\t\t\t}\n\t\t);\n\t");
          int evalDoAfterBody = _jspx_th_aui_script_1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_aui_script_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
          out = _jspx_page_context.popBody();
      }
      if (_jspx_th_aui_script_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_aui_script_sandbox.reuse(_jspx_th_aui_script_1);
        return;
      }
      _jspx_tagPool_aui_script_sandbox.reuse(_jspx_th_aui_script_1);
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

  private boolean _jspx_meth_liferay$1util_html$1bottom_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-util:html-bottom
    com.liferay.taglib.util.HtmlBottomTag _jspx_th_liferay$1util_html$1bottom_0 = (com.liferay.taglib.util.HtmlBottomTag) _jspx_tagPool_liferay$1util_html$1bottom_outputKey.get(com.liferay.taglib.util.HtmlBottomTag.class);
    _jspx_th_liferay$1util_html$1bottom_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1util_html$1bottom_0.setParent(null);
    _jspx_th_liferay$1util_html$1bottom_0.setOutputKey("social_bookmarks");
    int _jspx_eval_liferay$1util_html$1bottom_0 = _jspx_th_liferay$1util_html$1bottom_0.doStartTag();
    if (_jspx_eval_liferay$1util_html$1bottom_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_liferay$1util_html$1bottom_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_liferay$1util_html$1bottom_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_liferay$1util_html$1bottom_0.doInitBody();
      }
      do {
        out.write("\n\t\t");
        if (_jspx_meth_aui_script_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_liferay$1util_html$1bottom_0, _jspx_page_context))
          return true;
        out.write('\n');
        out.write('	');
        int evalDoAfterBody = _jspx_th_liferay$1util_html$1bottom_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_liferay$1util_html$1bottom_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_liferay$1util_html$1bottom_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1util_html$1bottom_outputKey.reuse(_jspx_th_liferay$1util_html$1bottom_0);
      return true;
    }
    _jspx_tagPool_liferay$1util_html$1bottom_outputKey.reuse(_jspx_th_liferay$1util_html$1bottom_0);
    return false;
  }

  private boolean _jspx_meth_aui_script_0(javax.servlet.jsp.tagext.JspTag _jspx_th_liferay$1util_html$1bottom_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  aui:script
    com.liferay.taglib.aui.ScriptTag _jspx_th_aui_script_0 = (com.liferay.taglib.aui.ScriptTag) _jspx_tagPool_aui_script.get(com.liferay.taglib.aui.ScriptTag.class);
    _jspx_th_aui_script_0.setPageContext(_jspx_page_context);
    _jspx_th_aui_script_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1util_html$1bottom_0);
    int _jspx_eval_aui_script_0 = _jspx_th_aui_script_0.doStartTag();
    if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_aui_script_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_aui_script_0.doInitBody();
      }
      do {
        out.write("\n\t\t\tfunction socialBookmarks_handleItemClick(className, classPK, type, postURL, url) {\n\t\t\t\tvar SHARE_WINDOW_HEIGHT = 436;\n\t\t\t\tvar SHARE_WINDOW_WIDTH = 626;\n\n\t\t\t\tvar shareWindowFeatures = [\n\t\t\t\t\t'left=' + (window.innerWidth / 2 - SHARE_WINDOW_WIDTH / 2),\n\t\t\t\t\t'height=' + SHARE_WINDOW_HEIGHT,\n\t\t\t\t\t'toolbar=0',\n\t\t\t\t\t'top=' + (window.innerHeight / 2 - SHARE_WINDOW_HEIGHT / 2),\n\t\t\t\t\t'status=0',\n\t\t\t\t\t'width=' + SHARE_WINDOW_WIDTH\n\t\t\t\t];\n\n\t\t\t\twindow.open(postURL, null, shareWindowFeatures.join()).focus();\n\n\t\t\t\tLiferay.fire(\n\t\t\t\t\t'socialBookmarks:share',\n\t\t\t\t\t{\n\t\t\t\t\t\tclassName: className,\n\t\t\t\t\t\tclassPK: classPK,\n\t\t\t\t\t\ttype: type,\n\t\t\t\t\t\turl: url\n\t\t\t\t\t}\n\t\t\t\t);\n\n\t\t\t\treturn false;\n\t\t\t}\n\t\t");
        int evalDoAfterBody = _jspx_th_aui_script_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_aui_script_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_aui_script_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_0);
      return true;
    }
    _jspx_tagPool_aui_script.reuse(_jspx_th_aui_script_0);
    return false;
  }
}
