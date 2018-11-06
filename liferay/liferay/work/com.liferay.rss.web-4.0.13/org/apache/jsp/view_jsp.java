package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.rss.web.internal.configuration.RSSPortletInstanceConfiguration;
import com.liferay.rss.web.internal.configuration.RSSWebCacheConfiguration;
import com.liferay.rss.web.internal.display.context.RSSDisplayContext;
import com.liferay.rss.web.internal.util.RSSFeed;
import com.liferay.rss.web.internal.util.RSSFeedEntry;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndImage;
import java.text.Format;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.portlet.ValidatorException;

public final class view_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/init-ext.jsp");
    _jspx_dependants.add("/feed.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_a_target_href;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_a_href;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_portlet_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ddm_template$1renderer_entries_displayStyleGroupId_displayStyle_contextObjects_className;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_a_onClick_href;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_icon_message_markupView_label_icon_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1theme_defineObjects_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset$1group_markupView;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset_label_id_cssClass_collapsible_collapsed;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_aui_fieldset;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_aui_a_target_href = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_a_href = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_portlet_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ddm_template$1renderer_entries_displayStyleGroupId_displayStyle_contextObjects_className = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_a_onClick_href = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_icon_message_markupView_label_icon_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1theme_defineObjects_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset$1group_markupView = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset_label_id_cssClass_collapsible_collapsed = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_aui_fieldset = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_aui_a_target_href.release();
    _jspx_tagPool_liferay$1ui_message_key_nobody.release();
    _jspx_tagPool_aui_a_href.release();
    _jspx_tagPool_c_if_test.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_portlet_defineObjects_nobody.release();
    _jspx_tagPool_liferay$1ddm_template$1renderer_entries_displayStyleGroupId_displayStyle_contextObjects_className.release();
    _jspx_tagPool_aui_a_onClick_href.release();
    _jspx_tagPool_liferay$1ui_icon_message_markupView_label_icon_nobody.release();
    _jspx_tagPool_liferay$1theme_defineObjects_nobody.release();
    _jspx_tagPool_aui_fieldset$1group_markupView.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_aui_fieldset_label_id_cssClass_collapsible_collapsed.release();
    _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.release();
    _jspx_tagPool_aui_fieldset.release();
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
      out.write("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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

RSSWebCacheConfiguration rssWebCacheConfiguration = (RSSWebCacheConfiguration)request.getAttribute(RSSWebCacheConfiguration.class.getName());

RSSDisplayContext rssDisplayContext = new RSSDisplayContext(request, rssWebCacheConfiguration);

RSSPortletInstanceConfiguration rssPortletInstanceConfiguration = rssDisplayContext.getRSSPortletInstanceConfiguration();

Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');

List<RSSFeed> rssFeeds = rssDisplayContext.getRSSFeeds();

Map<String, Object> contextObjects = new HashMap<String, Object>();

contextObjects.put("rssDisplayContext", rssDisplayContext);

if (rssFeeds.isEmpty()) {
	renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.TRUE);
}

      out.write('\n');
      out.write('\n');
      //  c:choose
      com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_0 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
      _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
      _jspx_th_c_choose_0.setParent(null);
      int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
      if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        out.write('\n');
        out.write('	');
        //  c:when
        com.liferay.taglib.core.WhenTag _jspx_th_c_when_0 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
        _jspx_th_c_when_0.setPageContext(_jspx_page_context);
        _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
        _jspx_th_c_when_0.setTest( rssFeeds.isEmpty() );
        int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
        if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t<div class=\"alert alert-info text-center\">\n\t\t\t<div>\n\t\t\t\t");
          if (_jspx_meth_liferay$1ui_message_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_when_0, _jspx_page_context))
            return;
          out.write("\n\t\t\t</div>\n\n\t\t\t");
          //  c:if
          com.liferay.taglib.core.IfTag _jspx_th_c_if_0 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
          _jspx_th_c_if_0.setPageContext(_jspx_page_context);
          _jspx_th_c_if_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
          _jspx_th_c_if_0.setTest( rssDisplayContext.isShowConfigurationLink() );
          int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
          if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\t\t\t\t<div>\n\t\t\t\t\t");
            //  aui:a
            com.liferay.taglib.aui.ATag _jspx_th_aui_a_0 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_onClick_href.get(com.liferay.taglib.aui.ATag.class);
            _jspx_th_aui_a_0.setPageContext(_jspx_page_context);
            _jspx_th_aui_a_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_0);
            _jspx_th_aui_a_0.setHref("javascript:;");
            _jspx_th_aui_a_0.setOnClick( portletDisplay.getURLConfigurationJS() );
            int _jspx_eval_aui_a_0 = _jspx_th_aui_a_0.doStartTag();
            if (_jspx_eval_aui_a_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              if (_jspx_meth_liferay$1ui_message_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_a_0, _jspx_page_context))
                return;
            }
            if (_jspx_th_aui_a_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_aui_a_onClick_href.reuse(_jspx_th_aui_a_0);
              return;
            }
            _jspx_tagPool_aui_a_onClick_href.reuse(_jspx_th_aui_a_0);
            out.write("\n\t\t\t\t</div>\n\t\t\t");
          }
          if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
            return;
          }
          _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
          out.write("\n\t\t</div>\n\t");
        }
        if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
          return;
        }
        _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
        out.write('\n');
        out.write('	');
        //  c:otherwise
        com.liferay.taglib.core.OtherwiseTag _jspx_th_c_otherwise_0 = (com.liferay.taglib.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(com.liferay.taglib.core.OtherwiseTag.class);
        _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
        _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
        int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
        if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          out.write("\n\t\t");
          //  liferay-ddm:template-renderer
          com.liferay.dynamic.data.mapping.taglib.servlet.taglib.TemplateRendererTag _jspx_th_liferay$1ddm_template$1renderer_0 = (com.liferay.dynamic.data.mapping.taglib.servlet.taglib.TemplateRendererTag) _jspx_tagPool_liferay$1ddm_template$1renderer_entries_displayStyleGroupId_displayStyle_contextObjects_className.get(com.liferay.dynamic.data.mapping.taglib.servlet.taglib.TemplateRendererTag.class);
          _jspx_th_liferay$1ddm_template$1renderer_0.setPageContext(_jspx_page_context);
          _jspx_th_liferay$1ddm_template$1renderer_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
          _jspx_th_liferay$1ddm_template$1renderer_0.setClassName( RSSFeed.class.getName() );
          _jspx_th_liferay$1ddm_template$1renderer_0.setContextObjects( contextObjects );
          _jspx_th_liferay$1ddm_template$1renderer_0.setDisplayStyle( rssPortletInstanceConfiguration.displayStyle() );
          _jspx_th_liferay$1ddm_template$1renderer_0.setDisplayStyleGroupId( rssDisplayContext.getDisplayStyleGroupId() );
          _jspx_th_liferay$1ddm_template$1renderer_0.setEntries( rssFeeds );
          int _jspx_eval_liferay$1ddm_template$1renderer_0 = _jspx_th_liferay$1ddm_template$1renderer_0.doStartTag();
          if (_jspx_eval_liferay$1ddm_template$1renderer_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            out.write("\n\n\t\t\t");

			for (int i = 0; i < rssFeeds.size(); i++) {
				RSSFeed rssFeed = rssFeeds.get(i);

				boolean last = false;

				if (i == (rssFeeds.size() - 1)) {
					last = true;
				}

				SyndFeed syndFeed = rssFeed.getSyndFeed();
			
            out.write("\n\n\t\t\t\t");
            out.write('\n');
            out.write('\n');
            //  c:choose
            com.liferay.taglib.core.ChooseTag _jspx_th_c_choose_1 = (com.liferay.taglib.core.ChooseTag) _jspx_tagPool_c_choose.get(com.liferay.taglib.core.ChooseTag.class);
            _jspx_th_c_choose_1.setPageContext(_jspx_page_context);
            _jspx_th_c_choose_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_liferay$1ddm_template$1renderer_0);
            int _jspx_eval_c_choose_1 = _jspx_th_c_choose_1.doStartTag();
            if (_jspx_eval_c_choose_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              out.write('\n');
              out.write('	');
              //  c:when
              com.liferay.taglib.core.WhenTag _jspx_th_c_when_1 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
              _jspx_th_c_when_1.setPageContext(_jspx_page_context);
              _jspx_th_c_when_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
              _jspx_th_c_when_1.setTest( Validator.isNotNull(rssFeed.getURL()) && (syndFeed != null) );
              int _jspx_eval_c_when_1 = _jspx_th_c_when_1.doStartTag();
              if (_jspx_eval_c_when_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t<div class=\"feed ");
                out.print( last ? "last" : StringPool.BLANK );
                out.write("\">\n\t\t\t");
                //  aui:fieldset-group
                com.liferay.taglib.aui.FieldsetGroupTag _jspx_th_aui_fieldset$1group_0 = (com.liferay.taglib.aui.FieldsetGroupTag) _jspx_tagPool_aui_fieldset$1group_markupView.get(com.liferay.taglib.aui.FieldsetGroupTag.class);
                _jspx_th_aui_fieldset$1group_0.setPageContext(_jspx_page_context);
                _jspx_th_aui_fieldset$1group_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_1);
                _jspx_th_aui_fieldset$1group_0.setMarkupView("lexicon");
                int _jspx_eval_aui_fieldset$1group_0 = _jspx_th_aui_fieldset$1group_0.doStartTag();
                if (_jspx_eval_aui_fieldset$1group_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                  out.write("\n\t\t\t\t");
                  //  aui:fieldset
                  com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_0 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset.get(com.liferay.taglib.aui.FieldsetTag.class);
                  _jspx_th_aui_fieldset_0.setPageContext(_jspx_page_context);
                  _jspx_th_aui_fieldset_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset$1group_0);
                  int _jspx_eval_aui_fieldset_0 = _jspx_th_aui_fieldset_0.doStartTag();
                  if (_jspx_eval_aui_fieldset_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\n\t\t\t\t\t");

					SyndImage syndImage = syndFeed.getImage();
					
                    out.write("\n\n\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_1 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
                    _jspx_th_c_if_1.setTest( (syndImage != null) && rssPortletInstanceConfiguration.showFeedImage() );
                    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
                    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t<div class=\"feed-image\">\n\t\t\t\t\t\t\t<img alt=\"");
                      out.print( HtmlUtil.escapeAttribute(syndImage.getDescription()) );
                      out.write("\" class=\"img-responsive\" src=\"");
                      out.print( HtmlUtil.escapeHREF(rssFeed.getSyndFeedImageURL()) );
                      out.write("\" />\n\t\t\t\t\t\t</div>\n\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
                    out.write("\n\n\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_2 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_2.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
                    _jspx_th_c_if_2.setTest( rssPortletInstanceConfiguration.showFeedTitle() );
                    int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
                    if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t<h3 class=\"feed-title\">\n\t\t\t\t\t\t\t");
                      //  aui:a
                      com.liferay.taglib.aui.ATag _jspx_th_aui_a_1 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_target_href.get(com.liferay.taglib.aui.ATag.class);
                      _jspx_th_aui_a_1.setPageContext(_jspx_page_context);
                      _jspx_th_aui_a_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_2);
                      _jspx_th_aui_a_1.setHref( HtmlUtil.escapeJSLink(rssFeed.getSyndFeedLink()) );
                      _jspx_th_aui_a_1.setTarget("_blank");
                      int _jspx_eval_aui_a_1 = _jspx_th_aui_a_1.doStartTag();
                      if (_jspx_eval_aui_a_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.print( HtmlUtil.escape(rssFeed.getTitle()) );
                      }
                      if (_jspx_th_aui_a_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_aui_a_target_href.reuse(_jspx_th_aui_a_1);
                        return;
                      }
                      _jspx_tagPool_aui_a_target_href.reuse(_jspx_th_aui_a_1);
                      out.write("\n\t\t\t\t\t\t</h3>\n\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
                    out.write("\n\n\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_3 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_3.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
                    _jspx_th_c_if_3.setTest( (syndFeed.getPublishedDate() != null) && rssPortletInstanceConfiguration.showFeedDescription() );
                    int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
                    if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t<div class=\"feed-date feed-published-date small\">\n\t\t\t\t\t\t\t");
                      //  liferay-ui:icon
                      com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_0 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_message_markupView_label_icon_nobody.get(com.liferay.taglib.ui.IconTag.class);
                      _jspx_th_liferay$1ui_icon_0.setPageContext(_jspx_page_context);
                      _jspx_th_liferay$1ui_icon_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_3);
                      _jspx_th_liferay$1ui_icon_0.setIcon("calendar");
                      _jspx_th_liferay$1ui_icon_0.setLabel( true );
                      _jspx_th_liferay$1ui_icon_0.setMarkupView("lexicon");
                      _jspx_th_liferay$1ui_icon_0.setMessage( dateFormatDateTime.format(syndFeed.getPublishedDate()) );
                      int _jspx_eval_liferay$1ui_icon_0 = _jspx_th_liferay$1ui_icon_0.doStartTag();
                      if (_jspx_th_liferay$1ui_icon_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_liferay$1ui_icon_message_markupView_label_icon_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
                        return;
                      }
                      _jspx_tagPool_liferay$1ui_icon_message_markupView_label_icon_nobody.reuse(_jspx_th_liferay$1ui_icon_0);
                      out.write("\n\t\t\t\t\t\t</div>\n\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
                    out.write("\n\n\t\t\t\t\t");
                    //  c:if
                    com.liferay.taglib.core.IfTag _jspx_th_c_if_4 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                    _jspx_th_c_if_4.setPageContext(_jspx_page_context);
                    _jspx_th_c_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_0);
                    _jspx_th_c_if_4.setTest( Validator.isNotNull(syndFeed.getDescription()) && rssPortletInstanceConfiguration.showFeedDescription() );
                    int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
                    if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t<div class=\"feed-description\">\n\t\t\t\t\t\t\t");
                      out.print( HtmlUtil.escape(syndFeed.getDescription()) );
                      out.write("\n\t\t\t\t\t\t</div>\n\t\t\t\t\t");
                    }
                    if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                      return;
                    }
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
                    out.write("\n\t\t\t\t");
                  }
                  if (_jspx_th_aui_fieldset_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
                    return;
                  }
                  _jspx_tagPool_aui_fieldset.reuse(_jspx_th_aui_fieldset_0);
                  out.write("\n\n\t\t\t\t");

				List<RSSFeedEntry> rssFeedEntryDisplayContexts = rssFeed.getRSSFeedEntries(themeDisplay);
				
                  out.write("\n\n\t\t\t\t");
                  //  c:if
                  com.liferay.taglib.core.IfTag _jspx_th_c_if_5 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                  _jspx_th_c_if_5.setPageContext(_jspx_page_context);
                  _jspx_th_c_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset$1group_0);
                  _jspx_th_c_if_5.setTest( !rssFeedEntryDisplayContexts.isEmpty() );
                  int _jspx_eval_c_if_5 = _jspx_th_c_if_5.doStartTag();
                  if (_jspx_eval_c_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                    out.write("\n\n\t\t\t\t\t");

					for (int j = 0; (j < rssFeedEntryDisplayContexts.size()) && (j < rssPortletInstanceConfiguration.entriesPerFeed()); j++) {
						RSSFeedEntry rssFeedEntry = rssFeedEntryDisplayContexts.get(j);

						SyndEntry syndEntry = rssFeedEntry.getSyndEntry();
					
                    out.write("\n\n\t\t\t\t\t\t");
                    //  aui:fieldset
                    com.liferay.taglib.aui.FieldsetTag _jspx_th_aui_fieldset_1 = (com.liferay.taglib.aui.FieldsetTag) _jspx_tagPool_aui_fieldset_label_id_cssClass_collapsible_collapsed.get(com.liferay.taglib.aui.FieldsetTag.class);
                    _jspx_th_aui_fieldset_1.setPageContext(_jspx_page_context);
                    _jspx_th_aui_fieldset_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_5);
                    _jspx_th_aui_fieldset_1.setCollapsed( themeDisplay.isStateMaximized() || (j < rssPortletInstanceConfiguration.expandedEntriesPerFeed()) );
                    _jspx_th_aui_fieldset_1.setCollapsible( true );
                    _jspx_th_aui_fieldset_1.setCssClass("feed-entry");
                    _jspx_th_aui_fieldset_1.setId( renderResponse.getNamespace() + "feed_" + i + "_panelId_" + j );
                    _jspx_th_aui_fieldset_1.setLabel( HtmlUtil.escape(syndEntry.getTitle()) );
                    int _jspx_eval_aui_fieldset_1 = _jspx_th_aui_fieldset_1.doStartTag();
                    if (_jspx_eval_aui_fieldset_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                      out.write("\n\t\t\t\t\t\t\t<div class=\"feed-entry-content\">\n\t\t\t\t\t\t\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_6 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_6.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
                      _jspx_th_c_if_6.setTest( rssPortletInstanceConfiguration.showFeedItemAuthor() && Validator.isNotNull(syndEntry.getAuthor()) );
                      int _jspx_eval_c_if_6 = _jspx_th_c_if_6.doStartTag();
                      if (_jspx_eval_c_if_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t<strong class=\"feed-entry-author small\">\n\t\t\t\t\t\t\t\t\t\t");
                        out.print( HtmlUtil.escape(syndEntry.getAuthor()) );
                        out.write("\n\t\t\t\t\t\t\t\t\t</strong>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_if_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_6);
                      out.write("\n\n\t\t\t\t\t\t\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_7 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_7.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
                      _jspx_th_c_if_7.setTest( syndEntry.getPublishedDate() != null );
                      int _jspx_eval_c_if_7 = _jspx_th_c_if_7.doStartTag();
                      if (_jspx_eval_c_if_7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t<div class=\"feed-date small\">\n\t\t\t\t\t\t\t\t\t\t");
                        //  liferay-ui:icon
                        com.liferay.taglib.ui.IconTag _jspx_th_liferay$1ui_icon_1 = (com.liferay.taglib.ui.IconTag) _jspx_tagPool_liferay$1ui_icon_message_markupView_label_icon_nobody.get(com.liferay.taglib.ui.IconTag.class);
                        _jspx_th_liferay$1ui_icon_1.setPageContext(_jspx_page_context);
                        _jspx_th_liferay$1ui_icon_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_7);
                        _jspx_th_liferay$1ui_icon_1.setIcon("calendar");
                        _jspx_th_liferay$1ui_icon_1.setLabel( true );
                        _jspx_th_liferay$1ui_icon_1.setMarkupView("lexicon");
                        _jspx_th_liferay$1ui_icon_1.setMessage( dateFormatDateTime.format(syndEntry.getPublishedDate()) );
                        int _jspx_eval_liferay$1ui_icon_1 = _jspx_th_liferay$1ui_icon_1.doStartTag();
                        if (_jspx_th_liferay$1ui_icon_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_liferay$1ui_icon_message_markupView_label_icon_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
                          return;
                        }
                        _jspx_tagPool_liferay$1ui_icon_message_markupView_label_icon_nobody.reuse(_jspx_th_liferay$1ui_icon_1);
                        out.write("\n\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_if_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_7);
                      out.write("\n\n\t\t\t\t\t\t\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_8 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_8.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
                      _jspx_th_c_if_8.setTest( Validator.isNotNull(rssFeedEntry.getSyndEnclosureLink()) );
                      int _jspx_eval_c_if_8 = _jspx_th_c_if_8.doStartTag();
                      if (_jspx_eval_c_if_8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t<div class=\"feed-entry-enclosure\">\n\t\t\t\t\t\t\t\t\t\t");
                        //  aui:a
                        com.liferay.taglib.aui.ATag _jspx_th_aui_a_2 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_target_href.get(com.liferay.taglib.aui.ATag.class);
                        _jspx_th_aui_a_2.setPageContext(_jspx_page_context);
                        _jspx_th_aui_a_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_8);
                        _jspx_th_aui_a_2.setHref( HtmlUtil.escapeJSLink(rssFeedEntry.getSyndEnclosureLink()) );
                        _jspx_th_aui_a_2.setTarget("_blank");
                        int _jspx_eval_aui_a_2 = _jspx_th_aui_a_2.doStartTag();
                        if (_jspx_eval_aui_a_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          out.print( HtmlUtil.escape(rssFeedEntry.getSyndEnclosureLinkTitle()) );
                        }
                        if (_jspx_th_aui_a_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_a_target_href.reuse(_jspx_th_aui_a_2);
                          return;
                        }
                        _jspx_tagPool_aui_a_target_href.reuse(_jspx_th_aui_a_2);
                        out.write("\n\t\t\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_if_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_8);
                      out.write("\n\n\t\t\t\t\t\t\t\t");
                      out.print( rssFeedEntry.getSanitizedContent() );
                      out.write("\n\n\t\t\t\t\t\t\t\t<br />\n\n\t\t\t\t\t\t\t\t");
                      //  c:if
                      com.liferay.taglib.core.IfTag _jspx_th_c_if_9 = (com.liferay.taglib.core.IfTag) _jspx_tagPool_c_if_test.get(com.liferay.taglib.core.IfTag.class);
                      _jspx_th_c_if_9.setPageContext(_jspx_page_context);
                      _jspx_th_c_if_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_fieldset_1);
                      _jspx_th_c_if_9.setTest( Validator.isNotNull(rssFeedEntry.getSyndEntryLink()) );
                      int _jspx_eval_c_if_9 = _jspx_th_c_if_9.doStartTag();
                      if (_jspx_eval_c_if_9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                        out.write("\n\t\t\t\t\t\t\t\t\t");
                        //  aui:a
                        com.liferay.taglib.aui.ATag _jspx_th_aui_a_3 = (com.liferay.taglib.aui.ATag) _jspx_tagPool_aui_a_href.get(com.liferay.taglib.aui.ATag.class);
                        _jspx_th_aui_a_3.setPageContext(_jspx_page_context);
                        _jspx_th_aui_a_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_if_9);
                        _jspx_th_aui_a_3.setHref( HtmlUtil.escapeJSLink(rssFeedEntry.getSyndEntryLink()) );
                        int _jspx_eval_aui_a_3 = _jspx_th_aui_a_3.doStartTag();
                        if (_jspx_eval_aui_a_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                          if (_jspx_meth_liferay$1ui_message_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_aui_a_3, _jspx_page_context))
                            return;
                        }
                        if (_jspx_th_aui_a_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                          _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_3);
                          return;
                        }
                        _jspx_tagPool_aui_a_href.reuse(_jspx_th_aui_a_3);
                        out.write("\n\t\t\t\t\t\t\t\t");
                      }
                      if (_jspx_th_c_if_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
                        return;
                      }
                      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_9);
                      out.write("\n\t\t\t\t\t\t\t</div>\n\t\t\t\t\t\t");
                    }
                    if (_jspx_th_aui_fieldset_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      _jspx_tagPool_aui_fieldset_label_id_cssClass_collapsible_collapsed.reuse(_jspx_th_aui_fieldset_1);
                      return;
                    }
                    _jspx_tagPool_aui_fieldset_label_id_cssClass_collapsible_collapsed.reuse(_jspx_th_aui_fieldset_1);
                    out.write("\n\n\t\t\t\t\t");

					}
					
                    out.write("\n\n\t\t\t\t");
                  }
                  if (_jspx_th_c_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                    return;
                  }
                  _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_5);
                  out.write("\n\t\t\t");
                }
                if (_jspx_th_aui_fieldset$1group_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_aui_fieldset$1group_markupView.reuse(_jspx_th_aui_fieldset$1group_0);
                  return;
                }
                _jspx_tagPool_aui_fieldset$1group_markupView.reuse(_jspx_th_aui_fieldset$1group_0);
                out.write("\n\t\t</div>\n\t");
              }
              if (_jspx_th_c_when_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
                return;
              }
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_1);
              out.write('\n');
              out.write('	');
              //  c:when
              com.liferay.taglib.core.WhenTag _jspx_th_c_when_2 = (com.liferay.taglib.core.WhenTag) _jspx_tagPool_c_when_test.get(com.liferay.taglib.core.WhenTag.class);
              _jspx_th_c_when_2.setPageContext(_jspx_page_context);
              _jspx_th_c_when_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_1);
              _jspx_th_c_when_2.setTest( portletDisplay.isShowConfigurationIcon() );
              int _jspx_eval_c_when_2 = _jspx_th_c_when_2.doStartTag();
              if (_jspx_eval_c_when_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                out.write("\n\t\t<div class=\"alert alert-danger\">\n\t\t\t");
                //  liferay-ui:message
                com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_3 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.get(com.liferay.taglib.ui.MessageTag.class);
                _jspx_th_liferay$1ui_message_3.setPageContext(_jspx_page_context);
                _jspx_th_liferay$1ui_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_2);
                _jspx_th_liferay$1ui_message_3.setArguments( HtmlUtil.escape(rssFeed.getURL()) );
                _jspx_th_liferay$1ui_message_3.setKey("cannot-be-found");
                _jspx_th_liferay$1ui_message_3.setTranslateArguments( false );
                int _jspx_eval_liferay$1ui_message_3 = _jspx_th_liferay$1ui_message_3.doStartTag();
                if (_jspx_th_liferay$1ui_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                  _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_3);
                  return;
                }
                _jspx_tagPool_liferay$1ui_message_translateArguments_key_arguments_nobody.reuse(_jspx_th_liferay$1ui_message_3);
                out.write("\n\t\t</div>\n\t");
              }
              if (_jspx_th_c_when_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
                return;
              }
              _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_2);
              out.write('\n');
            }
            if (_jspx_th_c_choose_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
              return;
            }
            _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_1);
            out.write("\n\n\t\t\t");

			}
			
            out.write("\n\n\t\t");
          }
          if (_jspx_th_liferay$1ddm_template$1renderer_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_liferay$1ddm_template$1renderer_entries_displayStyleGroupId_displayStyle_contextObjects_className.reuse(_jspx_th_liferay$1ddm_template$1renderer_0);
            return;
          }
          _jspx_tagPool_liferay$1ddm_template$1renderer_entries_displayStyleGroupId_displayStyle_contextObjects_className.reuse(_jspx_th_liferay$1ddm_template$1renderer_0);
          out.write('\n');
          out.write('	');
        }
        if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
          return;
        }
        _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
        out.write('\n');
      }
      if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
        return;
      }
      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
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

  private boolean _jspx_meth_liferay$1ui_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_0 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_0.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
    _jspx_th_liferay$1ui_message_0.setKey("this-application-is-not-visible-to-users-yet");
    int _jspx_eval_liferay$1ui_message_0 = _jspx_th_liferay$1ui_message_0.doStartTag();
    if (_jspx_th_liferay$1ui_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_0);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_1(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_a_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_1 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_1.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_a_0);
    _jspx_th_liferay$1ui_message_1.setKey("select-at-least-one-valid-rss-feed-to-make-it-visible");
    int _jspx_eval_liferay$1ui_message_1 = _jspx_th_liferay$1ui_message_1.doStartTag();
    if (_jspx_th_liferay$1ui_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_1);
    return false;
  }

  private boolean _jspx_meth_liferay$1ui_message_2(javax.servlet.jsp.tagext.JspTag _jspx_th_aui_a_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay$1ui_message_2 = (com.liferay.taglib.ui.MessageTag) _jspx_tagPool_liferay$1ui_message_key_nobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay$1ui_message_2.setPageContext(_jspx_page_context);
    _jspx_th_liferay$1ui_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_aui_a_3);
    _jspx_th_liferay$1ui_message_2.setKey("read-more");
    int _jspx_eval_liferay$1ui_message_2 = _jspx_th_liferay$1ui_message_2.doStartTag();
    if (_jspx_th_liferay$1ui_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
      return true;
    }
    _jspx_tagPool_liferay$1ui_message_key_nobody.reuse(_jspx_th_liferay$1ui_message_2);
    return false;
  }
}
